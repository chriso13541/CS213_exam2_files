# Source: feb10-static-design.pdf
--- PAGE 1 ---
CS 213 : Software Methodology
Spring 2026
Sesh Venugopal
Feb 10
Why Static? Design Considerations

--- PAGE 2 ---
Static for Non-Object-Oriented 
Programming
CS 213  2/10/26
Sesh Venugopal
2

--- PAGE 3 ---
Static for Non Object-Oriented Programming
Calling the main method directly on the class makes the design NOT object-oriented:
Object orientation implies that there is an object or an instance of which a field is accessed,
or on which a method is executed
Suppose you want to write a program that just echoes whatever is typed in:
public class Echo {
    public static void main(String[] args) 
    throws IOException {
        BufferedReader br = new BufferedReader(
                               new InputStreamReader(System.in));
        System.out.print("> ");
        String line = br.readLine();
        System.out.println(line);
    }
}  
This program works without having to create any Echo objects – the Virtual Machine
executes the main method directly on the Echo class (not via an Echo object) because
the main method is declared static
CS 213  2/10/26
3
Sesh Venugopal

--- PAGE 4 ---
Static Methods for “Functions”
CS 213  2/10/26
Sesh Venugopal
4

--- PAGE 5 ---
Static Methods for “Functions”
An extreme use of static methods is in the java.lang.Math class in which every
single method is static – why?
The Math methods can be called directly on the class, for example:
public class Math {
   public static float abs(float a) {...}
   ...
   public static int max(int a, int b) {...}
   ...
   public static double sqrt(double a) {...}
   ...
}
The reason is that every method implements a self-sufficient mathematical function
with inputs and result: once the function returns, there is nothing to be kept around 
(as in a field of an object) for later recall/use. 
In other words there is no state to be maintained
In fact, you CANNOT create an instance of the Math class  - “instantiation” is not allowed
double sqroot = Math.sqrt(35);   
CS 213  2/10/26
5
Sesh Venugopal

--- PAGE 6 ---
Static Fields for Constants
CS 213  2/10/26
Sesh Venugopal
6

--- PAGE 7 ---
Static Fields for Constants
public class Math {
   ...
   public static final double E ...
   public static final double PI ...
   ...
}
Aside from the utility methods, the Math class also has two static fields to store 
the values for the constants E (natural log base e) and PI (for the constant pi)
Since the constants are static, they can be accessed via class names (without objects):
double area = Math.PI * radius * radius; 
Math is a “utility” class because all methods are static or “utility” methods – the class is
just an umbrella under which a whole lot of math functions are gathered together
Math.PI = Math.PI * 2; 
CS 213  2/10/26
7
Sesh Venugopal
final means it can be never be 
assigned to afterward, so 
initialization MUST be done at 
declaration time 
(hence, a constant) 

--- PAGE 8 ---
Static Fields for Sharing Among 
Instances
CS 213  2/10/26
Sesh Venugopal
8

--- PAGE 9 ---
Consider a class for which only a limited number of 
instances are allowed.
CS 213  2/10/26
9
For instance, some kind of ecological simulation that 
populates a forest with tigers – want to put a limit on 
number of tigers
Need to keep track of current count, IN THE TIGER CLASS
And whenever a Tiger instance is no longer in play (say a 
Tiger dies or is transported to another location), the count 
of tigers has to be decremented
Whenever an attempt is made to create a new Tiger 
instance, count has to be checked, and if under limit, then 
count has to be incremented
Static Fields for Sharing Among Instances
Sesh Venugopal

