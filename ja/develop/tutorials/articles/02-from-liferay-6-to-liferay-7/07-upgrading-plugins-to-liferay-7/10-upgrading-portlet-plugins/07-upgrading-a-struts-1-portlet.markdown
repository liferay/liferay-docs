---
header-id: upgrading-a-struts-1-portlet
---

# Struts 1ポートレットのアップグレード

[TOC levels=1-4]

Strutsは、モデルビューコントローラー（MVC）デザインパターンを実装する、安定した、広く採用されているフレームワークです。 Liferay Portalの以前のバージョン用のStrutsポートレットがある場合は、@product-ver@にアップグレードできます。

Strutsポートレットを@product-ver@にアップグレードするのは、想像以上に簡単です。 @product@を使用すると、StrutsポートレットをJava EE Webアプリケーションとして引き続き使用できます。

このチュートリアルでは、Struts 1フレームワークを使用するポートレットをアップグレードする方法を説明します。

ファイル/フォルダの説明を含むサンプルStrutsポートレットのフォルダ構造を以下に示します。

  - `sample-struts-portlet`
      - `docroot/`
          - `html/portlet/sample_struts_portlet/` → JSP
          - `WEB-INF/`
              - `lib/` → @product@システムでは利用できない必須のサードパーティライブラリ
              - `src/`
                  - `com/liferay/samplestruts/model/` → モデルクラス
                  - `com/liferay/samplestruts/servlet/` → テストサーブレットとサーブレットコンテキストリスナー
                  - `com/liferay/samplestruts/struts/`
                      - `action/` → ビューページをクライアントに戻す`Action`クラス
                      - `form/` → モデル相互作用向けの`ActionForm`クラス
                      - `render/` → 追加のページを提示し、入力を処理する`Action`クラス
                      - `SampleException.java` → 例外クラス
                  - `content/test/` →リソースバンドル
                  - `META-INF/` → Javadoc
              - `tld/` → タグライブラリ定義
              - `liferay-display.xml` → アプリケーションカテゴリを設定
              - `liferay-plugin-package.properties` → メタデータとポータルの依存関係を設定
              - `liferay-portlet.xml` → 記述的なロール名をロールにマップ
              - `liferay-releng.properties` →（内部）リリースプロパティ
              - `portlet.xml` → ポートレットとその初期化パラメーターおよびセキュリティロールを定義
              - `struts-config.xml` → Struts設定
              - `tiles-defs.xml` → Strutsタイル定義
              - `validation.xml` → 検証用のフォーム入力を定義
              - `validation-rules.xml` → Struts検証ルール
              - `web.xml` → Webアプリケーション記述子
      - `build.xml` → Apache Antビルドファイル

Struts 1ポートレットのアップグレードには、次の手順が含まれます。

1.  Liferay Upgrade Plannerを使用して、ポートレットを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行する時、既存の事象の多くが自動修正されます。 残りの事象については、プランナーは新しいAPIの影響を受けるコードとそれを適合させる方法を特定します。

2.  [依存関係を解決します。](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

サンプルStrutsポートレットの依存関係を解決しました。 これでデプロイする準備ができました。

| **重要**: ポータルプロパティを`jsp.page.context.force.get.attribute` | ([JSP section](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#JSP)で説明） | `true` (default) に設定すると、 | `getAttribute(String)`を使用するため  | `com.liferay.taglib.servlet.PageContextWrapper#findAttribute(String)`の呼び出しが強制されます。 不要なフォールバックを回避 | することでパフォーマンスが向上しますが、Struts | ポートレットで属性検索の問題を引き起こす可能性があります。 サイトでStrutsポートレットを使用するには、ポータル | プロパティ`jsp.page.context.force.get.attribute`を、ファイル | `[Liferay-Home]/portal-ext.properties`で`false`へ設定するようにしてください。 | | `properties | jsp.page.context.force.get.attribute=false |`

StrutsポートレットWebアプリケーションaRchive（WAR）をデプロイすると、@product@のWeb アプリケーションバンドル（WAB）ジェネレーターは、ポートレット用のOSGiモジュール（バンドル）を作成し、LiferayのOSGiフレームワークにそれをインストールします。 サーバーは、次のポートレット状態を示すメッセージを出力します。

  - WAR processing
  - WAB startup
  - Availability to users

Strutsポートレットが@product@インスタンスで使用可能になりました。 Strutsポートレットは、@product-ver@サイトの以前のバージョンと同じように動作します。

これで、Strutsポートレットを@product-ver@\にアップグレードできました。
