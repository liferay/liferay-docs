# Designing Your App

As a developer, you know that developing any kind of app without an overall
design goal and plan to implement it is a recipe for disaster. To avoid that,
you need to decide some things upfront. The Liferay Guestbook app needs a
straightforward way to do three things: 

1. Authenticate users

2. Display guestbooks

3. Display entries

To authenticate users, all you need to do is insert and configure the Login
Screenlet in your app. The Login Screenlet comes complete with its own UI. You
must, however, create the UI for displaying guestbooks and entries. What sort of
UI would be best for this? Of course, the *best* UI for any purpose is a matter
of opinion. Displaying guestbooks and entries in a list is a good choice. Lists 
are common, compact design elements familiar to mobile users. Since most mobile 
devices don't have room to display a list of guestbooks and a list of entries at 
the same time, you need a user-friendly way to display and manage these lists. 
It makes sense to start off by showing the first guestbook's entries 
automatically after the user authenticates. This is similar to the Guestbook 
portlet's design: it shows a list of the first guestbook's entries by default. 
When the user selects a different guestbook, you can then use the same UI to 
show the selected guestbook's entries instead. 

![Figure 1: By default, the first guestbook in the portlet is selected.](../../../images/guestbook-portlet.png)

You must also decide how the users will select different guestbooks. Showing the 
list of guestbooks in a navigation drawer that slides out from the left side of 
the screen is a straightforward choice. A navigation drawer is easily hidden and 
is a common Android UI element. 

To display these lists of guestbooks and entries, you'll create your own 
Screenlets: Guestbook List Screenlet and Entry List Screenlet. The Guestbook 
List Screenlet retrieves guestbooks from the portlet and displays them in a 
simple list. Once written, using this Screenlet is a simple matter of inserting 
it in the navigation drawer. The Entry List Screenlet retrieves and displays a 
guestbook's entries in a similar list. You'll display the entries by inserting 
Entry List Screenlet in the UI element you want them to appear in. 

Note that these Screenlets aren't just any kind of Screenlet. They're *list 
Screenlets*. List Screenlets are developed using a special framework in Liferay 
Screens that sits on top of the core Screenlet framework. This list Screenlet 
framework makes it much simpler for developers to write Screenlets that retrieve 
and display lists of entities from a Liferay instance. 

Awesome! You now have a basic UI design and know the type of Screenlets you'll 
create to implement it. But where in the app will you use these Screenlets? The 
app only contains one empty activity: `MainActivity`. You'll use this activity 
for authentication, using Login Screenlet. To use your custom list Screenlets, 
you'll need to create an additional activity and a fragment: 
`GuestbooksActivity` and `EntriesFragment`. You'll create the activity in a 
moment. 

The following diagram shows the app's three different screens. Each activity and
fragment is labeled, along with the Screenlets and the navigation drawer. 

![Figure 2: The Liferay Guestbook app's design uses two activities and a fragment.](../../../images/android-app-design-screenlets.png)
<!-- re-label diagram with new Screenlet names -->

After sign in, the user transitions to `GuestbooksActivity`. This activity uses 
Entry List Screenlet in `EntriesFragment` to display the selected guestbook's 
entries (the first guestbook is selected by default). Pressing the hamburger 
button at the top-left of this screen opens the navigation drawer, where 
Guestbook List Screenlet displays the list of guestbooks. Selecting a guestbook 
closes the drawer to reveal that guestbook's entries. Note that you only need 
one activity, `GuestbooksActivity`, to display guestbooks and entries. The 
navigation drawer and `EntriesFragment` are part of this activity. 

Now you're ready to create `GuestbooksActivity`. Fortunately, Android Studio has 
a template for creating an activity that already contains a navigation drawer. 
In project view, right click the package `com.liferay.docs.liferayguestbook` and 
select *New* &rarr; *Activity* &rarr; *Navigation Drawer Activity* to launch the 
New Android Activity wizard. This is the same wizard you used to create an empty 
activity during project creation. Name the activity `GuestbooksActivity`, accept 
the default values for the remaining fields, and click *Finish*. After Android 
Studio creates the activity, the `GuestbooksActivity` class and 
`content_guestbooks.xml` layout open in the editor. Close them. You don't need 
to edit these files yet. 

Great! Now you understand the Liferay Guestbook app's basic design. You also 
have the basic app structure in place to implement its UI and features. You'll 
begin by authenticating users. 
