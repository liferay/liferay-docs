---
header-id: overriding-portal-properties-dynamically
---

# Overriding Portal Properties Dynamically

[TOC levels=1-4]

A portal properties hook lets you override a subset of
[portal properties](@platform-ref@/6.2/propertiesdoc/portal.properties.html)
that define event actions, model listeners, validators, generators, and content
sanitizers. The 
[`liferay-hook-6.2.dtd` file](@platform-ref@/6.2/definitions/liferay-hook_6_2_0.dtd.html)
lists these properties. 

| **Note:** To customize a property that's not found in the
| [`liferay-hook-6.2.dtd` file](@platform-ref@/6.2/definitions/liferay-hook_6_2_0.dtd.html), you must use an Ext plugin.

Some portal properties accept *multiple* values. For example, the
`login.event.pre` property lets you define multiple action classes to invoke
before login. Deploying multiple hooks that set such a portal property appends
the values to the property's current value. For example, multiple 
[hooks that add login event actions](/docs/6-2/tutorials/-/knowledge_base/t/performing-a-custom-action-using-a-hook)
append their action classes to the portal instance's `login.event.pre`
property. Refer to the
[portal property reference documentation](@platform-ref@/6.2/propertiesdoc/portal.properties.html)
to determine whether a property accepts multiple values. The property
description might explicitly mention it or show value lists assigned to a
default or example property setting. 

Some portal properties accept a
*single* value only. For example, the `terms.of.use.required` property is either `true` or
`false`. Modify single value properties from one hook only; otherwise there's no telling which value will be assigned.  

Here's how to override a portal property using a Hook:

1.  Create a Hook plugin using Liferay
[@ide@](/docs/6-2/tutorials/-/knowledge_base/t/creating-projects-using-the-new-project-wizard),
[the Plugins SDK](/docs/6-2/tutorials/-/knowledge_base/t/using-the-plugins-sdk),
or
[Maven](/docs/6-2/tutorials/-/knowledge_base/t/developing-liferay-hook-plugins-with-maven).

2.  In the plugin's `WEB-INF/src` folder, create a `portal.properties` file and
    override properties with the values you want.  

3.  In the plugin's `WEB-INF/liferay-hook.xml` file, add the following
    `portal-properties` element  as a child of the `hook` element. Refer to the
    [`liferay-hook-6.2.dtd` file](@platform-ref@/6.2/definitions/liferay-hook_6_2_0.dtd.html)
    for details.

        <portal-properties>portal.properties</portal-properties>

4.  [Deploy the plugin](/docs/6-2/tutorials/-/knowledge_base/t/deploying-plugins-to-a-local-portal-instance). 

You've modified the portal property. The
[*Server Administration* page's *Properties* screen](/docs/6-2/user/-/knowledge_base/u/server-administration)
in the Control Panel shows your new property setting. 

## Related Topics

[Portal Properties Reference](@platform-ref@/6.2/propertiesdoc/portal.properties.html)

[Creating Plugins using Liferay @ide@](/docs/6-2/tutorials/-/knowledge_base/t/creating-projects-using-the-new-project-wizard)

[Creating Plugins using the Plugins SDK](/docs/6-2/tutorials/-/knowledge_base/t/using-the-plugins-sdk)

[Creating Hook Plugins using Maven](/docs/6-2/tutorials/-/knowledge_base/t/developing-liferay-hook-plugins-with-maven)
