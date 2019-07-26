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

</table>

