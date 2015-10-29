import com.liferay.docs.scriptutil.api.ScriptUtil;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

Bundle bundle = FrameworkUtil.getBundle(ScriptUtil.class);
BundleContext context = bundle.getBundleContext();
ServiceTracker tracker = new ServiceTracker(context, ScriptUtil.class.getName(), null);
tracker.open();
ScriptUtil util = tracker.getService();

out.println(util.operationOne());
out.println(util.operationTwo("Joe Bloggs"));
