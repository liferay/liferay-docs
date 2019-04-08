import {
	ApplicationRef,
	ComponentFactoryResolver,
	Injector,
	NgZone,
	Type,
} from '@angular/core';

import LiferayParams from '../types/LiferayParams'

/**
 * This class loads an Angular component dinamically so that we can attach it to
 * the portlet's DOM, which is different for each portlet instance and thus, 
 * cannot be determined until the page is rendered (during runtime).
 */
export class DynamicLoader {
	constructor(private injector: Injector) {}

	loadComponent<T>(component: Type<T>, params: LiferayParams) {
		const node = document.getElementById(params.portletElementId);
		
		(<NgZone>this.injector.get(NgZone)).run(() => {
			const componentFactory = this.injector
				.get(ComponentFactoryResolver)
				.resolveComponentFactory(component);
			const componentRef = componentFactory.create(
				this.injector,
				[],
				node,
			);
			componentRef.instance.params = params;
			this.injector.get(ApplicationRef).attachView(componentRef.hostView);
		});
	}
}
