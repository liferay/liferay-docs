# Updating the Service XML

Just as with the guestbook entity, you must add status columns to indicate the 
asset's status to the indexer.

1.  Open `service.xml` and find the following line for the guestbook entity:

        <column name="name" type="String" />
    
2.  Add the following lines below:

        <!-- Status fields -->
        <column name="status" type="int" />
        <column name="statusByUserId" type="long" />
        <column name="statusByUserName" type="String" />
        <column name="statusDate" type="Date" />
    
3.  Add the following finders below the `GroupId` finder for the guestbook entity:

        <finder name="Status" return-type="Collection">
          <finder-column name="status" />
        </finder>
        <finder name="G_S" return-type="Collection">
          <finder-column name="groupId" />
          <finder-column name="status" />
        </finder>
    
4.  Save and run Service Builder to apply the changes.

5.  Sign in to the portal, open the *Control Panel* and navigate to 
    *Configuration* &rarr; *Server Administration*. Scroll down to the *Index 
    Actions* panel and verify that the Entry and Guestbook entities are listed 
    as reindexable:
    
        Reindex com.liferay.docs.guestbook.model.Entry

        Reindex com.liferay.docs.guestbook.model.Guestbook

Let's recap what you've accomplished so far. Whenever guestbooks are added,
updated, or deleted, their indexes are updated and their status is indicated.

![Figure 1: Guestbook and Entry entities can be reindexed through Server Administration.](../../../images/search-and-indexing-complete.png)

With your indexers in place, your guestbook and guestbook entry entities are 
all set to take advantage of Liferay's asset framework. We'll explore Liferay's 
asset framework in the next Learning Path.