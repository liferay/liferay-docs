# Publishing Basic Content [](id=publishing-basic-content)

Now that we've created and published our first piece of web content for
Nose-ster, it's time to display it. First, add the *Web Content Display* portlet
to our Welcome page by selecting *Add &rarr; Web Content Display* from the
Dockbar.

![Figure 2.18: Adding the Web Content Display
Portlet](../../images/add-web-content-display.png)

Once the portlet appears, drag it to the position on the page where you want
your content to appear. You can have as many Web Content Display portlets on a
page as you need, which gives you the power to lay out your content exactly the
way you want it. 

To add existing web content, select the *gear* icon on the lower left of the
portlet. You will see the message *Please select a web content from the list
below*. You have several options here.

Naturally, if your content appears in the list, you can simply select it. If
there is lots of published content available, you could search for the content
by name, ID, type, version, content and site (click the *Advanced* link to see
all the options). You can also show the available locales for your content. If
you're working on the page for a particular language, you can select the
translation of your content that goes with your locale.

![Figure 2.19: Publishing web content is a snap. At a minimum, you only have to
select the content you wish to publish. You can also enable lots of optional
features to let your users interact with your
content.](../../images/04-web-content-choosing-web-content.png)

If you have enabled OpenOffice.org integration with your portal, you can also
enable document conversion for your content. This gives your users the ability
to download your content in their format of choice. This is especially handy if
you are running a research or academically oriented site; users can very quickly
download PDFs of your content for their research projects.

Note that you also have other options, such as enabling a Print button, enabling
ratings so users can rate the content, enabling comments and enabling ratings on
comments.

The Print button pops the content up in a separate browser window that contains
just the content, without any of the web site navigation. This is handy for
printing the content. Enabling ratings shows one of two ratings interfaces
Liferay has: five stars or thumbs up and thumbs down. This can be set globally
in the `portal-ext.properties` file. See chapter 12 for further information
about this.

Enabling comments creates a discussion forum attached to your content which
users can use to discuss your content. Enabling ratings on comments gives your
users the ability to rate the comments. You may decide you want one, some or
none of these features, which is why they're all implemented as simple check
boxes to be enabled or disabled at need.

If you click the *Supported Clients* tab, you'll see you can choose the type of
client to which you want to expose content. This lets you target the large
screens of users' computers for expansive graphics and lots of special effects
or target the small screens of mobile devices with pertinent information and a
lightweight page. For now, leave both checked and click the *Save* button. You
can now close the configuration window.

To publish new content, select the *page and green plus icon* on the lower left
of the portlet. This launches the same full-featured editor you've already seen
in the Control Panel, which lets you add and edit content in place as you are
working on your page.

This is another example of the flexibility that Liferay Portal offers. At times,
you may want to add content directly into the Web Content Display portlet of the
page you're managing, especially if you are in the process of building the page.
At other times, you may want to use the Control Panel to create content, because
at that moment you're more concerned with the creation of the content and not
where the content will later be displayed. Liferay WCM supports both processes. 

Editing content that's already been published is just as easy as creating new
content is. You'll use the same exact tools. 

## Editing Content [](id=editing-content)

Once the content is displayed--whether you've selected content or created it in
the Web Content Display portlet--you can edit the content directly from the Web
Content Display portlet or from the Control Panel. To edit it from the Web
Content Display portlet, select the *pencil* icon to the lower left of the
portlet. This launches the WYSIWYG editor and from there you can make any
necessary changes.

![Figure 2.20: Edit, Select and Add Icons of Web Content Display
Portlet](../../images/web-content-display-icons.png)

When you publish your content this way, it becomes available immediately
(unless, of course, you have a workflow enabled, which we'll see below). This
happens whether you edit it in place or in the Control Panel.

Note: if you want to view your page the way your users will see it (i.e.,
without all those portlet controls and icons), go up to the Dockbar and select
*Toggle Edit Controls*. This makes all those extra controls you see as a portal
administrator disappear. If you need to use those controls again, just select
*Toggle Edit Controls* again. 

That's pretty much all there is to simple content creation. Whole sites have
been created this way. But if you want to take advantage of the full power of
Liferay's WCM, you'll want to use structures and templates found in chapter 3.
Next, let's see how you can manage your content with an approval process called
workflow. 
