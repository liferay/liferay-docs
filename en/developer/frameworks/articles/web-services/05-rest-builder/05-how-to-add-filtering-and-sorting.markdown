---
header-id: rest-builder-how-to-add-filtering-and-sorting
---

# How to add pagination, filtering and sorting in the REST Builder

[TOC levels=1-4]

## Pagination

To add pagination to your endpoints, just add _page_ and _pageSize_ as query parameters to your OpenAPI profile, like this: 

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

Those two parameters would add a _Pagination pagination_ parameter in the method signature, to be used to restrict the number of entries to return in the _Page.of_ constructor.

## Filtering, sorting and searching

Adding support for filtering, sorting, and searching is trickier. The first step would be adding the query parameters to the OpenAPI profile, like this:

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

The method signature would then receive a Sort object, a Filter object, and string with the search request. Those objects use the indexing framework built-in Liferay.

Headless support for filtering and sorting leverages the [Indexing framework](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/model-entity-indexing-framework). This gives us many benefits, like support for permissions out-of-the-box and having to write very little code to achieve sort, filter, and search.

So first, we have to make sure that our entity is indexed and uses the indexing framework. [This reference](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/model-entity-indexing-framework) is the best way of adding that support.

Once it's done we just have to do three things:

1. Add an EntityModel to translate between our indexing framework and our code
2. Link our entityModel with our resource implementation.
3. Call Search utilities to avoid boilerplate code.

### Add an EntityModel
 
The _EntityModel_ is the class responsible for doing the translation from the name the property has in our API to the name used to index it.

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

The _EntityModel_ allows us to decouple the way we filter/sort from the way we index the information. We could use one field to sort backed internally by several indexed fields or vice-versa.

### Link our EntityModel

Linking our _EntityModel_ is really easy, our resource implementation just has to implement the _EntityModelResource_ interface. 

Our entity model is very easy and doesn't have any dynamic fields, so we can instantiate it directly and return it in the _getEntityModel_ method, like this:

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

And lastly, we just have to call _SearchUtil.search()_ that links everything together and receives:

* _booleanQueryUnsafeConsumer_, a boolean query to restrict the information we want to retrieve.
* _filter_, pass-through of the filter object.
* _indexerClass_, the class of the entity that we are going to filter/search.
* _keywords_, pass-through of the search string.
* _pagination_, pass-through of the pagination object (to read the row requested).
* _queryConfigUnsafeConsumer_, the configuration for the fields that we want to return, typically the id to do a query later, in the _transformUnsafeFunction_.
* _searchContextUnsafeConsumer_, global configuration of the query.
* _transformUnsafeFunction_, the function that transforms from _Document_ (of the indexing framework) to our entity, either searching in the database, our persistance, another API...
* _sorts_, pass-through of the sorts object.

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

### Using our filter, search, and sort

We use OData to express our filter queries, following this [syntax](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/filter-sort-and-search#filter)

And that's it, we can filter/search and sort by the fields we have defined in our _EntityModel_.