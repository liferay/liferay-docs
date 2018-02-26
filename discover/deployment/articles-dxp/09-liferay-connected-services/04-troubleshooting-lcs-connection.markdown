# Troubleshooting LCS Connection Status

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


