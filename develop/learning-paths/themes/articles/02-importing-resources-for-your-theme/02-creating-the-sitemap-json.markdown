# Creating the sitemap.json

The `sitemap.json` defines the layout for the web content, pages, and portlets 
in your site. As the file extension suggest, `sitemap.json` uses JSON syntax, so
if you are familiar with JSON you should be able to navigate your way through
the sitemap fairly easy. If you are not familiar with JSON syntax do not worry,
the sitemap will be explained in further detail.

1. Right-click on the `resources-importer` folder and select *New*&rarr;*File*.

2. Enter *sitemap.json* for the *File name* and click *Finish*.

3. Add the following code to the file:

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
                },
                
                {
                        "name": "Facilities",
                        "title": "Facilities",
                        "friendlyURL": "/facilities",
                        "columns":[
                                [
                                        "Lunar Accomodations.html"
                                ]
                        ]
                },
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
                                        {
                                        "name": "Lunar Golf",
                                        "title": "Lunar Golf",
                                        "friendlyURL": "/lunar-golf",
                                        "columns": [
                                                [
                                                "Lunar Golf Article.html"
                                                ]
                                        ]
                                        },
                                        {
                                        "name": "Lunar Spelunking",
                                        "title": "Lunar Spelunking",
                                        "friendlyURL": "/lunar-spelunking",
                                        "columns": [
                                                [
                                                "Lunar Spelunking Article.html"
                                                ]
                                        ]
                                        }
                                ]
                        },
                {
                        "name": "Dining",
                        "title": "Dining",
                        "friendlyURL": "/dining",
                        "columns":[
                                [
                                        "Dining.html"
                                ]
                        ]
                },
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
                },
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
        ]
        }

4. Save the file.

The code below adds a *Welcome* and *About Us* page using the default layout 
template, and populates the pages with web content articles:


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
`1_column` layout, the `layoutTemplateId` doesn't need specified for the page. 
Since the page uses a one column layout, the web content articles are all placed 
within the same set of brackets`[`. As with an image in a web content article, 
you reference the web content by the name of the file in the Resources Importer.
 
The layout below adds a *Activities* page with a *Rover Racing* child page and 
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
                
As you can see, to create a child page you use the same `layouts` syntax that 
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
portlet ID which you can find in the `portlet-custom.xml` inside of your Liferay 
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

Custom portlets are defined the same way core portlets, such as the *Web Content 
Display* portlet, are defined. The portlet ID is not defined in the 
`portlet-custom.xml` though. Luckily an ID for a custom portlet follows an easy 
to remember pattern: `portletName_WAR_projectName`. In order for the portlet to
be assigned an ID it has to be deployed first. You took care of this in the last
section of the learning path.

Hopefully now you have a better understanding of the `sitemap.json`. There's one
last file to configure, `liferay-plugin-package.properties`, which you'll take 
care of in the next section.