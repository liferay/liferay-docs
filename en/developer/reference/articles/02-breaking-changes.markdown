---
header-id: breaking-changes
---

# Breaking Changes

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120"> This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-2-breaking-changes.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

This document presents a chronological list of changes that break existing
functionality, APIs, or contracts with third party Liferay developers or users.
We try our best to minimize these disruptions, but sometimes they are
unavoidable.

Here are some of the types of changes documented in this file:

- Functionality that is removed or replaced
- API incompatibilities: Changes to public Java or JavaScript APIs
- Changes to context variables available to templates
- Changes in CSS classes available to Liferay themes and portlets
- Configuration changes: Changes in configuration files, like
  `portal.properties`, `system.properties`, etc.
- Execution requirements: Java version, J2EE Version, browser versions, etc.
- Deprecations or end of support: For example, warning that a certain
  feature or API will be dropped in an upcoming version.
- Recommendations: For example, recommending using a newly introduced API that
  replaces an old API, in spite of the old API being kept in Liferay Portal for
  backwards compatibility.

## Breaking Changes List

### Removed Support for JSP Templates in Themes
- **Date:** 2018-Nov-14
- **JIRA Ticket:** [LPS-87064](https://issues.liferay.com/browse/LPS-87064)

#### What changed?

Themes can no longer leverage JSP templates. Also, related logic has been removed from the public APIs `com.liferay.portal.kernel.util.ThemeHelper` and `com.liferay.taglib.util.ThemeUtil`.

#### Who is affected?

This affects anyone who has themes using JSP templates or is using the removed methods.

#### How should I update my code?

If you have a theme using JSP templates, consider migrating it to FreeMarker.

#### Why was this change made?

JSP is not a real template engine and is rarely used. FreeMarker is the recommended template engine moving forward.

The removal of JSP templates allows for an increased focus on existing and new template engines.

---------------------------------------

### Lodash Is No Longer Included by Default
- **Date:** 2018-Nov-27
- **JIRA Ticket:** [LPS-87677](https://issues.liferay.com/browse/LPS-87677)

#### What changed?

Previously, Lodash was included in every page by default and made available through the global `window._` and scoped `AUI._` variables. Lodash is no longer included by default and those variables are now undefined.

#### Who is affected?

This affects any developer who used the `AUI._` or `window._` variables in their custom scripts.

#### How should I update my code?

You should provide your own Lodash version for your custom developments to use following any of the possible strategies to add third party libraries.

As a temporary measure, you can bring back the old behavior by setting the *Enable Lodash* property in Liferay Portal's *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; *Third Party* &rarr; *Lodash* to `true`.

#### Why was this change made?

This change was made to avoid bundling and serving additional library code on every page that was mostly unused and redundant.

---------------------------------------

### Moved Two Staging Properties to OSGi Configuration
- **Date:** 2018-Dec-12
- **JIRA Ticket:** [LPS-88018](https://issues.liferay.com/browse/LPS-88018)

#### What changed?

Two Staging properties have been moved from `portal.properties` to an OSGi configuration named `ExportImportServiceConfiguration.java` in the `export-import-service` module.

#### Who is affected?

This affects anyone using the following portal properties:

- `staging.delete.temp.lar.on.failure`
- `staging.delete.temp.lar.on.success`

#### How should I update my code?

Instead of overriding the `portal.properties` file, you can manage the properties from Portal's configuration administrator. This can be accessed by navigating to Liferay Portal's *Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; *Infrastructure* &rarr; *Export/Import* and editing the settings there.

If you would like to include the new configuration in your application, follow the instructions for [making applications configurable](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable).

#### Why was this change made?

This change was made as part of the modularization efforts to ease portal configuration changes.

---------------------------------------

### Remove Link Application URLs to Page Functionality
- **Date:** 2018-Dec-14
- **JIRA Ticket:** [LPS-85948](https://issues.liferay.com/browse/LPS-85948)

#### What changed?

The *Link Portlet URLs to Page* option in the Look and Feel portlet was marked as deprecated in Liferay Portal 7.1, allowing the user to show and hide the option through a configuration property. In Liferay Portal 7.2, this has been removed and can no longer be configured.

#### Who is affected?

This affects administrators who used the option in the UI and developers who leveraged the option in the portlet.

#### How should I update my code?

You should update any portlets leveraging this feature, since any preconfigured reference to the property is ignored in the portal.

#### Why was this change made?

A limited number of portlets use this property; there are better ways to achieve the same results.

---------------------------------------

### Moved TermsOfUseContentProvider out of kernel.util
- **Date:** 2019-Jan-07
- **JIRA Ticket:** [LPS-88869](https://issues.liferay.com/browse/LPS-88869)

#### What changed?

The `TermsOfUseContentProvider` interface's package changed:

`com.liferay.portal.kernel.util` &rarr; `com.liferay.portal.kernel.term.of.use`

The `TermsOfUseContentProviderRegistryUtil` class' name and package changed:

`TermsOfUseContentProviderRegistryUtil` &rarr; `TermsOfUseContentProviderUtil`

and `com.liferay.portal.kernel.util` &rarr; `com.liferay.portal.internal.terms.of.use`

The logic of getting `TermsOfUseContentProvider` was also changed. Instead of always returning the first service registered, which is random and depends on the order of registered services, the `TermsOfUseContentProvider` service is tracked and updated with `com.liferay.portal.kernel.util.ServiceProxyFactory`. As a result, the `TermsOfUseContentProvider` now respects service ranking.

#### Who is affected?

This affects anyone who used `com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil` to lookup the `com.liferay.portal.kernel.util.TermsOfUseContentProvider` service.

#### How should I update my code?

If `com.liferay.portal.kernel.util.TermsOfUseContentProvider` is used, update the import package name. If there is any usage in `portal-web`, update `com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil` to `com.liferay.portal.kernel.term.of.use.TermsOfUseContentProviderUtil`. Remove usages of `com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil` in modules and use the `@Reference` annotation to fetch the `com.liferay.portal.kernel.term.of.use.TermsOfUseContentProvider` service instead.

#### Why was this change made?

This is one of several steps to clean up kernel provider interfaces to reduce the chance of package version lock down.

---------------------------------------

### Removed HibernateConfigurationConverter and Converter
- **Date:** 2019-Jan-07
- **JIRA Ticket:** [LPS-88870](https://issues.liferay.com/browse/LPS-88870)

#### What changed?

The interface `com.liferay.portal.kernel.util.Converter` and its implementation `com.liferay.portal.spring.hibernate.HibernateConfigurationConverter` were removed.

#### Who is affected?

This removes the support of generating customized `portlet-hbm.xml` files implemented by `HibernateConfigurationConverter`. Refer to [LPS-5363](https://issues.liferay.com/browse/LPS-5363) for more information.

#### How should I update my code?

You should remove usages of `HibernateConfigurationConverter`. Make sure the generated `portlet-hbm.xml` is accurate.

#### Why was this change made?

This is one of several steps to clean up kernel provider interfaces to reduce the chance of package version lock down.

---------------------------------------

### Switched to Use JDK Function and Supplier
- **Date:** 2019-Jan-08
- **JIRA Ticket:** [LPS-88911](https://issues.liferay.com/browse/LPS-88911)

#### What changed?

The `Function` and `Supplier` interfaces in package `com.liferay.portal.kernel.util` were removed. Their usages were replaced with `java.util.function.Function` and `java.util.function.Supplier`.

#### Who is affected?

This affects anyone who implemented the `Function` and `Supplier` interfaces in package `com.liferay.portal.kernel.util`.

#### How should I update my code?

You should replace usages of `com.liferay.portal.kernel.util.Function` and `com.liferay.portal.kernel.util.Supplier` with `java.util.function.Function` and `java.util.function.Supplier`, respectively.

#### Why was this change made?

This is one of several steps to clean up kernel provider interfaces to reduce the chance of package version lock down.

---------------------------------------

### Deprecated com.liferay.portal.service.InvokableService Interface
- **Date:** 2019-Jan-08
- **JIRA Ticket:** [LPS-88912](https://issues.liferay.com/browse/LPS-88912)

#### What changed?

The `InvokableService` and `InvokableLocalService` interfaces in package `com.liferay.portal.kernel.service` were removed.

#### Who is affected?

This affects anyone who used `InvokableService` and `InvokableLocalService` in package `com.liferay.portal.kernel.service`.

#### How should I update my code?

You should remove usages of `InvokableService` and `InvokableLocalService`. Make sure to use the latest version of Service Builder to generate implementations for services in case there is any compile errors after removal.

#### Why was this change made?

This is one of several steps to clean up kernel provider interfaces to reduce the chance of package version lock down.

---------------------------------------

### Dropped Support of ServiceLoaderCondition
- **Date:** 2019-Jan-08
- **JIRA Ticket:** [LPS-88913](https://issues.liferay.com/browse/LPS-88913)

#### What changed?

The interface `ServiceLoaderCondition` and its implementation `DefaultServiceLoaderCondition` in package `com.liferay.portal.kernel.util` were removed.

#### Who is affected?

This affects anyone using `ServiceLoaderCondition` and `DefaultServiceLoaderCondition`.

#### How should I update my code?

You should remove usages of `ServiceLoaderCondition`. Update usages of `load` methods in `com.liferay.portal.kernel.util.ServiceLoader` according to the updated method signatures.

#### Why was this change made?

This is one of several steps to clean up kernel provider interfaces to reduce the chance of package version lock down.

---------------------------------------

### Switched to Use JDK Predicate
- **Date:** 2019-Jan-14
- **JIRA Ticket:** [LPS-89139](https://issues.liferay.com/browse/LPS-89139)

#### What changed?

The interface `com.liferay.portal.kernel.util.PredicateFilter` was removed and replaced with `java.util.function.Predicate`. As a result, the following implementations were removed:

- `com.liferay.portal.kernel.util.AggregatePredicateFilter`
- `com.liferay.portal.kernel.util.PrefixPredicateFilter`
- `com.liferay.portal.kernel.portlet.JavaScriptPortletResourcePredicateFilter`
- `com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValuePredicateFilter`

The `com.liferay.portal.kernel.util.ArrayUtil_IW` class was regenerated.

#### Who is affected?

This affects anyone who used `PredicateFilter`, `AggregatePredicateFilter`, `PrefixPredicateFilter`, `JavaScriptPortletResourcePredicateFilter`, and `DDMFormFieldValuePredicateFilter`.

#### How should I update my code?

You should replace usages of `com.liferay.portal.kernel.util.PredicateFilter` with `java.util.function.Predicate`. Additionally, remove usages of `AggregatePredicateFilter`, `PrefixPredicateFilter`, `JavaScriptPortletResourcePredicateFilter`, and `DDMFormFieldValuePredicateFilter`.

#### Why was this change made?

This is one of several steps to clean up kernel provider interfaces to reduce the chance of package version lock down.

---------------------------------------

### Removed Unsafe Functional Interfaces in Package com.liferay.portal.kernel.util
- **Date:** 2019-Jan-15
- **JIRA Ticket:** [LPS-89223](https://issues.liferay.com/browse/LPS-89223)

#### What changed?

The `com.liferay.portal.osgi.util.test.OSGiServiceUtil` class was removed. Also, the following interfaces were removed from the `com.liferay.portal.kernel.util` package:

- `UnsafeConsumer`
- `UnsafeFunction`
- `UnsafeRunnable`

#### Who is affected?

This affects anyone using the class/interfaces mentioned above.

#### How should I update my code?

The `com.liferay.portal.osgi.util.test.OSGiServiceUtil` class has been deprecated since Liferay Portal 7.1. If usages for this class still exist, replace it with its direct replacement: `com.liferay.osgi.util.service.OSGiServiceUtil`. Replace usages of `UnsafeConsumer`, `UnsafeFunction` and `UnsafeRunnable` with their corresponding interfaces in package `com.liferay.petra.function`.

#### Why was this change made?

This is one of several steps to clean up kernel provider interfaces to reduce the chance of package version lock down.

---------------------------------------

### Deprecated NTLM in Portal Distribution
- **Date:** 2019-Jan-21
- **JIRA Ticket:** [LPS-88300](https://issues.liferay.com/browse/LPS-88300)

#### What changed?

NTLM modules have been moved from the `portal-security-sso` project to a new project named `portal-security-sso-ntlm`. This new project is deprecated and available to download from Liferay Marketplace.

#### Who is affected?

This affects anyone using NTLM as an authentication system.

#### How should I update my code?

If you want to continue using NTLM as an authentication system, you must download the corresponding modules from Liferay Marketplace. Alternatively, you can migrate to Kerberos (recommended), which requires no changes and is compatible with Liferay Portal 7.0+.

#### Why was this change made?

This change was made to avoid using an old proprietary solution (NTLM). Kerberos is now recommended, which is a standard protocol and a more secure method of authentication compared to NTLM.

---------------------------------------

### Deprecated OpenID in Portal Distribution
- **Date:** 2019-Jan-21
- **JIRA Ticket:** [LPS-88906](https://issues.liferay.com/browse/LPS-88906)

#### What changed?

OpenID modules have been moved to a new project named `portal-security-sso-openid`. This new project is deprecated and available to download from Liferay Marketplace.

#### Who is affected?

This affects anyone using OpenID as an authentication system.

#### How should I update my code?

If you want to continue using OpenID as an authentication system, you must download the corresponding module from Liferay Marketplace. Alternatively, you should migrate to OpenID Connect, available on Liferay Portal Distribution.

#### Why was this change made?

This change was made to avoid using a deprecated solution (OpenID). OpenID Connect is now recommended, which is a more secure method of authentication since it runs on top of OAuth.

---------------------------------------

### Deprecated Google SSO in Portal Distribution
- **Date:** 2019-Jan-21
- **JIRA Ticket:** [LPS-88905](https://issues.liferay.com/browse/LPS-88905)

#### What changed?

Google SSO modules have been moved from the `portal-security-sso` project to a new project named `portal-security-sso-google`. This new project is deprecated and available to download from Liferay Marketplace.

#### Who is affected?

This affects anyone using Google SSO as an authentication system.

#### How should I update my code?

If you want to continue using Google SSO as an authentication system, you must download the corresponding module from Liferay Marketplace. Alternatively, you can use OpenID Connect.

#### Why was this change made?

This change was made to avoid using an old solution for authentication (Google SSO). OpenID Connect is the recommended specification to use Google implementation for authentication.

---------------------------------------

### Updated AlloyEditor v2.0 Includes New Major Version of React
- **Date:** 2019-Feb-04
- **JIRA Ticket:** [LPS-90079](https://issues.liferay.com/browse/LPS-90079)

#### What changed?

AlloyEditor was upgraded to version 2.0.0, which includes a major upgrade from React v15 to v16.

The `React.createClass` was [deprecated in React v15.5.0](https://reactjs.org/blog/2017/04/07/react-v15.5.0.html) (April 2017) and [removed in React v16.0.0](https://reactjs.org/blog/2017/09/26/react-v16.0.html) (September 2017). All the buttons bundled with AlloyEditor have been updated to use the ES6 class syntax instead of `React.createClass`.

#### Who is affected?

This affects anyone who built their own buttons using `React.createClass`. The `createClass` function is no longer available, and attempts to access it at runtime will trigger an error.

#### How should I update my code?

You should update your code in one of two ways:

- Port custom buttons from the `React.createClass` API to use the ES6 `class` API, as described in [the React documentation](https://reactjs.org/docs/react-component.html). For example, see the changes made in moving to an [ES6 class-based button](https://github.com/liferay/alloy-editor/blob/b082c312179ae6626cb2ddcc04ad3ebc5b355e1b/src/components/buttons/button-ol.jsx) from [the previous `createClass`-based implementation](https://github.com/liferay/alloy-editor/blob/2826ab9ceabe17c6ba0d38985baf8a787c23db43/src/ui/react/src/components/buttons/button-ol.jsx).

- Provide a compatibility adapter. The [create-react-class package](https://www.npmjs.com/package/create-react-class) (described [here](https://reactjs.org/docs/react-without-es6.html)) can be injected into the page to restore the `createClass` API.

#### Why was this change made?

This change was made to use a newer major version of React, which brings performance and compatibility improvements and reduces the bundle size by removing deprecated APIs.

---------------------------------------

### Deprecated dl.tabs.visible property
- **Date:** 2019-Apr-10
- **JIRA Ticket:** [LPS-93948](https://issues.liferay.com/browse/LPS-93948)

#### What changed?

The `dl.tabs.visible` property let users toggle the visibility of a Documents and Media widget's navigation tabs when placed on a widget page. This configuration option has been removed, so the navigation tab will never appear on widget pages.

#### Who is affected?

This affects anyone who set the `dl.tabs.visible` property to `true`.

#### How should I update my code?

No code changes are necessary.

#### Why was this change made?

Documents & Media has been reviewed from a UX perspective, and removing the navigation tabs in widget pages was part of a UI clean up process.

---------------------------------------

### Move the User Menu out of the Product Menu
- **Date:** 2019-Apr-19
- **JIRA Ticket:** [LPS-87868](https://issues.liferay.com/browse/LPS-87868)

#### What changed?

The User Menu was removed from the Product Menu, and the user menu entries were moved to the new Personal Menu, a dropdown menu triggered by the user avatar.

#### Who is affected?

This affects anyone who has customized the User Menu section of the Product Menu.

#### How should I update my code?

If you would like to keep your custom user menu entries and have them available in the Personal Menu, you need to implement the `PersonalMenuEntry` interface. All panel apps registered with the `PanelCategoryKeys.USER`, `PanelCategoryKeys.USER_MY_ACCOUNT`, and `PanelCategoryKeys.USER_SIGN_OUT` panel category keys should be converted to `PersonalMenuEntry`.

#### Why was this change made?

Product navigation has been reviewed from a UX perspective, and removing the User Menu from the Product Menu and splitting the menu to its own provides a better user experience.

---------------------------------------

### Removed Hong Kong and Macau from the List of Countries
- **Date:** 2019-Apr-26
- **JIRA Ticket:** [LPS-82203](https://issues.liferay.com/browse/LPS-82203)

#### What changed?

Hong Kong and Macau have been removed from the list of countries and listed as regions of China as Xianggang (region code: CN-91) and Aomen (region code: CN-92), respectively.

#### Who is affected?

This affects anyone who used Hong Kong or Macau in their addresses.

#### How should I update my code?

No code changes are necessary. However, if you have hardcoded the `countryId` of Hong Kong and Macau in your code, they should be updated to China's `countryId`. References to Hong Kong and Macau should be done with their corresponding `regionId`.

#### Why was this change made?

After the handover of Hong Kong in 1997 and of Macau in 1999, Hong Kong and Macau are now the special administrative regions of China.

---------------------------------------

### JGroups Was Upgraded From 3.6.16 to 4.1.1
- **Date:** 2019-Aug-15
- **JIRA Ticket:** [LPS-97897](https://issues.liferay.com/browse/LPS-97897)

#### What changed?

JGroups was upgraded from version 3.6.16 to 4.1.1.

#### Who is affected?

This affects anyone using Cluster Link.

#### How should I update my code?

The `cluster.link.channel.properties.*` property in `portal.properties` no longer accepts a connection string as a value; it now requires a file path to a configuration XML file. Some of the protocol properties from 3.6.16 are removed and no longer parsed by 4.1.1; you should update the protocol properties accordingly.

#### Why was this change made?

This upgrade was made to fix a security issue.

---------------------------------------

### Liferay `AssetEntries_AssetCategories` Is No Longer Used
- **Date:** 2019-Sep-11
- **JIRA Tickets:** [LPS-99973](https://issues.liferay.com/browse/LPS-99973),
[LPS-76488](https://issues.liferay.com/browse/LPS-76488)

#### What changed?

Previously, Liferay used a mapping table and a corresponding interface for the relationship between `AssetEntry` and `AssetCategory` in `AssetEntryLocalService` and `AssetCategoryLocalService`. This mapping table and the corresponding interface have been replaced by the table `AssetEntryAssetCategoryRel` and the service `AssetEntryAssetCategoryRelLocalService`.

#### Who is affected?

This affects any content or code that relies on calling the old interfaces for the `AssetEntries_AssetCategories` relationship, through the `AssetEntryLocalService` and `AssetCategoryLocalService`.

#### How should I update my code?

Use the new methods in `AssetEntryAssetCategoryRelLocalService` to retrieve the same data as before. The method signatures haven't changed; they have just been relocated to a different service.

**Example**

Old way:

```java
List<AssetEntry> entries =
AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId);

for (AssetEntry entry: entries) {
  ...
}
```

New way:

```java
long[] assetEntryPKs =
_assetEntryAssetCategoryRelLocalService.getAssetEntryPrimaryKeys(assetCategoryId);

for (long assetEntryPK: assetEntryPKs) {
  AssetEntry = _assetEntryLocalService.getEntry(assetEntryPK);
  ...
}

...

@Reference
private AssetEntryAssetCategoryRelLocalService _assetEntryAssetCategoryRelLocalService;

@Reference
private AssetEntryLocalService _assetEntryLocalService;
```

#### Why was this change made?

This change was made due to changes resulting from [LPS-76488](https://issues.liferay.com/browse/LPS-76488), which let developers control the order of a list of assets for a given category.

---------------------------------------

### Auto Tagging Must Be Reconfigured Manually
- **Date: 2019-Oct-2**
- **JIRA Ticket:** [LPS-97123](https://issues.liferay.com/browse/LPS-97123)

#### What changed?

Auto Tagging configurations were renamed and reorganized. There's no longer an automatic upgrade process, so you must reconfigure Auto Tagging manually.

#### Who is affected?

This affects DXP 7.2 installations that are upgraded to SP1 and have Auto Tagging configured and enabled.

#### How should I update my code?

You must reconfigure Auto Tagging through System Settings (please see the [official documentation](https://help.liferay.com/hc/en-us/articles/360029041551-Configuring-Asset-Auto-Tagging) for details). Any code referencing the old configuration interfaces must be updated to use the new ones.

#### Why was this change made?

This change unifies the previously split configuration interfaces, improving the user experience.

---------------------------------------

### Blogs Image Properties Were Moved to System Settings
- **Date: 2019-Oct-2**
- **JIRA Ticket:** [LPS-95298](https://issues.liferay.com/browse/LPS-95298)

#### What changed?

Blogs image configuration was moved from `portal.properties` to System Settings. There's no automatic upgrade process, so custom Blogs image properties must be reconfigured manually.

#### Who is affected?

This affects DXP 7.2 installations that are upgraded to SP1 and have custom values for the `blogs.image.max.size` and `blogs.image.extensions` properties.

#### How should I update my code?

If you would like to keep your custom Blogs image property values, you must reconfigure them through the System Settings under *Configuration* &rarr; *Blogs* &rarr; *File Uploads*. Any code referencing the old properties must be updated to use the new configuration interfaces.

#### Why was this change made?

This change was made so Blogs image properties can be configured without a restart.

---------------------------------------

### Removed Cache Bootstrap Feature
- **Date:** 2020-Jan-8
- **JIRA Ticket:** [LPS-96563](https://issues.liferay.com/browse/LPS-96563)

#### What changed?

The cache bootstrap feature has been removed. These properties can no longer be used to enable/configure cache bootstrap:

`ehcache.bootstrap.cache.loader.enabled`,
`ehcache.bootstrap.cache.loader.properties.default`,
`ehcache.bootstrap.cache.loader.properties.${specific.cache.name}`.

#### Who is affected?

This affects anyone using the properties listed above.

#### How should I update my code?

There's no direct replacement for the removed feature. If you have code that depends on it, you must implement it yourself.

#### Why was this change made?

This change was made to avoid security issues.

---------------------------------------