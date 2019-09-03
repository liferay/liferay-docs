# Defining the Lunar Resort's Footer and Footer Navigation

You've configured the Header and its navigation. Now you need to configure the 
Footer. The Footer will be pretty standard and we'll include navigation that 
you'll provide by embedding a navigation portlet. 

Follow these steps;

1.  To keep the Portal Normal template uncluttered, you'll create a separate 
    template to hold the Footer's markup. Create a new file called `footer.ftl` 
    in the theme's `src/templates/` folder.
    
2.  Remove the Footer markup (shown below) from `portal_normal.ftl` and copy it 
    into `footer.ftl`:
    
    ```markup
    <footer id="footer" role="contentinfo">
  		<p class="powered-by">
  			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
  		</p>
  	</footer>
    ```
    
3.  The Administrator just sent an email. She doesn't want to display the Footer 
    on every page, so she would like the option to hide it. To do that, you will 
    create a [theme setting]() to optionally show the Footer. Open your theme's 
    `/src/WEB-INF/liferay-look-and-feel.xml` file and add this snippet just 
    below the `<template-extension>ftl</template-extension>` entry. This renders 
    a togglable *Show Footer* option in the *Look and Feel* section for the 
    theme's configuration.

    ```xml
    <settings>
      <setting configurable="true" key="show-footer" type="checkbox" value="true" />
    </settings>
    ```

4.  Now you need to define a FreeMarker variable to store the value of the 
    `show-footer` theme setting so you can check for it in `portal_normal.ftl`. 
    Open `init_custom.ftl` and add the variable declaration below to set the 
    `show_footer` variable to the value (true or false) of the `show-footer` 
    theme setting:
    
    ```markup
    <#assign
      show_footer = getterUtil.getBoolean(themeDisplay.getThemeSetting("show-footer"))
    />
    ```

5.  Open `portal_normal.ftl` and add this snippet just above the closing 
    `</div>` for the `#wrapper` `<div>` to only render the Footer if the 
        `show-footer` setting is true:
    
    ```markup
    <#if show_footer>
  		<#include "${full_templates_path}/footer.ftl" />
  	</#if>
    ```
    
6.  Open `footer.ftl` and add this `@liferay.navigation_menu` macro snippet 
    above the `powered-by` paragraph to embed the navigation portlet. This 
    configuration stores the portlet preferences in a `preferencesMap` variable. 
    The `displayDepth` of `1` specifies that the portlet must only render the 
    top-level parent navigation and `portletSetupPortletDecoratorId` sets the 
    portlet decorator to `barebone` so no wrapper is displayed for the 
    navigation portlet in the Footer.

    ```markup
    <nav id="navbar-footer">
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

7.  Now you need some social media links for visitors so they can keep tabs on 
    the latest and greatest news from the Moon. Replace the snippet you just 
    added with the one below. This uses [FontAwesome icons](https://fontawesome.com/v3.2.1/icons/) 
    (v3.2.1 included with @product@). It also adds a wrapper to prepare for the 
    next step.
    
    ```markup
    <div id="navbar-contact-wrapper" class="row mx-0">
      <nav id="navbar-footer" class="col-12 col-md-6 pt-5">
        <div id="social-media-wrapper" class="col-12 col-md-4 text-center mx-auto mb-4">
          <h2 class="nav-heading">
              Follow Us
          </h2>
          <div id="social-media-links">
            <ul class="nav flex-row mx-auto">
                <li class="mx-2">
                    <div id="facebook"><a class="icon-facebook icon-3x text-white"
                    href="http://www.facebook.com/pages/Liferay/45119213107" 
                    target="_blank"><span class="hide">Facebook</span></a></div>
                </li>
                <li class="mx-2">
                    <div id="twitter"><a class="icon-twitter icon-3x text-white" 
                    href="http://www.twitter.com/liferay" 
                    target="_blank"><span class="hide">Twitter</span></a></div>
                </li>
                <li class="mx-2">
                    <div id="linked-in"><a class="icon-linkedin icon-3x text-white"
                    href="http://www.linkedin.com/company/83609" 
                    target="_blank"><span class="hide">LinkedIn</span></a></div>
                </li>
                <li class="mx-2">
                    <div id="youtube"><a class="icon-youtube icon-3x text-white"
                    href="http://www.youtube.com/user/liferayinc" 
                    target="_blank"><span class="hide">YouTube</span></a></div>
                </li>
                <li class="mx-2">
                    <div id="google-plus">
                    <a class="icon-google-plus icon-3x text-white"
                    href="https://plus.google.com/+liferay/posts" 
                    target="_blank"><span class="hide">Google</span></a></div>
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

7.  Add this snippet below the closing `</nav>` tag to add the Lunar Resort's 
    contact information:

    ```markup
    <div class="contact-info-container text-center pt-5 pb-2 col-12 col-md-4 mx-auto mb-4">
      <img alt="lunar-resort-logo" height="90" class="mb-2" src="${images_folder}/lunar-resort-logo-vertical.png" />
      <div id="contact-text-wrapper" class="row mx-0">
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
    
8.  Open `_custom.scss` and add this snippet above the `&.has-control-menu` 
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
      
      #social-media-wrapper ul {
        width: 248px;
      }
      
      p.powered-by a, .contact-info-container a {
          color: $lunar-resort-link-teal;
      }

    }
    ```