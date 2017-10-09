# Patching DXP Source Code [](id=patching-dxp-source-code)

Regarding cars, auto mechanics, car enthusiasts, and concerned car owners ask
"What's under the hood?" Here are some common reasons for asking that question:

-   Concern about an issue.
-   Curiosity about the car's capability and inner-workings.
-   Desire to improve or customize the car.

As a developer, you might have similar reasons for asking "What's under the
*DXP's* hood?" Since DXP Digital Enterprise (DXP) is open-source, you can
examine its code and see it in action! Setting up the DXP source code, applying
desired patches, and debugging it is your ticket to investigating issues,
exploring DXP, making improvements and customizations.

Here are the setup steps:

1.   [Download DXP, the DXP source code, and patches](#download-dxp-the-dxp-source-code-and-patches)
2.   [Prepare DXP](#prepare-dxp)
3.   [Patch the DXP source code](#patch-the-dxp-source-code)

## Step 1: Download DXP, the DXP source code, and patches [](id=download-dxp-the-dxp-source-code-and-patches)

From the
[customer portal](https://web.liferay.com/group/customer/dxp),
download a DXP bundle (or DXP JARs), and the DXP source code for the version
you're using. For example, here are ZIP files for a DXP / Tomcat bundle and DXP
source code:

-   `liferay-dxp-digital-enterprise-tomcat-7.0-sp4-20170705142422877.zip`
-   `liferay-dxp-digital-enterprise-src-7.0-sp4-20170814143552654.zip`

Download fix packs and their source code from
[here](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/fix-packs).
For example, this ZIP file contains a fix pack and source code:

-   `liferay-fix-pack-de-29-7010-src.zip`

+$$$

**Tip**: A fix pack `-src.zip` file contains both the fix pack and its source
code.  

$$$

Next, you'll install and configure DXP. The patching tool is required for
installing fix packs and fix pack source code. If you have a patched DXP
installation already and want to debug it, skip to the section on
[Patching the DXP Source Code](#patch-the-dxp-source-code).

## Step 2: Prepare DXP [](id=prepare-dxp)

Setting up DXP locally is straightforward. 

### Install and Configure DXP [](id=install-and-configure-dxp)

Here are the steps: 

1.   [Install and Deploy DXP](/discover/deployment/-/knowledge_base/7-0/deploying-liferay-portal)
locally. 

2.  Start DXP.

3.  Configure DXP to use your database. 

4.  Stop DXP.

It's time apply the DXP patches you want.

### Patch DXP [](id=patch-dxp)

Here are the steps for patching DXP:

1.  Copy all the patch ZIP files to the folder
    `[LIFERAY_HOME]/patching-tool/patches`. The `-src.zip` fix pack  files are
    good to use because contain both the fix pack binaries and source code. 

2.  Open a terminal window to the folder `[LIFERAY_HOME]/patching-tool`.

3.  Run the command `patching-tool.sh auto-discovery` to generate a default
    patching profile called `default.properties`. Make sure the profile's
    properties refer to your DXP installation. See the
    [patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    for more details.

    Here's an example profile:

    	patching.mode=binary
    	war.path=../tomcat-8.0.32/webapps/ROOT/
    	global.lib.path=../tomcat-8.0.32/lib/ext/
    	liferay.home=../

4.  To list all the patches available in your patches folder 
    `[LIFERAY_HOME]/patching-tool/patches`, execute the following
    command:

        patching-tool.sh info

5.  Install all the patches available in the patches folder by executing this
    command:

        patching-tool.sh install

    The
    [patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    explains additional steps that might apply to your situation, such as
    creating database indexes.

It's time to prepare the DXP source code and patches for debugging. 

## Step 3: Patch the DXP Source Code [](id=patch-the-dxp-source-code)

This step involves preparing the DXP source code locally and patching it. 

First, unzip the DXP source code to where you want to work with it. 

Next, you'll create a patching tool profile for your DXP source code. 

### Create a Patching Tool Profile for the Source Code [](id=create-a-patching-tool-profile-for-the-source-code)

Here are steps for creating a patching tool profile that refers to your source
code.  

1.  Execute the following command to create a profile. Replace `[profile]` with
    a name for your profile. 

        patching-tool.sh auto-discovery [profile]
      
2.  In the profile properties file generated in the previous step, set the
    `patching.mode` property to `source` and set the `source.path` property to
    your source code path:

        patching.mode=source
	    source.path=[location of the DXP source code]

It's time to apply the DXP patches you downloaded earlier. 

## Patch the Source Code [](id=patch-the-source-code)

DXP's patching tool is safe and easy to use. Not only does it let you install
patches, but it also can do these things for any patch:

-   Lists code changes
-   List fixed issues (LPS/LPE tickets)
-   Revert the patch

See the following patching tool documentation for more details:

-   [Comparing Patch Levels](/discover/deployment/-/knowledge_base/7-0/patching-tool#comparing-patch-levels)
-   [Removing or Reverting Patches](/discover/deployment/-/knowledge_base/7-0/patching-tool#removing-or-reverting-patches)

+$$$

Optionally you can manage DXP source code in a version control system such as
[Git](https://git-scm.com/)
in addition to using the patching tool. 

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
in your folder `[LIFERAY_HOME]/patching-tool/patches`. ZIP files for All patches
you're using and want to continue to use must be in the patches folder for the
patching tool to apply. 

1.  Copy all the patch source ZIP files to the folder   
    `[LIFERAY_HOME]/patching-tool/patches`, if you haven't already copied them
    there. 

2.  Execute the `info` command to make sure your patches are listed. Replace
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
