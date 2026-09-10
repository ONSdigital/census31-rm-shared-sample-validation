package uk.gov.ons.census.common.validation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class StartsWithAnyOfRuleTest {

  private final StartsWithAnyOfRule rule = new StartsWithAnyOfRule(List.of("AB", "XYZ", "202"));

  @Test
  void validPrefix_AB() {
    assertEquals(Optional.empty(), rule.checkStringValidity("AB123"));
  }

  @Test
  void validPrefix_XYZ() {
    assertEquals(Optional.empty(), rule.checkStringValidity("XYZ999"));
  }

  @Test
  void validPrefix_202() {
    assertEquals(Optional.empty(), rule.checkStringValidity("202ABC"));
  }

  @Test
  void invalidPrefix() {
    Optional<String> result = rule.checkStringValidity("HELLO");
    assertTrue(result.isPresent());
    assertTrue(result.get().contains("does not start with any of the allowed prefixes"));
  }

  @Test
  void emptyStringIsInvalid() {
    Optional<String> result = rule.checkStringValidity("");
    assertTrue(result.isPresent());
    assertEquals("Value is null or empty", result.get());
  }

  @Test
  void integerNotAllowed() {
    Optional<String> result = rule.checkIntegerValidity(10);
    assertTrue(result.isPresent());
    assertEquals("Integer values are not allowed for StartsWithAnyOfRule", result.get());
  }
}
