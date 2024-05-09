package insurance.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String beneficiaryName;
    private LocalDate beneficiaryDOB;
    private String beneficiaryRelationship;
    private String beneficiaryPhoneNumber;
    private String beneficiaryNRC;
    private String beneficiaryEmail;
    private String beneficiaryAddress;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "insured_person_id")
    private InsuredPerson insuredPerson;
}
