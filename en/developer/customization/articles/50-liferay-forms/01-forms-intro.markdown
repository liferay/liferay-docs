---
header-id: liferay-forms
---

# Liferay Forms

[TOC levels=1-4]

The [Liferay Forms](/docs/7-2/user/-/knowledge_base/u/forms) application is
a full-featured form building tool for collecting data. There's lots of built-in
functionality. For the pieces you're missing, there are extension points.

This section of articles shows developers how to

1.  Store form entry data in an alternative format. The default storage type is
    JSON.

2.  [Coming Soon] Create new form field types.

## Liferay Forms Extension Points

Here's a compilation of the Liferay Forms application's extension points that
are ready for your customization:

- Create a Form Storage Adapter by implementing a 
    [`StorageAdapter`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/StorageAdapter.java) 
    or by extending the Abstract implementation,
    [`BaseStorageAdapter`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/BaseStorageAdapter.java).
- Create a Form Field Type by implementing a
    [`DDMFormFieldType`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/form/field/type/DDMFormFieldType.java),
    [`DDMFormFieldTypeSettings`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/form/field/type/DDMFormFieldTypeSettings.java),
    and a
    [`DDMFormFieldTemplateContextContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/form/field/type/DDMFormFieldTemplateContextContributor.java).
- Create custom validation rules for form fields by implementing a
    [DDMFormFieldValueValidator](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/form/field/type/DDMFormFieldValueValidator.java).
