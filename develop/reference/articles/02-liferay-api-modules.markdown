# Liferay API Modules [](id=finding-liferay-api-modules)

The following table maps commonly used @product@ components to their API modules
and key classes. You 
[configure dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies) 
on the component API modules to use them. 

## API Modules Table [](id=api-module-names-table)

| Component | Classes | Module Symbolic Name (Artifact ID) |
| :-------------------- | :---------------- | :----------------------------------------------------------------------------------- |
| Application List    | `PanelApp`                                | `com.liferay.application.list.api` |
|                          | `PanelCategory`                         |                                                    |
|                          | `PanelEntry`                               |                                                       |
| Background Tasks | `BackgroundTask[Local]ServiceUtil` | `com.liferay.portal.background.task.api` |
| Blogs             | `BlogsEntry[Local]ServiceUtil`             | `com.liferay.blogs.api`                       |
| Bookmarks      | `BookmarksEntry[Local]ServiceUtil`      | `com.liferay.bookmarks.api`           |
|                      | `BookmarksFolder[Local]ServiceUtil`     |                                               |
| Calendar         | `Calendar[Local]ServiceUtil`                    | `com.liferay.calendar.api` |
|                      | `CalendarBooking[Local]ServiceUtil`             |                                 |
|                      | `CalendarImporter`                                       |                            |
|                      | `CalendarNotificationTemplate[Local]ServiceUtil` |                        |
|                      | `CalendarResource[Local]ServiceUtil`        |                                |
| Comment        | `Comment`                                    | `com.liferay.comment.api` |
|                      | `DiscussionComment`                     |                                          |
| Contacts         | `Entry`                                         | `com.liferay.contacts.api`          |
| Document Library    | `DLFileEntry[Local]ServiceUtil` | `com.liferay.document.library.api` |
|                      | `DLContent[Local]ServiceUtil`         |                                                   |
|                      | `DLFileEntryType[Local]ServiceUtil` |                                                     |
|                      | `DLFileVersion[Local]ServiceUtil`     |                                                      |
|                      | `DLFolder[Local]ServiceUtil`              |                                                      |
|                      | `DLSyncEvent[Local]ServiceUtil`            |                                                     |
| Dynamic Data Lists | `DDLRecord[Local]ServiceUtil`           | `com.liferay.dynamic.data.lists.api` |
|                            | `DDLRecordSet[Local]ServiceUtil`       |                                                   |
|                            | `DDLRecordVersion[Local]ServiceUtil` |                                                    |
| Dynamic Data Mapping | `DDMContent[Local]ServiceUtil`    | `com.liferay.dynamic.data.mapping.api` |
|                      | `DDMStructure[Local]ServiceUtil`             |                                |
|                      | `DDMStorageLink[Local]ServiceUtil`         |                                |
|                      | `DDMStructureLayout[Local]ServiceUtil`   |                                |
|                      | `DDMStructureLink[Local]ServiceUtil`       |                                |
|                      | `DDMStructureVersion[Local]ServiceUtil ` |                                |
|                      | `DDMTemplate[Local]ServiceUtil`            |                                |
|                      | `DDMTemplateLink[Local]ServiceUtil`      |                                    |
|                      | `DDMTemplateVersion[Local]ServiceUtil` |                                       |
| Export / Import  | `ExportImportConfiguration`               | `com.liferay.exportimport.api` |
|                        | `StagingServiceUtil[Local]ServiceUtil`   |                                              |
| Flags                | `FlagsEntryServiceUtil`                       | `com.liferay.flags.api`                  |
| Invitation          | `MemberRequest[Local]ServiceUtil`      | `com.liferay.invitation.invite.members.api` |
| Item Selector            | `ItemSelector`                            | `com.liferay.item.selector.api`             |
| Item Selector Criteria | `FileEntryItemSelectorReturnType` | `com.liferay.item.selector.criteria.api`|
|                               | `UploadableFileReturnType`         |                                                 |
|                               | `URLItemSelectorReturnType`      |                                             |
|                               | `UUIDItemSelectorReturnType`  |                                          |
| Lock                 | `Lock`                         | `com.liferay.portal.lock.api`              |
| Map               | `MapProvider`               | `com.liferay.map.api`                    |
| Marketplace    | `App`                          | `com.liferay.marketplace.api`        |
|                      | `Module`                       |                                            |
| Mentions         | `MentionsNotifier`          | `com.liferay.mentions.api`     |
|                      | `MentionsUserFinder`            |                                        |
|                      | `MentionsUtil`                          |                                            |
| Message Boards  | `MBMessage[Local]ServiceUtil`   | `com.liferay.message.boards.api` |
|                      | `MBCategory[Local]ServiceUtil`       |                                |
|                      | `MBThread[Local]ServiceUtil`             |                                |
|                      | `MBDiscussion[Local]ServiceUtil`        |                                   |
| Microblogs           | `MicroblogsEntry[Local]ServiceUtil` | `com.liferay.microblogs.api` |
| Mobile Device Rules  | `MDRAction[Local]ServiceUtil`       | `com.liferay.mobile.device.rules.api` |
|                      | `MDRRule[Local]ServiceUtil`                      |                                           |
|                      | `MDRRuleGroup[Local]ServiceUtil`              |                                       |
|                      | `MDRRuleGroupInstance[Local]ServiceUtil`   |                                   |
| Polls                | `PollsChoice[Local]ServiceUtil`                  | `com.liferay.polls.api` |
|                      | `PollsQuestion[Local]ServiceUtil`                |                                            |
|                      | `PollsVote[Local]ServiceUtil`                    |                                                          |
| Portal Access Policy     | `SAPEntry[Local]ServiceUtil`   | `com.liferay.portal.security.service.access.policy.api` |
| Portal Settings            | `PortalSettings`               | `com.liferay.portal.settings.api` |
| Portlet Configuration            | `PortletConfigurationIcon` | `com.liferay.portlet.configuration.icon.locator.api` |
|                                         | `PortletToolbar`               | `com.liferay.portlet.configuration.toolbar.contributor.locator.api` |
| Private Messaging            | `UserThread[Local]ServiceUtil`  |           `com.liferay.social.privatemessaging.api` |
| Product Navigation  | `ProductNavigationControlMenuCategory` | `com.liferay.product.navigation.control.menu.api`  |
|                             | `ProductNavigationControlMenuEntry`   |                                  |
| Ratings            | `RatingsEntry[Local]ServiceUtil`  | `com.liferay.ratings.api` |
| Reports Engine  | `RulesEngine`     | `reports.engine.api`                         |
|                       | `RulesLanguage`     |                                                  |
|                       | `Fact`                       |                                              |
|                       | `Query`                         |                                         |
| Screens   | `ScreensAssetEntryServiceUtil`    | `com.liferay.screens.api`   |
|              | `ScreensDDLRecordServiceUtil`     |                                         |
|              | `ScreensJournalArticleServiceUtil` |                                            |
| Security Audit | `AuditEvent`               | `com.liferay.portal.security.audit.api` |
|                     | `AuditEventManager`    |                                                         |
|                     | `AuditConfiguration`     |                                                            |
| Security Import / Export | `UserExporter`    | `com.liferay.portal.security.exportimport.api` |
|                                   | `UserImporter`   |                                                              |
|                                   | `UserOperation` |                                                           |
| Shopping Cart  | `ShoppingCart[Local]ServiceUtil`        | `com.liferay.shopping.api` |
|                      | `ShoppingCategory[Local]ServiceUtil`  |                                      |
|                      | `ShoppingCoupon[Local]ServiceUtil`    |                                      |
|                      | `ShoppingItem[Local]ServiceUtil`        |                                      |
|                      | `ShoppingItemPrice[Local]ServiceUtil`  |                                     |
|                      | `ShoppingOrder[Local]ServiceUtil`        |                                     |
|                      | `ShoppingOrderItem[Local]ServiceUtil`  |                                     |
| Site            | `GroupSearchProvider`                             | `com.liferay.site.api`           |
| Social Networking | `MeetupsEntry[Local]ServiceUtil`    | `com.liferay.social.networking.api`|
|                          | `MeetupsRegistration[Local]ServiceUtil` |                                           |
|                         | `WallEntry[Local]ServiceUtil`                |                                       |
| Staging            | `Staging[Local]ServiceUtil`               | `com.liferay.staging.api`   |
| Web Content   | `JournalArticle[Local]ServiceUtil`   | `com.liferay.journal.api`         |
|                      | `JournalFolder[Local]ServiceUtil`          |                                     |
|                      | `JournalArticleImage[Local]ServiceUtil` |                                     |
|                      | `JournalFeed[Local]ServiceUtil`            |                                      |
| Wiki               | `WikiNode[Local]ServiceUtil`                   | `com.liferay.wiki.api`     |
|                      | `WikiPage[Local]ServiceUtil`                     |                                      |
| XStream Configurator | `XStreamConfigurator`          | `com.liferay.xstream.configurator.api` |

For reference documentation on these APIs and others, see the the app reference 
docs at 
[@app-ref@](@app-ref@) and the @product@ core reference docs at 
[@platform-ref@/7.0-latest](@platform-ref@/7.0-latest). 

## Related Articles [](id=related-articles)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Adapting to @product-ver@'s API with the Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
