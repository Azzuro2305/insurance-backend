package insurance.project.service.impl;

import insurance.project.repo.AgentRepo;
import insurance.project.repo.BeneficiaryRepo;
import insurance.project.repo.ChildRepo;
import insurance.project.repo.InsuredPersonRepo;
import insurance.project.service.InsuredPersonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuredPersonServiceImpl implements InsuredPersonService {

    private final InsuredPersonRepo insuredRepo;
    private final BeneficiaryRepo beneficiaryRepo;
    private final ChildRepo childRepo;
    private final AgentRepo agentRepo;

    private final ModelMapper modelMapper;

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
