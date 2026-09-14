package uk.gov.ons.census.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class LatitudeLongitudeRuleTest {

  private final LatitudeLongitudeRule latRule =
      new LatitudeLongitudeRule(LatitudeLongitudeRule.Type.LATITUDE);

  private final LatitudeLongitudeRule lonRule =
      new LatitudeLongitudeRule(LatitudeLongitudeRule.Type.LONGITUDE);

  @Test
  void validLatitude() {
    assertEquals(Optional.empty(), latRule.checkStringValidity("51.5074000"));
  }

  @Test
  void validLongitude() {
    assertEquals(Optional.empty(), lonRule.checkStringValidity("-3.1883000"));
  }

  @Test
  void malformedDecimal() {
    checkValidity(".", "Value is not a valid float");
    checkValidity("..", "Value is not a valid float");
    checkValidity(".1", "Malformed decimal value");
    checkValidity(".1.", "Value is not a valid float");
    checkValidity("1.1.1.1", "Value is not a valid float");
    checkValidity("1.", "Malformed decimal value");
    checkValidity("0.0.", "Value is not a valid float");
    checkValidity("123", "Malformed decimal value");
  }

  void checkValidity(String value, String expectedError) {
    Optional<String> result = latRule.checkStringValidity(value);

    assertTrue(result.isPresent());
    assertEquals(result.get(), expectedError);
  }

  @Test
  void precisionExceededLatitude() {
    // precision = 10 (integer=2 digits, decimal=8 digits)
    Optional<String> result = latRule.checkStringValidity("12.12345678");
    assertTrue(result.isPresent());
    assertEquals("Precision exceeds", result.get());
  }

  @Test
  void scaleExceededLatitude() {
    // scale = 8 (max allowed = 7) precision = 9 (integer=1 digits, decimal=8 digits)
    Optional<String> result = latRule.checkStringValidity("1.12345678");
    assertTrue(result.isPresent());
    assertEquals("Scale exceeds", result.get());
  }
}
