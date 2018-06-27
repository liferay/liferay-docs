# Account Management [](id=account-management)

The Beryl Customer Portal's distinguishing feature is its account management
system. In a B2C scenario, product selection, order placement, and payment are
all handled by the consumer. B2B transactions, however, often have more complex
requirements: one employee might select products to buy, another might approve
that selection, and yet another might handle payment. By setting up a unique
account not for each user but for each customer organization, you can help
multiple users with different roles to collaborate on the buying process.

## Setting up an Account [](id=setting-up-an-account)

An account is a type of organization---specifically a sub-organization---which
has as its parent your store site's organization.

Your store site's organization was created automatically when you created the
site using the Beryl site initializer (see [Customer
Portal](/web/liferay-emporio/documentation/-/knowledge_base/1-0/customer-portal)).
To set up a new account, follow these steps:

1.  Go to *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*. Click
    on the *Organizations* tab.

2.  Click on the Organization with the name of your store site. Then click on
    the ![Add](../../images/icon-add.png) button and select *New Account* from the
    menu.

3.  Complete the *Add Account* form, just as you would to create an
    [Organization](/discover/portal/-/knowledge_base/7-1/managing-organizations).

+$$$

*Note:* When you open your store site's organization, three demo accounts are
displayed. You can use them to explore the site, edit them and assign the to
customers, or simply delete them.

$$$

## Adding Users to an Account [](id=adding-users-to-an-account)

Follow these steps to assign existing users to an account:

1.  Go to *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*. Click
    on the *Organizations* tab, select your store site's organization, and click
    on the account to which you want to add users.

2.  Click on the [Add](../../images/icon-add.png) button and select *Assign
    Users* from the menu.

3.  In the pop-up that appears, check the box next to each user you want to give
    access to the account. Click *Done*.

Follow these steps to add a new user to an account:

1.  Go to *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*. Click
    on the *Organizations* tab, select your store site's organization, and click
    on the account to which you want to add users.

2.  Click on the [Add](../../images/icon-add.png) button and select *New User*
    from the menu.

3.  Fill out the *Add User* form. At minimum enter a first and last name,
    a screen name, and an email address. Click Save.

## Account Roles [](id=account-roles)

Once you have added users to an account, you need to assign roles to them. Roles
can be customized any way you want (see 
[Roles and Permissions](/discover/portal/-/knowledge_base/7_1/roles-and-permissions)).
By default, however, the customer portal uses three roles for customer users:
Buyers, Order Managers, and Account Managers.

**Buyers** are customer users who select what products to buy. By default,
buyers have permission to view and checkout orders.

**Order Managers** are customer users who oversee the purchasing process. In
addition to the permissions that a buyer has, they can delete, manage, and
approve orders.

**Account Managers** are customer users who oversee the account. They can assign
roles to users and determine which users have access to the account.

Keep in mind that all three of these roles are meant to be occupied by
*customers*. The seller's site administrators still retain ultimate control over
account management.

Site Administrators can assign these roles to users just as they would any other
organization role (see 
[Managing Roles](/discover/portal/-/knowledge_base/7_1/managing-roles). However, you can
also allow your customer's account managers to take care of this job. An account
manager cannot add new users to your system, but she can add existing users to
her account and assign permissions to them.

An account manager can follow these steps to add a user to an account:

1.  From the Beryl site's main page, click on the *Account Management* tab and then
    the *Members* sub-tab.

2.  Click on the ![Add](../../images/icon-add.png) button. Enter the email
    address of an existing user and click *Add*. Then click *Save*.

An account manager can follow these steps to assign a role to an account member:

1.  From the *Account Management* &rarr; *Members* tab, click on the
    ![Options](../../images/icon-options.png) button and select *Edit* from the
    menu.

2.  Click on the *Entitlements* sub-tab and check the box next to whichever
    roles you want to assign to the user.
