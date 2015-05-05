# Workflow Enabling Entities

Workflow enabled (Workflown? Workflowed? Workflowized?) entities have some
extra fields in their database table that help keep track of their status.
Service Builder is used to create those fields. After that, the service layer
needs updating. It needs code to populate the new fields when entities are
added to the database, it needs to send the entity through liferay's workflow,
and it needs to handle the workflow status of the entity when it's returned by
the workflow. After the service layer updates, a workflow handler class is
needed to interact with Liferay's workflow, implementing the proper interface
and delegating persistence code to the service layer.

## Updating the Service Layer for Workflow

There are four database fields your entity should include for workflow
purposes. Add them to each entity’s `service.xml` file:

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />


Run Service Builder. Now each entity has these fields. Right now, there’s no
code for populating these fields. That will come later. 

An entity's service implementation class (`-LocalServiceImpl`)  contains
persistence code. To the `addFooEntity` method, add code to set the initial
workflow status as a *draft*, wherever you're now setting the other database
fields (your *setter* method calls, like
`fooEntity.setUuid(serviceContext.getUuid());`):

    fooEntity.setStatus(WorkflowConstants.STATUS_DRAFT);

Also in the `addFooEntity` method, make a call to the `startWorkflowInstance` 
method of [WorkflowHandlerRegistryUtil](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/workflow/WorkflowHandlerRegistryUtil.html):

    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(),
                    entry.getGroupId(), entry.getUserId(), Entry.class.getName(),
                    entry.getPrimaryKey(), entry, serviceContext);

Place the `startWorkflowInstance` call directly before the `return` statement.

The service layer needs another update, to follow best practices. Since your
persistence code should live in the service layer, the workflow handler is going
to get the workflow status back from the workflow back end, then pass it to your
service layer to manage the persistence code. 

Create an `updateStatus` method in `FooEntityLocalServiceImpl`:

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

This is really where the workflow fields are set, to be persisted in the
database:

    fooEntity.setStatus(status);
    fooEntity.setStatusByUserId(userId);
    fooEntity.setStatusByUserName(user.getFullName());
    fooEntity.setStatusDate(new Date());

When status was set in the `addFooEntity` method, it was just to get the initial
status set as a *draft* for the workflow. Once the entity is run through the
workflow process the status is returned and these fields can be properly set.

We don't have a critical class, the `FooEntityWorkflowHandler`.

## Creating a Workflow Handler

Workflow handler's are your application's way of interacting with the workflow
back end. They must implement the `WorkflowHandler` interface. However,
Liferay's abstract class, `BaseWorfklowHandler`, provides a base implementation for
all the boilerplate code. Extend this class and implement only the necessary
methods:


    @Override
    public String getClassName() {

    @Override
    public String getType(Locale locale) {

    @Override
    public Object updateStatus(int status,
            Map<String, Serializable> workflowContext) throws PortalException,
            SystemException {





    package com.liferay.docs.foo.workflow;

    import java.io.Serializable;
    ...

    public class FooEntityWorkflowHandler extends BaseWorkflowHandler {

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

    public static final String CLASS_NAME = FooEntity.class.getName();

    }


Next, create a
workflow handler class for each entity to be workflown, workflowed,
workflow-enabled, or hower you want to put it.  Creating a Workflow Handler

It’s most common to create the workflow handler class in a workflow package, so create a package in your portlet’s docroot/WEB-INF/src folder, something like this:

com.liferay.docs.foo.workflow

In this example, foo is the portlet name.

In the new package, create a new class, FooEntityWorkflowHandler (pro tip: replace FooEntity with your actual entity name). Make it extend com.liferay.portal.kernel.workflow.BaseWorfklowHandler
