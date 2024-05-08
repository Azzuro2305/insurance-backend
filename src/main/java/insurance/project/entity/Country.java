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
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String countryName;
    private String countryCode;
    private String shortCountryName;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int version;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<InsuredPerson> insuredPersons;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Beneficiary> beneficiaries;
}
