# Setting Up the Theme's Directory Structure [](id=setting-up-the-themes-directory-structure)

+$$$

Note: This step assumes you've [downloaded and unzipped the resources](/documents/10184/581742/themes-learning-path-6.2.zip)
needed to set up the theme. This was mentioned in the introduction to this step.

$$$

Your theme's directory structure should reflect the one below when it is 
complete:

- `lunar-resort-theme/`
    - `docroot/`
        - `_diffs/` (subfolders not created by default)
            - `css/`
            - `images/`
            - `templates/`
        - `css/`
                - `aui`
                        - (many directories)
                - `_aui_custom.scss`
                - `_aui_variables.scss`
                - `_liferay_custom.scss`
            - `application.css`
            - `aui.css`
            - `base.css`
            - `custom.css`
            - `dockbar.css`
            - `extras.css`
            - `layout.css`
            - `main.css`
            - `navigation.css`
            - `portlet.css`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.ftl`
            - `navigation.ftl`
            - `portal_normal.ftl`
            - `portal_pop_up.ftl`
            - `portlet.ftl`
        - `WEB-INF/`
                - `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`

Themes are based on differences from the themes they are based on. When you
created the project, you selected the *_styled* theme. A copy of this theme's
code, therefore, exists in most of these folders. This ensures that you have
default styling for all elements of the page. As you write your theme, you'll
define only the differences between what you want and what the underlying theme
already provides. 

Your edits should be made to the files in the `_diffs` directory, which then 
transfer to the files in the parent directory. For this reason, the directory in 
the `_diffs` folder must mirror the parent directory. If the structures do not 
match, the changes you make will not be applied. To keep the base files
unaltered, you should only add the files you want to edit to the `_diffs`
directory.

As a best practice, all of your CSS styling should go in a `custom.css` file 
in the `_diffs/css/` directory. Because it is loaded last, all the styles placed 
in `custom.css` override styles in the parent base theme. By keeping all your 
styles in one file, maintenance becomes an easier process.

Now that you're familiar with the theme structure and development workflow, you 
can begin working on your theme.

1.  Right-click the `_diffs` folder in the Package Explorer and select
    *New* &rarr; *Folder*.

2.  With the `_diffs` folder selected, enter `css` for the *Folder name* and
    click *Finish*.

3.  Repeat steps 1 and 2 to create the remaining `images`, and `templates` 
    directories.

4.  Copy the `custom.css` file from the `docroot/css/` directory to your 
    `_diffs/css` directory. All the changes you make to the `custom.css` file in
    your `_diffs` directory get applied to the mirrored file.

5.  Copy the `_aui_variables.scss` to the `diffs/css` 
    directory. 

6.  Copy `init_custom.ftl`, `navigation.ftl`, and `portal_normal.ftl` from the
    `docroot/templates` directory to the `diffs/templates` directory.

You now have the starting point for your theme. There are a few additional
housekeeping items to take care of before you move on to the next section.

1.  Open `_aui_variables.scss` and add the following variables to the top:

        $bkColor: #FFF;
        $themeMainColor: #DF5946;

2.  Copy the *lunar-resort-logo.png* from the `/images` directory of the code
    folder into the `_diffs/images` directory of the theme.
 
3.  Finally, install the `guestbook-portlet.war` file from the last step of the
    [MVC](https://dev.liferay.com/develop/learning-paths/mvc) Learning Path by
    copying it into your Liferay bundle's `/deploy` directory.
 
If you haven't gone through the MVC Learning Path, you should check it out if
you are interested in developing applications for Liferay.

Now that the housekeeping is finished, you can examine the HTML for
`portal_normal.ftl` next.
