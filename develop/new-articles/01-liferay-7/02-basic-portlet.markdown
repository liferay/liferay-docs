# Basic Portlet
## Overview
In Liferay 7, components are the building blocks of a large, yet modular, system.  Portlets are essentially components within the system as well.  Let us take a quick look at how to create a basic portlet with no bells and whistles.

## Implementation
### Create a New Plugin
In the `/portlets` folder of the plugin SDK, create a new plugin just like before:

```shell
./create.sh sample "Sample"
```

This will generate a new portlet plugin project called `/sample-portlet` with additional folders and files within.

### Delete DocRoot and Add BND
By default (and for backward compatibility), the SDK is design to build web applications (WAR).  However, in order to leverage the new approach, we'll need to update a couple of things to inform the SDK that we want to build components and bundles.

First, delete the `/docroot` folder.  It isn't necessary anymore (we'll elaborate on the folder structure later on).  Next, create a new BND file called `bnd.bnd`.

In this `bnd.bnd` file, add the following:

```bnd
Bundle-Name: Sample Portlet
Bundle-SymbolicName: com.liferay.sample
Bundle-Version: 1.0.0
```

This file provides BND (a bundling utility) the needed information to package the JAR file and generating the manifest needed in Liferay Portal 7.

### Declare Dependencies
Open up `ivy.xml`.  Remove all the default dependencies and add the following as default dependencies:

```xml
<dependencies defaultconf="default">
	<dependency name="portlet-api" org="javax.portlet" rev="2.0" />
	<dependency name="org.osgi.compendium" org="org.osgi" rev="5.0.0" />
</dependencies>
```

If you're using an IDE, first run `ant clean` which will download those two JAR files.  Then in your IDE, import those two JAR files into your workspace.

### Update build.xml
The `build.xml` file is set to build this plugin the *old* way.  We need to update it to import another set of build instructions.  Simply update it to the following:

```xml
<?xml version="1.0"?>
<!DOCTYPE project>

<project name="test-portlet" basedir="." default="deploy">
	<import file="../../build-common-osgi-plugin.xml" />
</project>
```
The important part is that it is now importing the `build-common-osgi-plugin.xml` file.

### Create Portlet POJO
Now we're ready to create our Portlet class.

```java
import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=Sample Portlet",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class SamplePortlet extends GenericPortlet {

	@Override
	public void doView(
			RenderRequest request, RenderResponse response)
		throws IOException {

		PrintWriter writer = response.getWriter();
		writer.print("Sample Portlet is working!");
	}
}
```

Minus the one annotation and all its attributes, this class is fairly straight forward.  We defined a portlet class named `SamplePortlet` that extends the `GenericPortlet` class found in the `javax.portlet` package.  Next, we overrode the `doView(...)` method from the parent class to print out our custom message.

Now let's discuss the @Component annotation applied to our portlet class.  Liferay 7 is very much a component container so-to-speak.  Most, if not all, of the functionality we can create within Liferay 7 will be in the form of a service component.  So this annotation simply flags our portlet as another component that needs to be incorporated.  This annotation accepts many attributes to further define the behavior of this component.  Let's take a look at the ones we've defined.

#### immediate = true
By default, Liferay 7 lazy-loads all its components.  If there is no demand for a component, then it doesn't make sense to load that component.  However, there are components, like portlets, that shouldn't be lazy-loaded, because they're not invoked directly like most classes.  For this, we use the `immediate` attribute to indicate that our portlet class needs to be instantiated immediately.

#### service = Portlet.class
This attribute explicitly declares that our portlet class is a service defined as (or a type of) the `javax.portlet.Portlet` class.  When this component is initialized by the container, Liferay 7 is notified that a portlet service is starting and Liferay will add this to its collection of portlets that can handle requests.

Note that Liferay 7 only listens for services that are defined as `Portlet.class`.  Defining a portlet as something else like `MVCPortlet.class` or leaving this attribute out all together would render the portlet class useless as far as the portal is concerned.

#### property
The property attribute allows you to add any additional attributes to this component.  As you may notice, what was once defined in the `portlet.xml` file and the `liferay-portlet.xml` file is now defined here.  For our example, we have elected to only define the `display-category`, `display-name`, and the `security-role-ref`.