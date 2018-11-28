# Audience Targeting System Settings [](id=audience-targeting-system-settings)

Audience Targeting's configuration options are provided in [System
Settings](/discover/portal/-/knowledge_base/7-1/system-settings). Configurations
made here take effect system-wide. You can find Audience Targeting system
settings by navigating to the Control Panel &rarr; *Configuration* &rarr;
*System Settings* &rarr; *Web Experience*. The following options are available
for the Audience Targeting apps:

- [Audience Targeting Analytics Service](#audience-targeting-analytics-service)
- [Audience Targeting Analytics Storage](#audience-targeting-analytics-storage)
- [Audience Targeting Service](#audience-targeting-service)
- [Campaign Content Report](#campaign-content-report)
- [Campaign Tracking Actions Report](#campaign-tracking-actions-report)
- [User Segment Content Report](#user-segment-content-report)

The settings described here are unique to the Audience Targeting application.
Some system setting groups may have more configurations than what are defined
here; unspecified settings are generic configurations that are applied across
many @product@ applications.

## Audience Targeting Analytics Service [](id=audience-targeting-analytics-service)

These settings define the general behavior of the Audience Targeting Analytics
modules.

**Analytics events check interval:** Defines the interval (in days) between
data check events.

**Analytics events max age:** Defines the max age (in days) of events stored
in the analytics tables. When a check event is triggered, entries older than
the max age are removed.

**Analytics flush interval:** Defines the interval (in ms) between flushes of
the analytics event buffer on the client-side.

**Get analytics key:** Currently only used for testing purposes.

**Content tracking enabled:** Enables Audience Targeting.

**Form excluded ids reg exp:** Defines forms' excluded IDs using regular
expressions.

**Form tracking enabled:** Enables tracking capabilities for forms.

**Form interaction tracking enabled:** Enables tracking capabilities for
interactions.

**Form submit tracking enabled:** Enables tracking capabilities for form
submissions.

**Form view tracking enabled:** Enables tracking capabilities for form views.

**Link excluded ids reg exp:** Defines links' excluded IDs using regular
expressions.

**Link tracking enabled:** Enables tracking capabilities for links.

**Link click tracking enabled:** Enables tracking capabilities for clicking
events on links.

**Page tracking enabled:** Enables tracking capabilities for pages.

**YouTube tracking enabled:** Enables tracking capabilities for YouTube
videos.

## Audience Targeting Analytics Storage [](id=audience-targeting-analytics-storage)

These settings configure the analytics engine used by Audience Targeting.

**External storage enabled:** Enables analytics storage.

**External storage driver class name:** Defines the external driver class name
for analytics storage.

**External storage URL:** Defines the external storage URL for analytics
storage.

**External storage username:** Defines the external storage user name for
analytics storage.

**External storage password:** Defines the external storage password for
analytics storage.

## Audience Targeting Service [](id=audience-targeting-service)

These settings define the cleaning task for the record of user assignments
(anonymous and registered) to user segments (i.e. the `AnonymousUserUserSegment`
table).

**Anonymous user user segment check interval:** Defines the interval (in days)
between data check events.

**Anonymous user user segment max age:** Defines the max age (in days) of
records stored in the `AnonymousUserUserSegment` table. When a check event is
triggered, entries older than the max age are removed.

## Campaign Content Report [](id=campaign-content-report)

These settings configure campaign content reports.

**Campaign content report check interval:** Defines the report check interval
(in hours) for campaign content. Since the lifetime of events in the Analytics
tables is limited to the *Analytics events max age*, reports must retrieve and
process the latest events from Analytics with a shorter time interval.

## Campaign Tracking Actions Report [](id=campaign-tracking-actions-report)

These settings configure campaign tracking action reports.

**Campaign tracking action report check interval:** Defines the report check
interval (in hours) for campaign tracking actions. Since the lifetime of events
in the Analytics tables is limited to the *Analytics events max age*, reports
must retrieve and process the latest events from Analytics with a shorter time
interval.

## User Segment Content Report [](id=user-segment-content-report)

These settings configure user segment content reports.

**User segment content report check interval:** Defines the report check
interval (in hours) for user segment content. Since the lifetime of events in
the Analytics tables is limited to the *Analytics events max age*, reports must
retrieve and process the latest events from Analytics with a shorter time
interval.
