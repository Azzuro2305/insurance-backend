package insurance.project.dto.CalculatePremium;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatePremium {
    private String packages;
    private int coveragePlan;
    private int fromAge;
    private int toAge;
}
