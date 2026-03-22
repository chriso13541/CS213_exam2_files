# Source: mar5-patterns.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Mar 5
Design Patterns
Iterator, Template Method

--- PAGE 2 ---
CS 213 3/5/2026                    
2
Sesh Venugopal

--- PAGE 3 ---
CS 213 3/5/2026                    
3
Categories of Patterns
Design patterns are classified into three categories:
– Creational patterns: to do with the object creation process
– Structural patterns: to do with the static composition and 
structure of classes and objects
– Behavioral patterns: to do with the dynamic interaction between 
classes and objects
Sesh Venugopal

--- PAGE 4 ---
CS 213 3/5/2026                    
4
Sesh Venugopal

--- PAGE 5 ---
CS 213 3/5/2026                    
5
Sesh Venugopal

--- PAGE 6 ---
CS 213 3/5/2026                    
6
Sesh Venugopal

--- PAGE 7 ---
CS 213 3/5/2026                    
7
Sesh Venugopal

--- PAGE 8 ---
Iterator 
Behavioral Pattern
CS 213 3/5/2026                    
8
Sesh Venugopal

--- PAGE 9 ---
CS 213 3/5/2026                    
Iterator Design Pattern: Behavioral
public class LinkedList<T> {
   public static class Node<E> {
      public E data;
      public Node<E> next;
   }
   public Node<T> front;
   . . .
}
LinkedList<String> list = 
                     new LinkedList<String>();
. . .
for (LinkedList.Node<String> ptr = list.front;
    ptr != null; ptr = ptr.next) {
    System.out.println(ptr.data);
}
Solution 1: Iterate by directly accessing nodes
Only works if Node and front are 
accessible to clients, which means they 
must be made public. Not a good 
design idea!
public class LinkedList<T> {
   protected static class Node<E> {
      protected E data;
      protected Node<E> next;
   }
   protected Node<T> front;
   . . .
}
Need something like this instead
9
Sesh Venugopal

--- PAGE 10 ---
CS 213 3/5/2026                    
Iterator: Behavioral
Solution 2: Iterate via method invocation
Basic Iteration using solution 2
LinkedList<String> list = new LinkedList<String>();
. . .
for (list.reset(); list.hasNext();) {
    System.out.println(list.next());
}
E.g. Print #links from each web page to all other web pages
This won’t work – the inner loop thrashes the state of 
the outer!
public class LinkedList<T> {
   . . .
}          
protected Node<T> curr;
public void reset() {
curr = front;
}
public T next() {
T ret=null;
if (curr != null) {
ret = curr.data;
curr = curr.next;
}
return ret;
}
public boolean hasNext() {
return curr != null;
}
LinkedList<URL> list = new LinkedList<URL>();
// populate with web pages . . .
for (list.reset(); list.hasNext();) {
URL wp1 = list.next();
for (list.reset(); list.hasNext();) {       
URL wp2 = list.next();
int n = numLinks(wp1, wp2);
System.out.println(“#links from “ + wp1 +
                         “ to “ + wp2 + “ = “ + n);
   }
}
10
Sesh Venugopal

--- PAGE 11 ---
CS 213 3/5/2026                    
Iterator: Behavioral
// in same package as LinkedList
public class LinkedListIterator<T> {
   protected LinkedList.Node<T> curr;
   
   public LinkedListIterator(
                LinkedList<T> list) {
      curr = list.front;
   }
   public T next() {
      T ret = null
      if (curr != null) {
          ret = curr.data;
          curr = curr.next;
      }
      return ret;
   }
   public boolean hasNext() {
      return curr != null;
   }
} 
Solution 3: Separate the Iterator from the LinkedList
LinkedList<URL> list = 
            new LinkedList<URL>();
// populate with web pages . . .
LinkedListIterator<URL> iter1 = 
    new LinkedListIterator<URL>(list);
while (iter1.hasNext()) {
   URL wp1 = iter1.next();
   LinkedListIterator<URL> iter2 =
     new LinkedListIterator<URL>(list);
   while (iter2.hasNext()) {
        URL wp2 = iter2.next();
        int n = numLinks(wp1, wp2);
        . . .
    }
}
Print #links from each web page 
to all other web pages
11
Sesh Venugopal

