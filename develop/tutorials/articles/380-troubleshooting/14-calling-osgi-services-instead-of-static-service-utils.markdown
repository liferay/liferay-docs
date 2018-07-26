# Calling Non-OSGi Code that Uses OSGi Services [](id=calling-non-osgi-code-that-uses-osgi-services)

@product@'s static service utilities (e.g., `UserServiceUtil`,
`CompanyServiceUtil`, `GroupServiceUtil`, etc.) are examples of non-OSGi code
that use OSGi services. Service Builder generates them for backwards
compatibility purposes only. If you're tempted to call a `*ServiceUtil` class or
your existing code calls one, access the `*Service` directly instead using one
these alternatives:

-   If your class is a Declarative Services component, use an
    [`@Reference` annotation](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)
    to access the `*Service` class.

-   If your class isn't a Declarative Services component, use a
    [`ServiceTracker`](/develop/tutorials/-/knowledge_base/7-1/service-trackers)
    to access the `*Service` class. 

You can check the state of @product@'s services in 
[the Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
The `scr:list` Gogo shell command shows all Declarative Services components,
including inactive ones from unsatisfied dependencies. To find unsatisfied
dependencies for Service Builder services, use the Dependency Manager's
`dependencymanager:dm wtf` command. Note that these commands only show
components that haven't been activated because of unsatisfied dependencies--they
don't show pure service trackers that are waiting for a service because of
unsatisfied dependencies. 

## Related Topics [](id=related-topics)

[Detecting Unresolved OSGi Components](/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components)

[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)

[OSGi Basics For Liferay Development](/develop/tutorials/-/knowledge_base/7-1/osgi-basics-for-liferay-development)
