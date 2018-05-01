### Overview

A basic Android app which simulates a vending machine.

Following capabilities are supported:
- Display machine inventory as a list of items.
- Display includes item code, item name, item price, and current inventory count.
- Allows users to input coins in denomination of 5c, 10c, and 25c.
- Displays a running total of the amount of coins entered by the user.
- Refund all funds and balance of funds.
- Allow selection of items based on item code.
- Provide option to purchase the selected item if enough funds and inventory is available.
- Reset inventory of the machine to default/ initial values.

### Unit tests
```shell
./gradlew testDebugUnitTest
```

### Assemble & Install

```shell
./gradlew assembleDebug
./gradlew installDebug
```

- Developed on Android Studio 3.1.2.

### Salient features
- Model–view–presenter (MVP) architecture.
- 39 unit tests for business rules testing.
- `Dagger2` for dependency injection.
- `Timber` used for logging.
- `Room persistence library` for storing items.

#### Where to start in the code?
- `VendingMachinePresenterTest` for fundamental business rules testing.
- `VendingMachineFragment` for vending machine interface.
- `VendingMachinePresenter` for high level business modeling of the vending machine.
