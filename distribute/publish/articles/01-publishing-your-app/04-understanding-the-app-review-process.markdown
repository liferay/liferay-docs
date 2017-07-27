# Understanding the App Review Process [](id=understanding-the-app-review-process)

The Liferay Marketplace app QA/review process begins as soon as you submit your
app for review. Every third-party app submitted to the Liferay Marketplace is
reviewed by our team to ensure that certain standards for information are upheld
and the app installs as expected. *However, Liferay cannot be a substitute for
your own testing and debugging team*. Ultimately, it is the responsibility of
the developer to test, refine, and ensure that the app functions as promised and
performs as expected.

+$$$

**Note:** Liferay is not responsible for the behavior (or misbehavior) of apps
on the Marketplace. For details regarding this, consult the *Liferay Marketplace
User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual
*End User License Agreements* associated with each app.

$$$

Once you've submitted your app for review, your app will change its status as it
moves through the review process. You will be updated via email as your app's
status changes, and we will try to provide as much detail as we can if we
discover a potential issue with your app. Overall, we don't want our app review
process to feel like a barrier or a black box. We love having new apps in the
Marketplace, and we'll try to be as helpful as we can to bring your app to
approval!

![Figure 1: Here's a diagram of the the QA/review process.](../../images/app_review_process.png)

If you submit an updated version of a previously approved app, the app
statuses will display as: Approved (Version Unsubmitted), Approved (Version
Pending), Approved (Version Pending QA), and Approved (Version Denied).

The app review process consists of **Two Major Phases**:

<style>
.lego-image {
	max-height: 100%;
	max-width: 100%;
}
.max-960 {
	margin: 0 auto;
	max-width: 960px;
}
.no-max .max-960 {
	max-width: none;
}
.app-review-process-table {
	padding-bottom: 40px;
}
.app-review-process-table thead td {
	font-weight: bold;
}
.app-review-process-table .first-column {
	border-left: none;
}
.app-review-process-table .second-column {
	border-right: none;
	width: 156px;
}
.app-review-process-table td {
	border : 1px solid;
	padding: 15px;
}
</style>
<div class="lego-article app-review-process-table" id="article-33460874">
<div class="lego-article-content max-960">
<div class="aui-helper-clearfix lego-section section-1" >
<div class="aui-w100 block-1 content-column lego-block" >
<div class="content-column-content">
<table>
	<thead>
		<td class="first-column"> <strong> Review Phase </strong> </td>
		<td class="second-column"> <strong> Est. Time Frame </strong> </td>
	</thead>
	<tbody>
		<tr>
			<td class="first-column">
				<strong> App metadata review </strong>
				<p>
					Our team will
					<a href="/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app#marketplace-app-metadata-guidelines">
					review your app's metadata</a>
					to confirm that titles, descriptions, images, etc. are
					appropriate.
				</p>
			</td>
			<td class="second-column"> ~1 week </td>
		</tr>
		<tr>
			<td class="first-column">
				<strong> App QA test </strong>
				<p> Liferay will ensure that apps meet a minimal set of
					requirements: </p>
				<ul>
					<li>Passes anti-virus scan</li>
					<li>Deploys successfully on standard Liferay supported
						environments/platforms without errors.</li>
					<li>Basic functionality "smoke" test.</li>
				</ul>
				<p>
					Liferay does not do source code review and will not ask for
					your source code. Further, Liferay is not responsible for
					the behavior (or misbehavior) of apps on the Marketplace.
					Please consult the Liferay Marketplace User Agreement,
					Liferay Marketplace Developer Agreement, and the individual
					End User License Agreements associated with each app.
				</p>
			</td>
			<td class="second-column"> ~1-2 weeks </td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

**Our QA Test Environments** are summarized below. At a minimum, your app should
be tested against these environments prior to submission. If technical reasons
prevent your app from running on certain platforms (e.g., app server-specific
issues), please specify your app's requirements in the app description and
documentation so that our review team can test accordingly and exclude certain
test conditions, if necessary.

<style>
table td {
	vertical-align: top;
}
.lego-image {
	max-height: 100%;
	max-width: 100%;
}
.max-960 {
	margin: 0 auto;
	max-width: 960px;
}
.no-max .max-960 {
	max-width: none;
}
.test-environments-table td {
	border : 1px solid;
	padding: 15px;
}
.test-environments-table td.empty {
	border-left: none;
}
.test-environments-table thead td {
	text-align: center;
}
.test-environments-table .first-column {
	border-left: none;
	font-weight: bold;
}
.test-environments-table .third-column {
	border-right: none;
}
</style>
<div class="lego-article test-environments-table" id="article-33460919">
<div class="lego-article-content max-960">
<div class="aui-helper-clearfix lego-section section-1" >
<div class="aui-w100 block-1 content-column lego-block" >
<div class="content-column-content">
<table>
	<thead>
		<td class="empty">
		</td>
		<td class="">
			<strong>Automated QA</strong>
			<p>(Anti-virus & Deployment)</p>
		</td>
		<td class="third-column">
			<strong>Manual QA</strong>
			<p>(Deployment & Basic Functionality)</p>
		</td>
	</thead>
	<tbody>
		<tr>
			<td class="first-column"> Liferay Portal </td>
			<td class="">
				<ul>
					<li> QA'ed against Liferay Portal version specified in app
						submission up to Liferay Portal 7. </li>
				</ul>
			</td>
			<td class="third-column">
				<ul>
					<li> QA'ed against Liferay Portal version specified in app
						submission up to Liferay Portal 7. </li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="first-column"> Operating System </td>
			<td class="">
				<ul>
					<li> Ubuntu 11x </li>
				</ul>
			</td>
			<td class="third-column">
				<ul>
					<li> Windows 7 x64 </li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="first-column"> Database </td>
			<td class="">
				<span>For versions up to Liferay Portal 6.2</span>
				<ul>
					<li> MySQL 5.5.x </li>
				</ul>

				<span>For Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0</span>
				<ul>
					<li> MySQL 5.6.x </li>
				</ul>
			</td>
			<td class="third-column">
				<ul>
					<span>For versions up to Liferay Portal 6.2</span>
					<ul>
						<li> MySQL 5.5.x </li>
					</ul>

					<span>For Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0</span>
					<ul>
						<li> MySQL 5.6.x </li>
					</ul>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="first-column"> Application Server* </td>
			<td class="">
				<span>For versions up to Liferay Portal 6.2</span>
				<ul>
					<li> Tomcat 7 </li>
					<li> Glassfish 3.1 </li>
					<li> Wildfly 10 </li>
				</ul>

				<span>For Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0</span>
				<ul>
					<li> Tomcat 8 </li>
					<li> Wildfly 10 </li>
				</ul>
			</td>
			<td class="third-column">
				<ul>
					<li> Tomcat 7 </li>
				</ul>

				<span>For Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0</span>
				<ul>
					<li> Tomcat 8 </li>
					<li> Wildfly 10 </li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="first-column"> JDK </td>
			<td class="">
				<span>For versions up to Liferay Portal 6.2</span>
				<ul>
					<li> Oracle JDK 6 </li>
					<li> Oracle JDK 7 </li>
				</ul>

				<span>For Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0</span>
				<ul>
					<li>Oracle JDK 8</li>
				</ul>
			</td>
			<td class="third-column">
				<span>For versions up to Liferay Portal 6.2</span>
				<ul>
					<li> Oracle JDK 6 </li>
					<li> Oracle JDK 7 </li>
				</ul>

				<span>For Liferay Digital Enterprise 7.0 and Liferay Portal CE 7.0</span>
				<ul>
					<li>Oracle JDK 8</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td class="first-column"> Browser </td>
			<td class="">
				<ul>
					<li> Firefox </li>
				</ul>
			</td>
			<td class="third-column">
				<ul>
					<li> Firefox </li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>
</div>

**Important:** Unless specified otherwise in your app description or
documentation, your app must run under all specified app servers in order to be
approved.

You'll receive an email confirmation, once your app is approved by Marketplace
staff. And the moment your app is approved, it is made available on Marketplace.
The app is also shown on your public Profile page, which lists all apps that you
or your company's developed and published.

If your app is rejected, an email is sent to the email address associated with
the app, along with a note explaining the reasons for rejection. At that point,
you can make the requested changes, and re-submit the app for approval.

After you've successfully published your app, you'll likely get all kinds
of feedback from users and yourself about what's right and wrong with it. The
next article explores how to make changes once you have published your app.
