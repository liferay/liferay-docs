---
header-id: customizing-the-navigation
---

# Customizing the Navigation

[TOC levels=1-4]

Navigation items (pages) are defined and configured in @product@. The Navigation 
template iterates through the existing navigation items (pages) and assigns the 
template's markup for each of them. Page updates therefore require no updates to 
the theme directly and can be made by a Site Administrator, thus reducing the 
maintenance costs. 

To customize the navigation, you can either use the default navigation provided 
in `navigation.ftl` and customize the markup template, or you can embed the 
navigation portlet in the theme and customize its preferences. Both approaches 
use the same overall markup. This section takes the former approach and 
customizes the default configuration in `navigation.ftl`. in the next section, 
you'll embed the navigation portlet in the Footer and configure its preferences 
to only display the top level (parent) navigation items. 

Follow these steps to configure the Header's navigation:

1.  Copy the default `navigation.ftl` file from the `/src/build/templates/` 
    folder into the theme's `/src/templates/` folder. The `build` folder was 
    generated when you built the theme and again when you initially deployed the 
    theme in the last section.

2.  By default, the User Personal Bar is hidden from the theme. You can either 
    enable this via System Settings outside the scope of the theme, or you can 
    include it in your theme. In this case, you'll include it in the theme. Open 
    the `navigation.ftl` template you just copied and add this User Personal Bar 
    markup to the top:

    ```markup
    <div class="mx-1 mx-sm-3 order-md-1 lunar-user">
    	<@liferay.user_personal_bar />
    </div>
    ```
    along with some utility classes to position and order the User Personal Bar, 
    this also adds a custom `lunar-user` class, which you'll use later for 
    styling. 

3.  Modify the default template to use Bootstrap's `navbar` format. Wrap the 
    `<nav>...</nav>` element with the `<div>` shown below:
    
    ```html
    <div class="collapse navbar-collapse" id="lunarNav">
      <nav ... >
      </nav>
    </div>
    ```

4.  Open the `portal_normal.ftl` template and find this conditional wrapper:

    ```markup
    <#if has_navigation>
      <#include "${full_templates_path}/navigation.ftl" />
    </#if>
    ```

    Update the conditional to include the menu toggler for the mobile 
    navigation. This targets the `#lunarNav` wrapper that you added in the 
    previous step:

    ```markup
    <#if has_navigation>
      <button 
        aria-controls="navigation" 
        aria-expanded="false" 
        class="btn-monospaced ml-auto navbar-toggler" 
        data-target="#lunarNav" 
        data-toggle="collapse" 
        type="button">
        <span class="navbar-toggler-icon"></span>
      </button>
      <#include "${full_templates_path}/navigation.ftl" />
    </#if>
    ```

5.  Open `navigation.ftl` and add the `navbar-nav` and `mr-auto` classes to 
    the `<ul>` element at the top: 

    ```html
    <ul aria-label="<@liferay.language key="site-pages" />" class="navbar-nav mr-auto" role="menubar">
    ```

6.  Open `navigation.ftl` and replace the first `<#assign... />` declaration 
    with the one below. This adds the `nav-item` class to the 
    `nav_item_css_class` variable declaration in `navigation.ftl` and declares a 
    new `nav_item_caret` variable:
    
    ```markup
    <#assign
      nav_item_attr_has_popup = ""
      nav_item_css_class = "nav-item"
      nav_item_layout = nav_item.getLayout()
      nav_item_caret = ""
    />
    ```

7.  Replace the `nav_item.isSelected` conditional block with the one shown 
    below. This adds the `selected` class to the existing `nav_item_css_class` 
    classes:
    
    ```markup
    <#if nav_item.isSelected()>
      <#assign
        nav_item_attr_has_popup = "aria-haspopup='true'"
        nav_item_css_class = "${nav_item_css_class} selected"
      />
    </#if>
    ```
    
8.  The Lunar Resort contains nested pages (child navigation items). By default, 
    child navigation items are displayed at the block level. Instead, the 
    Administrator wants to display these items in a dropdown list that is only 
    displayed on hover of the parent navigation item. Add this conditional block 
    directly below the `nav_item.isSelected` block you just modified. This adds 
    the `dropdown` class to the parent navigation item and updates the 
    `nav_item_caret` variable to hold Clay caret icon markup to indicate the 
    parent navigation has nested child items:
    
    ```markup
    <#if nav_item.hasChildren()>
      <#assign
        nav_item_css_class = "${nav_item_css_class} dropdown"
        nav_item_caret = '<svg class="lexicon-icon">
        <use xlink:href="${images_folder}/lexicon/icons.svg#caret-bottom" />
        </svg>'
      />
    </#if>
    ```

