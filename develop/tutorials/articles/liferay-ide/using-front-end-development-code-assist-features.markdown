# Using Front-End Development Code Assist Features in IDE

Since IDE 2.2, front-end development has become a much smoother process. You now
have access to code inferencing and code completion features for AlloyUI,
JavaScript, and CSS.  

This tutorial covers how to use the code assist features for JavaScript, 
AlloyUI, and CSS in IDE. The tutorial is broken into each language, so you can
jump to the section you want. Keep on reading to find out how to use IDE's code
assist features in your project.

## Using Code Assist Features

IDE's integration of Tern features give you access to code assist in JavaScript, 
AlloyUI, and CSS. To access these features, you need to be in either a 
JavaScript, JSP, or CSS file.

+$$$

**Note:** You must have Tern features enabled in your project in order to use 
them. By default Liferay portlet plugins already have Tern features enabled. 
Please see the [Enabling Code Assist Features in your Project](/develop/tutorials/-/knowledge_base/6-2/enabling-tern-code-assist-features-in-your-project)
tutorial to learn how to enable Tern features for your project.

$$$

Go ahead and get started with the AlloyUI code assist features next.

### AlloyUI Code Assist Features

There are several helpful code assist features that can improve your 
productivity when writing code for AlloyUI. The example below shows how to 
access the AlloyUI code assist features in the `main.js` of your project:

1.  Open your `main.js` file.

2.  Type the following code:

        AUI().
        
3.  Press *ctrl+space*, with your cursor next to `AUI().`, to bring up the code 
    inference for the `AUI()` global object, and press *enter* to use code 
    completion.
    
The figure below demonstrates the steps above:


![Figure 1: Code inference is just a few keystrokes away.](../../images/code-inference.png)

By default the code inference is triggered by a keystroke combination; however, 
you can enable auto activation in the Preferences for IDE. Follow the steps 
below to enable auto activation:
    
1.  Open the global Preferences and goto *JavaScript*&rarr;*Content Assist*.

2.  Check the box next to *Enable auto activation* and click *Apply* and *OK*.

The figure below shows how to enable auto activation:
    
![Figure 2: Enabling auto activation is a quick process.](../../images/enable-auto-activation.png)

Now, if you follow the previous example, code inference will activate as soon as
you press the trigger key, which in this case is the .(period) key. 

In addition to general code inference for AlloyUI, you have access to code 
templates. Follow the steps below to use AUI code templates:

1.  Type the following code in your `main.js`:
 
        AUI

2.  Press *ctrl+space* to bring up the code inference for `AUI`, and you will 
	see a list of all the available AlloyUI code templates, along with
    documentation.
    
    Your code inference window should look like the figure below:
    
    ![Figure 3: IDE now gives you access to AUI code templates.](../../images/code-template.png)
    
3.  Select your template and hit *enter* to paste it's contents into your
    `main.js`.
  
    +$$$
    
    **Note:** You can also see all of the AlloyUI code templates you have 
    installed by going to the Preferences for IDE and selecting *JavaScript*
    &rarr;*Editor*&rarr;*Templates*.
    
    $$$        


In addition to code inference in your JS files, you can also use code inference 
in your JSP files, using `<aui:script>` tags.

Open one of your JSPs and add the AUI taglib directive if it is not already in 
your JSP. The import should look like the code below:

    <%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
    
You can also add the import from the snippets under *Taglib Imports*&rarr;
*Liferay AUI Taglib Import v6.1*.

1.  Add an `<aui:script>` tag inside your JSP and configure it to look like the
    following code:
    
    <aui:script>
        aui
    </aui:script>

2. Press *ctrl+space*, with your cursor placed next to `aui`, to bring up code 
inference.

There you go! Whether in a JavaScript file, or inside of a JSP, you now have
access to code assist features that will improve your workflow.

Next, you can take a closer look at the JavaScript code assist features for IDE.

### JavaScript Code Assist Features

In addition to AlloyUI code assist features, you also have access to code 
inference and completion using raw JavaScript. The example below shows how you 
can use code assist features to easily access functions in your portlet project.

1.  Open the `main.js` of your portlet and add the following function:

        function say(text){
            alert(text);
        }

2.  Add the button below to the `view.jsp` of your portlet:

        <aui:button onClick=""/>

3.  Press *ctrl+space* while inside of the `" "` of the `onClick` attribute.

    You'll see the code inference dialog popup with a list of possible 
    JavaScript functions for you to call.

4.  Type 'say' and you will see that the list is now narrowed down to your new
    `say(text)` function. Your code inference dialog should look like the figure
    below:
    
    ![Figure 4: JavaScript code assist features give easy access to your functions.](../../images/js-code-inference.png)

5.  Select the `say(text)` function, and you will see that it is
    accompanied by documentation that provides the parameter for the function, 
    as well as the file path to which the function is located.

6.  Press *Enter* to use code completion and add the function to your button.

There you go! As you can see, JavaScript development just got a bit easier in 
IDE. Now that you know how to use the AlloyUI and JavaScript features, you can 
learn How to use the CSS code assist features next.

### CSS Code Assist Features

IDE now offers code inference and completion tools for CSS. In order to use
these tools, you will need to install an additional plugin. Follow the steps
below to install the plugin in IDE:

1.  Goto *Help*&rarr;*Install New Software...* .

2.  Paste this link into the `Work with:` input field:

        http://oss.opensagres.fr/eclipse-wtp-webresources/0.7.0-SNAPSHOT/

3.  Click *Add...* and check the box next to *Web Resources*.

4.  Click *Next* and follow the installation instructions.

Now that your plugin is installed, you'll need to enable the CSS features in 
your project.

1.  Right click your project and goto *Properties*&rarr;*Web Resources*&rarr;
    *CSS* and check both boxes to enable CSS features in your project.

You have successfully installed and enabled the new CSS features in your
project!

Now that you have the CSS features enabled, you can find out how to use them 
next. Follow the steps below to use the CSS code assist features to locate a CSS
class. Note that the process below can also be used to locate an id.

1.  Open your `main.css` file and add the following class to it:

        .sample-class{
            background-color:green;
        }

2.  Inside of your `view.jsp` add an `<aui:button/>` tag and configure it to
    match the following code:
    
        <aui:button name="test" value="test" cssClass=""/>
        
3.  Inside of the `cssClass` attribute press *ctrl+space* to bring up the code
    inference for CSS and type "sam" to narrow down the classes to your CSS 
    class.
    
    You should see a dialog like the figure below:
    
    ![Figure 5: CSS code inference improves workflow.](../../images/css-code-inference.png)

    As you can see, along with code inference, you can also see the styling you
    have for the class, as well as the file in which it is located.
    
4.  Press *enter* to use code completion and add the CSS class to the JSP.

If you look at the code inference dialog for CSS classes, you will also notice
that in addition to your own CSS classes, you also have access to the Bootstrap
CSS classes found in your Portal.

+$$$

**Note:** You can go to the file the class, id, or function is located in by 
hovering over top of it in your JSP and holding down the `ctrl` key, or 
`command(Apple)` key if you're on a Mac, and clicking the hyperlink that appears.

$$$

Excellent! You now know how to use IDE's front-end development code assist 
features to improve your workflow. 
    
## Related Topics

 [Enabling Tern Code Assist Features in your Project](/develop/tutorials/-/knowledge_base/6-2/enabling-tern-code-assist-features-in-your-project) 
