# Understanding Staged Models

To track an entity of an application with the Staging framework, you must
implement the
[StagedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface in the app's model classes. It provides the behavior contract for the
entities Staging used during the Staging process. For example, the Bookmarks
application manages
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

Implementing the necessary staged model logic manually should be done if you
**don't** want to extend your model with special attributes only required to
generate Staging logic (i.e., not needed by your business logic). In this case,
you should adapt your business logic to meet the Staging framework's needs.

To aid in your understanding of staged models, you'll explore the interfaces
involved.

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
dive into the variables used in Staging and why they're important.

## Variables in Staging















