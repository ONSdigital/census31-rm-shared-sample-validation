package uk.gov.ons.census.common.validation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;
import java.util.Optional;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StartsWithAnyOfRule implements Rule {

  private final List<String> prefixes;

  public StartsWithAnyOfRule(List<String> prefixes) {
    this.prefixes = prefixes;
  }

  @Override
  public Optional<String> checkStringValidity(String data) {

    boolean matches = prefixes.stream().anyMatch(data::startsWith);

    if (!matches) {
      return Optional.of("Value does not start with any of the allowed prefixes: " + prefixes);
    }

    return Optional.empty();
  }
}
