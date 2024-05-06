package insurance.project.dto.NewInsurance;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class NewInsurance {

    // Insured Person Information
    private String passportNumber;
    private LocalDate passportIssuedDate;
    private UUID passportIssuedCountry; // Country ID
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
    private String isChild; // bind the value from the selected option
    private String destinationCountry;  // no need to bind this data

    // OutBound Proposal Information
    private LocalDate estimateDepartureDate;
    private String journeyTo;
    private String journeyFrom;
    private LocalDate policyStartDate;
    private int coveragePlan;
    private String packages;
//    private LocalDate policyEndDate;  // calculate from policyStartDate in backend


    // Beneficiary Information
    private String beneficiaryName;
    private LocalDate beneficiaryDOB;
    private String beneficiaryRelationship;
    private UUID beneficiaryPhoneCode;  // check
    private String beneficiaryPhoneNumber;
    private String beneficiaryNRC;
    private String beneficiaryEmail;
    private String beneficiaryAddress;

    // Child Information
    private String childName;
    private LocalDate childDOB;
    private String childGender;
    private String guardianceName;
    private String childRelationship;

    // Agent Information
    private String agentName;
    private String agentPassword;
    private String agentLicenseNumber;

    // check
    private boolean hasChild;
    private boolean hasAgent;
}
