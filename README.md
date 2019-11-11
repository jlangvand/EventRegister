# EventRegister

University project. This is just a sample implementation of IskraBase.

## Use case

A client needs an application to store and retrieve events (concerts, lectures etc). Each event has a name, type, location and start/end times.

## Example usage

To build, use `./gradlew jar`. The jar can be found in `build/libs/`. The app will create a database with sample data on the first run.

    new event name:exampleevent,type:example,location:town,start_time:1700,end_time:2130,start_date:2019-11-10,end_date:2019-11-10
    get all
    get name:abc
    get location:l2
    get start_date:2019-11-11
    get range event start_date 2019-11-01 2019-11-20
    get range event start_date 2019-12-11 2019-12-12
    exit

## Quirks and limitations

*   All data fields must be exactly one word.
*   The syntax is horrible.
*   Fields appear in semi-random order in lists, no way to override it.
*   User input should be checked more thoroughly to be able to give more informative error message on syntax errors.
