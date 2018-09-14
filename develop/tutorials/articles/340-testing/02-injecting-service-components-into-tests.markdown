# Injecting Service Components into Tests [](id=injecting-service-components-into-tests)

You can use @product@'s `@Inject` annotation to inject service components into a
test, like you use the
[`@Reference` annotation to inject service components](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)
into a module component.

`@Inject` uses reflection to inject a field with a service component object
matching the field's interface.
[Test rule `LiferayIntegrationTestRule`](@platform-ref@/7.1-latest/javadocs/portal-test-integration/com/liferay/portal/test/rule/LiferayIntegrationTestRule.html)
provides the annotation. The annotation accepts `filter` and `type` parameters,
which you can use separately or together.

To fill a field with a particular implementation or sub-class object, set the
`type` with it. 

    @Inject(type = SubClass.class)

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

Here's how to inject a service component into a test class:

1.  In your test class, add a rule field of
    [type `com.liferay.portal.test.rule.LiferayIntegrationTestRule`](@platform-ref@/7.1-latest/javadocs/portal-test-integration/com/liferay/portal/test/rule/LiferayIntegrationTestRule.html).
    For example,

        @ClassRule
        @Rule
        public static final AggregateTestRule aggregateTestRule = 
            new LiferayIntegrationTestRule();

2.  Add a field to hold a service component. Making the field static improves
    efficiency, because the container injects static fields once before test
    runs and nulls them after all tests run. Non-static fields are injected
    before each test run but stay in memory till all tests finish. 

3.  Annotate the field with an `@Inject` annotation. By default, the container
    injects the field with a service component object matching the field's type.

4.  Optionally add a `filter` string or `type` parameter to further specify the
    service component object to inject.

At runtime, the `@Inject` annotation blocks the test until a matching service
component is available. The block has a timeout and messages are logged
regarding the test's unavailable dependencies.

+$$$

**Important**: If you're publishing the service component you are injecting, the
test might never run. If you must publish the service component from the test
class, use
[Service Trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers)
to access service components.

$$$

Great! Now you can inject service components into your tests.

## Related Articles [](id=related-articles)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers)

Unit Testing with JUnit
