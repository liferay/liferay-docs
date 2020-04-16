---
header-id: form-permissions
---

# フォームの権限

[TOC levels=1-4]

フォームの権限にアクセスするには、最初に *サイト管理* （サイトのメニュー）→ *コンテンツ* → *フォーム*のフォームアプリに移動します。 次に、フォームの[ *Actions* ]ボタン（![Actions](../../images/icon-actions.png)）をクリックし、[ *Permissions*を選択します。

デフォルトでは、フォームに次の権限を付与できます。

**削除：** フォームを削除します。

**アクセス許可：** フォームのアクセス許可にアクセスして構成します。

**フォームインスタンスレコードを追加：** フォームエントリを送信します。

**更新：** フォームエントリを更新します。

**表示：** フォームを表示します。

![図1：フォームのアクセス許可を構成できます。](../../images/forms-form-permissions.png)

ゲストユーザーはデフォルトでフォームを表示して入力できることに注意してください。 *Guest* Roleには *View* および *Add Form Instance Record* 権限があります。

| **注：** デフォルトでは、すべてのユーザーがゲストロールの権限を継承します。 ゲスト|ロールは、サイトの認証されていない訪問者を表します。 させたい場合|ゲストユーザーはフォームを送信します（デフォルト設定）。認証されたユーザーもできます。 ゲストの自動継承を無効にするには|ロールの権限、設定| [このプロパティ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Permissions) | `portal-ext.properties` ファイル：| | permissions.check.guest.enabled = false
