# Java APIs

Here you'll find Javadoc for @product-ver@ and @product@ apps. 

## @product-ver@ Java APIs

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
    <a href="@platform-ref@/7.2-latest/javadocs/" target="_blank">
    here.<span class="opens-new-window-accessible"> (Opens New Window) </span>
    </a> The 
    reference doc Zip is available  
    <a href="@platform-ref@/7.2-latest/" target="_blank">
    here.<span class="opens-new-window-accessible"> (Opens New Window) </span>
    </a>
    <br>
  </caption>

  <tr>
    <th rowspan="5">Core:</th>
    <td>
	<a href="@platform-ref@/7.2-latest/javadocs/portal-kernel/index.html" target="_blank">
	com.liferay.portal.kernel (portal-kernel):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for developing applications on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/util-bridges/index.html" target="_blank">
	com.liferay.util.bridges (util-bridges):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for using various non-proprietary computing languages, frameworks, and utilities
       on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/util-java/index.html" target="_blank">
	com.liferay.util.java (util-java):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for using various Java-related frameworks and utilities on @product@
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/util-slf4j/index.html" target="_blank">
	com.liferay.util.slf4j (util-slf4j):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;for using the Simple Logging Facade for Java (SLF4J)
    </td>
  </tr>

  <tr>
    <td>
       <a href="@platform-ref@/7.2-latest/javadocs/portal-impl/index.html" target="_blank">
	com.liferay.portal.impl (portal-impl):<span class="opens-new-window-accessible"> (Opens New Window) </span>
	</a>&nbsp;refer to this only if you are an advanced Liferay developer that needs a deeper
       understanding of @product-ver@'s implementation in order to contribute to it
    </td>
  </tr>

</table>

## @product@ App Java APIs

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

<!-- Collab -->
  <tr>
    <th rowspan="1">
      <a href="@app-ref@/announcements/latest/javadocs/" target="_blank">
      Announcements<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/announcements/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.announcements.api
      </td>
  </tr>

  <tr>
    <th rowspan="4">
      <a href="@app-ref@/blogs/latest/javadocs/" target="_blank">
      Blogs<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/blogs/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
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
    <th rowspan="2">
      <a href="@app-ref@/comment/latest/javadocs/" target="_blank">
      Comment<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/comment/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
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
    <th rowspan="8">
      <a href="@app-ref@/document-library/latest/javadocs/" target="_blank">
      Document Library<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/document-library/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
       com.liferay.document.library.demo.data.creator.api
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
      <a href="@app-ref@/flags/latest/javadocs/" target="_blank">
      Flags<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/flags/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.flags.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/invitation/latest/javadocs/" target="_blank">
      Invitation<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/invitation/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.invitation.invite.members.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/item-selector/latest/javadocs/" target="_blank">
      Item Selector<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/item-selector/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      <a href="@app-ref@/mentions/latest/javadocs/" target="_blank">
      Mentions<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/mentions/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.mentions.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/message-boards/latest/javadocs/" target="_blank">
      Message Boards<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/message-boards/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
       com.liferay.message.boards.api
      </td>
  </tr>
  <tr>
    <td>
       com.liferay.message.boards.demo.data.creator.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/ratings/latest/javadocs/" target="_blank">
      Ratings<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/ratings/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
        com.liferay.ratings.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/reading-time/latest/javadocs/" target="_blank">
      Reading Time<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/reading-time/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
        com.liferay.reading.time.api
      </td>
  </tr>

  <tr>
    <th rowspan="4">
      <a href="@app-ref@/social/latest/javadocs/" target="_blank">
      Social<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/social/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      <a href="@app-ref@/subscription/latest/javadocs/" target="_blank">
      Subscription<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/subscription/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
        com.liferay.subscription.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/upload/latest/javadocs/" target="_blank">
      Upload<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/upload/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
         com.liferay.upload.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/wiki/latest/javadocs/" target="_blank">
      Wiki<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/wiki/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
         com.liferay.wiki.api
      </td>
  </tr>

<!-- Forms & Workflow -->
  <tr>
    <th rowspan="1">
      <a href="@app-ref@/calendar/latest/javadocs/" target="_blank">
      Calendar<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/calendar/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
         com.liferay.calendar.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/dynamic-data-lists/latest/javadocs/" target="_blank">
      Dynamic Data Lists<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/dynamic-data-lists/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
         com.liferay.dynamic.data.lists.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/dynamic-data-mapping/latest/javadocs/" target="_blank">
      Dynamic Data Mapping<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/dynamic-data-mapping/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
         com.liferay.dynamic.data.mapping.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/polls/latest/javadocs/" target="_blank">
      Polls<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/polls/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
         com.liferay.polls.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-reports-engine/latest/javadocs/" target="_blank">
      Portal Reports Engine<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-reports-engine/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
         com.liferay.portal.reports.engine.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-rules-engine/latest/javadocs/" target="_blank">
      Portal Rules Engine<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-rules-engine/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
          com.liferay.portal.rules.engine.api
      </td>
  </tr>

  <tr>
    <th rowspan="4">
      <a href="@app-ref@/portal-workflow/latest/javadocs/" target="_blank">
      Portal Workflow<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-workflow/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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

