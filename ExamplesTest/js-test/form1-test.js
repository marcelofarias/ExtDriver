describe('Form 1', function() {
	var form1;
	
	beforeEach(function() {
		if (form1) {
			return;
		}
		
		driver.get('http://qa.sencha.com/knightly/qa/ext-4.1-20121014/examples/form/dynamic.html');
		form1 = new JavaAdapter(com.sencha.extjs.driver.FormFixture, {
			firstName: function() {
				return new com.sencha.extjs.driver.TextFieldFixture(new com.sencha.extjs.driver.locator.FieldLabelLocator("First Name:"), form1, driver);
			},
			lastName: function() {
				return new com.sencha.extjs.driver.TextFieldFixture(new com.sencha.extjs.driver.locator.FieldLabelLocator("Last Name:"), form1, driver);
			},
			company: function() {
				return new com.sencha.extjs.driver.TextFieldFixture(new com.sencha.extjs.driver.locator.FieldLabelLocator("Company:"), form1, driver);
			},
			email: function() {
				return new com.sencha.extjs.driver.TextFieldFixture(new com.sencha.extjs.driver.locator.FieldLabelLocator("Email:"), form1, driver);
			},
			dob: function() {
				return new com.sencha.extjs.driver.DateFieldFixture(new com.sencha.extjs.driver.locator.FieldLabelLocator("DOB:"), form1, driver);
			},
			age: function() {
				return new com.sencha.extjs.driver.NumberFieldFixture(new com.sencha.extjs.driver.locator.FieldLabelLocator("Age:"), form1, driver);
			},
			time: function() {
				return new com.sencha.extjs.driver.TimeFieldFixture(new com.sencha.extjs.driver.locator.FieldLabelLocator("Time:"), form1, driver);
			},
			cancel: function() {
				return new com.sencha.extjs.driver.ButtonFixture(new com.sencha.extjs.driver.locator.ButtonLabelLocator("Cancel"), form1, driver);
			},
			save: function() {
				return new com.sencha.extjs.driver.ButtonFixture(new com.sencha.extjs.driver.locator.ButtonLabelLocator("Save"), form1, driver);
			}
		});
		form1.initialize('simpleForm', null, driver);
	});
	
	it('does something', function() {
		form1
			.collapse()
			.requireCollapsed()
			.expand()
			.requireExpanded();
		form1.firstName().type('I\m typing in javascript!');
	});
	
	it('validates required fields', function() {
		form1.cancel().click();
		form1.save().click();
		
		form1.firstName()
			.requireInvalid()
			.requireErrorMessage("This field is required");
		form1.lastName()
			.requireInvalid()
			.requireErrorMessage("This field is required");
		form1.email()
			.requireInvalid()
			.requireErrorMessage("This field is required");
		
		form1.cancel().click();
	});
	
	it('we can spin up and down a lot of times', function() {
		form1.age()
		.type("31")
		.spinDown()
		.requireText("30")
		.spinUp()
		.requireText("31")
		.spinUp()
		.spinUp()
		.spinUp()
		.spinUp()
		.spinUp()
		.requireText("36")
		.spinDown()
		.spinDown()
		.spinDown()
		.spinDown()
		.spinDown()
		.requireText("31");
	});
	
});
