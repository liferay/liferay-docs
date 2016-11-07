# Soy Portlets

## What is Soy?

Soy, AKA Google [Closure Templates](https://developers.google.com/closure/templates/),
are a client and server side templating system that helps you dynamicaly build 
resuable HTML and UI elements.  The syntax is simple and comes naturally to most 
programmers, and you can customize it to fit your applications needs.

Closure templates are implemented for both JavaScript and Java, so you can use 
the same templates on both the server and client side. This provides the benefit 
of being able to render pages on the server before serving to the client, 
benefiting the user experience on first load.

## How does Soy relate to Metal.js?

Soy Templates are one of the template types compatible with Metal.js. Using Soy 
templates and Metal.js together is simple and easy to create solid, lightweight, 
and flexible UI compoenents.

## What is a Soy Portlet?

A Soy Portlet is a Liferay Portlet that uses Soy and Metal.js as its' frontend.

## What are the benefits of a Soy Portlet?

Using Soy as the template of your portlet gives you all the benefits of using 
Metal.js.  You get the advantage of a framework that's built from the ground up 
with performance in mind.  A versatile build system that you can leverage in a 
number of different ways.  And the intangible benefit of having your portlet 
written with future-ready ECMAScript6/2015 code making it clean and easy to read.

## What does a Soy Portlet look like?

The file strucutre of a Soy Portlet would look similar to a regular portlet:

- `my-soy-portlet`
    - `.lfrbuild-portal`
    - `build.gradle`
    - `bnd.bnd`
    - `package.json`
    - `src/main/`
        - `java/com/liferay/frontend/my/soy/portlet/web/internal/portlet`
            - `MySoyPortlet.java`
        - `resources/META-INF/resources/`
            - `MyComponent.es.js`
            - `MyComponent.soy`
            - `MyComponent.scss`

`MySoyPortlet` can extend from the `SoyPortlet` class like this.

    public class MySoyPortlet extends SoyPortlet {
    
        @Override
        public void render(RenderRequest renderRequest, RenderResponse renderResponse) {
            //do things here
        }
    }


And here is what the Soy Template magic looks like:

    {namespace MyComponent}

    /**
     * This renders the main content of the `MyComponent` component.
     * @param? content
     */
    {template .render}
        <div class="my-component">
            <div class="my-component-content">
                <button type="button" class="close" data-onclick="hide">
                    <span>×</span>
                </button>
                <h4>{$content ?: ''}</h4>
            </div>
        </div>
    {/template}
    ```
    
    And the Metal.js JavaScript code to go along with this component:
    
    ```javascript
    'use strict';
    
     /**
     * The soy template functions are automatically exported by `MyComponent.soy.js`.
     */
    import templates from './MyComponent.soy';
    import Component from 'metal-component';
    import Soy from 'metal-soy';
    
    class MyComponent extends Component {
        /**
         * This function is used as a listener by the soy file.
         * @param  {!Event} event
         */
        hide() {
            // All Metal.js components already have a `visible` state which sets 
            // the main element's `display` to "none" when set to false.
            this.visible = false;
        }
    }
    // This line is declaring that `MyComponent` will be using the given soy 
    // templates for rendering itself.
    Soy.register(MyComponent, templates);
    
    export default MyComponent;

## Soy Portlet Examples

- [Hello Soy Portlet](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/hello-soy/hello-soy-web) - A Hello World portlet built with Soy.
- [Image Editor Portlet](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-image-editor/frontend-image-editor-web) - A portlet for editing images built with Soy and Metal.js.
