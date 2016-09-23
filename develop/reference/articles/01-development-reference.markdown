# Development Reference [](id=development-reference)

Here you'll find reference documentation for Liferay Portal CE, Liferay Screens,
Liferay Faces, and technologies related to you as a third-party developer.

The different types of reference docs you'll find in this section of the Liferay
Developer Network are as follows:

- Descriptions of Java and JavaScript APIs, CSS, tags and tag libraries, and
XML DTDs
- Write ups on the latest Screenlets for Liferay Screens
- Breaking changes
- Cheat sheets and tips on
    - Plugin anatomy
    - Design patterns
    - Tools
    - Adapting to new APIs

Liferay's reference docs are your fingertips.

## Java APIs [](id=java-apis)

Here you'll find Javadoc for Liferay Portal 7.0 CE modules at the time of the
latest GA release.

### CE Java APIs [](id=ce-java-apis)

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
    This table links you to some commonly used Liferay API modules. Although the
    <a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/" target="_blank">
    Liferay artifact repository<span class="opens-new-window-accessible"> (Opens New Window) </span>
    </a>
    contains <em>all</em> of the modules, the convenient listing below gives you
    quicker access to some of the most popular module APIs.
    <ul>
       <li>
          <em>(HTML)</em>: for online Javadoc. As an alternative, you can
          <a href="https://www.liferay.com/downloads" target="_blank">
          download<span class="opens-new-window-accessible"> (Opens New Window) </span>
          </a>
          the reference doc ZIP file and browse locally.
       </li>
       <li>
		<em>(JARs)</em>: for accessing all the Javadoc JAR files for
		<em>all</em> the module's versions.
       </li>
    </ul>
    <br>
  </caption>

  <tr>
    <th rowspan="5">Core:</th>
    <td>
       portal-kernel&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/index.html" target="_blank">
	(HTML):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a> for developing applications on the Liferay platform
    </td>
  </tr>

  <tr>
    <td>
       util-bridges&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/javadocs/util-bridges/index.html" target="_blank">
	(HTML):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a> for using various non-proprietary computing languages, frameworks, and utilities
       on the Liferay platform
    </td>
  </tr>

  <tr>
    <td>
       util-java&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/javadocs/util-java/index.html" target="_blank">
	(HTML):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a> for using various Java-related frameworks and utilities on the Liferay platform
    </td>
  </tr>

  <tr>
    <td>
       util-slf4j&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/javadocs/util-slf4j/index.html" target="_blank">
	(HTML):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a> for using the Simple Logging Facade for Java (SLF4J)
    </td>
  </tr>

  <tr>
    <td>
       portal-impl&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/javadocs/portal-impl/index.html" target="_blank">
	(HTML):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a> refer to this only if you are an advanced Liferay developer that needs a deeper
       understanding of Liferay Portal's implementation in order to contribute to it
    </td>
  </tr>

  <tr>
    <th rowspan="2">Application Configuration:</th>
    <td>
       com.liferay.portlet.configuration.icon.locator.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portlet.configuration.icon.locator.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portlet.configuration.toolbar.contributor.locator.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portlet.configuration.toolbar.contributor.locator.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Application List:</th>
    <td>
       com.liferay.application.list.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.application.list.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="3">Backend Frameworks:</th>
    <td>
       com.liferay.portal.background.task.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.background.task.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.lock.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.lock.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.settings.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.settings.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Calendar:</th>
    <td>
       com.liferay.calendar.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.calendar.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="7">Collaboration:</th>
    <td>
       com.liferay.blogs.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.blogs.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.bookmarks.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.bookmarks.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.document.library.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.document.library.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.mentions.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.mentions.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.microblogs.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.microblogs.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.message.boards.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.message.boards.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.wiki.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.wiki.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="4">Export, Import, &amp; Staging:</th>
    <td>
       com.liferay.exportimport.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.exportimport.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.dynamic.data.mapping.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.dynamic.data.mapping.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.staging.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.staging.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.xstream.configurator.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.xstream.configurator.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="4">Forms &amp; Workflow:</th>
    <td>
       com.liferay.dynamic.data.lists.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.dynamic.data.lists.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.dynamic.data.mapping.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.dynamic.data.mapping.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.polls.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.polls.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       reports.engine.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/reports.engine.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="2">Item Selector:</th>
    <td>
       com.liferay.item.selector.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.item.selector.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
        com.liferay.item.selector.criteria.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.item.selector.criteria.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Map:</th>
    <td>
       com.liferay.map.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.map.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Marketplace:</th>
    <td>
       com.liferay.marketplace.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.marketplace.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="2">Mobile &amp; Screens:</th>
    <td>
       com.liferay.mobile.device.rules.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.mobile.device.rules.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.screens.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.screens.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Product Navigation:</th>
    <td>
       com.liferay.product.navigation.control.menu.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.product.navigation.control.menu.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="3">Security:</th>
    <td>
       com.liferay.portal.security.service.access.policy.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.security.service.access.policy.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.audit.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.security.audit.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.security.exportimport.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.security.exportimport.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Shopping Cart (deprecated):</th>
    <td>
       com.liferay.shopping.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.shopping.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Sites:</th>
    <td>
       com.liferay.site.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.site.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="7">Social:</th>
    <td>
       com.liferay.comment.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.comment.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.contacts.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.contacts.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.flags.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.flags.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.invitation.invite.members.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.invitation.invite.members.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.social.privatemessaging.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.privatemessaging.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.ratings.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.ratings.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.social.networking.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.networking.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Web Experience:</th>
    <td>
       com.liferay.journal.api&nbsp;
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.journal.api/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

