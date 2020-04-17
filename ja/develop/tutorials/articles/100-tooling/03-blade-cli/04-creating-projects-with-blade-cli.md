---
header-id: creating-projects-with-blade-cli
---

# Blade CLIを使用したプロジェクトの作成

[TOC levels=1-4]

Blade CLIを使用してプロジェクトを作成すると、選択したテンプレートに基づいて、プロジェクトのフォルダ構造、ビルドスクリプト（例: `build.gradle`など）、Javaクラス、およびその他のリソース（JSPなど）が作成されます。このチュートリアルでは、Blade CLIを使用して、既存のテンプレートとサンプルに基づいたモジュールを作成する方法を説明します。

Blade CLIを使用すると、アプリケーションの作成方法を柔軟に選択することができます。これは、独自のスタンドアロン環境、または[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)内で実行できます。
また、GradleまたはMavenのビルドツールを使用して、プロジェクトを作成することもできます。
Blade CLIを使用してワークスペースにLiferayモジュールを作成することは、スタンドアロン環境でモジュールを作成することに非常に似ています。

ワークスペースでプロジェクトを作成する際は、プロジェクトのタイプに対応する適切なフォルダ（例: モジュールプロジェクトには`/modules`フォルダ、など）に移動する必要があります。また、必要に応じて、そのフォルダにさらにディレクトリをネストすることもできます。たとえば、Gradleワークスペースはデフォルトで、ワークスペースの`gradle.properties`ファイルに以下のプロパティを設定することにより、モジュールを保存するディレクトリを設定します。

    liferay.workspace.modules.dir=modules

モジュールを別のディレクトリに保存する場合は、このプロパティを変更します。

| **注：**プロジェクトでは、外部の依存関係を| ダウンロードできるリポジトリを定義する必要があります。Mavenとは異なり、| デフォルトではGradleはリポジトリを定義しません。便宜上、Blade CLIで作成されたGradleプロジェクトは、| Liferayの公開Nexusリポジトリをデフォルトリポジトリとして定義します。| ただし、これはプロジェクトの作成場所に応じて、異なるファイルで定義されます。
| | Blade CLIを使用してワークスペースの外にGradleプロジェクトを作成した場合、| リポジトリはモジュールの`build.gradle`ファイルで定義されます。同様に、| ワークスペース内にモジュールを作成した場合、リポジトリはワークスペースのルートフォルダにある| `settings.gradle`ファイルで定義されます。これにより、| ワークスペースにあるすべてのモジュールは、同じリポジトリURLを共有します。

まず最初に、テンプレートを使用してモジュールを作成する方法について説明します。

## プロジェクトテンプレート

新しいLiferayプロジェクトを作成するには、Bladeの`create`コマンドを実行します。このコマンドには、利用可能なテンプレートが多数用意されています。ただし、希望通りのプロジェクトが作成できるように指定できる、その他のオプションも多数あります。Bladeの`create`コマンドの使用方法とコマンドが提供する多くのオプションについて確認するには、`blade help
create`をターミナルに入れます。`create`オプションのリストは、以下のとおりです。

`--base`: ベースディレクトリ。デフォルトのベースディレクトリは現在のディレクトリです。

`-b, --build <String>`：プロジェクトのビルドタイプ。利用可能なオプションは `gradle`（デフォルト）と`maven`です。

`-c, --classname <String>`：プロジェクトでクラスが作成される場合、作成されるクラスの名前を指定します。指定しない場合、クラス名はデフォルトでプロジェクト名になります。

`-C, --contributor-type <String>`：モジュールをテーマコントリビューターとして識別します。これは、BNDファイルに`Liferay-Theme-Contributor-Type`および`Web-ContextPath`のバンドルヘッダーを追加するためにも使用されます。

`-d, --dir <File>`：新しいプロジェクトを作成するためのディレクトリです。
`-h, --host-bundle-bsn <String>`：新しいJSPフックフラグメントを作成する場合は、ホストバンドルのシンボル名を指定します。これは、`fragment`プロジェクトテンプレートを使用する場合に必要です。

`-H, --host-bundle-version <String>`：新しいJSPフックフラグメントを作成する必要がある場合は、ホストバンドルのバージョン名を指定します。これは、`fragment`プロジェクトテンプレートを使用する場合に必要です。

