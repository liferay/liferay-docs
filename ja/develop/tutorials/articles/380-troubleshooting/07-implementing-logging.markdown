---
header-id: implementing-logging
---

# ロギングの実装

[TOC levels=1-4]

@product-ver@はLog4jロギングフレームワークを使用していますが、将来的には置き換えられる可能性があります。[Simple Logging Facade for Java
\(SLF4J\)](https://www.slf4j.org/)を使用して、モジュールおよび従来のプラグインのメッセージをログに記録することをお勧めします。 SLF4Jは@product@にすでに統合されているため、メッセージのロギングに集中できます。

SLF4Jを使用してクラス内のメッセージを記録する方法は次のとおりです。

1. プライベートの静的SLF4J [`Logger` フィールド](https://www.slf4j.org/apidocs/org/slf4j/Logger.html)を追加します。

       private static Logger _logger;
   
2. ロガーをインスタンス化します。

       _logger = LoggerFactory.getLogger(this.getClass().getName());
   
3. クラス全体で、注目すべきイベントが発生したメッセージを記録します。

   たとえば、

       _logger.debug("...");
       _logger.warn("...");
       _logger.error("...");
       ...
   
   各メッセージに適した`Logger`メソッドを使用します。

   - `debug`：デバッグに役立つイベントとアプリケーションの情報。
   - `error`：通常のエラー。これは、最も簡易的なメッセージレベルです。
   - `info`：ハイレベルのイベント。
   - `trace`：debugよりも多くの情報を提供します。これは最も詳細なメッセージレベルです。
   - `warn`：必ずしもそうとは限らないが、問題の可能性を示す情報。

ログの詳細度は、クラスまたはパッケージに設定されたログレベルと相関します。
 `info`や`debug`など、より詳細な情報を期待できるログレベルで追加情報を提供するようにしてください。

モジュールと従来のプラグインにロギングを追加できるようになりました。

## 関連トピック

[Adjusting Module Logging](/docs/7-1/tutorials/-/knowledge_base/t/adjusting-module-logging)
