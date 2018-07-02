# Installing Liferay Sync's Prerequisites [](id=installing-liferay-syncs-prerequisites)

Liferay Sync requires the *Liferay CE Sync Connector* app from 
[Liferay Marketplace](https://web.liferay.com/marketplace). This app enables and
configures Sync in your @product@ instance. For example, you can disable Sync
across the instance or on a site-by-site basis. Note that Sync is enabled by
default for all Sites. 

For instructions on installing Marketplace apps, see 
[the Liferay Marketplace documentation](/discover/portal/-/knowledge_base/7-1/using-the-liferay-marketplace). 

+$$$

**Note:** The Liferay Sync Security module that Sync requires is included and 
enabled by default in @product@. You can verify this by ensuring that the 
`SYNC_DEFAULT` and `SYNC_TOKEN` entries are enabled in *Control Panel* &rarr; 
*Configuration* &rarr; *Service Access Policy*. 

$$$

If you want to use Sync Connector's default settings and are fine with Sync 
being enabled for all your Sites, you can skip the articles that follow on
configuring Sync. However, before directing your users to install and configure
the Sync desktop and mobile clients, **make sure to read** this guide's articles
on preventing accidental file deletion and ensuring Sync security. You should
also **warn your users** about the potential for accidental data loss. 
