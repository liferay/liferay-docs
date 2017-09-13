## Creating a Workflow Handler for Guestbook Entries 

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
            return ResourceActionsUtil.getModelResource(locale, getClassName());
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

