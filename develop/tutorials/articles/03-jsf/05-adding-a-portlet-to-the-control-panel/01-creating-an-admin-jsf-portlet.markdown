# Creating an Admin JSF Portlet [](id=creating-an-admin-jsf-portlet)

Similar to what you completed when creating your Guestbook portlet, you'll
complete similar steps to create the Guestbook Admin portlet. You'll use the New
Liferay Portlet wizard, which creates various configuration files such as
`portlet.xml`, `liferay-portlet.xml`, and `liferay-display.xml`, or in this
case, adds entries to these files for your new admin portlet. In this learning
path, you'll create the new Guestbook Admin portlet, and then examine what's
different in your JSF portlet project. 

## Using the New Liferay Portlet Wizard [](id=using-the-new-liferay-portlet-wizard)

To create your new Guestbook Admin portlet, follow the steps below:

1. Right-click on your `guestbook-jsf-portlet` project and select *New* &rarr;
   *Liferay JSF Portlet*. 

2. Accept the defaults on the first screen, which includes setting your portlet
   class to the `GenericFacesPortlet` class. Click *Next* and fill in the
   *Portlet Info* section as follows:

    - **Name:** *guestbook-admin*
    
    - **Display Name:** *Guestbook Admin*
    
    - **Title:** *Guestbook Admin*

3. Under Resources, make sure the *Create view files* checkbox is selected and
   the View folder is set to `/views/guestbook-admin`. 

4. Change the View Template to *Liferay Faces Alloy*, and then click *Next*. 

5. For the Display Category field, select *category.hidden*. Then select the
   *Add to Control Panel* checkbox.

6. For the Entry Category, select *Site Administration - Content*. Leave the
   default Entry Weight at *1.5*. Then click *Finish*. 

What happens to your project when a new portlet is added? You'll examine a few
configuration files to find out. 

First, open the `docroot/WEB-INF/portlet.xml` file. Notice the new
`<portlet>` element has been added. Within this element are the
`<portlet-name>`, `<display-name>`, `<portlet-class>`, and `<portlet-info>`
sub-elements, which characterize the new Guestbook Admin portlet. You'll also
notice within the `<init-param>` element, the
`/views/guestbook-admin/view.xhtml` file is set as the default view. 

    <portlet>
        <portlet-name>guestbook-admin</portlet-name>
        <display-name>Guestbook Admin</display-name>
        <portlet-class>
            javax.portlet.faces.GenericFacesPortlet
        </portlet-class>
        <init-param>
            <name>javax.portlet.faces.defaultViewId.view</name>
            <value>/views/guestbook-admin/view.xhtml</value>
        </init-param>
        ...
        <portlet-info>
            <title>Guestbook Admin</title>
            <short-title>Guestbook Admin</short-title>
            <keywords></keywords>
        </portlet-info>
        ...
    </portlet>

The next configuration file you'll examine is the `liferay-portlet.xml` file.
While the `portlet.xml` file defines portlet attributes defined by the JSR-286
portlet specification, the `liferay-portlet.xml` file defines Liferay-specific
portlet attributes. 

    <portlet>
        <portlet-name>guestbook-admin</portlet-name>
        <icon>/icon.png</icon>
        <control-panel-entry-category>
            site_administration.content
        </control-panel-entry-category>
        <control-panel-entry-weight>1.5</control-panel-entry-weight>
        <requires-namespaced-parameters>
            false
        </requires-namespaced-parameters>
        <header-portlet-css>/css/main.css</header-portlet-css>
        <footer-portlet-javascript>
            /js/main.js
        </footer-portlet-javascript>
        <css-class-wrapper>guestbook-admin-portlet</css-class-wrapper>
    </portlet>

Notice the `<control-panel-entry-category>` element. This determines where this
portlet resides in the Control Panel. In this instance, you can find it by
navigating to *Admin* &rarr; *Site Administration* &rarr; *Content*. Also notice
the `<control-panel-entry-weight>` element. This value determines where your
portlet is located in the Site Administration menu's list of portlets. 

Another configuration file that was edited when you created the Guestbook Admin
portlet is the `liferay-display.xml` file. The following `<category>` element
was added: 

    <category name="category.hidden">
        <portlet id="guestbook-admin"></portlet>
    </category>

This element hides your Guestbook Admin portlet from the list of applications
that can be added to a portal page. This is appropriate for the Guestbook Admin
portlet because you only want it accessible from the Control Panel for
administrators. 

Lastly, the wizard created the `i18n.properties` file to house your Guestbook
Admin's language keys. For your new portlet, you will need to edit this file
to define a custom language key for your Guestbook Admin portlet. 

Open the `docroot/WEB-INF/src/i18n.properties` file and replace any existing
language keys with the following: 

    no-guestbooks-yet=No guestbooks yet ...

This new language key will be displayed by the Guestbook Admin portlet when no
guestbooks have been created yet. 

Now that your Guestbook Admin portlet is created, you'll need to add some
custom services to your `GuestbookLocalServiceImpl` to allow for editing and
deleting guestbooks. You'll do this next. 
