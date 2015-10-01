# DDL Form Screenlet for Android [](id=ddlformscreenlet-for-android)

<iframe width="560" height="315" src="https://www.youtube.com/embed/rHsreHmStLo" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 CE or EE

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

The `DDLFormScreenlet` shows a set of fields that can be filled in by the user. 
Initial or existing values can be shown in the fields. Fields of the following 
data types are supported:

- *Boolean*: A two state value typically represented by a checkbox.
- *Date*: A formatted date value. The format depends on the device's current 
  locale.
- *Decimal, Integer, and Number*: A numeric value.
- *Documents & Media*: A file stored on the device. It can be uploaded to a 
  specific portal repository.
- *Radio*: A set of options to choose from. A single option must be chosen. 
- *Select*: A selection box of options to choose from. A single option must be 
  chosen.
- *Text*: A single line of text.
- *Text Area*: Multiple lines of text.

The `DDLFormScreenlet` also supports the following features:

- Stored records can support a specific workflow.
- A Submit button can be shown at the end of the form.
- Required values and validation for fields can be used. 
- Users can traverse the form fields from the keyboard.
- Supports i18n in record values and labels.

There are also a few limitations that you should be aware of when using 
`DDLFormScreenlet`. They are listed here:

- Nested fields in the data definition aren't supported.
- Selection of multiple items in the Radio and Select data types isn't 
supported.

## Module [](id=module)

- DDL

## Views [](id=views)

The Default View uses a standard vertical `ScrollView` to show a scrollable list 
of fields. Other Views may use different components, such as `ViewPager` or 
others, to show the fields. You can find a sample of this implementation in the 
`DDLFormScreenletPagerView` class.

![The `DDLFormScreenlet` Default and Material Viewsets.](../../images/screens-android-ddlform.png)

### Editor Types [](id=editor-types)

Each field defines an editor type. You must define each editor type's layout by 
using the following attributes:

- `checkboxFieldLayoutId`: The layout to use for Boolean fields.
- `dateFieldLayoutId`: The layout to use for Date fields.
- `numberFieldLayoutId`: The layout to use for Number, Decimal, or Integer 
  fields.
- `radioFieldLayoutId`: The layout to use for Radio fields.
- `selectFieldLayoutId`: The layout to use for Select fields.
- `textFieldLayoutId`: The layout to use for Text fields.
- `textAreaFieldLayoutId`: The layout to use for Text Box fields.
- `textDocumentFieldLayoutId`: The layout to use for Documents &amp; Media fields.

If you don't define the editor type's layout in the attributes of 
`DDLFormScreenlet`, the default layout `ddlfield_xxx_default` is used, where 
`xxx` is the name of the editor type. It's important to note that you can change 
the layout used with any editor type at any point. 

### Custom Editors [](id=custom-editors)

If you want to have a unique appearance for one specific field, you can
customize your field's editor View by calling the Screenlet's
`setCustomFieldLayoutId(fieldName, layoutId)` method, where the first parameter
is the name of the field to customize and the second parameter is the layout to
use. You can also create custom editor Views. For examples of this, see the
files `ddlfield_custom_rating_number.xml` and `CustomRatingNumberView.java`.

## Activity Configuration [](id=activity-configuration)

The `DDLForm Screenlet` needs the following user permissions:

    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

Both are used by the Documents and Media fields to take a picture/video and 
store it locally before uploading it to the portal. The Documents and Media 
fields also need to override the `onActivityResult` method to receive the 
picture/video information. Here's an example implementation: 

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    
        _screenlet.startUploadByPosition(requestCode);
    }

## Portal Configuration [](id=portal-configuration)

Before using `DDLFormScreenlet`, you should make sure that Dynamic Data Lists 
and Data Types are configured properly in the portal. Refer to the 
[Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists) 
sections of the User Guide for more details. If Workflow is required, it must 
also be configured. See the [Using Workflow](/portal/-/knowledge_base/6-2/using-workflow) 
section of the User Guide for details.

### Permissions [](id=permissions)

To use `DDLFormScreenlet` to add new records, you must grant the Add Record 
permission in the Dynamic Data List:

![The Add Record permission.](../../images/screens-portal-permission-record-add.png)

If you want to use `DDLFormScreenlet` to view or edit record values, you must 
also grant the View and Update permissions, respectively:

![The permissions for viewing and editing records.](../../images/screens-portal-permission-record-edit.png)

Also, if your form includes at least one Documents and Media field, you must 
grant permissions in the target repository and folder. For more details, see the 
`repositoryId` and `folderId` attributes below.

![The permission for adding a folder.](../../images/screens-portal-permission-folder-add.png)

For more details, see the User Guide sections [Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-), 
[Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists), 
and [Using Workflow](/portal/-/knowledge_base/6-2/using-workflow). 

## Required Attributes [](id=required-attributes)

- `layoutId`
- `structureId`
- `recordSetId`

## Attributes [](id=attributes)

