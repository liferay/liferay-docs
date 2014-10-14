# Settings [](id=settings)

You can define settings to make your theme configurable. Create a file named
`liferay-look-and-feel.xml` in the `WEB-INF` directory, with the following
content:

    <?xml version="1.0"?>
    <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 6.1.0//EN"
    "http://www.liferay.com/dtd/liferay-look-and-feel_6_1_0.dtd">

    <look-and-feel>
        <compatibility>
            <version>6.1.2+</version>
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

Let's say you want to be able to choose from two different page headers (perhaps
one includes more details, while the other is smaller). Instead of creating two
themes that are identical except for some changes in the header, you can create
one and define a setting that lets you choose which header is displayed. 

Make sure you have a `docroot/_diffs/templates` folder created and copy the
`docroot/templates/portal_normal.vm` file into that directory. It's a good rule
of thumb to modify files for your new theme in the `_diffs` folder. Now, open
your `_diffs/templates/portal_normal.vm` template and insert the following:

    #if ($theme.getSetting("header-type") == "detailed")
        #parse ("$full_templates_path/header_detailed.vm")
    #else
        #parse ("$full_templates_path/header_brief.vm")
    #end

If you're following along with this example, you'll need to create the
`header_detailed.vm` and `header_brief.vm` files and place them in the
`_diffs/templates` folder. For this simple tutorial, you can keep these VM
templates blank.

Then, add two different entries in the `liferay-look-and-feel.xml` file
that refer to the same theme, but have different values for the header-type
setting:

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
portal. Use *configurable* settings to let users turn certain theme features on
or off or to allow users to provide input to a theme setting. 

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

+$$$

**Note:** Let's look more closely at two theme setting variables appearing in
the above logic. The `display-slogan-footer` variable holds a boolean value
indicating whether to display the version of the footer that contains your
slogan. The `slogan` variable holds your slogan text.

$$$

2.  Declare the two theme setting variables in your `liferay-look-and-feel.xml`,
    located in your theme's `WEB-INF` folder:

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

+$$$

**Warning:** Make sure you have an up-to-date DTD version specified for your
`liferay-look-and-feel.xml` file. For example,
`http://www.liferay.com/dtd/liferay-look-and-feel_6_1_0.dtd`. When referencing
older DTD files (e.g., 6.0.0), the slogan settings are unavailable.
 
$$$

The portal administrator can enter a slogan and activate it for the portal via
the *Look and Feel* section of the *Manage Site Pages* panel (see the *Creating
sites and managing pages* section of [Using Liferay
Portal](/discover/portal/-/knowledge_base/6-1/creating-sites-and-managing-pages)). 

 ![Figure 5.5: Setting the footer display slogan in the *Look and Feel* of the site's page settings.](../../images/themes-custom-configurable-setting.png)

When the portal administrator saves the settings, your site's pages show the new
footer, including the slogan. 

 ![Figure 5.6: The slogan displayed in the page footer.](../../images/themes-custom-configurable-setting-displayed.png)

+$$$

**Note:** Use a language properties hook to display configurable theme settings
properly, like the slogan text area and footer checkbox from the previous
example. For details, see the
[Overriding a *Language.properties* File](/develop/tutorials/-/knowledge_base/6-1/overriding-a-language-properties-file)
section found in the *Hooks* chapter of this guide.

$$$

Next, let's customize your theme's color scheme. 
