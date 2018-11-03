# Understanding the App Review Process [](id=understanding-the-app-review-process)

The Liferay Marketplace app QA/review process begins as soon as you submit your
app for review. Every third-party app submitted to the Liferay Marketplace is
reviewed by our team to ensure that certain standards for information are upheld
and the app installs as expected. *Liferay cannot, however, be a substitute for
your own testing and debugging team*. Ultimately, you must test, refine, and
ensure that your app functions as promised and performs as expected.

+$$$

**Note:** Liferay is not responsible for the behavior (or misbehavior) of apps
on the Marketplace. For details regarding this, consult the *Liferay Marketplace
User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual
*End User License Agreements* associated with each app.

$$$

Once you've submitted your app for review, your app's status changes as it moves
through the review process. We email you on status changes and provide as much
detail as we can if we discover potential issues with your app. Overall, we
don't want our app review process to feel like a barrier or a black box. We love
having new apps in the Marketplace, and we try to be as helpful as we can
throughout the approval process!

![Figure 1: Liferay informs you at every step during the QA/review process.](../../../images/app_review_process.png)

If you submit an updated version of a previously approved app, you'll see these
statuses: Approved (Version Unsubmitted), Approved (Version Pending), Approved
(Version Pending QA), and Approved (Version Denied).

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
                    Our team
                    <a href="/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app#marketplace-app-metadata-guidelines">
                    reviews your app's metadata</a>
                    to confirm that titles, descriptions, images, etc. are
                    appropriate.
                </p>
            </td>
            <td class="second-column"> ~1 week </td>
        </tr>
        <tr>
            <td class="first-column">
                <strong> App QA test </strong>
                <p> Liferay ensures that apps meet a minimal set of
                    requirements: </p>
                <ul>
                    <li>Passes anti-virus scan</li>
                    <li>Deploys successfully on standard Liferay supported
                        environments/platforms without errors.</li>
                    <li>Basic functionality "smoke" test.</li>
                </ul>
                <p>
                    Liferay does not do source code review and does not ask for
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

**Our QA Test Environments** are summarized below. At a minimum, test your app
against these environments prior to submission. If technical reasons prevent
your app from running on certain platforms (e.g., app server-specific issues),
specify compatibility in the app description and documentation so that our
review team can exclude certain test conditions, if necessary.

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
        <td class="first-column">
            <strong>Liferay Version</strong>
        </td>
        <td class="second-column">
            <strong>6.1.x</strong>
        </td>
        <td class="third-column">
            <strong>7.0.x</strong>
        </td>
        <td class="fourth-column">
            <strong>7.1.x</strong>
        </td>
    </thead>
    <tbody>
        <tr>
            <td class="first-column"> Operating Systems </td>
            <td class="second-column" colspan="3">Ubuntu 11x and Windows 10 x64</td>
        </tr>
        <tr>
            <td class="first-column"> Database </td>
            <td class="second-column">MySQL 5.5.x</td>
            <td class="third-column">MySQL 5.6.x</td>
            <td class="fourth-column">MySQL 5.7.x</td>
        </tr>
        <tr>
            <td class="first-column"> Application Server * </td>
            <td class="second-column">Tomcat 7</td>
            <td class="third-column" colspan="2">Tomcat 8</td>
        </tr>
        <tr>
            <td class="first-column"> JDK </td>
            <td class="second-column">Oracle JDK 6, 7</td>
            <td class="third-column" colspan="2">Oracle JDK 8</td>
        </tr>
        <tr>
            <td class="first-column"> Browser </td>
            <td class="second-column" colspan="3">Chrome</td>
        </tr>
    </tbody>
</table>
</div>
</div>
</div>
</div>
</div>

** \* ** You can request certification on additional, optional application 
servers. For all Liferay versions, Wildfly 10 is available. For Liferay 6.x,
Glassfish 3.1 is also available. Please add the request in the app submission
panel's *Note to testers* section.

Once your app is approved by Marketplace staff, you'll get email notification.
When your app is approved, it is made available on Marketplace. The app also
appears on your public Profile page, which lists all apps that you or your
company developed and published.

If your app is rejected, you receive an email explaining the reasons for
rejection. You can then make the requested changes and re-submit the app for
approval.

After you've successfully published your app, you might get all kinds of
feedback from users and yourself about what's right and wrong with it. The next
article explores how to make changes once you have published your app.
