package insurance.project.service;

import insurance.project.dto.InsuredData.InsuredData;
import insurance.project.dto.NewInsurance.NewInsurance;
import insurance.project.entity.Country;
import insurance.project.entity.InsuredPerson;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsuredPersonService {
    NewInsurance registerInsurance(NewInsurance newInsurance);

    List<InsuredData> getInsuredData(String passportNumber, UUID passportIssuedCountry);
}
