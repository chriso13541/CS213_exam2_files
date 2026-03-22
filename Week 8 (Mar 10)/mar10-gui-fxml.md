# Source: mar10-gui-fxml.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Mar 10
Graphical User Interface (GUI) using 
FXML

--- PAGE 2 ---
Fahrenheit-Celsius Converter
CS 213 3/10/26
Fahrenheit
Celsius
>>>
<<<
32.0
0.0
Sesh Venugopal
2

--- PAGE 3 ---
Fahrenheit-Celsius Converter
UI implemented in FXML 
(markup language like HTML)
with JavaFX library
CS 213 3/10/26
Sesh Venugopal
3

--- PAGE 4 ---
Preparing to build GUIs with Java FX Library
Install Java FX SDK Version 25 (will only work with JDK 24 and higher) 
or 21 (will work with JDK 17 or higher) for your computer 
platform architecture:
https://gluonhq.com/products/javafx/
After you download the SDK zip file, you need to set up your IDE
to work with Java FX. See the following page for instructions on 
how to do this:
https://openjfx.io/openjfx-docs/
You will be building non-modular projects, so follow instructions 
for non modular projects.
CS 213 3/10/26
Sesh Venugopal
4

--- PAGE 5 ---
CS 213 3/10/26
JavaFX Documentation
JavaFX documentation project: https://fxdocs.github.io/docs/html5/
JavaFX API: https://openjfx.io/javadoc/21/
https://docs.oracle.com/javase/8/javase-clienttechnologies.htm
Sesh Venugopal
5

--- PAGE 6 ---
The MVC Code Architecture
(Model-View-Controller)
CS 213 3/10/26
Model is the set of classes 
that store and manage application data
View is the set of Java classes 
and non-Java design artifacts 
(e.g. xml, css, etc.) that implement 
the user interface
Controller is the set of classes that 
broker between Model and View
F2C-MVC
f2c.app
f2c.view
F2Capp.java
F2CController.java
f2C.fxml
src
NOTE:
1. Each of the M, V, and C parts of the application need not always be in 
its own separate package
2. JavaFX uses the term “controller” to mean a Java class that holds the UI 
objects (e.g. F2CController) – this is different from the controller 
part of the MVC architecture that holds core application logic
Sesh Venugopal
6

--- PAGE 7 ---
View: Layout using fxml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.text.*?>
<?import javafx.geometry.*?>
<GridPane 
 
xmlns="http://javafx.com/javafx/11"
 
xmlns:fx="http://javafx.com/fxml/1"
 
fx:controller="f2c.view.F2CController"
 
vgap="10" hgap="10">
 
    <Text text="Fahrenheit" GridPane.valignment="BOTTOM"/>
 
<Button text="&gt;&gt;&gt;" GridPane.columnIndex="1" />
 
<Text text="Celsius" GridPane.columnIndex="2" GridPane.valignment="BOTTOM"/>
 
<TextField prefColumnCount="10" promptText="-40.0" GridPane.rowIndex="1" />
 
<Button text="&lt;&lt;&lt;" GridPane.rowIndex="1" GridPane.columnIndex="1" />
 
<TextField prefColumnCount="10" promptText="-40.0" 
           GridPane.rowIndex="1" GridPane.columnIndex="2" />
 
<padding>
 
 
<Insets top="10" right="10" bottom="10" left="10"/>
 
</padding>
</GridPane>
CS 213 3/10/26
Sesh Venugopal
7
hgap
vgap
padding
(bottom)

--- PAGE 8 ---
View: Layout using fxml
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.text.*?>
<?import javafx.geometry.*?>
<GridPane 
 
xmlns="http://javafx.com/javafx/11"
 
xmlns:fx="http://javafx.com/fxml/1"
 
fx:controller="f2c.view.F2CController"
 
vgap="10" hgap="10">
 
    <Text text="Fahrenheit" GridPane.valignment="BOTTOM"/>
 
<Button text="&gt;&gt;&gt;" GridPane.columnIndex="1" />
 
<Text text="Celsius" GridPane.columnIndex="2" GridPane.valignment="BOTTOM"/>
 
<TextField prefColumnCount="10" promptText="-40.0" GridPane.rowIndex="1" />
 
<Button text="&lt;&lt;&lt;" GridPane.rowIndex="1" GridPane.columnIndex="1" />
 
<TextField prefColumnCount="10" promptText="-40.0" 
           GridPane.rowIndex="1" GridPane.columnIndex="2" />
 
<padding>
 
 
<Insets top="10" right="10" bottom="10" left="10"/>
 
</padding>
</GridPane>
Don’t forget imports!! (Editor won’t flag
errors for unresolved tags.)
Name space for Java FX tags (e.g. Text)
CS 213 3/10/26
Row and column indexes default to 0
Some of the tags may be different if you use a version > 11
Name space for FXML tags (e.g. fx:controller)
Controller class to which the UI will be mapped
Sesh Venugopal
8

--- PAGE 9 ---
View: Set up SceneBuilder
• Get SceneBuilder at Gluon:
https://gluonhq.com/products/scene-builder/
     Download and install SceneBuilder 25.0 (works with Java 21 
and higher)
• You can open up the SceneBuilder application and load up 
any fxml file to create/edit a layout using its drag-and-drop 
abilities to place widgets, and editor to set widget 
properties
• You can construct UIs exclusively using SceneBuilder 
interface, or you can write up the UI fxml file in an editor 
and optionally verify/polish using SceneBuilder
CS 213 3/10/26
Sesh Venugopal
9

