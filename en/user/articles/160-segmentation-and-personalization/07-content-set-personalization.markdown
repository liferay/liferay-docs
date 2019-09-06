---
header-id: content-set-personalization
---

# Content Set Personalization

[TOC levels=1-4]

In [Creating User Segments](/docs/user/7-2/-/knowledge-base/u/creating-user-segments)
you created a  Segment called *American Engineers*. Now, you'll use it to
demonstrate Content  Set Personalization. For this example, create a Content Set
to be the  default displayed on the *Home* page. Then you'll modify it to create
a personalized variation containing technical articles for members of the
*American Engineers* segment.

If you're not familiar with Content Set, see the
[Creating Content Sets](/docs/7-2/user/-/knowledge_base/u/creating-content-sets)
article before you get started here. 

## Creating and Setting the Default Content Set

First create the default Content Set and configure it on the Home page using the
Asset Publisher.

1.  Go to *Site Administration* &rarr; *Content & Data* &rarr; *Content Sets*.

2.  Click the Add button (![Add](../../images/icon-add.png)) and choose *Manual 
    Selection*.

3.  Name it *Home Page Content*.

4.  For the new Content Set, click *Select* next to *Asset Entries* and select
    *Basic Web Content*.

    ![Figure 1: Click *Select* to add a new Asset Entries.](../../images/create-default-content-set.png)

5.  On the *Select Basic Web Content* page, check the boxes next to the content 
    you want to add and click *Add*.

6.  Navigate to the *Home* page and add an Asset Publisher to the page.

7.  Open *Configuration* for the Asset Publisher.

8.  Under *Asset Selection* select *Content Set*.

9.  Under *Select Content Set* click *Select*, choose *Home Page Content*,
    and click *Save*.

Now the Content Set that you configured appears in the Asset Publisher on 
the *Home Page*. Next configure the Content Set for Personalization.

## Personalizing the Content Set

Now create the content set for engineers and configure its display.

1.  Go back to the Content Set from *Site Administration*.

2.  Click *New Personalized Variation* and select the *American Engineers* 
    segment

    ![Figure 2: Create a new Personalized Variation.](../../images/create-personalized-variation.png)

3.  Click *Select* next to *Asset Entries* and select *Basic Web Content*.

4.  Select articles appropriate to an engineering audience and click *Add*.

Now anytime a member of the *American Engineers* segment views this Content Set 
being displayed, they see the personalized version and not the default. Test
this now, using the *Simulator*.
