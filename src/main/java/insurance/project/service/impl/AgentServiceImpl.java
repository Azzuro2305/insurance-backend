package insurance.project.service.impl;

import insurance.project.dto.AgentValidation.AgentValidation;
import insurance.project.entity.Agent;
import insurance.project.repo.AgentRepo;
import insurance.project.service.AgentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepo agentRepo;
    private final ModelMapper modelMapper;

    @Override
    public AgentValidation validateAgent(AgentValidation agentValidation) {
        AgentValidation agentValidationResult = new AgentValidation();
        if (agentValidation.getAgentType().equals("normalagent")) {
            Agent normalAgent = agentRepo.findAgentByAgentNameAndAgentLicense(agentValidation.getAgentName(), agentValidation.getAgentLicense());
            if (normalAgent != null) {
                modelMapper.map(normalAgent, agentValidationResult);
                agentValidationResult.setValid(true);
            } else {
                agentValidationResult.setValid(false);
            }
        }
        else if (agentValidation.getAgentType().equals("associationagent")) {
            Agent associationAgent = agentRepo.findAgentByAgentLicenseAndAgentPassword(agentValidation.getAgentLicense(), agentValidation.getAgentPassword());
//            Agent associationAgent = agentRepo.findAgentByAgentPasswordAndAgentLicense(agentValidation.getAgentPassword(), agentValidation.getAgentLicense());
            if (associationAgent != null) {
                modelMapper.map(associationAgent, agentValidationResult);
                agentValidationResult.setValid(true);
            } else {
                agentValidationResult.setValid(false);
            }
        }
        return agentValidationResult;
    }
}

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//@Service
//@AllArgsConstructor
//public class AgentServiceImpl implements AgentService {
//    private final AgentRepo agentRepo;
//    private final ModelMapper modelMapper;
//
//    private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);
//
//    @Override
//    public AgentValidation validateAgent(AgentValidation agentValidation) {
//        AgentValidation agentValidationResult = new AgentValidation();
//        String agentType = agentValidation.getAgentType();
//
//        if (agentType.equals("normalagent")) {
//            // existing code
//        }
//        else if (agentType.equals("associationAgent")) {
//            Agent agent = agentRepo.findAgentByAgentLicenseAndAgentPassword(agentValidation.getAgentLicense(), agentValidation.getAgentPassword());
//            if (agent != null) {
//                modelMapper.map(agent, agentValidationResult);
//                agentValidationResult.setValid(true);
//            } else {
//                logger.error("No agent found with license {} and password {}", agentValidation.getAgentLicense(), agentValidation.getAgentPassword());
//                agentValidationResult.setValid(false);
//            }
//        }
//        return agentValidationResult;
//    }
//}