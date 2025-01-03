package aegis.server.domain.survey.domain;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class SurveyDto {

    private Set<InterestField> interestFields = new HashSet<>();

    @Nullable
    private Map<InterestField, String> interestEtc = new HashMap<>(); 

    @Size(min = 3)
    private String registrationReason;
    private String feedBack;

}
