# Patching DXP Source Code [](id=patching-dxp-source-code)

Auto mechanics, car enthusiasts, and concerned car owners commonly ask "What's
under the hood?" Their reasons for asking could be as follows:

-   Concern about an issue.
-   Curiosity about the car's capability and inner-workings.
-   Desire to improve or customize the car.

As a developer, you might have similar reasons for asking "What's under the
*DXP's* hood?" Since DXP Digital Enterprise (DXP) is open-source, you can
examine its code and see it in action! Setting up the DXP source code for
debugging is your ticket to investigating issues, exploring DXP, making
improvements and customizations.

Here are the setup steps:

1.   [Download DXP, the DXP source code, and patches](#download-dxp-the-dxp-source-code-and-patches)
2.   [Prepare DXP](#prepare-dxp)
3.   [Patch the DXP source code](#patch-the-dxp-source-code)

## Step 1: Download DXP, the DXP source code, and patches [](id=download-dxp-the-dxp-source-code-and-patches)

From the
[customer portal](https://web.liferay.com/group/customer/dxp),
download a DXP bundle (or DXP JARs), and the DXP source code for the version
you're using. For example, here are ZIP files for a DXP / Tomcat bundle and DXP
code:

-   `liferay-dxp-digital-enterprise-tomcat-7.0-sp4-20170705142422877.zip`
-   `liferay-dxp-digital-enterprise-src-7.0-sp4-20170814143552654.zip`

Download fix packs and their source code from
[here](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/fix-packs).
For example, this ZIP file contains a fix pack and source code:

-   `liferay-fix-pack-de-29-7010-src.zip`

+$$$

**Tip**: Fix pack `-src.zip` files contain both the fix pack and its source
code.  

$$$

Next, you'll install and configure DXP since it has the patching tool. If
you have a patched DXP installation already and want to debug it, skip to the
section on
[patching the DXP source code](#patch-the-dxp-source-code).

## Step 2: Prepare DXP [](id=prepare-dxp)

Setting up DXP locally is straightforward. 

### Install and Configure DXP [](id=install-and-configure-dxp)

Follow these steps to install and configure DXP: 

1.   [Install and Deploy DXP](/discover/deployment/-/knowledge_base/7-0/deploying-liferay-portal)
locally. 

2.  Start DXP.

3.  Configure DXP to use your database. 

4.  Stop DXP.

It's time apply the DXP patches you want.

### Patch DXP [](id=patch-dxp)

Here are the steps for patching DXP:

1.  Copy the patch ZIP files to the folder
    `[LIFERAY_HOME]/patching-tool/patches`.

2.  Open a terminal to the folder `[LIFERAY_HOME]/patching-tool`.

3.  Generate a default patching profile called `default.properties` by running
    `patching-tool.sh auto-discovery`. Make sure the profile's properties
    refer to your DXP installation. See the
    [patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    for more details.

    Here's an example profile:

    	patching.mode=binary
    	war.path=../tomcat-8.0.32/webapps/ROOT/
    	global.lib.path=../tomcat-8.0.32/lib/ext/
    	liferay.home=../

4.  Execute the command `patching-tool.sh info` to list the installed
    patches and patches available in your `patches` folder.

5.  Install all patches available in the folder
    `[LIFERAY_HOME]/patching-tool/patches` by executing this command:

        patching-tool.sh install

    The
    [patching tool documentation](/discover/deployment/-/knowledge_base/7-0/patching-tool)
    explains additional steps, such as creating database indexes, that might
    apply to your situation.

It's time to prepare the DXP source code for debugging. 

## Step 3: Patch the DXP Source Code [](id=patch-the-dxp-source-code)

This step involves preparing the DXP source code locally for patching,
examining, and attaching a debugger. Committing the code to a version control
system, such as [Git](https://git-scm.com/), facilitates reverting back to
different patch levels. It's completely optional, but can be helpful. 

No matter whether you're going to use version control, unzip the DXP source code
to where you want to work with it. 

If you want to manage the DXP and patch source code using version control, read
the next section. Otherwise, skip it. 

### Commit the Source Code to a Git repository

Here are commands for committing the source code:

    cd [path to source code root folder]
    git init
    git add .
    git commit -a

Here's are the command descriptions:

-   `init` creates a Git repository for the current folder (i.e., the root
    folder) and all its contents.
-   `add` stages the root folder and its contents.
-   `commit` checks in the staged files.

+$$$

Note, you can use Git's `branch` command to create branches for various patch
levels and use the `reset` command to go back to earlier patch levels. 

$$$

Next, you'll create a patching tool profile for your source code. 

### Create a Patching Tool Profile for the Source Code [](id=create-a-patching-tool-profile-for-the-source-code)

Here are steps for creating a patching tool profile that referes to your source
code.  

1.  Execute the following command to create a profile. Replace `[profile]` with
    a name for your profile. 

        patching-tool.sh auto-discovery [profile]
      
2.  In the profile properties file generated in the previous step, set the
    `patching.mode` property to `source` and set the `source.path` property to
    your source code path:

        patching.mode=source
	    source.path=[location of the Liferay source code]

It's time to apply the DXP patches you downloaded at the start of this tutorial. 

## Patch the Source Code [](id=patch-the-source-code)

You're ready to apply patches. You can apply any number of patches at the same
time. If you're using Git, committing patches separately facilitates switching
between different patch levels. Use the following steps to apply (and optionally
commit to Git) patches to the DXP source code.  

1.  Copy the patch source ZIP file(s) to the folder   
    `[LIFERAY_HOME]/patching-tool/patches`.

2.  Execute the `info` command to make sure your patch(es) is listed. Replace
    `[profile]` with your DXP source code profile name:

        patching-tool.sh [profile] info

3.  Apply the patch(es) by executing the `install` command on your profile:

        patching-tool.sh [profile] install

4.  Optionally, commit the patch source code to Git by executing the following
    command. Replace `[message]` with your commit's description. 

        git commit -a -m "[message]"

If you're applying patches one at a time, repeat the above steps for each patch. 

+$$$

**Note**: The patching tool's `diff` command shows patch source code changes, modified files, and fixed issues (LPS/LPE tickets). 

$$$

Now you're ready to debug DXP. Attach your favorite debugger to your DXP
instance and start the server. See your debugger's documentation for
configuration details.

Congratulations! You're free to explore DXP inside and out!

## Related Topics (id=related-topics)

[Troubleshooting](/develop/tutorials/-/knowledge_base/7-0/troubleshooting)

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
