# Configuring Liferay for High Availability [](id=configuring-liferay-for-high-availability-lp-6-2-use-useportal)

Liferay Portal is a robust, enterprise-ready portal solution. As such, it is
fully ready to support mission-critical, enterprise applications in an
environment configured for multiple redundancies and 24/7 uptimes. The product,
however, like other products of its kind, doesn't come configured this way out
of the box, so there are some steps that need to be taken to tune it for your
needs. 

This chapter covers these topics in detail. Liferay runs on so many different
Java EE application servers that we can't cover all the differences between
them. For this reason, we'll only discuss Liferay configurations. For example,
we'll look at how to configure Liferay to work in a clustered environment, but
not how to create the cluster in your application server. The documentation for
your particular application server is always a much better place to learn those
kinds of things. 

This chapter explains how to configure Liferay for a number of advanced
scenarios, such as

- Clustering 

- Distributed Caching

- Deploying Customized versions of Liferay

- Performance Testing and Tuning

During the discussion, we'll mention a number of other open source products upon
which Liferay relies for much of this functionality. These products all have
their own documentation which should be consulted for a fuller view of what
these products can do. For example, Liferay uses Ehcache for its caching
mechanism. We'll cover how to configure Ehcache to enable caches in Liferay, but
will refer you to that product's documentation for further information about
that product.

Sometimes Liferay supports multiple products which perform the same function.
There are, for example, several single sign-on implementations you can use with
Liferay. We'll leave it up to you to select which product best fits the needs of
your project without recommending one product over another.

With all of that said, let's get started configuring Liferay for the enterprise.
