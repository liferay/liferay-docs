# Arquillian Integration Test Example [](id=arquillian-integration-test-example)

Integration tests exercise module interaction. The following integration test
validates the sample portlet using its API. Although the example's
`BasicPortletIntegrationTest` class demonstrates invoking the sample module's
`SampleService.add` method only, an Arquillian integration test could just as
easily invoke many methods to test behavior across many modules. The test
classes are in the `src/testIntegration/java` folder and test resources are in
the `src/testIntegration/resources` folder.

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

## Dependencies [](id=dependencies)

The project's `build.gradle` file specifies this test's dependencies on
Liferay's Arquillian container, JUnit, and an Arquillian JUnit test container:

    testIntegrationCompile group: "com.liferay.arquillian", name: "com.liferay.arquillian.arquillian-container-liferay", version: "1.0.6"
    testIntegrationCompile group: "junit", name: "junit", version: "4.12"
    testIntegrationCompile group: "org.jboss.arquillian.junit", name: "arquillian-junit-container", version: "1.1.11.Final"

Arquillian tests are configurable too.  

## Arquillian Configuration [](id=arquillian-configuration)

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

## JMX Settings [](id=jmx-settings)

Apache Aries JMX exposes the JMX API that Arquillian uses to
install/deploy/start the modules. Since DXP Digital Enterprise 7.0 Fix Pack 16
and Liferay CE 7.0 GA4, Liferay Workspace's `startTestableTomcat` Gradle task
installs the Apache Aries JMX modules automatically. In case you're using an
earlier @product@ version or haven't already installed the Aries modules, here's
their group ID, artifact ID, and version information. You can install them using
[Apache Felix GoGo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell):

    "org.apache.aries.jmx:org.apache.aries.jmx:1.1.5"
    "org.apache.aries:org.apache.aries.util:1.1.3"

JMX is enabled on the application server via Java runtime options. The following
Apache Tomcat environment script excerpts demonstrate enabling JMX (without
authentication) on port 8099. 

### `setenv.sh` JMX Settings [](id=setenv-sh-jmx-settings)

    JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    CATALINA_OPTS="${CATALINA_OPTS} ${JMX_OPTS}"

### `setenv.bat` JMX Settings [](id=setenv-bat-jmx-settings)

    set "JMX_OPTS=-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    set "CATALINA_OPTS=%CATALINA_OPTS% %JMX_OPTS%"

Apache Tomcat's guide
[*Enabling JMX Remote*](https://tomcat.apache.org/tomcat-7.0-doc/monitoring.html#Enabling_JMX_Remote)
has more JMX configuration details. 

You've seen how setting up the example integration test class is
straightforward. Next, you'll discover how fun it is to develop functional
browser-based tests using Arquillian. 
