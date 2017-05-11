# Understanding Staged Models

To track an entity of an application with the Staging framework, you must
implement the
[StagedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface in the app's model classes. It provides the behavior contract for the
entities Staging administers during the Staging process. For example, the
Bookmarks application manages
[BookmarksEntry](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntry.html)s
and
[BookmarksFolder](@app-ref/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksFolder.html)s,
and both implement the `StagedModel` interface. Once you've configured your
staged models, you can create staged model data handlers, which are used to
supply information about a staged model (entity) and its referenced content to
the Export/Import and Staging frameworks. See the
[Understanding Data Handlers](/develop/tutorials/-/knowledge_base/7-0/understanding-data-handlers)
tutorial for more information.

There are two ways to create staged models for your application's entities:

- Using Service Builder to generate the required Staging implementations
  ([tutorial]()).
- Implementing the required Staging interfaces manually
  ([tutorial]()).

You can follow step-by-step procedures for creating staged models for your
entities by visiting their respective tutorials.

Using Service Builder to generate your staged models is the easiest way to
create staged models for your app. You define the necessary columns in your
`service.xml` file and set the `uuid` attribute to `true`. Then you run
Service Builder, which generates the required code for your new staged models.

Implementing the necessary staged model logic *manually* should be done if you
**don't** want to extend your model with special attributes only required to
generate Staging logic (i.e., not needed by your business logic). In this case,
you should adapt your business logic to meet the Staging framework's needs.
You'll learn more about this later.

To aid in your understanding of staged models, you'll explore the provided
interfaces.

## Staged Model Interfaces

The `StagedModel` interface must be implemented by your app's model classes, but
this is typically done through inheritance by implementing one of the
interfaces extending the base interface:

- [StagedAuditedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedAuditedModel.html)
- [StagedGroupedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedGroupedModel.html)

The implementation of these is required when you want to use certain features of
the Staging framework like automatic group mapping or entity level *Last Publish
Date* handling. So how do you choose which is right for you?

The `StagedAuditedModel` interface provides all the audit fields to the model
that implements it. You can check the
[AuditedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/AuditedModel.html)
interface for the specific audit fields provided. The `StagedAuditedModel`
interface is intended for models that function independent from the group
concept (sometimes referred to as company models). If your model is a group
model, you should not implement the `StagedAuditedModel` interface.

The `StagedGroupedModel` interface must be implemented for group models. For
example, if your application requires the `groupId` column, your model is a
group model. If your model satisfies both the `StagedGroupModel` and
`StagedAuditedModel` requirements, it should implement the `StagedGroupedModel`.
Your model should only implement the `StagedAuditedModel` if it doesn't fulfill
the grouped model needs, but does fulfill the audited model needs. If your model
does not fulfill either the `StagedAuditedModel` or `StagedGroupedModel`
requirements, you should implement the base `StagedModel` interface.

As an example for extending your model class, you can visit the
[BookmarksEntryModel](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntryModel.html)
class, which extends the `StagedGroupedModel` interface; this is done because
bookmark entries are group models.

    public interface BookmarksEntryModel extends BaseModel<BookmarksEntry>,
        ShardedModel, StagedGroupedModel, TrashedModel, WorkflowedModel {

Now that you have a better understanding about staged model interfaces, you'll
dive into the attributes used in Staging and why they're important.

## Important Attributes in Staging

If you'd like to generate your staged models using
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder), you
must define the proper attributes in your project's `service.xml`. For a full
tutorial, see
[Generating Staged Models using Service Builder]().
You'll learn some general information about this process next.

One of the most important attributes used by the Staging framework is the UUID.
This attribute must be set to `true` in your `service.xml` file for Service
Builder to recognize your model as an eligible staged model. The UUID is used to
differentiate entities between environments. Because the UUID always remains the
same, it's unique across multiple systems. Why is this so important?

Suppose you're using
[remote staging](/discover/portal/-/knowledge_base/7-0/enabling-staging#enabling-remote-live-staging)
and you create a new entity on your local staging site and publish it to your
remote live site. What happens when you go back to modify the entity on your
local site and want to publish those changes? Without a UUID, this cannot be
done because the Staging framework has no way to distinguish between the two
entities. To avoid reproducing that entity again on the remote site, the Staging
framework must have a unique ID that stays the same across multiple systems.
That way, Staging recognizes the existence of the original entity on the remote
site, and updates it instead of reproducing it.

There are several columns that must be defined in your `service.xml` for
Service Builder to define your model as a staged model:

- `companyId`
- `createDate`
- `modifiedDate`

If you want a staged grouped model, also include the `groupId` and
`lastPublishDate` columns. If you want a staged audited model, include the
`userId` and `userName` columns.

Next, you'll learn how to build staged models from scratch.

## Building Staged Models from Scratch

What if you don't want to extend your model with special attributes that may not
be needed in your business logic? In this case, you should adapt your business
logic to meet the Staging framework's needs. Liferay provides the
[ModelAdapterBuilder](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html)
framework, which lets you adapt your model classes to staged models.

As an example, assume you have an app that is fully developed and you want to
configure it to work with Staging. Your app, however, does not require a UUID
for any of its entities, and therefore, does not provide them. Instead of
configuring your app to handle UUIDs just for the sake of generating staged
models, you can leverage the Model Adapter Builder to build your staged models.
You would need to 

Another example for building staged models from scratch is for applications that
use REST services to access their attributes instead of the database. Since this
kind of app is developed to pull its attributes from a remote system, it would
be more convenient to build your staged models yourself instead of relying on
Service Builder, which is database driven.

To adapt your model classes to staged models, follow the steps outlined below:

1.  Create a `Staged[Entity]` interface, which extends the model specific
    interface (e.g., `[Entity]`) and the appropriate staged model interface
    (e.g., `StagedModel`). This class serves as the staged model adapter.
2.  Create a `StagedAssetLinkImpl` class, which implements the
    `StagedAssetLink` interface and provides necessary logic for your entity
    model to be recognized as a staged model.
3.  Create a `Staged[Entity]ModelAdapterBuilder` class that implements
    `ModelAdapterBuilder<[Entity], Staged[Entity]>`. This class adapts the
    original model to the newly created staged model adapter.
4.  Adapt your existing model and call one of provided APIs to export or import
    the entity automatically.

To step through the process for leveraging the Model Adapter Builder for an
existing app, visit the
[Creating Staged Models Manually](/develop/tutorials/-/knowledge_base/7-0/creating-staged-models-manually)
tutorial.