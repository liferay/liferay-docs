# Calling Migrated Services from Legacy Plugins [](id=calling-migrated-services-from-legacy-plugins)

Some classes that were exposed as Liferay Portal API in 6.2 have migrated from
portal-service into separate OSGi modules. These classes are now services and
utilities of the following components:

| Component            | API (Services, Classes)               | Module                         |
|----------------------|---------------------------------------|--------------------------------|
| Background Tasks     | `BackgroundTaskService`               | portal-background-task-api     |
|                      |                                       | portal-background-task-service |
| Lock                 | `LockService`                         | portal-lock-api                |
|                      |                                       | portal-lock-service            |
| Kaleo                | `KaleoActionService`                  | portal-workflow-kaleo-api      |
|                      | `KaleoConditionService`               | portal-workflow-kaleo-service  |
|                      | `KaleoDefinitionService`              |                                |
|                      | `KaleoInstanceService`                |                                |
|                      | `KaleoInstanceTokenService`           |                                |
|                      | `KaleoLogService`                     |                                |
|                      | `KaleoNodeService`                    |                                |
|                      | `KaleoNotificationService`            |                                |
|                      | `KaleoNotificationRecipientService`   |                                |
|                      | `KaleoTaskService`                    |                                |
|                      | `KaleoTaskAssignmentService`          |                                |
|                      | `KaleoTaskAssignmentInstanceService`  |                                |
|                      | `KaleoTaskInstanceTokenService`       |                                |
|                      | `KaleoTimerService`                   |                                |
|                      | `KaleoTimerInstanceTokenService`      |                                |
|                      | `KaleoTransistionService`             |                                |
| Dynamic Data Mapping | `DDMContentService`                   | dynamic-data-mapping-api       |
|                      | `DDMStructureService`                 | dynamic-data-mapping-service   |
|                      | `DDMStorageLinkService`               |                                |
|                      | `DDMStructureLayoutService`           |                                |
|                      | `DDMStructureLinkService`             |                                |
|                      | `DDMStructureVersionService`          |                                |
|                      | `DDMTemplateService`                  |                                |
|                      | `DDMTemplateLinkService`              |                                |
|                      | `DDMTemplateVersionService`           |                                |
| Dynamic Data Lists   | `DDLRecordService`                    | dynamic-data-lists-api         |
|                      | `DDLRecordSetService`                 | dynamic-data-lists-service     |
|                      | `DDLRecordVersionService`             |                                |
| Mobile Device Rules  | `MDRActionService`                    | mobile-device-rules-api        |
|                      | `MDRRuleService`                      | mobile-device-rules-service    |
|                      | `MDRRuleGroupService`                 |                                |
|                      | `MDRRuleGroupInstanceService`         |                                |
| Web Content          | `JournalArticleService`               | journal-api                    |
|                      | `JournalFolderService`                | journal-service                |
|                      | `JournalArticleImageService`          |                                |
|                      | `JournalFeedService`                  |                                |
| Calendar             | `CalendarService`                     | calendar-api                   |
|                      | `CalendarBookingService`              | calendar-service               |
|                      | `CalendarImporterService`             |                                |
|                      | `CalendarNotificationTemplateService` |                                |
|                      | `CalendarResourceService`             |                                |
| Marketplace          | `AppService`                          | marketplace-api                |
|                      | `ModuleService`                       | marketplace-service            |
| Microblogs           | `MicroblogsEntryService`              | microblogs-api                 |
|                      |                                       | microblogs-service             |
| Shopping Cart        | `ShoppingCartService`                 | shopping-api                   |
|                      | `ShoppingCategoryService`             | shopping-service               |
|                      | `ShoppingCouponService`               |                                |
|                      | `ShoppingItemService`                 |                                |
|                      | `ShoppingItemPriceService`            |                                |
|                      | `ShoppingOrderService`                |                                |
|                      | `ShoppingOrderItemService`            |                                |
| Social Networking    | `MeetupsEntryService`                 | social-networking-api          |
|                      | `MeetupsRegistrationService`          | social-networking-service      |
|                      | `WallEntryService`                    |                                |
| Bookmarks            | `BookmarksEntryService`               | bookmarks-api                  |
|                      | `BookmarksFolderService`              | bookmarks-service              |
| Polls                | `PollsChoiceService`                  | polls-api                      |
|                      | `PollsQuestionService`                | polls-service                  |
|                      | `PollsVoteService`                    |                                |
| Wiki                 | `WikiNodeService`                     | wiki-api                       |
|                      | `WikiPageService`                     | wiki-service                   |

If your legacy plugins were consuming any of the APIs listed in the table above,
you'll need to update your existing code to consume the API from the new
modules.
