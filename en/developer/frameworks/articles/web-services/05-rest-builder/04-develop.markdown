---
header-id: rest-builder-develop
---

# Developing an API with REST Builder

[TOC levels=1-4]

After executing `gw buildREST`, you have two modules: _headless-test-api_ and
_headless-test-impl_.

* Headless Test API contains the interfaces for your resources and the POJOs
  of your schemas.

* Headless Test Impl contains your implementation and the JAX-RS application.

Your generated `EntityResource` looks like this:

```java
public interface EntityResource {

	public Page<Entity> getEntitiesPage() throws Exception;

	public Entity postEntity(Entity entity) throws Exception;

	public void deleteEntity(Integer entityId) throws Exception;

	public Entity getEntity(Integer entityId) throws Exception;

	public Entity putEntity(Integer entityId, Entity entity) throws Exception;

    //Context methods

	public default void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {}

	public void setContextCompany(Company contextCompany);

	public default void setContextHttpServletRequest(HttpServletRequest contextHttpServletRequest) {}

	public default void setContextHttpServletResponse(HttpServletResponse contextHttpServletResponse) {}

	public default void setContextUriInfo(UriInfo contextUriInfo) {}

	public void setContextUser(User contextUser);
}
```

These are generated methods you defined in the OpenAPI profile (the full set as
displayed in the examples).

REST builder also generates two implementation files, a base class, with all
the JAX-RS, GraphQL and OpenAPI annotations and an empty implementation,
`EntityResourceImpl`:

```java
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/entity.properties",
	scope = ServiceScope.PROTOTYPE, service = EntityResource.class
)
public class EntityResourceImpl extends BaseEntityResourceImpl {
}
```

This is where you implement new methods, by overriding the base class
implementation and returning your code. For example, here's a prototype
implementation storing entities in a Map:

```java
Map<Integer, Entity> entities = new HashMap<>();

@Override
public Entity getEntity(Integer entityId) throws Exception {
    return entities.get(entityId);
}

@Override
public Page<Entity> getEntitiesPage() throws Exception {
    return Page.of(entities.values());
}

@Override
public void deleteEntity(Integer entityId) throws Exception {
    entities.remove(entityId);
}

@Override
public Entity postEntity(Entity entity) throws Exception {
    entities.put(entity.getId(), entity);
    return entity;
}

@Override
public Entity putEntity(Integer entityId, Entity entity) throws Exception {
    entities.put(entity.getId(), entity);
    return entity;
}
```

For the collection, you return a `Page` object based on a list but there
are also utility methods that return the pagination information:

 ```java
Page.of(list, pagination, totalCount)
```

Don't touch the interfaces or the base classes (those are
regenerated every time you run REST Builder). Like 
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder), you only have 
to maintain the implementation classes, and if you change the API, run REST
Builder again and the interfaces are updated. Your business logic could call
other REST APIs, use Service Builder or another persistence mechanism.

## Development Cycle

While implementing your API's business logic, you'll typically improve your API
by adding parameters or other paths. For that, you'll modify the OpenAPI profile
and regenerate the API again calling the `buildREST` command.

The cycle starts anew until you get to the final state and deploy your APIs. 
They become available at this URL pattern: 
                                                                               
    http://localhost:8080/o/[application class name]/[OpenAPI version]/

You can also execute `jaxrs:check` in the OSGi console to see all the JAX-RS
endpoints.

GraphQL paths and entities are added automatically to the default
GraphQL endpoint: 

    localhost:8080/o/graphql 

You can disable GraphQL generation by adding `generateGraphQL: false` to your
`rest-config.yaml` (`generateREST` controls the generation of the REST
endpoints).

## Wrapping Up

So... that's all! 

When everything is ready, you might want to consider publishing your Headless API
to Swaggerhub so others can consume it. You can use the following URL pattern for that: 

    http://localhost:8080/o/[application name]/[application version]/openapi.yaml

The URL for the example above, therefore, would be 

    http://localhost:8080/o/headless-test/v1.0/openapi.yaml

This URL has the content of `rest-openapi.yaml` plus the classes that REST
Builder generated for you. 
