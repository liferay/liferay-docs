# Using the Get Guestbooks and Get Entries Screenlets [](id=using-the-get-guestbooks-and-get-entries-screenlets)

Now that you have the Get Guestbooks and Get Entries Screenlets, you're ready to 
put them to work. As you'll see, using these Screenlets isn't much more 
difficult than using Login Screenlet. This an advantage of using Screenlets: it 
typically takes only a few minutes to get them up and running. They also 
integrate seamlessly with the rest of your app's UI. 

You'll use the following steps to add your Screenlets to the app:

1. Refactor the Liferay Guestbook app. Since you'll use the Screenlets in the 
   same app you developed earlier, you need to remove the Guestbook Mobile SDK 
   code from the app. 

2. Use the Get Guestbooks Screenlet. You'll do this by inserting the Screenlet 
   in `GuestbooksActivity`. 

3. Use the Get Entries Screenlet. You'll do this by inserting the Screenlet in 
   `EntriesFragment`. 

If you get confused or stuck at any point in this section of the Learning Path, 
refer to the finished app's code 
[here in GitHub](https://github.com/ngaskill/liferay-docs/tree/LRDOCS-1816-android-lp/develop/learning-paths/mobile/code/06-using-your-screenlets/LiferayGuestbook). 
<!-- Change this link once the app gets merged in to 6.2.x -->

First, you'll refactor your app to remove the Guestbook Mobile SDK code.
