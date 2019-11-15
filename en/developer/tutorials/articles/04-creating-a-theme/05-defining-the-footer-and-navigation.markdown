---
header-id: defining-the-lunar-resorts-footer-and-footer-navigation
---

# Defining the Lunar Resort's Footer and Footer Navigation

[TOC levels=1-4]

You've configured the Header and its navigation, but at the moment the Footer is 
a bit bare bones. In this section, you'll update the Footer to include contact 
information for the Lunar Resort and include navigation with an embedded 
navigation portlet. 

Follow these steps:

1.  To keep the Portal Normal template uncluttered, create a separate template 
    to hold the Footer's markup. Create a new file called `footer.ftl` in the 
    theme's `/src/templates/` folder.
    
2.  Copy the Footer markup (shown below) from `portal_normal.ftl` into 
    `footer.ftl`:
    
    ```markup
    <footer id="footer" role="contentinfo">
  		<p class="powered-by">
  			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
  		</p>
  	</footer>
    ```
    
    And update the `<p>` element in `footer.ftl` to include the classes shown 
    below:
    
    ```markup
    <footer id="footer" role="contentinfo">
  		<p class="powered-by text-center text-white py-3 mb-0">
  			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
  		</p>
  	</footer>
    ```     

3.  Add this `@liferay.navigation_menu` macro snippet above the `powered-by` 
    paragraph to embed the navigation portlet. This configuration stores the 
    portlet preferences in a `preferencesMap` variable. The `displayDepth` of 
    `1` specifies that the portlet must only render the top-level parent 
    navigation, and `portletSetupPortletDecoratorId` sets the portlet decorator 
    to `barebone`, which removes the portlet's wrapper and only renders the 
    portlet's content:

    ```markup
    <nav id="navbarFooter">
    	<div class="text-center mx-auto">
    		<div class="nav text-uppercase" role="menubar">
    			<#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />

    			<@liferay.navigation_menu
    				default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
    				instance_id="footer_navigation_menu"
    			/>
    		</div>
    	</div>
    </nav>
    ```

