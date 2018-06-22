# Managing Organizations [](id=managing-organizations)

If you're not entirely sure what Organizations are or whether you need them in
your portal, start
[here](/discover/deployment/-/knowledge_base/7-0/organizations). 
This article gets right to the practical stuff: how to manage Organizations.

## Adding Organizations [](id=adding-organizations)

Add an Organization (perhaps start by adding the *Physical Plant Department*
organization to the Lunar Resort): 

1.  Click *Users and Organizations* from Control Panel &rarr; Users. 

2.  Go to the *Organizations* tab and click the *Add* button. Fill out the Name
    field at a minimum.

3.  To attach a Site when you create an Organization, click on *Organization
    Site* and check the *Create Site* box. If you don't know right now if your
    organization needs a site, that's fine. You can always add one later. 

    ![Figure 1: Fill out the add Organization form and click *Save*. ](../../../images/orgs-add-organization-site.png)

4.  If you're creating a child Organization, use the Parent Organization
    *Select* button to select an Organization in the system to be the direct
    parent. Click the *Remove* button to remove the currently configured parent.

5.  Click *Save* when finished filling out the Add Organization form.

As when creating a new user, once you submit the form a success message appears
and you have access to a new form which lets you enter additional information
about the Organization. Organizations can have associated multiple email
addresses, postal addresses, web sites, and phone numbers. The Services link can
be used to indicate the operating hours of the Organization, if any.

+$$$

**Tip:** After creating an Organization, assign the desired user to the
Organization Owner Role. The Organization Owner can do everything that an
organization Administrator can. In addition to their full administrative rights
within the Organization, they can do these things:

- Appoint other Users to be Organization Administrators 
- Appoint other Users to be Organization Owners
- Remove the memberships of other Organization Administrators or Owners

Organization Administrators can't make these Role assignments and can't manage
the memberships of other Organization Administrators or Owners.

$$$

## Editing Organizations [](id=editing-organizations)

To edit an Organization, go to the Users and Organizations section of the
Control Panel and click the *Organizations* tab. All active Organizations are
listed. Click the *Actions* button next to an Organization. This shows a list of
actions you can perform on this Organization.

- *Edit* lets you specify details about the Organization, including addresses,
  phone numbers, email addresses and websites.

- *Manage Site* lets you create and manage the public and private pages of the
  Organization's site. This only appears for Organizations that have attached
  Sites.

- *Assign Organization Roles* lets you assign Organization-scoped Roles to
  Users. By default, Organizations are created with three Roles: Organization
  Administrator, Organization User and Organization Owner. You can assign one or
  more of these Roles to Users in the Organization. All members of the
  Organization automatically get the Organization User Role so this Role is
  hidden when you click Assign Organization Roles.

- *Assign Users* lets you search and select Users to be assigned to this 
  Organization as members.

- *Add User* adds a new User and assigns the User as a member of
  this Organization.

- *Add Organization* lets you add a child Organization to this
  Organization. This is how you create hierarchies of Organizations with
  parent-child relationships.

- *Delete* removes this Organization. Make sure the
  Organization has no Users in it first. You'll be prompted for confirmation
  that you want to delete the Organization. If there are Users in the
  Organization or if there are sub-Organizations, you must remove the Users and
  delete the sub-Organizations before deleting the parent Organization.

If you click the Organization name you can view both a list of Users who are
members of this Organization and a list of all the sub-Organizations of this
Organization.

## Organization Types [](id=organization-types)

By default, @product@ only includes the *Organization* type. Configure the
existing type or add additional types using the aptly named Organization Type
entry in System Settings. There are two main reasons to configure Organization
types:

1.  Organizations usually correlate to real-life hierarchical structures.
    Calling them by their real names is helpful for administrators and Users. In
    the Major League Baseball (MLB) example, *League*, *Division*, and *Team*
    Organization types are useful.

2.  Enforce control over which Organizations can be top level Organizations and
    the type of sub-Organization allowed for each parent Organization type. For
    example, MLB would not allow Division Organization types to be
    sub-Organizations of Team Organizations.

![Figure 2: Create new organization types through the System Settings entry called Organization Types.](../../../images/orgs-organization-type.png)

Check out the configuration options that configure the default *Organization*
type and then configure an additional type.

To add another Organization type called *League*, enter these options into the
configuration form:

Name: *League*
: Adds League to the list of Organization types that appear in the Add
Organization menu.

Country Enabled: *True*
: Enables the Country selection list field on the form for adding and editing
League types.

Country Required: *False*
: Specifies that the *Country* field is not required when adding a League.

Rootable: *True*
: Enables Leagues as a top level Organization. Limit League to sub-Organization
status by excluding this property.

Children Types: *Division*
: Specifies Division as the only allowable sub-Organization type for the League
parent type.

Once you configure additional Organization types and click Save, you'll find
your new type(s) available for selection in the Add Organization form.

![Figure 3: Custom configuration tpyes are available in the Add Organization
form.](../../../images/orgs-add-custom-organization.png)

Users can join or be assigned to Sites when they share a common interest. Users
can be assigned to Organizations when they fit into a hierarchical structure.
User groups provide a more ad hoc way to group users than sites and
Organizations. You'll look at them next.

