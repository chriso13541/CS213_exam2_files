# Source: feb26-lambdas.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Feb 26
Lambda Expressions

--- PAGE 2 ---
Lambda -> Functional Interface
A lambda expression gets compiled into an object that implements 
a functional interface, with parameter and return types resolved 
according to context
List<Integer> evens = filter(list, 
                             i -> i % 2 == 0);
Because filter takes an instance of IntPicker as 2nd parameter, the 
matching lambda expression argument gets compiled to an instance 
of a class that implements IntPicker (the class is worked out by the 
compiler behind the scenes)
Because the method (name irrelevant) in the IntPicker functional 
interface takes a single int parameter and returns a boolean, the 
LHS of the lambda is taken to be an int type var, and the RHS 
expression is verified to be applicable to an int, with a boolean return
Multiple statements in RHS must be in a braces-block:
x -> { x++; System.out.println(x); }
CS 213 2/26/26                          
IntPicker
2
Sesh Venugopal

--- PAGE 3 ---
Some Pre-Defined 
Functional Interfaces in
java.util.function
CS 213 2/26/26                          
3
Sesh Venugopal

--- PAGE 4 ---
Generalizing filter method to work on
some boolean test on ANY type
Want to make boolean filter method work on ANY data type, 
not just int
CS 213 2/26/26                          
4
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
Want to generalize
Sesh Venugopal

--- PAGE 5 ---
Generalizing filter method to work on
some boolean test on ANY type
Java has a pre-defined functional interface for this very purpose,
in the package java.util.function:
There are other methods in this interface, which are either
static or default, that are not abstract (fully implemented).
So this is a functional interface because a single method, 
test, is abstract.
interface Predicate<T> {
    boolean test(T t);
    ...
}
CS 213 2/26/26                          
functional method (the single
abstract method of the interface)
5
Sesh Venugopal

--- PAGE 6 ---
public static <T> List<T> 
filter(List<T> list, 
       Predicate<T> p) { 
   List<T> result = 
            new ArrayList<T>(); 
   for (T t: list) {    
      if (p.test(t)) {        
         result.add(t);     
      }
   } 
   return result;
}
Using java.util.function.Predicate
Calls made for Integer list:
List<Integer> list = 
   Arrays.asList(2,3,16,8,-10,15,5,13);
List<Integer> evens = 
   filter(list, i -> i % 2 == 0);
List<Integer> greaterThan10s = 
   filter(list, i -> i > 10);
Calls made for String list:
List<String> colors = 
   Arrays.asList(
   “red”,”green”,”orange”,”violet”,
   ”blue”,”white”,”yellow”,”indigo”);
List<String> shortColors = 
   filter(colors, s -> s.length() < 4);
List<String> longColors = 
   filter(colors, s -> s.length() > 5);
CS 213 2/26/26                          
6
Sesh Venugopal

--- PAGE 7 ---
Beyond Predicates: 
Applying Non-Boolean Functions
java.util.function.Function  
interface helps with this:
interface Function<T,R> {
    R apply(T t); ...
}
// square all numbers in list
List<Integer> lengths = map(colors, s -> s.length())
CS 213 2/26/26                          
List<Integer> squares = map(list, i -> i * i);
// map color names to their lengths
public static <T,R> List<R> 
map(List<T> list, Function<T,R> f) { 
   List<R> result = new ArrayList<R>(); 
   for (T t: list) {    
       result.add(f.apply(t));     
   } 
   return result;
}
7
Sesh Venugopal

