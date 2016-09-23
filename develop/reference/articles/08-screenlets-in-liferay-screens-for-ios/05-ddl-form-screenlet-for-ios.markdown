# DDL Form Screenlet for iOS [](id=ddlformscreenlet-for-ios)

<iframe width="560" height="315" src="https://www.youtube.com/embed/Z1efQ2-LQQc" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Xcode 7.3
- iOS 9 SDK
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP 
- Liferay Screens Compatibility Plugin
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

DDL Form Screenlet can be used to show a collection of fields so that a user can 
fill in their values. Initial or existing values may be shown in the fields. 
Fields of the following data types are supported: 

- *Boolean*: A two state value typically shown using a checkbox.
- *Date*: A formatted date value. The format depends on the device's locale.
- *Decimal, Integer, and Number*: A numeric value.
- *Document and Media*: A file stored on the current device. It can be uploaded 
  to a specific portal repository.
- *Radio*: A set of options to choose from. A single option must be chosen. 
- *Select*: A selection box of options to choose from. A single option must be 
  chosen.
- *Text*: A single line of text.
- *Text Box*: Supports multiple lines of text.

DDL Form Screenlet also supports the following features: 

- Stored records can support a specific workflow.
- A Submit button can be shown at the end of the form.
- Required values and validation for fields can be used. 
- Users can traverse the form fields from the keyboard.
- Supports i18n in record values and labels.

There are also a few limitations you should be aware of when using DDL Form 
Screenlet. They are listed here:

- Nested fields in the data definition aren't supported.
- Selection of multiple items in the Radio and Select data types isn't supported 
  yet.

## Module [](id=module)

- DDL

## Themes [](id=themes)

The Default Theme uses a standard `UITableView` to show a scrollable list of 
fields. Other Themes may use a different component, such as `UICollectionView` 
or others, to show the fields.

![Figure 1: DDL Form Screenlet using the Default (`default`) Theme.](../../images/screens-ios-ddlform.png)

### Custom Cells [](id=custom-cells)

A Theme needs to define a cell view for each field type. For instance, the `xib` 
file `DDLFieldDateTableCell_default` is used to render `Date` fields in the 
Default Theme. 

If you want a specific field to have a unique appearance, you can customize your
field's display by using the following filename pattern, where `XXX` is your
field's name: `DDLCustomFieldXXXTableCell_default`.  For example, the "Are
you a subscriber?" field in screenshot above shows how text fields appear in the
Default Theme. If you want to customize this, you don't need to create an entire
Theme. You just need to create an `xib` file for the field `subscriberName`. The
filename is therefore `DDLCustomFieldSubscriberNameTableCell_default`. Be
careful to keep the same components and `IBOutlet` defined in the custom file.

## Portal Configuration [](id=portal-configuration)

Before using DDL Form Screenlet, you should make sure that Dynamic Data Lists 
and Data Types are configured properly in the portal. Refer to the 
[Creating Data Definitions](/discover/portal/-/knowledge_base/7-0/creating-data-definitions) 
and 
[Creating Data Lists](/discover/portal/-/knowledge_base/7-0/creating-data-lists) 
sections of the User Guide for more details. If Workflow is required, it must 
also be configured. See the 
[Using Workflow](/discover/portal/-/knowledge_base/7-0/using-workflow) 
section of the User Guide for details. 

### Permissions [](id=permissions)

To use DDL Form Screenlet to add new records, you must grant the Add Record 
permission in the Dynamic Data List in the portal. If you want to use DDL Form 
Screenlet to view or edit record values, you must also grant the View and Update 
permissions, respectively. The Add Record, View, and Update permissions are 
highlighted by the red boxes in the following screenshot:

![Figure 2: The permissions for adding, viewing, and editing DDL records.](../../images/screens-portal-permission-ddl.png)

Also, if your form includes at least one Documents and Media field, you must 
grant permissions in the target repository and folder. For more details, see the 
`repositoryId` and `folderId` attributes below.

![Figure 3: The permission for adding a document to a Documents and Media folder.](../../images/screens-portal-permission-folder-add.png)

For more details, please see the User Guide sections 
[Creating Data Definitions](/discover/portal/-/knowledge_base/7-0/creating-data-definitions), 
[Creating Data Lists](/discover/portal/-/knowledge_base/7-0/creating-data-lists), 
and 
[Using Workflow](/discover/portal/-/knowledge_base/7-0/using-workflow). 

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

When loading the form or record, the Screenlet supports the following offline 
mode policies:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The Screenlet loads the form or record from the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet loads the form or record, it stores the received data (record structure and data) in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection.|
| `cache-only` | The Screenlet loads the form or record from the local cache. If the form or record isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance.|
| `remote-first` | The Screenlet requests the form or record from the portal. The Screenlet shows the record or form to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the form or record from the local cache. If the form or record doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `cache-first` | If the form or record exists in the local cache, the Screenlet loads it from there. If it doesn't exist there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

