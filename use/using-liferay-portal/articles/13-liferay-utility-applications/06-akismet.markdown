# Akismet [](id=akismet)

Akismet is a web-based spam detection service. The Akismet app, available from
Liferay Marketplace, integrates Liferay with Akismet to provide spam detection
for Liferay's message boards, blogs, wikis, and comments. When you install the
Akismet app from Liferay Marketplace, two portlets are added to the Control
Panel: the *Akismet* portlet is added to the Configuration section and the *Spam
Moderation* portlet is added to the Content section of Site Administration.

In order to enable Liferay to use Akismet, you need to visit
[http://akismet.com](http://akismet.com), create an account, and generate an API
key. Once you've generated an API key, navigate to the Configuration section of
the Control Panel and click on *Akismet*. Enter your API key into the provided
field and check the *Enabled for Message Boards* and *Enabled for Discussion*
boxes. The term "discussions" is another way to refer to comment threads in
Liferay. Optionally, you can customize the *Reportable Time* and the *Check
Threshold*. The Reportable Time indicates the time in days after a post is
created or updated that it can be marked as spam or not spam. The Check
Threshold indicates the number of posts after which Akismet will no longer
check posts. After you've finished configuring Akismet, click *Save*.

To see Akismet in action, add some message board posts or comment on some
existing Liferay assets. See if you can get Akismet to mark one as spam. If
Akismet doesn't mark a post as spam, you can always manually do as an
administrator. Once one or more posts have been marked as spam, navigate to the
Content section of the Site Administration section of the Control Panel, then
click on *Spam Moderation*. Here, you can find all the message board posts,
discussions (comments), and wiki pages that have been marked as spam. You can
select all the posts that you agree are spam and delete them. You can also
select any posts that you don't agree are spam and mark them as *Not Spam*. If
you're not sure about a post, click the *Actions* button and select *View in
Context* to view the post in its original setting.
