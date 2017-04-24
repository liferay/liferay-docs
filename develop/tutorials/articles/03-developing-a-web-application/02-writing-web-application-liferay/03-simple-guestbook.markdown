# Viewing Guestbook Entries

To view guestbook entries, you have to do the reverse of what you did to store
them: retrieve them from portlet preferences, loop through them, and present
them on the page. The best way to do this with the MVC Portlet is to use the
Model-View-Controller paradigm. You already have the view (your JSP files) and
your controller (your portlet class). Now you need your model.

## Creating Your Model

1. Create a new package in your app called `com.liferay.docs.guestbook.model`.
   You can do this by right-clicking on your `src/main/java` folder and
   selecting *New* &rarr; *Package*, and then entering the package name in the
   dialog box that appears.

2. Next, create your model class. This is a simple Java bean that contains a
   guestbook entry. Right-click on your new package and select *New* &rarr;
   *Class*. Give your class the name `Entry` and click *Finish*.

You now have a Java class for your guestbook entries, and your next task is to
give it the fields you need to store entries.

1. Create two private String variables: `name` and `message`.

2. Right-click on a blank area of the editor and select *Source* &rarr;
   *Generate Getters and Setters*.

3. Next, provide two constructors: one that initializes the class with no
   values for the two fields, and one that takes the two fields as parameters
   and sets their values. Your completed class should look like this:

        package com.liferay.docs.guestbook.model;

        public class Entry {

            private String name;
            private String message;

            public Entry() {
                this.name = null;
                this.message = null;
            }

            public Entry(String name, String message) {
                setName(name);
                setMessage(message);
            }

            public String getName() {
                return name;
            }

            public String getMessage() {
                return message;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setMessage(String message) {
                this.message = message;
            }

        }

Now that you have your model, you have an easy way of encapsulating guestbook
entries so that they can be processed by the controller layer and displayed by
the view layer. Your next step is to enhance the controller (your portlet
class) so that guestbook entries are processed and ready to display when users
see the guestbook application.

## Customizing How Your Application is Rendered

As mentioned earlier, portlet applications have two basic phases: render and
action. To make the guestbook show the saved guestbook entries when users view
the application, you need to customize your portlet's render functionality,
which it's currently inheriting from its parent class, `MVCPortlet`.

1. Open `GuestbookModulePortlet.java` and add the following method below your
   `addEntry` method:

        @Override
        public void render(RenderRequest renderRequest, RenderResponse renderResponse)
                throws PortletException, IOException {
			ThemeDisplay themeDisplay= (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
			
			GuestbookModulePortletInstanceConfiguration gradebookPortletInstanceConfiguration = 
		    	portletDisplay.getPortletInstanceConfiguration(
		       	 GuestbookModulePortletInstanceConfiguration.class);

            if (gradebookPortletInstanceConfiguration != null) {
                List<Entry> entries = parseEntries(guestbookEntries);

                renderRequest.setAttribute("entries", entries);
            }

            super.render(renderRequest, renderResponse);
        }

    This method retrieves the guestbook entries from the Configuration, 
	converts it to a `List` of `Entry` objects, and places that
    `List` into the request object. It then calls the parent class's `render`
    method.

2. Beneath the `render` method, add the following method that converts the
   array to a `List` of your model objects:

        private List<Entry> parseEntries(String[] guestbookEntries) {
            List<Entry> entries = new ArrayList<Entry>();

            for (String entry : guestbookEntries) {
                String[] parts = entry.split("\\^", 2);
                Entry gbEntry = new Entry(parts[0], parts[1]);
                entries.add(gbEntry);
            }

            return entries;
        }

As you can see, this method splits the entries in the `String` array into two
parts based on the caret (`^`) character.

Now that you have your controller preparing your data for display, your next
step is to implement the view, so users can see guestbook entries.

## Displaying Guestbook Entries

Liferay's development framework makes it easy to loop through data and display
it nicely to the end user. You'll use a Liferay UI construct called the *Search
Container* to make this happen.

1. Add the these tags to your `view.jsp` below the closing
   `</portlet:renderURL>` tag but before the opening `<aui:button-row>` tag:

        <jsp:useBean id="entries" class="java.util.ArrayList" scope="request"/>

        <liferay-ui:search-container>
            <liferay-ui:search-container-results results="<%= entries %>" />

            <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Entry"
                modelVar="entry"
            >
                <liferay-ui:search-container-column-text property="message" />

                <liferay-ui:search-container-column-text property="name" />
            </liferay-ui:search-container-row>

            <liferay-ui:search-iterator />
        </liferay-ui:search-container>

Save your work, deploy your application, and try adding some guestbook entries.

You already know that this isn't the ideal use of portlet preferences, or the 
best way to handle persistance of data for your application. Next you'll use Service Builder to generate your persistance classes and the methods you need to use the database.

# Next Steps

Here are more tutorials that you might find interesting:

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

