# JSP Overrides Using Custom JSP Bag [](id=jsp-overrides-using-custom-jsp-bag)

Liferay's API based approaches to overriding JSPs (i.e.,
[Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/customizing-jsps-with-dynamic-includes)
and
[Portlet Filters](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-portlet-filters))
are the best way to override JSPs in apps and in the core. You can also use
Custom JSP Bags to override core JSPs. But the approach is not as stable as the
API based approaches. If your Custom JSP Bag's JSP is buggy (because of your
code or because of a change in Liferay), you are most likely to find out at
runtime, where functionality breaks and nasty log errors greet you. Using
Custom JSP Bags to override JSPs is a bad practice, equivalent to using Ext
plugins to customize @product@. If you're maintaining existing Custom JSP Bags,
however, this tutorial explains how they work.

+$$$

**Important:** Liferay cannot guarantee that JSPs overridden using Custom JSP
Bag can be upgraded.

$$$

A Custom JSP Bag module must satisfy these criteria: 

-   Provides and specifies a custom JSP for the JSP you're extending.

-   Includes a
    [`CustomJspBag`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBag.html)
    implementation for serving the custom JSPs.

The module provides transportation for this code into Liferay's OSGi runtime.
After you
[create your new module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module),
continue with providing your custom JSP. 

## Providing a Custom JSP [](id=providing-a-custom-jsp)

Create your JSPs to override @product@ core JSPs. If you're using the Maven
[Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html),
place your JSPs under `src/main/resources/META-INF/jsps`. For example, if you're
overriding

    portal-web/docroot/html/common/themes/bottom-ext.jsp 

place your custom JSP at

    [your module]/src/main/resources/META-INF/jsps/html/common/themes/bottom-ext.jsp

+$$$

**Note:** If you place custom JSPs somewhere other than 
`src/main/resources/META-INF/jsps` in your module, assign that location to a
`-includeresource: META-INF/jsps=` directive in your module's `bnd.bnd` file.
For example, if you place custom JSPs in a folder `src/META-INF/custom_jsps` in
your module, specify this in your `bnd.bnd`:

    -includeresource: META-INF/jsps=src/META-INF/custom_jsps

$$$

## Implement a Custom JSP Bag [](id=implement-a-custom-jsp-bag)

@product@ (specifically the
[`CustomJspBagRegistryUtil` class](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBagRegistryUtil.html))
loads JSPs from
[`CustomJspBag`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBag.html)
services. The
[Core Override JSP sample project](/develop/reference/-/knowledge_base/7-1/core-jsp-hook)
provides a working custom JSP bag implementation. The following steps for
implementing a custom JSP bag refer to that sample class code. 

1.  In your module, create a class that implements   
    [`CustomJspBag`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/deploy/hot/CustomJspBag.html).

2.  Register your class as an OSGi service by adding an `@Component` annotation
    to it, like this: 

        @Component(
            immediate = true,
            property = {
            	"context.id=BladeCustomJspBag",
                "context.name=Test Custom JSP Bag",
            	"service.ranking:Integer=100"
            }
        )

    - **`immediate = true`:** Makes the service available on module activation. 
    -  **`context.id`:** Your custom JSP bag class name. Replace 
    `BladeCustomJspBag` with your class name.
    -  **`contex.name`:** A more human readable name for your service. Replace 
    it with a name of your own. 
    -  **`service.ranking:Integer`:** A priority for your implementation. If you
       specify `100` here, and one of your coworkers develops a separate custom
       JSP bag implementation and gives theirs a ranking of `101`, you're out of
       luck. Theirs will take precedence. Logically then, you should use `102`.

3.  Implement the `getCustomJspDir` method to return the folder path in your 
    module's JAR  where the JSPs reside (for example, `META-INF/jsps`). 
    
        @Override
        public String getCustomJspDir() {
            return "META-INF/jsps/";
        }

4.  Create an `activate` method and the following fields. The method adds the 
    URL paths of all your custom JSPs to a list when the module is activated.

        @Activate
    	protected void activate(BundleContext bundleContext) {
    		_bundle = bundleContext.getBundle();

    		_customJsps = new ArrayList<>();

    		Enumeration<URL> entries = _bundle.findEntries(
    			getCustomJspDir(), "*.jsp", true);

    		while (entries.hasMoreElements()) {
    			URL url = entries.nextElement();

    			_customJsps.add(url.getPath());
    		}
    	}

    	private Bundle _bundle;
    	private List<String> _customJsps;

