---
header-id: cache-configuration
---

# Cache Configuration

[TOC levels=1-4]

Caching makes specified data readily available in memory. It costs memory but
improves performance. You can experiment with cache to determine what's good for
your system. If your site serves lots of web content articles, for example, you
may want to increase the limit on how many you can cache. 

Liferay's cache configuration framework uses
[Ehcache](https://www.ehcache.org/).
It's an independent framework used by @product@'s data access and template
engine components. It manages two pools: 

**Multi-VM:** Cache is replicated among cluster nodes. `EntityCache` and 
`FinderCache` (described next) are in this pool because they must synchronize
with data on all nodes.

**Single-VM:** Cache is managed uniquely per VM and isn't replicated among
nodes. Single-VM cache is for objects and references that you don't need/want
replicated among nodes. 

Here are ways you can configure the Ehcache: 

-   [Overriding Cache](/docs/7-2/frameworks/-/knowledge_base/f/overriding-cache):
    Tuning existing cache. 
-   [Caching Data](/docs/7-2/frameworks/-/knowledge_base/f/caching-data): 
    Implementing cache for custom data. 

Start learning the Liferay cache configuration basics here.

## Cache Types

You can cache any classes you like. Conveniently, @product@ caches
[service entities](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entities)
and
[service entity finder results](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entity-finder-methods)
automatically by default.
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
generates their caching code in the
[service persistence layer](/docs/7-2/appdev/-/knowledge_base/a/understanding-the-code-generated-by-service-builder).
The code operates on these cache types:

**`EntityCache`:** Holds service entities by primary keys. The caching code maps 
entity primary keys to implementation objects. An entity's
`*PersistenceImpl.fetchByPrimaryKey` method uses `EntityCache`.

**`FinderCache`:** Holds parameterized service entity search results. The caching 
code associates
[service entity finder](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entity-finder-methods)
query parameter values with matching entity results. There's code for caching
entities, paginated entity lists, and non-paginated entity lists that match your
finder parameters. An entity's `fetchByValue`, `findByValue`, `countByValue`,
`findAll`, and `countAll` methods use the FinderCache. 

## Cache Configuration

@product@ designates separate cache configurations for multi-VM and single-VM
environments. Default `EntityCache` and `FinderCache` are specified
programmatically, while Liferay's global cache configuration and custom cache
configurations are specified via files. All configurations adhere to the
[Ehcache XSD](http://www.ehcache.org/ehcache.xsd).

Liferay's global cache configuration is processed first on startup. Cache
configurations in modules and WARs are processed as they're deployed after the
initial global cache configuration. 

### Initial Global Cache Configuration

Liferay's portal cache implementation LPKG file 
(`Liferay [version] Foundation - Liferay [version] Portal Cache - Impl.lpkg`)
found in the `[Liferay_Home]/osgi/marketplace` folder contains the initial
global cache configuration. The LPKG file's
`com.liferay.portal.cache.ehcache.impl-[version].jar` holds the configuration
files:

-   `liferay-multi-vm.xml`: Maps to the multi-VM pool.
-   `liferay-single-vm.xml`: Maps to the single-VM pool.

### Module Cache Configuration

Modules can configure (add or override) cache using configuration files in their
`src/main/resources/META-INF` folder:

-   `module-multi-vm.xml`: Maps to the multi-VM cache manager.
-   `module-single-vm.xml`: Maps to the single-VM cache manager.

For example, the @product@ Web Experience suite's `com.liferay.journal.service`
module uses the following
[`module-multi-vm.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-service/src/main/resources/META-INF/module-multi-vm.xml)
to create a cache named `com.liferay.journal.util.JournalContent` in the
multi-VM pool. 

```xml
<ehcache
    dynamicConfig="true"
    monitoring="off"
    name="module-multi-vm"
    updateCheck="false"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
>
    <cache
        eternal="false"
        maxElementsInMemory="10000"
        name="com.liferay.journal.util.JournalContent"
        overflowToDisk="false"
        timeToIdleSeconds="600"
    >
    </cache>
</ehcache>
```

Portlet WARs can configure cache too. 
 
### Portlet WAR Cache Configuration 

Ehcache configuration in a portlet WAR has these requirements: 

1.  The Ehcache configuration XML file must be in the application context (e.g.,
    any path under `WEB-INF/src`).

2.  The `portlet.properties` file must specify the cache file location. Either 
    of the two properties is used and is assigned the cache file path, relative
    to the application context root (e.g., `WEB-INF/src`). 

```properties 
ehcache.single.vm.config.location=path/to/single/vm/config/file
ehcache.multi.vm.config.location=path/to/multi/vm/config/file 
```

For example, here's the
[`test-cache-configuration-portlet`](https://github.com/liferay/liferay-plugins/blob/7.0.x/portlets/test-cache-configuration-portlet)
WAR's structure:

-   `docroot/WEB-INF/src/`
    -   `ehcache/`
        -   [`liferay-single-vm-ext.xml`](https://github.com/liferay/liferay-plugins/blob/7.0.x/portlets/test-cache-configuration-portlet/docroot/WEB-INF/src/ehcache/liferay-single-vm-ext.xml)
        -   [`liferay-multi-vm-clustered-ext.xml`](https://github.com/liferay/liferay-plugins/blob/7.0.x/portlets/test-cache-configuration-portlet/docroot/WEB-INF/src/ehcache/liferay-multi-vm-clustered-ext.xml)
    -   `portlet.properties`

The `portlet.properties` file specifies these properties:

```properties 
ehcache.single.vm.config.location=ehcache/liferay-single-vm-ext.xml
ehcache.multi.vm.config.location=ehcache/liferay-multi-vm-clustered-ext.xml
```

## Cache Names and Registration

A cache is identified by its name (e.g., `<cache name="com.liferay.docs.MyClass"
... />`). If a module provides a cache configuration with the name of an
existing cache, the existing cache is overridden. If a module provides a cache
configuration with a new name, a new cache is added.

Here's what happens behind the scenes: Liferay's cache manager checks the
configurations. If a cache with the name already exists, the cache manager
removes it from Ehcache's cache registry and registers a new Ehcache into
Ehcache's cache registry. If the name is new, the Liferay cache manager just
registers a new Ehcache.

Cache names are arbitrary except for `EntityCache` and `FinderCache`. 

### EntityCache Names 

`EntityCache` uses this naming convention:

`PREFIX + ENTITY_IMPL_CLASS_NAME`

where the `PREFIX` is always this:

```
com.liferay.portal.kernel.dao.orm.EntityCache.
```

For example, the cache name for the `com.liferay.portal.kernel.model.User`
entity starts with the `PREFIX` and ends with the implementation class name
`com.liferay.portal.model.impl.UserImpl`:

```
com.liferay.portal.kernel.dao.orm.EntityCache.com.liferay.portal.model.impl.UserImpl
```

### FinderCache Names 

`FinderCache` uses this naming convention:

`PREFIX + ENTITY_IMPL_CLASS_NAME + [".LIST1"|".LIST2"]`

where the `PREFIX` is always this:

```
com.liferay.portal.kernel.dao.orm.FinderCache.
```

Here are the `FinderCache` types and their name patterns.

| Type | Pattern | Example |
| ---- | ------- | ------- |
| Entity instances matching query parameters.  | `PREFIX + ENTITY_IMPL_CLASS_NAME` | `com.liferay.portal.kernel.dao.orm.FinderCache.com.liferay.portal.model.impl.ClassNameImpl` |
| Paginated lists of entity instances matching query parameters.  | `PREFIX + ENTITY_IMPL_CLASS_NAME + ".List1"` | `com.liferay.portal.kernel.dao.orm.FinderCache.com.liferay.portal.model.impl.ClassNameImpl.List1` |
| Non-paginated lists of entity instances matching query parameters.  | `PREFIX + ENTITY_IMPL_CLASS_NAME + ".List2"` | `com.liferay.portal.kernel.dao.orm.FinderCache.com.liferay.portal.model.impl.ClassNameImpl.List2` |

Now that you have a basic understanding of cache in Liferay, continue with
overriding an existing cache configuration or caching custom data. 
