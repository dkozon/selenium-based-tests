# selenium-based-tests

Project purpose:

*Training project for teaching practical side of test automation using Java 1.8 and Selenium WebDriver 3.*

*Current project setup has tests and configuration for Google Gmail (www.google.com/en) and The Internet page (https://the-internet.herokuapp.com/).*

**Tests and framework is living product and will be extended frequent in the time.**


# Docker configuration for Grid
- run Docker Terminal
- go to project folder and see docker-compose.yml - there is configuration of the containers for hub and 2 nodes (FF and Chrome)
- to run containers use docker-compose up
- wait a while to establish containers
- then you can run tests against grid using IP address of the docker
- to stop docker use docker-compose stop (for process in the background) or ctrl+c

# OWASP ZAP proxy
- install OWASP ZAP
- run it with default proxy port on 8080
- while running tests from console set parameter -Ddriver=>>browserName<<ZAP for example -Ddriver=firefoxZAP

For more supported -Ddriver parameter values look onto BaseTest