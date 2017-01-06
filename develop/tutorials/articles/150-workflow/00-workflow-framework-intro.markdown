# Liferay's Workflow Framework [](id=liferays-workflow-framework)

Enabling your application's entities to support workflow is so easy, you could do it in
your sleep (but don’t try). Workflow enabled entities require a few things:

-  A workflow handler class to interact with Liferay’s workflow back end and
   the entity’s service layer.

-  Some extra fields in their database table that help keep track of their
   status.

    In most Liferay applications, [Service Builder](/develop/tutorials/-/knowledge_base/6-2/service-builder) will be used to create those fields. 
<!--Update link to 7-0 when ready-->

-  Updates to the service layer. 

    The service layer needs code to populate the new fields when entities are added to the
    database. 

    The service layer needs to send the entity through Liferay’s workflow, and it needs
    to handle the workflow status of the entity when it’s returned by the workflow. 

    The service layer needs getters that return entities of the desired workflow
    status (usually *approved*).

-  The View layer should account for the workflow status of displayed entities.

The code for most Liferay applications spans multiple modules, so where should
you implement the workflow handler? It should go in the module with your service
implementations. It's nice to keep your back end code separate from your view
layer and controller (in the MVC pattern).

## Creating a Workflow Handler [](id=creating-a-workflow-handler)

First create a Component class. It should extend `BaseWorkflowHandler<T>`, an
abstract class that provides a default implementation of the `WorkflowHandler<T>`
service. Pass the interface for your model as the type parameter for the class.

    FooEntity WorkflowHandler extends BaseWorkflowHandler<FooEntity>

Since you're publishing a service to be consumed in the OSGi runtime, your
workflow handler class needs to be registered. If you're using Declarative
Services, make it a Component class, using the `@Component` annotation.

    @Component(
        property = {"model.class.name=com.my.app.package.model.FooEntity"},
        service = WorkflowHandler.class
    )

It needs one property, to set `model.class.name` to the fully qualified class
name of the class you passed as the type parameter. It also needs to declare the
type of service being implemented (`WorkflowHandler.class`). 

