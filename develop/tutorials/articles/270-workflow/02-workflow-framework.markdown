# Liferay's Workflow Framework [](id=liferays-workflow-framework)

Enabling your application's entities to support workflow is so easy, you could do it in
your sleep (but don't try). Workflow-enabled entities require two things:

-  A workflow handler class to interact with Liferay's workflow back-end and
   the entity's service layer.

-  Some extra fields in their database table that help keep track of their
   status, along with methods in the service layer that update them. 

You have, therefore, two tasks to enable workflow: 

1.  Create a `WorkflowHandler` class for your application. 

2.  Update your service layer to integrate it with workflow. 

Time to get started. 

## Creating a Workflow Handler [](id=creating-a-workflow-handler)

The workflow handler should go in the module containing service
implementations. It's nice to keep your back-end code separate from your view
layer and controller (in the MVC pattern).

1.  Create a Component class. It should extend `BaseWorkflowHandler<T>`, an
    abstract class that provides a default implementation of the `WorkflowHandler<T>`
    service. Pass the interface for your model as the type parameter for the class.

        public class FooEntityWorkflowHandler extends BaseWorkflowHandler<FooEntity>

2.  Since you're publishing a service to be consumed in the OSGi runtime, your
    workflow handler class must be registered. If you're using Declarative
    Services, make it a Component class, using the `@Component` annotation.

        @Component(
            property = {"model.class.name=com.my.app.package.model.FooEntity"},
            service = WorkflowHandler.class
        )

    It needs one property, to set `model.class.name` to the fully qualified class
    name of the class you passed as the type parameter. It must also declare the
    type of service being implemented (`WorkflowHandler.class`). 

3.  There are three methods to override in the workflow handler, and the first two
    are boilerplate methods:

        @Override
        public String getClassName() {
            return FooEntity.class.getName();
        }

    `getClassName` returns the model class's fully qualified class name
    (`com.my.app.package.model.FooEntity`, for example).

        @Override
        public String getType(Locale locale) {
            return ResourceActionsUtil.getModelResource(locale, getClassName());
        }

    `getType` returns the model resource name
    (`model.resource.com.my.app.package.model.FooEntity`, for example).

        @Override
        public FooEntity updateStatus(int status, Map<String, Serializable> workflowContext) {

    Most of the heavy lifting is in the `updateStatus` method. It returns
    a call to a local service method of the same name (for example,
    `FooEntityLocalService.updateStatus`), so the status returned from the workflow
    back-end can be persisted to the entity table in the database. 

4.  The `updateStatus` method needs a user ID, the primary key for the
    class (for example, `fooEntityId`), the workflow status, the service
    context, and the workflow context. The status and the workflow context can
    be obtained from the workflow back-end. The other parameters can be
    obtained from the workflow context.

        @Override
        public FooEntity updateStatus(
                int status, Map<String, Serializable> workflowContext)
            throws PortalException {

            long userId = GetterUtil.getLong(
                (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
            long classPK = GetterUtil.getLong(
                (String)workflowContext.get(
                    WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

            ServiceContext serviceContext = (ServiceContext)workflowContext.get(
                "serviceContext");

            return _fooEntityLocalService.updateStatus(
                userId, classPK, status, serviceContext, workflowContext);
        }

Now your entity can be handled by Liferay's workflow framework. Next, update the
service methods to account for workflow status and add a new method to update
the status of an entity in the database.

## Updating the Service Layer [](id=updating-the-service-layer)

In most Liferay applications, 
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder) 
is used to create database fields. First, you must update the service layer: 

- The service layer must populate the new fields when entities are added to the
  database. 

- The service layer must send the entity through Liferay's workflow, and it needs
  to handle the workflow status of the entity when it's returned by the workflow. 

- The service layer needs getters that return entities by workflow
  status (usually *approved*).

After this is done, the View layer should account for the workflow status of
displayed entities.

1.  Make sure your entity database table has `status`, `statusByUserId`,
    `statusByUserName`, and `statusDate` fields. If you're using service
    builder, add this to your `service.xml` if you haven't already:

        <column name="status" type="int" />
        <column name="statusByUserId" type="long" />
        <column name="statusByUserName" type="String" />
        <column name="statusDate" type="Date" />

2.  Wherever you're setting the other database fields in your persistence code,
    set the workflow status as a draft and set the other fields.

        fooEntity.setStatus(WorkflowConstants.STATUS_DRAFT);
        fooEntity.setStatusByUserId(userId);
        fooEntity.setStatusByUserName(user.getFullName());
        fooEntity.setStatusDate(serviceContext.getModifiedDate(null));

With Service Builder driven Liferay applications, this is in the local service
implementation class (`-LocalServiceImpl`). 

When an entity is added to the database, the application must detect whether
workflow is enabled. If not, it automatically marks the entity as approved so
it appears in the UI. Otherwise, it's left in draft status and the workflow
back-end handles it. Thankfully, this whole process is easily done with
a single call to
`WorkflowHandlerRegistryUtil.startWorkflowInstance`. 

1.  At the end of any method that adds a new entity to your database, call the
    workflow service: 

        WorkflowHandlerRegistryUtil.startWorkflowInstance(fooEntity.getCompanyId(),
                fooEntity.getGroupId(), fooEntity.getUserId(), FooEntity.class.getName(),
                fooEntity.getPrimaryKey(), fooEntity, serviceContext);

2.  Once you've set the database fields for workflow status and started the
    workflow instance, implement the `updateStatus` method that must be called
    in the workflow handler. The workflow handler gets the entity's status from
    the workflow back-end and passes it to your service layer, which persists
    the updated entity to the database. 

        fooEntity.setStatus(status);
        fooEntity.setStatusByUserId(user.getUserId());
        fooEntity.setStatusByUserName(user.getFullName());
        fooEntity.setStatusDate(serviceContext.getModifiedDate(now));

        fooEntityPersistence.update(fooEntity);

3.  After setting the workflow fields for the entity, think about the specifics
    of your situation and whether any additional logic should be added to this
    method. For instance, if your entities are Liferay Assets already, you must
    change the visibility of the asset depending on its workflow status, so
    the Asset Publisher doesn't show entities that haven't yet been approved in
    the workflow process.

        if (status == WorkflowConstants.STATUS_APPROVED) {

            assetEntryLocalService.updateEntry(
                FooEntity.class.getName(), fooEntityId, fooEntity.getDisplayDate(),
                null, true, true);
        }

        else {

            assetEntryLocalService.updateVisible(
                fooEntity.class.getName(), entryId, false);
        }

    If approved, Workflow updates the asset with the publication date, a `listable`
    boolean, and a `visible` boolean being updated to reflect the current state of
    the asset. If the workflow status is anything other than approved, its
    visibility is set to `false`.


4.  Before leaving the service layer, add a call to
    `deleteWorkflowInstanceLinks` in the `deleteEntity` method. Here's what it
    looks like:

        workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
            fooEntity.getCompanyId(), fooEntity.getGroupId(),
            FooEntity.class.getName(), fooEntity.getFooEntityId());

    When you send an entity to the workflow framework via the
    `startWorkflowInstance` call, it creates an entry in the `workflowinstancelink`
    database table. This `delete` call ensures there are no orphaned entries in the
    `workflowinstancelinks` table.

5.  To get the `WorkflowInstanceLocalService` injected into your
    `*LocalServiceBaseImpl` so you can call its methods in the
    `LocalServiceImpl`, add this to your entity declaration in `service.xml`:

		<reference entity="WorkflowInstanceLink" package-path="com.liferay.portal" />

For an example of a fully implemented `updateStatus` method, see the
`com.liferay.portlet.blogs.service.impl.BlogsEntryLocalServiceImpl` class in
`portal-impl`.

Save your work and run Service Builder. Once you've accounted for workflow
status in your service layer, there's only one thing left to do: update the user
interface.

## Workflow Status and the View Layer [](id=workflow-status-and-the-view-layer)

If you have an application with database entities, you're likely displaying
them. Once you enable workflow, you should only display approved entities to
your end users.

This involves the following steps:

- Create a *finder* for your entities that accounts for the `status` field in
  your database table.

- Expose the finder in a *getter* method of your service layer.

- Update the view layer to use the new getter for displaying entities (e.g., in
  a Search Container).

1.  If you're using Service Builder, define your finder in your application's
    `service.xml` and let Service Builder generate it for you. 

        <finder name="G_S" return-type="Collection">
            <finder-column name="groupId"></finder-column>
            <finder-column name="status"></finder-column>
        </finder>

2.  Make sure you have a getter in your service layer that uses the new finder.

        public List<FooEntity> getFooEntities(long groupId, int status)
               throws SystemException {
            return fooEntityPersistence.findByG_S(groupId,
                 WorkflowConstants.STATUS_APPROVED);
        }

3.  Finally, update your JSP to use the appropriate getter.

        <liferay-ui:search-container-results
            results="<%=FooEntityLocalServiceUtil.getFooEntities(scopeGroupId,
                      fooEntityId(), Workflowconstants.STATUS_APPROVED, searchContainer.getStart(),
                      searchContainer.getEnd())%>"
            ...

In an *administrative*-type application (in other words, one that's displayed
in the Site Menu's *Content* section) you might want to display all the
entities with their current workflow status (for example, include workflow
status as a column in the search container). To do so, use the
`<aui:worklfow-status>` tag.

        <aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= fooEntity.getStatus() %>" />

<!--UPDATE WHEN UI IS STABILIZED [Figure 1: You can display the workflow status of your entities. This is useful in administrative applications.](../../images/workflow-status-list-view.png) -->

Great! You created one new class, updated your `add` methods, added one new
method in the service layer, and updated your view. Workflow is fully
implemented and ready to use in your Liferay application.
