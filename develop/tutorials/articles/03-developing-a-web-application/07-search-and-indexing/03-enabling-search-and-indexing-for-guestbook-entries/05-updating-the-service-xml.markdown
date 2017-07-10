# Updating the Service XML

Just as with the guestbook entity, you must add status columns to indicate the 
asset's status to the indexer.

Open `service.xml` and find the following line for the guestbook entity:

    <column name="name" type="String" />
    
Add the following lines below:

    <!-- Status fields -->
    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />
    
Add the following finders below the `GroupId` finder for the guestbook entity:

    <finder name="Status" return-type="Collection">
      <finder-column name="status" />
    </finder>
    <finder name="G_S" return-type="Collection">
      <finder-column name="groupId" />
      <finder-column name="status" />
    </finder>
    
Run Service Builder to apply the changes.

Let's recap what you've accomplished so far. Whenever guestbooks are added,
updated, or deleted, their indexes are updated and their status is indicated. 
With your indexers in place, your guestbook and guestbook entry entities are all 
set to take advantage of Liferay's asset framework. We'll explore Liferay's 
asset framework in the next Learning Path.