# Arquillian Extension for Liferay Example [](id=arquillian-extension-for-liferay-example)

The
[Arquillian Extension Liferay Example](https://github.com/arquillian/arquillian-extension-liferay/blob/master/arquillian-extension-liferay-example)
project demonstrates how to use the Arquillian Liferay Extension. In this
tutorial, you'll use the example project to learn how the Arquillian Extension
for @product@ works and how to use it in your own projects.

The Arquillian Extension Liferay Example project is executed in the following
environment:

- Tomcat Server
    - JMX enabled and configured
- Liferay 7
- JUnit

To set up a testing environment like the one used by the Arquillian Extension
for Liferay Example, you need to enable and configure JMX in your Liferay Tomcat
server.

## Enable and Configure JMX in Tomcat [](id=enable-and-configure-jmx-in-tomcat)

You can follow this [guide](https://tomcat.apache.org/tomcat-7.0-doc/monitoring.html#Enabling_JMX_Remote) to enable your JMX configuration in Tomcat.

Here's an example of a `setenv.sh` file that enables JMX in Tomcat on port 8099
without authentication:

    CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    CATALINA_OPTS="${CATALINA_OPTS} ${JMX_OPTS}"

You can customize your `setenv.sh` in a similar way.

## Creating a Liferay Portlet for Testing [](id=creating-a-liferay-portlet-for-testing)

You need a Liferay portlet project that can be used for testing. You can use
your preferred build tool. For example, either Gradle or Maven can be used.
Follow these steps to create a Liferay portlet project using Maven.

<!-- Gradle example coming soon. -Jesse -->

1. Add Liferay, portlet, and OSGi dependencies to your project's `pom.xml` file:

        ...
            <dependencies>
            ....
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>portal-service</artifactId>
                    <version>${liferay.version}</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-bridges</artifactId>
                    <version>${liferay.version}</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-taglib</artifactId>
                    <version>${liferay.version}</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.portal</groupId>
                    <artifactId>util-java</artifactId>
                    <version>${liferay.version}</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>org.osgi</groupId>
                    <artifactId>org.osgi.core</artifactId>
                    <version>${osgi.version}</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>javax.portlet</groupId>
                    <artifactId>portlet-api</artifactId>
                    <version>2.0</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>javax.servlet</groupId>
                    <artifactId>javax.servlet-api</artifactId>
                    <version>3.0.1</version>
                    <scope>provided</scope>
                </dependency>
                <dependency>
                    <groupId>javax.servlet.jsp</groupId>
                    <artifactId>jsp-api</artifactId>
                    <version>2.0</version>
                    <scope>provided</scope>
                </dependency>
                ....
            </dependencies>
        ...

2.  Create an OSGi service. This OSGi service is just an example that you'll use
    for testing purposes. It's a simple service that adds two numbers.

    First, create a new interface:

        package org.arquillian.liferay.sample.service;

        public interface SampleService {

            public int add(int a, int b);

        }

    Next, create an implementation for the interface:

        package org.arquillian.liferay.sample.service;

        import org.osgi.service.component.annotations.Component;

        @Component(immediate = true, service = SampleService.class)
        public class SampleServiceImpl implements SampleService {

            @Override
            public int add(int a, int b) {
                return a + b;
            }

        }

3.  Create a Liferay MVC portlet. This portlet will call the service defined in
    the previous step:

        package org.arquillian.liferay.sample.portlet;

        import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
        import com.liferay.portal.kernel.util.ParamUtil;
        import com.liferay.portal.kernel.util.WebKeys;
        import com.liferay.portal.theme.ThemeDisplay;
        import com.liferay.portlet.PortletURLFactoryUtil;

        import javax.portlet.ActionRequest;
        import javax.portlet.ActionResponse;
        import javax.portlet.Portlet;
        import javax.portlet.PortletRequest;
        import javax.portlet.PortletURL;

        import org.arquillian.liferay.sample.service.SampleService;

        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        @Component(
            property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=false",
                "javax.portlet.display-name=Arquillian Sample Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=arquillian_sample_portlet",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
        )
        public class SamplePortlet extends MVCPortlet {

            public void add(ActionRequest actionRequest, ActionResponse actionResponse)
                throws Exception {

                ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
                    WebKeys.THEME_DISPLAY);

                int firstParameter = ParamUtil.getInteger(
                    actionRequest, "firstParameter");
                int secondParameter = ParamUtil.getInteger(
                    actionRequest, "secondParameter");

                int result = _sampleService.add(firstParameter, secondParameter);

                PortletURL portletURL = PortletURLFactoryUtil.create(
                    actionRequest, "arquillian_sample_portlet", themeDisplay.getPlid(),
                    PortletRequest.RENDER_PHASE);

                portletURL.setParameter(
                    "firstParameter", String.valueOf(firstParameter));
                portletURL.setParameter(
                    "secondParameter", String.valueOf(secondParameter));
                portletURL.setParameter("result", String.valueOf(result));

                actionRequest.setAttribute(WebKeys.REDIRECT, portletURL.toString());
            }

            @Reference(unbind = "-")
            public void setSampleService(SampleService sampleService) {
                _sampleService = sampleService;
            }

            private SampleService _sampleService;

        }

    This portlet needs a `view.jsp` file:

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
        taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
        taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
        taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

        <portlet:defineObjects />

        <liferay-theme:defineObjects />

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

4.  Next, create a `bnd.bnd` file for deployment. Your tests will use
    [Bnd](http://aqute.biz/Bnd/Bnd) to create the artifact to be deployed, so
    add this Maven dependency to your project's `pom.xml` file:

        ...
        <dependencies>
            ...
            <dependency>
                <groupId>org.jboss.shrinkwrap.osgi</groupId>
                <artifactId>shrinkwrap-osgi</artifactId>
                <version>1.0.0-alpha-1</version>
                <scope>test</scope>
            </dependency>
            ...
        </dependencies>
        ...

    And, of course, you must create a `bnd-basic-portlet-test.bnd` file:

        Bundle-Name: Basic Portlet Test
        Bundle-SymbolicName: org.arquillian.liferay.sample
        Bundle-Version: 1.0.0
        Export-Package: org.arquillian.liferay.sample
        Include-Resource:\
            target/classes,\
            META-INF/resources=src/main/resources/META-INF/resources

        Require-Capability:\
         osgi.extender;filter:="(&(osgi.extender=jsp.taglib)(uri=http://java.sun.com/portlet_2_0))",\
         osgi.extender;filter:="(&(osgi.extender=jsp.taglib)(uri=http://liferay.com/tld/aui))",\
         osgi.extender;filter:="(&(osgi.extender=jsp.taglib)(uri=http://liferay.com/tld/portlet))",\
         osgi.extender;filter:="(&(osgi.extender=jsp.taglib)(uri=http://liferay.com/tld/theme))",\
         osgi.extender;filter:="(&(osgi.extender=jsp.taglib)(uri=http://liferay.com/tld/ui))",\
         osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.7))"

        -dsannotations: *

    That's it! Your Liferay portlet is complete!

