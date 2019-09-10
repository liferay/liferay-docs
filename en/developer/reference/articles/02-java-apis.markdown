---
header-id: java-apis
---

# Java APIs

[TOC levels=1-4]

Here you'll find Javadoc for @product-ver@ and @product@ apps. Note that each 
link to the Javadoc listed here opens in a new window. 

For help finding module attributes and configuring dependencies, see 
[Configuring Dependencies](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies). 

## @product-ver@ Java APIs

This table contains links to the Javadoc for @product-ver@ API modules. The root 
location for these modules' Javadoc is 
[here](@platform-ref@/7.2-latest/javadocs/). 

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
</style>
<table style="width:100%">

  <tr>
    <th rowspan="5">Core</th>
    <td>
	<a href="@platform-ref@/7.2-latest/javadocs/portal-kernel/index.html" target="_blank">
	com.liferay.portal.kernel (portal-kernel):
	</a>&nbsp;for developing applications on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/util-bridges/index.html" target="_blank">
	com.liferay.util.bridges (util-bridges):
	</a>&nbsp;for using various non-proprietary computing languages, frameworks, and utilities
       on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/util-java/index.html" target="_blank">
	com.liferay.util.java (util-java):
	</a>&nbsp;for using various Java-related frameworks and utilities on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/util-slf4j/index.html" target="_blank">
	com.liferay.util.slf4j (util-slf4j):
	</a>&nbsp;for using the Simple Logging Facade for Java (SLF4J)
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/portal-impl/index.html" target="_blank">
	com.liferay.portal.impl (portal-impl):
	</a>&nbsp;refer to this only if you are an advanced Liferay developer that needs a deeper
       understanding of @product-ver@'s implementation in order to contribute to it
    </td>
  </tr>

</table>

## @product@ App Java APIs

The tables in this section link to the API modules for apps in these categories: 

