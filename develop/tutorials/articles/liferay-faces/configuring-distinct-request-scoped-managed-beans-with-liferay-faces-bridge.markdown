# Configuring Distinct Request Scoped Managed Beans with Liferay Faces Bridge

Liferay Portal gives you the ability to specify whether or not
request attributes are shared among portlets, using the
`<private-request-attributes>` option in the `WEB-INF/liferay-portlet.xml`
descriptor. The default value of this option is `true`, meaning that request
attributes are NOT shared among portlets. 

    <liferay-portlet-app>
        <portlet>

            ...

            <private-request-attributes>false</private-request-attributes>
        </portlet>

        ...

    </liferay-portlet-app>

However, this non-shared feature only works for request attributes that are
present in the request map and that have a non-null value. This can cause a
problem for JSF managed-beans in request scope. Specifically, the problem arises
when a portal page has two or more portlets that have a request scope managed
bean with the same name.

For example, say Portlet X and Portlet Y each have a
class named `BackingBean` annotated with `@RequestScoped` `@ManagedBean`. When
the JSF runtime is asked to resolve an EL-expression `#{backingBean}`, there is
no guarantee that the correct instance will be resolved. In order to solve this
problem, Liferay Faces Bridge provides a configuration option that can be
specified in `WEB-INF/web.xml`. It causes request-scoped managed beans to be
distinct for each portlet. 

    <!-- The default value of the following context-param is false. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.distinctRequestScopedManagedBeans</param-name>
        <param-value>true</param-value>
    </context-param>

To ensure that `@RequestScoped` managed beans are resolved correctly for each
portlet, set this value to `true`. 
