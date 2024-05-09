package insurance.project.dto.InsuredData;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class InsuredData {
    // Insured Person Information
    private String insuredName;
    private String insuredAge; // manual
    private LocalDate insuredDOB;
    private String insuredPhoneNumber;
    private String passportNumber;
    private String passportIssuedCountry; // manual

    // Proposal Information
    private int coveragePlan;
    private String policyStartDate;
    private LocalDate policyEndDate;
    private double rate;
    private String destinationCountry;
    private int deductibleClaim;  // manual based on package
    private LocalDate paymentDate; // manual
    private String currency;
    private UUID proposalID;

    // Agency Information
    private String agentName;
    private boolean hasAgent; // manual



    // Child Information
    private String childName;
    private boolean hasChild; // manual
    private String childAge; // manual
    private LocalDate childDOB;
}
