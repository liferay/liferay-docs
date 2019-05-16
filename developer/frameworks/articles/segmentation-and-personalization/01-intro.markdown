# Segmentation and Personalization

Segments are groups of users that are defined by a specific criteria. You can 
use the metadata from the user or organization profile, context information 
derived from the user's behavior, or some combination of the two to define that 
criteria. Alternatively, segments can be a static set of manually selected 
members. 

In @product-ver@, the [creation of user segments and experience personalization](user-guide-link) are now part of the product's core 
functionality. Up to Liferay Portal 7.1, these functionalities were provided 
through the Audience Targeting application. In addition to the administration 
features of Segmentation and Personalization, developers have access to 
integrate and extend it.

## Managing segments

The API to manage segments in Liferay is provided by the 
`com.liferay.segments.api module`. The `SegmentsEntryService` provides the 
methods to perform permission aware operations on segments.  You can use the provided tools to assign members to segments and to extend segment criteria.

The `segmentsEntry` criteria field determines the conditions that a user must meet to be assigned to the segment. A condition represents a combination of properties, operations, target values, and conjunctions. For example, a condition might look like "organization name EQUALS Liferay AND Job Title  EQUALS Engineer" if you were identifying Liferay Engineers for a Segment.

In the Segments UI, the segments criteria is built using the [Segments Editor](user-doc-link). The available properties are grouped by topic (e.g. User, Organization, Session). Technically, they are what we call a `SegmentsCriteriaContributor`, because they “contribute” conditions to the segments criteria. 

You can see a a number of common Segment management operations with example 
code in [Segment Management].

## Extending Segment Criteria

The default segment capabilities are robust enough to cover most use cases,  
and many types of third party integration can be performed without developing a 
code extension. Some cases, like retrieving an external segment or list can be 
be handled by using the [REST API](rest-link).  

On the other hand, if you want to segment users based on a field provided from 
an external source (for example, the number of followers a user has on 
Instagram), you can contribute an indexable custom field to the User entity and 
query the value using the [Expando API](dev-link). Your new field will 
automatically be available for its use as a [profile-based criteria](user-guide-link).

You will only need to develop a code extension for Liferay if you need to: 
 
*  Add a custom session property. This is done through
    `RequestContextContributor`.

*  Extend the criteria query with new queries, based either on existing model 
    entities in Liferay or in custom model entities. This is done through 
    `SegmentsCriteriaContributor`.

### RequestContextContributor

User and Organization properties are model-based properties. That means that 
the available criteria for users and organizations are based on the attributes 
for users and organizations defined by the entities `model`. Criteria for 
model-based entities can be extended by creating a Custom Field for the 
corresponding model. Session properties are context-based properties and can't 
be extended through custom fields. To allow for user segmentation based on new 
context-based properties, like custom HTTP headers or attributes, you need to 
develop an extension and deploy it in your Liferay instance.

The default fields available for context-based segmentation can be found in the 
[Context interface](repo-link). Liferay generates a context instance with 
real-time information for every request. These are mostly obtained from the 
`HttpServletRequest`. The [`RequestContextContributor interface`](repo-link) 
provides an extension point for adding a new context-based property to the 
Session panel in the Segments criteria editor and populating the segmentation 
context with the right value for that field.

The following service properties define a `RequestContextContributor`:

**`request.context.contributor.key`:** the unique key of the contributed field

**`request.context.contributor.type`:** the type of the contributor field (boolean, date, double, integer or string[default])

The `contribute` method of the `RequestContextContributor` adds the custom field key-value pair to the context. 

![Figure 1: `RequestContextContributor` service properties.](../../images/request-context-contributor.png)

To create a `RequestContextContributor` through the step by step process, see [Creating a Request Context Contributor](dev-guide-link).

### SegmentsCriteriaContributor

The [`SegmentsCriteriaContributor` interface](repo-link) provides a mechanism 
to extend the segment criteria query. Each `SegmentsCriteriaContributor` 
contributes a sub-query (or criterion) and the conjunction (AND, OR) to build 
the complete criteria query that defines the segment. They also provide a list 
of Field elements to be shown in the Segment Editor UI, as depicted in the 
figure:

![Figure 2: `SegmentsCriteriaContributor` service properties.](../../images/segment-field-contributor.png)

The following service properties describe a SegmentsCriteriaContributor:

**`segments.criteria.contributor.key`:** the unique key that identifies the 
contributor.  

**`segments.criteria.contributor.model.class.name`:** the type of entity the 
contributor targets.

**`segments.criteria.contributor.priority`:** the order in which the fields and 
queries are contributed

The [`UserOrganizationSegmentsCriteriaContributor`](repo-link) is a good 
example of how a `SegmentsCriteriaContributor` works. It contributes new 
organization-related fields (“Organization Properties”) to the segments 
criteria editor, executes a query on the Organization model based, and finally 
contributes a subquery to the global user query (AND/OR the user belongs to the 
organizations found in the Organization model query). To put it simple, it 
allows to filter users based on aspects of a different but related entity, such 
as the organization.

To create a `SegmentsCriteriaContributor` through the step by step process, see [Creating Segment Criteria Contributors](dev-guide-link).
