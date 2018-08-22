# Implementing Portlet Actions [](id=implementing-portlet-actions)

<div class="learn-path-step">
    <p>Developing Your First Portlet<br>Step 7 of 8</p>
</div>

When users submit the form, your application stores the form data for display in 
the guestbook. To keep this first application simple, you'll implement this 
using a part of the Portlet API called Portlet Preferences. Normally, of course, 
you'd use a database. @product@'s Service Builder tool eliminates a great deal 
of complexity when working with databases. For now, however, you can create the 
first iteration of your guestbook application using portlet preferences. 

To make your portlet do anything other than re-render itself, you must implement 
portlet actions. An action defines some processing, usually based on user input, 
that the portlet must perform before it renders itself. In the case of the 
guestbook portlet, the action you'll implement next saves a guestbook entry that 
a user typed into the form. Saved guestbook entries can be retrieved and 
displayed later. 

Since you're using Liferay's MVC Portlet framework, you have an easy way to 
implement actions. Portlet actions are implemented in the portlet class, which 
acts as the controller. In the form you just created, you made an action URL, 
and you called it `addEntry`. To create a portlet action, you create a method in 
the portlet class with the same name. `MVCPortlet` calls that method when a user 
triggers its matching URL. 

1.  Open `GuestbookPortlet`. The project template generated this class when 
    you created the portlet project. 

2.  Create a method with the following signature:

        public void addEntry(ActionRequest request, ActionResponse response) {

        }

3.  Press [CTRL]+[SHIFT]+O to organize imports and import the required
    `javax.portlet.ActionRequest` and `javax.portlet.ActionResponse` classes.

You've now created a portlet action. It doesn't do anything, but at least you
won't get an error now if you submit your form. Next, you should make the
action save the form data. 

Because of the limitations of the portlet preferences API, you must store each 
guestbook entry as a `String` in a string array. Since your form has two fields, 
you must use a delimiter to determine where the user name ends and the guestbook 
entry begins. The caret symbol (`^`) makes a good delimiter because users are 
highly unlikely to use that symbol in a guestbook entry. 

+$$$

**Note:** The portlet preferences API is used here for prototyping purposes 
only. In most cases, you'll need a more robust solution for storing data. You'll 
learn how to implement such a solution later in the *Service Builder* section.  

$$$

The following method implements adding a guestbook entry to a portlet preference
called `guestbook-entries`: 

    public void addEntry(ActionRequest request, ActionResponse response) {
        try {
            PortletPreferences prefs = request.getPreferences();

            String[] guestbookEntries = prefs.getValues("guestbook-entries",
                    new String[1]);

            ArrayList<String> entries = new ArrayList<String>();

            if (guestbookEntries[0] != null) {
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
                Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
            catch (ValidatorException ex) {
                Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }

        }
        catch (ReadOnlyException ex) {
            Logger.getLogger(GuestbookPortlet.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

1.  Replace your existing `addEntry` method with the above method.

2.  Press [CTRL]+[SHIFT]+O to organize imports and select the
    `javax.portlet.PortletPreferences` and `java.util.logging.Logger` when
	prompted (not their Liferay equivalents). <!--also java.util.logging.Level?-->

First, the preferences are retrieved. Then the `guestbook-entries` preference is 
retrieved and converted to an `ArrayList` so that you can add an entry without 
worrying about exceeding the size of the array. Next, the name and message 
fields from your form are retrieved. Note that Liferay's `ParamUtil` class makes 
it very easy to retrieve URL parameters. 

Finally, the fields are combined into a `String` delimited by a caret, and the
new entry is added to the `ArrayList`, which is then converted back to an array
so it can be stored as a preference. The try/catch blocks are required by the
portlet preferences API. 

This isn't the normal way to use portlet preferences, but it provides a quick 
and easy way for you to store guestbook entries in this first version of your
application. In a later step, you'll implement a robust way to store guestbook 
entries in a database. 

The next and final feature to implement is a mechanism for viewing guestbook
entries. 
