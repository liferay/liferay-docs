# Displaying Content Sets

Content Sets are primarily displayed through the Asset Publisher. It is currently the only method to display them out of the box, but you can [develop your own external applications or widgets to utilize Content Sets](dev-guide-link). In [Creating Content Sets](user-guide-link) you created
two Content Sets. Now display them on a page.

## Configuring the Asset Publisher for Content Sets

To display the Content Sets, start with a blank page, and then add the 
necessary Asset Publishers and configure them to display the Content Sets.

1.  Create a new *Home* page for your site as a Widget Page with a 1 column layout. If you're using a fresh @product@ bundle, you can just remove the *Hello World* widget from the sample *Home* page.
    
2.  Open the *Add* menu and add two *Content Management* &rarr; *Asset Publishers* to the page stacked vertically.

3.  Click ![Options](../../../../images/icon-options.png) &rarr; *Configuration* for the top Asset Publisher.

4.  Under *Asset Selection* choose *Content Set*.

![Figure 1: The Asset Publisher has a number of options available for selecting its source for content.](../../../../images/content-set-asset-selection.png)

5.  Open *Select Content Set* and click *Select*.

6.  Click on the *Space Program Images* Content Set.

7.  Click *Save*.

Now the images will appear at the top of the page. You can manage the way the 
content is displayed---like what metadata appears---or even create an 
*Application Display Template* to style the content, but the items which 
display and the order in which they appear are determined by the Content Set.

Now configure the bottom Asset Publisher with the other Content Set.

1.  Click ![Options] &rarr; *Configuration* for the bottom Asset Publisher

2.  Under *Asset Selection* choose *Content Set*.

3.  Open *Select Content Set* and click *Select*.

6.  Click on the *Trending* Content Set.

![Figure 2: Select the Content Set you want to use.](../../../../images/content-set-select-set.png)

7.  Click *Save*.

Again, you can manage various display settings, but the items which appear and 
their order are determined by the Content Set criteria.

![Figure 3: You can see the results as the standard Asset Publisher output. You can create Application Display Templates to add more style and pizzazz here.](../../../../images/content-set-dynamic-results.png)

## Adding Items to an existing Content Set

To demonstrate both the management of both static and dynamic Content Sets, 
upload a new image, tag it, and add it to the static set manually.

1.  Upload a new image, and under *Categorization* tag it as *trending*.

2.  Without lifting another finger, the image is added to the top of the 
    *Trending* Content List.
    
![Figure 4: The result is dynamically added to the Content List wherever it is displayed.](../../../../images/content-set-dynamic-add.png)

3.  To add it to the manual set, go back to *Site Administration* &rarr; 
    *Content & Data* &rarr; *Site Builder*.

4.  Click on *Space Program Images* or select ![Options](../../../../images/icon-options.png) &rarr; *Edit* next to *Space 
    Program Images*.
    
5.  Next to *Asset Entries* click *Select* &rarr; *Basic Document*.

6.  Select the new image and click *Add*.
    
7.  Navigate back to the *Home* page to see your image added to the list.

Content Sets are a powerful feature which provide one place to easily define 
content and other assets to be displayed all over your site. Their reusability 
also means less repeated work involved in getting great content delivered to 
your users.