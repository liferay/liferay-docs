---
header-id: liferays-workflow-framework
---

# Liferay's Workflow Framework

[TOC levels=1-4]

To workflow-enable your entities,

1. Create a Workflow Handler
2. Update the Service Layer
3. Update the User Interface

Time to get started.

## Creating a Workflow Handler

If you're in a Service Builder application, the workflow handler goes in your
`-service` module.

1.  Create a Component class that extends `BaseWorkflowHandler<T>`.

    ```java
    @Component(immediate = true, service = WorkflowHandler.class)
    public class FooEntityWorkflowHandler extends BaseWorkflowHandler<FooEntity>
    ```

2.  Override three methods in the workflow handler.

    ```java
    @Override
    public String getClassName() {
        return FooEntity.class.getName();
    }

    @Override
    public String getType(Locale locale) {
        return ResourceActionsUtil.getModelResource(locale, getClassName());
    }

    @Override
    public FooEntity updateStatus(int status, Map<String, Serializable> workflowContext) throws PortalException {
    ... }
    ```

    Most of the heavy lifting is in the `updateStatus` method. It returns
    a call to a local service method of the same name (for example,
    `FooEntityLocalService.updateStatus`), so the status returned from the workflow
    back-end can be persisted to the entity table in the database.

    The `updateStatus` method needs a user ID, the primary key for the
    class (for example, `fooEntityId`), the workflow status, the service
    context, and the workflow context. The status and the workflow context can
    be obtained from the workflow back-end. The other parameters can be
    obtained from the workflow context. Here's an example `updateStatus` method:

    ```java
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
            WorkflowConstants.CONTEXT_SERVICE_CONTEXT);

        return _fooEntityLocalService.updateStatus(
            userId, classPK, status, serviceContext);
    }
    ```

Now your entity can be handled by Liferay's workflow framework. Next, update
the service methods to account for workflow status and add a new method to
update the status of an entity in the database.

## Updating the Service Layer

In most Liferay applications,
[Service Builder](/docs/7-2/frameworks/-/knowledge_base/f/service-builder)
is used to create database fields. First, you must update the service layer:

1.  Make sure your entity database table has `status`, `statusByUserId`,
    `statusByUserName`, and `statusDate` fields.

    ```xml
    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />
    ```

2.  Wherever you're setting the other database fields in your persistence code,
    set the workflow status as a draft and set the other fields.

    ```java
    fooEntity.setStatus(WorkflowConstants.STATUS_DRAFT);
    fooEntity.setStatusByUserId(userId);
    fooEntity.setStatusByUserName(user.getFullName());
    fooEntity.setStatusDate(serviceContext.getModifiedDate(null));
    ```

    With Service Builder driven Liferay applications, this is in the local service
    implementation class (`-LocalServiceImpl`).


3.  At the end of any method that adds a new entity to your database, call the
    workflow service to enable sending the entity into the workflow backend:

    ```java
    WorkflowHandlerRegistryUtil.startWorkflowInstance(
        fooEntity.getCompanyId(), fooEntity.getGroupId(), fooEntity.getUserId(),
        FooEntity.class.getName(), fooEntity.getPrimaryKey(), fooEntity,
        serviceContext);
    ```

4.  Implement the `updateStatus` method that must be called in the workflow
    handler. In the end, persist the updated entity to the database.

    ```java
    fooEntity.setStatus(status);
    fooEntity.setStatusByUserId(user.getUserId());
    fooEntity.setStatusByUserName(user.getFullName());
    fooEntity.setStatusDate(serviceContext.getModifiedDate(now));

    fooEntityPersistence.update(fooEntity);
    ```

5.  Do anything else that might make sense here, like
    changing the visibility of the asset depending on its workflow status:

    ```java
    if (status == WorkflowConstants.STATUS_APPROVED) {
        assetEntryLocalService.updateVisible(
            FooEntity.class.getName(), fooEntityId, true);
    }
    else {
        assetEntryLocalService.updateVisible(
            FooEntity.class.getName(), fooEntityId, false);
    }
    ```

    Here's what a full `updateStatus` method might look like:

    ```java
    @Indexable(type = IndexableType.REINDEX)
    public FooEntity updateStatus(
        long userId, long fooEntityId, int status, ServiceContext serviceContext
    ) throws PortalException, SystemException {

    		User user = userLocalService.getUser(userId);
    		FooEntity fooEntity = getFooEntity(fooEntityId);

    		fooEntity.setStatus(status);
    		fooEntity.setStatusByUserId(userId);
    		fooEntity.setStatusByUserName(user.getFullName());
    		fooEntity.setStatusDate(new Date());

    		fooEntityPersistence.update(fooEntity);

    		if (status == WorkflowConstants.STATUS_APPROVED) {
    			assetEntryLocalService.updateVisible(
              FooEntity.class.getName(), fooEntityId, true);
    		}
        else {
    			assetEntryLocalService.updateVisible(
              FooEntity.class.getName(), fooEntityId, false);
    		}

    		return fooEntity;
	  }
    ```
6.  Add a call to `deleteWorkflowInstanceLinks` in the `deleteEntity` method:

    ```java
    workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
        fooEntity.getCompanyId(), fooEntity.getGroupId(),
        FooEntity.class.getName(), fooEntity.getFooEntityId());
    ```

    To get the `WorkflowInstanceLocalService` injected into your
    `*LocalServiceBaseImpl` so you can call its methods in the
    `LocalServiceImpl`, add this to your entity declaration in `service.xml`:

    ```xml
    <reference entity="WorkflowInstanceLink" package-path="com.liferay.portal" />
    ```

For an example of a fully implemented `updateStatus` method, see the
`com.liferay.portlet.blogs.service.impl.BlogsEntryLocalServiceImpl` class in
`portal-impl`.
<!-- Check validity for 7.2-->

Once you've accounted for workflow status in your database and service layer,
there's only one thing left to do: update the user interface.

## Workflow Status and the View Layer

This is dependent on the needs of your application, but often involves the
following these steps:

**Display only approved entities:**

1.  If you're using Service Builder, define your finder in your application's
    `service.xml` and let Service Builder generate it for you.

    ```xml
    <finder name="G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>
    ```

2.  Make sure you have a getter in your service layer that uses the new finder.

    ```java
    public List<FooEntity> getFooEntities(long groupId, int status)
        throws SystemException {

        return fooEntityPersistence.findByG_S(
            groupId, WorkflowConstants.STATUS_APPROVED);
    }
    ```

3.  Finally, update your JSP to use the appropriate getter.

    ```jsp
    <liferay-ui:search-container-results
        results="<%= FooEntityLocalServiceUtil.getFooEntities(
            scopeGroupId, fooEntityId(), Workflowconstants.STATUS_APPROVED,
            searchContainer.getStart(), searchContainer.getEnd()) %>"
    />
        ...
    ```

**Display the workflow status:**

When you want to display the workflow status, use the `<aui:worklfow-status>`
tag.

```jsp
<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= fooEntity.getStatus() %>" />
```

Once your user interface accounts for workflow, your Liferay application is
fully workflow enabled.
