# Creating Content Sets

Content Sets are created by content administrators through the Content Sets 
interface in Site Administration. Content Sets can use either Manual or Dynamic 
secltion, and you can create any number of Content Sets, and display them 
through the Asset Publisher or [custom applications](dev-guide-link). Content 
Sets can also have [personalizated variations](user-guide-link) which provide 
different experiences for different users based on criteria that you specify. 
The criteria management is shared with the Asset Publisher, so for more 
information on each option, see the official [Asset Publisher Documentation](user-guide-link).

## Creating a Manual Content Set

To demonstrate the creation of a Manual Content Set, create a Content Set that 
contains a number of images to be displayed on the Frontpage of the fictitious 
Space Program website. To prepare for this exercise, upload some appropriate images to *Documents and Media* to use for the Content Set.

1.  Go to *Site Administratio* &rarr; *Content & Data* and select *Content 
    Sets*.
    
![Figure 1: Content Sets is found in the Content & Data section of Site Administration.](../../../../images/content-sets-empty-page.png)

2.  Click ![Add](../../../../images/icon-add.png) and select *Manual Selection*.

3.  Name your Content Set *Space Program Images*.

On the next screen, you can select the assets to include in the Content Set.

1.  Click *Select* &rarr; *Basic Document*.

![Figure 2: You can select the type of asset to add to the Content Set.](../../../../images/content-sets-select-basic-document.png)

2.  Now, check the boxes for each image that you want to add and click *Add*.

Now this Content Set can be displayed anywhere on the site where it was created. You can add or remove items from the set, and it will automatically update it whereever it is displayed. 

## Creating a Dynamic Content Set

To demonstrate the creation of a Dynamic Content Set, create a Content Set that 
contains a number of varied assets that are tagged as "trending." In order for 
this to work, you will need some number of existing assets with the appropriate 
tag.

1.  From the *Content Sets* page, click ![Add](../../../../images/icon-add.png)
    &rarr; *Dynamic Selection.

2.  Enter *Trending* for the name and click *Save*.

With Dynamic Content Sets, you can choose the *Source*, *Scope*, *Filter*, and *Ordering* for the items in the set.

1.  Leave *Source* as *Any* and *Scope* as *Current Site*

2.  Open *Filter*, make sure it is set to *Contains Any of the following Tags*, 
    and then enter "trending" in the *Tags* box.
    
![Figure 3: Content Sets use the same filter system as the Asset Publisher.](../../../../images/content-set-trending-filter.png)

3.  Open *Ordering* and set it to *Order By*: *Publish Date*, *And Then By*: 
    *Title*.

4.  Click *Save*.

This will create a Content Set which will contain any items that are currently 
tagged as "trending" and any future items with the "trending" tag will be added 
to the Content Set automatically.

Now that you have your Content Sets created, you can [display them on a page](user-guide-link).
