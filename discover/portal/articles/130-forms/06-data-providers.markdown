# Data Providers [](id=data-providers)

Select from List fields can hold a lot of options. There are around 200
countries on Earth, for example. If you have unoccupied unpaid interns you could
ask them to type each country into the Select from List field manually, or you
could auto-populate your select fields using a REST web service. This saves you
(or your interns) the trouble of typing all those options, and you can rely on
someone else (hopefully a trustworthy expert) to keep the data updated.

When setting up a data provider, you're accessing a 
[REST web service](https://en.wikipedia.org/wiki/Representational_state_transfer). 
Use the 
[JSON web services registered in Liferay](/develop/tutorials/-/knowledge_base/7-0/registering-json-web-services),
or any other REST web service you can access. To find a list of the ready-to-use
registered JSON web services in @product@, navigate to
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws) (assuming
you're running a local server). Browse the available Liferay services. Many
times, the services useful to you in the Forms application get a list of
something. Find the `get-countries` JSON web service (there are two---use either
one) and click on it, then click *Invoke*. The *Result* tab shows a list of
countries using JSON syntax, like this:

    [
      {
        "a2": "AF",
        "a3": "AFG",
        "countryId": "20",
        "idd": "093",
        "mvccVersion": "0",
        "name": "afghanistan",
        "nameCurrentValue": "Afghanistan",
        "number": "4"
      },
        ...

That's the record for the country Afghanistan. As you can see in the *URL* tab,
the URL you entered into the data provider form is the same as the one generated
for accessing the `get-countries` JSON web service. Find the URL for any
registered JSON web service using this same procedure. 

Note the field you want Users to select. With this service, it's most likely
`nameCurrentValue`, because it contains the full, properly capitalized name of
the country.

## Adding a Basic Data Provider [](id=adding-a-basic-data-provider)

To add a *Countries of the World* Data Provider for use in your Forms,

1.  Go to the Forms application.

2.  Click the Options button (![Options](../../images/icon-options.png)) and
    click *Data Providers*.

3.  Click the Add button (![Add](../../images/icon-add.png)).

    The REST Data Provider form loads.

4.  Fill out the Name and Description fields.

    Name: `Countries of the World`

5.  Enter the URL and authentication tokens for the REST service. For the
    `get-countries` service:

    URL: `http://localhost:8080/api/jsonws/country/get-countries/`

    User Name: `test@liferay.com`

    Password:`test`

6.  In the Outputs fields, specify which field from the REST service populates
    your select list. 

    Label: `Country Name`

    Path: `nameCurrentValue` 

    Type: `List`

7.  Save the Data Provider.

![Figure 1: Set up a simple data provider in no time.](../../images/forms-simple-data-provider.png)


<!--    provides the full, properly capitalized country name, and that’s what you want users of the form to see. We are using JsonPath to navigate the returning xml structure. JsonPath uses special notation to represent nodes and their connections to adjacent nodes in a path. There are two styles of notation, namely dot and bracket.
You can check more detail on this topic on the links showing in your screen:
http://jsonpath.com/

http://goessner.net/articles/JsonPath/

https://www.pluralsight.com/blog/tutorials/introduction-to-jsonpath

I'm using the same service but for outputing the EU countries only. The Path in the outputs needs to be Json Path. Use this website http://jsonpath.com/? to test the output and understand the expression you need to have.
I'm currently recording the videos for our training and the chapter on data providers looks like the text below.

Hope this helps!
RL
-->

## Using a Data Provider in a Select Field [](id=using-a-data-provider-in-a-select-field)

Once the Data Provider is configured, use it to populate a Select from List field:

1.  Go to the Form Builder (add a new form or edit an existing one)

2.  Drag a Select from List field onto the form.

3.  In the Create List section, choose *From Data Provider*.

4.  Choose the Data Provider and its Output Parameter:

    Choose a Data Provider: `Countries of the World`

    Choose an Output Parameter: `Country Name`

5.  Publish the form and test it. 

![Figure 2: Form users select an option form the list populated by the Data Provider.](../../images/forms-select-data-provider.png)

Your Data Provider is now being used to populate a select field. However, this
form should be submitted by Guest users, who don't currently have permission to
see the list of results from the data provider. Arrgh! Now what? 

## Granting Data Provider Permissions [](id=granting-data-provider-permissions)

To configure the data provider's permissions, go to the Forms application (*Site
Administration* &rarr; *Content* &rarr; *Forms*). Open the Options menu
(![Options](../../images/icon-options.png)) and select *Data Providers*. For
the data provider you want to configure, click the Actions button
(![Actions](../../images/icon-actions.png)), then *Permissions*. 

Configure the permissions you need. If Guests are to fill out the form, they
need the *View* permission, or else they won't be able to see the options
provided by the data provider. Once you grant permissions, click *Save*.

## Data Provider Configuration [](id=data-provider-configuration)

The above instructions for adding a basic Data Provider are a good start, but
there are more options.

<!-- NOT WORKING, LEAVE OUT UNTIL I CONFIRM IT'S NON-FUNCTIONAL *Support filtering by keyword.*
: Filter the REST service call's results by a valid parameter from the REST
service.-->

**Cache data on the first request.**
: If the data is cached, a second load of the select list field is much faster,
since a second call to the REST service provider is unnecessary.

**Timeout**
: The time (in ms) to allow the REST service call to process before aborting the
request, if a response is not returned.

**Inputs**
: Configure parameters from the REST service to use to filter the REST service's
response (displayed in the Outputs parameter configured below). Specify the
Label, Parameter, and Type (Text or Number), and Choose whether the input is
required to use the Data Provider. You can add multiple Inputs. These are used
in Form Rules. A User enters input into one field, and their input is sent to
the REST service. The REST service's response data is filtered by the input.

**Outputs**
: The Parameter to display in Select from List fields. You can add multiple
Outputs. Outputs can be filtered by inputs (see above) but can also be displayed
without configuring input filtering. Specify the Label, Path, and Type (Text,
Number, or List). The Path field is specified in JsonPath syntax.

![Figure 3: Set up Data Providers to display data retrieved from a REST service.](../../images/forms-data-provider-configuration.png)


