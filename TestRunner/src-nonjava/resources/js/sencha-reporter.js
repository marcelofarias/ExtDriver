Sencha = {
		running : true,
		results : [],
        Reporter : function() {
        }
};

Sencha.Reporter.prototype.log = function() {
//	console.log("log()");
//	console.log(arguments);
};

Sencha.Reporter.prototype.reportRunnerStarting = function() {
	console.log("reportRunnerStarting()");
	Sencha.running = true;
//	console.log(arguments);
};

Sencha.Reporter.prototype.reportRunnerResults = function() {
	console.log("reportRunnerResults()");
	Sencha.running = false;
//	console.log(arguments);
//	debugger;
};

Sencha.Reporter.prototype.fullSuiteName = function(suite) {
	return ((suite.parentSuite) ? suite.parentSuite.description + " " : "") + suite.description;
};

Sencha.Reporter.prototype.reportSpecResults = function(spec) {
	var description, passed;
	
//	debugger;
	description = this.fullSuiteName(spec.suite) + " " + spec.description;
	passed = spec.results().passed();
	
	Sencha.results.push({
		description : description,
		passed : passed
	});
//	Sencha.results.push(result);
//	console.log(result);
};

Sencha.Reporter.prototype.reportSpecStarting = function(spec) {
//	console.log("reportSpecStarting()");
//	console.log(arguments);
//	console.log("it " + spec.description);
};

Sencha.Reporter.prototype.reportSuiteResults = function(suite) {
//	console.log("reportSuiteResults()");
//	console.log(arguments);
//	console.log("describe " + suite.description);
};


