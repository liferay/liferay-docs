---
header-id: managing-user-associated-data-stored-by-custom-applications
---

# Managing User-Associated Data Stored by Custom Applications

[TOC levels=1-4]

Administrators can [delete or
anonymize](/docs/7-2/user/-/knowledge_base/u/managing-user-data) User Associated
Data (UAD), complying with the EU's General Data Protection Regulation (GDPR).
Out of the box, this tool supports Liferay applications (blogs, web content,
etc.), but you can also anonymize data stored by your custom applications.

At the core of the anonymization effort, you must identify fields to anonymize
by attaching anonymization attributes to elements in the `-service` module's
`service.xml` file. The UAD framework recognizes two kinds of field:

- The `uad-anonymize-field-name=fieldName` attribute indicates a field whose
    value is replaced by that of the anonymous user in the UAD deletion process.

- The `uad-nonanonymizable=true` attribute indicates data that cannot be
    anonymized automatically and must be reviewed by an administrator.

Once your application uses the UAD framework to manage User data, there are more
features in @product-ver@ that make searching and deleting User Associated Data
even easier.
