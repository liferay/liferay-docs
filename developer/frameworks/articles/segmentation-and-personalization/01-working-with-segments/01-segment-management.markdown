# Segment Management

There are a broad array of uses for Segments and ways that you can integrate 
them with your application. Below is a reference for different 

## Defining a Segment

This snippet defines a segment by retrieving `@Reference` objects from `SegmentsCriteriaContributor` and instantiating a new `Criteria` object. It then adds user criteria using the `segmentsEntryService` that the user's `jobTitle` is "Developer" AND belongs to an Organization with a name that contains “America”:

```private void addSegmentWithCriteria() {
Criteria criteria = new Criteria();

_userSegmentsCriteriaContributor.contribute(
criteria, “(jobTitle eq 'Developer')”, Criteria.Conjunction.AND);
_organizationCriteriaContributor.contribute(
criteria, “contains(name,'America')”, Criteria.Conjunction.OR);

segmentsEntryService.addSegmentsEntry(
"segment-key", nameMap, descriptionMap, true, CriteriaSerializer.serialize(criteria), User.class.getName(), serviceContext);
}

@Reference(target = "(segments.criteria.contributor.key=organization)")
private SegmentsCriteriaContributor _organizationSegmentsCriteriaContributor;

@Reference(target = "(segments.criteria.contributor.key=user)")
private SegmentsCriteriaContributor _userSegmentsCriteriaContributor;
```

### Manual Segment Member Assignments

To define manual user-segment member assignments by means of the `SegmentsEntryRelService` use a snippet like this, which assigns a user identified by a `userId` to a segment identified by a `segmentsEntryId`:

```segmentsEntryRelService.addSegmentsEntryRel(
segmentsEntryId, _portal.getClassNameId(User.class), userId, serviceContext)
```

## Retrieving Segments

Segments and Segment Members can be retrieved programmatically. This code snippet retrieves an ordered range of active segments of user type, within a site identified by a `groupId`.

```List<SegmentsEntry> segmentsEntries = segmentsEntryService.getSegmentsEntries(groupId, true, User.class.getName(), 0, 10, orderByComparator);
```

## Retrieving segment members

The local API to query computed segment-member associations is available in the `com.liferay.segments.api module`. The `SegmentsEntryProvider` service provides methods to obtain the entities associated to a segment, and the segments associated to an entity.

This snippet retrieves a range of primary keys of the entities associated to a segment identified by a `segmentsEntryId`:

```long[] segmentsEntryClassPKs = segmentsEntryProvider.getSegmentsEntryClassPKs(segmentsEntryId, 0, 10);
```

In order to obtain the total count of entities associated to a segment, use the 
`getSegmentsEntryClassPKsCount` method, as shown in the following snippet:

```int segmentsEntryClassPksCount =
	segmentsEntryProvider.getSegmentsEntryClassPKsCount(
		segmentsEntryId);
```

The method `getSegmentsEntryIds` obtains the reverse association --- the segments associated to a specific entity. For example, this snippet returns the segments associated to a user identified by a userId:

```int segmentsEntryClassPksCount =
	segmentsEntryProvider.getSegmentsEntryIds(
		User.class.getName(), userId);
```     
