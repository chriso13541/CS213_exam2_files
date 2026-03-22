# Source: feb12-uml.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Feb 12
UML Class Diagram

--- PAGE 2 ---
CS 213 2/12/26
UML Diagrams
• UML stands for Unified Modeling Language, which is a 
graphical notation used to express object-oriented design
• There are three kinds of UML diagrams that are used in 
practice:
– Class diagram, used to show classes and the 
relationships between them
– Sequence diagram, used to show sequences of activity 
when methods are invoked on classes
– State diagram, used to represent state-based designs
Sesh Venugopal
2

--- PAGE 3 ---
CS 213 2/12/26
Class Diagram
Rectangle
width
height
xpos
ypos
resize( )
move( )
Rectangle
•A class diagram shows classes and the relationships 
between them
•The simplest way to draw a class is like this:
•Details may be added to the class like this:
Attributes appear below the class 
name, and operations (methods)  
appear below the attributes
Sesh Venugopal
3

--- PAGE 4 ---
CS 213 2/12/26
•
An even greater level of detail 
can be added by specifying the 
type of each attribute, as well 
as type of each parameter and 
return type for each method:
Class Diagram with Attributes
and Methods
•
And the access level (private, 
public, etc.) for each member:
Rectangle
width: int
height: int
xpos: int
ypos: int
resize(w:int; h:int): void
move(x:int, y:int): void
Rectangle
- width: int
- height: int
- xpos: int
- ypos: int
+ resize(w:int; h:int): void
+ move(x:int, y:int): void
Sesh Venugopal
4

--- PAGE 5 ---
CS 213 2/12/26
UML Notation for Access Levels
abcpackage
+ ClassA
   
-  apriv: int
# aprot: int
+ apubl: int
~ apack: int
 
abcpackage
~ ClassB
+ : public
# : protected
~ : package
- : private
Sesh Venugopal
5

--- PAGE 6 ---
CS 213 2/12/26
Class Diagram: Relationships
• Relationships between classes are essentially of three 
kinds: generalization/specialization (super/sub), 
association, and dependency
Window
open( )
close( )
move( )
refresh( )
handleEvent( )
Event
ConsoleWindow
DialogBox
Control
generalization
dependency
association
This example from “The Unified Modeling Language User’s Guide” by Booch, Rumbaugh, Jacobson 
Sesh Venugopal
6

--- PAGE 7 ---
CS 213 2/12/26
Class Diagram: Relationships
• Relationships between classes are represented by various 
kinds of lines
Inheritance
Interface
Implementation
Aggregation
Composition
Bi-directional
Association
Dependency
Uni-directional
Association
Association
Class
Sesh Venugopal
7

--- PAGE 8 ---
CS 213 2/12/26
Generalization (and Interface  Implementation)
• Notation
Subclass
Superclass
<<interface>>
Book
•
Examples
<<interface>>
Interface
Implementation
Abstract Class
ConcreteSubclass
PrintBook
PDFBook
<<interface>>
eBook
Shape
Polygon
Rectangle
Triangle
<<interface>>
Subinterface
<<interface>>
SuperInterface
abstract methods are
also italicized
Sesh Venugopal
8

--- PAGE 9 ---
CS 213 2/12/26
Association and Multiplicity
• An association is a general relationship between two 
classes, with options for name of association, and 
number of instances (multiplicity) of participation of each 
class
ClassA
ClassB
n
m
name
role
role
Each instance of ClassA is associated 
with m instances of ClassB
Each instance of ClassB is associated 
with n instances of ClassA
9
Sesh Venugopal

--- PAGE 10 ---
CS 213 2/12/26
Association and Multiplicity: Examples
Student
Course
Instructor
advisee
advisor
1
Flight
Passenger
Airplane
1
1
takes
• Multiplicity can also be specified 
as one of the values an 
enumerated set such as 1, 3..5
*
1
teaches
1
1..*
1..*
*
(0 or more)
10..*
(10 or more)
10
Sesh Venugopal

--- PAGE 11 ---
CS 213 2/12/26
Aggregation and Composition
•
Aggregation is a special kind of association that represents a has-a 
or whole-parts relationship – the whole is the aggregate class 
instance, and the parts are the component class instances
•
Composition is a stronger form of aggregation, in which the 
components live or die with the containing class (the whole)—a 
deletion of the whole will lead to the deletion of the parts (an 
object may be a part of only one composite at a time)
ClassA
ClassB
Each instance of ClassA 
aggregates one or more
 instance of ClassB
