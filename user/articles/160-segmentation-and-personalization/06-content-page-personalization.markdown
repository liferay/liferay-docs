---
header-id: content-page-personalization
---

# Content Page Personalization

[TOC levels=1-4]

In [Creating Segments with Customer Fields and Session Data](/docs/7-2/user/-/knowledge_base/u/creating-segments-with-custom-fields-and-session-data)
you created a Segment called *Premium Card Prospects*. Now, you'll use it to
demonstrate Content Page Personalization.

If you're not familiar with Content Pages, see the
[Creating Content Pages](/docs/7-2/user/-/knowledge_base/u/creating-content-pages)
article before you get started here. 

## Creating the Default Page

First, you need to create the *Credit Cards* page. To do this, create a new 
content page, add some fragments to it, and edit the content to match. First 
you need to build the bones of the page:

1.  Go to *Site Administration* &rarr; *Site Builder* &rarr; *Pages*.

2.  Click the *Add* button (![Add](../../images/icon-add.png)).

3.  Select *Content Page* and name the page *Credit Cards*.

4.  Open the *Sections* &rarr; *Basic Sections* from the build menu on the right
    side of the screen and add a *Banner* to the page.

5.  Next, click on *Section Builder*, open *Layouts*, and add a three column 
    layout to the page, above the banner.

![Figure 1: Open Layouts from the Section Builder.](../../images/section-builder-layouts.png)

6.  Open the *Basic Components* tab and add a *Card* inside each of the columns.

Next, edit the content (this step is optional):

1.  Click on the text for the cards and the banner to edit it and provide 
    content relevant to someone looking for information about credit cards.

2.  Click on each image and provide an appropriate image.

3.  Click *Publish*.

![Figure 2: Your final result might look something like this.](../../images/personalization-default-content.png)

This is the default page that anyone visiting the site sees.

## Defining Custom Experiences

Next, define an experience specifically tailored to customers whom you have
identified as *Premium Card Prospects* using User Segments.

1.  At the top of the page, for the *Experience* click on *Default* to open
    the experience selection dialog.

    ![Figure 3: Click on the current experience to create a new one or select a different existing experience.](../../images/select-experience.png)

2.  Click on *New Experience*.

3.  Name it *Card Prospects* and select *Premium Card Prospects* for the 
    *Audience*.

4.  Add a *Banner* fragment above the three columns you added earlier.

5.  Edit the Banner to provide information specifically related to upgrading
    a card for an existing customer.

6.  Click *Publish*.

The *Default* version of the page appears for everyone except for those
defined as *Premium Card Prospects*, but customers in that segment have
an experience curated just for them.

![Figure 4: Your final result for the card prospects might look something like this.](../../images/personalization-prospects.png)

| **Note:** When you create a new experience, it copies the *Default*
| experience at the time that it is created. Any further changes to the
| *Default* experience do not effect any of experiences for that page.
