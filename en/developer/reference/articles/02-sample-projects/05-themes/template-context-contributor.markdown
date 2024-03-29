---
header-id: template-context-contributor
---

# Template Context Contributor

[TOC levels=1-4]

The Template Context Contributor sample injects a new variable into @product@'s
theme context. When deploying this sample with no customizations, you can use
the `${sample_text}` variable from any theme.

## What API(s) and/or code components does this sample highlight?

Many developers prefer using templating frameworks like FreeMarker and Velocity,
but don't have access to the common objects offered to those working with JSPs.
Context contributors allow non-JSP developers an easy way to inject variables
into their Liferay templates.

This sample leverages the
[TemplateContextContributor](@product-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html)
API.

## How does this sample leverage the API(s) and/or code component?

You can easily modify this sample by customizing its
`BladeTemplateContextContributor.java` Java class. For example, the default
context contributor sample provides the `${sample_text}` variable by injecting
it into Liferay's `contextObjects`, which is a map provided by default to offer
common variables to non-JSP template developers. You can easily inject your own
variables into the `contextObjects` map usable by any theme deployed to
@product@.

Are you working with templates that aren't themes (e.g., ADTs, DDM templates,
etc.)? You can change the context in which your variables are injected by
modifying the `property` attribute in the `@Component` annotation. If you want
your variable available for all templates, change it to

```java
property = {"type=" + TemplateContextContributor.TYPE_GLOBAL}
```

For more information on customizing the Template Context Contributor sample to
fit your needs, see the Javadoc listed in this sample's
`com.liferay.blade.samples.theme.contributorBladeTemplateContextContributor`
class. For more information on context contributors and how to create them in
@product@, visit the
[Context Contributors](/docs/7-2/frameworks/-/knowledge_base/f/injecting-additional-context-variables-and-functionality-into-your-theme-templates)
tutorial.

## Where Is This Sample?

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.2/gradle/themes/template-context-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/themes/template-context-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.2/maven/themes/template-context-contributor)
