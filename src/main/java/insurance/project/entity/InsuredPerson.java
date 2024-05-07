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

    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    @OneToMany(mappedBy = "insuredPerson", cascade = CascadeType.ALL)
    private List<OutboundProposal> outboundProposals;

    @OneToMany(mappedBy = "insuredPerson", cascade = CascadeType.ALL)
    private List<Child> children;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "beneficiaryID")
    private Beneficiary beneficiary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_issued_country_id")
    private Country country;
}
