package br.com.unialfa.aula.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class CalculadoraController {
	
	private List<Character> _valor = new ArrayList<>();
	private List<Character> _operador = Arrays.asList('+','-', '*', '/');
	
	@FXML
	private AnchorPane anpPrincipal;
	
	@FXML
	private Button btnSete, btnOito, btnNove, btnMais, btnQuatro, btnCinco, btnSeis, btnMenos, btnUm, btnDois, 
	btnTres, btnMultiplicar, btnIgual, btnZero, btnApagar, btnDividir, btnParentesesEsq, btnParentesesDir, btnLimpar, 
	btnNegatvo, btnPonto;
	
	@FXML
	private TextField txtPrincipal, txtSecundario;

	@FXML
	private void AdicioneNumero() {

		if(btnUm.isHover()) _valor.add('1');
		if(btnDois.isHover()) _valor.add('2');
		if(btnTres.isHover()) _valor.add('3');
		if(btnQuatro.isHover()) _valor.add('4');
		if(btnCinco.isHover()) _valor.add('5');
		if(btnSeis.isHover()) _valor.add('6');
		if(btnSete.isHover()) _valor.add('7');
		if(btnOito.isHover()) _valor.add('8');
		if(btnNove.isHover()) _valor.add('9');
		if(btnZero.isHover()) _valor.add('0');
		
		ImprivaValorNaTela(txtPrincipal);
	}
	
	private void ImprivaValorNaTela(TextField txt) {
		StringBuilder valor = new StringBuilder();
		_valor.forEach(v -> valor.append(v));

		txt.setText(valor.toString());
	}
	
	
	@FXML
	private void AdicioneOperadorMatematico() {
		

			char ultimaPosicao = _valor.get(_valor.size() - 1);
			
			if(_operador.contains(ultimaPosicao)) 
				ultimaPosicao = _valor.remove(_valor.size() - 1);
			
			if(btnMais.isHover()) ultimaPosicao = '+';
			if(btnMenos.isHover()) ultimaPosicao = '-';
			if(btnMultiplicar.isHover()) ultimaPosicao = '*';
			if(btnDividir.isHover()) ultimaPosicao = '/';
			
			_valor.add(ultimaPosicao);
		
		ImprivaValorNaTela(txtPrincipal);
	}
	
	@FXML
	private void Calcule() {
		
		List<String> operadores = new ArrayList<>();
		List<Double> numeroCompleto = new ArrayList<>();
		StringBuilder numero = new StringBuilder();
		double resultado = 0;
		
		for (Character numeroOuOperador : _valor) {
			
			if(_operador.contains(numeroOuOperador)){
				if(operadores.size() == 0 && numero.length() == 0) {
					numero.append(numeroOuOperador);
					continue;
				}
				operadores.add(numeroOuOperador.toString());
				numeroCompleto.add(Double.parseDouble(numero.toString()));
				numero.setLength(0);
			}
			else {
				numero.append(numeroOuOperador);
			}
		}
		if(numero.length() != 0) {
			numeroCompleto.add(Double.parseDouble(numero.toString()));
			numero.setLength(0);
		}

		if(_valor.size() < 2 && operadores.isEmpty()) return;
		
		double valorAnterior = numeroCompleto.remove(0);
		for (Double valor : numeroCompleto) {
			for (String operador : operadores) {
				
				if(operador.equals("+")) {
					resultado = valorAnterior + valor;
					operadores.remove(operador);
					break;
				}
				if(operador.equals("-")) {
					resultado = valorAnterior - valor;
					operadores.remove(operador);
					break;
				}
				if(operador.equals("*")) {
					resultado = valorAnterior * valor;
					operadores.remove(operador);
					break;
				}
				if(operador.equals("/")) {
					resultado = valorAnterior / valor;
					operadores.remove(operador);
					break;
				}
			}
			valorAnterior = resultado;
			
		}
		ImprivaValorNaTela(txtSecundario);
		_valor.clear();
		ConvertaInteiroParaChar(resultado);
		txtPrincipal.setText(String.valueOf(resultado));
	}
	
	@FXML
	private void AdicionePonte() {
		boolean podeAdicionar = false;
		if(_valor.contains('.')) {
			for (Character operador : _operador) {
				if(_valor.contains(operador)) {
					podeAdicionar = true;
					break;
				}
			}
			if(!podeAdicionar) return;
		}
		_valor.add('.');
		ImprivaValorNaTela(txtPrincipal);
	}
	
	@FXML
	private void MudeParaNegativoOuPositivo() {
		if(_valor.get(0).equals('-')) {
			_valor.remove(0);
			return;
		}else {
			for (Character operador : _operador) {
				if(_valor.contains(operador)) {
					return;
				}
			}
		}
		
		_valor.add(0, '-');
		ImprivaValorNaTela(txtPrincipal);
	}
	
	@FXML
	private void LimpeValores() {
		_valor.clear();
		txtPrincipal.clear();
		txtSecundario.clear();
	}
	
	private void ConvertaInteiroParaChar(double valor) {
		String valorString = String.valueOf(valor);
		for (Character character : valorString.toCharArray()) {
			_valor.add(character);
		}
		
	}
	
	@FXML
	private void Apague() {
		
		String valor = txtPrincipal.getText();
		if(valor.isEmpty()) {
			LimpeValores();
			return;
		}
		int tamanho = _valor.size();
		char valorRemovido = _valor.remove(tamanho - 1);

		ImprivaValorNaTela(txtPrincipal);
	}
}
