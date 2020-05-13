$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Scenarios.feature");
formatter.feature({
  "line": 1,
  "name": "Testing all HTTP methods",
  "description": "",
  "id": "testing-all-http-methods",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Testing HTTP GET method without headers",
  "description": "",
  "id": "testing-all-http-methods;testing-http-get-method-without-headers",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "Actual URI for GET request is provided",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Send the GET request",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "Verify the GET response",
  "keyword": "Then "
});
formatter.match({
  "location": "HttpMethodsTest.setUp()"
});
formatter.result({
  "duration": 314865900,
  "status": "passed"
});
formatter.match({
  "location": "HttpMethodsTest.sendGetRequest()"
});
formatter.result({
  "duration": 1212957900,
  "status": "passed"
});
formatter.match({
  "location": "HttpMethodsTest.verifyGetResponse()"
});
formatter.result({
  "duration": 24305000,
  "status": "passed"
});
});