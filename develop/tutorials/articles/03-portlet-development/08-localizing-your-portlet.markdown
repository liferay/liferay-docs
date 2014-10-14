# Localizing your Portlet [](id=localizing-your-portlet)

If your portlets target an international audience, you can localize the user
interface. Localizing your portlet's language is done using language keys for
each language you wish to support. You can translate these manually or use a web
service to translate them for you. Conveniently, all existing translated
messages in the portal core are accessible from plugin projects. You can check
for the presence of specific language keys in the core `Language.properties`
file found in `portal-impl/src/content`. Leveraging portal's core language keys
saves you time, since these keys always have up to date translations for
multiple languages. Additionally, your portlet blends better into Liferay's UI
conventions.

You can use a language key in your JSP via a `<liferay-ui:message />` tag. 

    <liferay-ui:message key="message-key" />

You specify the message key corresponding to the language key in the
`Language.properties` file you want to display. For example, to welcome a user
in their language, specify the message key named `welcome`.

    <liferay-ui:message key="welcome" />

This key maps to the of the word "Welcome", in your translation of it to the
user's locale. Here is the `welcome` language key from Liferay's
`Language.properties` file.

    welcome=Welcome

Let's add the `welcome` language key in front of our greeting in the
`view.jsp` file of the `my-greeting-portlet` we created earlier. Replace its
current greeting paragraph with this:

    <p><liferay-ui:message key="welcome" />! <%= greeting %></p>

Revisit the page to see the word "Welcome", from `Language.properties`, now
precedes your greeting!

Note, in order to use the `<liferay-ui:message />` tag, or any of the
`liferay-ui` tags, you must include the following line in your JSP. It imports
the `liferay-ui` tag library. 

    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

The `<liferay-ui:message />` tag also supports passing strings as arguments to
a language key. For example, the `welcome-x` key expects one argument. Here is
`welcome-x` key from the `Language.properties` file:

    welcome-x=Welcome{0}!

It references `{0}`, which denotes the first argument of the argument list. An
arbitrary number of arguments can be passed in via message tag, but only those
arguments expected by the language key are used. The arguments are referenced in
order as `{0}`, `{1}`, etc. Let's pass in the user's screen name as an argument
to the `welcome-x` language key in the "My Greeting" portlet. 

1.  Open the `view.jsp` file. 

2.  Add the following lines near the top of the JSP, just above the
    `<portlet:defineObjects />` tag. The first line imports the `liferay-theme`
    tag library. The second line defines the library's objects, providing access
    to the `user` object holding the user's screen name. 

        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>

        <liferay-theme:defineObjects />

3.  Replace the current welcome message tag and exclamation point,
    `<liferay-ui:message key="welcome" />!`, in the JSP with the following:

        <liferay-ui:message key="welcome-x" /> <%= user.getScreenName() %>

When you refesh your page, your "My Greeting" portlet greets you by your screen
name!

![Figure 3.11: By passing the user's screen name as an argument to Liferay's `welcome-x` language key, we were able to display a personalized greeting.](../../images/portlets-welcome-user-screenname.png)

Other message tags you'll want to use are the `<liferay-ui:success />` and
`<liferay-ui:error />` tags. The `<liferay-ui:success />` helps you give
positive feedback, marked up in a pleasant green background. The
`<liferay-ui:error />` tag helps you warn your users of invalid input or
exceptional conditions. The error messages are marked up in an appropriate red
background. 

The `<liferay-ui:success />` tag is triggered when its key value is found in the
`SessionMessages` object. Earlier in our `MyGreetingPortlet` class, we triggered
the success message `<liferay-ui:success key="success" ... />` by adding its key
to the `SessionMessages` object with the following call: 

    SessionMessages.add(actionRequest, "success");

Similarly, the `<liferay-ui:error />` tag is triggered when its key value is
found in the `SessionErrors` object. Likewise, in our `MyGreetingPortlet` class,
we triggered the error message `<liferay-ui:error key="error" ... />` by adding
its key to the `SessionErrors` object with the following call: 

    SessionErrors.add(actionRequest, "error");

That's all you need to do to leverage Liferay's core localization keys. If you
need to add localization keys, follow the instructions below to deliver locally
tailored portlets to your customers. 

## Your Localization Plan [](id=your-localization-plan)

First consider some questions that will make our life easier as we develop your
localization:

