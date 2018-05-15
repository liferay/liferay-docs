# Service Access Policies [](id=service-access-policies)

Service access policies provide web service security beyond user authentication
to remote services. Together with
[permissions](/develop/tutorials/-/knowledge_base/7-1/defining-application-permissions),
service access policies limit remote service access by remote client
applications. This forms an additional security layer that protects user data
from unauthorized access and modification.

To connect to a web service, remote clients must authenticate with credentials
in that instance. This grants the remote client the permissions assigned to
those credentials in the @product@ installation. Service access policies further
limit the remote client's access to the services specified in the policy.
Without such policies, authenticated remote clients are treated like users: they
can call any remote API and read or modify data on behalf of the authenticated
user. Since remote clients are often intended for a specific use case, granting
them access to everything the user has permissions for poses a security risk.

For example, consider a mobile app (client) that displays a user's appointments
from the Liferay Calendar app. This client app doesn't need access to the API
that updates the user profile, even though the user has such permissions on the
server. The client app doesn't even need access to the Calendar API methods that
create, update, and delete appointments. It only needs access to the remote
service methods for finding and retrieving appointments. A service access policy
on the server can restrict the client's access to only these service methods.
Since the client doesn't perform other operations, having access to them is
a security risk if the mobile device is lost or stolen or the client app is
compromised by an attacker.

## How Service Access Policies Work [](id=how-service-access-policies-work)

A remote client's request to a web service contains the user's credentials or an
authorization token. An authentication module recognizes the client based on the
credentials/token and grants the appropriate service access policy to the
request. The service access policy authorization layer then processes all
granted policies and lets the request access the remote service(s) permitted by
the policy.

![The authorization module maps the credentials or token to the proper Service Access Policy.](../../images/service-access-policies-arch.png)

Service Access policies are created in the Control Panel by
administrators. If you want to start creating policies yourself, see
[this article on service access policies](/discover/deployment/-/knowledge_base/7-1/service-access-policies)
that documents creating them in the UI.

There may be cases, however, when your server-side Liferay app must use the
service access policies API:

- It uses [custom remote API authentication](/develop/tutorials/-/knowledge_base/7-1/auto-login)
  (tokens) and require certain services to be available for clients using
  the tokens.

- It requires its services be made available to guest users, with no authentication
  necessary.

- It contains a [remote service authorization layer](/develop/tutorials/-/knowledge_base/7-1/password-based-authentication-pipelines)
  that needs to drive access to remote services based on granted
  privileges.

## API Overview [](id=api-overview)

Liferay provides an Interface and a `ThreadLocal` if you don't want to roll your own
policies. If you want to get low level, an API is provided that
Liferay itself has used to implement 
[Liferay Sync](/discover/portal/-/knowledge_base/7-1/administering-liferay-sync). 

