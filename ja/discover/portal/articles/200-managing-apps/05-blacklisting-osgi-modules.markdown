---
header-id: blacklisting-osgi-modules-and-components
---

# OSGiモジュールとコンポーネントのブラックリスト

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ブラックリストは善悪に使用されます。 邪悪なブラックリストは不当にペナルティを課します。良いブラックリストが保護します。 @product@のOSGiモジュールとコンポーネントのブラックリストは、特定のモジュールのインストールと特定のコンポーネントの有効化を妨げるファイルです。 これにより、アプリケーションマネージャー、コンポーネントリスト、またはGogoシェルを使用して個別にアンインストールおよび無効化する手間が省けます。

## モジュールのブラックリスト

@product@は、ブラックリストにインストールされているすべてのモジュールを削除します。 そのため、ブラックリストに登録されたモジュールはインストールできません。 ログは、各モジュールのアンインストールを報告します。

次の手順に従って、モジュールをブラックリストに登録します。

1.  コントロールパネルで、 *構成* → *システム設定* → *モジュールコンテナー*ます。

2.  [バンドルブラックリスト]画面で、アンインストールするOSGiモジュールJAR、LPKG、またはWARのバンドルシンボリック名（下の表を参照）を追加します。 完了したら、[ *保存* ]ボタンをクリックします。

    ![図1：このブラックリストは、 <code>com.liferay.docs.greeting.api</code> OSGiモジュール、Liferay Marketplace LPKG、および <code>クラシックテーマ</code> WARをアンインストールします。](../../images/bundle-blacklist-configuration.png)

3.  ブラックリストをエクスポートするには、そのアクションボタン（![Actions](../../images/icon-actions.png)）をクリックし、次に *エクスポート*クリックします。 次に、ブラックリスト構成ファイルがダウンロードされます（`com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`）。 次に、サンプルファイルの内容を示します。
   
        blacklistBundleSymbolicNames=["com.liferay.docs.greeting.api","Liferay\ Marketplace","classic-theme"]

4.  インストールを禁止する、リストにないモジュールのバンドルシンボリック名を追加します。

    **重要**：構成値に余分なスペースを含めることはできません。 余分なスペースがあると、リストが短絡したり、構成エントリが無効になったりする可能性があります。

5.  構成ファイルをデプロイするには、それをフォルダー `[Liferay_Home]/ osgi / configs`にコピーします。 Liferayホームフォルダーは通常、アプリケーションサーバーの親フォルダーです。

| **注**：LPKGをブラックリストに登録すると、その内部モジュールがすべてアンインストールされます。

**ブラックリストバンドルのシンボリック名**

| タイプ      | バンドルのシンボリック名                                                                                           |
|:-------- |:------------------------------------------------------------------------------------------------------ |
| モジュールJAR | `Bundle-SymbolicName` in `bnd.bnd` または `MANIFEST.MF` ファイル                                              |
| LPKG     | `.lpkg` 拡張子のないLPKGファイル名                                                                                |
| 戦争       | `サーブレットコンテキスト名liferay-plugin-package.properties` ファイル、またはサーブレットコンテキスト名プロパティがない場合はWARファイル名（マイナス `.war`） |

## ブラックリストモジュールの再インストール

再インストールしてブラックリストに登録されたモジュールのインストールを許可するには、次の手順に従います。

1.  構成ファイル `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`開きます。

2.  モジュール、LPKG、またはWARのシンボリック名を `blacklistBundleSymbolicNames` リストから削除し、ファイルを保存します。

*すべて* を再インストールするには、ブラックリストに登録されたモジュールが次のオプションのいずれかを実行します。

  - 構成ファイルを削除します。
  - [Application Manager](/docs/7-1/user/-/knowledge_base/u/managing-and-configuring-apps#using-the-app-manager) または [Felix Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)を使用して、モジュール `com.liferay.portal.bundle.blacklist` をアンインストールします。

| **注**：ブラックリストに登録されているモジュールを一時的に再インストールするには、次のことができます。システム設定のバンドルブラックリストモジュールからシンボル名を削除し、| *Update* ボタンをクリックします。 モジュールをその後にインストールしたい場合|サーバーの起動時に、既存のモジュールからモジュールのシンボル名を必ず削除してください。 `[Liferay_Home]/ osgi / configs` フォルダー内のモジュールブラックリスト構成ファイル。

ログは各モジュールのインストールを報告します。

## コンポーネントのブラックリスト化

次の手順に従って、コンポーネントをブラックリストに登録します。

1.  コントロールパネルで、 *構成* → *システム設定* → *モジュールコンテナー*ます。

2.  [コンポーネントブラックリストの構成]画面で、無効にするコンポーネントの名前を追加し、[ *保存* ]ボタンをクリックします。

    ![図2：このブラックリストは、コンポーネント <code>com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth</code> および <code>com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet</code>無効にします。](../../images/component-blacklist-configuration.png)

3.  ブラックリストをエクスポートするには、コンポーネントブラックリスト設定モジュールの[アクション]ボタン（![Actions](../../images/icon-actions.png)）をクリックし、[ *エクスポート*]をクリックします。 次に、ブラックリスト構成ファイルがダウンロードされます（`com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`）。 次に、サンプルファイルの内容を示します。
   
        blacklistComponentNames=["com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth","com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet "]

4.  まだリストされていないコンポーネント（たとえば、まだインストールされていないモジュールのコンポーネント）のアクティブ化を禁止する名前を追加します。

    **重要**：構成値に余分なスペースを含めることはできません。 余分なスペースがあると、リストが短絡したり、構成エントリが無効になったりする可能性があります。

5.  構成ファイルをデプロイするには、それをフォルダー `[Liferay_Home]/ osgi / configs`にコピーします。 Liferayホームフォルダーは通常、アプリケーションサーバーの親フォルダーです。

## ブラックリストに登録されたコンポーネントを再度有効にする

ブラックリストに登録されたコンポーネントを再度有効にして有効にするには、次の手順に従います。

1.  構成ファイル `com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`開きます。

2.  `blacklistComponentNames` リストからコンポーネントの名前を削除し、ファイルを保存します。

*すべて* のブラックリストコンポーネントを有効にするには、構成ファイルを削除します。

| **注**：ブラックリストに登録されたコンポーネントを一時的に再アクティブ化するには、その名前を削除します|システム設定のコンポーネントブラックリスト設定モジュールから、|をクリックします。 *アップデート*。 コンポーネントをその後のサーバー起動時にアクティブ化する場合は、|既存のコンポーネントのブラックリストからコンポーネントの名前を必ず削除してください。 `[Liferay_Home]/ osgi / configs` フォルダー内の構成ファイル。
