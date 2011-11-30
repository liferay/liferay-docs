# Dynamic Data Lists in Liferay

As needs change in businesses and organizations, the technology they use must adapt as well. One day, you'd like your team to sign up for a holiday party. You want to enter in custom sets of data, allow your users to add their information, and be able to access the set of data. To top it all off, you don't want to write a single line of code. Enter Liferay's *Dynamic Data Lists*: an easy way to create, aggregate and display new data types. Data Lists are flexible enough to handle all types of data, both simple and complex. Every fall, you may need to put up a job posting board, only allowing administrators to create new job posts. Maybe you want to allow users to manage a notebook or To-Do list on their private pages. Simply put, Liferay gives you the power to:

-  Define your own data definitions
-  Create new lists from those definitions
-  Customize the input forms for ease of use
-  Customize the output format
-  Integrate lists into Workflow and produce the best solution.

All of this capability is easily distilled into two concepts: data defining and data displaying. These data lists are dynamic for a reason: they are flexible and powerful. Whether you want to collect simple input from the user, or develop an entire data entry system for real estate listings, Dynamic Data Lists have your use case covered. Combined with the flexibility provided through templates and the power of languages like Velocity, entire applications can be built in short time.

## Building a List Platform in Liferay

To expand and extend the social capabilities of our site, we want to build a new, radical platform on Liferay: custom-built lists that users can share with their friends (or enemies, depending on their Social Relation type), and ask other users to contribute to. Marketing has come up with a great name for our new service: `list.it`. Our beautiful `list.it` dashboard will give each user the power to generate their own lists, see the lists of their friends, and tally the results of certain types of lists (surveys, anyone?). Liferay makes this as simple as throwing some Dynamic Data List Display and Form portlets on the user's private and public user pages. 

## Defining Data Types

When a new user logs in to `list.it`, they are going to want to build a few lists for themselves. Chances are, many of the lists they would want to create -- to do lists, shopping lists and memos come to mind -- will already be defined in the portal. All the user will have to do is create a new list, choose that pre-defined data type, and have at it! We'll take a look at that in a moment, but first we should look at what happens when you need to define your own data types. Perhaps we want to allow our `list.it` users (who would probably call themselves "list-ers" or "list-ies") to create their own data types for lists they create. In this case, they would have unfettered access to the content of their private user site, where they can create a new data type.

Using data lists to outline a new data model is as simple as point and click. You now have a `list.it` account and have been dying to bug your friends and family to sign up for that "volunteer" work that is moving apartments. Using an intuitive visual editor, you can quickly draw up the skeleton for that volunteer list in minutes. Since data lists exemplify a unique type of content for your site, you can find them in the content section of the control panel, going to *Go To -> Control Panel -> Content -> Dynamic Data Lists* or *Manage -> Site Content -> Dynamic Data Lists*. Within the dynamic data lists section, you can either create a new data type (*Manage Data Definitions*) or a new list from an existing data type.

![Figure 5.1: Data Lists in the control panel.](../../05-ddl-control-panel.png)

When creating a new *data definition*, you have a palette of fields to lay out, as well as a blank canvas to construct the definition. The interface will look very similar to creating and editing web content structures covered previously. Let's explore the different data types at our disposal:

**Boolean:** Presents a checkbox to the user and stores either a `true` (checked) or `false` (unchecked) based on state.

**Date:** A preformatted text field that displays a convenient date picker to assist in selecting the desired date. The format for the date is governed by the current locale.

**Number:** A text box that only accepts numbers as inputs, but puts no constraints on the kind of number entered.

**Integer:** Similar to `Number`, except that it constrains user input to non-fractional numbers.

**Decimal:** Similar to `Number`, except that it requires a decimal point (`.`) be present.

**Document Library:** Allows the user to select an existing document from the **document library** to attach to the data record.

**File Upload:** Allows the user to upload a document from their computer to the document library to attach to the data record. The upload can be constrained to a containing folder inside the document library, or the user's default upload location.

