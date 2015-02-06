# SOAP Web Services

You can access Liferay's services via *Simple Object Access Protocol* (*SOAP*)
over HTTP. The *packaging* protocol is SOAP and the *transport* protocol is
HTTP. 

+$$$

**Note:** An authentication related token must accompany each Liferay web
service invocation. For details, read the section on service security layers
found earlier in this chapter. 

$$$

As an example, let's look at the SOAP web service classes for Liferay's
`Company`, `User`, and `UserGroup` portal services to execute the following:

1. List each user group to which user *test* belongs. 

2. Add a new user group named *MyGroup*. 

3. Add your portal's administrative user to the new user group. For
   demonstration purposes, we'll use an administrative user whose email address
   is `test@liferay.com`. 

We'll use these SOAP related classes: 

    import com.liferay.portal.model.CompanySoap;
    import com.liferay.portal.model.UserGroupSoap;
    import com.liferay.portal.service.http.CompanyServiceSoap;
    import com.liferay.portal.service.http.CompanyServiceSoapServiceLocator;
    import com.liferay.portal.service.http.UserGroupServiceSoap;
    import com.liferay.portal.service.http.UserGroupServiceSoapServiceLocator;
    import com.liferay.portal.service.http.UserServiceSoap;
    import com.liferay.portal.service.http.UserServiceSoapServiceLocator;

Can you see the naming convention for SOAP related classes? The classes above
all have suffixes `-ServiceSoapServiceLocator`, `-ServiceSoap`, and `-Soap`. The
`-ServiceSoapServiceLocator` class *finds* the `-ServiceSoap` by means of the
service's URL you provide. The `-ServiceSoap` class is the interface to the
services specified in the *Web Services Definition Language* (*WSDL*) file for
each service. The `-Soap` classes are the serializable implementations of the
models. Let's look at how to determine the URLs for these services. 

You can see a list of the services deployed on your portal by opening your
browser to the following URL: 

  `http://[host]:[port]/api/axis`

+$$$

**Note:** Prior to Liferay 6.2, there were two different URLs for accessing
remote Liferay services. `http://[host]:[port]/api/secure/axis` was for services
requiring authentication and `http://[host]:[port]/api/axis` was for services
that didn't require authentication. As of Liferay 6.2, all remote Liferay
services require authentication and the `http://[host]:[port]/api/axis` URL is
used to access them.

$$$

Here's the list of *secure* web services for `UserGroup`: 

- `Portal_UserGroupService` (wsdl)
    - `addGroupUserGroups`
    - `addTeamUserGroups`
    - `addUserGroup`
    - `deleteUserGroup`
    - `getUserGroup`
    - `getUserUserGroups`
    - `unsetGroupUserGroups`
    - `unsetTeamUserGroups`
    - `updateUserGroup`

+$$$

**Note:** Liferay's developers use *Service Builder* to expose their services
via JSON automatically. If you haven't used Service Builder before, please see
the
[Defining an Object-Relational Map with Service Builder](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/defining-an-object-relational-map-with-service-builder)
tutorial.

$$$

Each web service is listed with its name, operations, and a link to its WSDL
file. The WSDL file is written in XML and provides a model for describing and
locating the web service. 

Here's a WSDL excerpt of the `addUserGroup` operation of `UserGroup`: 

    <wsdl:operation name="addUserGroup" parametterOrder="name description
    publicLayoutSetPropertyId privateLayoutSetPropertyId">
        <wsdl:input message="impl:addUserGroupRequest" name="addUserGroupRequest"
        />
        <wsdl:outputMessage="impl:addUserGroupResponse" name="assUserGroupResponse"
        />
    </wsdl:operation>

To use the service, you pass in the WSDL URL along with your login credentials
to the SOAP service locator for your service. We'll show you an example in the
next section. 

Next, let's invoke the web service! 

## SOAP Java Client

You can easily set up a Java web service client to access Liferay's remote
services using Eclipse. Here's how: 

In Eclipse, you can use the *New* &rarr; *Web Service Client* wizard to
either create a new web service client project or add a client to an existing
project. You need to add a new web service client to your project for each
service that you need to consume in your client code. For our example, we'll
build a web service client to invoke the portal's `Company`, `User`, and
`UserGroup` services.

To create a new web service client project in Eclipse, click *File* &rarr;
*New* &rarr; *Other...*, then expand the *Web Services* category. Select *Web
Service Client*.

