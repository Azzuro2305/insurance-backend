package insurance.project.service.impl;

import insurance.project.dto.NewInsurance.NewInsurance;
import insurance.project.entity.*;
import insurance.project.repo.*;
import insurance.project.service.InsuredPersonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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



    @Transactional
    @Override
    public NewInsurance registerInsurance(NewInsurance newInsurance) {
        Optional<InsuredPerson> optionalInsuredPerson = insuredRepo.findByPassportNumber(newInsurance.getPassportNumber());

//        if (optionalInsuredPerson.isPresent()) {
//            InsuredPerson insuredPerson = optionalInsuredPerson.get();
//            OutboundProposal newOutboundProposal = createAndSaveOutboundProposal(newInsurance, insuredPerson);
//            insuredPerson.setOutboundProposal(newOutboundProposal);
//            createAndSaveChild(newInsurance, insuredPerson);
//            createAndSaveBeneficiary(newInsurance, insuredPerson);
//            insuredRepo.save(insuredPerson);
//            return modelMapper.map(newInsurance, NewInsurance.class);
//        } else {
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
//        }
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
//            if (insuredPerson.getChildren() == null) {
//                insuredPerson.setChildren(new ArrayList<>());
//            }
//            insuredPerson.getChildren().add(newChild);
        }
    }


    private void createAndSaveBeneficiary(NewInsurance newInsurance, InsuredPerson insuredPerson) {
        Beneficiary newBeneficiary = modelMapper.map(newInsurance, Beneficiary.class);
        newBeneficiary.setCreatedDate(LocalDate.now());
        newBeneficiary.setUpdatedDate(LocalDate.now());
        newBeneficiary.setInsuredPerson(insuredPerson);
//        newBeneficiary.setCountry(countryRepo.findByCountryID(newInsurance.getBeneficiaryPhoneCode()));
        newBeneficiary.setCountry(countryRepo.findByCountryCode(newInsurance.getBeneficiaryPhoneCode()));
        newBeneficiary.setVersion(1);
        newBeneficiary.setInsuredPerson(insuredPerson);
        beneficiaryRepo.save(newBeneficiary);
//        insuredPerson.setBeneficiary(newBeneficiary);
    }


    private OutboundProposal createAndSaveOutboundProposal(NewInsurance newInsurance, InsuredPerson insuredPerson) {
        OutboundProposal newOutboundProposal = modelMapper.map(newInsurance, OutboundProposal.class);
        newOutboundProposal.setSubmittedDate(LocalDate.now());
        newOutboundProposal.setCreatedDate(LocalDate.now());
        newOutboundProposal.setUpdatedDate(LocalDate.now());
//        newOutboundProposal.setInsuredPerson(insuredPerson);
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










//    @Transactional
//    @Override
//    public InsuredPerson registerInsurance(NewInsuranceRequest newInsuranceRequest) {
//        Beneficiary newBeneficiaryPerson = Beneficiary.builder()
//                .name(newInsuranceRequest.getBeneficiaryName())
//                .dob(newInsuranceRequest.getBeneficiaryDob())
//                .relationship(newInsuranceRequest.getBeneficiaryRelationship())
//                .contactNumber(newInsuranceRequest.getBeneficiaryContactNumber())
//                .nationalIdentificationNumber(newInsuranceRequest.getBeneficiaryNationalIdentificationNumber())
//                .email(newInsuranceRequest.getBeneficiaryEmail())
//                .address(newInsuranceRequest.getBeneficiaryAddress())
//                .createdTime(LocalDateTime.now())
//                .updatedTime(LocalDateTime.now())
//                .isDeleted(false)
//                .build();
//
//        if (newInsuranceRequest.isHasChild()) {
//            Child newChild = Child.builder()
//                    .name(newInsuranceRequest.getChildName())
//                    .dob(newInsuranceRequest.getChildDob())
//                    .gender(newInsuranceRequest.getChildGender())
//                    .guardianceName(newInsuranceRequest.getChildGuardianceName())
//                    .relationship(newInsuranceRequest.getChildRelationship())
//                    .createdTime(LocalDateTime.now())
//                    .updatedTime(LocalDateTime.now())
//                    .isDeleted(false)
//                    .build();
//        }
//
//        InsuredPerson newInsuredPerson = new InsuredPerson();
//        newInsuredPerson.setPassportNumber(newInsuranceRequest.getPassportNumber());
//        newInsuredPerson.setPassportIssuedDate(newInsuranceRequest.getPassportIssuedDate());
//        newInsuredPerson.setPassportIssuedCountry(newInsuranceRequest.getPassportIssuedCountry());
//        newInsuredPerson.setName(newInsuranceRequest.getName());
//        newInsuredPerson.setDob(newInsuranceRequest.getDob());
//        newInsuredPerson.setGender(newInsuranceRequest.getGender());
//        newInsuredPerson.setEstimatedDepartureDate(newInsuranceRequest.getEstimatedDepartureDate());
//        newInsuredPerson.setJourneyFrom("MYANMAR");
//        newInsuredPerson.setJourneyTo(newInsuranceRequest.getJourneyTo());
//        newInsuredPerson.setPolicyStartDate(newInsuranceRequest.getPolicyStartDate());
//        newInsuredPerson.setCoverageDuration(newInsuranceRequest.getCoverageDuration());
//        newInsuredPerson.setInsurancePackage(newInsuranceRequest.getInsurancePackage());
//        newInsuredPerson.setContactNumber(newInsuranceRequest.getContactNumber());
//        newInsuredPerson.setForeignContactNumber(newInsuranceRequest.getForeignContactNumber());
//        newInsuredPerson.setFatherName(newInsuranceRequest.getFatherName());
//        newInsuredPerson.setRace(newInsuranceRequest.getRace());
//        newInsuredPerson.setMaritalStatus(newInsuranceRequest.getMaritalStatus());
//        newInsuredPerson.setEmail(newInsuranceRequest.getEmail());
//        newInsuredPerson.setAddressInMyanmar(newInsuranceRequest.getAddressInMyanmar());
//        newInsuredPerson.setAddressInForeign(newInsuranceRequest.getAddressInForeign());
//        newInsuredPerson.setDestinationCountry(newInsuranceRequest.getDestinationCountry());
//        newInsuredPerson.setBeneficiary(newBeneficiaryPerson);
//
//        if (newInsuranceRequest.isHasAgent()) {
//            Optional<Agent> optionalAgent = agentRepo.findAgentByLicenseNumber(newInsuranceRequest.getAgentLicenseNumber());
//            if (optionalAgent.isPresent()) {
//                Agent agent = optionalAgent.get();
//                newInsuredPerson.setAgent(agent);
//            } else {
//                throw new RuntimeException("Agent not found");
//            }
//        }
//
//        if (newInsuranceRequest.isHasChild()) {
//            Child newChild = Child.builder()
//                    .name(newInsuranceRequest.getChildName())
//                    .dob(newInsuranceRequest.getChildDob())
//                    .gender(newInsuranceRequest.getChildGender())
//                    .guardianceName(newInsuranceRequest.getChildGuardianceName())
//                    .relationship(newInsuranceRequest.getChildRelationship())
//                    .createdTime(LocalDateTime.now())
//                    .updatedTime(LocalDateTime.now())
//                    .isDeleted(false)
//                    .build();
//            newInsuredPerson.setChild(newChild);
//            childRepo.save(newChild);
//        }
//
//        newInsuredPerson.setCreatedTime(LocalDateTime.now());
//        newInsuredPerson.setUpdatedTime(LocalDateTime.now());
//        newInsuredPerson.setDeleted(false);
//
//        beneficiaryRepo.save(newBeneficiaryPerson);
//        return(insuredRepo.save(newInsuredPerson));
//    }


}
