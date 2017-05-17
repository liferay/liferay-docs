# Creating Staged Models Manually [](id=creating-staged-models-manually)

There are times when using
[Service Builder to generate your staged models](/develop/tutorials/-/knowledge_base/7-0/#important-attributes-in-staging)
is not practical. In these cases, you should create your staged models manually.
Make sure to read the
[Adapting Your Business Logic to Build Staged Models](/develop/tutorials/-/knowledge_base/7-0/understanding-staged-models#adapting-your-business-logic-to-build-staged-models)
section to determine if creating staged models manually is beneficial for your
use case.

In this tutorial, you'll explore the Asset Link framework (used for
[relating assets](/discover/portal/-/knowledge_base/7-0/defining-content-relationships)),
which is an internal @product@ framework that leverages the
[ModelAdapterBuilder](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html)
framework. The Model Adapter Builder lets you adapt your model classes to staged
models.

Asset links do not provide UUIDs by default; however, they still need to be
tracked in the Staging and Export/Import frameworks, and therefore, require
staged models. Since Service Builder cannot generate staged models for asset
links since they don't provide a UUID, the Asset Link framework has to create
staged models differently using the Model Adapter Builder. You'll leverage the
Model Adapter Builder for your application by referencing the Asset Link
framework as an example.

1.  Create a new interface that extends one of the
    [staged model interfaces](/develop/tutorials/-/knowledge_base/7-0/understanding-staged-models#staged-model-interfaces)
    and your model specific interface.

        public interface StagedAssetLink extends AssetLink, StagedModel {

        }

    This interface should define methods required for your model to qualify as a
    staged model. For asset links, methods for retrieving entry UUIDs (among
    others) are defined:

        public String getEntry1Uuid();

        public String getEntry2Uuid();

    These will be implemented by a new implementation class later. The
    naming convention for this interface typically follows the `Staged[Entity]`
    syntax.

2.  Create an implementation class that implements your new `Staged[Entity]`:

        public class StagedAssetLinkImpl implements StagedAssetLink {

        }

    This class provides necessary logic for your entity model to be recognized
    as a staged model. Below is a subset of logic in the `StagedAssetLinkImpl`
    class used to populate UUIDs for asset link entries:

        public StagedAssetLinkImpl(AssetLink assetLink) {
             _assetLink = assetLink;

            ...

             populateUuid();
        }

        @Override
        public String getEntry1Uuid() {
            if (Validator.isNotNull(_entry1Uuid)) {
                return _entry1Uuid;
            }

            populateEntry1Attributes();

            return _entry1Uuid;
        }

        @Override
        public String getEntry2Uuid() {
            if (Validator.isNotNull(_entry2Uuid)) {
                return _entry2Uuid;
            }

            populateEntry2Attributes();

            return _entry2Uuid;
        }

        protected void populateEntry1Attributes() {

            ...

            AssetEntry entry1 = AssetEntryLocalServiceUtil.fetchAssetEntry(
                _assetLink.getEntryId1());

            ...

            _entry1Uuid = entry1.getClassUuid();
        }

        protected void populateEntry2Attributes() {

            ...

            AssetEntry entry2 = AssetEntryLocalServiceUtil.fetchAssetEntry(
                _assetLink.getEntryId2());

            ...

            _entry2Uuid = entry2.getClassUuid();
        }

        protected void populateUuid() {

            ...

            String entry1Uuid = getEntry1Uuid();
            String entry2Uuid = getEntry2Uuid();

            ...

            _uuid = entry1Uuid + StringPool.POUND + entry2Uuid;
                }
        }

        private AssetLink _assetLink;
        private String _entry1Uuid;
        private String _entry2Uuid;
        private String _uuid;

    This logic retrieves asset link entries and populates UUIDs for them usable
    by the Staging and Export/Import frameworks. With the newly generated UUIDs,
    asset link model classes can be converted to staged models.

3.  Create a Model Adapter Builder class and implement the
    [ModelAdapterBuilder](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html)
    interface. You should define the entity type and your Staged Model Adapter
    class when implementing the interface:

        public class StagedAssetLinkModelAdapterBuilder
            implements ModelAdapterBuilder<AssetLink, StagedAssetLink> {

            @Override
            public StagedAssetLink build(AssetLink assetLink) {
                return new StagedAssetLinkImpl(assetLink);
            }

        }

    For the `StagedAssetLinkModelAdapterBuilder`, the entity type is `AssetLink`
    and the Staged Model Adapter is `StagedAssetLink`. Your Model Adapter
    Builder outputs a new instance of the `Staged[Entity]Impl` object.

4.  Now you need to adapt your existing business logic to call the provided
    APIs. You can call the
    [ModelAdapterUtil](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/ModelAdapterUtil.html)
    class to create an instance of your Staged Model Adapter:

        StagedAssetLink stagedAssetLink = ModelAdapterUtil.adapt(
            assetLink, AssetLink.class, StagedAssetLink.class);

    Once you've created
    [Staged Model Data Handlers](/develop/tutorials/-/knowledge_base/7-0/data-handlers),
    you can begin exporting/importing your now Staging-compatible entities:

        StagedModelDataHandlerUtil.exportStagedModel(
            portletDataContext, stagedAssetLink);

    Visit the
    [Understanding Data Handlers](/develop/tutorials/-/knowledge_base/7-0/understanding-data-handlers)
    tutorial if you're unfamiliar with how data handlers work.

Awesome! You've successfully adapted your business logic to build staged models!
