# Localizing Site Administration Portlets [](id=localizing-site-administration-portlets-lp-6-2-develop-tutorial)

You may have noticed that your Site Administration portlets are missing that
super-fancy, must-have portlet title and description in Site Administration. In
addition, you may want to give your Site Administration portlets localized
titles and descriptions, to cater to your users' differing language needs. To
make your portlets look cool, or offer localized portlets within Site
Administration, you can create specially tailored descriptions and title keys in
separate `Language.properties` files for each portlet in your project. You'll
use the `javax.portlet.title` and `javax.portlet.description` language keys. The
following figure displays an example of localizing your portlets to Spanish: 

![Figure 1: You can localize portlets' titles and descriptions in Site Administration to any language, including Spanish.](../../images/portlet-title-and-description-es.png)

Let's get started by running through a tutorial to learn how to do this!

## Changing Site Administration Portlet Titles and Descriptions [](id=changing-site-administration-portlet-titles-and-des-lp-6-2-develop-tutorial)

For our tutorial, we'll configure your portlet project to display in Site
Administration, and then localize its title and description. We'll need to
create a resource bundle for each of them to specify their localized title and
description values. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** If your project only has one
 portlet, it's best to put your resource bundle directly in the `content`
 folder. Specifying your bundle in file `content/Language.properties` lets you
 leverage the Plugins SDK's language building capabilities, via right-clicking
 on the `Language.properties` file &rarr; *Liferay* &rarr; *Build Languages* in
 Developer Studio or executing `ant build-lang` from the terminal. 

---

Let's begin the tutorial to localize the title and description for a portlet: 

1. Configure the portlet to display in Site Administration. For this tutorial,
   we'll display them in the *Content* portion and give them an arbitrary
   *weight* value for determining where they're to be placed in the column with
   respect to the other portlets. These two configurations are specified within
   the `<control-panel-entry-category/>` and `<control-panel-entry-weight/>`
   elements of your project's `liferay-portlet.xml`.
   
    For example, the following code specifies these elements for an
    `eventlisting` portlet:

		<portlet>
			<portlet-name>eventlisting</portlet-name>
			<icon>/icon.png</icon>
			<control-panel-entry-category>site_administration.content</control-panel-entry-category>
			<control-panel-entry-weight>1.5</control-panel-entry-weight>
			...
		</portlet>

    Your portlet is now available in the *Site Administration* &rarr; *Content*
    menu. 

    ![Figure 2: Notice that the lower the weighted number, the higher the portlet is listed in the specified menu.](../../images/new-portlet-in-site-admin.png)

2. Create a namespaced folder to hold your portlet's resource bundle. It's a
   best practice to name the resource bundle folder based on the name of its
   portlet.
   
    For example, you could create a resource bundler folder called
    `docroot/WEB-INF/src/content/eventlisting` for an `eventlisting` portlet.

3. Create a `Language.properties` file in the resource bundle folder you just
   created. Specify the `javax.portlet.title` and `javax.portlet.description`
   language key/values in each of these `Language.properties` files. 

    For example, our example `eventlisting` portlet could have the following
    key/value pairs in its `content/eventlisting/Language.properties` file:

        javax.portlet.title=Event Listing Portlet
        javax.portlet.description=Lists important upcoming events.

4. Specify the resource bundle for the portlet within the `<resource-bundle>`
   element in the portlet project's `portlet.xml` file. The example
   `portlet.xml` file code snippet below demonstrates specifying the resource
   bundle for the `eventlisting` portlet: 

        <portlet>
            <portlet-name>eventlisting</portlet-name>
            ...
            <resource-bundle>content.eventlisting.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

5. Redeploy your portlet project.

6. Go to *Site Administration* &rarr; *Content* and select your portlet. Notice
   that your portlet and description match what you specified in your
   `Language.properties` file.

    ![Figure 3: Notice that your portlet title and description are visible.](../../images/portlet-title-and-description.png)

7. To provide your portlet in Liferay's supported languages, you'll need to
   build your portlet's languages. To do this in Eclipse, right-click your
   portlet's `content/[PORTLET_NAME]/Language.properties` file and select
   *Liferay* &rarr; *Build Languages*. You can also accomplish the same thing
   from the terminal by executing `ant build-lang`.
   
    **Note:** Building languages only builds the language properties files, but
    does not produce the translations within the language files. 

8. Now that you have the languages built, you can specify language keys for each
   of the supported language files that were generated. For example, the Spanish
   translation for the `eventlisting` portlet's previous title and description
   would be designated inside the `Language_es.properties` file as the
   following:

        javax.portlet.title=Lista de Eventos
        javax.portlet.description=Enumera los proximos eventos importantes.

9. Add `es` to your portal context in your URL to interface with the portal in
   Spanish. For example, your URL would start like this:

        http://localhost:8080/es/group/control_panel/...

    Portal's Site Administration page displays your portlet's localized title
    and description.  

    ![Figure 4: It's easy to localize titles and descriptions for multiple portlets in your project.](../../images/portlet-title-and-description-es.png)

You're becoming an expert localizer!

---

 ![Tip](../../images/tip-pen-paper.png)**Tip:** Do you know how your portlet
 title is processed? If your portlet doesn't define a resource bundle or
 `javax.portlet.title`, the portal container next checks the `<portlet-info>`
 and inner `<portlet-title>` node in the `portlet.xml` descriptor. If they're
 missing too, the `<portlet-name>` node value is rendered as portlet title. 

---

---

 ![Note](../../images/tip-pen-paper.png)**Note:** Be aware that using a Struts 
 portlet and referring to a `StrutsResource` bundle in your `portlet.xml`
 engages a different title and description algorithm. Titles and long titles are
 pulled using two different keys:

 - `javax.portlet.long-title` 
 - `javax.portlet.title`

---

Terrific! You've configured your portlet to display in the Site Administration
page, created a resource bundle specifically for your portlet, built your
portlet's languages, and provided a Spanish translation for your portlet's title
and description!
