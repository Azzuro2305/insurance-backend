package insurance.project.service.impl;

import insurance.project.dto.InsuredData.InsuredData;
import insurance.project.dto.NewInsurance.NewInsurance;
import insurance.project.entity.*;
import insurance.project.repo.*;
import insurance.project.service.InsuredPersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InsuredPersonServiceImpl implements InsuredPersonService {

    private final InsuredPersonRepo insuredRepo;
    private final OutboundProposalRepo outboundProposalRepo;
    private final BeneficiaryRepo beneficiaryRepo;
    private final ChildRepo childRepo;
    private final AgentRepo agentRepo;
    private final PremiumRateRepo premiumRateRepo;
    private final CountryRepo countryRepo;

    private final ModelMapper modelMapper;


    @Override
    public List<InsuredData> getInsuredData(String passportNumber, UUID passportIssuedCountry) {
        List<InsuredPerson> insuredPersons = insuredRepo.findByPassportNumberAndCountryId(passportNumber, passportIssuedCountry);
        if (insuredPersons != null && !insuredPersons.isEmpty()) {
            List<InsuredData> insuredDataList = new ArrayList<>();
            for (InsuredPerson insuredPerson : insuredPersons) {
                InsuredData insuredData = modelMapper.map(insuredPerson, InsuredData.class);
                insuredData.setInsuredDOB(insuredPerson.getInsuredDOB());
                insuredData.setInsuredPhoneNumber(insuredPerson.getInsuredPhoneNumber());
                insuredData.setPassportNumber(insuredPerson.getPassportNumber());


                insuredData.setInsuredAge(String.valueOf(LocalDate.now().getYear() - insuredPerson.getInsuredDOB().getYear()));
                insuredData.setPassportIssuedCountry(insuredPerson.getCountry().getCountryName());

                OutboundProposal outboundProposal = insuredPerson.getOutboundProposal();
                insuredData.setPaymentDate(outboundProposal.getCreatedDate());
                modelMapper.map(outboundProposal, insuredData);

                PremiumRate premiumRate = outboundProposal.getPremiumRate();
                modelMapper.map(premiumRate, insuredData);

                Agent agent = outboundProposal.getAgent();
                if (agent != null) {
                    modelMapper.map(agent, insuredData);
                    insuredData.setHasAgent(true);
                } else {
                    insuredData.setHasAgent(false);
                }

                Child child = childRepo.findByInsuredPersonId(insuredPerson.getId());
                if (child != null) {
                    insuredData.setChildDOB(child.getChildDOB());
                    insuredData.setChildName(child.getChildName());
                    insuredData.setChildAge(String.valueOf(LocalDate.now().getYear() - child.getChildDOB().getYear()));
                    insuredData.setHasChild(true);
                } else {
                    insuredData.setHasChild(false);
                }

                insuredDataList.add(insuredData);
            }
            return insuredDataList;

        }
        return null;
    }


    @Transactional
    @Override
    public NewInsurance registerInsurance(NewInsurance newInsurance) {
            InsuredPerson newInsuredPerson = modelMapper.map(newInsurance, InsuredPerson.class);
            newInsuredPerson.setCreatedDate(LocalDate.now());
            newInsuredPerson.setUpdatedDate(LocalDate.now());
            newInsuredPerson.setCountry(countryRepo.findById(newInsurance.getPassportIssuedCountry()).orElseThrow(() -> new RuntimeException("Country not found")));
            newInsuredPerson.setVersion(1);
            OutboundProposal newOutboundProposal = createAndSaveOutboundProposal(newInsurance, newInsuredPerson);
            newInsuredPerson.setOutboundProposal(newOutboundProposal);
            createAndSaveChild(newInsurance, newInsuredPerson);
            createAndSaveBeneficiary(newInsurance, newInsuredPerson);

            insuredRepo.save(newInsuredPerson);
            return modelMapper.map(newInsurance, NewInsurance.class);
    }




    private void createAndSaveChild(NewInsurance newInsurance, InsuredPerson insuredPerson) {
        if (newInsurance.isHasChild()) {
            Child newChild = modelMapper.map(newInsurance, Child.class);
            newChild.setCreatedDate(LocalDate.now());
            newChild.setUpdatedDate(LocalDate.now());
            newChild.setInsuredPerson(insuredPerson);
            newChild.setVersion(1);
            insuredRepo.save(insuredPerson);
            childRepo.save(newChild);
        }
    }


    private void createAndSaveBeneficiary(NewInsurance newInsurance, InsuredPerson insuredPerson) {
        Beneficiary newBeneficiary = modelMapper.map(newInsurance, Beneficiary.class);
        newBeneficiary.setCreatedDate(LocalDate.now());
        newBeneficiary.setUpdatedDate(LocalDate.now());
        newBeneficiary.setInsuredPerson(insuredPerson);
        newBeneficiary.setCountry(countryRepo.findByCountryCode(newInsurance.getBeneficiaryPhoneCode()));
        newBeneficiary.setVersion(1);
        newBeneficiary.setInsuredPerson(insuredPerson);
        beneficiaryRepo.save(newBeneficiary);
    }


    private OutboundProposal createAndSaveOutboundProposal(NewInsurance newInsurance, InsuredPerson insuredPerson) {
        OutboundProposal newOutboundProposal = modelMapper.map(newInsurance, OutboundProposal.class);
        newOutboundProposal.setSubmittedDate(LocalDate.now());
        newOutboundProposal.setCreatedDate(LocalDate.now());
        newOutboundProposal.setUpdatedDate(LocalDate.now());
        newOutboundProposal.setPolicyEndDate(newInsurance.getPolicyStartDate().plusDays(newInsurance.getCoveragePlan()));
        newOutboundProposal.setSubmittedDate(LocalDate.now());
        newOutboundProposal.setVersion(1);

        int insuredPersonAge = LocalDate.now().getYear() - insuredPerson.getInsuredDOB().getYear();
        int[] ageRange = getAgeRange(insuredPersonAge);
        newOutboundProposal.setPremiumRate(premiumRateRepo.findPremiumRateByPackageAndCoveragePlanAndAgeRange(newInsurance.getPackages(), newInsurance.getCoveragePlan(), ageRange[0], ageRange[1])
                .orElseThrow(() -> new RuntimeException("Premium rate not found")));
        newOutboundProposal.setRate(newOutboundProposal.getPremiumRate().getRate());

        if (newInsurance.isHasAgent()) {
            Optional<Agent> optionalAgent = agentRepo.findAgentByAgentLicense(newInsurance.getAgentLicenseNumber());
            if (optionalAgent.isPresent()) {
                Agent agent = optionalAgent.get();
                agentRepo.save(agent);
                newOutboundProposal.setAgent(agent);
            } else {
                throw new RuntimeException("Agent not found");

            }
        } else {
            newOutboundProposal.setAgent(null);
        }
        return outboundProposalRepo.save(newOutboundProposal);
    }


    public int[] getAgeRange(int insuredPersonAge) {
        int fromAge = insuredPersonAge >= 1 && insuredPersonAge <= 50 ? 1 :
                insuredPersonAge >= 51 && insuredPersonAge <= 60 ? 51 :
                        insuredPersonAge >= 61 && insuredPersonAge <= 75 ? 61 :
                                insuredPersonAge >= 75 ? insuredPersonAge : -1;

        int toAge = insuredPersonAge >= 1 && insuredPersonAge <= 50 ? 50 :
                insuredPersonAge >= 51 && insuredPersonAge <= 60 ? 60 :
                        insuredPersonAge >= 61 && insuredPersonAge <= 75 ? 75 :
                                insuredPersonAge > 75 ? insuredPersonAge : -1;

        if (fromAge == -1) {
            throw new IllegalArgumentException("Invalid age: " + insuredPersonAge);
        }

        return new int[]{fromAge, toAge};
    }
}
