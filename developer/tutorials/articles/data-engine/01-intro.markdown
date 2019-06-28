# Data Engine

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
[here](/docs/7-2/deploy/-/knowledge_base/d/get-started-discover-the-api). 

The Data Engine framework has three fundamental entities. You'll be familiar
with the roles of each Data Engine entity if you used the Dynamic Data List
framework in the past.

1.  Data Definitions
2.  Data Record Collections
3.  Data Records

##  Data Definitions

Data Definitions (`DEDataDefinition`s) hold the form's field types (e.g., text,
boolean, date, radio buttons, selector menus, etc.) and those fields' labels and
settings. Data definitions effectively serve as data models for a Data
Collection. For example, you could create a data definition with two text
fields: one for a user's name, and one for their comments. You could then
display a form that gathers user feedback via a dynamic data list that uses that
data definition. 
<!--Link to swaggerhub for data defs, and the other entities?-->

## Data Record Collections

Data Record Collections (`DEDataRecordCollection`s) collect Data Records.
They're used to associate a displayed form with the data records it holds and
its backing data definition.

## Data Records

Data Records are the individual form entries for a given Data Record
Collection.

## Data Views

There's another important piece to the Data Engine framework: displays.

Once you have a Data Definition and a Data Collection, it's common to need
control over display. Do this with FreeMarker templates, like in the Dynamic
Data Lists application.
