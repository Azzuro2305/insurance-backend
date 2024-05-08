package insurance.project.dto.CountryResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CountryResponse {
    private UUID id;
    private String countryName;
    private String countryCode;
    private String shortCountryName;
}
