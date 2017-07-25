# Updating the Service XML

To properly index assets and display assets in the Asset Publisher you must 
indicate the asset's status. To do this, you'll add status columns to the 
database. Follow these steps:

1.  Open `service.xml` and find the following line for the entry entity:

        <column name="guestbookId" type="long" />
    
    Add the following lines below:

        <!-- Status fields -->
        <column name="status" type="int" />
        <column name="statusByUserId" type="long" />
        <column name="statusByUserName" type="String" />
        <column name="statusDate" type="Date" />
    
2.  Add the following finders below the `GG` finder for the entry entity:

        <finder name="Status" return-type="Collection">
          <finder-column name="status" />
        </finder>
        <finder name="G_S" return-type="Collection">
          <finder-column name="groupId" />
          <finder-column name="status" />
        </finder>
    
3.  Run Service Builder to apply the changes.

That's all there is to updating your service layer to handle indexing! Your 
final step to implementing search for Guestbook entries in the Guestbook portlet 
is to update the user interface.