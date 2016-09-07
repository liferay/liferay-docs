# Configuring the Patching Tool [](id=configuring-the-patching-tool)

Liferay DXP's patching tool is leveraged by LCS to apply its updates. If you're 
running a Liferay DXP bundle, then you should already have the patching tool 
installed. If you're not running a bundle, or your bundle doesn't have the 
patching tool installed, 
[click here to download it](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/patching-tool). 
Then see the user guide section on 
[how to install the patching tool](/discover/deployment/-/knowledge_base/7-0/patching-liferay). 
Once you have the patching tool installed, there are a few steps you need to 
complete before LCS can use it. Note that the commands below apply to Linux, 
Unix, and Mac systems. If you're running Windows, simply drop the `.sh` from 
each command that has it.

1. Navigate to the `patching-tool` directory on the command line. It's typically 
   located in the Liferay Home folder.

2. Let the patching tool discover your Liferay DXP installation by running 
   `patching-tool.sh auto-discovery`. 

3. Configure the patching tool by running `patching-tool.sh setup`. 

4. Patches downloaded through LCS are installed by the patching tool agent on 
   server startup. For the agent to start with your server, you need to set the 
   `javaagent` property in the JVM options. Make sure that you specify the 
   correct file path to the `patching-tool-agent.jar`. Here's an example of 
   setting the `javaagent` property: 

        -javaagent:../../patching-tool/lib/patching-tool-agent.jar

If your patching tool is installed in a location other than the Liferay Home 
folder, you must specify the path of the `patching-tool` folder as a JVM 
argument for the app server. This is done with the `patching.tool.home` 
property. For example:

    -Dpatching.tool.home=/opt/liferay-dxp-digital-enterprise-7.0/patching-tool/

Great! Now you're all set to activate your DXP instance with LCS. 
