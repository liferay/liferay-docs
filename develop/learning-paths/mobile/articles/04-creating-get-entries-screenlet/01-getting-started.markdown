# Getting Started with Get Entries Screenlet [](id=getting-started-with-get-entries-screenlet)

Like Get Guestbooks Screenlet, you'll create Get Entries Screenlet in a new 
package inside your app's project. You'll do this because you don't need to 
redistribute the Screenlet. Get started by creating the package 
`com.liferay.docs.getentriesscreenlet`. Once you have this package, you're ready 
to start writing the Screenlet. 

## Creating the Model Class for Entries

As with Get Guestbooks Screenlet, Get Entries Screenlet must use the same 
Guestbook Mobile SDK you built earlier to call the Guestbook portlet. Thus, the 
Screenlet needs a model class that can transform the JSON containing the entries 
into proper entry model objects. To do this, you can reuse the existing 
`EntryModel` class. In Android Studio's project view, copy `EntryModel` and 
paste it in the `getentriesscreenlet` package. In the *Copy Class* dialogue that 
appears, accept the defaults and click *OK*. If you need a review of how 
`EntryModel` works, see 
[this Learning Path article](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieve-and-display-entries#creating-the-model-class-for-entries).

Next, you need to create the Screenlet's UI. 
