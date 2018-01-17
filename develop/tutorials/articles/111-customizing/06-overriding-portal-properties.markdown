# Overriding Portal Properties using a Hook [](id=overriding-portal-properties-using-a-hook)

A portal properties hook plugin lets you override a subset of
[portal properties](@platform-ref@/7.0/propertiesdoc/portal.properties.html)
dynamically. These properties define event actions, model listeners, validators,
generators, and content sanitizers. The 
[`liferay-hook-7.0.dtd` file](@platform-ref@/7.0/definitions/liferay-hook_7_0_0.dtd.html)
lists this subset of properties. 

+$$$

**Note:** To customize a property that's not in the 
[`liferay-hook-7.0.dtd` file](@platform-ref@/7.0/definitions/liferay-hook_7_0_0.dtd.html), you must use an
[Ext plugin](/develop/tutorials/-/knowledge_base/7-0/advanced-customization-with-ext-plugins). 

$$$

Some portal properties accept *multiple* values. For example, the
`login.event.pre` property defines action classes to invoke before login.
Deploying multiple hooks for properties like this appends the values to the
property's current value. For example, multiple hooks that add login event
actions append their action classes to the portal instance's `login.event.pre`
property. The 
[portal property reference
documentation](@platform-ref@/7.0/propertiesdoc/portal.properties.html) shows
whether a property accepts multiple values by stating it or showing value lists
assigned to a default or example property setting. 

Some portal properties accept a *single* value only. For example, the
`terms.of.use.required` property is either `true` or `false`. Override a single
value property from one hook only--there's no telling which value is assigned if
multiple hooks override it. 

Here's how to override a portal property using a Hook:

1.  Create a Hook plugin using
    [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
    or
    [Maven](/develop/tutorials/-/knowledge_base/7-0/generating-new-projects-using-archetypes).

2.  In the plugin's `WEB-INF/src` folder, create a `portal.properties` file and
    override properties with the values you want. 

3.  In the plugin's `WEB-INF/liferay-hook.xml` file, add the following
    `portal-properties` element  as a child of the `hook` element. Refer to the
    [`liferay-hook-7.0.dtd` file](@platform-ref@/7.0/definitions/liferay-hook_7_0_0.dtd.html)
    for details.

        <portal-properties>portal.properties</portal-properties>

4.  Deploy the plugin. 

You've modified the portal property. The
[*Server Administration* page's *Properties* screen](/discover/portal/-/knowledge_base/7-0/server-administration#properties)
in the Control Panel shows your new property setting. 

## Related Topics [](id=related-topics)

[Portal Properties Reference](@platform-ref@/7.0/propertiesdoc/portal.properties.html)

[Server Administration](/discover/portal/-/knowledge_base/7-0/server-administration#properties)

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)

[Generating New Projects using Maven Archetypes](/develop/tutorials/-/knowledge_base/7-0/generating-new-projects-using-archetypes).
