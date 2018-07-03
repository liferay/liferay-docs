# Adding Templates with Structures [](id=adding-templates)

To better understand templates, now you'll create a structure and an associated
template. First create the structure:

1.  Go to *Content* &rarr; *Web Content* from Site Administration page and open
    the *Structures* tab.

2.  Click the *Add* button (![Add Structure](../../../../../images/icon-add.png)).

3.  Name the structure *News Article* and add the following fields:

	| Field Type | &nbsp;Field Label | &nbsp;Name |
	--------- | ---------- | ---------- |
	Text  | &nbsp;Title | &nbsp;`title` |
	Text Box | &nbsp;Abstract | &nbsp;`abstract` |
	Image | &nbsp;Image | &nbsp;`image` |
	HTML | &nbsp;Body | &nbsp;`body` |

5.  Click *Save*.

Now create the template and connect it to the structure.

1.  From the *Web Content* page, go to the *Templates* tab.

2.  Click the *Add* button (![Add Template](../../../../../images/icon-add.png)).

3.  Enter the name *News Article*.

4.  Open *Details* and make sure FreeMarker is selected as the script language (it's the default).

5.  Click *Select* under *Structure*.

6.  Choose the *News Article* structure.

7.  In the *Script* area, find the *Fields* label on the left and click on
    *Title*, *Abstract*, *Image* and *Body* into the editor area. It should
    look like this:
    
        ${title.getData()}
        ${abstract.getData()}
        <#if image.getData()?? && image.getData() != ""> <img alt="${image.getAttribute("alt")}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" /> </#if>
        ${body.getData()}
 

8.  Next, add heading and `<p>` tags and align the image to center to  style your
    elements like this:
    
        <h1>${title.getData()}</h1>
        <p>${abstract.getData()}</p>
        <#if image.getData()?? && image.getData() != ""> <img alt="${image.getAttribute("alt")}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" align="center" /> </#if>
        <p>${body.getData()}</p>

8. Click *Save*.

To finish it up, add some content:

1.  Go to the *Web Content* tab.

2.  Click on the *Add* button (![Add](../../../../../images/icon-add.png)) and
    select *News Article*.
 
3. Insert some content and publish!

![Figure 1: The Lunar Resort News Article is shaping up!](../../../../../images/web-content-structures-templates-completed.png)
