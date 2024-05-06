package insurance.project.service;

import insurance.project.dto.NewInsurance.NewInsurance;
import insurance.project.entity.InsuredPerson;

public interface InsuredPersonService {
    NewInsurance registerInsurance(NewInsurance newInsurance);
}
