# Selecting the ratings type for portlet entities

Liferay has three different mechanism to rate content in the portal: Stars, Thumbs up/down and Likes. Prior to 7.0 the ratings type was hardcoded in the source code and there was no way for administrators to select a ratings type.Since Liferay 7.0 portal admins and site admins can select the ratings type for the portlet entities through a user interface from Control Panel and Site Administration. Portal Admins can select the default values for the ratings type while site admins can override those values for their sites. All Liferay portlets are taking advantage of this feature but third party developed portlets can also benefit of this.

Third party portlets using ratings will need to define the ratings type used in the portlet through a OSGI component implementing `PortletRatingsDefinition` interface. This class declares the usage of ratings (specifying the portlet and the entity) and the default ratings type (that can be overriden by the portal admin and site admins). Developers can include their portlet portlet entites in this section by defining the ratings type for their entities using OSGI modules.

The ratings values are stored in the database as normalized values to be able to easily switch among different ratings types. When the administrators change the ratings type for an entity, Liferay will try its best to match the previous ratings values with the new ratings type. For example, when changing from Thumbs up/down to Likes we will consider all the Thumbs up as if they were Likes and we will omit the Thumbs down. Or, when changing from Stars to Thumbs up/down we will consider that ratings with 3, 4 or 5 stars are a Thumbs up and ratings with 1 or 2 stars are Thumbs down.

However, there are some cases where this may not be enough or we want ot apply different or complex criteria to determine the new ratings values. Liferay provides a mechanism to allow developers transform the ratings values as needed when changing the ratings type. Keep in mind that this will change the stored ratings values so it's not an interpretation of the data. Third party developers can define their own transformations by creating a OSGI component implementing `RatingsDataTransformer` interface. Only the highest OSGI ranking implementation is used.

Liferay by default doesn't provide any RatingsDataTransformer implementation so the ratings values will always remaing the same while Liferay does the best interpretation of the existing based on the selected ratings type.

# Specifying the usage of ratings in our portlets

The definitions to ratings type needs to implement `PortletRatingsDefition` interface. The implementation of method `getDefaultRatingsType` will return the default ratings type of the entity (that can be overriden by the portal admins and site admins). The implementation of method `getPortletId` will return the portlet id of the main portlet that uses this entity. 

Implementations of `PortletRatingsDefition` needs to be registed in OSGI so they can be used by the portal. The way of doing this it's using the OSGI annotation `@Component` with an attribute `model.class.name` with the classname or the classnames that uses ratings.

This is an example of a `PortletRatingsDefition` implementation for the Blogs portlet that will define the ´blogsEntry´ ratings data:

```java
@Component(
	property = {
		"model.class.name=com.liferay.portlet.blogs.model.BlogsEntry"
	}
)
public class BlogsPortletRatingsDefinition implements PortletRatingsDefinition {

	@Override
	public RatingsType getDefaultRatingsType() {
		return RatingsType.THUMBS;
	}

	@Override
	public String getPortletId() {
		return PortletKeys.BLOGS;
	}

}
```

# Transform the ratings values when changing ratings type

If the site admin or portal admin changes the ratings type, and there are existing ratings values in the database, Liferay won't modify any of them. It will interpret those values using the selected ratings type according to the following mechanism:

1. When changing from stars to...
  - **like:** 1 or 2 stars won't be considered. 3, 4 or 5 stars will be considered a Like. 1
  - **thumbs up/down:** 1 or 2 stars will be considered a thumbs down. 3, 4 or 5 stars will be considered a thumbs up.

2. When changing from thumbs up/down to...
  - **stars:** A thumbs down will be considered as 1 star. A thumbs up will be considered 5 stars.
  - **like:** A like will be considered a thumbs up.

3. When changing from like to...
	- **stars:** A like will be considered as 5 stars.
	- **thumbs up/down:** A like will be considered as a thumbs up.

There may be cases where this interpretation is not good enough or a different criteria or alghorithm needs to be applied. For those cases, Liferay provides a mechanism to allow transforming the existing ratings values. Keep in mind that this will modify the values stored in the database, so it needs to be carefully handle because these changes may not be reversible. Developers will need to create an OSGI component that implements `RatingsDataTransformer` interface.

The implementation of method `transformRatingsData` will perform the transformation of the data. In order to obtain a fine-grained behaviour framework provides the `fromRatingsType` ant the `toRatingsType`, so developer could transform data as needed, and perform different transformations under different circumstances.

This is an example of a `PortletRatingsDefition` that will reset the score from the ratings type when passing from `LIKE` to `STARS`. In this particular case changes are not reversible.

```java
@Component
public class DummieRatingsDataTransformer implements RatingsDataTransformer {
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
```
