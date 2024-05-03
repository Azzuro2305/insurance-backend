package insurance.project.dto.NewInsuranceRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class NewInsuranceRequest {
    private String passportNumber;
    private LocalDate passportIssuedDate;
    private String passportIssuedCountry;
    private String name;
    private LocalDate dob;
    private String gender;
    private LocalDate estimatedDepartureDate;
//    private String journeyFrom;
    private String journeyTo;
    private LocalDate policyStartDate;
    private String coverageDuration;
    private String insurancePackage;
    private String contactNumber;
    private String foreignContactNumber;
    private String fatherName;
    private String race;
    private String maritalStatus;
    private String email;
    private String addressInMyanmar;
    private String addressInForeign;
    private String destinationCountry;

    // Beneficiary Information
    private String beneficiaryName;
    private LocalDate beneficiaryDob;
    private String beneficiaryRelationship;
    private String beneficiaryContactNumber;
    private String beneficiaryNationalIdentificationNumber;
    private String beneficiaryEmail;
    private String beneficiaryAddress;

    // Child Information
    private String childName;
    private LocalDate childDob;
    private String childGender;
    private String childGuardianceName;
    private String childRelationship;

    // Agent Information
    private String agentName;
    private String agentLicenseNumber;

    // check
    private boolean hasChild;
    private boolean hasAgent;
}
