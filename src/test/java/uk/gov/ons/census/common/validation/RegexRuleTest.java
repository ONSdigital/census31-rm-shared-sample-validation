package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class RegexRuleTest {
  private static final String TEST_REGEX_UK_MOBILE_NUMBER = "^07[0-9]{9}$";
  private static final String TEST_REGEX_UK_MOBILE_NUMBER_ERROR = "Not a valid UK mobile number";
  private static final String TEST_REGEX_INTERNATIONAL_PHONE_NUMBER = "^(\\+|00)?[1-9]\\d{1,14}$";
  private static final String TEST_REGEX_INTERNATIONAL_PHONE_NUMBER_ERROR =
      "Not a valid international phone number";

  @Test
  void checkValidityValidPhoneNumber() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("07123456789");
    assertThat(actualResult).isNotPresent();
  }

  @Test
  void checkValidityInvalidPhoneNumberNonNumeric() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("07123456xxx");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_UK_MOBILE_NUMBER_ERROR);
  }

  @Test
  void checkValidityInvalidPhoneNumberTooShort() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("0712345");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_UK_MOBILE_NUMBER_ERROR);
  }

  @Test
  void checkValidityInvalidPhoneNumberTooLong() {
    RegexRule underTest =
        new RegexRule(TEST_REGEX_UK_MOBILE_NUMBER, TEST_REGEX_UK_MOBILE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("07123456789123456789");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_UK_MOBILE_NUMBER_ERROR);
  }

  @Test
  void checkValidityValidInternationalPhoneNumber() {
    RegexRule underTest =
        new RegexRule(
            TEST_REGEX_INTERNATIONAL_PHONE_NUMBER, TEST_REGEX_INTERNATIONAL_PHONE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("+447123456789");
    assertThat(actualResult).isNotPresent();
  }

  @Test
  void checkValidityValidInternationalPhoneNumberWith00Prefix() {
    RegexRule underTest =
        new RegexRule(
            TEST_REGEX_INTERNATIONAL_PHONE_NUMBER, TEST_REGEX_INTERNATIONAL_PHONE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("00447123456789");
    assertThat(actualResult).isNotPresent();
  }

  @Test
  void checkValidityInvalidInternationalPhoneNumberLeadingZeroCountryCode() {
    RegexRule underTest =
        new RegexRule(
            TEST_REGEX_INTERNATIONAL_PHONE_NUMBER, TEST_REGEX_INTERNATIONAL_PHONE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("+0123456789");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_INTERNATIONAL_PHONE_NUMBER_ERROR);
  }

  @Test
  void checkValidityInvalidInternationalPhoneNumberContainsSeparators() {
    RegexRule underTest =
        new RegexRule(
            TEST_REGEX_INTERNATIONAL_PHONE_NUMBER, TEST_REGEX_INTERNATIONAL_PHONE_NUMBER_ERROR);

    Optional<String> actualResult = underTest.checkValidity("+44 7123 456789");
    assertThat(actualResult).isPresent().contains(TEST_REGEX_INTERNATIONAL_PHONE_NUMBER_ERROR);
  }
}
