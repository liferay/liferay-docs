---
header-id: development-reference
---

# Development Reference

[TOC levels=1-4]

Here you'll find reference documentation for @product@, Liferay Screens, Liferay
Faces, and technologies related to you as a third-party developer.

The different types of reference docs you'll find in this section are as
follows:

- Descriptions of Java and JavaScript APIs, CSS, tags and tag libraries, and
XML DTDs
- Write ups on the latest Screenlets for Liferay Screens
- Breaking changes
- Cheat sheets and tips on
    - Plugin anatomy
    - Design patterns
    - Tools
    - Adapting to new APIs

Liferay's reference docs are at your fingertips.

## Java APIs

Here you'll find Javadoc for @product-ver@ and @product@ apps.

### @product-ver@ Java APIs

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
caption {
    text-align: left;
}
</style>
<table style="width:100%">
  <caption>
    This table links you to the @product-ver@ API modules. Their
    root location is
    <a href="@platform-ref@/7.1-latest/javadocs/" target="_blank">
    here.<span class="opens-new-window-accessible"> (Opens New Window) </span>
    </a> The
    reference doc Zip is available
    <a href="@platform-ref@/7.1-latest/" target="_blank">
    here.<span class="opens-new-window-accessible"> (Opens New Window) </span>
    </a>
    <br>
  </caption>

  <tr>
    <th rowspan="5">Core:</th>
    <td>
	<a href="@platform-ref@/7.1-latest/javadocs/portal-kernel/index.html" target="_blank">
	com.liferay.portal.kernel (portal-kernel):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for developing applications on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.1-latest/javadocs/util-bridges/index.html" target="_blank">
	com.liferay.util.bridges (util-bridges):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for using various non-proprietary computing languages, frameworks, and utilities
       on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.1-latest/javadocs/util-java/index.html" target="_blank">
	com.liferay.util.java (util-java):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for using various Java-related frameworks and utilities on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.1-latest/javadocs/util-slf4j/index.html" target="_blank">
	com.liferay.util.slf4j (util-slf4j):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for using the Simple Logging Facade for Java (SLF4J)
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.1-latest/javadocs/portal-impl/index.html" target="_blank">
	com.liferay.portal.impl (portal-impl):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;refer to this only if you are an advanced Liferay developer that needs a deeper
       understanding of @product-ver@'s implementation in order to contribute to it
    </td>
  </tr>

</table>

### @product@ App Java APIs