--- PAGE 10 ---
Tiger – Static field count 
CS 213  2/10/26
10
public class Tiger {
   
 
   
}
public static final int MAX_COUNT=10;
public static final int MAX_MASS=2000;
private static int count=0;
public Tiger(int mass) 
if (count == MAX_COUNT) {
throw new Exception(“Max count exceeded”); 
}
if (mass < 0 || mass > MAX_MASS) {
throw new IllegalArgumentException(“Unacceptable mass”); 
}
count++;
}
throws Exception {
This is a “checked” exception, so the
constructor must declare a throws
Class property, shared by instances
“Unchecked/runtime” exception, no
throws declaration needed (but it is a 
subclass of Exception, so is covered
by the throws Exception declaration)
...
Sesh Venugopal

--- PAGE 11 ---
Tiger – Static count field shared by instances
CS 213  2/10/26
11
public class Tiger {
   
 
      ...
}
public static final int MAX_COUNT=10;
public static final int MAX_MASS=2000;
private static int count=0;
public Tiger(int mass) 
count++;
}
throws Exception {
public static int getCount() { 
return count;
}
A client would want to know how many 
Tiger instances are around BEFORE 
creating (or not) another instance
Since count is private and static, it must 
be accessed via a method that is a 
property of the class, not of an instance, 
i.e. the method is static.
Sesh Venugopal

--- PAGE 12 ---
Static: Access
• Static fields and methods are accessed via the class name, or if they are 
mixed in with instance fields and methods, they may be accessed via an 
instance of the class: 
public class Application {
    public static void main(String[] args) 
    throws Exception {
        int m = Tiger.MAX_MASS;   // use class name to get MAX_MASS
        Tiger t = new Tiger(m-100); 
        
        int c = t.getCount();    // using instance to get count
        ...
    }
}
CS 213  2/10/26
12
Since the Tiger constructor throws a 
checked exception, the calling method, 
main, must either catch it, or throw it
Sesh Venugopal
You may use an instance to access a static field 
or method, but it is not good practice

--- PAGE 13 ---
Sesh Venugopal
Static: Access
• The part of the application you are working on may not be the only one creating 
Tiger instances. So, even for the first instance you want to create, you need to 
know count before you decide whether you can create another instance or not.
int currCount = Tiger.getCount();  // use class name 
        
        if (currCount < Tiger.MAX_COUNT) {
 
       Tiger t= new Tiger(...);
            ...
        } else {
           . . . // do whatever
        }
Always use class name to get at static members of a class, even in situations 
where you can use an instance, so that your code adheres to the design 
implication of static
CS 213  2/10/26
13
Sesh Venugopal

--- PAGE 14 ---
Global Storage – Utility Class
CS 213  2/10/26
Sesh Venugopal
14

--- PAGE 15 ---
”Global” variables that need to be shared by multiple classes/objects can be housed 
as static fields in a class:
CS 213  2/10/26
15
Class for “Global” Storage
public class Storage {
   static int x;
   static float y;
   static String color=“blue”;
   static float y;
   ...
}
Like the Math class, this is a utility class – every field is static 
If the design choice is to make the fields private, then static getter and setter methods 
can be defined
Sesh Venugopal

--- PAGE 16 ---
Static/Non-static Mix Example: 
Design Choices 
CS 213  2/10/26
Sesh Venugopal
16

--- PAGE 17 ---
Sesh Venugopal
Static/Non-Static Mix: Another Example
• Of the second and third choices, which one is better? Why?
int i = “123”.parseAsInteger();             
Bad design: An instance method should be applicable to ALL instances. But not
all strings are parsable as integers
int i = String.parseAsInteger(“123”);             
• Want to parse a string into an integer, e.g. “123” -> 123 – where to provide this functionality?
int i = Integer.parseInt(“123”);             
Think of converting strings to doubles, floats also –
having all these types of conversions in String would require String to know about
formats of other types, which is NOT its business.
Best to localize custom functionality in the corresponding target (converted type) classes.
CS 213  2/10/26
17
-  Have a String instance method, say, parseAsInteger that returns an int, e.g.
-  Have a String static method, say, parseAsInteger that returns an int, e.g.
-  Have an Integer static method, say, parseInt that returns an int, e.g.
OPTIONS:
Integer.parseInt is better