--- PAGE 12 ---
CS 213 3/5/2026                    
Iterator: Behavioral
class LinkedListIterator<T> 
             implements Iterator<T> {
   protected LinkedList<T> list;
   protected LinkedList.Node<T> curr;
   
   LinkedListIterator(LinkedList<T> list) {
      this.list = list; curr = list.front;
   }
   public T next() {
      T ret = null
      if (curr != null) {
          ret = curr.data; curr = curr.next;
      }
      return ret;
   }
   public boolean hasNext() {
      return curr != null;
   }
   // following are default methods
   public void remove() { 
      throw new 
        UnsupportedOperationException();
   }
   public void 
   forEachRemaining(Consumer<E> cons) {
       ...
   } 
} 
Solution 4: Generalization with Interface
public interface Iterator<T> {
   boolean hasNext();
   T next();
   void remove();
}
java.util
Have the LinkedListIterator class
implement an interface
12
Sesh Venugopal

--- PAGE 13 ---
CS 213 3/5/2026                    
Iterator: Behavioral
Solution 4: Generalization with Interface
LinkedList<URL> list = 
    new LinkedList<URL>();
// populate with web pages . . .
Iterator<URL> iter1 = list.iterator();
while (iter1.hasNext()) {
   URL wp1 = iter1.next();
   Iterator<URL> iter2 = list.iterator();
   while (iter2.hasNext()) {
        URL wp2 = iter2.next();
        int n = numLinks(wp1, wp2);
        . . .
    }
}
public class LinkedList<T> {
   . . .
   public Iterator<T> iterator() {
      return new 
      LinkedListIterator<T>(this);
   }
   . . .
}
Finish up by having the LinkedList class implement a method that will
return an instance of the LinkedListIterator
13
Sesh Venugopal

--- PAGE 14 ---
CS 213 3/5/2026                    
Iterator: Behavioral
•
Access the contents of a collection without exposing its internal 
representation
•
Support overlapping multiple traversals
•
Provide a uniform interface for traversing different collections – support 
polymorphic iteration
return new ConcreteIterator()
Collection
iterator()
Iterator
hasNext()
next()
ConcreteIterator
hasNext()
next()
14
Sesh Venugopal

--- PAGE 15 ---
Template Method 
Behavioral Pattern
CS 213 3/5/2026                    
15
Sesh Venugopal

--- PAGE 16 ---
CS 213 3/5/2026                    
Template Method: Behavioral
•
A template method implements an algorithm, or a set sequence of actions: 
each action is a method, some of which are abstract because their 
implementations are specific to concrete subclasses
•
The abstract methods are referred to as “hook” methods
•
The template method is hosted in an abstract class: note that the template 
method itself is not abstract.
•
Each specific algorithm can then extend this abstract host class, and provide 
its own specific version of the hook method
. . .
hook1()
. . .
hook2()
. . .
TemplateHost
templateMethod()
hook1()
hook2()
ConcreteClass
hook1()
hook2()
16
Sesh Venugopal

--- PAGE 17 ---
CS 213 3/5/2026                    
Example 1: Processing Data
public abstract class DataProcessor {  
   . . .
   // template method
   public final void process(Resource resource) {
      try {
        open(resource);
        Data data = read(resource);
        processData(data);
        close(resource);
      } catch (OpenCloseException o) {
        reportError(o);
      } catch (ReadException r) {
        reportError(r);
      }
   }
   // non abstract method
   protected void processData(Data data) { ... }
   // hook methods
   protected abstract void open(Resource resource);
   protected abstract Data read(Resource resource);
   protected abstract void close(Resource resource);
   protected abstract void reportError(Exception e);
   . . .
}
17
Sesh Venugopal

--- PAGE 18 ---
CS 213 3/5/2026                    
Example 1: Multiple resource types 
public class DatabaseProcessor extends DataProcessor {  
   . . .
   // implement hook methods
   protected void open(Resource resource) { ... }  // database connection
   protected Data read(Resource resource) { ... }  // SQL statement(s)
   protected void close(Resource resource) { ... } // database connection
   protected void reportError(Exception e) { ... } // write to database log
   . . .
}
18
Sesh Venugopal

--- PAGE 19 ---
CS 213 3/5/2026                    
Example 1: Multiple resource types 
public class FileProcessor extends DataProcessor {  
   . . .
   // implement hook methods
   protected void open(Resource resource) { ... }  // open file
   protected Data read(Resource resource) { ... }  // read file
   protected void close(Resource resource) { ... } // close file 
   protected void reportError(Exception e) { ... } // write to log file
   . . .
}
19
Sesh Venugopal

