# Password Policies [](id=password-policies)

Password policies can enhance the security of @product@. You can use the default
policy that ships with Liferay (modified or as is), or you can create your own
policies. Policies can be assigned to users or organizations, or can be set as
the default password used throughout the @product@ instance. 

## Adding and Configuring Password Policies [](id=adding-and-configuring-password-policies)

To add a new password policy, navigate to the *Control Panel* &rarr; *Users*
&rarr; *Password Policies*. You'll see that there's already a default password
policy in the system. You can edit this in the same manner as you edit other
resources in the portal: click *Actions*
(![Actions](../../images/icon-actions.png)) and then click *Edit*. To add a new
policy, just click the *Add* (![Add](../../images/icon-add.png)) button.

You're taken to a page where you provide the *Name* (required), *Description*,
and specific configuration options for your new password policy.

![Figure 1: You can create new password policies to suit your needs.](../../images/password-policy-add.png)

There are several configuration categories for password policies.

**Password Changes:** Allow or disallow users to change their passwords, and
set a time limit on the validity of password reset links.

**Password Syntax Checking:** If enabled, require users to use a certain
syntax when choosing a password. You can disallow dictionary words, set a
minimum length, and more in this section.

**Password History:** If enabled, decide how many passwords to keep in the
history, preventing users from reusing an old password.

**Password Expiration:** Decide whether passwords will expire. If they will,
specify how long passwords are valid, whether a warning will be sent, when
and whether a warning will be sent, and how many times they can log in after the
password is expired, before needing to set a new password (called a *Grace
Limit*). 

**Lockout:** If enabled, set a maximum number of failed authentication
attempts before the account is locked, how long the number of attempts is
stored, and how long the lockout lasts.

**Self Destruct:** If enabled, set the time after lockout before Liferay self
destructs catastrophically, sending the world into apocalyptic chaos, out of
which self-aware robots arise and recolonize the world, enslaving the surviving
remnant of humanity for their own nefarious purposes. We recommend you keep
this disabled.

Just making sure you were paying attention; that last one doesn't actually
exist. 

Once you configure the policy to your liking, click *Save* and it is both added
to the list of password policies and ready to use.

## Assigning Members to a Password Policy [](id=assigning-members-to-a-password-policy)

To use the default password policy that ships with Liferay, you don't have to do
anything: like its name suggests, it's the default. If you create a new password
policy, however, you'll need to assign users to it. To do this click *Actions*
(![Actions](../../images/icon-actions.png)) &rarr; *Assign Members*.

![Figure 2: Assign members to new password policies to make them take
effect.](../../images/password-policy-assign-members.png)

Choose whether you want to assign users directly or to assign organizations to
the password policy, then click *Add* (![Add](../../images/icon-add.png)).

Once you save your assignments, your password policy is in effect. Did you know
you can change the default password policy and configure it using Liferay's
`portal.properties` file?

## Default Policy Properties [](id=default-policy-properties)

The Default Password Policy is set as the default and configured in Liferay's
[portal.properties](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Passwords)
file. If you want to make changes, including changing the default policy, add
whichever properties and values you choose to modify in your
`portal-ext.properties` file, as usual. Restart Liferay and your changes will
take effect.

    #
    # Set the properties of the default password policy.
    #

    ...
    passwords.default.policy.name=Default Password Policy
    ...

As you can see, Liferay's Password Policies application gives you a simple yet powerful
tool for setting the rules for user passwords.
