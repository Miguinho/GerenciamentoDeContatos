package br.com.contatos.controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import br.com.contatos.helper.MySqlConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable {
	@FXML Button btninserir;
	@FXML TextField txttelefone, txtnome;
	@FXML ListView lstContatos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		preencherLista();
	}

	@FXML public void inserirContato() {
		Connection con = MySqlConnect.ConectarDb();

		txtnome.clear();
		txttelefone.clear();

		String query = "insert into contact(name, phone) values(?, ?);";

		PreparedStatement parametros;
		try {
			parametros = con.prepareStatement(query);
			parametros.setString(1, txtnome.getText());
			parametros.setString(2, txttelefone.getText());
			parametros.executeUpdate();

			con.close();
		} catch (SQLException e) { e.printStackTrace(); }

		String contato = txtnome.getText() + " - " + txttelefone.getText();


		preencherLista();
		//lstContatos.getItems().add(contato);
		//System.out.println("ok");
	}

	private void preencherLista(){
		Connection con = MySqlConnect.ConectarDb();

		lstContatos.getItems().clear();

		String query = "select * from contact;";

		try {
			ResultSet rs = con.createStatement().executeQuery(query);

			while(rs.next()){
				String contato = "";
				contato = rs.getString("name");
				contato += " - ";
				contato += rs.getString("phone");

				lstContatos.getItems().add(contato);
			}

		} catch(Exception e){}

	}
}