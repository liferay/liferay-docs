# Instance Settings: Remaining Sections

This article covers the virtual instance settings not included in the 
[previous article](/discover/portal/-/knowledge_base/7-1/instance-settings).

Start with the Identification section.

## Identification [](id=identification)

The Identification section has several links for addresses, phone numbers and
other contact information for the organization that owns the instance.
Developers can query for this information in their applications.

## Social [](id=social)

The Social section configures Ratings and Mentions throughout the instance. For
each of Liferay's core portlets that has ratings enabled, choose whether to use
a simple *Like*, *Thumbs*, or *Stars* for rating the content. You can also
choose whether Users can mention (*@Username*) other Users, and if so, whether
all Users have the capability or whether you want to further refine the mentions
configuration.

![Figure 2: You can configure the Mentions capability throughout a Liferay instance.](../../../images/instance-settings-mentions.png)

## Miscellaneous: Display Settings [](id=miscellaneous-display-settings)

Set the default instance language and time zone. You can also set up an
instance-wide logo which appears in the top left corners of site pages. 

When configuring a new logo, be careful to choose an image file that fits the
space. Large images might overlap with the navigation. 

<!-- Should we provide a range of recommended parameters for custom logos?  -->

## Miscellaneous: Integrations

In the Miscellaneous tab, below the Display Settings form, there are
three additional configuration forms for these integrations:

*Analytics*: Enter an analytics system, or remove one of the two pre-configured
options (`google` and `piwik`). Activate these systems here, and configure them
at the 
[site level](/discover/portal/-/knowledge_base/7-1/advanced-site-settings#analytics).

*Maps*: Choose OpenStreetMap or Google Maps as the maps API provider for
geolocalized asset display.

*Google Apps*: Specify the Google Apps API Key and Google Client ID to integrate
Google Apps. <!-- Is this now GSuite?-->