-   [Collaboration](#collaboration)
-   [Forms and Workflow](#forms-and-workflow)
-   [Foundation](#foundation)
-   [Web Experience](#web-experience)

Note that the root location for these modules' Javadoc is 
[@app-ref@](@app-ref@). 

### Collaboration

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
</style>

<table style="width:100%">

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/announcements/" target="_blank">
      Announcements
      </a>
    </th>
    <td>
       com.liferay.announcements.api
      </td>
  </tr>

  <tr>
    <th rowspan="3">
      <a href="@app-ref@/blogs/" target="_blank">
      Blogs
      </a>
    </th>
    <td>
       com.liferay.blogs.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.blogs.item.selector.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.blogs.recent.bloggers.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/comment/" target="_blank">
      Comment
      </a>
    </th>
    <td>
       com.liferay.comment.api
      </td>
  </tr>

  <tr>
    <th rowspan="7">
      <a href="@app-ref@/document-library/" target="_blank">
      Document Library
      </a>
    </th>
    <td>
       com.liferay.document.library.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.document.library.content.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.document.library.file.rank.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.document.library.repository.authorization.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.document.library.repository.cmis.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.document.library.repository.external.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.document.library.sync.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/flags/" target="_blank">
      Flags
      </a>
    </th>
    <td>
       com.liferay.flags.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/invitation/" target="_blank">
      Invitation
      </a>
    </th>
    <td>
       com.liferay.invitation.invite.members.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/item-selector/" target="_blank">
      Item Selector
      </a>
    </th>
    <td>
       com.liferay.item.selector.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.item.selector.criteria.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/mentions/" target="_blank">
      Mentions
      </a>
    </th>
    <td>
       com.liferay.mentions.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/message-boards/" target="_blank">
      Message Boards
      </a>
    </th>
    <td>
       com.liferay.message.boards.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/ratings/" target="_blank">
      Ratings
      </a>
    </th>
    <td>
        com.liferay.ratings.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/reading-time/" target="_blank">
      Reading Time
      </a>
    </th>
    <td>
        com.liferay.reading.time.api
      </td>
  </tr>

  <tr>
    <th rowspan="4">
      <a href="@app-ref@/social/" target="_blank">
      Social
      </a>
    </th>
    <td>
        com.liferay.social.activities.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.social.activity.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.social.bookmarks.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.social.user.statistics.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/subscription/" target="_blank">
      Subscription
      </a>
    </th>
    <td>
        com.liferay.subscription.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/upload/" target="_blank">
      Upload
      </a>
    </th>
    <td>
         com.liferay.upload.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/wiki/" target="_blank">
      Wiki
      </a>
    </th>
    <td>
         com.liferay.wiki.api
      </td>
  </tr>

</table>

### Forms and Workflow

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
</style>

<table style="width:100%">

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/calendar/" target="_blank">
      Calendar
      </a>
    </th>
    <td>
         com.liferay.calendar.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/dynamic-data-lists/" target="_blank">
      Dynamic Data Lists
      </a>
    </th>
    <td>
         com.liferay.dynamic.data.lists.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/dynamic-data-mapping/" target="_blank">
      Dynamic Data Mapping
      </a>
    </th>
    <td>
         com.liferay.dynamic.data.mapping.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/polls/" target="_blank">
      Polls
      </a>
    </th>
    <td>
         com.liferay.polls.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-reports-engine/" target="_blank">
      Portal Reports Engine
      </a>
    </th>
    <td>
         com.liferay.portal.reports.engine.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-rules-engine/" target="_blank">
      Portal Rules Engine
      </a>
    </th>
    <td>
          com.liferay.portal.rules.engine.api
      </td>
  </tr>

  <tr>
    <th rowspan="4">
      <a href="@app-ref@/portal-workflow/" target="_blank">
      Portal Workflow
      </a>
    </th>
    <td>
          com.liferay.portal.workflow.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.workflow.kaleo.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.workflow.kaleo.definition.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.workflow.kaleo.runtime.api
      </td>
  </tr>

</table>

### Foundation

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
</style>

<table style="width:100%">

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/captcha/" target="_blank">
      Captcha
      </a>
    </th>
    <td>
          com.liferay.captcha.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/configuration-admin/" target="_blank">
      Configuration Admin
      </a>
    </th>
    <td>
          com.liferay.configuration.admin.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/contacts/" target="_blank">
      Contacts
      </a>
    </th>
    <td>
          com.liferay.contacts.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/friendly-url/" target="_blank">
      Friendly URL
      </a>
    </th>
    <td>
          com.liferay.friendly.url.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/frontend-editor/" target="_blank">
      Frontend Editor
      </a>
    </th>
    <td>
          com.liferay.frontend.editor.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/frontend-image-editor/" target="_blank">
      Frontend Image Editor
      </a>
    </th>
    <td>
           com.liferay.frontend.image.editor.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/frontend-js/" target="_blank">
      Frontend JS
      </a>
    </th>
    <td>
            com.liferay.frontend.js.loader.modules.extender.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/map/" target="_blank">
      Map
      </a>
    </th>
    <td>
             com.liferay.map.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/mobile-device-rules/" target="_blank">
      Mobile Device Rules
      </a>
    </th>
    <td>
             com.liferay.mobile.device.rules.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/organizations/" target="_blank">
      Organizations
      </a>
    </th>
    <td>
             com.liferay.organizations.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.organizations.item.selector.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/password-policies-admin/" target="_blank">
      Password Policies Admin
      </a>
    </th>
    <td>
             com.liferay.password.policies.admin.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-background-task/" target="_blank">
      Portal Background Task
      </a>
    </th>
    <td>
             com.liferay.portal.background.task.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-cache/" target="_blank">
      Portal Cache
      </a>
    </th>
    <td>
             com.liferay.portal.cache.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-configuration/" target="_blank">
      Portal Configuration
      </a>
    </th>
    <td>
             com.liferay.portal.configuration.upgrade.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-instances/" target="_blank">
      Portal Instances
      </a>
    </th>
    <td>
             com.liferay.portal.instances.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-lock/" target="_blank">
      Portal Lock
      </a>
    </th>
    <td>
             com.liferay.portal.lock.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-remote/" target="_blank">
      Portal Remote
      </a>
    </th>
    <td>
             com.liferay.portal.remote.soap.extender.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-scripting/" target="_blank">
      Portal Scripting
      </a>
    </th>
    <td>
             com.liferay.portal.scripting.api
      </td>
  </tr>

  <tr>
    <th rowspan="3">
      <a href="@app-ref@/portal-search/" target="_blank">
      Portal Search
      </a>
    </th>
    <td>
             com.liferay.portal.search.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.search.engine.adapter.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.search.web.api
      </td>
  </tr>

  <tr>
    <th rowspan="3">
      <a href="@app-ref@/portal-security-audit/" target="_blank">
      Portal Security Audit
      </a>
    </th>
    <td>
             com.liferay.portal.security.audit.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.audit.event.generators.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.audit.storage.api
      </td>
  </tr>

  <tr>
    <th rowspan="7">
      <a href="@app-ref@/portal-security-sso/" target="_blank">
      Portal Security SSO
      </a>
    </th>
    <td>
             com.liferay.portal.security.sso.cas.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.sso.facebook.connect.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.sso.ntlm.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.sso.openid.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.sso.openid.connect.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.sso.opensso.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.sso.token.api
      </td>
  </tr>

  <tr>
    <th rowspan="5">
      <a href="@app-ref@/portal-security/" target="_blank">
      Portal Security
      </a>
    </th>
    <td>
             com.liferay.portal.security.exportimport.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.ldap.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.permission.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.service.access.policy.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.service.access.quota.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-security-sso-google/" target="_blank">
      Portal Security SSO Google
      </a>
    </th>
    <td>
             com.liferay.portal.security.sso.google.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-settings/" target="_blank">
      Portal Settings
      </a>
    </th>
    <td>
             com.liferay.portal.settings.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-template/" target="_blank">
      Portal Template
      </a>
    </th>
    <td>
             com.liferay.portal.template.soy.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-url-builder/" target="_blank">
      Portal URL Builder
      </a>
    </th>
    <td>
             com.liferay.portal.url.builder.api
      </td>
  </tr>

  <tr>
    <th rowspan="7">
      <a href="@app-ref@/portal/" target="_blank">
      Portal
      </a>
    </th>
    <td>
             com.liferay.portal.custom.jsp.bag.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.dao.orm.custom.sql.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.instance.lifecycle.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.jmx.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.output.stream.container.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.spring.extender.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.upgrade.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/roles/" target="_blank">
      Roles
      </a>
    </th>
    <td>
             com.liferay.roles.admin.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.roles.item.selector.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/text-localizer/" target="_blank">
      Text Localizer
      </a>
    </th>
    <td>
             com.liferay.text.localizer.address.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/user-associated-data/" target="_blank">
      User-associated Data
      </a>
    </th>
    <td>
             com.liferay.user.associated.data.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/user-groups-admin/" target="_blank">
      User Groups Admin
      </a>
    </th>
    <td>
             com.liferay.user.groups.admin.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.user.groups.admin.item.selector.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/users-admin/" target="_blank">
      Users Admin
      </a>
    </th>
    <td>
             com.liferay.users.admin.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.users.admin.item.selector.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/xstream/" target="_blank">
      XStream
      </a>
    </th>
    <td>
             com.liferay.xstream.configurator.api
      </td>
  </tr>

</table>

### Web Experience

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
</style>

<table style="width:100%">

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/application-list/" target="_blank">
      Application List
      </a>
    </th>
    <td>
             com.liferay.application.list.api
      </td>
  </tr>

  <tr>
    <th rowspan="11">
      <a href="@app-ref@/asset/" target="_blank">
      Asset
      </a>
    </th>
    <td>
             com.liferay.asset.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.categories.navigation.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.category.property.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.display.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.display.page.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.display.page.item.selector.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.entry.rel.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.publisher.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.tag.stats.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.tags.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.asset.tags.navigation.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/export-import/" target="_blank">
      Export Import
      </a>
    </th>
    <td>
             com.liferay.exportimport.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.exportimport.changeset.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/fragment/" target="_blank">
      Fragment
      </a>
    </th>
    <td>
             com.liferay.fragment.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.fragment.item.selector.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/html-preview/" target="_blank">
      HTML Preview
      </a>
    </th>
    <td>
             com.liferay.html.preview.api
      </td>
  </tr>

  <tr>
    <th rowspan="3">
      <a href="@app-ref@/journal/" target="_blank">
      Journal
      </a>
    </th>
    <td>
             com.liferay.journal.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.journal.content.asset.addon.entry.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.journal.item.selector.api
      </td>
  </tr>

  <tr>
    <th rowspan="6">
      <a href="@app-ref@/layout/" target="_blank">
      Layout
      </a>
    </th>
    <td>
             com.liferay.layout.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.layout.admin.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.layout.item.selector.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.layout.page.template.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.layout.prototype.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.layout.set.prototype.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portlet-display-template/" target="_blank">
      Portlet Display Template
      </a>
    </th>
    <td>
             com.liferay.portlet.display.template.api
      </td>
  </tr>

  <tr>
    <th rowspan="3">
      <a href="@app-ref@/product-navigation/" target="_blank">
      Product Navigation
      </a>
    </th>
    <td>
             com.liferay.product.navigation.control.menu.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.product.navigation.product.menu.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.product.navigation.simulation.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/rss/" target="_blank">
      RSS
      </a>
    </th>
    <td>
             com.liferay.rss.api
      </td>
  </tr>

  <tr>
    <th rowspan="4">
      <a href="@app-ref@/site-navigation/" target="_blank">
      Site Navigation
      </a>
    </th>
    <td>
             com.liferay.site.navigation.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.site.navigation.admin.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.site.navigation.item.selector.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.site.navigation.language.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/site/" target="_blank">
      Site
      </a>
    </th>
    <td>
             com.liferay.site.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.site.item.selector.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/staging/" target="_blank">
      Staging
      </a>
    </th>
    <td>
             com.liferay.staging.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/trash/" target="_blank">
      Trash
      </a>
    </th>
    <td>
             com.liferay.trash.api
      </td>
  </tr>

</table>

## JavaScript and CSS

[**Lexicon**](https://liferay.design/lexicon/): A system for building 
applications in and outside of @product@, designed to be fluid and extensible, 
as well as provide a consistent and documented API. 

[**Clay**](https://clayui.com/): The web implementation of Lexicon. 

[**Bootstrap**](http://getbootstrap.com/): The base CSS library onto which 
Lexicon is built. @product@ uses Bootstrap natively and all of its CSS classes 
and JavaScript features are available within portlets, templates, and themes. 

[**AlloyUI**](http://alloyui.com): AlloyUI and all of its JavaScript APIs are 
available within portlets, templates, and themes. 

## Descriptor Definitions

[**DTDs**](@platform-ref@/7.2-latest/definitions/): Describes the XML files used 
in configuring @product@ apps, @product@ plugins, and @product-ver@. 
