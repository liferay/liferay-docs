---
header-id: caching-data
---

# Caching Data

[TOC levels=1-4]

[Liferay's caching framework](/docs/7-2/frameworks/-/knowledge_base/f/cache-configuration)
helps you use Ehcache to cache any data. The [`SingleVMPool`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/SingleVMPool.html)
and
[`MultiVMPool`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/MultiVMPool.html)
classes use Liferay's 
[`PortalCache`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/PortalCache.html)
utility. Storing and retrieving cached data objects is as easy as using a hash
map: you associate a key with every cache value. The following steps demonstrate
implementing data caching. 

| **Note:** If you want to modify cache for Service Builder Service Entities or 
| Entity Finder results, see
| [Overriding Cache](/docs/7-2/frameworks/-/knowledge_base/f/overriding-cache).


## Step 1: Determine Cache Pool Requirements 

There are cache pools for single-VM and multi-VM environments. The pool types
and some Ehcache features require using `Serializable` values. 

1.  Determine whether to create a cache
    [in a single VM or across multiple VMs](/docs/7-2/frameworks/-/knowledge_base/f/cache-configuration)
    (e.g., in a clustered environment).

2.  Determine if it's necessary to serialize the data you're caching. 

    -   [`MultiVMPool`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/MultiVMPool.html)
        requires both the cache key and cache value to be 
        [`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html). 

    -   [`SingleVMPool`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/SingleVMPool.html)
        typically requires only cache keys to be
        [`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html). 
        Note that some Ehache features, such as `overflowToDisk`, require
        `Serializable` values too. 

## Step 2: Implement a Cache Key 

Cache keys must be unique,
[`Serializable`](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)
objects. They should relate to the values being cached. For example, in
@product@'s `JournalContentImpl`, a `JournalContentKey` instance relates to each
cached `JournalArticleDisplay` object. Here's the `JournalContentKey` class:

```java
private static class JournalContentKey implements Serializable {

	@Override
	public boolean equals(Object obj) {
		JournalContentKey journalContentKey = (JournalContentKey)obj;

		if ((journalContentKey._groupId == _groupId) &&
			Objects.equals(journalContentKey._articleId, _articleId) &&
			(journalContentKey._version == _version) &&
			Objects.equals(
				journalContentKey._ddmTemplateKey, _ddmTemplateKey) &&
			(journalContentKey._layoutSetId == _layoutSetId) &&
			Objects.equals(journalContentKey._viewMode, _viewMode) &&
			Objects.equals(journalContentKey._languageId, _languageId) &&
			(journalContentKey._page == _page) &&
			(journalContentKey._secure == _secure)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, _groupId);

		hashCode = HashUtil.hash(hashCode, _articleId);
		hashCode = HashUtil.hash(hashCode, _version);
		hashCode = HashUtil.hash(hashCode, _ddmTemplateKey);
		hashCode = HashUtil.hash(hashCode, _layoutSetId);
		hashCode = HashUtil.hash(hashCode, _viewMode);
		hashCode = HashUtil.hash(hashCode, _languageId);
		hashCode = HashUtil.hash(hashCode, _page);

		return HashUtil.hash(hashCode, _secure);
	}

	private JournalContentKey(
		long groupId, String articleId, double version,
		String ddmTemplateKey, long layoutSetId, String viewMode,
		String languageId, int page, boolean secure) {

		_groupId = groupId;
		_articleId = articleId;
		_version = version;
		_ddmTemplateKey = ddmTemplateKey;
		_layoutSetId = layoutSetId;
		_viewMode = viewMode;
		_languageId = languageId;
		_page = page;
		_secure = secure;
	}

	private static final long serialVersionUID = 1L;

	private final String _articleId;
	private final String _ddmTemplateKey;
	private final long _groupId;
	private final String _languageId;
	private final long _layoutSetId;
	private final int _page;
	private final boolean _secure;
	private final double _version;
	private final String _viewMode;

}
```

`JournalContentKey`s constructor populates fields that collectively define
unique keys for each piece of journal content. 

Note a cache key's characteristics:

1.  A key instance's field values relate to the cached data and distinguish it 
    from other data instances.

2.  A key follows `Serializable` class best practices. 

    -   Overrides `Object`'s `equals` and `hashcode` methods. 
    -   Includes a private static final long `serialVersionUID` field. It is to
        be incremented when a new version of the class is incompatible with
        previous versions. 

Your cache key class is ready for caching data values. 

## Step 3: Implement Cache Logic

When your application creates or requests the data type you're caching, you must
handle getting existing data from cache and putting new/updated data into the
cache. @product@'s caching classes are easy to inject into a 
[Declarative Services (DS) Component](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services),
but you can access them using
[`ServiceTracker`](/docs/7-2/frameworks/-/knowledge_base/f/using-a-service-tracker)s
too. These steps use fictitious key and value classes: `SomeKey` and `SomeValue`. 

1.  Name your cache. Cache names are arbitrary, but they must be unique in the 
    cache pool, and typically identify the data type being cached. 

```java 
protected static final String CACHE_NAME = SomeValue.class.getName();
```

2.  Access the VM pool you're using.
    [`MultiVMPool`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/MultiVMPool.html)
    and
    [`SingleVMPool`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/SingleVMPool.html)
    are Declarative  Service (DS) components. To  access a pool from a DS
    component, apply the
    [`@Reference`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Reference.html)
    annotation to a pool field (see below). Otherwise, use a
    [`ServiceTracker`](/docs/7-2/frameworks/-/knowledge_base/f/using-a-service-tracker)
    to access the pool. 

```java
@Reference
private MultiVMPool _multiVMPool;
```

3.  Declare a private static
    [`PortalCache`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cache/PortalCache.html)
    instance. 

```java 
private static PortalCache<SomeKey, SomeValue> _portalCache;
```

4.  Initialize your `PortalCache` when your class is being activated or 
    initialized. If you're using a DS component, initialize the cache in your
    component's activation method (annotated with
    [`@Activate`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Activate.html)).
    Get the cache from your VM pool using your cache name. For example, this DS
    component's activation method gets a cache from the multi-VM pool. 

