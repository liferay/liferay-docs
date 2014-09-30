# AlloyUI 2.0 TagLib and Bootstrap Migration [](id=alloyui-2-0-taglib-and-bootstrap-migration)

Liferay 6.2 uses Twitter&reg; Bootstrap-based theming for a slick, vibrant look
and feel with instant access to the Twitter&reg; Bootstrap (Bootstrap) theme
library. But there are a number of changes that needed to be made to AlloyUI in
order to accommodate and properly use Bootstrap. In this section, we'll explain
the reasoning behind the changes to AlloyUI and we'll explain how to migrate
plugins to use AlloyUI 2.0 and Bootstrap. 

Here is an outline of the types of changes you'll need to understand and handle
in migrating your plugins to Liferay 6.2:

- Removal of the "aui-" prefixes from all classes
- Module deprecations
- CSS classes replaced with Bootstrap equivalents
- Component output and markup changes
- Icon removals, in favor of using Bootstrap icons

<!-- Add Bullet - Only Use AlloyUI Classes in CSS via Their Modules Jim -->

The good news is that Liferay provides a tool for making these changes. But
before we show you that tool, we'll explain the impact of each of these AlloyUI
changes with respect to Liferay 6.1 plugins. First, let's look at the removal of
the "aui-" class name prefix. 

## Removal of the "aui-" Prefix from All Classes [](id=removal-of-the-aui--prefix-from-all-classes)

The "aui-" class prefix was hindering developers from copying and pasting
examples from Bootstrap's site into their Liferay plugin code. So we've removed
the prefix from all of AlloyUI's CSS and JavaScript classes. You'll need to
update any references to the classes that have been removed. For example, you
should remove the "aui-" prefix from the class reference
`.aui-ace-autocomplete`, converting the reference to `.ace-autocomplete`. There
are plenty more class references like this one that you'll need to update. 

