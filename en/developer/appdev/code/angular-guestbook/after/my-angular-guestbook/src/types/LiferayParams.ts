/**
 * This is the structure of the parameters passed by Liferay to the JS module.
 *
 * See https://tinyurl.com/js-ext-portlet-entry-point for the most recent 
 * information on the structure of this type.
 */
export default interface LiferayParams {
	portletElementId: string;
	contextPath: string;
	portletNamespace: string;
	configuration: any;
}

