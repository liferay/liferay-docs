---
header-id: high-level-architecture-of-liferay-screens-for-ios
---

# iOS用Liferayスクリーンの高レベルアーキテクチャ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

iOS用Liferay Screensは、コア、スクリーンレットレイヤー、ビューレイヤー、およびサーバーコネクタで構成されます。 サーバーコネクタは技術的にはコアの一部ですが、個別に説明する価値があります。 これらは、ローカルおよびリモートのデータソースとのやり取り、およびスクリーンレットレイヤーと [Liferay Mobile SDK](/docs/7-1/tutorials/-/knowledge_base/t/mobile-sdk)間の通信を容易にします。

![図1：Liferay Screens for iOSの高レベルのコンポーネント。](../../../../images/screens-ios-architecture-01.png)

各コンポーネントについて説明します。

  - **コア：** 開発者が独自のスクリーンレット、ビュー、およびサーバーコネクタクラスを作成できるようにするマイクロフレームワーク。 コアには、Screensコンポーネントを開発するためのすべての基本クラスが含まれています。

  - **スクリーンレット：任意の `UIView`に挿入する** Swiftクラス。 スクリーンレットは、ランタイムおよびInterface Builderで選択したテーマをレンダリングします。 また、UIイベントに反応して（サーバーコネクタ経由で）サーバー要求を開始し、Interface Builderから構成できる `@IBInspectable` プロパティのセットを定義します。 Liferayスクリーンにバンドルされているスクリーンレットは、 [スクリーンレットライブラリ](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)として知られてい
 。</p></li> 
    
      - **インタラクター：** サーバーまたは他のデータストアと通信するための特定のユースケースの実装。 サーバーコネクタまたはカスタムクラスを使用して、ローカルおよびリモートのデータソースを使用できます。 ユーザーアクションまたはユースケースがローカルストアまたはリモートストアで複数のクエリを実行する必要がある場合、シーケンスは対応するInteractorで実行されます。 スクリーンレットが複数のユーザーアクションまたはユースケースをサポートしている場合、それぞれに対してインタラクターを作成する必要があります。

  - **コネクタ** （またはサーバーコネクタ）：ローカルおよびリモートのデータソースおよびLiferayインスタンスと対話できるクラスのコレクション。 Liferay独自のコネクタセット、Liferayコネクタは、 [Liferay Mobile SDK](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-ios-app)使用します。 [`NSOperation` フレームワーク](https://developer.apple.com/library/mac/documentation/General/Conceptual/ConcurrencyProgrammingGuide/OperationObjects/OperationObjects.html#//apple_ref/doc/uid/TP40008091-CH101-SW1)を使用するため、すべてのサーバーコネクタを同時に実行できます。 コネクタ間の優先順位と依存関係を定義するのは簡単なので、フレームワークで解決できる独自のコネクタ（操作）のグラフを作成できます。 コネクタは常に [ファクトリクラス](https://en.wikipedia.org/wiki/Abstract_factory_pattern) を使用して作成されるため、アプリ開発者が挿入できます。

  - **テーマ：** XIBファイルのセットと、ユーザーにスクリーンレットを提示する `UIView` クラス。</ul>
