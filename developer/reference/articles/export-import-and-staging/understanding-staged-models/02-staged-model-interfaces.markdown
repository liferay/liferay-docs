# Staged Model Interfaces

The
[StagedModel](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface must be implemented by your app's model classes, but this is typically
done through inheritance by implementing one of the interfaces that extend the
base interface:

- [StagedAuditedModel](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedAuditedModel.html)
- [StagedGroupedModel](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedGroupedModel.html)

You must implement these when you want to use certain features of the Staging
framework like automatic group mapping or entity level *Last Publish Date*
handling. So how do you choose which is right for you?

The `StagedAuditedModel` interface provides all the audit fields to the model
that implements it. You can check the
[AuditedModel](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/AuditedModel.html)
interface for the specific audit fields provided. The `StagedAuditedModel`
interface is intended for models that function independent from the group
concept (sometimes referred to as company models). If your model is a group
model, you should not implement the `StagedAuditedModel` interface.

The `StagedGroupedModel` interface must be implemented for group models. For
example, if your application requires the `groupId` column, your model is a
group model. If your model satisfies both the `StagedGroupModel` and
`StagedAuditedModel` requirements, it should implement `StagedGroupedModel`.
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

Now you have a better understanding about staged model interfaces.
