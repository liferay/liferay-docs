# Data Upgrades and Verifiers [](id=data-upgrades-and-verifiers)

Your app goes through various stages of development. You add new features,
remove features, improve features, reorganize the code, etc. During the cycle of
development, you'll want to upgrade your app to a new version to indicate to
your end users that improvements have been made. Liferay provides a robust 
upgrade framework for you to use. In this section of tutorials, you'll learn how 
to create an upgrade process for your application, and how to migrate your 
existing upgrade processes to use the new upgrade framework.

## Data Verifiers [](id=data-verifiers)

A verify process is a class that will run on portal startup to verify and fix
any integrity problems found in the database. You should be aware that this may
make modifications directly to the database.

To enable your verify process on startup, you must add it to your portal
configuration, under **verify.processes** property in portal-ext properties
file.

    verify.processes=com.liferay.portal.verify.VerifyProcessSuite,my.package.MyVerifyProcess

### Verification frequency [](id=verification-frequency)

The verify processes can be configured to be executed with a custom
frequency on portal startup. This frequency only accepts three possible values:

  * Always executed. All verify processes will be executed on each portal
  startup.
  * Never executed. No verify processes will be executed on each portal
  startup.
  * Executed once. All verify processes will be executed only in next portal
  startup, and no more.

This frequency can be configured using portal.properties file, under property
**verify.frequency**, accepting -1, 0 and 1 as possible values, meaning Always,
Never and Once, respectively.

    verify.frequency=1

### Transactions during data verification [](id=transactions-during-data-verification)

You can disable database transactions management during verification. This
forces autocommit, which will speed up the verify process.

To disable database transactions, you must add it to your portal
configuration, under **verify.database.transactions.disabled** property in
portal-ext properties file, accepting *true* and *false* as possible values.

    verify.database.transactions.disabled=true

### Verification concurrency [](id=verification-concurrency)

Configure this threshold to indicate when to execute certain *com.liferay.portal.verify.VerifyProcess*
tasks concurrently. Each VerifyProcess implementation governs what it may
concurrently execute.

To configure the concurrency threshold, you must add it to your portal
configuration, under **verify.process.concurrency.threshold** property in
portal-ext properties file, accepting a natural number as value, including zero.

    verify.process.concurrency.threshold=5
