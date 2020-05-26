Feature: To fetch the country details


Scenario: To get all country details
Given url 'https://restcountries.eu'
And path '/rest/v2/alpha/co'
When method get
Then status 200

And def getResponse = response
Then match getResponse.languages[*].name contains ["Spanish"]




	


