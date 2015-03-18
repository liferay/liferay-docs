# Implementing Workflow Handlers

The Guestbook Portlet is well on its way to being enabled for workflow. The
service layer can now set the appropriate database fields. In this section
you'll learn to implement workflow handlers that interact with both the
portal's workflow classes and your service layer (by calling `updateStatus` on the
appropriate entity).

## Creating a Workflow Handler for Guestbook Entries

Create a new package in your `docroot/WEB-INF/src` folder, and call it
`com.liferay.docs.guestbook.workflow`. Create a new class in the package,
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
`EntryLocalServiceUtil`.

## Creating a Workflow Handler for Guestbooks

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

Now the Guestbook App can not only update the database with the necessary
information, but can interact with Liferay's workflow classes to make sure each
entity is properly handled by the portal.

## Registering the Guestbook Portlet's Workflow Handlers

You might think that's all there is to it, but you haven't told Liferay about
your `-WorkflowHandler` classes. Add the following lines to
`docroot/WEB-INF/liferay-portlet.xml`, underneath the `<asset-renderer-factory>`
declaration:

		<workflow-handler>com.liferay.docs.guestbook.workflow.EntryWorkflowHandler</workflow-handler>
	    <workflow-handler>com.liferay.docs.guestbook.workflow.GuestbookWorkflowHandler</workflow-handler>

Now you're almost done. If you test the workflow by adding an Entry to one of
the Guestbooks, your portal's administrative user will receive a notification
for reviewing the submission. However, the entity will still be displayed in the
portlet's search container! Why even bother having a review process if the
entity will be published anyway? Taking workflow status into account while
displaying entities in the Guestbook Portlet is the final task of this learning
path. 
