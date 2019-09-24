---
header-id: locales-and-encoding-configuration-ja
---

# ロケールとエンコーディング設定

[TOC levels=1-4]

言語、タイムゾーン、「右から左へ」（つまり、ヘブライ語、アラビア語、ペルシャ語などの言語）に基づいてコンテンツを表示できます。また、ユーザー名とタイトルをローカライズできます。メッセージが特定の言語で表示されるように、管理者は特定のコアUIメッセージをローカライズできます。

## タイムゾーン

タイムゾーンはコントロールパネルで設定できます。また、理論的にはJVMでも設定できます（ただし、その場合はGMTに設定する必要があります。下記参照）。

タイムゾーンの設定とデフォルトの言語のカスタマイゼーションは、コントロールパネルのインスタンスレベルで行われます。

1. *[コントロールパネル]* &rarr; *[設定方法]* に移動します。

2. *[インスタンス設定]* をクリックします。

3. *[その他]*タブをクリックします。

![図1：インスタンス設定で、デフォルトの言語と利用可能な言語、およびタイムゾーンを変更できます。](../../../images/instance-locales.png)

中央の左右の矢印を使用して、利用可能な言語とロケールを追加または削除できます。 [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)フォルダの`portal-ext.properties`ファイルで、これらをプロパティとして設定することもできます。`portal.properties`参照ドキュメントの[会社](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Company)のセクションはデフォルトのロケールを定義します。[言語とタイムゾーン](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)のセクションでは、利用可能なロケールと現在のロケールを定義します。

    company.default.locale=en_GB

例として、上記のプロパティはロケールを英語（イギリス）に変更します。

## JVMタイムゾーンをGMTへ設定

JVMでタイムゾーンを設定すると、カレンダーイベントやWebコンテンツ記事に誤った日付が表示されるといった事象が発生します。これは、データベースに保存されている各日付がGMT時刻に保存されているとシステムが想定しているためです。システムが保存された日付をエンドユーザーに表示する必要がある場合、表示日はアプリケーションサーバーの現在の日付によって計算されます。この日付は、設定されたJVMレベルのタイムゾーンと保存されたGMTフォーマットの日付の影響を受けます。表示日が正しく計算されるようにするには、タイムゾーンをJVMレベルでGMTに設定する必要があります。
そうでなければ、タイムゾーンのオフセットがJVMレベルで誤っているために、表示日が誤って計算されて表示されることになります。


## わかりやすいURLとロケール

インスタンス設定を設定することに加えて、ポータルの`web.xml`ファイルを編集することによって、`I18nServlet`を使用する特定の言語に固有のURLを定義することもできます。

    <servlet-mapping>
    <servlet-name>I18n Servlet</servlet-name>
    <url-pattern>/ar/*</url-pattern>
    </servlet-mapping>
    .
    .
    .
    <servlet-mapping>
    <servlet-name>I18n Servlet</servlet-name>
    <url-pattern>/de/*</url-pattern>
    </servlet-mapping>

デフォルトはほとんどすべての環境で十分なはずです。`web.xml`を変更すると、@product@を停止する必要があり、さらに再デプロイが必要になることもあるため（アプリケーションサーバーによって異なります）、デフォルトをテストして、これらの設定を本当に変更する必要があることを確認してください。クラスタ化している場合は、すべてのノードでこれらの変更を行う必要があります。


## 言語キーの変更

開発者は、デフォルトで出荷されている[言語キーの変更](/develop/tutorials/-/knowledge_base/7-1/overriding-language-keys)によって、特定のコアUIメッセージ（たとえば、*要求は正常に完了しました*）を追加、または変更することができます。

### 右から左へ

右から左に表示される言語の場合は、以下の言語プロパティ設定を使用します。


    lang.dir=rtl
    lang.line.begin=right
    lang.line.end=left

デフォルトで右から左に表示するには、[これらのプロパティを全体的に上書き](/develop/tutorials/-/knowledge_base/7-1/overriding-global-language-keys)します。

### ユーザー名のローカライズ

ユーザーはロケールの接頭辞と接尾辞の値を変更できます。例えば、スペイン語の場合、`language_es.properties`ファイルには次の値が含まれています。

    lang.user.name.field.names=prefix,first-name,last-name
    lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
    lang.user.name.required.field.names=last-name

詳細については、[Liferay言語設定の使用](/develop/tutorials/-/knowledge_base/7-1/using-liferays-language-settings)を参照してください。

## 関連トピック

[Liferay言語設定の使用](/develop/tutorials/-/knowledge_base/7-1/using-liferays-language-settings)

[グローバル言語キーの上書き](/develop/tutorials/-/knowledge_base/7-1/overriding-global-language-keys)

[モジュールの言語キーの上書き](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-language-keys)
