# Workflow Enabling Entities [](id=workflow-enabling-entities)

Enabling your app's entities to support workflow is so easy, you could do it in
your sleep (but don't try). Workflow enabled (Workflown? Workflowed?
Workflowized?) entities require a workflow handler class to interact with
Liferay's workflow back end and the entity's service layer. They also have some
extra fields in their database table that help keep track of their status.
Service Builder is used to create those fields. After that, the service layer
needs updating. It needs code to populate the new fields when entities are added
to the database, it needs to send the entity through Liferay's workflow, and it
needs to handle the workflow status of the entity when it's returned by the
workflow. 

## Creating a Workflow Handler [](id=creating-a-workflow-handler)

Workflow handlers are your application's way of interacting with the workflow
back end. They must implement the `WorkflowHandler` interface.  Liferay's
abstract class, `BaseWorkflowHandler`, provides a base implementation you can
leverage. Extend this class and implement only the methods not included in
`BaseWorkflowHandler`:

    @Override
    public String getClassName() {

    @Override
    public String getType(Locale locale) {

    @Override
    public Object updateStatus(int status,
            Map<String, Serializable> workflowContext) throws PortalException,
            SystemException {


It’s most common to create the workflow handler class in a `workflow` package in
your portlet’s `docroot/WEB-INF/src` folder, something like this:

    com.liferay.docs.foo.workflow

In this example, *foo* is the portlet name.

Here's how an entire Workflow handler class (minus imports) might look:


    package com.liferay.docs.foo.workflow;

    ...

    public class FooEntityWorkflowHandler extends BaseWorkflowHandler {

        public static final String CLASS_NAME = FooEntity.class.getName();

        @Override
        public String getClassName() {

            return CLASS_NAME;
        }

        @Override
        public String getType(Locale locale) {
            return LanguageUtil.get(locale,  "model.resource" + CLASS_NAME);
        }

        @Override
        public Object updateStatus(int status,
                Map<String, Serializable> workflowContext) throws PortalException,
                SystemException {

                long userId = GetterUtil.getLong(
                    (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
                long fooEntityId = GetterUtil.getLong(
                    (String)workflowContext.get(
                        WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

                ServiceContext serviceContext = (ServiceContext)workflowContext.get(
                    "serviceContext");

                return FooEntityLocalServiceUtil.updateStatus(
                    userId, fooEntityId, status, serviceContext);

        }
    }

All the interesting work here is done in the `updateStatus` method. It returns a
call to the local service utility's method of the same name, so the status
returned from the workflow back end can be persisted to the entity table in the
database. 

The workflow handler needs to be registered with Liferay. This is done by adding
the `<workflow-handler>` tag to `liferay-portlet.xml`. This tag must contain the
fully qualified class name for a class that implements the `WorkflowHandler
interface` Refer to the
[DTD](https://docs.liferay.com/portal/6.2/definitions/liferay-portlet-app_6_2_0.dtd.html#workflow-handler)
for the proper placement of the `<workflow-handler>` tag. If you have a
`<trash-handler>` declared, the workflow handler declaration goes right
after it.

        <workflow-handler>com.liferay.docs.foo.workflow.FooEntityWorkflowHandler</workflow-handler>

To fully implement workflow, the service layer also needs updating.

## Updating the Service Layer for Workflow [](id=updating-the-service-layer-for-workflow)

There are four database fields your entity should include for workflow
purposes. Add them to each entity’s `service.xml` file:

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />

Run Service Builder, and each entity now has these fields, though there’s no
code for populating them. That will come later. 

An entity's service implementation class (`-LocalServiceImpl`)  contains
persistence code. To the `addFooEntity` method, add code to set the initial
workflow status as a *draft*, wherever you're now setting the other database
fields (your *setter* method calls, such as
`fooEntity.setUuid(serviceContext.getUuid());`):

    fooEntity.setStatus(WorkflowConstants.STATUS_DRAFT);

Also in the `addFooEntity` method, a call to the `startWorkflowInstance` 
method of
[WorkflowHandlerRegistryUtil](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/workflow/WorkflowHandlerRegistryUtil.html)
must be made:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(),
                    entry.getGroupId(), entry.getUserId(), Entry.class.getName(),
                    entry.getPrimaryKey(), entry, serviceContext);

The `startWorkflowInstance` call goes directly before the `return` statement.

The service layer should contain all calls to your persistence code. The
workflow handler is going to get the workflow status back from the workflow back
end, then pass it to your service layer to persist it to the database. 

Create an `updateStatus` method in the entity's `-LocalServiceImpl` class:

    public FooEntity updateStatus(long userId, long fooEntityId, int status,
           ServiceContext serviceContext) throws PortalException,
           SystemException {

        User user = userLocalService.getUser(userId);
        FooEntity fooEntity = getFooEntity(fooEntityId);

        fooEntity.setStatus(status);
        fooEntity.setStatusByUserId(userId);
        fooEntity.setStatusByUserName(user.getFullName());
        fooEntity.setStatusDate(new Date());

        entryPersistence.update(entry);

        if (status == WorkflowConstants.STATUS_APPROVED) {

           assetEntryLocalService.updateVisible(FooEntity.class.getName(),
              fooEntityId, true);

        } else {

           assetEntryLocalService.updateVisible(FooEntity.class.getName(),
              fooEntity, false);
        }

        return fooEntity;
    }

This method is where the workflow fields are set, then persisted in the
database with a call to the persistence layer's `update` method:

    fooEntity.setStatus(status);
    fooEntity.setStatusByUserId(userId);
    fooEntity.setStatusByUserName(user.getFullName());
    fooEntity.setStatusDate(new Date());

    entryPersistence.update(entry);

When the workflow `status` was set initially (in `addFooEntity`, if you're into
fake code), it was just to get the initial status set as a *draft* for the
workflow. Once the entity is run through the workflow process the status is
returned and these fields can be properly set.

Aside from setting the database fields, the *if-else* block of `updateStatus` is
worth noting. You can see the `update` method is called before the if statement.
The work is done as far as the entity database is concerned. If your entity is
asset enabled, however, you might want to stop it from appearing in the Asset
Publisher unless it's approved in the workflow. The `AssetEntry` table has a
field called `visible`, which marks each asset as visible, or invisible. With
the `if` statement above, the display of asset enabled entities in the Asset
Publisher accounts for workflow status.

After following these steps, an entity is fully workflow enabled. Your view
layer, however, likely requires some work to make sure that only approved
entities are presented to the app's users.

## Updating the View Layer [](id=updating-the-view-layer)

Make sure that you account for the workflow status in your app's UI. This
involves creating a new *finder* method that accounts for workflow status,
exposing the finder by creating a *getter* in the local service implementation,
then calling the new getter method in the view layer, populating the UI with
approved entities.

    public List<FooEntity> getFooEntities(long groupId, int status)
			throws SystemException {
		return fooEntityPersistence.findByG_S(groupId,
				WorkflowConstants.STATUS_APPROVED);
	}

For an example of using service Builder to create a finder that takes workflow status into account, check out the appropriate section of the [Learning Path on workflow](/develop/tutorials/-/knowledge_base/6-2/displaying-approved-workflow-items).

If your app includes an *admin* portlet in Liferay's Site Administration
console, consider displaying all entities, regardless of workflow status. Many
of Liferay's portlets take this approach, displaying the entities in a 
[Search Container](https://docs.liferay.com/portal/6.2/taglibs/liferay-ui/search-container.html) and including the workflow status as a column.

![Figure 1: The Message Boards Admin portlet displays the workflow status of its entities.](../../images/message-boards-admin.png)

To make your admin portlet's *Status* field look like Liferay's core portlets,
use the AlloyUI tag
[`<aui:workflow-status>`](https://docs.liferay.com/portal/6.2/taglibs/aui/workflow-status.html)
in your JSP.

        <liferay-ui:search-container-column-text name="status" >
            <aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>"
                status="<%= fooEntity.getStatus() %>" />
        </liferay-ui:search-container-column-text>

Now you know how to workflow enable any entities you create. Keep exploring
Liferay's workflow capabilities in this tutorial, by learning to develop
your own workflow definitions, and use scripting to make them more robust.

##  Related Topics [](id=related-topics)

[Approving Content with Workflow](/develop/tutorials/-/knowledge_base/6-2/displaying-approved-workflow-items)

[Asset Framework](/develop/tutorials/-/knowledge_base/6-2/asset-framework)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

