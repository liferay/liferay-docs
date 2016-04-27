# Overriding Core JSPs [](id=overriding-core-jsps)

Not to beat a dead horse, but Liferay is modularized to a great extent, so most
of the JSPs you might want to override are no longer in Liferay's core. However, there
are still some that you might want to modify, and it can be done. These JSPs are
located in the `portal-web/docroot/html` directory.

Before you begin crafting a module to override core JSPs, make sure the benefits
are going to outweigh the costs:

1. Unlike other modifications you can make in Liferay, there's no way to
override core JSPs in a way that will gracefully fail. Instead, if your core JSP
is buggy (because of your code or because of a change in Liferay), you are
most likely to find out at runtime, where functionality breaks and nasty
log errors greet you.

    As a related counterexample, modifying a module's JSPs is done using a
    fragment module. The fragment module declares the exact version of the host
    module whose JSPs it is overriding. If the host module's developer changes the
    JSPs in the module and changes the module version, the fragment module can no
    longer be attached to the version of the host module it was designed to work
    with. In this scenario, the original JSPs are still available and the
    application will be functional (but lacking your JSP enhancements).

2. Liferay is still being modularized. If you modify a core JSP, it might be
moved out of the core and into a module, and then you'll have to re-implement
your override, since overriding a module's JSPs is [done differently](develop/tutorials/-/knowledge_base/7-0/overriding-module-jsps).

If it's *really* necessary to modify a core JSP, you need a module that satisfies these criteria: 

-  Includes a class that implements `CustomJspBag`.

-  Registers the service in the OSGi runtime.

-  Provides the JSP you're extending.

The module provides transportation for this code into Liferay's OSGi runtime.
When configuring it to build a proper JAR, map the path of the JSPs in the JAR
to their path in your module. In a `bnd.bnd` file you could specify 

    -includeresource: META-INF/jsps=src/META-INF/custom_jsps

If you're using the Maven [Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
and placing your JSPs under `src/main/resources`, you can ignore the
`-includeresource` directive.

Any core JSPs you're customizing should be put into this folder, and the rest of
their path and their name must match exactly the path and name of the JSP
that's nested underneath `portal-web/docroot/html`. For example, if you're
overriding

    portal-web/docroot/html/common/themes/bottom-ext.jsp 

and you used the `includeresource` directive above, put the overridden JSP in
this folder of your module:

    my-module/src/META-INF/custom_jsps/html/common/themes/bottom-ext.jsp

## Implement a Custom JSP Bag [](id=implement-a-custom-jsp-bag)

Create a class that implements `CustomJspBag`. The overall goal is to make sure
that Liferay (specifically `CustomJspBagRegistryUtil`) loads the JSPs from your
module upon activation.

    public class MyCustomJspBag implements CustomJspBag {

When the Component is activated, you need to add the URL path for all your custom
core JSPs (by directory path) to a `List`.

        @Activate
        protected void activate(BundleContext bundleContext) {
            bundle = bundleContext.getBundle();

            _customJsps = new ArrayList<>();

            Enumeration<URL> entries = bundle.findEntries(
                getCustomJspDir(), "*.jsp", true);

            while (entries.hasMoreElements()) {
                URL url = entries.nextElement();

                _customJsps.add(url.getPath());
            }
        }

        ...

        private List<String> _customJsps;

        private Bundle bundle;

In the custom JSP bag you'll need to override the following methods:

-  **`getCustomJspDir`:** Return the directory path in your
module's JAR where the JSPs are placed (for example, `META-INF/jsps`).

-  **`getCustomJsps`:** Return a List of the custom JSP URL paths.

-  **`getURLContainer`:** Return a new
   `com.liferay.portal.kernel.url.URLContainer`. Instantiate the URL container
and override its `getResources` and `getResource` methods. The `getResources`
method is for looking up all of the paths to resources in the container by a
given path. It should return a `HashSet` of `Strings` for the matching custom
JSP paths. The `getResource` method returns one specific resource by its name
(the path included).

-  **`isCustomJspGlobal`:** Return `true`.

For an example of a full class that provides a working implementation of a
custom JSP bag, refer to the [blade.corejsphook BLADE project](https://github.com/liferay/liferay-blade-samples/blob/master/liferay-gradle/blade.corejsphook/src/main/java/com/liferay/blade/samples/corejsphook/BladeCustomJspBag.java).

## Register the Custom JSP Bag [](id=register-the-custom-jsp-bag)

Register the custom JSP bag implementation from your module in the OSGi runtime
with three properties:

-  **`context.id`:** Specify your custom JSP bag class name. For example,
   `MyCustomJspBag`.

-  **`contex.name`:** This should be a more human readable name, like `My Custom
   JSP Bag`.

-  **`service.ranking:integer`:** This will determine the priority of your
   implementation. If you specify `100` here, and one of your coworkers develops
a separate custom JSP bag implementation and gives theirs a ranking of `101`,
you're out of luck. Theirs will take precedence. Logically then, you should use
`102`.

## Extend a JSP [](id=extend-a-jsp)

If you want to add something to a core JSP, see if it has an empty `-ext.jsp`
and override that instead of the whole JSP. It'll keep things simpler and more
stable, since the full JSP might change significantly, breaking your
customization in the process. By overriding the `-ext.jsp`, you're only relying
on the original JSP including the `-ext.jsp`. For an example, open
`portal-web/docroot/html/common/themes/bottom.jsp`, and scroll to the end.
You'll see this:

    <liferay-util:include page="/html/common/themes/bottom-ext.jsp" />

If you need to add something to `bottom.jsp`, override `bottom-ext.jsp`. 

Remember, this type of customization should be seen as a last resort. There's a
risk that your override will break due to the nature of this implementation, and
core functionality in Liferay can go down with it. If the JSP you want to
override is in another module, refer to the article on [overriding JSPs from a module](develop/tutorials/-/knowledge_base/7-0/overriding-module-jsps).

## Site Scoped JSP Customization [](id=site-scoped-jsp-customization)

In Liferay 6.2, you could use [Application Adapters](/develop/tutorials/-/knowledge_base/6-2/customizing-sites-and-site-templates-with-application-adapters) to scope your core JSP
customizations to a specific site. Since the majority of JSPs were moved into
modules for Liferay 7.0, the use case for this has shrunk considerably. If you
need to scope a core JSP customization to a site, prepare an application adapter
[as you would have for Liferay 6.2](/develop/tutorials/-/knowledge_base/6-2/customizing-sites-and-site-templates-with-application-adapters), and deploy it to Liferay 7.0. It will still
work. However, note that this approach is deprecated in Liferay 7.0 and won't be
supported at all in Liferay 8.0.

If you're interested in scoping a module's JSP customization to a site, that's
another story. See the documentation on using Dynamic Include (not yet written).

