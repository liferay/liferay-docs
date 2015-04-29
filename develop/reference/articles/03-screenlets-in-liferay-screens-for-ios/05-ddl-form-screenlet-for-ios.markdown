# DDLFormScreenlet for iOS [](id=ddlformscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.3.x
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin installed

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `DDLFormScreenlet` can be used to show a collection of fields so that a user 
can fill in their values. Initial or existing values may be shown in the fields. 
Fields of the following data types are supported:

- *Boolean*: A two state value typically shown using a checkbox.
- *Date*: A formatted date value. The format depends on the device's locale.
- *Decimal, Integer, and Number*: A numeric value.
- *Document and Media*: A file stored on the current device. It can be uploaded 
  to a specific portal repository.
- *Radio*: A set of options to choose from. A single option must be chosen. 
- *Select*: A dropdown list of options to choose from. A single option must be 
  chosen.
- *Text*: A single line of text.
- *Text Box*: Supports multiple lines of text.

The `DDLFormScreenlet` also supports the following features:

- Stored records can support a specific workflow.
- A Submit button can be shown at the end of the form.
- Required values and validation for fields can be used. 
- Users can traverse the form fields from the keyboard.
- Supports i18n in record values and labels.

There are also a few limitations you should be aware of when using 
`DDLFormScreenlet`. They are listed here:

- Nested fields in the data definition aren't supported.
- Selection of multiple items in the Radio and Select data types isn't supported 
  yet.

## Module [](id=module)

- DDL

## Themes [](id=themes)

The Default theme uses a standard `UITableView` to show a scrollable list of 
fields. Other themes may use a different component, such as `UICollectionView` 
or others, to show the fields.

![The `DDLForm` screenlet using Default theme.](../../images/screens-ios-ddlform.png)

### Custom Cells [](id=custom-cells)

A theme needs to define a cell view for each field type. For instance, the `xib` 
file `DDLFieldDateTableCell_default` is used to render `Date` fields in the 
`Default` theme. 

What if you want a specific field to have a unique appearance though? No 
problem! You can customize your field's display by using the following filename 
pattern, where `XXX` is your field's name: `DDLCustomFieldXXXTableCell_default`. 
For example, the the "Are you a subscriber?" field in screenshot above shows how 
text fields appear in the Default theme. If you want to customize this, you 
don't need to create an entire theme. You just need to create an `xib` file for 
the field `subscriberName`. The filename is therefore 
`DDLCustomFieldSubscriberNameTableCell_default`. Be careful to keep the same 
components and `IBOutlet` defined in the custom file.

## Portal Configuration [](id=portal-configuration)

Dynamic Data Lists and Data Types should be configured properly in the portal. 
Refer to the [Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists) 
sections of the User Guide for more details. If Workflow is required, it also 
must be configured. Please see the [Using Workflow](/portal/-/knowledge_base/6-2/using-workflow) 
section of the User Guide for details.

### Permissions [](id=permissions)

To add new records using this screenlet, you must grant the Add Record 
permission in the used Dynamic Data List:

![The Add Record permission.](../../images/screens-portal-permission-record-add.png)

If you want to view or edit record values, you must also grant the View and 
Update permissions, respectively:

![The permissions for viewing and editing records.](../../images/screens-portal-permission-record-edit.png)

Also, if your form includes the Documents and Media field, you must grant 
permissions in the target repository and folder. For more details, see the 
`repositoryId` and `folderId` attributes below.

![The permission for adding a folder.](../../images/screens-portal-permission-folder-add.png)

For more details, please see the User Guide sections [Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-), 
[Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists), 
and [Using Workflow](/portal/-/knowledge_base/6-2/using-workflow).

## Attributes [](id=attributes)

| Attribute | Data Type | Explanation |
|-----------|-----------|-------------| 
| `structureId` | `number` | This is the identifier of a data definition for your site in Liferay. To find the identifiers for your data definitions, click *Admin* from the Dockbar and select *Content*. Then click *Dynamic Data Lists* and click the *Manage Data Definitions* button. The identifier of each data definition is in the ID column of the table that appears. |
| `groupId` | `number` | The site (group) identifier where the record is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `recordSetId` | `number` | The identifier of a dynamic data list. To find the identifiers for your dynamic data lists, click *Admin* from the Dockbar and select *Content*. Then click *Dynamic Data Lists*. The identifier of each dynamic data list is in the ID column of the table that appears. |
|  `recordId` | `number` | The identifier of the record you want to show. You can also allow editing of its values. This identifier can be obtained from other methods or delegates. |
|  `repositoryId` | `number` | The identifier of the Documents and Media repository to upload to. If this value is `0`, the default repository for the site specified in `groupId` is used. |
|  `folderId` | `number` | The identifier of the folder where Documents and Media files are uploaded. If this value is `0`, the root file will be used. |
|  `filePrefix` | `string` | The prefix to attach to the names of files uploaded to a Documents and Media repository. A random GUID string is appended following the prefix. |
|  `autoLoad` | `boolean` | Sets whether or not the form is loaded when the screenlet is shown. If `recordId` is set, the record value is loaded together with the form definition. |
|  `autoscrollOnValidation` | `boolean` | Sets whether or not the form automatically scrolls to the first failed field when validation is used. |
|  `showSubmitButton` | `boolean` | Sets whether or not the form shows a submit button at the bottom. If this is set to `false`, you should call the `submitForm()` method. |

## Methods [](id=methods)

| Method | Return Type | Explanation |
|-----------|-----------|-------------| 
| `loadForm()` | `boolean` | Starts the request to load the form definition. The form fields are shown when the response is received. This method returns `true` if the request is sent. |
| `loadRecord()` | `boolean` | Starts the request to load the record specified in `recordId`. If needed, the form definition is also loaded. The form fields are shown filled with record values when the response is received. This method returns `true` if the request is sent. |
| `submitForm()` | `boolean` | Starts the request to submit form values to the dynamic data list specified in `recordSetId`. All fields are validated prior to submission. Validation errors stop the submit process. |

## Delegate [](id=delegate)

The `DDLFormScreenlet` delegates some events to an object that conforms with the 
`DDLFormScreenletDelegate` protocol. This protocol lets you implement the 
following methods:

- `onFormLoaded(record)`: Called when the form is loaded. The `record` contains 
  only field definitions.
- `onFormLoadError(error)`: Called when an error occurs while loading the form. 
  The `NSError` object describes the error.
- `onRecordLoaded(record)`: Called when a form with values loads. The `record` 
  contains field definitions and values. The method `onFormLoadResult` is called 
  before `onRecordLoaded`.
- `onRecordLoadError(error)`: Called when an error occurs while loading a 
  record. The `NSError` object describes the error.
- `onFormSubmitted(record)`: Called when the form values are successfully 
  submitted to the server.
- `onFormSubmitError(error)`: Called when an error occurs while submitting the 
  form. The `NSError` object describes the error.
- `onDocumentUploadStarted(field)`: Called when the upload of a Documents and 
  Media field begins.
- `onDocumentUploadedBytes(field, progress values)`: Called when a block of 
  bytes in a Documents and Media field is uploaded. This method is intended to 
  track progress of the uploads.
- `onDocumentUploadCompleted(field, attributes)`: Called when a Documents and 
  Media field upload is completed.
- `onDocumentUploadError(error)`: Called when an error occurs in the Documents 
  and Media upload process. The `NSError` object describes the error.
