---
header-id: calling-non-osgi-code-that-uses-osgi-services
---

# Calling Non-OSGi Code that Uses OSGi Services

[TOC levels=1-4]

@product@'s static service utilities (e.g., `UserServiceUtil`,
`CompanyServiceUtil`, `GroupServiceUtil`, etc.) are examples of non-OSGi code
that use OSGi services. Service Builder generates them for backwards
compatibility purposes only. If you're tempted to call a `*ServiceUtil` class or
your existing code calls one, access the `*Service` directly instead using one
these alternatives:

-   If your class is a Declarative Services component, use an
    [`@Reference` annotation](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)
    to access the `*Service` class.

-   If your class isn't a Declarative Services component, use a
    [`ServiceTracker`](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)
    to access the `*Service` class. 

You can check the state of @product@'s services in 
[the Gogo shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell).
The `scr:list` Gogo shell command shows all Declarative Services components,
including inactive ones from unsatisfied dependencies. To find unsatisfied
dependencies for Service Builder services, use the Dependency Manager's
`dependencymanager:dm wtf` command. Note that these commands only show
components that haven't been activated because of unsatisfied dependencies. They
don't show pure service trackers that are waiting for a service because of
unsatisfied dependencies. 

## Related Topics

[Detecting Unresolved OSGi Components](/docs/7-1/tutorials/-/knowledge_base/t/detecting-unresolved-osgi-components)

[Felix Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)

[OSGi Basics For Liferay Development](/docs/7-1/tutorials/-/knowledge_base/t/osgi-basics-for-liferay-development)
