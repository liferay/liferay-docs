# Forms Storage Adapters

When a User adds a form record, the Forms API routes the processing of the
request through the storage adapter API. The same is true for the other *CRUD*
operations performed on form entries (read, update, and delete operations). The
default implementation of the storage service is called `JSONStorageAdapter`,
and as its name implies, it implements the `StorageAdapter` interface to provide
JSON storage of form entry data.

The DDM backend can *adapt* to other data storage formats for form records. Want
to store your data in XML? YAML? No problem. Because the storage API before is
separate from the regular service calls used to populate the database table for
form entries, a developer can even choose to store form data outside the Liferay
database. 

Define your own format to save form entries by writing an OSGi component which
implements the `StorageAdapter` interface. The interface follows the *CRUD*
approach, so implementing it requires that you write methods to create, read,
update and delete form values.

The example storage adapter in this tutorial serializes form data to be stored
in a simple file, stored in the file system.

## Implementing a Storage Adapter

First declare the class a `Component` that provides a `StorageAdapter`
implementation. To implement a storage adapter, extend the abstract
`BaseStorageAdapter` class.

    @Component(service = StorageAdapter.class)
    public class FileSystemStorageAdapter extends BaseStorageAdapter {

The only method without a base implementation in the abstract class is
`getStorageType`. For the file system storage example, just make it return
`"FileSystem"`.

    @Override
    public String getStorageType() {
        return "File System";
    }

Return a human readable String, as `getStorageType` determines what appears in
the UI when the form creator is selecting a storage type for their form. The
String value you return here is added to the `StorageAdapterRegistry`'s Map of
storage adapters. 

Next override the `doCreateMethod` to return a `long` that
identifies each form record with a unique file ID: 

    @Override
    protected long doCreate(long companyId, long ddmStructureId,
            DDMFormValues ddmFormValues, ServiceContext serviceContext)
        throws Exception {

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

        long fileId = _counterLocalService.increment();

        return fileId;
    }

In addition to returning the file ID, add a storage link via the
`DDMStorageLinkLocalService`. The DDM Storage Link is used to associate each
form record with the form it's being entered for.

The `addStorageLink` method takes class name ID as retrieved by
`PortalUtil.getClassNameId`, the `fileId` (being used as the primary key for the
file storage type), the structure version ID, and the service context. There's
also a call to a `saveFile` method, which serializes the forms record's values
and uses two additional utility methods to write a `File`:

    private void saveFile(
            long structureVersionId, long fileId, DDMFormValues formValues)
        throws IOException {

        File formEntryFile = getFile(structureVersionId, fileId);

        String serializedDDMFormValues = _ddmFormValuesJSONSerializer.serialize(
            formValues);

        FileUtil.write(formEntryFile, serializedDDMFormValues);
    }

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

Override the `doUpdate` method so the record's values can be overwritten. This
example calls a utility method called `saveFile`:

    @Override
    protected void doUpdate(
            long classPK, DDMFormValues ddmFormValues,
            ServiceContext serviceContext)
        throws Exception {

        DDMStorageLink storageLink =
            _ddmStorageLinkLocalService.getClassStorageLink(classPK);

        saveFile(
            storageLink.getStructureVersionId(), storageLink.getClassPK(),
            ddmFormValues);
    }

These utility methods were called in writing the `File` and retrieving it:

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

For this specific example, here are the required service references:

    @Reference
    private CounterLocalService _counterLocalService;

    @Reference
    private DDMFormValuesJSONDeserializer _ddmFormValuesJSONDeserializer;

    @Reference
    private DDMFormValuesJSONSerializer _ddmFormValuesJSONSerializer;

    @Reference
    private DDMStorageLinkLocalService _ddmStorageLinkLocalService;

    @Reference
    private DDMStructureVersionLocalService _ddmStructureVersionLocalService;

}

## Enabling the Storage Adapter

After writing a new storage adapter and deploying is, you'll want to enable it.
This is done at the individual form level.

Go into the Form Builder view for a form and click the kebab menu. Open the
*Settings* window. There's a select list field called *Select a Storage Type*.
If your storage adapter is deployed, you'll see it in the list of options.

