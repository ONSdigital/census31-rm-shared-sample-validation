package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MandatoryRule implements Rule {

  @Override
  public Optional<String> checkStringValidity(String data) {
    if (data == null || data.isBlank()) {
      return Optional.of("Mandatory value missing");
    }

    return Optional.empty();
  }

  @Override
  public Optional<String> checkIntegerValidity(Integer data) {
    return Optional.empty();
  }

  @Override
  public Optional<String> checkBoolValidity(Boolean data) {
    return Optional.empty();
  }

  @Override
  public Optional<String> checkValidity(boolean data) {
    return Optional.empty();
  }

  @Override
  public Optional<String> checkValidity(Object data) {
    if (data == null) {
      return Optional.of("Mandatory value missing");
    }
    return Rule.super.checkValidity(data);
  }
}
