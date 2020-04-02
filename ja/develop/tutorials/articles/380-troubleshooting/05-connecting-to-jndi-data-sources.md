---
header-id: connecting-to-data-sources-using-jndi
---

# JNDIデータソースへの接続

[TOC levels=1-4]

@product@のOSGi環境からアプリケーションサーバーのJNDIデータソースへ接続するのは、Java EE環境から接続するのとほぼ同じです。唯一の違いは、OSGi環境では、アプリケーションサーバーのJNDIクラスをロードするために@product@のクラスローダーを使用する必要があることです。コードは以下のとおりです。

    Thread thread = Thread.currentThread();
    
    // Get the thread's class loader. You'll reinstate it after using
    // the data source you look up using JNDI
    
    ClassLoader origLoader = thread.getContextClassLoader();
    
    // Set Liferay's class loader on the thread
    
    thread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());
    
    try {
    
    // Look up the data source and connect to it
    
    InitialContext ctx = new InitialContext();
    DataSource datasource = (DataSource)
    ctx.lookup("java:comp/env/jdbc/TestDB");
    
    Connection connection = datasource.getConnection();
    Statement statement = connection.createStatement();
    
    // Execute SQL statements here ...
    
    connection.close();
    }
    catch (NamingException ne) {
    
    ne.printStackTrace();
    }
    catch (SQLException sqle) {
    
    sqle.printStackTrace();
    }
    finally {
    // Switch back to the original context class loader
    
    thread.setContextClassLoader(origLoader);
    }

このサンプルコードは、@product@のクラスローダーをスレッドに設定して、JNDI APIにアクセスします。

    thread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());

JNDIを使用してデータソースを検索します。

    InitialContext ctx = new InitialContext();
    DataSource datasource = (DataSource)
    ctx.lookup("java:comp/env/jdbc/TestDB");

データソースを操作した後、コードはスレッドの元のクラスローダーを復元します。

    thread.setContextClassLoader(origLoader);

サンプルコードのクラスインポートは次のとおりです。

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.naming.InitialContext;
    import javax.naming.NamingException;
    import javax.sql.DataSource;
    import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

お使いのアプリケーションでも、同様のコードを使用してデータソースにアクセスできます。必ず`jdbc/TestDB`をご自身のデータソース名に置き換えてください。

**注**：@product@のクラスローダーを使用せずにOSGiバンドルがJNDIデータソースに接続しようとすると、`java.lang.ClassNotFoundException`が発生します。
たとえば、@product@のクラスローダーを使用せずにApache TomcatのJNDI APIを使用しようとした場合の例外は次のとおりです。

|     javax.naming.NoInitialContextException: Cannot instantiate class:
|     org.apache.naming.java.javaURLContextFactory [Root exception is
|     java.lang.ClassNotFoundException:
|     org.apache.naming.java.javaURLContextFactory]

データベースを操作する簡単な方法は、[Service Builderを使用してデータベースに接続する](/docs/7-1/tutorials/-/knowledge_base/t/connecting-service-builder-to-external-databases)ことです。

## 関連トピック

[Connecting Service Builder to External Databases](/docs/7-1/tutorials/-/knowledge_base/t/connecting-service-builder-to-external-databases)
