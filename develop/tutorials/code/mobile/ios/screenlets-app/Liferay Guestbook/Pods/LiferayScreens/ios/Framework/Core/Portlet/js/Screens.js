window.console = (function (oldCons) {
	return {
		log: function (text) {
			if (window.Screens) {
				window.Screens.postMessage("screensinternal.consoleMessage", "Console message: " + text);
			}
		},
		info: function (text) {
			oldCons.info(text);
		},
		warn: function (text) {
			oldCons.warn(text);
		},
		error: function (text) {
			oldCons.error(text);
		}
	};
}(window.console));


var screens = {
	screensScripts_: [],
	addScreensScript: function (screensScript) {
		this.screensScripts_.push(screensScript);
	},

	reloadScripts: function () {
		this.screensScripts_.forEach(function (scripts) {
			scripts();
		});
	},

	postMessage: function (namespace, message) {
		if (window.cordova) {
			document.addEventListener("deviceready", function () {
				cordova.exec(null, null, "ScreensBridgePlugin", "postMessage", [
					namespace,
					message
				]);
			});
		} else if (window.webkit) {
			window.webkit.messageHandlers.screensDefault.postMessage([
				namespace,
				message
			]);
		}
	},

	waitForJsLoaded: function () {
		this.postMessage('jsloaded', window.location.href)
	}
};

window.Screens = Object.create(screens);

window.Liferay.on('endNavigate', function () {
	window.Screens.reloadScripts();
});
