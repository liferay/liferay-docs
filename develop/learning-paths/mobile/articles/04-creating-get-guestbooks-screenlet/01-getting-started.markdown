# Getting Started with the Get Guestbooks Screenlet [](id=getting-started-with-the-get-guestbooks-screenlet)

Before creating a Screenlet, you need to know where you'll use it. If you only 
plan to use it in one app, then you can create it in that app's project. If you 
need to use it in several apps, however, then it's best to create it in a 
separate project for redistribution. For information on creating Screenlets for 
redistribution, see the tutorial 
[Packaging Your Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/packaging-your-android-screenlets).
Since you'll use the Get Guestbooks Screenlet only in this app, you can create 
the Screenlet in a new package inside the app's project. 

Right click the *java* folder in Android Studio's project view and select 
*New* &rarr; *Package*. Select *.../app/src/main/java* as the destination 
directory, and click *OK*. Then enter *com.liferay.docs.getguestbooksscreenlet* 
as the package's name and click *OK*. Android Studio now lists the new package 
alongside the package containing the app's activities (`liferayguestbook`). If 
it doesn't appear at first, you may need to collapse and reopen the *java* 
folder in project view.

![Figure 1: The new package for the Get Guestbooks Screenlet is highlighted.](../../images/android-guestbooks-screenlet-package.png)

Now you're ready to get started on the Screenlet itself.

## Encapsulating Guestbooks [](id=encapsulating-guestbooks)

As you did when you used the Guestbook Mobile SDK directly, you need to 
encapsulate Guestbooks retrieved from the portlet. You can even reuse the 
existing `GuestbookModel` class. In Android Studio's project view, copy 
`GuestbookModel` and paste it in the `getguestbooksscreenlet` package. In the 
*Copy Class* dialogue that appears, accept the defaults and click *OK*. If you 
need a review of how `GuestbookModel` works, see 
[this Learning Path article](http://www.liferay.com/).

Next, you need to create the Screenlet's UI.
