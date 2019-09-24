---
header-id: cache-configuration
---

# Cache Configuration

[TOC levels=1-4]

Caching makes specified objects and object references readily available in
memory. It costs memory but improves performance. You can experiment with cache
to determine what's good for your system. If your site serves lots of Web
Content articles, for example, you may want to cache them.   

There are two cache levels.

**Level 1:** caching per session

**Level 2:** caching across multiple sessions

Liferay uses Hibernate for Level 1 cache but uses Ehcache for Level 2 cache.
Liferay's Level 2 Ehcache is configurable; the Level 1 Hibernate cache is not.
This section covers these topics on configuring Liferay's Level 2 cache using
Ehcache: 

-   Caching Service Entities
-   Caching Entity Finder Results
-   Caching Other Objects
-   Overriding Cache Configurations

To start, learn the Liferay cache configuration basics here.

## Cache Types

You can cache any classes you like.
[Service entities](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entities)
and
[service entity finder results](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entity-finder-methods)
are especially easy to cache, as
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
generates caching code for them in the service persistence layer. The code operates on these cache types:

**EntityCache:** Holds service entity primary keys. An entity's  
`*PersistenceImpl.fetchByPrimaryKey` method can use EntityCache if it's
configured. 

**FinderCache:** Holds parameterized service entity search results. An entity's
`fetchByValue`, `findByValue`, `countByValue`, `findAll`, and `countAll` methods
use the FinderCache if it's configured. 

## Cache Pools

@product@ manages two Level 2 cache pools:

**Multi-VM:** Cache is replicated among cluster nodes. EntityCache and
FinderCache belong in this pool because they must synchronize with data on all
nodes.

**Single-VM:** Cache is managed uniquely per VM and isn't replicated among
nodes. SingleVM cache is for objects and references that you don't need/want
replicated among nodes.

## Discovering and Viewing Cache Configurations

Existing cache configurations and statistics (hit/miss counts and percentages)
can be examined at runtime through JMX. Using a tool that supports JMX analysis,
you can examine @product@'s cache configurations in the MBean of
`net.sf.ehcache`. Please note that the caches listed in the MBean are more than
what @product@'s cache configuration files specify because some caches are
created purely through Java code.

![Figure 1: Caches configured in @product@ can be examined using JMX tools such as Zulu Mission Control  \(Portal Process &rarr; MBean server  &rarr; MBean Browser\)](../../images/zulu-mission-control.png)

Cache Configurations can also be viewed statically in their deployment artifacts
or source code.

-   `liferay-*-vm.xml` files in the `Liferay * Foundation - Liferay * Portal 
    Cache - Impl.lpkg`.

-   `module-*-vm.xml` files in modules or Liferay LPKG files.

The cache configuration file names map to the cache pools.

## Cache Configuration

Liferay's cache configuration files end in `-multi-vm.xml` for the multi-VM pool
and `-single-vm.xml` for the single-VM pool. All configurations adhere to the
[ehcache.xsd](http://www.ehcache.org/ehcache.xsd).
Liferay's global cache configuration is processed first on startup. Cache
configurations in modules and WARs are processed as they're deployed after the
initial global cache configuration. 

### Initial Global Cache Configuration

Liferay's portal cache implementation LPKG file 
(`Liferay * Foundation - Liferay * Portal Cache - Impl.lpkg`)
found in the `[Liferay_Home]/osgi/marketplace` folder contains the initial
global cache configuration. The LPKG file's
`com.liferay.portal.cache.ehcache.impl-[version].jar` hold the configuration
files:

-   `liferay-multi-vm.xml`: Maps to the multi-VM pool.  
-   `liferay-single-vm.xml`: Maps to the single-VM pool.

### Runtime Cache Configuration

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

## Cache Names and Registration

A cache is identified by its name (e.g., `<cache name="com.liferay.docs.MyClass"
... />`). If a module provides a cache configuration with the name of an
existing cache, the existing cache is overridden. If a module provides a cache
configuration with a new name, a new cache is added.

Here's what happens behind the scenes: Liferay's cache manager checks the
configurations. If the name of a cache exists, the cache manager removes it from
Ehcache's cache registry, and registers a new Ehcache into Ehcache's cache
registry. If the name is new, the Liferay cache manager just registers a new
Ehcache.

Cache names are arbitrary except for EntityCache and FinderCache, as described
in their respective articles in this section.

Now that you have a basic understanding of cache in Liferay, continue with
creating your own cache or overriding existing cache configurations. 
