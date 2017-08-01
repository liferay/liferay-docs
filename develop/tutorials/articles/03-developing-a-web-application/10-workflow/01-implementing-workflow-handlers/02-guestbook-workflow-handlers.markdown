# Creating a Workflow Handler for Guestbooks 

Each workflow enabled entity needs a `WorkflowHandler`. Create
`GuestbokWorkflowHandler` in `com.liferay.docs.guestbook.workflow`. Populate it
with the following code:

    public class GuestbookWorkflowHandler  extends BaseWorkflowHandler {

        @Override
        public String getClassName() {

            return CLASS_NAME;
        }

        @Override
        public String getType(Locale locale) {
            return ResourceActionsUtil.getModelResource(locale, getClassName());
        }

        @Override
        public Object updateStatus(int status,
                Map<String, Serializable> workflowContext) throws PortalException,
                SystemException {

                long userId = GetterUtil.getLong(
                    (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
                long guestbookId = GetterUtil.getLong(
                    (String)workflowContext.get(
                        WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
                
                ServiceContext serviceContext = (ServiceContext)workflowContext.get(
                    "serviceContext");

                return GuestbookLocalServiceUtil.updateStatus(
                    userId, guestbookId, status, serviceContext);

        }

        public static final String CLASS_NAME = Guestbook.class.getName();

    }

Now the Guestbook App not only can update the database with the necessary
information, but also can interact with Liferay's workflow classes to make sure
each entity is properly handled by the portal. The `updateStatus` method called
in this class must be added to your local service implementation. You'll do that
in the next section of the Learning Path.

