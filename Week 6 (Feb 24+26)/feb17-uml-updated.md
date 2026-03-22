# Source: feb17-uml-updated.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Feb 17
UML Class Diagram

--- PAGE 2 ---
CS 213 2/17/26                    
(From From “UML basics: The class diagram” by Donald Bell) 
http://www.ibm.com/developerworks/rational/library/content/RationalEdge/sep04/bell/ 
Unidirectional Association
OverdrawnAccounts
generatedOn:Date
refresh()
BankAccount
owner:String
Balance:Dollars
deposit(amount:Dollars)
withdrawal(amount:Dollars)
overdrawnAccounts
0..*
OverdrawnAccounts knows about BankAccount, 
but BankAccount does not know about OverdrawnAccounts => in
the implementation OverdrawnAccounts would have a list of BankAccount
instances, but BankAccount would not refer back to OverDrawnAccounts
Reflexive Association
Employee
firstName:String
lastName:String
manager
manages 0..*
1

--- PAGE 3 ---
CS 213 2/17/26                    
Dependency
•
Class A depends on class B if A uses B in such a way that a change in B will 
effect A
Promotion
Book
getPrice(p:Promotion)
The Book class uses (depends on) on the Promotion class – 
changing Promotion would affect Book
•
Dependencies are the weakest kind of relationships, and the “dependee” class 
(e.g. Promotion) is subordinate to the “depender/dependent” class (e.g. Book) 
In other words, the Book class can be defined meaningfully even without the 
Promotion class

--- PAGE 4 ---
CS 213 2/17/26                    
Modeling a University
From “The Unified Modeling Language User Guide” by Booch, Rumbaugh, and Jacobson
School
Instructor
Course
Student
Department
Member
1..*
*
chairperson
AssignedTo
1..*
1..*
0..1
0..1
Teaches
1..*
*
Attends
*
*
name:Name
address:String
phone:Number
addStudent()
removeStudent()
getStudent()
getAllStudents()
addDepartment()
removeDepartment()
getDepartment()
getAllDepartments()
name:Name
addInstructor()
removeInstructor()
getInstructor()
getAllInstructors()
name:Name
name:Name
courseId:Number
name:Name
studentId:Number
Has
1
1..*
Two associations between 
the same pair of classes

--- PAGE 5 ---
CS 213 2/17/26                    
Modeling a University
Relationship data 
Write the Student and Course classes with just fields (no methods)
Instructor
Course
Student
Teaches
1..*
*
Attends
*
*
name:Name
name:Name
courseId:Number
name:Name
studentId:Number
School
1..*
*

--- PAGE 6 ---
public class Course {
   Name name;
   Number courseId;
   ArrayList<Student> students;
   Instructor[] instructors;
   ...    
}
         
public class Student 
{
   Name name;
   Number studentId;
   Course[] courses;
   School[] schools;
   ...    
}
         
CS 213 2/17/26                    
Modeling a University
Relationship data 
Data needed to store a relationship between two classes 
DO NOT show up as UML attributes in either class, because 
they are NOT inherent properties of either. 
Instead, they are properties of the relationship between the 
classes.
Instructor
Course
Student
Teaches
1..*
*
Attends
*
*
name:Name
name:Name
courseId:Number
name:Name
studentId:Number
School
1..*
*

--- PAGE 7 ---
CS 213 2/17/26                    
Draw a UML Class Diagram to model orders for products. Customers 
who place orders can be personal customers or corporate customers. 
An order could have multiple products (items) in it, and at any point 
in time the company needs to know which of these items have been 
shipped.
Modeling Orders for Products

--- PAGE 8 ---
CS 213 2/17/26                    
Modeling Orders for Products
Order
dateReceived
isPrepaid
number:String
price:Money
dispatch()
close()
From “UML Distilled” by Martin Fowler with Kendall Scott
Order Line
quantity:Integer
price:Money
isSatisfied:Boolean
line
items *
1
Product
*
1
*
1
Customer
name
address
creditRating():String
Corporate
Customer
contactName
creditRating
creditLimit
remind()
billForMonth(Integer)
Personal
Customer
creditCard#
Employee
sales rep 0..1
*

--- PAGE 9 ---
CS 213 2/17/26                    
Modeling Police Incidents
Draw a UML Class Diagram to model the handling of incidents by the 
dispatchers and field officers of a police department. Dispatchers log 
incidents, and field officers write emergency reports, as many as 
each incident requires.

--- PAGE 10 ---
CS 213 2/17/26                    
Abstract Class/Inheritance/Bidirectional Association
From “Object-Oriented Software Engineering” 2nd ed. by Bruegge and Dutoit
Incident
EmergencyReport
reports
1..*
1
incidentsGenerated
initiator
1
*
reportsGenerated
1
*
author
PoliceOfficer
name:String
badgeNumber:Integer
FieldOfficer
name:String
badgeNumber:Integer
Dispatcher
name:String
badgeNumber:Integer
These are clearly 
inherited, so I don’t 
see why they are 
explicitly listed here

--- PAGE 11 ---
CS 213 2/24/26                    
Association Class
•
At times, an association between two classes itself has properties - 
      an association class is used to model these properties. 
Job
description
dateHired
salary
Company
Person
employer
employee
*
1..*
From “The Unified Modeling Language User Guide” by Booch, Rumbaugh, and Jacobson
Every pair of 
Company-Person 
instances will have a 
Job instance:
C1-P1: J1
C1:P2: J2
…
C5:P1: JX
…

--- PAGE 12 ---
CS 213 2/24/26                    
Association Class: Example
Flight
flightNumber:Integer
departureTime:Date
flightDuration:Minutes=60
delayFlight(numberOfMinutes:Minutes)
getArrivalTime():Date
FrequentFlyer
firstName:String
lastName:String
frequentFlyerNumber:String
flights
passengers
0..*
0..*
MileageCredit
baseMiles:Integer
bonusMiles:Integer
From “UML basics: The class diagram” by Donald Bell 
http://www.ibm.com/developerworks/rational/library/content/RationalEdge/sep04/bell/ 

--- PAGE 13 ---
CS 213 2/24/26                    
Association Class: Example
From “Object-Oriented Software Engineering” 2nd ed. by Bruegge and Dutoit
FieldOfficer
name:String
badgeNumber:Integer
Incident
Allocates
role:String
notificationTime:Time
resources
incident
1..*
1

