---
header-id: overriding-cache
---

# Overriding Cache

[TOC levels=1-4]

@product@ pre-configures cache for service entities, service entity finder
results, and cache for several other classes. You can tune existing cache to meet
your needs. For example, it may help to write cache overflow elements to disk,
increase the maximum number of cached elements, or make other adjustments. Using
a module and only one XML file, you can override cache configurations
dynamically. 

| **Warning:** Modifying an Ehcache element flushes its cache. 

Here is how to override a cache configuration: 

1.  Identify the name of the cache you want to override. Existing cache 
    configurations and statistics (hit/miss counts and percentages) can be
    examined at runtime through JMX. Using a tool that supports JMX analysis,
    you can examine @product@'s cache configurations in the MBean of
    `net.sf.ehcache`. Please note that the caches listed in the MBean are more
    than what @product@'s cache configuration files specify because some caches
    are created purely through Java code.

    ![Figure 1: Caches configured in @product@ can be examined using JMX tools such as Zulu Mission Control  \(Portal Process &rarr; MBean server  &rarr; MBean Browser\)](../../images/zulu-mission-control.png)

    | **Note:** See
    | [Cache Names and Registration](/docs/7-2/frameworks/-/knowledge_base/f/cache-configuration#cache-names-and-registration)
    | to identify `EntityCache` and the different kinds of `FinderCache` instances 
    | associated with service entities. 

    Some cache configurations can also be viewed statically in their deployment
    artifacts or source code.

    -   `liferay-*-vm.xml` files in the
        `Liferay [version] Foundation - Liferay [version] Portal Cache - Impl.lpkg` file.

    -   `module-*-vm.xml` files in modules or Liferay LPKG files.

2.  If you don't own the existing project that specifies the cache or you want 
    to use a different project to configure the cache, create a module project.
    Otherwise, edit the cache in the existing project. These instructions
    demonstrate adding the cache configuration to a new module project. 

    | **Tip:** create new projects using the
    | [API project template](/docs/7-2/reference/-/knowledge_base/r/api-template)
    | and remove the Java class generated in the `src/main/java/` folder.

3.  In the `src/main/resources/META-INF` folder, add an XML file for the type of
    cache (multi-VM or single-VM) you're overriding.

    `module-multi-vm.xml` file:

    ```xml
    <ehcache
        dynamicConfig="true"
        monitoring="off"
        name="module-multi-vm"
        updateCheck="false"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
    >
        <!-- cache elements go here -->
    </ehcache>
    ```

    `module-single-vm.xml` file:

    ```xml
    <ehcache
        dynamicConfig="true"
        monitoring="off"
        name="module-single-vm"
        updateCheck="false"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
    >
        <!-- cache elements go here -->
    </ehcache>
    ```

4.  In the `<ehcache/>` element, add a `<cache/>` element and set its `name`
    attribute to the name of the cache you're overriding.

5.  Specify all existing `<cache/>` element attributes you want to preserve. 
    Hint: view the attributes in an MBean browser, as mentioned earlier. 

6.  Add or modify attributes to meet your needs. The `<cache/>` element 
    attributes are described in the
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    and
    [Ehcache documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html). 

7.  [Deploy the project](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project). 

Congratulations! Your cache modification is in effect. 

## Related Topics 

[Caching Data](/docs/7-2/frameworks/-/knowledge_base/f/caching-data)
