package com.liferay.docs.simplebundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SimpleBundleActivator implements BundleActivator {

	@Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Hello Liferay!");
    }

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Goodbye Liferay!");
	}

}
