---
header-id: password-based-authentication-pipelines
---

# パスワードベースの認証パイプライン

[TOC levels=1-4]

デフォルトでは、ユーザーが資格情報を送信すると、それらの資格情報が@product@のデータベースと照合されますが、認証をLDAPサーバーに委任することもできます。資格情報をデータベースと照合する代わりに、またはそれに加えて、ご使用の環境で他のシステムを使用するには、認証パイプラインのステップとして`Authenticator`を書き込み、それを挿入できます。

`Authenticator`はログインポートレットによって確認されるため、ユーザーを外部システムにリダイレクトする必要がある場合、または認証にトークンが必要な場合は、このアプローチを使用できません。そのような場合は、[自動ログイン](/docs/7-0/tutorials/-/knowledge_base/t/auto-login)または[Auth Verifier](/docs/7-0/deploy/-/knowledge_base/d/authentication-verifiers)を使用する必要があります。

`Authenticator`を使用すると、次のことを実行できます。

- 外部システムで管理されているユーザー名とパスワードで@product@にログインする
- セカンダリユーザーの認証チェックを行う
- ユーザー認証が失敗したときに追加の処理を実行する

引き続き読み進め、`Authenticator`を作成する方法を学習してください。

## Authenticatorの構造

`Authenticator`は、認証パイプラインのさまざまなステップに実装されます。手順は次のとおりです。

1. `auth.pipeline.pre`：データベースへのデフォルト認証の前に来ます。このステップでは、データベースに対する資格情報の検証をスキップできます。
`Authenticator`によって実装されます。

2. データベースに対するデフォルト（オプション）認証。

3. `auth.pipeline.post`：さらなる（2次、3次）認証チェック。
`Authenticator`によって実装されます。

4. `auth.failure`：認証が失敗した後に追加の処理を実行します。
`AuthFailure`によって実装されます。

