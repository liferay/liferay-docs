# Leveraging Template Editors for Notifications [](id=leveraging-template-editors-for-notifications)

Designer lets you leverage the FreeMarker editor to customize templates for your 
workflow notifications. A FreeMarker editor comes bundled with Developer Studio.

## Creating Notifications [](id=creating-notifications)

To access the template editor, click on the node of your choice and select the
*Notifications* sub-tab in the *Properties* view. Create a new notification by
clicking the green "plus" symbol. 

![Figure 1: When creating a notification, Developer Studio offers several options, like choosing  a template language.](../../images/kaleo-16.png)

There are several fields to fill in for your notification: 

- **Name**
- **Template Language**
- **Execution Type**
- **Notification Type**
- **Notification Transports**
- **Addresses**

Click the pencil icon to open the editor associated with your notification's
template language. Like the script editor, the template editor's *Palette* view
lists entities that you can drag and drop onto your workflow diagram. 

Because Developer Studio lets you leverage a full featured FreeMarker template 
editor, content-assist is available for you to use right away. For example, 
content-assist suggests FreeMarker functions when you are editing your 
notification template. In addition, when you're doing a FreeMarker variable 
insertion, the editor gives you all the available variables that are a part of 
the Kaleo workflow. You can visit the documentation pages for [FreeMarker](http://freemarker.org/docs/) 
for more information on the variables and functions available.

+$$$

**Note:** Similar to the bundled script editors, Developer Studio lets you use
the FreeMarker template editor to customize notifications in your workflow
definition.

$$$

Let's continue editing our ticket process workflow. After completion of the QA
task assignments, our Project Management team should be notified. To set up a
notifcation email, we'll add a new task node that transitions from our join
node. This new task node will hold our email notification. Typically, we'd
assign this task to a project management role and email it to the project
management team's email alias. For demonstration purposes, we'll use "Joe Blogs"
for both purposes. As mentioned previously, you can specify an existing user
that has an email or create a user with screen name "joe" having your email
address. This process is similar to how we assigned our previous task nodes. 

1.  Drag a new *Task* node onto your workflow diagram. 

2.  Name the new node *Project Management* and select *Assign to a specific
    user*. 

3.  Click *Next*. 

4.  Enter *Screen name* "joe". 

5.  Click *Finish*. 

Now we just need to incorporate the Project Management node into our
workflow process. Add a transition named *Passed QA* from our join node to the
Project Management node. Lastly, add a transition named *Completed* from our
Project Management node to our *EndNode*. Here's an updated screenshot of what
your workflow diagram should look like: 

![Figure 2: Our workflow diagram is busy now; but we're not finished yet!](../../images/kaleo-31.png)

Let's create our email notification for our Project Management task node next.
Click on the Project Management node and select *Notifications* in the
Properties window. 

To create the email notification, follow these steps: 

1.  Click the green "plus" symbol to create a new notification. 

2.  In the **Name** text field, enter "ticket process email".

3.  Select *FreeMarker* from the **Template Language** drop-down menu.

4.  Select *On entry* from the **Execution type** drop-down menu.

5.  Select *email* under **Notification transports**.

Now open the FreeMarker template editor by clicking the pencil icon beneath the
green "plus" symbol. 

Insert FreeMarker code into the FreeMarker editor to specify your email
notification template. We've provided an example email notification code snippet
written in FreeMarker. 

    <#assign comments = taskComments!"">
    <#assign portalURL = serviceContext.portalURL!"">
    <#assign wTasksURL = portalURL+"/group/control_panel/manage?p_p_id=153&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&doAsGroupId="+groupId+"&refererPlid=">

    <!-- email body -->
    <p> There is a new submission of ${entryType} waiting for review in the workflow. </p>

    <!-- personal message to assignee -->
    <p> Please review the code waiting for you in your workflow tasks.
    <#if comments != "" > <br/> Assignment comment says: <strong>${comments}</strong> </#if>
    </p>
    <p> <a href="${wTasksURL}">Click here</a> to see workflow tasks assigned to you. </p>

    <!-- signature -->
    <p>Sincerely,<br /><strong>Liferay Portal Workflow</strong></p>

The snapshot below illustrates what the snippet sends to the configured email
recipient. 

![Figure 3: This is how the email notification will appear when it's received.](../../images/kaleo-43.png)

Your email notification is set up! Now, when the Project Management task node is
activated in the workflow, the specified user (i.e. *joe*) will receive the
notification email, all dressed up with your FreeMarker template (you might say
it's all dressed up with somewhere to go). 

With template editors, customizing your notification templates is easier than
ever. FreeMarker comes bundled with Developer Studio so it's obviously the
simplest solution.

Here's what the XML source looks like (with the embedded FreeMarker template)
for the Project Management task we created:

    <task>
        <name>project management</name>
        <actions>
            <notification>
                <name>ticket process email</name>
                <template>/* specify task notification template */
                    &lt;#assign comments = taskcomments!&quot;&quot;&gt;
                    &lt;#assign portalurl = servicecontext.portalurl!&quot;&quot;&gt;
                    &lt;#assign wtasksurl = portalurl+&quot;/group/control_panel/manage?p_p_id=153&amp;p_p_lifecycle=0&amp;p_p_state=maximized&amp;p_p_mode=view&amp;doasgroupid=&quot;+groupid+&quot;&amp;refererplid=&quot;&gt;
                    
                    &lt;!-- email body --&gt;
                    &lt;p&gt; there is a new submission of ${entrytype} waiting for review in the workflow. &lt;/p&gt;
                    
                    &lt;!-- personal message to assignee --&gt;
                    &lt;p&gt; please review the code waiting for you in your workflow tasks.
                    &lt;#if comments != &quot;&quot; &gt; &lt;br/&gt; assignment comment says: &lt;strong&gt;${comments}&lt;/strong&gt; &lt;/#if&gt;
                    &lt;/p&gt;
                    &lt;p&gt; &lt;a href=&quot;${wtasksurl}&quot;&gt;click here&lt;/a&gt; to see workflow tasks assigned to you. &lt;/p&gt;
                    
                    &lt;!-- signature --&gt;
                    &lt;p&gt;sincerely,&lt;br /&gt;&lt;strong&gt;liferay portal workflow&lt;/strong&gt;&lt;/p&gt;
                </template>
                <template-language>freemarker</template-language>
                <notification-type>email</notification-type>
                <execution-type>onEntry</execution-type>
            </notification>
        </actions>
        <assignments>
            <user>
                <screen-name>joe</screen-name>
            </user>
        </assignments>
        <transitions>
            <transition>
                <name>Completed</name>
                <target>EndNode</target>
            </transition>
        </transitions>
    </task>

In the next section you'll see a list of workflow and service context content
you can use when creating a customized script or template. 

## Workflow Context and Service Context Variables [](id=workflow-context-and-service-context-variables)

A context variable provides a uniform variable to insert into your templates and
scripts. When executed, a context variable is automatically deleted and replaced
with the value pertaining to that key. When you create notifications for a
workflow, assign Liferay portal context variables for a cleaner and more
efficient process. With context variables, your notifications become more
customizable, rather than following the same format for every recipient. The
context variables you declare in your notifications refer to your Liferay
instance and the values it holds for your declarations. 

Below you'll see tables listing numerous context variables and service context
content. The context variables are the first table, followed by the service
context content for web content, blog entries, and message board messages. We've
separated service context content from the workflow context variables because
service context keys depend on asset type, while context variables don't. Also,
note the asterisks (`*`); they're used to flag context variables that depend on
workflow activity. 

___

***Workflow* Context Variables**

 key | type | description |
---- | ---- | ----------- |
`accountPermission` | | |
`arrayUtil` | | |
`auditMessageFactoryUtil` | | |
`auditRouterUtil` | | |
`browserSniffer` | | |
`calendarFactory` | | |
`commonPermission` | | |
 `companyid` | &nbsp;&nbsp;java.lang.string&nbsp;&nbsp;&nbsp; | primary key of the company |
`dateFormatFactory` | | |
`dateFormats` | | |
`dateUtil` | | |
`ddmUtil` | | |
`dlUtil` | | |
 `entryclassname` | &nbsp;&nbsp;java.lang.string | class name for entry used by the task (e.g. com.liferay.portlet.journal.model.journalarticle) |
 `entryclasspk` | &nbsp;&nbsp;java.lang.string | primary key of the entry class |
 `entrytype` | &nbsp;&nbsp;java.lang.string | type of entry used by the task (e.g. web content, blog entry, mb message) |
`enumUtil` | | |
`expandoColumnLocalService` | | |
`expandoRowLocalService` | | |
`expandoTableLocalService` | | |
`expandoValueLocalService` | | |
`freeMarkerPortletPreferences` | | |
`getterUtil` | | |
 `groupid` | &nbsp;&nbsp;java.lang.string | primary key of the assigned group |
`groupPermission` | | |
`htmlUtil` | | |
`httpUtil` | | |
`imageToken` | | |
`journalContentUtil` | | |
`jsonFactoryUtil` | | |
`kaleoInstanceToken` | | |
`kaleoTaskInstanceToken` | | |
`languageUtil` | | |
`layoutPermission` | | |
`localeUtil` | | |
`locationPermission` | | |
`objectUtil` | | |
`organizationPermission` | | |
`paramUtil` | | |
`passwordPolicyPermission` | | |
`portal` | | |
`portalPermission` | | |
`portalUtil` | | |
`portletModeFactory` | | |
`portletPermission` | | |
`portletURLFactory` | | |
`prefsPropsUtil` | | |
`propsUtil` | | |
`randomizer` | | |
`rolePermission` | | |
`saxReaderUtil` | | |
`serviceContext` | | |
`serviceLocator` | | |
`sessionClicks` | | |
`staticFieldGetter` | | |
`staticUtil` | | |
`stringUtil` | | |
 `taskcomments*` | &nbsp;&nbsp;java.lang.string | workflow comments assigned to the task |
 `taskname*` | &nbsp;&nbsp;java.lang.string | workflow task that activates the notification (e.g. review) |
`timeZoneUtil` | | |
 `transitionname*` | &nbsp;&nbsp;java.lang.string | name of transition pointing to the task (e.g. approve) |
`unicodeFormatter` | | |
`unicodeLanguageUtil` | | |
`userGroupPermission` | | |
 `userid` | &nbsp;&nbsp;java.lang.string | primary key of the assigned user |
`userName` | | |
`userPermission` | | |
`utilLocator` | | |
`validator` | | |
`velocityTaglib_layoutIcon` | | |
`webServerToken` | | |
`windowStateFactory` | | |
`workflowTaskAssignees` | | |

---


***Web Content* Service Context Variables - obtain via key servicecontext**

 key | type | description |
---- | ---- | ----------- |
 `articleid` | &nbsp;&nbsp;java.lang.string&nbsp;&nbsp;&nbsp; | primary key of the web content |
 `articleurl` | &nbsp;&nbsp;java.lang.string | link to the web content in maximized mode |
 `assetlinkentryids` | &nbsp;&nbsp;java.lang.string | primary keys of the asset entries linked to the web content |
 `assetlinkssearchcontainerprimarykeys` | &nbsp;&nbsp;java.lang.string | primary keys of the asset link search container |
 `assettagnames` | &nbsp;&nbsp;java.lang.string | tag names applied the asset |
 `autoarticleid` | &nbsp;&nbsp;java.lang.string | boolean variable indicating whether an article id is generated (e.g. false) |
 `classnameid` | &nbsp;&nbsp;java.lang.string | primary key of the class name used by the task |
 `classpk` | &nbsp;&nbsp;java.lang.string | primary key of the model entity |
 `content` | &nbsp;&nbsp;java.lang.string | content of the web content |
 `defaultLanguageId` | &nbsp;&nbsp;java.lang.String | Primary key of the default language (e.g. en_US) |
 `description_en_US` | &nbsp;&nbsp;java.lang.String | Description of the web content (in English) |
 `displayDateDay` | &nbsp;&nbsp;java.lang.String | Calendar day the web content is set to display (e.g. 12) |
 `displayDateHour` | &nbsp;&nbsp;java.lang.String | Hour the web content is set to display (e.g. 4) |
 `displayDateMinute` | &nbsp;&nbsp;java.lang.String | Minute the web content is set to display (e.g. 26) |
 `displayDateMonth` | &nbsp;&nbsp;java.lang.String | Month the web content is set to display (e.g. 5) |
 `displayDateYear` | &nbsp;&nbsp;java.lang.String | Year the web content is set to display (e.g. 2012) |
 `doAsGroupId` | &nbsp;&nbsp;java.lang.String | Primary key of the organization associated with the web content |
 `folderId` | &nbsp;&nbsp;java.lang.String | Primary key of the web content's folder |
 `indexable` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the web content is searchable (e.g. true) |
 `indexableCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Searchable* checkbox is checked (e.g. false) |
 `inputPermissionsShowOptions` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether permission options are viewable (e.g. true) |
 `inputPermissionsViewRole` | &nbsp;&nbsp;java.lang.String | Role type that has permission to view web content (e.g. Site Member) |
 `languageId` | &nbsp;&nbsp;java.lang.String | Primary key of the selected language (e.g. en_US) |
 `localized` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Localizable* checkbox is selected (e.g. false) |
 `neverExpire` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the web content is set to expire (e.g. true) |
 `neverExpireCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Never Auto Expire* checkbox is checked (e.g. false) |
 `neverReview` | &nbsp;&nbsp;java.lang.String |  Boolean variable indicating whether the web content is set to review (e.g. true) |
 `neverReviewCheckbox` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the *Never Review* checkbox is checked (e.g. false) |
 `refererPlid` | &nbsp;&nbsp;java.lang.String | Primary key of the page hosting the web content |
 `smallImage` | &nbsp;&nbsp;java.lang.String | Indicates whether a small image is being used (e.g. on) |
 `smallImageURL` | &nbsp;&nbsp;java.lang.String | URL for the web content's attached image |
 `structureDescription` | &nbsp;&nbsp;java.lang.String | Description of the configured structure |
 `structureId` | &nbsp;&nbsp;java.lang.String | Primary key of the configured structure |
 `structureName` | &nbsp;&nbsp;java.lang.String | Name of the configured structure |
 `structureXSD` | &nbsp;&nbsp;java.lang.String | The XML schema definition used for the configured structure |
 `templateId` | &nbsp;&nbsp;java.lang.String | Primary key of the configured template |
 `title_en_US` | &nbsp;&nbsp;java.lang.String | Title of the web content (in English) |
 `type` | &nbsp;&nbsp;java.lang.String | Categorization type associated with the web content  (e.g. Press Release) |
 `variableName` | &nbsp;&nbsp;java.lang.String | Custom variable name set for the web content's configured structure (e.g. Content) |
 `version` | &nbsp;&nbsp;java.lang.String | Current version of the web content (e.g. 1.0) |
 `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 1) |
---

***Blog Entry* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
 `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String&nbsp;&nbsp;&nbsp; | Primary keys of the asset entries linked to the blog entry |
 `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the blog entry |
 `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. history, news, programming) |
 `attachments` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if blog entry has any attachments (e.g. true) |
 `content` | &nbsp;&nbsp;java.lang.String | Content of the blog entry |
 `description` | &nbsp;&nbsp;java.lang.String | Description of the blog entry (e.g. The comparison between two Fortune 500 companies) |
 `displayDateAmPm` | &nbsp;&nbsp;java.lang.String | Time "period" (based on the 12-hour clock) the blog entry is set to display (AM=0, PM=1) |
 `displayDateDay` | &nbsp;&nbsp;java.lang.String | Calendar day the blog entry is set to display (e.g. 3) |
 `displayDateHour` | &nbsp;&nbsp;java.lang.String | Hour the blog entry is set to display (e.g. 26) |
 `displayDateMinute` | &nbsp;&nbsp;java.lang.String | Minute the blog entry is set to display (e.g. 32) |
 `displayDateMonth` | &nbsp;&nbsp;java.lang.String | Month the blog entry is set to display (e.g. 8) |
 `displayDateYear` | &nbsp;&nbsp;java.lang.String | Year the blog entry is set to display (e.g 2012) |
 `doAsGroupId` | &nbsp;&nbsp;java.lang.String | Primary key of the organization associated with the blog entry |
 `editor` | &nbsp;&nbsp;java.lang.String | Content of the blog entry (equivalent to the `content` value) |
 `entryId` | &nbsp;&nbsp;java.lang.String | Primary key of the blog entry |
 `refererPlid` | &nbsp;&nbsp;java.lang.String | Primary key of the page hosting the blog entry |
 `smallImage` | &nbsp;&nbsp;java.lang.String | Indicates whether a small image is being used (e.g. on) |
 `smallImageURL` | &nbsp;&nbsp;java.lang.String | URL for the blog entry's attached image |
 `title` | &nbsp;&nbsp;java.lang.String | Title of the blog entry (e.g. My Blog Entry) |
 `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 2) |
---

***Message Board Message* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
 `anonymous` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if message is anonymous (e.g. true) |
 `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the message |
 `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset link search container |
 `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. sea, sailing, swimming) |
 `attachments` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating whether the message has any attachments (e.g. false) |
 `body` | &nbsp;&nbsp;java.lang.String | Content of the message |
 `editor` | &nbsp;&nbsp;java.lang.String | Content of the message (equivalent to the `body` value) |
 `mbCategoryId` | &nbsp;&nbsp;java.lang.String | Primary key of the message's category |
 `messageId` | &nbsp;&nbsp;java.lang.String | Primary key of the message |
 `parentMessageId` | &nbsp;&nbsp;java.lang.String | Primary key of the message's parent message |
 `preview` | &nbsp;&nbsp;java.lang.Boolean&nbsp;&nbsp; | Boolean variable indicating if message is available to preview (e.g. true) |
 `question` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if message is marked as a question (e.g. false) |
 `subject` | &nbsp;&nbsp;java.lang.String | Subject line of the message (e.g. My MB Subject) |
 `threadId` | &nbsp;&nbsp;java.lang.String | Primary key of the message board thread hosting the message |
 `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 3) |
---

***Documents and Media* Service Context Variables - obtain via key serviceContext**

 Key | Type | Description |
---- | ---- | ----------- |
<!-- REMOVE --> `anonymous` | &nbsp;&nbsp;java.lang.String | Boolean variable indicating if message is anonymous (e.g. true) |
 `assetLinkEntryIds` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset entries linked to the message |
 `assetLinksSearchContainerPrimaryKeys` | &nbsp;&nbsp;java.lang.String | Primary keys of the asset link search container |
 `assetTagNames` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. sea, sailing, swimming) |
 `cmd` | &nbsp;&nbsp;java.lang.String | Tag names applied the asset (e.g. sea, sailing, swimming) |
 `defaultLanguageId` | | |
 `description` | | |
 `fileEntryId` | | |
 `fileEntryTypeId` | | |
 `folderId` | | |
 `formDate` | | |
 `groupPermissions` | | |
 `guestPermissions` | | |
 `inputPermissionsShowOptions` | | |
 `inputPermissionsViewRole` | | |
 `p_p_col_count` | | |
 `p_p_col_id` | | |
 `p_p_id` | | |
 `p_p_lifecycle` | | |
 `p_p_mode` | | |
 `p_p_state` | | |
 `referringPortletResource` | | |
 `redirect` | | |
 `repositoryId` | | |
 `struts_action` | | |
 `title` | | |
 `uploadExceptionRedirect` | | |
 `uploadProgressId` | | |
 `workflowAction` | &nbsp;&nbsp;java.lang.String | Numerical value for the workflow action in progress (e.g. 3) |
---

<!--Rich says change these to HTML. -Russ -->

Now that the Ticket Process Definition is complete, learn how to publish it and
configure it inside the portal
[next](/develop/tutorials/-/knowledge_base/6-2/publishing-and-configuring-workflows).

<!-- ## Recent Articles -->
