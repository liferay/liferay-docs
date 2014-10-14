# Liferay's Deprecation Policy [](id=liferays-deprecation-policy)

Methods in Liferay's APIs are deprecated when they're no longer called by
Liferay internally. Method deprecation occurs during major releases of Liferay.
You'll know when there's a major release if you understand Liferay's
release version notation. Liferay version numbers consist of a three digit
number. Knowing what each digit represents is key, so let's consider a case
where you were using Liferay 6.0.2, and Liferay 6.0.3 has just been released:

- Digit 1 (**6**.0.3): The first digit represents the major release version,
  which is a *feature release*. Along with changes to Liferay's architecture,
  internal schema, and the introduction of new functionality, API methods get
  deprecated during feature releases. In our example, this number hasn't
  changed, so any API methods we use in our custom development are intact. 
- Digit 2 (6.**0**.3): The second digit indicates the minor release. Minor
  releases involve the introduction of new features and bug fixes. While
  customization might be affected when installing, API methods don't get
  deprecated. For our example, this didn't change.  feature release. It also did
  not change in our example. 
- Digit 3 (6.0.**3**): The third digit represents the maintenance release, or
  fix pack release. In our case, the third digit changed from *2* to *3*, so we
  discovered a maintenance release. In terms of API method deprecation, you're
  in the clear; in acoordince with our policy, methods you used in your custom
  development haven't been deprecated. 

<!-- I think this whole section is incorrect. It should be synced with this
(focusing on the deprecation policy, of course): https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/editions-of-lifer-4 -->

<!--I updated it based on the user guide. Please review -Russ -->
