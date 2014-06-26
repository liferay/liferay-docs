# Liferay installation overview [](id=liferay-installation-overview-lp-6-2-use-useportal)

Before we begin, it's important to go over the various facets of the
installation. They are: 

1. Create your database. 

2. Determine whether you want to use the Liferay managed data source or a data
   source managed by your application server. The Liferay managed data source is
   recommended. 

3. Gather credentials for sending email notifications to users. Liferay supports
   a JNDI mail session as well as its built-in mail session. 

4. Install a Liferay bundle or on an existing application server according to
   the instructions for your application server (see below). 

5. Determine whether you'll use Marketplace or other third party applications.
   If you will, you should enable Liferay's Plugin Access Control List (PACL). 

We'll go through the steps in order, so first we'll look at the Liferay
database.
