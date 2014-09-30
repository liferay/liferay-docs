# Using Portal Roles in a Portlet [](id=using-portal-roles-in-a-portlet)

"A role, a role, my portal for a role!" Don't worry, using portal roles in your 
portlets isn't that difficult. Roles in Liferay Portal are the key means for 
granting or restricting access to content. As such, portlet developers require a 
means for making use of portal roles in their portlets. This tutorial covers 
those means. Read on to learn how!

## JSR Portlet Security

The JSR specification defines a means to specify roles used by portlets in their
`portlet.xml` definitions. The role names themselves, however, are not
standardized, so when these portlets run in Liferay, you'll recognize familiar
role names. For example, the Liferay Blogs portlet definition references the
*guest*, *power-user*, and *user* roles: 

	<portlet>
		<portlet-name>33</portlet-name>
		<display-name>Blogs</display-name>
		<portlet-class>com.liferay.portlet.StrutsPortlet</portlet-class>
		<init-param>
			<name>config-template</name>
			<value>/html/portlet/blogs/configuration.jsp</value>
		</init-param>
		<init-param>
			<name>template-path</name>
			<value>/html/portlet/blogs/</value>
		</init-param>
		<init-param>
			<name>view-action</name>
			<value>/blogs/view</value>
		</init-param>
		<expiration-cache>0</expiration-cache>
		<supports>
			<mime-type>text/html</mime-type>
		</supports>
		<resource-bundle>com.liferay.portlet.StrutsResourceBundle</resource-bundle>
		<security-role-ref>
			<role-name>guest</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>power-user</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>user</role-name>
		</security-role-ref>
		<supported-public-render-parameter>categoryId</supported-public-render-parameter>
		<supported-public-render-parameter>resetCur</supported-public-render-parameter>
		<supported-public-render-parameter>tag</supported-public-render-parameter>
	</portlet>

Your `portlet.xml` roles need to be mapped to specific roles in the portal.
That way the portal can resolve conflicts between roles with the same name that
are from different portlets (e.g. portlets from different developers). 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** Each role named in a
 portlet's `<security-role-ref>` element is given permission to add the portlet
 to a page. 

---

## Mapping Portlet Roles to Portal Roles

To map the roles to the portal, you'll have to use a Liferay-specific
configuration file called `liferay-portlet.xml`. For an example, see the mapping
defined inside `liferay-portlet.xml` found in `portal-web/docroot/WEB-INF`: 

    <role-mapper>
        <role-name>administrator</role-name>
        <role-link>Administrator</role-link>
    </role-mapper>
    <role-mapper>
        <role-name>guest</role-name>
        <role-link>Guest</role-link>
    </role-mapper>
    <role-mapper>
        <role-name>power-user</role-name>
        <role-link>Power User</role-link>
    </role-mapper>
    <role-mapper>
        <role-name>user</role-name>
        <role-link>User</role-link>
    </role-mapper>

<!-- Better to use an example from plugins here, rather than from an internal
portlet. -Rich -->

<!-- Per Jim and Jorge, the internal portlet code samples are ok for the time 
being, but we want to update them to use an example from plugins later. -Nick -->

If a portlet definition references the role `power-user`, that portlet is
mapped to the Liferay role called *Power User* that's already in its database. 

Once roles are mapped to the portal, you can use methods as defined in portlet
specification: 

- `getRemoteUser()`
- `isUserInRole()`
- `getUserPrincipal()`

For example, you can use the following code to check if the current user has
the `power-user` role:

    if (renderRequest.isUserInRole("power-user")) {
        // ...
    }

By default, Liferay doesn't use the `isUserInRole()` method in any built-in
portlets. Liferay uses its own permission system directly to achieve more
fine-grained security. We recommend using Liferay's permission system,
because it offers a much more robust way of tailoring your application's
permissions. 

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/liferay-ide)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/liferay-ui-taglibs)
