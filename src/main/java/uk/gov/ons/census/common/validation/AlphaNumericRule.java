package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AlphaNumericRule implements Rule {

  // This rule checks that the string contains only letters and digits.
  @Override
  public Optional<String> checkStringValidity(String data) {
    // Regex: only A–Z, a–z, 0–9
    if (data.matches("[A-Za-z0-9]*")) {
      return Optional.empty();
    }

    return Optional.of("Contains non alphanumeric characters");
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
