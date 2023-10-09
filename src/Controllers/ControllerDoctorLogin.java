package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import dbconnection.Connect;

public class ControllerDoctorLogin {

    @FXML
    private TextField txtCpfDoctor;

    @FXML
    private TextField txtSenhaDoctor;

    @FXML
    private Button btnLogin;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    public void loginDoctor(ActionEvent event) {
        String sql = "select * from doctor where cpf=? and password=?";
        try {
            // conexao
            Connection connect = dbconnection.Connect.fazer_conexao();
            // preparo consulta sql
            pst = connect.prepareStatement(sql);
            pst.setString(1, txtCpfDoctor.getText());
            pst.setString(2, txtSenhaDoctor.getText());
            // resultado da consulta
            rs = pst.executeQuery();
            if (rs.next()) {
                // pega a primeira pagina e fecha
                Stage currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.close(); // 

                // abre segunda pagina
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PacienteHB.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage secondStage = new Stage();
                secondStage.setScene(scene);
                secondStage.setResizable(false); // impede que o stage seja redimensionado.
                secondStage.show();
            } else {
                System.out.println("Login ou senha incorretos.");
            }
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