- Does my plugin contain more than one portlet? This is very important if
  portlets share the same UI elements and messages; you don't want to maintain
  the same data in ten places. 
- Do my portlets need localized titles in portlet headers and administrative
  tools? 
- Do my portlets have to be accessible in the Control Panel? This is important
  if you want to provide your customers fancy *Title* and *Description* features
  like the majority of core liferay portlets. 

We'll cover these different cases in the following sections. 

## Create Resource Bundles [](id=create-resource-bundles)

First, let's create a resource bundle for providing textual translations of
three related fictional portlets named My Finances, Asset Ticker and Portfolio
Manager. All three portlets share some attributes:

- They use existing Liferay core messages to handle standard UI cases. 
- They use common financial terms, so we don't want to repeat them for each
  portlet. 
  
Let's explore sharing translated text between these portlets. 

Assuming you already created a plugin project and added portlets, let's
start:

1.  Create a `content` package in your `src` plugin project folder. 

2.  Create the `Language.properties` file to define all the keys our portlets
    need. 

3.  For each portlet, update its `<portlet>` node in `portlet.xml` to refer to
    the resource bundle correctly:

        <portlet>
            <portlet-name>finances</portlet-name>
            ...
            <resource-bundle>content/Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>portfolio</portlet-name>
            ...
            <resource-bundle>content/Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>ticker</portlet-name>
            ...
            <resource-bundle>content/Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

    Make sure to put each `resource-bundle` element in its proper place in the
    `portlet` element. See the `portlet.xml` file's schema
    [http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd)
    for details. 

At this point our portlets are ready to deliver a localized UI.

**Please note:** It's best to use the Liferay naming convention for language
bundles so your portlets can share properties, and the Plugins SDK Ant task used
to build the translations works.

In order for a user to see a message in his own locale, the message value must
be specified in a resource bundle file with a name ending in his locale's two
character code. For example, a resource bundle file named
`Language_es.properties` containing a message property with key `welcome` must
be present with a Spanish translation of the word "Welcome". Good news, Plugins
SDK provides a means for you to get translations for your default resource
bundle.

