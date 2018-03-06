# Forms Storage Adapters

The Forms API goes through the storage adapter API to add, update, and delete
form entries. The default implementation of the storage service is called
`JSONStorageAdapter`, and as its name implies, it implements the
`StorageAdapter` interface to define how the data is defined in JSON format. The
interface follows the *CRUD* approach, so implementing it requires that you
write methods to create, read, update and delete form values. 

The relevant data is stored in the `ddmStorageLink` table is a
feature that adds the ability to define how a form entry is stored when a user
submits a form.This means a developer can even choose to store form data outside
the Liferay database.  because the Forms API was designed to access form entries
through the use of a StorageAdapter. The API does not access tables directly.

By default, the Forms application stores form entry data as JSON.  

As the term *adapter* implies, the DDM backend can adapt to other data storage
formats. Want to store your data in XML? YAML? No problem. Define your own
format to save form entries by writing an OSGi component which implements the
`StorageAdapter` interface. 

Each StorageAdapter has a storage type, which is simply a name to identify it.

JSON is the default storage type for Forms. Form entries are serialized and
deserialized using JSONStorageAdapter, an implementation of StorageAdapter. 

Different forms might have different storage adapters. A storage adapter can be
assigned to a form during the form creation by accessing the form settings as
shown below. All available storage adapters will be listed on "Select a Storage
Type" field. Once a form is saved, it's not possible to change the storage type.

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
        return "FileSystem";
    }

The String value you return here is added to a Map storing storage adapters in
the storage adapter registry. Next override the `doCreateMethod` to return a
`long` that identifies each form record with a unique file ID: OVERALL PURPOSE
OF THIS METHOD?

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

In addition to returning the file ID, it's necessary to add a storage link via
the `DDMStorageLinkLocalService`. WHY DO WE NEED TO ADD A RECORD TO THE
DDMSTORAGELINK TABLE? Pass the method the class name ID as retrieved by
`PortalUtil.getClassNameId`, the `fileId` as the pseudo-primary key for the
storage type, the structure version ID, and the service context. There's also a
call to a `saveFile` method, which serializes the forms record's values and uses
two additional utility methods to write a `File`:

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

WHY DELETE THE ClassStorageLink? 
Override `doDeleteByDDMStructure` to provide a method for deleting the file and
its storage links:

    @Override
    protected void doDeleteByDDMStructure(long ddmStructureId)
        throws Exception {

        FileUtil.deltree(getStructureFolder(ddmStructureId));

        _ddmStorageLinkLocalService.deleteStructureStorageLinks(ddmStructureId);
    }

Override `doGetDDMFormValues` to provide a way for a record's values to be read
from the `File` object where they're written:

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

Overrise the `doUpdate` method so the record's values can be overwritten. Note
that it calls a utlity method called `saveFile`:

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


package com.liferay.custom.storage.adapter;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONDeserializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesJSONSerializer;
import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMStorageLinkLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService;
import com.liferay.dynamic.data.mapping.storage.BaseStorageAdapter;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.StorageAdapter;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.File;
import java.io.IOException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
