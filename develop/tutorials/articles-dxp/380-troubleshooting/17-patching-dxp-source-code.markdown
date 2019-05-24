---
header-id: patching-dxp-source-code
---

# Patching DXP Source Code

Auto mechanics, enthusiasts, and prospective owners ask about cars, "What's
under the hood?" Here are common reasons for asking that question:

-   Concern about an issue
-   Curiosity about the car's capability and inner-workings
-   Desire to improve or customize the car

You might have similar reasons for asking "What's under *DXP's* hood?" And
since you get access to DXP Digital Enterprise (DXP)'s source code, you can
attach a debugger and see it in action! Setting up the code locally is your
ticket to exploring DXP, investigating issues, and making improvements and
customizations.

Here's how:

[Step 1: Download DXP, the DXP source code, and patches](#download-dxp-the-dxp-source-code-and-patches)

[Step 2: Prepare DXP](#prepare-dxp)

[Step 3: Patch the DXP source code](#patch-the-dxp-source-code)

## Step 1: Download DXP, the DXP source code, and patches

1.  Download a DXP bundle (or DXP JARs) and the DXP source code for the version
    you're using from the
    [customer portal](https://web.liferay.com/group/customer/dxp/downloads/7-1).

2.  Download fix packs and their source code from
    [here](https://web.liferay.com/group/customer/dxp/downloads/7-1).
    Fix pack ZIP files that end in `-src.zip` contain a fix pack and source
    code.

Next install and configure DXP. DXP's patching tool lets you install fix packs
and fix pack source code. If you have a patched DXP installation already and
want to use it, skip to the section
[Patching the DXP Source Code](#patch-the-dxp-source-code).

## Step 2: Prepare DXP

Preparing DXP locally involves installing, configuring, and patching DXP. 

### Install and Configure DXP

Here's how to install and configure DXP: 

1.  [Install and Deploy DXP](/docs/7-1/deploy/-/knowledge_base/d/deploying-product) locally. 

2.  Start DXP.

3.  Configure DXP to use your database. 

4.  Stop DXP.

It's time apply the DXP patches you want.

### Patch DXP

Here's how to patch DXP:

1.  Copy all the patch ZIP files you want to
    `[LIFERAY_HOME]/patching-tool/patches`. The `-src.zip` fix pack files are
    best to use because they contain both the fix pack binaries and source code. 

2.  Open a command line to `[LIFERAY_HOME]/patching-tool`.

3.  Run the command `patching-tool.sh auto-discovery` to generate the default
    patching profile called `default.properties`. Make sure the profile's
    properties refer to your DXP installation. See the
    [patching tool documentation](/docs/7-1/deploy/-/knowledge_base/d/patching-tool)
    for more details.

    Here's an example profile:

        patching.mode=binary
        war.path=../tomcat-9.0.6/webapps/ROOT/
        global.lib.path=../tomcat-9.0.6/lib/ext/
        liferay.home=../

4.  To list all the patch files available in
    `[LIFERAY_HOME]/patching-tool/patches`, execute the following command:

        patching-tool.sh info

5.  Execute this command to install the patches:

        patching-tool.sh install

    The
    [patching tool documentation](/docs/7-1/deploy/-/knowledge_base/d/patching-tool)
    describes additional steps that might apply to your situation, such as
    creating database indexes.

It's time to prepare the DXP source code and patch source code. 

## Step 3: Patch the DXP Source Code

Unzip the DXP source code to where you want to work with it. 

Next you'll create a patching tool profile for your DXP source code. 

### Create a Patching Tool Profile for the Source Code

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

## Patch the Source Code

DXP's patching tool is safe and easy to use. Beyond installing patches, it has
these functions:

-   List a patch's code changes
-   List the issues
    ([LPS/LPE tickets](https://issues.liferay.com))
    a patch fixes
-   Revert a patch

See the following patching tool documentation for more details:

-   [Comparing Patch Levels](/docs/7-1/deploy/-/knowledge_base/d/working-with-patches#comparing-patch-levels)
-   [Removing or Reverting Patches](/docs/7-1/deploy/-/knowledge_base/d/working-with-patches#uninstalling-patches)

| In addition to using the patching tool to manage DXP source code, you can
| optionally manage it in a version control system such as
| [Git](https://git-scm.com/).
| 
| Here are commands for setting up the DXP source code in Git:
| 
|     cd [path to source code root folder]
|     git init
|     git add .
|     git commit -a
| 
| Here are the command descriptions:
| 
| -   `init` creates a Git repository for the current folder (i.e., the root
|     folder) and all its contents.
| -   `add` stages the root folder and its contents.
| -   `commit` checks in the staged files.
| 
| You can commit any code changes (e.g., DXP patches) to your Git repository.

The patching tool installs all patches and patch source code from the ZIP files
it finds in `[LIFERAY_HOME]/patching-tool/patches`. All your patches must be in
the `patches` folder for the patching tool to apply them. 

1.  Copy all the patch source ZIP files to 
    `[LIFERAY_HOME]/patching-tool/patches` if you haven't already copied them
    there. 

2.  Execute the `info` command to make sure it lists your patches. If a patch
    isn't listed, copy its ZIP file into the `patches` folder. Replace
    `[profile]` with your DXP source code profile name:

        patching-tool.sh [profile] info

3.  Apply the patches by executing the `install` command on your profile:

        patching-tool.sh [profile] install

Your DXP installation and source code is patched and ready to debug!

Attach your favorite debugger to your DXP instance and start the server. See
your debugger's documentation for configuration details.

Congratulations! You're free to explore DXP inside and out!

## Related Topics

[Troubleshooting FAQ](/docs/7-1/tutorials/-/knowledge_base/t/troubleshooting-faq)

[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)
