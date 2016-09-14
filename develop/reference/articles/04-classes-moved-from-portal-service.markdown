# Classes Moved from portal-service.jar [](id=classes-moved-from-portal-service-jar)

To leverage the benefits of modularization in Liferay 7, many classes from
former Liferay 6 JAR file `portal-service.jar` have been moved into
application and framework API modules. The table below provides details
about these classes and the modules they've moved to. Package changes are noted
and each module's symbolic name and version are included to facilitate
configuring dependencies on them.

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
    <b>Classes Moved from portal-service (now renamed portal-kernel)</b>
	<p>
      This information was generated based on comparing classes in
      <em>liferay-portal-src-6.2-ce-ga6.zip</em> to classes in <em>liferay-ce-portal-src-7.0-ga2.zip</em>.
    </p>
  </caption>
  <tr>
    <th>Class</th>
    <th>Package</th>
    <th>Module Symbolic Name</th>
    <th>Module Version</th>
  </tr>

  <tr>
    <td>ActionHandler</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.mobile.device.rulegroup.action<br>
	  <em>New:</em> com.liferay.mobile.device.rules.action
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>ActionHandlerManager</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.mobile.device.rulegroup<br>
	  <em>New:</em> com.liferay.mobile.device.rules.action
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>ActionHandlerManagerUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.mobile.device.rulegroup<br>
	  <em>New:</em> com.liferay.mobile.device.rules.action
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>ActionTypeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules<br>
	  <em>New:</em> com.liferay.mobile.device.rules.exception
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>AlternateKeywordQueryHitsProcessor</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.search<br>
	  <em>New:</em> com.liferay.portal.search.internal.hits
	</td>
    <td>com.liferay.portal.search</td>
    <td>3.1.2</td>
  </tr>
  <tr>
    <td>ArticleContentException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleContentSizeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleCreateDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util.comparator<br>
	  <em>New:</em> com.liferay.journal.util.comparator
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleDisplayDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util.comparator<br>
	  <em>New:</em> com.liferay.journal.util.comparator
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleDisplayDateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleExpirationDateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleIDComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util.comparator<br>
	  <em>New:</em> com.liferay.journal.util.comparator
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleIdException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleModifiedDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util.comparator<br>
	  <em>New:</em> com.liferay.journal.util.comparator
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleReviewDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util.comparator<br>
	  <em>New:</em> com.liferay.journal.util.comparator
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleReviewDateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleSmallImageNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleSmallImageSizeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleTitleComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util.comparator<br>
	  <em>New:</em> com.liferay.journal.util.comparator
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleTitleException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleVersionComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util.comparator<br>
	  <em>New:</em> com.liferay.journal.util.comparator
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>ArticleVersionException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>AssertUtils</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.test<br>
	  <em>New:</em> com.liferay.portal.search.test.util
	</td>
    <td>com.liferay.portal.search.test</td>
    <td>1.0.0</td>
  </tr>
  <tr>
    <td>AssetPublisherUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.assetpublisher.util<br>
	  <em>New:</em> com.liferay.asset.publisher.web.util
	</td>
    <td>com.liferay.asset.publisher.web</td>
    <td>1.1.5</td>
  </tr>
  <tr>
    <td>AuditMessageProcessor</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.audit<br>
	  <em>New:</em> com.liferay.portal.security.audit
	</td>
    <td>com.liferay.portal.security.audit.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>AverageStatistics</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.monitoring.statistics<br>
	  <em>New:</em> com.liferay.portal.monitoring.internal.statistics
	</td>
    <td>com.liferay.portal.monitoring</td>
    <td>3.0.4</td>
  </tr>
  <tr>
    <td>BackgroundTaskLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.background.task.service
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.background.task.service
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.background.task.service
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskModel</td>
    <td>
	  <em>Old:</em> com.liferay.portal.model<br>
	  <em>New:</em> com.liferay.portal.background.task.model
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service.persistence<br>
	  <em>New:</em> com.liferay.portal.background.task.service.persistence
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskService</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.background.task.service
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.background.task.service
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.background.task.service
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portal.model<br>
	  <em>New:</em> com.liferay.portal.background.task.model
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service.persistence<br>
	  <em>New:</em> com.liferay.portal.background.task.service.persistence
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BackgroundTaskWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portal.model<br>
	  <em>New:</em> com.liferay.portal.background.task.model
	</td>
    <td>com.liferay.portal.background.task.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BaseCmisRepository</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>BaseCmisSearchQueryBuilder</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>BaseDDLExporter</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.util<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exporter.impl
	</td>
    <td>com.liferay.dynamic.data.lists.service</td>
    <td>1.1.2</td>
  </tr>
  <tr>
    <td>BaseDDMDisplay</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>BaseFieldRenderer</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.storage
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>BaseScriptingExecutor</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.scripting<br>
	  <em>New:</em> com.liferay.portal.scripting
	</td>
    <td>com.liferay.portal.scripting.api</td>
    <td>1.0.1</td>
  </tr>
  <tr>
    <td>BaseStatistics</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.monitoring.statistics<br>
	  <em>New:</em> com.liferay.portal.monitoring.internal.statistics
	</td>
    <td>com.liferay.portal.monitoring</td>
    <td>3.0.4</td>
  </tr>
  <tr>
    <td>BaseStorageAdapter</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.storage
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>BillingCityException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingCountryException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingEmailAddressException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingFirstNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingLastNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingPhoneException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingStateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingStreetException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BillingZipException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>BlockingPortalCache</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.cache<br>
	  <em>New:</em> com.liferay.portal.cache
	</td>
    <td>com.liferay.portal.cache</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>BookmarksEntry</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service.persistence<br>
	  <em>New:</em> com.liferay.bookmarks.service.persistence
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service.persistence<br>
	  <em>New:</em> com.liferay.bookmarks.service.persistence
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntrySoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service.persistence<br>
	  <em>New:</em> com.liferay.bookmarks.service.persistence
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksEntryWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service.persistence<br>
	  <em>New:</em> com.liferay.bookmarks.service.persistence
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service.persistence<br>
	  <em>New:</em> com.liferay.bookmarks.service.persistence
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service<br>
	  <em>New:</em> com.liferay.bookmarks.service
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.service.persistence<br>
	  <em>New:</em> com.liferay.bookmarks.service.persistence
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>BookmarksFolderWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.model<br>
	  <em>New:</em> com.liferay.bookmarks.model
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>ByteArrayReportResultContainer</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>CCExpirationException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CCNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CCNumberException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CCTypeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CMISBetweenExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISConjunction</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISContainsExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISContainsNotExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISContainsValueExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISCriterion</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISDisjunction</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISFullTextConjunction</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISInFolderExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISInTreeExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISJunction</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISNotExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISParameterValueUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISRepositoryHandler</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISRepositoryUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.internal
	</td>
    <td>com.liferay.document.library.repository.cmis.impl</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>CMISSearchQueryBuilder</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISSimpleExpression</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CMISSimpleExpressionOperator</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.repository.cmis.search<br>
	  <em>New:</em> com.liferay.document.library.repository.cmis.search
	</td>
    <td>com.liferay.document.library.repository.cmis.api</td>
    <td>1.0.3</td>
  </tr>
  <tr>
    <td>CartMinOrderException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CartMinQuantityException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CollatedSpellCheckHitsProcessor</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.search<br>
	  <em>New:</em> com.liferay.portal.search.internal.hits
	</td>
    <td>com.liferay.portal.search</td>
    <td>3.1.2</td>
  </tr>
  <tr>
    <td>CompoundSessionIdServletRequest</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.servlet.filters.compoundsessionid<br>
	  <em>New:</em> com.liferay.portal.compound.session.id
	</td>
    <td>com.liferay.portal.compound.session.id</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>Condition</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage.query<br>
	  <em>New:</em> com.liferay.portal.workflow.kaleo.definition
	</td>
    <td>com.liferay.portal.workflow.kaleo.definition.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>ContactConverterKeys</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>ContentException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>ContentNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>ContextClassloaderReportDesignRetriever</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>CountStatistics</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.monitoring.statistics<br>
	  <em>New:</em> com.liferay.portal.monitoring.internal.statistics
	</td>
    <td>com.liferay.portal.monitoring</td>
    <td>3.0.4</td>
  </tr>
  <tr>
    <td>CouponActiveException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponCodeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponDateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponDescriptionException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponDiscountException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponEndDateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponLimitCategoriesException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponLimitSKUsException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponMinimumOrderException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>CouponStartDateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DDL</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.util<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.util
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLExporter</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.util<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exporter
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLExporterFactory</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.util<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exporter
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecord</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSet</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSetWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordVersion</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordVersionModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordVersionPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordVersionSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordVersionUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.service.persistence
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordVersionVersionComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.util.comparator<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.util.comparator
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordVersionWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDLRecordWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists.model<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.model
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>DDM</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContent</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMContentWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMDisplay</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMDisplayRegistry</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMIndexer</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLink</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStorageLinkWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLinkWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructurePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMStructureWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateHelper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplatePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.service.persistence<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.service.persistence
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMTemplateWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.model<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.model
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>DDMXML</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>Database</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.util<br>
	  <em>New:</em> com.liferay.portal.tools.db.upgrade.client
	</td>
    <td>com.liferay.portal.tools.db.upgrade.client</td>
    <td>1.0.0</td>
  </tr>
  <tr>
    <td>DefaultAttributesTransformer</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.internal
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>DefaultMessageBus</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging<br>
	  <em>New:</em> com.liferay.portal.messaging.internal
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DefaultSingleDestinationMessageSender</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.sender<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.sender
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DefaultSingleDestinationSynchronousMessageSender</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.sender<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.sender
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DefaultSynchronousMessageSender</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.sender<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.sender
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DestinationStatisticsManager</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.jmx<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.jmx
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DestinationStatisticsManagerMBean</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.jmx<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.jmx
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DirectSynchronousMessageSender</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.sender<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.sender
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DummyContext</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.dummy
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>DummyDirContext</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.dummy
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>DuplicateArticleIdException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>DuplicateArticleImageIdException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>DuplicateCouponCodeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DuplicateFeedIdException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>DuplicateItemSKUException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>DuplicateLDAPServerNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>DuplicateNodeNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>DuplicatePageException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>DuplicateRuleGroupInstanceException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules<br>
	  <em>New:</em> com.liferay.mobile.device.rules.exception
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>DuplicateVoteException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>EntryNameComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.util.comparator<br>
	  <em>New:</em> com.liferay.bookmarks.util.comparator
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>EntryPriorityComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.util.comparator<br>
	  <em>New:</em> com.liferay.bookmarks.util.comparator
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>EntryURLComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.util.comparator<br>
	  <em>New:</em> com.liferay.bookmarks.util.comparator
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>EntryVisitsComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.bookmarks.util.comparator<br>
	  <em>New:</em> com.liferay.bookmarks.util.comparator
	</td>
    <td>com.liferay.bookmarks.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>Fact</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.rules<br>
	  <em>New:</em> com.liferay.portal.rules.engine
	</td>
    <td>com.liferay.portal.rules.engine.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>FeedContentFieldException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>FeedIdException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>FeedNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>FeedTargetLayoutFriendlyUrlException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>FeedTargetPortletIdException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>FieldConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.document.library.lar.xstream
	</td>
    <td>com.liferay.document.library.service</td>
    <td>1.1.2</td>
  </tr>
  <tr>
    <td>FieldRenderer</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.storage
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>FieldRendererFactory</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.storage
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>Fields</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.storage
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>FlagsEntryService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.flags.service<br>
	  <em>New:</em> com.liferay.flags.service
	</td>
    <td>com.liferay.flags.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>FlagsEntryServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.flags.service<br>
	  <em>New:</em> com.liferay.flags.service
	</td>
    <td>com.liferay.flags.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>FlagsEntryServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.flags.service<br>
	  <em>New:</em> com.liferay.flags.service
	</td>
    <td>com.liferay.flags.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>FlagsRequest</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.flags.messaging<br>
	  <em>New:</em> com.liferay.flags.messaging
	</td>
    <td>com.liferay.flags.service</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>GroupConverterKeys</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>ImportFilesException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>ItemLargeImageNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemLargeImageSizeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemMediumImageNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemMediumImageSizeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemMinQuantityComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.util.comparator<br>
	  <em>New:</em> com.liferay.shopping.util.comparator
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemNameComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.util.comparator<br>
	  <em>New:</em> com.liferay.shopping.util.comparator
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemPriceComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.util.comparator<br>
	  <em>New:</em> com.liferay.shopping.util.comparator
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemSKUComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.util.comparator<br>
	  <em>New:</em> com.liferay.shopping.util.comparator
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemSKUException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemSmallImageNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ItemSmallImageSizeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>JobStateSerializeUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.scheduler<br>
	  <em>New:</em> com.liferay.portal.scheduler
	</td>
    <td>com.liferay.portal.scheduler</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>JournalArticle</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleDisplay</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImage</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImageLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImageLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImageLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImageModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImagePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImageSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImageUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleImageWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticlePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResource</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourceLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourceLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourceLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourceModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourcePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourceSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleResourceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalArticleWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContent</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journalcontent.util<br>
	  <em>New:</em> com.liferay.journal.util
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearch</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalContentSearchWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalConverter</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.util<br>
	  <em>New:</em> com.liferay.journal.util
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeed</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFeedWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service<br>
	  <em>New:</em> com.liferay.journal.service
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.service.persistence<br>
	  <em>New:</em> com.liferay.journal.service.persistence
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalFolderWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalSearchConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>JournalStructureConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal.model<br>
	  <em>New:</em> com.liferay.journal.model
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>LDAPFilterException</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.validator
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>LDAPGroup</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.exportimport
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>LDAPServerNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>LDAPToPortalConverter</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.exportimport
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>LDAPUser</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.exportimport
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>LDAPUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.util
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>LockLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.lock.service
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>LockLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.lock.service
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>LockLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service<br>
	  <em>New:</em> com.liferay.portal.lock.service
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>LockModel</td>
    <td>
	  <em>Old:</em> com.liferay.portal.model<br>
	  <em>New:</em> com.liferay.portal.lock.model
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>LockPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service.persistence<br>
	  <em>New:</em> com.liferay.portal.lock.service.persistence
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>LockSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portal.model<br>
	  <em>New:</em> com.liferay.portal.lock.model
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>LockUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.service.persistence<br>
	  <em>New:</em> com.liferay.portal.lock.service.persistence
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>LockWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portal.model<br>
	  <em>New:</em> com.liferay.portal.lock.model
	</td>
    <td>com.liferay.portal.lock.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MBeanRegistry</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.jmx<br>
	  <em>New:</em> com.liferay.portal.jmx
	</td>
    <td>com.liferay.portal.jmx</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>MDRAction</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRActionWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRPermission</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.permission<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.permission
	</td>
    <td>com.liferay.mobile.device.rules.service</td>
    <td>1.1.2</td>
  </tr>
  <tr>
    <td>MDRRule</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroup</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstancePermission</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.permission<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.permission
	</td>
    <td>com.liferay.mobile.device.rules.service</td>
    <td>1.1.2</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstancePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupInstanceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupPermission</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.permission<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.permission
	</td>
    <td>com.liferay.mobile.device.rules.service</td>
    <td>1.1.2</td>
  </tr>
  <tr>
    <td>MDRRuleGroupPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleGroupWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRulePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.service.persistence<br>
	  <em>New:</em> com.liferay.mobile.device.rules.service.persistence
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MDRRuleWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.model<br>
	  <em>New:</em> com.liferay.mobile.device.rules.model
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>MemoryReportDesignRetriever</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>MessageBusManager</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.jmx<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.jmx
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>MessageBusManagerMBean</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.jmx<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.jmx
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>Modifications</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.exportimport
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>NoSuchActionException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules<br>
	  <em>New:</em> com.liferay.portal.workflow.kaleo.exception
	</td>
    <td>com.liferay.portal.workflow.kaleo.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>NoSuchArticleException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.knowledge.base.exception
	</td>
    <td>com.liferay.knowledge.base.api</td>
    <td>3.2.1</td>
  </tr>
  <tr>
    <td>NoSuchArticleImageException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>NoSuchArticleResourceException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>NoSuchCartException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>NoSuchChoiceException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>NoSuchContentSearchException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>NoSuchCouponException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>NoSuchFeedException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.journal.exception
	</td>
    <td>com.liferay.journal.api</td>
    <td>2.1.1</td>
  </tr>
  <tr>
    <td>NoSuchItemException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>NoSuchItemFieldException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>NoSuchItemPriceException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>NoSuchNodeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>NoSuchOrderException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>NoSuchOrderItemException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>NoSuchPageException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>NoSuchPageResourceException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>NoSuchQuestionException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>NoSuchRecordException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exception
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>NoSuchRecordSetException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exception
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>NoSuchRecordVersionException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exception
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>NoSuchRuleException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules<br>
	  <em>New:</em> com.liferay.mobile.device.rules.exception
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>NoSuchRuleGroupException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules<br>
	  <em>New:</em> com.liferay.mobile.device.rules.exception
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>NoSuchRuleGroupInstanceException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules<br>
	  <em>New:</em> com.liferay.mobile.device.rules.exception
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>NoSuchStorageLinkException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>NoSuchStructureLinkException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>NoSuchTemplateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>NoSuchTemplateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>NoSuchVoteException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>NodeNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>OrderDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.util.comparator<br>
	  <em>New:</em> com.liferay.shopping.util.comparator
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>PageContentException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>PageCreateDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.util.comparator<br>
	  <em>New:</em> com.liferay.wiki.util.comparator
	</td>
    <td>com.liferay.wiki.service</td>
    <td>1.1.3</td>
  </tr>
  <tr>
    <td>PageTitleComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.util.comparator<br>
	  <em>New:</em> com.liferay.wiki.util.comparator
	</td>
    <td>com.liferay.wiki.service</td>
    <td>1.1.3</td>
  </tr>
  <tr>
    <td>PageTitleException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>PageVersionComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.util.comparator<br>
	  <em>New:</em> com.liferay.wiki.util.comparator
	</td>
    <td>com.liferay.wiki.service</td>
    <td>1.1.3</td>
  </tr>
  <tr>
    <td>PageVersionException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>PollsChoice</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoicePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service.persistence<br>
	  <em>New:</em> com.liferay.polls.service.persistence
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service.persistence<br>
	  <em>New:</em> com.liferay.polls.service.persistence
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsChoiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestion</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service.persistence<br>
	  <em>New:</em> com.liferay.polls.service.persistence
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service.persistence<br>
	  <em>New:</em> com.liferay.polls.service.persistence
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsQuestionWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVote</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVotePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service.persistence<br>
	  <em>New:</em> com.liferay.polls.service.persistence
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service<br>
	  <em>New:</em> com.liferay.polls.service
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.service.persistence<br>
	  <em>New:</em> com.liferay.polls.service.persistence
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PollsVoteWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls.model<br>
	  <em>New:</em> com.liferay.polls.model
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>PortalExecutorFactory</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.executor<br>
	  <em>New:</em> com.liferay.portal.executor.internal
	</td>
    <td>com.liferay.portal.executor</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>PortalToLDAPConverter</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap.exportimport
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>PortletDisplayTemplate</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.portletdisplaytemplate.util<br>
	  <em>New:</em> com.liferay.portlet.display.template
	</td>
    <td>com.liferay.portlet.display.template</td>
    <td>2.0.2</td>
  </tr>
  <tr>
    <td>PortletDisplayTemplateConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.portletdisplaytemplate.util<br>
	  <em>New:</em> com.liferay.portlet.display.template
	</td>
    <td>com.liferay.portlet.display.template</td>
    <td>2.0.2</td>
  </tr>
  <tr>
    <td>PortletDisplayTemplateUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.portletdisplaytemplate.util<br>
	  <em>New:</em> com.liferay.portlet.display.template
	</td>
    <td>com.liferay.portlet.display.template</td>
    <td>2.0.2</td>
  </tr>
  <tr>
    <td>QueryIndexingHitsProcessor</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.search<br>
	  <em>New:</em> com.liferay.portal.search.internal.hits
	</td>
    <td>com.liferay.portal.search</td>
    <td>3.1.2</td>
  </tr>
  <tr>
    <td>QuerySuggestionHitsProcessor</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.search<br>
	  <em>New:</em> com.liferay.portal.search.internal.hits
	</td>
    <td>com.liferay.portal.search</td>
    <td>3.1.2</td>
  </tr>
  <tr>
    <td>QueryType</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.rules<br>
	  <em>New:</em> com.liferay.portal.rules.engine
	</td>
    <td>com.liferay.portal.rules.engine.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>QuestionChoiceException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>QuestionDescriptionException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>QuestionExpirationDateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>QuestionExpiredException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>QuestionTitleException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.polls<br>
	  <em>New:</em> com.liferay.polls.exception
	</td>
    <td>com.liferay.polls.api</td>
    <td>4.0.0</td>
  </tr>
  <tr>
    <td>RecordSetDDMStructureIdException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exception
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>RecordSetDuplicateRecordSetKeyException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exception
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>RecordSetNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatalists<br>
	  <em>New:</em> com.liferay.dynamic.data.lists.exception
	</td>
    <td>com.liferay.dynamic.data.lists.api</td>
    <td>2.0.3</td>
  </tr>
  <tr>
    <td>RegistryAwareMBeanServer</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.jmx<br>
	  <em>New:</em> com.liferay.portal.jmx.internal
	</td>
    <td>com.liferay.portal.jmx</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ReportCompilerRequestMessageListener</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting.messaging<br>
	  <em>New:</em> com.liferay.portal.reports.engine.messaging
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportDataSourceType</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportDesignRetriever</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportEngine</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportExportException</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportFormat</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportFormatExporter</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportFormatExporterRegistry</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportGenerationException</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportRequest</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportRequestContext</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportRequestMessageListener</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting.messaging<br>
	  <em>New:</em> com.liferay.portal.reports.engine.messaging
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ReportResultContainer</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting<br>
	  <em>New:</em> com.liferay.portal.reports.engine
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>RequestStatistics</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.monitoring.statistics<br>
	  <em>New:</em> com.liferay.portal.monitoring.internal.statistics
	</td>
    <td>com.liferay.portal.monitoring</td>
    <td>3.0.4</td>
  </tr>
  <tr>
    <td>RequiredCouponException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>RequiredNodeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>RequiredTemplateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>RequiredTemplateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>RuleGroupInstancePriorityComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.mobiledevicerules.util<br>
	  <em>New:</em> com.liferay.mobile.device.rules.util.comparator
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RuleGroupProcessor</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.mobile.device.rulegroup<br>
	  <em>New:</em> com.liferay.mobile.device.rules.rule
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RuleGroupProcessorUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.mobile.device.rulegroup<br>
	  <em>New:</em> com.liferay.mobile.device.rules.rule
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RuleHandler</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.mobile.device.rulegroup.rule<br>
	  <em>New:</em> com.liferay.mobile.device.rules.rule
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RulesEngine</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.rules<br>
	  <em>New:</em> com.liferay.portal.rules.engine
	</td>
    <td>com.liferay.portal.rules.engine.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RulesEngineException</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.rules<br>
	  <em>New:</em> com.liferay.portal.rules.engine
	</td>
    <td>com.liferay.portal.rules.engine.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RulesEngineUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.rules<br>
	  <em>New:</em> com.liferay.portal.rules.engine
	</td>
    <td>com.liferay.portal.rules.engine.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RulesLanguage</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.rules<br>
	  <em>New:</em> com.liferay.portal.rules.engine
	</td>
    <td>com.liferay.portal.rules.engine.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>RulesResourceRetriever</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.rules<br>
	  <em>New:</em> com.liferay.portal.rules.engine
	</td>
    <td>com.liferay.portal.rules.engine.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>ServletContextReportDesignRetriever</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.bi.reporting.servlet<br>
	  <em>New:</em> com.liferay.portal.reports.engine.servlet
	</td>
    <td>com.liferay.portal.reports.engine.api</td>
    <td>3.0.0</td>
  </tr>
  <tr>
    <td>ShippingCityException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingCountryException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingEmailAddressException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingFirstNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingLastNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingPhoneException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingStateException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingStreetException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShippingZipException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping<br>
	  <em>New:</em> com.liferay.shopping.exception
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCart</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartItem</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCartWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategory</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategorySoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCategoryWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCoupon</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingCouponWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItem</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemField</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFieldWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPrice</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPricePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemPriceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingItemWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItem</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderItemWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderPersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service<br>
	  <em>New:</em> com.liferay.shopping.service
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.service.persistence<br>
	  <em>New:</em> com.liferay.shopping.service.persistence
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>ShoppingOrderWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.shopping.model<br>
	  <em>New:</em> com.liferay.shopping.model
	</td>
    <td>com.liferay.shopping.api</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>SortFactoryImpl</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.search<br>
	  <em>New:</em> com.liferay.portal.search.internal
	</td>
    <td>com.liferay.portal.search</td>
    <td>3.1.2</td>
  </tr>
  <tr>
    <td>Statistics</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.monitoring.statistics<br>
	  <em>New:</em> com.liferay.portal.monitoring.statistics
	</td>
    <td>com.liferay.portal.monitoring</td>
    <td>3.0.4</td>
  </tr>
  <tr>
    <td>StorageAdapter</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.storage
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StorageEngine</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.storage<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.storage
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StorageException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StorageFieldNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StructureDuplicateStructureKeyException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StructureFieldException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StructureIdComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util.comparator<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util.comparator
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StructureModifiedDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util.comparator<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util.comparator
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>StructureStructureKeyComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util.comparator<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util.comparator
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>SummaryStatistics</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.monitoring.statistics<br>
	  <em>New:</em> com.liferay.portal.monitoring.statistics
	</td>
    <td>com.liferay.portal.monitoring</td>
    <td>3.0.4</td>
  </tr>
  <tr>
    <td>SynchronousMessageListener</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.messaging.sender<br>
	  <em>New:</em> com.liferay.portal.messaging.internal.sender
	</td>
    <td>com.liferay.portal.messaging</td>
    <td>3.0.1</td>
  </tr>
  <tr>
    <td>TemplateDuplicateTemplateKeyException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateIdComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util.comparator<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util.comparator
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateModifiedDateComparator</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping.util.comparator<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.util.comparator
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateScriptException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateSmallImageNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateSmallImageNameException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateSmallImageSizeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.dynamicdatamapping<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>TemplateSmallImageSizeException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.journal<br>
	  <em>New:</em> com.liferay.dynamic.data.mapping.exception
	</td>
    <td>com.liferay.dynamic.data.mapping.api</td>
    <td>3.3.0</td>
  </tr>
  <tr>
    <td>UnknownRuleHandlerException</td>
    <td>
	  <em>Old:</em> com.liferay.portal.kernel.mobile.device.rulegroup.rule<br>
	  <em>New:</em> com.liferay.mobile.device.rules.rule
	</td>
    <td>com.liferay.mobile.device.rules.api</td>
    <td>2.0.1</td>
  </tr>
  <tr>
    <td>UserConverterKeys</td>
    <td>
	  <em>Old:</em> com.liferay.portal.security.ldap<br>
	  <em>New:</em> com.liferay.portal.security.ldap
	</td>
    <td>com.liferay.portal.security.ldap</td>
    <td>2.0.4</td>
  </tr>
  <tr>
    <td>WikiFormatException</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki<br>
	  <em>New:</em> com.liferay.wiki.exception
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNode</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service.persistence<br>
	  <em>New:</em> com.liferay.wiki.service.persistence
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service.persistence<br>
	  <em>New:</em> com.liferay.wiki.service.persistence
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiNodeWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPage</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageConstants</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageDisplay</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageFinder</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service.persistence<br>
	  <em>New:</em> com.liferay.wiki.service.persistence
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPagePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service.persistence<br>
	  <em>New:</em> com.liferay.wiki.service.persistence
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResource</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourceLocalService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourceLocalServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourceLocalServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourceModel</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourcePersistence</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service.persistence<br>
	  <em>New:</em> com.liferay.wiki.service.persistence
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourceSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service.persistence<br>
	  <em>New:</em> com.liferay.wiki.service.persistence
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageResourceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageService</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageServiceUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageServiceWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service<br>
	  <em>New:</em> com.liferay.wiki.service
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageSoap</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageUtil</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.service.persistence<br>
	  <em>New:</em> com.liferay.wiki.service.persistence
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>
  <tr>
    <td>WikiPageWrapper</td>
    <td>
	  <em>Old:</em> com.liferay.portlet.wiki.model<br>
	  <em>New:</em> com.liferay.wiki.model
	</td>
    <td>com.liferay.wiki.api</td>
    <td>2.1.0</td>
  </tr>

</table>

**Related Articles:**

[Migrating a Liferay 6 Application](/develop/tutorials/-/knowledge_base/7-0/migrating-a-liferay-6-application)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)