`Authenticator`を作成するには、モジュールを作成し、インターフェイスを実装するコンポーネントを追加します。

    @Component(
    immediate = true, property = {"key=auth.pipeline.post"},
    service = Authenticator.class
    )
    public class MyCustomAuth implements Authenticator {
    
    public int authenticateByEmailAddress(
    long companyId, String emailAddress, String password,
    Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
    throws AuthException {
    
    return Authenticator.SUCCESS;
    }
    
    public int authenticateByScreenName(
    long companyId, String screenName, String password,
    Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
    throws AuthException {
    
    return Authenticator.SUCCESS;
    }
    
    public int authenticateByUserId(
    long companyId, long userId, String password,
    Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
    throws AuthException {
    
    return Authenticator.SUCCESS;
    }
    }

この例は、構造を確認できるように簡略化されています。まず、`@Component`アノテーションの内容に注目してください。

- `immediate = true`：コンポーネントをすぐに開始するように設定します
- `key=auth.pipeline.post`：`auth.pipeline.post`フェーズで実行する`Authenticator`を設定します。`auth.pipeline.pre`フェーズを実行するには、`auth.pipeline.pre`に置き換えます。
- `service = Authenticator.class`：`Authenticator`サービスを実装します。すべての`Authenticator`がこれを実行します。

アノテーションの下の3つのメソッドは、認証の設定方法（電子メールアドレス（デフォルト）、スクリーン名、またはユーザID）に基づいて実行されます。
`Authenticator`がタスクを実行できない場合（認証対象のシステムが利用できない場合や依存関係が見つからない場合など）、すべてのメソッドが`AuthException`をスローします。この基本的な例のメソッドは、すべての場合において成功を返します。モジュールをデプロイしても効果はありません。当然、追加の機能を提供する必要があります。次に、その方法を示す例を示します。

## Authenticatorの作成

この例の`Authenticator`では、電子メールアドレスが*@liferay.com*または*@example.com*で終わるユーザーのみが許可されます。すべてを実行する1つのモジュールを使用してこれを実装できます。他のモジュールが電子メールアドレスを検証する機能を使用できると思われる場合は、2つのモジュールを作成できます。1つは`Authenticator`を実装し、もう1つは電子メールアドレスを検証します。この例は、2つのモジュールのアプローチを示しています。

`Authenticator`を作成するには、実装用のモジュールを作成します。これに最も適したブレードテンプレートは、[サービステンプレート](/docs/7-0/reference/-/knowledge_base/r/using-the-service-template)です。
モジュールを取得したら、`Activator` を簡単に作成できます。

1. `@Component`アノテーションを追加して、`Activator`を適切な認証パイプラインフェーズにバインドします。

2. `Authenticator`インターフェイスを実装し、必要な機能を提供します。

3. モジュールをデプロイします。[Blade CLI](/docs/7-0/tutorials/-/knowledge_base/t/blade-cli)を使用している場合は、`blade deploy`を介してこれを実行します。

この例では、これを2回行います。1回は電子メールアドレス検証モジュール用、もう1回は`Authenticator`自体用です。`Authenticator`プロジェクトにはバリデーター用のインターフェイスが含まれ、バリデータプロジェクトには実装が含まれています。`Authenticator`モジュールの構造は次のように見えます。

![図1：Authenticatorモジュールには、バリデーターのインターフェイスと認証子が含まれています。](../../../images/auth-pipeline-authenticator-project.png)

`Authenticator`が最も関連性が高いため、最初に検証します。

    package com.liferay.docs.emailaddressauthenticator;
    
    import java.util.Map;
    
    import com.liferay.docs.emailaddressauthenticator.validator.EmailAddressValidator;
    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.kernel.log.LogFactoryUtil;
    import com.liferay.portal.kernel.security.auth.AuthException;
    import com.liferay.portal.kernel.security.auth.Authenticator;
    import com.liferay.portal.kernel.service.UserLocalService;
    
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    import org.osgi.service.component.annotations.ReferenceCardinality;
    import org.osgi.service.component.annotations.ReferencePolicy;
    
    @Component(
    immediate = true,
    property = {"key=auth.pipeline.post"},
    service = Authenticator.class
    )
    public class EmailAddressAuthenticator implements Authenticator {
    
    @Override
    public int authenticateByEmailAddress(long companyId, String emailAddress,
    String password, Map<String, String[]> headerMap,
    Map<String, String[]> parameterMap) throws AuthException {
    
    return validateDomain(emailAddress);
    }
    
    @Override
    public int authenticateByScreenName(long companyId, String screenName,
    String password, Map<String, String[]> headerMap,
    Map<String, String[]> parameterMap) throws AuthException {
    
    String emailAddress =
    _userLocalService.fetchUserByScreenName(companyId, screenName).getEmailAddress();
    
    return validateDomain(emailAddress);
    }
    
    @Override
    public int authenticateByUserId(long companyId, long userId,
    String password, Map<String, String[]> headerMap,
    Map<String, String[]> parameterMap) throws AuthException {
    
    String emailAddress =
    _userLocalService.fetchUserById(userId).getEmailAddress();
    
    return validateDomain(emailAddress);
    }
    
    private int validateDomain(String emailAddress) throws AuthException {
    
    if (_emailValidator == null) {
    
    String msg = "Email address validator is unavailable, cannot authenticate user";
    _log.error(msg);
    
    throw new AuthException(msg);
    }
    
    if (_emailValidator.isValidEmailAddress(emailAddress)) {
    return Authenticator.SUCCESS;
    }
    return Authenticator.FAILURE;
    }
    
    @Reference
    private volatile UserLocalService _userLocalService;
    
    @Reference(
    policy = ReferencePolicy.DYNAMIC,
    cardinality = ReferenceCardinality.OPTIONAL
    )
    private volatile EmailAddressValidator _emailValidator;
    
    private static final Log _log = LogFactoryUtil.getLog(EmailAddressAuthenticator.class);
    }

今回は、スタブではなく、3つの認証メソッドに機能が含まれています。 `authenticateByEmailAddress`メソッドは、ログインポートレットから提供された電子メールアドレスを直接確認します。他の2つのメソッド`authenticateByScreenName`と`authenticateByUserId`は、`UserLocalService`を呼び出して、ユーザーの電子メールアドレスを検索してから確認します。OSGiコンテナーは、`@Reference`アノテーションのためにこのサービスを挿入します。バリデーターも同じ方法で挿入されますが、実装が見つからない場合には失敗しないように構成されていることに注意してください。これにより、このモジュールはバリデーター実装への依存関係に関係なく起動できます。この場合、`AuthException`をスローしてエラーをログに記録することでエラーが処理されるため、安全です。

なぜこのようにするのでしょうか。グレースフルエラーを行うためです。これは`auth.pipeline.post` `Authenticator`であるため、他の`Authenticator`がこの前に認証情報を確認していると推測されます。これが機能しない場合は、致命的な失敗が発生してユーザーがログインできなくなる前に、エラーメッセージを管理者に通知する必要があります。

このモジュールの他のJavaコードは、バリデーターのインターフェイスのみです。

    package com.liferay.docs.emailaddressauthenticator.validator;
    
    import aQute.bnd.annotation.ProviderType;
    
    @ProviderType
    public interface EmailAddressValidator {
    
    public boolean isValidEmailAddress(String emailAddress);
    }

これは、電子メールアドレスを確認するための単一のメソッドを定義します。

次に、検証モジュールに対処します。

![図2：検証プロジェクトは検証インターフェイスを実装し、認証モジュールに依存します。](../../../images/auth-pipeline-validator-project.png)

このモジュールにはクラスが1つだけ含まれています。これは検証インターフェイスを実装します。

    package com.liferay.docs.emailaddressvalidator.impl;
    
    import java.util.Arrays;
    import java.util.HashSet;
    import java.util.Set;
    import org.osgi.service.component.annotations.Component;
    import com.liferay.docs.emailaddressauthenticator.validator.EmailAddressValidator;
    
    @Component(
    immediate = true,
    property = {
    },
    service = EmailAddressValidator.class
    )
    public class EmailAddressValidatorImpl implements EmailAddressValidator {
    
    @Override
    public boolean isValidEmailAddress(String emailAddress) {
    
    if (_validEmailDomains.contains(
    emailAddress.substring(emailAddress.indexOf('@')))) {
    
    return true;
    }
    return false;
    }
    
    private Set<String> _validEmailDomains =
    new HashSet<String>(Arrays.asList(new String[] {"@liferay.com", "@example.com"}));
    }

このコードは、電子メールアドレスが*@liferay.com*または*@example.com*ドメインからのものであることを確認します。 このモジュールで他に興味深い部分は、2つのプロジェクト間のコンパイルのみの依存関係を定義するGradleビルドスクリプトです。 これは、`settings.gradle`と`build.gradle`の2つのファイルに分かれています。

`settings.gradle`ファイルは、バリデーターが依存するプロジェクト（`Authenticator`）の場所を定義します。

    include ':emailAddressAuthenticator'
    project(':emailAddressAuthenticator').projectDir = new File(settingsDir, '../com.liferay.docs.emailAddressAuthenticator')

このプロジェクトにはインターフェイスが含まれているため、コンパイル時に`build.gradle`が実行されているクラスパス上にある必要があります。

    buildscript {
    dependencies {
    classpath group: "com.liferay", name: "com.liferay.gradle.plugins", version: "3.0.23"
    }
    
    repositories {
    mavenLocal()
    
    maven {
    url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
    }
    }
    
    apply plugin: "com.liferay.plugin"
    
    dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
    compileOnly group: "org.osgi", name: "org.osgi.compendium", version: "5.0.0"
    
    compileOnly project(":emailAddressAuthenticator")
    }
    
    repositories {
    mavenLocal()
    
    maven {
    url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
    }

`settings.gradle`で定義された`Authenticator`プロジェクトを参照する依存関係セクションの行に注目してください。

これらのプロジェクトがデプロイされると、定義した`Authenticator`が実行され、バリデーターで指定された2つのドメインのログインが強制されます。

これらのプロジェクトをさらに検討したい場合は、[ZIPファイル](https://portal.liferay.dev/documents/113763090/114000186/auth-pipelines-authenticator.zip)でダウンロードできます。

## 関連トピック

[Auto Login](/docs/7-0/tutorials/-/knowledge_base/t/auto-login)

[Writing a Custom Login Portlet](/docs/7-0/tutorials/-/knowledge_base/t/writing-a-custom-login-portlet)
