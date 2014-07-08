# Portal Instances [](id=portal-instances-lp-6-2-use-useportal)

Liferay Portal allows you to run more than one portal instance on a single
server. The Portal Instances section of the Control Panel appears under the
Configuration heading. This sectin lets you manage multiple portal instances
from a single Liferay installation. Each portal instance's data is kept separate
from every other portal instance's data. All portal data, however, is kept in
the same database.

Each portal instance requires its own domain name. Liferay directs users to the
proper portal instance based on this domain name. So before you configure an
instance, configure its domain name in your network first. When you're ready to
add an instance, click the *Add* button here.

You'll be prompted for four fields and a check box:

**Web ID:** A general convention is to use the domain name for this. It's a
user-generated ID for the instance.

**Virtual Host:** Put the domain name you configured in your network here. When
**users** are directed to your Liferay server via this domain name, Liferay will
then be able to send them to the proper portal instance.

**Mail Domain:** Enter the domain name for the mail host for this instance.
Liferay will use this to send email notifications from the portal.

**Max Users:** Enter the maximum numbers of user accounts you would like your
portal instance to support.

**Active:** Use this check box to choose whether to create an active or an
inactive portal instance.

When you are finished filling out the form, click *Save*. Now navigate to the
portal using your new domain name. You will see you are brought to what looks
like a clean install of Liferay. This is your new portal instance which can now
be configured any way you like.
