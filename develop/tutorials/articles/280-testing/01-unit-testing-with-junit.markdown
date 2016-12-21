# Unit Testing with JUnit

Unit tests verify correct functionality of classes and their methods in
isolation. They test the intended behavior without the developer needing to
worry about external dependencies. Unit testing in @product@ is accomplished
using the JUnit framework. Developers create test methods that assert expected
results. They apply annotations to methods that prepare the test environment,
invoke tests, and clean up the test environment. The annotations dictate test
flow execution. 

In this tutorial you'll do these things:

- Learn the JUnit annotations
- Create a JUnit test class
- Execute the JUnit tests

## JUnit Annotations

The following table describes the JUnit annotations for methods.

 Method signature | Description |
:--------------------------  | :------------- |
 `@BeforeClass`<br />`public static void method()` | This method is invoked once, before the class's entire suite of tests is executed. It should prepare the general test environment. |
 `@Before`<br />`public void method()` | This method is invoked before each test. It should prepare the environment. |
 `@Test`<br />`public void method()`    | Marks the method as a test. |
 `@Test (expected = SomeException.class)`<br />`public void method()` | The test fails if the method doesn't throw the exception. |
 `@After`<br />`public void method()` | This method is invoked after each test. It should clean up the environment. |
 `@AfterClass`<br />`public static void method()` | This method is invoked once, after the class's entire suite of tests is executed. It should cleanup the general test environment. |
 `@Ignore or @Ignore("Why disabled")`<br />`public void method()` | This method is skipped. Adding the `@Ignore` annotation is an easy way to skip a test. The message (optional) can explain why the test is being ignored. |

JUnit executes the test class methods following the algorithm below. 

![Figure x: JUnit executes the annotated methods following this algorithm.](../../images/junit-test-flow.png)

Let's create a class for unit testing another class's business logic. 

## Creating a JUnit Test Class

Create an initial test class following these steps:

1.  Open the module of the class you're testing.

2.  Add a `src/test/java/` folder to the module. 

3.  In that folder, create a package path (ending in `test`) that mirrors the
    package path of the class you're testing.

    For example, if the class is in package `com.sample`, add a package
    `com.sample.test`. 

4.  In that package, create a test class that ends in `Test` (e.g.,
    `SomeTest.java`). 

Your new test class is ready for test methods.

![Figure x: In this example structure, the JUnit test class accompanies the class it tests in the same module. The test class resides in a different source folder and package.](../../images/junit-project-structure.png)

To help illustrate creating unit tests, let's refer to an example class to test:

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

In the sections that follow, you'll see test methods that relate to this example
class.

Let's create test methods that use the JUnit annotations per the test flow
execution order. 

### @BeforeClass

First you need to identify are resources or computationally expensive tasks that
must be completed prior to running all the tests. Create a method that
initializes these resources and invokes these tasks. Apply the `@BeforeClass`
annotation to the method.

### @Before

Consider what needs to be done before running each individual test. Create a
method that makes small preparations before each test case and add the `@Before`
annotation to it. 

For example, each of the tests for the class `MySampleNameClass` must operate on
a populated `MySampleNameClass` object. Implementing a method that instantiates
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

**Note**: Since this example class is immutable it technically might make more
sense to simply instantiate the object once in the `@BeforeClass` method and
forgo the `@Before` method. It's probably more typical, however, that you'll be
testing methods that change an object's state; so let's pretend this example
object needs to be instantiated anew before each `@Test` method.

Now that you've instantiated objects that each test needs, you can test the
expected output for the methods you're testing.

### @Test

JUnit's [`Assert` utility class](http://junit.org/junit4/javadoc/latest/org/junit/Assert.html)
contains static methods for comparing actual test results with expected results.
When an assertion fails, an `AssertionException` is thrown, thus failing the
test. If a test method completes execution without throwing an exception, the
test succeeds.

For tests that contain a large amount of logic it's typically a best practice to
use multiple assertions within the test to better identify the earliest point of
failure. But since the example class is fairly simple, let's consider creating a
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
`MySampleNameClass` constructor), you can easily determine the expected. You
can compare the computed value each method returns with the expected value.

To test that a method throws a particular exception when given certain invalid
inputs, add an `expected` attribute to the `@Test` annotation and assign the
attribute the class name of the expected exception. Important: refrain from
making any assertions in the test method.

    @Test(expected = MySampleException.class)
    public void testToString() {
       String fullName = _mySampleNamenClass.toString();
    }

