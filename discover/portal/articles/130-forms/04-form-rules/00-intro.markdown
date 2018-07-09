# Form Rules [](id=form-rules)

What's the difference between a chicken and a dog? Among other things, you can't
train a chicken, while dogs are quite trainable. If you're skeptical, try
teaching your chicken to sit on command or herd sheep. Better yet, get a team of
chickens to pull a sled in the [Iditarod](http://iditarod.com). The Forms
application is much more like the dog than the useful (southwestern omelet
anyone?) but untrainable chicken, and it's only getting more trainable as time
passes. 

Form rules are a good example of the trainable nature of the Forms application.
With form rules, you can train your form fields to behave as you wish. There are
several things you can make them do: 

Show/hide
: Based on a predefined condition, set the visibility of a form field.

Enable/disable
: Use a predefined condition to enable or disable a field.

Require
: Use a predefined condition to make a field required.

Jump to Page
: Based on user input, skip over some form pages directly to a relevant
page. 

Autofill with Data Provider 
: Use a [data provider](/discover/portal/-/knowledge_base/7-1/data-providers)
to populate fields when a condition is met in another field.

Calculate
: Populate a field with a calculated value using data entered in other fields.

Form rules are for changing fields and form elements by acting on conditions.

*If [condition] do [action].*

If you're not already familiar with the Forms application, start
[here](/discover/portal/-/knowledge_base/7-1/forms). Once you know how to create
forms, add and configure fields, and then publish forms, come back here and
learn about form rules.

## The Anatomy of a Form Rule [](id=the-anatomy-of-a-form-rule)

Each rule consists of one or more conditions and actions. 

*Conditions* determine whether any actions are executed. 

*Actions* determine what happens if the condition is met.

Rules are stored in the database in JSON format by default.

## Creating Form Rules: Rule Builder [](id=creating-form-rules-rule-builder)

Once you create a form and lay out its fields, you're ready to set up rules in
your form: 

1.  Save the form.

2.  Open the Rule Builder by clicking the *Rules* tab at the top of the *Edit
    Form* screen.

3.  In the rule builder view, you can now begin developing your form rule. Click
    the *Add* button (![Add](../../../images/icon-add.png)) to get started.

![Figure 1: The Rule Builder gives you a handy interface for creating dynamic
form rules.](../../../images/forms-rule-builder.png)

Before looking at each type of rule condition and action you can use to develop
rules, consider the *OR* selector box at the right side of the *Condition* (it's
grayed out and unusable at first). You can choose *OR* or *AND* here, depending
on what relationship the conditions should have with the action.

OR
: The action is triggered if *any* of the conditions you specify evaluates to
*true*

AND
: The action is triggered only if *all* the conditions you specify evaluate to
*true*

This box becomes usable once you click the Add button
(![Add](../../../images/icon-add.png)) to add an extra condition.

## Conditions [](id=conditions)

Conditions are the gatekeepers of form rules. If the condition's *if statement*
evaluates to *true*, the action is triggered. If it evaluates to *false*, no
action happens.

A condition checks whether one field's value

- *Is equal to* a specific value or another field's value.
- *Is not equal to* a specific value or another field's value.
- *Contains* a specific value or another field's value.
- *Does not contain* a specific value or another field's value.
- *Is empty*. This assumes you want to do something if a field *is* empty.
- *Is not empty*. This assumes you want to do something as long as a field is
    *not* empty.

One exception to this is the User condition, which is the last option in the
Condition dropdown menu.

The User condition doesn't act on a field at all. It checks whether a User
belongs to a certain role. For example, if the condition

If `User` *belongs to* `Administrator` 

evaluates to *true*, an action is triggered. 

A condition is the gateway into a form rule, but actions define what actually
happens when the condition evaluates to *true*. The remaining articles discuss
the available actions and demonstrate their use.
