package br.com.unialfa.aula.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class CalculadoraController {
	
	private String _valor = "";
	private String _primeiroValor = "";
	private String _segundoValor = "";
	private String _operador = "";
	
	@FXML
	private AnchorPane anpPrincipal;
	
	@FXML
	private Button btnSete, btnOito, btnNove, btnMais, btnQuatro, btnCinco, btnSeis, btnMenos, btnUm, btnDois, 
	btnTres, btnMultiplicar, btnIgual, btnZero, btnApagar, btnDividir;
	
	@FXML
	private TextField txtPrincipal;

	@FXML
	private void AdicioneNumero() {

		if(btnUm.isHover()) _valor += "1";
		if(btnDois.isHover()) _valor += "2";
		if(btnTres.isHover()) _valor += "3";
		if(btnQuatro.isHover()) _valor += "4";
		if(btnCinco.isHover()) _valor += "5";
		if(btnSeis.isHover()) _valor += "6";
		if(btnSete.isHover()) _valor += "7";
		if(btnOito.isHover()) _valor += "8";
		if(btnNove.isHover()) _valor += "9";
		if(btnZero.isHover()) _valor += "0";
		
		
		if(_operador.isEmpty()) _primeiroValor = _valor;
		else _segundoValor = _valor;
			
		txtPrincipal.setText(_primeiroValor + " " + _operador + " " +_segundoValor);
	}
	
	
	@FXML
	private void AdicioneOperadorMatematico() {
		
		if(btnMais.isHover()) _operador = "+";
		if(btnMenos.isHover()) _operador = "-";
		if(btnMultiplicar.isHover()) _operador = "*";
		if(btnDividir.isHover()) _operador = "/";
		
		if(_primeiroValor.isEmpty() && !txtPrincipal.getText().isEmpty()) _primeiroValor = txtPrincipal.getText();
		txtPrincipal.setText(_primeiroValor + " " + _operador + " " +_segundoValor);
		_valor = "";
	}
	
	@FXML
	private void Calcule() {
				
		if(ValoresEstaoInconsistentes()) return;
		
		int valorUm = Integer.parseInt(_primeiroValor);
		int valorDois = Integer.parseInt(_segundoValor);
		int resultado = 0;
		
		if(_operador.equals("+")) resultado = valorUm + valorDois;
		if(_operador.equals("-")) resultado = valorUm - valorDois;
		if(_operador.equals("*")) resultado = valorUm * valorDois;
		if(_operador.equals("/")) resultado = valorUm / valorDois;
			
		txtPrincipal.setText(String.valueOf(resultado));
		LimpeValores();
	}
	
	private void LimpeValores() {
		_primeiroValor = "";
		_operador = "";
		_segundoValor = "";
		_valor = "";
	}
	
	private boolean ValoresEstaoInconsistentes() {
		return _primeiroValor.isEmpty() || _operador.isEmpty() || _segundoValor.isEmpty();
	}
		
	
	@FXML
	private void Apague() {
		
		String valor = txtPrincipal.getText();
		if(valor.isEmpty()) {
			LimpeValores();
			return;
		}
		
		int tamanho = valor.length();
		valor = valor.substring(0, tamanho - 1);
		txtPrincipal.setText(AjusteValores(valor));
	}
	
	private String AjusteValores(String valor) {
		
		if(_operador.isEmpty())  {
			int tamanhoPrimeiro = _primeiroValor.length();
			_primeiroValor = _primeiroValor.substring(0, tamanhoPrimeiro - 1);
			_valor = _primeiroValor;
		}else {
			int segundoValor = _segundoValor.length();
			_segundoValor = _segundoValor.substring(0, segundoValor - 1);
			_valor = _segundoValor;
		}
		
		valor = _primeiroValor + " " + _operador + " " + _segundoValor;
		return valor;
	}
}
