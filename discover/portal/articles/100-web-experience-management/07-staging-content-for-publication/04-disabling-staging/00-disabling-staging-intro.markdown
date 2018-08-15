# Disabling Staging [](id=disabling-staging)

Disabling staging doesn't take a lot of steps, but should not be done lightly.
It's important to know the consequences of turning the staging environment off
so you can decide if your circumstances really warrant it.

The consequences for disabling Local Live and Remote Live staging are slightly
different, so you'll learn about both.

## Disabling Local Live Staging [](id=disabling-local-live-staging)

Conceptually, the live site is the final approved version of your site, whereas
the staging site is a temporary workspace containing information that is not
finalized.

Disabling local staging completely removes the staging environment, which means
all content not published to your live site is erased. Therefore, before
disabling staging, you must ensure all necessary information on the staged site
is published or preserved elsewhere.

Keep in mind that draft content types are not published, so they can be lost
too.

When you enabled staging there was an initial publication. Disabling staging
does not start a publication; the staging site is deleted. If the staged site
contains a large amount of content, however, those deletions could take
a substantial amount of time to process. For this reason, don't disable staging
when your portal instance is busy.

## Disabling Remote Live Staging [](id=disabling-remote-live-staging)

Disabling remote staging does not delete the staged site; it only disables the
connection between the live site and remote staging site. This means no data is
deleted from the live or remote staging sites after the connection is disabled.
Since no data is erased and no processes are started, disabling remote staging
is almost instantaneous.

When Remote Live staging is enabled, certain information (e.g., which portlet is
being staged) is recorded on both the live and staged sites. For this reason,
when you disable remote staging, you must ensure the live site is still
accessible so both sides can communicate that it's disabled. Do not shut down
your live site and then attempt to disable remote staging from your staged site;
this results in errors.

If there's ever a lost network connection between the remote staged site and the
live site, a message appears, informing you of the error and a way to forcibly
disable staging. This is only an option for the staged site; executing this
option erases the staged site's staging information---not the content. On the
contrary, the live site remains in a locked state. A possible workaround is to
create a new live site and import content to it, if necessary.

## Steps to Disable Staging [](id=steps-to-disable-staging)

Follow the steps below to disable Local Live or Remote Live staging:

1.  Navigate to the *Publishing* &rarr; *Staging* option, which is only
    available from the staged site.

2.  Click the *Options* icon (![Options](../../../images/icon-options.png))
    from the upper right corner of the page and select *Staging Configuration*.

3.  Select the *None* radio button and click *Save*.

That's it! Your staging environment is now turned off.