</table>

For help finding specific Liferay API modules, see [Liferay API
Modules](/develop/reference/-/knowledge_base/7-0/finding-liferay-api-modules).

<!--
Module APIs (Javadoc JARs): Javadoc for *all* versions of CE modules are
available in JARs here.
-->

## Taglibs [](id=taglibs)

Here you'll find tag library documentation for Liferay Portal 7.0 Community Edition
(CE) and Liferay Faces.

### CE Taglibs [](id=ce-taglibs)

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
    <ul>
       <li>
		<em>(HTML)</em>: for an online description the tag library used in the
		latest GA release of Liferay Portal 7.0 CE. As an alternative to online
		taglib docs, you can
		<a href="https://www.liferay.com/downloads" target="_blank">
		download<span class="opens-new-window-accessible"> (Opens New Window) </span>
		</a>
		the reference doc ZIP file and browse locally.
       </li>
       <li>
		<em>(JARs)</em>: for accessing all the taglib doc JAR files for
		<em>all</em> the module's versions.
       </li>
    </ul>
    <br>
  </caption>

  <tr>
    <th rowspan="1">Application List:</th>
    <td>
       liferay-application-list&nbsp;
       <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/web-experience/application-list/com.liferay.application.list.taglib/liferay-application-list/tld-summary.html" target="_blank">
       (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
       <a
       href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.application.list.taglib/" target="_blank">
       (JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
       </a>
      </td>
  </tr>

  <tr>
    <th rowspan="1">Application Security:</th>
    <td>
       liferay-security&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/util-taglib/liferay-security/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>

  <tr>
    <th rowspan="2">Assets:</th>
    <td>
	liferay-asset&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/web-experience/asset/com.liferay.asset.taglib/liferay-asset/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.asset.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
	liferay-trash&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/web-experience/trash/com.liferay.trash.taglib/liferay-trash/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.trash.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Forms:</th>
    <td>
       liferay-ddm&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/forms-and-workflow/dynamic-data-mapping/com.liferay.dynamic.data.mapping.taglib/liferay-ddm/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.dynamic.data.mapping.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </td>
  </tr>

  <tr>
    <th rowspan="8">Frontend Building Blocks:</th>
    <td>
	aui&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/util-taglib/aui/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
	<a
	href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.alloy.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
	liferay-map&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/foundation/map/com.liferay.map.taglib/liferay-map/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.map.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
	liferay-frontend&nbsp;
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.frontend.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
       liferay-portlet&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/util-taglib/liferay-portlet/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
       liferay-theme&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/util-taglib/liferay-theme/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
       liferay-ui&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/util-taglib/liferay-ui/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
       liferay-util&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/util-taglib/liferay-util/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>
  <tr>
    <td>
       portlet&nbsp;
	<a href="https://docs.liferay.com/portal/7.0/taglibs/util-taglib/portlet/tld-summary.html" target="_blank">
	(HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Import, Export, &amp; Staging:</th>
    <td>
	liferay-staging&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/web-experience/staging/com.liferay.staging.taglib/liferay-staging/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.staging.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Item Selector:</th>
    <td>
	liferay-item-selector&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/collaboration/item-selector/com.liferay.item.selector.taglib/liferay-item-selector/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.item.selector.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Product Navigation:</th>
    <td>
	liferay-product-navigation&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/web-experience/product-navigation/com.liferay.product.navigation.taglib/liferay-product-navigation/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.product.navigation.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </td>
  </tr>

  <tr>
    <th rowspan="2">Sites:</th>
    <td>
	liferay-layout&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/web-experience/layout/com.liferay.layout.taglib/liferay-layout/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.layout.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </td>
  </tr>
  <tr>
    <td>
	liferay-site-navigation&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/web-experience/site-navigation/com.liferay.site.navigation.taglib/liferay-site-navigation/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.site.navigation.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </td>
  </tr>

  <tr>
    <th rowspan="1">Social:</th>
    <td>
	liferay-flags&nbsp;
        <a href="https://docs.liferay.com/portal/7.0/taglibs/modules/apps/collaboration/flags/com.liferay.flags.taglib/liferay-flags/tld-summary.html" target="_blank">
        (HTML)<span class="opens-new-window-accessible"> (Opens New Window) </span>
        </a>
	<a href="https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.flags.taglib/" target="_blank">
	(JARs)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </td>
  </tr>

</table>

### Faces Taglibs [](id=faces-taglibs)

[**Faces 3.2 Taglibs**](https://docs.liferay.com/faces/3.2/vdldoc/): the latest
version of Liferay Faces JSF tag docs in View Declaration Language (VDL) format.
VDL docs for all versions of Liferay Faces are available
[here](http://docs.liferay.com/faces/).

## JavaScript and CSS [](id=javascript-and-css)

[**Lexicon**](http://liferay.github.io/lexicon/): The Liferay Experience
Language is a system for building applications in and outside of Liferay,
designed to be fluid and extensible, as well as provide a consistent and
documented API.

[**Bootstrap**](http://getbootstrap.com/): The base CSS library onto which
Lexicon is added. Liferay uses Bootstrap natively and all of its CSS classes are
available within portlets, templates, and themes.

[**AlloyUI**](http://alloyui.com): Liferay includes AlloyUI and all of its
JavaScript APIs are available within portlets, templates and themes.

## Descriptor Definitions [](id=descriptor-definitions)

[**DTDs**](http://docs.liferay.com/portal/7.0/definitions/): Describes the XML
files used in configuring Liferay apps, Liferay plugins, and Liferay Portal CE.
