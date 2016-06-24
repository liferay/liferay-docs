# Updating the Resources Importer

In the last section of the Learning Path you continued the upgrade process for
the lunar resort theme and updated the theme templates. In this portion of the
Learning Path, you'll make the required changes to the resources importer.

+$$$

**Note:** The resources importer articles have been slightly modfied due to a
known issue [LPS-64859](https://issues.liferay.com/browse/LPS-64859). The
articles in the 6.2 Learning Path have links to pages in the layout of the site.
Due to the order in which the pages and articles are imported, this causes a
null pointer exception. To avoid this issue, the links have been removed from
the articles.

$$$

Updates have been made to the importer, causing class names, the directory
structure, and web content structures to change.

Follow the steps below to make the updates to the resources importer for the 
lunar resort.

## Updating liferay-plugin-package.properties

First off you'll need to update the `liferay-plugin-package.properties` file to
have the proper settings:

1.  Open `liferay-plugin-package.properties` in your `src/WEB-INF` directory and
    remove the `required-deployment-contexts=\    resources-importer-web` line.
    
    The resources importer has been reconfigured as an osgi module that is
    available to you by default in Liferay, so this line is no longer needed.

2.  Update the `resources-importer-target-class-name` value to the following:

        com.liferay.portal.kernel.model.Group
        
    The class has been reogranized in Liferay 7, so this is now the required
    full class name for the model group.
    
Now that the resources importer is configured properly, you can move on to
updating the web content.

## Updating the Web Content

In Liferay 7, all web content articles require a structure and a template.
You'll need to update the current web content articles to use a structure and a
matching template. 

### Updating the Directory Structure

In Liferay 7, basic web content articles have a Basic Web Content structure and
template that provides the essentials needed to create and render the article's 
content.

You'll create your own version of the Basic Web Content structure and template
for the lunar resort theme's articles.

Follow the steps below to reorganize the basic web content articles:

1.  Create a `Basic Web Content` folder in the 
    `src/resources-importer/journal/articles/` directory.
    
2.  Move all of the basic HTML articles into the `Basic Web Content` folder you
    just created.
    
3.  Add a matching `Basic Web Content` folder to the 
    `resources-importer/journal/templates/` directory.
    
    Now that you have the folders setup for the new structure and templates, you
    can create the structure and template next.

4.  Create a `Basic Web Content.json` file in the 
    `resources-importer/journal/structures/` directory and add the following 
    code to it:
    
        {
            "availableLanguageIds": [
                "en_US"
            ],
            "defaultLanguageId": "en_US",
            "fields": [
                {
                    "label": {
                        "en_US": "Content"
                    },
                    "predefinedValue": {
                        "en_US": ""
                    },
                    "style": {
                        "en_US": ""
                    },
                    "tip": {
                        "en_US": ""
                    },
                    "dataType": "html",
                    "fieldNamespace": "ddm",
                    "indexType": "keyword",
                    "localizable": true,
                    "name": "content",
                    "readOnly": false,
                    "repeatable": false,
                    "required": false,
                    "showLabel": true,
                    "type": "ddm-text-html"
                }
            ]
        }     

    This structure simply identifies the language and field settings for the 
    articles, and specifies a `name` to identify the content. In previous 
    versions of Liferay, article structures were XML, now they must be JSON. Now 
    that you have the structure created, you can create the matching template 
    next.

5.  Create a `Basic Web Content.ftl` template file in the 
    `resources-importer/journal/templates/Basic Web Content/`
    folder you just created and add the following code to it:

        ${content.getData()}

This accesses the article's content by the `name` defined in the structure, 
`content` in this case, and renders the HTML data. Now that you have the basic
web content structure and template created, all that's left to update is the
article type.

### Updating the Web Content Articles to XML

In 6.2, HTML articles were an acceptable type for the resources importer. In
Liferay 7, all articles must be of type XML. This migration is easier then it
may sound for basic web content. You just need to wrap the HTML in 
`<![CDATA[...]]>` tags.

For example, to update the `2 column description.html` article to XML, you would
write the following in a `2 column description.xml` file:

    <?xml version="1.0"?>

    <root available-locales="en_US" default-locale="en_US">
	    <dynamic-element name="content" type="text_area" 
	    index-type="keyword" index="0">
		    <dynamic-content language-id="en_US">
			    <![CDATA[
				    <div class="container-fluid">
	    <div class="col-md-4" id="columnLeft">Out of This World</div>
	    <div class="col-md-8" id="columnRight">Come to the Lunar Resort and 
	    live out your childhood dream of being an astronaut on the Moon. If 
	    that's not enough incentive, you'll enjoy a luxurious 3 day 2 night 
	    stay in our fabulous Lunar accomodations. Enjoy a round of Lunar 
	    Golf on our one of a kind course. Have a blast on our Rover Racing 
	    track. Make your reservation now. The rest of your life starts 
	    today!</div>
    </div>
    ]]>
		    </dynamic-content>
	    </dynamic-element>
    </root>

You may have also noticed that the `<div>` classes have been changed. The 
`2 column description.xml` article shown above illustrates the updated Bootstrap 
grid system for  Bootstrap 3. Instead of using the `span[number]` class to 
designate a column width, the `col-md-[number]` class is now used to designate 
column width. The total amount for the column widths must still add up to 12.

That's all that is needed for most of the basic web content articles. To make 
the article migration run as smoothly as possible, you can replace the HTML web 
content articles with the updated XML ones in the 
`resources-importer/journal/articles/Basic Web Content/` directory of the files 
you downloaded at the beginning.

There is one last update you'll need to make to one of the web content articles
which you can read about more next.

### Updating the Reservation Form's Bootstrap

Liferay 7's UI is built with a design language, known as [Lexicon](http://liferay.github.io/lexicon/).
When building UI, it is recommended that you use Lexicon to follow the design
patterns that Liferay has created.

The reservation form for the lunar resort theme used Bootstrap 2's design 
language for the 6.2 themes Learning path. Lexicon is an extension of Bootstrap 
3, and uses updated patterns and classes that you should be aware of. To 
follow best practices for Liferay 7, this form should be updated to follow 
Lexicon's design language.

Follow the step below to update the reservation form:

1.  Open the `Reservation Form.xml` article in the 
    `/src/resources-importer/journal/articles/Basic Web Content/` directory and
    replace the contents with the markup below:
    
    <?xml version="1.0"?>
    
    <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area" 
            index-type="keyword" index="0">
                    <dynamic-content language-id="en_US">
                            <![CDATA[
                                    <p>Thanks for choosing to stay at the 
                                    Liferay Lunar Resort! Please fill out the 
                                    form below to book your stay. We know you 
                                    have a choice in where to stay on the Moon... 
                                    oh wait no you don't. Thanks for picking us 
                                    anyways. We'll see you soon on the Moon!</p>
    <form role="form-horizontal">
            <fieldset>
              <legend>Reservation Form</legend>
              <div class="form-group">
                  <label for="inputName">Name</label>
                  <input type="text" id="inputName" class="form-control" 
                  placeholder="Enter your Name here" required="required">
              </div>
              <div class="form-group">
                  <label for="inputEmail">Email</label>
                  <input type="email" id="inputEmail" class="form-control" 
                  placeholder="Enter your E-Mail here" required="required">
              </div>
              <div class="form-group">
                      <button type="submit" class="btn btn-primary">Submit
                      </button>
              </div>
            </fieldset>
    </form>
    
    <p style="padding-bottom:25px;">Thanks again for booking with Liferay. When 
    you book with Liferay, you remember your stay. Please take a moment to fill 
    out our guestbook below.</p>
                            ]]>
                    </dynamic-content>
            </dynamic-element>
    </root>

The `control-group` classes were updated to `form-group` classes. The 
The `control-label` classes were removed from the `<label>` elements. The 
`<div class=""controls>` elements were removed. A `form-control` class was added 
to each `<input>` element. Finally, the `btn-primary` class was added to the 
submit button for the form, to give it more emphasis and contrast.

Now that the basic web content is updated, all that is left to update is the
carousel article's structure and template.

### Updating the Carousel's Structure and Template

The carousel article uses a more advanced structure and template than the basic 
web content. Luckily, the carousel article is already XML, so it doesn't need 
updated.

Follow these steps to migrate the 6.2 carousel structure and template to 
Liferay 7:

Liferay 6.2 used AlloyUI 2.0.x, Liferay 7 uses Alloy UI 3.0.x. There has been a
slight syntax change to the AlloyUI carousel between the two versions. Note that
support of HTML markup within the carousel images has been removed, causing the 
image links to no longer work. Make the changes below to update the carousel:

1.  Open the `carousel.vm` template in the `templates/carousel/` directory and
    replace all instances of `aui-carousel-item` with `image-viewer-base-image`
    and save the changes.
    
    Now that the template is updated, you can update the structure next. The
    carousel structure is XML and, as mentioned earlier, all structures must be
    JSON in Liferay 7.

2.  Open the `carousel.xml` structure in the `journal/structures/` directory and
    replace the contents with the JSON structure below:
    
        {
            "availableLanguageIds": [
                "en_US"
            ],
            "defaultLanguageId": "en_US",
            "fields": [
                {
                    "label": {
                        "en_US": "image"
                    },
                    "predefinedValue": {
                        "en_US": ""
                    },
                    "style": {
                        "en_US": ""
                    },
                    "tip": {
                        "en_US": "Image to use in the carousel."
                    },
                    "dataType": "image",
                    "fieldNamespace": "ddm",
                    "indexType": "keyword",
                    "localizable": true,
                    "name": "image",
                    "readOnly": false,
                    "repeatable": true,
                    "required": false,
                    "showLabel": true,
                    "type": "ddm-image",
                    "nestedFields": [
                        {
                            "label": {
                                "en_US": "content"
                            },
                            "predefinedValue": {
                                "en_US": ""
                            },
                            "style": {
                                "en_US": ""
                            },
                            "tip": {
                                "en_US": "This is text made availability to 
                                users with accessibility issues. Screen readers 
                                will be able to read this text."
                            },
                            "dataType": "html",
                            "fieldNamespace": "ddm",
                            "indexType": "keyword",
                            "localizable": true,
                            "name": "content",
                            "readOnly": false,
                            "repeatable": false,
                            "required": false,
                            "showLabel": true,
                            "type": "ddm-text-html"
                        },
                        {
                            "label": {
                                "en_US": "link"
                            },
                            "predefinedValue": {
                                "en_US": ""
                            },
                            "style": {
                                "en_US": ""
                            },
                            "tip": {
                                "en_US": "A URL to use for this image. Be sure 
                                to include the \"http://\" at the beginning."
                            },
                            "dataType": "string",
                            "indexType": "keyword",
                            "localizable": true,
                            "name": "link",
                            "readOnly": false,
                            "repeatable": false,
                            "required": false,
                            "showLabel": true,
                            "type": "text"
                        }
                    ]
                }
            ]
        }
        
The JSON structure above was created by manually recreating the structure in
Liferay 7. You can use the same process to convert your deprecated XML 
structures to JSON.

<!--Note that Rob said he would try to work on a automated process for this 
conversion, but for now this is the best approach-->

The resources importer is fully upgraded! The lunar resort theme is ready for
Liferay 7!

Run `gulp deploy` to build and deploy the theme to the app server you defined at
the beginning.

