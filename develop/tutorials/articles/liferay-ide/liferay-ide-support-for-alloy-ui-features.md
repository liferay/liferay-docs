# Liferay IDE Support for AlloyUI Features

The new release Liferay IDE 2.2 support for AlloyUI features. But as javascript is becoming more and more strategic to how Liferay developers build their UIs in Liferay, it only made sense that AlloyUI tooling needed to be a first class citizen for Liferay IDE. So in IDE 2.2, both general javescript tooling along with AlloyUI specific features have been added.

In order to use tern for working with AlloyUI framework we needed to make several changes. We needed three plugins for tern to handle AlloyUI code(*yui, aui, and liferay tern plugins*).

## aui.js

- The aui.js plugin for tern provides many AUI specific tern completions. Here we see there is a type level AUI method available and the documentation from the alloyui framework’s own api documentation is displayed.

![aui_js](images/aui_js.png)

- Here you see that all the methods on the AUI object are available along with the method *js* doc.

![aui_js](images/aui_js_2.png)

- In this code snippet the callback method has a single parameter called ‘`A`’. This ‘type’ information for this is supplied by the aui.js tern plugin, and you can see in this screenshot that is provides code assist for all of the methods that are on the A object.

![aui_js](images/aui_js_3.png)

- The methods that are proposed on the ‘`A`’ type are specifically the ones available for the ‘`aui-button`’ module. If another module was selected, say, ‘`aui-datatable`’ then those methods are made available instead.

![aui_js](images/aui_js_4.png)

- The aui.js plugin tells tern that the `A.Button` type has all of the methods shown when you invoke code assist.

![aui_js](images/aui_js_5.png)

- The yui.js tern plugin also contributes type information. 
- The aui.js plugin tells tern that the object that is returned by *A.one()* method is of type Node.
- The yui.js tern plugin tells tern that objects of type Node have several available methods, so when you invoke completions on the table variable it will show methods for Node types.

![js](images/yui_js.png)

- The *liferay.js* plugin provides completions for the static javascript object APIs available for portlets when running in Liferay portal.

![js](images/liferay_js.png)

- *jquery.js* tern plugin can give type information for jquery framework.

![jquery](images/jquery_js_1.png)

- For jquery callback handlers, the type information for parameters is also made available.

![jquery](images/jquery_js_2.png)

## aui:script tag

Tern also works on embedded scripts inside *html/jsp* pages, that was working by default. However, for eclipse or tern didn’t know anything about `<aui:script>` and the fact that it should be treated just like `<script>` tags. So we had to modify the eclipse jsp editor to allow the javascript tooling treat `<aui:script>` tags as normal html `<script>` tags. 

- By default the tern tooling is only available in stand-alone *.js* files and also in `<script>` elements in both html and jsp files.
- In Liferay IDE the *jsp/html* editor in Eclipse has been extended to make `<aui:script>` tags operate just the same as `<script>` elements so that type inference and API information.

![aui_script](images/aui_script.png)

## aui code templates

Many new code templates have been added for both working with alloyui in the javascript and jsp editors. 

- In this release we’ve added lots of new aui templates for speeding up development when using alloyui.
- (*first screenshot*) aui javascript templates are added to the javascript editor (*also works in script, `aui-script` tags in html/jsp editor*).
- (*second screenshot*) aui jsp tag templates are added to jsp editor.

![aui_template](images/aui_templates.png)

## Better CSS support

- Since alloyUI framework is about much more than just javascript, we have added some CSS related development features to this release as well.
- When editing in a html or jsp file in a liferay project you will now get features related to CSS classNames or IDs.
- Features that available for css classname or id are code completion, hover info, and hyperlinks.

![css](images/css_1.png)

- In *screenshot#1* you can see that completion is available for any css Ids that is finds in any CSS file in the user’s project. As you can see the `interviewid-` completions are in the file, main.css in the user’s project. 
- These types of completions work for `<style>` elements that are defined in the same html or jsp file as well. (*screenshot #2*).

![css](images/css_2.png)

- Both hover information and hyperlink navigation are supported for these CSS classname and ids as well.
- The duplicated hyperlink definition was just a bug at the time of this screenshot, that has been fixed now.

![css](images/css_3.png)

- These css features work for both `<style>` element, external css files in the project, but also for portal bootstrap CSS resources as well.
- In these screenshots you can see that for aui taglib cssClass attributes we are providing both `<style>` and project css classNames and ids but also offereing those names available in the *_unstyled* portal bootstrap theme.
- This also works from jsf facelets component tags styleClass attribute.


![css](images/css_4.png)

## AlloyUI feature

- All of the features related to AlloyUI and new javascript tooling are available is a separate installable feature. 
- This is because of the size of this feature which includes the bundled tern server which includes a nodejs runtime, also this feature makes significant modifications to the core eclipse *html/jsp* editors that could cause problems in some environments, so we have made it separately installable for those users who are interested in these features.

![alloyui_feature](images/alloyui_feature.png)