--- PAGE 8 ---
public static <T> void
consume(List<T> list,
        Consumer<T> cons) { 
   for (T t: list) {    
       cons.accept(t);     
   } 
}
Consumer Interface
The java.util.function.Consumer interface “consumes” its
single argument, returning nothing
interface Consumer<T> {
    void accept(T t);
    ...
}
// print colors, capitalized
CS 213 2/26/26                          
consume(colors, s ->                  
System.out.println(                     
Character.toUpperCase(s.charAt(0)) +      
s.substring(1));
8
Sesh Venugopal

--- PAGE 9 ---
Method References
CS 213 2/26/26                          
9
Sesh Venugopal

--- PAGE 10 ---
// consuming method
public static <T> void consume(List<T> list, Consumer<T> cons) { 
   for (T t: list) {  cons.accept(t); } } 
}
Method References
Here’s a call to this method, with a lambda for the Consumer argument:
A method reference is a lambda written with a :: and method 
name, instead of an actual call to the method with parameters
CS 213 2/26/26                          
10
Alternatively, we can pass a method reference to System.out.println:
// passing method reference
consume(list,                     );
System.out::println  
// call to consuming method
List<Integer> list = Arrays.asList(2,3,16,8,-10,15,5,13);
consume(list, i -> System.out.println(i));
Say we write a consume method with a java.util.function.Consumer 
parameter:
Sesh Venugopal

--- PAGE 11 ---
// consuming method
public static <T> void consume(List<T> list, Consumer<T> cons) { 
   for (T t: list) {  cons.accept(t); } } 
}
Method References
A method reference is a way to rewrite a lambda to pass just the 
name of a method (no parens after), instead of an actual call to it
// passing method reference
consume(list, System.out::println);
System.out.println accepts an argument and does not return
a value
So it can work like a java.util.function.Consumer function,
and in the accept method, each item in the list will be passed
in as argument to System.out.println
CS 213 2/26/26                          
11
Sesh Venugopal

--- PAGE 12 ---
public static <T,R> List<R> 
map(List<T> list, Function<T,R> f) { 
   List<R> result = new ArrayList(); 
   for (T t: list) { result.add(f.apply(t));} 
   return result;
}
Method Reference: Instance Method
Recall the earlier example of a map method that took a
java.util.function.Function as parameter:
// map color names to their lengths
List<Integer> lengths = map(colors, String::length);
CS 213 2/26/26                          
12
The lambda can be simplified by using a method reference instead:
// map color names to their lengths
List<Integer> lengths = map(colors, s -> s.length());
It was used to map color names to their lengths like this:
length() is 
an instance method
of String
Sesh Venugopal

--- PAGE 13 ---
public static List<T> 
filter(List<T> list, Predicate<T> p) {
   List<T> result = new ArrayList<T>(); 
   for (T t: list) {
        if (p.test(t)) {    
       
 
result.add(t);
        }     
   } 
   return result;
}
Instance Method Reference: Example 2
System.out.println(filter(students, Student::isSenior));
class Student {
   ...
   public boolean 
   isSenior() { ... }
}
s -> s.isSenior()
equivalent to
CS 213 2/26/26                          
13
// filter seniors using method reference
List<Student> students = new ArrayList<Student>();
... // populate list
Sesh Venugopal

--- PAGE 14 ---
Method Reference Example: Sorting
Version 1: Write a named Comparator class and pass an instance
Say we want to sort the students list by year 
class YearComparator 
implements Comparator<Student> {    
   public int compare(
         Student s1, Student s2) {    
      return s1.getYear() – 
             s2.getYear();    
   }
}
class Student {
  public static final int FRESHMAN=1;
  public static final int SOPHOMORE=2;
  public static final int JUNIOR=3;
  public static final int SENIOR=4;
  private int year;  
  ...
  public int getYear() { 
     return year;
  }
}
CS 213 2/26/26                          
14
// sort with instance of YearComparator
students.sort(new YearComparator());
java.util.Comparator is a functional interface with a single abstract compare method
Sesh Venugopal
java.util.List interface 
default sort method that
takes a Comparator argument

--- PAGE 15 ---
Version 2: Pass an instance of an anonymous Comparator 
         implementation
// sort with instance of anonymous YearComparator implementation
students.sort(new Comparator<Student>() {
                 public int compare(Student s1, Student s2) {       
                    return s1.getYear() – s2.getYear();    
                 }
              });
Version 3: Pass a lambda
students.sort((s1,s2) -> s1.getYear – s2.getYear());
CS 213 2/26/26                          
15
Method Reference Example: Sorting
Sesh Venugopal

