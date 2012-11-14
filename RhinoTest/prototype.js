var AClass = function() {
	this.a = function() {
		println("I'm a");
	}
}

AClass.prototype.b = function() {
	println("I'm b");
}

var anObject = new AClass();
anObject.a();
anObject.b();