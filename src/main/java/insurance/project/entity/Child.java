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
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String childName;
    private LocalDate childDOB;
    private String childGender;
    private String guardianceName;
    private String childRelationship;

    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;;

    @ManyToOne
    @JoinColumn(name = "insured_person_id")
    private InsuredPerson insuredPerson;;
}
