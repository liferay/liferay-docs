# Designing Your App [](id=designing-your-app-ios)

As a developer, you know that developing any kind of app without an overall 
design goal and plan to implement it is a recipe for disaster. To avoid this, 
you need to decide some things upfront. The Liferay Guestbook app needs a 
straightforward way to do three things: 

1. Authenticate users
2. Display guestbooks
3. Display entries

To authenticate users, all you need to do is insert and configure 
[Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios) 
in your app. Login Screenlet comes complete with its own UI. The design for
authentication, therefore, like with @product@ itself, is done for you. 

You must, however, create the UI for displaying guestbooks and entries. What 
sort of UI would be best for this? Although the *best* UI for any purpose is a 
matter of opinion, displaying guestbooks and entries in lists is a good choice. 
Lists are common, compact design elements familiar to mobile users. Since most 
mobile devices don't have room to display a list of guestbooks and a list of 
entries at the same time, you also need a user-friendly way to display and 
manage these lists. It makes sense to show a list of guestbooks automatically 
after the user authenticates. The user can then select a guestbook to view a 
list of its entries. Users are familiar with this drill-down navigation style, 
as it's common throughout iOS. 

To display these lists, you'll create your own Screenlets: Guestbook List 
Screenlet and Entry List Screenlet. Guestbook List Screenlet needs to retrieve 
guestbooks from the portlet and display them in a simple list. Once written, 
using this Screenlet is a simple matter of inserting it in a scene. Entry List 
Screenlet needs to retrieve and display a guestbook's entries in a similar list. 
You'll display the entries by inserting Entry List Screenlet in another scene. 

Also note that these Screenlets are *list Screenlets*. You develop list 
Screenlets by using the list Screenlet framework, which sits on top of the core 
Screenlet framework. The list Screenlet framework makes it easy for developers 
to write Screenlets that display lists of entities from a @product@ instance. 

![Figure 1: After login, the user transitions to the guestbooks scene where Guestbook List Screenlet displays a list of guestbooks. Upon selecting a guestbook, the entries scene displays a list of that guestbook's entries with Entry List Screenlet. Because the guestbooks and entries scenes are embedded in a navigation controller, the user can navigate back to the guestbooks scene via a back button in the navigation bar.](../../../images/ios-app-design.png)

Awesome! Now you have a basic UI design and know the Screenlets you'll create to 
implement it. But the app currently contains only one empty scene, which you'll 
use for authentication with Login Screenlet. To use your custom list Screenlets, 
you'll need to create two additional scenes. You'll do this as you develop the 
app. 

Great! Now you understand the Liferay Guestbook app's design. Next, you'll use 
Login Screenlet to implement authentication. 