--- PAGE 20 ---
CS 213 3/5/2026                    
Example 1: Multiple resource types 
public class NetworkProcessor extends DataProcessor {  
   . . .
   // implement hook methods
   protected void open(Resource resource) { ... }  // open network stream
   protected Data read(Resource resource) { ... }  // read from stream
   protected void close(Resource resource) { ... } // close network stream
   protected void reportError(Exception e) { ... } // write to a network location
   . . .
}
20
Sesh Venugopal

--- PAGE 21 ---
CS 213 3/5/2026                    
Example 1: Application Calls
// use database 
DataProcessor dproc = new DatabaseProcessor();
Resource dresource = new DatabaseResource();
. . .
dproc.process(dresource);
// use file
DataProcessor dproc = new FileProcessor();
Resource dresource = new FileResource();
. . .
dproc.process(dresource);
// use network
DataProcessor dproc = new NetworkProcessor();
Resource dresource = new NetworkResource();
. . .
dproc.process(dresource);
21
Sesh Venugopal

--- PAGE 22 ---
CS 213 3/5/2026                    
Example 2 – Graph DFS
Since depth-first search serves as a basis for various graph algorithms, 
it can be implemented with template methods that can then be 
overridden appropriately by DFS-based algorithms/applications
Key observation: The base DFS code does the traversal through the graph, 
while providing hooks for:
- Restarting DFS at different vertices
- Doing stuff on getting to a vertex
- Doing stuff just before leaving a vertex (to back up to 
previous recursive level)
22
Sesh Venugopal

--- PAGE 23 ---
CS 213 3/5/2026                    
Example 2 – Graph DFS
public abstract class DFS {
   protected Graph G; 
   protected boolean[] visited; 
   protected int[] info;
   
   public DFS(Graph G) {
       this.G = G; visited = new boolean[G.n];
       for (int v=0; v < G.n; v++) {
           visited[v] = false;
       }
       info = new int[G.n]; 
   }
   public final int[] dfs() {  // template method
     ...
   }
   protected final void dfs(int v) {  // template method
     ...
   }
   ...
}
23
Sesh Venugopal

--- PAGE 24 ---
CS 213 3/5/2026                    
Example 2 – Graph DFS
public abstract class DFS
   ...
   public final int[] dfs() {  // template method
      for (int v=0; v < G.n; v++) { 
          if (!visited[v]) { 
             restart(); 
             dfs(v); 
          }
      }
      return info;
   }
   
   protected abstract void restart();          // hook 1
   
   protected final void dfs(int v) {  // template method
      preAction(v); visited[v] = true; 
      Iterator<Integer> iter = G.neighborsIterator(v);
      while (iter.hasNext()) {
         int v = iter.next();
         if (!visited[v]) { dfs(v); }
      } 
      postAction(v);
   }
   
   protected abstract void preAction(int v);   // hook 2 
   protected abstract void postAction(int v);   // hook 3
}
24
Sesh Venugopal

--- PAGE 25 ---
CS 213 3/5/2026                    
Example 2: Topological Sort
public class Topsort extends DFS {
   
   protected int topNum;
   public Topsort(Graph G) {
      super(G);
      topNum = n-1;
   }
   // hook methods, redefined
   protected void restart() { }         // do nothing
   protected void preAction(int v) { }  // do nothing
   
   protected void postAction(int v) {   // slot v in sequence
      info[topNum--] = v;
   }
} 
DFS topsort = new Topsort(graph);
int[] topSequence = topsort.dfs();
USAGE:
25
Sesh Venugopal

--- PAGE 26 ---
CS 213 3/5/2026                    
Example 2: Connected Components
public class ConnComp extends DFS {
   
   protected int currComp;
   public Conncomp(Graph G) {
      super(G);
      currComp = 0;
   }
  
   // hook methods, redefined
   protected void restart() { currComp++; } // for next component
   protected void preAction(int v) { info[v] = currComp; }
   
   protected void postAction(int v) { }  // do nothing
} 
DFS connectedComps = new ConnComp(graph);
int[] components = connectedComps.dfs();
USAGE:
26
Sesh Venugopal

