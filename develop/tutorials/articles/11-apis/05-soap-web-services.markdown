# SOAP Web Services [](id=soap-web-services)

You can access Liferay's services via *Simple Object Access Protocol* (*SOAP*)
over HTTP. The *packaging* protocol is SOAP and the *transport* protocol is
HTTP.

As an example, let's look at the SOAP web service classes for Liferay's
`Company`, `User`, and `UserGroup` portal services to execute the following:

1. List each UserGroup to which user *test* belongs. 

2. Add a new UserGroup named *MyGroup*. 

3. Add user *test* to the UserGroup. 

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
browser to a URL following one of these formats: 

- For your secure services (i.e., serevices requiring authentication) use
  `http://[host]:[port]/api/secure/axis`. 

- For your sevices that don't require authentication, use
  `http://[host]:[port]/api/axis`. 

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

**Note:** Liferay's developers use a tool called *Service Builder* to expose
their services via SOAP automatically. If you're interested in using Service
Builder, check out the *Service Builder* chapter in this guide. 

$$$

Each web service is listed with its name, operations, and a link to its WSDL
file. The WSDL file is written in XML and provides a model for describing and
locating the web service. 

Here's the WSDL Excerpt for the `addUserGroup` operation of `UserGroup`: 

    <wsdl:operation name="addUserGroup" parametterOrder="name description
    publicLayoutSetPropertyId privateLayoutSetPropertyId">
        <wsdl:input message="impl:addUserGroupRequest" name="addUserGroupRequest"/>
        <wsdl:outputMessage="impl:addUserGroupResponse" name="assUserGroupResponse"/>
    </wsdl:operation>

<!--Jim, I removed the screenshot of this XML and just typed it out. Is it okay?-->

To use the service, you pass in the WSDL URL along with your login credentials
to the SOAP service locator for your service. We'll show you an example in the
next section. 

Next, let's invoke the web service! 

## SOAP Java Client [](id=soap-java-client)

A Java web service client can easily be set up using Eclipse IDE. Here's how: 

In Eclipse, add a new Web Service Client to your project for each service you
plan to consume in your client code. For our purposes, the client we're building
needs a Web Service Client for the portal's `Company`, `User`, and `UserGroup`
services. 

To add your Web Service Clients in Eclipse IDE, click *New* &rarr; *Other...*, then
expand the *Web Services* category. Click *Web Service Client*.

For each client you create, you're prompted to enter the service definition
(WSDL) for the desired service. Here's an example WSDL: 

    http://localhost:8080/api/axis/Portal_UserService?wsdl

![Figure 11.3: Service Definition](../../images/api-web-svc-wsdl.png)

<!-- Again, too many screenshots. -Rich -->
<!--Jim I removed three screenshots that were too close otgether to respond to
Rich's comments and added some detail to the text. -->

With the WSDL specified, Eclipse automatically adds the auxiliary files and
libraries required to consume that web service.  Nifty!

Here's the code that locates and invokes operations to add a new UserGroup named
`MyUserGroup` and assign to it a `User` with screen name *test*: 

    import java.net.URL;

    import com.liferay.portal.model.CompanySoap; import
    com.liferay.portal.model.UserGroupSoap; import
    com.liferay.portal.service.http.CompanyServiceSoap; import
    com.liferay.portal.service.http.CompanyServiceSoapServiceLocator; import
    com.liferay.portal.service.http.UserGroupServiceSoap; import
    com.liferay.portal.service.http.UserGroupServiceSoapServiceLocator; import
    com.liferay.portal.service.http.UserServiceSoap; import
    com.liferay.portal.service.http.UserServiceSoapServiceLocator;

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
                } catch (Exception excep) {
                    // print cause, but continue
                    System.out.println(excep.getLocalizedMessage());
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
                e.printStackTrace();
            }
        }

        private static URL _getURL(String remoteUser, String password,
            String serviceName, boolean authenicate)
        throws Exception {
            //Unauthenticated url
            String url = "http://localhost:8080/api/axis/" + serviceName;

            //Authenticated url
            if (authenicate) {
                url = "http://" + remoteUser + ":" + password +
                    "@localhost:8080/api/secure/axis/" + serviceName;
            }
            return new URL(url);
        }
    }

