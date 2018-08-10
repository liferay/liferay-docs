# Creating Staged Models

To implement the Staging framework, you must first specify the entities you want
to track. For the Guestbook application, there are two: guestbooks and entries.
You can register these entities so they're recognizable to the Staging framework
by implementing the
[StagedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)
interface in your Guestbook's model classes.

Fortunately, Service Builder has the power to generate an app's models as staged
models when
[certain attributes](/develop/tutorials/-/knowledge_base/7-1/understanding-staged-models#important-attributes-in-staging)
are specified in the app's `service.xml` file. The Guestbook app already defines
many of the necessary attributes in its `service.xml` file, so both your
`GuestbookModel` and `EntryModel` interfaces already extend the `StagedModel`
interface! For example, your Guestbook app's `EntryModel` interface's
declaration looks like this:

    public interface EntryModel extends BaseModel<Entry>, GroupedModel, ShardedModel,
        StagedAuditedModel, WorkflowedModel {

The `StagedModel` interface is implemented by the extension of the
[StagedAuditedModel](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedAuditedModel.html)
interface. The `StagedAuditedModel` interface was chosen by Service Builder
based on the columns you declared. You'll update this later.

The following Staging-specific attributes/columns are currently defined in the
Guestbook app's `service.xml` file:

- `uuid` (required)
- `groupId`
- `companyId` (required)
- `userId`
- `userName`
- `createDate` (required)
- `modifiedDate` (required)

One of the most important attributes used by the Staging framework is the UUID
(Universally Unique Identifier). This attribute must be set to `true` in your
`service.xml` file for Service Builder to recognize your model as an eligible
staged model. The UUID is used to differentiate entities between environments.
Because the UUID always remains the same, it's unique across multiple systems.

The `companyId`, `createDate`, and `modifiedDate` columns are required to track
the current entity's site and creation/modification dates.

The others are required to leverage features of the Staging framework like
automatic group mapping or entity level Last Publish Date handling. See the
[Understanding Staged Models](/develop/tutorials/-/knowledge_base/7-0/understanding-staged-models)
tutorial for more information.

## Updating the Extended Staged Model Interface

Staged models that extend the `StagedAuditedModel` interface are intended to
function independent from the group concept (sometimes referred to as company
models). This means that, for example, your guestbook and entry's scope would
not be tracked by the Staging framework. You must add one more column to your
`service.xml` file to convert your models to `StagedGroupedModel`s (i.e.,
correctly track the scope of your entities):

1.  Open your `guestbook-service` module's `service.xml` file and add the
    `lastPublishDate` column for both Guestbook and Entry entities:

        <column name="lastPublishDate" type="Date" />

2.  Run Service Builder. Do this by navigating to the Gradle Tasks pane on the
    right side of IDE and selecting your project's *build* &rarr; *buildService*
    task.

Service Builder has updated your models to now extend the `StagedGroupedModel`.
For example, your `EntryModel` interface's declaration now looks like this:

    public interface EntryModel extends BaseModel<Entry>, ShardedModel,
        StagedGroupedModel, WorkflowedModel {

For more information on the available staged model interfaces, see this
[tutorial](/develop/tutorials/-/knowledge_base/7-0/understanding-staged-models#staged-model-interfaces)

Excellent! Now it's time to create your staged model data handlers.
