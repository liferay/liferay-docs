---
header-id: evaluating-a-rule
---

# Evaluating a Rule

[TOC levels=1-4]

After the administrator has successfully configured and saved your custom rule
to his or her user segment, your rule needs to fulfill its purpose: to evaluate
the preset weather value compared to a user's weather value visiting the site.
If the user's value matches the preset value (along with the segment's other
rules), that user is added to the user segment. 

1.  You must implement the `evaluate(...)` rule to begin the evaluation
    process. This method is part of the user segmentation lifecycle. When a page
    is loaded, Liferay invokes the `evaluate` method of the rule to determine
    if the current user belongs to the user segment. For the weather rule, add
    this `evaluate` method:

    ```java
    @Override
    public boolean evaluate(
            HttpServletRequest request, RuleInstance ruleInstance,
            AnonymousUser anonymousUser)
        throws Exception {

        String userWeather = getUserWeather(anonymousUser);

        String weather = ruleInstance.getTypeSettings();

        if (Validator.equals(userWeather, weather)) {
                return true;
        }

        return false;
    }
    ```

    You acquire the user's weather by calling the `getUserWeather` method, which
    you'll define later. Then you get the preset weather value by accessing
    the rule instance's `typeSettings` parameter. Finally, you compare the two
    values. If they match, return `true`; otherwise return `false`. Remember
    that users are only added to User Segments when all the Rules in the User
    Segment return true.

2.  Next, you need to retrieve the user's weather. As you learned earlier,
    you must access the user's location first. Add the logic below to do
    this.

    ```java
    protected String getCityFromUserProfile(long contactId, long companyId)
        throws PortalException, SystemException {
 
        List<Address> addresses = AddressLocalServiceUtil.getAddresses(companyId, Contact.class.getName(), contactId);
 
        if (addresses.isEmpty()) {
            return null;
        }
 
        Address address = addresses.get(0);

        return address.getCity() + StringPool.COMMA + address.getCountry().getA2();
    }
    ```

    This method retrieves the location by accessing the user's profile
    information. You could also have used a geo-location service to find this by
    the user's IP address. Once you have the user's location, you can find the
    current weather for that location.

3.  Add the following method to retrieve a user's weather forecast.

    ```java
    protected String getUserWeather(AnonymousUser anonymousUser)
        throws PortalException, SystemException {

        User user = anonymousUser.getUser();

        String city = getCityFromUserProfile(user.getContactId(), user.getCompanyId());

        Http.Options options = new Http.Options();

        String location = HttpUtil.addParameter(API_URL, "q", city);
        location = HttpUtil.addParameter(location, "format", "json");

        options.setLocation(location);

        int weatherCode = 0;

        try {
            String text = HttpUtil.URLtoString(options);

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(text);

            weatherCode = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
        }
        catch (Exception e) {
            _log.error(e);
        }

        return getWeatherFromCode(weatherCode);
    }

    private static Log _log = LogFactoryUtil.getLog(WeatherRule.class);
    ```

    This method calls the `getCityFromUserProfile` method to acquire the user's
    location. Then it retrieves the weather code for that location from a
    weather service. 

4.  Set the `API_URL` field to the Open Weather Map's API URL:

    ```java
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    ```

    For the weather rule, you can access Open Weather Map's APIs to retrieve the
    weather code.
    
5.  The last thing is to convert the weather code to a string you can evaluate
    (e.g., `sunny`). Add the following method to convert Open Weather Map's
    weather codes:

    ```java
    protected String getWeatherFromCode(int code) {
        if (code == 800 || code == 801) {
            return "sunny";
        }
        else if (code > 801 && code < 805) {
            return "clouds";
        }
        else if (code >= 600 && code < 622) {
            return "snow";
        }
        else if (code >= 500 && code < 532) {
            return "rain";
        }
 
        return null;
    }
    ```

    All possible weather codes are
    [here](http://openweathermap.org/weather-conditions).

Excellent! You've implemented the `evaluate` method and added the necessary
logic in your `-Rule` class to acquire a user's local weather. The weather
rule's behavior is defined and complete. The last thing you need to do is create
a JSP template.
