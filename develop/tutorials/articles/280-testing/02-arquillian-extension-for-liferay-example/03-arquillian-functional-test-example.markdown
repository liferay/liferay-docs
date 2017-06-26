# Arquillian Functional Test Example [](id=arquillian-functional-test-example)

Where the
[integration test](/develop/tutorials/-/knowledge_base/7-0/arquillian-integration-test-example)
invokes the `SampleService`'s `add` method directly, the functional test invokes
the `add` method indirectly using a web browser. The Arquillian Blade Example's
functional tests interact with the portlet UI to verify content and validate
behavior. The test classes are in the `src/testIntegration/java` folder and test
resources are in the `src/testIntegration/resources` folder. 

The example functional tests operate on the following view parameters: 

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

## Dependencies [](id=dependencies)

In addition to the Liferay Arquillian container, JUnit, and an Arquillian JUnit
test container artifacts that the
[integration test](/develop/tutorials/-/knowledge_base/7-0/arquillian-integration-test-example#dependencies)
required, the `BasicPortletFunctionalTest` class requires Arquillian's Graphine
extension to use the web client. Here's the dependency from the Gradle file
`build.gradle`:

    testIntegrationCompile group: "org.jboss.arquillian.graphene", name: "graphene-webdriver", version: "2.1.0.Final"

+$$$

**Note**: To learn more about functional testing using Graphine, see this
[guide](http://arquillian.org/guides/functional_testing_using_graphene/).

$$$

The test requires additional Arquillian configuration elements too. 

## Arquillian Configuration [](id=arquillian-configuration)

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

## Portal Properties [](id=portal-properties)

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
skip to [Running the Arquillian
Example](/develop/tutorials/-/knowledge_base/7-0/running-the-arquillian-example).
Otherwise, jump into JaCoCo! 
