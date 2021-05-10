# Coffee Machine

1. It will be serving some beverages.
2. Each beverage will be made using some ingredients.
3. Assume time to prepare a beverage is the same for all cases.
4. The quantity of ingredients used for each beverage can vary. Also, the same ingredient (ex:
   water) can be used for multiple beverages.
5. There would be N ( N is an integer ) outlet from which beverages can be served.
6. Maximum N beverages can be served in parallel.
7. Any beverage can be served only if all the ingredients are available in terms of quantity.
8. There would be an indicator that would show which all ingredients are running low. We need some methods to refill
   them.

## Requirements

1. Java 16

### Running the code

1. Run `./gradlew build` to generate and executable JAR
2. Run `java -jar app/build/libs/app-all.jar < app/src/test/resources/test_1.json`

### Sample Usage

```
$ java -jar app/build/libs/app-all.jar < app/src/test/resources/test_1.json

hot_coffee is prepared
green_tea cannot be prepared because green_mixture is not available
black_tea is prepared
hot_tea cannot be prepared because sugar_syrup is not sufficient
```
