# Data Engine

Docs Outline

1.  Introduction

    Explain the overall goal: A Guestbook application where administrators can add
    Guestbooks, while Guest Users can add Guestbook Entries to Guestbooks they're
    interested in.

    Administrators must have permission to manage (CRUD operations) Guestbooks,
    which are the Data Record Collection.

    The Guestbook Entry form is backed by the Data Engine Data Definition, where
    Guest Users can add an Entry, so they must be able to view the Data Record
    Collection and have CRUD operation ability over their data record.

2.  Create the application project

    We can provide a template here, or we can link to the React docs we
    currently have, which basically say: 

    Use `npm install -g yo generator-liferay-bundle`
    Call `yo liferay-bundle`
    Fill out CLI form accordingly

3.  Create the Guestbook Entry Data Definition

    Data structure: Name, Message text fields.

    Validation: Both are required fields.

    Permissions: Admins should be able to manage these with full CRUD operation support

4.  Create the Guestbook Data Record Collection

    Each Data Record collection represents a different Guestbook

    Admins can do the full set of CRUD operations on these, from an
    administrative UI (is that possible with data engine?)

5.  Creating Data Layouts

    Expose the administrative functions for the Guestbook Data Record
    Collections

    Expose the Add Guestbook Entry UI for Guest Users

    Display the list of records for each guestbook


Data Record Collection: Guestbook Name

Data Records: For each Guestbook, fill out the Name and Message fields

Create a React application 
    Use `npm install -g yo generator-liferay-bundle`
    Call `yo liferay-bundle`
    Fill out CLI form accordingly

Create the Guestbook data definition
    Needs only a name field

Create the Guestbook Data Record Collection
    Each DRC takes a Name

Implement CRUD actions on the Guestbook Data Definitions
    Admins should be able to add, update, and delete data definitions, and see
    all of them in the Site Administration.

Create the Guestbook Entry data definition

Crate the Add Guestbook Entry data record collection, backed the Guestbook
Entry data definition

Implement CRUD functions on the Guestbook Entry data record collection
    Give Guest Users the ability to add guestbook entry records

## Create an Application

Link to React docs, perhaps provide a template, use our generator

1.  Run 

        npm install -g yo generator-liferay-bundle

    to install the project generation tool

2.  Generate the React project

        yo liferay-bundle

    Choose these options:

        
        ? What type of project do you want to create? React based portlet

        ? What name shall I give to the folder hosting your project? guestbook-data-engine

        ? What is the human readable description of your project? Guestbook Data Engine

        ? Do you want to add localization support? Yes

        ? Do you want to add settings support?
          (👀 needs JS Portlet Extender 1.1.0) Yes

        ? Under which category should your portlet be listed? category.collaboration

        ? Do you have a local installation of Liferay for development? Yes

        ? Where is your local installation of Liferay placed? /path/to/liferay/home

        ? Do you want to generate sample code? Yes
## 
