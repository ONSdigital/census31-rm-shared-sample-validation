# census-shared-sample-validation
Shared/common classes which are used to validate sample data for Census Response Management

## Jackson migration note
For the 2027 test window, this shared library keeps Jackson annotation compatibility (`com.fasterxml.jackson.annotation`) while validating interoperability with Jackson 3 in tests.

After test 2027, we will review and plan the full Jackson 3 migration strategy across RM services, including coordinated communication and announcement to all integration teams that depend on RM APIs/events.

