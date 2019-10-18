---
header-id: using-font-awesome-and-glyph-icons-in-your-theme
---

# Using Font Awesome and Glyph Icons in Your Theme

[TOC levels=1-4]

By default, [Font Awesome v3.2.1](https://fontawesome.com/v3.2.1/) 
and [Bootstrap 3 Glyphicons](https://getbootstrap.com/docs/3.3/components/) 
are enabled globally in @product@ via a system setting. This means that you can 
use them in your themes to create social media links, for example. A Site 
Administrator can disable this to improve performance, if they choose. 

## Disabling Enabling Global Font Awesome and Glyphicons in Portal

Since Liferay DXP Fix Pack 2 and Liferay Portal 7.2 CE GA2, Font Awesome is 
available globally as a system setting, which is enabled by default. You can 
disable this setting to improve performance. To update the setting, follow these 
steps:

1.  Open the Control Menu and navigate to *Control Panel* &rarr; 
    *Configuration* &rarr; *System Settings* and select *Third Party* under the 
    *PLATFORM* heading. 
    
2.  Select *Font Awesome* under the *System Scope* and check/uncheck the 
    *Enable Font Awesome* checkbox to enable/disable Font Awesome icons and 
    Glyphicons across the site.
    
3.  Click *Save* to save the configuration.

## Including Font Awesome and Glyphicons in Your Theme

As a safeguard, you should include Font Awesome and Glyphicons with your theme 
if you want to use them. This ensures that your icons won't break if the global 
system setting is disabled. If you created the theme with the Liferay Theme 
Generator and answered yes (y) to the Font Awesome prompt, the Font Awesome 
dependency is added which includes Font Awesome and Glyphicons for you. If you 
didn't include Font Awesome and Glyphicons when you initially created the theme, 
follow these steps to include them in your theme now:

1.  Use the Font Awesome v3.2.1 or Bootstrap 3 Glyphicons in your theme's 
    template. The example below uses Font Awesome icons:

    ```html    
    <div id="social-media-links">
      <ul class="nav flex-row mx-auto">
          <li class="mx-2">
              <div id="facebook">
                <a class="icon-facebook icon-3x text-white"
                href="http://www.facebook.com/pages/Liferay/45119213107" 
                target="_blank"><span class="hide">Facebook</span>
                </a>
              </div>
          </li>
          <li class="mx-2">
              <div id="twitter">
                <a class="icon-twitter icon-3x text-white" 
                href="http://www.twitter.com/liferay" 
                target="_blank"><span class="hide">Twitter</span>
                </a>
              </div>
          </li>
          <li class="mx-2">
              <div id="linked-in">
                <a class="icon-linkedin icon-3x text-white"
                href="http://www.linkedin.com/company/83609" 
                target="_blank"><span class="hide">LinkedIn</span>
                </a>
              </div>
          </li>
          <li class="mx-2">
              <div id="youtube">
                <a class="icon-youtube icon-3x text-white" 
                href="http://www.youtube.com/user/liferayinc" 
                target="_blank"><span class="hide">YouTube</span>
                </a>
              </div>
          </li>
          <li class="mx-2">
              <div id="google-plus">
                <a class="icon-google-plus icon-3x text-white"
                href="https://plus.google.com/+liferay/posts" 
                target="_blank"><span class="hide">Google</span>
                </a>
              </div>
          </li>
      </ul>
    </div>
    ```

2.  Open the theme's `package.json` and include the `liferay-font-awesome` 
    dev dependency:

    ```json
    "liferay-font-awesome": "3.4.0"
    ```

3.  Run `gulp deploy` from the theme's root folder to build and deploy the 
    theme's files. This adds a `/css/font/` folder to the theme's `build` folder 
    that contains the Font Awesome and Glyphicon fonts. 
    
## Related Topics

- [Installing the Theme Generator and Creating a Theme](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme)
- [Upgrading Themes](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-theme-to-7-2)
