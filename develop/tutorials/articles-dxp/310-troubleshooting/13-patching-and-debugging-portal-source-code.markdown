# Patching DXP Source Code [](id=patching-dxp-source-code)

Regarding cars, auto mechanics, enthusiasts, and prospective owners ask "What's
under the hood?" Here are common reasons for asking that question:

-   Concern about an issue.
-   Curiosity about the car's capability and inner-workings.
-   Desire to improve or customize the car.

You might have similar reasons for asking "What's under the *DXP's* hood?" And
since DXP Digital Enterprise (DXP) is open-source, you can examine its code and
attach a debugger see it in action! Setting up the code locally is your ticket
to exploring DXP, investigating issues, and making improvements and
customizations.

Here' are the setup steps:

[Step 1: Download DXP, the DXP source code, and patches](#download-dxp-the-dxp-source-code-and-patches)

[Step 2: Prepare DXP](#prepare-dxp)

[Step 3: Patch the DXP source code](#patch-the-dxp-source-code)

## Step 1: Download DXP, the DXP source code, and patches [](id=download-dxp-the-dxp-source-code-and-patches)

Download a DXP bundle (or DXP JARs), and the DXP source code for the version
you're using from the
[customer portal](https://web.liferay.com/group/customer/dxp).
Here are examples of ZIP files for a DXP / Tomcat bundle and DXP source code:

-   `liferay-dxp-digital-enterprise-tomcat-7.0-sp4-20170705142422877.zip`
-   `liferay-dxp-digital-enterprise-src-7.0-sp4-20170814143552654.zip`

Download fix packs and their source code from
[here](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/fix-packs).
Fix pack ZIP files that end in `-src.zip`, such as the one below, contain a fix
pack and source code:

-   `liferay-fix-pack-de-29-7010-src.zip`

Next you'll install and configure DXP. DXP's patching tool lets you install fix
packs and fix pack source code. If you have a patched DXP installation already
and want to use it, skip to the section
[Patching the DXP Source Code](#patch-the-dxp-source-code).

## Step 2: Prepare DXP [](id=prepare-dxp)

Preparing DXP locally involves installing, configuring, and patching DXP. 

### Install and Configure DXP [](id=install-and-configure-dxp)

Here's how to install and configure DXP: 

1.   [Install and Deploy DXP](/discover/deployment/-/knowledge_base/7-0/deploying-liferay-portal)
locally. 

2.  Start DXP.

3.  Configure DXP to use your database. 

4.  Stop DXP.

It's time apply the DXP patches you want.

### Patch DXP [](id=patch-dxp)

Here's how to patch DXP:

1.  Copy all the patch ZIP files you want to
    `[LIFERAY_HOME]/patching-tool/patches`. The `-src.zip` fix pack files are
    best to use because they contain both the fix pack binaries and source code. 

2.  Open a terminal window to `[LIFERAY_HOME]/patching-tool`.

3.  Run the command `patching-tool.sh auto-discovery` to generate the default
    patching profile called `default.properties`. Make sure the profile's
    properties refer to your DXP installation. See the
    [patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    for more details.

    Here's an example profile:

    	patching.mode=binary
    	war.path=../tomcat-8.0.32/webapps/ROOT/
    	global.lib.path=../tomcat-8.0.32/lib/ext/
    	liferay.home=../

4.  To list all the patch files available in
    `[LIFERAY_HOME]/patching-tool/patches`, execute the following command:

        patching-tool.sh info

5.  Execute this command to install the patches:

        patching-tool.sh install

    The
    [patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    describes additional steps that might apply to your situation, such as
    creating database indexes.

It's time to prepare the DXP source code and patch source code. 

## Step 3: Patch the DXP Source Code [](id=patch-the-dxp-source-code)

Unzip the DXP source code to where you want to work with it. 

Next you'll create a patching tool profile for your DXP source code. 

### Create a Patching Tool Profile for the Source Code [](id=create-a-patching-tool-profile-for-the-source-code)

Here's how to create a profile that refers to your source code.  

1.  Execute the following command to create a profile. Replace `[profile]` with
    a name for your profile. 

        patching-tool.sh auto-discovery [profile]
      
2.  In the profile properties file generated in the previous step, set the
    `patching.mode` property to `source` and set the `source.path` property to
    your source code path:

        patching.mode=source
	    source.path=[DXP source code path]

It's time to apply the DXP patches you downloaded earlier. 

## Patch the Source Code [](id=patch-the-source-code)

DXP's patching tool is safe and easy to use. In addition to installing patches,
it can do these things with respect to a patch:

-   List its code changes
-   List the issues (LPS/LPE tickets) it fixes
-   Revert it

See the following patching tool documentation for more details:

-   [Comparing Patch Levels](/discover/deployment/-/knowledge_base/7-0/patching-tool#comparing-patch-levels)
-   [Removing or Reverting Patches](/discover/deployment/-/knowledge_base/7-0/patching-tool#removing-or-reverting-patches)

+$$$

In addition to using the patching tool to manage DXP source code, you can
optionally manage it in a version control system such as
[Git](https://git-scm.com/). 

Here are commands for setting up the DXP source code in Git:

    cd [path to source code root folder]
    git init
    git add .
    git commit -a

Here's are the command descriptions:

-   `init` creates a Git repository for the current folder (i.e., the root
    folder) and all its contents.
-   `add` stages the root folder and its contents.
-   `commit` checks in the staged files.

You can commit any code changes (e.g., DXP patches) to your Git repository. 

$$$

The patching tool installs all patches and patch source code from the ZIP files
it finds in `[LIFERAY_HOME]/patching-tool/patches`. ZIP files for All patches
you're using and want to continue to use must be in the `patches` folder for the
patching tool to apply. 

1.  Copy all the patch source ZIP files to 
    `[LIFERAY_HOME]/patching-tool/patches`, if you haven't already copied them
    there. 

2.  Execute the `info` command to make sure it lists your patches. If a patch
    isn't listed, copy its ZIP file into the `patches` folder. Replace
    `[profile]` with your DXP source code profile name:

        patching-tool.sh [profile] info

3.  Apply the patches by executing the `install` command on your profile:

        patching-tool.sh [profile] install

You're DXP installation and source code is patched and ready to debug!

Attach your favorite debugger to your DXP instance and start the server. See
your debugger's documentation for configuration details.

Congratulations! You're free to explore DXP inside and out!

## Related Topics (id=related-topics)

[Troubleshooting](/develop/tutorials/-/knowledge_base/7-0/troubleshooting)

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
