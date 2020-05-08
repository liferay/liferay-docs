---
header-id: auto-login
---

# 自動ログイン

[TOC levels=1-4]

@product@は多種多様な[認証メカニズム](/docs/7-0/deploy/-/knowledge_base/d/liferay-portal-security)をサポートしていますが、ユーザー認証には自社開発のシステムや他の製品も使用できます。そのために、認証システムをサポートする自動ログインコンポーネントを作成できます。

自動ログインコンポーネントは、要求に何らかの形でユーザーに関連付けられるもの（Cookie、属性など）が含まれているかどうかをチェックできます。コンポーネントがその関連付けを作成できる場合、そのユーザーを認証できます。

## 自動ログインコンポーネントの作成

[Declarative Servicesコンポーネント](/docs/7-0/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide#creating-component-classes)を作成します。
コンポーネントは、`com.liferay.portal.kernel.security.auto.login.AutoLogin`インターフェイスを実装する必要があります。テンプレートの例を以下に示します。

    import com.liferay.portal.kernel.security.auto.login.AutoLogin;
    
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    import org.osgi.service.component.annotations.Component;
    
    @Component(immediate = true)
    public class MyAutoLogin implements Autologin {
    
    public String[] handleException(
    HttpServletRequest request, HttpServletResponse response,
    Exception e)
    throws AutoLoginException {
    
    /* This method is no longer used in the interface and can be
    left empty */
    
    }
    
    public String[] login(
    HttpServletRequest request, HttpServletResponse response)
    throws AutoLoginException {
    
    /* Your Code Goes Here */
    
    }
    
    }

ご覧のとおり、`HttpServletRequest`オブジェクトと`HttpServletResponse`オブジェクトにアクセスできます。サインオンソリューションが、Cookie、属性、パラメータなどのユーザーを識別するものをここに配置する場合は、その情報を取得し、ユーザー情報を取得してそのユーザーを認証するために必要なアクションを実行できます。

たとえば、ユーザーキーの暗号化された値を含む要求属性があるとします。これは、ユーザーキーの値を認識し、それを暗号化して要求属性として追加したサードパーティシステムでユーザーが認証されている場合にのみ存在できます。値を読み取り、同じ事前共有キーを使用して値を復号化し、その値を使用してユーザーを検索および認証するコードを作成できます。

これはすべて`login`メソッドによって行われます。このメソッドは、次の順序で3つのアイテムを含む`String`配列を戻す必要があります。

- ユーザーID
- ユーザーパスワード
- パスワードが暗号化されている場合は`true`、暗号化されていない場合は`false`のブールフラグ（`Boolean.TRUE.toString()`または`Boolean.FALSE.toString()`）。

リダイレクトの送信は、オプションの`AutoLogin`機能です。`AutoLogin`はサーブレットフィルターチェーンの一部であるため、2つのオプションがあります。両方とも、要求に属性を設定することで実装されます。属性は次のとおりです。

- `AutoLogin.AUTO_LOGIN_REDIRECT`：このキーにより、`AutoLoginFilter`はフィルターチェーンの実行を停止し、属性の値で指定された場所にすぐにリダイレクトします。

- `AutoLogin.AUTO_LOGIN_REDIRECT_AND_CONTINUE`：このキーにより、`AutoLoginFilter`はリダイレクトを設定し、チェーン内の残りのフィルターの実行を継続します。

自動ログインコンポーネントは、@product@がまだサポートしていないシステムに認証メカニズムを提供する便利な方法です。必要な統合を提供するために、これらは短時間で記述可能です。

## 関連トピック

[Password-Based Authentication Pipelines](/docs/7-0/tutorials/-/knowledge_base/t/password-based-authentication-pipelines)

[Writing a Custom Login Portlet](/docs/7-0/tutorials/-/knowledge_base/t/writing-a-custom-login-portlet)
