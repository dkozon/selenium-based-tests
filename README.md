## Java Selenium Training project a.k.a selenium-based-tests

Project purpose:

*Training project for teaching practical side of test automation using Java 1.8 and Selenium WebDriver 3.x*

*Current project setup has tests and configuration for Google Gmail (www.google.com/en) and The Internet page (https://the-internet.herokuapp.com/).*

**Tests and framework is living product and will be extended frequent in the time.**

### Requirements

Currently selenium-based-tests is supporting examples of tests running against (all windows instances):
- Firefox (geckodriver 0.24.0)
- Chrome (chromedriver 73.0.3683.20)
- Edge (Microsoft WebDriver for Windows 10 compilation 16299)

For running against other platforms there is need to download and change drivers for executing tests in BaseTest.java)


### Additional configuration
#### Docker configuration for Grid

Sometimes tests needs to be run through Selenium Grid. For setting this up the simplest way is to use Docker.
For basic configuration run against Docker Selenium Grid you need to follow below instruction:

- Run Docker Terminal installed on your environment
- Go to project folder and see *docker-compose.yml* - there is stored configuration of the containers for **hub** and **2 nodes** (Firefox and Chrome)
- For running containers use *docker-compose up*
- Wait a while for automation establishing of containers
- Then you can run tests against grid using IP address of the docker
- For stopping docker use *docker-compose stop* (for process in the background) or combination of keys *ctrl+c*

#### Docker configuration for running test environment 

There is example of test which is running against environment set up in docker named **FileUploadTest**.

To run it smoothly you need to have docker environment up and running on execution machine.

Current configuration is running docker container on the same machine where tests are executing - for training purposes it should be good enough
but for enterprise it would be perfect to split running docker container on different instance than executing tests machine.

First run test while image of docker container with app is not deployed yet, could take about couple of minutes to establish - 
no worries test will wait until it will be ready and will continue when container will be on place.

#### Proxy

This feature is dedicated for running tests through any proxy while you would like to 
test and analyze your enpoints from external tools like Burp, Owasp ZAP or any other.

For establishing connection with proxy you need to follow next steps:

- Run your proxy installed on your environment
- Run it with default proxy port on 8080 (can be changed to other while default one can't be used)
- While running tests from console set parameter -Ddriver=>>browserName<<Proxy for example -Ddriver=firefoxProxy

For more supported -Ddriver parameter values look onto BaseTest