| Attribute | Data Type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the View. |
| `checkboxFieldLayoutId ` | `@layout` | The layout to use to show the view for Boolean fields. |
| `dateFieldLayoutId` | `@layout` | The layout to use to show the view for Date fields. |
| `numberFieldLayoutId` | `@layout` | The layout to use to show the view for Number, Decimal, and Integer fields. |
| `radioFieldLayoutId` | `@layout` | The layout to use to show the view for Radio fields. |
| `selectFieldLayoutId` | `@layout` | The layout to use to show the view for Select fields. |
| `textFieldLayoutId` | `@layout` | The layout to use to show the view for Text fields. |
| `textAreaFieldLayoutId` | `@layout` | The layout to use to show the view for Text Box fields. |
| `textDocumentFieldLayoutId` | `@layout` | The layout to use to show the view for Documents & Media fields. |
| `structureId` | `number` | The ID of a data definition in your Liferay site. To find the IDs for your data definitions, click *Admin* &rarr; *Content* from the Dockbar. Then click *Dynamic Data Lists* on the left and click the *Manage Data Definitions* button. The ID of each data definition is in the ID column of the table. |
| `groupId` | `number` | The ID of the site (group) where the record is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `recordSetId` | `number` | A dynamic data list's ID. To find your dynamic data lists' IDs, click *Admin* &rarr; *Content* from the Dockbar. Then click *Dynamic Data Lists* on the left. Each dynamic data list's ID is in the ID column of the table. |
| `recordId` | `number` | The ID of the record you want to show. You can also allow the record's values to be edited. This ID can be obtained from other methods or listeners. |
| `repositoryId` | `number` | The ID of the Documents and Media repository to upload to. If this value is `0`, the default repository for the site specified by `groupId` is used. |
| `folderId` | `number` | The ID of the folder where Documents and Media files are uploaded. If this value is `0`, the root is used. |
| `filePrefix` | `string` | The prefix to attach to the names of files uploaded to a Documents and Media repository. The upload date followed by the original file name is appended following the prefix. |
| `autoLoad` | `boolean` | Sets whether the form loads when the Screenlet is shown. If `recordId` is set, the record value is loaded together with the form definition. The default value is `false`. |
| `autoScrollOnValidation` | `boolean` | Sets whether the form automatically scrolls to the first failed field when validation is used. The default value is `true`. |
| `showSubmitButton` | `boolean` | Sets whether the form shows a submit button at the bottom. If this is set to `false`, you should call the `submitForm()` method. The default value is `true`. |

## Methods [](id=methods)

| Method | Return Type | Explanation |
|-----------|-----------|-------------| 
| `loadForm()` | `void` | Starts the request to load the form definition. The form fields are shown when the response is received. |
| `loadRecord()` | `void` | Starts the request to load the record specified by `recordId`. If needed, the form definition also loads. When the response is received, the form fields are shown filled with record values. |
| `load()` | `void` | Starts the request to load the record if `recordId` is specified. Otherwise, the form definition is loaded. |
| `submitForm()` | `void` | Starts the request to submit form values to the dynamic data list specified by `recordSetId`. If the record is new, a new record is added. If `loadRecord` is used to retrieve the record, or the record already exists, its values are updated. Fields are validated prior to the request. If validation fails, the validation errors are shown and the request is terminated. |

## Listener [](id=listener)

The `DDLFormScreenlet` delegates some events to an object that implements to the 
`DDLFormScreenletListener` interface. This interface lets you implement the 
following methods:

- `onDDLFormLoaded(Record record)`: Called when the form definition successfully 
  loads.

- `onDDLFormRecordLoaded(Record record)`: Called when the form record data 
  successfully loads.

- `onDDLFormRecordAdded(Record record)`: Called when the form record is 
  successfully added.

- `onDDLFormRecordUpdated(Record record)`: Called when the form record data 
  successfully updates.

- `onDDLFormLoadFailed(Exception e)`: Called when an error occurs in the load 
  form definition request.

- `onDDLFormRecordLoadFailed(Exception e)`: Called when an error occurs in the 
  load form record request.

- `onDDLFormRecordAddFailed(Exception e)`: Called when an error occurs in the 
  request to add a new record.

- `onDDLFormUpdateRecordFailed(Exception e)`: Called when an error occurs in the 
  request to update an existing record.

- `onDDLFormDocumentUploaded(DocumentField field)`: Called when a specified 
  document field's upload completes.

- `onDDLFormDocumentUploadFailed(DocumentField field, Exception e)`: Called when 
  a specified document field's upload fails.

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

_When loading_ the form or record, these are the four policies supported:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `REMOTE_ONLY` | The form or record will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the listener as usual. If the portrait can be successfully loaded, the received data (record structure and data) is stored in the local cache for later usage.| Use this policy when you need to show always updated data and show nothing when there's no connection.|
| `CACHE_ONLY` | The form or record will be loaded from the local cache. If it's not present, the screenlet will notify about the error through the listener as usual.| Use this policy when you need to show always local data without retrieving remote information under any circumstance.|
| `REMOTE_FIRST` | The form or record will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the listener as usual.| When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `CACHE_FIRST` | The form or record will be loaded from the local cache if exists. If it doesn't exist, then it will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|

_When editing_ the record, these are the four policies supported:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `REMOTE_ONLY` | The record will be sent to the portal at the moment. If a connection issue happens, the screenlet will notify about the error through the listener as usual, but the record data will be discarded. | Use this policy when you need to make sure the record is updated or added in the portal at the moment.|
| `CACHE_ONLY` | The record will be stored in the local cache. | Use this policy when you need to save the data locally but you don't want to change the remote content (update or add record). |
| `REMOTE_FIRST` | The record will be sent to the remote portal. If it's successfully sent, it will be also stored in the local cache for later usage. If a connection issue happens, then it will be stored in the local cache with the "dirty flag" enabled. This makes the record to be sent to the portal when the synchronization process runs.| Use this policy when you need to make sure the record will be sent to the portal, right at the moment or when the connection is restored. |
| `CACHE_FIRST` | The record will be stored in local cache and then sent to the remote portal. If a connection issue happens, then it will be stored in the local cache with the "dirty flag" enabled. This makes the record to be sent to the portal when the synchronization process runs.| Use this policy when you need to make sure the record will be sent to the portal, right at the moment or when the connection is restored. The different between this policy and `REMOTE_FIRST` is the former will store record in the cache always, while the latter will store the record only in case of network error.|