For each client you create, you're prompted to enter the service definition
(WSDL) for the desired service. Since our example web service client will use
Liferay Portal's `Company`, `User`, and `UserGroup` services, we'll need to
enter the following WSDLs: 

    http://localhost:8080/api/axis/Portal_CompanyService?wsdl

    http://localhost:8080/api/axis/Portal_UserService?wsdl

    http://localhost:8080/api/axis/Portal_UserGroupService?wsdl

![Figure 1: Service Definition](../../images/api-web-svc-wsdl.png)

When you specify a WSDL, Eclipse automatically adds the auxiliary files and
libraries required to consume that web service. Nifty! After you've created your
web service client project using one of the above WSDLs, you need to create
additional clients in the project using the remaining WSDLs. To create an
additional client in an existing project, right-click on the project and select
*New* &rarr; *Other* &rarr; *Web Service Client*. Click *Next*, enter the
WSDL, and complete the wizard.

The code below locates and invokes operations to create a new user group named
`MyUserGroup` and add a user with the screen name *test* to it. Create a
`LiferaySoapClient.java` file in your web service client project and add the
following code to it. If you create this class in a package other than the one
that's specified in the code below, replace the package with your package. To
run the client from Eclipse, make sure that your Liferay server is running,
right-click on the `LiferaySoapClient.java` class, and select *Run as Java
application*. Check your console to check that your service calls succeeded.

    package com.liferay.test;

    import java.net.URL;

    import com.liferay.portal.model.CompanySoap;
    import com.liferay.portal.model.UserGroupSoap;
    import com.liferay.portal.service.http.CompanyServiceSoap;
    import com.liferay.portal.service.http.CompanyServiceSoapServiceLocator;
    import com.liferay.portal.service.http.UserGroupServiceSoap;
    import com.liferay.portal.service.http.UserGroupServiceSoapServiceLocator;
    import com.liferay.portal.service.http.UserServiceSoap;
    import com.liferay.portal.service.http.UserServiceSoapServiceLocator;

    public class LiferaySoapClient {
            
        public static void main(String[] args) {

            try {
                String remoteUser = "test";
                String password = "test";
                String virtualHost = "localhost";

                String groupName = "MyUserGroup";

                String serviceCompanyName = "Portal_CompanyService";
                String serviceUserName = "Portal_UserService";
                String serviceUserGroupName = "Portal_UserGroupService";

                long userId = 0;

                // Locate the Company
                CompanyServiceSoapServiceLocator locatorCompany =
                    new CompanyServiceSoapServiceLocator();

                CompanyServiceSoap soapCompany =
                    locatorCompany.getPortal_CompanyService(
                        _getURL(remoteUser, password, serviceCompanyName,
                                true));

                CompanySoap companySoap =
                    soapCompany.getCompanyByVirtualHost(virtualHost);

                // Locate the User service
                UserServiceSoapServiceLocator locatorUser =
                    new UserServiceSoapServiceLocator();
                UserServiceSoap userSoap = locatorUser.getPortal_UserService(
                    _getURL(remoteUser, password, serviceUserName, true));

                // Get the ID of the remote user
                userId = userSoap.getUserIdByScreenName(
                    companySoap.getCompanyId(), remoteUser);
                System.out.println("userId for user named " + remoteUser +
                        " is " + userId);

                // Locate the UserGroup service
                UserGroupServiceSoapServiceLocator locator =
                    new UserGroupServiceSoapServiceLocator();
                UserGroupServiceSoap usergroupsoap =
                    locator.getPortal_UserGroupService(
                        _getURL(remoteUser, password, serviceUserGroupName,
                                true));

                // Get the user's user groups
                UserGroupSoap[] usergroups = usergroupsoap.getUserUserGroups(
                        userId);

                System.out.println("User groups for userId " + userId + " ...");
                for (int i = 0; i < usergroups.length; i++) {
                    System.out.println("\t" + usergroups[i].getName());
                }

                // Adds the user group if it does not already exist
                String groupDesc = "My new user group";
                UserGroupSoap newUserGroup = null;

                boolean userGroupAlreadyExists = false;
                try {
                    newUserGroup = usergroupsoap.getUserGroup(groupName);
                    if (newUserGroup != null) {
                        System.out.println("User with userId " + userId +
                                " is already a member of UserGroup " +
                                        newUserGroup.getName());
                        userGroupAlreadyExists = true;
                    }
                } catch (Exception e) {
                    // Print cause, but continue
                    System.out.println(e.getLocalizedMessage());
                }

                if (!userGroupAlreadyExists) {
                    newUserGroup = usergroupsoap.addUserGroup(
                            groupName, groupDesc);
                    System.out.println("Added user group named " + groupName);

                    long users[] = {userId};
                    userSoap.addUserGroupUsers(newUserGroup.getUserGroupId(),
                            users);
                }

                // Get the user's user groups
                usergroups = usergroupsoap.getUserUserGroups(userId);

                System.out.println("User groups for userId " + userId + " ...");
                for (int i = 0; i < usergroups.length; i++) {
                    System.out.println("\t" + usergroups[i].getName());
                }
            }
            catch (Exception e) {
                e.getLocalizedMessage();
            }
        }

            private static URL _getURL(String remoteUser, String password,
                            String serviceName, boolean authenticate)
                            throws Exception {
                    
                    // Unauthenticated url
                    String url = "http://localhost:8080/api/axis/" + serviceName;

                    // Authenticated url
                    if (authenticate) {
                            url = "http://" + remoteUser + ":" + password
                                            + "@localhost:8080/api/axis/"
                                            + serviceName;
                    }
                    
                    return new URL(url);
            }
        
    }

