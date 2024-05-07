package insurance.project.service;

import insurance.project.dto.AgentValidation.AgentValidation;

public interface AgentService {
    AgentValidation validateAgent(AgentValidation agentValidation);
}
