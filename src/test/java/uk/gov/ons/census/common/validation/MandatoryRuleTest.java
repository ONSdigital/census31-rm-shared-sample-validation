package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MandatoryRuleTest {

  @Test
  void checkValidityValid() {
    MandatoryRule underTest = new MandatoryRule();
    Optional<String> validity = underTest.checkValidity("foo");
    assertThat(validity).isNotPresent();
  }

  @Test
  void checkValidityInvalid() {
    MandatoryRule underTest = new MandatoryRule();
    Optional<String> validity = underTest.checkValidity("");
    assertThat(validity).isPresent().contains("Mandatory value missing");
  }

  @ParameterizedTest
  @ValueSource(ints = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE})
  void checkValidityIntAlwaysValid(int value) {
    MandatoryRule underTest = new MandatoryRule();
    Optional<String> validity = underTest.checkValidity(value);
    assertThat(validity).isNotPresent();
  }

  @Test
  void checkValidityObjectNullInvalid() {
    MandatoryRule underTest = new MandatoryRule();
    Optional<String> validity = underTest.checkValidity((Object) null);
    assertThat(validity).isPresent().contains("Mandatory value missing");
  }

  @Test
  void checkValidityBoolValid() {
    MandatoryRule underTest = new MandatoryRule();
    Optional<String> validity = underTest.checkValidity(true);
    assertThat(validity).isNotPresent();
  }
}
