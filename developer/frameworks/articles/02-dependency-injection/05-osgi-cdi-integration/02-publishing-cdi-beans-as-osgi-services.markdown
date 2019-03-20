---
header-id=publishing-cdi-beans-as-osgi-services
---

# Publishing CDI Beans as OSGi Services

You can publish CDI beans as OSGi services, making them accessible via the
Liferay's OSGi service registry. Here's how: 

1.  Add a project dependency on the OSGi CDI Integration artifact. For example, 
    here's the dependency to use in a Maven `pom.xml` file:

        <dependency>
            <groupId>org.osgi</groupId>
            <artifactId>org.osgi.service.cdi</artifactId>
            <version>1.0.0</version>
        </dependency>

2.  Make your CDI bean implement the service interface you're providing. For 
    example, `ShopImpl` provides the `Shop` service by implementing that
    interface.

    ```java
    package my.package;

    public class ShopImpl implements Shop {
        ...
    }
    ```

3.  Annotate your CDI bean class with 
    `@org.osgi.service.cdi.annotations.Service`. 

    ```java
    package my.package;

    import org.osgi.service.cdi.annotations.Service;

    @Service 
    public class ShopImpl implements Shop {
        ...
    }
    ```

4.  Deploy the API that defines the service interface, if you haven't deployed 
    it already. 

5.  Build and deploy your service project bundle. 

Once your bundle installs and activates, your bean's service implementation is
available. You can use Gogo Shell commands can verify that the service
registered. 

For example, here are steps for verifying that a bundle
`com.liferay.portal.samples.cdi.jar.portlet` registers a service called
`org.apache.portals.samples.Users`. 

1.  Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Gogo Shell*. 

2.  Use the `lb` Gogo command and `grep` (pass in the bundle's symbolic name) to
    find your bundle (and its ID). 

    Example command:

        g!: lb | grep com.liferay.portal.samples.cdi.jar.portlet

    Results:

        924|Active     |   10|com.liferay.portal.samples.cdi.jar.portlet (0.0.1.201901252134)|0.0.1.201901252134

    The first column contains the bundle ID. 

3.  Use the `b` Gogo command with your bundle ID to list your bundle's details 
    and verify the bundle includes your service as one of its registered
    services. 

    Example command:

        g!: b 924

    Results:

        com.liferay.portal.samples.cdi.jar.portlet_0.0.1.201901252134 [924]
        Id=924, Status=ACTIVE      Data Root=C:\git\bundles\osgi\state\org.eclipse.osgi\924\data
        "Registered Services"
          ...
          {org.apache.portals.samples.Users}={osgi.command.scope=cdiportlet, service.id=4232, service.bundleid=924, service.scope=singleton, osgi.command.function=[getUsersCount], component.name=com.liferay.portal.samples.cdi.jar.portlet, component.id=1}
          ...

Congratulations on publishing your CDI bean as an OSGi service! 
