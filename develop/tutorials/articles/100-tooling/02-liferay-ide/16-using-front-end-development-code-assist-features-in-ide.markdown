# Using Front-End Code Assist Features in @ide@ [](id=using-front-end-code-assist-features-in-ide)

Liferay @ide@ provides extended front-end development tools to assist in Liferay
development. You now have access to code inferencing and code completion
features for AlloyUI, JavaScript, CSS, and jQuery. 

This tutorial covers how to use the code assist features in @ide@ for

- [AlloyUI](#alloyui-code-assist-features)
- [JavaScript](#javascript-code-assist-features)
- [CSS](#css-code-assist-features)
- [jQuery](#jquery-code-assist-features)

Each language is covered in its own section, so you can navigate to the language
you're most interested in. There are many languages, including the four listed
above, that @ide@ provides code assist for. This is provided by @ide@'s
integration of [Tern](http://ternjs.net/). To access these features, you must be
working in a file those languages are expected for (e.g., JavaScript, JSP, HTML,
CSS, etc.).

You must have Tern features enabled in your project in order to use them. You
should also have the appropriate Tern modules enabled based on the language
you're writing in. For example, if you're writing in a jQuery file, you must
apply the Tern *jQuery* module to use code assist for that language. See the
[Enabling Code Assist Features in your Project](/develop/tutorials/-/knowledge_base/7-1/enabling-code-assist-features-in-your-project)
tutorial to learn how to enable Tern features for your projects. By default,
Liferay projects created using the Plugins SDK already have Tern features
enabled.

+$$$

**Note:** For those developing with the Plugins SDK, the taglib descriptions
that @ide@ makes available to users are dependent upon the Plugins SDK version.
@ide@ uses taglib descriptions from the current SDK's `util-taglib.jar` file, so
a more up-to-date Plugins SDK means more up-to-date taglib descriptions. 

$$$

You'll begin testing the AlloyUI code assist features first.

## AlloyUI Code Assist Features [](id=alloyui-code-assist-features)

There are several helpful code assist features that can improve your
productivity when writing code for AlloyUI. Before beginning, enable the Tern
modules required to use AlloyUI features: *AlloyUI*, *Browser*, *JSCS*,
*Liferay*, and *YUI Library*. The example below shows how to  access the AlloyUI
code assist features in the `main.js` of your project: 

1. Open your project's `main.js` file and type the following code: 

        AUI().

2. Press *Ctrl+Space* with your cursor to the right of `AUI().`. This brings up
   the code inference for the `AUI()` global object. Notice the AlloyUI
   framework's own API documentation is also displayed. Press *Enter* to use
   code completion. 

![Figure 1: This figure demonstrates code inference in a JS file.](../../../images/code-inference.png)

+$$$

**Note:** Code assist not only works for methods of an object, but also works
for AUI-specific Tern completions for objects. For instance, you could type `AU`
and press *Ctrl+Space* to see a list of objects to choose from.

$$$

By default, code inference is triggered by a keystroke combination; however, you
can enable auto activation in @ide@'s Preferences menu. Follow the steps below
to enable auto activation: 
 
1. Navigate to *Window* &rarr; *Preferences* &rarr; *JavaScript* &rarr; *Editor*
   &rarr; *Content Assist*. 

2. Check the *Enable auto activation* box and click *Apply and Close*.

    The figure below shows how to enable auto activation: 
 
    ![Figure 2: The *Enable auto activation* checkbox is listed below the *Auto-Activation* heading.](../../../images/enable-auto-activation.png)

Now, if you follow the previous example, code inference activates as soon as
you press the trigger key, which in this case is the *.* (period) key. 

In addition to general code inference for AlloyUI, you have access to code 
templates. AUI JavaScript templates are available in Eclipse's JavaScript editor
as well as in the HTML/JSP editor when working with `<script>` and
`<aui-script>` tags. Follow the steps below to use AUI code templates: 

1.  Type the following code in your `main.js`: 
 
        AUI

2.  Press *Ctrl+Space* to bring up the code inference for `AUI`, and you'll 
    see a list of all the available AlloyUI code templates, along with
    documentation. 

    ![Figure 3: @ide@ gives you access to AUI code templates in the JS and JSP editors.](../../../images/code-template.png)
 
3.  Select your template and hit *Enter* to paste its contents into your
    `main.js`. 

    +$$$

    **Note:** You can view all the AlloyUI code templates you have 
    installed by going to @ide@'s Preferences menu and selecting *JavaScript*
    &rarr; *Editor* &rarr; *Templates*. 

    $$$ 

In addition to code inference in your JS files, you can also use code inference 
in your JSP/HTML files using `<aui:script>` tags. 

Open one of your project's JSPs and add the AUI taglib directive if it's not
already in your JSP: 

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

You can also add the import from the Snippets menu (*Window* &rarr; *Show View*
&rarr; *Snippets*) under *Taglib imports* &rarr; *Liferay AUI Taglib Import
v6.0*. 

1. Add an `<aui:script>` tag inside your JSP and configure it to look like the
   following code: 

        <aui:script>
            aui
        </aui:script>

2. Press *Ctrl+Space* with your cursor placed to the right of `aui` to bring up
   code inference. 

There you go! Whether in a JavaScript file or inside a JSP, you now have access
to code assist features that improve your workflow. 

Next, you'll examine the JavaScript code assist features for @ide@.

## JavaScript Code Assist Features [](id=javascript-code-assist-features)

In addition to AlloyUI code assist features, you also have access to code
inference and completion using raw JavaScript. This code assist feature is
available in your project because the Tern module `Liferay` is enabled. This
plugin provides code completions for the static JavaScript object APIs available
to portlets when running in Liferay Portal. To learn more about enabling Tern
modules in Eclipse, refer to the
[Enabling Code Assist Features in Your Project](/develop/tutorials/-/knowledge_base/7-1/enabling-code-assist-features-in-your-project)
tutorial. 

The example below shows how you can use code assist features to easily access
functions in your portlet project. 

1.  Open the `main.js` of your portlet and add the following function: 

        function say(text){
            alert(text);
        }

2.  Add the following button to the `view.jsp` of your portlet: 

        <aui:button onClick=""/>

3.  Place your cursor within the quotation marks of the `onClick` attribute and
    press *Ctrl+Space*. The code inference dialog pops up with a list of
    possible JavaScript functions available for you to use. 

4.  Type *say* and you'll notice the list is narrowed down to your new
    `say(text)` function. 

    ![Figure 4: JavaScript code assist features give easy access to your functions.](../../../images/js-code-inference.png)

5.  Select the `say(text)` function, and you'll notice that it's accompanied by
    documentation that provides the parameter for the function, as well as the
    file path where the function is located. 

6.  Press *Enter* to use code completion and add the function to your button. 

As you can see, JavaScript development is a breeze using @ide@'s code assist
features. Now that you know how to use the AlloyUI and JavaScript code assist
features, you can learn how to use the CSS code assist features next. 

## CSS Code Assist Features [](id=css-code-assist-features)

@ide@ offers code inference and completion tools for CSS. In order to use these
tools, you'll need to install an additional plugin. 

+$$$

**Note:** The plugin described below is planned to be bundled with Liferay
@ide@ in the near future. Initial tests of the plugin revealed performance issues
in some cases, which is why it is not yet a part of Liferay @ide@. Problems were
not consistent, so you may have no issues installing the plugin, but we wanted
to give full disclosure about it. 

$$$

Follow the steps below to install the plugin in @ide@: 

1. Go to *Help* &rarr; *Install New Software...*. 

2. Paste the following link into the `Work with:` input field: 

        http://oss.opensagres.fr/eclipse-wtp-webresources/1.1.0/

3. Click *Add...* and check the box next to *WTP HTML - Web Resources*. 

4. Click *Next* and follow the installation instructions. 

Now that your plugin is installed, you'll need to enable the CSS features in
your project. Right-click your project and go to *Properties* &rarr; *Web
Resources* &rarr; *CSS*. Check both boxes to enable CSS features in your
project. 

You have successfully installed and enabled the new CSS features in your
project! 

Now that you have the CSS features enabled, you'll find out how to use them 
next. Follow the steps below to use the CSS code assist features to locate a CSS
class. Note that the process below can also be used to locate an ID. 

1.  Open your `main.css` file and add the following class to it: 

        .sample-class {
            background-color:green;
        }

2.  Inside your `view.jsp` add an `<aui:button/>` tag and configure it to
    match the following code: 

        <aui:button name="test" value="test" cssClass=""/>
 
3.  Within the quotations of the `cssClass` attribute, press *Ctrl+Space* to
    bring up the code inference for CSS. Begin typing *sample-class* to narrow
    down the classes to the one you're looking for. 

    ![Figure 5: CSS code inference improves your workflow when developing in CSS.](../../../images/css-code-inference.png)

    Notice, along with code inference, you can also view the styling you have
    for the class, as well as the file in which it is located. 
 
4.  Press *Enter* to use code completion and add the CSS class to the JSP. 

If you look at the code inference dialog for CSS classes, you'll also notice
that in addition to your own CSS classes, you also have access to Bootstrap CSS
classes found in Liferay Portal. 

+$$$

**Note:** You can go to the file that the class, ID, or function is located in
by hovering over top of it in your JSP and holding down the `Ctrl` (Windows) or
`command` (Mac) key, and clicking the hyperlink that appears. 

$$$

Lastly, you'll learn about the code assist features for jQuery. 

## jQuery Code Assist Features [](id=jquery-code-assist-features)

You can also use code assist with jQuery. To do this, you must enable the
`jQuery` Tern module. Follow the instructions in the
[Enabling Code Assist Features in Your Project](/develop/tutorials/-/knowledge_base/7-1/enabling-code-assist-features-in-your-project)
tutorial to learn how to enable Tern modules in your project. 

The jQuery Tern plugin gives type information for the jQuery framework. In the
example below, you'll test the jQuery code assist feature. 

1.  Open your project's `jquery.js` file. 

2.  In the file, type the following sample variable: 

        var form = 

3.  Press *Ctrl+Space* to bring up the code inference for the variable you're
    declaring, and you'll see a list of everything that is available. Also notice
    jQuery documentation is available for each method. Take a look at the figure
    below for an example of using code assit in jQuery. 

    ![Figure 6: Using the jQuery code assist features gives you the convenience of showing you what's available, and the documentation behind each option.](../../../images/jquery-js-1.png)

    Furthermore, for jQuery callback handlers, the type information for
    parameters is also made available. 

    ![Figure 7: jQuery code assist also displays type information for parameters.](../../../images/jquery-js-2.png)

Excellent! You now know how to use @ide@'s front-end development code assist 
features to improve your workflow. 

## Related Topics [](id=related-topics)

[Enabling Code Assist Features in your Project](/develop/tutorials/-/knowledge_base/7-1/enabling-code-assist-features-in-your-project) 

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)

[From Liferay 6 to @product-ver@](/develop/tutorials/-/knowledge_base/7-1/from-liferay-6-to-liferay-7)
