# Calling Non-OSGi Code that Uses OSGi Services [](id=calling-non-osgi-code-that-uses-osgi-services)

@product@'s static utility functions (e.g., `UserServiceUtil`, 
`CompanyServiceUtil`, `GroupServiceUtil`, etc.) are examples of non-OSGi code 
that use OSGi services. 

Note that it's safer to track and use @product@'s OSGi services directly with 
@product@'s Registry API than to invoke @product@'s similar static utility
functions. For example, you can't call an OSGi service unless all its
dependencies are satisfied: the container won't enable the service. If you
invoke @product@'s static utility functions, you might invoke them prematurely
(e.g., before OSGi bundle activation and application startup events). You could
work around this by identifying all of the implied OSGi service dependencies and
making sure they are satisfied before invocation, but then you're not only
duplicating the container's more robust functionality for this, you're also
creating a bigger surface for bugs. Avoid this mess by using @product@'s
Registry API to track the services you want. This way, you let OSGi
make sure a service's dependencies are satisfied before invoking that service.
For example, use @product@'s OSGi service `UserService` instead of
`UserServiceUtil`, which in turn uses the OSGi service `UserService`.
[Click here](/develop/tutorials/-/knowledge_base/7-0/using-osgi-services-from-ext-plugins)
to see an example of this. 

Remember that you can check the state of @product@'s services in 
[the Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 
If you're running @product@ locally, use the command `telnet localhost 11311` to 
connect to the Gogo shell. Once connected, the `scr:list` command shows all
Declarative Services components, including inactive ones from unsatisfied
dependencies. To find unsatisfied dependencies for Service Builder services, use
the Dependency Manager's `dependencymanager:dm wtf` command. Note that these
commands only show components that haven't been activated because of unsatisfied
dependencies--they don't show pure service trackers that are waiting for a
service because of unsatisfied dependencies. 

## Related Topics [](id=related-topics)

[Using OSGi Services from EXT Plugins](/develop/tutorials/-/knowledge_base/7-0/using-osgi-services-from-ext-plugins)

[Detecting Unresolved OSGi Components](/develop/tutorials/-/knowledge_base/7-0/detecting-unresolved-osgi-components)

[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

[OSGi Basics For Liferay Development](/develop/tutorials/-/knowledge_base/7-0/osgi-basics-for-liferay-development)
