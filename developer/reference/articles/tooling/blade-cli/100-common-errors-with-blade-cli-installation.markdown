---
header-id: common-errors-with-blade-cli
---

# Common Errors with Blade CLI

If Blade CLI isn't working as expected, you may find answers here.

The following issues are documented:

- [The blade command is not available in my CLI](#the-blade-command-is-not-available-in-my-cli)
- [I can't update my Blade CLI version](#i-cant-update-my-blade-cli-version)

Visit the appropriate section to learn more.

## The blade command is not available in my CLI

The Liferay Project SDK installer attempts to add JPM to your path. For Windows,
it uses the Windows registry. For Mac/Linux, it updates `.bashrc` or `.zshrc`. 

At a minimum, Mac/Linux users must open a new shell after the installer finishes
for the new features to be available. If, however, you're using a different
shell (e.g., Korn, csh) or you've customized your CLI via `.profile` or
some other configuration file, you must add JPM to your path manually. 

To add JPM's required `bin` folder, execute the appropriate command based on
your operating system.

macOS:

    echo 'export PATH="$PATH:$HOME/Library/PackageManager/bin"' >> ~/.bash_profile

Linux:

    echo 'export PATH="$PATH:$HOME/jpm/bin"' >> ~/.bash_profile

Once you open a new shell, the `blade` command should be available.

## I can't update my Blade CLI version

If you run `blade version` after updating, but don't see the expected version
installed, you may have two separate Blade CLI installations on your machine.
This is typically caused if you installed an earlier version of Blade CLI and
then used the Liferay Project SDK installer (at any time prior) to update the
older Blade CLI instance. This is not recommended. Doing this installs Blade CLI
in the global and user home folder of your machine. The latest Blade CLI update
process installs to your user home folder, so you must delete the legacy Blade
files in your global folder, if present. To do this, navigate to your
`GLOBAL_FOLDER/JPM4J` folder and delete

- `/bin/blade`
- `/commands/blade`

The newest Blade CLI installation in your user home folder is now recognized and
available.
