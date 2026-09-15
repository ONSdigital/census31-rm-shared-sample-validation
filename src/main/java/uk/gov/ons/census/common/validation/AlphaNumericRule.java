package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AlphaNumericRule implements Rule {

  // This rule checks that the string contains only letters and digits.
  @Override
  public Optional<String> checkStringValidity(String data) {
    // Remove all spaces
    String cleaned = data.replaceAll("\\s+", "");

    if (cleaned.isEmpty()) {
      return Optional.of("Value is empty after removing space");
    }
    // Regex: only A–Z, a–z, 0–9
    if (cleaned.matches("[A-Za-z0-9]*")) {
      return Optional.empty();
    }
    return Optional.of("Contains non alphanumeric characters");
  }
}
