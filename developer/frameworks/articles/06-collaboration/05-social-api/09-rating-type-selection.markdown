---
header-id: implementing-rating-type-selection
---

# Implementing Rating Type Selection

For administrators to change your app's rating type (e.g. likes, stars, thumbs), 
you must implement rating type selection. The steps here show you how. For a 
detailed explanation of these steps and rating type selection, see 
[Rating Type Selection](liferay.com). 

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

2.  Implement the `PortletRatingsDefinition` methods `getDefaultRatingsType` and 
    `getPortletId` to return the entity's default rating type and the portlet ID 
    of the main portlet that uses the entity, respectively. In this example, the 
    rating type is thumbs and the portlet ID is for the Blogs portlet: 

        @Override
        public RatingsType getDefaultRatingsType() {
            return RatingsType.THUMBS;
        }

        @Override
        public String getPortletId() {
            return PortletKeys.BLOGS;
        }

## Related Topics

[Rating Type Selection](liferay.com)

[Rating Assets](/develop/tutorials/-/knowledge_base/7-2/rating-assets)

[Customizing Rating Value Transformation](liferay.com)
