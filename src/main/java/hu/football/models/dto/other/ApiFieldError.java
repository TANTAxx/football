package hu.football.models.dto.other;

import hu.football.models.dto.other.FieldError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiFieldError {
    private List<FieldError> validationErrors;
}
