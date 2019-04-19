---
header-id: customizing-rating-value-transformation
---

# Customizing Rating Value Transformation

[TOC levels=1-4]

To customize rating value transformation, you must create an OSGi component that 
implements 
[`RatingsDataTransformer`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/transformer/RatingsDataTransformer.html). 
The steps here show you how. For a detailed explanation of these steps and 
rating value transformation, see 
[Rating Value Transformation](/docs/7-2/frameworks/-/knowledge_base/frameworks/social-api#rating-value-transformation). 

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
    transformation: 

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

    This example irreversibly transforms the rating type from likes to stars, 
    resetting the value to `0`. The `if` statement uses the `fromRatingsType` 
    and `toRatingsType` values to specify that the transformation only occurs 
    when going from likes to stars. The transformation is performed via 
    `RatingsEntry` and its `-LocalServiceUtil`. After getting a 
    [`RatingsEntry`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/model/RatingsEntry.html) 
    object, its `setScore` method sets the rating score to `0`. The 
    [`RatingsEntryLocalServiceUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/service/RatingsEntryLocalServiceUtil.html) 
    method `updateRatingsEntry` then updates the `RatingsEntry` in the database. 

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

## Related Topics

[Rating Value Transformation](/docs/7-2/frameworks/-/knowledge_base/frameworks/social-api#rating-value-transformation)

[Implementing Rating Type Selection](/docs/7-2/frameworks/-/knowledge_base/frameworks/implementing-rating-type-selection)

[Rating Assets](/docs/7-2/frameworks/-/knowledge_base/frameworks/rating-assets)
