---
header-id: organizing-folders-for-larger-applications
---

# 大規模アプリケーション用のフォルダの整理

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">プロトタイプのリファクタリング</p><p>手順1/6</p>
</div>

大規模なプロジェクトでは、すべてのファイルとモジュールを適切に整理することが重要です。 プロジェクトをより適切に整理するために、2つの変更を行います。

1.  `guestbook-web`モジュールを`guestbook`フォルダに移動して、作成した `guestbook-service`および`guestbook-api` モジュールと同じ場所に配置します。

2.  これで2つのポートレットができるので、JSPを再編成して、ポートレットごとにグループ化します。

## guestbook-webの移動

モジュールを移動させる最良の方法は、@ide@の *Refactor*関数を使用することです。 リファクタリング機能は、プロジェクトの依存関係とリンクをスキャンしてアップデートします。

1.  *Project Explorer*で`guestbook-web`を右クリックし、*Refactor* → *Move*を選択します。

2.  表示されるウィンドウで、*Browse*をクリックし、`guestbook`フォルダに移動して、*New Folder*をクリックします。

3.  新しいフォルダに`guestbook-web`と名前を付けます。

4.  *Open*、そのあと*OK*をクリックして確認します。

`guestbook-web`フォルダが、他のモジュールと共に構造に表示されます。

![図1：リファクタリング機能を使用して移動した後、すべてのモジュールは同じフォルダにあります。](../../../images/guestbook-refactor.png)

## JSPの再編成

現在、すべてのJSPはWebモジュールの`src/main/resources/META-INF/resources`フォルダにあり、コンテキストルートフォルダとして機能します。 GuestbookポートレットとGuestbook Adminポートレットを明確に区別するには、ビューレイヤーを構成するファイルを個別のフォルダに配置する必要があります。

1.  `guestbook-web`プロジェクトで、`src/main/resources/META-INF/resources`フォルダを右クリックし、*New* → *Folder*を選択します。 新しいフォルダに`guestbookwebportlet`という名前を付け、*Finish*をクリックします。

2.  `view.jsp`および`edit_entry.jsp`を新しいフォルダにドラッグアンドドロップしてコピーします。

3.  両方のファイルを開き、ファイルの上部にある`init.jsp`のロケーションを変更します。

    ``` 
    <%@include file="../init.jsp"%>        
    ```

4.  ファイル内のJSPへの他の参照をチェックして、それらが新しいロケーションを指していることを確認します。

ビューレイヤーをアップデートして新しいバックエンドを最大限に活用すると、古いパスへの参照がアップデートされます。 さらに、コンポーネントのプロパティでリソースのロケーションをアップデートする必要があります。 次の手順では、リソースのロケーションを定義するプロパティを含む、これらのプロパティをすべてアップデートします。
