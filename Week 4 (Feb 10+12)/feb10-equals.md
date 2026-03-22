# Source: feb10-equals.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Feb 10
Inheritance – equals method

--- PAGE 2 ---
class Test {
   int m(int x) {…}
   float m(int y) {…}
}
class Test {
   int m(int x) {…}
   int m(float y) {…}
}
CS 213 2/10/26                     
Method Overloading/Overriding
Error
Overloaded method m 
Overloaded method m 
Two or more methods in a class are overloaded if they have the
same name but different signatures 
class Test {
   int m(int x) {…}
   float m(float y) {…}
}
A method in a subclass has the same signature as in the superclass
signature = name + params (return type NOT included in signature)
Method OVERRIDING:
Method OVERLOADING: 
     Two or more methods in a class have the same name but different 
number, types, or sequences of parameters
Sesh Venugopal
2

--- PAGE 3 ---
Implementing equals – Rookie Version 
CS 213 2/10/26                     
Sesh Venugopal
3

--- PAGE 4 ---
Implementing equals – Rookie Version 
CS 213 2/10/26                     
Rookie attempt to implement equals (e.g. in Point):
public boolean equals(Point p) {
return x == p.x && y == p.y;
}
True
cp.equals(cp); // ?
Point cp = 
   new ColoredPoint(3,4,”black”);
Ok, inherited equals(Point p) 
in ColoredPoint is called
Sesh Venugopal
4
Static type Point of argument sent in 
matches static type Point of parameter
Point cp = ...
1
compile time
Dynamic type ColoredPoint argument at run time 
matches static type Point parameter
(Point references ColoredPoint)
... = new ColoredPoint(..)
2
run time

--- PAGE 5 ---
Implementing equals – Rookie Version 
CS 213 2/10/26                     
Rookie attempt to implement equals (e.g. in Point):
public boolean equals(Point p) {
return x == p.x && y == p.y;
}
FALSE!!
cp.equals(s); // ?
String s = “(3,4)”;
Since the argument String type does not match expected Point type parameter,
the inherited Object equals(Object o) is called instead!!! (Parameter Object 
can reference any object type)
equals(Point p) does NOT override Object class’s 
equals(Object o)
The Object class’s equals(Object o) compares memory addresses, and returns 
true if addresses of the compared objects are the same, i.e. they are the exact same 
object. Otherwise, it will return false, no matter the type of the argument sent in. 
WHY IS IT NOT A COMPILER ERROR??
Sesh Venugopal
5

--- PAGE 6 ---
Implementing equals – Rookie Version
CS 213 2/10/26                     
Rookie attempt to implement equals (e.g. in Point):
public boolean equals(Point p) {
return x == p.x && y == p.y;
}
FALSE!!
p.equals(op); // ?
The inherited Object equals(Object o) is called!!!
Moral of the story: You MUST override Object equals(Object o)
Reason: the STATIC type of sent argument  op is Object, which
matches the Object parameter type of inherited equals
Object op = new Point(3,4);
Point p = new Point(3,4);
Sesh Venugopal
6

--- PAGE 7 ---
Overriding equals
CS 213 2/10/26                     
Boiler-plate way to override equals:
Header must be same as in Object class
1
Check if actual object (runtime) is of
type Point, or a subclass of Point
2
Must cast to Point type before referring 
to fields of Point
3
Last part is to implement equality as appropriate
(here, if x and y coordinates are equal)
4
public class Point {
   int x,y;
   ...
   ...
}
if (o == null || !(o instanceof Point)) {
return false;
}
Point other = (Point)o;
return x == other.x && y == other.y;
public boolean equals(Object o) {
}
Sesh Venugopal
7

