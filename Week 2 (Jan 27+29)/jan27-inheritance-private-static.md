# Source: jan27-inheritance-private-static.pdf
--- PAGE 1 ---
CS 213 : Software Methodology
Spring 2026
Sesh Venugopal
Jan 27
Inheritance – Private and Static

--- PAGE 2 ---
CS 213 1/27/2026
Sesh Venugopal
2
Inheritance – Private fields/methods

--- PAGE 3 ---
CS 213 1/27/2026
2
Inheritance - Private Fields
public class Point {
   private int x,y;
   ...
}
     
public class ColoredPoint extends Point {
   
   ...
   public int getX() { // override inherited getX()
      return x;
   }
}
     
COMPILE?
WILL NOT COMPILE
because x is hidden
// x and y inherited but HIDDEN
Sesh Venugopal
3

--- PAGE 4 ---
CS 213 1/27/2026
2
Inheritance - Private Fields
public class Point {
   private int x,y;
   ...
}
     
public class ColoredPoint extends Point {
   // x and y inherited but HIDDEN
   ...  // getX() is NOT overridden
}
     
WILL NOT COMPILE, x is private
4
Inherited getX() method is 
able to access the x field
public class PointApp {
   public static void
   main(String[] args) {
}
     
ColoredPoint cp = new ColoredPoint(4,5,”blue”);     
System.out.println(cp.x); // ?
System.out.println(cp.getX()); // ? 
Sesh Venugopal
4

--- PAGE 5 ---
CS 213 1/27/2026
2
Inheritance - Private Methods
public class Supercl {
   private void mpriv() { 
      System.out.println(“Supercl”);
   }
   public void mpub() {
      mpriv();
   }
   public static void 
   main(String[] args) {
      new Supercl()
         .mpub(); // ? 
   }
}
     
Supercl
Sesh Venugopal
5
public class Subcl extends Supercl {
   public static void 
   main(String[] args) {
      new Subcl()
         .mpub(); // ? 
   }
}
     
Inherited mpub() method calls 
the Supercl method mpriv()
Supercl

--- PAGE 6 ---
CS 213 1/27/2026
2
Inheritance - Private Methods
public class Supercl {
   private void mpriv() { 
      System.out.println(“Supercl”);
   }
   public void mpub() {
      mpriv();
   }
   public static void 
   main(String[] args) {
      new Supercl()
         .mpub(); // ? 
   }
}
     
Supercl
Sesh Venugopal
6
Inherited mpub() method STILL calls 
the Supercl method mpriv()
Supercl
public class Subcl extends Supercl {
   private void mpriv() {
      System.out.println(”Subcl”);
   }
   public static void 
   main(String[] args) {
      new Subcl()
         .mpub(); // ? 
   }
}
     

--- PAGE 7 ---
CS 213 1/27/2026
2
Inheritance - Private Methods
Supercl
Sesh Venugopal
7
The inherited mpub() will still call the 
superclass private method, EVEN if the 
mpriv() method in Subcl is made public
public class Subcl extends Supercl {
   public void mpriv() {
      System.out.println(”Subcl”);
   }
   public static void 
   main(String[] args) {
      new Subcl()
         .mpub(); // ? 
   }
}
     
If mpub is not overridden, it will always call the private method in its (super) class, 
even if the superclass’s private method is overridden and made public in the subclass

--- PAGE 8 ---
CS 213 1/27/2026
2
Inheritance - Private Methods
Subcl
Sesh Venugopal
8
When you override the inherited mpub() 
THEN the public mpriv() method in Subcl 
is called. This will persist even if mpriv() is 
private in the subclass
public class Subcl extends Supercl {
   public void mpriv() {
      System.out.println(”Subcl”);
   }
   
   // override inherited mpub
   
