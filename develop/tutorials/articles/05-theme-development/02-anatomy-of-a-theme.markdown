# Anatomy of a Theme [](id=anatomy-of-a-theme)

Custom themes are created by layering your customizations on top of one of
Liferay's built-in themes. 

The structure of a theme separates different types of resources into easily
accessible folders. Here's the full structure of our Deep Blue theme:

- `deep-blue-theme/`
    - `docroot/`
        - `WEB-INF/`
            - `liferay-plugin-package.properties`
        - `_diffs/` - subfolders not created by default.
            - `css/`
            - `images/`
            - `js/`
            - `templates/`
        - `css/`
            - `application.css`
            - `base.css`
            - `custom.css`
            - `dockbar.css`
            - `extras.css`
            - `forms.css`
            - `layout.css`
            - `main.css`
            - `navigation.css`
            - `portlet.css`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.vm`
            - `navigation.vm`
            - `portal_normal.vm`
            - `portal_pop_up.vm`
            - `portlet.vm`

The `_diffs` folder that's created inside the `docroot` directory of your theme
is important; this is where you place your theme's code. The `_diffs` folder
must mirror the parent theme's directory structure. Since you'll only customize
the parts of your theme that differ from the parent theme, place only the
folders and files you'll customize there. 

The other folders inside `docroot` were copied over from the parent theme in
your Liferay bundle when you deployed your theme. Use these files as the basis
for your modifications. For example, to customize the navigation, copy
`navigation.vm` from `deep-blue-theme/docroot/templates/navigation.vm` into the 
`deep-blue-theme/docroot/_diffs/templates` folder (you may have to create this
folder first). You can then open this file and customize it to your liking. 

For custom styles, create a folder named `css` inside your `_diffs` folder and
place a single file there called `custom.css`. This is where you'll define all
your new styles. Because `custom.css` is loaded last, styles defined here
override any styles in the parent theme.

It's a best practice to add your styles only to the `custom.css` file. This
keeps all of your changes in one place and makes future upgrades easier, because
you won't have to manually modify your templates to add support for new Liferay
features. 

Whenever you modify your theme in Developer Studio, redeploy it by
right-clicking your theme (located underneath your server), then selecting
*Redeploy* from the menu. 

 ![Figure 5.3: How to redeploy your theme plugin](../../images/05-themes-2.png)

Alternatively, redeploy your theme by opening a terminal, navigating to
`themes/deep-blue-theme` and entering the command

    ant deploy 

Wait a few seconds until the theme deploys, then refresh your browser to see
your changes. 

Would you rather see your changes immediately, rather than having to redeploy to
make your changes visible? Let's talk about Liferay Developer Mode to learn how. 
