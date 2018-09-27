# Model Summary Contributor

The `ModelSummaryContributor` shows a condensed, text-based version of the
entity that can be displayed generically.

### What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

After you deploy the sample, open your Liferay Portal and type the text you want
to search on the "Search Bar".

### What Api(S) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the ModelSummaryContributor API.

### How does this sample leverage the api(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

Liferay's search engine provides an API to define custom fields on entities to
be displayed on Liferay's Portal search results. To do that:

Create a class to register your contributor:

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

The method annotated with `@Activate` will register the contributor to OSGi.

The `ModelSummaryContributor` `@Reference` target attribute should point to the
Entity you want to use, in this sample we are using `BlogsEntry`.

Create a class to define the custom fields:

    @Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.blogs.model.BlogsEntry",
        service = com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor.class
    )

The `@Component`  should point is where you define the Entity and the Service
that will be used. In this sampĺe we are using `BlogsEntry` as the property and
`ModelSummaryContributor` as the service.

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

The method `getSummary` will be invoked when you add a blog on Liferay Portal.

In this sample, we are adding `Field.TITLE` and `Field.COMPANY_ID`, but you can
change that to use other fields.

To test this feature:

- Open you Liferay Portal, then click on the "+" icon -> "Widgets ->
  Collaboration -> Blogs" click on the "New Entry" button.
- Fill the fields with the values you want, click on "Publish" button.
- Type the value you used before on the "Search Bar". The blog you added should
  be displayed with the values defined on your `ModelSummaryContributor`.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different build tool:

- [Gradle](https://github.com/luanmaoski/liferay-blade-samples/tree/model-summary-contributor/gradle/extensions/model-summary-contributor)
- [Liferay Workspace](https://github.com/luanmaoski/liferay-blade-samples/tree/model-summary-contributor/liferay-workspace/extensions/model-summary-contributor)
- [Maven](https://github.com/luanmaoski/liferay-blade-samples/tree/model-summary-contributor/maven/extensions/model-summary-contributor)