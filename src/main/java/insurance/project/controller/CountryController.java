package insurance.project.controller;

import insurance.project.dto.CountryResponse.CountryResponse;
import insurance.project.dto.utility.HttpResponse;
import insurance.project.entity.Country;
import insurance.project.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

//    @GetMapping("/all")
//    public ResponseEntity<HttpResponse<ArrayList<Country>>> getAllCountries() {
//        ArrayList<Country> countries = countryService.getAllCountries();
//        HttpResponse<ArrayList<Country>> response = new HttpResponse<>(countries, "Successfully retrieved", HttpStatus.OK);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/all")
    public ResponseEntity<HttpResponse<ArrayList<CountryResponse>>> getAllCountries() {
        ArrayList<CountryResponse> countries = countryService.getAllCountries();
        HttpResponse<ArrayList<CountryResponse>> response = new HttpResponse<>(countries, "Successfully retrieved", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/all")
//    public ResponseEntity<HttpResponse<Page<Country>>> getAllCountries(Pageable pageable) {
//        Page<Country> countries = countryService.getAllCountries(pageable);
//        HttpResponse<Page<Country>> response = new HttpResponse<>(countries, "Successfully retrieved", HttpStatus.OK);
//        return ResponseEntity.ok(response);
//    }
}
