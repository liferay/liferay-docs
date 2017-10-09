# Breaking Changes [](id=breaking-changes)

This document presents a chronological list of changes that break existing
functionality, APIs, or contracts with third party Liferay developers or users.
We try our best to minimize these disruptions, but sometimes they are
unavoidable.

The breaking changes covered in this article apply to both the commercial and 
open source versions of Liferay.

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

### Standardized Data Attribute Names Passed into Selectors [](id=standardized-data-attribute-names-passed-into-selectors)
- **Date:** 2016-Oct-26
- **JIRA Ticket:** LPS-66646

#### What changed? [](id=what-changed)

The data attributes passed into the event when someone uses a selector (e.g.,
asset selector, document selector, file selector, role selector, site selector,
user group selector, etc.) have been standardized from being selector specific
(e.g., `groupid`, `groupdescriptivename`, `teamid`, `teamname`, etc.) to being
more generic (e.g., `entityid` and `entityname`).

#### Who is affected? [](id=who-is-affected)

This affects anyone passing selector specific data attributes to a selector.

#### How should I update my code? [](id=how-should-i-update-my-code)

Instead of using selector specific data attributes, you should change your data
attributes to use `entityid` and `entityname`.

**Example**

Old way:

    <portlet:namespace />selectFileEntryType(event.fileentrytypeid, event.fileentrytypename);

New way:

    <portlet:namespace />selectFileEntryType(event.entityid, event.entityname);

Old way:

    data.put("roleid", role.getRoleId());
    data.put("roletitle", role.getTitle(locale));

New way:

    data.put("entityid", role.getRoleId());
    data.put("entityname", role.getTitle(locale));

#### Why was this change made? [](id=why-was-this-change-made)

This change was made to standardize the data attribute names and allow utility
methods to accept standardized event parameters.

---------------------------------------

### Removed URL Parameters p_p_col_id, p_p_col_pos, and p_p_col_count from Every Portlet URL. [](id=removed-url-parameters-ppcolid-ppcolpos-and-ppcolcount-from-every-portlet-u)
- **Date:** 2016-Dec-12
- **JIRA Ticket:** LPS-69482

#### What changed? [](id=what-changed-0)

The parameters `p_p_col_count`, `p_p_col_id`, and `p_p_col_pos` are no longer
present in every portlet URL.

#### Who is affected? [](id=who-is-affected-0)

This affects developers who are reading these parameters in their custom code.

#### How should I update my code? [](id=how-should-i-update-my-code-0)

You can no longer obtain these parameters from the portlet URL. If you need to
read them, you should do it from `PortletDisplay`.

- The parameter `p_p_col_count` can be obtained via the
  `portletDisplay.getColumnCount()` method.
- The parameter `p_p_col_id` can be obtained via the
  `portletDisplay.getColumnId()` method.
- The parameter `p_p_col_pos` can be obtained via the
  `portletDisplay.getColumnPos()` method.

#### Why was this change made? [](id=why-was-this-change-made-0)

This change simplifies portlet URLs so they only contain the required
parameters. This was done as a preliminary step of a bigger story to create
portlet URLs without passing the request as a necessary parameter.

---------------------------------------

### Moved Users File Uploads Portlet Properties to OSGi Configuration [](id=moved-users-file-uploads-portlet-properties-to-osgi-configuration)
- **Date:** 2017-Feb-06
- **JIRA Ticket:** LPS-69211

#### What changed? [](id=what-changed-1)

The Users File Uploads portlet properties have been moved from Server
Administration to an OSGI configuration named
`UserFileUploadsConfiguration.java` in the `users-admin-api` module.

#### Who is affected? [](id=who-is-affected-1)

This affects anyone using the following portlet properties:

- `users.image.check.token`
- `users.image.max.size`
- `users.image.max.height`
- `users.image.max.width`

#### How should I update my code? [](id=how-should-i-update-my-code-1)

Instead of overriding the `portal.properties` file, you can manage the
properties from Portal's configuration administrator. This can be accessed by
navigating to Liferay Portal's *Control Panel* &rarr; *Configuration* &rarr;
*System Settings* &rarr; *Foundation* &rarr; *User Images* and editing the
settings there.

If you would like to include the new configuration in your application, follow
the instructions for
[making your applications configurable in Liferay 7.0](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable).

#### Why was this change made? [](id=why-was-this-change-made-1)

This change was made as part of the modularization efforts to ease portal
configuration changes.

---------------------------------------

### Moved CAPTCHA Portal Properties to OSGi Configuration [](id=moved-captcha-portal-properties-to-osgi-configuration)
- **Date:** 2017-Feb-13
- **JIRA Ticket:** LPS-67830

