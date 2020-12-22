/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import accesoBD.AccesoBD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Alumno;

/**
 * FXML Controller class
 *
 * @author lipez
 */
public class FXMLListStudentsController implements Initializable {
    private AccesoBD BDaccess;
    private ObservableList<Alumno> students;

    @FXML
    private Button addStudent;
    @FXML
    private Button deleteStudent;
    @FXML
    private ListView<Alumno> studentsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deleteStudent.disableProperty().bind(Bindings.lessThan(studentsList.getSelectionModel().
            selectedIndexProperty(), 0));
        BDaccess = AccesoBD.getInstance();
        students = BDaccess.getTutorias().getAlumnosTutorizados();
        studentsList.setItems(students);
        studentsList.setCellFactory((cell) -> new ListCell<Alumno>() {
            @Override
            protected void updateItem(Alumno item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item.getNombre() + " " + item.getApellidos());
                }
            }
        });
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        FXMLLoader customLoader = new FXMLLoader(getClass().
            getResource("/views/FXMLAddStudent.fxml"));
        Parent root = customLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Añadir alumno");
        stage.setScene(scene);
        stage.showAndWait();
        FXMLNewStudentController controller = customLoader.getController();

        if (controller.pressedOk()) {
            students.add(controller.getNewStudent());
            BDaccess.salvar();
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        students.remove(studentsList.getSelectionModel().getSelectedIndex());
        BDaccess.salvar();
    }

}
