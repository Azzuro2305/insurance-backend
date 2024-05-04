package insurance.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID currencyID;
    private String currencyName;
    private String description;

//    private UUID createdUserID;
//    private UUID updatedUserID;

    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    @OneToMany(mappedBy = "currency")
    private List<PremiumRate> premiumRate;

}