The Plugins SDK uses the Bing Translator service
[http://www.microsofttranslator.com/](http://www.microsofttranslator.com/) to
translate all of the resources in your Language.properties file to multiple
languages. It provides a base translation for you to start with. To create base
translations using the Bing Translator service, you'll need to do the following:

1.  Signup for an Azure Marketplace account and register your application. Be
    sure to write down your ID and secret given to you for your application.

2.  Edit the `portal-ext.properties` file in your Liferay Home directory by
    adding the following two lines replaced with your values:

        microsoft.translator.client.id=your-id
        microsoft.translator.client.secret=your-secret

3.  In Developer Studio, right-click on the `Language.properties` file &rarr;
    Liferay &rarr; Build Languages.

    If prompted, choose the option to force Eclipse to accept the
    `Language.properties` file as UTF-8. Make sure you are connected to the
    Internet when you execute this. 

When the build completes, you'll find the generated files with all of the
translations, in the same folder as your `Language.properties` file.



+$$$

**Note:** If you're mavenizing your portlet, make sure to copy your `content`
folder into your portlet's `src/main/webapp/WEB-INF/classes` folder. 

$$$

By using Studio's language building capability, you can keep all created
translations synchronized with your default `Language.properties`. You can run
it any time during development. It significantly reduces the time spent on the
maintanance of translations. Of course, you'll want to have someone fluent in
that language review the translation before deploying the translation to a
Production environment. 

Now that you know how to create a shared resource bundle, let's consider a case
in which you must use separate resource bundles for each portlet. In order to
localize messages used in the Control Panel for a Control Panel-enabled portlet,
you must use separate resource bundles. We'll show you how to implement them. 

## Portlet Title and Description In Control Panel [](id=portlet-title-and-description-in-control-panel)

You may have noticed that your Control Panel-enabled portlets are missing that
super-fancy must-have portlet title and description in the Control Panel. To
make your portlet look cool within the Control Panel, create specially tailored
description and title keys in separate `Language.properties` files for each
portlet in your project. You'll use the `javax.portlet.title` and
`javax.portlet.description` language keys. 

For demonstration purposes, let's consider a project that has one portlet named
`eventlisting` and another portlet named `locationlisting`. We'll need to create
a resource bundle for each of them to specify their localized title and
description values. 

+$$$

**Note:** If your project only has one portlet, it's best to put your resource
bundle directly in the `content` folder. Specifying your bundle in file
`content/Language.properties` lets you leverage the Plugins SDK's language
building capabilities, via right-clicking on the `Language.properties` file
&rarr; Liferay &rarr; Build Languages in Developer Studio or executing `ant
build-lang` from the terminal. 

$$$

Here's what you'd do to localize the title and description for each portlet in
the project: 

1.  If you haven't done so already, configure each portlet to display in the
    Control Panel. For our example, we would display them in the *Content*
    portion and give them an arbitrary *weight* value for determining where
    they're to be placed in the column with respect to the other portlets.
    Here's a sample of how to specify this in our project's
    `liferay-portlet.xml` file: 
                   
		<portlet>
			<portlet-name>eventlisting</portlet-name>
			<icon>/icon.png</icon>
			<control-panel-entry-category>content</control-panel-entry-category>
			<control-panel-entry-weight>1.5</control-panel-entry-weight>
			....
		</portlet>
		<portlet>
			<portlet-name>locationlisting</portlet-name>
			<icon>/icon.png</icon>
			<control-panel-entry-category>content</control-panel-entry-category>
			<control-panel-entry-weight>1.6</control-panel-entry-weight>
			....
		</portlet>

2.  Create a namespaced folder to hold each portlet's resource bundle. It's a
    best practice to name each resource bundle folder based on the name of its
    portlet. 

    For example, you could create a resource bundler folder
    `content/eventlisting` for the `eventlisting` portlet and a folder
    `content/locationlisting` for the `locationlisting` portlet. 

3.  Create a `Language.properties` file in the resource bundle folders you just
    created. Specify the `javax.portlet.title` and `javax.portlet.description`
    language key/values in each of these `Language.properties` files. 

    The `eventlisting` portlet could have the following
    key/value pairs in its `content/eventlisting/Language.properties` file:

        javax.portlet.title=Event Listing Portlet
        javax.portlet.description=Lists important upcoming events.
 
    And the `locationlisting` portlet could have these key/value pairs
    in its `content/locationlisting/Language.properties` file: 

        javax.portlet.title=Location Listing Portlet
        javax.portlet.description=Lists event locations.

4.  Specify the resource bundles for the portlets in the project's `portlet.xml`
    file. The example `portlet.xml` file code snippet below demonstrates
    specifying the resource bundles for the `eventlisting` and `locationlisting`
    example portlets: 

        <portlet>
            <portlet-name>eventlisting</portlet-name>
            ...
            <resource-bundle>content.eventlisting.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>
        <portlet>
            <portlet-name>locationlisting</portlet-name>
            ...
            <resource-bundle>content.locationlisting.Language</resource-bundle>
            <portlet-info>...</portlet-info>
            ...
        </portlet>

5.  Redeploy your plugin project.

6.  Go to the Control Panel and select the Event Locations portlet.  

7.  Add `en` to your portal context in your URL to interface with the portal in
    Spanish. For example, your URL would start like this:

        http://localhost:8080/es/group/control_panel/...

Portal's Control Panel displays your portlet's localized title and description. 

![Figure 3.12: It's easy to localize titles and descriptions for multiple portlets in your project.](../../images/localized-portlet-title-desc-in-control-panel.png)

You're becoming an expert localizer!

+$$$

**Tip:** Do you know how your portlet title is processed? If your portlet
doesn't define a resource bundle or `javax.portlet.title`, the portal container
next checks the `<portlet-info>` and inner `<portlet-title>` node in the
`portlet.xml` descriptor. If they're missing too, the `<portlet-name>` node
value is rendered as portlet title. 

$$$

+$$$

**Note:** Be aware that using Struts portlet and referring to a `StrutsResource`
bundle in your `portlet.xml` engages a different title and description
algorithm. Titles and long titles are pulled using two different keys:

- `javax.portlet.long-title` 
- `javax.portlet.title`

$$$

Now that you're comfortable localizing portlet content, you may want to learn
how to make translations available throughout the portal or how to override an
existing translation. For instructions on doing that, refer to Chapter 7 of this
guide, specifically the *Overriding a Language.properties File* section. It
describes how to use a hook to override existing Liferay translations. You can
share your keys with other portlets, as well as override existing Liferay
translations. 

Next, let's learn how to configure your portlets' preferences using
configuration actions.
