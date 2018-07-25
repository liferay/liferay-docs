# Setting a Default ADT

You can change the ADT for an individual widget through its own configuration, 
but to configure the default ADT for all widgets of that type, you need to go 
to **System Settings**. In the System Settings you can find a specific 
configuration for every widget in Liferay. Any widget that supports ADTs will 
have a *Display Style Group ID* and a *Display Style* option.

![Figure 1: Here is the ADT configuration in System Settings..](../../../../../images/adt-system-settings.png)


- **Display Style Group ID:** The site ID where the ADT is located. For Global
    templates use *0* for the ID.
 
- **Display Style:** The template key for the 

To enter a Display Style, you first need the **Template Key** for the template
you want to use. To get the Template Key, go to the *Application Display 
Template* list for a given site, and retrieve it from the ADT listing. Then,
enter the display style as `"ddmTemplate_[template-key]"`.

## Default ADT Example

For example, Configure the Language Selector ADTs like this:

1.  Open the *Menu*.

2.  Using the *Site Selector*, select the *Global* site.

3.  Go to *Build* &rarr; *Application Display Templates*

4.  Find the *Icon* *Language Selector Template* and click on it.

5.  Open *Details* and find the *Template Key* - `LANGUAGE-ICON-FTL`

![Figure 2: This is where you can find the Template Key.](../../../../../images/adt-system-settings.png)

Now that you have the ID, you can change the template from System Settings.

1.  Once you have the ID, go to the *Control Panel* &rarr; *System Settings* 

2.  Find *Localization* under the *Platform* heading, and select *Language 
    Selector* from the options on the left.
    
3.  In the *Display Style* field, enter `ddmTemplate_LANGUAGE-ICON-FTL`.
    
Now any Language Selector widgets that are added to a page will use the new 
defaults. This will not effect widgets that have already been added to a page
or already configured.

![Figure 2: You can see the new default configuration.](../../../../../images/adt-new default.png)
