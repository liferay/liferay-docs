# Action: Show and Hide [](id=action-show-and-hide)

With a show and hide rule, you use one or more conditions to determine whether
to show or hide a field if the condition evaluates to *true*.

To set this example up, add these fields to a form: 

- *I am 18 Years Old or Older*, a required single selection field with two options:
  *Yes* and *No*.

- *Legal Guardian Email Address*, a text field that accepts valid email
  addresses (use text field validation to dictate input type).

+$$$

**Example:** If you're under 18 years old, you need the approval of a legal
guardian to drive a sled in a sled dog race (even if you're racing chickens, not
dogs). The form for registering your chicken team asks you the age of the
driver. If you enter a number less than 18, the Legal Guardian Email Address
field appears.

To configure a Show/Hide rule,

1.  Open the Rules tab of the Edit Form page and click the Add
    (![Add](../../../images/icon-add.png)) button.

2.  Define the rule:
     - If the *I am 18 years old or older* field is equal to *No*, show the
       *Legal Guardian Email Address* field.

    ![Figure 1: Build form rules quickly by defining your conditions and actions.](../../../images/forms-rule-development.png)

     - Save the rule. 

    ![Figure 2: Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-rule-list.png)

Now the *Legal Guardian Email Address* field is only displayed in the form if
the user selects *No* in the *I am 18 years old or older* field.

$$$

Show rules let you keep a field hidden until some condition is met.
