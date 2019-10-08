---
header-id: retrieving-guestbooks-and-entries-by-status
---

# Retrieving Guestbooks and Entries by Status

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Supporting Workflow at the Service Layer<br>Step 3 of 3</p>
</div>

The service implementation for both entities now supports adding the status
fields to the database tables. There's one more update to make in the service
layer, but to understand why, you must think about the view layer. When the
Guestbook portlet displays entries, you must make sure it doesn't show entries
that haven't been approved. Currently, the entry's view layer shows all
guestbooks:

		List<Guestbook> guestbooks = GuestbookLocalServiceUtil
					.getGuestbooks(scopeGroupId);

There's a problem: the getter only takes the `scopeGroupId` as a parameter, so
there's no way to get guestbooks by their status.

Likewise, unapproved entries must not be displayed, but the view layer currently
gets all entries:

        <liferay-ui:search-container total="<%=GuestbookEntryLocalServiceUtil.getGuestbookEntriesCount()%>">
        <liferay-ui:search-container-results
            results="<%=GuestbookEntryLocalServiceUtil.getGuestbookEntries(scopeGroupId.longValue(),
                            guestbookId, searchContainer.getStart(),
                            searchContainer.getEnd())%>" />

The solution is to implement for guestbooks and entries a getter that takes the
`status` field as a parameter. Thankfully, Service Builder makes it easy.

Open the `guestbook-service` module's `service.xml` file. 

1. For the `GuestbookEntry` entity, remove the following finder:
    
        <finder name="G_S" return-type="Collection">
          <finder-column name="groupId" />
          <finder-column name="status" />
        </finder>

2. Add this finder in its place:

   ```xml
   <finder name="G_G_S" return-type="Collection">
      <finder-column name="groupId" />
      <finder-column name="guestbookId" />
      <finder-column name="status" />
   </finder>
   ```

Run service builder (double-click `guestbook-service/build/buildService` in the
Gradle Tasks pane). Service Builder generates finder methods in the
persistence layer that take the specified fields (for example, `status`) as
parameters.

## Calling the Persistence Layer

Don't call the persistence layer directly in the application code. Instead
expose the new persistence methods in the service layer. 

1.  Open `GuestbookLocalServiceImpl`, add this getter, and save the file:

    ```java
	public List<Guestbook> getGuestbooks(long groupId, int status)
		throws SystemException {
		
		return guestbookPersistence.findByG_S(
			groupId, WorkflowConstants.STATUS_APPROVED);
	}
    ```

    This getter gets only approved guestbooks. That's why you hard code the
    workflow constant `STATUS_APPROVED` into the status parameter when calling
    the persistence method. 

2.  Now open `GuestbookEntryLocalServiceImpl`, add these two getters, and save
    the file:

    ```java
	public List<GuestbookEntry> getGuestbookEntries(
		long groupId, long guestbookId, int status, int start, int end)
		throws SystemException {

		return guestbookEntryPersistence.findByG_G_S(
			groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}

	public int getGuestbookEntriesCount(
		long groupId, long guestbookId, int status)
		throws SystemException {

		return guestbookEntryPersistence.countByG_G_S(
			groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}
    ```

    You'll replace the existing methods with these `getGuestbookEntries` and
    `getGuestbookEntriesCount` methods in the view layer, ensuring that only
    approved entries are displayed. 

3.  Save the file, run Service Builder, and refresh the Gradle project. 

The work here relates to the UI updates you'll make later. Next, you must
implement workflow handlers so that you can call the `updateStatus` service
method when the entity returns from the workflow framework.