#### What changed? [](id=what-changed-2)

The CAPTCHA properties have been moved from `portal.properties` and Server
Administration to an OSGi configuration named `CaptchaConfiguration.java` in the
`captcha-api` module.

#### Who is affected? [](id=who-is-affected-2)

This affects anyone using the following portal properties:

- `captcha.max.challenges`
- `captcha.check.portal.create_account`
- `captcha.check.portal.send_password`
- `captcha.check.portlet.message_boards.edit_category`
- `captcha.check.portlet.message_boards.edit_message`
- `captcha.engine.impl`
- `captcha.engine.recaptcha.key.private`
- `captcha.engine.recaptcha.key.public`
- `captcha.engine.recaptcha.url.script`
- `captcha.engine.recaptcha.url.noscript`
- `captcha.engine.recaptcha.url.verify`
- `captcha.engine.simplecaptcha.height`
- `captcha.engine.simplecaptcha.width`
- `captcha.engine.simplecaptcha.background.producers`
- `captcha.engine.simplecaptcha.gimpy.renderers`
- `captcha.engine.simplecaptcha.noise.producers`
- `captcha.engine.simplecaptcha.text.producers`
- `captcha.engine.simplecaptcha.word.renderers`

#### How should I update my code? [](id=how-should-i-update-my-code-2)

Instead of overriding the `portal.properties` file, you can manage the
properties from Portal's configuration administrator. This can be accessed by
navigating to Liferay Portal's *Control Panel* &rarr; *Configuration* &rarr;
*System Settings* &rarr; *Captcha* and editing the settings there.

If you would like to include the new configuration in your application, follow
the instructions for
[making your applications configurable in Liferay 7.0](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable).

#### Why was this change made? [](id=why-was-this-change-made-2)

This change was made as part of the modularization efforts to ease portal
configuration changes.

---------------------------------------

### Moved OpenOffice Properties to OSGi Configuration [](id=moved-openoffice-properties-to-osgi-configuration)
- **Date:** 2017-March-24
- **JIRA Ticket:** LPS-71382

#### What changed? [](id=what-changed-3)

The OpenOffice properties have been moved from Server Administration to an OSGi
configuration named `OpenOfficeConfiguration.java` in the
`document-library-document-conversion` module.

#### Who is affected? [](id=who-is-affected-3)

This affects anyone using the following portal properties:

- `openoffice.cache.enabled`
- `openoffice.server.enabled`
- `openoffice.server.host`
- `openoffice.server.port`

#### How should I update my code? [](id=how-should-i-update-my-code-3)

Instead of overriding the `portal.properties` file, you can manage the
properties from Portal's configuration administrator. This can be accessed by
navigating to Liferay Portal's *Control Panel* &rarr; *Configuration* &rarr;
*System Settings* &rarr; *Other* &rarr; *OpenOffice Integration* and editing the
settings there.

If you would like to include the new configuration in your application, follow
the instructions for
[making your applications configurable in Liferay 7.0](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable).

#### Why was this change made? [](id=why-was-this-change-made-3)

This change was made as part of the modularization efforts to ease portal
configuration changes.

---------------------------------------

### Removed Indexation of Fields ratings and viewCount [](id=removed-indexation-of-fields-ratings-and-viewcount)
- **Date:** 2017-May-16
- **JIRA Ticket:** LPS-70724

#### What changed? [](id=what-changed-4)

The fields `ratings` and `viewCount` are no longer indexed in the `BaseIndexer`
class for `AssetEntry` objects.

#### Who is affected? [](id=who-is-affected-4)

This affects any search-related custom code where the `ratings` and `viewCount`
fields are used in queries.

#### How should I update my code? [](id=how-should-i-update-my-code-4)

To adapt to these changes, consider several alternatives:

 - Use the Highest Rated Assets and Most Viewed Assets Liferay portlets.
 - Replace the index query with a database query.
 - Implement an `IndexerPostProcessor` to index these fields in certain
   documents.

#### Why was this change made? [](id=why-was-this-change-made-4)

Keeping the Ratings and View Count options in the search index in sync with the
database has a negative impact on normal operations due to the significantly
increased number of index Write requests causing throughput issues and,
therefore, performance degradation.

In addition, the view count is not always up-to-date in the database. This
behavior is controlled by the *Buffered Increment* mechanism. You can find
more information about this in the `portal.properties` file.

---------------------------------------

### Moved Upload Servlet Request Portal Properties to OSGi Configuration [](id=moved-upload-servlet-request-portal-properties-to-osgi-configuration)
- **Date:** 2017-May-30
- **JIRA Ticket:** LPS-69102

#### What changed? [](id=what-changed-5)

