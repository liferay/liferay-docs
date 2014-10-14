# Anatomy of a Portlet [](id=anatomy-of-a-portlet)

A portlet project is made up of at least three components:

1.  Java Source. 

2.  Configuration files. 

3.  Client-side files (`.jsp`, `.css`, `.js`, graphics files, etc.). 

When using Liferay's Plugins SDK, these files are stored in a standard directory
structure:

- `PORTLET-NAME/`
    - `build.xml`
    - `docroot/`
        - `css/`
        - `js/`
        - `WEB-INF/`
            - `src/` - this folder is not created by default.
            - `liferay-display.xml`
            - `liferay-plugin-package.properties`
            - `liferay-portlet.xml`
            - `portlet.xml`
            - `web.xml` - this file is not created by default.
        - `icon.png`
        - `view.jsp`

The portlet we just created is fully functional and deployable to your Liferay
instance. 

By default, new portlets use the MVCPortlet framework, a light framework that
hides part of the complexity of portlets and makes the most common operations
easier. The default MVCPortlet project uses separate JSPs for each portlet
mode: each of the registered portlet modes has a corresponding JSP with the
same name as the mode. For example, 'edit.jsp' is for edit mode and 'help.jsp'
is for help mode.

The **Java Source** is stored in the `docroot/WEB-INF/src` folder. 

The **Configuration Files** are stored in the `docroot/WEB-INF` folder. Files
stored here include the standard JSR-286 portlet configuration file
`portlet.xml`, as well as three optional Liferay-specific configuration files.
The Liferay-specific configuration files, while optional, are important if your
portlets will be deployed on a Liferay Portal server. Here's a description of
the Liferay-specific files:

- `liferay-display.xml`- Describes the category the portlet appears under in the
  *Add* menu of the Dockbar (the horizontal bar that appears at the top of the
  page to all logged-in users). 
- `liferay-portlet.xml`- Describes Liferay-specific enhancements for JSR-286
  portlets installed on a Liferay Portal server. For example, you can set
  an image icon to represent the app, trigger a job for the scheduler, and much
  more. A complete listing of this file's settings is in its DTD in the
  `definitions` folder in the Liferay Portal source code. 
- `liferay-plugin-package.properties`- Describes the plugin to Liferay's hot
  deployer. You can configure Portal Access Control List (PACL) properties,
  `.jar` dependencies, and more. 

**Client Side Files** are the `.jsp`, `.css`, and `.js` files that you
write to implement your portlet's user interface. These files should go in the
`docroot` folder, either in the root of the folder or in a folder structure that
makes sense for your application. Remember, with portlets you're only dealing
with a portion of the HTML document that is getting returned to the browser. Any
HTML code in your client side files must be free of global tags like `<html>`
or `<head>`.  Additionally, namespace all CSS classes and element IDs to prevent
conflicts with other portlets. Liferay provides two tools, a taglib and API
methods, to generate a namespace for you. 

## A Closer Look at the My Greeting Portlet [](id=a-closer-look-at-the-my-greeting-portlet)

If you're new to portlet development, this section will enhance your
understanding of portlet configuration options. 

**docroot/WEB-INF/portlet.xml**

