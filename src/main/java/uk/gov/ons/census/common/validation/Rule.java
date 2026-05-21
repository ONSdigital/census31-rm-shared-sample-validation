package uk.gov.ons.census.common.validation;

import java.io.Serializable;
import java.util.Optional;

public interface Rule extends Serializable {
  Optional<String> checkStringValidity(String data);

  default Optional<String> checkIntegerValidity(Integer data) {
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName() + " is not implemented for integers");
  }

  default Optional<String> checkBoolValidity(Boolean data) {
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName() + " is not implemented for Booleans");
  }

  default Optional<String> checkValidity(boolean data) {
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName() + " is not implemented for booleans");
  }

  default Optional<String> checkValidity(Object data) {
    if (data == null) {
      // Allow nulls by default
      return Optional.empty();
    }

    if (data instanceof String stringData) {
      return checkStringValidity(stringData);
    } else if (data instanceof Integer integerData) {
      return checkIntegerValidity(integerData);
    } else if (data instanceof Boolean booleanData) {
      return checkBoolValidity(booleanData);
    }

    // Otherwise the rule can't handle this type of object, throw an exception
    throw new UnsupportedOperationException(
        this.getClass().getSimpleName()
            + " is not implemented for data of Class: "
            + data.getClass().getName());
  }
}
