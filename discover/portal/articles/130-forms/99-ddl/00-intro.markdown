# Dynamic Data Lists [](id=dynamic-data-lists)

Dynamic data lists display custom forms created from custom field sets called 
*data definitions*. Data definitions consist of a form's field types (e.g., 
text, boolean, date, radio buttons, selector menus, etc.) and those fields' 
labels and settings. Data definitions effectively serve as data models for a 
dynamic data list. For example, you could create a data definition with 2 text 
fields: 1 for a user's name, and 1 for their comments. You could then display a 
form that gathers user feedback via a dynamic data list that uses that data 
definition. 

To summarize: 

-   **Data Definitions:** Define a form's fields. 
-   **Dynamic Data Lists:** Display a form based on a data definition. 

Create one or multiple dynamic data lists from a single data definition. The
user data entered for each dynamic data list is kept separate, even if the data
definition is shared. For instance, you could use the example data definition
above to create several dynamic data lists, and then place them anywhere you
need to get feedback from users. Because each dynamic data list's form data is
separate, you don't need to worry about trying to figure out which dynamic data
list the user comment came from. 

Dynamic data lists are flexible. Creating data definitions and lists doesn't
require any coding. Create data definitions and dynamic data lists in from the
Site Menu's Content &rarr; Dynamic Data Lists application. However, additional
formatting can be added with 
[FreeMarker](https://freemarker.apache.org/) 
templates. And you don't have to restrict dynamic data lists to simple input.
You could create something as complex as an entire data entry system for real
estate listings, or any other simple list-based application you can think of.

This section of articles shows you how to create and use data definitions and 
dynamic data lists. You'll cover these topics: 

-   Creating data definitions. 
-   Creating dynamic data lists. 
-   Creating form and display templates. 

## System Configuration

There are two Dynamic Data Lists entries in System Settings:

The Dynamic Data Lists Service entry contains one setting:

**Add Default Structures:**
: This is enabled by default, and pre-loads several embedded data definitions to
base data lists on. Once loaded on portal startup, these definitions must be
manually deleted from the site menu. Dynamic Data Lists application. This
setting applies to the first start of a virtual instance.

The Dynamic Data Lists entry contains three settings:

**Changeable Default Language:**
: If enabled, the default language of a data definition becomes changeable.

**CSV Export:**
: Choose whether DDL records can be exported in CSV format with or without a
warning, or disable this option. The warning displayed when the records are being
exported says

    Warning: This CSV file contains user supplied inputs. Opening a CSV file 
    in a spreadsheet program may be dangerous.

**Default Display View:**
: Choose whether to use a table based default view or a list based default view. 
<!-- I couldn't see this working. Might be non-functional-->
