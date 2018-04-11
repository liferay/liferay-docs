## Step 7: Defining Service Entity Finder Methods [](id=step-7-defining-service-entity-finder-methods)

Finder methods retrieve entity objects from the database based on specified
parameters. You'll probably want to create at least one finder method for each
entity you create in your services. Service Builder generates several methods
based on each finder you create for an entity. It creates methods to fetch,
find, remove, and count entity instances based on the finder's parameters. 

For many applications, it's important to be able to find its entities per site.
You can specify these finders using Liferay @ide@'s Overview mode of
`service.xml`. Select the *Finders* node under the entity node in the Outline on
the left side of the screen. The IDE displays an empty *Finders* table in the
main part of the view. Create a new finder by clicking the *Add* icon
(![Add](../../../images/icon-add-ide.png)) to the right of the table. Give your
finder a name and return type. Use the Java
camel-case naming convention when naming finders since the finder's name is used
to name the methods that Service Builder creates. The IDE creates a new
finder sub-node under the *Finders* node in the outline. Next, you'll learn how
to specify the finder column for this node. 

Under the new finder node, the IDE created a *Finder Columns* node. Select the
*Finder Columns* node to specify the columns for your finder's parameters.
Create a new finder column by clicking the *Add* icon and specifying the
column's name. Keep in mind that you can specify multiple parameters (columns)
for a finder.

![Figure 4: Creating Finder entities is easy with Liferay @ide@.](../../../images/service-builder-finders.png)

If you're creating site-scoped entities (entities whose data should be unique to
each site), you should follow the steps described above to create finders by
`groupId` for retrieving your entities. Remember to save your `service.xml` file
after editing it to preserve the finders you define. 

When you run Service Builder, it generates finder-related methods
(e.g., `fetchByGroupId`, `findByGroupId`, `removeByGroupId`, `countByGroupId`) for the
your entities in the `*Persistence` and `*PersistenceImpl` classes.
The first of these classes is the interface; the second is its implementation.
For example, Liferay's Bookmarks application generates its entity finder methods
in the
`-Persistence` classes found in the
`/bookmarks-api/src/main/java/com/liferay/bookmarks/service/persistence`
folder and the `-PersistenceImpl` classes in the
`/bookmarks-service/src/main/java/com/liferay/bookmarks/service/persistence/impl`
folder.

Now you know to configure Service Builder to create finder methods for your
entity. Terrific!

Now that you've specified the service for your project, you're ready to *build*
the service by running Service Builder. To learn how to run Service Builder and
to learn about the code that Service Builder generates, please refer to the
[Running Service Builder and Understanding the Generated
Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
tutorial. 
