# Setting Theme Settings for an Individual Page [](id=setting-theme-settings-for-an-individual-page)

For most pages, you'll set the default theme settings so they work for the
majority of your site. For some pages, however, you may want settings different
from the defaults. This is where individual page settings come into play. 

Follow these steps to configure the theme settings for the *Welcome* page:

1.  Navigate to the *Welcome* page for the theme and sign in as an
    Administrator. 

2.  Select the *Edit* button and go to the *Look and Feel* section.

3.  Click the radio button to *Define a specific look and feel for this page*.

4.  Set *show-page-title* to *False*. Verify that *Show Breadcrumbs* is already
    *False*.

5.  Click *Save*.

6.  Reload the page to see the changes you made.

You'll notice that *show-page-title* matches the `key` that was set up in 
`liferay-look-and-feel.xml`, while the other theme settings do not; this is 
because they are using language keys. As a best practice you should configure 
all your theme settings to use language keys. If you open `portal-impl.jar` 
in your `liferay-portal-[version]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/` 
folder, you'll find the `language_en.properties` file inside the
`portal-impl/content` folder. This file holds all the English language keys that
Liferay Portal uses.

1. Search for *show-breadcrumbs* and *portlet-setup-show-borders-default* in the
   `language_en.properties` file and you'll see the English translations for the 
   theme settings that are available to Liferay by default.

In order to include a language key for *show-page-title* you'll need to create a 
hook to modify the `language_en.properties` file. You'll learn how to do this 
in the next section.
