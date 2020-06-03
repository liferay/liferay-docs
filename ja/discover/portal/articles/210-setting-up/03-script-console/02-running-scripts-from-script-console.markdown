---
header-id: running-scripts-from-the-script-console
---

# スクリプトコンソールからのスクリプトの実行

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

スクリプトコンソールは、Groovyスクリプトを実行してその出力を印刷するための単一のビューを提供します。 これには、出力の印刷およびポートレットとユーザーの操作を容易にする事前定義変数があります。 ここでは、次のことを学びます。

  - [スクリプトコンソールでスクリプトを実行する方法](#running-the-sample-script)

  - [スクリプトコンソールで使用可能な定義済み変数](#predefined-variables)

  - [スクリプトコンソールでスクリプトを実行するためのヒント](#tips)

| **重要：** スクリプトコンソールは、システムの操作とメンテナンスおよび|エンドユーザー向けではありません。 スクリプトコンソールへのアクセスをポータル管理者に制限します。

まず、スクリプトコンソールのサンプルスクリプトを実行します。

## サンプルスクリプトの実行

スクリプトコンソールでサンプルスクリプトを実行する方法は次のとおりです。

1.  管理者としてサインインします。

2.  製品メニューで、 *コントロールパネル* → *構成* → *サーバー管理*ます。

3.  *Script*クリックし* 。 これはスクリプトコンソールです。 デフォルトのサンプルスクリプトは、ユーザー数をコンソール出力に出力します。</p> 
   
        // ### Groovy Sample ###
       
        number = com.liferay.portal.kernel.service.UserLocalServiceUtil.getUsersCount();
       
        out.println(number);
       </li>

4

*実行* をクリックし、スクリプトコンソール *出力* でユーザー数を確認します。</ol>

![図1：スクリプトコンソールのサンプルGroovyスクリプトは、ユーザー数をコンソールの*出力*セクションに出力します。](../../../images/groovy-script-sample.png)

Groovyサンプルは、Liferayサービスユーティリティ `UserLocalServiceUtil` を呼び出して、ユーザー数を取得します。 次に、 `out` （組み込みの `PrintWriter`）を使用して、スクリプトコンソールにカウントを書き込みます。 `out.println`ではなく `System.out.println` を使用する場合、出力はスクリプトコンソールではなくLiferayのログファイルに出力されることに注意してください。

## 定義済み変数

スクリプトコンソールで実行されるスクリプトで使用できる定義済みの変数は次のとおりです。

  - `アウト` （`java.io.PrintWriter`）
  - `actionRequest` （`javax.portlet.ActionRequest`）
  - `actionResponse` （`javax.portlet.ActionReponse`）
  - `portletConfig` （`javax.portlet.PortletConfig`）
  - `portletContext` （`javax.portlet.PortletContext`）
  - `設定` （`javax.portlet.PortletPreferences`）
  - `userInfo` （`java.util.Map<String, String>`）

このスクリプトは、 `actionRequest` 変数を使用してポータルインスタンスの `Company`を取得する方法を示しています。

    import com.liferay.portal.kernel.util.*
    
    company = PortalUtil.getCompany(actionRequest)
    out.println("Current Company:${company.getName()}\n")
    
    out.println("User Info:")
    userInfo.each { 
            k,v -> out.println("${k}:${v}") 
    }

![図2は：ここで事前定義使用グルービースクリプト呼び出しの例です <code>アウト</code>、 <code>actionRequest</code>、及び <code>ユーザー情報</code> 会社と現在のユーザの情報を印刷する変数。](../../../images/groovy-script-current-user-info.png)

## チップ

スクリプトコンソールを使用するときは、次のことに注意してください。

  - 元に戻すことはできません。
  - プレビューはありません。
  - アクセス許可のチェックは、ローカルサービスには適用されません。
  - スクリプトは同期的に実行されます。 時間がかかる可能性のあるスクリプトの実行は避けてください。

これらの理由から、スクリプトコンソールは慎重に使用してください。 本番環境で実行する前に、非本番システムでスクリプトをテストします。

もちろん、Liferayのスクリプトエンジンは、スクリプトコンソールの外でも使用できます。 次に、ワークフローがLiferayのスクリプトエンジンをどのように活用するかを学習します。
