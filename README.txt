
General Approach:
	Whenever we call to the function moreSimplify, it starts with a recursive regular simlification to the current Expression, after that
	the function calls in a recursive method to the function moreSimlify on the left and the right Expressions, the stop condition is when the
	recursive calls gets to the var and num classes, then the function return the current Expression.
1. Plus:
	a. (x + ( 2x + 3)) -> (3x + 3), (x + (x+3)) -> (2x + 3), (x +(2x-1)) -> (3x -1), 
	Approach:
	 if the left Expression is a Var the function calls to the function simplifyIfLeftIsVar, checks if right is Var, Mult, Plus or Minus:
	 Var - the function return a new Mult (x + x) -> 2x.
	 Mult - the function return a new Mult (x + 2x) -> 3x
	 Plus -  the function check if the left or the right Plus Expression is a Var or mult.
	 Minus - the function check if the left or the right Minus Expression is a Var or mult.
	b. (2x+x) -> 3x, (2x + 2x) -> 4x, (2x +(2x +3) ->(4x+3), (2x+(x+3))-> (3x+3).
	Approach:
	if the left Expression is a Mult the function calls to the function simplifyIfLeftIsMult, check if right is Var Mult Plus or Minus:
	 Var - the function return a new Mult (2x + x) -> 3x.
	 Mult - the function return a new Mult (2x + 2x) -> 4x
	 Plus -  the function check if the left or the right Plus Expression is a Var or mult.
	 Minus - the function check if the left or the right Minus Expression is a Var or mult.
	c. (3+(3+x))-> (x+6), (3+(3-x))->(6-x)
	Approach:
	 if the left Expression is a Num the function calls to the function simplifyIfLeftIsNum, check if right is Var Mult Plus or Minus:
	  Num - the function return a new Num (1 + 2) -> 3.
	  Plus -  the function checks if the left or the right Plus Expression is a Num.
	  Minus - the function checks if the left or the right Minus Expression is a Num.
2. Minus:
	a. (x-(2x+3)->(-(x+3)), (x-(x+3))->(-3),  (x-(2x-3))->(3-x),  (x-(x-3))->3
	Approach:
	 if the left Expression is a Var the function calls to the function simplifyIfLeftIsVar, checks if right is Var Mult Plus or Minus:
	 Var - the function return a new Mult (x - x) -> 0.
	 Mult - the function checks if the left is Mult (x - (2x-3)) -> (-(x+3)) if the right is mult (x - (3-2x))->(3x-3)
	 Plus -  the function checks if the left or the right Plus Expression is a Var or mult and works like the lines above.
	 Minus - the function checks if the left or the right Minus Expression is a Var or mult and works like the lines above.
	b. (2x-x)->x, (4x-2x)->(2x), (5x-7x)-> (-2x), (4x -(2x+3))->(2x-3), (2x - (4x+3))->(-(2x+3)),
    	(2x-(3+4x))-> (-(2x+3)), (3x-(x+3))-> (2x-3), (4x -(2x -3))->(2x+3), (2x -(3-2x))-> (4x-3)
	Approach:
	if the left Expression is a Mult the function calls to the function simplifyIfLeftIsMult, checks if right is Var Mult Plus or Minus:
	 Var - the function return a new Mult (2x - x) -> x.
	 Mult - the function checks if the left is Mult (x - (2x-3)) -> (-(x+3)) if the right is mult (x - (3-2x))->(3x-3) and if the left mult bigger or smaller than the right Mult
	 Plus -  the function checks if the left or the right Plus Expression is a Var or mult and works like the lines above.
	 Minus - the function checks if the left or the right Minus Expression is a Var or mult and works like the lines above.
	c. (1-(2+x))->(-(x+1)), (1-(x+2))->(-(x+1)), (3-(x+2))->(1+x), (1-(2-x))->(x-1), (3-(2-x))->(x+1), (1 - (x-1))->(2-x)
	Approach:
	 if the left Expression is a Num the function call to the function simplifyIfLeftIsNum, check if right is Var Mult Plus or Minus:
	  Num - the function check if the left Num bigger or smaller than the right num and return a new num (2 - 1) -> 1.
	  Plus -  the function checks if the left or the right Plus Expression is a Num.
	  Minus - the function checks if the left or the right Minus Expression is a Num.
3. Pow:
	a. (x^1.0)-> x
	Approach:
	 the function checks if the right Expression is Num and the value is 1.
	b. (x^0.0)-> 1
	Approach:
	 the function checks if the right Expression is Num and the value is 0.
	c. ((x^y)^z) -> (x^(y*z))
	Approach:
	 the function checks if the left Expression is a Pow and returns a new Pow with power Mult.
	d. (x^(-y))->(1.0/(x^y))
	Approach:
	 the function checks if the Power is negative.
4. Neg:
	(-(-x))-> x
	Approach:
	the function checks if the inner Expression is a neg and if so, return the positive inner Expression.
5. Div:
	((x^y)/(x^z)) -> (x^(y-z))
	Approach:
	the function checks if the left and the right Expressions are Pow , if so, check if the left inner left Expression String is equal to the right inner left String if so, return new Pow with Minus power.
6. Mult:
	a. ((2*x)*x)->(2*(x^2)), ((2*x)*(2x))->(4*(x^2)), (2x*(x+2))-> ((2*(x^2)+((4*x)),
    	(2x*(2x+2))-> ((4*(x^2)+((4*x)),  (2x*(x-2))-> ((2*(x^2)-((4*x)), (2x*(2x-2))-> ((4*(x^2))-((4*x))
	Approach:
     if the left Expression is a Mult the function call to the function simplifyIfLeftIsMult, check if right is Var Mult Plus or Minus: 
	 Var - the function return a new Pow (2x * x) -> (2*(x^2)).
	 Mult - the function checks if the left is Mult((2*x)*(2*x))-> (4*(x^2)).
	 Plus -  the function checks if the left or the right Plus Expression is a Var or mult and works like the lines above and checks if the other Expression is a num.
	 Minus - the function checks if the left or the right Minus Expression is a Var or mult and works like the lines above and checks if the other Expression is a num.
	b. (x*x)-> (x^2), (x*2x)->(2*(x^2)), (x*(x+2))-> ((x^2)+2*x), (x*(x-2))-> ((x^2)-2*x)
	Approach:
	if the left Expression is a Var the function calls to the function simplifyIfLeftIsVar, checks if right is Var Mult Plus or Minus:
	 Var - the function return a new Pow (x * x) -> (x^2).
	 Mult - the function return a new Pow (x*(2*x)) -> (2*(x^2))
	 Plus -  the function checks if the left or the right Plus Expression is a Var or mult and works like the lines above and checks if the other Expression is a num.
	 Minus - the function checks if the left or the right Minus Expression is a Var or mult and works like the lines above and checks if the other Expression is a num.