<!-- Foundation -->
  <tr>
    <th rowspan="1">
      <a href="@app-ref@/captcha/latest/javadocs/" target="_blank">
      Captcha<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/captcha/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
          com.liferay.captcha.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/configuration-admin/latest/javadocs/" target="_blank">
      Configuration Admin<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/configuration-admin/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
          com.liferay.configuration.admin.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/contacts/latest/javadocs/" target="_blank">
      Contacts<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/contacts/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
          com.liferay.contacts.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/friendly-url/latest/javadocs/" target="_blank">
      Friendly URL<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/friendly-url/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
          com.liferay.friendly.url.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/frontend-editor/latest/javadocs/" target="_blank">
      Frontend Editor<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/frontend-editor/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
          com.liferay.frontend.editor.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/frontend-image-editor/latest/javadocs/" target="_blank">
      Frontend Image Editor<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/frontend-image-editor/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
           com.liferay.frontend.image.editor.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/frontend-js/latest/javadocs/" target="_blank">
      Frontend JS<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/frontend-js/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
            com.liferay.frontend.js.loader.modules.extender.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/map/latest/javadocs/" target="_blank">
      Map<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/map/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.map.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/mobile-device-rules/latest/javadocs/" target="_blank">
      Mobile Device Rules<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/mobile-device-rules/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.mobile.device.rules.api
      </td>
  </tr>

  <tr>
    <th rowspan="2">
      <a href="@app-ref@/organizations/latest/javadocs/" target="_blank">
      Organizations<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/organizations/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      <a href="@app-ref@/password-policies-admin/latest/javadocs/" target="_blank">
      Password Policies Admin<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/password-policies-admin/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.password.policies.admin.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-cache/latest/javadocs/" target="_blank">
      Portal Cache<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-cache/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.cache.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-configuration/latest/javadocs/" target="_blank">
      Portal Configuration<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-configuration/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.configuration.upgrade.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-instances/latest/javadocs/" target="_blank">
      Portal Instances<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-instances/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.instances.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-lock/latest/javadocs/" target="_blank">
      Portal Lock<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-lock/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.lock.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-remote/latest/javadocs/" target="_blank">
      Portal Remote<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-remote/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.remote.soap.extender.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-scripting/latest/javadocs/" target="_blank">
      Portal Scripting<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-scripting/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.scripting.api
      </td>
  </tr>

  <tr>
    <th rowspan="3">
      <a href="@app-ref@/portal-search/latest/javadocs/" target="_blank">
      Portal Search<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-search/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      <a href="@app-ref@/portal-security-audit/latest/javadocs/" target="_blank">
      Portal Security Audit<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-security-audit/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      <a href="@app-ref@/portal-security-sso/latest/javadocs/" target="_blank">
      Portal Security SSO<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-security-sso/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      <a href="@app-ref@/portal-security/latest/javadocs/" target="_blank">
      Portal Security<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-security/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      <a href="@app-ref@/portal-security-sso-google/latest/javadocs/" target="_blank">
      Portal Security SSO Google<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-security-sso-google/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.security.sso.google.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-settings/latest/javadocs/" target="_blank">
      Portal Settings<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
      <br>
      <a href="@app-ref@/portal-settings/latest/" target="_blank">
       (JAR)<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.settings.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-template/" target="_blank">
      Portal Template<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.template.soy.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/portal-url-builder/" target="_blank">
      Portal URL Builder<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.portal.url.builder.api
      </td>
  </tr>

  <tr>
    <th rowspan="7">
      <a href="@app-ref@/portal/" target="_blank">
      Portal<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      Roles<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      Text Localizer<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.text.localizer.address.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/user-associated-data/" target="_blank">
      User-associated Data<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.user.associated.data.api
      </td>
  </tr>

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/user-groups-admin/" target="_blank">
      User Groups Admin<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      Users Admin<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      XStream<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.xstream.configurator.api
      </td>
  </tr>

<!-- WEM -->

  <tr>
    <th rowspan="1">
      <a href="@app-ref@/application-list/" target="_blank">
      Application List<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.application.list.api
      </td>
  </tr>

  <tr>
    <th rowspan="11">
      <a href="@app-ref@/asset/" target="_blank">
      Asset<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      Export Import<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      Fragment<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      HTML Preview<span class="opens-new-window-accessible"> (Opens New Window) </span>
      </a>
    </th>
    <td>
             com.liferay.html.preview.api
      </td>
  </tr>

  <tr>
    <th rowspan="3">
      <a href="@app-ref@/journal/" target="_blank">
      Journal<span class="opens-new-window-accessible"> (Opens New Window) </span>
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
      Layout<span class="opens-new-window-accessible"> (Opens New Window) </span>
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

</table>

