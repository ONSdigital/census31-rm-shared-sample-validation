package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;

public class LengthRule implements Rule {

  private final Integer maxLength;
  private final Integer minLength;

  @JsonCreator
  public LengthRule(@JsonProperty("maxLength") int maxLength) {
    this.maxLength = maxLength;
    this.minLength = 0;
  }

  @JsonCreator
  public LengthRule(
      @JsonProperty("maxLength") int maxLength, @JsonProperty("minLength") int minLength) {
    this.maxLength = maxLength;
    this.minLength = minLength;
  }

  @Override
  public Optional<String> checkStringValidity(String data) {
    if (data.length() > maxLength) {
      return Optional.of("Exceeded max length of " + maxLength);
    }
    if (data.length() < minLength) {
      return Optional.of("Does not meet the min length of " + minLength);
    }

    return Optional.empty();
  }

  @Override
  public Optional<String> checkIntegerValidity(Integer data) {
    if (String.valueOf(data).length() > maxLength) {
      return Optional.of("Exceeded max length of " + maxLength);
    }

    if (String.valueOf(data).length() < minLength) {
      return Optional.of("Does not meet the min length of " + minLength);
    }

    return Optional.empty();
  }

  public Integer getMaxLength() {
    return maxLength;
  }

  public Integer getMinLength() {
    return minLength;
  }
}
