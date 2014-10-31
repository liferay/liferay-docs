# Writing Your First Liferay Application [](id=writing-your-first-liferay-application)

Getting started with your first Liferay application takes only minutes. We'll
guide you through the step-by-step process of creating your project and
deploying your application to Liferay. Before you know it, you'll have your
application deployed alongside of the applications that come with Liferay. 

Your first application is simple: you'll build a guestbook application that
looks like this: 

![Figure 1: You'll create this simple application.](../../images/my-first-app.png)

By default, it shows guestbook messages that various users have left on your
website. To add a message, you click the *Add Entry* button to show a form you
can use to enter and save a message. 

Ready to write your first Liferay application? 

## Creating Your First Liferay Application [](id=creating-your-first-liferay-application)

Now that everything's configured, let's jump right in and create your first
project. Applications in Liferay Portal are called *portlets*, so you'll create a
portlet project. 

1. Select *File* &rarr; *New* &rarr; *Liferay Plugin Project*. The dialog below
appears. 

    ![Figure 2: Liferay IDE/Developer Studio's new project wizard makes it easy to create Liferay projects.](../../images/lds-new-liferay-plugin-project-1.png)

2. Fill in the Project Name and the Display Name. Make the project name
*guestbook-portlet* and the display name *Guestbook*. 

3. Uncheck *Include Sample Code*. 

4. Check *Launch New Portlet Wizard after project is created*. 

5. When done, click *Finish*. 

+$$$

**Note:** The first time you create a project,
make sure you're online. In the background, Liferay's Plugins SDK downloads
several JAR files that it needs in order to operate. This keeps the initial
Plugins SDK download small but means that it may take several minutes to create
your first project. Subsequent projects are created almost instantly.

$$$

Your project now appears in the Package Explorer on the left. What you've just
done is create a blank Liferay project. Projects can have any number of
applications (called *portlets*) in them. Your next step is to create the
portlet, which the New Portlet Wizard helps you to do. 

1. For the Portlet class, enter the name `GuestbookPortlet`. 

2. All classes in Java should be properly namespaced to avoid conflicts with
other classes that could have similar names. For the Java package, enter
`com.liferay.docs.guestbook.portlet`. 

3. Click *Finish*. 

Liferay IDE/Developer Studio creates the portlet class in the proper package in
the `src` folder and creates a new folder structure for your application's JSP
files. It then opens your portlet class in an editor. 

You are now ready to work. 

## What is a portlet? [](id=what-is-a-portlet)

When you access a web site, you interact with an application. That application
may be simple: it may only show you information, such as an article. The
application may be complex: you may be doing your taxes online, inputting lots
of data into an application that calculates whether you owe or are due a refund.
These applications run on a *platform* that provides application developers the
building blocks they need to make applications. 

![Figure 3: Many Liferay applications can run at the same time on the same page.](../../images/portlet-applications.png)

Liferay Portal is a platform, and its applications are called *portlets*. One
difference between a Liferay application and an application built on another
platform is that Liferay Portal can serve many applications at the same time on
the same page. Usually, a web application takes up the entire page, and if you
want, you can do this with Liferay as well. But Liferay has the added benefit of
being able to run many applications on the same page. For this reason, you'll
find that the framework takes this into account at every step. We're saying this
up front, because you'll be experiencing this soon, and we want you to know why
certain things, like platform-generated URLs, are necessary. 

With all of that said, the next thing to do is to jump in and create your
Guestbook portlet. 

## Adding Guestbook Entries [](id=adding-guestbook-entries)

A guestbook application is pretty simple, right? People come to your site, type
their names and a brief message, and then post it for you. Users can read the
entries that others posted, and they can post entries themselves. 

The first thing, therefore, that we need is a landing page that displays entries
and that has a button on it that allows users to add an entry. This page was
created when you created your project: you'll find it in the
`/docroot/html/guestbook` folder of your project. It's called `view.jsp`. Open
this file. 

You'll see there's some content in it already: Liferay IDE generates a fully
functional sample portlet, but the only thing it does is display the message you
see in the `view.jsp` file. Let's give it some functionality by adding a button
that says *Add Entry*. 

 1. Open the *Snippets* tab on the right side of Liferay IDE, and expand the
 *Taglib Imports* category. 

 2. Drag the snippet labeled *Liferay AUI Taglib Import v6.1* from the snippet
 area to the line beneath the existing taglib import in `view.jsp`. Don't worry
 about the Liferay version number in the snippet title: the import was changed
 for version 6.1, and it is valid for Liferay versions going forward. The
 following code gets added to `view.jsp`: 

        <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

    This declares that we want to use Liferay's AlloyUI tags. 

 3. Next, remove the default message in the JSP. 

 4. Open the *Liferay AUI Taglib* snippet category and drag the snippet labeled
 *button-row* onto the bottom of the page. A dialog box pops up asking for the
 `cssClass` attribute. Type `guestbook-buttons` for this value, hit Enter, and
 click *Insert*. 

You're almost ready to add the button inside the button row, but as hinted above,
we should talk about system generated URLs, because you need a URL to map to
your button. 

### Generating URLs for Portlets [](id=generating-urls-for-portlets)

Since portlets are designed to be placed on pages by the end user, and end users
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
applications are clashing because they use the same URL parameter. System
generated URLs are designed to prevent this from happening. Creating a system
generated URL is easy. 

1. Make space inside the `<aui:button-row>` tag; you'll place the URL tag there. 

2. From the *Portlet Taglib* snippet category, drag the *renderURL* tag to the
space you just made inside the `<aui:button-row>` tag. A dialog box pops up
asking you to enter values for various tag attributes. 

3. The only attribute you need at the moment is the `var` attribute. For this
attribute, enter the value *addEntryURL*. You're creating a variable to hold the
generated URL. 

4. Make space inside the `<portlet:renderURL>` tag so that you can add another
tag inside it. 

5. Drag the *param* tag into the `<portlet:renderURL>` tag. A dialog box pops up
asking you to enter values for two tag attributes. 

6. For the name, enter *mvcPath*. For the value, enter
*/html/guestbook/edit_entry.jsp*. Hit *Enter*. Click *Insert*. 

By hitting *Finish* instead of *Next* when you created this portlet, you
indicated you accepted the default portlet framework, which is Liferay's MVC
Portlet. To navigate to another page in your portlet application, you use the
parameter `mvcPath` to indicate a link to that page. You just created a
`renderURL` that links to another JSP in your application, and you can now use
a button your users can click (or any other HTML construct that accepts a link)
with that URL. That's the next step. 

### Creating a Button [](id=creating-a-button)

Creating a button is as easy as creating the URL was. You'll drag the proper
snippet for the button to the proper location in your page, just like you did
for the other tags. 

1. Inside the `<aui:button-row>` tag but outside the `</portlet:renderURL>` tag,
drag and drop the *button* snippet from the *Liferay AUI Taglib* category. A
dialog box pops up asking you to enter values for various tag attributes. 

2. For the `onClick` attribute, enter the text `<%= addEntryURL.toString() %>`.
This code supplies the `String` value of the URL object you created with the
`<portlet:renderURL` tag. See how we used the `var` attribute in that tag so we
would have a variable for the URL? This is so you could refer to it here. 

3. For the `value` attribute, enter *Add Entry* as the text. This is what
appears on the button. Click *Insert*. 

At this point, you should have code that looks like this: 

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
    <portlet:defineObjects />

    <aui:button-row cssClass="guestbook-buttons">

        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>
        
        <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
        
    </aui:button-row>

Now is a good time to check your work to see if everything you've done so far is
working properly. As the image below describes, drag your project from the
Package Explorer and drop it on your server runtime. 

![Figure 4: Deploying projects is a drag and drop process.](../../images/lds-deploy-portlet.png)

Once your application deploys, go to a Liferay page and click the *Add* button
(it looks like a plus sign). Choose *Applications* and from the Sample category,
drag your Guestbook application onto the page and drop it there. Your portlet is
pretty sparse at this point, but here's what you have so far: 

![Figure 5: Your button doesn't go anywhere--yet.](../../images/guestbook-portlet-add-entry-button.png)

Don't click the button: remember that you haven't created the page the
button points to yet. All you'll get is an error if you do. Of course, reverse
psychology says that you've clicked it already. Don't worry; you didn't break
anything. Next, you'll create the page that URL points to so the app doesn't
break when you click that button. 

### Making a Page with a Form Users Can Fill Out [](id=making-a-page-with-a-form-users-can-fill-out)

In the same folder your `view.jsp` is in, create the `edit_entry.jsp`: 

1. Navigate to `docroot/html/guestbook` and right-click on the folder. Choose
*New* &rarr; *File*. 

2. Name the file `edit_entry.jsp` and click *Finish*. 

3. The file is opened in a new editor tab. Copy the first three lines of
`view.jsp` and paste them into this file. You need to initialize the tag
libraries again because you'll use the portlet tags to create URLs and the Alloy
tags to create the form. 

4. You'll create two URLs: one to submit the form and one to go back to the previous
page (`view.jsp`). There will be a difference between the two, however, which
you'll see in a bit. 

5. As you did before, drag the *renderURL* snippet from the *Portlet Taglib*
snippet category onto your JSP. For the `var` attribute, supply *viewURL*. 

6. As you did before, drag the *param* snippet from the *Portlet Taglib* snippet
category inside the `<portlet:renderURL` tag. Make the `name` attribute
*mvcPath* and the `value` attribute */html/guestbook/view.jsp*. 

Next, you need to create a new URL for submitting the form. Before you do, some
explanation is in order. 

### Using Portlet Actions [](id=using-portlet-actions)

As has been stated already, portlets are designed to run in a portion of a page
next to other portlets. Because of this, they have two *phases* of operation.
The first phase is the one you've already used: it's called the *render* phase.
All this means is that the portlet draws itself, using the JSPs you write for
it. 

The other phase is called the *action* phase. This phase runs once, when a user
triggers a portlet action. The portlet performs whatever action the user
triggered, such as performing a search or adding a record to a database, and
then based on what happened in the action, it goes back to the render phase and
re-renders itself according to its new state after the action. 

To save a guestbook entry, you'll have to trigger a portlet action. For this,
you'll create an action URL. 

1. From the *Portlet Taglib* snippet category, drag the *actionURL* snippet to
your page, after your closing `</portlet:renderURL>` tag. 

2. In the dialog box, supply *addEntry* for the `name` attribute and *addEntryURL*
for the `var` attribute. Click *Insert*. 

You now have the two URLs you need for your form. 

### Creating a Form [](id=creating-a-form)

Your guestbook form is pretty simple. All you need are two fields: one for the
name of the person submitting the entry and one for the entry itself. 

1. From the *Liferay AUI Taglib* snippet category, drag the *form* snippet to
your page. 

2. For the `action` attribute, supply *addEntryURL*, which is the action URL you
just created. 

3. For the `name` attribute, give your form the name *<portlet:namespace />fm*.
Click *Insert*. The `<portlet:namespace />` tag inserts a unique runtime value
for this portlet. This ensures that the form name in this portlet does not clash
with a form name in another portlet that may be on the page. 

4. Provide some space between your `<aui:form>` tags so you can add fields to
your form. 

5. Drag the *fieldset* snippet inside your `<aui:form>` tags. Click *Insert*. 

6. Drag an *input* snippet inside your `<aui:fieldset>` tags. For the `name`
attribute, type *name*, as this is the Name field on the form. 

7. Drag another *input* snippet below the last one, inside your `<aui:fieldset>`
tags. For the `name` attribute, type *message*, as this field holds the message
a user leaves on the guestbook. 

8. Below the closing `</aui:fieldset>` tag, drag the *button-row* snippet and
click *Insert*. 

9. Inside the `<aui-button-row>` tags, drag a *button* snippet. For the `type`
attribute, enter *submit*. Click *Insert*. 

10. Beneath the Submit button you just created, drag another *button* snippet.
For the `type` attribute, enter *cancel*. For the `onClick` attribute, enter
`<%= viewURL.toString() %>`. This supplies the render URL you created that goes back to the
`view.jsp` page.

Your form is done! Save your JSP; it should look like this:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
    <portlet:defineObjects />

    <portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
    </portlet:renderURL>

    <portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

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

When you save the JSP, Liferay IDE re-deploys your application, and if you
refresh the page and click the *Add Entry* button, your form appears. If you
click the *Cancel* button, it works! Don't click the *Save* button yet, though:
you haven't created the action that saves a guestbook entry, so it'll
display an error. Implementing the action is your next task. 

### Implementing Portlet Actions [](id=implementing-portlet-actions)

When users submit the form, your application needs to store the form data so it
can be displayed in the guestbook. To keep this first application simple, you'll
implement this using a part of the Portlet API called Portlet Preferences.
Normally, of course, you'd use a database, and Liferay makes it very easy to
work with databases using its Service Builder framework. For now, however, you
can create the first iteration of your guestbook application using portlet
preferences. 

To make your portlet do anything other than re-render itself, you must implement
portlet actions. An action defines some processing, usually based on user input,
that the portlet must do before it renders itself. In the case of the guestbook
portlet, the action you need to implement saves a guestbook entry that a user
typed into the form, so it can be retrieved and displayed later. 

Since you're using Liferay's MVC Portlet framework, you have an easy way to
implement actions: in the portlet class, which acts as the controller. In the
form you just created, you make an action URL, and you called it `addEntry`. To
create a portlet action, you create a method in the portlet class with the same
name, and `MVCPortlet` calls that method when a user triggers its matching URL. 

1. Open `GuestbookPortlet.java`. Liferay IDE generated this class when you
created the portlet. 

2. Create a method with the following signature: 

        public void addEntry(ActionRequest request, ActionResponse response) {

        }

3. Hover your mouse over `ActionRequest` and add the import. Do the same for
`ActionResponse`. 

You've now created a portlet action. It doesn't do anything, but at least you
won't get an error now if you submit your form. Next, you should make the action
save the form data. 

Because of the limitations of the portlet preferences API, you need to store
each guestbook entry as a `String` in a string array. Since you have two fields
in your form, you need to have a delimiter so you can determine where the user
name ends and the guestbook entry begins. The caret symbol (^) makes a good
delimiter because users are highly unlikely to use that symbol in a guestbook
entry. 

+$$$

**Note:** Clearly, portlet preferences and string
delimiters are not the best way to implement this. To learn about a proper
implementation for saving data, follow the *Writing a Data-Driven Application*
learning path. 

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

			} catch (IOException ex) {

				Logger.getLogger(GuestbookPortlet.class.getName()).log(
						Level.SEVERE, null, ex);

			} catch (ValidatorException ex) {

				Logger.getLogger(GuestbookPortlet.class.getName()).log(
						Level.SEVERE, null, ex);

			}

		} catch (ReadOnlyException ex) {

			Logger.getLogger(GuestbookPortlet.class.getName()).log(
					Level.SEVERE, null, ex);

		}

	}

