---
header-id: adding-a-color-scheme-variant-for-the-lunar-resort-theme
---

# Adding a Color Scheme Variant for the Lunar Resort Theme

[TOC levels=1-4]

In this section, you'll create a color scheme variant for the Lunar Resort Theme 
to apply during the Lunar Eclipse, when special discounts are available. You'll 
create a color scheme that reflects the reds and yellows present during a lunar 
eclipse. Since the majority of the Lunar Resort Site's content is created with 
page fragments, you must account for the color scheme styles in the page 
fragments as well. Follow these steps:

1.  Open the theme's `WEB-INF/liferay-look-and-feel.xml` file and add these 
    color-scheme entries above the `<portlet-decorator>..</portlet-decorator>` 
    ones:

    ```xml
    <theme id="my-theme-id" name="My Theme Name">
      <template-extension>ftl</template-extension>
      <color-scheme id="01" name="Default">
      		<default-cs>true</default-cs>
      		<css-class>default</css-class>
      		<color-scheme-images-path>
      				${images-path}/color_schemes/${css-class}
      		</color-scheme-images-path>
      </color-scheme>
      <color-scheme id="02" name="Eclipse">
      	<css-class>eclipse</css-class>
      </color-scheme>
      ...
    </theme>
    ```

    | **Note:** Color schemes are sorted alphabetically by `name` rather than 
    | `id`. For example, a color scheme named `Clouds` and `id` `02` would be 
    | selected by default over a color scheme named `Day` with `id` `01`. The 
    | `<default-cs>` element overrides the alphabetical sorting and sets the 
    | color scheme that is selected by default when the theme is chosen.  

2.  Open `/src/css/_colors.scss` and update the colors to include the two new 
    ones (eclipse yellow and eclipse red) for the color scheme:

    ```sass    
    $lunar-resort-orange: #dfa356;
    $lunar-resort-blue: #415fa7;
    $lunar-resort-link-teal: #00ccFF;
    $lunar-resort-eclipse-yellow: #dfd456;
    $lunar-resort-eclipse-red: #a75441;
    ```

3.  Create a `/src/css/color_schemes/` folder for the color scheme, and add a 
    `eclipse.scss` file to it for the Eclipse color scheme. The default color 
    scheme's styles are included in `_custom.scss`, so you don't need to create 
    anything for them. 

4.  The color scheme's class is added to the `<body>` element when the theme's 
    color scheme is applied, so you must prefix the body styles with the 
    `eclipse` class to target the proper color scheme. Open 
    `/src/css/color_schemes/eclipse.scss` and add this import and styles to it 
    to use the new colors you defined:

    ```sass
    @import '../colors';
    
    body.eclipse {
      
      a.btn-orange {
        background-color: $lunar-resort-eclipse-yellow;
      }
    
      header {
        background-color: $lunar-resort-eclipse-red;
    
        .user-avatar-link .lexicon-icon {
          color: $lunar-resort-eclipse-red;
        }
        
        li.nav-item {
          
          ul.child-menu {
            background-color: $lunar-resort-eclipse-red;
          }
            
          &:hover ul.child-menu {
            background-color: $lunar-resort-eclipse-red;
          }
          
          &.selected {
            & a.nav-link {
              color: $lunar-resort-eclipse-red;
              &:hover {
                color: $lunar-resort-eclipse-red;
              }
            }
          }
        }
      }
    
      #footer {
        background-color: $lunar-resort-eclipse-red;
      }
    
    }
    ```

5.  Import the eclipse color scheme's CSS file into `_custom.scss` so it's 
    loaded with the rest of the custom styles:

    ```sass
    @import "color_schemes/eclipse";
    ```

6.  You must create thumbnails for each color scheme, just like you did the 
    theme. To save time, copy the 
    [`lunar-resort-build/assets/images/color_schemes/`](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/lunar-resort-theme/lunar-resort-build/assets/images/color_schemes) 
    folder to the theme's 
    `/src/images/` folder. Note that the color scheme folder names match the 
    color scheme CSS class names defined in `liferay-look-and-feel.xml`.

7.  Now that the color scheme is created, you must update the page fragments to 
    use the eclipse color scheme class so they have the same look as the color 
    scheme when it's applied to the page. The fragments don't have access to the 
    SASS color variables, so you must use the hexadecimal color codes. To save 
    time, import the updated page fragments from the 
    `lunar-resort-build/assets/` folder. Open the Control Menu and navigate to 
    *Site Builder* &rarr; *Page Fragments*, click the Actions menu next to 
    COLLECTIONS, and select the *Import* option. Import the 
    `collections-lunar-resort-color-scheme.zip` asset from the 
    [`lunar-resort-build/assets/`](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/lunar-resort-theme/lunar-resort-build/assets) 
    folder. Note that each fragment style that requires a color change is 
    duplicated and prefixed with `body.eclipse`. A couple example configurations 
    are shown below:
    
    ```css
    .fragment_35201 h3.text-center {
      background-color: #dfa356;
      color: #FFF;
    }

    body.eclipse .fragment_35201 h3.text-center {
      background-color: #dfd456;
    }
    ```

    ```css
    .fragment_35201 a.btn {
      background-color: #415fa7;
    }

    body.eclipse .fragment_35201 a.btn {
      background-color: #a75441;
    }
    ```

8.  Deploy the theme. Open the Control Menu, navigate to *Site Builder* &rarr; 
    *Pages*, and click the Gear icon next to *Public Pages*. Select the Eclipse 
    color scheme under the *LOOK AND FEEL* tab and save to apply the changes. 
    
    ![Figure 1: Color schemes are a good way to subtly change the look and feel of your site.](../../images/theme-tutorial-color-schemes.png)
    
    The theme should look like the figure below with the Eclipse color scheme 
    applied:

    ![Figure 2: The finished color scheme gives the Lunar Resort site a fiery glow.](../../images/theme-tutorial-eclipse-color-scheme.png)

Great! You've seen how you can quickly change the look and feel of the Lunar 
Resort with just a simple color scheme. Now you know how to develop a theme to 
customize the overall look and feel of your site! 

See the [Themes section](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) 
for information on developing themes. 