**Radio:** Presents the user with a list of options to choose from, using the radio button inputs. Values are stored as `string`s. Similar to `Select`.

**Select:** Lists a selection of options for the user to choose from, using a combo box. Can be configured to allow multiple selections, unlike `Radio`.

**Text:** A simple text field for any `string` input.

**Text Box:** A large text box for long text input.

![Figure 5.2: Data definition fields.](../../05-data-definition-screen.png)

Using that reference as a nice cheat-sheet, you can now create the data type you need for "Volunteer Work Sign-Up". Maybe a `Text` type for the name, copious options as `Select` for all the tasks they can volunteer for (obviously set to allow multiple options), and a `File Upload` so they can upload an image of themselves. After all, how much more official-feeling and fun is it if you can print out some nifty badges?

When creating data definitions, you can also customize the appearance of the input fields, and provide helpful tips and hints for those entering data. Some data types have specific configuration options, but all have some in common. The following properties can be edited by double-clicking on any field, by clicking the gear icon in the upper-right corner of the field, or by clicking the *Settings* tab when the field is selected (highlighted with a green background). Most of the properties you will find there are fairly self-explanatory, but there are a few that need a closer look --

**Multiple (Select):** When set to `Yes`, allows the user to select more than one option. This defaults to no.

**Allowed File Extensions (File Upload):** By default, form validation will accept any file type to be submitted. Set this value to a comma-delimited list of extensions, including the character `.`, and Liferay will check the extension before the file can be uploaded.

**Folder (File Upload):** Set the location the document will be uploaded to in the document library. You can choose from an existing folder, create one, or default to the document library's home location.

![Figure 5.3: Data field properties.](../../05-data-definition-field-properties.png)

In addition to dragging the fields around to create your desired forms, you can stack inputs within inputs, by dragging a field within another field. Organize your data into unlimited levels of hierarchy, creating the clearest, most logical data model easily. There is also a duplicate button on each field (the middle button), allowing you to easily clone any field as many times as you need.

That really covers the basic tools that users of `list.it` need to get rolling with an unlimited array of custom types. But just like we talked about at the beginning, most users will probably want to use a host of pre-defined data types already. A number of data definitions ship with the portal to help you get started: *To Do*, *Issues*, *Meeting Minutes*, and *Contacts*. Use these on their own to generate new data lists, or tweak them to fit your use case. All that's left to do is build a new data list, and let your users play with it.

## Creating Data Lists

Building out new lists really isn't all that different from creating new pieces of web content. Just as you can create new Web Content Structures to control the input of a particular type of web content, you can use Data List Definitions to control the input of new list types. Similarly, just as you simply create a new piece of web content, selecting the Structure you would like to use with it, `list.it` users (we'll call them *Listies*) will choose the Definition they want to use when creating a new list. With a data definition in place, all that remains is to create a new data list to capture the information we're going after. This is the easiest step in creating the list, with only a few clicks between a data definition and robust data entry and delivery.

If we wanted to create a new volunteer list with the "Volunteer Sign-Up" definition:

1.  From the *content* section of the Control Panel, select Dynamic Data Lists.
2.  Click on *Add* to create a new list based on a data definition, which in our case will be the job post.
3.  Give the data list a name, like *Open Jobs*, and a description to assist administrative users in the future.
4.  Last, and most importantly, click *Select* under the *Data Defition* section -- this is where you set the data model that drives this list.
5.  Choose the *Volunteer Sign-Up* data definition you created, then click *Save*.

Now that you've created your brand now volunteer list, you can pester everyone you know to sign up. But what would it look like for them to add an entry to this list? Using a data definition you've previously created (or selected), the appropriate form will appear on the page.

![Figure 5.4: Entering a new data record.](../../05-ddl-add-record.png)

Now, this example here is some stodgy old job posting that radically cool *Listies* would never come up with, but it is a good example. Just looking at the form reminds of precisely the way it looked when you first defined it. And that's as it should be: build the data type the way you want it, and have it appear consistently to your end users.

