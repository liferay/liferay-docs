# Using the Get Guestbooks and Get Entries Screenlets [](id=using-the-get-guestbooks-and-get-entries-screenlets)

Now that you have the Get Guestbooks and Get Entries Screenlets, you're ready to 
put them to work. As you'll see, using these Screenlets isn't much more 
difficult than using Login Screenlet. This an advantage of Screenlets: it 
typically takes only a few minutes to get them up and running. They also 
integrate with the rest of your app's UI. 

You'll use the following steps to add your Screenlets to the app: 

1. Prepare `GuestbooksActivity` for Get Guestbooks Screenlet. 

2. Use the Get Guestbooks Screenlet by inserting it in `GuestbooksActivity`. 

3. Create `EntriesFragment` for Get Entries Screenlet. You'll also set 
   `GuestbooksActivity` to display this fragment when a guestbook is selected in 
   Get Guestbooks Screenlet. 

4. Use the Get Entries Screenlet by inserting it in `EntriesFragment`. 

If you get confused or stuck at any point in this section of the Learning Path, 
refer to the finished app's code 
[here in GitHub](https://github.com/ngaskill/liferay-docs/tree/LRDOCS-1816-android-lp/develop/learning-paths/mobile/code/06-using-your-screenlets/LiferayGuestbook). 
<!-- Change this link once the app gets merged in to 6.2.x -->

First, you'll prepare `GuestboooksActivity` for Get Guestbooks Screenlet. 
