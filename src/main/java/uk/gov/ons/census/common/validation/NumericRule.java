package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NumericRule implements Rule {
  // This rule checks that as a string, the data contains only digit characters, or as an int the
  // number is positive,
  // so can be represented as a string of pure digits.
  @Override
  public Optional<String> checkStringValidity(String data) {
    if (data.matches("\\d*")) {
      return Optional.empty();
    }

    return Optional.of("Contains non digit characters");
  }

  @Override
  public Optional<String> checkIntegerValidity(Integer data) {
    if (data < 0) {
      return Optional.of("Negative integers are not allowed");
    }
    return Optional.empty();
  }
}
