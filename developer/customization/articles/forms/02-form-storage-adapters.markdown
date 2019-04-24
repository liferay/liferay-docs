# Form Storage Adapters

When a User adds a form record, the Forms API routes the processing of the
request through the storage adapter API. The same is true for the other *CRUD*
operations performed on form entries (read, update, and delete operations). The
default implementation of the storage service is called `JSONStorageAdapter`,
and as its name implies, it implements the `StorageAdapter` interface to provide
JSON storage of form entry data.

The DDM backend can *adapt* to other data storage formats for form records. Want
to store your data in XML? YAML? No problem. Because the storage API is
separated from the regular service calls used to populate the database table for
form entries, a developer can even choose to store form data outside the Liferay
database. 

Define your own format to save form entries by writing your own implementation
of the `StorageAdapter` interface. The interface follows the *CRUD* approach, so
implementing it requires that you write methods to create, read, update and
delete form values.

|**Note:** When you add a new storage adapter, it can only be used with new
|Forms. All existing Forms continue to use the adapter selected (JSON by default)
|at the time of their creation, and a different storage adapter cannot be
|selected.

The example storage adapter in this tutorial serializes form data to be stored
in a simple file, stored on the file system.

<!-- RECREATE IMAGE: Figure 1: Choose a Storage Type for your form records.](../../images/forms-storage-type.png)-->

## Storage Adapter Methods

Before worrying about how to handle the CRUD logic, write a `getStorageType`
method.

`getStorageType`
: Return a human readable String, as `getStorageType` determines what appears
in the UI when the form creator is selecting a storage type for their form. The
String value you return here is added to the `StorageAdapterRegistry`'s Map of
storage adapters. 

### The CRUD Methods

`doCreate`:
Return a `long` that identifies each form record with a unique file ID. Almost
as important is to validate the form values being sent through the storage
adapter API. This is as simple as calling
`DDMFormValuesValidator.validate(ddmFormValues)`. In addition, you'll interact
with at least two other DDM services to get the form the values are associated
with, and to make sure they're linked: `DDMStructureVersionLocalService` and
`DDMStorageLinkLocalService`. Lastly, the form values in the `DDMFormValues`
object must be serialized (converted) into the right storage format. If JSON
works for your use case, feel free to use the `DDMFormValuesJSONSerializer`
service in the Liferay Forms code, as demonstrated in the following article.
Otherwise you'll need to provide your own serialization service for the form
values.

`doGetDDMFormValues`
: Return the form values (`DDMFormValues`) for a form. You'll call the
`deserialize` method after retrieving them, to take them from the storage format
(e.g., JSON) to a proper `DDMFormValues` object. You can use the Liferay Forms
`DDMFormValuesJSONDeserializer` if you're retrieving JSON data.

`doUpdate`
: A request to update the values comes from a User in the Liferay Forms
application, so call the validator again, serialize the values into the proper
format, and save them.

`doDeleteByClass`
: When a delete request is made on a form record directly, delete the form
values in whatever format they're currently being stored in (this is entirely
dependent on your own application of the storage adapter). In addition, retrieve
and delete the DDM class storage link using `DDMStorageLinkLocalService`.

`doDeleteByDDMStrcuture`
: When a delete request is made on an entire form, delete all the form records
associated with it. In addition, take the form's `ddmStructureId` and delete all
the DDM structure storage links that were created for it.

### Validating Form Entries

Because the Storage Adapter handles User entered data during the `add` and
`update` operations, it's important to validate that the entries include only
appropriate data. Add a `validate` method to the `StorageAdapter`, calling the
Liferay Forms' `DDMFormValuesValidator` method to do the heavy lifting. 

	protected void validate(
        DDMFormValues ddmFormValues, ServiceContext serviceContext)
		throws Exception {

		boolean validateDDMFormValues = GetterUtil.getBoolean(
			serviceContext.getAttribute("validateDDMFormValues"), true);

		if (!validateDDMFormValues) {
			return;
		}

		_ddmFormValuesValidator.validate(ddmFormValues);
	}

Make sure to do three things:

1.  Retrieve the value of the `boolean validateDDMFormValues` attribute from the
    service context.

2.  If `validateDDMFormValues` is false, exit the validation without doing
    anything.

    When a User accesses a form at its dedicated link, there's a periodic
    auto-save process of in-progress form values. There's no need to validate
    this data until the User hits the *Submit* button on the form, so the
    auto-save process sets the `validateDDMFormValues` attribute to `false`.

3.  Otherwise, call the validate method from the `DDMFormValuesValidator`
    service.

All the Java code for the logic discussed here is shown in the next article,
[Creating Form Storage Adapters](/docs/7-2/customization/-/knowledge_base/customization/creating-form-storage-adapters).

## Enabling the Storage Adapter

The storage adapter is enabled at the individual form level. Create a new form,
and select the Storage Adapter _before saving or publishing the form_. If you
wait until first Saving the Form, the default Storage Adapter is already
assigned to the Form, and this setting is no longer editable.

1.  Go to the Site Menu &rarr; Content &rarr; Forms, and click the *Add* button
    (![Add](../../images/icon-add.png)).

2.  In the Form Builder view, click the *Options* button
    (![Options](../../images/icon-options.png)) and open the *Settings*
    window. 

3.  From the select list field called *Select a Storage Type*, choose the
    desired type and click _Done_.

Now all the form's entries are stored in the desired format.