When editing the record, the Screenlet supports the following offline mode 
policies:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The Screenlet sends the record to the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error, but it also discards the record. | Use this policy to make sure the portal always has the most recent version of the record. |
| `cache-only` | The Screenlet stores the record in the local cache. | Use this policy when you need to save the data locally, but don't want to update the data in the portal (update or add record). |
| `remote-first` | The Screenlet sends the record to the portal. If this succeeds, it also stores the record in the local cache for later usage. If a connection issue occurs, then Screenlet stores the record in the local cache with the *dirty flag* enabled. This causes the synchronization process to send the record to the portal when it runs. | Use this policy when you need to make sure the Screenlet sends the record to the portal as soon as the connection is restored. |
| `cache-first` | The Screenlet stores the record in the local cache and then sends it to the remote portal. If a connection issue occurs, then Screenlet stores the record in the local cache with the *dirty flag* enabled. This causes the the synchronization process to send the record to the portal when it runs. | Use this policy when you need to make sure the Screenlet sends the record to the portal as soon as the connection is restored. Compared to `remote-first`, this policy always stores the record in the cache. The `remote-first` policy only stores the record in the event of a network error. |

## Attributes [](id=attributes)

| Attribute | Data Type | Explanation |
|-----------|-----------|-------------| 
| `structureId` | `number` | This is the identifier of a data definition for your site in Liferay. To find the identifiers for your data definitions, click *Admin* from the Dockbar and select *Content*. Then click *Dynamic Data Lists* and click the *Manage Data Definitions* button. The identifier of each data definition is in the ID column of the table that appears. |
| `groupId` | `number` | The site (group) identifier where the record is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `recordSetId` | `number` | The identifier of a dynamic data list. To find the identifiers for your dynamic data lists, click *Admin* from the Dockbar and select *Content*. Then click *Dynamic Data Lists*. The identifier of each dynamic data list is in the ID column of the table that appears. |
| `recordId` | `number` | The identifier of the record you want to show. You can also allow editing of its values. This identifier can be obtained from other methods or delegates. |
| `repositoryId` | `number` | The identifier of the Documents and Media repository to upload to. If this value is `0`, the default repository for the site specified in `groupId` is used. |
| `folderId` | `number` | The identifier of the folder where Documents and Media files are uploaded. If this value is `0`, the root folder is used. |
| `filePrefix` | `string` | The prefix to attach to the names of files uploaded to a Documents and Media repository. A random GUID string is appended following the prefix. |
| `autoLoad` | `boolean` | Sets whether or not the form is loaded when the Screenlet is shown. If `recordId` is set, the record value is loaded together with the form definition. |
| `autoscrollOnValidation` | `boolean` | Sets whether or not the form automatically scrolls to the first failed field when validation is used. |
| `showSubmitButton` | `boolean` | Sets whether or not the form shows a submit button at the bottom. If this is set to `false`, you should call the `submitForm()` method. |
| `editable` | `boolean` | Sets whether the values can be changed by the user. The default is `true`. |

## Methods [](id=methods)

| Method | Return Type | Explanation |
|-----------|-----------|-------------| 
| `loadForm()` | `boolean` | Starts the request to load the form definition. The form fields are shown when the response is received. This method returns `true` if the request is sent. |
| `loadRecord()` | `boolean` | Starts the request to load the record specified in `recordId`. If needed, the form definition is also loaded. The form fields are shown filled with record values when the response is received. This method returns `true` if the request is sent. |
| `submitForm()` | `boolean` | Starts the request to submit form values to the dynamic data list specified in `recordSetId`. All fields are validated prior to submission. Validation errors stop the submit process. |

## Delegate [](id=delegate)

DDL Form Screenlet delegates some events to an object that conforms with the 
`DDLFormScreenletDelegate` protocol. This protocol lets you implement the 
following methods: 

- `- screenlet:onFormLoaded:`: Called when the form is loaded. The second 
  parameter (`record`) contains only field definitions.

- `- screenlet:onFormLoadError:`: Called when an error occurs while loading the 
  form. The `NSError` object describes the error.

- `- screenlet:onRecordLoaded:`: Called when a form with values loads. The 
  second parameter (`record`) contains field definitions and values. The method 
  `onFormLoadResult` is called before `onRecordLoaded`.

- `- screenlet:onRecordLoadError:`: Called when an error occurs while loading a 
  record. The `NSError` object describes the error.

- `- screenlet:onFormSubmitted:`: Called when the form values are successfully 
  submitted to the server.

- `- screenlet:onFormSubmitError:`: Called when an error occurs while submitting 
  the form. The `NSError` object describes the error.

- `- screenlet:onDocumentFieldUploadStarted:`: Called when the upload of a 
  Documents and Media field begins.

- `- screenlet:onDocumentField:uploadedBytes:totalBytes:`: Called when a block 
  of bytes in a Documents and Media field is uploaded. This method is intended 
  to track progress of the uploads.

- `- screenlet:onDocumentField:uploadResult:`: Called when a Documents and Media 
  field upload is completed.

- `- screenlet:onDocumentField:uploadError:`: Called when an error occurs in the 
  Documents and Media upload process. The `NSError` object describes the error.