```java 
@Activate
public void activate() {
    _portalCache =
        (PortalCache<SomeKey, SomeValue>)
            _multiVMPool.getPortalCache(CACHE_NAME);
    ...
}
```

5.  Similarly, remove your cache when your class instance is deactivated or 
    destroyed. If you're using a DS component, remove the cache in your
    deactivation method (annotated with
    [`@Deactivate`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Deactivate.html)).
    Use the VM pool to remove the cache. 

```java 
@Deactivate
public void deactivate() {
    _multiVMPool.removePortalCache(CACHE_NAME);
}
```

6.  In your code that uses the cached data, implement your caching logic. 
    Here's some example code:

```java 
SomeKey key = new SomeKey(...); 

SomeValue value = _portalCache.get(
    key);

if (value == null) {
    value = createSomeValue(...);

    _portalCache.put(key, value);
}

// continue using the data 
...
```

The code above constructs a key based on the data being used. Then, the key is
used to check the `PortalCache` for the data. If the cache doesn't have data
associated with the key, data is created and put it into the cache. The code
continues using the cached data. Use similar logic for the data you are caching. 

Configuring the cache and deploying your project is next. 

## Step 4: Configure the Cache 

It's time to specify your Ehcache configuration. 

1.  Depending on the VM pool you're using, start your XML file in one of the
    following ways. 

Multi VM file:

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

Single VM file: 

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

2.  Add a `<cache>` element for the cache you're creating. Although the cache
    name is arbitrary, using a name-spaced name such as a fully qualified class
    name is a best practice. 

    Configure your `<cache>` element to fit your caching requirements. The
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    and
    [Ehcache documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html)
    describe the `<cache>` attributes.

    For example, the Liferay Web Experience suite's `com.liferay.journal.service`
    module uses this
    [`module-multi-vm.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-service/src/main/resources/META-INF/module-multi-vm.xml)
    file to configure its cache named `com.liferay.journal.util.JournalContent`. 

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

3.  Deploy your project. 

Congratulations! Your data cache is in effect. 

## Related Topics 

[Overriding Cache](/docs/7-2/frameworks/-/knowledge_base/f/overriding-cache)
