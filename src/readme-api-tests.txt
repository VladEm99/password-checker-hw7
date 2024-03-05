Testing API endpoints for positive and negative scenarios
API - http://35.208.34.242:8080/swagger-ui/index.html#/

Tools:
    1. intellij idea
    2. rest assured
    3. Junit
    4. maven

Valid orders ID`s: 1 - 10
Valid API key for authentication: 16-digit

Check list:
| Nr  | Check name                                                                                        | Status         |
| --- | ------------------------------------------------------------------------------------------------- | -------------- |
| 1   | Returns 204 for DELETE method request with valid ID                                               | Passed         |
| 2   | Returns 404 for DELETE method request with invalid ID (order not found, ID greater than 10)       | Failed         |
| 3   | Returns 401 for DELETE method request with invalid API key (8-digit)                              | Passed         |
| 4   | Returns 401 for DELETE method request with invalid API key (16 chars)                             | Passed         |
| 5   | Returns 400 for DELETE method request with no given API key in headers (bad request)              | Passed         |
| 6   | Returns 404 for DELETE method request with invalid ID (order not found, ID - char instead of int) | Failed         |
| 6   | Returns 200 for GET method request with username and password in query params, returns API key    | Passed         |
| 6   | Returns 400 for GET method request without username and password, doesn't return API key           | Failed         |