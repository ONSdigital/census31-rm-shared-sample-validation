package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumericRuleTest {
  private static final NumericRule numericRule = new NumericRule();

  @ParameterizedTest
  @ValueSource(
      strings = {
        "1",
        "0123456789",
        "123",
        "0000000000",
        "",
      })
  void testValidNumericStrings(String validString) {
    assertThat(numericRule.checkValidity(validString)).isNotPresent();
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "a",
        "a0123456789",
        "*",
        "-1",
        "1.0",
        "I am not a number",
        "10e10",
        "100L",
      })
  void testinvalidNumericStrings(String invalidString) {
    assertThat(numericRule.checkValidity(invalidString)).isPresent();
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 42, Integer.MAX_VALUE})
  void testValidNumericIntegers(int validInteger) {
    assertThat(numericRule.checkValidity(validInteger)).isNotPresent();
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, -42, Integer.MIN_VALUE})
  void testInvalidNumericIntegers(int invalidInteger) {
    assertThat(numericRule.checkValidity(invalidInteger))
        .isPresent()
        .contains("Negative integers are not allowed");
  }
}
