package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BooleanRule implements Rule {
  // Booleans are represented as "1" or "0" in string form (e.g. in CSV files), or as a boolean
  // types once ingested

  @Override
  public Optional<String> checkStringValidity(String data) {
    if (!"1".equals(data) && !"0".equals(data)) {
      return Optional.of("Not a valid string boolean value (expected \"1\" or \"0\")");
    }
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
}
