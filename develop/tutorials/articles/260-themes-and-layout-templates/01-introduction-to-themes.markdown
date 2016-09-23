# Introduction to Themes [](id=introduction-to-themes)

A Liferay Theme is the overall look and feel for a site. Themes are a 
combination of CSS, JavaScript, HTML, and FreeMarker templates. Although the
default themes are nice, you may wish to create your own custom look and feel
for your site.

Liferay has its own set of base themes, called [styled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-theme/frontend-theme-styled) 
and [unstyled](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-theme/frontend-theme-unstyled), 
that create the default look and feel that you're first greeted with when you 
start the server. The *styled* theme inherits from the *unstyled* theme, and 
simply adds some additional styling on top. These same base themes are used to 
create a custom theme. See the [User Profile Theme](https://github.com/liferay/liferay-portal/tree/1ec7ef30e409de8b53dc8342e8ba7e7540bceef8/modules/apps/foundation/frontend-theme/frontend-theme-user-profile)
for example, which uses the *styled* theme as its base. Using a base theme as 
your foundation, you can then make your customizations to the theme files. To
modify the theme, simply mirror the directory structure of the files
you wish to change and copy them into the `src` directory of your theme. Build
the theme to apply the changes.

Liferay provides an easy-to-use tool called the [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
that helps automate the theme development process, but you can use other tools 
and environments, such as `ant`, to create themes.

Themes created with the Themes Generator give you access to [theme gulp tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks) 
that offer basic functions, such as `build` and `deploy`, as well as 
more complex interactions, such as auto deploying when a change is made and 
setting the base theme.

What if you only wish to make a minor change to the overall look and feel? Let's
say you just want to change a menu animation. Instead of creating an entire
theme for this single modification, you can create a Themelet. Themelets are 
modular, customizable, reusable, shareable, pieces of code, that extend a theme. 
They are very useful for code that you may use over and over. Instead of 
rewriting the code each time, you can simply use the same themelet in each theme.

Once your theme is developed it is packaged as a WAR(Web application ARchive)
file and can be deployed to the server, either manually or using build tools.

Once your theme is deployed, apply it to your pages through the [Look and Feel
menu](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/creating-and-managing-pages#customizing-the-look-and-feel-of-site-pages).

The only limitation is your imagination.
