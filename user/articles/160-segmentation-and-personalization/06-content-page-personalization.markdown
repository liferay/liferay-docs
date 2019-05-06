# Content Page Personalization

In [Creating Segments with Customer Fields and Session Data](/user/7-2/-/knowledge-base/user/creating-segments-with-custom-fields-and-session-data) you created a Segment called *Premium Card Prospects*. Now, you'll
use it to demonstrate Content Page Personalization.

If you're not familiar with Content Pages, see the [Creating Content Pages](user-docs-link) guide before you get started here. 

## Creating the Default Page

First, you need to create the *Credit Cards* page. To do this, create a new 
content page, add some fragments to it, and edit the content to match. First 
you need to build the bones of the page:

1.  Go to *Site Administration* &rarr; *Site Builder* &rarr; *Pages*.

2.  Click the [Add](../../images/icon-add.png) button.

3.  Select *Content Page* and name the page *Credit Cards*.

4.  From the edit page for the *Default*, open *Sections* &rarr; *Basic 
    Sections* from the build menu on the right side of the screen and add a 
    *Banner* to the page.

5.  Next, click on *Section Builder*, open *Layouts*, and add a three column 
    layout to the page, above the banner.
    
![Figure 1: Open Layouts from the Section Builder.](../../images/section-builder-layouts.png)
    
6.  Open *Basic Components* and add a *Card* inside each of the columns.
    
Next, edit the content (this step is optional):

1.  Click on the text for the cards and the banner to edit it and provide 
    content relevant to someone looking for information about credit cards.

2.  Click on each image and provide an appropriate image.

3.  Click *Publish*.

![Figure 2: Your final result might look something like this.](../../images/personalization-default-content.png)

This is the default page that anyone visiting the site will see.

## Defining Custom Experiences

Next, define an experience specifically tailored to customers who you have 
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

The *Default* version of the page will display for everyone except for those
defined as *Premium Card Prospects*, but customers in that segment will have
an experience curated just for them.

![Figure 3: Your final result for the card prospects might look something like this.](../../images/personalization-prospects.png)

| **Note:** When you create a new experience, it will copy the *Default*
| experience at the time that it is created. Any further changes to the
| *Default* experience will not effect any of experiences for that page.

