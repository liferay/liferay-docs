# Creating a Workflow Handler for Guestbooks [](id=creating-a-workflow-handler-for-guestbooks)

<div class="learn-path-step">
    <p>Handling Workflow<br>Step 1 of 2</p>
</div>

Each workflow enabled entity needs a `WorkflowHandler`. Create a new package in
the `guestboook-service` module called `com.liferay.docs.guestbook.workflow`,
then create the `GuestbokWorkflowHandler` class in it. Extend
`BaseWorkflowHandler` and pass in `Guestbook` as the type parameter:

    public class GuestbookWorkflowHandler extends BaseWorkflowHandler<Guestbook> {

Make it a Component class:

    @Component(immediate = true, service = WorkflowHandler.class)

There are three abstract methods to implement: `getClassName`, `getType`, and
`updateStatus`.

        @Override
        public String getClassName() {
            return Guestbook.class.getName();
        }

`getClassName` returns the guestbook entity's fully qualified class name
(`com.liferay.docs.guestbook.model.Guestbook`).

        @Override
        public String getType(Locale locale) {
            return _resourceActions.getModelResource(locale, getClassName());
        }

`getType` returns the model resource name
(`model.resource.com.liferay.docs.guestbook.model.Guestbook`). The meat of the
workflow handler is in the `updateStatus` method: 

        @Override
        public Guestbook updateStatus(
                int status, Map<String, Serializable> workflowContext)
            throws PortalException {

            long userId = GetterUtil.getLong(
                (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
            long resourcePrimKey = GetterUtil.getLong(
                (String)workflowContext.get(
                    WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

            ServiceContext serviceContext = (ServiceContext)workflowContext.get(
                "serviceContext");

            return _guestbookLocalService.updateStatus(
                userId, resourcePrimKey, status, serviceContext);
        }

When you crafted the service layer's `updateStatus` method (see the last section
for more details), you specified parameters that must be passed to the method.
Here you're making sure that those parameters are available to pass to the
service call. Get the `userId` and `resourcePrimKey` from `GetterUtil`. Its
`getLong` method takes a `String`, which you can get from the `workflowContext`
`Map` using `WorkflowConstants` for the context user ID and the context
entry class PK.

Make sure you inject the `ResourceActions` service into a private variable at
the end of the class, using the `@Reference` annotation:

        @Reference(unbind = "-")
        protected void setResourceActions(ResourceActions resourceActions) {

            _resourceActions = resourceActions;
        }

        private ResourceActions _resourceActions;

Inject a `GuestbookLocalService` into a private variable using the `@Reference`
annotation.

        @Reference(unbind = "-")
        protected void setGuestbookLocalService(
            GuestbookLocalService guestbookLocalService) {

            _guestbookLocalService = guestbookLocalService;
        }

        private GuestbookLocalService _guestbookLocalService;

    }

Organize imports (*[CTRL]+[SHIFT]+O*) and save your work.

Now the Guestbook Application updates the database with the necessary status
information, interacting with Liferay's workflow classes to make sure each
entity is properly handled by @product@. At this point you can enable workflow
for the Guestbook inside @product@ and see how it works. Navigate to *Control
Panel &rarr; Workflow Configuration*. The Guestbook entity appears among
@product@'s native entities. Enable the Single Approver Workflow for Guestbooks;
then go to the Guestbook Admin portlet and add a new Guestbook. A notification
appears next to your user name in the product menu. You receive a notification
from the workflow that a task is ready for review. Click it, and you're taken to
the My Workflow Tasks portlet, where you can complete the review task.

![Figure x: Click the workflow notification in the Notifications portlet to review the guestbook submitted to the workflow.](../../../../images/workflow-notification.png)

To complete the review, click the actions button
(![Actions](../../../../images/icon-actions.png)) from My Workflow Tasks and
select *Assign to Me*. Click the actions button again and select *Approve*.

![Figure x: Click the workflow notification in the Notifications portlet to review the guestbook submitted to the workflow.](../../../../images/workflow-assign-to-me.png)

Right now the workflow process for guestbooks is functional, but the UI isn't
adapted for it. You'll write the workflow handler for guestbook entries next,
and then update the UI to account for each entity's workflow status.
