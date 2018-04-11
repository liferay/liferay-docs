## Step 6: Defining Ordering of Service Entity Instances [](id=step-6-defining-ordering-of-service-entity-instances)

Often, you want to retrieve multiple instances of a given entity and list them
in a particular order. Liferay lets you specify the default order of the
entities in your `service.xml` file. 

Suppose you want to return `BookmarksEntry` entities alphabetically by name.
It's easy to specify these default orderings using Liferay @ide@. Switch back to
*Overview* mode in the editor for your `service.xml` file. Then select the
*Order* node under the entity node in the outline on the left side of the
view. The IDE displays a form for ordering the chosen entity. Check the *Specify
ordering* checkbox to show the form for specifying the ordering. Create an order
column by clicking the *Add* icon (![Add](../../../images/icon-add-ide.png)) to
the right of the table. Enter the column name (e.g., *name*, *date*, etc.) to
use in ordering the entity. Click the *Browse icon*
(![Browse](../../../images/icon-browse-ide.png)) to the right of the *By* field
and choose the *asc* or *desc* option. This orders the entity in ascending or
descending order. 

Now that you know how to order your service entities, the last thing to do is to
define the finder methods for retrieving entity instances from the database. 
