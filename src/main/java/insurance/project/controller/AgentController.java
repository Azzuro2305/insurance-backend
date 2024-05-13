package insurance.project.controller;

import insurance.project.dto.AgentValidation.AgentValidation;
import insurance.project.dto.utility.HttpResponse;
import insurance.project.service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/agent")
public class AgentController {
    private final AgentService agentService;

    @PostMapping("/validate")
    public ResponseEntity<HttpResponse<AgentValidation>> validateAgent(@RequestBody AgentValidation agentValidation) {
        AgentValidation agent = agentService.validateAgent(agentValidation);
        if (agent.isValid()) {
            return ResponseEntity.ok(new HttpResponse<>(agent, "Successfully validated", HttpStatus.OK));
        } else {
            return ResponseEntity.ok(new HttpResponse<>(agent, "Validation failed", HttpStatus.BAD_REQUEST));
        }
    }
}