Running this client should produce output like the following example: 

    userId for user named test is 10196
    User groups for user 10196 ...
    java.rmi.RemoteException: No UserGroup exists with the key {companyId=10154, name=MyUserGroup}
    Added user group named
    Added user to user group named MyUserGroup
    User groups for user 10196 ...
        MyUserGroup

The output tells us the user had no groups, but was added to UserGroup
`MyUserGroup`. 

You might be thinking, "But an error was thrown! We did something wrong!" Yes,
an error was thrown (`java.rmi.RemoteException:`), but we're sitting here as
cool as an iced cream sandwich all the same. The exception was thrown simply
because the `UserGroup` check was invoked before the `UserGroup` was created.
Because the very next line of the output says `Added user group named...`, we're
okay. Don't worry, be happy! 

Here are a few things to note about the URL: 

- It's a *secure* (authenticated) URL for the service. Authentication is done
using HTTP Basic Authentication, which isn't appropriate for a production
environment, since the password is unencrypted. It's simply used for convenience
in this example. 

- The screen name and password are passed in as credentials. 
- The name of the service (e.g. `Portal_UserGroupService`) is specified at the
  end of the URL. Remember that the service name can be found in the web
  service listing.

The operations `getCompanyByVirtualHost()`, `getUserIdByScreenName()`,
`getUserUserGroups()`, `addUserGroup()` and `addUserGroupUsers()` are specified
for the `-ServiceSOAP` classes `CompanyServiceSoap`, `UserServiceSoap` and
`UserGroupServiceSoap` in the WSDL files. Information on parameter types,
parameter order, request type, response type, and return type are conveniently
specified in the WSDL for each Liferay web service. It's all there for you!

Next let's implement a web service client implemented in PHP. 

## SOAP PHP Client [](id=soap-php-client)

You can write your client in any language that supports web services invocation.
Let's invoke the same operations we did when we created our Java client, this
time using PHP and the PHP SOAP Client: 

    <?php
        $groupName = "MyGroup2";
        $userName = "test";
        $clientOptions = array(
        'login' => $userName,
        'password' => 'test');

        // Add user group

        $userGroupClient = new SoapClient(
            "http://localhost:8080/api/secure/axis/Portal_UserGroupService?wsdl",
            $clientOptions);
        $group = $userGroupClient->addUserGroup($groupName, "This is my group",
            0, 0);
        print "group id for " . $groupName . " is " . $group->userGroupId . "\n";

        // add user to user group

        $companyClient = new SoapClient(
            "http://localhost:8080/api/secure/axis/Portal_CompanyService?wsdl",
            $clientOptions);
        $company = $companyClient->getCompanyByVirtualHost("localhost");
        $userClient = new SoapClient(
            "http://localhost:8080/api/secure/axis/Portal_UserService?wsdl",
            $clientOptions);
        $userId = $userClient->getUserIdByScreenName($company->companyId,
            $userName);
        print "user id for " . $userName . " is " . $userId . "\n";

        $users = array($userId);
        $userClient->addUserGroupUsers($group->userGroupId, $users);

        // get and print user groups to which user belongs

        $userGroups = $userGroupClient->getUserUserGroups($userId);
        print "user groups for user " . $userId . " ...\n";
        foreach($userGroups as $k=>$v)
            print ($v->name) . " " . $v->userGroupId . "\n";
    ?>

It's worth repeating that you can use any language that supports use of SOAP web
services to create your web services client. Try it out on Liferay's SOAP web
services!

Next we'll explore Liferay's JSON Web Services. 
