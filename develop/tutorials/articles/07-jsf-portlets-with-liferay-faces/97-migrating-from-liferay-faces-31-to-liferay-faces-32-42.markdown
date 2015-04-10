# Migrating from Liferay Faces 3.1 to Liferay Faces 3.2/4.2 [](id=migrating-from-liferay-faces-3-1-to-liferay-faces-3-2-4-2)

Liferay Faces 3.2 and 4.2 are compatible with Liferay Portal 6.2 (see the
[Liferay Faces Version Scheme](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
for more info on Liferay Portal compatibility). Migrating to Liferay Faces
3.2/4.2 from Liferay Faces 3.1 requires a few changes to ensure your projects
continue working correctly. 

This tutorial covers the following migration topics:

- Migrating the liferay-portlet.xml File for Liferay Faces 3.2/4.2
- Migrating Liferay Faces Alloy Tags for Liferay Faces Alloy 3.2/4.2

First, you'll learn how to upgrade your `liferay-portal.xml` file. 

## Migrating the liferay-portlet.xml File [](id=migrating-the-liferay-portlet-xml-file)

Liferay Portal 6.2 has two compatible Liferay Faces Versions: 3.2 and 4.2.
Liferay provides Liferay Faces 3.2 for compatibility with JSF 2.1 and Liferay
Faces 4.2 for compatibility with JSF 2.2 (see the [Liferay Faces Version
Scheme](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
for more info on Liferay Portal and JSF compatibility). If you're currently
using Liferay Faces 3.1 and are interested in upgrading from Liferay Portal 6.1
to 6.2, but aren't interested in compatibility with JSF 2.2, you should upgrade
from Liferay Faces 3.1 to 3.2. But, if you want to use JSF 2.2 in addition to
Liferay 6.2, you must upgrade to Liferay Faces 4.2. 

<!-- Verify above link is correct, once Liferay Faces tutorials are published to
dev.liferay.com -Cody -->

+$$$

**Note:** This guide only addresses upgrading as it relates to Liferay Portal.
Upgrading from JSF 2.1 to 2.2 may require additional changes. For information on
upgrading to JSF 2.2 you should check out JSF specific upgrade guides. 

$$$

Liferay Portal 6.2 can enforce namespacing of portlet request parameters, but
you must turn this off for your JSF portlets by specifying
`<requires-namespaced-parameters>false</requires-namespaced-parameters>` in each
`<portlet>` element of your portlet project's `WEB-INF/liferay-portlet.xml`
file. Here's a snippet that specifies this descriptor: 

    <liferay-portlet-app>
        <portlet>

            ...

            <requires-namespaced-parameters>false</requires-namespaced-parameters>

            ...

        </portlet>

        ...

    </liferay-portlet-app>

Turning off the parameter namespace requirement is all you need to do to upgrade
your JSF portlets to Liferay Faces 3.2 or 4.2, for use in Liferay Portal 6.2. 

As an example JSF portlet that runs on Liferay Portal 6.2, check out the
[demo JSF2-portlet](https://github.com/liferay/liferay-faces/blob/3.2.x/demos/bridge/jsf2-portlet)
and its 
[`liferay-portlet.xml`](https://github.com/liferay/liferay-faces/blob/3.2.x/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/liferay-portlet.xml)
file. 

Your `liferay-portlet.xml` file is now migrated to Liferay Faces 3.2/4.2.
Stupendous! 

## Migrating Liferay Faces Alloy 3.1 Tags to Liferay Faces Alloy 3.2/4.2 Tags [](id=migrating-liferay-faces-alloy-3-1-tags-to-liferay-faces-alloy-3-2-4-2-tags)

AlloyUI was upgraded from AlloyUI 1.5 to AlloyUI 2.0 between Liferay Portal 6.1
and Liferay Portal 6.2. The AlloyUI changes include the deprecation and removal
of some JavaScript functionality, and the addition of some JavaScript widgets.
As a result, in Liferay Faces 3.2 and 4.2, corresponding Liferay Faces Alloy
tags are deprecated and some are replaced. In this section, you'll go over the
Liferay Faces Alloy tag changes that you'll need to accommodate in your
Facelets. 

First, you'll look at the changes to the `aui` namespace tags. 

### Changes to the Liferay Faces Alloy aui Tags [](id=changes-to-the-liferay-faces-alloy-aui-tags)

The following table identifies the `aui` tags that are deprecated in 3.2/4.2 and
each tag's replacement, if a replacement exists. 

**Tag Deprecations in the `aui` Namespace**

| Deprecated `aui` Tag&nbsp;&nbsp;&nbsp; | Replacement `aui` Tag |
|----------------------|-----------------------|
| `aui:column` | [`aui:row`](http://docs.liferay.com/faces/3.2/vdldoc/aui/row.html) and  [`aui:col`](http://docs.liferay.com/faces/3.2/vdldoc/aui/col.html) |
| `aui:layout` | No Replacement |

+$$$

**Note:** The `aui:column` tag must be replaced by an `aui:col` tag that is
nested within an `aui:row` tag.

Also, because `aui:col` has completely different attributes than `aui:column`,
you must account for the `aui:col` attributes. For more information on the
`aui:col` tag, see the [VDLdocs for Liferay Faces 3.2](http://docs.liferay.com/faces/3.2/vdldoc/). 

$$$

Next, you'll look at the changes to the tags in the `aui-cc` namespace. 

### Changes to the Liferay Faces Alloy aui-cc Tags [](id=changes-to-the-liferay-faces-alloy-aui-cc-tags)

Below is a table of the `aui-cc` tags that are deprecated or removed with
respect to the Liferay Faces 3.2, 4.1, and 4.2 releases: 

**Tag Deprecations in the `aui-cc` Namespace**

| Deprecated `aui-cc` Tag |
|-------------------------|
| `aui-cc:button` |
| `aui-cc:input` |
| `aui-cc:select` |
| `aui-cc:message` (**REMOVED in 4.1/4.2**) |
| `aui-cc:messages` (**REMOVED in 4.1/4.2**) |

+$$$

**Note:** The `aui-cc:message` and `aui-cc:messages` tags have been completely
removed in 4.1/4.2 because no analogous tags in Liferay Portal exist for them
and their functionality is already implemented in the bridge's `h:message` and
`h:messages` tags, respectively. 

$$$

Excellent! You're now familiar with the AlloyUI related migration changes, in
addition to other changes required to migrate to Liferay Faces 3.2/4.2.   

## Related Topics [](id=related-topics)

<!-- Add once JSF tutorials are finished. -Cody -->
