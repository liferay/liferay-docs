# Arquillian Extension for Liferay Example [](id=arquillian-extension-for-liferay-example)

The
[Arquillian Blade Example](https://github.com/liferay-labs/arquillian-blade-example)
project demonstrates how to use the
[Arquillian Liferay Extension](https://github.com/liferay-labs/arquillian-liferay)
to perform integration and functional tests and how to use JaCoCo to measure
code coverage. In this tutorial, you'll use the example project to learn how the
Arquillian Liferay Extension and JaCoCo work. 

Here are the tutorial's sections:

-   [Example Portlet](#example-portlet)
-   [Integration and Functional Testing with Arquillian](#integration and-functional-testing-with-arquillian)
-   [Measuring Code Coverage with JaCoCo](#measuring-code-coverage-with-jacoco)
-   [Running the Arquillian Blade Example Tests](#running-the-arquillian-blade-example-tests)

## Example Portlet [](id=example-portlet)

The example portlet uses Liferay's `MVCPortlet` class and JSPs to provide a
service for calculating the sum of two numbers. 

![Figure 1: The Arquillian Sample Portlet returns the sum of two entered values.](../../images/arquillian-example-portlet.png)

The example project follows the standard OSGi module folder structure with Java
files in `src/main/java/`, resource files in
`src/main/resources/META-INF/resources`, and build files in the project root. 

Here are this portlet's primary files:

-   `SampleService.java`: This interface defines method
    `public long add(final int addend1, final int addend2)` for returning the
    sum of two numbers. 

-   `SampleServiceImpl.java`: Uses OSGi Declarative Services to implement the
    `SampleService` interface. 

-   `SamplePortlet.java`: A Liferay `MVCPortlet` that processes portlet action
    commands from the view JSP and renders the result of executing the `add`
    service.

-   `bnd.bnd`: Specifies the module's name, symbolic name, and version.

-   `init.jsp`: Imports classes and tag libraries for the view. 

-   `view.jsp`: Provides a form for calculating the sum of two numbers. 

The `view.jsp` file and `SamplePortlet` class file pass following variables to
each other:

-   `firstParameter`: First number to add
-   `secondParameter`: Second number to add
-   `result`: Sum of the two numbers

The browser-based functional test demonstrated later uses these variables.
Here's the `view.jsp` file contents:

    <%@ include file="/init.jsp" %>

    <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

    <%
    int firstParameter = ParamUtil.getInteger(request, "firstParameter", 1);
    int secondParameter = ParamUtil.getInteger(request, "secondParameter", 1);
    int result = ParamUtil.getInteger(request, "result");
    %>

    <portlet:actionURL name="add" var="portletURL" />

    <p>
    <b>Sample Portlet is working!</b>
    </p>

    <aui:form action="<%= portletURL %>" method="post" name="fm">
    <aui:input inlineField="<%= true %>" label="" name="firstParameter" size="4" type="int" value="<%= firstParameter %>" />
    <span> + </span>
    <aui:input inlineField="<%= true %>" label="" name="secondParameter" size="4" type="int" value="<%= secondParameter %>" />
    <span> = </span>
    <span class="result"><%= result %></span>

    <aui:button type="submit" value="add" />
    </aui:form>

Now that you've seen the portlet under test, you can examine the tests. 

## Integration and Functional Testing with Arquillian [](id=integration and-functional-testing-with-arquillian)

The Arquillian Blade Example demonstrates testing the portlet's service via the
API and web interface. 

The example has two tests:

-   [Integration Test](#integration-test): Class `BasicPortletIntegrationTest` 
    invokes the `SampleService`'s `add` method directly. 
-   [Functional Test](#functional-test): Class `BasicPortletFunctionalTest`
    invokes the `add` method indirectly via a web browser. 

The test classes are in the `src/testIntegration/java` folder and test resource
files are in `src/testIntegration/resources` folder. 

The example integration test is basic and a great place to start. 

### Integration Test [](id=integration-test)

Integration testing focuses on testing a subset of modules and their
interactions. Although the `BasicPortletIntegrationTest` class only demonstrates
invoking one module's method `SampleService.add`, an Arquillian integration test
could just as easily invoke multiple methods in many modules. 

Here's the `BasicPortletIntegrationTest` class: 

    package com.liferay.arquillian.test;

    import com.google.common.io.Files;

    import com.liferay.arquillian.containter.remote.enricher.Inject;
    import com.liferay.arquillian.sample.service.SampleService;
    import com.liferay.portal.kernel.exception.PortalException;

    import java.io.File;
    import java.io.IOException;

    import org.jboss.arquillian.container.test.api.Deployment;
    import org.jboss.arquillian.junit.Arquillian;
    import org.jboss.shrinkwrap.api.ShrinkWrap;
    import org.jboss.shrinkwrap.api.spec.JavaArchive;

    import org.junit.Assert;
    import org.junit.Test;
    import org.junit.runner.RunWith;

    @RunWith(Arquillian.class)
    public class BasicPortletIntegrationTest {

        @Deployment
        public static JavaArchive create() throws Exception {
            final File tempDir = Files.createTempDir();

            String gradlew = "./gradlew";

            String osName = System.getProperty("os.name", "");
            if (osName.toLowerCase().contains("windows")) {
                gradlew = "./gradlew.bat";
            }

            final ProcessBuilder processBuilder = new ProcessBuilder(
                gradlew, "jar", "-Pdir=" + tempDir.getAbsolutePath());

            final Process process = processBuilder.start();

            process.waitFor();

            final File jarFile = new File(
                tempDir.getAbsolutePath() +
                    "/com.liferay.arquillian.sample-1.0.0.jar");

            return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
        }

        @Test
        public void testAdd() throws IOException, PortalException {
            final long result = _sampleService.add(1, 3);

            Assert.assertEquals(4, result);
        }

        @Inject
        private SampleService _sampleService;

    }

JUnit annotation `@RunWith(Arquillian.class)` marks the class for Arquillian to
execute. 

JUnit annotation `@Test` designates the `testAdd` method as a test method. It
invokes a `SampleService` object's `add` method and asserts the result. 

    final long result = _sampleService.add(1, 3);

    Assert.assertEquals(4, result);

The Liferay Arquillian Extension injects the class with the `SampleService`
implementation (i.e., a `SampleServiceImpl` instance) via the `_sampleService`
field. 

    @Inject
    private SampleService _sampleService;

Arquillian packages the test class and resources as a Java archive (JAR file)
using the class's `create` method. The method invokes the project's `jar`
Gradle task to create the test JAR Arquillian executes. 

    final File tempDir = Files.createTempDir();

    final ProcessBuilder processBuilder = new ProcessBuilder(
        "./gradlew", "jar", "-Pdir=" + tempDir.getAbsolutePath());

    final Process process = processBuilder.start();

    process.waitFor();

    final File jarFile = new File(
        tempDir.getAbsolutePath() +
            "/com.liferay.arquillian.sample-1.0.0.jar");

    return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);

Note: On Windows replace `gradlew` with `gradlew.bat` in the `create`
method.

Let's look at this test's dependencies. 

#### Dependencies [](id=dependencies)

The project's `build.gradle` file includes dependencies on Liferay's Arquillian container, JUnit, and Arquillian's JUnit container for this test:

    testIntegrationCompile group: "com.liferay.arquillian", name: "com.liferay.arquillian.arquillian-container-liferay", version: "1.0.6"
    testIntegrationCompile group: "junit", name: "junit", version: "4.12"
    testIntegrationCompile group: "org.jboss.arquillian.junit", name: "arquillian-junit-container", version: "1.1.11.Final"
 
Arquillian tests are configurable too.  

#### Arquillian Configuration [](id=arquillian-configuration)

Arquillian configuration file `src/testIntegration/resources/arquillian.xml`
uses property `deploymentExportPath` (optional) to write a test archive (e.g.,
JAR file) to a folder before deploying the tests. You can inspect all the test
files from this archive. To highlight the `deploymentExportPath` property,
here's an abbreviated view of the
[`arquillian.xml` file](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/testIntegration/resources/arquillian.xml):

<?xml version="1.0" encoding="UTF-8"?>
<arquillian xmlns="http://jboss.org/schema/arquillian"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://jboss.org/schema/arquillian http://jboss.org/schema/arquillian/arquillian_1_0.xsd">

    <!-- More content here -->

    <engine>
        <property name="deploymentExportPath">build/deployments</property>
    </engine>
</arquillian>

The project uses  Java Management Extensions (JMX) to deploy OSGi modules to
@product@. Enabling JMX for the application server is next. 

#### JMX Settings [](id=jmx-settings)

The Arquillian Liferay Extension uses JMX to deploy OSGi modules to @product@.
Apache Aries JMX exposes the JMX API that Arquillian uses to
install/deploy/start the modules.

Since DXP Digital Enterprise 7.0 Fix Pack 16 and Liferay CE 7.0 GA4, Liferay
Workspace's `startTestableTomcat` Gradle task installs the Apache Aries JMX
modules automatically. In case you're using and earlier @product@ version or
haven't already installed the Aries modules, here's their group ID, artifact ID,
and version information. You can install them using
[Apache Felix GoGo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell):

    "org.apache.aries.jmx:org.apache.aries.jmx:1.1.5"
    "org.apache.aries:org.apache.aries.util:1.1.3"

The Arquillian Blade Example enables JMX in Apache Tomcat on port 8099 without
authentication. It's enabled via the `setenv.*` scripts. 

##### `setenv.sh` JMX Settings [](id=setenv-sh-jmx-settings)

    JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    CATALINA_OPTS="${CATALINA_OPTS} ${JMX_OPTS}"

##### `setenv.bat` JMX Settings [](id=setenv-bat-jmx-settings)

    set "JMX_OPTS=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    set "CATALINA_OPTS=%CATALINA_OPTS% %JMX_OPTS%"

You can refer to Apache Tomcat's
[guide](https://tomcat.apache.org/tomcat-7.0-doc/monitoring.html#Enabling_JMX_Remote)
for more details and options on enabling JMX.

Test class `BasicPortletIntegrationTest` and its setup is straightforward. The
Arquillian Blade Example's functional tests are more interesting and demonstrate
browser-based testing. 

### Functional Test [](id=functional-test)

Test class `BasicPortletFunctionalTest` is a functional browser-based test
that's easy to follow. It verifies the portlet's installation and uses the web
form to test the portlet's sum calculation service. 

Here's the `BasicPortletFunctionalTest` class:

    package com.liferay.arquillian.test;

    import com.google.common.io.Files;

    import com.liferay.arquillian.portal.annotation.PortalURL;
    import com.liferay.portal.kernel.exception.PortalException;

    import java.io.File;
    import java.io.IOException;

    import java.net.URL;

    import org.jboss.arquillian.container.test.api.Deployment;
    import org.jboss.arquillian.container.test.api.RunAsClient;
    import org.jboss.arquillian.drone.api.annotation.Drone;
    import org.jboss.arquillian.junit.Arquillian;
    import org.jboss.shrinkwrap.api.ShrinkWrap;
    import org.jboss.shrinkwrap.api.spec.JavaArchive;

    import org.junit.Assert;
    import org.junit.Test;
    import org.junit.runner.RunWith;

    import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;

    @RunAsClient
    @RunWith(Arquillian.class)
    public class BasicPortletFunctionalTest {

        @Deployment
        public static JavaArchive create() throws Exception {
            final File tempDir = Files.createTempDir();

            String gradlew = "./gradlew";

            String osName = System.getProperty("os.name", "");
            if (osName.toLowerCase().contains("windows")) {
                gradlew = "./gradlew.bat";
            }

            final ProcessBuilder processBuilder = new ProcessBuilder(
                gradlew, "jar", "-Pdir=" + tempDir.getAbsolutePath());

            final Process process = processBuilder.start();

            process.waitFor();

            final File jarFile = new File(
                tempDir.getAbsolutePath() +
                    "/com.liferay.arquillian.sample-1.0.0.jar");

            return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
        }

        @Test
        public void testAdd()
            throws InterruptedException, IOException, PortalException {

            _browser.get(_portlerURL.toExternalForm());

            _firstParamter.clear();

            _firstParamter.sendKeys("2");

            _secondParameter.clear();

            _secondParameter.sendKeys("3");

            _add.click();

            Thread.sleep(5000);

            Assert.assertEquals("5", _result.getText());
        }

        @Test
        public void testInstallPortlet() throws IOException, PortalException {
            _browser.get(_portlerURL.toExternalForm());

            final String bodyText = _browser.getPageSource();

            Assert.assertTrue(
                "The portlet is not well deployed",
                bodyText.contains("Sample Portlet is working!"));
        }

        @FindBy(css = "button[type=submit]")
        private WebElement _add;

        @Drone
        private WebDriver _browser;

        @FindBy(css = "input[id$='firstParameter']")
        private WebElement _firstParamter;

        @PortalURL("arquillian_sample_portlet")
        private URL _portlerURL;

        @FindBy(css = "span[class='result']")
        private WebElement _result;

        @FindBy(css = "input[id$='secondParameter']")
        private WebElement _secondParameter;

    }

Arquillian annotation @RunAsClient and JUnit annotation @RunWith mark this class
as a web client that runs on Arquillian.

Similar to the `BasicPortletIntegrationTest` class's `create` method, the method
packages the test as a JAR file for Arquillian to execute. 

+$$$

**Reminder**: On Windows in the `create` method, replace `gradlew` with
`gradlew.bat`.

$$$

The `testInstallPortlet` method verifies the portlet content shows. 

    final String bodyText = _browser.getPageSource();

    Assert.assertTrue(
        "The portlet is not well deployed",
        bodyText.contains("Sample Portlet is working!"));

The client test class uses the following fields in its tests:

-   `_browser`: Arquillian annotation `@Drone` sets this field as a Selenium
    `WebDriver` (browser).

        @Drone
        private WebDriver _browser;

-   `_portlerURL`: Liferay Arquillian annotation `@PortalURL` assigns this field
    the portlet's URL. 

        @PortalURL("arquillian_sample_portlet")
        private URL _portlerURL;

-   `_firstParamter` and `_secondParameter`: Selenium annotation `FindBy` and
    the JavaScript selectors set these fields as Selenium `WebElement`s for the
    two numbers to be added. 

        @FindBy(css = "input[id$='firstParameter']")
        private WebElement _firstParamter;

        @FindBy(css = "input[id$='secondParameter']")
        private WebElement _secondParameter;

-   `_add`: Set to the form's `submit` button. 
 
        @FindBy(css = "button[type=submit]")
        private WebElement _add;

-   `_result`: Set to the resulting sum of the two numbers. 

        @FindBy(css = "span[class='result']")
        private WebElement _result;

Using the `*Parameter` fields, the `testAdd` method injects numbers `2` and `3`
into the form, submits the form, and asserts the result is `5`. 

    _browser.get(_portlerURL.toExternalForm());

    _firstParamter.clear();

    _firstParamter.sendKeys("2");

    _secondParameter.clear();

    _secondParameter.sendKeys("3");

    _add.click();

    Thread.sleep(5000);

    Assert.assertEquals("5", _result.getText());

Next, consider this functional test's dependencies. 

#### Dependencies

The `BasicPortletFunctionalTest` class requires the Graphine extension to
Arquillian for functional client testing. Here's the dependency from the Gradle
file `build.gradle`:

    testIntegrationCompile group: "org.jboss.arquillian.graphene", name: "graphene-webdriver", version: "2.1.0.Final"

For more information on functional testing using Graphine, see this
[guide](http://arquillian.org/guides/functional_testing_using_graphene/)

The test also has additional Arquillian configuration elements. 

#### Arquillian configuration

In addition to the `engine` element `BasicPortletIntegrationTest` required in
the `arquillian.xml` file, this functional test specifies the portal's URL and
uses `phantomjs` to specify the browser. 

    <?xml version="1.0" encoding="UTF-8"?>
    <arquillian xmlns="http://jboss.org/schema/arquillian"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xsi:schemaLocation="http://jboss.org/schema/arquillian http://jboss.org/schema/arquillian/arquillian_1_0.xsd">

        <extension qualifier="webdriver">
            <property name="browser">phantomjs</property>
        </extension>

        <extension qualifier="graphene">
            <property name="url">http://localhost:8080</property>
        </extension>

        <engine>
            <property name="deploymentExportPath">build/deployments</property>
        </engine>
    </arquillian>

+$$$

**Note**: you can also inject the URL of the container (e.g., Tomcat) using the
annotation `@ArquillianResource`. You can use one of these solutions (if you are
using the Arquillian Liferay Extension):
    
1.  In a deployment method or field in your test class, designate the URL using
    the annotation `@ArquillianResource`.

2.  Configure Arquillian using the graphene URL property (via
    `arquillian.xml`, `arquillian.properties`, or System Properties).

$$$

There are also some portal properties you should set for functional
browser-based testing.  

#### Portal Properties

The Arquillian Blade Example specifies these Portal properties in file
`src/testIntegration/resources/portal-ext.properties`:

    browser.launcher.url=
    setup.wizard.enabled=false

Setting `browser.launcher.url` to an empty value prevents @product@ from
launching a browser on its own. And setting `setup.wizard.enabled=false`
bypasses launching the setup wizard. The project's Gradle task `copyPortalExt`,
copies the properties file into the @product@ installation.

You've explored the Arquillian Blade Example's tests. As you develop tests, you
might find it helpful to know what parts of the code your tests cover. The
example project  uses JaCoCo to identify the portlet code its tests cover. 

## Measuring Code Coverage with JaCoCo [](id=measuring-code-coverage-with-jacoco)

[JaCoCo](http://eclemma.org/jacoco/) is a code coverage library for Java. The
Arquillian Blade Example uses JaCoCo to produce a code coverage report based on
the executed tests. 

![Figure 2: JaCoCo reports the parts classes, methods, and lines of code your tests cover.](../../images/arquillian-example-jacoco-results.png)

### Enabling JaCoCo

To use JaCoCo, you must attach a JaCoCo agent to the JVM. To do so, you can
append the following JaCoCo options to the `CATALINA_OPTS` variable in Tomcat's
environment script. 

#### `setenv.sh` JaCoCo Settings [](id=setenv-sh-jacoco-settings)

    JACOCO_OPTS="-javaagent:PATH_TO_JACOCO_AGENT_JAR/jacocoagent.jar=destfile=JACOCO_EXEC_FILE,output=file,append=true,jmx=true"
    
    CATALINA_OPTS="${CATALINA_OPTS} ${JACOCO_OPTS}"

#### `setenv.bat` JaCoCo Settings [](id=setenv-bat-jacoco-settings)

    set "JACOCO_OPTS=-javaagent:PATH_TO_JACOCO_AGENT_JAR/jacocoagent.jar=destfile=JACOCO_EXEC_FILE,output=file,append=true,jmx=true"
    
    set "CATALINA_OPTS=%CATALINA_OPTS% %JACOCO_OPTS%"

`PATH_TO_JACOCO_AGENT_JAR` is the path to the `jacocoagent.jar` file and
`JACOCO_EXEC_FILE` is the path to the JaCoCo result dump. Replace these values
with the appropriate paths. 

### JaCoCo Build File Instructions

The Gradle build file `build.gradle` specifies several JaCoCo related
instructions.

1.  Apply the plugin to the build:

        apply plugin: 'jacoco'

        jacoco {
            toolVersion = '0.7.9'
        }

2.  Copy the JaCoCo agent into the project build. 

        task copyJacocoAgent(type: Copy) {
        println configurations.jacocoAgent

            configurations.jacocoAgent.asFileTree.each {
                from(zipTree(it))
            }

            into "${rootDir}/build/jacoco"
        }

3.  Dump the code coverage data:

        task dumpJacoco {
            doLast {
                def serverUrl = 'service:jmx:rmi:///jndi/rmi://localhost:8099/jmxrmi'
                String beanName = "org.jacoco:type=Runtime"
                def server = JmxFactory.connect(new JmxUrl(serverUrl)).MBeanServerConnection
                def gmxb = new GroovyMBean(server, beanName)

                println "Connected to:\n$gmxb\n"
                println "Executing dump()"
                gmxb.dump(true)
            }
        }

4.  Generate JaCoCo reports in HTML:

        jacocoTestReport {
            dependsOn dumpJacoco
            group = "Reporting"
            reports {
                xml.enabled true
                csv.enabled false
                html.destination "${buildDir}/reports/coverage"
            }
            executionData = files("${rootDir}/build/jacoco/testIntegration.exec")
        }

JaCoCo code coverage reporting runs as part of the project's `testIntegration`
Gradle task. 

## Running the Arquillian Blade Example Tests [](id=running-the-arquillian-blade-example-tests)

That's it! If you've forked and cloned the
[Arquillian Blade Example](https://github.com/liferay-labs/arquillian-blade-example)
locally from GitHub, use the following command to run the tests:

    gradlew testIntegration

The command accomplishes these things:

1.  Downloads and installs @product-ver@ bundled with Apache Tomcat
2.  Starts a @product-ver@ server
3.  Runs the tests, including the functional tests that use the browser
4.  Shuts down the server
5.  Reports test and code coverage results

For `testIntegration` task details, you can examine the `build.gradle` file in
the Arquillian Blade Example project.

The command can take a long time to execute because
of all it does. 

The following files report the results:

-   *Tests*: `build\reports\tests\testIntegration\index.html`
-   *Code Coverage*: `build\reports\coverage\index.html`

+$$$

Note: before running again, you must delete the `build/reports/` and
`build/test-results/` folders.

$$$

![Figure 3: Open the test reports to check results.](../../images/arquillian-example-test-results.png)

Now that you've examined functional and integration testing and code coverage in
the Arquillian Blade Example, you can set up your projects with similar tests
and measure code coverage. 
