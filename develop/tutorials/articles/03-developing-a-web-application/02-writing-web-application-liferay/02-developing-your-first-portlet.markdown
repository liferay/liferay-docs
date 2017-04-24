# Developing Your First Portlet

So far, you've installed and set up @ide@, created a Liferay Workspace, 
generated a Liferay project, and deployed it to a running Liferay bundle. Now 
you can take the next steps to add functionality and fully develop your Liferay
Web Application.

## Creating an Add Guestbook Button

A guestbook application is pretty simple, right? People come to your site,
enter their names and brief messages, and then post them. Users can read the
entries that others posted, and they can post entries themselves.

The first thing, therefore, that we need is a landing page that displays
entries and that has a button on it that allows users to add an entry. This
page was created when you created your project: you'll find it in your
project's `src/main/resources/META-INF/resources` folder. It's called
`view.jsp`. Open this file:

    <%@ include file="/init.jsp" %>

    <p>
        <b><liferay-ui:message key="com_liferay_docs_guestbook_GuestbookModulePortlet.caption"/></b>
    </p>

As you can see, this file contains some sample content since the project 
template generated a fully functional sample portlet. First of all, `view.jsp` 
imports `init.jsp`. By convention, all JSP imports and tag library declarations 
in a Liferay JSP portlet application are made in an `init.jsp` file. Every 
other JSP file in the application imports `init.jsp`. This convention ensures 
that JSP dependency management can always be handled from a single fie.

Besides importing `init.jsp`, `view.jsp` simply displays a message that
corresponds to a language key. This key and its value are declared in your
project's `src/main/resources/content/Language.properties` file. Let's add some
functionality to your Guestbook application by adding a button that says *Add
Entry*.

1. First, remove the paragraph containing the default message from `view.jsp`.

2. Next, add the following AlloyUI tags to display an Add Entry button inside
   of a button row:

        <aui:button-row>
            <aui:button value="Add Entry"></aui:button>
        </aui:button-row>

Notice that you can use `aui` tags in `view.jsp` since `init.jsp` includes the
AlloyUI tag library declaration by default (as well as other important imports
and two useful tags):

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
        <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

        <portlet:defineObjects />

        <liferay-theme:defineObjects />

Your application now displays a button instead of a message but the button
currently doesn't do anything. Next, you'll learn about system-generated URLs
since you need a URL to map to your button.

### Generating URLs for Portlets

Since portlets are designed to be placed on pages by end users, and end users
can place multiple portlets on a page, and you as the developer have no idea
what other portlets will share a page with yours, guess what? You can't define
URLs for various functions in your application like you may be used to. Why? A
simple example should explain.

Say you've written a Calendar application that a user has placed on the same
page as a Blog application. The Calendar allows the user to add and delete
events. The Blog allows the user to add and delete blog entries. To implement
the functionality for deleting, both application developers elected to append
the *del* parameter to the URL, and give it a primary key value so the
application can go look up the calendar event or the blog entry and delete it.

Since both applications read this parameter, when a user uses the delete
function in either one of them, both applications attempt to delete either a
calendar event or a blog entry that matches the given ID. In other words, the
applications are clashing because they use the same URL parameter.
system-generated URLs are designed to prevent this from happening. Creating a
system-generated URL is easy:

1. Add these tags below the `<%@ include file="/init.jsp" %>` line but above
   the `<aui:button-row>` tag:

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>

2. Add this attribute to the `<aui:button>` tag:

        onClick="<%= addEntryURL.toString() %>"

    Your `view.jsp` page should now look like this:

        <%@ include file="/init.jsp" %>

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>

        <aui:button-row>
            <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
        </aui:button-row>

The `var` attribute of the `<portlet:renderURL>` tag creates a variable to hold
the system-generated URL. In this example, the generated URL will be stored in
a variable named `addEntryURL`. The `<portlet:param>` tag defines a URL
parameter which is appended to the generated URL. In this example, a URL
parameter named `mvcPath` with a value of `/edit_entry` is appended to the URL.

Notice that your `GuestbookModulePortlet` class extends `MVCPortlet`, which is
Liferay's MVC portlet class. In a Liferay MVC portlet, the `mvcPath` URL
parameter is used to indicate a page within your portlet application. To
navigate to another page in your portlet application, use a portal URL with the
parameter `mvcPath` to indicate the specific page.

In the example above, you created a `renderURL` that points to your
application's `edit_entry.jsp` page, which you haven't yet created. Note that
using an AlloyUI button to follow the generated URL is not required. You could
have used any HTML construct that contains a link. Users can click on your
button to access your application's `edit_entry.jsp` page. This currently
produces an error since no `edit_entry.jsp` exists yet. Creating the
`edit_entry.jsp` page is your next step.

### Creating a Page for Adding Guestbook Entries

In the same folder your `view.jsp` is in, create the `edit_entry.jsp` file:

1. Right-click on your project's `src/main/resources/META-INF/resources` folder
   and choose *New* &rarr; *File*.

2. Name the file `edit_entry.jsp` and click *Finish*.

3. Add this line to the top of the file:

        <%@ include file="/init.jsp" %>

    As mentioned previously, it's a best practice to add all of your JSP
    imports and tag library declarations to a single file which is imported by
    every other JSP file in your application. For your `edit_entry.jsp` file,
    you need these imports so that you can access to the portlet tags to create
    URLs and to the Alloy tags to create the form.

4. You'll create two URLs: one to submit the form and one to go back to the
   previous page: `view.jsp`. To create the URL to go back to `view.jsp`, add
   the following tags below the first line you added:

        <portlet:renderURL var="viewURL">
            <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
        </portlet:renderURL>