First, the preferences are retrieved, and then the `guestbook-entries`
preference is retrieved and converted to an `ArrayList` so that you can add an
entry reliably without going outside the bounds of the array. Next, the two
fields from your form are retrieved. Note that Liferay's `ParamUtil` class makes
this very easy to do. 

Finally, the fields are combined into a `String` delimited by a caret, and the
new entry is added to the `ArrayList`, which is then converted back to an array
so it can be stored as a preference. The try/catch blocks are required by the
portlet preferences API. 

This is not the normal way to use portlet preferences, but it provides a quick
and easy way for you to store guestbook entries in this first version of your
application. You'd also want to do proper logging in case of an error, rather
than use `System.out.println`. These are things that can be corrected later. 

The next and final feature to implement is a way to view guestbook entries. 

## Viewing Guestbook Entries [](id=viewing-guestbook-entries)

To view guestbook entries, you have to do the reverse of what you did to store
them: retrieve them from portlet preferences, loop through them, and present
them on the page. The best way to do this with the MVC Portlet is to use the
Model-View-Controller paradigm. You already have the view (your JSP files) and
your controller (your portlet class): now you need your model. 

### Creating Your Model [](id=creating-your-model)

1. Create a new package in your app called `com.liferay.docs.guestbook.model`.
You can do this by right-clicking on the source folder and selecting *New*
&rarr; *Package*, and then supplying the package name to the dialog box that
appears. 

