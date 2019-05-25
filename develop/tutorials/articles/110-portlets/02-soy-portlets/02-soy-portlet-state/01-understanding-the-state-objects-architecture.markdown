---
header-id: understanding-the-state-object-architecture
---

# Understanding The State Object's Architecture

[TOC levels=1-4]

An example `STATE` object configuration appears below:

    View.STATE {
      myStateProperty: {
        setter: 'setterFunction',
        validator: val => val === expected value,
        value: default value,
        valueFn: val => default value,
        writeOnce: true
      }
    }
 
State properties have these configuration options:

**setter:** Normalizes the state key's value. The setter function receives the
new value that was set and returns the value that should be stored.

**validator:** Validates the state key's value. When it returns `false`, the new
value is ignored.

**value:** The state key's default value. Alternatively, you can set the default 
value with the `valueFn` property. Setting this to an object causes 
all class instances to use the same reference to the object. To have each 
instance use a different reference for objects, use the `valueFn` option instead. 
Note that the portlet template parameter's value (if applicable) has 
priority over this value.

**valueFn:** A function that returns the state key's default value. 
Alternatively, you can set the default value with the `value` property. Note 
that the portlet template parameter's value (if applicable) has priority 
over this value.

**writeOnce:** Whether the state key is read-only, meaning the initial value is 
the final value.

Now you know the `STATE` object's architecture and how to configure it!

## Related Topics

[Configuring Portlet Template Parameter State Properties](/docs/7-1/tutorials/-/knowledge_base/t/configuring-portlet-template-parameter-state-properties)

[Configuring Soy Portlet Template Parameters on the Client Side](/docs/7-1/tutorials/-/knowledge_base/t/configuring-soy-portlet-template-parameters-on-the-client-side)
