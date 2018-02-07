# Creating Templates [](id=creating-templates)

<div class="learn-path-step">
    <p>Creating Content<br>Step 3B of 3D</p>
</div>

Structures, like the one you created in the previous step, need templates to
style and display their items. Now you'll use the
[FreeMarker](http://freemarker.apache.org/) templating language to create
a template. 

Follow these steps to create your template: 

1.  Open the *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)) and select *The Lunar 
    Resort* &rarr; *Content* &rarr; *Web Content*. 

2.  At the top-right of the screen, select *Options* 
    (![Options](../../../../images/icon-options.png)) &rarr; *Templates*. 

![Figure x: Select Templates from the menu.](../../../../images/001-menu-templates.png)

3.  Click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) at the bottom-right of the page to 
    begin creating a new template. 

4.  Name the template *Lunar Guides List*. 

5.  Now you must select a structure to link with this template. Open the
    *Details* section, click the *Select* button under *Structure*, and select 
    the *Lunar Guides List* structure. Click *OK* when prompted. 

![Figure x: Template details.](../../../../images/001-template-details.png)

6.  Next, select the template's language. Still in the Details section, make 
    sure *FreeMarker* is selected for the Language field (it should be 
    selected by default). FreeMarker uses the FreeMarker Template Language 
    (FTL), which uses HTML, interpolations (`${...}`), and FTL tags. FreeMarker 
    templates can also include CSS. For more information on FTL, see 
    [the FreeMarker documentation](http://freemarker.apache.org/docs/dgui_quickstart_template.html). 
    Note that @product@ also supports Velocity and Extensible Stylesheet 
    Language. 

7.  Now you're ready to create the script. First you'll insert the CSS. This 
    example uses a four column, two row grid layout to define the field 
    positions. The content appears only in columns two and four--the other two 
    columns exist for spacing. This example also defines the text heading
    styles. In the *Script* section, replace the code in the editor with this
    code: 

        <style>
           .wrapper {
          display: grid;
          grid-template-columns: 300px, 300px, 300px, 300px;
          grid-gap: 10px;
          grid-auto-rows: minmax(auto, auto);
          text-align: center;
        }

        h1 {
            text-align: center;
        }

        h2 { 
           position: relative;
           color: black;
        }

        .item-one {
          grid-column: 2;
          grid-row: 1;
          max-width: 355px;
        }

        .item-two { 
          grid-column: 4;
          grid-row: 1;
          max-width: 355px;
        }

        .item-three {
          grid-column: 2;
          grid-row: 2;
          max-width: 355px;
        }

        .item-four {
          grid-column: 4;
          grid-row: 2;
          max-width: 355px;
        }
        </style>    

8.  Now you're ready to create the template's HTML. Like the structure, you can
    use the sidebar to the editor's left to insert fields in the editor. Doing
    so automatically inserts fields with the appropriate variables. *General
    Variables* provides quick access to universal information for your @product@
    instance. *Fields* provides access to the variables that your structure
    defines. Mousing over a field displays its variable name. Clicking a field
    adds the code in the editor that retrieves that field's data. 

    ![Figure x: A field's tooltip shows that field's variable name.](../../../../images/001-field-mouse-over.png)

    In this example, however, you'll copy and paste the following code. This
    code gets the field values that the user entered, styles the text and
    images, and puts all the information into divs as defined in your CSS. Add
    this code in the editor, below the closing `</style>` tag: 

        <h1>${title.getData()}</h1>

        <div class="wrapper">
          <a class="item-one" href="${name1.link1.getFriendlyUrl()}">
          <h2>${name1.getData()}</h2>
            <#if name1.image1.getData()?? && name1.image1.getData() != "">
              <img alt="${name1.image1.getAttribute("alt")}" src="${name1.image1.getData()}" />
            </#if>
          </a>

          <a class="item-two" href="${name2.link2.getFriendlyUrl()}">
          <h2>${name2.getData()}</h2>
            <#if name2.image2.getData()?? && name2.image2.getData() != "">
              <img alt="${name2.image2.getAttribute("alt")}" src="${name2.image2.getData()}" />
            </#if>
          </a>

        <hr />

          <a class="item-three" href="${name3.link3.getFriendlyUrl()}">
          <h2>${name3.getData()}</h2>
            <#if name3.image3.getData()?? && name3.image3.getData() != "">
              <img alt="${name3.image3.getAttribute("alt")}" src="${name3.image3.getData()}" />
            </#if>
          </a>

          <a class="item-four" href="${name4.link4.getFriendlyUrl()}">
          <h2>${name4.getData()}</h2>
            <#if name4.image4.getData()?? && name4.image4.getData() != "">
              <img alt="${name4.image4.getAttribute("alt")}" src="${name4.image4.getData()}" />
            </#if>
          </a>

        </div>

9.  Click *Save*. 

Great! Now you have a template for displaying your structure's items. Next, 
you'll create some content that leverages your structure and template. 
