---
header-id: why-arent-jsp-overrides-i-made-using-fragments-showing
---

# Why Aren't JSP overrides I Made Using Fragments Showing?

[TOC levels=1-4]

The fragment module must specify the exact version of the host module. A 
@product@ upgrade might have changed some JSPs in the host module, prompting a 
version update. If this occurs, check that your JSP customizations are 
compatible with the updated host JSPs and then update your fragment module's 
targeted version to match the host module. 

For example, this `bnd.bnd` file from a fragment module uses `Fragment-Host` to 
specify the host module and host module version: 

    Bundle-Name: custom-login-jsp
    Bundle-SymbolicName: custom.login.jsp
    Bundle-Version: 1.0.0
    Fragment-Host: com.liferay.login.web;bundle-version="1.1.18"

For information on finding the versions of your deployed modules, 
[click here](/docs/7-0/tutorials/-/knowledge_base/t/configuring-dependencies#finding-liferay-app-and-independent-artifacts). 

For more information on overriding JSPs, 
[click here](/docs/7-0/tutorials/-/knowledge_base/t/overriding-jsps). 

## Related Topics

[Overriding JSPs](/docs/7-0/tutorials/-/knowledge_base/t/overriding-jsps)

[Configuring Dependencies](/docs/7-0/tutorials/-/knowledge_base/t/configuring-dependencies)
