---
header-id: example-removing-intermediate-journal-article-versions
---

# Example: Removing Intermediate Journal Article Versions

[TOC levels=1-4]

These instructions and code samples demonstrate removing intermediate versions
of Journal Articles. To remove unneeded objects from your database, you can execute similar Java code or Groovy code in the scripting console. 

Here are example steps for removing intermediate Journal Article versions: 

1.  Decide how many of the most recent versions to keep. You must keep the 
    original version and the most recent version, but you may keep older recent
    versions too. For example, you may want to keep the two most recent versions
    or just the most recent. 

2.  Find a method for deleting versions of the entity. @product@ [app 
    APIs](@app-ref@/apps/) and [com.lifieray.portal.kernel
    API](@platform-ref@/7.2-latest/javadocs/portal-kernel/) are available at
    [@platform-ref@](@platform-ref@).

    If it's a [Service
    Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) entity,
    examine the `delete*` methods in the entity's `*LocalServiceUtil` class. For
    example,
    [`JournalArticleLocalServiceUtil`](@app-ref@/apps/web-experience/latest/javadocs/com/liferay/journal/service/JournalArticleLocalServiceUtil.html#deleteArticle-long-java.lang.String-double-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-)
    has this method for deleting Journal Articles:

    ```java
    deleteArticle(long groupId, java.lang.String articleId, double version, 
        java.lang.String articleURL, 
        com.liferay.portal.kernel.service.ServiceContext serviceContext)
    ```

3.  Get entity ID, group ID, and version number of each entity (e.g., each 
    Journal Article) and collect that information for the entity versions you
    want to delete (e.g., the versions between the original and the oldest
    recent version you want to keep). 

    The entity object (e.g., `JournalArticle`) typically has a version field.
    `JournalArticleResource` has each Journal Article's article ID (the entity's
    ID) and group ID. `JournalArticleResource` is our key to getting each
    `JournalArticle`, which can have multiple versions. Here are steps for
    identifying the Journal Article versions to delete:

    1.  Get all the `JournalArticleResource` objects, to get each Journal 
        Article's article ID and group ID. 

    ```java
    List<JournalArticleResource> journalArticleResources = 
        JournalArticleLocalServiceUtil.getJournalArticleResources(start, end);
    ```

    2.  Get each Journal Article entity's version and workflow status by 
        querying  for all `JournalArticle` objects associated with each
        `JournalArticleResource`. [Dynamic
        Query](/docs/7-2/appdev/-/knowledge_base/a/dynamic-query) is an
        efficient way to get exactly the data you want (and nothing more) from
        each object. 

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
        entity), build a list of intermediate versions whose status is expired
        (i.e.,
        [WorkflowConstants.STATUS_EXPIRED](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/workflow/WorkflowConstants.html)).
        Filtering on Journal Articles that are expired assures that those that
        are drafts or pending approval are *not* added to the list. The
        `MIN_NUMBER_FIRST_VERSIONS_KEPT` and `MIN_NUMBER_LATEST_VERSIONS_KEPT`
        variables mark the min and max number of oldest and newest versions to
        keep. 

    ```java 
    List<Double> journalArticlesVersionsToDelete =
		new ArrayList<Double>();

	for (int i=0; i < result.size(); i++) {
		long id = (long) result.get(i)[0];
		double version = (double) result.get(i)[1];
		int status = (int) result.get(i)[2];

		if (status == WorkflowConstants.STATUS_EXPIRED) {

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

    4.  The last thing you'll do for the `JournalArticleResource` you're 
        operating on in this `for` loop, delete the unneeded versions of the
        associated Journal Article. 

    ```java
    for (double version : journalArticlesVersionsToDelete) {
    {
        JournalArticleLocalServiceUtil.deleteArticle(journalArticeResource.getGroupId(),
            journalArticeResource.getArticleId(), 
            journalArticlesVersionsToDelete(i), null, null);
    }
    ```

You can write a similar code to remove intermediate versions of other entities. 

| **Tip:** Print out the version (and any other information of interest) of 
| each object you're removing. You can also comment out the object deletion call
| and read the printout of versions to be removed as a test before committing
| to deleting them. 

After you've pruned your database of unneeded objects, test it with @product@. 