9.  Locate the anchor's markup below: 

    ```markup
    <a aria-labelledby="layout_${nav_item.getLayoutId()}" 
    ${nav_item_attr_has_popup} 
    href="${nav_item.getURL()}" 
    ${nav_item.getTarget()} 
    role="menuitem"
    >
      <span>
        <@liferay_theme["layout-icon"] layout=nav_item_layout /> 
        ${nav_item.getName()}
      </span>
    </a>
    ```

    Replace it with the updated markup shown below to include the 
    `${nav_item_caret}` variable:
    
    ```markup
    <a 
      aria-labelledby="layout_${nav_item.getLayoutId()}" 
      class="nav-link" ${nav_item_attr_has_popup} 
      href="${nav_item.getURL()}" 
      ${nav_item.getTarget()} 
      role="menuitem"
    >
      <span>
        <@liferay_theme["layout-icon"] layout=nav_item_layout /> 
        ${nav_item.getName()}
      </span> 
      ${nav_item_caret}
    </a>
    ```

10.  Add the `dropdown-menu` class to the `<ul class="child-menu" role="menu">` 
     element and replace the `nav_child_css_class` variable declarations with 
     the ones below to add the `nav-item` class to them:
     
     ```markup
     <#assign
       nav_child_css_class = "nav-item"
     />

     <#if nav_item.isSelected()>
       <#assign
         nav_child_css_class = "nav-item selected"
       />
     </#if>
     ```

11.  Find the `<a>` element with the 
     `aria-labelledby="layout_${nav_child.getLayoutId()}"` attribute and add the 
     `class="nav-link"` attribute to it:

    ```markup
    <a aria-labelledby="layout_${nav_child.getLayoutId()}" class="nav-link"...></a>
    ```

12.  Add a call to action for the visitors to the Lunar Resort site so they can 
     book their flight. Add the book now button's code below the closing 
     `</nav>` element. This uses some utility classes for the basic look and 
     feel and ordering, as well as a custom `btn-orange` class that you'll 
     provide styling for later:

    ```html
    <a aria-controls="book-now" class="btn text-white btn-orange order-md-2">
    	<p class="book-now-text mb-0">Book Now</p>
    </a>
    ```
    
13.  The Lunar Resort's color scheme is comprised of three colors: orange, 
     white, and blue. Since these colors are used throughout the theme, you'll 
     store them in SASS variables in a separate file. Create a new file called 
     `_colors.scss` inside the theme's `/src/css/` folder and add these 
     variables to it. Note that White is already defined as the global variable 
     `$white` by the Atlas theme.

     ```scss
     $lunar-resort-orange: #dfa356;
     $lunar-resort-blue: #415fa7;
     $lunar-resort-link-teal: #00ccFF;
     ```

14.  Now that the main colors are defined, open `/src/css/_custom.scss` and add 
     the code snippet below. This imports the `_colors.scss` file so you can use 
     the variables you just created. It adds some basic styling for the Header 
     and navigation, including a style to highlight the page that is currently 
     active via the `selected` class. It also displays the child menu items at 
     the block level on smaller devices with the 
     `@include media-breakpoint-down` breakpoint:
    
     ```scss
     @import 'colors';

     body {
      
       a.btn-orange {
         background-color: $lunar-resort-orange;
         margin-right: 5px;
         
         &:hover {
           border-color: $white;
         }
         
         @include media-breakpoint-down(sm){
           width: 100%;
         }
       }

       header {
         background-color: $lunar-resort-blue;
         
         .lunar-user a {
             color: $lunar-resort-link-teal;
         }

         .user-avatar-link .lexicon-icon {
           color: $lunar-resort-blue;
         }
        
         li.nav-item {
           & a.nav-link span {
             font-size: 1.5em;
           }

           &:hover ul.child-menu {
             background-color: $lunar-resort-blue;
             display: block;
             margin-top: -10px;
           }

           &.selected {
             background-color: $white;
             height: 73px;
             & a.nav-link {
               color: $lunar-resort-blue;
               font-weight: bold;
               &:hover {
                 color: $lunar-resort-blue;
                 font-weight: normal;
                 padding-left: 9.619px;
                 padding-right: 9.619px;
               }
             }
           }
          
           @include media-breakpoint-down(sm){
             ul.child-menu {
               display: block;
             }
           }    
         }
       }
     }
     ```

15.  The Control Menu is displayed on top of everything when the user is signed 
     in, which covers the Header. You must update the `navigation.ftl` template 
     to account for the Control Menu. @product@ provides a unique class that is 
     added to the `body` of the page when each product navigation (which 
     includes the Control Menu) is visible. Use the `has-control-menu` class is 
     added to the body when the Control Menu is visible. Open `_custom.scss` and 
     add this code snippet just above the closing bracket for the `body` to add 
     a top margin to the Header that's equal to the height of the Control Menu:

     ```scss
     &.has-control-menu {
       header {
         margin-top: 56px;  
       }
     }
     ```

16.  The Control Menu's height is slightly smaller on mobile devices, so you 
     must account for that responsiveness in your styling. Update the code 
     snippet you just added to match the one below:
     
     ```scss
     &.has-control-menu {
       header {
         margin-top: 56px;
         @include media-breakpoint-down(sm){
           margin-top: 48px;
         }  
       }
     }
     ```

Great! The Header's navigation is customized. The updated Header and logo should 
look like the figure below:

![Figure 1: The updated Header and navigation are much more user-friendly now.](../../images/theme-tutorial-updated-navigation.png)

Next, you'll define the Footer and embed a navigation portlet to display 
navigation. 
