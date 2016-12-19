# Unit Testing with JUnit

The goal of a Unit Test is to verify the correct functionality of a class or
method in isolation. This allows a developer to test said method or class for
its intended behavior without needing to worry about any dependencies it might
have. From a Liferay DXP/OSGi perspective, these dependencies effectively
translates to services.

## JUnit

Unit Testing in Liferay is accomplished using the JUnit framework. The framework
essentially boils down to the usage of a variety of annotations that are applied
to methods to mark what each should do. In a way, they dictate the flow of
execution* of your tests.

<table border="2" cellpadding="1" cellspacing="1" height="617" width="1030">
	<caption>(src: http://www.vogella.com/tutorials/JUnit/article.html)</caption>
	<tbody>
		<tr>
			<td>@Test<br />
			public void method()&nbsp;</td>
			<td>The @Test annotation identifies a method as a test method.</td>
		</tr>
		<tr>
			<td>@Test (expected = Exception.class)</td>
			<td>Fails if the method does not throw the named exception.</td>
		</tr>
		<tr>
			<td>@Test(timeout=100)</td>
			<td>Fails if the method takes longer than 100 milliseconds.</td>
		</tr>
		<tr>
			<td>@Before<br />
			public void method()</td>
			<td>This method is executed before each test. It is used to prepare the test environment (e.g., read input data, initialize the class).</td>
		</tr>
		<tr>
			<td>@After<br />
			public void method()</td>
			<td>
			<p>This method is executed after each test. It is used to cleanup the test environment (e.g., delete temporary data, restore defaults). It can also save memory by cleaning up expensive memory structures.</p>
			</td>
		</tr>
		<tr>
			<td>@BeforeClass<br />
			public static void method()</td>
			<td>
			<p>This method is executed once, before the start of all tests. It is used to perform time intensive activities, for example, to connect to a database. Methods marked with this annotation need to be defined as static to work with JUnit.</p>
			</td>
		</tr>
		<tr>
			<td>@AfterClass<br />
			public static void method()&nbsp;</td>
			<td>
			<p>This method is executed once, after all tests have been finished. It is used to perform clean-up activities, for example, to disconnect from a database. Methods annotated with this annotation need to be defined as static to work with JUnit.</p>
			</td>
		</tr>
		<tr>
			<td style="white-space: nowrap;">@Ignore or @Ignore("Why disabled")&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>Ignores the test method. This is useful when the underlying code has been changed and the test case has not yet been adapted. Or if the execution time of this test is too long to be included. It is best practice to provide the optional description, why the test is disabled.</td>
		</tr>
	</tbody>
</table>

Putting it all together, the methods you annotate will be run in the following
order:

![Figure x: JUnit executes the annotated methods following this algorithm.](../../images/junit-test-flow.png)

To help illustrate the creation of a Unit Test, we’ll be using the following
sample class with a few methods:

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


Now let's write our actual test class. As far as where the class should go, it's
generally a best practice to place tests within the same module as the classes
it's testing like so:

![Figure x: This example project follows the project structure for JUnit test classes.](../../images/junit-project-structure.png)

Structurally, you'll want to make sure your test classes lie somewhere within
the src/test/java/... directory of your module. As a best practice, the test
classes should be placed within a package path that mirrors that of your
module's classes with an additional "test" package appended to the end. Lastly,
all Unit Test class names should end with "Test."

## @BeforeClass

The first thing you need to identify when writing a Unit Test is the presence of
resources or computationally expensive tasks that must be completed prior to
running all of the tests. The logic for which should go in a method with this
annotation. In our example, we don't have anything that matches this description
so we can safely skip creating this method.

## @Before

Now we need to consider what needs to be done prior to running each test. In our
sample, we need to make sure we have an object of our sample class instantiated
in order to test its methods so let's go ahead and start drafting our test
class:

    public class MySampleNameClassTest {

       @Before
       public void setUp() {
          _mySampleNameClass = new MySampleNameClass("Brian", "Edward", "Greenwald");
       }
       private MySampleNameClass _mySampleNameClass;

    }

(Note: Since our class is immutable it technically might make more sense to
simply instantiate the object once in @BeforeClass and forgo the @Before.
However, it's probably more typical that you'll be testing methods that do
change an object's state, so let's pretend it needs to be instantiated anew
before each @Test!)

Now that we've instantiated our class with concrete values, we can easily move
on to testing the expected output of our sample methods.

## @Test

When creating your test cases, be mindful that each case should be fully
independent of one another and thus not rely on them being run in a specific
order - JUnit offers no guarantees in the flow of execution.Our sample class is
entirely immutable so we don't need to worry about state. If the methods we were
testing did alter state, we would simple need to make sure that the @Before and
@After methods ensured a fresh state for each @Test, as is their purpose. Before
we actually move on to writing our tests, however, we need a way for JUnit to
recognize whether or not our tests passed or failed...

## Assert

To accomplish this, JUnit offers the Assert utility class. The Assert class
contains a variety of static methods that allow you to compare the actual
results of your test to those you expected. When an assertion fails, an
AssertionException is thrown, thus marking the test as failing. If a test method
completes execution without an exception, the test is considered successful. For
tests that contain a large amount of logic it's typically a best practice to use
multiple assertions within the test to better identify the earliest point of
failure. Our sample class is fairly simple with only 3 methods so it makes the
most sense to create 3 separate cases to test each one, like so:

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

Since we know what the input was for our tests (the parameters passed into the
constructor), we can easily determine what the right answer should be. So, we
simply take the computed value from our methods and compare them with our
correct answer.

But let's say we need to test our method to make sure it's throwing an exception
when given certain invalid inputs. In such a case, you would simply use the
@Test annotation field “expected,” with the name of the exception you’re hoping
gets thrown rather than an assertion.

    @Test(expected = MySampleException.class)
    public void testToString() {
       String fullName = _mySampleNamenClass.toString();
    }


## @After

Once each test is finished, you want to make sure you clean up anything that was
changed or created during it. We don't really need to worry about cleaning up
after our tests since our class doesn't affect or create any objects. Our
@Before will take care of making sure our MySampleNameClass object always starts
fresh.

## @AfterClass

Since we did not utilize a method annotated with @BeforeClass, there's nothing
for us to do here.

## Running Unit Tests

Once your test classes are completed, you're ready to run them. Doing so with
Liferay and the Blade tool is very simple. To run a specific module's Unit
Tests, you simply need to navigate to the module's directory in a command line
and run the command blade gw test. The command will compile your module, its
tests, and finally run your tests. Upon running the command, assuming everything
has been set up correctly and your tests pass successfully, you should see the
following output:

![Figure x: Commandline output of successful test execution looks like this.](../../images/junit-command-output.png)

However it's not very descriptive - nor is it clear that your tests were run
and/or succeeded. Thankfully, this task also generates an HTML report for us
that does contain more detailed information regarding the test or tests that
were run. They can be found within your module's build/reports/ directory under
the name "index.html." Open up the file with a web browser and you'll see a list
of all packages that included tests that had been run, along with some overall
testing metrics. Since we only had one package that contained tests, we can
click through the listed package. Clicking through displays a list of test
classes that the selected package contained. Again, we only had one test class,
so we can go ahead and click through the class name. Finally, we're met with a
list of all test methods that had been run, along with information regarding
their results:

![Figure x: JUnit produces HTML files that show test result details.](../../images/junit-results-html.png)

Now we can be pretty sure that all of our test methods were, in fact, run and
were successful. While this is certainly helpful, it's even more helpful when
checking for failing tests. Let's go ahead and change our expected values in our
assertions to deliberately force the tests to fail. After changing some of the
values, running blade gw test again will yield the following results:

![Figure x: Commandline output of failing tests looks like this.](../../images/junit-failure-command-output.png)

The output clearly shows that the three tests are now failing, noting that the
failure stemmed the comparison between the expected and actual values
(org.junit.ComparisonFailure) at the line numbers indicated. Taking a look at
the newly generated report and clicking through the test class, we now see a
stack trace for each of the failing tests:

![Figure x: Here's HTML for a failed test.](../../images/junit-failure-results-html.png)

The stack traces tell us exactly why the test failed - we expected to see 21
(the change we made to ensure it failed), but the actual result of the logic we
tested was 20. Unless you're using a debugger to step through your tests, this
output is critical in determining whether the failure is the result of your
logic or of the expected values used in the assertions, thus giving you a
clearer direction as to what may need to be modified.

## Dependencies

Our simple class was entirely self-contained so there wasn't any need for us to
declare any dependencies in our build.gradle file. If, however, you require
access to classes external to your module, you can declare these these
dependencies within your build.gradle. Rather than using compile or provided,
your test dependencies are declared by using testCompile:

    testCompile group: "com.sample", name: "com.sample.external", version: "1.0.0"

Note that while you can use external modules/libraries in your tests, you will
still be unable to access any services thereof - which makes sense since we're
running our tests independent of any Liferay instance running.
