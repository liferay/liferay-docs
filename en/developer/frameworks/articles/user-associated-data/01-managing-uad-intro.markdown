---
header-id: managing-user-associated-data-stored-by-custom-applications
---

# Managing User-Associated Data Stored by Custom Applications

[TOC levels=1-4]

Administrators can [delete or
anonymize](/docs/7-2/user/-/knowledge_base/u/managing-user-data) User Associated
Data (UAD) using management tools that aid compliance efforts with the
EU's General Data Protection Regulation (GDPR). Out of the box, the UAD
management tool supports @product@ applications (Blogs, Documents and Media,
etc.), and you can also anonymize data stored by your custom applications.

This task is made easier for 
[Service Builder applications](/docs/7-2/appdev/-/knowledge_base/a/service-builder).
At the core of the anonymization effort, you must identify the model entity's
fields to anonymize. With Service Builder, attach anonymization attributes to
elements in the `-service` module's `service.xml` file. For the entire DTD for
Service Builder, see
[here](https://docs.liferay.com/portal/7.2-ga1/definitions/).
These two are the most important attributes for the UAD framework:

- The `uad-anonymize-field-name=fieldName` attribute indicates a field whose
  value is replaced by that of the anonymous user in the UAD deletion process.

- The `uad-nonanonymizable=true` attribute indicates data that cannot be
  anonymized automatically and must be reviewed by an administrator.

Once your application uses the UAD framework to manage User data, there are more
features in @product-ver@ that make searching and deleting User Associated Data
even easier.
