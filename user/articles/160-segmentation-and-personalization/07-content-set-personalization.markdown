# Content Set Personalization

In [Creating User Segments](/user/7-2/-/knowledge-base/user/creating-user-segments) you created a 
Segment called *American Engineers*. Now, you'll use it to demonstrate Content 
Set Personalization. For this example, create a Content Set which will be the 
default displayed on the *Home* page, and then modified to create a 
personalized variation with with technical articles that will be displayed for 
members of the *American Engineers* segment.

If you're not familiar with Content Set, see the [Creating Content Sets](user-docs-link) guide before you get started here. 

## Creating and Setting the Default Content Set

First create the default Content Set and configure it on the Home page using the
Asset Publisher.

1.  Go to *Site Administration* &rarr; *Content & Data* &rarr; *Content Sets*.

2.  Click the ![Add](../../images/icon-add.png) button and choose *Manual 
    Selection*.
    
3.  Name it *Home Page Content*.

4.  For the new Content Set, click *Select* next to *Asset Entries* and select
    *Basic Web Content*.

![Figure 1: Click *Select* to add a new Asset Entries.](../../images/create-default-content-set.png)

5.  On the *Select Basic Web Content* page. check the boxes next to the content 
    you want to add and click *Add*.
    
6.  Now, navigate to the *Home* page and add an Asset Publisher to the page.

7.  Open *Configuration* for the Asset Publisher.

8.  Under *Asset Selection* select *Content Set*.

9.  Under *Select Content Set* click *Select*, choose *Home Page Content*
    and click *Save*.
    
Now the Content Set that you configured will appear in the Asset Publisher on 
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
being displayed, they will see the personalized version and not the default. 
Test this now, using the *Simulator*.
