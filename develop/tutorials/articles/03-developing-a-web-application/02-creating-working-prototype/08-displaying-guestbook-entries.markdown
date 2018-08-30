# Displaying Guestbook Entries [](id=viewing-guestbook-entries)

<div class="learn-path-step">
    <p>Developing Your First Portlet<br>Step 8 of 8</p>
</div>

To display guestbook entries, you must do the reverse of what you did to store
them: retrieve them from portlet preferences, loop through them, and present
them on the page. The best way to do this with MVC Portlet is to use the 
[Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
paradigm. You already have the view (your JSP files) and your controller (your
portlet class). Now you need your model.

## Creating Your Model [](id=creating-your-model)

1.  Create a new package called `com.liferay.docs.guestbook.model`. To do this, 
    right-click your `src/main/java` folder and select *New* &rarr; *Package*. 
    Then enter the package name in the dialog box that appears. 

2.  Next, create your model class. This is a simple class that models a
    guestbook entry. To do this, right-click your new package and select *New* 
    &rarr; *Class*. Name the class `Entry`, and click *Finish*. 

    You now have a Java class for your guestbook entries. Next, you'll give it 
    the fields you need to store entries. 

3.  Create two private String variables: `name` and `message`.
    
        private String name;
        private String message;

4.  Right-click a blank area of the editor and select *Source* &rarr; *Generate 
    Getters and Setters*. Click *Select All* in the dialog that pops up, and 
    then click *Generate*. 

5.  Next, provide two constructors: one that initializes the class with no
    values for the two fields, and one that takes the two fields as parameters
    and sets their values:

        public Entry() {
           this.name = null;
           this.message = null;
        }

        public Entry(String name, String message) {
           setName(name);
           setMessage(message);
        }

Your completed model class looks like this:

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
entries so they can be processed by the controller layer and displayed by
the view layer. Your next step is to enhance the controller (your portlet class) 
so that guestbook entries are processed and ready to display when users see the 
guestbook application. 

## Customizing How Your Application is Rendered [](id=customizing-how-your-application-is-rendered)

As mentioned earlier, your application is using two portlet phases: render and
action. To make the guestbook show the saved guestbook entries when users view
the application, you need to customize your portlet's render functionality,
which it's currently inheriting from its parent class, `MVCPortlet`. 

1.  Open `GuestbookPortlet` and add the following method below your `addEntry` 
    method: 

        @Override
        public void render(RenderRequest renderRequest, RenderResponse renderResponse)
            throws PortletException, IOException {

            PortletPreferences prefs = renderRequest.getPreferences();
            String[] guestbookEntries = prefs.getValues("guestbook-entries", new String[1]);

            if (guestbookEntries[0] != null) {
                List<Entry> entries = parseEntries(guestbookEntries);
                renderRequest.setAttribute("entries", entries);
            }

            super.render(renderRequest, renderResponse);
        }

    This method retrieves the guestbook entries from the configuration, converts 
    it to a `List` of `Entry` objects, and places that `List` into the request 
    object. It then calls the parent class's `render` method. 

2.  Beneath the `render` method, add the following method that converts the
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

3. Press [CTRL]+[SHIFT]+O to organize imports. 

+$$$

Note: When you are prompted to choose imports, here are some guidelines:

* Always use `org.osgi...` packages instead of `aQute.bnd...`

* Generally use `java.util...` or `javax.portlet...` packages.

* You never use `java.awt...` in this project.

* Only use `com.liferay...` when it is for a Liferay specific implementation or
    your custom implementation of a concept.
 
For example:

* If you are given the choice between `javax.portlet.Portlet` and
    `com.liferay.portlet.Portlet` choose `javax.portlet.Portlet`.

* If you are given the choice between `org.osgi.component` and
  `aQute.bnd.annotation.component` choose `org.osgi.component`

* However, if you are given the choice between `java.util.Map.Entry` and 
  `com.liferay.docs.guestbook.model.Entry` (the custom class you created) 
  choose `com.liferay.docs.guestbook.model.Entry`
 
If at some point you think you chose an incorrect import, but you're not sure 
what it might be, you can erase all of the imports from the file and press
[CTRL]+[SHIFT]+O again and see if you can identify where you went wrong.

$$$

As you can see, this method splits the entries in the `String` array into two
parts based on the caret (`^`) character.

Now that you have your controller preparing your data for display, your next
step is to implement the view so users can see guestbook entries. 

## Displaying Guestbook Entries [](id=displaying-guestbook-entries)

Liferay's development framework makes it easy to loop through data and display 
it nicely to the end user. You'll use a Liferay UI construct called *Search 
Container* to make this happen. 

1.  Add these tags to your `view.jsp` in between the `</portlet:renderURL>` and 
    `<aui:button-row>` tags: 

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

![Figure 1: You have a form to enter information.](../../../images/guestbook-prototype-form.png)

![Figure 2: Submitted entries are displayed here..](../../../images/guestbook-prototype-container.png)

Awesome! You've finished your working prototype! You have a working application
that adds and saves guestbook entries. 

The way you're saving the entries isn't the best way to persist data in your 
application. Next, you'll use Service Builder to generate your persistence 
classes and the methods you need to store your application data in the database. 
