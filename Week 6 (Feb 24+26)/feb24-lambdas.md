# Source: feb24-lambdas.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Feb 24
Lambda Expressions

--- PAGE 2 ---
Example: List Filtering
CS 213 2/24/26                             
Given a list, want to extract a subset of 
items based on some filtering condition
2
Sesh Venugopal

--- PAGE 3 ---
Example: List Filtering
List<Integer> result = 
      new ArrayList<Integer>(); 
for (Integer i: list) {    
    if (i % 2 == 0) {        
       result.add(i);     
    }
} 
return result;
Pick even numbers out of a list
List<Integer> result = 
      new ArrayList<Integer>(); 
for (Integer i: list) {    
    if (i > 10) {        
       result.add(i);     
    }
} 
return result;
Pick numbers > 10 out of a list
There may be other conditions for filtering numbers out of a
list that an application may need to use elsewhere
(e.g. pick multiples of 5, pick primes, etc.)
How to redo this so that we can maintain a single scaffolding
(loop through list and apply condition), and change ONLY the 
actual condition as needed?
CS 213 2/24/26                             
3
Sesh Venugopal

--- PAGE 4 ---
Passing Behavior to Method
But, as of Java 8, there is a way to pass a method through a 
very light object, with simple syntax that makes it appear 
as if we are just passing a function instead of an object
CS 213 2/24/26                             
method(list, function)
function to be
applied to each
member of the list
Setup: Write a method with two parameters:
             the list, and a filtering function
Technically, there’s no way to pass a function (method) 
as a parameter
4
Sesh Venugopal

--- PAGE 5 ---
public List<Integer> 
filter( List<Integer> list, IntPicker picker) { 
   List<Integer> result = new ArrayList<Integer>(); 
   for (Integer i: list) {    
       if (picker.pick(i)) {        
          result.add(i);     
       }
   } 
   return result;
}
Define Behavior in Functional Interface
Start by defining an interface that has only ONE abstract method.
(There may be other methods, so long as they are not abstract.) 
This makes it a functional interface
public interface IntPicker {
   boolean pick(int i);
}
Next, implement the filter method with an instance of the 
functional interface as the second parameter
CS 213 2/24/26                             
5
can have an interface
type for parameter
Sesh Venugopal

--- PAGE 6 ---
Passing function argument : v1
Named interface implementation
For each type of filter, make a named class that implements the interface:
public class EvenPicker 
implements IntPicker {
   public boolean pick(int i) {
      return i % 2 == 0;
   }
}
public class GreaterThan10Picker 
implements IntPicker {
   public boolean pick(int i) {
      return i > 10;
   }
}
Set up a list:
List<Integer> list = Arrays.asList(2,3,16,8,-10,15,5,13);
CS 213 2/24/26                             
List<Integer> evens = filter(list, new EvenPicker());
List<Integer> greaterThan10s = filter(list, new GreaterThan10Picker());
(List and Arrays are in java.util)
Call the filter method:
6
Sesh Venugopal

--- PAGE 7 ---
What is anonymous interface implementation
CS 213 2/24/26                             
7
Sesh Venugopal
public class EvenPicker 
implements IntPicker {
   public boolean pick(int i) 
   {
      return i % 2 == 0;
   }
}
Implementation with class name
IntPicker {
    public boolean pick(int i) 
    {
       return i % 2 == 0;
    }
};
new IntPicker() {
    public boolean pick(int i) 
    {
       return i % 2 == 0;
    }
};
Part 1: Implement the interface without 
class name (anonymous class) 
Part 2: Create new object of the 
anonymous class
Anonymous implementation
Constructors not allowed (falls back to 
default constructor)
Since there is no named class, cannot create
Object later

--- PAGE 8 ---
Passing function argument: v2
Anonymous interface implementation
Write anonymous interface on the fly when calling the filter method:
List<Integer> list = Arrays.asList(2,3,16,8,-10,15,5,13);
CS 213 2/24/26                             
List<Integer> evens = filter(list, 
                             new IntPicker() {
                                 public boolean pick(int i) {
                                    return i % 2 == 0;
                                 }
                             });
List<Integer> greaterThan10s = filter(list, 
                             new IntPicker() {
                                 public boolean pick(int i) {
                                    return i > 0;
                                 }
                             });
8
Sesh Venugopal

--- PAGE 9 ---
Passing function argument: v3
Named Lambda Expression
A lambda expression is essentially a simplified syntax to define the method 
of a functional interface:
(int i) -> i % 2 == 0 // a lambda for IntPicker
Since the method pick of functional interface IntPicker is defined to 
accept an int and return a boolean, the LHS of the expression is the int 
input, and the RHS is the boolean expression for the value to be returned
IntPicker greaterThan10Picker = (int i) -> i > 10;
CS 213 2/24/26                             
9
Sesh Venugopal
IntPicker evenPicker = (int i) -> i % 2 == 0;
LHS
RHS
We can refer to a lambda expression with a variable name of the 
functional interface type (here IntPicker):

--- PAGE 10 ---
Passing function argument: v3
Named Lambda Expression
Call our filter method, sending in the named lambda as parameter:
List<Integer> list = Arrays.asList(2,3,16,8,-10,15,5,13);
List<Integer> evens = filter(list, evenPicker);
List<Integer> greaterThan10s = filter(list, greaterThan10Picker);
CS 213 2/24/26                             
10
Sesh Venugopal
public List<Integer> 
filter( List<Integer> list, IntPicker picker) { 
   List<Integer> result = new ArrayList<Integer>(); 
   for (Integer i: list) {    
       if (picker.pick(i)) {        
          result.add(i);     
       }
   } 
   return result;
}

--- PAGE 11 ---
Passing function argument: v4
On-the-fly Anonymous Lambda Expression
Call the filter method:
List<Integer> list = Arrays.asList(2,3,16,8,-10,15,5,13);
List<Integer> evens = filter(list, (int i) -> i % 2 == 0);
List<Integer> greaterThan10s = filter(list, (int i) -> i > 10);
Type of LHS var can be dropped since it can be unambiguously resolved:
List<Integer> evens = 
         filter(list, i -> i % 2 == 0);
List<Integer> greaterThan10s = 
         filter(list, i -> i > 10);
CS 213 2/24/26                             
11
public interface IntPicker {
   boolean pick(int i);
}
In both calls to filter, i is required to
be an int to match with parameter to
pick
Sesh Venugopal

