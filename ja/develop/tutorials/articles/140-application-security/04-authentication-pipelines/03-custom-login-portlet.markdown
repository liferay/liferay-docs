---
header-id: writing-a-custom-login-portlet
---

# カスタムログインポートレットの作成

[TOC levels=1-4]

ユーザーの認証エクスペリエンスを完全にカスタマイズする必要がある場合は、独自のログインポートレットを作成できます。マクロレベルでのこのメカニズムは、他のポートレットの記述と変わりません。したがって、その方法について理解したい場合は、[チュートリアルのポートレットのセクション](/docs/7-0/tutorials/-/knowledge_base/t/portlets)を参照してください。

このチュートリアルでは、ユーザーを認証する[Liferay MVCポートレット](/docs/7-0/tutorials/-/knowledge_base/t/liferay-mvc-portlet)の関連部分のみを示します。[認証パイプライン](/docs/7-0/tutorials/-/knowledge_base/t/authentication-pipelines)を呼び出して、ユーザーを選択した場所にリダイレクトする方法を学習します。

## @product@への認証

[このZIPファイル](https://portal.liferay.dev/documents/113763090/114000186/MyCustomLoginPortlet.zip)に含まれているサンプルプロジェクトを出発点として使用できます。

**注：**サンプルプロジェクトを使用する場合は、セッションタイムアウトのポータルプロパティを次のように設定します。

|     session.timeout.auto.extend.offset=45

これが必要なのは、デフォルト（[LPS-68543](https://issues.liferay.com/browse/LPS-68543)の時点）設定が`0`であり、ブラウザが`extend_session`呼び出しを実行するためです。このため、ユーザーがログインを2回試みなければならない場合があります。

ログインに使用されるビュー、またはすでにログインしているユーザーを表示するビューは1つのみです。

    <%@ include file="/init.jsp" %>
    
    <p>
    <b><liferay-ui:message key="myloginportlet_MyLogin.caption"/></b>
    </p>
    
    <c:choose>
    <c:when test="<%= themeDisplay.isSignedIn() %>">
    
    <%
    String signedInAs = HtmlUtil.escape(user.getFullName());
    
    if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
    String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());
    
    signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
    }
    %>
    
    <liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" />
    </c:when>
    <c:otherwise>
    
    <%
    String redirect = ParamUtil.getString(request, "redirect");
    %>
    
    <portlet:actionURL name="/login/login" var="loginURL">
    <portlet:param name="mvcRenderCommandName" value="/login/login" />
    </portlet:actionURL>
    
    <aui:form action="<%= loginURL %>" autocomplete='on' cssClass="sign-in-form" method="post" name="loginForm">
    
    <aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
    <aui:input name="redirect" type="hidden" value="<%= redirect %>" />
    
    <aui:input autoFocus="true" cssClass="clearable" label="email-address" name="login" showRequiredLabel="<%= false %>" type="text" value="">
    <aui:validator name="required" />
    </aui:input>
    
    <aui:input name="password" showRequiredLabel="<%= false %>" type="password">
    <aui:validator name="required" />
    </aui:input>
    
    <aui:button-row>
    <aui:button cssClass="btn-lg" type="submit" value="sign-in" />
    </aui:button-row>
    
    </aui:form>
    </c:otherwise>
    </c:choose>

これはサンプルプロジェクトであるため、このフォームでは、電子メールアドレスによる認証（デフォルト設定）がハードコードされていることに注意してください。 現在のページはフォーム上の非表示フィールドとして送信されるため、ポートレットはユーザーをそのページにリダイレクトできますが、これを任意の値に設定することもできます。

ポートレットは、単一の[アクションコマンド](/docs/7-0/tutorials/-/knowledge_base/t/mvc-action-command)を使用してこのフォームのすべての処理を処理します（簡潔にするためにインポートは省略されています）。

    @Component(
    property = {
    "javax.portlet.name=MyLoginPortlet",
    "mvc.command.name=/login/login"
    },
    service = MVCActionCommand.class
    )
    public class MyLoginMVCActionCommand extends BaseMVCActionCommand {
    
    @Override
    protected void doProcessAction(ActionRequest actionRequest,
    ActionResponse actionResponse) throws Exception {
    
    ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
    WebKeys.THEME_DISPLAY);
    
    HttpServletRequest request = PortalUtil.getOriginalServletRequest(
    PortalUtil.getHttpServletRequest(actionRequest));
    
    HttpServletResponse response = PortalUtil.getHttpServletResponse(
    actionResponse);
    
    String login = ParamUtil.getString(actionRequest, "login");
    String password = actionRequest.getParameter("password");
    boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");
    String authType = CompanyConstants.AUTH_TYPE_EA;
    
    AuthenticatedSessionManagerUtil.login(
    request, response, login, password, rememberMe, authType);
    
    actionResponse.sendRedirect(themeDisplay.getPathMain());
    }
    
    }

ここで扱いにくい/異常な唯一のコードは、`HttpServletRequest`と`HttpServletResponse`を取得する必要があることです。これは、認証のために@product@のAPIを呼び出すために必要です。アクションコマンドの最後に、ポートレットはユーザーを同じページに送信するリダイレクトを送信します。これを任意のページにすることもできます。

独自のログインポートレットを実装すると、認証プロセスを完全に制御できます。

## 関連トピック

[Password-Based Authentication Pipelines](/docs/7-0/tutorials/-/knowledge_base/t/password-based-authentication-pipelines)

[Auto Login](/docs/7-0/tutorials/-/knowledge_base/t/auto-login)
