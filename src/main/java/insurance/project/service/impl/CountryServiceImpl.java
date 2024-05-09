package insurance.project.service.impl;

import insurance.project.dto.CountryResponse.CountryResponse;
import insurance.project.entity.Country;
import insurance.project.repo.CountryRepo;
import insurance.project.service.CountryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepo countryRepo;
    private final ModelMapper modelMapper;

    @Override
    public ArrayList<CountryResponse> getAllCountries() {
        ArrayList<Country> countries = countryRepo.findAll();
        countries.sort(Comparator.comparing(Country::getCountryName));
        ArrayList<CountryResponse> countryResponses = new ArrayList<>();
        for (Country country : countries) {
            CountryResponse countryResponse = new CountryResponse();
            modelMapper.map(country, countryResponse);
            countryResponses.add(countryResponse);
        }
        return countryResponses;
    }
}
