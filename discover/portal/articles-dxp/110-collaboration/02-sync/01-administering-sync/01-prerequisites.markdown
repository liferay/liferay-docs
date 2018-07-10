# Installing Liferay Sync's Prerequisites [](id=installing-liferay-syncs-prerequisites)

Liferay Sync requires that you install the following apps from Liferay 
Marketplace. Be sure to install them in this order: 

1.  [Liferay Connector to OAuth 1.0a](https://web.liferay.com/marketplace/-/mp/application/45261909): 
    Enables OAuth 1 in your @product@ installation (note that Sync isn't 
    compatible with OAuth 2). You **must** install this app **before** 
    installing the *Liferay Sync Connector* app. Otherwise, your portal will not 
    be able to start. 

2.  [Liferay Sync Connector](https://web.liferay.com/marketplace/-/mp/application/31709100): 
    Lets you enable and configure Sync in your @product@ instance. For example, 
    you can disable Sync across the instance or on a site-by-site basis. Note 
    that Sync is enabled by default for all your @product@ instance's sites. 
    You **must** install this app **after** installing the 
    *Liferay Connector to OAuth 1.0a* app. Otherwise, your portal will not be 
    able to start. 

+$$$

**Warning:** If you install *Liferay Sync Connector* before installing 
*Liferay Connector to OAuth 1.0a*, your portal will not be able to start. 

$$$

For instructions on installing Marketplace apps, see 
[the Liferay Marketplace documentation](/discover/portal/-/knowledge_base/7-1/using-the-liferay-marketplace). 

+$$$

**Note:** The Liferay Sync Security module that Sync requires is included and 
enabled by default. You can verify this by ensuring that the `SYNC_DEFAULT` and
`SYNC_TOKEN` entries are enabled in *Control Panel* &rarr; *Configuration*
&rarr; *Service Access Policy*. 

$$$

If you want to use Sync Connector's default settings and are fine with enabling
Sync for all your Sites, you can skip the articles that follow on configuring
Sync. However, before directing your users to install and configure the Sync
desktop and mobile clients, **make sure to read** this guide's articles on
preventing accidental file deletion and ensuring Sync security. You should also
**warn your users** about the potential for accidental data loss. 

## Configuring Sync to Use SSO [](id=configuring-sync-to-use-sso)

If you use an SSO (single sign-on) solution, you must ensure that Sync can
access the following URLs without being redirected to your SSO server. Sync
can't work without direct access to these URLs. You must therefore whitelist
these URLs: 

    http(s)://<portal-address>/c/portal/oauth/*
    http(s)://<portal-address>/api/jsonws/sync-web.*
    http(s)://<portal-address>/sync-web/*

For example, if your installation's address is `https://www.joesblog.com`, then
you must whitelist the following URLs: 

    https://www.joesblog.com/c/portal/oauth/*
    https://www.joesblog.com/api/jsonws/sync-web.*
    https://www.joesblog.com/sync-web/*

Sync uses the paths specified in the first URL for communication via OAuth, and 
the paths specified in the remaining URLs for normal communication with your 
@product@ installation. 

You must also enable OAuth in the Sync Connector app. The next articles cover 
this, as well as other information on how to configure Sync. 
