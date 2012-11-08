(function foo() {
	var a = 'a';
	var ten = 10;
	var abc = 'abc';
	
	ten++;
	throw new Error("that's an error");
	ten++;
}())