<h1 id="title">Javadoc Guidelines</h1>

<p>We welcome your API documentation contributions to Liferay's Java source code. Following these guidelines helps to ensure that Liferay classes are documented sufficiently and consistently. The guidelines also explain how to leverage our Javadoc tools and submit your contributions.</p>

<p>The sections <a href="/participate/javadoc-guidelines#moduleComments">Module Comments</a> and <a href="/participate/javadoc-guidelines#packageComments">Package Comments</a> provide you with instructions on how to provide a general overview of modules and packages.&nbsp;The sections <a href="/participate/javadoc-guidelines#classComments">Class Comments</a> and <a href="/participate/javadoc-guidelines#methodComments">Method Comments</a>&nbsp;provide you with simple instructions on how to describe Java classes and methods.&nbsp;<span style="word-spacing: normal;">Subsequent sections guide you in describing the <a href="/participate/javadoc-guidelines#topTips">most important tips</a>&nbsp;for writing Javadoc</span><span style="word-spacing: normal;">, <a href="/participate/javadoc-guidelines#formattingAndBuildingJavadoc">formatting and building Javadoc</a></span><span style="word-spacing: normal;">, and </span><a href="/participate/javadoc-guidelines#sendingYourPullRequest" style="word-spacing: normal;">submitting your contributions</a><span style="word-spacing: normal;">. This article covers the main points and most important rules to follow. If you're interested in an extensive amount of examples and more advanced information, visit the </span><a href="/participate/advanced-javadoc-guidelines" style="word-spacing: normal;">Advanced Javadoc Guidelines</a><span style="word-spacing: normal;">.</span></p>

<h2><a id="moduleComments" name="moduleComments"></a>MODULE COMMENTS</h2>

<p>You can provide documentation for a module by writing HTML in an <em>overview.html</em> file and inserting it in the module's <em>/src/main/java</em> directory. When the module's Javadoc is generated for its JAR file, the module's description will be available when viewing the module's HTML.</p>

<h2><a id="packageComments" name="packageComments"></a>PACKAGE COMMENTS</h2>

<p>You can provide documentation for Java packages by writing HTML in a <em>package.html</em> file and inserting it in the package you'd like to describe. When Liferay's documentation is generated on <a href="https://docs.liferay.com/">docs.liferay.com</a>, the package description will be available when viewing the package. For example, view the <a href="https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/counter/kernel/model/package-summary.html">com.liferay.counter.kernel.model</a> package's description.</p>

<h2><a id="classComments" name="classComments"></a>Class Comments</h2>

<p>The following information should be present in the following order in the class comment:</p>

<ul>
	<li>Initial class description (paragraph/sentence)
	<ul>
		<li><strong>First sentence</strong> - Should describe the class clearly and concisely. (<strong>required</strong>)</li>
		<li><strong>Followup sentences</strong> - Support the first sentence with important points about the class. Note, these sentences show up in the description for the class but do not show up in the summary table for the class. (optional)</li>
	</ul>
	</li>
	<li>Detailed class description (additional paragraph(s))
	<ul>
		<li>Provide more information on the class's purpose, abilities, and general role. For some classes (simple utility classes for instance) this additional information is not necessary if the initial paragraph provides an adequate description.</li>
		<li>Usage examples or <em>@link</em> tags to where the class can be seen in use.</li>
	</ul>
	</li>
	<li><a href="/participate/advanced-javadoc-guidelines#authortags">@author tags</a> for each author of the class, from first to most recent. (<strong>required</strong>)</li>
	<li><a href="/participate/advanced-javadoc-guidelines#seetags">@see tags</a> to other closely related classes whose Javadocs gives the reader a clearer picture of the purpose of this class.</li>
	<li><a href="/participate/advanced-javadoc-guidelines#sincetags">@since tags</a>. (as applicable)</li>
	<li><a href="/participate/advanced-javadoc-guidelines#deprecatedtags">@deprecated tags</a>. (as applicable)</li>
</ul>

<p>For more detailed information and examples for writing class descriptions, see the <a href="/participate/advanced-javadoc-guidelines#ClassDescriptions">Class Descriptions</a> section. A simple example class comment is provided below:</p>

