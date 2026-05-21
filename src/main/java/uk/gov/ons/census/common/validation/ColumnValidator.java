package uk.gov.ons.census.common.validation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;

public class ColumnValidator implements Serializable {

  @Getter private final String columnName;

  private Rule[] rules;

  public ColumnValidator(String columnName, Rule[] rules) {
    this.rules = rules.clone();
    this.columnName = columnName;
  }

  public Optional<String> validateRow(Map<String, String> rowData) {
    return validateData(rowData.get(columnName), false);
  }

  public Optional<String> validateRow(
      Map<String, String> rowData, boolean excludeDataFromReturnedErrorMsgs) {
    return validateData(rowData.get(columnName), excludeDataFromReturnedErrorMsgs);
  }

  public Optional<String> validateData(
      Object dataToValidate, boolean excludeDataFromReturnedErrorMsgs) {
    List<String> validationErrors = new LinkedList<>();

    for (Rule rule : rules) {
      Optional<String> validationError;
      validationError = rule.checkValidity(dataToValidate);
      if (validationError.isPresent()) {
        if (excludeDataFromReturnedErrorMsgs) {
          validationErrors.add(
              "Column '"
                  + columnName
                  + "' Failed validation for Rule '"
                  + rule.getClass().getSimpleName()
                  + "' validation error: "
                  + validationError.get());
        } else {
          String dataString;
          if (dataToValidate == null) {
            dataString = "null";
          } else {
            dataString = dataToValidate.toString();
          }
          validationErrors.add(
              "Column '"
                  + columnName
                  + "' value '"
                  + dataString
                  + "' validation error: "
                  + validationError.get());
        }
      }
    }

    if (!validationErrors.isEmpty()) {
      return Optional.of(String.join(", ", validationErrors));
    }

    return Optional.empty();
  }

  public Rule[] getRules() {
    return rules.clone();
  }
}
