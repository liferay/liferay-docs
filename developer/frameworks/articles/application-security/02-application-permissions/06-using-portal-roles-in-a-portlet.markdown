---
header-id: using-portal-roles-in-a-portlet
---

# Using JSR Roles in a Portlet

[TOC levels=1-4]

Roles in @product@ are the primary means for granting or restricting access
to content. If you've decided *not* to use Liferay's permissions system, you can
use the basic system offered by the JSR 168, 286, and 362 specifications that
map Roles in a portlet to Roles provided by the portal. 

## JSR Portlet Security

The portlet specification defines a means to specify Roles used by portlets in
their `docroot/WEB-INF/portlet.xml` descriptors. The Role names themselves,
however, are not standardized. When these portlets run in @product@, the Role
names defined in the portlet must be mapped to Roles that exist in the Portal.

For example, consider a Guestbook project that contains two portlets: The
Guestbook portlet and the Guestbook Admin portlet. The WAR version of the
Guestbook project's `portlet.xml` file references the *administrator*, *guest*,
*power-user*, and *user* Roles: 

```xml
<?xml version="1.0"?>

<portlet-app xmlns="http://xmlns.jcp.org/xml/ns/portlet" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/portlet http://xmlns.jcp.org/xml/ns/portlet/portlet-app_3_0.xsd" version="3.0">
	<portlet>
		<portlet-name>guestbook-war</portlet-name>
		<display-name>guestbook-war</display-name>
		<portlet-class>com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet</portlet-class>
		<init-param>
			<name>template-path</name>
			<value>/</value>
		</init-param>
		<init-param>
			<name>view-template</name>
			<value>/view.jsp</value>
		</init-param>
		<expiration-cache>0</expiration-cache>
		<supports>
			<mime-type>text/html</mime-type>
		</supports>
		<resource-bundle>content.Language</resource-bundle>
		<portlet-info>
			<title>guestbook-war</title>
			<short-title>guestbook-war</short-title>
			<keywords>guestbook-war</keywords>
		</portlet-info>
		<security-role-ref>
			<role-name>administrator</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>guest</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>power-user</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>user</role-name>
		</security-role-ref>
	</portlet>
</portlet-app>
```

An OSGi-based `guestbook-web` module project defines Roles without an XML file,
in the portlet class's `@Component` annotation: 

```java
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GuestbookPortletKeys.Guestbook,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
```
If you are using an OSGi-based MVC Portlet, you must use Liferay's permissions
system, as the only way to map JSR-362 Roles to Liferay Roles is to place them
in the Liferay WAR file's `portlet.xml`. 

Your `portlet.xml` Roles must be mapped to specific Roles that have been created.
These mappings allow @product@ to resolve conflicts between Roles with the same
name that are from different portlets (e.g. portlets from different developers). 

| **Note:** Each Role named in a portlet's `<security-role-ref>` element is given
| permission to add the portlet to a page.

## Mapping Portlet Roles to Portal Roles

To map the Roles to @product@, you must use the
`docroot/WEB-INF/liferay-portlet.xml` Liferay-specific configuration file. For
an example, see the mapping defined in the Guestbook project's
`liferay-portlet.xml` file. 

```xml
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
```

If a portlet definition references the Role `power-user`, that portlet is mapped
to the Liferay Role called *Power User* that's already in Liferay's database. 

As stated above, there is no standardization with portal Role names. If you
deploy a portlet with Role names different from the above default Liferay names,
you must add the names to the `system.roles` property in your
`portal-ext.properties` file: 

```properties
system.roles=my-role,your-role,our-role
```

This prevents Roles from being created accidentally. 

Once Roles are mapped to the portal, you can use methods as defined in the
portlet specification: 

- `getRemoteUser()`
- `isUserInRole()`
- `getUserPrincipal()`

For example, you can use the following code to check if the current User has
the `power-user` Role:

```java
if (renderRequest.isUserInRole("power-user")) {
    // ...
}
```

By default, Liferay doesn't use the `isUserInRole()` method in any built-in
portlets. Liferay uses its own permission system directly to achieve more
fine-grained security. If you don't intend on deploying your portlets to other
portal servers, we recommend using Liferay's permission system, because it
offers a much more robust way of tailoring your application's permissions. 

## Related Topics

[Liferay Permissions](/docs/7-2/frameworks/-/knowledge_base/f/defining-application-permissions)

[Asset Framework](/docs/7-2/frameworks/-/knowledge_base/f/asset-framework)

[Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets)

[Understanding ServiceContext](/docs/7-2/frameworks/-/knowledge_base/f/understanding-servicecontext)
