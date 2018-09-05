# Using the Patching Tool [](id=patching-tool)

The Patching Tool installs, removes, compares, and prepares @product@ patches.
It is pre-installed in @product@ bundles, easy to install into @product@ manual
installations, and easy to update. The Patching Tool's executable scripts
facilitate patching. 

Here are the essentials to get started using the Patching Tool:

- [Installing the Patching Tool](#installing-the-patching-tool) (for manual installations only)
- [Executables](#executables)

## Installing the Patching Tool [](id=installing-the-patching-tool)

@product@ bundles come with the Patching Tool pre-installed (in `[Liferay
Home]/patching-tool`) and pre-configured with the default settings. Skip this
section if you're using a bundle. 

If you installed @product@ manually, however, you must also install the Patching
Tool manually. 

1.  Download the Patching Tool from the
    [Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1/patching-tool).

2.  Unzip the Patching Tool to your
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay-portal#liferay-home)
    folder (recommended) or to another folder. 

After installing the Patching Tool, you must
[configure it to use your @product@ installation](/discover/deployment/-/knowledge_base/7-1/configuring-the-patching-tool).
The `patching-tool` folder you extracted from the Patching Tool ZIP file
contains the Patching Tool, including its executable scripts. 

## Executables [](id=executables)

The Unix shell and Windows batch scripts distributed with the Patching Tool make
it easier to use. On Unix systems, run

    ./patching-tool.sh parameters

On Windows, run

    patching-tool parameters

The Windows command `patching-tool` is used in the examples that follow. On
Unix, replace the name of the executable before running the commands. 

Installing patches is next! 
