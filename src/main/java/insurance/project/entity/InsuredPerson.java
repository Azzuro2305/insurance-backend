package insurance.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID insuredPersonID;

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

//    private UUID beneficiaryID;
//    private UUID createdUserID;
//    private UUID updatedUserID;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

//    @OneToOne
//    @JoinColumn(name = "agent_id")
//    private Agent agent;
//
//    @OneToOne
//    @JoinColumn(name = "beneficiary_id")
//    private Beneficiary beneficiary;
//
//    @OneToOne
//    @JoinColumn(name = "child_id")
//    private Child child;
//
    @OneToMany(mappedBy = "insuredPerson")
    private List<OutboundProposal> outboundProposal;

}
