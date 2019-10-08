package Calculadora;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	private ComboBox<String> operadorBox;
	private TextField operandoTextA1;
	private TextField operandoTextA2;
	private TextField operandoTextB1;
	private TextField operandoTextB2;
	private TextField operandoTextC1;
	private TextField operandoTextC2;
	
	
	private StringProperty operador = new SimpleStringProperty();
	private Complejo A= new Complejo();
	private Complejo B=new Complejo();
	private Complejo resultado=new Complejo();

	
	
	
	
	public void start(Stage primaryStage) throws Exception {
	
		operandoTextA1 = new TextField();
		operandoTextA1.setPrefColumnCount(4);
		
		operandoTextA2 = new TextField();
		operandoTextA2.setPrefColumnCount(4);
		
		operandoTextB1 = new TextField();
		operandoTextB1.setPrefColumnCount(4);
		
		operandoTextB2 = new TextField();
		operandoTextB2.setPrefColumnCount(4);
		
		operandoTextC1 = new TextField();
		operandoTextC1.setPrefColumnCount(4);
		
		operandoTextC2 = new TextField();
		operandoTextC2.setPrefColumnCount(4);

		operadorBox = new ComboBox<String>();
		operadorBox.getItems().addAll("+","-","*","/");
		
		HBox operacionA = new HBox(operandoTextA1,new Label("+"),operandoTextA2,new Label(" i"));
		operacionA.setAlignment(Pos.CENTER);
		
		HBox operacionB = new HBox(operandoTextB1,new Label("+"),operandoTextB2,new Label(" i"));
		operacionB.setAlignment(Pos.CENTER);
		
		
		HBox operacionC = new HBox(operandoTextC1,new Label("+"),operandoTextC2,new Label(" i"));
		operacionC.setAlignment(Pos.CENTER);
		
		VBox operacionComboBox = new VBox(operadorBox);
		operacionComboBox.setAlignment(Pos.CENTER);
		
		VBox operacion = new VBox(operacionA,operacionB,new Separator(),operacionC);
		operacion.setAlignment(Pos.CENTER);
		
		HBox root = new HBox(5,operacionComboBox,operacion);
		root.setAlignment(Pos.CENTER);
		
		Scene scene=new Scene(root,320,200);
		
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//bindeos
		operandoTextA1.textProperty().bindBidirectional(A.realProperty(),new NumberStringConverter());
		operandoTextA2.textProperty().bindBidirectional(A.imaginarioProperty(),new NumberStringConverter());
		operandoTextB1.textProperty().bindBidirectional(B.realProperty(),new NumberStringConverter());
		operandoTextB2.textProperty().bindBidirectional(B.imaginarioProperty(),new NumberStringConverter());
		operandoTextC1.textProperty().bindBidirectional(resultado.realProperty(),new NumberStringConverter());
		operandoTextC2.textProperty().bindBidirectional(resultado.imaginarioProperty(),new NumberStringConverter());
		
		operador.bind(operadorBox.getSelectionModel().selectedItemProperty());
		
		operador.addListener((o,ov,nv)->OnoperadorChanged(nv));
		operadorBox.getSelectionModel().selectFirst();
	}

	
	
	private void OnoperadorChanged(String nv) {
		switch(nv) {
		case "+": resultado.realProperty().bind(A.realProperty().add(B.realProperty()));resultado.imaginarioProperty().bind(A.imaginarioProperty().add(B.imaginarioProperty()));break;
		case "-": resultado.realProperty().bind(A.realProperty().subtract(B.realProperty()));resultado.imaginarioProperty().bind(A.imaginarioProperty().subtract(B.imaginarioProperty()));break;
		case "*": resultado.realProperty().bind(A.realProperty().multiply(B.realProperty()).subtract(A.imaginarioProperty().multiply(B.imaginarioProperty())));resultado.imaginarioProperty().bind(A.realProperty().multiply(B.imaginarioProperty()).add(A.imaginarioProperty().multiply(B.realProperty())));break;
		case "/": resultado.realProperty().bind((A.realProperty().multiply(B.realProperty()).add(A.imaginarioProperty().multiply(B.imaginarioProperty()))).divide((B.realProperty().multiply(B.realProperty())).add(B.imaginarioProperty().multiply(B.imaginarioProperty()))));resultado.imaginarioProperty().bind((A.imaginarioProperty().multiply(B.realProperty()).subtract(A.realProperty().multiply(B.imaginarioProperty()))).divide((B.realProperty().multiply(B.realProperty())).add(B.imaginarioProperty().multiply(B.imaginarioProperty()))));break;
		}
	}



	public static void main(String[] args) {
		launch(args);
	}
	
	
}
