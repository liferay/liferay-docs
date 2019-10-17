---
header-id: caching-entity-finder-results
---

# Caching Entity Finder Results

[TOC levels=1-4]

If your app frequently retrieves service entities based on criteria, your
service entity finder might be good candidates to cache. In Service Builder project's
[persistence classes](/docs/7-2/appdev/-/knowledge_base/a/understanding-the-code-generated-by-service-builder)
Service Builder generates code that associates
[service entity finder](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entity-finder-methods)
query parameter values with their results. There's code for caching entities,
paginated entity lists, and non-paginaged entity lists that match your finder
parameters. The code is bypassed by default. Configuring cache for the entity
finder results (FinderCache) enables the generated caching code. Here you'll
configure cache for retrieving finder results faster. 

1.  Open your
    [`*-service` project](/docs/7-2/appdev/-/knowledge_base/a/creating-a-service-builder-project)
    project.

2.  Create a file called `module-multi-vm.xml` in your
    `src/main/resources/META-INF` folder.

3.  In the `module-multi-vm.xml` file, add finder cache elements for the entity
    query results you want to cache for quick retrieval.

    This `module-multi-vm.xml` file configures cache for all three query result
    types (see previous table) for the `ResourcePermission` entity.

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
    		name="com.liferay.portal.kernel.dao.orm.FinderCache.com.liferay.portal.model.impl.ResourcePermissionImpl"
    		overflowToDisk="false"
    		timeToIdleSeconds="600"
    	>
    	</cache>
    	<cache
    		eternal="false"
    		maxElementsInMemory="10000"
    		name="com.liferay.portal.kernel.dao.orm.FinderCache.com.liferay.portal.model.impl.ResourcePermissionImpl.List1"
    		overflowToDisk="false"
    		timeToIdleSeconds="600"
    	>
    	</cache>
    	<cache
    		eternal="false"
    		maxElementsInMemory="10000"
    		name="com.liferay.portal.kernel.dao.orm.FinderCache.com.liferay.portal.model.impl.ResourcePermissionImpl.List2"
    		overflowToDisk="false"
    		timeToIdleSeconds="600"
    	>
    	</cache>
    </ehcache>
    ```

4.  Replace the `<cache/>` element's `name` with one for your entity. The cache 
    configuration
    [introduction](/docs/7-2/frameworks/-/knowledge_base/f/cache-configuration)
    explains the FinderCache naming convention.

5.  Refer to the
    [Ehcache documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html)
    and the
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    to configure cache that meets your requirements.

6.  [Deploy your project](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project).

Congratulations! Your entity finder cache is active.

