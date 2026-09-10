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
    if (!cleaned.matches("[A-Za-z0-9]*")) {
      return Optional.of("Contains non alphanumeric characters");
    }

    // Must contain at least one letter
    boolean hasLetter = cleaned.matches(".*[A-Za-z].*");

    // Must contain at least one digit
    boolean hasDigit = cleaned.matches(".*\\d.*");

    if (!hasLetter || !hasDigit) {
      return Optional.of("Value must contain both letters and digits");
    }

    return Optional.empty();
  }

  // Integers are always numeric, so only check positivity (same semantics as NumericRule)
  @Override
  public Optional<String> checkIntegerValidity(Integer data) {
    if (data < 0) {
      return Optional.of("Negative integers are not allowed");
    }
    return Optional.empty();
  }
}
