# Creating a Workflow Handler for Guestbook Entries [](id=creating-a-workflow-handler-for-guestbook-entries)

<div class="learn-path-step">
    <p>Handling Workflow<br>Step 2 of 2</p>
</div>

The entry's workflow handler is almost identical to the guestbook's. Create
a new class in the `com.liferay.docs.guestbook.workflow` package of the
`guestbook-service` module. Name it `EntryWorkflowHandler` and extend
`BaseWorkflowHandler`. Decorate it with a Component annotation and implement the
same three methods you implemented in the `GuestbookWorkflowHandler`. Paste this
in as the class body:

    @Component(immediate = true, service = WorkflowHandler.class)
    public class EntryWorkflowHandler extends BaseWorkflowHandler<Entry> {

        @Override
        public String getClassName() {

            return Entry.class.getName();

        }

        @Override
        public String getType(Locale locale) {

            return _resourceActions.getModelResource(locale, getClassName());

        }

        @Override
        public Entry updateStatus(
            int status, Map<String, Serializable> workflowContext)
            throws PortalException {

            long userId = GetterUtil.getLong(
                (String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
            long resourcePrimKey = GetterUtil.getLong(
                (String) workflowContext.get(
                    WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

            ServiceContext serviceContext =
                (ServiceContext) workflowContext.get("serviceContext");

            long guestbookId =
                _entryLocalService.getEntry(resourcePrimKey).getGuestbookId();
            
            return _entryLocalService.updateStatus(
                userId, guestbookId, resourcePrimKey, status, serviceContext);
        }

        @Reference(unbind = "-")
        protected void setEntryLocalService(EntryLocalService entryLocalService) {

            _entryLocalService = entryLocalService;
        }

        @Reference(unbind = "-")
        protected void setResourceActions(ResourceActions resourceActions) {

            _resourceActions = resourceActions;
        }

        private EntryLocalService _entryLocalService;
        private ResourceActions _resourceActions;
    }

There is nothing unique about this code as compared with the guestbook's
workflow handler, except that we need the `gustbookId` for the entry. That's
easily obtained by getting the `Entry` object with `entryLocalService`, then
getting its `guestbookId`. See the last article for the rest of the handler's
implementation details.

Organize imports with *CTRL+SHIFT+O* and save the file.

The back-end of the guestbook project is fully workflow enabled. All that's left
is to update the Guestbook Application's UI to handle workflow status. 
