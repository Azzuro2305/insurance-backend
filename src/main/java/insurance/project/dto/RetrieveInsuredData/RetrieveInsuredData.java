package insurance.project.dto.RetrieveInsuredData;

import insurance.project.entity.Beneficiary;
import insurance.project.entity.Child;
import insurance.project.entity.OutboundProposal;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class RetrieveInsuredData {
    private UUID id;

    private String insuredName;
    private LocalDate insuredDOB;
    private String insuredGender;
    private String insuredPhoneNumber;
    private String foreignContactNumber;
    private String fatherName;
    private String race;
    private String occupation;
    private String maritalStatus;
    private String insuredEmail;
    private String insuredAddress;
    private String insuredAddressAbroad;
    private LocalDate passportIssuedDate;
    private String passportNumber;
    private String isChild;

    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    Optional<OutboundProposal> outboundProposals;
    Optional<Child> children;
    Optional<Beneficiary> beneficiaries;
}
