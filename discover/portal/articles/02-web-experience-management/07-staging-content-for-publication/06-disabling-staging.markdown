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
not disable staging when your portal instance is under a big load.

## Disabling Remote Live Staging

Disabling Remote Live staging does not delete the staged site; it only disables
the connection between the live site and remote staging site.
<!-- ^ Is this true? Wrote this based on my own understanding. -Cody -->

When Remote Live staging is enabled, certain information (e.g., which portlet is
being staged) is recorded on both the live and staged sites. For this reason,
when remote staging is disabled, the network link is still active to allow for
staging to properly be disabled (i.e., the information must be reset once
staging is turned off).

If remote staging must be turned off, you should make sure the live site is
still accessible.

Turning off remote staging does not erase data on the live site.

In case the network connection is lost between the staged site and the live site
instances, there is an option to forcibly turn staging off. This is only an
option for the staged site; the site's staging information will be erased---not
the content. On the contrary, the live site will remain in a locked state. A
possible workaround is to create a new live site, and import content to it if
necessary.

Turning remote staging off does not start any kind of process; also, because no
data is being erased, it's almost instantaneous.




## Steps to Disable Staging

Follow the steps below to disable Local Live or Remote Live staging:

1.  Navigate to the *Publishing* &rarr; *Staging* option, which is only
    available from the staged site.

2.  Click the *Options* icon (![Options](../../../../images/icon-options.png))
    from the upper right corner of the page and select *Staging Configuration*.

3.  Select the *None* radio button and click *Save*.

That's it! Your staging environment is now turned off.
