# Registering Entries with the Search Framework [](id=registering-entries-with-the-search-framework)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Entries<br>Step 1 of 5</p>
</div>

The search registrar for Entries is very similar to the one created for
Guestbooks. You'll even put it in the same package
(`com.liferay.docs.guestbook.search`). 

Create the `EntrySearchRegistrar`:

1.  In `com.liferay.docs.guestbook.search`, create a new class called
    `EntrySearchRegistrar` and populate it with two methods, `activate` and
    `deactivate`.

        @Component(immediate = true)
        public class EntrySearchRegistrar {

            @Activate
            protected void activate(BundleContext bundleContext) {

                _serviceRegistration = modelSearchRegistrarHelper.register(
                    Entry.class, bundleContext, modelSearchDefinition -> {
                        modelSearchDefinition.setDefaultSelectedFieldNames(
                            Field.COMPANY_ID, Field.ENTRY_CLASS_NAME,
                            Field.ENTRY_CLASS_PK, Field.UID, 
                            Field.SCOPE_GROUP_ID, Field.GROUP_ID);

                        modelSearchDefinition.setDefaultSelectedLocalizedFieldNames(
                            Field.TITLE, Field.CONTENT);

                        modelSearchDefinition.setModelIndexWriteContributor(
                            modelIndexWriterContributor);
                        modelSearchDefinition.setModelSummaryContributor(
                            modelSummaryContributor);
                        modelSearchDefinition.setSelectAllLocales(true);

                    });
            }

            @Deactivate
            protected void deactivate() {
                _serviceRegistration.unregister();
            }


As you did with Guestbooks, set the default selected field names used to
retrieve results documents from the search engine. For Entries, call
`setDefaultSelectedLocalizedFieldNames` for the title and content fields. This
ensures that the localized version of the field is searched and returned. The
only other difference with Entries is the call to
`setSelectAllLocales(true)`. It takes the fields set in
`setDefaultSelectedLocalizedFieldNames` and sets those fields for each
available locale in the `stored_fields` parameter of the search request. If not
set to `true`, only a single locale is searched.

2.  Specify the service references for the class:

            @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
            protected ModelIndexerWriterContributor<Entry> modelIndexWriterContributor;

            @Reference
            protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

            @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
            protected ModelSummaryContributor modelSummaryContributor;

            private ServiceRegistration<?> _serviceRegistration;

        }

    Target the `Entry` model while looking up a reference to the contributor
    classes. Later, when you create these contributor classes, you'll specify
    the model name again to complete the circle.

The Entry search and indexing class registration is completed. Next write the
search and indexing logic.