2. Next, create your model class. This is a simple JavaBean that contains a
guestbook entry. Right-click on your new package and select *New* &rarr;
*Class*. Give your class the name `Entry` and click *Finish*. 

You now have a Java class for your guestbook entries, and your next task is to
give it the fields you need to store entries. 

1. Create two private String variables: `name` and `message`. 

2. Right-click on a blank area of the editor and select *Source* &rarr;
*Generate Getters and Setters*. 

3. Next, provide two constructors: one that initializes the class with no values
for the two fields, and one that takes the two fields as parameters and sets
their values. Your completed class should look like this: 

        package com.liferay.docs.guestbook.model;

        public class Entry {

            private String name;
            private String message;
        
            public Entry() {

                this.name = null;
                this.message = null;

            }
        
            public Entry (String name, String message) {
            
                setName(name);
                setMessage(message);

            }
        
            public String getName() {

                return name;

            }

            public void setName(String name) {

                this.name = name;

            }

            public String getMessage() {

                return message;

            }

            public void setMessage(String message) {

                this.message = message;

            }
        
        }

Now that you have your model, you have an easy way of encapsulating guestbook
entries so that they can be processed by the controller layer and displayed by
the view layer. Your next step is to enhance the controller (your portlet class)
so that guestbook entries are processed and ready to display when users see the
guestbook application. 

