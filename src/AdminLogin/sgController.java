package AdminLogin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class sgController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtPassword;
    
    //=================================
    URL url;
	Media media;
	MediaPlayer mediaplayer;
	AudioClip audio;
	void playSound(){
    	url=getClass().getResource("s1.wav");
		audio=new AudioClip(url.toString());
		audio.play();
    }
    
    
    
    //=============================

    @FXML
    void doLogin(ActionEvent event) 
    {
    	playSound();
    	if(txtPassword.getText().equals("naman@3246"))
    	{
    		try
        	{
        	 	Parent root=FXMLLoader.load(getClass().getResource("/AdminDashBoard/Ng.fxml")); 
        	 	Scene scene=new Scene(root);
        	 	Stage stage=new Stage();
        	 	stage.setScene(scene);
        	 	stage.show();
        	 	
//        	 	Scene scene1=(Scene)lblHeading.getScene();
//    			   scene1.getWindow().hide();
    			 
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	
    	}

    }
    	else
    	{
    		System.out.println("Enter Valid Password");
    	}
    }

    @FXML
    void initialize() {
        

    }

}