5.  Implement the `getCustomJsps` method to return the list of this module's 
    custom JSP URL paths.

        @Override
        public List<String> getCustomJsps() {
            return _customJsps;
        }

6.  Implement the `getURLContainer` method to return a new
    `com.liferay.portal.kernel.url.URLContainer`. Instantiate the URL container
    and override its `getResources` and `getResource` methods. The
    `getResources` method looks up all the paths to resources in the container
    by a given path. It returns a `HashSet` of `Strings` for the matching custom
    JSP paths. The `getResource` method returns one specific resource by its
    name (the path included). The sample's `BladeCustomJspBag` class implements
    `getURLContainer` like this: 

        @Override
        public URLContainer getURLContainer() {
            return _urlContainer;
        }

        private final URLContainer _urlContainer = new URLContainer() {

            @Override
            public URL getResource(String name) {
                return _bundle.getEntry(name);
            }

            @Override
            public Set<String> getResources(String path) {
                Set<String> paths = new HashSet<>();

                for (String entry : _customJsps) {
                    if (entry.startsWith(path)) {
                       paths.add(entry);
                    }
                }

                return paths;
            }

        };

7.  Implement the `isCustomJspGlobal` method to return `true`.

        @Override
        public boolean isCustomJspGlobal() {
            return true;
        }

Now your module provides custom JSPs and a custom JSP bag implementation. When
you deploy it, @product@ uses its custom JSPs in place of the core JSPs they
override. For a working example, examine the
[Core Override JSP sample project](/develop/reference/-/knowledge_base/7-1/core-jsp-hook).

## Extend a JSP [](id=extend-a-jsp)

If you want to add something to a core JSP, see if it has an empty `-ext.jsp`
and override that instead of the whole JSP. It keeps things simpler and more
stable, since the full JSP might change significantly, breaking your
customization in the process. By overriding the `-ext.jsp`, you're only relying
on the original JSP including the `-ext.jsp`. For an example, open
`portal-web/docroot/html/common/themes/bottom.jsp`, and scroll to the end.
You'll see this:

    <liferay-util:include page="/html/common/themes/bottom-ext.jsp" />

If you must add something to `bottom.jsp`, override `bottom-ext.jsp`. 

Since @product@ 7.0, the content from the following JSP files formerly in
`html/common/themes` are inlined to improve performance.
 
- `body_bottom-ext.jsp`
- `body_top-ext.jsp`
- `bottom-ext.jsp`
- `bottom-test.jsp`

They're no longer explicit files in the code base. But you can still create them
in your module to add functionality and content. 

Remember, this type of customization is a last resort. Your override may break
due to the nature of this implementation, and core functionality in Liferay can
go down with it. If the JSP you want to override is in another module, refer to
the section on 
[API based approaches to overriding JSPs](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-custom-jsp-bag#using-portals-api-to-override-a-jsp).

## Site Scoped JSP Customization [](id=site-scoped-jsp-customization)

In Liferay Portal 6.2, you could use
[Application Adapters](/develop/tutorials/-/knowledge_base/6-2/customizing-sites-and-site-templates-with-application-adapters)
to scope your core JSP customizations to a specific Site. Since the majority of
JSPs were moved into modules for @product@ 7.0, the use case for this has shrunk
considerably. If you must scope a core JSP customization to a Site, prepare an
application adapter 
[as you would have for Liferay Portal 6.2](/develop/tutorials/-/knowledge_base/6-2/customizing-sites-and-site-templates-with-application-adapters),
and deploy it to @product-ver@. It will still work. However, note that this
approach is deprecated in @product-ver@ and won't be supported at all in Liferay
8.0.

<!-- Uncomment once we cover scoping to a site
If you're interested in scoping a module's JSP customization to a site, that's
another story. See the documentation on [using Dynamic Include](/develop/tutorials/-/knowledge_base/7-1/customizing-jsps-with-dynamic-includes).
-->

## Related Topics [](id=related-topics)

[Core Override JSP sample project](/develop/reference/-/knowledge_base/7-1/core-jsp-hook)

[Upgrading Core JSP Hooks](/develop/tutorials/-/knowledge_base/7-1/upgrading-core-jsp-hooks)
