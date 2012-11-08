Sencha = {
		results : [],
        Reporter : function() {
        }
};

Sencha.Reporter.prototype.log = function() {
//	console.log("log()");
//	console.log(arguments);
};

Sencha.Reporter.prototype.reportRunnerResults = function() {
//	console.log("reportRunnerResults()");
//	console.log(arguments);
//	debugger;
};

Sencha.Reporter.prototype.reportRunnerStarting = function() {
//	console.log("reportRunnerStarting()");
//	console.log(arguments);
};

Sencha.Reporter.prototype.fullSuiteName = function(suite) {
	return ((suite.parentSuite) ? suite.parentSuite.description + " " : "") + suite.description;
};

Sencha.Reporter.prototype.reportSpecResults = function(spec) {
	var result = this.fullSuiteName(spec.suite) + " " + spec.description;
	Sencha.results.push(result);
	console.log(result);
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


