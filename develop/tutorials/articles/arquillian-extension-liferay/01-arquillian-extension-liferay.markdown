# Arquillian Extension for Liferay Example

The
[Arquillian Extension Liferay Example](https://github.com/arquillian/arquillian-extension-liferay/blob/master/arquillian-extension-liferay-example)
project demonstrates how to use the Arquillian Liferay Extension. In this
tutorial, you'll use this project to learn how the Arquillian Extension for
Liferay works and how to use it in your own projects.

The Arquillian Extension Liferay Example project is executed in the following
environment:

- Tomcat Server 7.0.62
    - JMX enabled and configured
    - Tomcat Manager installed and configured
- Liferay 7.0.0
- JUnit 4.12

To set up a testing environment like the one used by the Arquillian Extension
for Liferay Example, you need to configure your Liferay Tomcat server. This
takes only two steps: 

1. Enable and configure JMX in Tomcat
2. Install and configure the Tomcat Manager

Read on to learn how to get your testing environment working. 

## Enable and Configure JMX in Tomcat

You can follow this [guide](https://tomcat.apache.org/tomcat-7.0-doc/monitoring.html#Enabling_JMX_Remote) to enable your JMX congifuration in Tomcat.

Here's an example of a `setenv.sh` file that enables JMX in Tomcat on port 8099
without authentication:

    CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=8099 -Dcom.sun.management.jmxremote.ssl=false"

    CATALINA_OPTS="${CATALINA_OPTS} ${JMX_OPTS}"

You can customize your `setenv.sh` in a similar way. 

## Install and Configure the Tomcat Manager

You can follow this [guide] (https://tomcat.apache.org/tomcat-7.0-doc/manager-howto.html#Introduction) to configure the Tomcat Manager.

The Tomcat Manager is installed by default on context path `/manager`. Here's an
example `tomcat-users.xml` file that goes in Tomcat's `conf` folder:

    <?xml version="1.0" encoding="utf-8" standalone="no"?>
    <tomcat-users>
        <role rolename="tomcat"/>
        <role rolename="manager-gui"/>
        <role rolename="manager-script"/>
        <role rolename="manager-jmx"/>
        <role rolename="manager-status"/>
        <user password="tomcat" roles="tomcat,manager-gui,manager-script,manager-jmx,manager-status" username="tomcat"/>
    </tomcat-users>

In the above example, you can see that a Tomcat user is defined with the
username `tomcat` and the password `tomcat`. This user has the roles `tomcat`,
`manager-gui`, `manager-script`, `manager-jmx`, `manager-status`. These roles
are required to execute Arquillian tests using the Arquillian Liferay Extension.

By default, this extension requires that the username and password are both
`tomcat`. This behaviour can be configured in a custom `arquilliam.xml` file.
You can see how to configure the custom extension properties in another tutorial.

## Create a Simple Test in Liferay with the Arquillian Liferay Extension

1. Add dependencies to your `pom.xml` file:

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

2. Create a simple test class using the Arquillian Extension.

        package org.arquillian.liferay.test;

        import java.io.InputStream;
        import org.jboss.arquillian.container.test.api.Deployment;
        import org.jboss.arquillian.junit.Arquillian;

        import org.jboss.osgi.metadata.OSGiManifestBuilder;
        import org.jboss.shrinkwrap.api.ShrinkWrap;
        import org.jboss.shrinkwrap.api.asset.Asset;
        import org.jboss.shrinkwrap.api.spec.JavaArchive;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        @RunWith(Arquillian.class)
        public class SimpleTest {

                @Deployment
                public static JavaArchive create() {
                        final JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "bundle.jar");

                        archive.setManifest(new Asset() {
                                @Override
                                public InputStream openStream() {
                                        OSGiManifestBuilder builder = OSGiManifestBuilder.newInstance();
                                        builder.addBundleSymbolicName(archive.getName());
                                        builder.addBundleManifestVersion(2);
                                        return builder.openStream();
                                }
                        });
                        return archive;
                }

                @Test
                public void test() {
                        System.out.println("Example of test executed in Liferay");
                }

        }

## Create a Functional Test in Liferay with the Arquillian Liferay Extension

To create a functional test in Liferay with the Arquillian Liferay Extension,
follow this
[guide](http://arquillian.org/guides/functional_testing_using_graphene/).

1. Add dependencies to `pom.xml`.

    First of all, you need to configure the `pom.xml` file to add the
    `graphene-webdriver` dependencies:

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

2. Add a easy way to execute tests between different browsers.

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

3. Next, you need to set up `arquillian.xml` in order to change the Arquillian
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

4. Create a `SignIn` test:

        import java.net.MalformedURLException;
        import java.net.URL;

        import org.jboss.arquillian.container.test.api.RunAsClient;
        import org.jboss.arquillian.drone.api.annotation.Drone;
        import org.jboss.arquillian.junit.Arquillian;
        import org.jboss.arquillian.test.api.ArquillianResource;

        import org.junit.Assert;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;

        @RunAsClient
        @RunWith(Arquillian.class)
        public class BasicFunctionalTest {

                @Test
                public void testSignIn(@ArquillianResource URL url)
                        throws MalformedURLException {

                        Assert.assertNotNull(url);

                        Assert.assertNotNull(browser);

                        browser.get(url.toExternalForm());

                        Assert.assertNotNull(signIn);
                        Assert.assertNotNull(login);
                        Assert.assertNotNull(password);

                        login.clear();

                        login.sendKeys("test@liferay.com");

                        password.sendKeys("test");

                        signIn.click();

                        String bodyText = browser.findElement(By.tagName("body")).getText();

                        Assert.assertTrue(
                                "SignIn has failed", bodyText.contains("Terms of Use"));
                }

                @Drone
                private WebDriver browser;

                @FindBy(css = "input[id$='_login']")
                private WebElement login;

                @FindBy(css = "input[id$='_password']")
                private WebElement password;

                @FindBy(css = "button[type=submit]")
                private WebElement signIn;

        }

5. Configure the `ArquillianResource`:

    If you want to inject the URL of the container using the annotation
    `@ArquillianResource`, you can use one of these solutions (if you are using
    the Arquillian Liferay Extension):

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

## Running Tests with the Liferay Arquillian Extension

That's it! If you've download the
[Arquillian Liferay Extension Example](https://github.com/arquillian/arquillian-extension-liferay/tree/master/arquillian-extension-liferay-example),
use the following command to run the tests:

    mvn clean install

This command can take a long time to execute since Maven needs to download
Tomcat and Liferay, install Liferay into Tomcat, start Liferay, and then deploy
and run your tests.
