# Enabling Staging [](id=enabling-staging)

@product@ provides site administrators with two different ways to set up
staging: Local Live and Remote Live. With Local Live staging, both your staging
environment and your live environment are hosted on the same server. When Local
Live staging is enabled for a site, a clone of the site is created containing
copies of all of the site's existing pages. Typically, this means the staging
and live environments share the same JVM, database, portlet data (depending on
which portlets are selected when staging is enabled), and setup configurations,
such as the properties set in the `portal-ext.properties` file. The cloned site
becomes the staging environment and the original site becomes the live
environment.

When Remote Live staging is enabled for a site, a connection is established
between the current site and another site on a remote Liferay server. The remote
site becomes the live environment and the current site becomes the staging
environment--an instance of Liferay used solely for staging. Content
creators can use the staging server to make their changes while the live server
handles the incoming user traffic. When changes to the site are ready to be
published, they are pushed over the network to the remote live server. Whether
you enable Local Live or Remote Live staging, the interface for managing and
publishing staged pages is the same.

So when should you use Local Live staging and when should you use Remote Live
staging? Local Live staging lets you publish site changes very quickly, since
the staged and live environments are on the same server. It's also easier to
switch between the staged and live environments using Local Live staging. Since
the staged content, however, is stored in the same database as the production
content, your server needs to have more resources, and the content isn't as well
protected or backed up as with Remote Live staging. Also, you can't install new
versions of apps for testing purposes in a Local Live staging environment, since
only one version of an app can be installed at any given time on a single
Liferay server.

With Remote Live staging, your staging and live environments are hosted on
separate servers, so your data is separated. This lets you deploy new versions
of apps and content to your staging environment without interfering with your
live environment. However, publishing is slower with Remote Live staging since
data must be transferred over a network. Of course, you also need more
hardware to run a separate staging server.
