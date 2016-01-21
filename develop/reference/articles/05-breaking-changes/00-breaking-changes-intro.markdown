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

## Breaking Changes Contribution Guidelines [](id=breaking-changes-contribution-guidelines)

Each change must have a brief descriptive title and contain the following
information:

* **[Title]** Provide a brief descriptive title. Use past tense and follow
the capitalization rules from
<http://en.wikibooks.org/wiki/Basic_Book_Design/Capitalizing_Words_in_Titles>.
* **Date:** Specify the date you submitted the change. Format the date as
*YYYY-MMM* (e.g., 2014-Mar) or *YYYY-MMM-DD* (e.g., 2014-Feb-25).
* **JIRA Ticket:** Reference the related JIRA ticket (e.g., LPS-123456)
(Optional).
* **What changed?** Identify the affected component and the type of change that
was made.
* **Who is affected?** Are end-users affected? Are developers affected? If the
only affected people are those using a certain feature or API, say so.
* **How should I update my code?** Explain any client code changes required.
* **Why was this change made?** Explain the reason for the change. If
applicable, justify why the breaking change was made instead of following a
deprecation process.

Here's the template to use for each breaking change (note how it ends with a
horizontal rule):

```
### Title [](id=title)
- **Date:**
- **JIRA Ticket:**

#### What changed? [](id=what-changed)

#### Who is affected? [](id=who-is-affected)

#### How should I update my code? [](id=how-should-i-update-my-code)

#### Why was this change made? [](id=why-was-this-change-made)

---------------------------------------
```

**80 Columns Rule:** Text should not exceed 80 columns. Keeping text within 80
columns makes it easier to see the changes made between different versions of
the document. Titles, links, and tables are exempt from this rule. Code samples
must follow the column rules specified in Liferay's
[Development Style](http://www.liferay.com/community/wiki/-/wiki/Main/Liferay+development+style).

The remaining content of this document consists of the breaking changes listed
in ascending chronological order.

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
