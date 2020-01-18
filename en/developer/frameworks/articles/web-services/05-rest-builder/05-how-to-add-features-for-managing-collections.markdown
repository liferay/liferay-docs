---
header-id: rest-builder-managing-collections
---

# Managing Collections in REST Builder

[TOC levels=1-4]

## Pagination

To add pagination to your endpoints, add `page` and `pageSize` as query
parameters to your OpenAPI profile, like this: 

```yaml
- in: query
  name: page
  schema:
      type: integer
- in: query
  name: pageSize
  schema:
      type: integer
```

Those two parameters add a `Pagination pagination` parameter in the method
signature to restrict the number of entries to return in the `Page.of`
constructor.

Pagination is highly recommended for entities that can have many elements, to
avoid very large requests.

## Filtering, sorting and searching

Adding support for filtering, sorting, and searching is trickier. The first
step is to add the query parameters to the OpenAPI profile, like this:

```yaml
- in: query
  name: filter
  schema:
      type: string
- in: query
  name: search
  schema:
      type: string
- in: query
  name: sort
  schema:
      type: string
```

The method signature then receives a Sort object, a Filter object, and
string with the search request. Those objects use 
[Liferay's indexing framework](/docs/7-2/frameworks/-/knowledge_base/f/model-entity-indexing-framework).
This gives you many benefits, like support for permissions out-of-the-box and
having to write very little code to achieve sort, filter, and search.

So first, you must make sure your entity is indexed and uses the indexing
framework.

Once that's done you have three things to do:

1. Add an `EntityModel` to translate between our indexing framework and your code
2. Inject your `entityModel` into your resource implementation.
3. Call Search utilities to avoid boilerplate code.

### Add an EntityModel
 
The `EntityModel` is a class that translates the name the property has
in your API to the name used to index it.

```java
public class EntityEntityModel implements EntityModel {
	public EntityEntityModel() {
		_entityFieldsMap = EntityModel.toEntityFieldsMap(
			new StringEntityField(
				"name", locale -> Field.getSortableFieldName(Field.NAME))
		);
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	private final Map<String, EntityField> _entityFieldsMap;
}
```

The `EntityModel` decouples the way you filter/sort from the way you
index the information. You could use one field to sort, backed internally by
several indexed fields or vice-versa.

### Inject Your EntityModel

Injecting your `EntityModel` is really easy, our resource implementation just has
to implement the `EntityModelResource` interface. 

This entity model is simple and doesn't have any dynamic fields, so you can
instantiate it directly and return it in the `getEntityModel` method, like this:

```java
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/entity.properties",
	scope = ServiceScope.PROTOTYPE, service = EntityResource.class
)
public class EntityResourceImpl extends BaseEntityResourceImpl implements
	EntityModelResource {

    ...

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityEntityModel;
	}

	private EntityEntityModel _entityEntityModel = new EntityEntityModel();
}
```

### Call search utilities

Finally, you must call `SearchUtil.search()` that links everything together. It
requires these parameters:

`booleanQueryUnsafeConsumer`: a boolean query to restrict the information we
want to retrieve.

`filter`: pass-through of the filter object.

`indexerClass`: the class of the entity that to filter/search.

`keywords`: pass-through of the search string.

`pagination`: pass-through of the pagination object (to read the row requested).

`queryConfigUnsafeConsumer`: the configuration for the fields that you want to
return, typically the id to do a query later, in the `transformUnsafeFunction`.

`searchContextUnsafeConsumer`: global configuration of the query.

`transformUnsafeFunction`: the function that transforms from `Document` (of the
indexing framework) to your entity, either searching in the database, your
persistence, another API, etc. 

`sorts`: pass-through of the sorts object.

The code would be similar to this:

```java
@Override
public Page<Entity> getEntitiesPage(
    String search, Filter filter, Pagination pagination, Sort[] sorts)
    throws Exception {

    return SearchUtil.search(
        booleanQuery -> {},
        filter, Entity.class, search, pagination,
        queryConfig -> queryConfig.setSelectedFieldNames(
            Field.ENTRY_CLASS_PK),
        searchContext -> searchContext.setCompanyId(contextCompany.getCompanyId()),
        document -> new Entity(), //FILL with your implementation
        sorts);
}
```

### Using Your filter, search, and sort

Lifeay uses OData to express our filter queries, following this
[syntax](/docs/7-2/frameworks/-/knowledge_base/f/filter-sort-and-search#filter). 

And that's it! Now you can can filter/search and sort by the fields you 
defined in your `EntityModel`.

