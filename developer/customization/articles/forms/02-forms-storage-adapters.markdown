# Forms Storage Adapters

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

![Figure 1: Choose a Storage Type for your form records.](../../images/forms-storage-type.png)

Note that these code snippets include the references to the services they're
calling directly beneath the first method that uses the service. It's a Liferay
code convention to place these at the very end of the class.

## Implementing a Storage Adapter [](id=implementing-a-storage-adapter)

First declare the class a `Component` that provides a `StorageAdapter`
implementation. To implement a storage adapter, extend the abstract
`BaseStorageAdapter` class.

    @Component(service = StorageAdapter.class)
    public class FileSystemStorageAdapter extends BaseStorageAdapter {


### Step 1: Name the Storage Type [](id=step-1-name-the-storage-type)

The only method without a base implementation in the abstract class is
`getStorageType`. For the file system storage example, just make it return
`"File System"`.

    @Override
    public String getStorageType() {
        return "File System";
    }

Return a human readable String, as `getStorageType` determines what appears in
the UI when the form creator is selecting a storage type for their form. The
String value you return here is added to the `StorageAdapterRegistry`'s Map of
storage adapters. 

### Step 2: Override the CRUD Methods [](id=step-2-override-the-crud-methods)

Next override the `doCreateMethod` to return a `long` that
identifies each form record with a unique file ID: 

    @Override
    protected long doCreate(
        long companyId, long ddmStructureId, DDMFormValues ddmFormValues, 
        ServiceContext serviceContext)
        throws Exception {

        validate(ddmFormValues, serviceContext);

        long fileId = _counterLocalService.increment();

        DDMStructureVersion ddmStructureVersion =
            _ddmStructureVersionLocalService.getLatestStructureVersion(
                ddmStructureId);

        long classNameId = PortalUtil.getClassNameId(
            FileSystemStorageAdapter.class.getName());

        _ddmStorageLinkLocalService.addStorageLink(
            classNameId, fileId, ddmStructureVersion.getStructureVersionId(),
            serviceContext);

        saveFile(
            ddmStructureVersion.getStructureVersionId(), fileId, ddmFormValues);

        return fileId;
    }

    @Reference
    private CounterLocalService _counterLocalService;

    @Reference
    private DDMStorageLinkLocalService _ddmStorageLinkLocalService;

    @Reference
    private DDMStructureVersionLocalService _ddmStructureVersionLocalService;

The first line in this method (and the subsequent `doUpdate` method) calls a
[`validate`](#step-3-validating-form-entries) method that's not yet written, so
don't save the class until you've written that method. 

In addition to returning the file ID, add a storage link via the
`DDMStorageLinkLocalService`. The DDM Storage Link associates each form record
with the DDM Structure backing the form.

The `addStorageLink` method takes class name ID as retrieved by
`PortalUtil.getClassNameId`, the `fileId` (used as the primary key for
the file storage type), the structure version ID, and the service context.
There's also a call to a `saveFile` method, which serializes the forms record's
values and uses two additional utility methods (`getStructureFolder` and
`getFile`) to write a `java.io.File` object. There are some other utility
methods invoked as well:

    private File getFile(long structureId, long fileId) {
        return new File(
            getStructureFolder(structureId), String.valueOf(fileId));
    }

    private File getStructureFolder(long structureId) {
        return new File(String.valueOf(structureId));
    }

    private void saveFile(
            long structureVersionId, long fileId, DDMFormValues formValues)
        throws IOException {

        String serializedDDMFormValues = _ddmFormValuesJSONSerializer.serialize(
            formValues);

        File formEntryFile = getFile(structureVersionId, fileId);

        FileUtil.write(formEntryFile, serializedDDMFormValues);
    }

    @Reference
    private DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer;

Note the call to the `write` method.`FileUtil` is a Liferay utility class for
manipulating `java.io.File` objects. By default, the `write` method writes the
data into the user home folder of your system.

Override the `doDeleteByClass` method to delete the `File` using the `classPK`:

    @Override
    protected void doDeleteByClass(long classPK) throws Exception {
        DDMStorageLink storageLink =
            _ddmStorageLinkLocalService.getClassStorageLink(classPK);

        FileUtil.delete(getFile(storageLink.getStructureId(), classPK));

        _ddmStorageLinkLocalService.deleteClassStorageLink(classPK);
    }

Once the file is deleted, its storage links should also be deleted. Use
`doDeleteByDDMStructure` for this logic:

    @Override
    protected void doDeleteByDDMStructure(long ddmStructureId)
        throws Exception {

        FileUtil.deltree(getStructureFolder(ddmStructureId));

        _ddmStorageLinkLocalService.deleteStructureStorageLinks(ddmStructureId);
    }

To retrieve the form record's values from the `File` object where they were
written, override `doGetDDMFormValues`:

    @Override
    protected DDMFormValues doGetDDMFormValues(long classPK) throws Exception {
        DDMStorageLink storageLink =
            _ddmStorageLinkLocalService.getClassStorageLink(classPK);

        DDMStructureVersion structureVersion =
            _ddmStructureVersionLocalService.getStructureVersion(
                storageLink.getStructureVersionId());

        String serializedDDMFormValues = FileUtil.read(
            getFile(structureVersion.getStructureVersionId(), classPK));

        return _ddmFormValuesJSONDeserializer.deserialize(
            structureVersion.getDDMForm(), serializedDDMFormValues);
    }

    @Reference
    private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;

Override the `doUpdate` method so the record's values can be overwritten. This
example calls the `saveFile`  utility method provided earlier:

    @Override
    protected void doUpdate(
            long classPK, DDMFormValues ddmFormValues,
            ServiceContext serviceContext)
        throws Exception {

        validate(ddmFormValues, serviceContext);

        DDMStorageLink storageLink =
            _ddmStorageLinkLocalService.getClassStorageLink(classPK);

        saveFile(
            storageLink.getStructureVersionId(), storageLink.getClassPK(),
            ddmFormValues);
    }

Once the CRUD logic is defined, deploy and test the storage adapter.

## Step 3: Validating Form Entries [](id=step-3-validating-form-entries)

The `doCreate` and `doUpdate` methods above both include this line:

    validate(ddmFormValues, serviceContext);

Because the Storage Adapter handles User entered data, it's important to
validate that the entries include only appropriate data. Add
a `validate` method to the `StorageAdapter`:

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

1.  Retrieve the value of the `validateDDMFormValues` attribute.

2.  If `validateDDMFormValues` is false, exit the validation without doing
    anything.

    When a User accesses a form at its dedicated link, there's a periodic
    auto-save process of in-progress form values. There's no need to validate
    this data until the User hits the *Submit* button on the form, so the
    auto-save process sets the `validateDDMFormValues` attribute to `false`.

3.  Otherwise, call the validate method from the `DDMFormValuesValidator`
    service.

Once the necessary logic is in place, deploy and test the Storage Adapter.

## Enabling the Storage Adapter [](id=enabling-the-storage-adapter)

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