### Customizing the Rendering of Your Application [](id=customizing-the-rendering-of-your-application)

As stated before, portlet applications at their most basic have two phases:
render and action. To make the guestbook show the saved guestbook entries when
users view the application, you need to customize your portlet's render
functionality, which it's currently inheriting from its parent class,
`MVCPortlet`. 

1.  Open `GuestbookPortlet.java` and place the following method below your
`addEntry` method: 

        @Override
        public void render (RenderRequest renderRequest, RenderResponse renderResponse) 
                throws PortletException, IOException {

            PortletPreferences prefs = renderRequest.getPreferences();
            String[] guestbookEntries = prefs.getValues("guestbook-entries",
                    new String[1]);

            if (guestbookEntries != null) {

                List<Entry> entries = parseEntries(guestbookEntries);

                renderRequest.setAttribute("entries", entries);
            }

            super.render(renderRequest, renderResponse);

        }

    This method retrieves the array of guestbook entries from portlet
    preferences, converts it to a `List` of `Entry` objects, and places that
    `List` into the request object. It then calls the parent class's `render`
    method. 

2.  Beneath the `render` method, place the following method that does the
conversion of the array to a `List` of your model objects: .

        private List<Entry> parseEntries (String[] guestbookEntries) {

            ArrayList<Entry> entries = new ArrayList();

            for (String entry : guestbookEntries) {
                String[] parts = entry.split("\\^", 2);
                Entry gbEntry = new Entry(parts[0], parts[1]);
                entries.add(gbEntry);
            }

            return entries;
        }

