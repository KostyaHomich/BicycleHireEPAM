package epam.project.validation.impl;


import epam.project.validation.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

@Component
@Scope("prototype")
public class BicycleValidator {

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    private static final String CHECK_NAME = "[a-zA-Z]";
    private static final String SPACE = " ";
    private static final int MAX_NAME_LENGTH = 25;
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MIN_DESCRIPTION_LENGTH = 4;

    @Autowired
    private ValidationResult validationResult;

    public ValidationResult doValidate(Map<String, String> params) {

        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case NAME:
                    validateName(validationResult, value);
                    break;
                case DESCRIPTION:
                    validateDescription(validationResult, value);
                    break;
                default:
                    break;

            }

        }
        return validationResult;
    }

    private void validateName(ValidationResult validationResult, String name) {
        ArrayList<String> errors = new ArrayList<>();

        if (name.length() <= MIN_NAME_LENGTH) {
            errors.add("bicycle.error.min_name_length");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            errors.add("bicycle.error.max_name_length");
        }
        if (name.contains(SPACE)) {
            errors.add("bicycle.error.name_empty_space");
        }
        if (name.matches(CHECK_NAME)) {
            errors.add("bicycle.error.invalid_name");
        }
        if (!errors.isEmpty()) {
            validationResult.add(NAME, errors);
        }
    }

    private void validateDescription(ValidationResult validationResult, String description) {
        ArrayList<String> errors = new ArrayList<>();

        if (description.length() <= MIN_DESCRIPTION_LENGTH) {
            errors.add("bicycle.error.min_description_length");
        }

        if (!errors.isEmpty()) {
            validationResult.add(DESCRIPTION, errors);
        }
    }


}
