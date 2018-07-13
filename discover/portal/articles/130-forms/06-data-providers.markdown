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
[JSON web services registered in Liferay](/develop/tutorials/-/knowledge_base/7-1/registering-json-web-services),
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

The above instructions cover adding a basic Data Provider. Knowing more about
each field in the Data Provider setup form opens up more possibilities.

**URL**
: The URL of an internal or external REST service endpoint. Consider the REST
service at https://restcountries.eu/, which contains a REST API endpoint to find
countries by `region`:

    https://restcountries.eu/rest/v2/region/{region}

Data Provider URLs can take two parameter types: path parameters and
query parameters. 

Path parameters are part of the URL calling the REST web service, and are added
using the pattern `https://service-url.com/service/{path_parameter_name}`: 

In the `restcountries.eu` service, using the `region` endpoint, the path
parameter is `{region}`. Path parameters are mandatory parts of the URL, so make
sure you specify an Input (see below) with a _Parameter_ field value matching
the path parameter from the URL. 

Query parameters are complementary parts of the URL that filter the output of
the service call, following the pattern
`?query_parameter=query_parameter_value`:

    https://restcountries.eu/rest/v2/all?fields=capital

Unlike path parameters, query parameters are optional.

**User Name and Password** 
: Credentials used to authenticate to the REST Web Service, if necessary.

**Cache data on the first request.**
: If the data is cached, a second load of the select list field is much faster,
since a second call to the REST service provider is unnecessary.

**Timeout**
: The time (in ms) to allow the REST service call to process before aborting the
request, if a response is not returned.

**Inputs**
: Configure path or query parameters from the REST service to filter the REST
service's response. Specify the Label, Parameter, and Type (Text or Number), and
Choose whether the input is required to use the Data Provider. You can add
multiple Inputs. To provide a way for users to specify the input value, use an
[_Autofill_ Form Rule](/discover/portal/-/knowledge_base/7-1/action-autofill).
A User enters input into one field, and their input is sent to the REST service.
The REST service's response data is filtered by the input parameter.

**Outputs**
: The Parameter to display in Select from List or Text fields with autocomplete
enabled. You can add multiple Outputs. Outputs can be filtered by inputs (see
above) but can also be displayed without configuring input filtering. Specify
the Label, Path, and Type (Text, Number, or List). The Path field is specified
in JsonPath syntax. Using the `restcountries.eu` service, specify the `name`
field as am Output by entering enter `$..name` in the Path field.

![Figure 3: Set up Data Providers to display data retrieved from a REST service.](../../images/forms-data-provider-configuration.png)
