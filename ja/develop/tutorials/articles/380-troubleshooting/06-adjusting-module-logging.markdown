---
header-id: adjusting-module-logging
---

# モジュールロギングの調整

[TOC levels=1-4]

@product@は[Log4j](http://logging.apache.org/log4j/1.2/)ロギングサービスを使用します。モジュールクラスとクラス階層のロギングを設定する方法は以下のとおりです。

- [@product@のUI](/docs/7-1/user/-/knowledge_base/u/server-administration)の*ログレベル*
- `[anyModule]/src/main/resources/META-INF/module-log4j.xml`ファイルで複数のモジュールにLog4jを構成する。
- `[Liferay Home]/osgi/log4j/[symbolicNameOfBundle]-log4j-ext.xml`ファイルで特定のモジュールにLog4jを構成する。
- `/META-INF/module-log4j-ext.xml`ファイルでOSGiフラグメントホストモジュールにLog4jを構成する。

Log4j XML構成の例を次に示します。

    <?xml version="1.0"?>
    <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
    <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    <category name="org.foo">
    <priority value="DEBUG" />
    </category>
    </log4j:configuration>

`category`要素を使用して、メッセージを記録する各クラスまたはクラス階層を指定します。 `name`属性をそのクラス名またはルートパッケージに設定します。サンプルカテゴリでは、パッケージ`org.foo`から始まるクラス階層のロギングを設定しています。`DEBUG`ログレベル以上のログメッセージは、`org.foo`のクラスおよび`org.foo`で始まるパッケージ内のクラスに対して出力されます。

各カテゴリの`priority`要素を必要なログ[レベル](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html)（優先度）に設定します。

- ALL
- DEBUG
- ERROR
- FATAL
- INFO
- OFF
- TRACE
- WARN

ログメッセージは、`[Liferay_Home]/logs`のLiferayログファイルに出力されます。

いくつかのLiferayサンプルプロジェクトでモジュールロギングの例を確認できます。たとえば、サンプル[action-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/action-command-portlet)、[document-action](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/extensions/document-action)、および[service-builder/jdbc](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/service-builder/jdbc)は、特にモジュールロギングを活用している例です。

注：ログレベルの設定が表示されない場合（たとえば、ログレベルを`ERROR`に設定しても、`WARN`メッセージが表示される場合など）は、ログ設定ファイル名のプレフィックスがモジュールのシンボル名と一致していることを確認してください。bndがインストールされている場合、コマンド`bnd print [path-to-bundle]`からの出力には、モジュールのシンボル名が含まれています（コマンドラインにbndをインストールする手順は[こちら](https://github.com/bndtools/bnd/wiki/Install-bnd-on-the-command-line)です）。

モジュールのログ設定は以上です。必要な情報を出力する準備がすべて整いました。

## 関連トピック

[Implementing Logging](/docs/7-1/tutorials/-/knowledge_base/t/implementing-logging)
