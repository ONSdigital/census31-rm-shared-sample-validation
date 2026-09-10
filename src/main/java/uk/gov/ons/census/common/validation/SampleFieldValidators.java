package uk.gov.ons.census.common.validation;

import java.util.ArrayList;
import java.util.Arrays;

public final class SampleFieldValidators {
  private SampleFieldValidators() {}

  public static ColumnValidator[] getValidators() {
    return new ColumnValidator[] {
      new ColumnValidator(
          "UPRN", new Rule[] {new MandatoryRule(), new LengthRule(13), new NumericRule()}),
      new ColumnValidator(
          "ESTAB_UPRN", new Rule[] {new MandatoryRule(), new LengthRule(13), new NumericRule()}),
      new ColumnValidator("ABP_CODE", new Rule[] {new MandatoryRule(), new LengthRule(6)}),
      new ColumnValidator("ORGANISATION_NAME", new Rule[] {new LengthRule(50)}),
      new ColumnValidator("ADDRESS_LINE1", new Rule[] {new MandatoryRule(), new LengthRule(50)}),
      new ColumnValidator("ADDRESS_LINE2", new Rule[] {new LengthRule(50)}),
      new ColumnValidator("ADDRESS_LINE3", new Rule[] {new LengthRule(50)}),
      new ColumnValidator("TOWN_NAME", new Rule[] {new MandatoryRule(), new LengthRule(30)}),
      new ColumnValidator(
          "POSTCODE",
          new Rule[] {new MandatoryRule(), new AlphaNumericRule(), new LengthRule(8, 5)}),
      new ColumnValidator(
          "ADDRESS_TYPE",
          new Rule[] {new MandatoryRule(), new InSetRule(new String[] {"HH", "CE"})}),
      new ColumnValidator(
          "ESTAB_TYPE",
          new Rule[] {
            new InSetRule(
                new String[] {
                  "HALL OF RESIDENCE",
                  "HOUSEHOLD",
                  "SHELTERED ACCOMMODATION",
                  "RESIDENTIAL CARAVAN",
                  "RESIDENTIAL BOAT",
                })
          }),
      new ColumnValidator(
          "ADDRESS_LEVEL",
          new Rule[] {new MandatoryRule(), new InSetRule(new String[] {"E", "U"})}),
      new ColumnValidator(
          "CE_EXPECTED_CAPACITY", new Rule[] {new NumericRule(), new LengthRule(4)}),
      new ColumnValidator("CE_SECURE", new Rule[] {new MandatoryRule(), new BooleanRule()}),
      new ColumnValidator("PRINT_BATCH", new Rule[] {new NumericRule(), new LengthRule(2)}),
      new ColumnValidator(
          "LATITUDE", new Rule[] {new LatitudeLongitudeRule(LatitudeLongitudeRule.Type.LATITUDE)}),
      new ColumnValidator(
          "LONGITUDE",
          new Rule[] {new LatitudeLongitudeRule(LatitudeLongitudeRule.Type.LONGITUDE)}),
      new ColumnValidator("OA", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator("LSOA", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator("MSOA", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator("LAD", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator(
          "REGION",
          new Rule[] {
            new MandatoryRule(),
            new LengthRule(9),
            new StartsWithAnyOfRule(new ArrayList<>(Arrays.asList("E", "W", "N", "S")))
          }),
      new ColumnValidator(
          "HTC_WILLINGNESS",
          new Rule[] {new MandatoryRule(), new InSetRule(new String[] {"1", "2", "3", "4", "5"})}),
      new ColumnValidator(
          "HTC_DIGITAL",
          new Rule[] {new MandatoryRule(), new InSetRule(new String[] {"1", "2", "3", "4", "5"})}),
      new ColumnValidator(
          "FIELDCOORDINATOR_ID", new Rule[] {new MandatoryRule(), new LengthRule(10)}),
      new ColumnValidator("FIELDOFFICER_ID", new Rule[] {new MandatoryRule(), new LengthRule(13)}),
      new ColumnValidator(
          "TREATMENT_CODE",
          new Rule[] {
            new MandatoryRule(),
            new InSetRule(
                new String[] {
                  "HH_PFE", "HH_OFE", "HH_ONE", "HH_PFW", "HH_OFW", "HH_ONW", "HH_ONS",
                  "HH_PBNN", "HH_OANN", "HH_OBNN", "CE_FEE", "CE_FUE", "CE_FEW", "CE_FUW",
                  "CE_XXNES", "CE_LPNUS"
                })
          })
    };
  }
}
