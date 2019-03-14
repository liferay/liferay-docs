# Why doesn't the package I use from the fragment host resolve? [](id=why-is-a-package-i-use-from-the-fragment-host-unresolved)

An OSGi fragment can access all of the fragment host's packages---it 
doesn't need to import them from another bundle. bnd adds external packages the
fragment uses (even ones in the fragment host) to the fragment's
`Import-Package: [package],...` OSGi manifest header. That's fine for packages
exported to the OSGi runtime. The problem is, however, when bnd tries to import
a host's internal package (a package the host doesn't export). The OSGi runtime
can't activate the fragment because the internal package remains an `Unresolved
requirement`---a fragment shouldn't import a fragment host's packages. 

Resolve the issue by explicitly excluding host packages that the host doesn't
export.

For example, this fragment bundle's JSP uses classes from the fragment host
bundle's internal package
`com.liferay.portal.search.web.internal.custom.facet.display.context`: 

    <%@
    page import="com.liferay.portal.search.web.internal.custom.facet.display.context.CustomFacetDisplayContext" %><%@
    page import="com.liferay.portal.search.web.internal.custom.facet.display.context.CustomFacetTermDisplayContext" %>

Since the example host bundle doesn't export the package, the fragment bundle
can avoid importing the package by using an OSGi manifest header, like the one
below, to explicitly exclude the package from package imports:

    Import-Package: !com.liferay.portal.search.web.internal.*,*
