# Exporting and Importing Fragments [](id=exporting-and-importing-fragments)

Often you'll want to reuse fragments, or repurpose some of the code from 
Fragments in another site on your Liferay instance or on another Liferay 
instance altogether. Since all of the content is plain text, copy/pasting your 
code would always be an option, but Liferay provides a much more elegant 
solution - exporting your Fragment Collections.

## Exporting Fragments [](id=exporting-fragments)

When you export Fragments, you have four options:

1.  Export the entire repository of Fragments and Collections

2.  Export a single Collection.

3.  Export all of the Fragments in a Collection without exporting the 
    Collection itself.
    
4.  Export only a single Fragment.

For option 1, to export the entire repository, do the following actions from the Fragments page:

1.  Open the menu in the top left corner of the Fragments view.

2.  Click *Export*.

3.  A .zip file containing the export of all collections and fragments is 
    generated and you will be prompted to download it (or it will be 
    automatically downloaded, depending on your browser behaviour.)

The .zip file contains a series of nested folders with the base folders 
representing Collection and subfolders representing Fragments. The Collection 
and Fragment folders contain a .json file which defines the details of the 
entity. The Fragment folders contain the plain text HTML, JS, and CSS files in 
a `src` folder. 

For option 2, to export the entire single Collection do the following from the 
top level of the Fragments page:

1.  Click on the menu icon for the Collection from the top level menu.

2.  Select Export.

The Collection .zip will contain all of the data for the Collection as well as
the Fragments within it.

For option 3, to export the Fragments, but not the Collection, perform 
following actions from the Fragments page:

1.  Click on the Collection that you want to export.

2.  From the view showing all of the Fragments within the Collection, open the
    menu in the top right corner.
    
3.  Select *Export*.

The exported .zip file will contain only the Fragments in folders from  
within the Collection and not any information for the Collection itself. To 
re-import the Fragments, you will need to create a new collection and import
them from within it.

For option 4, to export a single Fragment, do the following:

1.  Go into the Collection which contains the Fragment you want to export.

2.  Click on the menu icon for the specific Fragment.

3.  Select *Export*

The .zip file will contain the folder with the Fragment data in it.

Now that you've done all this exporting, it's time to imported it all back it.

## Importing Fragments [](id=importing-fragments)

After you export Fragments, you are probably going to want to import them 
somewhere at some point. The type of import that you can do will depend on the
way the Fragments were exported. If you did an export with option 1 or 2, and 
exported the entire repository or at least a full Collection, you must import 
the Fragments and Collections into the root folder of the Fragments repository. 
If you did option 3 or 4, and exported Fragments without a Collection, you will 
need to first create a Collection and then you can import those Fragments into 
the Collection.

To export a Collection or full Repository do the following:

1.  Make sure that you're on the root level on the Fragments page.

2.  Click on the menu icon in the top right of the page.

3.  Select *Import*.

4.  Either drag-and-drop or click *Select* to upload the Fragments .zip.

5.  Check the box labeled "Overwrite Existing Entries" to replace any 
    Collections or Fragments with the same name as one that you are importing. 
    Uncheck it to preserve all the existing and imported entries.

6.  Click *Import*

The Collection or Collections included in the .zip file will be added to your 
repository.

To export a Fragment or group of Fragments that was exported without a 
collection do the following:

1.  From the root level of the Fragments page, either click on an existing
    Collection where you want to import the Fragments or create a new 
    Collection.
    
2.  From inside of the Collection click the menu button in the top right corner
    of the page.
    
3.  Select *Import*.

4.  Either drag-and-drop or click *Select* to upload the Fragments .zip.

5.  Click *Import*

The Fragments will be imported into the Collection where you performed the 
import.

Export and Import are powerful tools that can save you time and help you collaborate and save your work in development.
