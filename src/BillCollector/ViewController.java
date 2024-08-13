package BillCollector;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import papermaster.MysqlConnector;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private DatePicker doe;

    @FXML
    private DatePicker dos;

    @FXML
    private Label lblFinal;
   

    @FXML
    private TextField txtAmount;

    PreparedStatement pst;
    Connection con;
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
    void doBill(ActionEvent event) 
    {
    	playSound();
    	float prc=0;

    	try
    	{
    		pst=con.prepareStatement("Select * from bills where mobile=? and billstatus=0");
    		pst.setString(1, txtMobile.getText());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			 prc+=table.getFloat("bill");
    			 txtAmount.setText(String.valueOf(prc));

    			java.sql.Date de=table.getDate("dateto");
    			
    			doe.setValue(de.toLocalDate());
    		}
    		if(prc==0)
    		{
    			txtAmount.setText(String.valueOf(prc));
    			lblFinal.setText("Bill  Already Paid");
    		}
    		
    		
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp);
    	}
    	try
    	{
    		pst=con.prepareStatement("Select * from bills where mobile=? and billstatus=0 ORDER BY datefrom limit 1");
    		pst.setString(1, txtMobile.getText());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
                java.sql.Date ds=table.getDate("datefrom");
    			
    			dos.setValue(ds.toLocalDate()); 
    		}
    		
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp);
    	}
    	
    	
    }

    @FXML
    void doPayment(ActionEvent event) 
    {

    	playSound();
    	try
    	{
    		pst=con.prepareStatement("Update bills set billstatus =? where mobile=?");
    		pst.setString(2, txtMobile.getText());
    		pst.setInt(1, 1);
    		int count=pst.executeUpdate();
    		if(count!=0)
    		lblFinal.setText("Bill Paid Successfully==");
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp);
    	}
    }

   

    @FXML
    void initialize() {
   
    	con=MysqlConnector.doConnect();
        if(con==null)
    			System.out.println("Connection Problem");
    	else
    		System.out.println("Connected###"); 
       
lblFinal.setText("");
    }

}
