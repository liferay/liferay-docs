---
header-id: creating-a-segment-criteria-contributor
---

# Creating a Segment Criteria Contributor

[TOC levels=1-4]

To demonstrate the Segment Criteria Contributor, you'll create a contributor
that segments users based on the title of Knowledge Base articles they have
authored.

The first step is to make your related entity searchable through OData queries.
For this purpose, you must have classes:

- `EntityModel`: represents your associated entity (in this case, the
  `KBArticle`) with its fields of interest.

- `ODataRetriever`: obtains the `KBArticles` that match a given OData query.

You can view the
[full project on Github](https://github.com/epgarcia/liferay-portal/tree/LPS-86249.criteria.extension.sample.2/modules/apps/segments/segments-criteria-extension-sample).

Follow the instructions below to get started.

1.  [Create a module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project).

2.  Create the following packages within the module: 

    - `com.liferay.segments.criteria.extension.sample.internal.odata.retreiver`
    - `com.liferay.segments.criteria.extension.sample.internal.odata.entity`
    - `com.liferay.segments.criteria.extension.sample.internal.criteria.contributor`

Excellent! You have your module ready. Next, you'll create the entity model.

## Creating the Entity Model

First, create the Entity Model for `KBArticle`.

1.  Inside the `...internal.odata.entity` package, create the 
    `KBArticleEntityModel` class which implements `EntityModel`:

    ```java
    public class KBArticleEntityModel implements EntityModel {

    }
    ```

2.  Create the key for the entity name:

    ```java
    public static final String NAME = "KBArticle";
    ```

3.  Create the variable for the entity field map:

    ```java
    private final Map<String, EntityField> _entityFieldsMap;
    ```

4.  Create the methods to retrieve the `KBArticleEntity`, entity map, and entity
    name key:

    ```java
    public KBArticleEntityModel() {
        _entityFieldsMap = Stream.of(
            new StringEntityField("title", locale -> "titleKeyword")
            ).collect(
                Collectors.toMap(EntityField::getName, Function.identity())
            );
    }

    @Override
    public Map<String, EntityField> getEntityFieldsMap() {
        return _entityFieldsMap;
    }

    @Override
    public String getName() {
        return NAME;
    }
    ```

Next, you'll create the OData Retriever.

## Creating the `ODataRetriever`

Next, create the `ODataRetriever` which gets the data using the relevant filter. 

1.  Inside the `...internal.odata.retreiver` package, create 
    `KBArticleODataRetriever.java` which implements `ODataRetriever`:

    ```java
    public class KBArticleODataRetriever implements ODataRetriever<KBArticle> {
    	
    }
    ```

2.  Add the `@Component` declaration above the class declaration:

    ```java
    @Component(
        immediate = true,
        property = "model.class.name=com.liferay.knowledge.base.model.KBArticle",
        service = ODataRetriever.class
    )
    ```

3.  Create the `@Reference` objects that you need for the Filter Parser,
    Knowledge Base Article Service, and OData Search Adapter:

    ```java
    @Reference
    private FilterParserProvider _filterParserProvider;

     @Reference
    private KBArticleLocalService _kbArticleLocalService;

    @Reference
    private ODataSearchAdapter _oDataSearchAdapter;
    ```

4.  Create and instantiate the `_entityModel` object for the `KBArticle` model:

    ```java
    private static final EntityModel _entityModel = new KBArticleEntityModel();
    ```

5.  Create the public methods to retrieve the results and the results count from
    the OData filter:

    ```java
    @Override
    public List<KBArticle> getResults(
            long companyId, String filterString, Locale locale, int start,  int end)
        throws PortalException {

        Hits hits = _oDataSearchAdapter.search(
            companyId, filterString, KBArticle.class.getName(), _entityModel,
            	_getFilterParser(), locale, start, end);

        return _getKBArticles(hits);
    }

    @Override
    public int getResultsCount(
            long companyId, String filterString, Locale locale)
        throws PortalException {

        return _oDataSearchAdapter.searchCount(
            companyId, filterString, KBArticle.class.getName(), _entityModel,
                _getFilterParser(), locale);
    }
    ```

6.  Create the private methods for instantiating the `FilterParser` and
    retrieving the Knowledge Base article(s) that meet the criteria:

    ```java
    private FilterParser _getFilterParser() {
        return _filterParserProvider.provide(_entityModel);
    }

    private KBArticle _getKBArticle(Document document) throws PortalException {
        long resourcePrimKey = GetterUtil.getLong(
            document.get(Field.ENTRY_CLASS_PK));

        return _kbArticleLocalService.getLatestKBArticle(resourcePrimKey, 0);
    }

    private List<KBArticle> _getKBArticles(Hits hits) throws PortalException {
        Document[] documents = hits.getDocs();

        List<KBArticle> kbArticles = new ArrayList<>(documents.length);

        for (Document document : documents) {
            kbArticles.add(_getKBArticle(document));
        }

        return kbArticles;
    }
    ```

You're all set to create the Segments Criteria Contributor!

## Creating the `SegmentsCriteriaContributor`

Now create the `SegmentsCriteriaContributor` class that consumes the previous 
classes to retrieve the articles that match the query generated by the criteria 
editor, and contributes a query to filter users based on the articles they 
authored.

1.  In the `...internal.criteria.contributor` package, create a
    `UserKBArticleSegmentCritieriaContributor` class that implements 
    `SegmentsCriteriaContributor`.

    ```java
    public class UserKBArticleSegmentsCriteriaContributor
        implements SegmentsCriteriaContributor {

    }
    ```

2.  Create the `@Component` declaration to set properties and declare the 
    service class.

    ```java
    @Component(
        immediate = true,
        property = {
            "segments.criteria.contributor.key=" + UserKBArticleSegmentsCriteriaContributor.KEY,
            "segments.criteria.contributor.model.class.name=com.liferay.portal.kernel.model.User",
            "segments.criteria.contributor.priority:Integer=70"
        },
        service = SegmentsCriteriaContributor.class
    )
    ```

3.  Create the variables to enable logging, retrieve the entity model, and 
    entity key.

    ```java
    private static final Log _log = LogFactoryUtil.getLog(
        UserKBArticleSegmentsCriteriaContributor.class);

        private static final EntityModel _entityModel = new KBArticleEntityModel();

        public static final String KEY = "user-kb-article";
    ```

4.  Create the reference variables for the OData retriever and Portal instance.

    ```java
    @Reference(
        target = "(model.class.name=com.liferay.knowledge.base.model.KBArticle)"
    )

    private ODataRetriever<KBArticle> _oDataRetriever;

    @Reference
    private Portal _portal;
    ```

5.  Create the methods to define the implementation of
    `SegmentsCriteriaContributor`.

    ```java
    @Override
    public void contribute(
        Criteria criteria, String filterString,
            Criteria.Conjunction conjunction) {

        criteria.addCriterion(getKey(), getType(), filterString, conjunction);

        long companyId = CompanyThreadLocal.getCompanyId();
        String newFilterString = null;

        try {
            StringBundler sb = new StringBundler();

            List<KBArticle> kbArticles = _oDataRetriever.getResults(
                companyId, filterString, LocaleUtil.getDefault(),
                QueryUtil.ALL_POS, QueryUtil.ALL_POS);

            for (int i = 0; i < kbArticles.size(); i++) {
                KBArticle kbArticle = kbArticles.get(i);

                sb.append("(userId eq '");
                sb.append(kbArticle.getUserId());
                sb.append("')");

                if (i < (kbArticles.size() - 1)) {
                    sb.append(" or ");
                }
            }

            newFilterString = sb.toString();
        }
        catch (PortalException pe) {
            _log.error(
                com.liferay.petra.string.StringBundler.concat(
                    "Unable to evaluate criteria ", criteria, " with filter ",
                    filterString, " and conjunction ", conjunction.getValue()),
                pe);
        }

        if (Validator.isNull(newFilterString)) {
            newFilterString = "(userId eq '0')";
        }

        criteria.addFilter(getType(), newFilterString, conjunction);
    }

    @Override
    public EntityModel getEntityModel() {
        return _entityModel;
    }

    @Override
    public String getEntityName() {
        return KBArticleEntityModel.NAME;
    }

    @Override
    public List<Field> getFields(PortletRequest portletRequest) {
        return Collections.singletonList(
            new Field(
                "title",
                LanguageUtil.get(_portal.getLocale(portletRequest), "title"),
                "string"));
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public Criteria.Type getType() {
        return Criteria.Type.MODEL;
    }
    ```

6.  [Deploy your module](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project).

After deploying your extension, the segment criteria editor includes a new 
section containing Knowledge Base properties. Notice that the section's UI, 
the properties, and their associated input fields and operations have been 
automatically generated based on the information provided by the extension 
services. For instance, the Knowledge Base article title supports *equals*, *not 
equals*, *contains*, and *not contains* operations because it was defined as a 
`StringEntityField`.

![Figure 1: The sample field appears.](../../images/segment-new-category.png)

Awesome! You've created a Segment Criteria Contributor!
