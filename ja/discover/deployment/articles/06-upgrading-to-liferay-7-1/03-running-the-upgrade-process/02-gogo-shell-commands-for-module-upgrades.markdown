---
header-id: gogo-shell-commands-for-module-upgrades
---

# モジュールアップグレード用のGogoシェルコマンド

LiferayのGogoシェルコマンドを使用すると、個々のモジュールをアップグレードして検証できます。
これにより、コアモジュールとコア以外のコアモジュールのアップグレードを詳細まで調整することができます。以下が、コマンドでできることです。

- [コマンドの使用法を入手する](#command-usage)
- [アップグレードするモジュールを一覧表示する](#listing-module-upgrade-processes)
- [モジュールをアップグレードする](#executing-module-upgrades)
- [アップグレードステータスを確認する](#checking-the-upgrade-status)
- [アップグレードを確認する](#executing-verify-processes)

+$$$

**注**：Gogoシェルコマンドを使用してコアをアップグレードする前に、[コアアップグレードを設定する](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade#configuring-the-core-upgrade)必要があります。

$$$

まず最初に、コマンドの使用法について説明します。

## コマンドの使用法

アップグレードツールを実行してGogoシェルを開いた場合は、すでに接続されています。
それ以外の場合は、[Gogoシェルポートレット](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)を使用してコマンドを実行でき ます。

`upgrade`名前空間で利用可能なコマンドは、次のとおりです。

**exit**または**quit:**： Gogoシェルを終了します。

**upgrade:help:**アップグレードコマンドを表示します。

**upgrade:check:**過去にアップデートに失敗したため、あるいはモジュールが最終バージョンに達していないために、実行が保留されているアップグレードを一覧表示します。

**upgrade:execute {module_name}：**対象モジュールのアップグレードを実行します。

**upgrade:executeAll：**保留中のモジュールアップグレードプロセスをすべて実行します。

**upgrade:list：**登録されているすべてのアップグレードを一覧表示します。

**upgrade:list {module_name}：**モジュールに必要なアップグレード手順を一覧表示します。

**upgrade:list | grep Registered：**登録されているアップグレードとそのバージョンを一覧表示します。

**verify:help：**検証コマンドを表示します。

**verify:check {module_name}：**モジュールの検証プロセスの最新の実行結果を一覧表示します。

**verify:checkAll：**すべての検証プロセスの最新の実行結果を一覧表示します。

**verify:execute {module_name}：**モジュールのベリファイアを実行します。

**verify:executeAll：**全てのベリファイアを実行します。

**verify:list：**登録されているすべてのベリファイアを一覧表示します。

[LiferayコマンドおよびGogoシェルで利用可能な標準コマンド](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)には、便利なものがたくさんあります。
以下のセクションでは、Liferayのアップグレードコマンドについて説明します。

## モジュールアップグレードプロセスの一覧表示

モジュールをアップグレードする前に、未解決の依存関係があるかどうか、解決されてアップロードができる状態かどうか、そしてモジュールのアップグレードプロセスを調べてください。

Gogoシェルにある`upgrade:list`を実行すると、アップグレードの依存関係が満たされているモジュールが一覧表示されます。表示されたモジュールはアップグレードすることができます。

モジュールがアクティブであってもリストされていない場合は、その依存関係をアップグレードする必要があります。Gogoシェルコマンドの`scr:info [upgrade_step_class_qualified_name]`は、アップグレードステップクラスの満たされていない依存関係を表示します。以下は、`scr:info`コマンドの例です。

    scr:info com.liferay.journal.upgrade.JournalServiceUpgrade

`upgrade:list [module_name]`を起動すると、モジュールのアップグレードプロセスが順不同で一覧表示されます。たとえば、`upgrade:list com.liferay.bookmarks.service`を（Bookmarks Serviceモジュールに対して）実行すると、次のようになります。

    Registered upgrade processes for com.liferay.bookmarks.service 1.0.0
    {fromSchemaVersionString=0.0.0, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.portal.spring.extender.internal.context.ModuleApplicationContextExtender$ModuleApplicationContextExtension$1@6e9691da}
    {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0-step-3, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId@5f41b7ee}
    {fromSchemaVersionString=1.0.0-step-1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletSettings@53929b1d}
    {fromSchemaVersionString=1.0.0-step-2, toSchemaVersionString=1.0.0-step-1, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeLastPublishDate@3e05b7c8}
    {fromSchemaVersionString=1.0.0-step-3, toSchemaVersionString=1.0.0-step-2, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeClassNames@6964cb47}

アプリケーションのアップグレード手順クラス名は通常、その意図が明確です。たとえば、例にあげた`com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId`アップグレード手順クラスは、アプリのポートレットIDを更新します。他の例のアップグレード手順クラスはクラス名、`LastPublishDate`、および`PortletSettings`を更新します。例の`0.0.0`から`1.0.0`へのステップは、空のデータベースからモジュールをアップグレードします。

モジュールのアップグレードプロセスをよりよく調べるにあたって、リストされたアップグレード手順は頭の中で、またはテキストエディタを使って、並べ替えます。以下は、Bookmarks ServiceモジュールをLiferay Portal 6.2（モジュールのデータベースが存在する）からスキーマバージョン`1.0.0`にアップグレードするためのアップグレード手順の例です。

- `0.0.1`から`1.0.0-step-3`
- `0.0.1-step-3`から`1.0.0-step-2`
- `0.0.1-step-2`から`1.0.0-step-1`
- `0.0.1-step-1`から`1.0.0`

全体的なモジュールアップグレードプロセスはバージョン`0.0.1`から始まり、バージョン`1.0.0`で終わります。最初のステップは最初のバージョン（`0.0.1`）から始まり、ターゲットバージョンの最も高いステップ（`step-3`）で終わります。最後のステップは、ターゲットバージョンの最も低いステップ（`step-1`）から始まり、ターゲットバージョン（`1.0.0`）で終了します。

モジュールのアップグレードプロセスが理解できたら、安心して実行することができます。

## モジュールアップグレードの実行

`upgrade:execute [module_name]`を実行すると、モジュールがアップグレードされます。解決しなければならないアップグレードエラーに遭遇することがあるかもしれません。その場合、再度コマンドを実行すると、最後に成功したステップからアップグレードが開始されます。

`upgrade:list [module_name]`を実行することで、アップグレード状況を確認できます。たとえば、`upgrade:list com.liferay.iframe.web`を入力すると次のように出力されます。

    Registered upgrade processes for com.liferay.iframe.web 0.0.1
    {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

最初の行はモジュールの名前と現在のバージョンをリストしています。例のモジュールの現在のバージョンは`0.0.1`です。`toSchemaVersionString`値は、ターゲットバージョンです。

モジュールのアップグレードが成功した後で、モジュール上の`upgrade:list [module_name]`を実行すると、モジュールの名前とそれに続くターゲットバージョンが表示されます。

たとえば、`com.liferay.iframe.web`のバージョン`1.0.0`へのアップグレードが成功した場合、`upgrade:list com.liferay.iframe.web`を実行すると、モジュールのバージョンは`1.0.0`であることが表示されます。

    Registered upgrade processes for com.liferay.iframe.web 1.0.0
    {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

完了していないモジュールのアップグレードの場合は、それらのステータスを確認して問題を解決できます。

## アップグレードステータスの確認

まだアップグレードが必要なものと、その理由を知っておくと便利です。アップグレードが必要な場合、モジュールのアップグレードを忘れてしまったか、アップグレードが失敗した可能性があります。いずれにせよ、アップグレードステータスがどうなっているのかを知ることは重要です。

`upgrade:check`コマンドは、差し迫ったアップグレードがあるモジュールをリストします。

たとえば、`com.liferay.dynamic.data.mapping.service`モジュールが`1.0.0-step-2`というラベルの付いたステップで失敗したとします。`upgrade:check`を実行すると、以下の内容が表示されます。

    Would upgrade com.liferay.dynamic.data.mapping.service from 1.0.0-step-2 to
    1.0.0 and its dependent modules

モジュールはアップグレードを完了するために、他のモジュールにしばしば依存します。`scr:info [upgrade_step_class_qualified_name]`を実行すると、アップグレードステップクラスの依存関係が表示されます。依存関係にあるモジュールを正常にアップグレードするには、依存モジュールをアップグレードする必要があります。

モジュールを解決してアクティブ化するには、アップグレードが完了している必要があります。 [Apache Felix依存性マネージャ](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager/tutorials/leveraging-the-shell.html)のGogoシェルコマンドの`dm wtf`は、未解決の依存関係を明らかにします。モジュールが特定のデータスキーマバージョン（例えば、`bnd.bnd`は`Liferay-Require-SchemaVersion: 1.0.2`を指定します）を必要としているのに、モジュールがそのバージョンへのアップグレードを完了していない場合に、`dm wtf`はスキーマバージョンが登録されていないことを表示します。

    1 missing dependencies found.
    -------------------------------------
    The following service(s) are missing:
    * com.liferay.portal.kernel.model.Release (&(release.bundle.symbolic.name=com.liferay.journal.service)(release.schema.version=1.0.2)) is not found in the service registry

この`dm wtf`コマンドは、ポートレットの定義、およびカスタムポートレットの`schemaVersion`フィールドにあるエラーを検出するのにも役立ちます。

@product@データベースの`Release_`テーブルを参照すると、モジュールのアップグレードステータスも判断できます。コアの`servletContextName`フィールド値は`portal`です。コアの`schemaVersion`フィールドが新しい@product@バージョン（例えば、Liferay Portal CE GA2の場合は`7.1.1`）と一致し、その`verified`フィールドが`1`（true）の場合、コアのアップグレードは正常に完了しています。

各モジュールには1つの`Release_`テーブルレコードがあり、その`schemaVersion`フィールドの値は`1.0.0`以上でなければなりません（Liferay Portalのバージョン6.2以前を対象とした従来のプラグインを除いて、`1.0.0`が@product-ver@モジュールの初期バージョンです。）。

## 検証プロセスの実行

検証プロセスにより、アップグレードが正常に実行されたことを確認します。コアのプロセスが@product@のアップグレード後に自動的に実行されることを確認します。[`verify.*`ポータルのプロパティ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Verify)を設定してサーバを再起動することでも実行できます。

また、一部のモジュールには検証プロセスがあります。利用可能な検証プロセスを確認するには、Gogoシェルコマンドの`verify:list`を入力します。検証プロセスを実行するには、`verify:execute [verify_qualified_name]`と入力します。
