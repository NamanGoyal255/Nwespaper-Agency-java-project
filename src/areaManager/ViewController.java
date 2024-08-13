package areaManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import BillCollector.*;
public class ViewController {

	Connection con;
	PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> ComboArea;

	 URL url;
	   	Media media;
	   	MediaPlayer mediaplayer;
	   	AudioClip audio;
	   	void playSound(){
	       	url=getClass().getResource("mixkit-arcade-game-jump-coin-216.wav");
	   		audio=new AudioClip(url.toString());
	   		audio.play();
	   	}
    
    @FXML
    void doAdd(ActionEvent event) 
    {
    	playSound();

    	
    	String area=ComboArea.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("insert into areas values(?)");
			pst.setString(1,area);
			
			pst.executeUpdate();
			ComboArea.getItems().clear();
    		doFillAreas();
    		
		}
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    }

    @FXML
    void doRemove(ActionEvent event) //#############################################################
    {
    	
    	String del="";
    	playSound();

     try{
    	  del=ComboArea.getSelectionModel().getSelectedItem();
        	
        	pst=con.prepareStatement("delete from areas where area=?");
        	pst.setString(1, ComboArea.getSelectionModel().getSelectedItem());
        		pst.executeUpdate();
        		ComboArea.getItems().clear();
        		doFillAreas();
        		
    	}
        	
      catch(Exception exp)
       {
        		System.out.println(exp.toString());
       }
     //######################################################################
     try
     {
    	 
    	pst=con.prepareStatement("Select * from hawkers where alloareas like ?"); 
    	pst.setString(1, "%"+del+"%");
    	ResultSet table=pst.executeQuery();
    	while(table.next())
    	{
    		String sg="";
    		String ng=table.getString("alloareas");
    		String name=table.getString("hname");
    		String []parts=ng.split(",");
    		for(String part :parts)
    		{
    			if(!part.equals(del))
    			{
    				sg+=part+",";
    			}
    		}
    		if(sg.endsWith(","))
    		{
    			sg=sg.substring(0, sg.length()-1);
    		}
    		System.out.println(sg);
    		pst=con.prepareStatement("update hawkers set alloareas=? where hname= ?");
    		pst.setString(1, sg);
    		pst.setString(2, name);
    		pst.executeUpdate();
    		
    	}
     }
     catch(Exception exp)
     {
      		System.out.println(exp.toString());
     }
     
     
     // ##########################################################################
     
     //######################################################################
     try
     {
    	 
    	pst=con.prepareStatement("update customers set area =? ,hawker=? where area=?"); 
    	pst.setString(1, null);
    	pst.setString(2, null);
    	pst.setString(3, del);
    	pst.executeUpdate();
    	
     }
     catch(Exception exp)
     {
      		System.out.println(exp.toString());
     }
     
     
     // ##########################################################################
 
     
     
     
    }

    void doFillAreas()
    {
    	try{
			pst=con.prepareStatement("select distinct area from areas");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			
			
			
			ComboArea.getItems().add(table.getString("area"));
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }

    @FXML
    void initialize() {
        con=MysqlConnector.doconnect();
        doFillAreas();

    }

}
