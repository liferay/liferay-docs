# Getting Started with the Mobile SDK [](id=getting-started-with-the-mobile-sdk)

The app you'll create here with the Mobile SDK requires the same preparation as 
the one you created earlier. You've already set up the Guestbook portlet in a 
local Liferay instance and built the Guestbook Mobile SDK, so you can skip those 
steps. You must still, however, create a new Android project and install the 
Guestbook Mobile SDK and Liferay Screens in it. You might now be thinking, 
"What? I thought I was only going to use the Mobile SDK! Why do I need to 
install Screens?" You'll install Screens because you're still going to use Login 
Screenlet for authentication. You could create your own login implementation 
with the Mobile SDK, but this makes no sense. Login Screenlet already provides a 
complete login implementation, and it only takes a few minutes to put in your 
app. It also includes helper classes that you'll use to authenticate your Mobile 
SDK calls. 

To get started, follow the earlier instructions from the 
[article on creating the Android project](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-the-android-project) 
through the 
[article on authenticating with Login Screenlet](/develop/learning-paths/mobile/-/knowledge_base/6-2/using-login-screenlet-for-authentication). 
In the article on designing your app, skip to the end and create 
`GuestbooksActivity`. Once you've authenticated with Login Screenlet, return 
here to resume this portion of the Learning Path. You'll then use the Guestbook 
Mobile SDK to retrieve guestbooks. This is covered next. 
