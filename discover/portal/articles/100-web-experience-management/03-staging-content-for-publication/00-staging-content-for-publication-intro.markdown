# Staging Content for Publication [](id=staging-content-for-publication)

Today's enterprises are generating an enormous amount of content for their
users. @product@ provides advanced publishing tools to make the content easily and
reliably available for users.

Staging is an important feature of Liferay WCM. The concept of staging is a
simple one: you can modify your site behind the scenes and then publish all your
updates in one shot. You don't want users seeing your web site change before
their eyes as you're modifying it, do you? @product@'s staging environment allows
you to make changes to your site in a specialized *staging area*, which is
linked to a production environment. Typically the staging site has a more
limited audience (e.g., content editors, site administratros, etc.) while the
production environment is accessible for most users. When you're finished, you
can publish all your site changes at once.

Site administrators can set up their staging environments locally or remotely.
With Local live staging, your staging environment and live environment are
hosted on the same server, whereas Remote live staging has the staging and live
environments on separate servers. You'll learn more about the differences
between these two staging environments and how to properly enable them for your
Liferay instance.

@product@ also offers the Page Versioning feature. This feature works with both
Local Live and Remote Live staging and allows site administrators to create
multiple variations of staged pages. This allows several different versions of
sites and pages to be developed at the same time. Variations can be created,
merged, and published using a Git-like versioning system. In the next section,
you'll jump in to see how to enable staging.
