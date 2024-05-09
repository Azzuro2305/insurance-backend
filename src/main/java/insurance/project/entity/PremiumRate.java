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
public class PremiumRate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int coveragePlan;
    private int fromAge;
    private int toAge;
    private double rate;
    private String countryName;
    private String packages;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;
}
