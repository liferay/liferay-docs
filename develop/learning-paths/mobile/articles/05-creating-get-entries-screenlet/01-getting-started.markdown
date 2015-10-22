# Getting Started with Get Entries Screenlet [](id=getting-started-with-get-entries-screenlet)

Like Get Guestbooks Screenlet, you'll create Get Entries Screenlet in your app's 
project. Get started by creating the package 
`com.liferay.docs.getentriesscreenlet`. Once you have this package, 
you're ready to start writing the Screenlet.

## Encapsulating Entries [](id=encapsulating-entries)

As with Guestbooks, you need to encapsulate Entries retrieved from the portlet. 
In Android Studio's project view, copy the existing `EntryModel` class and paste 
it in the `getentriesscreenlet` package. In the *Copy Class* dialogue that 
appears, accept the defaults and click *OK*. If you need a review of how 
`EntryModel` works, see 
[this Learning Path article](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieve-and-display-entries#encapsulating-entries).

Next, you need to create the Screenlet's UI.
