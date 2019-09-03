# Building the Lunar Resort's content with Fragments

Let's recap what you've completed so far:

- Set up the theme to extend Atlas
- Configured the Header and logo
- Configured the default navigation
- Configured the Footer and embedded a navigation portlet

If you deploy the theme as is you may notice that the pages are a bit bare at 
the moment. You've configured the key aspects of the theme, but the majority of 
the sites content, the portion that lies between the Header and the Footer, will 
be created with [Fragments](). 
Fragments are independent chunks of code containing JavaScript, CSS, and HTML. 
Although you can manually define your content in theme templates, that can 
clutter up your templates pretty quick and can result in large files. The other 
issue of manually writing code is the inherent repetition of writing the same or 
similar pieces of code that are required for multiple elements, and then 
modifying it for each page. Fragments are the perfect solution to this common 
problem. You can create a fragment once, make aspects of it editable, and then 
reuse it across multiple content pages and just edit the portions that need 
changed for each page. Fragments are somewhat out of the scope of these 
tutorials, so this section only briefly covers them to demonstrate their 
workflow as it pertains to the look and feel of the page. 

As an example, you'll create a hero image fragment that will be used on every 
page except for the home page. In this case, the hero image has two key 
requirements:

- The image must be configurable and of course responsive
- There must be a configurable page title text element that the 
  Administrator can update for each page
  
Follow these steps to create the fragment:

1.  Upload an image to use as a placeholder in the fragment. This produces a 
    more accurate preview. Open the Control Menu and navigate to 
    *Site Builder* &rarr; *Page Fragments* and select the *Resources* tab. Click 
    the Add button and upload the 
    `lunar-hero-image.png` asset from the `lunar-resort-build/assets/images/` 
    folder.

2.  Open the Control Menu and navigate to *Site Builder* &rarr; *Page Fragments* 
    and click the Add button next to *COLLECTIONS*. Enter 
    *Lunar Resort Fragments* for the Name and Save. 

3.  Open the Control Menu and navigate to *Site Builder* &rarr; *Page Fragments* 
    and click the Add button and select *Section*. Enter `Hero Image` for the 
    Name and Save. You're presented with 4 panes: an HTML editor, a CSS editor, 
    a JavaScript editor, and a preview.
    
    ![Figure: 1 The Fragments editor provides three specialized editor windows and a preview pane.]()
    
4.  Copy the text below into the HTML pane, between the existing fragment 
    `<div>` tags. Note the `<lfr-editable>` tag used below. This lets a Site 
    Administrator replace the content, the image and text in this case, with 
    their own. 

    ```html
    <div class="jumbotron jumbotron-fluid">
      <lfr-editable id="memberText" type="image">
        <img class="img-fluid" src="[resources:lunar-hero-image.png]">
      </lfr-editable>
      <div class="title-text text-center mb-0">
        <p><lfr-editable id="text01" type="text">lunar resort</lfr-editable></p>
      </div>
    </div>
    ```

5.  Copy the CSS below into the CSS pane. Make sure to replace the `fragment*` 
    classes with the number that identifies your fragment. When the fragment is 
    edited on the fly, these styles will remain, ensuring consistency across 
    pages:

    ```css
    .fragment_36416 .jumbotron{
      display: inline-block;
      margin:0;
      padding:0;
      position: relative;
    }

    .fragment_36416 img {
      display: block;
      height: 400px;
      object-fit: cover;
      width: 100%;
    }

    .fragment_36416 .title-text {
      background-color: rgba(0,0,0,0.4);
      border-radius: 15px;
      color: #FFF;
      font-family: Source Sans Pro Heavy;
      font-size: 2.5em;
      left: 50%;
      position: absolute;
      text-transform: uppercase;
      top: 50%;
      transform: translate(-50%, -50%);
      width: 400px;
    }
    ```
    
6.  Click the *Publish* button to save the changes.
<!--
7.  Now you need some pages to add the content to. Open the Control Menu and 
    navigate to *Site Builder* &rarr; *Pages* &rarr; *Public Pages*, click the 
    Add button to add a public page. Select *Content Page* as the page type, and 
    enter `Home` for the Name. Repeat this step to create pages for *About*, 
    *Activities*, *Dining* and *Accommodations*.
    
8.  Import the fragments bundle for the remaining fragments and add them to each 
    page.
    -->
    
    In the next section you'll create a color scheme and import the remaining 
    fragments and pages. 