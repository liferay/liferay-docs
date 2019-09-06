---
header-id: setting-a-default-widget-template
---

# Setting a Default Widget Template

[TOC levels=1-4]

You can change the widget template for an individual widget through its own
configuration, but to configure the default widget template for all widgets of
that type, you must go to *System Settings*. In the System Settings you can
find a configuration for every widget in @product@. Any widget that supports
widget templates has a *Display Style Group ID* and a *Display Style* option.

![Figure 1: The widget template configuration in System Settings lets you change the display style.](../../../../../images/adt-system-settings.png)


- **Display Style Group ID:** The Site ID where the widget template is located.
  For Global templates use *0* for the ID.
 
- **Display Style:** The widget template's key. 

To enter a Display Style, you first need the *Template Key* for the template
you want to use. To get the Template Key, go to the *Application Display
Template* list for a given Site and retrieve it from the widget template
listing. Then enter the display style as `ddmTemplate_[template-key]`.

## Default Widget Template Example

For example, configure the Language Selector widget templates like this:

1.  Open the *Product Menu*.

2.  Using the *Site Selector*, select the *Global* site.

3.  Go to *Site Builder* &rarr; *Widget Templates*

4.  Create a *Language Selector Template*.

5.  Click the *Actions* (![Actions](../../../../../images/icon-actions.png))
    button for the new widget template.

6.  Open *Details* and find the *Template Key* - `LANGUAGE-ICON-FTL`

![Figure 2: System Settings shows where you can find the Template Key.](../../../../../images/adt-template-key.png)

Now that you have the ID, you can change the template from System Settings.

1.  Go to the *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

2.  Find *Localization* under the *Platform* heading and select *Language 
    Selector* from the options on the left.
 
3.  In the *Display Style* field, enter `ddmTemplate_LANGUAGE-ICON-FTL`.
 
Now any Language Selector widgets are added to a page use the new defaults.
This doesn't affect widgets already added to a page and configured.

![Figure 3: You can see the new default configuration.](../../../../../images/adt-new-default.png)
