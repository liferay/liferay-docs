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

## LCS Error Handling

### Connection Grace Period

If your server's LCS connection is interrupted, the server continues to run and 
enters a grace period that lasts for up to 30 days to allow for reconnection. 
During this grace period, your @product@ instance displays a warning message to 
administrators. If for some reason the connection can't be restored, Liferay 
Support will provide an alternative for activating your server. 

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
| DXP DE-32 | 20 Oct 2017 | The grace period warning is displayed as soon as the LCS connection is lost. | The grace period warning is displayed only after the connection has been out for 1 hour. |
| DXP DE-33 | 17 Nov 2017 | The grace period is only invoked for lost network connections. | The grace period is invoked for failed subscription validation, if the server was previously registered. |

<!--

For DXP DE-33, I'm assuming the grace period is still invoked for lost network 
connections, correct?

-->
