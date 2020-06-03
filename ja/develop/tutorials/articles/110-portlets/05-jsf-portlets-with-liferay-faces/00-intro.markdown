---
header-id: jsf-portlets-with-liferay-faces
---

# Liferay Facesを使用したJSFポートレット

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

![ ](../../../images/liferay-faces-logo.png)

Java EE標準を使用してMVCベースのポートレットを開発しますか？ 洗練されたリッチなUIを簡単に開発できるUIコンポーネントモデルでポートレット開発フレームワークを使用しますか？ それとも、@product@で使用したいJSFを使用してWebアプリを書いていますか？ これらの質問のいずれかに *はい* と答えた場合、あなたは幸運です！ Liferay Facesは、これらすべての機能などを提供します。

Liferay Faces is an umbrella project that provides support for the JavaServer™ Faces (JSF) standard in @<product@>. 次のプロジェクトが含まれます。

  - [Liferay Faces Bridge](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-faces-bridge) 使用すると、ポートレット固有のJavaコードを記述することなく、JSF Webアプリをポートレットとしてデプロイできます。 また、ポートレットアプリケーション内でJSF 2.xのパワーを活用できるようにする革新的な機能も含まれています。 Liferay Faces Bridgeは、JSR 329/378 Portlet Bridge Standardを実装しています。
  - [Liferay Faces Alloy](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-faces-alloy) では、JSF開発と一貫した方法でAlloyUIコンポーネントを使用できます。
  - [Liferay Faces Portal](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-faces-portal) では、JSFポートレットでLiferay固有のユーティリティとUIコンポーネントを活用できます。

JSFコンポーネントスイートの包括的なデモについては、 [Liferay Faces Showcase](http://www.liferayfaces.org)ご覧ください。

JSFを初めて使用する場合は、その長所、短所、およびCSS / JavaScriptを使用したポートレットの開発にどのように役立つかを知りたい場合があります。

JSFとLiferay Facesを使用する理由は次のとおりです。

  - JSFは、Model / View / Controller（MVC）設計パターンを使用するWebアプリケーションを開発するためのJava EE標準です。 標準として、仕様はJava Community Process（JCP）によって積極的に維持されており、Oracleリファレンス実装（Mojarra）は頻繁にリリースされています。 ソフトウェアアーキテクトは、Java EEアプリケーションサーバーベンダーによってサポートされ、サービスレベルアグリーメント（SLA）に従って保証されたサービス寿命を持っているため、JSFなどの標準を選択することがよくあります。
  - JSFは2003年に初めて導入され、（おそらく）維持が容易なWebアプリケーションを開発するための成熟したテクノロジーです。
  - JSFポートレットブリッジ（Liferay Faces Bridgeなど）もJCPによって標準化されており、ポートレット固有のJavaコードを記述せずにJSF Webアプリケーションをポートレットとしてデプロイできます。
  - Liferay DXPサポートには、JSF（Liferay Faces経由）のサポートが含まれています。
  - JSFは、洗練されたリッチなユーザーインターフェイスの開発を容易にするUIコンポーネントモデルを提供するという点で、ユニークなフレームワークです。
  - JSFにはAjax機能が組み込まれており、DOM内の要素を置き換えることでブラウザーに自動更新を提供します。
  - JSFは、さまざまな統合を可能にする多くの拡張ポイントを使用して設計されています。
  - Liferay Faces Alloy、Primefaces、ICEfaces、RichFacesなど、JSFコンポーネントスイートがいくつかあります。 これらの各コンポーネントスイートは、さまざまなUIコンポーネントとAjaxプッシュなどの補完的なテクノロジーでJSFを強化します。
  - JSFは、Webユーザーインターフェイスを構築する必要があるサーバー側の開発者に適しています。 これにより、サーバー側の開発者は、HTML / CSS / JavaScriptの専門家ではなく、コアコンピテンシーに集中できます。
  - JSFは、マークアップとしてカプセル化された再利用可能なUIコンポーネントの作成を可能にするFaceletsテンプレートエンジンを提供します。
  - JSFはHTML5マークアップとの良好な統合を提供します
  - JSFはFaces Flows機能を提供します。これにより、開発者は、ビューからビューへと流れるウィザードのようなアプリケーションを簡単に作成できます。
  - JSFは、コンテナによって管理範囲内に配置されているBeanを作成する開発者のために簡単にそれを作るようにCDIや春などの依存性注入フレームワークとの良好な統合を持っている： `@RequestScoped`、 `@ViewScoped`、 `@SessionScoped`、 `@ FlowScoped`
  - JSFはステートフルテクノロジーであるため、フレームワークはアプリケーションの状態管理の複雑さをカプセル化するため、開発者は状態管理コードを記述する必要がありません。 JSFをステートレスな方法で使用することも可能ですが、アプリケーションの状態管理の機能の一部は事実上無効になります。

JSFを使用しない理由はいくつかあります。 たとえば、HTML / CSS / JavaScriptを多用するフロントエンド開発者の場合、JSF UIコンポーネントはHTMLをレンダリングし、HTMLドキュメント全体の制御をあまり行わないことがあります。 JavaScriptにこだわり、AlloyUIまたは他のJavaScriptフレームワークを活用する方がよい場合があります。 または、標準は重要な考慮事項ではないか、現在のフレームワークを使用してポートレットを開発することを単に好むかもしれません。

次のポートレットアプリケーションをJSFとLiferay Facesで開発するか、HTML / CSS / JavaScriptで開発するかは完全にあなた次第です。 しかし、おそらくLiferay Facesについてもっと学び、自分で試してみたいと思うでしょう。