`-v, --liferay-version`：プロジェクトを作成する際の対象とするバージョンです。デフォルトは`7.2`です。

`-l, --list-templates`：使用可能なプロジェクトテンプレートのリストを印刷します。

`-p, --package-name <String>`：プロジェクトの作成時に使用するパッケージ名です。

`-s, --service <String>`：新しい宣言サービス（DS）のコンポーネントを作成する必要がある場合は、実装するサービスの名前を指定します。このコンテキストでは、*サービス*という用語は、Liferay APIではなくOSGiサービスを指すことに注意してください。

`-t, --template <String>`：プロジェクトの作成時に使用する、プロジェクトテンプレートです。利用可能なBlade CLIテンプレートのリストに対し、`blade create -l` を実行します。
`--trace`：例外のスタックトレースが発生したときに出力します。デフォルトでは、`false`です。

モジュールプロジェクトを作成するには、以下のシンタックスを使用します。

    blade create [OPTIONS] <NAME>

たとえば、GradleでMVCポートレットプロジェクトを作成する場合は、以下を実行できます。

    blade create -t mvc-portlet -p com.liferay.docs.guestbook -c GuestbookPortlet my-guestbook-project

このコマンドは、`mvc-portlet`テンプレートに基づいて、MVCポートレットプロジェクトを作成します。
`com.liferay.docs.guestbook`というパッケージ名を使用して、`GuestbookPortlet`というポートレットクラスを作成します。プロジェクト名は`my-guestbook-project`です。ディレクトリが指定されていないため、コマンドを実行したフォルダに作成されます。Blade CLIを使用してプロジェクトを作成する場合、ダウンロードは行われません。つまり、インターネットにアクセスする必要はありません。

以前のバージョン（例: Liferay Portal 7.0）のプロジェクトを作成する場合は、`-v`フラグを使用して指定できます。たとえば、Liferay Portal 7.0用のプロジェクトを作成するには、`-v 7.0`を`create`コマンドシーケンスに含めます。

Blade CLIは、`-b maven`パラメータを指定することにより、Mavenで同じプロジェクトを作成することもできます。LiferayのMavenプロジェクトテンプレートを活用する方法は、Blade CLIのMavenオプションを使用することだけではありません。Mavenアーキタイプを使用して作成することもできます。その方法については、Liferayの[Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)についての記事を参照してください。

Blade CLIを使用する場合、プロジェクトのコンポーネントクラスを手動で編集する必要があります。
Blade CLIでは、クラスの名前を指定できますが、クラスの他のすべてのコンテンツは、クラスが作成された後にのみ編集できます。 コンポーネントクラスの詳細と重要な依存関係情報については、[Creating Modules with Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide)のチュートリアルを参照してください。

以上が`blade create`を使用したLiferayプロジェクトを作成するための基礎知識です。[Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)のリファレンスセクションにアクセスして、特定の`create`テンプレートとその機能も確認してみてください。

次に、Liferayが提供するプロジェクトサンプルと、Blade CLIを使用してプロジェクトサンプルを作成する方法について説明します。

## プロジェクトサンプル

Liferayは、特定のタスクを達成するためにプロジェクトを構築するにあたって、ベストプラクティスを知りたい方々に役立つサンプルプロジェクトを多数提供しています。これらのサンプルは、 [liferay-blade-samples](https://github.com/liferay/liferay-blade-samples) Githubリポジトリにあります。[Liferay Sample Projects](/docs/7-1/tutorials/-/knowledge_base/t/liferay-sample-projects)の記事にアクセスして、これらのサンプルの詳細を確認することもできます。

これらのサンプルは、リポジトリを複製して使用している環境に手動でコピー/ペーストする代わりに、Blade CLIを使用して簡単に作成することができます。これを行うには、以下のシンタックスを使用します。

    blade samples <NAME>

たとえば、[portlet-ds](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/ds-portlet)サンプルを作成する場合は、以下を実行します。

    blade samples ds-portlet

| ** 注：**レガシーバージョンのBladeサンプルの作成に興味がある場合、| `-v`に@product@バージョンを続けて、ターゲットに渡します。たとえば、| |    blade samples -v 7.0 ds-portlet

利用可能なBladeサンプルの完全なリストを得るには、以下を実行してください。

    blade samples

 これで、Blade CLIを使用したLiferayプロジェクトの作成に関する基本情報は以上です。
