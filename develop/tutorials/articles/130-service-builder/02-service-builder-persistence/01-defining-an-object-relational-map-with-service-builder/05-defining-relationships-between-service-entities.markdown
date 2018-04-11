## Step 5: Defining Relationships Between Service Entities [](id=step-5-defining-relationships-between-service-entities)

Often you'll want to reference one type of entity in the context of another
entity. That is, you'll want to *relate* the entities. Liferay's Bookmarks
application defines a relationship between an entry and its folder.

As mentioned earlier, each bookmark must have a folder. Therefore, each
`BookmarksEntry` entity must relate to a `BookmarksFolder` entity. Liferay @ide@'s
Diagram mode for `service.xml` makes relating entities easy. First, select
Diagram mode for the `service.xml` file. Then select the *Relationship* option
under *Connections* in the palette on the right side of the view. This
relationship tool helps you draw relationships between entities in the diagram.
Click your first entity and move your cursor over to the entity you'd like to
relate it with. Liferay @ide@ draws a dashed line from your selected entity to the
cursor. Click the second entity to complete drawing the relationship. Liferay
IDE turns the dashed line into a solid line, with an arrow pointing to the
second entity. Save the `service.xml` file. 

Congratulations! You've related two entities. Their relationship should show in
Diagram mode and look similar to that of the figure below. 

![Figure 3: Relating entities is a snap in Liferay @ide@'s *Diagram* mode for `service.xml`.](../../../images/service-builder-relate-entities.png)

Switch to *Source* mode in the editor for your `service.xml` file and note that
Liferay @ide@ created a column element in the first selected entity to hold the ID
of the corresponding entity instance reference. For example:

    <column name="folderId" type="long" />

Now that your entity columns are in place, you can specify the default order in
which the entity instances are retrieved from the database. 