In the Plugins SDK, the portlet descriptor's default content looks like this
(shown using Developer Studio's Portlet Application Configuration Editor):

![Figure 3.3: Portlet XML file of the My Greeting portlet](../../images/02-portlet-development-4.png)

Here's a basic summary of what each element represents:

- `portlet-name`: Contains the portlet's canonical name. Each portlet name is
  unique within the portlet application (that is, within the portlet plugin). In
  Liferay Portal, this is also referred to as the portlet ID. 
- `display-name`: Contains a short name that's shown by the portal whenever
  this application needs to be identified. It's used by `display-name` elements.
  The display name need not be unique. 
- `portlet-class`: Contains the fully qualified name of the class that handles
  invocations to the portlet. 
- `init-param`: Contains a name/value pair as an initialization parameter of
  the portlet. 
- `expiration-cache`: Indicates the time, in seconds, after which the portlet
  output expires. A value of `-1` indicates that the output never expires. 
- `supports`: Contains the supported mime-type, and indicates the portlet modes
  supported for a specific content type. The concept of "portlet modes" is
  defined by the portlet specification. Modes are used to separate certain views
  of the portlet from others. The portal is aware of the portlet modes and
  provides generic ways to navigate between them (for example, using links in
  the box surrounding the portlet when it's added to a page), so they're useful
  for operations that are common to all or most portlets. The most common usage
  is to create an edit screen where each user can specify personal preferences
  for the portlet. All portlets must support the view mode. 
- `portlet-info`: Defines information that can be used for the portlet title-bar
  and for the portal's categorization of the portlet. The JSR-286 specification
  defines a few resource elements that can be used for these purposes: `title`,
  `short-title`, and `keywords`. You can either include resource elements 
  directly in a `portlet-info` element or you can place them in resource
  bundles.

    Specifying the information directly into the `portlet-info` element in your
    `portlet.xml` file is straightforward. For example, to you could specify a
    weather portlet's information, like this:

        <portlet>
            ...
            <portlet-info>
                <title>Weather Portlet</title>
                <short-title>Weather></short-title>
                <keywords>weather,forecast</keywords>
            </portlet-info>
            ...
        </portlet>

    Alternatively, you can specify this same information as resources in a
    resource bundle file for your portlet. For example, you could create file
    `docroot/WEB-INF/src/content/Language.properties`, in your portlet project,
    to specify your portlet's title, short title, and keywords:

        # Default Resource Bundle
        #
        # filename: Language.properties
        # Portlet Info resource bundle example
        javax.portlet.title=Weather Portlet
        javax.portlet.short-title=Weather
        javax.portlet.keywords=weather,forecast

    To use the resource bundle, you'd reference it in your `portlet.xml`
    file, in your `portlet` element just before your `portlet-info` element:

        <portlet>
            ...
            <resource-bundle>content.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

    As a best practice, if you're not planning on supporting localized title,
    short title, and keywords values for your portlet, simply specify them
    within the `<portlet-info>` element in your `portlet.xml` file. Otherwise,
    if you're ready to provide localized values, use a resource bundle for 
    specifying your default values and specify the localized values in separate
    resource bundles.

    +$$$
    
    **Note:** You should not specify values for a portlet's title, short title,
    and keywords in both a portlet's `<portlet-info>` element in `portlet.xml`
    and in a resource bundle. But if by accident you do, the values in the
    resource bundle take precedence over the values in the `<portlet-info>`
    element.

    $$$

    Specifying *localized* values for your portlet's title, short title, and
    keywords in resource bundles is easy. For example, if you're supporting
    German and English locales, you'd create `Language_de.properties` and
    `Language_en.properties` files, respectively, in your portlet's
    `docroot/WEB-INF/src/content/` directory. This is the same directory as your
    default resource bundle file `Language.properties`. The contents of the
    German and English resource bundles may look like the following: 

        # English Resource Bundle
        #
        # filename: Language_en.properties
        # Portlet Info resource bundle example
        javax.portlet.title=Weather Portlet
        javax.portlet.short-title=Weather
        javax.portlet.keywords=weather,forecast
        
        # German Resource Bundle
        #
        # filename: Language_de.properties
        # Portlet Info resource bundle example
        javax.portlet.title=Wetter Portlet
        javax.portlet.short-title=Wetter
        javax.portlet.keywords=wetter,vorhersage

    You'd reference your default bundle and these localized bundles in your
    `portlet.xml` file, like this:

        <portlet>
            ...
            <resource-bundle>content.Language</resource-bundle>
            <resource-bundle>content.Language_de</resource-bundle>
            <resource-bundle>content.Language_en</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

    If you're mavenizing your portlet, make sure to copy your `content` folder
    into your portlet's `src/main/webapp/WEB-INF/classes` folder. 

    For more information, see the JSR-286 portlet specification, at
    [http://www.jcp.org/en/jsr/detail?id=286](http://www.jcp.org/en/jsr/detail?id=286).

- `security-role-ref`: Contains the declaration of a security role reference in
  the code of the web application. Specifically in Liferay, the `role-name`
  references which roles can access the portlet. 

**docroot/WEB-INF/liferay-portlet.xml**: In addition to the standard
`portlet.xml` options, there are optional Liferay-specific enhancements for Java
Standard portlets that are installed on a Liferay Portal server. By default, the
Plugins SDK sets the contents of this descriptor, as shown in Developer Studio:

![Figure 3.4: Liferay-Portlet XML file of the My Greeting portlet](../../images/02-portlet-development-5.png)

Here's a basic summary of what some of the elements represent. 

- `portlet-name`: Contains the canonical name of the portlet. This needs to be
  the same as the `portlet-name` specified in the `portlet.xml` file. 
- `icon`: Path to icon image for this portlet. 
- `instanceable`: Indicates whether multiple instances of this portlet can
   appear on the same page. 
- `header-portlet-css`: The path to the `.css` file for this portlet to include
  in the `<head>` tag of the page. 
- `footer-portlet-javascript`: The path to the `.js` file for this portlet, to
  be included at the end of the page before the `</body>` tag. 

There are many more elements that you should be aware of for more advanced
development. They're all listed in the DTD for this file, which is found in the
`definitions` folder in the Liferay Portal source code.
