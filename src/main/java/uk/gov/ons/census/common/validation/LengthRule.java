package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

public class LengthRule implements Rule {

  private final Integer maxLength;

  @JsonCreator
  public LengthRule(@JsonProperty("maxLength") int maxLength) {
    this.maxLength = maxLength;
  }

  @Override
  public Optional<String> checkStringValidity(String data) {
    if (data.length() > maxLength) {
      return Optional.of("Exceeded max length of " + maxLength);
    }

    return Optional.empty();
  }

  @Override
  public Optional<String> checkIntegerValidity(Integer data) {
    if (String.valueOf(data).length() > maxLength) {
      return Optional.of("Exceeded max length of " + maxLength);
    }

    return Optional.empty();
  }

  public Integer getMaxLength() {
    return maxLength;
  }
}
