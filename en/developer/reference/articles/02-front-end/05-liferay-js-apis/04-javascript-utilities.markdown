---
header-id: javascript-utilities
---

# @product@ JavaScript Utilities

[TOC levels=1-4]

This reference explains some of the utility methods and objects inside the 
`Liferay` global JavaScript object. 

## Retrieve Browser Information

The `Liferay.Browser` object contains 
methods that expose the current user agent characteristics without the need of 
accessing and parsing the global `window.navigator` object. 

The available methods for the `Liferay.Browser` object are listed in the table 
below:

| Method | Return Type | Description |
| --- | --- | --- |
| acceptsGzip | boolean | Returns whether the browser accepts gzip file compression |
| getMajorVersion | number | Returns the major version of the browser |
| getRevision | number | Returns the revision version of the browser |
| getVersion | number | Returns the major.minor version of the browser |
| isAir | boolean | Returns whether the browser is Adobe AIR |
| isChrome | boolean | Returns whether the browser is Chrome |
| isFirefox | boolean | Returns whether the browser is Firefox |
| isGecko | boolean | Returns whether the browser is Gecko |
| isIe | boolean | Returns whether the browser is Internet Explorer |
| isIphone | boolean | Returns whether the browser is on an Iphone |
| isLinux | boolean | Returns whether the browser is being viewed on Linux |
| isMac | boolean | Returns whether the browser is being viewed on Mac |
| isMobile | boolean | Returns whether the browser is being viewed on a mobile device |
| isMozilla | boolean | Returns whether the browser is Mozilla |
| isOpera | boolean | Returns whether the browser is Opera |
| isRtf | boolean | Returns whether the browser supports RTF |
| isSafari | boolean | Returns whether the browser is Safari |
| isSun | boolean | Returns whether the browser is being viewed on Sun OS |
| isWebKit | boolean | Returns whether the browser is WebKit |
| isWindows | boolean | Returns whether the browser is being viewed on Windows |

Below is an example configuration:

```javascript  
Liferay.Browser.isChrome(); //returns true in Chrome
```

## Format XML

The `Liferay.Util.formatXML` utility takes XML content, as a String, and returns 
it formatted. 

Parameters:
- `content`: The XML string to format
- `options`: An optional configuration object `{}` that contains additional 
  parameters for formatting the XML 

The default configuration contains these options:

```javascript  
const DEFAULT_OPTIONS = {
  newLine: NEW_LINE, //'\r\n'
  tagIndent: TAG_INDENT //'\t'
};
```

Below is an example configuration for a JSP that overwrites the default options:

```javascript  
var options = {newLine: '\n', tagIndent: ' '};

var input = `<?xml xlmns:a="http://www.w3.org/TR/html4/" version="1.0" encoding="UTF-8"?>
<!DOCTYPE note>
<a:note>                         <a:to>Foo</a:to>
<a:from>Bar</a:from><a:heading>FooBar</a:heading>
<a:body>FooBarBaz!</a:body>
                  </a:note>
`;

var formattedXMLString = Liferay.Util.formatXML(input, options);

console.log(formattedXMLString);
/*results:
<?xml xlmns:a="http://www.w3.org/TR/html4/" version="1.0" encoding="UTF-8"?>\n'
<!DOCTYPE note>\n'
<a:note>\n'
<a:to>Foo</a:to>\n'
<a:from>Bar</a:from>\n'
<a:heading>FooBar</a:heading>\n'
<a:body>FooBarBaz!</a:body>\n'
</a:note>';
*/
```

## Format Storage Size

The `Liferay.Util.formatStorage` utility takes a storage size number (in bytes) 
and returns it in the proper format (KB, MB, or GB) as a String. 

Parameters:
- `size`: The numerical value of the storage size in bytes
- `options`: An optional configuration object `{}` that contains additional 
  parameters for formatting the storage size 

The default configuration contains these options:

```javascript  
const DEFAULT_OPTIONS = {
  addSpaceBeforeSuffix: false,
  decimalSeparator: '.',
  denominator: 1024.0,
  suffixGB: 'GB',
  suffixKB: 'KB',
  suffixMB: 'MB'
};
```

Below is an example configuration that overwrites some of the default options:

```javascript  
var formattedSize = Liferay.Util.formatStorage(1048576, {
  addSpaceBeforeSuffix: true,
  decimalSeparator: ',',
  suffixMB: 'megabytes'
});

console.log(formattedSize); //1,0 megabytes
```

## Store and Retrieve Session Form data

`Liferay.Util.setSessionValue()`: Sets a key, value pair for the Store utility's 
fetch value. 

Parameters:
- `key`: The `formData` key (String)
- `value`: The `formData` key's corresponding value (Object|String)

`Liferay.Util.getSessionValue()`: Retrieves the Store utility's fetch value for 
the given `key`.

Parameters:
- `key`: The key (String) to fetch the value for.

Below is an example configuration for a JSP:

```markup
Liferay.Util.Session.set('state', 'open');

Liferay.Util.Session.get('state').then(function(value) {
  console.log(value); //open
});
```

Here is an example configuration that uses ES6:

```javascript  
import {getSessionValue, setSessionValue} from 'frontend-js-web';

setSessionValue('state', 'open');

getSessionValue('state').then(value =>{
  console.log(value); //open
});
```
