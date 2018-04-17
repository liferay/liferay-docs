# Managing Organizations

If you're not entirely sure what organizations are, or whether you need them in
your portal, start
[here](/discover/deployment/-/knowledge_base/7-0/organizations). 
This article gets right to the practical stuff: how to manage organizations.
Start by learning about adding organizations.

## Adding Organizations [](id=adding-organizations)

Now add an organization to the portal (perhaps start by adding the *Physical
Plant Department* organization to the Lunar Resort): 

1.  Click *Users and Organizations* from Control Panel &rarr; Users. 
2.  Go to the *Organizations* tab, and click the *Add* button. Fill out the Name
    field, at a minimum.
3.  To attach a site when you create an organization, click on *Organization
    Site* and check the *Create Site* box. If you don't know right
    now if your organization needs a site, that's fine. You can always add one
    later. 
<!-- Add a screenshot showing the Add Org UI -->
4.  If you're creating a child organization, use the Parent Organization
    *Select* button to select an organization in the system to be the direct
    parent. Click the *Remove* button to remove the currently configured parent.

5.  Click *Save* when finished filling out the Add Organization form.

As when creating a new user, once you submit the form a success message appears
and you have access to a new form which lets you enter additional information
about the organization. Organizations can have multiple email addresses, postal
addresses, web sites, and phone numbers associated with them.  The Services link
can be used to indicate the operating hours of the organization, if any.

<!-- Figure that shows a screenshot of the additional info for the org. -->

+$$$

**Tip:** After creating an organization, you should assign the desired user to
the Organization Owner Role. The Organization Owner can do everything that an
Organization Administrator can. In addition to their full administrative rights
within the organization, they can do these things:

- Appoint other users to be Organization Administrators 
- Appoint other users to be Organization Owners
- Remove the memberships of other Organization Administrators or Owners

Organization Administrators can't make these role assignments and can't manage
the memberships of other Organization Administrators or Owners.

$$$

## Editing Organizations [](id=editing-organizations)

To edit an organization go to the Users and Organizations section of the Control
Panel and click the *Organizations* tab. You should see any active portal users
and organizations listed. Click the *Actions* button next to an organization.
This shows a list of actions you can perform on this organization.

<!-- Screenshot of Actions Button menu options -->
- *Edit* lets you specify details about the organization, including addresses,
phone numbers, email addresses and websites.

- *Manage Site* lets you create and manage the public and private pages of the
organization's site. This only appears for organizations that have attached
sites.

- *Assign Organization Roles* lets you assign organization-scoped roles to
users. By default, Organizations are created with three roles: Organization
Administrator, Organization User and Organization Owner. You can assign one or
more of these roles to users in the organization. All members of the
organization automatically get the Organization User role so this role is hidden
when you click Assign Organization Roles.

- *Assign Users* lets you search and select users in the portal to be assigned
to this organization as members.

- *Add User* adds a new user in the portal and assigns the user as a member of
this organization.

- *Add Organization* lets you add a child organization to this
organization. This is how you create hierarchies of organizations with
parent-child relationships.

- *Delete* removes this organization from the portal. Make sure the
organization has no users in it first. You'll be prompted for confirmation that
you want to delete the organization. If there are users in the organization or
if there are suborganizations, you must remove the users and delete the
suborganizations before deleting the parent organization.

If you click the *View* button at the top of the Users and Organizations page
and select *View Hierarchy* you can view both a list of users who are members of
this organization and a list of all the sub-organizations of this organization.

<!-- See if a screenshot of this for the proposed Lunar Resort intranet
organizations could replace the textual hierarchy representation above -->

## Organization Types [](id=organization-types)

By default, @product@ only includes the *Organization* type. Configure
additional organization types using `portal.properties`. There are two main
reasons an enterprise wants to configure organization types:

1.  Organizations usually correlate to real-life hierarchical structures.
    Calling them by their real names is helpful for administrators and users. In
    the Major League Baseball (MLB) example, *League*, *Division*, and *Team*
    organization types are useful.
2.  Enforce control over which organizations can be top level organizations and
    the type of sub-organization allowed for each parent organization type. For
    example, MLB would not allow Division organization types to be
    sub-organizations of Team organizations.

![Figure 2: Make additional organization types available in the Control Panel by adding them to the `organizations.types` portal property.](../../images/organization-new-type.png)

Check out the portal properties that configure the default *Organization* type
on [docs.liferay.com](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Organizations).

To add another organization type called *League*, add this to
`portal-ext.properties`:

    organizations.types=organization,League
    organizations.rootable[League]=true
    organizations.children.types[League]=Division
    organizations.country.enabled[League]=true
    organizations.country.required[League]=false

So what do all those properties do?

- `organizations.types=organization,League`: adds League to the list of
    organization types that appear in the Add Organization menu.
- `organizations.rootable[League]=true`: enables Leagues as a top level
    organization. Limit League to sub-organization status by excluding this
    property.
- `organizations.children.types[League]=Division`: specifies Division
    as the only allowable sub-organization type for the League parent
    type.
- `organizations.country.enabled[League]=true`: enables the Country
    selection list field on the form for adding and editing League types.
- `organizations.country.required[League]=false`: specifies that the *Country*
    field is not required when adding a League.

Once you configure additional organization types in `portal-ext.properties`,
restart the server and you'll see your new type(s) in the Organizations section
of the Control Panel.

<!-- REPLACE [Figure 3: Add the Country select list field to the Add Organization form with the `organizations.country[my-org-type].enabled` property.](../../images/organization-country-selection.png) -->

Users can join or be assigned to sites when they share a common interest. Users
can be assigned to organizations when they fit into a hierarchical structure.
Users groups provide a more ad hoc way to group users than sites and
organizations. Let's look at them next.

