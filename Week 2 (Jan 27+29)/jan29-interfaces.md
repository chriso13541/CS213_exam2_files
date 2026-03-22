# Source: jan29-interfaces.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Jan 29
Interfaces

--- PAGE 2 ---
Comparing for inequality
CS 213 1/29/2026

--- PAGE 3 ---
Comparing for inequality in a library module
CS 213 1/29/2026
public class Searcher {
   ...
   public static<T> boolean 
   binarySearch(T[] list, T target) {
       ... 
       list[index].___?___target 
     
       ...
   }
   ...
}
               
    
How to compare for inequality? All we know
Is T is some Object, but Object does not 
define an inequality comparison method
Want to somehow specify that Ts are not any objects,
but only those objects that have a known inequality 
comparison method
AND, this specification MUST be checkable by compiler, so that (a) 
our binarySearch will compile, and 
(b) the client code’s call to this method will be guaranteed to send 
in required type of object (with the known comparison method)
Note the syntax used for a static method that 
accepts generically typed parameters

--- PAGE 4 ---
How to specify a T type with inequality method?
CS 213 1/29/2026
public class Searcher {
   ...
   public static<T> boolean 
   binarySearch(T[] list, T target) {
       ... 
       list[index].___?___target 
     
       ...
   }
   ...
}
               
    
A class is a user-defined type, e.g. Point and ColoredPoint 
are types introduced by the program, which can be checked
by the compiler (and appropriately matched at run time)
But we (library designer) can’t implement logic for a new type 
instead of T that has, say, a compareTo method because the logic 
depends on the actual type of object that matches the generic 
type T: different matching objects would need different 
compareTo implementations (e.g. comparing strings is different 
to comparing points)

--- PAGE 5 ---
How to specify a T type with inequality method?
CS 213 1/29/2026
public class Searcher {
   ...
   public static<T> boolean 
   binarySearch(T[] list, T target) {
       ... 
       list[index].___?___target 
     
       ...
   }
   ...
}
               
    
Solution is to make like we are defining a new class (type), with 
an inequality method, but stop short of actually implementing 
the method body – this is an INTERFACE
Then it’s up to the client to implement a matching class and fill in the 
compareTo body as appropriate (String class implements this interface)
e.g. java.lang.Comparable interface, which defines a compareTo method,
without a body:
public interface Comparable<T> {
    int compareTo(T o);
}   
No curly braces!!
Generic
Object of type T is compared 

--- PAGE 6 ---
What interface to use with our binary 
search method?
CS 213 1/29/2026

--- PAGE 7 ---
What interface to use for T in binarySearch method?
CS 213 1/29/2026
public class Searcher {
   ...
   public static<T> boolean 
   binarySearch(T[] list, T target) {
       ... 
       list[index].___?___target 
     
       ...
   }
   ...
               
    
We have the option of using any of the interfaces defined in Java,
or roll our own if none of those fits our need
In our Searcher example, Comparable would be a perfect fit
public class Searcher {
   ...
   public static <Comparable<T>> boolean 
   binarySearch(Comparable<T>[] list, Comparable<T> target) {
       ... 
       list[index].compareTo(target) 
       ...
   }
   ...
WILL NOT COMPILE
(not proper generic type syntax)

--- PAGE 8 ---
CS 213 1/29/2026
How to specify that binarySearch expects 
Comparable<T> type objects?
public class Searcher {
   ...
   public static <T extends Comparable<T>> 
   
