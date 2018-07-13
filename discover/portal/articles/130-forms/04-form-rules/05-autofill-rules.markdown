# Action: Autofill [](id=action-autofill)

Autofill rules let you change the selection options of another field based
on the value entered into a related field. A [data provider's]((/discover/portal/-/knowledge*base/7-1/data-providers)) output is used to
populate a field, as long as the condition is met.

Before configuring an autofill rule, 
[set up a data provider](/discover/portal/-/knowledge*base/7-1/data-providers). 
That's how autofilled fields are populated. Pay careful attention to the
input and output parameters you choose when setting up the rule.

To follow this example: 

1.  Set up a data provider using the `get-countries` JSON web service. If you're
    running @product@ at `localhost:8080`, you can access this web service here:
        
        http://localhost:8080/api/jsonws?contextName=&signature=%2Fcountry%2Fget-countries-0

    Make sure the output parameter is set to `nameCurentValue`. If you're unsure
    how to do this, first read the article on 
    [Data Providers](/discover/portal/-/knowledge*base/7-1/data-providers).

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
   (![Add](../../../images/icon-add.png)) button.

2. Define the rule:
    - If field *If I win I'd like my award to be* is equal to
        *All Expenses Paid Vacation*, Autofill the *Choose a Destination
        Country* field from the *countries* data provider (note that you might
        have named this differently when setting it up).

    ![Figure 1: Build form rules quickly by defining your conditions and actions.](../../../images/forms-autofill.png)

    - Save the rule.

    ![Figure 2: Once a rule is saved, it is displayed so that you can easily understand what it does.](../../../images/forms-autofill2.png)

$$$

## Using Inputs with Autofill [](id=using-inputs-with-autofill)

The above example is simple, using only an Output to autofill a Select from List
field if another field has a certain value. Many times, the response from the
Rest provider must be filtered before returning results in the Select from List
field. For this, a Data Provider Input field is required. For example, to
configure an autofill rule to display the countries of the world filtered by a
Region field (for example, Americas, Europe, or Oceania),

1.  Create a Data Provider.

    **Name:** restcountries
    **URL:** https://restcountries.eu/rest/v2/region/{region}?fields=name
    **Inputs:** Fill in the Label (*region*), Parameter (*region*), and Type
    (*Text*).
    **Outputs:** fill out a  Label (**name*), Path (*$..name*), and Type
    (*List*).

    To understand more about these values, read the 
    [Data Provider documentation](/discover/portal/-/knowledge*base/7-1/data-providers).

    ![Figure 3: ](../../../images/forms-autofill-data-provider.png)

2.  Create a form with these fields:

    **Text::** Use the Label *Region*.
    **Select from List:** Label it *Country*, and choose *From Autofill* under
    Create List.

    ![Figure 4: ](../../../images/forms-autofill-input-output-fields.png)

3.  Configure the Autofill rule.

    **Condition:** If *Region* **Is not Empty**
    **Action:** Do **Autofill** From Data Provider *restcountries*, Data
    Provider's Input: region---*Region*, Data Provider's Output:
    name---*Country*.

    ![Figure 5: ](../../../images/forms-autofill-rule.png)

Once you're done, publish the form and try it out, by entering a valid region
into the Region field, and observing that the options in the Select from List
Field are filtered based on the Region. The [restcountries.eu]() service has
these regions you can use: Africa, Americas, Asia, Europe, Oceania, and Polar.

![Figure 6: Filter countries by region of the world.](../../../images/forms-autofill-region.gif)

Autofill rules combine the power of data providers and form rules.
