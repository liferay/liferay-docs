# Overriding Core JSPs

Not to beat a dead horse, but Liferay is modularized to a great extent, so most
of the JSPs you might want to override are not in Liferay's core. However, there
are still some that you might want to modify, and it can be done. These JSPs are
located in the `portal-web/docroot/html` directory.

Before you begin crafting a module to override core JSPs, make sure the benefits
are going to outweigh the costs:

1. Liferay is still being modularized. If you modify a core JSP, it might be
moved out of the core and into a module, and then you'll have to re-implement
your override, since overriding a module's JSPs is [done differently](develop/tutorials/-/knowledge_base/7-0/overriding-module-jsps).

2. There's no clean, API-centered way to override Liferay's JSPs. Because of
that, compatibility cannot be guaranteed.

If it's *really* necessary, modifying a core JSP requires a few things of you: 

-  Include a class that implements `CustomJspBag`.

-  Register the service in the OSGi runtime.

-  Provide the JSP you're extending.

Of course you need to provide transportation for this code into Liferay's OSGi
runtime, and that transportation will be a module. When configuring it, make
sure you point to the root directory of JSPs in your bundle. In a `bnd.bnd` file
specify 

    -includeresource: META-INF/jsps=src/META-INF/jsps

Any core JSPs you're customizing should be put into this folder, and the rest of
their path and their name must match exactly the path and name of the JSP
that's nested underneath `portal-web/docroot/html`. For example, if you're
overriding

    portal-web/docroot/html/common/themes/bottom-ext.jsp 

and you used the `includeresource` directive above, put the overridden JSP in
this folder of your module:

    my-module/src/META-INF/jsps/html/common/themes/bottom-ext.jsp

## Implement a Custom JSP Bag

Create a class that implements `CustomJspBag`. 

    public class MyCustomJspBag implements CustomJspBag {

The overall goal in this implementation is to 

Override the `activate` method, adding all the custom JSPs to an `ArrayList` by
their directory path inside the directory you have them in.

<!--Ask Ray: Why is overriding the activate method necessary? And check
description of what it's doing.-->

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

Override the `getCustomJspDir` method, specifying the directory in your module
where your JSPs live.

        @Override
        public String getCustomJspDir() {
            return "META-INF/jsps/";
        }

Next override the `getCustomJsps` method returning the custom jsps loaded in the
activate method.

        @Override
        public List<String> getCustomJsps() {
            return _customJsps;
        }

Override `getURLContainer`, returning a new
`com.liferay.portal.kernel.url.URLContainer`. Instantiate the URL container and
override its `getResources` and `getResource` methods. The `getResources`
method is for looking up all of the paths to resources in the container by a
given path. It should return a `HashSet` of `Strings` for any matching
custom JSP paths. The `getResource` method returns one specific resource by its
name (the path included).


        @Override
        public URLContainer getURLContainer() {
            return _urlContainer;
        }

        ...

        private final URLContainer _urlContainer = new URLContainer() {

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

            @Override
            public URL getResource(String name) {
                return bundle.getEntry(name);
            }

        };

Override `isCustomJspGlobal` by simply returning true.

        @Override
        public boolean isCustomJspGlobal() {
            return true;
        }

    }

For an example of a full class that provides a working implementation of a
custom JSP bag, refer to the [blade.corejsphook BLADE project](https://github.com/rotty3000/blade/tree/blade.corejsphook/bndtools/blade.corejsphook).

## Register the Custom JSP Bag

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

## Extend a JSP

Now to the good part. Take a core JSP and extend it! If you want to add
something to a core JSP, see if it has an empty `-ext.jsp` and override that
instead of the whole JSP.  It'll keep things simpler and more stable, since the
full JSP might change significantly, breaking your customization in the process.
By overriding the `-ext.jsp`, you're only relying on the original JSP including
the `-ext.jsp`. As an example, open
`portal-web/docroot/html/common/themes/bottom.jsp`, and scroll to the end.
You'll see this:

    <liferay-util:include page="/html/common/themes/bottom-ext.jsp" />

If you need to add something to this JSP, override the `bottom-ext.jsp`. 

Remember, this type of customization should be seen as a last resort. There's a
risk that your override will break due to the nature of this implementation.
Proceed at your own risk.