<p>Example (class comment):</p>

<pre>
/**
 * Represents an example class. If a basic description requires more than one
 * sentence, include it in the first paragraph.
 *
 * &lt;p&gt;
 * Example of a second paragraph. Note the blank line between the first &lt;p&gt; tag
 * and the end of the first paragraph.
 * &lt;/p&gt;
 *
 * @author Brian Wing Shun Chan
 * @see	   BigExample
 */
public class Example {
	...
}

</pre>

<h2><a id="methodComments" name="methodComments"></a>Method Comments</h2>

<p>The following information should be present in the Javadoc comment on each method:</p>

<ul>
	<li>A short, one sentence description of the method.</li>
	<li>Additional sentences and/or paragraphs providing more information on the method's purpose and function. Any information the user of the method would find useful should be included here, including special requirements, circumstances where it should/should not be used, etc. For many methods, this additional explanation is not necessary, particularly for getters and setters.</li>
	<li>Usage examples if necessary and possible. Trivial methods don't need examples, and if an example would be extremely large, simply <em>@link</em> to a place the method is used.</li>
	<li>If the method is only used in one or two places, <em>@link</em> to the methods it is called from. This helps later developers to understand its role in Liferay.</li>
	<li>No need to mention matching a company ID parameter. It should be understood and is not worth cluttering the description.</li>
</ul>

<p>The following information should always be present in the following order in the Javadoc tags for the method:</p>

<ul>
	<li><a href="/participate/advanced-javadoc-guidelines#paramtags">@param</a> - The method parameters, in order, with descriptions.</li>
	<li><a href="/participate/advanced-javadoc-guidelines#returntags">@return</a> - All possible return values, including <em>null</em>. If the method is void, do not include this.</li>
	<li><a href="/participate/advanced-javadoc-guidelines#throwstags">@throws</a> - The exceptions the method can throw, in order, with explanations of what would trigger them.</li>
	<li><a href="/participate/advanced-javadoc-guidelines#seetagsformethods">@see</a> - (as applicable)</li>
	<li><a href="/participate/advanced-javadoc-guidelines#sincetagsformethods">@since</a> - (as applicable)</li>
	<li><a href="/participate/advanced-javadoc-guidelines#deprecatedtagsformethods">@deprecated</a> - (as applicable)</li>
</ul>

<p>For more detailed information and examples for writing method descriptions, see the <a href="/participate/advanced-javadoc-guidelines#MethodDescriptions">Method Descriptions</a> section. A simple class description is provided below:</p>
Example (method comments):

<pre>
/**
 * Returns the localized preferences values for the key, optionally using the
 * default language if the no localization exists for the requested
 * language.
 *
 * @param  preferences the preferences container
 * @param  key the preferences key
 * @param  languageId the primary key of the language
 * @param  useDefault whether to use the default language if no localization
 *         exists for the requested language
 * @return the localized preferences values. If <code>useDefault</code> is
 *         <code>false</code> and no localization exists for the requested
 *         language, an empty array will be returned.
 * @throws PortalException if a portal exception occurred
 */
public String[] getPreferencesValues(
	PortletPreferences preferences, String key, String languageId,
	boolean useDefault);
    throws PortalException;
</pre>

<p style="word-spacing: 0px;"><strong>Method comments for interfaces and sub-classes</strong></p>

<ul>
	<li><strong>Interface method Javadoc</strong>&nbsp;is *always* required.</li>
	<li><strong>Sub-class Method Javadoc</strong>&nbsp;is required for methods of sub-classes that have differences from their parent class(es) that should be noted. Some examples are sub-class methods that have different behavior, different side-effects, and/or different requirements of their parameters. In most cases, the Javadoc must be manually transferred from the interface or super-class to the sub-class if you think the descriptions should stay the same. If the interface or super-class is in the same module as the sub-class, the Javadoc is transferred automatically.</li>
</ul>

<h2><a id="topTips" name="topTips"></a>Top Tips</h2>

