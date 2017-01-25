# What are the Breaking Changes for Liferay 6.2? [](id=what-are-the-breaking-changes-for-liferay-6-2)

This document presents a chronological list of changes that break existing
functionality, APIs, or contracts with third party Liferay developers or users.
We try our best to minimize these disruptions, but sometimes they are
unavoidable.

Here are some of the types of changes documented in this file:

* Functionality that is removed or replaced
* API incompatibilities: Changes to public Java or JavaScript APIs
* Changes to context variables available to templates
* Changes in CSS classes available to Liferay themes and portlets
* Configuration changes: Changes in configuration files, like
 `portal.properties`, `system.properties`, etc.
* Execution requirements: Java version, J2EE Version, browser versions, etc.
* Deprecations or end of support: For example, warning that a certain
feature or API will be dropped in an upcoming version.
* Recommendations: For example, recommending using a newly introduced API that
replaces an old API, in spite of the old API being kept in Liferay Portal for
backwards compatibility.

## Breaking Changes List [](id=breaking-changes-list)

### Utility Methods Have Been Moved from ImageLocalServiceUtil [](id=utility-methods-have-been-moved-from-imagelocalserviceutil)
- **Date:** 2013-Jan-13
- **JIRA Ticket:** LPS-31888

#### What changed? [](id=what-changed-0)
Utility methods have been moved from
[`ImageLocalServiceUtil`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/ImageLocalServiceUtil.html)
to
[`ImageTool`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/image/ImageTool.html).

#### Who is affected? [](id=who-is-affected-0)
Developers using utility methods of
[`ImageLocalServiceUtil`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/ImageLocalServiceUtil.html)
may find that the method they're using have been moved to
[`ImageTool`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/image/ImageTool.html).

#### How should I update my code? [](id=how-should-i-update-my-code-0)
If you were using a method of
[`ImageLocalServiceUtil`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/ImageLocalServiceUtil.html)
that's no longer there, use the corresponding method of
[`ImageTool`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/image/ImageTool.html)
instead.

#### Why was this change made? [](id=why-was-this-change-made-0)
Utility methods should stay in utility classes rather than service classes to
avoid unnecessary AOP overhead.

---------------------------------------
