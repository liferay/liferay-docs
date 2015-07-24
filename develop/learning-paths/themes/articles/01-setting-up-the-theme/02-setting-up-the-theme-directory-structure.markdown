# Setting Up the Theme's Directory Structure

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

Your edits should be made to the files in the `_diffs` directory, which then 
transfer to the files in the parent directory. For this reason, the directory in 
the `_diffs` folder must mirror the parent directory. If the structures do not 
match, the changes you make will not be applied. Therefore to keep the base 
files unaltered, you should only add the files you want to edit to the `_diffs` 
directory.

As a best practice, all of your CSS styling should go in the `custom.css` file 
in the `_diffs/css/` directory. Because it is loaded last, all the styles placed 
in `custom.css` override styles in the parent base theme. By keeping all your 
styles in one file, maintenance becomes an easier process.

Now that you're familiar with the theme structure and development workflow you 
can finsh setting up your directory structure.

1. Right-click the `_diffs` folder in the Package Explorer and select
*File*&rarr;*New*&rarr;*Folder*.

2. With the `_diffs` folder selected, enter `css` for the *Folder name* and
click *Finish*.

3. Repeat steps 1 and 2 to create the remaining `images`, and `templates` 
   directories.

4. Copy the `custom.css` file from the `docroot/css/` directory to your 
`_diffs/css` directory. All of the changes you make to the `custom.css` file in
your `_diffs` directory will be applied to the mirrored file.

5. Copy over the `_aui_variables.scss` to the `diffs/css` 
directory, and `init_custom.ftl`, `navigation.ftl`, and `portal_normal.ftl` to 
the `diffs/templates` directory.

The directory structure is setup. There are a few additional house keeping items 
to take care of before you move on to the next section.

1. Open `_aui_variables.scss` and add the following variables to the top:

        $bkColor: #fff;
        $themeMainColor: #df5946;

2. Copy the *lunar-resort-logo.png* from the `/images` directory of the code
   folder into the `_diffs/images` directory of the theme.
   
3. Finally, copy the `guestbook-portlet.war` file from the `/code` directory of
   the learning path into your Liferay bundle's `/deploy` directory.
   
The Guestbook Portlet was created in the [MVC](https://dev.liferay.com/develop/learning-paths/mvc)
learning path. I encourage you to check it out if you are interested in
developing applications for Liferay.

Now that the house keeping is finished, you can take a closer look at the 
HTML for `portal_normal.ftl` next.