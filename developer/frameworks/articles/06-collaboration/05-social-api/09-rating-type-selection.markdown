---
header-id: implementing-rating-type-selection
---

# Implementing Rating Type Selection

[TOC levels=1-4]

For administrators to change your app's rating type (e.g. likes, stars, thumbs), 
you must implement rating type selection. The steps here show you how. For a 
detailed explanation of these steps and rating type selection, see 
[Rating Type Selection](/docs/7-2/frameworks/-/knowledge_base/frameworks/social-api#rating-type-selection). 

1.  Implement the 
    [`PortletRatingsDefinition`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/definition/PortletRatingsDefinition.html) 
    interface, registering the class as an OSGi component. In the `@Component` 
    annotation, set the `model.class.name` property to the fully qualified name 
    of the class that will use this rating definition. This example rating 
    definition is for a blog entry, so the `model.class.name` property is set to 
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

[Rating Type Selection](/docs/7-2/frameworks/-/knowledge_base/frameworks/social-api#rating-type-selection)

[Rating Assets](/docs/7-2/frameworks/-/knowledge_base/frameworks/rating-assets)

[Customizing Rating Value Transformation](/docs/7-2/frameworks/-/knowledge_base/frameworks/customizing-rating-value-transformation)
