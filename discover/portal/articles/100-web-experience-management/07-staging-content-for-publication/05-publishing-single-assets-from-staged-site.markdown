# Publishing Single Assets From a Staged Site [](id=publishing-single-assets-from-a-staged-site)

Sometimes, stepping through the entire publication process is not necessary and
can be overkill. For example,

- What if you created a web content article and want only to publish it and
  nothing else?
- What if you want to publish a new folder of articles and their dependencies,
  but don't want the hassle of the publication process?
- What if you found a typo in a document and want to fix it quickly and push to
  the live site?

You're in luck! You can publish certain single assets from the staged site to
the live site without creating a staging publication process, from their
respective app menus:

- Web Content
    - Web Content
    - Folder
- Documents and Media
    - Document
    - Folder
    - Shortcut
    - Document Type
- Blogs
    - Blog
- Bookmarks
    - Bookmark
    - Folder

**Important:** Single asset publication is not supported for page-scoped
content.

+$$$

**Note:** When publishing a Web Content or Bookmarks folder, their respective
entries and subfolders are included. Publishing a Documents and Media folder
works the same way, but also includes shortcuts.

$$$

You'll step through an example to see how this is done.

1.  Make sure the Staging framework is
    [enabled](/discover/portal/-/knowledge_base/7-1/enabling-staging) and you're
    on the staged site.

2.  Create a Web Content Article in the Product Menu's *Content* &rarr; *Web
    Content* menu.

3.  Once you've saved the new Web Content Article, select its the *Actions*
    button (![Actions](../../../images/icon-actions.png)) next to the article
    and select *Publish to Live*.

    ![Figure 1: You can publish the single web content article to the live site.](../../../images/single-asset-publish.png)

4.  You're presented a Process Details page where you can view the progress of
    your single asset publication request. Ensure the Web Content Article is
    published successfully.

    +$$$

    **Note:** Sometimes the publication process doesn't start immediately (e.g.,
    if there's another publication running). You can check a specific asset's
    publication progress by navigating to the *Options*
    (![Options](../../../images/icon-options.png)) &rarr; *Staging* &rarr;
     *Current and Previous* tab in its Site Admin app.

    $$$

There you have it! If you navigate to your live site's Web Content section, the
new article is available.

Similar to the regular staging publication process, your single asset
publications also include associated dependencies. For example, if your web
content article contains an image, custom structure, and custom template, they
are all published together. The same concept applies for folders---if you
publish a folder containing several web content articles, all the articles and
their associated dependencies are published too.

By default, only those with permissions to publish widgets can publish single
assets. Follow the steps below to modify these permissions for a Role:

1.  Navigate to the Control Panel &rarr; *Users* &rarr; *Roles*.

2.  Select the Role you're updating.

3.  Click the *Define Permissions* tab.

4.  In the left menu, navigate to *Control Panel* &rarr; *Sites* &rarr; *Sites*.

5.  Under the Resource Permissions heading, select the *Export/Import
    Application Info* option.

Great! Now you know how to publish single assets and manage the permissions for
who can do it.
