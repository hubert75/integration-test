# integration-test

The aim of the application is to retrieve transaction list for a given bank account and present it back to the user via simple text based web page.

## frameworks used

It is required that the application is written using the following frameworks/libraries:

- https://github.com/ReactiveX/RxJava/tree/2.x
- https://square.github.io/retrofit/
- https://github.com/google/auto/blob/master/value/userguide/index.md
- https://dagger.dev/users-guide

## application current state

The application is compiling and can be run using a command:

```
gradle run
```

The application has already a working endpoint exposed under: http://localhost:8200/link

## external data

The application can utilize a swagger api exposed under: https://app.swaggerhub.com/apis/empsp0/integration-test/1.0.0 to test the implementation

## future state

It is expected that the given endpoint request will return a transaction list data in a text based form of some sort eg.

```json
"transactions": [
    {
      "transactionId": "cdf3758d-ca17-4182-baf3-0ce0dd74776f",
      "bookedDate": "2019-07-01",
      "payment": {
        "currency": "EUR",
        "amount": "8.80"
      }
    },
    {
      "transactionId": "ddc09541-0395-4cee-ad54-f2a1286f81ff",
      "bookedDate": "2019-08-02",
      "payment": {
        "currency": "EUR",
        "amount": "44.52"
      }
    }
]
```

Please note, the above may vary depending on the data required to be returned to the application page, please refer to [Transaction](src/main/java/com/binarapps/integrationtest/http/dto/Transaction.java) class.

## KPI's

The application in it's final state should be written in clean code fashion (possibly following the thumb rules already established in the current state of the application (not obligatory)). The goal is to have the application return data (it is perfectly valid to assume certain aspects in order not to be blocked from delivering the app), secondary objectives would be to cover the business code with testing logic (junits, possibly integration tests eg. with use of wiremock).

## support

Please feel free to write to: turski.marek@gmail.com in case you're stuck or found a bug that is a blocker for the delivery.