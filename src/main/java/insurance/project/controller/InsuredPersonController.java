package insurance.project.controller;

import insurance.project.service.InsuredPersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/insured")
public class InsuredPersonController {

    private final InsuredPersonService insuredService;

//    @PostMapping
//    public ResponseEntity<HttpResponse<InsuredPerson>> registerInsured(@RequestBody NewInsuranceRequest newInsuranceRequest) {
//        InsuredPerson newInsured = insuredService.registerInsurance(newInsuranceRequest);
//        HttpResponse<InsuredPerson> response = new HttpResponse<>(newInsured, "Successfully registered", HttpStatus.CREATED);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
}
