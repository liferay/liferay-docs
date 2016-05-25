# Using the Get Guestbooks and Get Entries Screenlets [](id=using-the-get-guestbooks-and-get-entries-screenlets)

Now that you have the Get Guestbooks and Get Entries Screenlets, you're ready to 
put them to work. As you'll see, using these Screenlets isn't much more 
difficult than using Login Screenlet. This an advantage of Screenlets: it 
typically takes only a few minutes to get them up and running. They also 
integrate with the rest of your app's UI. 

You'll follow these steps when adding your Screenlets to the app: 

1. Understand how `GuestbooksActivity`'s UI works. Since your Screenlets augment 
   this UI instead of replacing it, you should first understand how it works. 

2. Prepare `GuestbooksActivity` for Get Guestbooks Screenlet. 

3. Use Get Guestbooks Screenlet by inserting it in `GuestbooksActivity`. 

4. Create `EntriesFragment` for Get Entries Screenlet. You'll also set 
   `GuestbooksActivity` to display this fragment when a guestbook is selected in 
   Get Guestbooks Screenlet. 

5. Use Get Entries Screenlet by inserting it in `EntriesFragment`. 

If you get confused or stuck at any point in this section of the Learning Path, 
refer to the finished app's code 
[here in GitHub](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/code/04-mobile/screenlets-app/LiferayGuestbook). 

First, you'll see how `GuestbooksActivity`'s UI works. 
