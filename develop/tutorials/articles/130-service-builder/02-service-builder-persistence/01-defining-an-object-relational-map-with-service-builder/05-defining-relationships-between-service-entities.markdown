# Defining Relationships Between Service Entities [](id=defining-relationships-between-service-entities)

Referencing one type of entity in the context of another entity is a common
requirement. This is also known as *relating* entities. Liferay's Bookmarks
application defines a relationship between an entry and its folder.

As mentioned earlier, each bookmark must have a folder. Therefore, each
`BookmarksEntry` entity must relate to a `BookmarksFolder` entity. Liferay
@ide@'s Diagram mode for `service.xml` facilitates relating entities. 

1.  Select Diagram mode for the `service.xml` file. 

2.  Select the *Relationship* option under *Connections* in the palette on the 
    right side of the view. This relationship tool helps you draw relationships
    between entities in the diagram.

3.  Click your first entity and move your cursor over to the entity you'd like 
    to relate it with. Liferay @ide@ draws a dashed line from your selected
    entity to the cursor.

4.  Click the second entity to complete drawing the relationship. Liferay @ide@ 
    turns the dashed line into a solid line, with an arrow pointing to the
    second entity.

5.  Save the `service.xml` file. 

Congratulations! You've related two entities. Their relationship shows in
Diagram mode and looks similar to the relationship in the figure below. 

![Figure 1: Relating entities is a snap in Liferay @ide@'s *Diagram* mode for `service.xml`.](../../../../images/service-builder-relate-entities.png)

Switch to *Source* mode in the editor for your `service.xml` file and note that
Liferay @ide@ created a column element in the first selected entity to hold the
ID of the corresponding entity instance reference. For example, the
`BookmarksEntry` entity uses this column to relate to a `BookmarksFolder` entity :

    <column name="folderId" type="long" />

Now that your entity columns are in place and entity relationships are
established, you can specify the default order in which the entity instances are
retrieved from the database. 