--- PAGE 16 ---
Version 4: Use lambda with comparing method of Comparator
students.sort(comparing(s -> s.getYear()));
comparing method returns a Comparator instance 
that uses key extracted by given function (e.g. getYear())
static method 
of Comparator
function that extracts
key from type of objects
to be compared
Version 5: Use method reference with comparing method
students.sort(comparing(Student::getYear));
CS 213 2/26/26                          
16
Method Reference Example: Sorting
Code above requires:
import static java.util.Comparator.comparing;
static methods can
 be imported!!
Sesh Venugopal

--- PAGE 17 ---
Constructor as Method Reference
class Student {
  ... 
  public Student(int year, boolean commuter, String major) {...}
  public Student(int year, String major) {...}
  public Student(int year) {...}
  public Student() {...}
}
1. No-arg constructor used for java.util.function.Supplier instance
interface Supplier<T> {
    T get();
}
2. 1-arg constructor used for java.util.function.IntFunction instance
CS 213 2/26/26                          
17
Supplier<Student> s = Student::new;
Student student = s.get();
IntFunction<Student> func = Student::new;
Student student = func.apply(Student.SOPHOMORE);
Student student = 
((Supplier<Student>)Student::new).get();
or
Sesh Venugopal

--- PAGE 18 ---
Constructor as Method Reference
3. 2-arg constructor used for java.util.function.BiFunction instance
Example: Generating a list of students, mapping from years to instances
static List<Student> 
generate(List<Integer> years, IntFunction<Student> func) {      
   List<Student> result = new ArrayList<Student>();      
   for (Integer i: years) {           
      result.add(func.apply(i));      
   }      
   return result;   
}
CS 213 2/26/26                          
18
Call:
List<Student> students = generate(    
Arrays.asList(Student.JUNIOR, Student.FRESHMAN, Student.SENIOR),
Student::new);
BiFunction<Integer,String,Student> bifunc = Student::new;
Student student = bifunc.apply(Student.SOPHOMORE,”CS”);
parameter, parameter, return
Sesh Venugopal

--- PAGE 19 ---
Composing Predicates and Functions
CS 213 2/26/26                          
19
Sesh Venugopal

--- PAGE 20 ---
public static<T> List<T> 
filter(List<T> list, Predicate<T> p) { 
   List<T> result = new ArrayList<T>(); 
   for (T t: list) {    
      if (p.test(t)) { 
         result.add(t); 
   } 
   return result;
}
Composing Predicates
Predicate<Student> cs_major = s -> s.getMajor().equals(“CS”);
CS 213 2/26/26                          
20
Predicate<Student> senior = s -> s.getYear() == Student.SENIOR;
Predicate<Student> junior = s -> s.getYear() == Student.JUNIOR;
Sesh Venugopal

--- PAGE 21 ---
Composing Predicates
Predicates can be composed to make compound conditions:
filter(students,                  // ? 
       cs_major.and(senior)); 
filter(students,                  // ? 
        cs_major
       .and(junior.or(senior))); 
CS seniors
CS juniors or seniors
filter(students,                  // ?
        cs_major
       .and(junior.or(senior))
       .negate());              
Students who are not
CS juniors or seniors
filter(students,                  // ?
        cs_major
       .and((junior.or(senior))
            .negate()
           )); 
CS majors who are not
juniors or seniors
CS 213 2/26/26                          
21
Sesh Venugopal

--- PAGE 22 ---
public static<T,R> List<R> 
map(List<T> list, Function<T,R> f) { 
   List<R> result = new ArrayList<R>(); 
   for (T t: list) {    
       result.add(f.apply(t)); 
   } 
   return result;
}
Composing Functions
Function<Integer,Integer> f = i -> i*i;
map(list, f.andThen(g));
// g(f(x)) = [11, 66, 102, 227, 27]
map(list, f.compose(g));
// f(g(x)) = [25, 100, 64, 289, 49]
CS 213 2/26/26                          
22
Function<Integer,Integer> g = i -> i+2;
List<Integer> list = Arrays.asList(3,8,-10,15,5);
Sesh Venugopal