Each instance of ClassA 
is composed of one or more
instance of ClassB
ClassA
ClassB
11
Sesh Venugopal

--- PAGE 12 ---
CS 213 2/12/26
Aggregation and Composition: Example
1..*
Building
Office
Employee
Elevator
1
1..*
1
1
Job
The multiplicity “1” is redundant because 
a component cannot be in more than one
container in a composition relationship
*
1..*
1..*
Employee is a “first class” object - it 
has an independent existence. Even 
if the Office object goes away, 
Employee objects will still remain. 
Hence aggregation instead of
composition.
12
Sesh Venugopal

--- PAGE 13 ---
Aggregation and Composition
CS 213 2/12/26
public class Building {
    public class Elevator {
       ...
    }
    public class Office {
       private ArrayList<Employee>
             employees;
       ...
    } 
    private Elevator[] elevators;
    private Office[] offices;
    public Building(int e_num, 
                    int o_num) {
         elevators = new Elevator[e_num];
         offices = new Office[o_num];
         ...
    }
    ...
}
         
One possible implementation of a 
composition is to define the composed 
object (e.g. elevator/office) as an inner 
class of the composing object (e.g. 
building)
Deleting the whole must result in 
deleting the parts – implementation 
wise this applies to languages that do
NOT have garbage collection (e.g. C++) 
because memory for components must 
be explicitly freed
Elevator and Office are defined as non-
static inner classes – creating an object of 
either requires a Building object
13
Sesh Venugopal

--- PAGE 14 ---
CS 213 2/12/26
Polygon
Point
From “UML Distilled” By Martin Fowler with Kendall Scott
Style
color
isFilled
Circle
radius
*
1
1
1
3..*
*
All associations have a directional arrow: what does this mean?
{ordered}
Example of Aggregation and Composition
14
Sesh Venugopal
Point participates in two composition relationships?
(Remember that the contained class in a composite relationship 
cannot also be contained in another class.)

--- PAGE 15 ---
CS 213 2/12/26
Polygon
Point
Circle
radius
1
3..*
{ordered}
public class Polygon {
   // implementation must
   // ensure at least 3 points
   // in sequence 
   private Point[] points;
   ... 
}
         
public class Circle {
   private Point center;
   private int radius;
   ... 
}
         
public class Point {
   private int x,y;
   ... 
}
         
Point participates in two compositions. The 
qualifier (design wise) is that the Point instance 
in the Circle is different from any of the Point 
instances in Polygon. 
So that if a Circle instance, or Polygon instance, 
is no longer active, the contained Point 
instances can be safely destroyed.
However, in Java, which implements automatic 
garbage collection, this restriction does not 
apply. An instance of Point used in Circle can 
also be used in Polygon: if the Polygon instance 
goes out of scope, only the contained 
instances of Point that DO NOT have a 
reference from elsewhere will go out of scope 
as well. (If an instance is referred from a Circle 
instance, it will not be garbage collected.)
Class participates in multiple compositions
15
Sesh Venugopal

--- PAGE 16 ---
CS 213 2/12/26
The meaning of directed associations
Polygon
Point
Style
color
isFilled
Circle
radius
*
1
1
1
3..*
*
{ordered}
Polygon “knows” about its 
Style and Point associations 
(and so they are fields), but 
Style and Point do not know 
about their Polygon 
associations 
Circle “knows” about its 
Style and Point associations 
(so, fields), but Style and 
Point do not know about 
their Circle associations 
public class Polygon {
   // implementation must
   // ensure at least 3 points
   // in sequence 
   private Point[] points;
   private Style style;
   ... 
}
         
public class Circle {
   private Point center;
   private int radius;
   private Style style;
   ... 
}
         
public class Point {
   private int x,y;
   ...
   // NO REFERENCE TO
   // Polygon or Circle 
}
         
public class Style {
   private Color color;
   private boolean isFilled;
   ...
   // NO REFERENCE TO
   // Polygon or Circle 
}
         
16
Sesh Venugopal