<ol>
	<li>If you're new to Javadoc, familiarize yourself with the <a href="http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html">official Javadoc style guide</a>.</li>
	<li>Starting with an <i>action word</i>, describe what each <a href="/participate/javadoc-guidelines#classComments">class</a>&nbsp;and <a href="/participate/javadoc-guidelines#methodComments">method</a>&nbsp;does.</li>
	<li>Avoid just restating the class or method name (e.g., Avoid <em>updateLocalization(...) → Updates the localization</em> <strong>OR </strong><em>@param key the key</em>).</li>
	<li>Describe the most important details in the first sentence, because it is the only one shown in the class/method summary.</li>
	<li>Include all relevant tags (<a href="/participate/advanced-javadoc-guidelines#paramtags">@param</a>, <a href="/participate/advanced-javadoc-guidelines#returntags">@return</a>, etc) for each method; without them, the method Javadoc is incomplete.</li>
	<li>Start a method description with <em>Returns</em>, if the method returns a value.</li>
	<li>Don’t explicitly refer to collections in descriptions (e.g., <em>list of articles</em>). Use, instead, the plural (e.g., <em>the articles</em>) or “all the” (e.g., <em>all the articles</em>).</li>
	<li>When referring to another class, try to either link using <em>{@link entity}</em> (e.g., <em>{@link com.liferay.osb.lcs.model.LCSClusterNode}</em>) or refer to the class in common terms (e.g., <em>LCS cluster node</em>). The first alternative provides direct access to the class's Javadoc, the second alternative makes for smooth reading.</li>
	<li>Avoid referring explicitly to parameters by name; instead, refer to them in general terms (e.g., <em>class name ID</em> instead of &lt;code&gt;<em>classNameId&lt;/code&gt;</em>).</li>
	<li>Refer to primary keys explicitly, even when the parameter is an ID. For example, use <em>“the primary key of the something”</em> instead of <em>“the ID of the something”</em>.</li>
	<li>Begin <a href="/participate/advanced-javadoc-guidelines#ParameterBoolean">boolean parameter</a> descriptions with <em>whether</em>.</li>
	<li>Punctuate every class and method description (sentence or phrase) with a period.</li>
	<li>Only punctuate an initial description (sentence or phrase) for a method tag <a href="/participate/advanced-javadoc-guidelines#paramtags">@param</a>, <a href="/participate/advanced-javadoc-guidelines#returntags">@return</a>, or <a href="/participate/advanced-javadoc-guidelines#throwstags">@throws</a> if it's followed by a sentence(s). Write followup text in complete sentences.</li>
	<li>Describe exceptions (e.g., <a href="/participate/advanced-javadoc-guidelines#throwstags">@throws</a>) in past tense.</li>
	<li>Only include a class's package in an <em>@see</em> and <em>@link</em> tag if the class is in another package. Refer to the <a href="/participate/advanced-javadoc-guidelines#JavadocLinking">Javadoc Linking</a> subsection for comprehensive rules and examples on linking to other methods/classes.</li>
	<li>Don't wrap the first paragraph of a Javadoc comment with paragraph tags. Do wrap subsequent paragraphs with paragraph tags (e.g., &lt;p&gt;second paragraph&lt;/p&gt;).</li>
	<li>Conform Javadoc to 80 columns, if possible.</li>
</ol>

<p>Terrific! You're off to a great start to writing Javadoc. The following sections describe the most important aspects of writing class comments and method comments, using the Javadoc Formatter, and submitting your contributions.</p>

<h2><a id="formattingAndBuildingJavadoc" name="formattingAndBuildingJavadoc"></a>Formatting and Building Javadoc</h2>

<p><strong>Before committing any new or modified Javadocs, run ant format-javadoc on your code first!</strong> This will automatically wrap your comments to the proper width, format html tags, and line up Javadoc tags. The formatter will also propagate your Javadoc changes to the JSON interface for the class.</p>

<p><strong>1. Compile the code</strong></p>

<p>To compile the Portal Core, do this:</p>

<pre>
cd liferay-portal
ant compile
cd portal-impl
ant compile compile-test
</pre>

<p><strong>2. Run the Javadoc Formatter</strong></p>

<p>To format Javadoc <strong>in a module under <em>liferay-portal/modules/</em></strong>, execute this in the module's root folder:</p>

<pre>
gradlew formatJavadoc</pre>

<p><br />
The optional arguments you can pass are:</p>

