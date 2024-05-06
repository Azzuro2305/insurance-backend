package insurance.project.controller;

import insurance.project.dto.NewInsurance.NewInsurance;
import insurance.project.dto.utility.HttpResponse;
import insurance.project.service.InsuredPersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/insured-person")
public class InsuredPersonController {

    private final InsuredPersonService insuredService;

    @PostMapping
    public ResponseEntity<HttpResponse<NewInsurance>> registerInsured(@RequestBody NewInsurance newInsurance) {
        NewInsurance newInsured = insuredService.registerInsurance(newInsurance);
        HttpResponse<NewInsurance> response = new HttpResponse<>(newInsured, "Successfully registered", HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//    @PostMapping
//    public ResponseEntity<HttpResponse<NewInsurance>>
//    @PostMapping
//    public ResponseEntity<HttpResponse<InsuredPerson>> registerInsured(@RequestBody NewInsuranceRequest newInsuranceRequest) {
//        InsuredPerson newInsured = insuredService.registerInsurance(newInsuranceRequest);
//        HttpResponse<InsuredPerson> response = new HttpResponse<>(newInsured, "Successfully registered", HttpStatus.CREATED);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
}
