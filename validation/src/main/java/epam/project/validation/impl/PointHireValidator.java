package epam.project.validation.impl;

import epam.project.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
@Scope("prototype")
public class PointHireValidator {

    private static final String CHECK_TELEPHONE = "/^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$/";

    private static final String LOCATION = "location";
    private static final String TELEPHONE = "telephone";

    @Autowired
    private ValidationResult validationResult;


    private static final int MIN_LOCATION_LENGTH = 3;
    private static final int MAX_LOCATION_LENGTH = 35;

    public ValidationResult doValidate(Map<String, String> params) {
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case LOCATION:
                    validateLocation(validationResult, value);
                    break;
                case TELEPHONE:
                    validateTelephone(validationResult, value);
                    break;
                default:
                    break;

            }

        }
        return validationResult;
    }


    private void validateLocation(ValidationResult validationResult, String location) {
        ArrayList<String> errors = new ArrayList<>();

        if (location.length() <= MIN_LOCATION_LENGTH) {
            errors.add("point_hire.error.min_location_length");
        }
        if (location.length() > MAX_LOCATION_LENGTH) {
            errors.add("point_hire.error.max_location_length");
        }
        if (!errors.isEmpty()) {
            validationResult.add(LOCATION, errors);
        }
    }

    private void validateTelephone(ValidationResult validationResult, String telephone) {
        ArrayList<String> errors = new ArrayList<>();

        if (telephone.matches(CHECK_TELEPHONE)) {
            errors.add("point_hire.error.invalid_telephone");
        }
        if (!errors.isEmpty()) {
            validationResult.add(TELEPHONE, errors);
        }
    }

}
