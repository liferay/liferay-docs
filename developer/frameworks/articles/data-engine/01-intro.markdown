---
header-id: data-engine
---

# Data Engine

[TOC levels=1-4]

Past versions of @product@ included a robust framework for creating form and
list-based applications, either programmatically or from the Dynamic Data Lists
Application. While Dynamic Data Lists continue to exist, a new framework was
written to be based on the Forms Application's backend. ????? SAY WHY ?????. In
@product-ver@, only the backend APIs are available for developers. Future
releases will add a frontend tool to allow non-developers to create
applications. For now, developers can use the API to 

1.  Create frontend applications running outside the portal, making service
    calls from a REST client to the Data Engine APIs.

2.  Create applications inside the portal that call the Data Engine's local APIs
    APIs for their persistence code.

To start discovering the Data Engine API and the other headless REST APIs of
@product@, read
[here](/docs/7-2/frameworks/-/knowledge_base/f/get-started-discover-the-api). 

The Data Engine framework has three fundamental entities. You'll be familiar
with the roles of each Data Engine entity if you used the Dynamic Data List
framework in the past.

1.  Data Definitions
2.  Data Record Collections
3.  Data Records

For detailed information on the Data Engine API, visit the [SwaggerHub profile](https://app.swaggerhub.com/apis/liferayinc/data-engine/1.0).

##  Data Definitions

Data Definitions (`DEDataDefinition`s) hold the form's field types (e.g., text,
boolean, date, radio buttons, selector menus, etc.) and those fields' labels and
settings. Data definitions effectively serve as data models for a Data
Collection. For example, you could create a data definition with two text
fields: one for a user's name, and one for their comments. You could then
display a form that gathers user feedback via a dynamic data list that uses that
data definition. 

It's important to understand that deleting a data definition will
simultaneously delete all the entities associated with it (Data Record
Collections, Data Records, and Data Layouts).

## Data Record Collections

Data Record Collections (`DEDataRecordCollection`s) collect Data Records.
They're used to associate a displayed form with the data records it holds and
its backing data definition.

## Data Records

Data Records are the individual form entries for a given Data Record
Collection.

## Data Layouts

There's another important piece to the Data Engine framework: data display.

Data Layouts are used to display the form fields (defined in the data
definition) in rows and columns.

The articles in this section will show example calls to the Data Engine REST
API for each entity. For a complete reference guide to the API, visit the
[SwaggerHub profile](https://app.swaggerhub.com/apis/liferayinc/data-engine/1.0).
