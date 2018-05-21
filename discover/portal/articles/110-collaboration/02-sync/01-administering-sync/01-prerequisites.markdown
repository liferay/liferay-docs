# Installing Liferay Sync's Prerequisites [](id=installing-liferay-syncs-prerequisites)

Liferay Sync requires that your @product@ instance contains the most recent 
versions of the following 
[Liferay Marketplace](https://web.liferay.com/marketplace) 
apps: 

-   **Liferay CE IP Geocoder:** Uses geographic IP lookup to get the approximate 
    location of devices that register with Sync. This is a security measure that 
    lets you see where the devices are that connect to your @product@ instance 
    via Sync. 

-   **Liferay CE Sync Connector:** Lets you enable and configure Sync in your 
    @product@ instance. For example, you can disable Sync across the instance or 
    on a site-by-site basis. Note that Sync is enabled by default for all your 
    @product@ instance's sites. 

These apps are preinstalled in the latest @product@ releases. To verify that 
these apps are installed in your @product@ instance, navigate to *Control Panel* 
&rarr; *Apps* &rarr; *App Manager*. The App Manager lists the installed apps. 
It also lets you search for them via the *Search* field. If your @product@ 
instance doesn't contain these apps, install them from Liferay Marketplace. 
For instructions on this, see 
[the Liferay Marketplace documentation](/discover/portal/-/knowledge_base/7-1/using-the-liferay-marketplace). 

Note that older Sync 1.x clients can't connect to the latest Sync Connector app. 

+$$$

**Note:** The Liferay Sync Security module that Sync requires is included and 
enabled by default in @product@. You can verify this by ensuring that the 
`SYNC_DEFAULT` and `SYNC_TOKEN` entries are enabled in *Control Panel* &rarr; 
*Configuration* &rarr; *Service Access Policy*. 

$$$

If you want to use Sync Connector's default settings and are fine with Sync 
being enabled for all your @product@ instance's sites, you can skip the articles 
that follow on configuring sync. However, before directing your users to install 
and configure the Sync desktop and mobile clients, **make sure to read** this 
guide's articles on preventing accidental file deletion and ensuring Sync 
security. You should also **warn your users** about the potential for accidental 
data loss. 
