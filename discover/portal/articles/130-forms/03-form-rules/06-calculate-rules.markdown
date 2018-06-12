# Action: Calculate [](id=action-calculate)

Calculate rules let you automatically populate a numeric field by calculating
its value based on other fields. Calculations are limited to numeric fields.

To follow the example below:

1.  Create 5 Numeric fields called *Animal Weight 1 (US lb.)*, *Animal Weight 2
    (US lb.)*, etc.

2.  Create a Numeric field called *Total Food Required (US lb.)*.

+$$$

**Example:** A 16-dog sled team can consume 2,000 US lb. of food during the
Iditarod. This equates to about 0.25 lb. of food per lb. of animal, if the race
lasts ten days. We'll use five numeric fields for animal weight instead of
sixteen here, because it's tedious to create sixteen fields, even with the field
duplication function. When the form user enters the weight of each animal the
Total Food Required field should be calculated based on this simple formula:

    Animal Weight 1, Animal Weight 2, ... = AW1, AW2, ...
    Total Food Required = TFW

    (AW1 + AW2 + AW3 + AW4 + AW5) * 0.25 = TFW

To configure a calculate rule:

1. Open the Rules tab of the Edit Form page and click the Add
   (![Add](../../../images/icon-add.png)) button.

2. Define the rule:
    - If field *Animal Weight 1* is greater than 0, Calculate the sum of the
        Animal Weight fields, multiplied by 0.25.

    ![Figure 1: Build calculate actions with a handy calculator.](../../../images/forms-calculate-rule.png)

    - Save the rule.

    ![Figure 2: Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-calculate-rule2.png)

$$$

The calculation is defined using the embedded calculator. Use a mix of numeric
field values, mathematical operators, and constants to define calculation
rules.
