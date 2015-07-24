# Setting Theme Settings for an Individual Page

For most pages you will probably use the default theme settings that you define 
under the *Settings* menu in *Site Administration*. You may want to set unique 
theme settings for a page though, that differ from the defaults. This is where
individual page settings come into play. 

Follow these steps to configure the theme settings for the *Welcome* page:

1. Navigate to the *Welcome* page for the theme and sign in as an Administrator
   if you haven't already.
   
2. Select the *Edit* button and goto the *Look and Feel* section.

3. Click the radio button to *Define a specific look and feel for this page*.

4. Set *show-page-title* and *Show Breadcrumbs* to *False*.

5. Click *Save*.

6. Reload the page to see the changes you made.

You'll notice that *show-page-title* matches the `key` that was set up in 
`liferay-look-and-feel.xml`, while the other theme settings do not; this is 
because they are using language keys. As a best practice you should configure 
all of your theme settings to use language keys. If you open `portal-impl.jar` 
in your `liferay-portal-[version]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/` 
directory you'll find the `language_en.properties` file inside of the 
`portal-impl/content` folder. This file holds all of the English language keys
that Liferay Portal uses.

1. Search for *show-breadcrumbs* and *portlet-setup-show-borders-default* in the
   `language_en.properties` file and you'll see the English translations for the 
   theme settings that are available to Liferay by default.

In order to include a language key for *show-page-title* you'll need to create a 
hook to modify the `language_en.properties` file. You'll learn how to do this 
in the next section.