# Action: Jump to Page [](id=action-jump-to-page)

Use a Jump to Page rule to navigate automatically to a specific page in the form
based on one or more conditions. This is useful if some pages don't apply to all
the form's users. Even fields marked as required on the skipped pages can be
successfully skipped using this rule.

This action doesn't appear in the rule builder unless the form has multiple
pages.

To follow the example here,

1.  Create a second form page called *Team Information*.

2.  On the new form page, create a single selection field labeled *Are you a
    returning racer with the same team?* with two options: *Yes*
    and *No*.

3.  On the new form page, create a repeatable text field called *Animal Name*.

4.  Create a third form page called *Final Confirmation*.

+$$$

**Example:** There's a question on the *Team Information* page of the dog sled
race registration form that asks *Are you a returning racer with the same team?*
If you select *Yes*, when you click the form's *Next* button, you skip to
the final page of the form, since there's no need to fill out your animal's name
again. Their monogrammed T-shirts will be ready at the start of the race.

To configure the Jump to Page rule, 

1. Open the Rules tab of the Edit Form page and click the Add
   (![Add](../../../images/icon-add.png)) button.

2. Define the rule:
    - If field *Are you a returning racer with the same team?* is equal to
        *Yes*, Jump to Page *Final Confirmation*.

    ![Figure 1: Build form rules quickly by defining your conditions and actions.](../../../images/forms-jump-to-page.png)

    - Save the rule.

    ![Figure 2: Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-jump-to-page2.png)

$$$

If you use an *is not equal to* condition for form fields on two different
pages, the condition is checked after leaving the page of the first form field,
and evaluates to *true* since there's a value in the first field and no value in
the second field. It's best to use this condition with fields existing on the
same page.
