# Customizing the Lunar Resort's Header and Logo

The header contains the navbar and logo for the site. In this section you'll 
customize the header to use utility classes and a custom logo. 

Follow these steps:

1.  Open `portal_normal.ftl` and add a `class` attribute to the `<header>` 
    element with the value `header_css_class`. This variable doesn't exist yet, 
    so you'll need to define it in the theme's `init_custom.ftl`.

    ```markup
    <header class="${header_css_class}">
    ```

2.  Create `init_custom.ftl` in your theme's `/src/templates` folder and assign 
    the new `header_css_class` variable to the value below:

    ```markup
    <
    #assign header_css_class = 
    "navbar navbar-expand-md navbar-dark flex-column flex-md-row bd-navbar" 
    />
    ```

    This applies the Bootstrap and Clay utility classes to the header. These 
    classes could of course be added directly in `portal_normal`, but this keeps 
    the code much cleaner, and leaves room for you to dynamically update it 
    later if you need to by simply adding the new class(es) to the variable 
    (e.g. `header_css_class = header_css_class + " my-new-class"`).

3.  Now that you've updated the logo's classes, you can replace the default 
    Liferay logo with the Lunar Resort logo. The theme isn't complete yet, but 
    you'll deploy what you have, so you can modify the logo and see what the 
    header looks like. Make sure to enable [Developer Mode](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes) 
    before deploying your theme, so the theme's files are not cached for future 
    deployments. Start your server if it's not already started, open the Control 
    Menu, and navigate to *Site Builder* &rarr; *Pages*. Click the *Cog icon* 
    next to *Public Pages* to open the configuration menu. Under the 
    *Look and Feel* tab, scroll down and click the *Change Current Theme* button 
    and select the Lunar Resort Theme. Scroll to the Logo heading and click the 
    *Change* button and upload the `lunar-resort-logo.png` asset from the 
    `lunar-resort-build/assets/images/` folder and click the *Save* button to 
    apply the theme and logo. 