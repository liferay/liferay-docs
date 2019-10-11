---
header-id: creating-a-workflow-handler-for-guestbook-entries
---

# Creating a Workflow Handler for Guestbook Entries

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Handling Workflow<br>Step 2 of 2</p>
</div>

The Guestbook entry's workflow handler is almost identical to the guestbook's.

1.  Create a new class in the `com.liferay.docs.guestbook.workflow` package of
    the `guestbook-service` module. Name it `GuestbookEntryWorkflowHandler` and
    extend `BaseWorkflowHandler`. Paste this in as the class body:

    ```java
    @Component(immediate = true, service = WorkflowHandler.class)
    public class GuestbookEntryWorkflowHandler extends BaseWorkflowHandler<GuestbookEntry> {

        @Override
        public String getClassName() {

            return GuestbookEntry.class.getName();

        }

        @Override
        public String getType(Locale locale) {

            return _resourceActions.getModelResource(locale, getClassName());

        }

        @Override
        public GuestbookEntry updateStatus(
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
                _guestbookEntryLocalService.getGuestbookEntry(resourcePrimKey).getGuestbookId();
            
            return _guestbookEntryLocalService.updateStatus(
                userId, guestbookId, resourcePrimKey, status, serviceContext);
        }

        @Reference(unbind = "-")
        protected void setGuestbookEntryLocalService(GuestbookEntryLocalService guestbookEntryLocalService) {

            _guestbookEntryLocalService = guestbookEntryLocalService;
        }

        @Reference(unbind = "-")
        protected void setResourceActions(ResourceActions resourceActions) {

            _resourceActions = resourceActions;
        }

        private GuestbookEntryLocalService _guestbookEntryLocalService;
        private ResourceActions _resourceActions;
    }
    ```

    There is nothing unique about this code as compared with the guestbook's
    workflow handler, except that we need the `gustbookId` for the entry. That's
    easily obtained by getting the `GuestbookEntry` object with
    `guestbookEntryLocalService`, then getting its `guestbookId`. See the last
    article for the rest of the handler's implementation details.

2.  Organize imports with *CTRL+SHIFT+O* and save the file.

The back-end of the guestbook project is fully workflow enabled. All that's left
is to update the Guestbook Application's UI to handle workflow status. 
