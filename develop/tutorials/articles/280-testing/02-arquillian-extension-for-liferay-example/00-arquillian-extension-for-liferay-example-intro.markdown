# Arquillian Extension for Liferay Example [](id=arquillian-extension-for-liferay-example)

The
[Arquillian Blade Example project](https://github.com/liferay-labs/arquillian-blade-example)
demonstrates performing integration and functional tests using the
[Arquillian Liferay Extension](https://github.com/liferay-labs/arquillian-liferay).
Additionally it measures code coverage using
[JaCoCo](http://eclemma.org/jacoco/).
In this tutorial, you'll learn how the Arquillian Liferay Extension and JaCoCo
work. You can download the Arquillian Blade Example project
[here](https://dev.liferay.com/documents/10184/656312/arquillian-blade-example.zip/27250a6e-4acf-6ed3-33bb-9a8a87fd0776)
or access its latest code on
[GitHub](https://github.com/liferay-labs/arquillian-blade-example). 

Here are the tutorial sections:

-   [Examining the Sample Portlet](#sample-portlet)
-   [Integration and Functional Testing with Arquillian](#integration-and-functional-testing-with-arquillian)
-   [Measuring Code Coverage with JaCoCo](#measuring-code-coverage-with-jacoco)
-   [Running the Tests](#running-the-tests)

## Examining the Sample Portlet [](id=sample-portlet)

The sample portlet calculates the sum of two numbers. 

![Figure 1: The Arquillian Sample Portlet calculates the sum of two numbers.](../../images/arquillian-example-portlet.png)

The portlet project comprises a portlet class, service classes, and JSPs. It
follows the standard OSGi module folder structure with Java files in
`src/main/java/`, resource files in `src/main/resources/META-INF/resources`, and
build files in the project root. 

Here are the primary files:

-   [`SampleService.java`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/java/com/liferay/arquillian/sample/service/SampleService.java):
    Provides an interface that defines method `public long add(final int
    addend1, final int addend2)` for returning the sum of two numbers. 

-   [`SampleServiceImpl.java`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/java/com/liferay/arquillian/sample/service/SampleServiceImpl.java):
    Uses OSGi Declarative Services to implement the
    `SampleService` interface. 

-   [`SamplePortlet.java`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/java/com/liferay/arquillian/sample/portlet/SamplePortlet.java):
    Extends
    [Liferay `MVCPortlet`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
    and processes portlet action commands and renders the result of executing
    the `add` service.

-   [`bnd.bnd`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/bnd.bnd):
    Specifies the module's name, symbolic name, and version.

-   [`init.jsp`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/resources/META-INF/resources/init.jsp):
    Imports classes and tag libraries for the view layer. 

-   [`view.jsp`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/resources/META-INF/resources/view.jsp):
    Provides a form for calculating the sum of two numbers.

You'll examine the tests next. 

## Integration and Functional Testing with Arquillian [](id=integration-and-functional-testing-with-arquillian)

The Arquillian Blade Example tests the sample portlet using its API and UI. 

-   [Integration Test](#integration-test): Invokes the `SampleService`'s `add`
    method directly. 
-   [Functional Test](#functional-test): Invokes the `add` method indirectly
    using a web browser. 

The test classes are in the `src/testIntegration/java` folder and test resources
are in the `src/testIntegration/resources` folder. The integration test is basic
and worth examining first. 

### Integration Test [](id=integration-test)

Integration tests exercise module interaction. Although the
`BasicPortletIntegrationTest` class demonstrates invoking the sample module's
`SampleService.add` method only, an Arquillian integration test could just as
easily invoke many methods to test behavior across many modules. 

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

The `create` method packages the test class and resources in a Java archive
(JAR). Invoking the project's `jar` Gradle task creates the test JAR Arquillian
executes. 

    @Deployment
    public static JavaArchive create() throws Exception {
        final File tempDir = Files.createTempDir();

        final ProcessBuilder processBuilder = new ProcessBuilder(
            "./gradlew", "jar", "-Pdir=" + tempDir.getAbsolutePath());

        final Process process = processBuilder.start();

        process.waitFor();

        final File jarFile = new File(
            tempDir.getAbsolutePath() +
                "/com.liferay.arquillian.sample-1.0.0.jar");

        return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
    }

JUnit annotation `@Test` designates the `testAdd` method as a test. The method
invokes the `SampleService` object's `add` method and asserts its result. 

    @Test
    public void testAdd() throws IOException, PortalException {
        final long result = _sampleService.add(1, 3);

        Assert.assertEquals(4, result);
    }

The Liferay Arquillian Extension injects the `_sampleService` field with a
`SampleService` implementation (i.e., a `SampleServiceImpl` instance). 

    @Inject
    private SampleService _sampleService;

The integration test has some dependencies, of course. 

#### Dependencies [](id=dependencies)

The project's `build.gradle` file specifies this test's dependencies on
Liferay's Arquillian container, JUnit, and an Arquillian JUnit test container:

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

Apache Aries JMX exposes the JMX API that Arquillian uses to
install/deploy/start the modules. Since DXP Digital Enterprise 7.0 Fix Pack 16
and Liferay CE 7.0 GA4, Liferay Workspace's `startTestableTomcat` Gradle task
installs the Apache Aries JMX modules automatically. In case you're using and
earlier @product@ version or haven't already installed the Aries modules, here's
their group ID, artifact ID, and version information. You can install them using
[Apache Felix GoGo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell):

    "org.apache.aries.jmx:org.apache.aries.jmx:1.1.5"
    "org.apache.aries:org.apache.aries.util:1.1.3"

JMX is enabled on the application server via Java runtime options. The following
Apache Tomcat environment script excerpts demonstrate enabling JMX (without
authentication) on port 8099. 

##### `setenv.sh` JMX Settings [](id=setenv-sh-jmx-settings)

    JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    CATALINA_OPTS="${CATALINA_OPTS} ${JMX_OPTS}"

##### `setenv.bat` JMX Settings [](id=setenv-bat-jmx-settings)

    set "JMX_OPTS=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    set "CATALINA_OPTS=%CATALINA_OPTS% %JMX_OPTS%"

Apache Tomcat's
[guide *Enabling JMX Remote*](https://tomcat.apache.org/tomcat-7.0-doc/monitoring.html#Enabling_JMX_Remote)
has more JMX configuration details. 

You've seen how the example integration test class and its setup are
straightforward. Next, you'll discover how fun it is to develop functional
browser-based tests using Arquillian. 

### Functional Test [](id=functional-test)

The Arquillian Blade Example's functional tests interact with the portlet UI to
verify content and validate behavior. The example tests operate on the following
view parameters: 

-   `firstParameter`: First number to add
-   `secondParameter`: Second number to add
-   `result`: Sum of the two numbers

The JSP file `view.jsp` and portlet class `BasicPortletFunctionalTest` use these
parameters. Here's the `view.jsp` code:

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

Users enter numbers in the `firstParameter` and `secondParameter` input fields
and click on the `add` button to show the sum to the `result` field. 

Functional test class `BasicPortletFunctionalTest` uses Selenium to interact
with the portlet's UI. Here's the `BasicPortletFunctionalTest` class:

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

Arquillian annotation `@RunAsClient` and JUnit annotation `@RunWith` mark the
class as a web client that runs on Arquillian.

Similar to the integration test class, this class's `create` method packages the
test as a JAR file for Arquillian to execute. 

Method `testInstallPortlet` verifies portlet content. 

    @Test
    public void testInstallPortlet() throws IOException, PortalException {
        _browser.get(_portlerURL.toExternalForm());

        final String bodyText = _browser.getPageSource();

        Assert.assertTrue(
            "The portlet is not well deployed",
            bodyText.contains("Sample Portlet is working!"));
    }

This test class uses the following fields:

-   `_browser`: Arquillian annotation `@Drone` sets this field as a Selenium
    `WebDriver` (browser).

        @Drone
        private WebDriver _browser;

-   `_portlerURL`: Liferay Arquillian annotation `@PortalURL` assigns the
    portlet's URL to this field. 

        @PortalURL("arquillian_sample_portlet")
        private URL _portlerURL;

-   `_firstParamter` and `_secondParameter`: JavaScript selectors and Selenium
    annotation `@FindBy` map these fields to the form's inputs. 

        @FindBy(css = "input[id$='firstParameter']")
        private WebElement _firstParamter;

        @FindBy(css = "input[id$='secondParameter']")
        private WebElement _secondParameter;

-   `_add`: The form's `submit` button. 
 
        @FindBy(css = "button[type=submit]")
        private WebElement _add;

-   `_result`: Sum of the two numbers. 

        @FindBy(css = "span[class='result']")
        private WebElement _result;

Using the `*Parameter` fields, the `testAdd` method injects numbers `2` and `3`
into the form, submits the form, and asserts `5` as the result. 

    @Test
    public void testAdd()
        throws InterruptedException, IOException, PortalException {

        _browser.get(_portlerURL.toExternalForm());

        _firstParameter.clear();

        _firstParameter.sendKeys("2");

        _secondParameter.clear();

        _secondParameter.sendKeys("3");

        _add.click();

        Thread.sleep(5000);

        Assert.assertEquals("5", _result.getText());
    }

Testing portlets via a web client is that simple! 

Functional tests typically require more setup than integration tests. 

#### Dependencies

The `BasicPortletFunctionalTest` class requires Arquillian's Graphine extension
to use the web client. Here's the dependency from the Gradle file
`build.gradle`:

    testIntegrationCompile group: "org.jboss.arquillian.graphene", name: "graphene-webdriver", version: "2.1.0.Final"

+$$$

**Note**: To learn more about functional testing using Graphine, see this
[guide](http://arquillian.org/guides/functional_testing_using_graphene/).

$$$

The test requires additional Arquillian configuration elements too. 

#### Arquillian Configuration

In addition to the `deploymentExportPath` property introduced with the
integration test, this functional test specifies the browser type using
`phantomjs` and  the portal's URL. Here's the project's `arquillian.xml` file:

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

**Note**: The Arquillian Liferay Extension
provides these options for injecting the URL of the container (e.g., Apache Tomcat):
    
1.  In a test class deployment method or field, use the annotation
    `@ArquillianResource` to designate the URL.

2.  Configure Arquillian using the graphene URL property (via
    `arquillian.xml`, `arquillian.properties`, or System Properties).

$$$

You should use Portal properties to prevent @product@ from launching a browser
and the Setup Wizard. 

#### Portal Properties

The Arquillian Blade Example specifies the following Portal properties in file
`src/testIntegration/resources/portal-ext.properties`:

    browser.launcher.url=
    setup.wizard.enabled=false

Browsers other than the ones the functional tests launch can interfere with
tests. Setting `browser.launcher.url` to an empty value prevents @product@ from
launching a browser on its own. You don't need @product@'s setup wizard either.
Setting `setup.wizard.enabled=false` bypasses launching the Setup Wizard.

The example project's Gradle task `copyPortalExt`, copies the Portal properties
file into the @product@ installation.

As you develop tests, you might want to track the parts of the product your
tests cover. The example project uses JaCoCo to measure code coverage. JaCoCo is
explained next. 

If you'd rather launch the Arquillian Blade Example before investigating JaCoCo,
skip to [Running the Tests](#running-the-tests). Otherwise, jump into JaCoCo! 

## Measuring Code Coverage with JaCoCo [](id=measuring-code-coverage-with-jacoco)

[JaCoCo](http://eclemma.org/jacoco/) measures Java code coverage. The
Arquillian Blade Example uses JaCoCo to report parts and percentages of the product code the tests execute. 

![Figure 2: JaCoCo reports lines of code tests execute in methods and classes.](../../images/arquillian-example-jacoco-results.png)

### Enabling JaCoCo

JaCoCo requires attaching an agent to the JVM. To attach the JaCoCo agent,
append the following JaCoCo options to  the Tomcat environment script's
`CATALINA_OPTS` variable.

#### `setenv.sh` JaCoCo Settings [](id=setenv-sh-jacoco-settings)

    JACOCO_OPTS="-javaagent:PATH_TO_JACOCO_AGENT_JAR/jacocoagent.jar=destfile=JACOCO_EXEC_FILE,output=file,append=true,jmx=true"
    
    CATALINA_OPTS="${CATALINA_OPTS} ${JACOCO_OPTS}"

#### `setenv.bat` JaCoCo Settings [](id=setenv-bat-jacoco-settings)

    set "JACOCO_OPTS=-javaagent:PATH_TO_JACOCO_AGENT_JAR/jacocoagent.jar=destfile=JACOCO_EXEC_FILE,output=file,append=true,jmx=true"
    
    set "CATALINA_OPTS=%CATALINA_OPTS% %JACOCO_OPTS%"

Replace `PATH_TO_JACOCO_AGENT_JAR` with the path to the `jacocoagent.jar` file
and `JACOCO_EXEC_FILE` with the path to the JaCoCo result dump file. 

### JaCoCo Build Instructions

The Gradle build file `build.gradle` specifies several JaCoCo-related
instructions:

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

4.  Generate JaCoCo reports:

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

## Running the Tests [](id=running-the-tests)

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

![Figure 3: Open the test reports to analyze the results.](../../images/arquillian-example-test-results.png)

+$$$

Note: before rerunning the tests, you must delete the `build/reports/` and
`build/test-results/` folders.

$$$

Now that you've examined Arquillian functional and integration tests and JaCoCo
code coverage capabilities, you can create similar tests and improve test code
coverage in your projects. 
