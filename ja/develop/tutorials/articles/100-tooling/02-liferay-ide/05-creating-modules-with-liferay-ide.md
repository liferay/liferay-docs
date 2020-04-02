---
header-id: creating-modules-with-liferay-ide
---

# Liferay Dev Studioでモジュールを作成する

[TOC levels=1-4]

Dev Studioは、ユーザーがさまざまなモジュールプロジェクトを作成することができるModule Project Wizardを提供します。*[ファイル]* → *[新規]* → *[Liferay Module Project]*で、新しいLiferay Module Projectを作成できます。

![図1：*[新規]* → *[Liferay Module Project]*を選択すると、Module Project Wizardが表示されます。](../../../images/new-module-project.png)

オプションには、プロジェクト名、場所、ビルドタイプ、テンプレートタイプがあり、
GradleまたはMavenを使用してプロジェクトを構築できます。どのテンプレートタイプを選択すべきかわからない場合は、[Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)のリファレンスセクションをご覧ください。*[次へ]*をクリックすると、選択したプロジェクトテンプレートに基づいて、追加の設定オプションが表示されます。たとえば、コンポーネントクラスを必要とするテンプレートを選択した場合、ウィザードで設定する必要があります。

![図2：Portlet Component Class Wizardでコンポーネントクラスの詳細を指定します。](../../../images/component-class-wizard.png)

コンポーネントクラスの名前、パッケージ名、およびそのプロパティが指定できます。
割り当てるプロパティは、`@Component`アノテーションの`property = {...}`アサイメントで見つかったものです。Liferay Dev Studioでのコンポーネントクラスの作成の詳細については[Creating Component Classes](#creating-component-classes)のセクションを参照してください。

モジュールを設定したら、*[完了]*をクリックしてプロジェクトを作成します。

これでモジュールプロジェクトが作成されたので、Dev StudioのProject Explorerでプロジェクトのプレゼンテーションが設定できます。プロジェクトのプレゼンテーションを変更するには、デフォルトの*階層*ビューまたは*フラット*ビューを選択します。これを行うには、Project Explorerの*表示メニュー*（![表示メニュー](../../../images/icon-ide-view-menu.png)）に移動し、*[プロジェクトプレゼンテーション]*を選択してから、表示するプレゼンテーションモードを選択します。階層ビューではプロジェクトの下にサブフォルダとサブプロジェクトが表示されますが、フラットビューではプロジェクトとは別にモジュールが表示されます。

![図3：デフォルトでは、階層プロジェクトプレゼンテーションモードが設定されています。](../../../images/workspace-presentation.png)

Liferay Dev StudioからLiferay Module Projectを作成する方法は以上です。

## コンポーネントクラスの作成

既存のモジュールプロジェクトの新しいコンポーネントクラスを作成することもできます。
*[ファイル]* → *[新規]* → *[Liferay Component Class]*に移動します。これは、コンポーネントクラステンプレートを選択できることを除いて、以前のコンポーネントクラスウィザードと同様のウィザードです。`Component Class Template`リストには多くのテンプレートがあります。

- *Auth Failures*： 失敗したログイン検証を処理します。
- *Auth Max Failure*：ログイン失敗の最大数を処理します。
- *Authenticator*：処理を認証します。
- *Friendly URL Mapper*：フレンドリーURLを処理します。
- *GOGOコマンド*：カスタムのGogoコマンドを作成します。
- *Indexer Post Processor*：新しいインデクサーポストプロセッサを作成します。
- *Login Pre Action*：ログインの事前アクションを作成します。
- *MVC Portlet*：新しいMVCポートレットを作成します。
- *Model Listener*：モデルリスナーを設定します。
- *Poller Processor*：新しいポーラープロセッサを作成します。
- *Portlet*：新しいポートレットクラスファイルを作成します。
- *Portlet Action Command*：新しいポートレットアクションコマンドを作成します。
- *Portlet Filter*：新しいポートレットフィルターを作成します。
- *Rest*：Restの方法で内部サービスを呼び出してラップします。
- *Service Wrapper*：新しいサービスラッパーを作成します。
- *Struts in Action*：新しいStrutsのアクションを作成します。
- *Struts Portlet Action*：新しいStrutsポートレットアクションを作成します。

次は、既存のプロジェクトをDev Studioにインポートする方法について説明します。

## 既存のモジュールプロジェクトのインポート

Dev Studioは、既存のモジュールプロジェクトをインポートする方法も提供します。*[ファイル]* → *[インポート]* → *[Liferay]* → *[Liferay Module Project(s)]*に移動すると、モジュールプロジェクトをインポートすることができます。次に、プロジェクトの場所を指定して、*[完了]*をクリックします。

![図4：*[Liferay Module Project(s)]* を選択して、モジュールプロジェクトをインポートします。](../../../images/import-wizard.png)

これで、モジュールプロジェクトをLiferay Dev Studioにインポートする準備が整いました。
