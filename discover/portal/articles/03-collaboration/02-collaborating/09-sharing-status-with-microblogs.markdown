# Sharing Status with Microblogs

Liferay's Microblogs Status Update and Microblogs apps let you post, receive, 
and share short, 150 character status updates from your personal pages in 
Liferay. This gives you a quick and simple way to interact with your social 
connections and other users. Microblogs Status Update is a very simple app that 
lets you post a status update and displays your most recent post. In addition to 
this functionality, the Microblogs app lets you: 

- View all your posts and those of your social connections. 

- Comment on and share posts. 

- View posts that mention you or contain a specific hashtag. 

Before delving into these details, you should understand where in your Liferay 
instance you'll use these apps. 

## Understanding Where to Use the Apps

Because Microblogs Status Update and Microblogs broadcast your personal status, 
you must use them from your Liferay personal site's pages. By default, these 
pages are called *My Profile* and *My Dashboard*. You can access both from the 
user menu. My Profile is public and serves as your public profile. Users and 
guests can view any apps you add to this page, although app appearance and 
functionality are typically limited. My Dashboard is private and by default 
contains the *My Sites* app. You can add other apps to this page to help you, 
and only you, manage your Liferay experience. You can add the Microblogs Status 
Update and Microblogs apps to both pages, but the apps behave differently 
depending on where they are. If you put them on a public page like My Profile, 
then they only display posts to logged in users. After all, you wouldn't want 
others to make posts on your behalf! The internet is crazy enough without 
granting others that kind of power. Logged in users can, however, comment on 
posts in the Microblogs app. Guests can see the apps, but not their contents. 
Things get a great deal more interesting when you add the apps to a private page 
that only you can access, like My Dashboard. In this case, you unleash the apps' 
full power. Next, you'll learn how to use these apps.

## Using Microblogs Status Update and Microblogs

As mentioned, Microblogs Status Update is a very simple app that lets you post 
your status, and displays only your most recent post. That's it. You can even 
configure it not to display this post. To do so, mouse over the app, click the 
*Options* button 
(![Options](../../../images/icon-app-options.png)) that appears on top of the 
app, and select *Configuration*. In the *Setup* tab, uncheck *Display Most 
Recent Status* and click *Save*. To post your status, enter it into the 
*Update Your Status* field and click the *Post* button that appears. You can 
also select who can view your post from the *Viewable By* menu next to the Post 
button. You can select *Everyone*, *Connections*, or *Followers*. For more 
information on connections and followers, see the section 
[Creating a Social Network](/discover/portal/-/knowledge_base/7-0/creating-a-social-network). 

![Figure 1: The Microblogs Status Update app lets you post your status, and displays your most recent post.](../../../images/microblogs-status-update.png)

There are two special symbols that you can use in your microblog posts. The 
hashtag symbol (`#`) turns the following word into a hashtag. When viewing a 
post, hashtags appear as links. You can click on any hashtag to view a list of 
posts in the Microblogs app that reference the same hashtag. Note that hashtags 
are distinct from Liferay tags--only Liferay tags are indexed and made available 
for search. The other special symbol you can use is the mentions symbol (`@`). 
When writing a post, typing the `@` symbol brings up a list of connections to 
select. Once you select a connection and publish your post, the connection's
username becomes a link to their public profile page. 

The Microblogs app is effectively the Microblogs Status Update app on steroids. 
You can post your status the exact same way. The Microblogs app, however, 
displays all your posts in the *Timeline* tab. The posts of your connections and 
those you follow also appear in the Timeline. You can also comment on 
any post, edit your posts, and repost the posts of others. The *Mentions* tab 
displays any posts that mention you. 

![Figure 2: The Microblogs app displays all your status posts, along with those of your social connections. It also lets you view any posts you're mentioned in.](../../../images/microblogs-app.png)


