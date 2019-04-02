# Configuring Local Maven Settings to Access Repositories

To configure your Maven environment to access your repository servers, do the
following:

1.  Navigate to your `[USER_HOME]/.m2/settings.xml` file. Create it if it
    doesn't yet exist. 

2.  Configure your repository server settings. Here are contents from a
    `settings.xml` file that has `liferay-releases` and `liferay-snapshots`
    repository servers configured: 

    ```xml
    <?xml version="1.0"?>
    <settings>
        <servers>
            <server>
                <id>liferay-releases</id>
                <username>admin</username>
                <password>admin123</password>
            </server>
            <server>
                <id>liferay-snapshots</id>
                <username>admin</username>
                <password>admin123</password>
            </server>
        </servers>
    </settings>
    ```

    The user name `admin` and password `admin123` are the credentials of the
    default Nexus administrator account. If you changed these credentials for
    your Nexus server, make sure to update `settings.xml` with these changes. 

Now that your repositories are configured, they're ready to receive all the
Liferay Maven artifacts you'll download and the Liferay module artifacts you'll
create!
