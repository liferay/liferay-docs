---
header-id: rest-builder-develop
---

# How to develop our API with REST Builder

[TOC levels=1-4]

Let's take a quick look at what we've generated after executing _gw buildREST_ and our project structure. Right now we have two modules, _headless-test-api_, and _headless-test-impl_.

* Headless Test API will contain the interfaces for our resources and the POJOs of our schemas.
* Headless Test Impl will contain our implementation, under the _ResourceImpl files, GraphQL files, and the JAX-RS application.

Our, generated, _EntityResource_ looks like this:

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

Here we have the methods that we have defined in our OpenAPI profile (the full set as displayed in the examples).

REST builder also generates two implementation files, a base class, with all the JAX-RS, GraphQL and OpenAPI annotations and an empty implementation, _EntityResourceImpl_:

```java
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/entity.properties",
	scope = ServiceScope.PROTOTYPE, service = EntityResource.class
)
public class EntityResourceImpl extends BaseEntityResourceImpl {
}
```

This is the file where we'll implement our new methods, by overriding the base class implementation and returning our code. For example, a fake implementation storing entities in a Map would be:

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

For the collection, we are returning a _Page_ object based on a list but there are also utility methods that return the pagination information like:

     Page.of(list, pagination, totalCount)

We don't have to touch the interfaces or the base classes (those will be regenerated every time we run REST Builder), just our resource implementations, adding there our logic. We could call other REST APIs, use Service Builder or another persistence mechanism.

## Development Cycle

After implementing the business logic of our API, typically we'll improve our API adding parameters or other paths. For that, we'll modify the OpenAPI profile and regenerate the API again calling the buildREST command.

And the cycle starts anew until we get to the final state and deploy our APIs that will be available under:
                                                                               
    http://localhost:8080/o/APPLICATION_CLASSNAME/OPEN_API_VERSION/
    
You can also execute _jaxrs:check_ in the OSGi console to see all the JAX-RS endpoints.

And GraphQL? your paths and entities have been added automatically to the GraphQL endpoint (by default, localhost:8080/o/graphql). You can disable GraphQL generation by adding _generateGraphQL: false_ to your rest-config.yaml (generateREST controls headless).

## Wrapping Up

So... that's all! 

When everything is ready, we might want to consider publishing our Headless API to Swaggerhub so others can consume it. For that, we can use the endpoint http://localhost:8080/o/headless-test/v1.0/openapi.yaml, which has the content of rest-openapi.yaml plus the classes that the REST Builder generates for us. 