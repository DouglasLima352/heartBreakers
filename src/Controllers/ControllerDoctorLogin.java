package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbconnection.Connect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;


import model.Doctor;// import da classe Doctor

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
	
	public void loginDoctor (ActionEvent event) {
		String sql = "select * from doctor where login=? and senha=?";
		try {
			//preparo pra consulta sql
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtCpfDoctor.getText());
			pst.setString(2, txtSenhaDoctor.getText());
			//resultado da consulta
			rs = pst.executeQuery();
			if (rs.next()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/caminho/para/seu/arquivo/FXML.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
			}else {
				System.out.print("Deu ruim");
			}
		} catch (Exception e) {
			System.out.print(e);
		}	
	}
	
	private void btnLoginActionPerformed(ActionEvent evt) {
        loginDoctor(evt);
	}
	
}


	
	
	
	