# Finding Liferay API Modules [](id=finding-liferay-api-modules)

Some classes that were exposed as Liferay Portal API in 6.2 have migrated from
portal-service into separate OSGi modules. These classes are now services and
utilities of the following components:

| Component            | API Entity (Services, Classes) | Module                         |
|----------------------|--------------------------------|--------------------------------|
| Background Tasks     | `BackgroundTask`               | portal-background-task-api     |
|                      |                                | portal-background-task-service |
| Bookmarks            | `BookmarksEntry`               | bookmarks-api                  |
|                      | `BookmarksFolder`              | bookmarks-service              |
| Calendar             | `Calendar`                     | calendar-api                   |
|                      | `CalendarBooking`              | calendar-service               |
|                      | `CalendarImporter`             |                                |
|                      | `CalendarNotificationTemplate` |                                |
|                      | `CalendarResource`             |                                |
| Dynamic Data Lists   | `DDLRecord`                    | dynamic-data-lists-api         |
|                      | `DDLRecordSet`                 | dynamic-data-lists-service     |
|                      | `DDLRecordVersion`             |                                |
| Dynamic Data Mapping | `DDMContent`                   | dynamic-data-mapping-api       |
|                      | `DDMStructure`                 | dynamic-data-mapping-service   |
|                      | `DDMStorageLink`               |                                |
|                      | `DDMStructureLayout`           |                                |
|                      | `DDMStructureLink`             |                                |
|                      | `DDMStructureVersion`          |                                |
|                      | `DDMTemplate`                  |                                |
|                      | `DDMTemplateLink`              |                                |
|                      | `DDMTemplateVersion`           |                                |
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
| Lock                 | `Lock`                         | portal-lock-api                |
|                      |                                | portal-lock-service            |
| Marketplace          | `App`                          | marketplace-api                |
|                      | `Module`                       | marketplace-service            |
| Microblogs           | `MicroblogsEntry`              | microblogs-api                 |
|                      |                                | microblogs-service             |
| Mobile Device Rules  | `MDRAction`                    | mobile-device-rules-api        |
|                      | `MDRRule`                      | mobile-device-rules-service    |
|                      | `MDRRuleGroup`                 |                                |
|                      | `MDRRuleGroupInstance`         |                                |
| Polls                | `PollsChoice`                  | polls-api                      |
|                      | `PollsQuestion`                | polls-service                  |
|                      | `PollsVote`                    |                                |
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
| Web Content          | `JournalArticle`               | journal-api                    |
|                      | `JournalFolder`                | journal-service                |
|                      | `JournalArticleImage`          |                                |
|                      | `JournalFeed`                  |                                |
| Wiki                 | `WikiNode`                     | wiki-api                       |
|                      | `WikiPage`                     | wiki-service                   |

If your legacy plugins were consuming any of the APIs listed in the table above,
you'll need to update your existing code to consume the API from the new
modules.
