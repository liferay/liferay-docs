# Development Reference [](id=development-reference)

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

## Java APIs [](id=java-apis)

Here you'll find Javadoc for @product-ver@ and @product@ apps.

### @product-ver@ Java APIs [](id=liferay-portal-core-java-apis)

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

### @product@ App Java APIs [](id=liferay-portal-app-java-apis)

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
    This table links you to @product@ application APIs. Their root location is 
    <a href="@app-ref@" target="_blank">
    here.<span class="opens-new-window-accessible"> (Opens New Window) </span>
    </a>
    <br>
  </caption>

  <tr>
    <th rowspan="29">
      <a href="@app-ref@/collaboration/latest/javadocs/" target="_blank">
      Collaboration<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/collaboration/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.announcements.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.blogs.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.blogs.demo.data.creator.api
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
    <td>
       com.liferay.bookmarks.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.comment.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.comment.demo.data.creator.api
      </td>
  </tr>
  <tr>
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
    com.liferay.document.library.repository.cmis.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.document.library.sync.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.flags.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.invitation.invite.members.api
      </td>
  </tr>
  <tr>
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
    <td>
    com.liferay.mentions.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.message.boards.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.microblogs.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.ratings.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.reading.time.api
      </td>
  </tr>
  <tr>
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
    <td>
    com.liferay.subscription.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.upload.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.wiki.api
      </td>
  </tr>

  <tr>
    <th rowspan="10">
      <a href="@app-ref@/forms-and-workflow/latest/javadocs/" target="_blank">
      Forms &amp; Workflow<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/forms-and-workflow/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.calendar.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.dynamic.data.lists.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.dynamic.data.mapping.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.polls.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.portal.reports.engine.api
      </td>
  </tr>
    <td>
       com.liferay.portal.rules.engine.api
      </td>
  </tr>
  <tr>
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

  <tr>
    <th rowspan="56">
      <a href="@app-ref@/foundation/latest/javadocs/" target="_blank">
      Foundation<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/foundation/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.apio.architect.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.apio.architect.wiring.osgi.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.apio.architect.writer.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.captcha.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.configuration.admin.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.contacts.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.friendly.url.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.frontend.editor.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.frontend.image.editor.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.frontend.js.loader.modules.extender.api
      </td>
  </tr>
  <tr>
    <td>
    com.liferay.map.api
    </td>
    </tr>
  </tr>
  <tr>
    <td>
    com.liferay.mobile.device.rules.api
    </td>
    </tr>
    <tr>
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
    <td>
    com.liferay.password.policies.admin.api
    </td>
    </tr>
  <tr>
    <td>
    com.liferay.portal.background.task.api
    </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.cache.api
        </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.configuration.upgrade.api
        </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.instances.api
        </td>
    </tr>
  <tr>
    <td>
    com.liferay.portal.lock.api
    </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.remote.soap.extender.api
        </td>
    </tr>
  <tr>
    <td>
    com.liferay.portal.scripting.api
    </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.search.api
        </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.search.web.api
        </td>
    </tr>
  <tr>
    <td>
    com.liferay.portal.security.audit.api
    </td>
    </tr>
    <tr>
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
      com.liferay.portal.security.sso.google.api
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
      com.liferay.portal.security.service.access.policy.api
      </td>
      </tr>
    <tr>
      <td>
      com.liferay.portal.settings.api
        </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.template.soy.api
        </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.url.builder.api
        </td>
    </tr>
    <tr>
      <td>
      com.liferay.portal.custom.jsp.bag.api
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
      <td>
      com.liferay.roles.admin.api
        </td>
    </tr>
    <tr>   
      <td>
      com.liferay.roles.admin.demo.data.creator.api
        </td>
    </tr>
    <tr>   
        <td>
        com.liferay.roles.item.selector.api
        </td>
    </tr>
    <tr>
      <td>
      com.liferay.text.localizer.address.api
      </td>
    </tr>
    <tr>
      <td>
      com.liferay.user.associated.data.api
      </td>
    </tr>
  <tr>
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
    <td>
    com.liferay.users.admin.api
    </td>
    </tr>
  <tr>
    <td>
    com.liferay.users.admin.demo.data.creator.api
    </td>
    </tr>
    <tr>
      <td>
      com.liferay.users.admin.item.selector.api
      </td>
    </tr>
  <tr>
    <td>
    com.liferay.xstream.configurator.api
    </td>
  </tr>

  <tr>
    <th rowspan="40">
      <a href="@app-ref@/web-experience/latest/javadocs/" target="_blank">
      Web Experience<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/web-experience/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
    com.liferay.application.list.api
    </td>
  </tr>
  <tr>
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
    com.liferay.asset.display.template.api
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
  </tr>
  <tr>
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
    <td>
    com.liferay.fragment.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.fragment.demo.data.creator.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.fragment.item.selector.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.html.preview.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.journal.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.journal.demo.data.creator.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.journal.item.selector.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.layout.admin.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.layout.api
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
    <td>
    com.liferay.portlet.display.template.api
    </td>
  </tr>
  <tr>
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
    <td>
    com.liferay.rss.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.site.navigation.admin.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.site.navigation.api
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
    <td>
    com.liferay.site.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.site.demo.data.creator.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.site.item.selector.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.staging.api
    </td>
  </tr>
  <tr>
    <td>
    com.liferay.trash.api
    </td>
  </tr>
       
</table>

For help finding API modules for specific common classes, see
[@product-ver@ API Modules](/develop/reference/-/knowledge_base/7-1/finding-liferay-api-modules).

For help finding module attributes and configuring dependencies, see 
[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies). 

## Taglibs [](id=taglibs)

Here you'll find tag library documentation for the @product-ver@, @product@
apps, and Liferay Faces. 

### @product-ver@ Taglibs [](id=liferay-portal-core-taglibs)

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

### @product@ App Taglibs [](id=liferay-portal-app-taglibs)

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
[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies). 

### Faces Taglibs [](id=faces-taglibs)

[**Faces 3.2 Taglibs**](https://docs.liferay.com/faces/3.2/vdldoc/): the latest
version of Liferay Faces JSF tag docs in View Declaration Language (VDL) format.
VDL docs for all versions of Liferay Faces are available
[here](http://docs.liferay.com/faces/).

## JavaScript and CSS [](id=javascript-and-css)

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

## Descriptor Definitions [](id=descriptor-definitions)

[**DTDs**](@platform-ref@/7.1-latest/definitions/): Describes the XML
files used in configuring @product@ apps, @product@ plugins, and @product-ver@.
