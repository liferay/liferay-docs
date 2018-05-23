# Configuring Search Pages

In @product-ver@ there are multiple options for the default search experience.
If you're unsure which approach to take, use the
[default](#default-search-pages). It provides a sensible starting point. If
you've been using @product@ for a long time and like the search experience
you've always used, use the [legacy approach](#legacy-search-experience). If
you're in need of a fully customized experience, [manually
configure](#manual-search-page-configuration) the search experience. After
choosing your approach and reading here to get it up and running, find the
articles on the [Search Bar](), [Search Facets](), and [Search Results]() to
understand the full suite of configuration options. 

Search display pages are where users go to enter search terms and browse search
results. 

## Default Search Pages

Using the default site and the default theme with the default search settings,
the out-of-the-box search experience has two components for end users:

1. A search bar embedded on each page.
2. A default search page where search requests are routed.

Behind the scenes, The search bar widget is configured to point to the default
search page with the friendly URL `/search`.

![Figure x: By default, the embedded search bar points to the pre-configured `/search` destination page.](../../images/search-dest-page.png)

Enter a search term and you're redirected to the default search page, where
results are displayed in the Search Results widget.

![Figure x: The default page is pre-configured with the Search Results widget and the various Facet widgets to provide a full search experience.](../../images/search-default-page.png)

The default search page is based on a Search page template, but it's configured
to not inherit changes from the page template. That means you can start
customizing the search page without messing with the page template's inheritance
configuration.

![Figure x: Configure the Search page. By default, it doesn't inherit changes
from the page template.](../../images/search-page-config.png)

+$$$

**Note:** If you change the friendly URL of the default search page, a new page
with the `/search` URL is created when you restart the portal. To avoid this,
navigate to System Settings &rarr; Search &rarr; Search Web. Check the box for
*Skip Automatic Creation of Search Page in Guest Site*. Now you can be certain
that the page won't be recreated on portal restart.

$$$

If you require just a few changes to the default page, don't abandon it and
create one manually. Just make the configuration changes you need, including
adding more widgets to the page and configuring widgets on the page. On the
other hand, if you want a clean break from the default search page, starting
from scratch is also an option.

## Manual Search Page Configuration

It's reasonable to create the search experience from the ground up. If you're
working from a newly created site, it's a necessity. These steps will show you
how to switch to a manually configured search experience in the default site,
but you can skip the steps on disabling the default search experience if you're
starting with a new site:

1.  There's a configuration to avoid the creation of the default search page.
    You must use this setting so that once you delete the default search page,
    it doesn't come back from the dead every time you restart the portal. 

    Navigate to System Settings &rarr; Search &rarr; Search Web. Check the box
    for *Skip Automatic Creation of Search Page in Guest Site*. Now you can be
    certain that the page won't be recreated on portal restart.

2.  Delete the existent search page by navigating to the default site's menu and
    clicking *Build* &rarr; *Site Pages*. Click the Search page's Options menu
    icon (![Options](../../images/icon-options.png)) and select *Delete*.
    Confirm you want to delete the page, and it's gone.

    Once deleted, the search bar disappears from your site pages, replaced by a
    warning message visible only to site administrators:

    ![Figure x: The search bar is only visible if it points to an existent page.](../../images/search-bar-warning.png)

2.  Create a new page named whatever you want (_Finders Keepers_, perhaps). Make
    it hidden or add it to the navigation, as you please (the default search
    page is hidden from the navigation).

    If you want a pre-configured search page, create it from the Search page
    template. Find the template in the Add Site Page form. It's under *Global
    Templates*.

    ![Figure x: There's a handy page template for creating search pages.](../../images/search-page-template.png)

3.  If you're creating a page not backed by the template, add and configure all
    the widgets you need. You'll find all the available search widgets in the
    Add Widget menu's Search section. Lay them out however you'd like on the
    page.

4.  Configure the search bar at the top of the page, making sure it points to your
    new search page's friendly URL (for example, `/finders-keepers`). 

    Click the Search Bar widget's Actions menu (![Actions](../../images/icon-actions.png)).

    Click *Configuration* and set the Destination Page to the search page's
    friendly URL.

    Click *Save*.

Now your search page is up and running.

## Legacy Search Experience

In prior versions, the search experience was encapsulated in one application,
*Search*. This application was embedded in the default theme, just like the
search bar is now. It looked very similar, with only the search bar visible in
the default view of the application. In the legacy search application, once a
search term is entered, the maximized view of the application is presented, with
the search facets and results now in view. It looks a lot like the new search
behavior, only it's monolithic structure means it's not as easily customized. If
you like the old application, take heart. It's still available. To use the
legacy search, follow these steps:

1.  Delete the default search page. From the site menu, click *Build* &rarr;
    *Site Pages*. Click the Options menu
    (![Options](../../images/icon-options.png)) and choose *Delete*. 

2.  Disable the automatic creation of a search page in the default site.
    Navigate to System Settings &rarr; Search &rarr; Search Web. Check the box
    for *Skip Automatic Creation of Search Page in Guest Site*. Now you can be
    certain that the page won't be recreated on portal restart.

    Don't leave the System Settings page yet.

3.  Enable the legacy search application. In the Search Web System Settings entry, click 

4.  Restart the portal.

Now your portal's search is backed by the legacy Search application, and it's
embedded on each page in the default theme. To add the legacy Search application
to a page, open the Add Widget menu, find the Search widget under the Tools
category, and drag and drop it onto the page.

Configure the default search behavior to suit your needs. Here you've seen three
distinct search configurations for your portal pages.
