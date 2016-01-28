# Getting Started with the Mobile SDK

The app you'll create here with the Mobile SDK requires the same preparation as 
the one you created earlier. You've already set up the Guestbook portlet in a 
local Liferay instance and built the Guestbook Mobile SDK, so you can skip those 
steps. You must still, however, create a new Android project and install the 
Guestbook Mobile SDK and Liferay Screens in it. You might now be thinking, 
"What?! I thought I was only going to use the Mobile SDK! Why do I need to 
install Screens too?" You must install Screens because you're still going to use 
Login Screenlet for authentication. You could create your own login 
implementation with the Liferay Mobile SDK, but this makes no sense. You'd be 
reinventing the wheel that Screens already invented for you in Login Screenlet. 
To get started, follow the earlier instructions from the 
[article on creating the Android project](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-the-android-project) 
through the 
[article on authenticating with Login Screenlet](/develop/learning-paths/mobile/-/knowledge_base/6-2/using-login-screenlet-for-authentication). 
When you finish, return here to resume this portion of the Learning Path. Next, 
you'll use the Guestbook Mobile SDK to retrieve guestbooks. 
