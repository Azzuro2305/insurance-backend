package insurance.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID countryID;
    private String countryName;
    private String countryCode;
    private String shortCountryName;
    private LocalDate createdDate;
    private LocalDate updatedDate;
//    private UUID createdUserID;
//    private UUID updatedUserID;
    private int version;
}