1. The Interface and `ThreadLocal` are available in the [package
   `com.liferay.portal.kernel.security.service.access.policy`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/package-summary.html).
   This package provides classes for basic access to policies. For example, you
   can use the [singleton
   `ServiceAccessPolicyManagerUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyManagerUtil.html)
   to obtain Service Access Policies configured in the system. You can also use
   the [`ServiceAccessPolicyThreadLocal`
   class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyThreadLocal.html)
   to set and obtain Service Access Policies granted to the current request
   thread.

   At this level, you can get a list of the configured policies to let your
   app/client choose a policy for accessing services. Also, apps like OAuth
   can offer a list of available policies during the authorization step in
   the OAuth workflow and allow the user to choose the policy to assign to
   the remote application. You can also grant a policy to a current request
   thread. When a remote client accesses an API, something must tell the
   Liferay instance which policies are assigned to this call. This
   something is in most cases an [`AuthVerifier`
   implementation](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/security/auth/verifier/AuthVerifier.html).
   For example, in the case of the OAuth app, an `AuthVerifier` implementation
   assigns the policy chosen by the user in the authorization step.

2. The API ships with the product as OSGi modules:

- `com.liferay.portal.security.service.access.policy.api.jar`
- `com.liferay.portal.security.service.access.policy.service.jar`
- `com.liferay.portal.security.service.access.policy.web.jar`

   These OSGi modules are active by default, and you can use them to manage
   Service Access Policies programmatically. You can find their source code 
   [here in GitHub](https://github.com/liferay/liferay-portal/tree/master/modules/apps/portal-security).
   Each module publishes a list of packages and services that can be
   consumed by other OSGi modules.

You can use both tools to develop a token verification module (a module that
implements custom security token verification for use in authorizing remote
clients) for your app to use. For example, this module may contain a JSON Web
Token implementation for @product@'s remote API. A custom token verification
module must use the Service Access Policies API during the remote API/web
service call to grant the associated policy during the request. The module

- can use `com.liferay.portal.security.service.access.policy.api.jar`
  and `com.liferay.portal.security.service.access.policy.service.jar` to
  create policies programmatically.

- should use the method
  `ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName()` to
  grant the associated policy during a web service request.

- can use `ServiceAccessPolicyManagerUtil` to display list of
  supported policies when authorizing the remote application, to associate
  the token with an existing policy.

## Service Access Policy Example [](id=service-access-policy-example)

[Liferay Sync's](https://www.liferay.com/supporting-products/liferay-sync)
`sync-security` module is a service access policy module. It uses
`com.liferay.portal.security.service.access.policy.service` to create the
`SYNC_DEFAULT` and `SYNC_TOKEN` policies programmatically. For service calls to
Sync's remote API, these policies grant access to Sync's
`com.liferay.sync.service.SyncDLObjectService#getSyncContext` and
`com.liferay.sync.service.*`, respectively. Here's the code in the
`sync-security` module that defines and creates these policies:

    @Component(immediate = true)
    public class SyncSAPEntryActivator {

        // Define the policies
        public static final Object[][] SAP_ENTRY_OBJECT_ARRAYS = new Object[][] {
            {
                "SYNC_DEFAULT",
                "com.liferay.sync.service.SyncDLObjectService#getSyncContext", true
            },
            {"SYNC_TOKEN", "com.liferay.sync.service.*", false}
        };

        ...

        // Create the policies
        protected void addSAPEntry(long companyId) throws PortalException {
                for (Object[] sapEntryObjectArray : SAP_ENTRY_OBJECT_ARRAYS) {
                    String name = String.valueOf(sapEntryObjectArray[0]);
                    String allowedServiceSignatures = String.valueOf(
                        sapEntryObjectArray[1]);
                    boolean defaultSAPEntry = GetterUtil.getBoolean(
                        sapEntryObjectArray[2]);

                    SAPEntry sapEntry = _sapEntryLocalService.fetchSAPEntry(
                        companyId, name);

                    if (sapEntry != null) {
                        continue;
                    }

                    Map<Locale, String> map = new HashMap<>();

                    map.put(LocaleUtil.getDefault(), name);

                    _sapEntryLocalService.addSAPEntry(
                        _userLocalService.getDefaultUserId(companyId),
                        allowedServiceSignatures, defaultSAPEntry, true, name, map,
                        new ServiceContext());
                }
        }

        ...

    }

This class creates the policies when the module starts. Note that this module is
included and enabled by default. You can access these and other policies in
*Control Panel* &rarr; *Configuration* &rarr; *Service Access Policy*.

The `sync-security` module must then grant the appropriate policy when
needed. Since every authenticated call to Liferay Sync's remote API
requires access to `com.liferay.sync.service.*`, the module must
grant the `SYNC_TOKEN` policy to such calls. The module does this
with the method
`ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName`, as
shown in this code snippet:

    if ((permissionChecker != null) && permissionChecker.isSignedIn()) {
        ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName(
            String.valueOf(
                SyncSAPEntryActivator.SAP_ENTRY_OBJECT_ARRAYS[1][0]));
    }

Now every authenticated call to Sync's remote API, regardless of
authentication method, has access to `com.liferay.sync.service.*`. To
see the full code example,
[click here](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/sync/sync-security/src/main/java/com/liferay/sync/security/servlet/filter/SyncAuthFilter.java).

Nice! Now you know how to integrate your apps with the Service Access
Policies.
