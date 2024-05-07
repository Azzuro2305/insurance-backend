package insurance.project.service;

import insurance.project.dto.CountryResponse.CountryResponse;
import insurance.project.entity.Country;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.ArrayList;

public interface CountryService {
//    ArrayList<Country> getAllCountries();
    ArrayList<CountryResponse> getAllCountries();

//    Page<Country> getAllCountries(Pageable pageable, String[] sort);
}
