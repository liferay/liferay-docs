---
header-id: exporting-and-importing-fragments
---

# Exporting and Importing Fragments

[TOC levels=1-4]

Often you'll want to reuse or re-purpose code from Fragments. To do this, export
your Fragment Collections.

## Exporting Fragments

There are two ways to export Fragments:

1.  Export a Collection or set of Collections.

2.  Export some Fragments outside of a Collection.

To export a single Collection, 

1.  Go to *Site Administration* &rarr; *Build* &rarr; *Page Fragments*.

2.  Next to *Collections* click *Actions*
    (![Actions](../../../../../images/icon-actions.png)) and select *Export*.

3.  Select a Collection or multiple Collections to be exported. Each collection
    exports in a separate file.

    ![Figure 1: Select Collections to export.](../../../../../images/collections-export.png)

Each Collection `.zip` contains all Collection data and Fragments.

To export individual Fragments,

1.  Click on the Collection that you want to export.

2.  To export all Fragments in the Collection without exporting any Collection
    data, click *Actions*
    (![Actions](../../../../../images/icon-actions.png)) *Export* next to 
    the Collection name. A `.zip` file is generated and downloaded
    automatically.
 
    ![Figure 2: Exporting all of the Fragments in a Collection.](../../../../../images/fragments-export-individual.png)
 
3.  To export a single Fragment, click *Actions* 
    (![Actions](../../../../../images/icon-actions.png)) &rarr; *Export* next
    to the Fragment. A `.zip` file is generated and downloaded automatically.

Note that if you export a single Fragment or a group of Fragments without
a collection, they must be imported into an existing Collection. 

You can also export Global Collections and single Fragments from your Site.

Now it's time to import your Fragments to where you need them. 

## Importing

You can import a Collection that was created in @product@, a Collection
created using external tools, or Page Fragments without a collection. When you
first import Page Fragments, they aren't available for use until you have
approved them for use. This is to ensure that there are no errors in any
imported fragments before they are added to a page.

See 
[Developing a Fragment Using Desktop Tools](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments-desktop-tools#importing-and-exporting-fragments) for more information on 
creating and importing Fragments using other tools.

### Importing Collections

To import a collection, follow these steps:

1.  Go to *Site Administration* &rarr; *Build* &rarr; *Page Fragments*.

2.  Next to *Collections*, click *Actions* (![Actions](../../../../../images/icon-actions.png)) and select *Import*.

    ![Figure 3: Importing and exporting Collections is accessed from a single menu.](../../../../../images/collections-import.png)

3.  On the next screen, click *Choose File* and select the file you want to 
    import.

4.  If you want to replace an existing collection, make sure the box is checked
    for *Overwrite Existing Files*.
 
5.  Click *Import* and the collection is uploaded.

### Importing Individual Page Fragments

You can also import a single Page Fragment or a set that was exported outside of
a collection.

1.  From the root level of the Fragments page, click on an existing Collection
    where you want to import the Fragments.
 
2.  From inside the Collection click the *Actions*
    (![Actions](../../../../../images/icon-actions.png)) button in the top
    right corner of the page.
 
3.  Select *Import*.

4.  Drag-and-drop or click *Select* to upload the Fragments `.zip`.

5.  Click *Import*

The Fragments are imported into the Collection. 

Exporting and importing fragments is the preferred way to share code or bring it
into your Site. 
