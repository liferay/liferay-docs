# Retrieve and Display Entries

If you've completed the previous articles in this learning path, then you have 
an Android app that retrieves and displays guestbooks from a Guestbook portlet 
in Liferay. However, that's all it does. Tapping a guestbook in the navigation 
drawer doesn't show any of the guestbook's entries. In fact, tapping a guestbook 
doesn't do anything besides close the drawer to show an empty screen with the 
action bar. The action bar title is also still hardcoded with the `"Section *"` 
strings created as placeholders by Android Studio. 

![Figure 1: The guestbook entries aren't showing yet in the app.](../../images/android-entries-empty.png)

In this article you'll add the functionality that retrieves a guestbook's 
entries from the portal. Although the details differ slightly, you'll follow the 
same basic pattern you did when retrieving guestbooks from the portal: 
encapsulate the portlet entity and then create a callback class. It's time to 
get started by encapsulating the entries!

## Encapsulating the Entries

As with guestbooks, entries retrieved from the portlet should be encapsulated in 
your app. Create a new class in your app's `model` package by right clicking it 
and selecting *New* &rarr; *Java Class*. Name the new class *EntryModel*. 
Replace its contents with the following code:


