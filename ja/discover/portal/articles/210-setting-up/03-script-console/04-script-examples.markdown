---
header-id: script-examples
---

# スクリプトの例

[TOC levels=1-4]

以下は、Liferayのスクリプトコンソールの使用に役立ついくつかの例です。 注：これらのほとんどは [Liferayブログ投稿](https://liferay.dev/blogs/-/blogs/5-tips-to-improve-usage-of-the-liferay-script-console)由来します。

以下のスクリプトはGroovyスクリプトですが、他の言語に適合させることができます。

  - [例1：新しい利用規約をユーザーに提示する](#example-1-presenting-new-terms-of-use-to-users)

  - [例2：スクリプト出力へのHTMLマークアップの埋め込み](#example-2-embedding-html-markup-in-script-outputs)

  - [例3：スクリプトコンソールで例外を表示する](#example-3-show-exceptions-in-the-script-console)

  - [例4：プレビューモードの実装](#example-4-implement-a-preview-mode)

  - [例5：長時間実行スクリプトのファイル出力を計画する](#example-5-plan-a-file-output-for-long-running-scripts)

## 例1：新しい利用規約をユーザーに提示する

この例では、データベースからユーザー情報を取得し、変更を加えて、変更をデータベースに保存します。 会社が [の利用規約](/docs/7-1/user/-/knowledge_base/u/terms-of-use) を更新し、ユーザーが次にサインインするたびに更新された利用規約をユーザーに提示したいとします。 ユーザーが利用規約に同意すると、ユーザーレコードに `合意されたTermsOfUse` と呼ばれるブール属性が設定されます。 この変数の値が `true`である限り、ユーザーはサインイン時に利用規約が表示されません。 ユーザーごとにこのフラグを `false` に設定した場合、各ユーザーはサインインする前に、利用規約に再度同意する必要があります。

1.  スクリプトコンソールに次のコードを入力して実行します。
   
        import com.liferay.portal.kernel.service.UserLocalServiceUtil
       
        userCount = UserLocalServiceUtil.getUsersCount()
        users = UserLocalServiceUtil.getUsers(0, userCount)
       
        long currentUserId = Long.parseLong(userInfo.get("liferay.user.id"))
       
        for (user in users) { println("User Name: " + user.getFullName() + " -- " +
        user.getAgreedToTermsOfUse()) }

    このコードは、各ユーザーの合意された `合意された使用` 属性値を出力します。

2.  これを次のスクリプトに置き換えます。
   
        import com.liferay.portal.kernel.service.UserLocalServiceUtil
       
        userCount = UserLocalServiceUtil.getUsersCount()
        users = UserLocalServiceUtil.getUsers(0, userCount)
       
        for (user in users){
       
            if(!user.isDefaultUser() && (user.getUserId() != currentUserId)) {
       
                    user.setAgreedToTermsOfUse(false)
                    UserLocalServiceUtil.updateUser(user)
       
            }
       
        }

    これにより、各ユーザーの合意された `の<code>合意属性` が `false`ます。 デフォルトのユーザーと、現在サインインしてスクリプトを実行しているデフォルトの管理ユーザーをスキップします。

3.  *実行*クリックし* 。</p></li>

4

最初のスクリプトを再度実行して、スクリプトがレコードを更新したことを確認します。

    すべてのユーザー（デフォルトのユーザーと自分のユーザーを除く）が更新されました。</ol>

すべてのユーザーが同意する新しい利用規約を有効にしました。

## 例2：スクリプト出力へのHTMLマークアップの埋め込み

スクリプトコンソールの出力は、HTMLコンテンツとしてレンダリングされます。 したがって、HTMLマークアップを出力に埋め込んで、ルックアンドフィールを変更できます。 次に例を示します。

    import com.liferay.portal.kernel.service.*
    
    number = com.liferay.portal.kernel.service.UserLocalServiceUtil.getUsersCount();
    out.println(
            """ 
                    <div style="background-color:black; text-align: center">
                            <h1 style="color: #37A9CC; font-size:xx-large">${number}</h1>
                    </div>
            """);

![図1：スクリプトの出力にHTMLマークアップを埋め込むGroovyスクリプトを呼び出す例を次に示します。](../../../images/groovy-script-embed-html-markup.png)

## 例3：スクリプトコンソールで例外を表示する

スクリプトの実行中に例外が発生した場合、エラーメッセージは常に同じです。

`Your request failed to complete.`

このメッセージはエラーの詳細を提供しません。 エラーとその原因に関する情報を見つけるには、通常、サーバーログを調べる必要があります。

ただし、次の方法を使用して、スクリプトコンソールに例外の詳細を表示することができます。 try / catchブロックでコードをラップし、catch句からのコンソール出力にスタックトレースを出力します。 この手法でもスクリプトの構文エラーをキャッチしないことに注意してください。 次に例を示します。

    try {
            nullVar = null
            out.println(nullVar.length())
    } catch(e) {
            out.println("""<div class="portlet-msg-error">${e}</div>""")
            e.printStackTrace(out)
    }

![図2：例外をキャッチしてスクリプトコンソールに例外情報を出力するGroovyスクリプトの例を次に示します。](../../../images/groovy-script-show-exception.png)

## 例4：プレビューモードの実装

Liferayのスクリプトコンソールには元に戻す機能がないため、一種のプレビューモードを設定すると便利です。 プレビューモードの目的は、情報が実際にLiferayデータベースに保存される前に、スクリプトの永続的な影響を判断することです。 プレビューモードは `previewMode` フラグを使用して構成され、永続的な効果を持つ操作を実行するかどうかを決定します。 `previewMode` が `true`場合、スクリプトによって永続的に影響を受けるすべてのデータが代わりに出力されます。 次に、スクリプトの影響を受けるデータの概要を確認できます。 すべて問題がない場合は、フラグを切り替えて、スクリプトがデータベースを永続的に更新できるようにします。

以下は、ユーザーを非アクティブに設定するGroovyスクリプトの例です。 明らかに、それを実行する前にプレビューモードでこれをテストしたいでしょう：

    import java.util.Calendar
    import com.liferay.portal.kernel.service.*
    import com.liferay.portal.kernel.model.*
    import com.liferay.portal.kernel.dao.orm.*
    import static com.liferay.portal.kernel.workflow.WorkflowConstants.*
    
    //
    // Deactivate users never logged and created since more than 2 years
    //
    
    previewMode = true // Update this flag to false to really make changes
    
    Calendar twoYearsAgo = Calendar.getInstance()
    twoYearsAgo.setTime(new Date())
    twoYearsAgo.add(Calendar.YEAR, -2)
    
    DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class)
            .add(PropertyFactoryUtil.forName("lastLoginDate").isNull())
            .add(PropertyFactoryUtil.forName("createDate").lt(twoYearsAgo.getTime()))
    
    users = UserLocalServiceUtil.dynamicQuery(query)
    
    users.each { u ->
             if(!u.isDefaultUser() && u.getStatus() != STATUS_INACTIVE) {
                    out.println(u.getEmailAddress())
                    if(!previewMode) {
                            UserLocalServiceUtil.updateStatus(u.getUserId(), STATUS_INACTIVE)
                    }
             }
    }
    
    if(previewMode) {
            out.println('Preview mode is on: switch off the flag and execute '
    
                    + 'again this script to make changes to the database') 
    }

## 例5：長時間実行スクリプトのファイル出力を計画する

| **重要：** スクリプトコンソールは、システムの操作とメンテナンスおよび|エンドユーザー向けではありません。 スクリプトコンソールへのアクセスをポータル管理者に制限します。

スクリプトが長時間実行されている場合、スクリプトが実行を継続して正常に終了する可能性があっても、コンソールはエラーを返す可能性があります。 しかし、対応する出力がなければ結果を知ることは不可能です！

この制限を回避するには、スクリプトコンソールの出力を、コンソール自体やLiferayログではなく、ファイルに送信します。 たとえば、次のスクリプトについて考えてみます。

``` 
import com.liferay.portal.kernel.service.*
import com.liferay.portal.kernel.dao.orm.*

// Output management

final def SCRIPT_ID = "MYSCRIPT"
outputFile = new File("""${System.getProperty("liferay.home")}/scripting/out-${SCRIPT_ID}.txt""")
outputFile.getParentFile().mkdirs()

def trace(message) {
        out.println(message)
        outputFile << "${message}\n"
}

// Main code

users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)
users.each { u ->
        trace(u.getFullName())
} 
```

上記のスクリプトは、 `Scripting` と呼ばれる [Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home) サブフォルダーを作成し、スクリプト出力をこのフォルダー内のファイルに保存します。 上記のスクリプトを実行すると、ファイルシステムに直接アクセスしなくても、生成されたファイルを読み取ることができます。 これを示す2つ目のスクリプトを次に示します。

    final def SCRIPT_ID = "MYSCRIPT"
    outputFile = new File("""${System.getProperty("liferay.home")}/scripting/out-${SCRIPT_ID}.txt""")
    out.println(outputFile.text)

従来のロガーを使用する代わりに専用の出力ファイルを使用する利点の1つは、スクリプトの出力データを簡単に戻すことができることです。 たとえば、他のすべての情報があるため、スクリプトの出力データを取得することは、ポータルログから取得するのがより困難になります。

## 関連トピック

[スクリプトコンソールからのスクリプトの実行](/docs/7-1/user/-/knowledge_base/u/running-scripts-from-the-script-console)

[ワークフローでのスクリプトエンジンの活用](/docs/7-1/user/-/knowledge_base/u/leveraging-the-script-engine-in-workflow)

[Liferayのスクリプトエンジンの使用](/docs/7-1/user/-/knowledge_base/u/using-liferays-script-engine)
