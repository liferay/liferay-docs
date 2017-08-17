# Why Aren't JSP overrides I Made Using Fragments Showing?

The fragment module must specify the exact version of the host module. A 
@product@ upgrade might have changed some JSPs in the host module, prompting a 
version update. If this occurs, check that your JSP customizations are 
compatible with the updated host JSPs and then update your fragment module's 
targeted version to match the host module. For more information, 
[click here](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps) 
to see the tutorial on overriding app JSPs. 

## Related Topics

[Overriding App JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps)
