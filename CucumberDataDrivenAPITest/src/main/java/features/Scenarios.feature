Feature: To Test Http Methods in data driven method using cucumber and TestNG

Scenario: Without examples keyword
Given The GET URI is provided
Then Send the URI "https://reqres.in/api/users"
Then Verify the response

Scenario Outline: With examples keyword
Given The GET URI is given
Then Send the URI "<URI>" with headers
Then Verify the response for URI with headers

Examples:
	|URI|
	|https://reqres.in/api/users|
