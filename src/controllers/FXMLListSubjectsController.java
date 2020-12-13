/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Asignatura;
import modelo.Tutorias;

/**
 * FXML Controller class
 *
 * @author lipez
 */
public class FXMLListSubjectsController implements Initializable {
    private ObservableList<Asignatura> asignaturas = null;

    @FXML
    private Button addSubject;
    @FXML
    private Button deleteSubject;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deleteSubject.setDisable(true);
        try {
            FXMLLoader customLoader = new FXMLLoader(getClass().getResource("/views/FXMLMain.fxml"));
            FXMLMainController controller = customLoader.getController();
            Tutorias misTutorias = controller.getTutorias();
            asignaturas = misTutorias.getAsignaturas();
        } catch (Exception e) {
        }

    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        FXMLLoader customLoader = new FXMLLoader(getClass().getResource("/views/FXMLAddSubject.fxml"));
        Parent root = customLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Añadir asignatura");
        stage.setScene(scene);
        stage.showAndWait();
        FXMLNewSubjectController controller = customLoader.getController();
    }

    @FXML
    private void delete(ActionEvent event) {
    }

}
