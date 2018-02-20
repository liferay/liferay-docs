# Javadoc Submission Process

Documenting your code with Javadoc is essential for anyone developing for
Liferay products. Please follow the submission processes outlined here to ensure
an effective review process. 

## Liferay Internal Developers

Developers are responsible for creating Javadoc when writing/maintaining its
related code. This ensures that the documentation is correct and useful. There
are two paths you can take for providing Javadoc for your area of expertise:

- [Write Javadoc during the code creation/modification process.](#write-javadoc-during-code-creation)
- [Write Javadoc for completed code that's already merged and available.](#write-javadoc-for-mergedavailable-code)

Follow the path corresponding to your current workflow.

### Write Javadoc During Code Creation

Developers can submit Javadoc **with** code if certain precautions are followed
(outlined below). Follow the below process for submitting Javadoc this way:

1.  Write Javadoc for code areas that you have deep knowledge for. Be sure to
    follow the [Javadoc Guidelines](06-javadoc-guidelines.markdown) when
    writing Javadoc to ensure comprehensive descriptions and consistent style.

2.  Insert an `@review` tag below each new Javadoc entry. This will be searched
    for by the Knowledge Management (KM) team after it's merged to ensure that
    all Javadoc submissions follow the correct format and make sense. This
    should look like this:

        /**
         * Returns the something.
         *
         * @return the something
         *
         * @review
         */
        public int getSomething();

    Submit your Javadoc following your typical code review process.

3.  Notify your team's tech writer (a KM team member) that Javadoc is waiting
    for review. If you're not sure whom to notify, contact Cody Hoag
    (cody.hoag@liferay.com).

The tech writer will track the Javadoc and review it as soon as possible.

### Write Javadoc for Merged/Available Code

Follow the process outlined below to provide Javadoc for code that is already
published:

1.  Write Javadoc for code areas that you have deep knowledge on. Be sure to
    follow the [Javadoc Guidelines](06-javadoc-guidelines.markdown) when
    writing Javadoc to ensure comprehensive descriptions and consistent style.

2.  Send a pull request containing the new Javadoc to your team's tech writer 
    (a KM team member). If you're unsure who should receive the pull request, 
    send it to Cody Hoag (Github handle:
    [`codyhoag`](https://github.com/codyhoag)).

The tech writer will review the Javadoc as soon as possible.

## Community Members

Community submissions for Javadoc are welcome and promoted! If a community
member finds undocumented code on [docs.liferay.com](https://docs.liferay.com/),
and is knowledgeable enough in this area to submit Javadoc, follow the steps
below to contribute:

1.  Write Javadoc for code areas that you have deep knowledge on. Be sure to
    follow the [Javadoc Guidelines](06-javadoc-guidelines.markdown) when
    writing Javadoc to ensure comprehensive descriptions and consistent style.

2.  Send a pull request containing the new Javadoc to
    [`codyhoag`](https://github.com/codyhoag).

The pull request will be reviewed by a subject matter expert for accuracy and
published.

## KM Members

KM members are restricted to only **review** Javadoc. Developers are responsible
for providing drafted Javadoc for their areas of expertise. Below is a checklist
to complete to ensure Javadoc is properly formatted and ready for submission:

- All Javadoc should follow the
  [Javadoc Guidelines](06-javadoc-guidelines.markdown).
- Format Javadoc using the Javadoc Formatter. See
  [here](06-javadoc-guidelines.markdown#formatting-and-building-javadoc)
  for more information.
- Ensure no source formatting violations were introduced while editing. To check
  this, run Source Formatter on your local changes. The steps below
  can be used when reviewing a submission in `liferay-portal`:
    1. Navigate to `liferay-portal/portal-impl` using a command line tool.
    2. Run `ant format-source-local-changes`.
- Ensure all commit descriptions start with a ticket number (e.g., *LPS-12345
  Javadoc*).
- If you're reviewing Javadoc describing a Service Builder service, you must run
  Service Builder to propagate the Javadoc changes/updates. To do this,
  execute `gradlew buildService` from the appropriate module's root folder.
- Do not modify Javadoc in a class containing an `@generated` tag in
  its class description. This class's Javadoc is generated from another class
  when you run Service Builder.

When finished reviewing, send your edits in a pull request to the repository
gate keeper. If you're editing Javadoc for `liferay-portal`, send your edits to
[`codyhoag`](https://github.com/codyhoag).
