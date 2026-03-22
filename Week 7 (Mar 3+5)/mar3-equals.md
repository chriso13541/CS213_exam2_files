# Source: mar3-equals.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Mar 3
Inheritance – equals method 
(Continued)

--- PAGE 2 ---
Implementing equals – Pro Version
CS 213 3/3/26                     
Sesh Venugopal
2

--- PAGE 3 ---
Overriding equals
CS 213 3/3/26                     
Boiler-plate way to override equals (e.g. Point):
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
3

--- PAGE 4 ---
CS 213 3/3/26                     
public class Point {
int x,y;
. . . 
public boolean equals(Object o) {
if (o == null || !(o instanceof Point)) { return false; }
Point other = (Point)o;
return x == other.x && y == other.y
}
}
Calling the Point equals method
Single Version: Overriding equals
Point p = new Point(3,4);
p.equals(p); // ? True
True
False
False
p.equals(cp); // ?
Point cp = 
new ColoredPoint(3,4,”black”);
p.equals(p2); // ?
Point p2 = new Point(4,5);
p.equals(s); // ?
String s = “(3,4)”;
Sesh Venugopal
4

--- PAGE 5 ---
CS 213 3/3/26                     
equals overload + override (both versions present)
public class Point {
int x,y;
. . .
public boolean equals(Object o) {
if (o == null ||
(!(o instanceof Point)) { 
return false; 
}
Point other = (Point)o;
return x == other.x && 
y == other.y
}
public boolean equals(Point p) {
if (p == null) {
return false; 
}  
return x == p.x && y == p.y
}
}
Point p = new Point(3,4);
Object o = new Object();
Object op = new Point(3,4);
Given the following setup:
p.equals(o); // ?
p.equals(p); // ?
p.equals(op); // ?
Which method is called in each case,
and what’s the result of the call?:
True
False
True
Sesh Venugopal
5

--- PAGE 6 ---
CS 213 3/3/26                     
equals overload + override
public class Point {
int x,y;
. . .
public boolean equals(Object o) {
if (o == null ||
(!(o instanceof Point)) { 
return false; 
}
Point other = (Point)o;
return x == other.x && 
y == other.y
}
public boolean equals(Point p) {
if (p == null) {
return false; 
}  
return x == p.x && y == p.y
}
}
With the same setup as before:
op.equals(o); // ?
op.equals(op); // ? 
op.equals(p); // ?
Which method is called in each case,
and what’s the result of the call?:
False
True
True
Point p = new Point(3,4);
Object o = new Object();
Object op = new Point(3,4);
[  Same as  p.equals(o) ]
[  Same as  p.equals(op) ]
[  However,  p.equals(p) ]
Sesh Venugopal
6

--- PAGE 7 ---
Here are the rules for 
how it all works …
CS 213 3/3/26                     
(Applies broadly, not 
just to equals)
Given: Point overrides equals(Object) 
and overloads it with equals(Point)
Sesh Venugopal
7

--- PAGE 8 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What rules determine which method is called?
A. First, the COMPILER determines the signature of the method that will be called:
1. Look at the STATIC type of the object (“target”) on which method is called. 
Say this type/class is X
Object o = new Object();
Point p = new Point(3,4);
Object op = new Point(3,4);
p.equals(o); 
p.equals(p);
p.equals(op); 
Static type of
p is Point
op.equals(o); 
op.equals(p);  
op.equals(op);  
Static type of
op is Object
Sesh Venugopal
8

--- PAGE 9 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What rules determine which method is called?
A. First, the COMPILER determines the signature of the method that will be called:
2. In the class X, find a method whose name matches the called 
method, and whose parameters most specifically match the STATIC 
types of the arguments at call
p.equals(o); 
p.equals(p);
p.equals(op); 
Static type of
p is Point
Object o = ...
Point p = ...
Object op = ...
public class Point {
... 
public boolean 
equals(Object o) { ... }
public boolean 
equals(Point p) { ... }
}
e.g. X is Point
Point->Object is also a legit
match, but Point->Point wins
because it is more specific
static type of
o is Object
Sesh Venugopal
9

--- PAGE 10 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What rules determine which method is called?
A. First, the COMPILER determines the signature of the method that will be called:
2. In the class X, find a method whose name matches the called 
method, and whose parameters most specifically match the static 
types of the arguments at call
op.equals(o); 
op.equals(p);  
op.equals(op);  
Static type of
op is Object
Object has a single equals
method that matches all 
of these calls 
e.g. X is Object
Sesh Venugopal
10

--- PAGE 11 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What rules determine which method is called?
Point p = new Point(3,4);
p.equals(o); 
p.equals(p);
p.equals(op); 
Static type of
p is Point
Dynamic type of p is Point
Point defines equals(Object) 
as well as equals(Point), 
which match with the respective statically 
bound method signatures
B. At run time, the actual “target” object (dynamic type), 
or its superclass chain is searched for the pre-determined signature, 
and the matching method executed
Sesh Venugopal
11

--- PAGE 12 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What rules determine which method is called?
Point p = new Point(3,4);
p.equals(o); 
p.equals(p);
p.equals(op); 
Static type of
p is Point
Dynamic type of p is Point
What if Point did NOT override equals(Object) inherited from Object?
superclass Object has 
equals(Object)
B. At run time, the actual “target” object (dynamic type), 
or its superclass chain is searched for the pre-determined signature, 
and the matching method executed
Bad news: original Object equals is 
called, result is false, even though
both points are are (3,4)!!
Sesh Venugopal
12

--- PAGE 13 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What rules determine which method is called?
Object op = new Point(3,4);
op.equals(o); 
op.equals(p);
op.equals(op); 
Static type of
op is Object
Dynamic type of op is Point
Point defines equals(Object) 
which matches with the statically 
bound methods, so gives correct results
B. At run time, the actual “target” object (dynamic type), 
or its superclass chain is searched for the pre-determined signature, 
and the matching method executed
Sesh Venugopal
13

--- PAGE 14 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What rules determine which method is called?
Object op = new Point(3,4);
op.equals(o); // ?  
op.equals(p); // ?
op.equals(op); // ? 
Static type of
op is Object
Dynamic type of op is Point
What if Point did NOT override equals(Object) inherited from Object?
Bad news: result is false, even though
both objects are (3,4)!!
All these calls would be to the 
Object version of equals
False
False
True
B. At run time, the actual “target” object (dynamic type), 
or its superclass chain is searched for the pre-determined signature, 
and the matching method executed
Sesh Venugopal
14

--- PAGE 15 ---
CS 213 3/3/26                     
Method Overloading/Overriding
Static and Dynamic Types
What if the inherited equals(Object) is not overridden,  
and only equals(Point) is coded?
The call op.equals(p) will result in false,
which fails the requirement of (3,4) being equal to (3,4), 
even if the point objects are physically different
So, the inherited equals(Object) must be overridden
Is it sufficient to only override the inherited equals(Object), 
and not code an equals(Point) method?
Yes
Is it inadvisable to have both?
Yes, it leads to avoidable confusion, so removing equals(Point) is 
clearer/unambiguous/better design
Sesh Venugopal
15

