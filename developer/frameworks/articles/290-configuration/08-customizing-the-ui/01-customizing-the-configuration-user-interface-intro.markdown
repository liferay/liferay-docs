# Customizing the Configuration User Interface [](id=customizing-the-configuration-user-interface)

There are three ways to customize a configuration UI.

-   Provide a custom form for a configuration object. This modifies the
    auto-generated UI.

-   Write a completely custom configuration UI. This is useful especially if you
    aren't using the Configuration Admin service or any of Liferay's
    Configuration APIs.

-   Exclude a configuration object. You'll want this option if you're using
    a configuration interface but don't wan't a UI generated for you.

## Providing Custom Configuration Forms [](id=providing-custom-configuration-forms)

Customize your auto-generated UI by implementing the `ConfigurationFormRender`
interface. To write this interface, you must refer to your configuration
interface. For this example, refer to this configuration interface from
Liferay's Currency Converter application:

```java

@ExtendedObjectClassDefinition(category = "localization")
@Meta.OCD(
    id = "com.liferay.currency.converter.web.configuration.CurrencyConverterConfiguration",
    localization = "content/Language",
    name = "currency-converter-configuration-name"
)
public interface CurrencyConverterConfiguration {

    @Meta.AD(deflt = "GBP|CNY|EUR|JPY|USD", name = "symbols", required = false)
    public String[] symbols();
}

```

This example defines one configuration option, `symbols`, which takes an array
of values. 

Implement `ConfigurationFormRenderers` three methods:

1.  `getPid`: Return the configuration object's ID. This is defined in the `id`
    property in the `*Configuration` class's `@Meta.OCD` annotation.

2.  `getRequestParameters`: Read the parameters sent by the custom form and put
    them in a Map whose keys should be the names of the methods of the
    Configuration interface.

3.  `render`: Render the custom form's fields, using your desired method (for
    example, JSPs or another template mechanism). The `<form>` tag itself is
    provided automatically and shouldn't be included in the
    `ConfigurationFormRenderer`.

Here's a complete `ConfigurationFormRenderer` implementation:

```java

@Component(immediate = true, service = ConfigurationFormRenderer.class)
public class CurrencyConverterConfigurationFormRenderer
    implements ConfigurationFormRenderer {

    @Override
    public String getPid() {
        return "com.liferay.currency.converter.web.configuration.CurrencyConverterConfiguration";
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
            
        String formHtml = "<input name=\"mysymbols\" />";

        PrintWriter writer = response.getWriter();

        writer.print(formHtml);

    }

    @Override
    public Map<String, Object> getRequestParameters(
            HttpServletRequest request) {

        Map<String, Object> params = new HashMap<>();

        String[] mysymbols = ParamUtil.getParameterValues(request, "mysymbols");

        params.put("symbols", mysymbols);

        return params;
    }
}

```

The above example generates a custom rendering (HTML) for the form in the
`render()` method and reads the information entered in the custom form in the
`getRequestParameters()` method.

To see a complete demonstration, including JSP markup, read the dedicated
tutorial on creating a 
[configuration form renderer](/develop/tutorials/-/knowledge_base/7-1/configuration-form-renderer).

## Creating a Completely Custom Configuration UI [](id=creating-a-completely-custom-configuration-ui)

You get more flexibility if you create a completely custom UI using
a `ConfigurationScreen` implementation.

At a high level you must:

1.  Write a Component that declares itself an implementation of the
    `ConfigurationScreen` interface.

2.  Implement `ConfigurationScreen`'s methods.

3.  Create the UI by hand.

Here's an example implementation:

```java

@Component(immediate = true, service = ConfigurationScreen.class) 
public class SampleConfigurationScreen implements ConfigurationScreen {

```

First declare the class an implementation of `ConfigurationScreen`.

```java

@Override 
public String getCategoryKey() { 

    return "third-party"; 

}

@Override 
public String getKey() { 

    return "sample-configuration-screen"; 

}

@Override 
public String getName(Locale locale) { 

    return "Sample Configuration Screen"; 

}

```

Second, set the category key, the configuration entry's key, and its localized
name. This example puts the configuration entry, keyed
`sample-configuration-screen`, into the `third-party` System Settings section.
The String that appears in System Settings is _Sample Configuration Screen_.

```java

@Override 
public String getScope() { 

    return "system"; 

}

```

Third, set the 
[configuration scope](/docs/7-2/frameworks/-/knowledge_base/f/scoping-configurations).

```java

@Override 
public void render(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {

    _jspRenderer.renderJSP( _servletContext, request, response,
    "/sample_configuration_screen.jsp"); 

}

@Reference private JSPRenderer _jspRenderer;

@Reference(
    target ="(osgi.web.symbolicname=com.liferay.currency.converter.web)", 
    unbind = "-")
private ServletContext _servletContext;

```

The most important step is to write the `render` method.This example relies on
the `JSPRenderer` service to delegate rendering to a JSP.

It's beyond the scope of this tutorial to write the JSP markup. A separate
tutorial will provide a complete demonstration of the `ConfigurationScreen` and
implementation and the JSP markup to demonstrate its usage.

## Excluding a Configuration UI [](id=excluding-a-configuration-ui-from-system-settings)

If you don't want a UI to be generated for you, you have two options.

-   If you don't want a UI to be generated no matter what, use the `generateUI` property.

-   If you only want the UI to render under specific circumstances (defined by
    logic you'll write yourself), use the configuration visibility SPI.

### Using `generateUI`

To turn off auto-generating at all scopes, include the
`ExtendedObjectClassDefinition` annotation property `generateUI` in your
configuration interface. The property defaults to `true`; here is an example
setting it to `false`:

```java

@ExtendedObjectClassDefinition(generateUI=false)
@Meta.OCD(
  id = "com.foo.bar.LowLevelConfiguration",
)
public interface LowLevelConfiguration {

  public String[] foo();
  public String bar();

}

```

Now no UI will be auto-generated for this configuration. You can still manage
the configuration via a `ConfigurationScreen` implementation, a 
[.config file](/docs/7-2/user/-/knowledge_base/u/understanding-system-configuration-files),
or programatically.

### Using the Configuration Visibility SPI

The configuration visibility SPI involves implementing a single interface,
`ConfigurationVisibilityController`. You can see the whole interface
[here](https://github.com/liferay/liferay-portal/blob/48cd71b35a2d3b66e88f47685be7186cb7c52075/modules/apps/configuration-admin/configuration-admin-api/src/main/java/com/liferay/configuration/admin/display/ConfigurationVisibilityController.java).

To implement the interface, you'll need to identify your configuration interface
using an `@Component` property, then write your own logic for the interface's
only method, `isVisible`. Here is a sample implementation from Liferay's source
code:

```java

@Component(
	immediate = true,
	property = "configuration.pid=com.liferay.sharing.internal.configuration.SharingCompanyConfiguration",
	service = ConfigurationVisibilityController.class
)
public class SharingCompanyConfigurationVisibilityController
	implements ConfigurationVisibilityController {

	@Override
	public boolean isVisible(
		ExtendedObjectClassDefinition.Scope scope, Serializable scopePK) {

		SharingConfiguration systemSharingConfiguration =
			_sharingConfigurationFactory.getSystemSharingConfiguration();

		return systemSharingConfiguration.isEnabled();
	}

	@Reference
	private SharingConfigurationFactory _sharingConfigurationFactory;

}

```

Note that the property `configuration.pid` identifies the configuration
interface of the UI to be hidden. In this example, the configuration UI will
only render when `systemSharingConfiguration.isEnabled` returns `true`.
