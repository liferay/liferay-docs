# Injecting Service Components into Tests [](id=injecting-service-components-into-tests)

Similar to using OSGi's `@Reference` annotation to inject service components
into a module component, you can use @product@'s `@Inject` annotation to inject
them into a test.

`@Inject` uses reflection to inject a field with a service component object
matching the field's interface.
[Test rule `LiferayIntegrationTestRule`](https://docs.liferay.com/ce/portal/7.0-latest/javadocs/portal-test-integration/com/liferay/portal/test/rule/LiferayIntegrationTestRule.html)
provides this annotation. The annotation accepts `filter` and `type` parameters, which you can use separately or together.

To fill a field of a with a particular implementation or sub-class object, set
the `type` with it. 

    `@Inject(type = SubClass.class)`

Replace `SubClass` with the name of the service interface to inject.

Here's an example test class that injects a `DDLServiceUpgrade` object into an `UpgradeStepRegistrator` interface field:

    public class Test {

        @ClassRule
        @Rule
        public static final AggregateTestRule aggregateTestRule = 
            new LiferayIntegrationTestRule();

        @Test
        public void testSomething() {
            // your test code here
        }

        @Inject(
            filter = "(&(objectClass=com.liferay.dynamic.data.lists.internal.upgrade.DDLServiceUpgrade))"
        )
        private static UpgradeStepRegistrator _upgradeStepRegistrator;

    }

Field `aggregateTestRule` applies a `LiferayIntegrationTestRule` instance to the class. 

The `@Test` annotation marks method `testSomething` to be a test method. Test code belongs in it. 

The `@Inject` annotation and its filter string parameter mark field `_upgradeStepRegistrator` to be injected with a `DDLServiceUpgrade` object. `DDLServiceUpgrade` implements the interface `UpgradeStepRegistrator`. 

Here are the steps for injecting a service component into a test class:

1.  In your test class, add a rule field of
    [type `com.liferay.portal.test.rule.LiferayIntegrationTestRule`](https://docs.liferay.com/ce/portal/7.0-latest/javadocs/portal-test-integration/com/liferay/portal/test/rule/LiferayIntegrationTestRule.html).
    For example,

        @ClassRule
        @Rule
        public static final AggregateTestRule aggregateTestRule = 
            new LiferayIntegrationTestRule();

2.  Add a field to hold a service component. Making the field static improves
    efficiency. Static fields are injected once before test runs and nulled out
    after all tests run. Non-static fields are injected before each test run
    and are nulled out after each test.

3.  Annotate the field with an `@Inject` annotation. By default, the field is
    injected with a service component object matching the field's type.

4.  Optionally add a `filter` string or `type` parameter to further specify the
    service component object to inject.

At runtime, the `@Inject` annotation blocks the test until a matching service
component is available. The block has a timeout and messages are logged
regarding the test's unavailable dependencies.

+$$$

**Important**: If you're publishing the service component you are injecting with
`@Inject` annotation, the test might never be run. If you must publish the
service component from the test class, use
[Service Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers)
to access service components.

$$$

You've learned how easy it is to inject service components into your tests.

## Related Articles [](id=related-articles)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-0/service-trackers)

[Finding and Invoking Liferay Services](/develop/tutorials/-/knowledge_base/7-0/finding-and-invoking-liferay-services)

[Unit Testing with JUnit](/develop/tutorials/-/knowledge_base/7-0/unit-testing-with-junit)