The Upload Servlet Request properties have been moved from the
`portal.properties` file and Server Administration to an OSGi configuration
named `UploadServletRequestConfiguration.java` in the `portal-upload` module.

#### Who is affected? [](id=who-is-affected-5)

This affects anyone using the following portal properties:

- `com.liferay.portal.upload.UploadServletRequestImpl.max.size`
- `com.liferay.portal.upload.UploadServletRequestImpl.temp.dir`

#### How should I update my code? [](id=how-should-i-update-my-code-5)

Instead of overriding the `portal.properties` file, you can manage the
properties from Portal's configuration administrator. This can be accessed by
navigating to Liferay Portal's *Control Panel* &rarr; *Configuration* &rarr;
*System Settings* &rarr; *Upload Servlet Request* and editing the settings
there.

If you would like to include the new configuration in your application, follow
the instructions for
[making your applications configurable in Liferay 7.0](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable).

#### Why was this change made? [](id=why-was-this-change-made-5)

This change was made as part of the modularization efforts to ease portal
configuration changes.

---------------------------------------

### Removed the soyutils Module [](id=removed-the-soyutils-module)
- **Date:** 2017-Aug-28
- **JIRA Ticket:** LPS-69102

#### What changed? [](id=what-changed-6)

The module `frontend-js-soyutils-web` is no longer available.

#### Who is affected? [](id=who-is-affected-6)

This affects anyone using the `soyutils` module.

#### How should I update my code? [](id=how-should-i-update-my-code-6)

In the rare case that a component is affected, it is recommended that the code
is migrated to use the `metal-soy` module instead. You can do this by extending
the `Metal.js` provided `Component` classes.

#### Why was this change made? [](id=why-was-this-change-made-6)

The removed module exposed a legacy version of `soyutils`. This caused
interoperability issues between applications using different versions of the
Closure Template library.

---------------------------------------

### Moved three DL File Properties to OSGi Configurations [](id=moved-three-dl-file-properties-to-osgi-configurations)
- **Date:** 2017-August-01
- **JIRA Ticket:** LPS-69208

#### What changed? [](id=what-changed-7)

Two DL File properties have been moved from Server Administration to the OSGi
configuration `DLConfiguration`, and one to `DLFileEntryConfiguration`. Both configurations are located in the `document-library-api` module.

#### Who is affected? [](id=who-is-affected-7)

This affects anyone who is using the following portal properties:

- `dl.file.extensions`
- `dl.file.max.size`
- `dl.file.entry.previewable.processor.max.size`

#### How should I update my code? [](id=how-should-i-update-my-code-7)

Instead of overriding the `portal.properties` file, you can manage the
properties from Portal's configuration administrator. This can be accessed by
navigating to Liferay's *Control Panel* &rarr; *Configuration* &rarr; *System
Settings* &rarr; *Configuration* &rarr; *Documents & Media Service* or *Documents & Media File Entries* and editing the settings there.

If you would like to include the new configuration in your application, follow
the instructions for
[making your applications configurable in Liferay 7.0](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable).

#### Why was this change made? [](id=why-was-this-change-made-7)

This change was made as part of the modularization efforts to ease portal
configuration changes.

---------------------------------------

### Changed Default Value for Browser Cache Properties [](id=changed-default-value-for-browser-cache-properties)
- **Date:** 2017-Sep-05
- **JIRA Ticket:** LPS-74452

#### What changed? [](id=what-changed-8)

The default values for the portal properties `browser.cache.disabled` and
`browser.cache.signed.in.disabled` were changed to `true`.

#### Who is affected? [](id=who-is-affected-8)

This affects anyone relying on proxies and load balancers to cache HTML content.

#### How should I update my code? [](id=how-should-i-update-my-code-8)

You should set both properties `browser.cache.disabled` and
`browser.cache.signed.in.disabled` to `false`, as documented in
`portal-legacy-7.0.properties`.

#### Why was this change made? [](id=why-was-this-change-made-8)

The load balancer and web proxy's behavior when Cache-Control headers are
missing is not defined. In the past, many preferred to not cache the content for
correctness; however, it is now common to cache the content for performance.

When an aggressive caching load balancer or web proxy appears in the network
architecture, the default value may result in security problems such as
personalized content being mistakenly shared, including names or other
personally identifiable information. As Liferay shifts towards use cases
providing personalized experiences, this is becoming a serious problem.

While this is ultimately a load balancer or web proxy configuration issue, it is
perceived as a Liferay issue because it is Liferay content being cached, and is
viewed negatively because leaking sensitive information in a production
environment is a very serious security issue.

A value of `true` will improve a portal administrator's experience, and a value
of `false` can be considered during performance tuning, if needed.
