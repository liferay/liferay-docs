# Patching and Debugging DXP Source [](id=patching-and-debugging-dxp-source)

Auto mechanics, car enthusiasts, and concerned car owners ask "What's under the
hood?" Reasons for asking could be as follows:

-   Concern about an issue.
-   Curiosity about the car's capability and inner-workings.
-   Desire to improve or customize the car.

As a developer, you might have similar reasons for wanting to know "What's under
the *DXP's* hood?" Since DXP is open-source, you can examine its code and see it
in action! Setting up DXP with a debugger is your ticket to investigating
issues, exploring DXP, making improvements and customizations.

Here are the setup steps:

1.   [Download DXP, the DXP source code, and patches](#download-dxp-the-dxp-source-code-and-patches)
2.   [Prepare DXP](#prepare-dxp)
3.   [Prepare the DXP source code](#prepare-the-dxp-source-code)
4.   [Attach a debugger](#debug-dxp)

## Step 1: Download DXP, the DXP source code, and patches [](id=download-dxp-the-dxp-source-code-and-patches)

From the
[customer portal](https://web.liferay.com/group/customer/dxp),
download a DXP Digital Enterprise (DXP) bundle (or DXP JARs), and the DXP source
code for the DXP version you're using. For example, here are ZIP files for a DXP /
Tomcat bundle and DXP code:

-   `liferay-dxp-digital-enterprise-tomcat-7.0-sp4-20170705142422877.zip`
-   `liferay-dxp-digital-enterprise-src-7.0-sp4-20170814143552654.zip`

Download fix packs and their source code from
[here](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/fix-packs).
For example, here are fix pack and source code  ZIP files:

-   `liferay-fix-pack-de-29-7010.zip`
-   `liferay-fix-pack-de-29-7010-src.zip`

Next, you'll install and configure DXP first since it has the patching tool. If
you have a patched DXP installation already and want to debug it, skip to the
section on
[setting up and patching the DXP source code](#prepare-the-dxp-source-code).

## Step 2: Prepare DXP [](id=prepare-dxp)

Setting up and patching DXP locally is straightforward. 

### Install and Configure DXP [](id=install-and-configure-dxp)

Follow these steps to install and configure DXP: 

1.   [Install and Deploy DXP](/discover/deployment/-/knowledge_base/7-0/deploying-liferay-portal)
locally. 

2.  Start DXP.

3.  Configure DXP to use your database. 

4.  Stop DXP.

It's time apply patches you want to DXP.

### Patch DXP [](id=patch-dxp)

Here are the steps for patching DXP:

1.  Copy the patch ZIP files to the folder
    `[LIFERAY_HOME]/patching-tool/patches`.

2.  Open a terminal to the folder `[LIFERAY_HOME]/patching-tool`.

3.  Generate a default patching profile called `default.properties` by running
    `patching-tool.sh auto-discovery`. Make sure the profile's properties
    refers to your DXP installation. See
    [the patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    for more details.

    Here's an example profile:

    	patching.mode=binary
    	war.path=../tomcat-8.0.32/webapps/ROOT/
    	global.lib.path=../tomcat-8.0.32/lib/ext/
    	liferay.home=../

4.  Execute the command `patching-tool.sh info` to list your installed
    patches and patches available in your `patches` folder.

5.  Install all available local patches by executing this command:

        patching-tool.sh install

    The
    [patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    explains additional steps that might apply, such as deleting your
    `[LIFERAY_HOME]/osgi/state` folder and creating database indexes.

You're ready to prepare the DXP source code for debugging. 

## Step 3: Prepare the DXP Source Code [](id=prepare-the-dxp-source-code)

Version control systems, such as [Git](https://git-scm.com/) facilitate
examining code changes. To help you compare patches, it's recommended to commit
the DXP source code and each patch separately to a version control system.
Seeing the diffs between the patch commits can help you find where behaviors are
introduced. This tutorial uses the Git version control system. 

Here are steps for preparing the source:

1.  [Commit the source code to version control.](#commit-the-dxp-source-code)
2.  [Create a patching tool profile for the source code.](#create-a-patching-tool-profile-for-the-source-code)
3.  [Patch the source.](patch-the-source-code)

### Commit the DXP Source Code [](id=commit-the-dxp-source-code)

The DXP source code is your initial code base. You'll commit it to version
control before patching it. 

1.  Unzip the DXP source code to a folder where you want to work with it.

2.  In a terminal, commit the DXP source code to a Git repository by executing
    these commands:

        cd [path to source code root folder]
        git init
        git add .
        git commit -a

Here's are the command descriptions:

-   `init` creates a Git repository for the current folder (i.e., the root
    folder) and all its contents.
-   `add` stages the root folder and its contents.
-   `commit` checks in the staged files.

### Create a Patching Tool Profile for the Source Code [](id=create-a-patching-tool-profile-for-the-source-code)

Next, you need a patching tool profile that points to your source code.  

1.  Execute the following command to create a profile. Replace `[profile]`
    with an arbitrary name for your profile. 

        patching-tool.sh auto-discovery [profile]
      
2.  In the profile properties file you generated, set the `patching.mode`
    property to `source` and set the `source.path` property to your source code
    path:

        patching.mode=source
	    source.path=[location of the Liferay source code]

It's time to apply the DXP patches you downloaded at the start of this tutorial. 

### Patch the Source Code [](id=patch-the-source-code)

Now you're ready to apply each patch. Committing the patches separately
facilitates finding where certain behaviors originate.

Execute these steps for each patch:

1.  Copy the patch ZIP file to the folder   
    `[LIFERAY_HOME]/patching-tool/patches`.

2.  Execute the `info` command to make sure your patch is listed. Replace
    `[profile]` with your DXP source code profile name:

        patching-tool.sh [profile] info

3.  Apply the patch by executing the `install` command on your profile:

        patching-tool.sh [profile] install

4.  Commit the patch's source code to your repository by executing the
    following command. Replace `[message]` with your commit's description. 

        git commit -a -m "[message]"

Repeat the previous steps for every source code patch. 

Now you're ready to debug DXP. 

## Step 4: Debug DXP [](id=debug-dxp)

Attach your favorite debugger to your DXP instance and start the server. See
your debugger's documentation for details. You're free to explore DXP inside and
out!

## Related Topics (id=related-topics)

[Troubleshooting](/develop/tutorials/-/knowledge_base/7-0/troubleshooting)

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
