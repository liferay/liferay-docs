# Audience Targeting System Settings

When Audience Targeting is installed in your @product@ instance, there are
additional configuration options provided in
[System Settings](/discover/portal/-/knowledge_base/7-0/system-settings).
Audience Targeting configurations made here take effect system-wide. You can
find Audience Targeting system settings by navigating to the Control Panel
&rarr; *Configuration* &rarr; *System Settings* &rarr; *Web Experience*. The
following options are available for the Audience Targeting apps:

- [Audience Targeting Analytics Service](#audience-targeting-analytics-service)
- [Audience Targeting Analytics Storage](#audience-targeting-analytics-storage)
- [Audience Targeting Service](#audience-targeting-service)
- [Campaign Content Report](#campaign-content-report)
- [Campaign Tracking Actions Report](#campaign-tracking-actions-report)
- [User Segment Content Report](#user-segment-content-report)

The settings described in this article are unique to the Audience Targeting
application. Some system setting groups may have more configurations than what
are defined here; unspecified settings are generic configurations that are
applied across many @product@ applications.

## Audience Targeting Analytics Service

These settings define the general behavior of the Audience Targeting Analytics
modules.

- **Analytics events check interval:** defines the interval (in days) between
  data check events.
- **Analytics events max age:** defines the max age (in days) of events stored
  in the analytics tables. When a check event is triggered, entries older than
  the max age are removed.
- **Analytics flush interval:** defines the interval (in ms) between flushes of
  the analytics event buffer on the client-side.
- **Get analytics key:** currently only used for testing purposes.
- **Content tracking enabled:** enables Audience Targeting.
- **Form excluded ids reg exp:** defines forms' excluded IDs using regular
  expressions.
- **Form tracking enabled:** enables tracking capabilities for forms.
- **Form interaction tracking enabled:** enables tracking capabilities for
  interactions.
- **Form submit tracking enabled:** enables tracking capabilities for form
  submissions.
- **Form view tracking enabled:** enables tracking capabilities for form views.
- **Link excluded ids reg exp:** defines links' excluded IDs using regular
  expressions.
- **Link tracking enabled:** enables tracking capabilities for links.
- **Link click tracking enabled:** enables tracking capabilities for clicking
  events on links.
- **Page tracking enabled:** enables tracking capabilities for pages.
- **Youtube tracking enabled:** enables tracking capabilities for YouTube
  videos.

## Audience Targeting Analytics Storage

These settings configure the analytics engine used by Audience Targeting.

- **External storage enabled:** enables analytics storage.
- **External storage driver class name:** defines the external driver class name
  for analytics storage.
- **External storage URL:** defines the external storage URL for analytics
  storage.
- **External storage username:** defines the external storage username for
  analytics storage.
- **External storage password:** defines the external storage password for
  analytics storage.

## Audience Targeting Service

These settings define the cleaning task for the record of assignments of users
(anonymous and registered) to user segments (i.e. the `AnonymousUserUserSegment`
table).

- **Anonymous user user segment check interval:** defines the interval (in days)
  between data check events.
- **Anonymous user user segment max age:** defines the max age (in days) of
  records stored in the `AnonymousUserUserSegment` table. When a check event is
  triggered, entries older than the max age are removed.

## Campaign Content Report

These settings configure campaign content reports.

- **Campaign content report check interval:** defines the report check interval
(in hours) for campaign content. Since the lifetime of events in the Analytics
tables is limited to the *Analytics events max age*, reports need to retrieve
and process the latest events from Analytics with a shorter time interval.

## Campaign Tracking Actions Report

These settings configure campaign tracking action reports.

- **Campaign tracking action report check interval:** defines the report check
interval (in hours) for campaign tracking actions. Since the lifetime of events
in the Analytics tables is limited to the *Analytics events max age*, reports
need to retrieve and process the latest events from Analytics with a shorter
time interval.

## User Segment Content Report

These settings configure user segment content reports.

- **User segment content report check interval:** defines the report check
interval (in hours) for user segment content. Since the lifetime of events in
the Analytics tables is limited to the *Analytics events max age*, reports need
to retrieve and process the latest events from Analytics with a shorter time
interval.