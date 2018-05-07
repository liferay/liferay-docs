# Disabling Staging

Disabling staging is straightforward, but should not be taken lightly. It's
important to know the consequences of turning the staging environment off so you
you know whether it's a smart decision for your scenario.

The consequences for disabling Local Live and Remote Live staging are slightly
different, so you'll learn about both.

## Disabling Local Live Staging

Conceptually, the live site is viewed as the final approved version of your
site, whereas the staging site is a temporary workspace containing information
that is not finalized.

Disabling local staging completely removes the staging environment altogether,
which means all content that was not published to your live site is erased. This
means that before disabling staging, you must ensure all necessary information
on the staged site is published or preserved elsewhere.

Keep in mind that draft content types are not published, so they can be lost
too.

Unlike the staging enablement process where there was an initial publication,
disabling staging does not start a publication; the staging site is deleted. If
the staged site contains a large amount of content, however, those deletions
could take a substantial amount of time to process. For this reason, you should
not disable staging when your portal instance is using a large amount of memory
elsewhere.

## Disabling Remote Live Staging

Disabling remote staging does not delete the staged site; it only disables the
connection between the live site and remote staging site. This means no data is
deleted from the live or remote staging sites after the connection is disabled.
Since no data is erased and no processes are started, disabling remote staging
is almost instantaneous.

When Remote Live staging is enabled, certain information (e.g., which portlet is
being staged) is recorded on both the live and staged sites. For this reason,
when remote staging is disabled, you must ensure the live site is still
accessible to allow for staging to be properly disabled. Do not shut down your
live site and then attempt to disable remote staging from your staged site; this
results in errors.

If there's ever a lost network connection between the remote staged site and the
live site, a message is displayed informing you of the error and a way to
forcibly disable staging. This is only an option for the staged site; executing
this option erases the staged site's staging information---not the content. On
the contrary, the live site remains in a locked state. A possible workaround is
to create a new live site and import content to it, if necessary.

## Steps to Disable Staging

Follow the steps below to disable Local Live or Remote Live staging:

1.  Navigate to the *Publishing* &rarr; *Staging* option, which is only
    available from the staged site.

2.  Click the *Options* icon (![Options](../../../../images/icon-options.png))
    from the upper right corner of the page and select *Staging Configuration*.

3.  Select the *None* radio button and click *Save*.

That's it! Your staging environment is now turned off.