   boolean binarySearch(T[] list, T target) {
       ... 
       list[index].compareTo(target) 
       ...
   }
   ...
               
    
Type T stands for a class that implements
the java.lang.Comparable<T> interface,
OR extends a class (to any number of levels down
the inheritance chain) that implements the
java.lang.Comparable<T> interface
class X implements Comparable<X>
class Y extends X
class Z extends Y
X, Y, and Z will all match T

--- PAGE 9 ---
CS 213 1/29/2026
Objects that can match binarySearch 
requirement of  T extends Comparable<T>
public class Point implements Comparable<Point> {
   ...
   public int compareTo(Point other) {
       
       int c = x – other.x;
       if (c == 0) {  
          c = y – other.y;
       }
       return c;
   }
   ...
               
    
Type Point is not just any class, but one that 
implements the java.lang.Comparable<Point> 
interface
Since Point implements Comparable<Point>  it MUST 
implement the compareTo method specified by the 
Comparable interface, with Point as the parameter. 
Otherwise, there will be compile-time error.
On the other hand, if Point implements the compareTo method specified 
by the Comparable interface but omits implements Comparable<T>  then it 
would NOT match T extends Comparable<Point> and it can’t be sent 
as an argument to binarySearch

--- PAGE 10 ---
CS 213 1/29/2026
Objects that can match binarySearch 
requirement of  T extends Comparable<T>
public class ColoredPoint extends Point {
   ...
   public int compareTo(Point other) { // Inherited
       
       int c = x – other.x;
       if (c == 0) {  
          c = y – other.y;
       }
       return c;
   }
   ...
               
    
Type ColoredPoint is not just any class, 
but one that extends a class (Point) that
implements java.lang.Comparable<Point>
By extending Point, ColoredPoint implicitly 
implements the Comparable<Point> interface, 
equivalent to:
public class ColoredPoint extends Point implements Comparable<Point>

--- PAGE 11 ---
CS 213 1/29/2026
Implicit/Explicit Interface

--- PAGE 12 ---
CS 213 1/29/2026
Implicit interface – Public members of a class
The term “interface” GENERALLY refers to the means by which an 
object can be manipulated by its clients – in this sense the public fields 
and methods of an object comprise its implicit interface. 
For example, public methods push, pop, isEmpty (as well as constructors) in a 
Stack implicitly define its interface – these methods/constructors will be used 
by clients to create and manipulate stacks

--- PAGE 13 ---
CS 213 1/29/2026
Explicit Interface
public interface Comparable<T> {
   int compareTo(T o);
}
    
The (generic) Comparable interface is defined in java.lang package
For method compareTo,
keywords  public and abstract 
are omitted by convention 
(redundant if written)
Prescribes a single, compareTo method, 
but there is no method body, just a 
semicolon terminator
Java provides a way (keyword interface) to define an explicit interface that can be 
implemented (keyword implements) by classes
public interface I { . . . }
public class X implements I { . . . }

--- PAGE 14 ---
CS 213 1/29/2026
Interface Properties

--- PAGE 15 ---
CS 213 1/29/2026
Properties of interfaces:
1. An interface defines a new type that is tracked by the compiler
2. All fields in an interface are constants: implicitly public, static, and final
3. Originally, all interface methods were implicitly public and abstract 
(no method body)
6. When a class implements an interface, it must implement every single
     abstract method of the interface
4. Now interfaces can have default and static methods (fully implemented). 
However, these need to be public 
5. Interfaces can also have fully implemented private methods ( static or 
non static)
7. An interface J can extend another interface I, in which case I is the super 
interface and J is its sub interface

--- PAGE 16 ---
CS 213 1/29/2026
Properties of interfaces - continued:
10. An interface may be generic, but this does not require an implementing 
class to match the generic type with itself – see the ColoredPoint 
example above (class is ColoredPoint but generic-instance type for 
interface is Point)
8.  A class may implement multiple interfaces 
public class X implements I1, I2, I3 { ... }
9. A subclass implicitly implements all interfaces that are implemented by 
its superclass
public class Point implements Comparable<Point> { ... }
public class ColoredPoint extends Point 
          implements Comparable<Point> { ... }
implicit (writing it out is ok too)

--- PAGE 17 ---
CS 213 1/29/2026
Examples of using java.lang.Comparable

--- PAGE 18 ---
public class Point 
  implements Comparable<Point> {
      . . .
      
      public int compareTo(Point other) {
         int c = x – other.x;
         if (c == 0) {
             c = y – other.y;
         }
         return c;
     }
   
}
CS 213 1/29/2026
Using java.lang.Comparable
public static <T extends Comparable<T>> 
   boolean binarySearch(T[ ] list, 
                        T target) {
       ...
       int c = target.compareTo(list[i]);
       ...
   }
}
Array of Point
objects
target 
Point
public class Widget 
  implements Comparable<Widget> {
     . . . 
     
     public int compareTo(Widget other) {
        float f = mass – other.mass;
        if (f == 0) return 0;
        return f < 0 ? -1 : 1;
     }
   
}
Array of Widget
objects
target 
Widget

--- PAGE 19 ---
When to Use Interfaces
CS 213 1/29/2026

--- PAGE 20 ---
Use #1:
 To Make Classes Conform to a Specific 
Role Used in External Context 
CS 213 1/29/2026

--- PAGE 21 ---
CS 213 1/29/2026
Classes – Conform to Specific External Role
Often,
   a specialized role needs to be specified
         for some classes in an application (e.g. comparing for ==, >, <), 
  and given a type name (.e.g. Comparable) 
The type name is the interface name, 
      and the role is the set of interface methods. 
You can think of an interface as
     a filter that is overlaid on a class. 
Depending on the context, 
       the class can be fully itself (class type)
       or can adopt a subset, specialized role (interface type) 

--- PAGE 22 ---
CS 213 1/29/2026
Specialized Role For Classes
public interface Comparable<T> {
    int compareTo(T o);
}
methodM will admit any object,
so long as it is Comparable, and
it knows the admitted object ONLY
as Comparable – that is, the filter is blind
to all other aspects of the object type (X, or
Y, or Z) but the Comparable part
class U
static 
<T extends Comparable<T>> 
void methodM(T c) {
   ...
}
class X implements Comparable<X>
class Y implements Comparable<Y>
class Z implements Comparable<Z>
The implementor of methodM in
class U may call the compareTo method 
on the parameter object c, without knowing 
anything about the argument except that 
it will be guaranteed to implement 
compareTo 

--- PAGE 23 ---
Use #2:
To define a single type that gathers 
functionality common to classes 
that are not in an inheritance hierarchy
CS 213 1/29/2026

--- PAGE 24 ---
CS 213 1/29/2026
Zebras, Horses and Donkeys can all trot, gallop, and snap (common behavior)
public interface Equine { 
   void trot();
   void gallop();
   void snap();
 }
Type for Classes with Common Behavior
In a simulation with many instances of each, you may want to evoke one or more of these 
behaviors in a randomly selected instance or group, without regard to what exact 
specimen is targeted – grouping these behaviors under a new type meets this need
class Zebra implements 
      Equine
class Horse implements 
      Equine
class Donkey implements 
      Equine

--- PAGE 25 ---
CS 213 1/29/2026
A collection (e.g. ArrayList) might have a combination of zebras, donkeys and horses
Polymorphism using interface type
ArrayList<Equine> equines = new ArrayList<>();
equines.add(new Zebra());  
equines.add(new Horse());
...
Now you can apply any of the common behaviors to instances of the collection, without 
regard to the actual type of animal (no need to check what actual type it is):
for (Equine eq: equines) {
   
  eq.trot();
   ...
}
actual behavior is executed on runtime instance 
This is polymorphism via an interface type – common behavior executed on
objects with same interface (static) type, but the way the behavior is executed
is automatically determined by binding to the run time type (“shape” of object
changes automatically, hence poly “morph” ism.)