As you can see, this method splits the entries in the `String` array into two
parts based on the caret (^) character. 

Now that you have your controller preparing your data for display, your next
step is to implement the view, so users can see guestbook entries. 

### Displaying Guestbook Entries [](id=displaying-guestbook-entries)

Liferay's development framework makes it easy to loop through data and display
it nicely to the end user. You'll use a component called the *Search Container*
to make this happen. 

1.  Open the Snippets tab on the right side of Liferay IDE and expand the
    *Taglib Imports* category.

2.  Drag the snippet labeled *Liferay UI Taglib Import* from the snippet area to
    the line beneath the existing taglib import in `view.jsp`. The following code gets
    added to `view.jsp`:

	<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    This declares that you want to use Liferay’s UI tags. 

3.  Open the `docroot/html/guestbook/view.jsp` file. Below the
`<portlet:defineObjects />` tag, add the following tag: 

        <jsp:useBean id="entries" class="java.util.ArrayList" scope="request"/>

    This makes your list of `Entry` objects available to the page. 

4.  From the *Liferay UI Search Container* snippet category, drag the *Model
Search Container* snippet and drop it at the bottom of your page. 

5.  In the dialog that pops up, click the *Browse* button and then type *Entry*
into the search dialog. Liferay IDE shows every instance of an `Entry` class on
your classpath. Choose the one you created in the
`com.liferay.docs.guestbook.model` package and click *Ok*. 

6.  Your model class is parsed automatically, and now you can choose the property
columns you wish to display to the user. Check off the *message* and *name*
columns. 

7.  The generated variable is `aEntry`. If you want something more grammatically
correct, change it to `entry`, and then click *Finish*. 

    The tag snippet assumes that you're using Liferay's persistence framework,
    Service Builder. Because you're not using that, you'll have to fix one of
    the tags. 

8.  Modify the `<liferay-ui:search-container-results>` tag so that it uses your
`entries` list: 

        <liferay-ui:search-container-results
            results="<%= entries %>"
        />

You're done! Your `view.jsp` should look like this:

    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    <portlet:defineObjects />
    <jsp:useBean id="entries" class="java.util.ArrayList" scope="request"/>

    <liferay-ui:search-container>
    	<liferay-ui:search-container-results
		results="<%= entries %>"
	/>
	
	<liferay-ui:search-container-row
		className="com.liferay.docs.guestbook.model.Entry"
		modelVar="entry"
	>
	
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
    </liferay-ui:search-container>
    
    <aui:button-row cssClass="guestbook-buttons">
	<portlet:renderURL var="addEntryURL">
		<portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp"></portlet:param>
	</portlet:renderURL>
	
	<aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
    </aui:button-row>

Save your work, deploy your application, and try adding some
guestbook entries. 

## Next Steps [](id=next-steps)

At this point, the next learning path on writing a data-driven application may
interest you. 

Here are more tutorials that you may find interesting:

[Developing Plugins with the Plugins SDK](/develop/tutorials/knowledge_base/6-2/plugins-sdk)

[Developing Plugins with Maven](/develop/tutorials/-/knowledge_base/6-2/maven)

<!--
[Create web forms with Alloy UI tag libs](http://www.liferay.com)
-->

