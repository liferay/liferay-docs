---
header-id: working-with-addresses
---

# Working with Addresses

[TOC levels=1-4]

The `Liferay` global JavaScript Object exposes methods, objects, and properties 
that access the portal context. The `Liferay.Address` utility contains methods 
for retrieving information about the addresses country and region. The `Liferay` 
global object is automatically available at runtime, so no additional 
dependencies are required. 

The available methods are listed below, along with an example configuration. 

`Liferay.Address.getCountries(callback)`: returns an Array of the available 
countries.

Parameters:
- `callback`: A callback function to post-process the Array of countries

The example below prints the list of available regions for the selected country 
(the United States in this case) in a table:

```javascript
Liferay.Address.getCountries(function(e){console.table(e)}, 19);
```

`Liferay.Address.getRegions(callback, selectKey)`: returns an Array of the 
available regions, by country, for the specified region ID.

Parameters:
- `callback`: A callback function to post-process the Array of regions
- `selectKey`: The selected region ID

The example below prints the list of available countries in a table to the 
console:

```javascript
Liferay.Address.getCountries(function(e){console.table(e)});
```

This example uses an AUI `DynamicSelect` module to create a pair of select 
fields in a JSP. The first field retrieves the countries with the 
`Liferay.Address.getCountries()` method, and the second select field is 
dynamically populated with the selected country's available regions with the 
`Liferay.Address.getRegions()` method:

```markup
<aui:script use="liferay-dynamic-select">
  new Liferay.DynamicSelect(
    [
      {
        select: '<portlet:namespace />countryId',
        selectData: Liferay.Address.getCountries,
        selectDesc: 'nameCurrentValue',
        selectId: 'countryId',
        selectSort: '<%= true %>',
        selectVal: '<%= countryId %>'
      },
      {
        select: '<portlet:namespace />regionId',
        selectData: Liferay.Address.getRegions,
        selectDesc: 'name',
        selectDisableOnEmpty: true,
        selectId: 'regionId',
        selectVal: '<%= regionId %>'
      }
    ]
  );
</aui:script>
```
