# Using the Patching Tool [](id=patching-tool)

If you're using a @product@ bundle, the Patching Tool is already installed. When
an update is necessary to install a patch, the Patching Tool automatically
asks for an update.

You follow the same procedure whether you're installing or upgrading the
Patching Tool. Once you've obtained it from the Customer Portal, unzip it
anywhere in the file system. Generally it's a good idea to keep it together with
the @product@ installation.

Upgrading is easy: override the previous Patching Tool with newest one by
unzipping it on top of the old version. 

## Executables [](id=executables)

The Patching Tool is a Java based application. The distribution contains shell/
.bat scripts to make it easier to use. On Unix systems you can run

    ./patching-tool.sh parameters

On Windows, run

    patching-tool parameters

The latter method appears in the examples below. On Unix, replace the name of
the executable before running the scripts.
