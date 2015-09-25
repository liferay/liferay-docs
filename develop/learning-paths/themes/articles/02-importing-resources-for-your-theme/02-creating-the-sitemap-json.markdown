# Creating the sitemap.json File [](id=creating-the-sitemap-json-file)

The `sitemap.json` file defines the layout for the web content, pages, and
portlets in your site. As the file extension suggests, `sitemap.json` uses JSON
syntax, so if you are familiar with JSON you should be able to navigate your way
through the sitemap fairly easy. If you are not familiar with JSON syntax, don't
worry, the sitemap will be explained in further detail.

Since the `sitemap.json` file was already created when you copied the contents
of the `resources-importer` folder, you don't need to do anything to implement.
Here's what it does. 

The code below adds a *Welcome* and *About Us* page using the default layout 
template and populates the pages with web content articles:


    {
            "layoutTemplateId": "1_column",
            "layouts": [
                    {
                            "name": "Welcome",
                            "title": "Welcome",
                            "friendlyURL": "/home",
                            "columns": [
                                    [
                                            "carousel.xml",
                                            "Welcome to the Lunar Resort.html",
                                            "Amenities.html",
                                            "2 column description.html"
                                    ]
                            ]
                    },
                    {
                            "name": "About Us",
                            "title": "About Us",
                            "friendlyURL": "/about-us",
                            "columns": [
                                    [
                                            "About Us Info.html"
                                    ]
                            ]
                    }
 
The sitemap starts out by defining a default layout template ID for the pages.
You can find layout template IDs in the `layouttpl` folder in your Liferay 
bundle. Next, the pages, referred to as `layouts` in the sitemap, are organized. 
The page is then given a name, a title, and a friendly URL.

Next, the web content is organized under the `columns` section. Columns refers
to the columns in the layout template. Since the *Welcome* page uses the default
`1_column` layout, the `layoutTemplateId` doesn't need to be specified. Since
the page uses a one column layout, the web content articles are all placed
within the same set of brackets`[`. As with an image in a web content
article, you reference the web content by the name of the file in the Resources
Importer.
 
The layout below adds an *Activities* page with a *Rover Racing* child page and
populates them with web content:

    {
    "name": "Activities",
    "title": "Activities",
    "friendlyURL": "/activities",
    "columns":[
                [
                    "Activities Info.html",
                    "Activities Links.html"
                ]
    ],
    "layouts":[
    {
        "name": "Rover Racing",
        "title": "Rover Racing",
        "friendlyURL": "/rover-racing",
        "columns": [
            [
                "Rover Racing Article.html"
            ]
        ]
    },
 
As you can see, to create a child page, you use the same `layouts` syntax that
you would use to create a parent page. The `layouts` configuration for the child
page is placed within the parent page's configuration. 

The following code adds two portlets to the page and defines its own layout 
template.

    {
        "name": "Collaboration",
        "title": "Collaboration",
        "friendlyURL": "/collaboration",
        "layoutTemplateId": "2_columns",
        "columns": [
            [
                {
                "portletId": "36"
                }
            ],
            [
                {
                "portletId": "115"
                }
            ]
        ]
    }
 
Portlets are defined within the layout template columns like the web content
articles, with just a slight variation in syntax. They are referenced by their 
portlet ID, which you can find in the `portlet-custom.xml` inside your Liferay 
bundle. A `layoutTemplateId` is set for the page because it does not use the 
default template ID that was defined at the top of the sitemap. 

The following code creates a page hidden from the navigation menu and adds a web
content article and a custom portlet to the page.

    {
        "name": "Reservation",
        "title": "Reservation",
        "friendlyURL": "/reservation",
        "hidden": "true",
        "columns":[     
                [
                        "Reservation Form.html",
                        {
                        "portletId": "guestbook_WAR_guestbookportlet"
                        }
                ]
        ]

    }

Portlet plugins are defined the same way core portlets, such as the *Web Content
Display* portlet, are defined, and their IDs are defined in their own
`portlet.xml` files. They follow an easy to remember pattern:
`portletName_WAR_projectName`. In order for the portlet to be assigned an ID it
has to be deployed first. You took care of this in the last section of the
Learning Path.

You should now have a better understanding of the `sitemap.json` file. There's
one last file to configure, `liferay-plugin-package.properties`, which you'll
work with in the next section.
