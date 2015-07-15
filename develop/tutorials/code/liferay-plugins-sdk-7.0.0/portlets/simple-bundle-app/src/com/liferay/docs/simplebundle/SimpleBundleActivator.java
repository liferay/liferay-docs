package com.liferay.docs.simplebundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class SimpleBundleActivator implements BundleActivator {

	@Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Simple Bundle starting");
    }

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Simple Bundle stopping");
	}

}
