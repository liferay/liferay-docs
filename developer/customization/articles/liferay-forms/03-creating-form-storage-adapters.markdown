---
header-id: creating-a-form-storage-adapter
---

# Creating a Form Storage Adapter

[TOC levels=1-4]

There's only one class to create when implementing a Form Storage Adapter, and
it extends the base `StorageAdapter` implementation.

```java
@Component(service = StorageAdapter.class)
public class FileSystemStorageAdapter extends BaseStorageAdapter {
```

The only method without a base implementation in the abstract class is
`getStorageType`. For file system storage, it can return `"File System"`.

```java
@Override
public String getStorageType() {
    return "File System";
}
```

## Storage Adapter CRUD Operations

The CRUD operations must be created to properly handle the Form Records.

### Create

Next override the `doCreateMethod` to return a `long` that identifies each form
record with a unique file ID: 

```java
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
```

These are the utility methods invoked in the create method:

```java
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
```

### Read

To retrieve the form record's values from the `File` object where they were
written, override `doGetDDMFormValues`:

```java
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
```

### Update

Override the `doUpdate` method so the record's values can be overwritten. This
example calls the `saveFile`  utility method provided earlier:

```java
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
```

### Delete

Override the `doDeleteByClass` method to delete the `File` representing the form
record, using the `classPK`, and to delete the class storage links:

```java
@Override
protected void doDeleteByClass(long classPK) throws Exception {
    DDMStorageLink storageLink =
        _ddmStorageLinkLocalService.getClassStorageLink(classPK);

    FileUtil.delete(getFile(storageLink.getStructureId(), classPK));

    _ddmStorageLinkLocalService.deleteClassStorageLink(classPK);
}
```

Provide form record deletion logic to be called when deleting all the records
and storage links associated with a form (using its `ddmStructureId`):

```java
@Override
protected void doDeleteByDDMStructure(long ddmStructureId)
    throws Exception {

    FileUtil.deltree(getStructureFolder(ddmStructureId));

    _ddmStorageLinkLocalService.deleteStructureStorageLinks(ddmStructureId);
}
```

## Beyond CRUD: Validation

Add a `validate` method to the `StorageAdapter`:

```java
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
```

Deploy your storage adapter and it's ready to use.
