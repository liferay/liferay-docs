# Using Front-End Development Code Assist Features in IDE

Since IDE 2.2, front-end development has become a much smoother process. You now
have access to code inferencing and code completion features for AlloyUI,
JavaScript, and CSS.

This tutorial covers how to use the code assist features for AlloyUI,
JavaScript, and CSS in IDE. Each language is covered in its own section, so you
can navigate to the language you're most interested in. Continue reading to find
out how to use IDE's code assist features in your project.

## Using Code Assist Features

IDE's integration of [Tern](http://ternjs.net/) features give you access to code
assist in JavaScript, AlloyUI, and CSS. To access these features, you must be
working in a JavaScript, JSP, or CSS file.

+$$$

**Note:** You must have Tern features enabled in your project in order to use 
them. By default, Liferay portlet plugins already have Tern features enabled. 
Visit the [Enabling Code Assist Features in your Project](/develop/tutorials/-/knowledge_base/6-2/enabling-tern-code-assist-features-in-your-project)
tutorial to learn how to enable Tern features for non-Liferay specific projects.

$$$

You'll begin testing the AlloyUI code assist features next.

### AlloyUI Code Assist Features

There are several helpful code assist features that can improve your 
productivity when writing code for AlloyUI. The example below shows how to 
access the AlloyUI code assist features in the `main.js` of your project:

1. Open your project's `main.js` file and type the following code:

        AUI().

2. Press *Ctrl+Space* with your cursor to the right of `AUI().`. This brings up
   the code inference for the `AUI()` global object. Press *Enter* to use code
   completion.

![Figure 1: This figure demonstrates code inference in a JS file.](../../images/code-inference.png)

By default, code inference is triggered by a keystroke combination; however, you
can enable auto activation in IDE's Preferences menu. Follow the steps below
to enable auto activation:
    
1. Navigate to *Window* &rarr; *Preferences* &rarr; *JavaScript* &rarr; *Editor*
   &rarr; *Content Assist*.

2. Check the *Enable auto activation* box and click *Apply*. Then click *OK*.

The figure below shows how to enable auto activation:
    
![Figure 2: The *Enable auto activation* checkbox is listed below the *Auto-Activation* heading.](../../images/enable-auto-activation.png)

Now, if you follow the previous example, code inference activates as soon as
you press the trigger key, which in this case is the *.* (period) key.

In addition to general code inference for AlloyUI, you have access to code 
templates. Follow the steps below to use AUI code templates:

1. Type the following code in your `main.js`:
 
        AUI

2. Press *Ctrl+Space* to bring up the code inference for `AUI`, and you'll 
   see a list of all the available AlloyUI code templates, along with
   documentation.

    ![Figure 3: IDE now gives you access to AUI code templates.](../../images/code-template.png)
    
3. Select your template and hit *Enter* to paste its contents into your
   `main.js`.

    +$$$

    **Note:** You can view all of the AlloyUI code templates you have 
    installed by going to IDE's Preferences menu and selecting *JavaScript*
    &rarr; *Editor* &rarr; *Templates*.

    $$$        

In addition to code inference in your JS files, you can also use code inference 
in your JSP files using `<aui:script>` tags.

Open one of your project's JSPs and add the AUI taglib directive if it is not
already in your JSP. The import should look like the following:

    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

You can also add the import from the Snippets menu under *Taglib imports* &rarr;
*Liferay AUI Taglib Import v6.1*.

1. Add an `<aui:script>` tag inside your JSP and configure it to look like the
   following code:

        <aui:script>
            aui
        </aui:script>

2. Press *Ctrl+Space* with your cursor placed to the right of `aui` to bring up
   code inference.

There you go! Whether in a JavaScript file, or inside of a JSP, you now have
access to code assist features that improve your workflow.

Next, you can take a closer look at the JavaScript code assist features for IDE.

### JavaScript Code Assist Features

In addition to AlloyUI code assist features, you also have access to code 
inference and completion using raw JavaScript. The example below shows how you 
can use code assist features to easily access functions in your portlet project.

1. Open the `main.js` of your portlet and add the following function:

        function say(text){
            alert(text);
        }

2. Add the following button to the `view.jsp` of your portlet:

        <aui:button onClick=""/>

3. Place your cursor within the quotation marks of the `onClick` attribute and
   press *Ctrl+Space*. You'll see the code inference dialog popup with a list of
   possible JavaScript functions available for you to use.

4. Type *say* and you'll notice the list is narrowed down to your new
   `say(text)` function.

    ![Figure 4: JavaScript code assist features give easy access to your functions.](../../images/js-code-inference.png)

5. Select the `say(text)` function, and you'll notice that it's accompanied by
   documentation that provides the parameter for the function, as well as the
   file path to which the function is located.

6. Press *Enter* to use code completion and add the function to your button.

As you can see, JavaScript development is a breeze using IDE's code assist
features. Now that you know how to use the AlloyUI and JavaScript code assist
features, you can begin learning how to use the CSS code assist features next.

### CSS Code Assist Features

IDE offers code inference and completion tools for CSS. In order to use these
tools, you'll need to install an additional plugin. Follow the steps below to
install the plugin in IDE:

1. Go to *Help* &rarr; *Install New Software...*.

2. Paste the following link into the `Work with:` input field:

        http://oss.opensagres.fr/eclipse-wtp-webresources/0.7.0/

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

1. Open your `main.css` file and add the following class to it:

        .sample-class {
            background-color:green;
        }

2. Inside of your `view.jsp` add an `<aui:button/>` tag and configure it to
   match the following code:

        <aui:button name="test" value="test" cssClass=""/>
  
3. Within the quotations of the `cssClass` attribute, press *Ctrl+Space* to
   bring up the code inference for CSS. Begin typing *sample-class* to narrow
   down the classes to the one you're looking for.

    ![Figure 5: CSS code inference improves your workflow when developing in CSS.](../../images/css-code-inference.png)

    Notice, along with code inference, you can also view the styling you have
    for the class, as well as the file in which it is located.
    
4. Press *Enter* to use code completion and add the CSS class to the JSP.

If you look at the code inference dialog for CSS classes, you'll also notice
that in addition to your own CSS classes, you also have access to Bootstrap CSS
classes found in Liferay Portal.

+$$$

**Note:** You can go to the file that the class, ID, or function is located in by
hovering over top of it in your JSP and holding down the `Ctrl` (Windows) or
`command` (Mac) key, and clicking the hyperlink that appears.

$$$

Excellent! You now know how to use IDE's front-end development code assist 
features to improve your workflow. 
    
## Related Topics

[Enabling Code Assist Features in your Project](/develop/tutorials/-/knowledge_base/6-2/enabling-code-assist-features-in-your-project) 