Next, you need to create a new URL for submitting the form. Before you do, some
explanation is in order.

### Using Portlet Actions

As has been stated already, portlets are designed to run in a portion of a
page. A page can contain multiple portlets. Because of this, portlets have two
*phases* of operation. The first phase is the one you've already used: it's
called the *render* phase. All this means is that the portlet draws itself,
using the JSPs you write for it.

The other phase is called the *action* phase. This phase runs once, when a user
triggers a portlet action. The portlet performs whatever action the user
triggered, such as performing a search or adding a record to a database. Then,
based on what happened in the action, the portlet goes back to the render phase
and re-renders itself according to its new state.

To save a guestbook entry, you'll have to trigger a portlet action. For this,
you'll create an action URL.

1. Add the following tag after the closing `</portlet:renderURL>` tag:

        <portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

You now have the two required URLs for your form.

### Creating a Form

Your guestbook form is pretty simple. All you need are two fields: one for the
name of the person submitting the entry and one for the entry itself.

1. Add the following tags to the end of your `edit_entry.jsp` file:

        <aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">
                <aui:fieldset>
                    <aui:input name="name"></aui:input>
                    <aui:input name="message"></aui:input>
                </aui:fieldset>

                <aui:button-row>
                    <aui:button type="submit"></aui:button>
                    <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
                </aui:button-row>
        </aui:form>

Save `edit_entry.jsp` and redeploy your application. If you refresh the page
and click the *Add Entry* button, your form appears. If you click the *Cancel*
button, it works! However, don't click the *Save* button yet. You haven't yet
created the action that saves a guestbook entry, so clicking *Save* will
produce an error.

![Figure x: This is the Guestbook application's form for adding entries.](../../images/first-guestbook-portlet.png)

Implementing the portlet action (what happens when the user clicks *Save*) is
your next task.

### Implementing Portlet Actions

When users submit the form, your application needs to store the form data so it
can be displayed in the guestbook. To keep this first application simple, you'll
implement this using a part of the Portlet API called Portlet Preferences.
Normally, of course, you'd use a database, and Liferay makes it very easy to
work with databases using its Service Builder framework. For now, however, you
can create the first iteration of your guestbook application using portlet
preferences.

To make your portlet do anything other than re-render itself, you must
implement portlet actions. An action defines some processing, usually based on
user input, that the portlet must perform before it renders itself. In the case
of the guestbook portlet, the action you need to implement saves a guestbook
entry that a user typed into the form. Saved guestbook entries can be retrieved
and displayed later.

Since you're using Liferay's MVC Portlet framework, you have an easy way to
implement actions. Portlet actions can be implemented in the portlet class,
which acts as the controller. In the form you just created, you made an action
URL, and you called it `addEntry`. To create a portlet action, you create a
method in the portlet class with the same name. `MVCPortlet` calls that method
when a user triggers its matching URL.

1. Open the `GuestbookModulePortlet.java`. The project templates generated this class 
	when you created the portlet project.

2. Create a method with the following signature:

        public void addEntry(ActionRequest request, ActionResponse response) {

        }

3. Add the required `javax.portlet.ActionRequest` and
   `javax.portlet.ActionResponse` imports.

You've now created a portlet action. It doesn't do anything, but at least you
won't get an error now if you submit your form. Next, you should make the
action save the form data.

Because of the limitations of the Configuration API, you need to store
each guestbook entry as a `String` in a string array. Since you have two fields
in your form, you need to have a delimiter so you can determine where the user
name ends and the guestbook entry begins. The caret symbol (`^`) makes a good
delimiter because users are highly unlikely to use that symbol in a guestbook
entry.

+$$$

**Note:** In most cases, you'll need a more robust solution than the 
Configuration API for storing data. You'll learn how to implement a
robust mechanism for saving application data in the  *Service Builder* learning path.

$$$

The following method implements adding a guestbook entry to a portlet preference
called `guestbook-entries`:
 
    public void addEntry(ActionRequest request, ActionResponse response) {
        try {
            PortletPreferences prefs = request.getPreferences();

            String[] guestbookEntries = prefs.getValues("guestbook-entries",
                    new String[1]);

            ArrayList<String> entries = new ArrayList<String>();

            if (guestbookEntries != null) {
                entries = new ArrayList<String>(Arrays.asList(prefs.getValues(
                        "guestbook-entries", new String[1])));
            }

            String userName = ParamUtil.getString(request, "name");
            String message = ParamUtil.getString(request, "message");
            String entry = userName + "^" + message;

            entries.add(entry);

            String[] array = entries.toArray(new String[entries.size()]);

            prefs.setValues("guestbook-entries", array);

            try {
                prefs.store();
            }
            catch (IOException ex) {
                Logger.getLogger(GuestbookModulePortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
            catch (ValidatorException ex) {
                Logger.getLogger(GuestbookModulePortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }

        }
        catch (ReadOnlyException ex) {
            Logger.getLogger(GuestbookModulePortlet.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

First, the preferences are retrieved, and then the `guestbook-entries`
preference is retrieved and converted to an `ArrayList` so that you can add an
entry without worrying about exceeding the size of the array. Next, the name
and message fields from your form are retrieved. Note that Liferay's
`ParamUtil` class makes it very easy to retrieve URL parameters.

Finally, the fields are combined into a `String` delimited by a caret, and the
new entry is added to the `ArrayList`, which is then converted back to an array
so it can be stored as a preference. The try/catch blocks are required by the
portlet preferences API.

This is not the normal way to use portlet preferences, but it provides a quick
and easy way for you to store guestbook entries in this first version of your
application. You'll implement a robust way to store guestbook entries in a
later learning path.

The next and final feature to implement is a mechanism for viewing guestbook
entries.