What methods do you need to override in your workflow handler? Just three:

    @Override
    public String getClassName() {

    @Override
    public String getType(Locale locale) {

    @Override
    public FooEntity updateStatus(int status, Map<String, Serializable> workflowContext) {

The first two are pretty boilerplate. Most of the heavy lifting is being done in
the `updateStatus` method. It returns a call to a local service method
of the same name, so the status returned from the workflow back end can be
persisted to the entity table in the database. 

The `updateStatus` method should take a user ID, the primary key for the class
(for example, `fooEntityId`), the workflow status, the service context, and the
workflow context. The status and the workflow context can be obtained from the
workflow back end. You'll need to define the rest of the parameters, which can
be obtained from the workflow context.

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
service methods to account for workflow status, and add a new method to update
the status of an entity in the database.

## Updating the Service Layer [](id=updating-the-service-layer)

Make sure your entity database table has `status`, `statusByUserId`,
`statusByUserName`, and `statusDate` fields. If you're using service builder,
add this to your `service.xml` if you haven't already:

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />

Wherever you're setting the other database fields in your persistence code, set
the workflow status as a draft and set the other fields.

    fooEntity.setStatus(WorkflowConstants.STATUS_DRAFT);
    fooEntity.setStatusByUserId(userId);
    fooEntity.setStatusByUserName(user.getFullName());
    fooEntity.setStatusDate(serviceContext.getModifiedDate(null));

With Service Builder driven Liferay applications, this will be in the local service
implementation class (`-LocalServiceImpl`). 

Whenever an entity is added to the database you need to detect whether workflow
is installed and active. If not, you need to automatically mark the entity as
approved so it appears in the UI. If it is, you want to leave it in draft status
and send it to the workflow back end where it can be properly handled.
Thankfully, this whole process is easily done with a single call to
`WorkflowHandlerRegistryUtil.startWorkflowInstance`. There are several methods
of this name which take a different parameter set, so inspect the
`WorkflowHandlerRegistryUtil`
[class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/workflow/WorkflowHandlerRegistryUtil.html)
and decide which is right for your case.

    WorkflowHandlerRegistryUtil.startWorkflowInstance(fooEntity.getCompanyId(),
            fooEntity.getGroupId(), fooEntity.getUserId(), FooEntity.class.getName(),
            fooEntity.getPrimaryKey(), fooEntity, serviceContext);

Once you've set the database fields for workflow status and started the workflow
instance, implement the `updateStatus` method that you need to call in the
workflow handler. The workflow handler gets the entity's status from the workflow
back end and passes it to your service layer, which persists
the updated entity to the database. 

    fooEntity.setStatus(status);
    fooEntity.setStatusByUserId(user.getUserId());
    fooEntity.setStatusByUserName(user.getFullName());
    fooEntity.setStatusDate(serviceContext.getModifiedDate(now));

    fooEntityPersistence.update(fooEntity);

After setting the workflow fields for the entity, think about the specifics of
your situation and whether any additional logic should be added to this method.
For instance, if your entities are Liferay Assets already, you'll want to change
the visibility of the asset depending on its workflow status. You don't want the
Asset Publisher displaying entities that haven't yet been approved in the
workflow process.

    if (status == WorkflowConstants.STATUS_APPROVED) {

        assetEntryLocalService.updateEntry(
            FooEntity.class.getName(), fooEntityId, fooEntity.getDisplayDate(),
            null, true, true);
    }

    else {

        assetEntryLocalService.updateVisible(
            fooEntity.class.getName(), entryId, false);
    }

If approved, the asset is updated, with the publication date, a `listable`
boolean, and a `visible` boolean being updated to reflect the current state of
the asset. If the workflow status is anything other than approved, its
visibility is set to `false`.

For an example of a fully implemented `updateStatus` method, see the
`com.liferay.portlet.blogs.service.impl.BlogsEntryLocalServiceImpl` class in
`portal-impl`.

Once you've accounted for workflow status in your service layer, there's only
one thing left to do: update the user interface.

## Workflow Status and the View Layer [](id=workflow-status-and-the-view-layer)

If you have an application with database entities, you're likely displaying
them. If you're sending entities through a workflow process, you only want
to display approved entities to your end users.

This often involves the following steps:

- Create a *finder* for your entities that accounts for the `status` field in
  your database table.

- Expose the finder in a *getter* method of your service layer.

- Update the view layer to use the new getter for displaying entities (e.g., in
  a Search Container).

If you're using Service Builder, define your finder in your application's
`service.xml` and let Service Builder generate it for you. 

    <finder name="G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>

Then make sure you have a getter in your service layer that uses the new finder.

    public List<FooEntity> getFooEntities(long groupId, int status)
           throws SystemException {
        return fooEntityPersistence.findByG_S(groupId,
             WorkflowConstants.STATUS_APPROVED);
    }

Now all you need to do is update your JSP to use the appropriate getter.

    <liferay-ui:search-container-results
        results="<%=FooEntityLocalServiceUtil.getFooEntities(scopeGroupId,
                  fooEntityId(), Workflowconstants.STATUS_APPROVED, searchContainer.getStart(),
                  searchContainer.getEnd())%>"
        ...

In an *administrative* type application (in other words, one that's displayed in the Site
Menu's *Content* section) you might want to display all the entities, with their
current workflow status (for example, include workflow status as a column in the
search container). To do so, use the `<aui:worklfow-status>` tag.

        <aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= fooEntity.getStatus() %>" />

![Figure 1: You can display the workflow status of your entities. This is useful in administrative applications.](../../images/workflow-status-list-view.png)

You only needed one new class, one new method in the service layer, and some
updates to your view, and workflow is fully implemented and ready to use in your
Liferay application.

<!-- ## Full Code Examples

Here's what an entire, functional workflow handler class might look like:

    package com.liferay.docs.foo.workflow;

    import com.liferay.docs.foo.model.FooEntity;
    import com.liferay.docs.foo.service.FooEntityLocalService;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
    import com.liferay.portal.kernel.service.ServiceContext;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandler;

    import java.io.Serializable;

    import java.util.Locale;
    import java.util.Map;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    @Component(
        property = {"model.class.name=com.liferay.docs.foo.model.FooEntity"},
        service = WorkflowHandler.class
    )
    public class FooEntityWorkflowHandler extends BaseWorkflowHandler<FooEntity> {

        @Override
        public String getClassName() {
            return FooEntity.class.getName();
        }

        @Override
        public String getType(Locale locale) {
            return ResourceActionsUtil.getModelResource(locale, getClassName());
        }

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

        @Reference(unbind = "-")
        protected void setFooEntityLocalService(
            FooEntityLocalService fooEntityLocalService) {

            _fooEntityLocalService = fooEntityLocalService;
        }

        private FooEntityLocalService _fooEntityLocalService;

    }

Here's a fully developed, yet simple, example of an `updateStatus` method that
you might use in your `-LocalServiceImpl`:

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

-->

