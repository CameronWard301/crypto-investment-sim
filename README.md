# Crypto Currency Investment Simulator

This is a simulator that lets you practice investing in Bitcoin, Ethereum, and Cardano with Dollars, Pounds and Euros.
You can track your progress on the portfolio page to see how well your investment is doing

## Prerequisites:

* Spring Boot 2.6.1
* MySQL Server > 5.7
* Gradle 7.2
* JDK 11
* Obtain an API key from https://freecurrencyapi.net/
* Obtain an API key from https://coinmarketcap.com/api/
* Get a recaptcha V2 Site and Secret Key from https://www.google.com/recaptcha/admin

## Installation
### Method 1
* Download and install the required prerequisites
* Download and extract the repository from gitHub
* Make sure your SQL server is running
* Navigate to the directory you downloaded the repository and go to : `src/main/resources/application.properties`
Modify this file to contain your database url, name and password.
* Open a terminal in the root directory and run `.\gradle bootRun --args='--google.recaptcha.key.site=YOUR_SITE_KEY --recaptcha.validation.secret-key=YOUR_SECRET_KEY'`
* Navigate to localhost:8080 in a web-browser

### Method 2
* Download the latest stable Jar file from GitHub
* open a terminal in the same directory as the Jar file and run `java -jar .\##Name of .Jar##` 
* By default, this will use the application.properties configuration
  * To customise the configuration from the command line write:  
  * `java -jar .\##NAME_OF_.jar## --property1=xyz --coin.requests=true....`

## Usage/Properties:
* spring.datasource.url the type of datasource, database version, url and schema: 
  * Eg: ```{DATASOURCE}:${DB_VERSION}://${URL}:${PORT}/${DATABASE_NAME)```
  * ```jdbc:mysql://localhost:3306/crypto```
* spring.datasource.username - database username
* spring.datasource.password - database password
* spring.jpa.hibernate.ddl-auto
  * Default is ```update```
  * Determines how the app interacts with the database, update creates and updates database if there are changes / tables that are not created yet
  * create-drop - creates the db on startup and deletes the db on shutdown
* spring.jpa.open-in-view - set to ```false``` for better performance
* spring.session.store-type
  * Set to ```jdbc``` by default
* spring.session.jdbc.initialize-schema
  * Set to ```always``` by default
* server.reactive.session.timeout
  * How long users stay logged in for
  * Default is ```60m``` (60 minutes)
* spring.thymeleaf.cache
  * Set to false for development purposes for Hot reload
  * Set to true for deployment
* coin.requests
  * Set the app to send GET requests to cryptocurrency brokers
  * ```false``` - don't send GET requests (default) good for development if you are restarting the app lots
  * ```true``` - Send get requests **Required on initial boot**
coin.frequency.USD_EUR_ETH 
  * How quickly requests are made to ```freecurrencyapi.net``` for coins USD EUR ETH in ms
  * ```300000``` - 5 minutes default for the free plan, don't go any quicker than this but can be slower
* coin.frequency.ADA_BTC
  * How quickly requests are made to ```pro-api.coinmarketcap.com``` for coins ADA BTC in ms
  * ```270000``` - 4.5 minutes for the free plan - don't go quicker than this but can be slower
*portfolio.history.frequency
  * How often each user's portfolio history is updated in ms
  * Set to ```3600000``` (1 hour) - not tested with a larger user base
* crypto.api.key.freecurrencyAPI
  * Your API key for free currency API
* crypto.api.key.coinmarketcapAPI
  * Your API key for Coin Market CAP API
* google.recaptcha.key.site
  * Your Google Recaptcha Site Key - used on the signup page to prevent bots 
* recaptcha.validation.secret-key
  * Secret Key for the recaptcha