This table links you to Liferay DXP application APIs. Their root location is [here](https://docs.liferay.com/dxp/apps).

| App                                                                                                                                                          | Packages                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Announcements:**](https://docs.liferay.com/portal/7.1-latest/apps/announcements-1.0.1/javadocs/)                                                          | com.liferay.announcements.constants                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [**Apio Architect:**](https://docs.liferay.com/portal/7.1-latest/apps/apio-architect-1.0.1/javadocs/)                                                        | com.liferay.apio.architect.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [**Application List:**](https://docs.liferay.com/portal/7.1-latest/apps/application-list-1.0.1/javadocs/)                                                    | com.liferay.application.list.api <br> com.liferay.application.list.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| [**Asset:**](https://docs.liferay.com/portal/7.1-latest/apps/asset-1.0.1/javadocs/)                                                                          | com.liferay.asset.api <br> com.liferay.asset.categories.navigation.api <br> com.liferay.asset.category.property.api <br> com.liferay.asset.display.api <br> com.liferay.asset.display.page.api <br> com.liferay.asset.display.page.item.selector.api <br> com.liferay.asset.entry.rel.api <br> com.liferay.asset.publisher.api <br> com.liferay.asset.tag.stats.api <br> com.liferay.asset.taglib <br> com.liferay.asset.tags.api <br> com.liferay.asset.tags.navigation.api <br> com.liferay.asset.test.util |
| [**Blogs:**](https://docs.liferay.com/portal/7.1-latest/apps/blogs-1.0.1/javadocs/)                                                                          | com.liferay.blogs.api <br> com.liferay.blogs.demo.data.creator.api <br> com.liferay.blogs.item.selector.api <br> com.liferay.blogs.recent.bloggers.api <br> com.liferay.blogs.test.util                                                                                                                                                                                                                                                                                                                       |
| [**Calendar:**](https://docs.liferay.com/portal/7.1-latest/apps/calendar-3.0.1/javadocs/)                                                                    | com.liferay.calendar.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [**Captcha:**](https://docs.liferay.com/portal/7.1-latest/apps/captcha-1.0.0/javadocs/)                                                                      | com.liferay.captcha.api <br> com.liferay.captcha.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [**Comment:**](https://docs.liferay.com/portal/7.1-latest/apps/comment-1.0.1/javadocs/)                                                                      | com.liferay.comment.api <br> com.liferay.comment.demo.data.creator.api <br> com.liferay.comment.taglib                                                                                                                                                                                                                                                                                                                                                                                                        |
| [**Configuration Admin:**](https://docs.liferay.com/portal/7.1-latest/apps/configuration-admin-1.0.1/javadocs/)                                              | com.liferay.configuration.admin.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [**Contacts:**](https://docs.liferay.com/portal/7.1-latest/apps/contacts-1.0.1/javadocs/)                                                                    | com.liferay.contacts.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [**Document Library:**](https://docs.liferay.com/portal/7.1-latest/apps/document-library-1.0.1/javadocs/)                                                    | com.liferay.document.library.api <br> com.liferay.document.library.content.api <br> com.liferay.document.library.demo.data.creator.api <br> com.liferay.document.library.file.rank.api <br> com.liferay.document.library.repository.authorization.api <br> com.liferay.document.library.repository.cmis.api <br> com.liferay.document.library.repository.external.api <br> com.liferay.document.library.sync.api <br> com.liferay.document.library.test.util                                                  |
| [**Dynamic Data Lists:**](https://docs.liferay.com/portal/7.1-latest/apps/dynamic-data-lists-1.0.1/javadocs/)                                                | com.liferay.dynamic.data.lists.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| [**Dynamic Data Mapping:**](https://docs.liferay.com/portal/7.1-latest/apps/dynamic-data-mapping-1.0.1/javadocs/)                                            | com.liferay.dynamic.data.mapping.api <br> com.liferay.dynamic.data.mapping.taglib <br> com.liferay.dynamic.data.mapping.test.util                                                                                                                                                                                                                                                                                                                                                                             |
| [**Export Import:**](https://docs.liferay.com/portal/7.1-latest/apps/export-import-1.0.1/javadocs/)                                                          | com.liferay.exportimport.api <br> com.liferay.exportimport.changeset.api <br> com.liferay.exportimport.changeset.taglib <br> com.liferay.exportimport.test.util                                                                                                                                                                                                                                                                                                                                               |
| [**Flags:**](https://docs.liferay.com/portal/7.1-latest/apps/flags-1.0.1/javadocs/)                                                                          | com.liferay.flags.api <br> com.liferay.flags.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [**Fragment:**](https://docs.liferay.com/portal/7.1-latest/apps/fragment-1.0.1/javadocs/)                                                                    | com.liferay.fragment.api <br> com.liferay.fragment.demo.data.creator.api <br> com.liferay.fragment.item.selector.api                                                                                                                                                                                                                                                                                                                                                                                          |
| [**Friendly URL:**](https://docs.liferay.com/portal/7.1-latest/apps/friendly-url-1.0.1/javadocs/)                                                            | com.liferay.friendly.url.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| [**Frontend Editor:**](https://docs.liferay.com/portal/7.1-latest/apps/frontend-editor-1.0.1/javadocs/)                                                      | com.liferay.frontend.editor.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [**Frontend Image Editor:**](https://docs.liferay.com/portal/7.1-latest/apps/frontend-image-editor-1.0.1/javadocs/)                                          | com.liferay.frontend.image.editor.capability                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| [**Frontend JS:**](https://docs.liferay.com/portal/7.1-latest/apps/frontend-js-1.0.1/javadocs/)                                                              | com.liferay.frontend.js.loader.modules.extender.npm                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [**HTML Preview:**](https://docs.liferay.com/portal/7.1-latest/apps/html-preview-1.0.1/javadocs/)                                                            | com.liferay.html.preview.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| [**Invitation:**](https://docs.liferay.com/portal/7.1-latest/apps/invitation-1.0.1/javadocs/)                                                                | com.liferay.invitation.invite.members.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| [**Item Selector:**](https://docs.liferay.com/portal/7.1-latest/apps/item-selector-1.0.1/javadocs/)                                                          | com.liferay.item.selector.api <br> com.liferay.item.selector.criteria.api <br> com.liferay.item.selector.taglib                                                                                                                                                                                                                                                                                                                                                                                               |
| [**Journal:**](https://docs.liferay.com/portal/7.1-latest/apps/journal-1.0.1/javadocs/)                                                                      | com.liferay.journal.api <br> com.liferay.journal.content.asset.addon.entry.api <br> com.liferay.journal.demo.data.creator.api <br> com.liferay.journal.item.selector.api <br> com.liferay.journal.taglib <br> com.liferay.journal.test.util                                                                                                                                                                                                                                                                   |
| [**Layout:**](https://docs.liferay.com/portal/7.1-latest/apps/layout-1.0.1/javadocs/)                                                                        | com.liferay.layout.api <br> com.liferay.layout.admin.api <br> com.liferay.layout.item.selector.api <br> com.liferay.layout.page.template.api <br> com.liferay.layout.prototype.api <br> com.liferay.layout.set.prototype.api <br> com.liferay.layout.taglib                                                                                                                                                                                                                                                   |
| [**Map:**](https://docs.liferay.com/portal/7.1-latest/apps/map-1.0.1/javadocs/)                                                                              | com.liferay.map.api <br> com.liferay.map.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [**Mentions:**](https://docs.liferay.com/portal/7.1-latest/apps/mentions-1.0.1/javadocs/)                                                                    | com.liferay.mentions.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [**Message Boards:**](https://docs.liferay.com/portal/7.1-latest/apps/message-boards-1.0.1/javadocs/)                                                        | com.liferay.message.boards.api <br> com.liferay.message.boards.demo.data.creator.api <br> com.liferay.message.boards.test.util                                                                                                                                                                                                                                                                                                                                                                                |
| [**Mobile Device Rules:**](https://docs.liferay.com/portal/7.1-latest/apps/mobile-device-rules-1.0.1/javadocs/)                                              | com.liferay.mobile.device.rules.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [**Organizations:**](https://docs.liferay.com/portal/7.1-latest/apps/organizations-1.0.1/javadocs/)                                                          | com.liferay.organizations.api <br> com.liferay.organizations.item.selector.api                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [**Password Policies Admin:**](https://docs.liferay.com/portal/7.1-latest/apps/password-policies-admin-1.0.1/javadocs/)                                      | com.liferay.password.policies.admin.constants                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [**Polls:**](https://docs.liferay.com/portal/7.1-latest/apps/polls-1.0.1/javadocs/)                                                                          | com.liferay.polls.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| [**Portal:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-1.0.1/javadocs/)                                                                        | com.liferay.portal.custom.jsp.bag.api <br> com.liferay.portal.dao.orm.custom.sql.api <br> com.liferay.portal.instance.lifecycle.api <br> com.liferay.portal.jmx.api <br> com.liferay.portal.output.stream.container.api <br> com.liferay.portal.spring.extender.api <br> com.liferay.portal.upgrade.api                                                                                                                                                                                                       |
| [**Portal Background Task:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-background-task-1.0.1/javadocs/)                                        | com.liferay.portal.background.task.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| [**Portal Cache:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-cache-1.0.1/javadocs/)                                                            | com.liferay.portal.cache.api <br> com.liferay.portal.cache.ehcache.spi <br> com.liferay.portal.cache.test.util                                                                                                                                                                                                                                                                                                                                                                                                |
| [**Portal Configuration:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-configuration-1.0.1/javadocs/)                                            | com.liferay.portal.configuration.test.util <br> com.liferay.portal.configuration.upgrade.api                                                                                                                                                                                                                                                                                                                                                                                                                  |
| [**Portal Instances:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-instances-1.0.1/javadocs/)                                                    | com.liferay.portal.instances.service                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [**Portal Lock:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-lock-1.0.1/javadocs/)                                                              | com.liferay.portal.lock.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| [**Portal Reports Engine:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-reports-engine-1.0.1/javadocs/)                                          | com.liferay.portal.reports.engine.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| [**Portal Remote:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-remote-1.0.1/javadocs/)                                                          | com.liferay.portal.remote.soap.extender                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [**Portal Rules:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-rules-engine-1.0.1/javadocs/com/liferay/portal/rules/engine/package-summary.html) | com.liferay.portal.rules.engine                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [**Portal Scripting:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-scripting-1.0.1/javadocs/)                                                    | com.liferay.portal.scripting                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| [**Portal Search:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-search-1.0.1/javadocs/)                                                          | com.liferay.portal.search.api <br> com.liferay.portal.search.engine.adapter.api <br> com.liferay.portal.search.spi <br> com.liferay.portal.search.test.util <br> com.liferay.portal.search.web.api                                                                                                                                                                                                                                                                                                            |
| [**Portal Security:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-security-1.0.1/javadocs/)                                                      | com.liferay.portal.security.exportimport.api <br> com.liferay.portal.security.ldap.api <br> com.liferay.portal.security.permission.api <br> com.liferay.portal.security.service.access.policy.api <br> com.liferay.portal.security.service.access.quota.api                                                                                                                                                                                                                                                   |
| [**Portal Security Audit:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-security-audit-1.0.1/javadocs/)                                          | com.liferay.portal.security.audit.api <br> com.liferay.portal.security.audit.event.generators.api <br> com.liferay.portal.security.audit.storage.api                                                                                                                                                                                                                                                                                                                                                          |
| [**Portal Security SSO:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-security-sso-1.0.1/javadocs/)                                              | com.liferay.portal.security.sso.cas.api <br> com.liferay.portal.security.sso.facebook.connect.api <br> com.liferay.portal.security.sso.google.api <br> com.liferay.portal.security.sso.ntlm.api <br> com.liferay.portal.security.sso.openid.api <br> com.liferay.portal.security.sso.openid.connect.api <br> com.liferay.portal.security.sso.opensso.api <br> com.liferay.portal.security.sso.token.api                                                                                                       |
| [**Portal Settings:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-settings-1.0.1/javadocs/)                                                      | com.liferay.portal.settings.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [**Portal Template:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-template-1.0.1/javadocs/)                                                      | com.liferay.portal.template.soy.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| [**Portal URL Builder:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-url-builder-1.0.1/javadocs/)                                                | com.liferay.portal.url.builder                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| [**Portal Workflow:**](https://docs.liferay.com/portal/7.1-latest/apps/portal-workflow-1.0.1/javadocs/)                                                      | com.liferay.portal.workflow.api <br> com.liferay.portal.workflow.kaleo.api <br> com.liferay.portal.workflow.kaleo.definition.api <br> com.liferay.portal.workflow.kaleo.runtime.api                                                                                                                                                                                                                                                                                                                           |
| [**Portlet Display Template:**](https://docs.liferay.com/portal/7.1-latest/apps/portlet-display-template-1.0.1/javadocs/)                                    | com.liferay.portlet.display.template.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [**Product Navigation:**](https://docs.liferay.com/portal/7.1-latest/apps/product-navigation-1.0.1/javadocs/)                                                | com.liferay.product.navigation.control.menu.api <br> com.liferay.product.navigation.product.menu.api <br> com.liferay.product.navigation.simulation.api <br> com.liferay.product.navigation.taglib                                                                                                                                                                                                                                                                                                            |
| [**Ratings:**](https://docs.liferay.com/portal/7.1-latest/apps/ratings-1.0.1/javadocs/)                                                                      | com.liferay.ratings.page.ratings.constants                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [**Reading Time:**](https://docs.liferay.com/portal/7.1-latest/apps/reading-time-1.0.1/javadocs/)                                                            | com.liferay.reading.time.api <br> com.liferay.reading.time.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| [**Roles:**](https://docs.liferay.com/portal/7.1-latest/apps/roles-1.0.1/javadocs/)                                                                          | com.liferay.roles.admin.api <br> com.liferay.roles.admin.demo.data.creator.api <br> com.liferay.roles.item.selector.api                                                                                                                                                                                                                                                                                                                                                                                       |
| [**RSS:**](https://docs.liferay.com/portal/7.1-latest/apps/rss-1.0.1/javadocs/)                                                                              | com.liferay.rss.api <br> com.liferay.rss.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [**Site:**](https://docs.liferay.com/portal/7.1-latest/apps/site-1.0.1/javadocs/)                                                                            | com.liferay.site.api <br> com.liferay.site.demo.data.creator.api <br> com.liferay.site.item.selector.api <br> com.liferay.site.taglib                                                                                                                                                                                                                                                                                                                                                                         |
| [**Social:**](https://docs.liferay.com/portal/7.1-latest/apps/social-1.0.1/javadocs/)                                                                        | com.liferay.social.activities.api <br> com.liferay.social.activities.taglib <br> com.liferay.social.activity.api <br> com.liferay.social.activity.test.util <br> com.liferay.social.bookmarks.api <br> com.liferay.social.bookmarks.taglib <br> com.liferay.social.user.statistics.api                                                                                                                                                                                                                        |
| [**Staging:**](https://docs.liferay.com/portal/7.1-latest/apps/staging-1.0.1/javadocs/)                                                                      | com.liferay.staging.api <br> com.liferay.staging.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| [**Subscription:**](https://docs.liferay.com/portal/7.1-latest/apps/subscription-1.0.1/javadocs/)                                                            | com.liferay.subscription.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| [**Text Localizer:**](https://docs.liferay.com/portal/7.1-latest/apps/text-localizer-1.0.1/javadocs/)                                                        | com.liferay.text.localizer.address.api <br> com.liferay.text.localizer.taglib                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [**Trash:**](https://docs.liferay.com/portal/7.1-latest/apps/trash-1.0.1/javadocs/)                                                                          | com.liferay.trash.api <br> com.liferay.trash.taglib <br> com.liferay.trash.test.util                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [**Upload:**](https://docs.liferay.com/portal/7.1-latest/apps/upload-1.0.1/javadocs/)                                                                        | com.liferay.upload                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| [**User Associated Data:**](https://docs.liferay.com/portal/7.1-latest/apps/user-associated-data-1.0.1/javadocs/)                                            | com.liferay.user.associated.data.api <br> com.liferay.user.associated.data.test.util                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [**User Groups Admin:**](https://docs.liferay.com/portal/7.1-latest/apps/user-groups-admin-1.0.1/javadocs/)                                                  | com.liferay.user.groups.admin.api <br> com.liferay.user.groups.admin.item.selector.api                                                                                                                                                                                                                                                                                                                                                                                                                        |
| [**Users Admin:**](https://docs.liferay.com/portal/7.1-latest/apps/users-admin-1.0.1/javadocs/)                                                              | com.liferay.users.admin.api <br> com.liferay.users.admin.demo.data.creator.api <br> com.liferay.users.admin.item.selector.api <br> com.liferay.users.admin.test.util                                                                                                                                                                                                                                                                                                                                          |
| [**Wiki:**](https://docs.liferay.com/portal/7.1-latest/apps/wiki-1.0.1/javadocs/)                                                                            | com.liferay.wiki.api                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| [**XStream:**](https://docs.liferay.com/portal/7.1-latest/apps/xstream-1.0.1/javadocs/)                                                                      | com.liferay.xstream.configurator                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |

<!-- TODO: Add back when this article is available:
For help finding API modules for specific common classes, see
[@product-ver@ API Modules](develop/reference/-/knowledge_base/7-1/finding-liferay-api-modules).
-->

For help finding module attributes and configuring dependencies, see
[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies).

## Taglibs

Here you'll find tag library documentation for the @product-ver@, @product@
apps, and Liferay Faces.

### @product-ver@ Taglibs

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
caption {
    text-align: left;
}
</style>

<table style="width:100%">
  <caption>
    <br>
  </caption>

  <tr>
    <th rowspan="9">
    <a href="@platform-ref@/7.1-latest/taglibs/util-taglib/">
    Util Taglibs<span class="opens-new-window-accessible"> (Opens New Window) </span>
    </a>
    </th>
    <td>
    JSTL core
    </td>
  </tr>
  <tr>
    <td>
    aui
    </td>
  </tr>
  <tr>
    <td>
    liferay-portlet
    </td>
  </tr>
  <tr>
    <td>
    portlet
    </td>
  </tr>
  <tr>
    <td>
    portlet_1_0
    </td>
  </tr>
  <tr>
    <td>
    liferay-security
    </td>
  </tr>
  <tr>
    <td>
    liferay-theme
    </td>
  </tr>
  <tr>
    <td>
    liferay-ui
    </td>
  </tr>
  <tr>
    <td>
    liferay-util
    </td>
  </tr>
</table>

### @product@ App Taglibs

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
caption {
    text-align: left;
}
</style>

<table style="width:100%">
  <caption>
    <br>
  </caption>

  <tr>
    <th rowspan="1">Adaptive Media:</th>
    <td>
       <a href="@app-ref@/adaptive-media/latest/taglibdocs/liferay-adaptive-media/tld-summary.html" target="_blank">
       liferay-application-list<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Application List:</th>
    <td>
       <a href="@app-ref@/web-experience/latest/taglibdocs/liferay-application-list/tld-summary.html" target="_blank">
       liferay-application-list<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="2">Assets:</th>
    <td>
	 <a href="@app-ref@/web-experience/latest/taglibdocs/liferay-asset/tld-summary.html" target="_blank">
        liferay-asset<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>
  <tr>
    <td>
	 <a href="@app-ref@/web-experience/latest/taglibdocs/liferay-trash/tld-summary.html" target="_blank">
        liferay-trash<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Forms:</th>
    <td>
       <a href="@app-ref@/forms-and-workflow/latest/taglibdocs/liferay-ddm/tld-summary.html" target="_blank">
	liferay-ddm<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>

  <tr>
    <th rowspan="2">Foundation:</th>
    <td>
	 <a href="@app-ref@/foundation/latest/taglibdocs/liferay-map/tld-summary.html" target="_blank">
        liferay-map<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>
  <tr>
    <td>
	<a href="@app-ref@/foundation/latest/taglibdocs/liferay-frontend/tld-summary.html" target="_blank">
       liferay-frontend<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Import, Export, &amp; Staging:</th>
    <td>
	 <a href="@app-ref@/web-experience/latest/taglibdocs/liferay-staging/tld-summary.html" target="_blank">
        liferay-staging<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Item Selector:</th>
    <td>
	 <a href="@app-ref@/collaboration/latest/taglibdocs/liferay-item-selector/tld-summary.html" target="_blank">
        liferay-item-selector<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Product Navigation:</th>
    <td>
	 <a href="@app-ref@/web-experience/latest/taglibdocs/liferay-product-navigation/tld-summary.html" target="_blank">
        liferay-product-navigation<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>

  <tr>
    <th rowspan="2">Sites:</th>
    <td>
	 <a href="@app-ref@/web-experience/latest/taglibdocs/liferay-layout/tld-summary.html" target="_blank">
        liferay-layout<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>
  <tr>
    <td>
	 <a href="@app-ref@/web-experience/latest/taglibdocs/liferay-site-navigation/tld-summary.html" target="_blank">
        liferay-site-navigation<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Social:</th>
    <td>
	 <a href="@app-ref@/collaboration/latest/taglibdocs/liferay-flags/tld-summary.html" target="_blank">
        liferay-flags<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
    </td>
  </tr>

</table>

<!-- TODO: Add additional TLD categories once they're finalized for 7.1. Still
in the works and have not all been properly published. -Cody -->

For help finding module attributes and configuring dependencies, see
[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies).

### Faces Taglibs

[**Faces 3.2 Taglibs**](https://docs.liferay.com/faces/3.2/vdldoc/): the latest
version of Liferay Faces JSF tag docs in View Declaration Language (VDL) format.
VDL docs for all versions of Liferay Faces are available
[here](http://docs.liferay.com/faces/).

## JavaScript and CSS

[**Lexicon**](https://lexicondesign.io/): A system for building applications in
and outside of @product@, designed to be fluid and extensible, as well as
provide a consistent and documented API.

[**Clay**](https://clayui.com/): The web implementation of Liferay's
[Lexicon Experience Language](https://lexicondesign.io/).

[**Bootstrap**](http://getbootstrap.com/): The base CSS library onto which
Lexicon is built. @product@ uses Bootstrap natively and all of its CSS classes
and JavaScript features are available within portlets, templates, and themes.

[**AlloyUI**](http://alloyui.com): Liferay includes AlloyUI and all of its
JavaScript APIs are available within portlets, templates and themes.

## Descriptor Definitions

[**DTDs**](@platform-ref@/7.1-latest/definitions/): Describes the XML
files used in configuring @product@ apps, @product@ plugins, and @product-ver@.
