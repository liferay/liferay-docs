---
header-id: updating-6-2-liferay-plugin-package-properties
---

# Updating 6.2 Liferay Plugin Package Properties

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating 6.2 Resources Importer</p><p>Step 1 of 3</p>
</div>

Since the Lunar Resort theme was developed in the Plugins SDK, it requires 
the updates covered in this section. Themes developed outside of the Plugins SDK 
do not require these changes. 

Follow these steps to update the Lunar Resort Theme's 
`liferay-plugin-package.properties` file:

1.  Open the `src\WEB-INF\liferay-plugin-package.properties` file and remove the 
    `required-deployment-contexts` property. This is no longer needed since the 
    Resources Importer is bundled with @product@.

2.  The group model class's fully-qualified class name has changed. Replace the 
    `resources-importer-target-class-name` property's value with the updated one 
    below:

```properties
com.liferay.portal.kernel.model.Group
```

Now that the `liferay-plugin-package.properties` is updated, you can update the 
theme's web content. 
