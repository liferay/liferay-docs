# Account Management [](id=account-management)

The Beryl Customer Portal's distinguishing feature is its account management
system. In a B2C scenario, product selection, order placement, and payment are
all handled by the consumer. B2B transactions, however, often have more complex
requirements: one employee might select products to buy, another might approve
that selection, and yet another might handle payment. By setting up a unique
account not for each user but for each customer organization, you can help
multiple users with different roles to collaborate on the buying process.

## Setting up an Account [](id=setting-up-an-account)

An account is a type of Organization---specifically a sub-organization---which
has as its parent your store Site's Organization.

Your store Site's Organization was created automatically when you created the
Site using the Beryl site initializer (see 
[Customer Portal](/web/liferay-emporio/documentation/-/knowledge_base/1-0/customer-portal)).
To set up a new account, follow these steps:

1.  Go to *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*. Click
    on the *Organizations* tab.

2.  Click on the Organization with the name of your store Site. Then click
    ![Add](../../images/icon-add.png) and select *New Account* from the menu.

3.  Complete the *Add Account* form, just as you would to create an
    [Organization](/discover/portal/-/knowledge_base/7-1/managing-organizations).

+$$$

*Note:* When you open your store Site's Organization, three demo accounts
appear. You can use them to explore the Site, edit them and assign them to
customers, or delete them.

$$$

## Adding Users to an Account [](id=adding-users-to-an-account)

Follow these steps to assign existing users to an account:

1.  Go to *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*. Click
    on the *Organizations* tab, select your store Site's Organization, and click
    on the account to which you want to add users.

2.  Click [Add](../../images/icon-add.png) and select *Assign Users* from the
    menu.

3.  In the pop-up that appears, check the box next to each user that should have
    access to the account. Click *Done*.

Follow these steps to add a new user to an account:

1.  Go to *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*. Click
    on the *Organizations* tab, select your store Site's Organization, and click
    on the account to which you want to add users.

2.  Click [Add](../../images/icon-add.png) and select *New User*
    from the menu.

3.  Fill out the *Add User* form. At minimum enter a first and last name,
    a screen name, and an email address. Click *Save*.

## Account Roles [](id=account-roles)

Once you have added users to an account, you must assign Roles to them. Roles
can be customized any way you want (see 
[Roles and Permissions](/discover/portal/-/knowledge_base/7-1/roles-and-permissions)). By
default, however, the customer portal uses three Roles for customer users:
Buyers, Order Managers, and Account Managers.

**Buyers** are customer users who select what products to buy. By default,
buyers have permission to view and checkout orders.

**Order Managers** are customer users who oversee the purchasing process. In
addition to the permissions that a buyer has, they can delete, manage, and
approve orders.

**Account Managers** are customer users who oversee the account. They can assign
Roles to users and determine which users have access to the account.

Keep in mind that all three of these are *customer* Roles. The seller's Site
administrators still retain ultimate control over account management.

Site Administrators can assign these Roles to users just as they would any other
Organization Role (see 
[Managing Roles](/discover/portal/-/knowledge_base/7-1/managing-roles). However,
you can also allow your customer's account managers to do this. Account managers
cannot add new users to your system, but they can add existing users to their
accounts and assign permissions to them.

An account manager can follow these steps to add a user to an account:

1.  From the Beryl site's main page, click the *Account Management* tab and then
    the *Members* sub-tab.

2.  Click ![Add](../../images/icon-add.png). Enter the email address of an
    existing user and click *Add*. Then click *Save*.

Here's how an account manager can assign a Role to an account member:

1.  From the *Account Management* &rarr; *Members* tab, click 
    ![Options](../../images/icon-options.png) and select *Edit* from the
    menu.

2.  Click on the *Entitlements* sub-tab and check the box next to whichever
    Roles you want to assign to the user.
