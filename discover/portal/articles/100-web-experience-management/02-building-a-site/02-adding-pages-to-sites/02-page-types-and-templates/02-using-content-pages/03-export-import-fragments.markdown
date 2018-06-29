# Exporting and Importing Fragments [](id=exporting-and-importing-fragments)

Often you'll want to reuse fragments, or repurpose some of the code from 
Fragments in another site on your Liferay instance or on another Liferay 
instance altogether. Since all of the content is plain text, copy/pasting your 
code would always be an option, but Liferay provides a much more elegant 
solution - exporting your Fragment Collections.

## Exporting Fragments [](id=exporting-fragments)

When you export Fragments, two main options:

1.  Export a single Collection.

2.  Export some number of Fragments outside of a Collection.

For option 1, to export the entire single Collection do the following from the 
top level of the Fragments page:

1.  Go to *Site Administration* &rarr; *Build* &rarr; *Page Fragments*.

2.  Next to *Collections* click *Actions* (![Actions](../../../../../../images/icon-actions.png)) and select *Export*.

3.  Select a Collection or multiple Collections to be exported. Each collection will export in a separate file.

![Figure 1: Selecting Collections to export.](../../../../../../images/collections-export.png)

Each Collection `.zip` will contain all of the data for the Collection as well 
as the Fragments within it.

For option 2, to export individual Fragments, but not the Collection, perform 
following actions from the Fragments page:

1.  Click on the Collection that you want to export.

2.  To export all of the Fragments in the Collection without exporting any
    Collection data, click *Actions* (![Actions](../../../../../../images/icon-actions.png)) *Export* next to 
    the Collection name. A `.zip` file will be generated and downloaded 
    automatically.
    
![Figure 2: Exporting all of the Fragments in a Collection.](../../../../../../images/fragments-export-individual.png)
    
3.  To export a single Fragment click *Actions* (![Actions](../../../../../../images/icon-actions.png)) *Export* next to 
    the Fragment. A `.zip` file will be generated and downloaded 
    automatically.

When you export a single Fragment or a group of Fragments without a collection, 
you will need to have an existing Collection to import them into.

Now that you've done all this exporting, it's time to import it all back it.

## Importing [](id=importing)

There are a few options for importing fragments, depending on how you exported 
them. You can import a Collection which was created in Liferay, a Collection 
created using external tools, or any number of Page Fragments without a 
collection. Any time you import any Page Fragments they will not be available 
for use until you have gone to each imported fragment and approved it for use. 
This is to ensure that there are no errors in any imported fragments before 
they are added to a page.

See the [Developing a Fragment Using Desktop Tools](/discover/portal/-/knowledge_base/7-1/recommendations-and-best-practices#developing-a-fragment-using-desktop-tools) section for more information on 
creating and importing Fragments using other tools.

### Importing Collections [](id=importing-collections)

You can import collections that were exported @product@, or that were created 
using other tools. To import a collection, do the following:

1.  Go to *Site Administration* &rarr; *Build* &rarr; *Page Fragments*.

2.  Next to *Collections* click *Actions* (![Actions](../../../../../../images/icon-actions.png)) and select *Import*.

![Figure 3: The Sites Pages page allows you to edit your site pages as a whole.](../../../../../../images/collections-import.png)

3.  On the next screen click *Choose File* and select the file you want to 
    import.

4.  If you want to replace an existing collection make sure the box is checked
    for *Overwrite Existing Files*.
    
5.  Click *Import* and the collection will be uploaded.


### Importing Individual Page Fragments [](id=importing-individual-page-fragments)

You can also import a single Page Fragment or a set of them that were exported
outside of a collection.

1.  From the root level of the Fragments page, either click on an existing
    Collection where you want to import the Fragments.
    
2.  From inside of the Collection click the *Actions* (![Actions](../../../../../../images/icon-actions.png)) button in the top 
    right corner of the page.
    
3.  Select *Import*.

4.  Either drag-and-drop or click *Select* to upload the Fragments .zip.

5.  Click *Import*

The Fragments will be imported into the Collection where you performed the 
import.

Export and Import are powerful tools that can save you time and help you 
collaborate and save your work in development.
