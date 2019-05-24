# Configuring Permissions for Change Lists

Configuring permissions for who can access and use change lists is a critical
part of managing your @product@ instance's content management.

You can assign users access to the Change Lists framework by defining roles
for it. Since a change list is not tied to a specific site, site roles are
not an option; instead, you must assign regular roles for it.

To do this, complete the following steps:

1.  Navigate to the Control Panel &rarr; *Configuration* &rarr; *System
    Settings* &rarr; *Infrastructure* &rarr; *Change Tracking*.

2.  Assign the role names you wish to grant access to the Change Lists
    framework.

3.  Click *Save*.

![Figure 1: Assign regular roles access to the Change Lists framework.](../../../images/change-tracking-roles.png)

Users with the roles defined for Change Tracking have access to all Change Lists
features. Therefore, they can access the UI, can enable/disable Change Lists,
and can publish change lists to the production environment. Be careful who is
assigned Change Tracking permissions because they will have direct access to
editing your live production environment!

| **Note:** Users granted access to the Change Lists framework can access other
| user's change lists. This is a temporary behavior. A collaboration feature is
| planned to allow users to work together on change lists. This is currently in
| development.

Now you know all about Change Lists' permissioning system.
