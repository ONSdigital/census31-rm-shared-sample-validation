package uk.gov.ons.census.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class AlphaNumericRuleTest {

  private final AlphaNumericRule rule = new AlphaNumericRule();

  @Test
  void validAlphanumericString() {
    assertEquals(Optional.empty(), rule.checkStringValidity("ABC123"));
  }

  @Test
  void emptyStringIsValid() {
    assertEquals(Optional.empty(), rule.checkStringValidity(""));
  }

  @Test
  void stringWithSymbolsIsInvalid() {
    Optional<String> result = rule.checkStringValidity("AB#12");
    assertTrue(result.isPresent());
    assertEquals("Contains non alphanumeric characters", result.get());
  }

  @Test
  void negativeIntegerIsInvalid() {
    Optional<String> result = rule.checkIntegerValidity(-5);
    assertTrue(result.isPresent());
    assertEquals("Negative integers are not allowed", result.get());
  }

  @Test
  void positiveIntegerIsValid() {
    assertEquals(Optional.empty(), rule.checkIntegerValidity(42));
  }
}
