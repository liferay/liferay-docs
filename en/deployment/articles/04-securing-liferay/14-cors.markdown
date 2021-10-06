---
header-id: configuring-cors
---

# Configuring CORS

<aside class="alert alert-info">
   <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/securing-liferay/securing-web-services/setting-up-cors.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

[TOC levels=1-4]

CORS stands for Cross-Origin Resource Sharing. An Origin is a web server at
a different domain, and a Resource is some asset stored on the server, like an
image, PDF, or HTML file. Sometimes you must request resources stored on another
origin. This is called a cross-origin request, and web servers have policies to
allow or deny such requests. 

For example, browsers themselves don't allow cross-origin AJAX-style requests
from scripts to help mitigate 
[cross-site scripting](https://en.wikipedia.org/wiki/Cross-site_scripting)
attacks. These APIs follow a *same origin* policy. But for certain resources, it
can be convenient to allow @product@ to serve them to different origins. 

For example, if you manage images in Docs & Media, you may want to allow
cross-origin requests for them. You can enable CORS for matching URLs in
@product@ or for JAX-RS application resources. 

## Enabling CORS for @product@ Services

You'll find the settings in Configuration &rarr; System Settings &rarr; Security
&rarr; Security Tools &rarr; Portal Cross Resource Origin Sharing (CORS): 

1.  Click *Add* to create a configuration entry. 

2.  Fill out the fields on the form. When finished, click *Save*. 

![Figure 1: The CORS system settings provide a way to configure CORS headers for Liferay services.](../../images/CORS-portal.png)

**Enabled:** Check this box to enable the entry. 

**Name:** Give the configuration entry a name. 

**URL Pattern:** Use the Plus button to add as many patterns as you need. Define
patterns that match URLs to the resources you want to share. For example, if you
have many attachments in the Knowledge Base application, you could define
this pattern: 

    /knowledge_base/*

This would define resources stored in the Knowledge Base as applicable to the
policy you set in the response header below. 

**CORS Response Headers:** Use the Plus button to add as many headers as you
need. Define policies for any of the [CORS headers](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers#CORS) 
here. 

You can also use a [configuration file](/docs/7-2/user/-/knowledge_base/u/understanding-system-configuration-files)
to configure CORS. 

## Enabling CORS for JAX-RS Applications

You'll find the settings in Configuration &rarr; System Settings &rarr; Security
&rarr; Security Tools &rarr; Web Contexts Resource Origin Sharing (CORS): 

1.  Click *Add* to create a configuration entry. 

2.  Fill out the fields on the form. When finished, click *Save*. 

![Figure 2: There's a separate system settings category for CORS web contexts.](../../images/CORS-jax-rs.png)

**Dynamic Web Context OSGi Filter:** Define an LDAP-style [filter](https://osgi.org/specification/osgi.cmpn/7.0.0/service.http.whiteboard.html) 
to define which JAX-RS whiteboard applications the CORS headers in this entry
apply to. This is the default filter: 

```properties
(&(!(liferay.cors=false))(osgi.jaxrs.name=*))
```
It applies CORS headers to all deployed JAX-RS whiteboard applications without
a `liferay.cors=false` property. This helps during development, but in
production you should use the narrowest configuration possible. 

**URL Pattern:** Use the Plus button to add as many patterns as you need. Define
patterns that match URLs to the web services you want to access. 

**CORS Response Headers:** Use the Plus button to add as many headers as you
need. Define policies for any of the [CORS headers](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers#CORS) 
here. 

[JAX-RS](/docs/7-2/frameworks/-/knowledge_base/f/jax-rs) developers can use the
`@CORS` annotation to set policies for their deployed applications. 
