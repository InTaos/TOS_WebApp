# TosWebApp-Automation-Testing
Automation testing framewok for "Thinkorswim" Web application. Tech: JAVA, TESTNG, MAVEN, JENKINS

1. The xml runner files are in src/recources
2. All Methods, Locators and @Tests are in src/test/java

**Tests:**
1. Login tests.
2. BUY and SELL orders end to end tests.
3. Order History tests.
4. BUY & SELL orders creation tests.
5. BUY & SELL orders cancelation tests.

**Testing techniques:** Boundary Value Analysis, Decision Table Testing, End to end tests, Pairwise Testing

All test are executed in context of **cross-browser parallel** execution.

**Browsers:** Chrome, Firefox, Edge

All of the parametrization and orchestration of tests and browsers is done in **testng.xml** file.

**The project is suitable with **Jenkins** integration and parametrization.**

In according to run all test suits simultaneously you need: 
1. To install the mentioned browsers: **Chrome, Firefox, Edge**
2. You need to have three different registrations for accounts at https://papermoney.thinkorswim.com/platform/index.html#!/pmregister
3. You need to replace your credentials in **tesng.xml file for "user" and "password".**

