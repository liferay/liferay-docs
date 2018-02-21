# Building Content Pages with Fragments

Once you have Fragments created, you can start using them to create Content 
Pages. A Content Page is a page which is built entirely from Fragments. You 
can't mix Fragments and Portlets on a single page (though you can embed 
portlets within a Fragment), you'll need to choose between which style of page 
you're going to use when you start creating it. Content Pages must be created 
from Page Templates, and like a Fragment, Page Templates must exist within a 
Collection. For more information on choosing between page types, see our Modern 
Site Building[link] tutorial.

## Creating and Managing Fragments

You can manage Fragments through Site Administration:

1.  Open the main menu.

2.  Under *Site Administration* make sure the Site where you want to work is 
    selected.

3.  In the *Content* section, select *Fragments*.

<screenshot>

The main Fragments pages will display any Collections that are available for the current site, provide the option to Import and Export through the menu in the top right, and enable you to create Collections and Fragments. To create a Fragment, you must first create a Collection.

1.  Click the `+` button to add a Collection.

2.  Give the Collection a *Name* and *Description* and click *Save*.

Collections help you organize Fragments, and can be used to differentiate 
between different types of Fragments or Fragments used by different groups or 
departments. Next you want to create a Fragment inside of the Collection you 
created.

1.  Click on the Collection you created.

2.  Click the `+` button to create a Fragment.

3.  Give it a *Name* and click *Submit*.

<screenshot>

Now you're looking at the Fragment creation form. Each pane in the editor has a 
different function:

*  The top left pane is for entering HTML.
*  The top right pane is for entering CSS.
*  The bottom left pane is for entering JavaScript.
*  The bottom right pane provides a live preview as you work in the other panes.

For more information on how to create to Fragments, see our Fragment 
Development tutorial.

## Exporting and Importing Fragments

Often you'll have a set group of Fragments that you're working with across 
sites, organized into Collections. You can easily export and import collection 
to move them across Liferay instances or sites within an instance. Fragment 
Collections are imported and exported from the Fragments page in Site 
Administration. To export Fragments from the Fragments page:

1.  In the top right corner of the screen click on the menu button and select
    *Export*.
    
2.  A zip file will be generated containing all of the Fragments and 
    Collections from your current position in the Fragment hierarchy.
    
If you want to export all of the Fragments associated with the current site, you
can export from the root of the Collections hierarchy. Alternatively, you can 
navigate to a specific collection if you only want to export that collection 
and not any others.

Fragments are exported in a simple .zip format which contains a hierarchy of 
collections, along with all of the HTML, CSS, and JS in text format. You 
import Fragments in the same way that you exported them:

1.  In the top right corner of the screen click on the menu button, and select 
    *Import*.
    
2.  Select a zip file containing exported Fragments collections.

You will now have a selection of Fragments to use. The Fragments will be organized in the Collections they were exported as.

## Creating a Page Template

Your first step is to create a Page Template, composed of Fragments which have already been created by a developer.

1.  Go to *Site Configuration* &rarr; *Navigation* &rarr; *Site Pages*.

2.  Open the *Page Templates* tab.

<screenshot>

3.  Click the `+` button to add a new Collection.

4.  Give your collection a *Name* and *Description*, and *Save* it.

5.  Click the `+` button on this page to create a new Page Template, and give 
    it a name.

Now you can create your page layout by adding Fragments to the page.

<screenshot>

Fragments are added by selecting a Collection from the *Fragments* tab on the 
right and adding them to the page. You can add multiple Fragments from 
different collections, and you can add the same Fragment to the page multiple 
times. To see which Fragments the current page is made up of, you can check the 
*Added* tab. The template is automatically saved as you work. Click on the back arrow at the top to stop editing the template.

## Creating a Content Page

Now that you have a Page Template created, you can create a Content Page.

1.  Go to the *Pages* tab under *Navigation* &rarr; *Site Pages*.

2.  Click the `+` button to add a page.

3.  From the page creation page, give it a *Name* and go to the *Type* 
    selection and select *Content*.

4.  Now you can select a *Page Template* to use.

5.  Click *Add Page* to create the page.

After you create a Content Page, by default it will be added to the default 
navigation menu or any other menus that are set to automatically add new pages. 


