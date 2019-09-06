---
header-id: migrating-off-of-velocity-templates
---

# Migrating Off of Velocity Templates

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Frameworks and Features</p><p>Step 4 of 4</p>
</div>

Velocity templates were deprecated in Liferay Portal 7.0 and are now removed in
favor of FreeMarker templates in @product-ver@. Below are the key reasons for
this move:

- FreeMarker is developed and maintained regularly, while Velocity is no longer
  actively being developed.

- FreeMarker is faster and supports more sophisticated macros.

- FreeMarker supports using taglibs directly rather than requiring a method to
  represent them. You can pass body content to them, parameters, etc.

Although Velocity templates still work in @product-ver@, we highly recommend
migrating to FreeMarker templates. For more information on this topic, see the
[Upgrading Layout Templates](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-layout-template-to-7-2)
section.
