# Declaring Optional Import Package Requirements [](id=declaring-optional-import-package-requirements)

When developing @product@ modules, you can declare *optional* dependencies. An
optional dependency is one your module can use if it's available, but can still
function without it. 

+$$$

**Important:** Try to avoid optional dependencies. The best module designs
rely on normal dependencies. If an optional dependency seems desirable, your
module may be trying to provide distinct types of functionality. In such a
situation, it's best to split it into multiple modules that provide smaller,
more focused functionality. 

$$$

If you decide that your module requires an optional dependency, follow these 
steps to add it: 

1.  Declare the package that your module optionally requires as an optional 
    dependency in your module's `bnd.bnd` file:

        Import-Package: com.liferay.demo.foo;resolution:="optional"

    Note that you can use either an optional or dynamic import. The differences 
    are explained 
    [here](https://osgi.org/specification/osgi.core/7.0.0/framework.module.html#i2548181). 

2.  Create a component to use the optional package: 

        import com.liferay.demo.foo.Foo; // A class from the optional package

        @Component(
            enabled = false // instruct declarative services to ignore this component by default
        )
        public class OptionalPackageConsumer implements Foo {...}

3.  Create a second component to act as a controller of the first component. The
    second component checks the classloader for the optional class on the
    classpath. It handles both cases appropriately. If it's not there, this
    means you must catch any `ClassNotFoundException`. For example: 

        @Component
        public class OptionalPackageConsumerStarter {
            @Activate
            void activate(ComponentContext componentContext) {
                try {
                    Class.forName(com.liferay.demo.foo.Foo.class.getName());

                    componentContext.enableComponent(OptionalPackageConsumer.class.getName());
                }
                catch (Throwable t) {
                    _log.warn("Could not find {}", t.getMessage()); // Could use _log.info instead
                }
            }
        }

If the classloader check in the controller component is successful, the client 
component is enabled. This check is automatically performed whenever there are 
any wiring changes to the module containing these components (Declarative 
Services components are always restarted when there are wiring changes). 

As above, if you install the module when the optional dependency is missing from
@product@'s OSGi runtime, your controller component catches a
`ClassNotFoundException` and logs a warning or info message (or takes whatever
other action you implement to handle this case). If you install the optional
dependency, refreshing your module triggers the OSGi bundle lifecycle events
that trigger your controller's `activate` method and the check for the optional
dependency. Since the dependency exists, your client component uses it. 

Note that you can refresh a bundle from
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
with this command: 

    equinox:refresh [bundle ID] 

## Related Topics [](id=related-topics)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)
