# Implementing Ratings Type Selection and Value Transformation [](id=ratings)

Liferay has three different mechanisms for rating content: Stars, Thumbs
Up/Down, and Likes. Prior to 7.0, there was no way for administrators to select
a ratings type as it was hard-coded. Now, portal and site admins can select the
ratings type for portlet entities through the Control Panel and Site
Administration. Portal admins can select default values for the ratings type
while site admins can override values for their sites. All Liferay portlets take
advantage of this feature, and so can custom portlets.

A custom portlet that uses ratings must define its ratings type through an OSGi
component that implements the `PortletRatingsDefinition` interface. This class
declares the usage of ratings (specifying the portlet and the entity) and the
default ratings type (that can be overridden by portal and site admins).
Developers can include their portlet entities in this section by defining the
ratings type for the entities using OSGi modules.

The ratings values are stored in the database as normalized values to be able to
easily switch among different ratings types. When the administrators change the
ratings type for an entity, Liferay does a best match of the previous ratings
values to values for the new ratings type. For example, when changing from
Thumbs Up/Down to Likes, Portal considers all the Thumbs Up values as if they
are Likes and omits all Thumbs Down values. Also, when changing from Stars to
Thumbs Up/Down Portal considers ratings with 3, 4, or 5 Stars as Thumbs Up and
ratings with 1 or 2 Stars as Thumbs Down.

However, there are some cases where this may not be enough or where you want to
apply different or complex criteria to determine the new ratings values. Liferay
provides a mechanism to allow developers to transform the ratings values as
needed when admins change the ratings type. Keep in mind, this is not an
interpretation of the data, this is modification of the stored ratings values.
Third party developers can define their own transformations by creating an OSGi
component that implements the `RatingsDataTransformer` interface. The highest
OSGi ranking implementation is used.

Liferay by default doesn't provide any `RatingsDataTransformer` implementation;
the ratings values always remain the same while Liferay interprets existing
values for the selected ratings type.

## Specifying an Entity's Ratings Type [](id=specifying-an-entitys-ratings-type)

Ratings type definitions needs to implement the `PortletRatingsDefinition`
interface. The implementation of method `getDefaultRatingsType` returns the
entity's default ratings type. Note, it can be overridden by portal and site
admins. The implementation of method `getPortletId` returns the portlet ID of
the main portlet that uses the entity.

Implementations of `PortletRatingsDefinition` need to be registered in OSGi to
be used by the portal. You do this in the implementation by specifying the OSGi
annotation `@Component` with an attribute `model.class.name` set to the name or
names of the classes to use the ratings definition.

Here is an example of a `PortletRatingsDefition` implementation for the Blogs
portlet that defines the `blogsEntry` ratings data:

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

Next, let's examine how to transform values between ratings types. 

## Transforming Ratings Values Between Ratings Types [](id=transforming-ratings-values-between-ratings-types)

If the site admin or portal admin changes the ratings type and there are
existing ratings values in the database, Liferay doesn't modify any of them. It
interprets the values using the selected ratings type according to the
following mechanism:

1. When changing from Stars to ...
     - **Like:** 1 or 2 Stars aren't considered. 3, 4, or 5 Stars is
       considered a Like.
     - **Thumbs Up/Down:** 1 or 2 Stars are considered a Thumbs Down. 3, 4, or
       5 Stars is considered a Thumbs Up. 

2. When changing from Thumbs Up/Down to ...
    - **Like:** A Like is considered a Thumbs Up.
    - **Stars:** A Thumbs Down is considered 1 Star. A Thumbs Up is
      considered 5 Stars. 

3. When changing from Like to ...
	- **Stars:** A Like is considered 5 Stars.
	- **Thumbs Up/Down:** A Like is considered a Thumbs Up.

There may be cases where this interpretation is insufficient for you or where a
different criteria or algorithm needs to be applied. For these cases, Liferay
provides a mechanism to allow transforming the existing ratings values. Keep in
mind that this modifies the values stored in the database. Since these changes
may not be reversible, you must be careful. Developers will need to create an
OSGi component that implements the `RatingsDataTransformer` interface.

The implementation of method `transformRatingsData` performs the data
transformation. In order to obtain a fine-grained behaviour, the framework
provides the methods `fromRatingsType` and `toRatingsType`. The developer can
implement them to transform data as needed, and perform different
transformations for different circumstances.

This is an example of a `RatingsDataTransformer` that resets the score from
the ratings type when passing from `LIKE` to `STARS`. In this particular case,
changes are not reversible.

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

In this tutorial, you have learned how to set a ratings type for an entity and how
to implement a ratings data transformer. Liferay salutes you with a Thumbs Up! 
