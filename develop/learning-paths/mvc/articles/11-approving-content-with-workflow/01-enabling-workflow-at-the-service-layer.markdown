# Enabling Workflow at the Service Layer

In the learning path on assets, you learned that asset enabled entities are
added to the AssetEntry table. There's no special table to workflow entities,
but there are some additional database columns in the entity table (e.g.,
`GB_Entry`)  that allow you to keep track of workflow status. The necessary
fields include *status*, *statusByUserName*, *statusByUserId*, and
*statusDate*. Add the following to `docroot/WEB-INF/service.xml`, in the
`Entry` entity section:

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />

Now the `GB_Entry` database table contains the proper fields, but when you add
a Guestbook Entry, you'll see that the new fields are not populated. You need
to modify your service layer to set these fields. 

Open `EntryLocalServiceImpl` and add the following line in the
`addGuestbookEntry` method, immediately following the current setter methods
(e.g., `entry.setMessage(message)`):

    entry.setStatus(WorkflowConstants.STATUS_DRAFT);

This manually sets the status of the workflow as a draft; if you look in your
`GB_Entry` database table after adding an Entry now, you'll see the `status`
field of your Entry with the value `2`. But you still haven't set the rest of
the values.

Still in the `addGuestbookEntry` method, place the following code right before the `return` statement:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(), 
				entry.getGroupId(), entry.getUserId(), Entry.class.getName(), 
				entry.getPrimaryKey(), entry, serviceContext);

This will detect whether workflow is installed and enabled. If it isn't, the
added entity is automatically marked as approved. The `startWorkflowInstance`
will call your custom `EntryWorkflowHandler` class,  which you'll create later
in this learning path. The service layer needs to be given the ability to
update the workfflow status fields you added to `service.xml`. For this
purpose, add the following method to the bottom of `EntryLocalServiceImpl`:

     public Entry updateStatus(long userId, long guestbookId, long entryId, int status,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		User user = userLocalService.getUser(userId);
		Entry entry = getEntry(entryId);

		entry.setStatus(status);
		entry.setStatusByUserId(userId);
		entry.setStatusByUserName(user.getFullName());
		entry.setStatusDate(new Date());

        String name = entry.getName();
		String email = entry.getEmail();
		String message = entry.getMessage();

		entryPersistence.update(entry);

		if (status == WorkflowConstants.STATUS_APPROVED) {

			assetEntryLocalService.updateVisible(Entry.class.getName(),
					entryId, true);

		} else {

			assetEntryLocalService.updateVisible(Entry.class.getName(),
					entryId, false);
		}

		return entry;
	}

Make sure you run service builder.

The `updateStatus` method is responsible for setting the status fields, then
persisting the information to the database. The `if` block checks the workflow
status of the entity and does on of two things:

- If the entity has an approved status, it is marked as visible in the 


First create a new package in your `docroot/WEB-INF/src` folder, and call it
`com.liferay.docs.guestbook.workflow`. Create a new class inside this apckage,
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

The most important thing to note is that this class calls `updateStatus` from
`EntryLocalServiceUtil` in the return statement. That method doesn't exist yet;
you'll create it next.

Back in your `EntryLocalServiceImpl` class, add the following method to the end
of the class:


You might think that's all there is to it, but you haven't told Liferay about
your `EntryWorkflowHandler` yet. Add the following to
`docroot/WEB-INF/liferay-portlet.xml`, underneath your asset renderer factory
declaration:

    <workflow-handler>com.liferay.docs.guestbook.workflow.EntryWorkflowHandler</workflow-handler>

Now you're almost done. If you test the workflow by adding an Entry, your
portal's administrative user will receive a notification for reviewing the
submission. However, the entity will still be displayed in the portlet's search
container. Why even bother having a review process if the entity will be
published anyway? To fix this, you need to add a new `finder` that takes
workflow status into account. Service Builder makes this easy.

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

