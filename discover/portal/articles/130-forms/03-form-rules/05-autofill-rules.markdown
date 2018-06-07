# Action: Autofill

Autofill rules let you the change the selection options of another field based
on the value entered into a related field. For discussion purposes, let's call
the field that's *not* autofilled, the one that the user enters data into first,
the *controller field*. The field(s) to be autofilled can be called the
*autofilled field*.

For example, you coould use a *Zip Code* field as the controller to autofill
some related fields: Country, Region, and City.

Before configuring an autofill rule, [set up a data provider](LINK). That's how the
autofilled fields will be populated. Pay careful attention to the input and
output parameters you choose when setting up the rule.

Input Parameters
: Input parameters are used for telling the autofill rule you configure what the
value of the controller field should correspond to in the data provider. If you
use zip code, the data provider must contain a field that corresponds to the
controller's value.

Output Parameters
: Data providers often have multiple fields per record (for example, a country
data provider's records might have *name*, *shortName*, and *countryId* fields).
This is where you pick which field to display for each record of the data
provider. If it's a data provider with the countries of the world, for example,
you'd probably use the name field.

To follow this example: 

1.  Set up a data provider using the `get-countries` JSON web service. If you're
    running @product@ at `localhost:8080`, you can access this web service here:
        
        http://localhost:8080/api/jsonws?contextName=&signature=%2Fcountry%2Fget-countries-0

    Make sure the output parameter is set to `nameCurentValue`. If you're unsure
    how to do this, first read the article on 
    [Data Providers](/discover/portal/-/knowledge_base/7-1/data-providers).

2.  On the last form page, add two fields:

    - A Single Selection field called *If I win I'd like my award to be:*, with
        two choices: *Cash* and  *All Expenses Paid Vacation*.

    - A Select from List field called *Choose a Destination Country*. Under
        *Create List*, choose *From Autofill*.

+$$$

**Example:** Before submitting the race registration, let users decide whether
they want a cash prize or an all-expenses-paid vacation. If they choose the
vacation, populate the Choose a Destination Country with output from the data
provider.

To configure an Autofill rule, 

1. Open the Rules tab of the Edit Form page and click the Add
   (![Add](../../../images/icon-add.png) button.

2. Define the rule:
    - If field *If I win I'd like my award to be* is equal to
        *All Expenses Paid Vacation*, Autofill the *Choose a Destination Country* filed from the *countries* data provider (note that you might have named this differently when setting it u).

    ![Figure x: Build form rules quickly by defining your conditions and actions.](../../../images/forms-autofill.png)

    - Save the rule.

    ![Figure x: Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-autofill2.png)

$$$

Autofill rules combine the power of data providers and form rules.