Running this client should produce output like the following example: 

    userId for user named test is 10196
    User groups for user 10196 ...
    java.rmi.RemoteException: No UserGroup exists with the key {companyId=10154,
    name=MyUserGroup}
    Added user group named
    Added user to user group named MyUserGroup
    User groups for user 10196 ...
        MyUserGroup

The output tells us the user had no groups, but was added to UserGroup
`MyUserGroup`.

You might be thinking, "But an error was thrown! We did something wrong!" Yes,
an error was thrown (`java.rmi.RemoteException:`), but we're sitting here as
cool as an ice cream sandwich all the same. The exception was thrown simply
because the `UserGroup` check was invoked before the `UserGroup` was created.
Because the very next line of the output says `Added user group named...`, we're
okay. Don't worry, be happy! 

Here are a few things to note about this example: 

- Authentication is done using HTTP Basic Authentication, which isn't
  appropriate for a production environment, since the password is unencrypted.
  It's simply used for convenience in this example. In production, you should
  set `company.security.auth.requires.https=false`. Please refer to Liferay's
  [`portal.properties`](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
  file for more information.
- The screen name and password are passed in the URL as credentials. 
- The name of the service (e.g. `Portal_UserGroupService`) is specified at the
  end of the URL. Remember that the service name can be found in the web service
  listing. 

The operations `getCompanyByVirtualHost()`, `getUserIdByScreenName()`,
`getUserUserGroups()`, `addUserGroup()` and `addUserGroupUsers()` are specified
for the `-ServiceSOAP` classes `CompanyServiceSoap`, `UserServiceSoap` and
`UserGroupServiceSoap` in the WSDL files. Information on parameter types,
parameter order, request type, response type, and return type are conveniently
specified in the WSDL for each Liferay web service. It's all there for you!

Next, let's implement a web service client in PHP. 

## SOAP PHP Client

You can write your client in any language that supports web services invocation.
Let's invoke the same operations we did when we created our Java client, this
time using PHP and a PHP SOAP Client: 

    <?php
        $userGroupName = "MyUserGroup2";
        $userName = "test";
        $clientOptions = array('login' => $userName, 'password' => 'test');

        // Add user group
        $userGroupClient = new 
            SoapClient(
                "http://localhost:8080/api/axis/Portal_UserGroupService?wsdl", 
                $clientOptions);
        $userGroup = $userGroupClient->addUserGroup($userGroupName,
            "This user group was created by the PHP client! ");
        print ("User group ID is $userGroup->userGroupId ");

        // Add user to user group
        $companyClient = new SoapClient(
            "http://localhost:8080/api/axis/Portal_CompanyService?wsdl",
            $clientOptions);
        $company = $companyClient->getCompanyByVirtualHost("localhost");
        $userClient = new SoapClient(
            "http://localhost:8080/api/axis/Portal_UserService?wsdl",
            $clientOptions);
        $userId = $userClient->getUserIdByScreenName($company->companyId,
            $userName);
        print ("User ID for $userName is $userId ");
        $users = array($userId);
        $userClient->addUserGroupUsers($userGroup->userGroupId, $users);

        // Print the user groups to which the user belongs
        $userGroups = $userGroupClient->getUserUserGroups($userId);
        print ("User groups for user $userId ... ");
        foreach($userGroups as $ug)
            print ("$ug->name, $ug->userGroupId ")
    ?>

Remember, you can implement a web service client in any language that supports
using SOAP web services. To learn how to implement OAuth so that
you can access third-party services, please see the
[Authorizing Access to Services with OAuth](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/authorizing-access-to-services-with-oauth) 
tutorial.
