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
    Optional<String> result = latRule.checkStringValidity("12..34");
    assertTrue(result.isPresent());
    assertTrue(result.get().contains("is not a valid float"));
  }

  @Test
  void nonFloatParts() {
    Optional<String> result = latRule.checkStringValidity("12.ABC");
    assertTrue(result.isPresent());
    assertTrue(result.get().contains("is not a valid float"));
  }

  @Test
  void nonNumericParts() {
    Optional<String> result = latRule.checkStringValidity("ABC.3455");
    assertTrue(result.isPresent());
    assertTrue(result.get().contains("is not a valid float"));
  }

  @Test
  void nonDecimal() {
    Optional<String> result = latRule.checkStringValidity("3455");
    assertTrue(result.isPresent());
    assertTrue(result.get().contains("Malformed decimal, Value"));
  }

  @Test
  void precisionExceededLatitude() {
    // precision = 10 (integer=2 digits, decimal=8 digits)
    Optional<String> result = latRule.checkStringValidity("12.12345678");
    assertTrue(result.isPresent());
    assertTrue(result.get().contains("Precision"));
  }

  @Test
  void scaleExceededLatitude() {
    // scale = 8 (max allowed = 7) precision = 9 (integer=1 digits, decimal=8 digits)
    Optional<String> result = latRule.checkStringValidity("1.12345678");
    assertTrue(result.isPresent());
    assertTrue(result.get().contains("Scale"));
  }

  @Test
  void integerNotAllowed() {
    Optional<String> result = latRule.checkIntegerValidity(10);
    assertTrue(result.isPresent());
    assertEquals("Integer values are not allowed for latitude/longitude", result.get());
  }
}
