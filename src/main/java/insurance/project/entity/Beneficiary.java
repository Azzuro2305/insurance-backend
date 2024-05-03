package insurance.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private UUID beneficiaryID;

    private String beneficiaryName;
    private LocalDate beneficiaryDOB;
    private String beneficiaryRelationship;
    private String beneficiaryPhoneNumber;
    private String beneficiaryNRC;
    private String beneficiaryEmail;
    private String beneficiaryAddress;

//    private UUID countryID;
//    private UUID createdUserID;
//    private UUID updatedUserID;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;
}
