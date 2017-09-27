# Creating a Workflow Handler for Guestbooks 

Each workflow enabled entity needs a `WorkflowHandler`. Create a new package in
the `guestboook-service` module called `com.liferay.docs/guestbook.workflow`.
Then create the `GuestbokWorkflowHandler` class in it. Make it a Component
class:

    @Component(immediate = true, service = WorkflowHandler.class)

Extend `BaseWorkflowHandler` and pass in `Guestbook` as the type parameter:

    public class GuestbookWorkflowHandler  extends BaseWorkflowHandler {

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
(`model.resource.com.liferay.docs.guestbook.model.Guestbook`) <!--WHAT DO THESE
METHODS DO? --> Make sure you inject the `ResourceActions` service into a local
variable at the end of the class, using the `@Reference` annotation:

        @Reference(unbind = "-")
        protected void setResourceActions(ResourceActions resourceActions) {

            _resourceActions = resourceActions;
        }
        private ResourceActions _resourceActions;

The meat of the workflow handler is in the `updateStatus` method: 

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
Now you need to make sure that those parameters are available to pass to the
service call. Here's the method signature from `GuestbookLocalServiceImpl`:

	public Guestbook updateStatus(long userId, long guestbookId, int status,
		ServiceContext serviceContext)

In the workflow handler get the `long`s, `userId` and `resourcePrimKey`, from
`GetterUtil`. Its `getLong` method takes a `String` parameter. Get the context
user ID and context entry class PK from teh `workflowContext` `Map`, cast them
to `String`s, and use `GetterUtil.getLong` to cast them to `long`s. <!-- HUH?
-->

Inject a `GuestbookLocalService` into a local variable, using the `@Reference`
annotation.

        @Reference(unbind = "-")
        protected void setGuestbookLocalService(
            GuestbookLocalService guestbookLocalService) {

            _guestbookLocalService = guestbookLocalService;
        }

        private GuestbookLocalService _guestbookLocalService;

    }

Now the Guestbook Application is updating the database with the necessary
status information and interacting with Liferay's workflow classes to make sure
each entity is properly handled by the portal. At this point you can enable
workflow for the Guestbook inside @product@ and see how it works. Navigate tot
he *Control Panel &rarr; Workflow Configuration*. The Guestbook entity is listed
alongside @product@'s native entities. Enable the Single Approver Workflow for
Guestbooks, then go to the Guestbook Admin portlet and add a new Guestbook.
You'll see a notification appear. You receive a notification from the workflow
that a task is ready for review. Click it and you're taken to the My Workflow
Tasks portlet, where you can complete the review task.

Right now the workflow process is functional, but the UI isn't adapted for it.
First write the workflow handler for guestbook entries, then update the UI to
account for each entity's workflow status.
