# DDM Form Screenlet for Android [](id=ddm-form-screenlet-for-android)

## Requirements [](id=requirements)

-   Android SDK 4.1 (API Level 16) or above
-   Liferay Portal Liferay CE Portal 7.1 - GA3, Liferay DXP - FP5
-   [APIO Architecture](/develop/tutorials/-/knowledge_base/7-1/foundations-jax-rs-osgi-whiteboard-and-apio#apio-architect)
    This app is preinstalled in Liferay CE Portal 7.1 GA3 and Liferay DXP FP5.
-   [Liferay Headless APIs](/develop/tutorials/-/knowledge_base/7-1/enabling-hypermedia-rest-apis) This module is not installed by default.

## Compatibility [](id=compatibility)

- Android SDK 4.1 (API Level 16) or above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

DDM Form Screenlet shows a set of fields that can be filled in by the user. 
Initial or existing values can be shown in the fields. The following fields are
supported:

- *Paragraph*: Add a title and/or text in your form.
- *Text Field*: A single or multiline text area.
- *Single Selection*: Select only one item with a radio button
- *Select from List*: Choose one or more options from a list.
- *Multiple Selection*: Select multiple options using a checkbox.
- *Date*: Select date from a Datepicker.
- *Grid*: Select options from a matrix.
- *Numeric*: It accepts only numbers. Integer or Decimal.
- *Upload*: Send files via Documents and Media.

The DDM Form Screenlet also supports the following features:

- Element Sets: You can reuse pre-existing element sets in your form.
- Multipages: Your form can have more than one page.
- Success Page: Show friendly feedback at the end of your form.
- Autosave: Any change in the form values will be automatically saved to your draft.
- Restore previous draft: Your last draft will be restored automatically when you open the form, independent of platform.
- Rules: You can create complex rules in your form. For example, you can show or hide fields depending on the input of other fields.
- Form submission can trigger a specific workflow.
- Required values and validation for fields can be used. 
- Supports i18n in record values and labels.

## Module [](id=module)

- DDM

## Views [](id=views)

- Default
- Lexicon
- Material

![Figure 1: The DDM Form Screenlet with the Lexicon View Set.](../../../images/screens-android-ddm-form-screenlet-lexicon-view.png)

### Custom Layouts [](id=custom-layouts)

If you want to create your custom layouts for each field, you can just create the new layout following our naming pattern and it will be automatically loaded: `<field_editor_id>_<theme_name>`. Some of the `DDM` fields inherit from `DDL`, so some field layouts even in `DDM` will be referenced as `DDL`. Above you can see how to create new `.xml` layout files.

| Editor Type | Field Editor ID | Example using Lexicon Theme |
|-----------|-----------|-------------| 
| Checkbox | ddlfield_checkbox | `ddlfield_checkbox_lexicon.xml` |
| Checkbox Multiple | ddmfield_checkbox | `ddmfield_checkbox_multiple.xml` |
| Date | ddlfield_date | `ddlfield_date_lexicon.xml` |
| Number | ddlfield_number | `ddlfield_number_lexicon.xml` |
| Integer | ddlfield_number | `ddlfield_number_lexicon.xml` |
| Decimal | ddlfield_number | `ddlfield_number_lexicon.xml` |
| Radio | ddlfield_radio | `ddlfield_radio_lexicon.xml` |
| Text | ddlfield_text | `ddlfield_text_lexicon.xml` |
| Select | ddlfield_select | `ddlfield_select_lexicon.xml` |
| Text Area | ddlfield_text_area | `ddlfield_text_area_lexicon.xml` |
| Paragraph | ddmfield_paragraph | `ddmfield_paragraph_lexicon.xml` |
| Document | ddlfield_document | `ddlfield_document_lexicon.xml` |
| Grid | ddmfield_grid | `ddmfield_grid_lexicon.xml` |
| Repeatable | ddmfield_repeatable | `ddmfield_repeatable_lexicon.xml` |

## Application Configuration [](id=activity-configuration)

DDM Form Screenlet needs the following user permissions:

```xml
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

Both are used by the Documents and Media fields to take a picture/video and 
store it locally before uploading it to the portal.

## Portal Configuration [](id=portal-configuration)

Before using DDM Form Screenlet, you should make sure that Dynamic Data Mapping is
configured properly in the portal. Refer to the 
[Creating and Managing Forms](/discover/portal/-/knowledge_base/7-1/creating-and-managing-forms).
You will also need to enable Liferay Hypermedia Rest APIs.
Refer to the [Enabling Hypermedia Rest APIs](https://dev.liferay.com/es/develop/tutorials/-/knowledge_base/7-1/enabling-hypermedia-rest-apis).
If Workflow is required, it must  also be configured. See the 
[Using Workflow](/discover/portal/-/knowledge_base/7-1/workflow) 
section of the User Guide for details. 

## Required Attributes [](id=required-attributes)

- `formInstanceId`

## Attributes [](id=attributes)

| Attribute | Data Type | Explanation |
|-----------|-----------|-------------| 
| `formInstanceId` | `number` | The ID of the form you want to load. To find the IDs for your data definitions, click *Content* &rarr; *Forms* from the side menu. The ID of each form is in the table that lists all forms. |

![Figure 2: How to find DDM Form ID.](../../../images/screens-portal-ddm-form-id.png)

## Permissions [](id=permissions)

If your form includes at least one Documents and Media field, you must 
grant permissions in the target repository and folder.

![Figure 3: The permission for adding a document to a Documents and Media folder.](../../../images/screens-portal-permission-folder-add.png)

For more details, see the User Guide sections 
[APIO Architecture](/develop/tutorials/-/knowledge_base/7-1/foundations-jax-rs-osgi-whiteboard-and-apio#apio-architect), [Liferay Headless APIs](/develop/tutorials/-/knowledge_base/7-1/enabling-hypermedia-rest-apis) and [Creating and Managing Forms](/discover/portal/-/knowledge_base/7-1/creating-and-managing-forms).

## Methods [](id=methods)

| Method | Return Type | Explanation |
|-----------|-----------|-------------| 
| `load()` | `void` | Starts the request to load the form. The form fields are shown when the response is received. |

## Listener [](id=listener)

DDM Form Screenlet delegates some events to an object that implements to the 
`DDMFormListener` interface. This interface lets you implement the 
following methods:

- `onFormLoaded(FormInstance formInstance)`: Called when the form instance successfully 
  loads. 

- `onError(Exception e)`: Called when an error occurs in the 
  process. For example, this method is called when an error occurs while loading 
  a form instance.