# Implementing Ratings Type Selection and Value Transformation [](id=implementing-ratings-type-selection-and-value-transformation)

@product@ has three different mechanisms for rating content: 

-   Likes
-   Stars
-   Thumbs (up/down)

Prior to 7.0, there was no way for portal or site administrators to select a 
rating type--it was hard-coded in each app. In 7.0 and later, admins can select 
the rating type for an app's entities via the Control Panel and Site 
Administration: 

-   **Portal admins:** can set the default rating type for the portal
-   **Site admins:** can override the default rating type for their site

All Liferay apps leverage this feature. Your apps can too: this tutorial shows
you how. 

## Specifying an Entity's Rating Type [](id=specifying-an-entitys-ratings-type)

A custom app that uses ratings must define its rating type in an OSGi component 
that implements the `PortletRatingsDefinition` interface. This class declares 
the usage of ratings (specifying the portlet and the entity) and the default 
rating type (that can be overridden by portal and site admins). 

Follow these steps to implement `PortletRatingsDefinition` to define your app's 
rating type: 

1.  Register the class as an OSGi component and set the `model.class.name` 
    property to the fully qualified class name of the class that will use this 
    rating definition. For example, this example rating definition is for a blog 
    entry, so the `model.class.name` property is set to 
    `com.liferay.portlet.blogs.model.BlogsEntry`:

        @Component(
            property = {
                "model.class.name=com.liferay.portlet.blogs.model.BlogsEntry"
            }
        )
        public class BlogsPortletRatingsDefinition implements PortletRatingsDefinition {...

2.  The `PortletRatingsDefinition` interface has two methods that you must 
    implement: 

    -   `getDefaultRatingsType`: returns the entity's default rating type, which 
        portal and site admins can override. You can do this via the 
        [`RatingsType` enum](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/RatingsType.html), 
        which lets you use `LIKE`, `STARS`, or `THUMBS` to set the rating type: 

            @Override
            public RatingsType getDefaultRatingsType() {
                return RatingsType.THUMBS;
            }

    -   `getPortletId`: returns the portlet ID of the main portlet that uses the 
        entity. You can do this via the 
        [`PortletKeys` enum](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/PortletKeys.html), 
        which defines many constants that correspond to the portlet IDs of the 
        built-in portlets. This example specifies the Blogs portlet: 

            @Override
            public String getPortletId() {
                return PortletKeys.BLOGS;
            }

Next, you'll learn how to transform values between rating types. 

## Transforming Ratings Values Between Rating Types [](id=transforming-ratings-values-between-ratings-types)

The rating values are stored in the database as normalized values. This permits 
switching among different rating types without modifying the underlying data. 
When administrators change an entity's rating type, its best match is computed. 
Here's a list of the default transformations between rating types: 

1.  When changing from stars to: 

    - **Like:** A value of 3, 4, or 5 stars is considered a like; a value of 1 
    or 2 stars is omitted. 
    - **Thumbs up/down:** A value of 3, 4, or 5 stars is considered a thumbs up; 
    a value of 1 or 2 stars is considered a thumbs down.

2.  When changing from thumbs up/down to: 

    - **Like:** A like is considered a thumbs up.
    - **Stars:** A thumbs down is considered 1 star; a thumbs up is considered 5 
    stars. 

3.  When changing from like to: 

	- **Stars:** A like is considered 5 stars.
	- **Thumbs up/down:** A like is considered a thumbs up.

There may be some cases, however, where you want to apply different criteria to 
determine the new rating values. A mechanism exists to let you do this, but it 
modifies the stored rating values. To define such transformations, create an 
OSGi component that implements the 
[`RatingsDataTransformer` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/transformer/RatingsDataTransformer.html). 

+$$$

**Note:** The portal doesn't provide a default `RatingsDataTransformer` 
implementation. Unless you provide such an implementation, the stored rating 
values always remain the same while the portal interprets existing values for 
the selected rating type. 

$$$

When implementing `RatingsDataTransformer`, implement the `transformRatingsData` 
method to transform the data. This method's arguments include the `RatingsType` 
variables `fromRatingsType` and `toRatingsType`, which contain the rating type 
to transform from and to, respectively. These values let you write your custom 
transformation's logic. You can write this logic by implementing the interface 
`ActionableDynamicQuery.PerformActionMethod` as an anonymous inner class in the 
`transformRatingsData` method, implementing the `performAction` method with your 
transformation's logic. 

For example, follow these steps to implement a `RatingsDataTransformer`: 

1.  Create an OSGi component class that implements `RatingsDataTransformer`: 

        @Component
        public class DummyRatingsDataTransformer implements RatingsDataTransformer {...

2.  In this class, implement the `transformRatingsData` method. Note that it 
    contains the `RatingsType` variables `fromRatingsType` and `toRatingsType`: 

        @Override
        public ActionableDynamicQuery.PerformActionMethod transformRatingsData(
                final RatingsType fromRatingsType, final RatingsType toRatingsType)
            throws PortalException {

        }

3.  In the `transformRatingsData` method, implement the interface 
    `ActionableDynamicQuery.PerformActionMethod` as an anonymous inner class: 

        return new ActionableDynamicQuery.PerformActionMethod() {

        };

4.  In the anonymous `ActionableDynamicQuery.PerformActionMethod` 
    implementation, implement the `performAction` method to perform your 
    transformation. This example irreversibly transforms the rating type from 
    like to stars, resetting the value to `0`. The `if` statement uses the 
    `fromRatingsType` and `toRatingsType` values to specify that the 
    transformation only occurs when going from likes to stars. The 
    transformation is performed via `RatingsEntry` and its `-LocalServiceUtil`. 
    After getting a 
    [`RatingsEntry` object](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/model/RatingsEntry.html), 
    its `setScore` method sets the rating score to `0`. 
    [The `RatingsEntryLocalServiceUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/service/RatingsEntryLocalServiceUtil.html) 
    method `updateRatingsEntry` then updates the `RatingsEntry` in the database: 

        @Override
        public void performAction(Object object)
            throws PortalException {

            if (fromRatingsType.getValue().equals(RatingsType.LIKE) &&
                toRatingsType.getValue().equals(RatingsType.STARS)) {

                RatingsEntry ratingsEntry = (RatingsEntry) object;

                ratingsEntry.setScore(0);

                RatingsEntryLocalServiceUtil.updateRatingsEntry(
                    ratingsEntry);
            }
        }

Here's the complete class for this example: 

    @Component
    public class DummyRatingsDataTransformer implements RatingsDataTransformer {
        @Override
        public ActionableDynamicQuery.PerformActionMethod transformRatingsData(
                final RatingsType fromRatingsType, final RatingsType toRatingsType)
            throws PortalException {

            return new ActionableDynamicQuery.PerformActionMethod() {

                @Override
                public void performAction(Object object)
                    throws PortalException {

                    if (fromRatingsType.getValue().equals(RatingsType.LIKE) &&
                        toRatingsType.getValue().equals(RatingsType.STARS)) {

                        RatingsEntry ratingsEntry = (RatingsEntry) object;

                        ratingsEntry.setScore(0);

                        RatingsEntryLocalServiceUtil.updateRatingsEntry(
                            ratingsEntry);
                    }
                }
            };
        }
    
    }

Once you've implemented ratings type selection and value type transformation for 
your app's entities, you can configure the default ratings type values through 
the Control Panel by going to *Configuration* &rarr; *Instance Settings*, and 
selecting the *Social* tab. To override the default values for a site, go to 
Site Administration &rarr; *Configuration* &rarr; *Site Settings*, and select 
the *Social* tab. 

Nice work! Now you know how to set an entity's rating type. You also know how to 
implement a rating data transformer. We salute you with a thumbs up! 

## Related Topics [](id=related-topics)

[Asset Framework](/develop/tutorials/-/knowledge_base/7-1/asset-framework)

[Rating Assets](/develop/tutorials/-/knowledge_base/7-1/rating-assets)
