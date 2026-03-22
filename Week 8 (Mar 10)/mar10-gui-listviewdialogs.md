# Source: mar10-gui-listviewdialogs.pdf
--- PAGE 1 ---
CS 213 – Software Methodology
Spring 2026
Sesh Venugopal
Mar 10
ListView and Dialogs

--- PAGE 2 ---
Step 1: ListView in AnchorPane
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.control.ListView?>
<AnchorPane 
 
xmlns="http://javafx.com/javafx/11"
 
xmlns:fx="http://javafx.com/fxml/1"
 
fx:controller=”view.ListController">
 
 
<ListView fx:id="listView" 
 
 
AnchorPane.topAnchor = "10"
 
 
AnchorPane.leftAnchor = "10" 
 
 
AnchorPane.rightAnchor = "10"
 
 
AnchorPane.bottomAnchor = "10"/>
 
</AnchorPane>
view/List.fxml
ListView is “anchored” to 
the sides of the containing 
pane with a 10 pixel 
margin – View will resize 
with pane, so that margins 
are always 10 pixels
ListView is empty at this point – need to populate it
CS 213 3/10/26
2
Sesh Venugopal

--- PAGE 3 ---
Step 2: Populating with ObservableList
package view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
public class ListController {        
   @FXML         
   ListView<String> listView;                
   private ObservableList<String> obsList;              
  
   public void start() {                
      // create an ObservableList 
      // from an ArrayList              
      obsList = FXCollections.observableArrayList(                            
                 ”Rams",                               
                 ”Bengals",                               
                 ...
                 "Jaguars”);               
      listView.setItems(obsList);                       
   }
}
view.ListController
CS 213 3/10/26
3
Sesh Venugopal

--- PAGE 4 ---
Step 3: Loading and Displaying
package app;
...
public class ListApp extends Application {        
     
   public void start(Stage primaryStage) 
   throws Exception {                
      FXMLLoader loader = new FXMLLoader();   
      loader.setLocation(
         getClass().getResource("/view/List.fxml"));
      AnchorPane root = (AnchorPane)loader.load();
      ListController listController = 
         loader.getController();
      listController.start(primaryStage);
      Scene scene = new Scene(root, 200, 300);
      primaryStage.setScene(scene);
      primaryStage.show(); 
   }
   public static void main(String[] args) {
      launch(args);
   }
}
app.ListApp
Scroll bar automatically appears 
if list is longer than view area
CS 213 3/10/26
4
Sesh Venugopal
IMPORTANT

--- PAGE 5 ---
CS 213 3/10/26
Sesh Venugopal
5
DO NOT CREATE A CONTROLLER INSTANCE with new :
ListController listController = new ListController();
      
If you do it this way, the controller will not have any connection to the FXML-
sourced widgets with which the user will interact
Instead, you will need to get at the controller through the getController 
method of the FXMLoader, AFTER you have loaded the fxml:
ListController listController = loader.getController();   
IMPORTANT NOTE ABOUT CONTROLLER FOR fxml LAYOUT

--- PAGE 6 ---
Listening to List Item Selection Event
package view;
...
import javafx.stage.Stage;
public class ListController {        
   ...
  
   public void start(Stage mainStage) {                
      ...
      // select the first item
      listView.getSelectionModel().select(0);
      // set listener for the items
      listView
        .getSelectionModel()
        .selectedIndexProperty()
        .addListener(
           (obs, oldVal, newVal) -> 
               showItem(mainStage));
   }
}
view.ListController
lambda expression for the
changed method of the
functional interface
javafx.beans.value.ChangeListener
CS 213 3/10/26
6
Sesh Venugopal

--- PAGE 7 ---
package view;
...
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class ListController {        
   ...
  
   private void showItem(Stage mainStage) {                
      Alert alert = 
         new Alert(AlertType.INFORMATION);
      alert.initOwner(mainStage);
      alert.setTitle("List Item");
      alert.setHeaderText(
           "Selected list item properties");
      String content = "Index: " + 
          listView.getSelectionModel()
                   .getSelectedIndex() + 
          "\nValue: " + 
          listView.getSelectionModel()
                   .getSelectedItem();
          alert.setContentText(content);
          alert.showAndWait();
   }
}
Handling List Item Selection Event
CS 213 3/10/26
7
Sesh Venugopal
The dialog will block execution until user responds
(AndWait()). Also, it will not allow interaction with 
owner window: this makes the dialog “modal” (
default behavior)

--- PAGE 8 ---
Enhancement: Change Item
package view;
...
import java.util.Optional; import javafx.scene.control.TextInputDialog;
public class ListController {        
   ...
   public void start(Stage mainStage) {
      ...
      listView.
        ...
        .addListener((obs, oldVal, newVal) -> 
               showItemInputDialog(mainStage));
   }
   private void showItemInputDialog(Stage mainStage) {                
      String item = listView.getSelectionModel().getSelectedItem();
      int index = listView.getSelectionModel().getSelectedIndex();
      TextInputDialog dialog = new TextInputDialog(item);
      dialog.initOwner(mainStage); dialog.setTitle("List Item");
      dialog.setHeaderText("Selected Item (Index: " + index + ")");
      dialog.setContentText("Enter name: ");
      Optional<String> result = dialog.showAndWait();
      if (result.isPresent()) { obsList.set(index, result.get()); }
   }
}
CS 213 3/10/26
8
Sesh Venugopal

--- PAGE 9 ---
CS 213 3/10/26
Sesh Venugopal
9
showItemInputDialog
TextInputDialog
showAndWait()
Updating ObservableList automatically 
refreshes the ListView 
Optional<String> 
result
obsList.set(index, 
          result.get())
ObservableList => ListView Auto Update

