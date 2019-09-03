# Customizing the Navigation

There are two approaches to take when creating the navigation. You can use the 
default navigation provided in `navigation.ftl` and customize the markup 
template, or you can embed the navigation portlet in the theme and customize its 
preferences. The navigation portlet's default widget template uses the same 
markup, for the most part, as that used in `navigation.ftl`. In this tutorial, 
you'll take the former approach and customize the default configuration in 
`navigation.ftl`. Later, you'll embed the navigation portlet in the footer and 
configure its preferences to only display the top level navigation. 

The Navigation template iterates through the existing navigation items (pages) 
defined in @product@ and assigns the template's markup for each of them. This 
makes adding new pages, or deleting existing ones, much easier, thereby cutting 
down on the maintenance costs. 

Follow these steps to configure the header's navigation:

1.  Start by copying the default `navigation.ftl` file from the `/build` folder 
    into the `/src/templates` folder.

2.  Add the User Personal Bar to the top of the navigation template. By default, 
    this is hidden from the theme. You can either enable this via System 
    Settings outside the scope of the theme, or you can include it in your 
    theme. In this case, you'll include it in the theme. Open the 
    `navigation.ftl` template you just copied and add the User Personal Bar 
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
    `<nav>...</nav>` element with the `<div>` shown below.
    
    ```html
    <div class="collapse navbar-collapse" id="lunarNav">
    ```

4.  Open the `portal_normal.ftl` template and find this conditional wrapper:

    ```markup
    <#if has_navigation && is_setup_complete>
      <#include "${full_templates_path}/navigation.ftl" />
    </#if>
    ```

    Add the navigation toggler just above the `<#include.../>` to toggle the 
    mobile nav's visibility. This targets the `#lunarNav` wrapper that you added 
    in the previous step.

    ```html
    <button aria-controls="navigation" aria-expanded="false" 
    class="btn-monospaced ml-auto navbar-toggler" data-target="#lunarNav" 
    data-toggle="collapse" type="button">
      <span class="navbar-toggler-icon"></span>
    </button>
    ```

4.  Add the `nav-item` class to the `nav_item_css_class` variable 
    declaration in `navigation.ftl`:
    
    ```markup
    <#list nav_items as nav_item>
    	<#assign
    		nav_item_attr_has_popup = ""
    		nav_item_css_class = "nav-item"
    		nav_item_layout = nav_item.getLayout()
    	/>
      ...
    ```

5.  Add the `navbar-nav` and `mr-auto` classes to the `<ul>` element in 
    `navigation.ftl`:

    ```html
    <ul aria-label="<@liferay.language key="site-pages" />" class="navbar-nav mr-auto" role="menubar">
    ```

6.  Add a call to action for the visitors to the Lunar Resort site, so they 
    can book their flight now. Add the book now button's code below the closing 
    `</nav>` element. This uses some utility classes for the basic look and feel 
    and ordering, as well as a custom `btn-orange` class that you'll reference 
    later:

    ```html
    <a aria-controls="book-now" class="btn text-white btn-orange order-md-2">
    	<p class="book-now-text mb-0">Book Now</p>
    </a>
    ```
    
7.  Now for some styling. The Lunar Resort has a straight forward color scheme 
    made up of three colors: orange, white, and blue. Since these colors will be 
    used throughout the theme, you'll store them in SASS variables in a separate 
    file. Create a new file called `_colors.scss` inside the theme's `src/css/` 
    folder and add these variables to it. Note that White is already defined as 
    the global variable `$white` by the Atlas theme.

    ```scss
    $lunar-resort-orange: #dfa356;
    $lunar-resort-blue: #415fa7;
    $lunar-resort-link-teal: #00ccFF;
    ```

8.  Now that the main colors are defined, open `/src/_custom.scss` and add the 
    code snippet below. This imports the `_colors.scss` file so you can use the 
    variables you just created. It adds some basic styling for the header and 
    navigation, including a style to highlight the page that is currently active 
    via the `selected` class:
    
    ```scss
    @import 'colors';

    body {
      
      a.btn-orange {
        background-color: $lunar-resort-orange;
        margin-right: 5px;
        
        &:hover {
          border-color: $white;
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
        }
      }
    }
    ```

9.  So far, you've modified the default navigation template for standard 
    navigation. Now, you'll make some minor modifications to account for the 
    the Control Menu which is displayed on top of everything when the user is 
    signed in, causing the header to disappear. Fortunately, @product@ provides 
    a few different classes that are added to the `body` of the page when each 
    product navigation is visible. The `has-control-menu` class is added to the 
    body when the Control Menu is visible. Add a top margin to the Header that's 
    equal to the height of the Control Menu. Open `_custom.scss` and add this 
    code snippet just above the closing bracket for the `body`:

    ```scss
    &.has-control-menu {
      header {
        margin-top: 56px;  
      }
    }
    ```

10.  The Control Menu's height is slightly smaller on mobile devices, so you'll 
     need to account for that responsiveness in your styling. Use the  
     `@include media-breakpoint-down` breakpoint to respond to smaller devices. 
     Update the code snippet you just added to match the one below:
     
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

Great! The Header's navigation is customized. Next, you'll define the Footer and 
embed a navigation portlet to display navigation. 