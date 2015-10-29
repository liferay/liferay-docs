import com.liferay.docs.scriptutil.api.ScriptUtil;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

Bundle bundle = FrameworkUtil.getBundle(ScriptUtil.class);
BundleContext context = bundle.getBundleContext();
ServiceReference ref = context.getServiceReference(ScriptUtil.class.getName());
ScriptUtil util = context.getService(ref);

out.println(util.operationOne());
out.println(util.operationTwo("Joe"));
