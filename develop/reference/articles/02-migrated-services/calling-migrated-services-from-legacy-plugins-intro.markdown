# Calling Migrated Services from Legacy Plugins [](id=calling-migrated-services-from-legacy-plugins)

Some classes that were exposed as Liferay Portal API in 6.2 have migrated from
portal-service into separate OSGi modules. These classes are now services and
utilities of the following components:

| Component            | API (Services, Classes)               |
|----------------------|---------------------------------------|
| Background Tasks     | `BackgroundTaskService`               |
| Lock                 | `LockService`                         |
| Kaleo                | `KaleoActionService`                  |
|                      | `KaleoConditionService`               |
|                      | `KaleoDefinitionService`              |
|                      | `KaleoInstanceService`                |
|                      | `KaleoInstanceTokenService`           |
|                      | `KaleoLogService`                     |
|                      | `KaleoNodeService`                    |
|                      | `KaleoNotificationService`            |
|                      | `KaleoNotificationRecipientService`   |
|                      | `KaleoTaskService`                    |
|                      | `KaleoTaskAssignmentService`          |
|                      | `KaleoTaskAssignmentInstanceService`  |
|                      | `KaleoTaskInstanceTokenService`       |
|                      | `KaleoTimerService`                   |
|                      | `KaleoTimerInstanceTokenService`      |
|                      | `KaleoTransistionService`             |
| Dynamic Data Mapping | `DDMContentService`                   |
|                      | `DDMStructureService`                 |
|                      | `DDMStorageLinkService`               |
|                      | `DDMStructureLayoutService`           |
|                      | `DDMStructureLinkService`             |
|                      | `DDMStructureVersionService`          |
|                      | `DDMTemplateService`                  |
|                      | `DDMTemplateLinkService`              |
|                      | `DDMTemplateVersionService`           |
| Dynamic Data Lists   | `DDLRecordService`                    |
|                      | `DDLRecordSetService`                 |
|                      | `DDLRecordVersionService`             |
| Mobile Device Rules  | `MDRActionService`                    |
|                      | `MDRRuleService`                      |
|                      | `MDRRuleGroupService`                 |
|                      | `MDRRuleGroupInstanceService`         |
| Web Content          | `JournalArticleService`               |
|                      | `JournalFolderService`                |
|                      | `JournalArticleImageService`          |
|                      | `JournalFeedService`                  |
| Calendar             | `CalendarService`                     |
|                      | `CalendarBookingService`              |
|                      | `CalendarImporterService`             |
|                      | `CalendarNotificationTemplateService` |
|                      | `CalendarResourceService`             |
| Marketplace          | `AppService`                          |
|                      | `ModuleService`                       |
| Microblogs           | `MicroblogsEntryService`              |
| Shopping Cart        | `ShoppingCartService`                 |
|                      | `ShoppingCategoryService`             |
|                      | `ShoppingCouponService`               |
|                      | `ShoppingItemService`                 |
|                      | `ShoppingItemPriceService`            |
|                      | `ShoppingOrderService`                |
|                      | `ShoppingOrderItemService`            |
| Social Networking    | `MeetupsEntryService`                 |
|                      | `MeetupsRegistrationService`          |
|                      | `WallEntryService`                    |
| Bookmarks            | `BookmarksEntryService`               |
|                      | `BookmarksFolderService`              |
| Polls                | `PollsChoiceService`                  |
|                      | `PollsQuestionService`                |
|                      | `PollsVoteService`                    |
| Wiki                 | `WikiNodeService`                     |
|                      | `WikiPageService`                     |

If your legacy plugins were consuming any of the APIs listed in the table above,
you'll need to update your existing code to consume the API from the new
modules.
