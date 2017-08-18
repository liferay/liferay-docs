# Calling Non-OSGi Code that Uses OSGi Services [](id=calling-non-osgi-code-that-uses-osgi-services)

@product@'s static utility functions (e.g., `UserServiceUtil`, 
`CompanyServiceUtil`, `GroupServiceUtil`, etc.) are examples of non-OSGi code 
that use OSGi services. 

Note that it's safer to track and use @product@'s OSGi services directly via 
@product@'s Registry API than it is to invoke @product@'s similar static utility 
functions. For example, it's preferable to use @product@'s OSGi service 
`UserService` instead of `UserServiceUtil`, which in turn uses the OSGi service 
`UserService`. Also, you can't call an OSGi service unless all its dependencies 
are satisfied--if there are unsatisfied dependencies, the service is 
unavailable. When invoking @product@'s static utility functions, however, it's 
possible to invoke them prematurely (e.g., before OSGi bundle activation and 
application startup events). Although it's possible to work around this by 
identifying all of the implied OSGi service dependencies and making sure they 
are satisfied before invocation, this can be difficult and error-prone. You can 
and should avoid this by using @product@'s Registry API to track the services 
you're interested in. This way, you let OSGi make sure a service's dependencies 
are satisfied before invoking that service. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/using-osgi-services-from-ext-plugins) 
to see an example of this. 

Remember that you can check the state of @product@'s services via 
[the Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 
If you're running @product@ locally, use the command `telnet localhost 11311` to 
connect to the Gogo shell. Once connected, the `scr:list` command shows all of 
@product@'s Declarative Services components, including whether any are inactive 
due to unsatisfied dependencies. To find unsatisfied dependencies for Liferay 
Service Builder services, use the Dependency Manager's 
`dependencymanager:dm wtf` command. Note that these commands only show 
components that haven't been activated due to unsatisfied dependencies--they 
don't show pure service trackers that are waiting for a service due to 
unsatisfied dependencies. 

## Related Topics [](id=related-topics)

[Using OSGi Services from EXT Plugins](/develop/tutorials/-/knowledge_base/7-0/using-osgi-services-from-ext-plugins)

[Detecting Unresolved OSGi Components](/develop/tutorials/-/knowledge_base/7-0/detecting-unresolved-osgi-components)

[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

[OSGi Basics For Liferay Development](/develop/tutorials/-/knowledge_base/7-0/osgi-basics-for-liferay-development)
