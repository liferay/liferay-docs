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
- **JIRA Ticket:** [LPS-66646](https://issues.liferay.com/browse/LPS-66646)

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
- **JIRA Ticket:** [LPS-69482](https://issues.liferay.com/browse/LPS-69482)

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
- **JIRA Ticket:** [LPS-69211](https://issues.liferay.com/browse/LPS-69211)

#### What changed? [](id=what-changed-1)

The Users File Uploads portlet properties have been moved from Server
Administration to an OSGi configuration named
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
- **JIRA Ticket:** [LPS-67830](https://issues.liferay.com/browse/LPS-67830)

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
- **Date:** 2017-Mar-24
- **JIRA Ticket:** [LPS-71382](https://issues.liferay.com/browse/LPS-71382)

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
- **JIRA Ticket:** [LPS-70724](https://issues.liferay.com/browse/LPS-70724)

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
- **JIRA Ticket:** [LPS-69102](https://issues.liferay.com/browse/LPS-69102)

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

### Moved Three DL File Properties to OSGi Configuration [](id=moved-three-dl-file-properties-to-osgi-configuration)
- **Date:** 2017-Aug-01
- **JIRA Ticket:** [LPS-69208](https://issues.liferay.com/browse/LPS-69208)

#### What changed? [](id=what-changed-6a)

Two DL File properties have been moved from Server Administration to the OSGi
configuration `DLConfiguration`, and one to `DLFileEntryConfiguration`. Both
configurations are located in the `document-library-api` module.

#### Who is affected? [](id=who-is-affected-6a)

This affects anyone who is using the following portal properties:

- `dl.file.extensions`
- `dl.file.max.size`
- `dl.file.entry.previewable.processor.max.size`

#### How should I update my code? [](id=how-should-i-update-my-code-6a)

Instead of overriding the `portal.properties` file, you can manage the
properties from Portal's configuration administrator. This can be accessed by
navigating to Portal's *Control Panel* &rarr; *Configuration* &rarr; *System
Settings* &rarr; *Collaboration* &rarr; *Documents & Media Service* or
*Documents & Media File Entries* and editing the settings there.

If you would like to include the new configuration in your application, follow
the instructions for
[making your applications configurable](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/making-your-applications-configurable).

#### Why was this change made? [](id=why-was-this-change-made-6a)

This change was made as part of the modularization efforts to ease portal
configuration changes.

---------------------------------------

### Removed the soyutils Module [](id=removed-the-soyutils-module)
- **Date:** 2017-Aug-28
- **JIRA Ticket:** [LPS-69102](https://issues.liferay.com/browse/LPS-69102)

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

### Changed Default Value for Browser Cache Properties [](id=changed-default-value-for-browser-cache-properties)
- **Date:** 2017-Sep-05
- **JIRA Ticket:** [LPS-74452](https://issues.liferay.com/browse/LPS-74452)

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

---------------------------------------

### Users Can Have Numeric Screen Names with No Limitations, and Sites Can No Longer Have Numeric Friendly URLs [](id=users-can-have-numeric-screen-names-with-no-limitations-and-sites-can-no-lo)
- **Date:** 2017-Oct-10
- **JIRA Ticket:** [LPS-66460](https://issues.liferay.com/browse/LPS-66460)

#### What changed? [](id=what-changed-7)

- The portal property `users.screen.name.allow.numeric` now defaults to `true`.
- Numeric screen names are no longer limited by whether they correspond to an
  existing group ID.
- Sites can no longer set their group ID as their friendly URL.
- Sites can no longer be implicitly accessed by using their group ID in the URL
  (this used to be available automatically, even if it wasn't set that way).
- If the friendly URL of a site is already set to the group ID, it will continue
  to work as normal, but you will be forced to change it if you update the site
  in the Site Settings portlet.
- If a site is updated and no friendly URL is provided, it will default to
  `/group-<groupId>`. If that duplicates another friendly URL, the friendly URL
  will be incremented until a unique friendly URL is found (e.g.,
  `/group-<groupId>-1`).
- The default friendly URL for new sites has **not** changed.

#### Who is affected? [](id=who-is-affected-7)

This affects anyone who

- has set the friendly URL of their site to the group ID.
- uses a group ID to navigate or direct to a site.

#### How should I update my code? [](id=how-should-i-update-my-code-7)

No code updates should be required, but if you fall under one of the scenarios
in the previous section, you should consider the following changes:

- If you have set the friendly URL of a site to its group ID, you should
  update the friendly URL of that site to something else. A site administrator
  can do this through the Site Settings portlet.
- If you have hard-coded the group ID in any links, you must change them to
  match the updated friendly URL.

#### Why was this change made? [](id=why-was-this-change-made-7)

There were common complaints from customers who used LDAP to import users
&mdash; if users were given a numeric screen name during import, some imports
would fail because those screen names conflicted with an existing group ID.

This was because a site's group ID could be used as its friendly URL, while a
user's screen name is used as the friendly URL to their personal site. This
could introduce a routing conflict, so numeric screen names were disallowed if
they conflicted with an existing group ID.

By removing sites' ability to use their group ID as their friendly URL, the
possible conflict with numeric screen names is expunged, allowing users to have
any number as their screen name. This makes it much less likely for LDAP imports
to fail when using numeric screen names for imported users.

Since LDAP import is more commonly used than a site using the group ID as its
friendly URL, the less useful feature was removed to stabilize the more common
one.

---------------------------------------

### Removed Support for Velocity in Themes [](id=removed-support-for-velocity-in-themes)
- **Date:** 2017-Oct-19
- **JIRA Ticket:** [LPS-74379](https://issues.liferay.com/browse/LPS-74379)

#### What changed? [](id=what-changed-9)

- Themes can no longer use Velocity for templates.
- Some helper methods have been removed from the public APIs
`com.liferay.portal.kernel.util.ThemeHelper` and
`com.liferay.taglib.util.ThemeUtil`.

#### Who is affected? [](id=who-is-affected-9)

This affects anyone who has themes using Velocity templates or is using the
removed methods.

#### How should I update my code? [](id=how-should-i-update-my-code-9)

If you have a theme using Velocity, consider migrating it to FreeMarker for
better maintenance and improved security.

If you are using the removed methods, consider using the
`com.liferay.portal.kernel.template.Template` functionality directly to process
templates.

#### Why was this change made? [](id=why-was-this-change-made-9)

Velocity was deprecated in Liferay Portal 7.0 and the recommendation was to
migrate to FreeMarker. Also, Velocity has had no new releases for a long time.

The removal of Velocity support for Liferay Portal 7.1 themes allows for an
increased focus on existing and new template engines.

---------------------------------------

### Moved Organization Type Properties to OSGi Configuration [](id=moved-organization-type-properties-to-osgi-configuration)
- **Date:** 2018-Jan-19
- **JIRA Ticket:** [LPS-77183](https://issues.liferay.com/browse/LPS-77183)

#### What changed? [](id=what-changed-10)

The organization type properties have been moved from `portal.properties` to an
OSGi configuration named `OrganizationsTypesConfiguration.java` in the
`users-admin-api` module.

#### Who is affected? [](id=who-is-affected-10)

This affects anyone using the following portal properties:

- `organizations.types`
- `organizations.rootable`
- `organizations.children.types`
- `organizations.country.enabled`
- `organizations.country.required`

#### How should I update my code? [](id=how-should-i-update-my-code-10)

Instead of overriding the `portal.properties` file, you can manage the
properties from Portal's configuration administrator. This can be accessed by
navigating to Liferay Portal's *Control Panel* &rarr; *Configuration* &rarr;
*System Settings* &rarr; *Foundation* &rarr; *Organization Type* and editing
the settings there.

If you would like to include the new configuration in your application, follow
the instructions for
[making your applications configurable](/develop/tutorials/-/knowledge_base/7-1/making-your-applications-configurable).

#### Why was this change made? [](id=why-was-this-change-made-10)

This change was made as part of the modularization efforts to ease portal
configuration changes.

---------------------------------------

### Updated jQuery and Lodash Bundled Versions [](id=updated-jquery-and-lodash-bundled-versions)
- **Date:** 2018-Feb-07
- **JIRA Ticket:** [LPS-66645](https://issues.liferay.com/browse/LPS-66645),
[LPS-66646](https://issues.liferay.com/browse/LPS-66646)

#### What changed? [](id=what-changed-11a)

The bundled jQuery version has been updated from 2.1.4 to 3.3.1. The bundled
Lodash version has been updated from 3.10.1 to 4.17.4.

#### Who is affected? [](id=who-is-affected-11a)

This affects anyone using the previous API versions in their code.

#### How should I update my code? [](id=how-should-i-update-my-code-11a)

Follow the changelogs on the [jQuery](http://jquery.com/upgrade-guide/3.0/) and
[Lodash](https://github.com/lodash/lodash/wiki/Changelog#v400) sites to update
any affected code.

#### Why was this change made? [](id=why-was-this-change-made-11a)

This change provides the latest jQuery and Lodash versions available.

---------------------------------------

### Removed JavaScript Minification Properties minifier.javascript.impl and yui.compressor.* from portal.properties [](id=removed-javascript-minification-properties-minifier-javascript-impl-and-yui)
- **Date:** 2018-Feb-28
- **JIRA Ticket:** [LPS-74375](https://issues.liferay.com/browse/LPS-74375)

#### What changed? [](id=what-changed-11)

The JavaScript minifiers have been extracted from `portal-kernel` and moved to
their own OSGi module. Thus, they are not configured in `portal.properties` any
more, but rather, through OSGi configuration.

#### Who is affected? [](id=who-is-affected-11)

This affects anyone who had the Yahoo JavaScript minifier active and configured
to override its default settings.

#### How should I update my code? [](id=how-should-i-update-my-code-11)

If you are implementing your own JavaScript minifier, you should extract it to
its own OSGi module. See module
[frontend-js-minifier](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-js/frontend-js-minifier)
for an example of how to do this.

#### Why was this change made? [](id=why-was-this-change-made-11)

The JavaScript minifiers were not easy to customize. For example, the Google
minifier used an old version of the closure-compiler, which was difficult to
upgrade because it required `portal-kernel` dependency changes. This could
create conflicts.

Having JavaScript minifiers in their own OSGi modules requires less dependency
management and makes it easier to provide new implementations of JavaScript
minifiers. Also, configuration can now be done using OSGi standards.

---------------------------------------

### Changed Behavior of liferay-ui:input-date Taglib's showDisableCheckbox Argument [](id=changed-behavior-of-liferay-uiinput-date-taglibs-showdisablecheckbox-argume)
- **Date:** 2018-Mar-06
- **JIRA Ticket:** [LPS-78475](https://issues.liferay.com/browse/LPS-78475)

#### What changed? [](id=what-changed-12)

Previously, when the `liferay-ui:input-date` taglib's `showDisableCheckbox`
argument was set to `true`, the disable checkbox was hidden. Now, the value
`true` displays it, and `false` hides it.

#### Who is affected? [](id=who-is-affected-12)

This affects anyone trying to hide the `liferay-ui:input-date` taglib's disable
checkbox.

#### How should I update my code? [](id=how-should-i-update-my-code-12)

If you are setting the `showDisableCheckbox` argument to `true` to hide the
`liferay-ui:input-date` taglib's disable checkbox, you should now set it to
`false`, and vice versa.

#### Why was this change made? [](id=why-was-this-change-made-12)

The behavior did not match with the name of the argument and was
counter-intuitive.

---------------------------------------

### Updated Liferay Portal's Portlet API Implementation [](id=updated-liferay-portals-portlet-api-implementation)
- **Date:** 2018-May-10
- **JIRA Ticket:** LPS-73282

#### What changed? [](id=what-changed-13)

Liferay Portal 7.1 implements the Portlet 3.0 API. Previous versions implemented
the Portlet 2.0 API.

#### Who is affected? [](id=who-is-affected-13)

This affects developers planning to upgrade custom portlets from earlier
versions of Liferay Portal.

#### How should I update my code? [](id=how-should-i-update-my-code-13)

There are four specific development use-cases:

1. Portlets that were developed as `.jar` (not `.war`) portlet modules using
   `@Component`. These portlets must upgrade from the `portlet-api-2.0`
   dependency to the `portlet-api-3.0.1` dependency. In most cases, there are
   zero code changes required.

2. JSPs that use `portlet:defineObjects` will encounter JSP compilation problems
   if they have created reference variables with the following names in Java
   scriptlets:

    - `actionParams`
    - `clientDataRequest`
    - `cookies`
    - `contextPath`
    - `locale`
    - `locales`
    - `mutableRenderParams`
    - `namespace`
    - `portletContext`
    - `portletMode`
    - `portletRequest`
    - `portletResponse`
    - `resourceParams`
    - `windowId`
    - `windowState`
    - `stateAwareResponse`

    For example, JSP scriptlets like the following ones had to be removed from
    several of Liferay's out-of-the-box portlets' `view.jsp`:

          <%=
          PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

          PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

          String namespace = AUIUtil.getNamespace(portletRequest, portletResponse);

          if (Validator.isNull(namespace)) {
              namespace = AUIUtil.getNamespace(request);
          }
          %>

3. The return values of
   [`MimeResponse.createActionURL()`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/MimeResponse.html#createActionURL())
   and
   [`MimeResponse.createRenderURL()`](https://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/MimeResponse.html#createRenderURL())
   have changed. Although this is a binary-runtime-compatible type of change, it
   can possibly cause compilation failures or `ClassCastException`s to be thrown
   during request/response processing.

    For example, a Liferay Portal sample portlet's `view.jsp` had to be changed
    from:

        <aui:form action="<%= renderResponse.createActionURL() %>" method="post" name="fm">

    to:

        <aui:form action="<%= (PortletURL)renderResponse.createActionURL() %>" method="post" name="fm">

4. JSF Portlets must be upgraded to the latest version of Liferay Faces Bridge,
   which is planned for release in Q4, 2018. Download and upgrade instructions
   will be made available at
   [https://www.liferayfaces.org](https://www.liferayfaces.org) at that time.

#### Why was this change made? [](id=why-was-this-change-made-13)

This change provides the latest features offered by the Portlet 3.0
Specification, which was released in early 2017.

---------------------------------------
