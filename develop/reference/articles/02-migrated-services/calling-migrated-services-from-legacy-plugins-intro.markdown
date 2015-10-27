# Calling Migrated Services from Legacy Plugins [](id=calling-migrated-services-from-legacy-plugins)

Some classes that were exposed as Liferay Portal API in 6.2 have migrated from
portal-service into separate OSGi modules. These classes are now services and
utilities of the following components:

| Component            | API Entity (Services, Classes) | Module                         |
|----------------------|--------------------------------|--------------------------------|
| Background Tasks     | `BackgroundTask`               | portal-background-task-api     |
|                      |                                | portal-background-task-service |
| Lock                 | `Lock`                         | portal-lock-api                |
|                      |                                | portal-lock-service            |
| Kaleo                | `KaleoAction`                  | portal-workflow-kaleo-api      |
|                      | `KaleoCondition`               | portal-workflow-kaleo-service  |
|                      | `KaleoDefinition`              |                                |
|                      | `KaleoInstance`                |                                |
|                      | `KaleoInstanceToken`           |                                |
|                      | `KaleoLog`                     |                                |
|                      | `KaleoNode`                    |                                |
|                      | `KaleoNotification`            |                                |
|                      | `KaleoNotificationRecipient`   |                                |
|                      | `KaleoTask`                    |                                |
|                      | `KaleoTaskAssignment`          |                                |
|                      | `KaleoTaskAssignmentInstance`  |                                |
|                      | `KaleoTaskInstanceToken`       |                                |
|                      | `KaleoTimer`                   |                                |
|                      | `KaleoTimerInstanceToken`      |                                |
|                      | `KaleoTransistion`             |                                |
| Dynamic Data Mapping | `DDMContent`                   | dynamic-data-mapping-api       |
|                      | `DDMStructure`                 | dynamic-data-mapping-service   |
|                      | `DDMStorageLink`               |                                |
|                      | `DDMStructureLayout`           |                                |
|                      | `DDMStructureLink`             |                                |
|                      | `DDMStructureVersion`          |                                |
|                      | `DDMTemplate`                  |                                |
|                      | `DDMTemplateLink`              |                                |
|                      | `DDMTemplateVersion`           |                                |
| Dynamic Data Lists   | `DDLRecord`                    | dynamic-data-lists-api         |
|                      | `DDLRecordSet`                 | dynamic-data-lists-service     |
|                      | `DDLRecordVersion`             |                                |
| Mobile Device Rules  | `MDRAction`                    | mobile-device-rules-api        |
|                      | `MDRRule`                      | mobile-device-rules-service    |
|                      | `MDRRuleGroup`                 |                                |
|                      | `MDRRuleGroupInstance`         |                                |
| Web Content          | `JournalArticle`               | journal-api                    |
|                      | `JournalFolder`                | journal-service                |
|                      | `JournalArticleImage`          |                                |
|                      | `JournalFeed`                  |                                |
| Calendar             | `Calendar`                     | calendar-api                   |
|                      | `CalendarBooking`              | calendar-service               |
|                      | `CalendarImporter`             |                                |
|                      | `CalendarNotificationTemplate` |                                |
|                      | `CalendarResource`             |                                |
| Marketplace          | `App`                          | marketplace-api                |
|                      | `Module`                       | marketplace-service            |
| Microblogs           | `MicroblogsEntry`              | microblogs-api                 |
|                      |                                | microblogs-service             |
| Shopping Cart        | `ShoppingCart`                 | shopping-api                   |
|                      | `ShoppingCategory`             | shopping-service               |
|                      | `ShoppingCoupon`               |                                |
|                      | `ShoppingItem`                 |                                |
|                      | `ShoppingItemPrice`            |                                |
|                      | `ShoppingOrder`                |                                |
|                      | `ShoppingOrderItem`            |                                |
| Social Networking    | `MeetupsEntry`                 | social-networking-api          |
|                      | `MeetupsRegistration`          | social-networking-service      |
|                      | `WallEntry`                    |                                |
| Bookmarks            | `BookmarksEntry`               | bookmarks-api                  |
|                      | `BookmarksFolder`              | bookmarks-service              |
| Polls                | `PollsChoice`                  | polls-api                      |
|                      | `PollsQuestion`                | polls-service                  |
|                      | `PollsVote`                    |                                |
| Wiki                 | `WikiNode`                     | wiki-api                       |
|                      | `WikiPage`                     | wiki-service                   |

If your legacy plugins were consuming any of the APIs listed in the table above,
you'll need to update your existing code to consume the API from the new
modules.
