---
header-id: enabling-a-b-testing
---

# Enabling A/B Testing

[TOC levels=1-4]

Before creating an A/B test, you must ensure some conditions are met:

- You must have @product@ connected to
  [Analytics Cloud](https://help.liferay.com/hc/en-us/articles/360006608732). To
  begin,
  [add a Data Source](https://help.liferay.com/hc/en-us/articles/360006653472-Adding-a-Liferay-DXP-Data-Source).

- Your page must be a Content Page, since only Content Pages (not Widget Pages)
  support Experiences for different Segments.

- The Content Page you intend to test must be published.

If these conditions are met, A/B Testing is automatically enabled for any
Content Page you navigate to, assuming you have the proper permissions. You'll
learn more about how to assign permissions for A/B Testing next.

## Setting A/B Testing Permissions

To use all the features of A/B Testing, you must have *Update* permissions for
the Content Page.

To assign *Update* permissions for a Content Page,

1.  Navigate to Site Administration &rarr; *Site Builder* &rarr; *Pages*.

2.  Select the Actions button (![Actions](../../../images-dxp/icon-actions.png))
    next to the Content Page and click *Permissions*.

3.  Enable the *Update* permission for the Role(s) you want to grant A/B Testing
    access for and click *Save*.

For more information, see
[Page Permissions](/docs/7-2/user/-/knowledge_base/u/changing-page-permissions)
and [Managing Roles](/docs/7-2/user/-/knowledge_base/u/managing-roles).
