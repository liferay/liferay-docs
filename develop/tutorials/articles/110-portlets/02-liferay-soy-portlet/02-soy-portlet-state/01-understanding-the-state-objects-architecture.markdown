# Understanding The State Object's Architecture [](id=understanding-the-state-object-architecture)

An example `STATE` object configuration is shown below:

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

**setter:** Specifies a function to normalize the state key's value. The setter 
function should receive the new value that was set and return the value that 
should be stored.

**validator:** Specifies a function that validates the state key's value. When 
it returns `false`, the new value is ignored.

**value:** The state key's default value. Alternatively, you can set the default 
value with the `valueFn` property. Note that setting this to an object causes 
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

## Related Topics [](id=related-topics)

[Configuring Portlet Template Parameter State Properties](/develop/tutorials/-/knowledge_base/7-0/configuring-portlet-template-parameter-state-properties)

[Configuring Soy Portlet Template Parameters on the Client Side](/develop/tutorials/-/knowledge_base/7-0/configuring-soy-portlet-template-parameters-on-the-client-side)