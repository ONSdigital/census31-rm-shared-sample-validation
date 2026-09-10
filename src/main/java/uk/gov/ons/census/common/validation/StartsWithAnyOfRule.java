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
    if (data == null || data.isBlank()) {
      return Optional.of("Value is null or empty");
    }

    boolean matches = prefixes.stream().anyMatch(data::startsWith);

    if (!matches) {
      return Optional.of(
          "Value \"" + data + "\" does not start with any of the allowed prefixes: " + prefixes);
    }

    return Optional.empty();
  }

  @Override
  public Optional<String> checkIntegerValidity(Integer data) {
    return Optional.of("Integer values are not allowed for StartsWithAnyOfRule");
  }
}