## Create a Simple Test in Liferay with the Arquillian Liferay Extension [](id=create-a-simple-test-in-liferay-with-the-arquillian-liferay-extension)

Now that you've configured JMX in Tomcat and created a portlet project, you're
ready to create Liferay tests with the Arquillian Liferay Extension.

1.  Add dependencies to your `pom.xml` file:

        ...
        <dependencies>
                ....
                <dependency>
                        <groupId>org.arquillian.liferay</groupId>
                        <artifactId>arquillian-container-liferay</artifactId>
                        <version>1.0.0.Final-SNAPSHOT</version>
                        <scope>test</scope>
                </dependency>
                <dependency>
                        <groupId>org.jboss.arquillian.junit</groupId>
                        <artifactId>arquillian-junit-container</artifactId>
                        <scope>test</scope>
                </dependency>
                <dependency>
                        <groupId>junit</groupId>
                        <artifactId>junit</artifactId>
                        <version>4.12</version>
                        <scope>test</scope>
                </dependency>
                ....
        </dependencies>
        ...

2.  Create simple integration tests using the Arquillian Liferay Extension.

        package org.arquillian.liferay.test;

        import com.liferay.portal.kernel.exception.PortalException;

        import java.io.File;
        import java.io.IOException;

        import org.arquillian.container.liferay.remote.enricher.Inject;
        import org.arquillian.liferay.sample.service.SampleService;

        import org.jboss.arquillian.container.test.api.Deployment;
        import org.jboss.arquillian.junit.Arquillian;
        import org.jboss.shrinkwrap.api.ShrinkWrap;
        import org.jboss.shrinkwrap.api.spec.JavaArchive;
        import org.jboss.shrinkwrap.osgi.api.BndProjectBuilder;

        import org.junit.Assert;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        @RunWith(Arquillian.class)
        public class BasicPortletIntegrationTest {

            @Deployment
            public static JavaArchive create() {
                BndProjectBuilder bndProjectBuilder = ShrinkWrap.create(
                    BndProjectBuilder.class);

                bndProjectBuilder.setBndFile(new File("bnd-basic-portlet-test.bnd"));

                bndProjectBuilder.generateManifest(true);

                return bndProjectBuilder.as(JavaArchive.class);
            }

            @Test
            public void testAdd() throws IOException, PortalException {
                int result = _sampleService.add(1, 3);

                Assert.assertEquals(4, result);
            }

            @Inject
            private SampleService _sampleService;

        }