But how will this data appear? How will my awesome, new Volunteer Sign-Up sheet appear, or that boring Jobs Listing appear? The answers to those pressing, burning questions bring us to the mecca that is the display side of this equation.

### Using Default Displays

The average Listie isn't going to be too concerned with customizing the display much, and will likely see something like this when they display a new list:

![Figure 5.5: The default data list display.](../../05-ddl-list-display.png)

This isn't all that exciting, but it allows users to see the list's contents, and if they have permission, to add and/or edit list items. Within a site like `list.it`, this type of interaction will either be used for display-only lists that the user chooses to expose to others, or for the user's own private lists that they interact with. It could also be used to display a beautiful spreadsheet, so you can view the responses to your Volunteer Sign-Up in a comfortable, easy-to-read format. In other environments, the Dynamic Data List display provides an easy way for a user (such as a member of a site) to interact with whatever list is active and available.

While you can interact with the data list within the control panel, and ask everyone to contribute through the content section of their control panel, it's more likely you want to give them a simple way to access the list. Liferay provides the *Dynamic Data List Display* portlet to ease the integration of your new list onto your site. With your jobs posting list in hand, head over to the page you want, and add the portlet. Like the web content display, you just configure the data list display to show your list and you're done! The default display will spill out the contents of the list, but can be configured to use a different display template, which will be explored later. The two important configuration options to consider are:

-  **Editable:** Allows users that have permission to add new entries to the list. By default, this is disabled and when enabled, administrators are the only ones with add permission. To easily grant access to other users, edit the permissions on the list you'd like to grant access to, and grant the `Add Record` permission.

-  **Spreadsheet View:** Displays the list in a dynamic spreadsheet view, allows users with permission to interact with the list in the same way as in a standard spreadsheet program.

If you need more customizability with the display of the list or the data entry form, you may want to consider a custom *list template* or *detail template*.

![Figure 5.6: The spreadsheet view](../../05-ddl-spreadsheet-view.png)

Now, as useful as this default display is, and it's certainly useful for my To Do list and my Memo notes, it can be an awkward way to ask my Volunteers to sign up. In fact, any time I want other Listies to interact with my lists and contribute responses, I really just want a simple form to show them. They don't really need to see the full range of responses to what others have said. And in some cases, it can be hazardous to your health for everyone to see the responses. Then you don't have to explain why your sister-in-law won't work with your brother on the same task because of his B.O. problem.

### Using Data List Forms

For those moments you just don't want others to see your lists in the standard Dynamic Data List Display, Liferay provides the Dynamic Data List Forms portlet. Unlike the data list display, the forms portlet is tailored to entering new records. When you deploy that data list for your users to sign up for a retreat, or your family members to volunteer to help you move, using the data list form allows you to simplify the sign-up process and hide the contents of the list.

Using the Dynamic Data List Form is exactly the same as using the Web Content Display portlet: just set it up, point it to a list (either existing or new), and let it go. However, unlike the Web Content Display portlet, the Dynamic Data List Forms portlet may not be installed already in your portlet. If not, just head over to *Plugins Installation* in the *Server Administration* section of your Control Panel. You should be able to find it there, install it, and deploy away.

If you're not a Listie, and you happen to be deploying custom lists in an environment that requires approval from other users, then it's not enough to just create the list and display a form. What you need is a real integration with Workflow. Now, we talk so much about Workflow in other places, that we won't bore you with the details here, but you should know that Workflow both works and flows well with Dynamic Data Lists.

### Using Workflow

Liferay integrates the powerful features of workflow and the data capabilities of dynamic data lists in *Kaleo Forms*. Workflow is not directly enabled in the dynamic data list portlets, instead allowing you to build custom forms backed by a data list. You can then deploy custom workflows to the form and its data. Though Kaleo Forms is only available in Enterprise Edition, you can still apply a workflow to a list when creating it. See the section on Workflow for more information.

If you are a Listie, or a `list.it` developer, then you're less interested in the intricacies of workflow, and more excited about showing your lists to the world. That is, in fact, the reason you created `list.it` in the first place, right?

## Make It Pretty: Creating Custom Displays

