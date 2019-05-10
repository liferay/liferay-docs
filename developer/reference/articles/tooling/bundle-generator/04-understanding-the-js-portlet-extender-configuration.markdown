---
header-id: understanding-the-js-portlet-extender-configuration
---

# Understanding the JS Portlet Extender Configuration

[TOC levels=1-4]

Bundles generated with the Liferay Bundle Generator require specific method 
signatures, MANIFEST headers, and configuration within their `package.json` 
file to use the JS Portlet Extender. This configuration is provided by default. 
For reference, this configuration is covered in detail below.

## Manifest Header

The OSGi bundle is identified with the MANIFEST header shown below, which 
specifies to process it with the JS Portlet Extender:

```properties
Require-Capability: osgi.extender;filter:="(osgi.extender=liferay.npm.portlet)"
```

## Main Entry Point

The main module of your JavaScript portlet must export a JavaScript function 
with the signature below. Bundles created with the Liferay Bundle Generator have 
this out-of-the-box:

```javascript
function({portletNamespace, contextPath, portletElementId, configuration}) {
  ...
}
```

The entry point function receives one object parameter with four fields:

- `portletNamespace`: the unique namespace of the portlet as defined in 
the Portlet specification. 
- `contextPath`: the URL path that can be used to retrieve bundle 
resources from the browser (it doesn't contain the protocol, host, or port, 
just the absolute path). 
- `portletElementId`: the DOM identifier of the portlet's `<div>` node 
that can be used to render HTML. 
- `configuration` (optional): since JS Portlet Extender version 1.1.0, this 
field contains the system (OSGi) and portlet instance (preferences as described 
in the Portlet spec) configuration for the portlet. It has two subfields: 

  - **system:** contains the system level configuration (defined in Control 
    Panel &rarr; System Settings)
    
  - **portletInstance:** contains the per-portlet configuration (defined in the 
    Configuration menu option of the portlet)

Note that all values are received as strings, no matter what their type is in 
OSGi configuration store. 

The JavaScript-based portlet's main `index.js` file configuration is shown 
below for reference. Note that system settings and localization are enabled in 
the example below:

```javascript
export default function main({portletNamespace, contextPath, portletElementId, configuration}) {
    
    const node = document.getElementById(portletElementId);

    node.innerHTML =`
        <div>
            <span class="tag">${Liferay.Language.get('porlet-namespace')}:</span>
            <span class="value">${portletNamespace}</span>
        </div>
        <div>
            <span class="tag">${Liferay.Language.get('context-path')}:</span>
            <span class="value">${contextPath}</span>
        </div>
        <div>
            <span class="tag">${Liferay.Language.get('portlet-element-id')}:</span>
            <span class="value">${portletElementId}</span>
        </div>
        
        <div>
            <span class="tag">${Liferay.Language.get('configuration')}:</span>
            <span class="value">
                ${JSON.stringify(configuration, null, 2)}
            </span>
        </div>
        
    `;
    
}
```

The JavaScript file containing the main entry point function is specified in the 
`main` entry of the `package.json` file. Below is the `main` entry for the 
*JavaScript based portlet*:

```json    
"main": "index.js"
```
