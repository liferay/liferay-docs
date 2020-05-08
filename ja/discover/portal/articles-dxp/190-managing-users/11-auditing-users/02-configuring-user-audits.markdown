---
header-id: configuring-audits
---

# 監査の構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

監査はデフォルトで有効になっています。 監査アプリは監査イベントを報告しますが、@product@のログまたはコンソールでそれらを報告したり、スケジュールされたジョブでそれらを有効にしたり、完全に無効にしたりすることもできます。

@product@を設定するには、主に2つの方法があります。

1.  コントロールパネルから設定を編集します。 これにより、構成がデータベースに保存されます。

2.  `[LIFERAY_HOME]/ osgi / configs` フォルダーのOSGi構成ファイル（`.config` ファイル）を介して構成を編集します。

これらの方法は、以下で説明する監査構成オプションのそれぞれに適用されます。

## Liferayのログとコンソールでの監査イベントの報告

コントロールパネルを使用して@product@のログとコンソールでログイベントのレポートを構成するには、次の手順に従います。

1.  *コントロールパネル* → *構成* → *システム設定*に移動し、 *セキュリティ* セクションから *監査* を選択します。

2.  左側の *SYSTEM SCOPE* 列で、 *Logging Message Audit Message Processor*選択します。

3.  *有効* チェックボックスを選択して、@product@のログに監査イベントを報告します。

4.  選択 *コンソールに出力を* コンソールで監査イベントを報告するチェックボックスをオンにします。

5.  *Log Message Format* セレクターメニューで、監査イベントのフォーマット（CSVまたはJSON）を選択します。

