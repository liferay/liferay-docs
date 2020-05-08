---
header-id: using-files-to-configure-product-modules
---

# ファイルを使用したモジュールコンポーネントの構成

[TOC levels=1-4]

@product@は、[Felix File Install](http://felix.apache.org/documentation/subprojects/apache-felix-file-install.html)を使用して、新規の構成ファイルまたは更新された構成ファイルのファイルシステムフォルダを監視し、[構成管理](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html)の[Felix OSGiを実装](http://felix.apache.org/)することで、ファイルを使用したモジュールサービスコンポーネントの構成を可能にします。

構成ファイルの操作方法を学習するには、最初に[Understanding System Configuration Files](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)を確認してください。

## 構成ファイルの形式

2つの異なる構成ファイル形式があります。

- `.cfg`：プロパティとして`String`値のみをサポートする、古いシンプルな形式。
- `.config`：プロパティで文字列、型情報、およびその他の非文字列値をサポートする形式。

@product@は両方の形式をサポートしていますが、柔軟性と型情報を使用する機能がある`.config`ファイルを使用してください。`.cfg`ファイルには型情報がないため、`String`以外を保存する場合は、プロパティユーティリティクラスを使用して`String`を目的の型にキャストする必要があります（かつ、`String`ではないプロパティを慎重に文書化する必要があります）。`.config`ファイルでは、型情報を許可することにより、この必要がなくなります。以下の記事では、ファイル形式について説明しています。

- [Understanding System Configuration Files](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)
- [Configuration file (`.config`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#configuration-files-config)
- [Properties file(`.cfg`) syntax](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#property-files-cfg)

## 構成ファイルの命名

[構成ファイルを作成する](/docs/7-1/user/-/knowledge_base/u/creating-configuration-files)前に、次の手順に従って、コンポーネントに複数のインスタンスを作成できるかどうか、またはコンポーネントがシングルトン用であるかどうかを判断します。

1. コンポーネントのモジュールをまだデプロイしていない場合はデプロイします。

2. @product@のUIで、*[コントロールパネル]* &rarr; *[設定]* &rarr;
*[System Settings]*に移動します。

3. コンポーネントを検索または参照して、コンポーネントの設定を見つけます。

4. コンポーネントの設定ページに*[Configuration Entries]*というセクションがある場合は、構成したコンポーネントに複数のインスタンスを自由に作成できます。それ以外の場合は、コンポーネントをシングルトンとして扱う必要があります。

![図1：[System Settings]ページに*[Configuration Entries]* セクションがあるコンポーネントには複数のインスタンスを作成できます。](../../images/system-settings-page-lists-configuration-entries.png)

*すべての*構成ファイル名は、コンポーネントのPID（PIDは*永続ID*の略）で始まり、`.config`または`.cfg`で終わる必要があります。

たとえば、次のクラスは[Declarative Services](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)を使用してコンポーネントを定義します。

    package com;
    @Component
    class Foo {}

コンポーネントのPIDは`com.Foo`です。すべてのコンポーネントの構成ファイルは、PID `com.Foo`で始まる必要があります。

構成を使用して作成または更新するシングルトン以外のコンポーネントインスタンスごとに、コンポーネントのPIDで始まり`.config`または`.cfg`で終わる一意の名前の構成ファイルを使用する必要があります。複数のコンポーネントインスタンスの構成を作成するには、構成ファイルで異なる*サブネーム*を使用する必要があります。サブネームは構成ファイル名の一部で、PIDの後、サフィックス`.config`または`.cfg`の前に来ます。シングルトン以外のコンポーネントの構成ファイル名のパターンは次のとおりです。

- `[PID]-[subname1].config`
- `[PID]-[subname2].config`
- 等々。

たとえば、次の名前の構成ファイルを使用して、コンポーネント`com.Foo`の2つの異なるインスタンスを構成できます。

- `com.Foo-one.config`
- `com.Foo-two.config`

各構成ファイルは、PIDに一致するコンポーネントのインスタンスを作成または更新します。サブネームは任意です。特定のコンポーネントインスタンスと一致する必要はありません。任意のサブネームを使用できます。たとえば、次の構成ファイルは上記の2つと同様に有効です。

- `com.Foo-puppies.config`
- `com.Foo-kitties.config`

ただし、@product@の規則として、コンポーネントの最初のインスタンスを構成する場合は、サブネーム`default`を使用します。したがって、ファイル名のパターンは次のとおりです。

    [PID]-default.config

シングルトンコンポーネントの構成ファイルも`[PID]`で始まり、`.config`または`.cfg`で終わる必要があります。シングルトンコンポーネントの構成ファイル名に使用される一般的なパターンは次のとおりです。

    [PID].config

構成ファイルの作成が完了したら、[デプロイ](/docs/7-1/user/-/knowledge_base/u/creating-configuration-files#deploying-a-configuration-file)できます。

## 構成ファイルのデプロイエラーの解決

次の`IOException`は、構成ファイルに構文の問題があることを示唆しています。

    Failed to install artifact: [path to .config or .cfg file]
    java.io.IOException: Unexpected token 78; expected: 61 (line=0, pos=107)

解決するには、[構成ファイルの構文](#configuration-file-formats)を修正します。

 これで、構成ファイルを使用してモジュールコンポーネントを構成する方法がわかりました。

## 関連記事

[Understanding System Configuration Files](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)
