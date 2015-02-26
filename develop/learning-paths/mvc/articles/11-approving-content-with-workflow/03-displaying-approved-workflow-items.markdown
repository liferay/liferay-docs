# Displaying Approved Workflow Items

To display only entites approved in the workflow, you'll add a new `finder`
that takes workflow status into account. Service Builder makes this easy.

## Adding a Finder for the Entry

Use this from view.jsp to decide on finder and which finders to expose in service layer.

<liferay-ui:search-container-results
		results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
						guestbook.getGuestbookId(), searchContainer.getStart(),
						searchContainer.getEnd())%>"
		total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
						guestbook.getGuestbookId())%>" />



Open `service.xml`. In the `Entry` entity, replace the current finder with the
`name` value of `G_G_N` with the following tags:

    <finder name="G_G_N_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="guestbookId"></finder-column>
        <finder-column name="name"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>

After you run service builder, you'll have a finder that allows you to retrieve
`Entry` objects from the database based on their 
[Group Id](participate/liferaypedia/-/wiki/Main/Group+ID), `guestbookId`,
`name`, and workflow status. Now you can use the finder in your service layer
and your JSPs to make sure that any entries that appear in the Guestbook
portlet have been approved in the workflow (if workflow is enabled).

Open `EntryLocalServiceImpl` and replace the current *getter* methods with the
following:




<!--Once the Friendly URL learning path is merged, I will have the Entry entity in the rquest to be able to get easily from the request attribute in the JSP, so that entry.getStatus can be called for the new getter methods status parameter-->




## Adding a Finder for the Guestbook






Make setStatus(WorkflowRegistryUtilHandler.re of references to AssetEntry and AssetLink (should be there since
already asset-enabled): 
    <reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
	<reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

