---
header-id: using-the-upgrade-planner-with-proxy-requirements
---

# プロキシ要件でのUpgrade Plannerの使用

[TOC levels=1-4]

プロキシサーバーの要件があり、Liferay Upgrade Plannerと連携するようにhttp(s)プロキシを設定する場合は、以下の手順に従ってください。

1. Dev Studioの`DeveloperStudio.ini`/`eclipse.ini`ファイルに、以下のパラメータを追加します。

   ```ini
   -Djdk.http.auth.proxying.disabledSchemes=
-Djdk.http.auth.tunneling.disabledSchemes=
   ```

2. Dev Studioを起動します。

3. *[ウィンドウ]* → *[設定]* → *[一般]* → *[ネットワーク接続]*に移動します。

4. *[Active Provider]*のドロップダウンセレクターを*[手動]*に設定します。

5. *[プロキシエントリ]*にあるフィールドをクリックして、*[編集]*ボタンを選択し、プロキシHTTPとHTTPSの両方を設定します。

   ![図1：Dev Studioの[ネットワーク接続]メニューでプロキシを設定できます。](../../../images/upgrade-planner-proxy.png)

6. 各スキーマ（HTTPおよびHTTPS）に対して、プロキシサーバーのホスト、ポート、および認証設定を入力します（必要な場合）。プロキシホストまたはポート設定の末尾には、ホワイトスペースを入れないでください。

7. プロキシエントリを設定したら、*[Apply and Close]*をクリックします。

 これで、Upgrade Plannerのプロキシ設定が正常に設定できました。
