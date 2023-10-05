package Controllers;

import java.awt.Button;
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
					
			}else {
				System.out.print("Deu ruim");
			}
		} catch (Exception e) {

		}
		
		
		
		
	}
}


	
	
	
	