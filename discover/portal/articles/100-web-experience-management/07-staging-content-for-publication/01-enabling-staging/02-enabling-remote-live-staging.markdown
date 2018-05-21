# Enabling Remote Live Staging [](id=enabling-remote-live-staging)

In Remote Live staging, a connection is established between the current site and
another site on a remote Liferay server. The remote site becomes the live
environment and the current site becomes the staging environment---an instance
of Liferay used solely for staging. The remote (live) Liferay server and the
local (staging) Liferay server should be completely separate systems. They
should not, for example, share the same database. When Remote Live staging is
enabled, all the necessary information is transferred over the network
connecting the two servers. Content creators use the staging server to make
their changes while the live server handles the incoming user traffic. When
changes to the site are ready to be published, they are pushed over the network
to the remote live server. 

Before enabling Remove Live staging, ensure you've configured your Liferay
server and remote server appropriately. Follow the
[Configuring Servers for Remote Live Staging](/discover/portal/-/knowledge_base/7-1/configuring-servers-for-remote-live-staging)
article to do this.

You can enable remote staging for a site by navigating to the *Publishing*
&rarr; *Staging* menu. Step through the instructions below to create a Remote
Live staging environment for your site. 

1.  Navigate to the Product Menu (left side) and select *Publishing* &rarr;
    *Staging*.

2.  Select *Remote Live*. Additional fields appear for Remote Live Connection
    Settings.

    ![Figure 1: After your remote Liferay server and local Liferay server have been configured to communicate with each other, you have to specify a few Remote Live connection settings.](../../../../images/remote-live-staging-settings.png)

3.  Enter your remote Liferay server's IP address into the Remote Host/IP field.
    If the remote Liferay server is a cluster, you can set the Remote Host/IP to
    the load balanced IP address of the cluster in order to increase the
    availability of the publishing process. See the
    [Configuring Remote Staging in a Clustered Environment](/discover/deployment/-/knowledge_base/7-0/configuring-remote-staging-in-a-clustered-environment)
    for details.

4.  Enter the port on which the remote Liferay instance is running into the
    Remote Port field. You only need to enter a Remote Path Context if a
    non-root portal servlet context is being used on the remote Liferay server.

5.  Enter the ID of the site on the remote Liferay server that's for the Live
    environment. If a site hasn't already been prepared on the remote Liferay
    server, you can log in to the remote Liferay server and create a new blank
    site.

    After the site has been created, note the site ID so you can enter it into
    the Remote Site ID field on your local Liferay server. You can find any
    site's ID by selecting the site's name on the Sites page of the Control
    Panel.
 
6.  Check the *Use a Secure Network Connection* field to use HTTPS for the
    publication of pages from your local (staging) Liferay server to your remote
    (live) Liferay server.

7.  Decide whether to enable page versioning and select staged content. For more
    information on these options, see the
    [Enabling Page Versioning and Staged Content](/discover/portal/-/knowledge_base/7-1/enabling-page-versioning-and-staged-content)
    article.

8.  Click *Save*.

You've officially begun the staging process!

If you fail to configure your current and remote server properly, you won't be
able to enable staging and an error message appears. If you have issues,
[verify you've configured your servers properly](/discover/portal/-/knowledge_base/7-1/configuring-servers-for-remote-live-staging).

When a user publishes changes from the local (staging) server to the remote
(live) server, @product@ passes the user's email address, screen name, or user
ID to the remote server to perform a permission check. For a publishing
operation to succeed, the operation must be performed by a user that has
identical credentials and permissions on both the local (staging) and the remote
(live) server. This is true regardless of whether the user attempts to publish
the changes immediately or attempts to schedule the publication for later.

If only a few users should have permission to publish changes from staging to
production, it's easy enough to create a few user accounts on the remote server
that match a selected few on the local server. The more user accounts that you
have to create, however, the more tedious this job becomes and the more likely
you are to make a mistake. And you not only have to create identical user
accounts, you also have to ensure that these users have identical permissions.
For this reason, it's recommended that you use LDAP to copy selected user
accounts from your local (staging) Liferay server to your remote (live) Liferay
server. Liferay's Virtual LDAP Server application, available on Liferay
Marketplace, makes this easy.

If you ever need to turn off the staging environment, return back to *Staging*
from the Publishing dropdown. For more information on this, see the
[Disabling Staging](/discover/portal/-/knowledge_base/7-1/disabling-staging)
article.

Great! Now you're ready to use Remote Live Staging.
