---
header-id: customizing-the-lunar-resorts-header-and-logo
---

# Customizing the Lunar Resort's Header and Logo

[TOC levels=1-4]

The Header contains the navigation and logo for the site. In this section you'll 
customize the look and feel of the Header and add a custom logo. 

Follow these steps:

1.  Open `portal_normal.ftl` and replace the `<header>...</header>` element and 
    contents with the updated code snippet below. This updates the structure 
    slightly, making the banner expand the full width of the Header, and adds a 
    new `header_css_class` variable to the `class` attribute. This variable is 
    defined in a later step.
    
    ```markup
    <header class="${header_css_class}">
    	<div class="container-fluid" id="banner" role="banner">
    		<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
    			<img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" />
    			<#if show_site_name>
    				${site_name}
    			</#if>
    		</a>

    		<#if has_navigation>
    			<#include "${full_templates_path}/navigation.ftl" />
    		</#if>
    	</div>
    </header>
    ```

2.  Replace the `<div class="container-fluid" id="wrapper">` element with the 
    updated code below to remove some margins and padding:
    
    ```markup
    <div class="container-fluid mt-0 pt-0 px-0" id="wrapper">
    ```
    
    And move the wrapper down, and place it directly above the 
    `<section id="content">` element:
    
    ```markup
    <div class="container-fluid mt-0 pt-0 px-0" id="wrapper">
      <section id="content">
      ...
      </section>
      <footer...>
      ...
      </footer>
    </div>
    ```

3.  The logo's height is retrieved with the `${site_logo_height}` variable. The 
    height of the logo is a bit too large for the Lunar Resort theme, so you 
    must adjust it. Remove the `width` attribute from the logo's image so it 
    defaults to `auto`:
    
    ```markup
    <img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" />
    ```
    
4.  Create `init_custom.ftl` in your theme's `/src/templates/` folder and assign 
    the logo's `site_logo_height` variable to the value below:
    
    ```
    <#assign site_logo_height = 56 />
    ```

5.  Assign the new `header_css_class` variable you added in step one to the 
    value below:

    ```markup
    <
    #assign header_css_class = 
    "navbar navbar-expand-md navbar-dark flex-column flex-md-row bd-navbar" 
    />
    ```

    This applies Bootstrap and Clay utility classes to provide the overall look 
    and feel of the Header. Assigning the classes to a variable keeps 
    `portal_normal` clean and makes the code easy to maintain. If you want to 
    update the classes, you just have to modify the variable 
    (e.g. `header_css_class = header_css_class + " my-new-class"`).

6.  Add the code snippet below to update the `logo_css_class` variable to use 
    Bootstrap's `navbar-brand` class:

    ```markup
    <#assign logo_css_class = logo_css_class + " navbar-brand" />
    ```

7.  Before you upload the theme to see what it looks like so far, you must 
    [create a theme thumbnail](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-thumbnail-preview-for-your-theme) 
    so you can identify it. To save time, copy the `thumbnail.png` asset from 
    the [`lunar-resort-build/assets/images/`](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/lunar-resort-theme/lunar-resort-build/assets/images) 
    folder to a new `/src/images/` folder. Note that its dimensions are 480px by 
    270px. These dimensions are required to display the theme thumbnail properly.

8.  The theme isn't complete yet, but you'll deploy what you have so you can 
    replace the default logo with the Lunar Resort logo. Enable [Developer Mode](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes) 
    before deploying your theme, so the theme's files are not cached for future 
    deployments. Start the server, if it's not already started, and deploy the 
    theme with the command below: 

    ```bash
    gulp deploy
    ```

9.  Before you configure the pages, you must import the Lunar Resort's pages. 
    Open the Control Menu and navigate to *Publishing* &rarr; *Import*. Click 
    the Plus button to create a new import process. Click *Select File* and 
    import the `lunar_resort_pages.lar` from the [`lunar-resort-build/assets/`](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/lunar-resort-theme/lunar-resort-build/assets) 
    folder. Keep the default settings and click *Import*.

10.  Open the Control Menu and navigate to *Site Builder* &rarr; *Pages*. Click 
     the Gear icon next to *Public Pages* to open the configuration menu. Under 
     the *Look and Feel* tab, scroll down and click the *Change Current Theme* 
     button and select the Lunar Resort Theme. Scroll to the Logo heading, click 
     the *Change* button, upload the `lunar-resort-logo.png` asset from the 
     [`lunar-resort-build/assets/images/`](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/lunar-resort-theme/lunar-resort-build/assets/images) 
     folder, and click the *Save* button to apply the theme and logo.
    
Great! You've customized the Lunar Resort's Header and applied a custom logo. 
Next, you'll configure and customize the theme's navigation. 