## Create Simple Functional Tests Using the Arquillian Liferay Extension [](id=create-simple-functional-tests-using-the-arquillian-liferay-extension)

To create a functional test in Liferay with the Arquillian Liferay Extension,
follow this
[guide](http://arquillian.org/guides/functional_testing_using_graphene/).

1.  Add dependencies to `pom.xml`:

        ...
        <dependencies>
                ....
                <dependency>
                        <groupId>org.jboss.arquillian.graphene</groupId>
                        <artifactId>graphene-webdriver</artifactId>
                        <version>2.1.0.Alpha2</version>
                        <type>pom</type>
                        <scope>test</scope>
                </dependency>
                ....
        </dependencies>
        ...	

2.  Add a easy way to execute tests between different browsers.

    In your `pom.xml` file, create a profile for each desired browser:

        ...
        properties>
            <browser>phantomjs</browser> 
        </properties>
        ...

        <profiles>
            ...
            <profile>
               <id>firefox</id>
               <properties>
                  <browser>firefox</browser>
               </properties>
            </profile>
            <profile>
               <id>chrome</id>
               <properties>
                   <browser>chrome</browser>
               </properties>
            </profile>
            ...
        </profiles>

3.  Next, you need to set up `arquillian.xml` in order to change the Arquillian
    settings for browser selection. Add the following to your project's
    `arquillian.xml` file in the `src/test/resources` directory.

        <?xml version="1.0" encoding="UTF-8"?>
        <arquillian xmlns="http://jboss.org/schema/arquillian"
                                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                xsi:schemaLocation="http://jboss.org/schema/arquillian http://jboss.org/schema/arquillian/arquillian_1_0.xsd">

                <extension qualifier="webdriver">
                        <property name="browser">${browser}</property>
                </extension>

        </arquillian>

4.  Create a portlet functional test:

        package org.arquillian.liferay.test;

        import com.liferay.portal.kernel.exception.PortalException;

        import java.io.File;
        import java.io.IOException;

        import java.net.URL;

        import org.arquillian.container.liferay.remote.enricher.Inject;
        import org.arquillian.liferay.installportlet.annotation.InstallPortlet;
        import org.arquillian.liferay.sample.service.SampleService;

        import org.jboss.arquillian.container.test.api.Deployment;
        import org.jboss.arquillian.container.test.api.RunAsClient;
        import org.jboss.arquillian.drone.api.annotation.Drone;
        import org.jboss.arquillian.junit.Arquillian;
        import org.jboss.shrinkwrap.api.ShrinkWrap;
        import org.jboss.shrinkwrap.api.spec.JavaArchive;
        import org.jboss.shrinkwrap.osgi.api.BndProjectBuilder;

        import org.junit.Assert;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;

        /**
         * @author Cristina González
         */
        @RunAsClient
        @RunWith(Arquillian.class)
        public class BasicPortletFunctionalTest {

            @Deployment
            public static JavaArchive create() {
                BndProjectBuilder bndProjectBuilder = ShrinkWrap.create(
                    BndProjectBuilder.class);

                bndProjectBuilder.setBndFile(new File("bnd-basic-portlet-test.bnd"));

                bndProjectBuilder.generateManifest(true);

                return bndProjectBuilder.as(JavaArchive.class);
            }

            @Test
            public void testAdd() throws IOException, PortalException {
                browser.get(_portlerURL.toExternalForm());

                firstParamter.clear();

                firstParamter.sendKeys("2");

                secondParameter.clear();

                secondParameter.sendKeys("3");

                add.click();

                Assert.assertEquals("5", result.getText());
            }

            @Test
            public void testInstallPortlet() throws IOException, PortalException {
                browser.get(_portlerURL.toExternalForm());

                String bodyText = browser.findElement(By.tagName("body")).getText();

                Assert.assertTrue(
                    "The portlet is not well deployed",
                    bodyText.contains("Sample Portlet is working!"));
            }

            @InstallPortlet(name ="arquillian_sample_portlet")
            private URL _portlerURL;

            @Inject
            private SampleService _sampleService;

            @FindBy(css = "button[type=submit]")
            private WebElement add;

            @Drone
            private WebDriver browser;

            @FindBy(css = "input[id$='firstParameter']")
            private WebElement firstParamter;

            @FindBy(css = "span[class='result']")
            private WebElement result;

            @FindBy(css = "input[id$='secondParameter']")
            private WebElement secondParameter;

        }

5.  Configure the `ArquillianResource`:

    If you want to inject the URL of the container (e.g., Tomcat) using the
    annotation `@ArquillianResource`, you can use one of these solutions (if
    you are using the Arquillian Liferay Extension):

    1. Create a deployment method in your test class.
    2. Configure Arquillian using the graphene url property (via
       `arquillian.xml`, `arquillian.properties` or System Properties).

        <?xml version="1.0" encoding="UTF-8"?>
        <arquillian xmlns="http://jboss.org/schema/arquillian"
                                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                xsi:schemaLocation="http://jboss.org/schema/arquillian http://jboss.org/schema/arquillian/arquillian_1_0.xsd">
                ...
                <extension qualifier="graphene">
                        <property name="url">http://localhost:8080</property>
                </extension>
                ...
        </arquillian>

## Create a Jacoco Profile [](id=create-a-jacoco-profile)

[JaCoCo](http://eclemma.org/jacoco/) is a code coverage library for Java.

1.  Create a new Maven profile called `jacoco` that configures the plugin
    `jacoco-maven-plugin` with the dependencies `org.jacoco.core` and
    `arquillian-jacoco`:

        ...
        <profile>
            <id>jacoco</id>
            <dependencies>
                <dependency>
                    <groupId>org.jacoco</groupId>
                    <artifactId>org.jacoco.core</artifactId>
                    <version>0.7.4.201502262128</version>
                    <scope>test</scope>
                </dependency>
                <dependency>
                    <groupId>org.jboss.arquillian.extension</groupId>
                    <artifactId>arquillian-jacoco</artifactId>
                    <version>1.0.0.Alpha8</version>
                    <scope>test</scope>
                </dependency>
            </dependencies>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.jacoco</groupId>
                        <artifactId>jacoco-maven-plugin</artifactId>
                        <version>0.7.4.201502262128</version>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>prepare-agent</goal>
                                </goals>
                            </execution>
                            <execution>
                                <id>report</id>
                                <phase>prepare-package</phase>
                                <goals>
                                    <goal>report</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>

2.  Generate a Jacoco report in HTML:

        mvn test -Pjavacoc jacoco:report

## Running Tests with the Liferay Arquillian Extension [](id=running-tests-with-the-liferay-arquillian-extension)

That's it! If you've download the
[Arquillian Liferay Extension Example](https://github.com/arquillian/arquillian-extension-liferay/tree/master/arquillian-extension-liferay-example),
use the following command to run the tests:

    mvn clean install

This command can take a long time to execute since Maven needs to download
Tomcat and Liferay, install Liferay into Tomcat, start Liferay, and then deploy
and run your tests.
