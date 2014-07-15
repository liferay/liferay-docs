# Making Themes Configurable with Settings [](id=making-themes-configurable-with-settings)

You can define settings to make your theme configurable. The process is easier 
than you may think. Follow the steps below to get started.

## Setting up the liferay-look-and-feel.xml

1. Create `liferay-look-and-feel.xml` in the `WEB-INF` directory (if necessary).

2. Add the following content(make sure to replace the theme and setting values 
with your own):

    <?xml version="1.0"?>
    <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 6.2.0//EN"
    "http://www.liferay.com/dtd/liferay-look-and-feel_6_2_0.dtd">

    <look-and-feel>
        <compatibility>
            <version>6.2.0+</version>
        </compatibility>
        <theme id="deep-blue" name="Deep Blue">
            <settings>
                <setting key="my-setting" value="my-value" />
            </settings>
        </theme>
    </look-and-feel>
    
To define additional settings, add more `<setting>` elements to the file.
Access the settings from the theme templates using the following code:

    $theme.getSetting("my-setting")

Take a look at a working example next. 

## Creating Page Header Settings

Say you want to be able to choose from two different page headers (perhaps
one includes more details, while the other is smaller). Instead of creating two
themes that are identical except for some changes in the header, you can create
one and define a setting that lets you choose which header is displayed. 
although the steps below create an example header setting, the same process can 
be applied to any setting. Follow the steps below to see how it's done:

1. Make sure you have a `docroot/_diffs/templates` folder created and copy the
   `docroot/templates/portal_normal.vm` file into that directory. It's a good 
   rule of thumb to modify files for your new theme in the `_diffs` folder. 
   
2. Now, open your `_diffs/templates/portal_normal.vm` template and insert the 
   following:

   ```
      #if ($theme.getSetting("header-type") == "detailed")
          #parse ("$full_templates_path/header_detailed.vm")
      #else
          #parse ("$full_templates_path/header_brief.vm")
      #end
    ```

    If you're following along with this example, you'll need to create the
    `header_detailed.vm` and `header_brief.vm` files and place them in the
    `_diffs/templates` folder. For this simple tutorial, you can keep these VM
    templates blank.

3. Add two different entries in the `liferay-look-and-feel.xml` file that refer 
   to the same theme, but have different values for the header-type setting:

      <theme id="deep-blue" name="Deep Blue">
          <settings>
              <setting key="header-type" value="detailed" />
          </settings>
      </theme>
      <theme id="deep-blue-mini" name="Deep Blue Mini">
          <settings>
              <setting key="header-type" value="brief" />
          </settings>
      </theme>

Alternatively, you can make your settings configurable from within Liferay
portal. Learn more about the process next.

## Creating Configurable Settings in Liferay Portal

Use *configurable* settings to let users turn certain theme features on or off 
or to allow users to provide input to a theme setting. 

As an example, you can create an option to display a slogan next to your
company's name in the footer of your site's pages:

1. Insert logic into your `portal_normal.vm` template to display a slogan along
   with your company's name (e.g. Nosester) in the footer of your site pages:

        <footer id="footer" role="contentinfo">
            <p>
                #if($theme.getSetting("display-slogan-footer") == true)
                    Nosester $theme.getSetting("slogan")
                #else
                    Nosester
                #end
            </p>
        </footer>

    ---

    ![note](../../images/tip-pen-paper.png) **Note:** take a closer look at two 
    theme setting variables appearing in the above logic. The 
    `display-slogan-footer` variable holds a boolean value indicating whether
    to display the version of the footer that contains your slogan. The
    `slogan` variable holds your slogan text.

    ---

2. Declare the two theme setting variables for your theme in your
   `liferay-look-and-feel.xml`, located in your theme's `WEB-INF` folder:

        <settings>
            <setting configurable="true" 
                     key="slogan" 
                     type="textarea" 
                     value="" 
            />
    
            <setting configurable="true" 
                     key="display-slogan-footer" 
                     type="checkbox" 
                     value="true"
            />
        </settings>

---

![tip](../../images/tip-pen-paper.png) **Warning:** Make sure you have an
up-to-date DTD version specified for your `liferay-look-and-feel.xml` file.
For example, `http://www.liferay.com/dtd/liferay-look-and-feel_6_2_0.dtd`. When
referencing older DTD files (e.g., 6.0.0), the slogan settings are unavailable.
 
---

The portal administrator can enter a slogan and activate it for the portal via
the *Look and Feel* section of the *Site Administration* &rarr; *Site Pages*
panel (see the *Creating and Managing Pages* section of [Using Liferay Portal](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-liferays-multi-site-capabili-liferay-portal-6-2-user-guide-02-en)).

![Figure 1: Setting the footer display slogan in the *Look and Feel* of the site's page settings.](../../images/themes-custom-configurable-setting.png)

When the portal administrator saves the settings, your site's pages show the new
footer, including the slogan. 

![Figure 2: The slogan displayed in the page footer.](../../images/themes-custom-configurable-setting-displayed.png)

---

![note](../../images/tip-pen-paper.png) **Note:** Use a language properties
hook to display configurable theme settings properly, like the slogan text area
and footer checkbox from the previous example. For details, see the 
 [Overriding a *Language.properties* File](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/override-a-language-properties-hook-liferay-portal-6-2-dev-guide-en)
section in the *Hooks* chapter of the DevGuide.

---

Congrats! Now you know how to create configurable settings for your theme!

## Next Steps
<!-- URL will probably need updated when added to the new devsite-->
 [Using Developer Mode with Themes](/tutorials/-/knowledge_base/using-developer-mode)

 [Specifying Color Schemes for Your Themes](/tutorials/-/knowledge_base/specifying-color-schemes-for-your-theme)