<ul>
	<li><em>-DformatJavadoc.limit="SomeClassName1,SomeClassName2,com.liferay.portal.**"</em> -&nbsp;<span style="line-height: 27px;">Runs the formatter on more than one class/package. Limits must be wrapped in double quotes. This can be combined with the following two options, or completely removed (which runs the formatter on the entire code base).</span></li>
	<li><em>-DformatJavadoc.init=true</em> -&nbsp;<span style="line-height: 27px;">Inserts place holders for all comment elements.</span></li>
	<li><em>-DformatJavadoc.update=true</em>&nbsp;-&nbsp;<span style="line-height: 27px;">Inserts place holders for comment elements that are not up to date with the current method signatures.&nbsp;</span>For example, the formatter will add comment placeholders for parameters that are in method signatures but not present in the Javadoc comments for those methods.</li>
	<li>-D<em>formatJavadoc</em>.<em>generate.xml</em> - Generates an XML document of the Javadoc that shows for a module's web services API page.</li>
</ul>

<p>To format Javadoc in a class you've edited <strong>in a Portal Core module</strong> (e.g., in portal-kernel, portal-impl, etc), run:</p>

<pre>
ant format-javadoc -Dlimit=SomeClassName</pre>

<p>There are several other alternative options for invoking basic Javadoc formatting and updates.</p>

<ul>
	<li><em>-Dlimit="SomeClassName1,SomeClassName2,com.liferay.portal.**"</em> -&nbsp;<span style="line-height: 27px;">Runs the formatter on more than one class/package. Limits must be wrapped in double quotes. This can be combined with the following two options, or completely removed (which runs the formatter on the entire code base).</span></li>
	<li><em>-Dinit=true</em> -&nbsp;<span style="line-height: 27px;">Inserts place holders for all comment elements.</span></li>
	<li><em>-Dupdate=true</em>&nbsp;-&nbsp;<span style="line-height: 27px;">Inserts place holders for comment elements that are not up to date with the current method signatures.&nbsp;</span>For example, the formatter will add comment placeholders for parameters that are in method signatures but not present in the Javadoc comments for those methods.</li>
</ul>

<p><strong>3. Building Javadoc (optional)</strong></p>

<p>To optionally build a module's Javadoc HTML to the module's <em>build/docs/</em> folder to see what it looks like, execute this:</p>

<pre>
gradlew javadoc</pre>

<p>Javadoc HTML is only generated for modules that export their packages. This is because modules that do not export their packages are not considered public API, so there's no need to generate them. To check if your module exports packages, open its <em>bnd.bnd</em> file in the root folder and verify it has the following syntax:</p>

<pre>
Export-Package:\</pre>

<p>If it doesn't but you'd still like to generate HTML to see what it looks like, add the above syntax followed by the packages you want to generate.</p>

<h2><a id="sendingYourPullRequest" name="sendingYourPullRequest"></a>Sending Your Pull Request</h2>

<p>Send your Javadoc commit (no code changes) in a separate pull request to Cody Hoag (GitHub user: <strong><i>codyhoag</i></strong>). Specify your Jira ticket number and the word "Javadoc" in your commit message. For example, "LPS-1234 Javadoc".</p>

<p>To request <strong>re-adding</strong> Javadoc or comments that have been removed from a file, please open an <a href="https://issues.liferay.com/browse/LRDOCS">LRDOCS</a> JIRA ticket:</p>

<ul>
	<li><strong>Summary:</strong> Re-add Javadoc for .... (name or prefix of class/file)</li>
	<li><strong>Component:</strong>
	<ul>
		<li><i>API-Javadoc</i> - For Javadoc</li>
		<li><i>Config Files</i> - For comments/descriptions in properties, XML, or DTD files</li>
	</ul>
	</li>
	<li><strong>Affected Release:</strong> 7.0.x, 6.2.x, 6.1.x</li>
	<li><strong>Description:</strong> Include the <i>commit number</i> of the original Javadoc commit and/or the Javadoc removal commit</li>
</ul>

<p>For more extensive examples and comprehensive guidelines, visit the <a href="/participate/advanced-javadoc-guidelines">Advanced Javadoc Guidelines</a> article.</p>