When creating custom lists and data definitions, you might want to control not only how the input form appears to your users, but also how the list itself displays. It could be a few months down the road, and you realize you need to create another sign-up sheet, but don't need the level of detail provided by the Volunteer Sign-Up data definition you created. Liferay empowers you to customize both the input and output of your lists to unlimited levels. Dynamic data lists provide two areas to customize: detail templates and list templates. This covers the forms of lists (*detail templates*), as well as the display of the list contents (*list templates*). 

### Detail Templates

The default data entry form is simply the entire data model you created in a data definition, both required and optional fields. Listies that create new lists using a data definition would expect to see every item in that definition on the input form. However, maybe you want a quick sign-up form to find out who's coming to dinner tonight? Using a detail template, you can limit the fields displayed for entry, or change the order of elements. Data definitions can have multiple templates, that you choose when displaying in either a dynamic data list display, or a dynamic data list form. To access and create new templates, go to *Control Panel -> Content -> Dynamic Data Lists -> Manage Data Definitions*, choose the data model you want to modify, click the *Actions* button, and choose *Manage templates*. Once you click on *Add Detail Template*, you'll be presented with the same kind of graphical, drag-and-drop interface used for creating the data definition. Move items around, and delete unwanted fields from view and save when ready. Create as many as you might need, and change them around in the portlets to see how each feels.

Now your friends and enemies alike will be impressed with your `list.it` skills. It may look to the untrained eye like you have singlehandedly created three or four different data types for your lists, but you know better. You used the power that detail templates provide, and made one data model that encompasses the maximum information you might need (like preferred activity, favorite color, and ideal schedule), then quickly churned out four different detail templates with a few mouse clicks. Now that you have such a vast amount of data collection options, how will you display them? However you want, as we're about to find out.

### List Templates

For every data definition, you have an unlimited number of displays you can create. If you created a special "Thanksgiving Dinner Sign-Up" list using your "Volunteer Sign-Up" definition, you wouldn't want to confuse fellow Listies by displaying data fields you never asked for. "Preferred task?" a friend might say, "I don't remember seeing *that* on the sign-up form!" To avoid such embarassing situations, you'll want to create a custom display to match that list. Taking it even further, you may want to provide a fancy, Javascript-driven image carousel preview of all the attendees of the party. This would complement your other displays, and just be another bragging right on `list.it`. List templates give you the power to do all this and more.

Just like detail templates, list templates are found in the Manage Templates section of a data definition. With list templates, you can customize the display of a list in precisely the same way as　you can customize web content. List templates allow either Freemarker or Velocity templates, pulling data from the data definition in the same way that web content templates pull data from their structures. For more information on using Velocity and other templates to pull data from a backing structure, see web content templates.

All of the knowledge you have accrued through building out your award-winning content can be brought to bear in list templates. With the full power of Velocity templates at your fingertips, you have easy access to all of the data in the list, as well as the full complement of helper methods and the Alloy UI Javascript library to make easy work of dynamic displays. Since all these technologies are documented so successfully elsewhere, it would be tedious and boring to rehash it all there. And really, it's been said so well before, we might as well look back on it and enjoy.

## Take It Further

Our `list.it` experience is now much more enjoyable than when we first signed up. As new Listies, we had no idea how to define our own data types for our lists, let alone how to create a list. You can now be the envy of your co-workers as you breeze through list setup and data definitions. Once you have your new lists set up, you'll want to work through building new, custom input forms for the data. Your friends on `list.it` will thank you, and wonder how you were able to accomplish it all. That will be nothing next to the masterpiece of design that you show off in your custom displays through list templates. Once the lists have the precise look and feel you envisioned, then living amongst the Listies will not only be easy, but fun and exciting.

With the ability to create dynamic sets of data, customize both the data display as well as the entry, the possible combinations are limitless. In　some ways, it can be viewed as a way to deliver small-scale applications that display desired information quickly. Whether you're building a site like `list.it`, or a real estat listing service, you'll find the limitless power of dynamic data lists enticing, easy to use, and above all, empowering.
