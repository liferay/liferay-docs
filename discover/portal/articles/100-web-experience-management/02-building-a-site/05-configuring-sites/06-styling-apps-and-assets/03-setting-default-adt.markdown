# Setting a Default ADT [](id=setting-a-default-adt)

You can change the ADT for an individual widget through its own configuration,
but to configure the default ADT for all widgets of that type, you must go to
**System Settings**. In the System Settings you can find a configuration for
every widget in Liferay. Any widget that supports ADTs has a *Display Style
Group ID* and a *Display Style* option.

![Figure 1: The ADT configuration in System Settings lets you change the display style.](../../../../../images/adt-system-settings.png)


- **Display Style Group ID:** The Site ID where the ADT is located. For Global
  templates use *0* for the ID.
 
- **Display Style:** The ADT's template key. 

To enter a Display Style, you first need the **Template Key** for the template
you want to use. To get the Template Key, go to the *Application Display 
Template* list for a given Site and retrieve it from the ADT listing. Then
enter the display style as `ddmTemplate_[template-key]`.

## Default ADT Example [](id=default-adt-example)

For example, configure the Language Selector ADTs like this:

1.  Open the *Menu*.

2.  Using the *Site Selector*, select the *Global* site.

3.  Go to *Build* &rarr; *Application Display Templates*

4.  Find the *Icon* *Language Selector Template* and click on it.

5.  Open *Details* and find the *Template Key* - `LANGUAGE-ICON-FTL`

![Figure 2: System Settings shows where you can find the Template Key.](../../../../../images/adt-system-settings.png)

Now that you have the ID, you can change the template from System Settings.

1.  Once you have the ID, go to the *Control Panel* &rarr; *System Settings* 

2.  Find *Localization* under the *Platform* heading and select *Language 
    Selector* from the options on the left.
 
3.  In the *Display Style* field, enter `ddmTemplate_LANGUAGE-ICON-FTL`.
 
Now any Language Selector widgets are added to a page use the new defaults.
This doesn't affect widgets already added to a page and configured.

![Figure 3: You can see the new default configuration.](../../../../../images/adt-new-default.png)