--- PAGE 10 ---
CS 213 3/10/26
Verify fxml Layout with SceneBuilder
(In SceneBuilder, do Preview -> Show Preview in Window to simulate layout behavior)
Sesh Venugopal
10

--- PAGE 11 ---
fxml Layout – Id’ing widgets
...
<Text text="Fahrenheit" GridPane.valignment="BOTTOM"/>
<Button fx:id=“f2c” text="&gt;&gt;&gt;" GridPane.columnIndex="1" />
<Text text="Celsius" GridPane.columnIndex="2" GridPane.valignment="BOTTOM"/>
<TextField fx:id=“f” prefColumnCount="10" promptText="-40.0" 
 
GridPane.rowIndex="1" />
<Button fx:id=“c2f” text="&lt;&lt;&lt;" GridPane.rowIndex="1" 
 
GridPane.columnIndex="1" />
<TextField fx:id=“c” prefColumnCount="10" promptText="-40.0" 
           GridPane.rowIndex="1" GridPane.columnIndex="2" />
<padding>
 
<Insets top="10" right="10" bottom="10" left="10"/>
</padding>
CS 213 3/10/26
Sesh Venugopal
11

--- PAGE 12 ---
fxml Layout – Naming Event Handlers
...
<Text text="Fahrenheit" GridPane.valignment="BOTTOM"/>
<Button fx:id=“f2c” text="&gt;&gt;&gt;" GridPane.columnIndex="1" 
 
onAction=“#convert” />
<Text text="Celsius" GridPane.columnIndex="2" GridPane.valignment="BOTTOM"/>
<TextField fx:id=“f” prefColumnCount="10" promptText="-40.0" 
 
GridPane.rowIndex="1" />
<Button fx:id=“c2f” text="&lt;&lt;&lt;" GridPane.rowIndex="1" 
 
GridPane.columnIndex="1" onAction=“#convert” />
<TextField fx:id=“c” prefColumnCount="10" promptText="-40.0" 
           GridPane.rowIndex="1" GridPane.columnIndex="2" />
<padding>
 
<Insets top="10" right="10" bottom="10" left="10"/>
</padding>
CS 213 3/10/26
Sesh Venugopal
12

--- PAGE 13 ---
Controller that shadows FXML UI (Java Code)
package f2c.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class F2CController {
@FXML Button f2c;
@FXML Button c2f;
@FXML TextField f;
@FXML TextField c;
 
public void convert(ActionEvent e) {
 
 
Button b = (Button)e.getSource();
 
 
if (b == f2c) {
 
 
 
float fval = Float.valueOf(f.getText());
 
 
 
float cval = (fval-32)*5f/9;
 
 
 
c.setText(String.format("%5.1f", cval));
 
 
} else {
 
 
 
float cval = Float.valueOf(c.getText());
 
 
 
float fval = cval*9f/5+32;
 
 
 
f.setText(String.format("%5.1f", fval));
 
 
}
 
}
}
CS 213 3/10/26
This f2c.view.F2CController 
class is the one that is referenced 
in the fxml file:
    
<GridPane 
 
xmlns="http://javafx.com/javafx/11"
 
xmlns:fx="http://javafx.com/fxml/1"
 
fx:controller="f2c.view.F2CController"
 
vgap="10" hgap="10">
The JavaFX framework uses the 
term “controller” to mean a class 
that is tied to an fxml file. 
    
In MVC terms, the JavaFX 
controller is actually a part of the 
View 
    
The C of MVC is the controller part 
that is separate from any View 
component
    
Sesh Venugopal
13

--- PAGE 14 ---
Controller – Java Code
package f2c.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class F2CController {
@FXML Button f2c;
@FXML Button c2f;
@FXML TextField f;
@FXML TextField c;
 
public void convert(ActionEvent e) {
 
 
Button b = (Button)e.getSource();
 
 
if (b == f2c) {
 
 
 
float fval = Float.valueOf(f.getText());
 
 
 
float cval = (fval-32)*5f/9;
 
 
 
c.setText(String.format("%5.1f", cval));
 
 
} else {
 
 
 
float cval = Float.valueOf(c.getText());
 
 
 
float fval = cval*9f/5+32;
 
 
 
f.setText(String.format("%5.1f", fval));
 
 
}
 
}
}
@FXML directive links widget to fxml element: 
    var name in code = id in layout
Name of method = name assigned
in # directive in fxml file for onAction
attribute
    
CS 213 3/10/26
Sesh Venugopal
14

--- PAGE 15 ---
package f2c.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
...
public class F2CApp extends Application {
 
@Override
 
public void start(Stage primaryStage) throws Exception {
 
 
FXMLLoader loader = new FXMLLoader();
 
 
loader.setLocation(getClass().getResource("/f2c/view/f2C.fxml"));
 
 
 
 
 
 
 
 
GridPane root = (GridPane)loader.load();
 
 
Scene scene = new Scene(root);
 
 
primaryStage.setScene(scene);
 
 
primaryStage.setTitle(“Fahrenheit-Celsius”);
 
 
primaryStage.show();
 
}
 
public static void main(String[] args) {
 
 
launch(args);
 
}
}
Main App
CS 213 3/10/26
Creating loader with full path 
name of fxml file, relative to 
project name as root
    
Top-level layout tag in fxml file 
Loading creates Java
objects for various widgets 
and layouts in the fxml file
    
Sesh Venugopal
15

