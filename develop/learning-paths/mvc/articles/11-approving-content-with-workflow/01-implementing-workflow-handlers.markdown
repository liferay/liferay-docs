# Implementing Workflow Handlers [](id=implementing-workflow-handlers)

In this section you'll learn to implement workflow handlers. Workflow handlers
are fairly simple classes that interact with both the portal's workflow classes
and your service layer (by calling `updateStatus` on the appropriate entity).

## Creating a Workflow Handler for Guestbook Entries [](id=creating-a-workflow-handler-for-guestbook-entries)

Create a new package in your `docroot/WEB-INF/src` folder and call it
`com.liferay.docs.guestbook.workflow`. Create a new class in the package
called `EntryWorkflowHandler`. Populate it with the following code:

    public class EntryWorkflowHandler extends BaseWorkflowHandler {

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
                long entryId = GetterUtil.getLong(
                    (String)workflowContext.get(
                        WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

                ServiceContext serviceContext = (ServiceContext)workflowContext.get(
                    "serviceContext");

                return EntryLocalServiceUtil.updateStatus(
                    userId, entryId, status, serviceContext);

        }

        public static final String CLASS_NAME = Entry.class.getName();

    }

Organize imports:

    import java.io.Serializable;
    import java.util.Locale;
    import java.util.Map;

    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.language.LanguageUtil;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.service.ServiceContext;

Your workflow handler class extends the abstract class `BaseWorkflowHandler`,
which implements the `WorkflowHandler` interface. The methods included in the
workflow handler are methods with no implementation in `BaseWorkflowHandler`.
The most important thing to note is that this class calls `updateStatus` from
`EntryLocalServiceUtil`. You'll add that method later.

## Creating a Workflow Handler for Guestbooks [](id=creating-a-workflow-handler-for-guestbooks)

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
            return LanguageUtil.get(locale,  "model.resource" + CLASS_NAME);
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

## Registering the Guestbook Portlet's Workflow Handlers [](id=registering-the-guestbook-portlets-workflow-handlers)

Now that you have new `-WorkflowHandler`s, you need to register them with
Liferay. Add the following lines to `docroot/WEB-INF/liferay-portlet.xml`,
underneath the `<asset-renderer-factory>` declaration:

		<workflow-handler>com.liferay.docs.guestbook.workflow.EntryWorkflowHandler</workflow-handler>
	    <workflow-handler>com.liferay.docs.guestbook.workflow.GuestbookWorkflowHandler</workflow-handler>

Both the Guestbook App's entities are now prepared to be handled by the portal's
workflow. To make this work, the service layer still needs some updating to set
the initial workflow status, send the entity through the portal's workflow (if
any), and persist the status to the database after it's returned from the
workflow process.
