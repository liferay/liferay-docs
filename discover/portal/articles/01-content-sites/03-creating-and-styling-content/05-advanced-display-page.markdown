# Advanced Content Display Page Options [](id=advanced-content-display-page-options)

<div class="learn-path-step">
    <p>Creating Content<br>Step 5 of 7</p>
</div>

Now that you've used a basic use case for the content display page, let's take
a deeper look at how they work and how to use them for more advanced use cases.

## Choose Your Own Content Display Page [](id=choose-your-own-content-display-page)

A Content Display Page can be any page with an Asset Publisher configured as the
"default" asset publisher for a page, and you can set up the rest of the page 
however you like. This allows you to use Content Display Pages for things beyond
displaying a single static piece of content or providing a friendly URL.

### Configuring Asset Publishers [](id=configuring-asset-publishers)

Now, let's configure an Asset Publisher to function as our primary content 
display.

1.  Go to the Activities page.

2.  Open the Configuration menu for the Asset Publisher on the right side.

3.  In *Asset Selection* scroll down to *Filter* and open it.

4.  Set *Show only assets with Activities as its display page* to *Yes*. 

5.  Open *Display Settings*

6.  For the *Display Template* select *Full Content*.

7.  For the *Number of Items to Display* change it to 1.

8.  Set *Set as the Default Asset Publisher for This Page* select *Yes*.

    This is the most important step in configuring a Content Display Page. If a
    page has an Asset Display Template set as the default for the page, any 
    content can be displayed in it.

9.  Set *Show Metadata Descriptions* to *No*.

10. Click Save.

Most of the configuration options are self explanatory. Next, configure the Asset Publisher on the left to function as an Asset Selector.

1. Open the Configuration menu.

2. In *Asset Selection* scroll down to *Filter* and open it.

3. Set *Show only assets with Activities as its display page* to *Yes*. 

4. Open *Display Settings*

5. For *Display Template* select *Title List*.

6. For *Asset Link Behavior* select *View in Context*

7. Click Save.

The Asset Publishers are configured, but there's no content configured for this
page. Next you'll create content that uses a Content Display Page.

## Creating Content with a Display Page [](id=creating-content-with-a-display-page)

Any content can have a display page set for it, and any page can be a display
page if it has a properly configured Asset Publisher on it. Next, you'll create
three content items to be displayed here.

1. Go to the *Web Content* page in *Site Administration*.

2. Click the `+` and select *Basic Web Content*.

You'll need to repeat this three times. The content will only have a title and
an image.

1. For the first item, set the *Title* as "Lunar Rover Racing".

2. Add the `lunar-rover.png` image.

3. Scroll down and for Display page, click *Choose* and select *Activities*.

4. Click *Publish*.

5. For the second item, name it "Lunar Golf" and add the `lunar-golf.png` image,
    set the Content Display Page to *Activities* and click *Publish*.

6. For the third item, name it "Lunar Spelunking" and add the 
    `lunar-spelunking.png` image, set the Content Display Page to *Activities* 
    and click *Publish*.

## Housekeeping and Final Test [](id=housekeeping-and-final-test)

Now to add a few finishing touches.

1. Go back to the *Activities* page.

2. In the lefthand Asset Publisher, click the Portlet Title, and change it to
    *Choose an Activity* and click the checkmark.
    
3. On the right side, open the *Look and Feel Configuration* and change the
    *Decoration* to *Barebone*.
    
Now that your page looks great, test it out - 

1. Click on a title and that content will display in the publisher to the right.

![Figure x: Final Activities Page.](../../../images/001-activities-page.png)

Congratulations, you've finished creating the Activities page, and configured a
dynamic content display.
