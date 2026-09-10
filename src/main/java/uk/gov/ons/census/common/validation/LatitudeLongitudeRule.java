package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LatitudeLongitudeRule implements Rule {

  public enum Type {
    LATITUDE(9, 7),
    LONGITUDE(8, 7);

    final int maxPrecision;
    final int maxScale;

    Type(int maxPrecision, int maxScale) {
      this.maxPrecision = maxPrecision;
      this.maxScale = maxScale;
    }
  }

  private final Type type;

  public LatitudeLongitudeRule(Type type) {
    this.type = type;
  }

  @Override
  public Optional<String> checkStringValidity(String value) {
    if (value == null || value.isBlank()) {
      return Optional.of("Value is null or empty");
    }

    // Basic float check
    try {
      Float.parseFloat(value);
    } catch (NumberFormatException ex) {
      return Optional.of("Value \"" + value + "\" is not a valid float");
    }

    // Must contain exactly one decimal point
    String[] parts = value.split("\\.");
    if (parts.length != 2) {
      return Optional.of("Malformed decimal, Value = \"" + value + "\"");
    }

    String integer = parts[0];
    String decimal = parts[1];

    // Check integer and decimal parts are numeric
    try {
      Integer.parseInt(integer.replace("-", ""));
      Integer.parseInt(decimal);
    } catch (NumberFormatException ex) {
      return Optional.of("Malformed decimal, Value = \"" + value + "\"");
    }

    // Calculate precision & scale
    int scale = decimal.length();
    int precision = integer.replace("-", "").length() + decimal.length();

    if (precision > type.maxPrecision) {
      return Optional.of("Precision " + precision + " exceeds max of " + type.maxPrecision);
    }

    if (scale > type.maxScale) {
      return Optional.of("Scale " + scale + " exceeds max of " + type.maxScale);
    }

    return Optional.empty();
  }

  @Override
  public Optional<String> checkIntegerValidity(Integer value) {
    return Optional.of("Integer values are not allowed for latitude/longitude");
  }
}
