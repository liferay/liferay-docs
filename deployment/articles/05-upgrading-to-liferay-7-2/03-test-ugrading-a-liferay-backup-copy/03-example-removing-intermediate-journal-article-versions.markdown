---
header-id: example-removing-intermediate-journal-article-versions
---

# Example: Removing Intermediate Journal Article Versions

[TOC levels=1-4]

These instructions and code samples demonstrate removing intermediate Journal
Article versions. In the 
[script console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console),
you can remove unneeded object versions by executing Java or Groovy
code. 

Here are example steps for removing intermediate Journal Article versions: 

1.  Decide how many of the latest versions to keep. You must keep the original
    version and the most recent version, but you may keep older recent versions
    too. For example, you may want to keep the two latest versions or just the
    latest. 

2.  Find a method for deleting the entity versions. @product@ [app 
    APIs](@app-ref@/apps/) and [com.lifieray.portal.kernel
    API](@platform-ref@/7.2-latest/javadocs/portal-kernel/) are available at
    [@platform-ref@](@platform-ref@).

    If it's a [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) entity,
    examine the `delete*` methods in the entity's `*LocalServiceUtil` class. For
    example, this `deleteArticle` in
    [`JournalArticleLocalServiceUtil`](@app-ref@/web-experience/latest/javadocs/com/liferay/journal/service/JournalArticleLocalServiceUtil.html#deleteArticle-long-java.lang.String-double-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-)
    deletes a Journal Article version:

    ```java
    deleteArticle(long groupId, java.lang.String articleId, double version, 
        java.lang.String articleURL, 
        com.liferay.portal.kernel.service.ServiceContext serviceContext)
    ```

3.  Aggregate the entity versions to delete and the information required to 
    delete them. For example, get all the Journal Article versions in range that
    match your removal criteria and associate their entity IDs and group IDs
    with them---the `deleteArticle` method requires the entity ID and group ID. 

    The entity object (e.g., `JournalArticle`) typically has a version field.
    `JournalArticleResource` has each Journal Article's article ID (the entity's
    ID) and group ID. `JournalArticleResource` is our key to getting each
    `JournalArticle`, which can have multiple versions. Here are steps for
    identifying the Journal Article versions to delete:

    1.  Get all the `JournalArticleResource` objects. 

    ```java
    List<JournalArticleResource> journalArticleResources = 
        JournalArticleLocalServiceUtil.getJournalArticleResources(start, end);
    ```

    2.  Get each Journal Article version's workflow status via the 
        `JournalArticle` object associated with each `JournalArticleResource`.
        Dynamic Query is an efficient way to get exactly the data you want 
        (and nothing more) from each object.
        
        <!--Add back link for 'Dynamic Query' once dynamic-query article is available-->

    ```java 
	for (JournalArticleResource
		journalArticeResource : journalArticleResources) {

		List<Double> journalArticlesVersionsToDelete =
			new ArrayList<Double>();

        DynamicQuery dq =
            DynamicQueryFactoryUtil.forClass(JournalArticle.class)
                .setProjection(ProjectionFactoryUtil.projectionList()
                    .add(ProjectionFactoryUtil.property("id"))
                    .add(ProjectionFactoryUtil.property("version"))
                    .add(ProjectionFactoryUtil.property("status")))
                .add(PropertyFactoryUtil.forName("groupId")
                    .eq(journalArticeResource.getGroupId()))
                .add(PropertyFactoryUtil.forName("articleId")
                    .eq(journalArticeResource.getArticleId()))
                .addOrder(OrderFactoryUtil.asc("version"));

        List<Object[]> result =
            JournalArticleLocalServiceUtil.dynamicQuery(dq);

        // See the next step for the sample code that goes here
    }
    ```

    3.  For each `JournalArticleResource` (there's one for each Journal Article 
        entity), build a list of intermediate versions in range of the first or
        latest versions you want to keep and whose status qualifies them for
        deletion. For example, you may want to delete intermediate article
        versions that are approved or expired (i.e.,
        [WorkflowConstants.STATUS_APPROVED or
        WorkflowConstants.STATUS_EXPIRED](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/workflow/WorkflowConstants.html)).
        The `MIN_NUMBER_FIRST_VERSIONS_KEPT` and
        `MIN_NUMBER_LATEST_VERSIONS_KEPT` variables here mark the minimum and
        maximum number of first (oldest) and latest (newest) versions to keep. 

    ```java 
    List<Double> journalArticlesVersionsToDelete =
		new ArrayList<Double>();

	for (int i=0; i < result.size(); i++) {
		long id = (long) result.get(i)[0];
		double version = (double) result.get(i)[1];
		int status = (int) result.get(i)[2];

		if ((status == WorkflowConstants.STATUS_APPROVED) || (status == WorkflowConstants.STATUS_EXPIRED) {

			if (i < MIN_NUMBER_FIRST_VERSIONS_KEPT) {
				continue;
			}

			if (i >= (result.size() -
				MIN_NUMBER_LATEST_VERSIONS_KEPT)) {
				continue;
			}

			journalArticlesVersionsToDelete.add(version);
		}
	}

    // See the next step for the sample code that goes here
    ```

4.  Lastly, delete each Journal Article matching the versions you aggregated. 

    ```java
    for (double version : journalArticlesVersionsToDelete) {
    {
        JournalArticleLocalServiceUtil.deleteArticle(journalArticeResource.getGroupId(),
            journalArticeResource.getArticleId(), 
            journalArticlesVersionsToDelete(i), null, null);
    }
    ```

You can write similar code to remove intermediate versions of other entities. 

| **Tip:** Print the version (and any other information of interest) of each
| object you're removing. You can also comment out the object deletion call and
| read the printout of versions to be removed as a test before committing to
| deleting them. 

After you've pruned your database, test it with @product@. 
