package insurance.project.controller;

import insurance.project.dto.InsuredData.InsuredData;
import insurance.project.dto.NewInsurance.NewInsurance;
import insurance.project.dto.utility.HttpResponse;
import insurance.project.service.InsuredPersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<HttpResponse<List<InsuredData>>> getInsuredData(@RequestParam String passportNumber, @RequestParam UUID passportIssuedCountry) {
        List<InsuredData> insuredData = insuredService.getInsuredData(passportNumber, passportIssuedCountry);
        HttpResponse<List<InsuredData>> response = new HttpResponse<>(insuredData, "Successfully retrieved", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
