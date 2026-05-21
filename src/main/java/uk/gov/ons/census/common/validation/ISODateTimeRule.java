package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.ZonedDateTime;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ISODateTimeRule implements Rule {

  @Override
  public Optional<String> checkStringValidity(String data) {
    try {
      ZonedDateTime.parse(data);
      return Optional.empty();
    } catch (Exception e) {
      return Optional.of("Not a valid ISO date time");
    }
  }
}
