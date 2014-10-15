# Editions of Liferay [](id=editions-of-liferay)

Liferay ships in two different editions: Liferay Portal Community Edition (CE)
and Liferay Portal Enterprise Edition (EE). CE is the same Liferay Portal that
has been available for years: frequently updated and bursting with the latest
features, the Community Edition of Liferay Portal is offered for free under the
Lesser GNU public license, an open source license. This license gives you the
flexibility to link Liferay with your own code in your portlet, theme, hook,
layout, Ext or web plugins, no matter what license you use for your code. If,
however, you modify Liferay directly, those modifications need to be released as
open source. This is really the best of both worlds: you have the freedom to do
what you want with your code if you use plugins, but if you modify Liferay
directly, the community receives the benefits of any enhancements that you've
made. 

Liferay Portal EE is a supported version of Liferay Portal for the enterprise.
Hardened for security and designed to be rock solid stable, EE is offered with a
subscription and support package, allowing organizations to build their portals
on a stable version of the product that is offered over an extended period of
time.

Because the release cycle for EE is longer than that for CE, each enterprise
release is supported for four years. All bug fixes in Liferay Portal are
backported to your version of Liferay for the duration of your subscription.
This gives organizations the peace of mind that comes from knowing that their
Liferay-powered web sites are stable and will run for years to come, enabling
them to build their sites on a proven, stable platform. Additionally, Liferay
offers training and consulting on the Enterprise Edition to ensure long-term
support and stability for our clients. 

## Liferay's Versioning Schema [](id=liferays-versioning-schema)

Liferay's release process follows a prescribed structure that is consistent from
one release to the next. Each release has a specific number sequence attached to
it signifying the type of release it is, whether it's a major, minor or
maintenance release. Each release also has a term attached to it to
indicate its intended level of quality.

EE subscribers have access to additional maintenance releases, along with 
specific *Fix Packs* and *Hot Fixes* that make applying updates to production
environments safer and faster.

Let's start with an explanation of Liferay's version structure. Liferay versions
are organized in a straightforward numerical system consisting of a three digit
number. For example, 6.1.2. These numbers represent the type of the release:
Major.Minor.Maintenance.

### Major Release [](id=major-release)

A change in the first digit of the version (e.g., 6.x to 7.x) is a major
release. This means that:

- There are major changes in functionality or new functionality based on high
  demand.

- There are architectural changes, changes to APIs (as part of the deprecation
  process), or changes to internal schema.

### Minor Release [](id=minor-release)

A change to the second digit of the version scheme (e.g., 6.0 to 6.1) is a minor
release. This means that:

- There are new features and bug fixes from prior releases.

- Customizations may be affected when installing.

- Customers should leverage the upgrade tools and documentation.

### Maintenance Release [](id=maintenance-release)

A change in the third digit of the version scheme (e.g, 6.1.5 to 6.1.6) is a
maintenance release. This means that:

- Each maintenance release provides an improved level of security and reliability.

- Customizations are generally safe, but we recommend doing a review.

- No new features are included. 

These rules are relaxed when a minor or major release is still in beta quality.

Now let's delve into the evolution of versions.

### Release Process [](id=release-process)

Each version of Liferay has a surname that specifies the expected quality of
that release. This is needed because pre-releases of Liferay look very much like
maintenance releases when viewed solely through their version numbers. The
surname in general replaces the third digit in the version, but is visible
through the logs and administration UIs. Here is a description of each surname
and what it means:

- **Milestone** and **Beta:** (6.2 M1, 6.2 B1, 6.2 B2, ...) There can be zero or
  more of these types within each minor or major release.  These releases are
  meant for testing and to provide Liferay feedback through the beta testing
  category in the forums. There will likely be major changes in milestone
  releases, but beta releases are considered "feature complete" and should have
  only bug fixes. 

- **Release Candidates:** (6.1 RC1, 6.1 RC2) There can be zero, one, or more of
  these right after the beta releases. These releases appear near the end of the
  release process and are candidates for release. As such, they should have
  minimal to no bugs, but because they are very new, some minor bugs may have
  slipped by. 

- **General Availability:** (6.1 GA1, 6.1 GA2, ....) There can be one or more of
  these releases. A General Availability release is a re-label of the last
  release candidate, based on internal testing and feedback from beta
  testers. These releases are stable and are made available via Liferay's
  Downloads page for CE and on the Customer Portal for EE. 

### Comments and Recommendations [](id=comments-and-recommendations)

At this point you might be asking yourself questions like, which version
should I use? What if I was using a previous version? Will the update to a new
maintenance release cost a lot? Here are some comments and recommendations to
address these questions.

- When starting a new project, always use the latest stable version available;
  that is, the latest available GA. At the time of writing, the most recent
  version is Liferay CE 6.1 GA2 (6.1.1) or Liferay EE 6.1 GA2 (6.1.20). 

- Always update to the latest maintenance release available for the functional
  version (major or minor) that you are using. For example, if you started your
  project with Liferay 6.1.0 GA1, it is recommended that you switch to GA2 to
  take advantage of bug fixes and improvements. If you have a subscription, you
  can benefit from the fixes faster by requesting fix packs and hot fixes from
  the support team.

- You are always welcome to use any preview, beta or release candidate.  In
  fact, that's why they exist--so as many people as possible start using it and
  provide us their feedback. Please note, we do not recommend using pre-releases
  (milestones, betas, or release candidates) in production. You may not want to
  use these releases even during development if you have tight deadlines, since
  you may find road blocks.

- Plugins that work in any GA or fix pack version will work in any later
  maintenance release. That is, a plugin developed for Liferay 6.1 GA1 will also
  work in Liferay 6 GA2 or a GA2 fix pack. 

For more details on updating Liferay Portal, see [Upgrading
Liferay](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/upgrading-lifer-6)
in chapter 17.

Liferay Portal is a very flexible application that runs well on several
different server environments. It's simple to install and comes in either a
Community Edition or an Enterprise Edition, depending on your needs. Liferay
follows a systematic versioning system that makes it easy to keep current with
the latest updates. The strength of the Liferay community helps detect potential
issues early that are then reported through the forums and are later fixed in a
series of maintenance releases.

Now let's learn about Liferay bundles.
