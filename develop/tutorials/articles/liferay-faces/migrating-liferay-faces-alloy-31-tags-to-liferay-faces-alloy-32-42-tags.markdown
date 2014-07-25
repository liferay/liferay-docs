# Migrating Liferay Faces Alloy 3.1 Tags to Liferay Faces Alloy 3.2/4.2 Tags

AlloyUI was upgraded from AlloyUI 1.5 to AlloyUI 2.0 between Liferay Portal 6.1
and Liferay Portal 6.2. The AlloyUI changes include the deprecation and removal
of some JavaScript functionality, and the addition of some JavaScript widgets.
As a result, in Liferay Faces 3.2 and 4.2, corresponding Liferay Faces Alloy
tags are deprecated and some are replaced. In this tutorial, you'll go over the
Liferay Faces Alloy tag changes that you'll need to accommodate in your
Facelets. 

First, you'll look at the changes to the `aui` namespace tags. 

## Changes to the Liferay Faces Alloy aui Tags

The following table identifies the `aui` tags that are deprecated in 3.2/4.2 and
each tag's replacement, if a replacement exists. 

**Tag Deprecations in the `aui` Namespace**

| Deprecated `aui` Tag&nbsp;&nbsp;&nbsp; | Replacement `aui` Tag |
|----------------------|-----------------------|
| `aui:column` | [`aui:row`](http://docs.liferay.com/faces/3.2/vdldoc/aui/row.html) and  [`aui:col`](http://docs.liferay.com/faces/3.2/vdldoc/aui/col.html) |
| `aui:layout` | No Replacement |

---

 ![Note](../../images/tip.png) **Note:** The `aui:column` tag must be replaced
 by an `aui:col` tag that is nested within an `aui:row` tag. 

 Also, because `aui:col` has completely different attributes than `aui:column`,
 you must account for the `aui:col` attributes. For more information on the
 `aui:col` tag, see the
 [VDLdocs for Liferay Faces 3.2](http://docs.liferay.com/faces/3.2/vdldoc/).

---

Next, you'll look at the changes to the tags in the `aui-cc` namespace. 

## Changes to the Liferay Faces Alloy aui-cc Tags

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

---

 ![Note](../../images/tip.png) **Note:** The `aui-cc:message` and
 `aui-cc:messages` tags have been completely removed in 4.1/4.2 because no
 analogous tags in Liferay Portal exist for them and their functionality is
 already implemented in the bridge's `h:message` and `h:messages` tags,
 respectively. 

---

Excellent! You're now familiar with the AlloyUI related migration changes. 
