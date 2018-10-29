# Customizing the System Settings User Interface

@product@ applications use the Apache Felix Configuration Admin Service to
provide application configuration. By specifying a single Configuration
Interface class, the configuration data is typed and scoped, and the application
has an auto-generated configuration user interface, available in Control Panel &rarr;
Configuration &rarr; System Settings as soon as the configuration is registered.
If this is new information for you, consider first reading the set of tutorials
on 
[Making Applications Configurable](/discover/deployment/-/knowledge_base/7-1/making-applications-configurable).

This tutorial describes how to customize the System Settings entry's user
interface in the following ways:

- Provide a custom form for a configuration object.

- Write a completely custom configuration UI. This is useful especially if you
    aren't using the Configuration Admin service or any of Liferay's
    Configuration APIs.

- Exclude a configuration object from System Settings. If you're providing a
    completely custom configuration UI but are still using Configuration Admin,
    you'll want to hide the auto-generated UI. If your configuration is not
    meant to be accessible to administrative Users (perhaps because it's too low
    level), you might want to exclude it from the System Settings UI.

## Providing Custom Configuration Forms

This method relies on an existing Config Admin configuration class, as described
[here](/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable). 
Here's an example configuration class, from Liferay's own Currency Converter
application:

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

There's one configuration option, symbols, that takes an array of values. 

All that's necessary is to customize the auto-generated form is one additional
class, an implementation of the `ConfigurationFormRenderer` interface.

Implement it's three methods:

1.  `getPid`: Return the id of the configuration object as defined in the `id`
    property of the `@Meta.OCD` annotation of the `*Configuration` class.

2.  `getRequestParameters`: Read the parameters sent by the custom form and put
    them in a Map whose keys should be the names of the fields of the
    Configuration interface.

3.  `render`: Render the custom form's fields, using your desired method (for
    example, JSPs or another template mechanism). The `<form>` tag itself is
    provided automatically and shouldn't be included in the
    `ConfigurationFormRenderer`.

Here's a complete `ConfigurationFormRenderer` implementation:

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

The above example generates a custom rendering (HTML) for the form in the
`render()` method and reads the information entered in the custom form in the
`getRequestParameters()` method.

## Creating a Completely Custom Configuration UI

In some cases, you want a completely custom UI for your configuration. For
example: 

- Your application doesn't use Config Admin to provide its configuration. You
    have a completely different configuration backend, and you'll write a
    completely independent frontend.

- Your application needs more flexibility in its UI, such as multiple
    configuration screens.

To accomplish this, write a `ConfigurationScreen` implementation.

At a high level you must

1.  Write a Component that declares itself an implementation of the
    `ConfigurationScreen` interface.

2.  Implement `Configurationscreen`'s methods.

3.  Create the UI by hand.

Here's an example implementation:

    @Component(immediate = true, service = ConfigurationScreen.class) 
    public class SampleConfigurationScreen implements ConfigurationScreen {

First declare the class an implementation of `ConfigurationScreen`.

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

Second, set the category key, the configuration entry's key, and its localized
name. This example puts the configuration entry, keyed
`sample-cofniguration-screen`, into the `third-party` System Settings section.
The String that appears in System Settings is _Sample Configuration Screen_.

    @Override 
    public String getScope() { 
    
        return "system"; 
        
    }

Third, set the 
[configuration scope](/develop/tutorials/-/knowledge_base/7-1/scoping-configurations).

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

The most important step is to write the `render` method.This example relies on
the `JSPRenderer` service to delegate rendering to a JSP.

It's beyond the scope of this tutorial to write the JSP markup. A separate
tutorial will provide a complete demonstration of the `ConfigurationScreen` and
implementation and the JSP markup to enhance the configuration screen for an
existing @product@ application.

## Excluding a Configuration UI from System Settings

Providing a custom UI in System Settings is well and good, but what if you
instead must exclude your configuration from the System Settings UI? For
instance, if you're using Config Admin but also providing a
`ConfigurationScreen` implementation and a custom JSP, you'll get two System
Settings entries: the custom one you wrote _and_ the auto-generated UI from
Config Admin. Other times, a configuration is required to be present for backend
developers but isn't intended to be changed in the UI.

To exclude the UI entry, use the `ExtendedObjectClassDefinition` annotation
property called `generateUI`. It defaults to `true`, so set it to `false` to
suppress the auto-generated UI. Here is an example:

    @ExtendedObjectClassDefinition(generateUI=false)
    @Meta.OCD(
      id = "com.foo.bar.LowLevelConfiguration",
    )
    public interface LowLevelConfiguration {

      public String[] foo();
      public String bar();

    }

Now the configuration is available to be managed programmatically or via
[.config
file](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files),
but not via the System Settings UI.