6.  完了したら、[ *保存* クリックします。

または、OSGi構成ファイルを使用して同じ構成を作成することもできます。

1.  `com.liferay.portal.security.audit.router.configuration.LoggingAuditMessageProcessorConfiguration.config`というファイルを作成します。

2.  これらのプロパティをファイルに追加します。
   
        enabled="true"
        logMessageFormat="CSV"
        #logMessageFormat="JSON"
        outputToConsole="true"

    これらは、コントロールパネルで設定したものと同じオプションです。 必要に応じて編集してください。

3.  ファイルを `[LIFERAY_HOME]/ osgi / modules` フォルダーにデプロイします。 [Liferayホームフォルダー](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home) は、通常、アプリケーションサーバーの親フォルダーです。

構成方法に関係なく、@product@の `log4j-ext.xml` ファイルを拡張して、適切なクラスによって生成されたメッセージを適切なファイルに記録するようにLog4j（@product@のロギング実装）を構成する必要もあります。 そのためには、次の構成で `portal-log4j-ext.xml` ファイルを `[LIFERAY_HOME]/ tomcat-[version]/ webapps / ROOT / WEB-INF / classes / META-INF` します。

    <?xml version="1.0"?>
    <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
    
    <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    
        <!-- additional audit logging -->
    
        <appender name="auditFile" class="org.apache.log4j.rolling.RollingFileAppender">
            <rollingPolicy class="org.apache.log4j.rolling.TimeBasedRollingPolicy">
                <param name="FileNamePattern" value="@liferay.home@/logs/audit.%d{yyyy-MM-dd}.log" />
            </rollingPolicy>
            <layout class="org.apache.log4j.EnhancedPatternLayout">
                <param name="ConversionPattern" value="%d{ABSOLUTE} %-5p [%t][%c{1}:%L] %m%n" />
            </layout>
        </appender>
    
        <category name="com.liferay.portal.security.audit.router.internal.LoggingAuditMessageProcessor">
            <priority value="INFO" />
            <appender-ref ref="auditFile"/>
        </category>
    </log4j:configuration>

この構成クラスからINFOレベルのメッセージを記録するためのLog4j `com.liferay.portal.security.audit.router.internal.LoggingAuditMessageProcessor` ファイルへと呼ばれる `audit.yyyy-MM-dd.log` で `[LIFERAY_HOME]/ログ` フォルダ。 監査ファイルのプロパティまたはログレベルを必要に応じて調整します。

## スケジュールされたLiferayジョブの監査イベントの構成

デフォルトでは、スケジュールされたジョブは監査イベントをトリガーしません。 次の手順に従って、コントロールパネルから有効にします。

1.  *Control Panel* → *Configuration* → *System Settings*に移動し、 *Platform* セクションから *Infrastructure* を選択します。

2.  左側の *SYSTEM SCOPE* 列で、 *Scheduler Engine Helper*選択します。

3.  *監査スケジューラジョブを有効にする* のチェックボックスを選択し、 *保存*をクリックします。

または、OSGi構成ファイルを使用して同じ構成を作成することもできます。

1.  `com.liferay.portal.scheduler.configuration.SchedulerEngineHelperConfiguration.config`というファイルを作成します。

2.  このプロパティをファイルに追加します。
   
        auditSchedulerJobEnabled=true

3.  ファイルを `[LIFERAY_HOME]/ osgi / modules` フォルダーにデプロイします。 [Liferayホームフォルダー](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home) は、通常、アプリケーションサーバーの親フォルダーです。

後で説明するように、能力レベルに疑いのある人がジョブをスケジュールしようとする可能性がある場合は、スケジュールされたジョブを監査するのが賢明な選択です。

## 監査イベント全体の有効化または無効化

監査イベントはデフォルトで有効になっています。 次の手順に従って、コントロールパネルから無効にしてください。

1.  *コントロールパネル* → *構成* → *システム設定* 、次に *セキュリティ* セクションの *監査* をクリックします。

2.  [ *有効* ボックスをオフにします。 監査が有効な場合、監査メッセージの最大キューサイズをデフォルト値から調整できることに注意してください。

または、OSGi構成ファイルを使用して監査を有効または無効にすることもできます。

1.  `com.liferay.portal.scheduler.configuration.SchedulerEngineHelperConfiguration.config`というファイルを作成します。

2.  これらのプロパティをファイルに追加します。 必要に応じて値を調整できます。
   
        enabled="true"
        auditMessageMaxQueueSize="200"

3.  ファイルを `[LIFERAY_HOME]/ osgi / modules` フォルダーにデプロイします。 [Liferayホームフォルダー](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home) は、通常、アプリケーションサーバーの親フォルダーです。

## 物語の終わり

「わかりました」とハリーは言います。「監査システムを起動して、何が起こったのかを理解できるかどうか見てみましょう。」

あなたとディックはハリーの椅子の後ろに立ち、監査アプリのフォームにクエリが入力されるのを見ます。 *search*クリックすると、画面は監査イベントで一杯になります。

「わあ、それは多くの未割り当てイベントです。」ハリーは言う。 「そして、犯人が誰であるか見てください」と彼は皮肉にも付け加えます。

「メルヴィン・ドイトロンは誰ですか？」ディックは尋ねます。

「それが私の新しいインターンです」とハリーは言います。 「私は彼を殺すつもりです。」ハリーは彼の椅子を押し出し、立方体の列を最後まで歩きます。そこで、乱れた髪の20歳以下の子供が座って、耳にはイヤホンがあります。

「やあメルビン」メルビンが振り向くとハリーは彼と向き合う。 「そのユーザーセットをサイトメンバーシップから組織メンバーシップに移動するように依頼しませんでしたか？」

「そうだ」とメルビン氏は言う。

「どうやってやったの？」

「それを手動で行うにはしばらく時間がかかるので、スクリプトを作成してスクリプトホストで実行しました」とMelvinは答えました。

「そうでしたね。 さて、何を推測しますか？ あなたのスクリプトは、</em> *すべての* *サイトから *全員* を削除しました。」</p>

「*何？*」

「そうですね。次に、ジョーンズ氏から始めて、手動で1つずつ追加していきます。」

トムとディックは、ハリーとメルヴィンが彼らと話し続けている間、メルヴィンの立方体からゆっくりと遠ざかっています。 はっきりしているのは、メルビンよりも良い一日を過ごしているということです。
