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
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID childID;

    private String childName;
    private LocalDate childDOB;
    private String childGender;
    private String guardianceName;
    private String childRelationship;

//    private UUID insuredPersonID;
//    private UUID createdUserID;
//    private UUID updatedUserID;

    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;
}
