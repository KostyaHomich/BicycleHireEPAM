package epam.project.validation;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@Scope("prototype")
public class ValidationResult {
    private Map<String, List<String>> errors = new HashMap<>();

    public ValidationResult() {
    }

    public void add(String key, List<String> values) {
        errors.put(key, values);
    }

    public void remove(String key) {
        errors.remove(key);
    }

}
