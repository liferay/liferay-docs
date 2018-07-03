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

You can use a single data definition to create multiple dynamic data lists. The 
data that users enter in such dynamic data lists is kept separate. For instance, 
you could use the example data definition above to create several dynamic data 
lists, and then place them anywhere you need to get feedback from users. Because 
each dynamic data list's form data is separate, you don't need to worry about 
trying to figure out which dynamic data list the user comment came from. 

Dynamic data lists are also very flexible. You can create them, and data 
definitions, without writing a single line of code. You create data definitions 
and dynamic data lists in @product@'s UI. However, you can format a dynamic data 
list with 
[FreeMarker](https://freemarker.apache.org/) 
templates if you so choose. And you don't have to restrict dynamic data lists to 
simple input. You could create something as complex as an entire data entry 
system for real estate listings. 

This section of articles shows you how to create and use data definitions and 
dynamic data lists. You'll cover these topics: 

-   Creating data definitions. 
-   Creating dynamic data lists. 
-   Creating form and display templates. 


