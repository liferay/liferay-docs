# Extending and Overriding portal.properties [](id=extending-and-overriding-portal-properties)

In our hook that created a custom login action, we modified the
`login.events.pre` portal property. This property accepts *multiple* values, so
our value was appended to the existing `login.events.pre` values. We can
repeatedly modify the property from additional hooks because it accepts
multiple values. Some portal properties only accept a *single* value, such as
the `terms.of.use.required` property, which is either `true` or `false`. Only
modify single value properties from one hook; otherwise Liferay won't know
which value to use. 

To find out which properties accept multiple values, look in the [Configuring
Liferay's
Properties](http://docs.liferay.com/portal/6.1/propertiesdoc/portal.properties.html)
document. 

+$$$

**Note:** Hooks support customizing a specific list of predefined properties.
This list is found in `liferay-hook-[liferay version].dtd`, in the `definitions`
folder of the Liferay source code. In addition to defining custom actions, hooks
can override portal properties to define model listeners, validators,
generators, and content sanitizers. If you want to customize a property that's
not found in this list, you must use an Ext plugin (Chapter 8). 

$$$

Now let's look at overriding and adding Struts actions from a hook plugin. 