There are a number of HTML tags that AlloyUI 1.5 styled by defining custom CSS
classes. For example, AlloyUI previously styled the HTML `<fieldset>` tag in a
class named `.aui-fieldset`. But since Bootstrap provides styling for these tags,
we now leverage the styling by wrapping the Bootstrap code (see
[aui.css](https://github.com/liferay/liferay-portal/blob/6.2.0-ga1/portal-web/docroot/html/themes/_styled/css/aui.css)).
For migrating such classes as `.aui-fieldset` to AlloyUI 2.0, simply remove
the "aui-" prefix but append the `.aui` parent class name.

For example, you'd replace this ...

    .aui-fieldset {
        // Styling
    }

... with this ...

    .aui .fieldset {
        // Styling
    }

You can check Bootstrap's
[\_forms.scss](https://github.com/liferay/alloy-bootstrap/blob/v3/lib/_forms.scss)
file for the HTML tags that Bootstrap styles. 

Next, let's consider the modules that have been deprecated in AlloyUI 2.0. 

## AlloyUI Module Deprecations [](id=alloyui-module-deprecations)

Because extensive changes were needed for a number of AlloyUI modules, many of
the original modules were deprecated. In some cases the original modules were
deprecated with no replacement; in other cases we used the original name for the
new module implementation and have simply renamed the old module by adding a
"-deprecated" suffix to it. AlloyUI 2.0's module API is documented at
[http://alloyui.com/api/](http://alloyui.com/api/), but we've listed the
deprecated modules here: 

	aui-autocomplete-deprecated
	aui-autosize-deprecated
	aui-button-item-deprecated
	aui-chart-deprecated
	aui-color-picker-base-deprecated
	aui-color-picker-deprecated
	aui-color-picker-grid-plugin-deprecated
	aui-color-util-deprecated
	aui-data-set-deprecated
	aui-datasource-control-base-deprecated
	aui-datasource-control-deprecated
	aui-datepicker-base-deprecated
	aui-datepicker-deprecated
	aui-datepicker-select-deprecated
	aui-delayed-task-deprecated
	aui-dialog-iframe-deprecated
	aui-editable-deprecated
	aui-form-base-deprecated
	aui-form-combobox-deprecated
	aui-form-deprecated
	aui-form-field-deprecated
	aui-form-select-deprecated
	aui-form-textarea-deprecated
	aui-form-textfield-deprecated
	aui-input-text-control-deprecated
	aui-io-deprecated
	aui-io-plugin-deprecated
	aui-io-request-deprecated
	aui-live-search-deprecated
	aui-loading-mask-deprecated
	aui-overlay-base-deprecated
	aui-overlay-context-deprecated
	aui-overlay-context-panel-deprecated
	aui-overlay-deprecated
	aui-overlay-manager-deprecated
	aui-overlay-mask-deprecated
	aui-panel-deprecated
	aui-resize-base-deprecated
	aui-resize-constrain-deprecated
	aui-resize-deprecated
	aui-scroller-deprecated
	aui-simple-anim-deprecated
	aui-skin-deprecated
	aui-state-interaction-deprecated
	aui-swf-deprecated
	aui-template-deprecated
	aui-textboxlist-deprecated
	aui-tooltip-deprecated
	aui-tpl-snippets-base-deprecated
	aui-tpl-snippets-checkbox-deprecated
	aui-tpl-snippets-deprecated
	aui-tpl-snippets-input-deprecated
	aui-tpl-snippets-select-deprecated
	aui-tpl-snippets-textarea-deprecate

Note, some of these modules have new implementations with the same name,
excluding the suffix "-deprecated". Liferay's AlloyUI Upgrade Tool tacks the
"-deprecated" suffix onto module references it finds. It's up to you to migrate
to the new AlloyUI 2.0 modules. 

Next, let's consider the CSS classes that have been replaced by Bootstrap
equivalent components. 

<!-- Section: Only Use AlloyUI Classes in CSS via Their Modules

In CSS, a plugin should only use AlloyUI classes via their modules. This ensures
that the AlloyUI classes are available on the site pages. 

We need to explain this change. Maybe use a change to
portal-web/docroot/html/css/portal/preview.css as was done for
https://issues.liferay.com/browse/LPS-34974 ? Jim -->


## CSS Classes Replaced with Bootstrap Equivalents [](id=css-classes-replaced-with-bootstrap-equivalents)

Many of the CSS classes used in AlloyUI 1.5 were replaced with Bootstrap classes
or were removed because they didn't blend well with Bootstrap. You may find this
with CSS classes in your plugins. Consider replacing your classes with
Bootstrap's CSS classes. See
[http://liferay.github.io/alloy-bootstrap/base-css.html](http://liferay.github.io/alloy-bootstrap/base-css.html) for more information on these CSS classes. 

Next, let's consider the component output and markup changes in AlloyUI 2.0. 

## Component Output and Markup Changes [](id=component-output-and-markup-changes)

AlloyUI 2.0 introduces appealing new changes in its output and some practical
changes to its markup. These changes help facilitate building UIs with a
consistent look and feel, and they help improve UI performance. You can try many
of these component changes via the pages of examples and tutorials found on
[http://alloyui.com/](http://alloyui.com/). You'll have to take a look at the
AlloyUI 2.0 [API documentation](http://alloyui.com/api/) to understand a number
of the markup changes--but here are some common changes: 

-   Buttons work a little differently in AlloyUI 2.0. By default, they now
    submit the form. If you don't want that default behavior, you should prevent 
    it by using a DOM event call like this: `event.domEvent.preventDefault();`.
-   In `A.Modal`, you now use `close-panel` instead of the old `aui-btn-cancel`
    tablib. 
-   To delegate selectors for buttons, simply use `.selector-button` instead of
    `.selector-button input`.

There are plenty more changes, but at least these are a few to get you started.
And remember that the Liferay AlloyUI Upgrade Tool--that we'll introduce
shortly--will help you out as well. Next, let's consider the changes in the
icons available. 

## Icon Removals, in Favor of Using Bootstrap Icons [](id=icon-removals-in-favor-of-using-bootstrap-icons)

We replaced many icons with those provided by Bootstrap. We also added icons
from the Font Awesome project. These icons look great and provide a consistent
look and feel throughout Liferay and our plugins. You'll need to update your
plugin's references for icons that have been removed. In cases where you use the
`liferay:icon` taglib, you simply need to change the value of its `image`
attribute to that of a different icon. Consider using the new icons available in
Bootstrap, such as their [icons from
Glyphicons](http://liferay.github.io/alloy-bootstrap/base-css.html#icons). 

We've given you the "dime" tour of the types of changes you'll need to
accommodate in the plugins you're migrating from Liferay 6.1 to 6.2, but to
really jump-start your migration process, we'll show you Liferay's AlloyUI
Upgrade Tool. So, put on your work gloves and get ready to power through
migrating your plugins! 

## Upgrading Plugins with the Liferay AlloyUI Upgrade Tool [](id=upgrading-plugins-with-the-liferay-alloyui-upgrade-)

To access the liferay-aui-upgrade-tool project and install it locally, you'll
need an account on GitHub and the Git tool on your machine. Visit
[https://github.com/](https://github.com/) for instructions on setting up the
account and see [http://git-scm.com/](http://git-scm.com/) for instructions on
installing Git. 

Here are some simple steps for forking the liferay-aui-upgrade-tool project on
GitHub and installing the project locally: 

1.  Go to the AlloyUI project repository at
    [https://github.com/liferay/liferay-aui-upgrade-tool](https://github.com/liferay/liferay-aui-upgrade-tool).

2.  Click *Fork* to copy Liferay's liferay-aui-upgrade-tool repository to your
    account on GitHub. 

3.  In your terminal or in GitBash, navigate to the location where you want to
    put the liferay-aui-upgrade-tool project. Then download a clone of the
    repository by executing the following  command, replacing `[username]` with
    your GitHub user name: 

        git clone git@github.com:[username]/liferay-aui-upgrade-tool

4.  Navigate into your new liferay-aui-upgrade-tool repository directory and
    associate a remote branch to Liferay's liferay-aui-upgrade-tool repository
    so you'll be able to fetch its latest changes from time to time:  

        cd liferay-aui-upgrade-tool
        git remote add upstream git@github.com:liferay/liferay-aui-upgrade-tool

You now have all of the liferay-aui-upgrade-tool project's source code. The
project's tool you use to upgrade plugins to AlloyUI 2.0 is called *laut*, which
stands for Liferay AUI Upgrade Tool. You can build the upgrade tool using
[Node.js](http://nodejs.org), which is a platform for building applications. You
can download it from [http://nodejs.org/](http://nodejs.org/). Linux, OS X, or
UNIX users can download its source in a `.tar.gz` file, unzip it, un-tar it, and
build it per the instructions in its `README.md` file. Windows users can
download the `.msi` installer file and run it. 

+$$$

**Warning:** On Windows, only install to
 locations that have UNIX-friendly paths. Paths like `C:\Program Files (x86)`
 that contain space characters and parentheses can prevent software from working
 properly. 

$$$

To build the upgrade tool with NodeJS, execute the following command (exclude
`[sudo]` on Windows): 

    [sudo] npm install -g laut

To get the usage summary of the upgrade tool, run it with the `--help` option. 

    laut --help

By default, the upgrade tool expects to convert files with extension `js`,
`jsp`, `jspf`, and `css`. You can specify a list of file extensions as arguments
to the tool's `-e` option. With the `-f` option you can specify individual files
or directories to search through and convert. It's common for users to simply
specify a single directory for the tool to search and convert all of the files
with the default extensions. 

    laut -f some-directory

## Example: Upgrading the Microblogs Portlet to AlloyUI 2.0 [](id=example-upgrading-the-microblogs-portlet-to-alloyui)

Let's use the upgrade tool to upgrade Liferay's CE 6.1 Microblogs portlet from
using AlloyUI version 1.5 to using AlloyUI 2.0. Here's how to run it on the
Microblogs portlet in its liferay-plugins repository:

    laut -f /home/joe.bloggs/liferay-plugins/portlets/microblogs-portlet

Let's take a look at the changes the upgrade tool made to the portlet's JSPs. 

In the `view.jsp`, the upgrade tool renamed the `aui-io` module to
`aui-io-deprecated`. The tool replaces module references, even if a 2.0 module
exists with the same name, for a couple of different reasons. First, assuming
that you look at a diff of the modifications the tool makes, you'll notice that
the module has been deprecated. Knowing that, you can investigate whether there
is a new AlloyUI 2.0 module that you should start using instead. You should
investigate the API for the 2.0 module to find out how it works and to determine
how you might use it. Second, by using the deprecated module, you're assured
that your code will not run into interpretation errors; it may even exhibit the
same behavior as before. You must investigate if the deprecated module's
behavior has changed. It's up to you as to when and how to start using a 2.0
module. 

In 2.0, many of the "aui-" prefixes were dropped and in some cases modules were
completely renamed. For example, the upgrade tool modified the Microblogs
portlet's `edit_microblogs_entry.jsp`, replacing module reference
`aui-helper-hidden` with its new 2.0 module named `hide`.

Let's take a look at a different type of change done in the Microblogs portlet's
`edit_microblogs_entry.jsp`. Notice that the "aui-" prefix is deleted from all
AlloyUI class names, replacing `aui-button-holder`, `aui-button-disabled`, and
`aui-button-submit` class references with `button-holder`, `button-disabled` and
`button-submit`, respectively. Keep this type of change throughout your
portlet's files. 

+$$$

**Warning:** Make sure to add the `.aui`
 parent class reference in front of a classname if you're extending the
 styling of a class that Bootstrap already styles. See the previous section on
 *Removal of the aui- Prefix from All Classes* for details. 

$$$

Lastly, consider the changes done to the Microblogs portlet's `main.js` file.
The script now uses the `liferay-util-window` module in place of the old
`aui-dialog` module. The upgrade tool took things a step further by updating so
that the script properly uses the `liferay-util-window` module to get pop-up
windows for the portlet. Below are code snippets of what it like before and
after running the upgrade tool.

The Microblogs portlet's `main.js` code **before** upgrading:

    AUI().use(
        'aui-base',
        'aui-dialog',
        'aui-io-plugin',
        ...
			getPopup: function() {
				var instance = this;
		
				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							centered: true,
							constrain2view: true,
							cssClass: 'microblogs-portlet',
							modal: true,
							resizable: false,
							width: 475
						}
					).plug(
						A.Plugin.IO,
						{
							autoLoad: false
						}
					).render();
				}
		
				return instance._popup;
            },
        ...
    );

The Microblogs portlet's `main.js` code **after** upgrading:

    AUI().use(
        'aui-base',
        'liferay-util-window',
        'aui-io-plugin-deprecated',
        ...
            getPopup: function() {
                var instance = this;
            
                if (!instance._popup) {
                    instance._popup = Liferay.Util.Window.getWindow(
                    {
                        dialog: {
                            centered: true,
                            constrain2view: true,
                            cssClass: 'microblogs-portlet',
                            modal: true,
                            resizable: false,
                            width: 475
                        }
                    }
                    ).plug(
                        A.Plugin.IO,
                        {
                            autoLoad: false
                        }
                    ).render();
                }
            
                return instance._popup;
            },
        ...
    );

The Liferay AlloyUI Upgrade Tool gives you a great jump-start on migrating your
plugins to AlloyUI 2.0. Of course, you should review the upgrade changes and
test the changes before redeploying your plugin into a production environment.
And remember that the tool may not pick up all of the changes that need to be
made to your plugin. But you'll be happy that the tool does a good bit of the
monotonous conversion work for you. 

