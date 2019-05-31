# Migrating User Segments

In Audience Targeting, a user segment represents a subset of users. A user segment is defined by one or more rules that users must match to belong to that user segment. In Liferay Portal 7.2, segments work in a similar way, but they are defined by criteria, instead of rules. Segments criteria are a set of fields associated to different aspects of the user (profile, organization, session) that can be combined through operations (e.g. equals, not equals, contains, not contains, greater than, lower than…) and conjunctions (AND, OR) to define complex filters. 

Due to these similarities between Audience Targeting user segments and Liferay Portal 7.2 segments, certain data can be automatically migrated as part of the upgrade process.


## Upgrade Process

The automated migration of Audience Targeting user segments and their rules into the new Liferay Portal 7.2 segments and their criteria is performed as part of the upgrade process for this version. Just follow the documented steps to upgrade to Liferay Portal 7.2. [LINK TO DOC].

As a result of the upgrade process:

* All Audience Targeting User Segments will show under the new Segments administration in 7.2, with the same name.
* For every segment, those Audience Targeting rules with an equivalent in Liferay 7.2 have been migrated into the corresponding criteria fields (see Table).
* Audience Targeting tables have been removed from your Liferay Portal Database.


| Audience Targeting Rule | Liferay 7.2. Segment Criteria Field | Upgrade Path
|---                      |---                      |---
| Browser                 | Browser                 | Automated. Use user agent field with `contains` operation as an alternative
| Custom Field            | Custom Field            | Automated
| Language                | Language                | Automated
| Last Login Date         | Last Sign In Date       | Automated
| Organization Member     | Organization            | Automated
| OS                      | User Agent              | Automated
| Regular Role            | Role                    | Automated
| Site Member             | Sie                     | Automated
| User Group Member       | User Group              | Automated
| IP Range                | TBD                     |
| Organization Role       | TBD                     |
| Sign Up                 | TBD                     |
| Site Role               | TBD                     |
| Time                    | TBD                     |
| Geolocation             | TBD                     |
| Age                     | Not Available           | Suggested: custom field
| Facebook (various)      | Not Available           | Suggested: custom field
| Gender                  | Not Available           | Suggested: custom field
| Previous Visited Site   | Not Available           | Suggested: URL, cookie
| Score Points            | Not Available           | Suggested: custom field
| Visited Page/Content    | Not Available           | Suggested: custom field


For those Audience Targeting rules without a direct equivalent, a manual migration is required. See the Manual Migration section [LINK TO THE SECTION].
