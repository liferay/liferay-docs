# Unit Testing with JUnit [](id=unit-testing-with-junit)

Test driven development is a best practice for any developer. Unit tests verify
and validate functionality of classes and methods in isolation by "mocking" 
external dependencies. One of the most widely-used tools for test driven
development on the Java platform is JUnit. You can use the
[JUnit](http://junit.org) framework to write unit tests for @product@
applications. 

JUnit integrates with build environments such as Maven and Gradle. JUnit plugins
are available in IDEs such as Eclipse, IntelliJ, and NetBeans. And of course,
JUnit is available in 
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace). 

This tutorial covers the following topics:

- [Writing good tests](#writing-good-tests)

- [JUnit annotations](#understanding-junit-annotations)

- [Creating JUnit test classes](#creating-a-junit-test-class)

- [Running JUnit tests](#running-junit-tests)

You'll start by learning best practices for writing unit tests.

## Writing Good Tests [](id=writing-good-tests)

To write good tests, developers must understand assertions and follow best
practices. 

**Assertion**: an executable specification of the expected behavior of the
software under test (SUT) given a scenario. The tests define the behavior in the
scenario using several methods: a test setup method, a class setup method, and a
test method.  It's *executable* because it programmatically checks behavior and
tracks requirements.

**Best Practices for Unit Tests:**

 Rule                                                     |  Description |
:-------------------------------------------------  | :------------- |
 A test should have only one reason to fail. | Resolving failures from a single root cause is easiest. 
 A test should check just one thing. |  Tests that verify or validate one thing are easier to understand and maintain. Focusing on multiple things can lead to multiple failure points, thus breaking the *one reason to fail* rule. 
 Avoid conditional logic in tests. | Conditional test logic that uses loops or if/else clauses increases the probability of test bugs. 
 A test that asserts nothing or cannot fail is worthless. | Tests that can't fail create a false sense of security.<br />Here's example test code that can't fail:<br />`File f = new File ("foo");`<br />`Assert.assertTrue(f != null);`
 A test that inaccurately advertises what it asserts is untrustworthy | A test's name should accurately express what it tests. A name that's inaccurate or that promises more than what the test does creates confusion. <br />`@Test`<br />`public void testAddUser() { // do something not related to user creation }`

Next, you'll learn JUnit's annotations for test methods. 

## Understanding JUnit Annotations [](id=understanding-junit-annotations)

The following table describes the JUnit method annotations.

 Method signature | Description |
:--------------------------  | :------------- |
 `@BeforeClass`<br />`public static void method()` | The method is invoked once, before the class's entire suite of tests is executed. It should prepare the general test environment. |
 `@Before`<br />`public void method()` | The method is invoked before each test. It should prepare the environment for each test. |
 `@Test`<br />`public void method()`    | Marks the method as a test. |
 `@Test (expected = SomeException.class)`<br />`public void method()` | The test fails if the method doesn't throw the exception. |
 `@After`<br />`public void method()` | The method is invoked after each test. It should clean up the environment. |
 `@AfterClass`<br />`public static void method()` | The method is invoked once, after the class's entire suite of tests is executed. It should cleanup the general test environment. |
 `@Ignore or @Ignore("Why disabled")`<br />`public void method()` | The method is skipped. Adding the `@Ignore` annotation is an easy way to skip a test. The message (optional) can explain why the test is being ignored. |

JUnit follows the algorithm below to execute the test class's methods. 

![Figure 1: JUnit executes the annotated methods following this algorithm.](../../images/junit-test-flow.png)

Let's create a JUnit test class.

## Creating a JUnit Test Class [](id=creating-a-junit-test-class)

Here you'll create a JUnit test class and fill it with methods that both 
set up/clean up the test environment and assert the software's expected behavior.

To help illustrate creating unit tests, here's an example class to test:

    public class MySampleNameClass {

       public MySampleNameClass(String firstName, String middleName, String lastName) {
          _firstName = firstName;
          _middleName = middleName;
          _lastName = lastName;
       }

       public int fullNameLength() {
          return _firstName.length() + _middleName.length() + _lastName.length();
       }

       public String getMiddleInitial() {
          return _middleName.charAt(0) + ".";
       }

       @Override
       public String toString() {
          return _firstName + " " + getMiddleInitial() + " " + _lastName;
       }

       private String _firstName;
       private String _middleName;
       private String _lastName;

    }

In the sections that follow, you'll see setup, cleanup, and test methods that
relate to this example class.

Create a test class: 

1.  Open the module of the class you're testing.

2.  Add a `src/test/java/` folder to the module. 

3.  In that folder, create a package path (ending in `test`) that mirrors the
    package path of the class you're testing.

    For example, if the class is in package `com.sample`, add a test package
    `com.sample.test`. 

4.  In that package, create a test class that ends in `Test` (e.g.,
    `SomeTest.java`). 

Your new test class is ready for test methods.

![Figure 2: In this example module, the JUnit test class is in the same module of the class it tests. The test class resides in a source folder and package following standard test structure conventions.](../../images/junit-project-structure.png)

Now create methods in the order of test flow execution. 

### @BeforeClass [](id=beforeclass)

Identify resources or computationally expensive tasks that must be completed
prior to running all the tests. Create a method that initializes these resources
and invokes these tasks. Apply the `@BeforeClass` annotation to the method.

### @Before [](id=before)

Consider what needs to be done before running each individual test. Create a
method that makes small preparations before each test case. Add the `@Before`
annotation to the method. 

For example, each of the tests for the class `MySampleNameClass` operate on a
populated `MySampleNameClass` object. Implementing a method that instantiates
such an object beforehand is appropriate. Adding the `@Before` annotation to the
method ensures it's executed before each individual test.

Here's what the `MySampleNameClassTest` class might look like with such a
method:

    public class MySampleNameClassTest {

       @Before
       public void setUp() {
          _mySampleNameClass = new MySampleNameClass("Brian", "Edward", "Greenwald");
       }

       private MySampleNameClass _mySampleNameClass;

    }

**Note**: Since this example class is immutable, it might make more sense to
instantiate the object once in the `@BeforeClass` method and forgo the `@Before`
method. It's probably more typical, however, that you'll be testing methods that
change an object's state; so pretend this example object must be instantiated
anew before each `@Test` method.

Now that you've instantiated objects each test needs, you can add `@Test`
methods to assert expected output from the object's methods.

### @Test [](id=test)

JUnit's [`Assert` utility class](http://junit.org/junit4/javadoc/latest/org/junit/Assert.html)
contains static methods for comparing actual test results with expected results.
When an assertion fails, an `AssertionException` is thrown and the test fails.
If a test method completes execution without throwing an exception, the test
succeeds. 

For tests that contain a large amount of logic it's typically a best practice to
use multiple assertions within the test to better identify the earliest point of
failure. But since the example class is fairly simple, it's better to create a
test for each of its methods:

    @Test
    public void testFullNameLength() {
       int length = _mySampleNameClass.fullNameLength();

       Assert.assertEquals(20, length);
    }

    @Test
    public void testGetMiddleInitial() {
       String middleInitial = _mySampleNameClass.getMiddleInitial();

       Assert.assertEquals("E.", middleInitial);
    }

    @Test
    public void testToString() {
       String fullName = _mySampleNameClass.toString();

       Assert.assertEquals("Brian E. Greenwald", fullName);
    }

Since you know the test input (e.g., the parameters passed into the
`MySampleNameClass` constructor), you can easily determine expected output. You
can compare the computed value each method invocation returns with the expected
value.

To test that a method throws a particular exception when given certain invalid
inputs, add an `expected` attribute to the `@Test` annotation and assign the
attribute the expected exception's class name. Important: refrain from making
any assertions in the test method.

    @Test(expected = MySampleException.class)
    public void testToString() {
       String fullName = _mySampleNamenClass.toString();
    }

**Warning**: Each test method should be independent. Since JUnit doesn't
guarantee test order, you can't rely on a test being run before or after other
tests.

### @After [](id=after)

On finishing each individual test, you should clean up anything that was created
or modified. Implement the cleanup instructions in a method and add the `@After`
annotation to it. 

### @AfterClass [](id=afterclass)

On finishing the entire set of tests, you should clean up any remaining test
environment resources. Implement the final cleanup instructions in a method and
add the `@AfterClass` annotation to it. 

It's time to compile and run your JUnit tests.

## Running JUnit Tests [](id=running-junit-tests)

Unit testing involves these things:

- Resolving test dependencies

- Executing the tests

- Analyzing test results

If your tests require accessing classes outside the module, declare them as
`testCompile`
[dependencies](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
in the module's `build.gradle` file. Here's an example `testCompile` dependency. 

    testCompile group: "com.sample", name: "com.sample.external", version: "1.0.0"

Because unit tests run independent of any running @product@ instance, you can
use external modules in tests but can't access their services.

Gradle and Maven commands execute module unit tests:

**Gradle**: `./gradlew test`

**Maven**: `mvn test`

The module's classes and tests compile and its tests run. The following figure
shows command output for a successful test execution.

![Figure 3: Command output of successful test execution looks like this.](../../images/junit-command-output.png)

Reports are generated to module subfolders based on the build environment and/or
reporting mechanism.

**Gradle**:

-   HTML report `build/reports/tests/index.html`
-   XML report file in `build/test-results/`

**Maven**:

-   [SureFire plugin](http://maven.apache.org/surefire/maven-surefire-plugin/)
    generates XML and text report files in `targets/surefire-reports/`

Gradle's HTML report, for example, shows overall test metrics and organizes test
results by package. Clicking on a package name lists test class results.
Clicking on a test class name lists test method results.

![Figure 4: In Gradle environments, JUnit produces an HTML file named `index.html` that reports test result details.](../../images/junit-results-html.png)

While it's certainly helpful to see successful test results, it's even more
helpful to see results of failing tests.

As an experiment, change the expected values of a test's assertions to
force the test to fail and rerun the tests to yield the respective failures. 

![Figure 5: Command output of failing tests looks like this.](../../images/junit-failure-command-output.png)

The command output shows the class and method name of each failed test, the
JUnit assertion type, and the assertion's line number.

The generated HTML report shows each failing test's stack trace.  

![Figure 6: Here's a failed test's stack trace.](../../images/junit-failure-results-html.png)

Stack traces show exactly why the test failed. They're essential for determining
whether the failure is the result of faulty business logic or an incorrect
expected value in the assertion. Using this information the developer can
resolve the issue.

Congratulations on creating and executing unit tests with JUnit! 

## Related Topics [](id=related-topics)

[Integration Testing with the Arquillian Extension](/develop/tutorials/-/knowledge_base/7-0/arquillian-extension-for-liferay-example)

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)

[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
