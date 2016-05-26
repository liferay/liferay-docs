import logMsg from './utils/log.es';

class Logger {
	constructor(shellPrompt = '|> ') {
		this.shellPrompt = shellPrompt;
	}

	log(msg) {
		let shellMessage = `${this.shellPrompt} ${msg}`;

		logMsg(shellMessage);
	}
}

export default Logger;