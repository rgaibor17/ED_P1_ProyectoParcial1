package com.mycompany.ed_p1_proyectoparcial1;

// import java.io.IOException;
// import javafx.fxml.FXMLLoader;
// import javafx.stage.Stage;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import java.net.URL;
// import java.util.ResourceBundle;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rgaibor17
 */
public class Main extends Application {

    private ObservableList<Car> carData = FXCollections.observableArrayList();
    private TableView<Car> tableView = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        // Initialize the car data (for demonstration)
        carData.add(new Car("Toyota", "Camry", 2020));
        carData.add(new Car("Honda", "Accord", 2019));
        // Add more cars as needed

        // Create UI components (text fields, buttons, table view)
        TextField makeField = new TextField();
        TextField modelField = new TextField();
        TextField yearField = new TextField();

        Button searchButton = new Button("Search");
        Button resetButton = new Button("Reset");
        searchButton.setOnAction(e -> searchCars(makeField.getText(), modelField.getText(), yearField.getText()));
        resetButton.setOnAction(e -> tableView.setItems(carData));
        
        TableColumn<Car, String> makeColumn = new TableColumn<>("Make");
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));

        TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        tableView.setItems(carData);
        tableView.getColumns().addAll(makeColumn, modelColumn, yearColumn);

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        hbox.getChildren().addAll(searchButton, resetButton);
        vbox.getChildren().addAll(makeField, modelField, yearField, hbox, tableView);

        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setTitle("Car Search App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void searchCars(String make, String model, String year) {
        // Implement your search logic here
        // Example: Filter carData based on make, model, and year
        ObservableList<Car> filteredCars = carData.filtered(car ->
                car.getMake().equalsIgnoreCase(make) ||
                car.getModel().equalsIgnoreCase(model) ||
                (year.isEmpty() ? false : car.getYear() == Integer.parseInt(year)));

        tableView.setItems(filteredCars);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
