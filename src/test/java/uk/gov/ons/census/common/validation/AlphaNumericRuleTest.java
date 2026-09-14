package uk.gov.ons.census.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class AlphaNumericRuleTest {

  private final AlphaNumericRule rule = new AlphaNumericRule();

  @Test
  void validAlphanumericString() {

    assertEquals(Optional.empty(), rule.checkStringValidity("ABC 123"));
    assertEquals(Optional.empty(), rule.checkStringValidity("123"));
    assertEquals(Optional.empty(), rule.checkStringValidity("ABC"));
  }

  @Test
  void emptyStringIsInValid() {
    Optional<String> result = rule.checkStringValidity("");
    assertTrue(result.isPresent());
    assertEquals("Value is empty after removing space", result.get());
  }

  @Test
  void stringWithSymbolsIsInvalid() {
    Optional<String> result = rule.checkStringValidity("AB#12");
    assertTrue(result.isPresent());
    assertEquals("Contains non alphanumeric characters", result.get());
    assertEquals("Contains non alphanumeric characters", rule.checkStringValidity("-1234").get());
  }
}
