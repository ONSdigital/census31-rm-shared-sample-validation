package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

class JacksonCompatibilityRoundTripTest {

  private final ObjectMapper objectMapper = JsonMapper.builder().build();

  @Test
  void regexRuleRoundTripSupportsJackson3MapperWithJackson2Annotations() throws Exception {
    RegexRule original = new RegexRule("^(\\+|00)?[1-9]\\d{1,14}$", "Not a valid phone number");

    String json = objectMapper.writeValueAsString(original);
    RegexRule roundTripped = objectMapper.readValue(json, RegexRule.class);

    assertThat(json)
        .isEqualTo(
            "{\"expression\":\"^(\\\\+|00)?[1-9]\\\\d{1,14}$\",\"userFriendlyError\":\"Not a valid phone number\"}");
    assertThat(roundTripped.getExpression()).isEqualTo(original.getExpression());
    assertThat(roundTripped.getUserFriendlyError()).isEqualTo(original.getUserFriendlyError());
    assertThat(roundTripped.checkValidity("+44 7123 456789")).contains("Not a valid phone number");
  }

  @Test
  void emailRuleRoundTripSupportsJackson3MapperWithJackson2Annotations() throws Exception {
    EmailRule original = new EmailRule(true);

    String json = objectMapper.writeValueAsString(original);
    EmailRule roundTripped = objectMapper.readValue(json, EmailRule.class);

    assertThat(json).isEqualTo("{\"mandatory\":true}");
    assertThat(roundTripped.checkValidity("")).contains("Email didn't match regex");
    assertThat(roundTripped.checkValidity("valid.email@example.com")).isEmpty();
  }
}
