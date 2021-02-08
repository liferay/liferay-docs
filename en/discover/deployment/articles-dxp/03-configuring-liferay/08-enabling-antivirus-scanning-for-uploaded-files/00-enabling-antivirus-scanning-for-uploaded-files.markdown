# Enabling Antivirus Scanning for Uploaded Files

Antivirus scanning is available for files uploaded to Liferay DXP. When you enable the antivirus scanner, it checks files on upload to Liferay applications, such as Documents and Media, Message Boards, and more. Virus-infected files are reported for users to reject.

![The scanner detects virus-infected files on upload to Documents and Media and other Liferay applications.](../../../images-dxp/clamd-virus-detected.png)

Liferay DXP 7.1 Fix Pack 20+ (and Service Pack 5+) integrates with the [ClamAV Daemon](https://www.clamav.net/documents/scanning#clamd) (Clamd) running on a separate server.

**Note:** Prior to DXP 7.1 Fix Pack 20, the ClamAV antivirus scanner ran locally. Now DXP delegates antivirus scanning to a separate server.

Here's how to enable the Clamd antivirus scanner:

1. On a separate server, [configure and start Clamd](https://www.clamav.net/documents/scanning#clamd).

    **Important:** Load your ClamAV database before starting Clamd.

1. Enable antivirus for your File Store (Document Library) by setting the following portal property or [Docker Env variable](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.html#using-liferay-env-variables).

    Portal property:

    ```propertie
    dl.store.antivirus.enabled=true
    ```

    Env variable:

    ```properties
    -e LIFERAY_DL_PERIOD_STORE_PERIOD_ANTIVIRUS_PERIOD_ENABLED=true
    ```

1. Start your DXP server.

1. Go to *Control Panel* &rarr; *System Settings*, and select *Antivirus* in the *Security* category.

    ![Antivirus is in the Security category in System Settings.](../../../images-dxp/clamd-antivirus-system-settings.png)

1. Select *Antivirus Clamd Scanner* in the menu.

    ![Antivirus Clamd Scanner configuration](../../../images-dxp/clamd-setup.png)

1. Enter the Clamd server's hostname or IP address, port, and a connection timeout time (milliseconds).

1. Click *Save*.

Now files are scanned on upload to Liferay applications. If a virus is detected in a file you're uploading, the scanner reports the infected file--decline saving the file.

![Here's the virus detection message.](../../../images-dxp/clamd-virus-detected-message.png)

**Important:** Never save a virus-infected file. Reject the file by canceling the current operation.