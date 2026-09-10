package uk.gov.ons.census.common.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class SampleFieldValidatorsTest {

  @Test
  void testSampleFieldValidators() {
    ColumnValidator[] underTest = SampleFieldValidators.getValidators();

    assertThat(underTest).hasSize(27);
    assertThat(underTest[0].getColumnName()).isEqualTo("UPRN");
    Rule[] uprnRules = underTest[0].getRules();
    assertThat(uprnRules).hasSize(3);
    assertThat(uprnRules[0]).isInstanceOf(MandatoryRule.class);
    assertThat(uprnRules[1]).isInstanceOf(LengthRule.class);
    assertThat(uprnRules[2]).isInstanceOf(NumericRule.class);
  }

  @Test
  void testSampleFieldValidatorsAreImmmutable() {
    ColumnValidator[] firstColumnValidators = SampleFieldValidators.getValidators();

    firstColumnValidators[0] = null;

    ColumnValidator[] secondColumnValidators = SampleFieldValidators.getValidators();

    assertThat(secondColumnValidators).isNotNull();
    assertThat(secondColumnValidators[0].getColumnName()).isEqualTo("UPRN");
  }

  @Test
  void testSampleFieldValidatorsSuccessfulRow() {
    ColumnValidator[] underTest = SampleFieldValidators.getValidators();
    Map<String, String> sampleRow = new HashMap<>();
    sampleRow.put("UPRN", "000000");
    sampleRow.put("ADDRESS_LEVEL", "U");
    sampleRow.put("ADDRESS_TYPE", "HH");
    sampleRow.put("ESTAB_TYPE", "HOUSEHOLD");
    sampleRow.put("ESTAB_UPRN", "00000");
    sampleRow.put("ORGANISATION_NAME", "");
    sampleRow.put("ABP_CODE", "ab");
    sampleRow.put("ADDRESS_LINE1", "ab");
    sampleRow.put("ADDRESS_LINE2", "");
    sampleRow.put("ADDRESS_LINE3", "");
    sampleRow.put("TOWN_NAME", "Tres Town");
    sampleRow.put("POSTCODE", "CH324FG");
    sampleRow.put("LATITUDE", "34.1234567");
    sampleRow.put("LONGITUDE", "44.654321");
    sampleRow.put("OA", "0000");
    sampleRow.put("LSOA", "0000");
    sampleRow.put("MSOA", "0000");
    sampleRow.put("LAD", "0000");
    sampleRow.put("REGION", "EN");
    sampleRow.put("TREATMENT_CODE", "HH_ONW");
    sampleRow.put("HTC_WILLINGNESS", "1");
    sampleRow.put("HTC_DIGITAL", "1");
    sampleRow.put("FIELDCOORDINATOR_ID", "Field");
    sampleRow.put("FIELDOFFICER_ID", "Field");
    sampleRow.put("CE_EXPECTED_CAPACITY", "");
    sampleRow.put("CE_SECURE", "0");
    sampleRow.put("PRINT_BATCH", "1");

    for (ColumnValidator columnValidator : underTest) {
      assertThat(columnValidator.validateRow(sampleRow)).isEmpty();
    }
  }

  @Test
  void testSampleFieldValidatorsFailureRow() {
    ColumnValidator[] underTest = SampleFieldValidators.getValidators();
    Map<String, String> sampleRow = new HashMap<>();
    sampleRow.put("UPRN", "");
    sampleRow.put("ADDRESS_LEVEL", "U");
    sampleRow.put("ADDRESS_TYPE", "HH");
    sampleRow.put("ESTAB_TYPE", "HOUSEHOLD");
    sampleRow.put("ESTAB_UPRN", "000000");
    sampleRow.put("ORGANISATION_NAME", "");
    sampleRow.put("ABP_CODE", "abcdefgh");
    sampleRow.put("ADDRESS_LINE1", "ab");
    sampleRow.put("ADDRESS_LINE2", "");
    sampleRow.put("ADDRESS_LINE3", "");
    sampleRow.put("TOWN_NAME", "Tres Town");
    sampleRow.put("POSTCODE", "CAA-TD");
    sampleRow.put("LATITUDE", "1.2.3.4");
    sampleRow.put("LONGITUDE", "0.0.0.0");
    sampleRow.put("OA", "0000");
    sampleRow.put("LSOA", "0000");
    sampleRow.put("MSOA", "0000");
    sampleRow.put("LAD", "0000");
    sampleRow.put("REGION", "XN");
    sampleRow.put("TREATMENT_CODE", "XXX");
    sampleRow.put("HTC_WILLINGNESS", "1");
    sampleRow.put("HTC_DIGITAL", "1");
    sampleRow.put("FIELDCOORDINATOR_ID", "Field");
    sampleRow.put("FIELDOFFICER_ID", "Field");
    sampleRow.put("CE_EXPECTED_CAPACITY", "");
    sampleRow.put("CE_SECURE", "0");
    sampleRow.put("PRINT_BATCH", "1");
    List<String> validationErrors = new ArrayList<>();

    for (ColumnValidator columnValidator : underTest) {
      columnValidator.validateRow(sampleRow).ifPresent(validationErrors::add);
    }
    assertThat(validationErrors).hasSize(7);
    assertThat(validationErrors.get(0))
        .isEqualTo("Column 'UPRN' value '' validation error: Mandatory value missing");
    assertThat(validationErrors.get(1))
        .isEqualTo("Column 'ABP_CODE' value 'abcdefgh' validation error: Exceeded max length of 6");
    assertThat(validationErrors.get(2))
        .isEqualTo(
            "Column 'POSTCODE' value 'CAA-TD' validation error: Contains non alphanumeric characters");
    assertThat(validationErrors.get(3))
        .isEqualTo(
            "Column 'LATITUDE' value '1.2.3.4' validation error: Value \"1.2.3.4\" is not a valid float");
    assertThat(validationErrors.get(4))
        .isEqualTo(
            "Column 'LONGITUDE' value '0.0.0.0' validation error: Value \"0.0.0.0\" is not a valid float");
    assertThat(validationErrors.get(5))
        .isEqualTo(
            "Column 'REGION' value 'XN' validation error: Value \"XN\" does not start with any of the allowed prefixes: [E, W, N, S]");
    assertThat(validationErrors.get(6))
        .contains("Column 'TREATMENT_CODE' value 'XXX' validation error: Not in set of");
  }
}
