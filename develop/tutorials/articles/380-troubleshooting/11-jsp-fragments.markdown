# Why Aren't JSP overrides I Made Using Fragments Showing? [](id=why-arent-jsp-overrides-i-made-using-fragments-showing)

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
[click here](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies#finding-liferay-portal-app-and-independent-artifacts). 

For more information on overriding JSPs, 
[click here](/develop/tutorials/-/knowledge_base/7-0/overriding-jsps). 

## Related Topics [](id=related-topics)

[Overriding JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-jsps)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
