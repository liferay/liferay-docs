---
header-id: data-providers
---

# Data Providers

[TOC levels=1-4]

Select from List fields can hold a lot of options. There are around 200
countries on Earth, for example. If you have unoccupied unpaid interns you could
ask them to type each country into the Select from List field manually, or you
could auto-populate your select fields using a REST web service. This saves you
(or your interns) the trouble of typing all those options, and you can rely on
someone else (hopefully a trustworthy expert) to keep the data updated.

When setting up a data provider, you're accessing a 
[REST web service](https://en.wikipedia.org/wiki/Representational_state_transfer). 
Use the JSON web services registered in @product@, or any other REST web service
you can access. To find a list of the registered JSON web services in @product@,
navigate to
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws)
(assuming you're running a local server). Browse the available Liferay services.
Many times, the services useful to you in the Forms application get a list of
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

That's the record for the country Afghanistan. As you can see in the *URL
Example* tab, the URL you entered into the data provider form is the same as the
one generated for accessing the `get-countries` JSON web service. Find the URL
for any registered JSON web service using this same procedure. 

Note the field you want Users to select. With this service, it's most likely
`nameCurrentValue`, because it contains the full, properly capitalized name of
the country.

| *Enabling Access to Data on the Local Network:* By default, you cannot configure
| data providers to use URLs on the local network. This is a good default for
| security in a production environment, but makes testing more difficult. To
| enable local network access from data providers, got to Control Panel &rarr;
| Configuration &rarr; System Settings &rarr; Data Providers (under Content &
| Data), and enable _Access Local Network_. You'll need to configure this if you
| want to follow the basic example in the next section.

## Adding a Basic Data Provider

To add a *Countries of the World* Data Provider for use in your Forms,

1.  Go to the Forms application.

2.  Click the *Data Providers* tab.

3.  Click the Add button (![Add](../../images/icon-add.png)).

    The REST Data Provider form loads.

4.  Fill out the Name and Description fields.

    Name: `Countries of the World`

5.  Enter the URL and authentication tokens for the REST service. For the
    `get-countries` service:

    URL: `http://localhost:8080/api/jsonws/country/get-countries/`

    User Name: `adminuser@liferay.com`

    Password:`adminuserpass`

6.  In the Outputs fields, specify which field from the REST service populates
    your select list. 

    Label: `Country Name`

    Path: `$..nameCurrentValue` 

    Type: `List`

7.  Save the Data Provider.

![Figure 1: Set up a simple data provider in no time.](../../images/forms-simple-data-provider.png)

What's that `$..` before `nameCurrentValue`? It's JsonPath syntax to navigate
the JSON data structure and specify the path to the output. Learn more about
JsonPath
[here](https://github.com/json-path/JsonPath/blob/master/README.md) and
[here](http://goessner.net/articles/JsonPath/).

## Using a Data Provider in a Select Field

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

## Granting Data Provider Permissions

To configure the data provider's permissions, go to the Forms application (*Site
Administration* &rarr; *Content & Data* &rarr; *Forms*). Open the Data Providers
tab. For the data provider you want to configure, click the Actions button
(![Actions](../../images/icon-actions.png)), then *Permissions*. 

Configure the permissions you need. If Guests are to fill out the form, they
need the *View* permission, or else they won't be able to see the options
provided by the data provider. Once you grant permissions, click *Save*.

## Data Provider Configuration

The above instructions cover adding a basic Data Provider. Knowing more about
each field in the Data Provider setup form opens up more possibilities.

**URL**
: The URL of an internal or external REST service endpoint. Consider the REST
service at https://restcountries.eu/, which contains a REST API endpoint to find
countries by `region`:

    `https://restcountries.eu/rest/v2/region/{region}`

Data Provider URLs can take two parameter types: path parameters and
query parameters. 

Path parameters are part of the URL calling the REST web service, and are added
using the pattern `https://service-url.com/service/{path_parameter_name}`: 

The `restcountries.eu` service's `region` endpoint's path parameter is
`{region}`. Path parameters are mandatory parts of the URL, so make sure you
specify an Input (see below) with a _Parameter_ field value matching the path
parameter from the URL. 

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
choose whether the input is required to use the Data Provider. You can add
multiple Inputs. To provide a way for users to specify the input value, use an
[_Autofill_ Form Rule](/docs/7-2/user/-/knowledge_base/u/action-autofill).
A User enters input into one field, and their input is sent to the REST service.
The REST service's response data is filtered by the input parameter.

**Outputs**
: The Parameter to display in Select from List or Text fields with autocomplete
enabled. You can add multiple Outputs. Outputs can be filtered by inputs (see
above) but can also be displayed without configuring input filtering. Specify
the Label, Path, and Type (Text, Number, or List). The Path field is specified
in
[JsonPath syntax](https://github.com/json-path/JsonPath/blob/master/README.md), so it
must always start with a `$`. The type of data returned by the Path must match
the type you choose in the Type field. Using the `restcountries.eu` service,
specify the `name` field as an Output by entering enter `$..name` in the Path
field.

If you have a more complex JsonPath expression to construct (for example, you
need the names of all countries with a population over 100
million---`$..[?(@.population>100000000)].name` with the `restcountries.eu`
service), consider using an online JsonPath evaluator, like
[this one](http://jsonpath.herokuapp.com/) or
[this one](https://jsonpath.com/).

| **Hint:** To display one value to the user, but persist another in the database,
| enter both into the Paths field, separated by a semicolon:
| 
|      `$..name;$..numericCode`
| 
| If this is used with the `restcountries.eu` data provider, the name of the
| country is displayed to the User, while the numeric country code is stored in
| the database.

![Figure 3: Set up Data Providers to display data retrieved from a REST service.](../../images/forms-data-provider-configuration.png)

## Troubleshooting Data Provider Errors

To uncover errors arising from Data Provider failures, 
[configure log levels](/docs/7-2/user/-/knowledge_base/u/server-administration) 
for these services:

**Category:**
`com.liferay.dynamic.data.mapping.data.provider.internal.DDMDataProviderInvokerImpl`
*Level:* WARN 

**Category:**
`com.liferay.dynamic.data.mapping.form.field.type.internal.DDMFormFieldOptionsFactoryImpl`
*Level:* DEBUG

With Data Providers, the world's (RESTful) data is at your disposal to use with
the Forms application.
