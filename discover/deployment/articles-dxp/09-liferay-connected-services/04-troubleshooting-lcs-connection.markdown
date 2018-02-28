# Troubleshooting Your LCS Connection

Since LCS activates your @product@ instance, your server must maintain its 
connection to LCS at all times. If this connection is interrupted, your server 
enters a grace period to allow for reconnection. Lengthy interruptions, however, 
can affect your server's uptime. Liferay takes this very seriously and takes 
steps to ensure the stability of LCS. This document discusses how Liferay does 
this, how LCS handles connection errors and the grace period, and some 
troubleshooting steps that you can follow if you have problems with your 
server's LCS connection. 

## LCS System Resilience

Because LCS is deployed on a global cloud infrastructure set up for automatic 
failure recovery, the potential for non-availability is very low. Notifications 
also let the LCS team react quickly to any downtime. During LCS updates and new 
version releases, LCS will be unavailable for a few minutes while the new 
changes are being applied. 

## LCS Grace Periods

There are 2 grace period types in LCS: 

1.  **Connection Grace Period:** Occurs when your activated @product@ instance's 
    LCS connection is interrupted. This gives you time to re-establish the 
    connection. 

2.  **Subscription Grace Period:** Occurs when your @product@ subscription is 
    about to expire. This gives you time to renew the subscription. 

+$$$

**Note:** These grace periods only apply to servers previously connected and 
activated in LCS. If the subscription check or connection fails when a server 
attempts to connect to LCS for the first time, that server doesn't enter a grace 
period. It's therefore important to verify that there are available 
subscriptions before connecting a new server. To do this, check the 
Subscriptions page in LCS. 

$$$

First, you'll learn about the connection grace period. 

### Connection Grace Period

If your server's LCS connection is interrupted, the server continues to run and 
enters a grace period that lasts for up to 30 days to allow for reconnection. 
During this grace period, your @product@ instance displays a warning message to 
administrators. Upon seeing this message, administrators should immediately 
contact Liferay Support and follow the troubleshooting steps in the below 
section. If for some reason the connection can't be restored, Liferay Support 
will provide an alternative for activating your server. 

LCS automatically restores your server's activation upon reconnection (you 
shouldn't need to restart the server). If this doesn't happen, you can force it 
by redeploying the LCS client app. 
<!-- 

The original text says, "... customers can force a reconnect without restarting 
by redeploying the LCS Client (pending on Core Infra fix: LPS-75451)." 

Is there an ETA for this fix? The LPS has been open for 4 months now. I'm 
inclined to just tell customers to restart their servers.

-->

The grace period behavior described above has been implemented in several 
updates to @product@. If you're not running these updates, your server's grace 
period may be different. The following table lists each update and its grace 
period change. Note that newer fix packs and hot fixes also include these 
updates. 

| &nbsp;Fix Pack | &nbsp;Hotfix Built After | Before | After |
| --------- | ------------------ | ------ | ----- |
|   n/a     |        n/a         | The grace period warning is always displayed to administrators, on all pages. | A portal property can be used to restrict the grace period message to the License Manager and LCS pages in the Control Panel. |
|   n/a     |        n/a         | The grace period lasts 7 days. | The grace period lasts 30 days. |
| DXP DE-32 | 20 Oct 2017 | The grace period warning is displayed as soon as the LCS connection is lost. | The grace period warning is displayed only after the connection has been out for 1 hour. This prevents false alarms for transient network problems. |
| DXP DE-33 | 17 Nov 2017 | The grace period is only invoked for lost network connections. | The grace period is invoked for failed subscription validation, if the server was previously registered. |

<!--

For DXP DE-33, I'm assuming the grace period is still invoked for lost network 
connections, correct?

-->

### Subscription Grace Period

At least 90 days before a subscription expires, Liferay will reach out to begin 
the renewal process. 30 days before expiration, Liferay Support sends warning 
messages through 
[LESA](https://web.liferay.com/group/customer/support/-/support/ticket), 
[the LCS site](https://lcs.liferay.com), 
and 
[the Customer Portal](https://www.liferay.com/group/customer). 
After the expiration date, your servers may be placed in an additional grace 
period, which will be communicated through the same support channels. If the 
renewal isn't completed during this grace period, then the subscription becomes 
inactive and the @product@ instance enters the 7-day grace period. As soon as 
the renewal is processed, the instance activates and any error or warning 
messages disappear within 24 hours. Note that by using XML activation keys 
(provided by Liferay Support upon request), you can continue to use your 
@product@ instances even after a subscription has expired. 

<!-- 

When the subscription expires, should the 7 day grace period for the Liferay 
instance be 30 days instead?

-->