   public void mpub() {
      mpriv();
   }
   public static void 
   main(String[] args) {
      new Subcl()
         .mpub(); // ? 
   }
}
     
Private methods are NOT inherited

--- PAGE 9 ---
CS 213 1/27/2026
Sesh Venugopal
9
Inheritance – Static fields/methods

--- PAGE 10 ---
Inheritance - Static Fields
CS 213 1/27/2026
public class Supercl {
   static int x=2;
}
public class StaticTest {
   public static void main(String[] args) {
   
   
   }
}
2
System.out.println(Supercl.x); // ?
Static fields are inherited
public class Subcl 
extends Supercl { 
}
Sesh Venugopal
10
System.out.println(Subcl.x); // ? 2

--- PAGE 11 ---
Inheritance - Static Fields
CS 213 1/27/2026
public class Supercl {
   static int x=2;
}
public class StaticTest {
   public static void main(String[] args) {
   
   
   }
}
2
System.out.println(Supercl.x); // ?
Sesh Venugopal
11
System.out.println(Subcl.x); // ? 3
public class Subcl 
extends Supercl { 
   static int x=3;
}
Static field with
same name as 
inherited static field x
Static field x in subclass 
HIDES the x inherited from 
Supercl

--- PAGE 12 ---
Inheritance - Static Fields
CS 213 1/27/2026
public class Supercl {
   static int x=2;
}
public class Subcl 
extends Supercl { 
   static int x=3;
   public int getX() {
      return x;
   }
}
Sesh Venugopal
12
public class StaticTest {
   public static void main(String[] args) {
   
   
   }
}
2
System.out.println(Supercl.x); // ?
System.out.println(new Subcl().getX()); // ? 3
Instance method can 
access static fields

--- PAGE 13 ---
Inheritance - Static Fields
CS 213 1/27/2026
public class Supercl {
   static int x=2;
}
public class StaticTest {
   public static void main(String[] args) {
   
   
   }
}
DOES NOT COMPILE
“cannot make static reference to non-static field x”
System.out.println(Subcl.x); // ?
Instance field of same name will HIDE inherited static field, and the inherited static 
field cannot be accessed with class name Subcl
As far as the compiler is concerned, you are trying to access the instance field x 
with the class name, which is not allowed. 
public class Subcl 
extends Supercl { 
   int x=3;
}
Instance (NOT static) field with same 
name as inherited static field x
Sesh Venugopal
13
Static fields/methods can be accessed via an instance – as we saw in slide 12 – 
but instance fields/methods cannot be accessed via class name

--- PAGE 14 ---
Inheritance - Static Fields
CS 213 1/27/2026
public class Supercl {
   static int x=2;
}
public class Subcl 
extends Supercl { 
   int x=3;
   public static int getX() {
      return x;
   }
}
“cannot make static reference to non-static field x” 
– same as before, can’t access instance fields from 
inside a static method
What if we write a static method in Subcl to get at the inherited static x?
NO
WILL THIS COMPILE?
Sesh Venugopal
14

--- PAGE 15 ---
Inheritance - Static Fields
CS 213 1/27/2026
public class Supercl {
   static int x=2;
}
public class Subcl 
extends Supercl { 
   int x=3;
}
public class StaticTest {
   public static void main(String[] args) {
   
   
   }
}
static type
dynamic type
3 – instance field x
2 – inherited static field x !!!
Inherited static fields are statically bound (to reference/declared type), 
NOT dynamically bound (to instance type)
Subcl subcl  = new Subcl();
System.out.println(subcl.x); // ?
Supercl supercl  = new Subcl();
System.out.println(supercl.x); // ?
Sesh Venugopal
15

--- PAGE 16 ---
CS 213 1/27/2026
public class Sorter {
   
   public static void
   sort(String[] names) {
       System.out.println(
         “simple sort”);  
   }
}
public class IllustratedSorter 
extends Sorter {
   
   // override
   public static void
   sort(String[] names) {
       System.out.println(
         “illustrated sort”);
   }
}
sort() is statically bound to p, meaning
since Sorter is the static type of p, 
the sort() method in Sorter is called
Trying to override a static method with 
another static method 
Sorter p = new IllustratedSorter();
p.sort(); // ? 
static type
dynamic type
“simple sort”
Sesh Venugopal
16

--- PAGE 17 ---
CS 213 1/27/2026
public class Sorter {
   
   public static void
   sort(String[] names) {
       System.out.println(
         “simple sort”);
   }
}
Trying to override a static method with 
an instance method
Sesh Venugopal
17
“Instance method cannot override static method sort from Sorter”
COMPILE?
public class IllustratedSorter 
extends Sorter {
   
   // override
   public static void
   sort(String[] names) {
       System.out.println(
         “illustrated sort”);   
   }
}
NO

--- PAGE 18 ---
CS 213 1/27/2026
public class IllustratedSorter 
extends Sorter {
   
   // override
   public static void
   sort(String[] names) {
       System.out.println(
         “illustrated sort”);
   }
}
Trying to override an instance method 
with a static method
Sesh Venugopal
18
“Static method cannot override instance method sort from Sorter”
COMPILE?
public class Sorter {
   
   public static void
   sort(String[] names) {
       System.out.println(
         “simple sort”);
   }
}
NO

