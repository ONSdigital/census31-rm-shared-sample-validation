package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BooleanRuleTest {
  private static final BooleanRule booleanRule = new BooleanRule();

  @ParameterizedTest
  @ValueSource(strings = {"1", "0"})
  void testValidBooleanStrings(String validStringBoolean) {
    assertThat(booleanRule.checkValidity(validStringBoolean)).isNotPresent();
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "true", "false", "2", "-1", "yes", "no", "01"})
  void testInvalidBooleanStrings(String invalidStringBoolean) {
    assertThat(booleanRule.checkValidity(invalidStringBoolean)).isPresent();
  }

  @Test
  void testNullBooleanStringIsValid() {
    assertThat(booleanRule.checkValidity(null)).isNotPresent();
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void testBooleanTypeValuesAreValid(boolean validBoolean) {
    assertThat(booleanRule.checkValidity(validBoolean)).isNotPresent();
  }
}
