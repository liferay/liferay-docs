# Registering Guestbooks with the Search Framework

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 2 of 3</p>
</div>

First, update your `build.gradle` to have all of the necessary imports.

1.  Open the `build.gradle` file in your `guestbook-service` project.

2.  Add the Search Service Provider Interface dependency to the `build.gradle`
    file:

        compileOnly group: "com.liferay", name: "com.liferay.portal.search.spi", version: "2.0.0"

3.  Save the file and run `Refresh Gradle Project`.

Once the dependency is configured, register the Search services that build the
entity's `ModelSearchDefinition`.

A `*SearchRegistrar` specifies the classes that the entity uses to contribute to
building a `ModelSearchDefinition`. Activation of the `SearchRegistrar`
component results in a cascade of activity in the search framework, culminating
with the building of a `DefaultIndexer`. The `DefaultIndexer` is registered
under the class name defined in the registrar, and then used for
indexing/searching objects of that class.

Create the `GuestbookSearchRegistrar`:

1.  Create a new package in the `guestbook-service` module project's
    `src/main/java` folder called `com.liferay.docs.guestbook.search`. In this
    package, create a new class called `GuestbookSearchRegistrar` and populate
    it with two methods, `activate` and `deactivate`.

        @Component(immediate = true)
        public class GuestbookSearchRegistrar {

            @Activate
            protected void activate(BundleContext bundleContext) {

                _serviceRegistration = modelSearchRegistrarHelper.register(
                    Guestbook.class, bundleContext, modelSearchDefinition -> {
                        modelSearchDefinition.setDefaultSelectedFieldNames(
                            Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
                            Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                            Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
                            Field.TITLE, Field.UID);

                        modelSearchDefinition.setModelIndexWriteContributor(
                            modelIndexWriterContributor);
                        modelSearchDefinition.setModelSummaryContributor(
                            modelSummaryContributor);
                    });
            }

            @Deactivate
            protected void deactivate() {

                _serviceRegistration.unregister();
            }

    The annotations `@Activate` and `Deactivate` ensure each method is invoked
    as soon as the Component is started (activated) or when it's about to be
    stopped (deactivated). On activation of the Component, a chain of search and
    indexing classes is registered for the Guestbook entity. Set the default
    selected field names used to retrieve results documents from the search
    engine. Then set the contributors used to build a model search definition.

2.  Specify the service references for the class:

            @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Guestbook)")
            protected ModelIndexerWriterContributor<Guestbook> modelIndexWriterContributor;

            @Reference
            protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

            @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Guestbook)")
            protected ModelSummaryContributor modelSummaryContributor;

            private ServiceRegistration<?> _serviceRegistration;

        }

    Target the `Guestbook` model while looking up a reference to the contributor
    classes. Later, when you create these contributor classes, you'll specify
    the model name again to complete the circle.

3. Export the `com.liferay.docs.guestbook.search` package in the 
    `guestbook-service` module's `bnd.bnd` file. The export section should look
    like this: 

        Export-Package:
          com.liferay.docs.guestbook.service.permission,\
          com.liferay.docs.guestbook.search

The Guestbook search and indexing class registration is completed. Next write
the search and indexing logic.