**Warning**: When creating your tests, make sure each is fully independent.
Since JUnit doesn't guarantee test order in the flow of execution, you can't
rely on tests being run in a particular order.

### @After

On finishing each individual test, you should clean up anything that was
changed or created during it. Implement the cleanup instructions in a method and
add the `@After` annotation to it. 

### @AfterClass

On finishing the entire set of tests, you should clean up any remaining test
environment resources. Implement the final cleanup instructions in a method and
add the `@AfterClass` annotation to it. 

## Writing good tests

First, it's important to describe what an assertion is.

An assertion is an executable specificacion of the expected behavior of the
software under test (SUT), under the scenario defined by the test: setUp and
setUpClass methods, and specific logic put in the test method. It's important to
remark the `executable` word, because it means that you can automate checks for
the specification, in the form of tests, which allows you to keep track of your
requirements.

Once we understand the importance of assertions, we must know some best
practices to write good tests.

### A test should have only one reason to fail

This way we will know the root cause that caused our test to fail, and we could
fix it up very quickly. Otherwise, we'll have to investigate the several reasons
that could have caused the failure.

### A test should test just one thing

If we test only one thing, then the test will be simple, and simpler tests are
easier to maintain. Testing more things adds complexity, and more causes for
failure, so we would be breaking the previous rule.

### Avoid conditional logic in tests

If you add `for` or `while` loops, or `if/else` clauses, then you are adding
complexity to the test, and this will increase the probability of failure for
things not related to the test itself. You may find yourself creating tests
for your test, which is insane.

### A test that cannot fail has no value

It creates a false sense of security, because although the test passes, it test
nothing. This is an example of a test that cannot fail:

  File f = new File ("foo");
  Assert.assertTrue(f != null);

### A test promising much more than delivers is an untrustworthy test
A test is untrustworthy if it has no asserts, or it cannot fail, or its name
says something different than what it actually does.

  @Test
  public void testAddUser() {
    // do something not related to user creation
  }

Now that you've created your test methods and setup/cleanup methods, you can run
your unit tests.

## Running JUnit Tests

Once your test classes are complete, you can compile them and run their tests.

If your tests require accessing classes outside the module, declare them as
`testCompile` dependencies in the module's `build.gradle` file. Here's an
example `testCompile` dependency. 

    testCompile group: "com.sample", name: "com.sample.external", version: "1.0.0"

Because unit tests run independent of any running @product@ instance, you can
use external modules in tests but won't be able to access their services.

[Workspace's](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
[Blade CLI](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/blade-cli)
tool facilitates running tests. To run the tests, navigate to the module's
folder in a terminal and execute the following command:

    blade gw test

The module's classes and tests are compiled and its tests are run. The
following figure shows the command output for a successful test execution. 

![Figure x: Command output of successful test execution looks like this.](../../images/junit-command-output.png)

Workspace's `test` task generates a detailed informative test report in an HTML
file. It's named `index.html` and is in the module's `build/reports/` folder. 

The report shows overall test metrics and organizes test results by package.
Clicking on a package name lists test class results. Clicking on a test class
name lists test method results.

![Figure x: JUnit produces HTML files that show test result details.](../../images/junit-results-html.png)

While it's certainly helpful to see successful test results, it's even more
helpful to see results of failing tests.

As an experiment, change the expected values of your assertions to deliberately
force the tests to fail. After changing some of the values, running `blade gw
test` again to yield the respective test failures. 

![Figure x: Commandline output of failing tests looks like this.](../../images/junit-failure-command-output.png)

The command output shows the class and method name of each failed test, the
JUnit assertion type, and the assertion's line number.

The generated HTML report shows each failing test's stack trace.  

![Figure x: Here's a failed test's stack trace.](../../images/junit-failure-results-html.png)

Stack traces show exactly why the test failed. They're essential for determining
whether the failure is the result of faulty business logic or incorrect expected
values in the assertions. Using this information the developer can resolve the
issue.

Congratulations on creating new unit tests using JUnit! 

## Related Articles

[Integration Testing with the Arquillian Extension](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/arquillian-extension-for-liferay-example)

[Liferay Workspace](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)

[Liferay @ide@](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-ide)