4.  The visitors need some social media links so they can keep tabs on the 
    latest and greatest news from the Lunar Resort. Replace the snippet you just 
    added with the one below. This uses [Clay icons](https://clayui.com/docs/components/icons.html) 
    and adds a wrapper to prepare for the next step.
    
    ```markup
    <div id="navbarContactWrapper" class="row mx-0">
      <nav id="navbarFooter" class="col-12 col-md-6 pt-5">
        <div id="socialMediaWrapper" class="col-12 col-md-4 text-center mx-auto mb-4">
          <h2 class="nav-heading">
              Follow Us
          </h2>
            <div id="socialMediaLinks">
              <ul class="nav flex-row mx-auto">
                  <li class="mx-2">
                      <div id="facebook"><a class="text-white"
                      href="http://www.facebook.com/pages/Liferay/45119213107" 
                      target="_blank"><span class="hide">Facebook</span>
                      <@clay["icon"] symbol="social-facebook" />
                      </a></div>
                  </li>
                  <li class="mx-2">
                      <div id="twitter"><a class="text-white" 
                      href="http://www.twitter.com/liferay" 
                      target="_blank"><span class="hide">Twitter</span>
                      <@clay["icon"] symbol="twitter" />
                      </a></div>
                  </li>
                  <li class="mx-2">
                      <div id="linked-in"><a class="text-white"
                      href="http://www.linkedin.com/company/83609" 
                      target="_blank"><span class="hide">LinkedIn</span>
                      <@clay["icon"] symbol="social-linkedin" />
                      </a></div>
                  </li>
                  <li class="mx-2">
                      <div id="youtube"><a class="text-white"
                      href="http://www.youtube.com/user/liferayinc" 
                      target="_blank"><span class="hide">YouTube</span>
                      <@clay["icon"] symbol="video" />
                      </a></div>
                  </li>
              </ul>
            </div>
        </div>
        <div class="text-center mx-auto">
          <div class="nav text-uppercase" role="menubar">
            <#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />
    
            <@liferay.navigation_menu
              default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
              instance_id="footer_navigation_menu"
            />
          </div>
        </div>
      </nav>
    </div>
    ```

5.  Add this snippet below the closing `</nav>` tag to add the Lunar Resort's 
    contact information. Also, copy the `lunar-resort-logo-vertical.png` asset 
    from the [`lunar-resort-build/assets/images/`](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/lunar-resort-theme/lunar-resort-build/assets/images) 
    folder to the `/src/images/` folder so you can use it in the Footer:

    ```markup
    <div class="contact-info-container text-center pt-5 pb-2 col-12 col-md-4 mx-auto mb-4">
      <img alt="lunar-resort-logo" height="90" class="mb-2" src="${images_folder}/lunar-resort-logo-vertical.png" />
      <div id="contactTextWrapper" class="row mx-0">
        <p class="col-12 col-md-6">
          123 Mare Nectaris Lane<br>
          Mare Nectaris, Moon Colony 10010<br>
        </p>
        <p class="col-12 col-md-6">
          Tel: 4-919-843-6666<br>
          Fax: 4-919-843-6667<br>
          <a href="mailto:info@lunarresort.com">info@thelunarresort.com</a>
        </p>
      </div>
    </div>
    ```
  
6.  The Administrator doesn't want to display the Footer on every page, so she 
    would like the option to hide it. To do that, create a [theme setting](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings) 
    to optionally show the Footer. Open your theme's 
    `/src/WEB-INF/liferay-look-and-feel.xml` file and add this snippet just 
    below the `<template-extension>ftl</template-extension>` entry. This renders 
    a togglable *Show Footer* option in the *Look and Feel* section for the 
    theme's configuration.

    ```xml
    <settings>
      <setting configurable="true" key="show-footer" type="checkbox" value="true" />
    </settings>
    ```

7.  Now you must define a FreeMarker variable to store the value of the 
    `show-footer` theme setting so you can check for it in `portal_normal.ftl`. 
    Open `init_custom.ftl` and add the variable declaration below to set the 
    `show_footer` variable to the value (true or false) of the `show-footer` 
    theme setting:
    
    ```markup
    <#assign
      show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))
    />
    ```

8.  Open `portal_normal.ftl` and replace the Footer markup with the code snippet 
    below to include the Footer template when the `show-footer` theme setting is 
    `true`:
    
    ```markup
    <#if show_footer>
  		<#include "${full_templates_path}/footer.ftl" />
  	</#if>
    ```
    
9.  Open `_custom.scss` and add this snippet above the `&.has-control-menu` 
    styling to style the Footer:

    ```scss
    #footer {
      background-color: $lunar-resort-blue;
      color: $white;
      ul {
        margin-left: auto;
        margin-right: auto;

        &.navbar-nav {
          width: 410px;
          .nav-item.hover:after {
            width: auto;
          }
          
          a {
            color: $white;
            @include media-breakpoint-down(sm) {
              padding-left: 6px;
              padding-right: 6px;
            }
          }
        }
      }
      
      #socialMediaWrapper ul {
        width: 192px;
        
        li a {
          font-size: 2rem;
        }
      }
      
      p.powered-by a, .contact-info-container a {
          color: $lunar-resort-link-teal;
      }

    }
    ```

10.  The majority of the Lunar Resort's content is provided with [Fragments](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments). 
     Since Fragments are out of the scope of this tutorial, you'll upload the 
     completed fragments. Open the Control Menu and navigate to *Site Builder* 
     &rarr; *Page Fragments*, and select the *Import* option from the New 
     dropdown menu. Import the `collections-lunar-resort.zip` asset from the 
     [`lunar-resort-build/assets/`](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/lunar-resort-theme/lunar-resort-build/assets) folder.
    
11.  Re-deploy the updated theme with the command below:

     ```bash
     gulp deploy
     ```

The updated Footer and navigation should look like the figure below:
    
![Figure 1: The updated Footer provides everything visitors need to follow and contact the Lunar Resort.](../../images/theme-tutorial-updated-footer.png)

In the next section you'll learn how to create a color scheme for the Lunar 
Resort. 
