---
header-id: upgrading-the-core-using-the-upgrade-tool
---

# Upgrading the Core Using the Upgrade Tool

[TOC levels=1-4]

The upgrade tool provides the easiest way to upgrade the core and installed
modules. Here's how to use it. 

## Upgrade Tool Usage

The `db_upgrade.sh` script in the  `[Liferay Home]/tools/portal-tools-db-upgrade-client`
folder (`db_upgrade.bat` on Windows) invokes the upgrade tool. 

This command prints the upgrade tool usage: 

    db_upgrade.sh --help

This configuration prevents automatic module upgrade, but causes the upgrade
tool to open a Gogo shell for
[upgrading modules](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell)
after finishing the core upgrade. 
 
Here are the tool's default Java parameters:

    -Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m 

The `-j` option overrides the JVM parameters. For example, these options set the
JVM memory to 10GB, which is a good starting point for this process type:

    db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx10240m"

The `-l` option specifies the tool's log file name: 

    db_upgrade.sh -l "output.log"

Here are all the upgrade tool command line options:

**--help** or **-h**: Prints the tool's help message.

**--jvm-opts** or **-j** + **[arg]**: Sets any JVM options for the upgrade 
process.

**--log-file** or **-l** + **[arg]**: Specifies the tool's log file name---the 
default name is `upgrade.log`.

**--shell** or **-s**: Automatically connects you to the Gogo shell after
finishing the upgrade process.

| **Note:** Only execute the upgrade process on a server with ideal memory, CPU,
| and database connection configurations. If executing an upgrade remotely using
| `ssh`, make sure to guard against interruptions: 
| 
| -   If you're executing the upgrade using `ssh`, ignore hangups (connection
|     loss) by using `nohup` or something similar. 
| -   On the machine you're connecting from, disable settings that shutdown or 
|     sleep that machine. 
| 
| The upgrade process continues on the server even if you lose connection to it.
| If you lose connection, reconnect and monitor upgrade status via the log
| (default log file is `upgrade.log`). If you're using an earlier version of
| @product-ver@ and upgrade execution is interrupted, check your log file for
| where execution stopped. 
| 
| -   If execution stopped during an upgrade process for Core 7.1 or higher, or 
|     any module upgrade process, restart the upgrade tool to continue the 
|     upgrade from that point. You can also use Gogo shell to
|     [check module upgrade status](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell#checking-upgrade-status)
|     and continue upgrading modules. 
| -   If execution stopped during an upgrade process for Core 7.0 or lower, you 
|     must
|     [restore the data from a backup](/docs/7-2/deploy/-/knowledge_base/d/backing-up-a-liferay-installation)
|     and start the upgrade again. 

| **Warning:** To prevent the tool's expanded command from growing too large for
| Windows, execute the upgrade tool script from the `[Liferay
| Home]/tools/portal-tools-db-upgrade-client` folder.

It's time to upgrade your core data using the upgrade tool. 

## Running and Managing the Core Upgrade

Start the upgrade tool, as the previous section explains. Here are the core
upgrade stages:

1.  Show the upgrade patch level

2.  Execute the core upgrade processes

3.  Execute the core verifiers

Monitor the upgrade via the upgrade tool log file (default file is
`upgrade.log`). If a core upgrade process fails, analyze the failure and resolve
it. If a core upgrade step for @product@ 7.1 (or newer) fails, executing the
upgrade tool again starts it from that step. 

If you configured the upgrade tool to upgrade non-core modules, the tool opens a
Gogo shell and starts upgrading them. The Gogo shell lets you upgrade modules,
check module upgrade status, verify upgrades, and restart module upgrades. Read
on to learn how to use Gogo shell commands to complete @product@ upgrades. 
