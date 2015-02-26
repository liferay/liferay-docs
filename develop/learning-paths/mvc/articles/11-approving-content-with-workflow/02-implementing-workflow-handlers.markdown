# Implementing Workflow Handlers

The Guestbook Portlet is well on its way to being enabled for workflow. The
service layer can now set the appropriate database fields. You'll  learn to
implement workflow handlers in this section.

## Creating `EntryWorkflowHandler`

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

Your workflow handler class extends the abstract class `BaseWorkflowHandler`,
which implements the `WorkflowHandler` interface. The methods included in the
workflow handler are methods with no implementation in `BaseWorkflowHandler`.
The most important thing to note is that this class calls `updateStatus` from
`EntryLocalServiceUtil`.

## Creating `GuestbookWorkflowHandler`

<!-- Do the same thing -->


## Registering the Guestbook Portlet's Workflow Handlers

You might think that's all there is to it, but you haven't told Liferay about
your `-WorkflowHandler` classes. Add the following to
`docroot/WEB-INF/liferay-portlet.xml`, underneath your asset renderer factory
declaration:

    <workflow-handler>com.liferay.docs.guestbook.workflow.EntryWorkflowHandler</workflow-handler>

Now you're almost done. If you test the workflow by adding an Entry, your
portal's administrative user will receive a notification for reviewing the
submission. However, the entity will still be displayed in the portlet's search
container! Why even bother having a review process if the entity will be
published anyway? Taking workflow status into account while displaying entities
in the Guestbook Portlet is the final task of this learning path. 

To fix this, you'll add a new `finder` that takes workflow
status into account. Service Builder makes this easy.

Open `service.xml`. In the `Entry` entity, replace the current finder tag with
the following:

    <finder  name="G_G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="status"></finder-column>
        <finder-column name="guestbookId"></finder-column>    
    </finder>

After you run service builder, you'll have a finder that allows you to retrieve
`Entry` objects from the database based on their 
[Group Id`](participate/liferaypedia/-/wiki/Main/Group+ID), workflow status,
and `guestbookId`. Now you can use the finder in your service layer and your
JSPs to make sure that any entries that appear in the Guestbook portlet have
been approved in the workflow (if workflow is enabled).

Open `EntryLocalServiceImpl` and replace the current *getter* methods with the
following:

<!--Once the Friendly URL learning path is merged, I will have the Entry entity in the rquest to be able to get easily from the request attribute in the JSP, so that entry.getStatus can be called for the new getter methods status parameter-->










Make setStatus(WorkflowRegistryUtilHandler.re of references to AssetEntry and AssetLink (should be there since
already asset-enabled): 
    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
	<reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

