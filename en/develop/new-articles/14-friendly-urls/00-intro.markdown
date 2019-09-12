# Making URLs Friendly [](id=making-urls-friendly)

You're probably feeling good about the Guestbook Portlet now. The UI is looking
snazzy, it has front- and back-end validation, and you've implemented several
other features that a modern app should have. But before you jump off the
learning paths and begin trailblazing Liferay development on your own, click on
one of your Guestbook app's buttons, like *Add Guestbook*. Check out the URL
that the portal so generously generated:

    http://localhost:8080/web/guest/home?p_p_id=guestbook_WAR_guestbookportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_pos=1&p_p_col_count=3&_guestbook_WAR_guestbookportlet_mvcPath=%2Fhtml%2Fguestbook%2Fedit_guestbook.jsp

As the developer of the app, maybe you like to see all those parameters as much
as Liferay likes to generate them for you. How do you think the app's
users will feel when they see the URL? Let's make it a little more friendly--
and meaningful--for them. Your app's users aren't the only reason to create
Friendly URLs. Search engine optimization requires that your URLs be *concise*.
That's a word you wouldn't use to describe the generated URL above.

You can greatly improve the readability of any portlet URLs in Liferay, and the
first part of this learning path will show you how.

If you've inspected the Guestbook App's URLs closely, did you notice that the
Primary Key of `Guestbook` and `Entry` entities are frequently used as URL
parameters? In the second part of this learning path, you'll remedy that by
substituting the `name` field of each entity for the Primary Key. Then your
portlet URLs will be even friendlier, which means they'll be more human
readable, more concise, and more likely to buy you dinner. Okay, that last one
is pushing it a bit, but the point remains: the resulting URLs will be Friendly.
