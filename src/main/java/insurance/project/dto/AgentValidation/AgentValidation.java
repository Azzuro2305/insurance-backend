package insurance.project.dto.AgentValidation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentValidation {
    private String agentName;
    private String agentLicense;
    private String agentPassword;
    private String agentType;
    private boolean isValid;
}
