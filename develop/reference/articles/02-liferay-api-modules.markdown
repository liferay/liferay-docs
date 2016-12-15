# Liferay API Modules [](id=finding-liferay-api-modules)

This document explains how to find Liferay API modules, specify them as
dependencies, and find their Javadoc. 

Let's start by considering Liferay module information for specifying to Ivy,
Gradle, and Maven dependency frameworks. This information includes the following
dependency attributes:

- Group name
- Module name
- Module version

The next table shows the group value to specify to Ivy, Gradle, and Maven. 

## Module Group Table [](id=module-group-table)

| &nbsp;**Project Dependency Type** | &nbsp;**Variable** | &nbsp;**Value**     |
|-------------------------------------|-----------------|-----------------|
| Ivy                                      | `org`           | `com.liferay` |
| Gradle                                 | `group`        | `com.liferay` |
| Maven                                 | `groupId`     | `com.liferay` |


Specify group value `com.liferay` for all your dependencies on Liferay modules. 

Next, look up the API module's name. 

## API Module Names Table [](id=api-module-names-table)

This table maps Liferay components to their commonly used classes and API module
names. 

| &nbsp;Component | &nbsp;Classes | &nbsp;Module<br>(Ivy `name` /<br>Gradle `name` /<br>Maven `artifactId`) |
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

## Module Version [](id=module-version)

The last module dependency item to specify is the module version. The Liferay
Portal CE artifact repository has a folder for each module version. To browse
the version folders, visit the artifact repository at
<https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/>
and click on the module's folder. 

Liferay module names start with `com.liferay`, followed by a dot ".", and
the component's name. Here's the entire naming convention:

    com.liferay.[component].api-[version].jar

For example, the JAR file for version 1.0.1 of the Web Content (Journal)
component's API module looks like this:

    com.liferay.journal.api-1.0.1.jar

Ivy, Gradle, and Maven dependencies on this JAR file are demonstrated as
follows. 

### Ivy [](id=ivy)

	<dependency name="com.liferay.journal.api" org="com.liferay" rev="1.0.1" />

### Gradle [](id=gradle)

	dependencies {
		provided group: "com.liferay", name: "com.liferay.journal.api", version: "1.0.1"
		...
	}

### Maven [](id=maven)

	<dependency>
		<groupId>com.liferay.faces</groupId>
		<artifactId>com.liferay.journal.api</artifactId>
		<version>1.0.1</version>
	</dependency>

Now that you've specified dependencies on Liferay API modules, consider their
Javadoc. 

## Java API [](id=java-api)

A module's Javadoc describes its Java API. 

Javadoc for the modules in the latest GA release of Liferay Portal 7.0 CE are
available online at <https://docs.liferay.com/portal/7.0/javadocs/> and for
download from [Liferay Downloads](https://www.liferay.com/downloads). All
versions of modules for CE are available for download from the [Liferay CE
artifact repository](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/).

Here is the common naming convention used for the module packages:

- `com.liferay.[component].constants` - Classes that specify module specific
constant values, such as web keys
- `com.liferay.[component].exception` - Module specific exception classes
- `com.liferay.[component].model` - Model entity interfaces, entity wrapper
classes, and SOAP classes 
- `com.liferay.[component].service` - Local and remote service interfaces
- `com.liferay.[component].service.persistence` - Entity persistence interfaces,
entity finder interfaces, and static utilities 
- `com.liferay.[component].util` - Utility classes

+$$$

**Important**: The API called *portal-service* in Liferay Portal 6.2 and previous versions is
called *portal-kernel* in @product-ver@. 

$$$

Portal kernel API modules have *kernel* packages. Here is the common naming
convention used for portal-kernel module packages: 

- `com.liferay.[component].kernel.constants` - Classes that specify module
specific constant values, such as web keys 
- `com.liferay.[component].kernel.exception` - Module-specific exception classes
- `com.liferay.[component].kernel.model` - Model entity interfaces, entity
wrapper classes, and SOAP classes 
- `com.liferay.[component].kernel.service` - Local and remote service interfaces
- `com.liferay.[component].kernel.service.persistence` - Entity persistence
interfaces, entity finder interfaces, and static utilities 
- `com.liferay.[component].kernel.util` - Utility classes

You now know how to find Liferay component API modules and their Javadoc. 

## Related Articles [](id=related-articles)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)

[Adapting to @product-ver@'s API with the Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)

