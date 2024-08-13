package AdminDashBoard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class NgController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lblHeading;
    
    URL url;
   	Media media;
   	MediaPlayer mediaplayer;
   	AudioClip audio;
   	void playSound(){
       	url=getClass().getResource("s1.wav");
   		audio=new AudioClip(url.toString());
   		audio.play();
       }

    @FXML
    void MTD(ActionEvent event) {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/AboutUs/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
			 
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void doAm(ActionEvent event) {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/areaManager/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void doBc(ActionEvent event) {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/BillCollector/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void doBg(ActionEvent event) {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/BillGenerator/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void doBs(ActionEvent event) {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/billBoard/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void doCg(ActionEvent event) {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/CustomerPanel/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
//    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void doCustomerManager(ActionEvent event)
    {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/CustomerManager/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void doDh(ActionEvent event) 
    {

    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/hawkerTable/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void doHawkerManager(ActionEvent event) 
    {

    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/HawkerManager/View.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void doPm(ActionEvent event) 
    {
    	playSound();
    	try
    	{
    	 	Parent root=FXMLLoader.load(getClass().getResource("/papermaster/view.fxml")); 
    	 	Scene scene=new Scene(root);
    	 	Stage stage=new Stage();
    	 	stage.setScene(scene);
    	 	stage.show();
    	 	
//    	 	Scene scene1=(Scene)lblHeading.getScene();
//			   scene1.getWindow().hide();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

    }

    @FXML
    void initialize() {

    }

}

