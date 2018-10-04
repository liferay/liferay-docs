# Liferay Audience Targeting Rules [](id=liferay-audience-targeting-rules)

Rules enable Audience Targeting administrators to define how users are
classified into the user segments they create.

A rule evaluates if the current user matches or not one or several
conditions. Rules can be configured and combined differently for each user
segment to create a specific classification algorithm. A user must match all
rules in a user segment to be classified into it.

## Behavior Rules [](id=behavior-rules)

These rules classify users based on their navigation behavior.

These rules don't work properly if content tracking or page tracking is not
enabled. For Site level configuration,

1.  Go to *Site Administration* &rarr; *Configuration*

2.  Select *Site Settings* and then the *Advanced* tab.

3.  Find the *Audience Targeting Analytics* heading.

For portal level configuration, 

1. Go to *Control Panel* &rarr; *Configuration* &rarr;

2. Select *Instance Settings* and the *Audience Targeting Analytics*.

### Content Visited [](id=content-visited)

Evaluates if the user has visited the selected content.

### Page Visited [](id=page-visited)

Evaluates if the user has visited the selected page.

### Previously Visited Site [](id=previously-visited-site)

Evaluates if the user has visited a specific Site before visiting the current
Site. The Site's URL can be evaluated based on whether it contains or starts
with defined text.

For example, if you want to segment users based on whether they navigated to
your Site from Twitter, you could define a Twitter URL for this rule.

### Score Points [](id=score-points)

Evaluates if the user has visited any page or content categorized under this
user segment a number of times equals or higher than the configured threshold.

## Social Rules [](id=social-rules)

Social rules classify users based on their profiles in social networks (e.g.,
Facebook).

These rules do not work properly if login through the specific social network
(Single Sign On) is not enabled and properly configured. You can learn more
about using Single Sign On in the [Facebook Connect
SSO](/discover/portal/-/knowledge_base/7-1/facebook-connect-single-sign-on-authentication).

### Facebook Age [](id=facebook-age)

Evaluates if the age in the user's Facebook profile is in the configured range.

### Facebook City [](id=facebook-city)

Evaluates if the city in the user's Facebook profile matches the configured
value.

### Facebook Education [](id=facebook-education)

Evaluates if the education in the user's Facebook profile matches the configured
values.

### Facebook Friends [](id=facebook-friends)

Evaluates if the user has more or less friends in Facebook than the selected
value.

### Facebook Gender [](id=facebook-gender)

Evaluates if the gender in the user's Facebook profile matches the selected
value.

### Facebook Page Like [](id=facebook-page-like)

Evaluates if the user likes the configured Facebook page.

## User Attributes Rules [](id=user-attributes-rules)

These rules classify users based on the attributes.

These rules do not work properly if the corresponding attributes from the user
profile are not available. Follow the given instructions for each rule in such
cases.

### Age [](id=age)

Evaluates if the age in the user's profile is in the configured range.

### Custom Field [](id=custom-field)

Evaluates if the value set for the selected custom field matches the value from
the user's profile.

### Gender [](id=gender)

Evaluates if the gender in the user's profile matches the selected value.

### Organization Member [](id=organization-member)

Evaluates if the user is member of the selected Organization.

### Organization Role [](id=organization-role)

Evaluates if the user has the selected Role assigned in the selected
organization.

### Regular Role [](id=regular-role)

Evaluates if the user has the selected regular Role assigned.

### Site Member [](id=site-member)

Evaluates if the user is a member of the selected Site.

### Site Role [](id=site-role)

Evaluates if the user has the selected Role assigned in the selected Site.

### User Group Member [](id=user-group-member)

Evaluates if the user is member of the selected User Group.

### User Signed In [](id=user-signed-in)

Evaluates if the user is signed in.

## Session Attributes Rules [](id=session-attributes-rules)

These rules classify users based on their session attributes. Session attributes
come from Java
[`HttpSession`](https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpSession.html)
data. 

These rules do not work properly if the corresponding session attributes are
retrieved through any tools that are not currently available or properly
configured. Follow the given instructions for such rules in such cases.

### Access Time [](id=access-time)

Evaluates if the user visited at a time that is within the configured
range. Notice that the reference time is that of the server.

### Browser [](id=browser)

Evaluates if the user visited with the selected browser.

### Device [](id=device)

Evaluates if the user visited with the selected device.

This rule is based on the existing Device Families. To manage Device Families,
go to *Site Administration* &rarr; *Configuration* &rarr; *Mobile Device
Families*.

### IP Range [](id=ip-range)

Evaluates if the user visited with an IP with the configured range.

### Language [](id=language)

Evaluates if the user visited with the selected language.

### Last Login Date [](id=last-login-date)

Evaluates if the last login date of the current user was after, before, or
between the selected dates.

### Location [](id=location)

Evaluates if the user visited from a country and region that match the
configured values.

### Operating System [](id=operating-system)

Evaluates if the user visited with the selected Operating System.

### Sign Up Date [](id=sign-up-date)

Evaluates if the Sign Up date of the current user was after, before, or between
the selected dates.

Liferay's Audience Targeting app can define custom user segments, target
specific content to different user segments, and create campaigns for different
user segments. You can generate reports to measure the effectiveness of your
campaigns. A simulator previews how your Site appears to users belonging to
different user segments. You can create user segments by applying various rules
that reference session attributes, profile attributes, behavior, and other
information, such as information from social networks. If you need more,
developers can create additional rules and rule types.
