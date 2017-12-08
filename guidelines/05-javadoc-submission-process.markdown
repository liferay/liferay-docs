# Javadoc Submission Process

The production of Javadoc with Liferay code is highly recommended and essential
for anyone developing for Liferay products. The submission processes outlined
in this article must be followed to ensure quality and efficiency during the
review process. Follow the appropriate process matching your relationship with
Liferay, Inc.

## Liferay Internal Developers

The creation of Javadoc is the responsibility of the developers who
write/maintain its related code. This ensures that the documentation is correct
and useful. There are two paths you can take for providing Javadoc for your area
of expertise:

- [Write Javadoc during the code creation/modification process.](#write-javadoc-during-code-creation)
- [Write Javadoc for completed code that's already merged and available.](#write-javadoc-for-mergedavailable-code)

Follow the path corresponding to your current workflow.

### Write Javadoc During Code Creation

Developers are allowed to submit Javadoc **with** code if certain precautions
are followed (outlined below). Follow the below process for submitting Javadoc
this way:

1.  Write Javadoc for code areas that you have deep knowledge for. Be sure to
    follow the
    [Javadoc Guidelines](https://dev.liferay.com/participate/javadoc-guidelines)
    when writing Javadoc to ensure comprehensive descriptions and consistent
    style.

2.  Insert an `@review` tag below each Javadoc entry that you provide. This will
    be searched for by the Knowledge Management (KM) team after it's merged to
    ensure that all Javadoc submissions follow the correct format and make
    sense. This should look something like this:

        /**
         * Returns the something.
         *
         * @return the something
         *
         * @review
         */
        public int getSomething();

    Submit your Javadoc following your typical code review process.

3.  Notify the KM member (tech writer) that represents your team of the
    unreviewed Javadoc. If you're unsure of who you should notify, contact
    Cody Hoag (cody.hoag@liferay.com).

The KM member will track the Javadoc and review it as soon as possible.

### Write Javadoc for Merged/Available Code

Follow the process outlined below to provide Javadoc for code that is already
published:

1.  Write Javadoc for code areas that you have deep knowledge on. Be sure to
    follow the
    [Javadoc Guidelines](https://dev.liferay.com/participate/javadoc-guidelines)
    when writing Javadoc to ensure comprehensive descriptions and consistent
    style.

2.  Send a pull request containing the new Javadoc to the KM member (tech
    writer) that represents your team. If you're unsure who you should send the
    pull request to, send it to Cody Hoag (Github handle:
    [`codyhoag`](https://github.com/codyhoag)).

The KM member will review the Javadoc as soon as possible.

## Community Members

Community submissions for Javadoc are welcome and promoted! If a community
member finds undocumented code on [docs.liferay.com](https://docs.liferay.com/),
and is knowledgeable enough in this area to submit Javadoc, follow the steps
below to contribute:

1.  Write Javadoc for code areas that you have deep knowledge on. Be sure to
    follow the
    [Javadoc Guidelines](https://dev.liferay.com/participate/javadoc-guidelines)
    when writing Javadoc to ensure comprehensive descriptions and consistent
    style.

2.  Send a pull request containing the new Javadoc to
    [`codyhoag`](https://github.com/codyhoag).

The pull request will be reviewed by a subject matter expert for accuracy and
published.

## KM Members

KM members are restricted to only **review** Javadoc. Developers are responsible
for providing drafted Javadoc for their areas of expertise. Below is a checklist
to complete to ensure Javadoc is properly formatted and ready for submission:

- All Javadoc should follow the
  [Javadoc Guidelines](https://dev.liferay.com/participate/javadoc-guidelines).
- Format Javadoc using the Javadoc Formatter. See
  [here](https://dev.liferay.com/participate/javadoc-guidelines#formattingAndBuildingJavadoc)
  for more information.
- Ensure no source formatting violations were introduced while editing. To check
  this, run Source Formatter on your local changes. For example, the steps below
  can be used when reviewing a submission in `liferay-portal`:
    1. Navigate to `liferay-portal/portal-impl` using a command line tool.
    2. Run `ant format-source-local-changes`.
- Ensure all commit descriptions start with a ticket number (e.g., *LPS-12345
  Javadoc*).
- If you're reviewing Javadoc describing a Service Builder service, you'll need
  to run Service Builder to prorogate the Javadoc changes/updates. To do this,
  execute `gradlew buildService` from the appropriate module's root folder.
- Do not modify Javadoc residing in a class containing an `@generated` tag in
  its class description. This class will have its Javadoc generated from another
  class when you run Service Builder.

When finished reviewing, send your edits in a pull request to the repository
gate keeper. If you're editing Javadoc for `liferay-portal`, send your edits to
[`codyhoag`](https://github.com/codyhoag).
