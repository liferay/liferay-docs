# Running the Arquillian Example [](id=running-the-arquillian-example)

You're ready to run the Arquillian Blade Example tests. Open a terminal to the
project root and execute the following command:

    gradlew testIntegration

The command does these things:

1.  Downloads and installs @product-ver@ bundled with Apache Tomcat
2.  Starts a @product-ver@ server
3.  Runs the tests, including the functional browser-based tests
4.  Shuts down the server
5.  Reports test and code coverage results

For `testIntegration` task details, examine the `build.gradle` file in the
project root. 

The command can take several minutes to execute because of all it does.  

Test results are found in these locations:

-   *Tests*: `build\reports\tests\testIntegration\index.html`
-   *Code Coverage*: `build\reports\coverage\index.html`

![Figure 1: Open the test reports to analyze the results.](../../../images/arquillian-example-test-results.png)

+$$$

Note: before rerunning the tests, you must delete the `build/reports/` and
`build/test-results/` folders.

$$$

Now that you've examined Arquillian functional and integration tests and JaCoCo
code coverage capabilities, you can create similar tests and improve test code
coverage in your projects. 

**Related Topics**

[Liferay's Slim Runtime](/develop/tutorials/-/knowledge_base/7-0/liferay-slim-runtime)

[Unit Testing with JUnit](/develop/tutorials/-/knowledge_base/7-0/unit-testing-with-junit)
