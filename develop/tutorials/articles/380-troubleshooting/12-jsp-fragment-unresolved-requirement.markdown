# Why doesn't the package I use from the fragment host resolve? [](id=why-is-a-package-i-use-from-the-fragment-host-unresolved)

An OSGi fragment can access all of the fragment host's packages. If your
fragment imports a package (i.e., lists the package in its OSGi manifest header
`Import-Package: `) and the host doesn't export that package to the OSGi
runtime, attempts to install the fragment fail because the package is an
`Unresolved requirement`. 

Resolve the issue by explicitly excluding host packages that the host doesn't
export.

For example, this fragment bundle's JSP uses classes from the fragment host
bundle's internal package
`com.liferay.portal.search.web.internal.custom.facet.display.context`: 

    <%@
    page import="com.liferay.portal.search.web.internal.custom.facet.display.context.CustomFacetDisplayContext" %><%@
    page import="com.liferay.portal.search.web.internal.custom.facet.display.context.CustomFacetTermDisplayContext" %>

Since the example host bundle doesn't export the package, the fragment bundle can avoid importing the package by using an OSGi manifest header, like the one below, to explicitly exclude the package from package imports:

    Import-Package: !com.liferay.portal.search.web.internal.*,*
