# Model Summary Contributor

### What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Model Summary Contributor sample shows how to provide a condensed,
text-based version of an entity that can be displayed generically. After
deploying the sample, type the text you want to search in Liferay Portal's
search bar.

You can demo this sample by completing the following steps in Liferay Portal:

1.  Click *Add* &rarr; *Widgets* and add the *Blogs* widget to the page.

2.  Create a new blog entry and then click *Publish*.

3.  Type the value you used before on the "Search Bar". The blog you added is
    displayed with the values defined in your `ModelSummaryContributor`.

### What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[ModelSummaryContributor](@app-ref@/portal-search/latest/javadocs/com/liferay/portal/search/spi/model/result/contributor/ModelSummaryContributor.html)
API.

### How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

Liferay's search engine provides an API to define custom fields on entities to
be displayed in Liferay's Portal search results. There are two classes that help
do this:

- `ModelSummayContributorRegistrar`
- `ModelSummayContributor`

The `ModelSummayContributorRegistrar` class registers the contributor:

    @Component(immediate = true)
    public class ModelSummayContributorRegistrar {

        @Activate
        protected void activate(BundleContext bundleContext) {
            _serviceRegistration = modelSearchRegistrarHelper.register(
                BlogsEntry.class, bundleContext,
                modelSearchDefinition -> {
                    modelSearchDefinition.setModelSummaryContributor(
                        modelSummaryContributor);
                });
        }

        @Deactivate
        protected void deactivate() {
            _serviceRegistration.unregister();
        }

        @Reference
        protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

        @Reference(
            target = "(indexer.class.name=com.liferay.blogs.model.BlogsEntry)"
        )
        protected ModelSummaryContributor modelSummaryContributor;

        private ServiceRegistration<?> _serviceRegistration;

    }

The `activate` method registers the contributor to OSGi. The
`ModelSummaryContributor` field's `@Reference` target attribute points to the
entity to use (i.e., `BlogsEntry`).

The second class, `ModelSummaryContributor`, defines the custom fields. First,
an `@Component` annotation is set to define the entity and register the service
to use:

    @Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.blogs.model.BlogsEntry",
        service = com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor.class
    )
    public class ModelSummaryContributor
        implements com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor {

In this sample, the `BlogsEntry` is the defined entity and
`ModelSummaryContributor` is the service.

There are also methods that create and retrieve the desired model summary:

    @Override
    public Summary getSummary(
        Document document, Locale locale, String snippet) {

        Summary summary = _createSummary(document);

        summary.setMaxContentLength(128);

        return summary;
    }

    private Summary _createSummary(Document document) {
        String prefix = Field.SNIPPET + StringPool.UNDERLINE;

        String title = document.get(prefix + Field.TITLE, Field.TITLE);

        String company = document.get(
            prefix + Field.COMPANY_ID, Field.COMPANY_ID);

        return new Summary(title, company);
    }

The method `getSummary` is invoked when a blog is added in Liferay Portal. This
calls the private `_createSummary` method, which adds `Field.TITLE` and
`Field.COMPANY_ID` to the blog entry's summary. You can update the sample to use
other fields, if necessary.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/extensions/model-summary-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/extensions/model-summary-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.1/maven/extensions/model-summary-contributor)