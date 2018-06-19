# Action: Require [](id=action-require)

Use a require rule to make a field required based on one or more conditions.

+$$$

**Example:** If you are following the example, you already set up a _show_ rule,
where a *Legal Guardian Email Address* field appears if the user selects *No* in
the *I am 18 years old or older* field. You also want to make the *Legal
Guardian Email Address* field required.

To configure a require rule, 

1.  Edit the 
    [Show Rule](/discover/portal/-/knowledge_base/7-1/action-show-and-hide)
    configured above. Open the Rules tab of the Edit Form page and click the
    kebab menu (![Actions](../../../images/icon-actions.png)) for the rule, and
    then click *Edit*.
2.  Add an Action to the rule:
     - If the *I am 18 years old or older* field is equal to *No*, show the
       *Legal Guardian Email Address* field and make it required.

    ![Figure 1: Build form rules quickly by defining your conditions and
    actions.](../../../images/forms-require-rule.png)

     - Save the rule. 

    ![Figure 2: Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-require-rule2.png)

$$$

Require rules let you require fields based on input from other fields.
