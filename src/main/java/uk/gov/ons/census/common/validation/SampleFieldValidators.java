package uk.gov.ons.census.common.validation;

public final class SampleFieldValidators {
  private SampleFieldValidators() {}

  public static ColumnValidator[] getValidators() {
    return new ColumnValidator[] {
      new ColumnValidator(
          "UPRN", new Rule[] {new MandatoryRule(), new LengthRule(13), new NumericRule()}),
      new ColumnValidator(
          "ESTAB_UPRN", new Rule[] {new MandatoryRule(), new LengthRule(13), new NumericRule()}),
      new ColumnValidator(
          "ADDRESS_TYPE",
          new Rule[] {new MandatoryRule(), new InSetRule(new String[] {"HH", "CE", "SPG"})}),
      new ColumnValidator(
          "ESTAB_TYPE",
          new Rule[] {
            new InSetRule(
                new String[] {
                  "HALL OF RESIDENCE",
                  "CARE HOME",
                  "HOSPITAL",
                  "HOSPICE",
                  "MENTAL HEALTH HOSPITAL",
                  "MEDICAL CARE OTHER",
                  "BOARDING SCHOOL",
                  "LOW/MEDIUM SECURE MENTAL HEALTH",
                  "HIGH SECURE MENTAL HEALTH",
                  "HOTEL",
                  "YOUTH HOSTEL",
                  "HOSTEL",
                  "MILITARY SLA",
                  "MILITARY US SLA",
                  "RELIGIOUS COMMUNITY",
                  "RESIDENTIAL CHILDRENS HOME",
                  "EDUCATION OTHER",
                  "PRISON",
                  "IMMIGRATION REMOVAL CENTRE",
                  "APPROVED PREMISES",
                  "ROUGH SLEEPER",
                  "STAFF ACCOMMODATION",
                  "CAMPHILL",
                  "HOLIDAY PARK",
                  "HOUSEHOLD",
                  "SHELTERED ACCOMMODATION",
                  "RESIDENTIAL CARAVAN",
                  "RESIDENTIAL BOAT",
                  "GATED APARTMENTS",
                  "MOD HOUSEHOLDS",
                  "FOREIGN OFFICES",
                  "CASTLES",
                  "GRT SITE",
                  "MILITARY SFA",
                  "EMBASSY",
                  "ROYAL HOUSEHOLD",
                  "CARAVAN",
                  "MARINA",
                  "TRAVELLING PERSONS",
                  "TRANSIENT PERSONS",
                  "MIGRANT WORKERS",
                  "MILITARY US SFA"
                })
          }),
      new ColumnValidator(
          "ADDRESS_LEVEL",
          new Rule[] {new MandatoryRule(), new InSetRule(new String[] {"E", "U"})}),
      new ColumnValidator("ABP_CODE", new Rule[] {new MandatoryRule(), new LengthRule(6)}),
      new ColumnValidator("ORGANISATION_NAME", new Rule[] {new LengthRule(60)}),
      new ColumnValidator("ADDRESS_LINE1", new Rule[] {new MandatoryRule(), new LengthRule(60)}),
      new ColumnValidator("ADDRESS_LINE2", new Rule[] {new LengthRule(60)}),
      new ColumnValidator("ADDRESS_LINE3", new Rule[] {new LengthRule(60)}),
      new ColumnValidator("TOWN_NAME", new Rule[] {new MandatoryRule(), new LengthRule(30)}),
      new ColumnValidator("POSTCODE", new Rule[] {new MandatoryRule(), new LengthRule(8)}),
      new ColumnValidator("LATITUDE", new Rule[] {new MandatoryRule()}),
      new ColumnValidator("LONGITUDE", new Rule[] {new MandatoryRule()}),
      new ColumnValidator("OA", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator("LSOA", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator("MSOA", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator("LAD", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator("REGION", new Rule[] {new MandatoryRule(), new LengthRule(9)}),
      new ColumnValidator(
          "HTC_WILLINGNESS",
          new Rule[] {
            new MandatoryRule(), new InSetRule(new String[] {"0", "1", "2", "3", "4", "5"})
          }),
      new ColumnValidator(
          "HTC_DIGITAL",
          new Rule[] {
            new MandatoryRule(), new InSetRule(new String[] {"0", "1", "2", "3", "4", "5"})
          }),
      new ColumnValidator(
          "TREATMENT_CODE",
          new Rule[] {
            new MandatoryRule(),
            new InSetRule(
                new String[] {
                  "HH_LP1E",
                  "HH_LP1W",
                  "HH_LP2E",
                  "HH_LP2W",
                  "HH_QP3E",
                  "HH_QP3W",
                  "HH_1ALSFN",
                  "HH_2BLEFN",
                  "HH_2CLEFN",
                  "HH_3DQSFN",
                  "HH_3EQSFN",
                  "HH_3FQSFN",
                  "HH_3GQSFN",
                  "HH_4HLPCVN",
                  "HH_SPGLNFN",
                  "HH_SPGQNFN",
                  "CE_LDIEE",
                  "CE_LDIEW",
                  "CE_LDIUE",
                  "CE_LDIUW",
                  "CE_QDIEE",
                  "CE_QDIEW",
                  "CE_LDCEE",
                  "CE_LDCEW",
                  "CE_1QNFN",
                  "CE_2LNFN",
                  "CE_3LSNFN",
                  "SPG_LPHUE",
                  "SPG_LPHUW",
                  "SPG_QDHUE",
                  "SPG_QDHUW",
                  "SPG_VDNEE",
                  "SPG_VDNEW"
                })
          }),
      new ColumnValidator(
          "FIELDCOORDINATOR_ID", new Rule[] {new MandatoryRule(), new LengthRule(10)}),
      new ColumnValidator("FIELDOFFICER_ID", new Rule[] {new MandatoryRule(), new LengthRule(13)}),
      new ColumnValidator(
          "CE_EXPECTED_CAPACITY", new Rule[] {new NumericRule(), new LengthRule(4)}),
      new ColumnValidator("CE_SECURE", new Rule[] {new MandatoryRule(), new BooleanRule()}),
      new ColumnValidator("PRINT_BATCH", new Rule[] {new MandatoryRule(), new LengthRule(2)}),
    };
  }
}
