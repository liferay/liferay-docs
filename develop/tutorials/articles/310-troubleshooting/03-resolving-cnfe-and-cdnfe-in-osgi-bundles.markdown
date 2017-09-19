# Resolving ClassNotFoundException and NoClassDefFoundError in OSGi Bundles [](id=resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles)

Understanding a `ClassNotFoundException` or `NoClassDefFoundError` in non-OSGi
environments is straightforward. 

-   `ClassNotFoundException`: thrown when looking up a class that isn't on the
    classpath or using an invalid name to look up a class. 
-   `NoClassDefFoundError`: occurs when a compiled class references another 
    class that isn't on the runtime classpath. 

In OSGi environments, however, things are more complex. When trying to determine 
why a `ClassNotFoundException` or `NoClassDefFoundError` is occurring, consider 
these possible cases: 

1.  The missing class belongs to a module dependency that's an OSGi module. 
2.  The missing class belongs to a module dependency that's *not* an OSGi 
    module. 
3.  The missing class belongs to a global library, either at the @product@ 
    webapp scope or the application server scope. 

Figuring out which of these cases applies to your situation is a good first step 
towards diagnosing the problem. This tutorial looks at each case in more detail. 

## Case 1: The Missing Class Belongs to an OSGi Module [](id=case-1-the-missing-class-belongs-to-an-osgi-module)

In this case, there are two possible causes: 

1.  **The module doesn't import the class's package:** For a module (or WAB) to 
    consume another module's exported class, the consuming module must import 
    the exported package that contains the class. For example, you can do this 
    via an `Import-Package` header in the consuming module's `bnd.bnd` file. If 
    the consuming module tries to access the class without importing it, a 
    `ClassNotFoundException` or `NoClassDefFoundError` occurs. 

    In the consuming module, make sure to import the correct package. First 
    check the package name. If the package import is correct but you still get 
    the exception or error, the class might no longer exist in the package. 

2.  **The class no longer exists in the imported package:** In OSGi runtime 
    environments, modules can change, and come and go. If you reference or look 
    up another module's class and that class has been removed, a 
    `NoClassDefFoundError` or `ClassNotFoundException` occurs.
    [Semantic Versioning](http://semver.org) 
    guards against this scenario. It dictates that removing a class from an 
    exported package constitutes a new major version for that package. 
    Neglecting to increment the package's major version breaks dependent 
    modules. 

    For example, a module that consumes the class `com.foo.Bar` specifies the 
    package import `com.foo;version=[1.0.0, 2.0.0)`--the module uses `com.foo` 
    versions from `1.0.0` up to (but not including) `2.0.0`. The first part of 
    the version number (the `1` in `1.0.0`) represents the *major* version. The 
    consuming module doesn't expect any *major* breaking changes, like a class 
    removal. Removing `com.foo.Bar` from `com.foo` without incrementing the 
    package to a new major version (e.g., `2.0.0`) causes a 
    `ClassNotFoundException` or `NoClassDefFoundError` when other modules look 
    up or reference that class. 

    Here are a couple options to consider since the class no longer exists in 
    the package: 

    -   Adapt to the new API. To learn how to do this, read the 
        package's/module's Javadoc, release notes, and or formal documentation. 
        You can also ask the author, or search forums. 

    -   Revert to the module version you used previously. Deployed module 
        versions reside in `[Liferay_Home]/osgi/`. For details, see
        [Backing up Liferay Installations](/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation#backing-up-liferays-file-system). 

    Do what you think is best to get your module working properly. 

Now you know how to resolve common situations involving `ClassNotFoundException` 
or `NoClassDefFoundError`. For additional information on `NoClassDefFoundError`, 
see OSGi Enroute's article 
[What is NoClassDefFoundError?](http://enroute.osgi.org/faq/class-not-found-exception.html). 

## Case 2: The Missing Class Doesn't Belong to an OSGi Module [](id=case-2-the-missing-class-doesnt-belong-to-an-osgi-module)

In this case, you have two options: 

1.  Convert the dependency into an OSGi module so that it can export the missing 
    class. Converting a non-OSGi `JAR` file dependency into an OSGi module that 
    you can deploy alongside your application should be your first choice--it's 
    the ideal solution. 

2.  Embed the dependency in your module. To do this, you can embed the 
    dependency `JAR` file's packages as private packages in your module. If you 
    want to embed a non-OSGi `JAR` file in your application, see the tutorial 
    [Adding Third Party Libraries to a Module](/develop/tutorials/-/knowledge_base/7-0/adding-third-party-libraries-to-a-module). 

## Case 3: The Missing Class Belongs to a Global Library [](id=case-3-the-missing-class-belongs-to-a-global-library)

In this case, you can configure @product@ so that the OSGi system module exports 
the missing class's package. Then your module can import it. You should **NOT**, 
however, undertake this lightly. If Liferay intended to make a global library 
available for use by developers, the system module would already export such a 
library! Still, if you must access a global library that's not currently 
exported and can't think of **any** other solution, you can consider adding the 
required package for export by the system module. There are two ways to do this: 

1.  In your @product@ instance's `portal-ext.properties` file, use the property 
    `module.framework.system.packages.extra` to specify the packages to export. 

2.  If the package you need is from a @product@ JAR, you might be able to add 
    the module to the list of exported packages in the 
    `META-INF/system.packages.extra.bnd` file of  
    `[LIFERAY_HOME]/osgi/core/com.liferay.portal.bootstrap.jar`. Try this option 
    only if the first option doesn't work. 

If the package you need is from a @product@ module, in which case it's **NOT** 
from a global library), you can add the package to that module's `bnd.bnd` 
exports. But again, you should **NOT** undertake this lightly. The package would 
already be be exported if Liferay intended for it to be available. 

## Related Topics [](id=related-topics)

[Backing up Liferay Installations](/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation)

[Adding Third Party Libraries to a Module](/develop/tutorials/-/knowledge_base/7-0/adding-third-party-libraries-to-a-module)
