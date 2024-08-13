package papermaster;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class viewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboPaper;

    @FXML
    private TextField txtPrice;
    Connection con;
    PreparedStatement pst;
    
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
    void doAvail(ActionEvent event) 
    {
    	playSound();
    	float prc=Float.parseFloat(txtPrice.getText());
    	String newpaper=comboPaper.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("insert into papers values(?,?)");
			pst.setString(1,newpaper);
			pst.setFloat(2, prc);
			pst.executeUpdate();
			comboPaper.getItems().clear();
    		doFillPapers();
    		
		}
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doEdit(ActionEvent event) 
    {
    	String paper=comboPaper.getSelectionModel().getSelectedItem();//##
    	int count=0;//##
    	int count2=0;//###
    	playSound();
    	float prc=Float.parseFloat(txtPrice.getText());
    //##################################################################################
    	String spap="";
    	String sprc="";
    	try 
    	{
    		pst=con.prepareStatement("select * from customers where spapers like ?");
    		pst.setString(1, "%"+comboPaper.getSelectionModel().getSelectedItem()+"%");
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			spap="";
    			sprc="";
    			spap=table.getString("spapers");
    			sprc=table.getString("sprices");
    			String mbl=table.getString("mobile");
    			String []parts=spap.split(",");
    			
    			for(String part:parts)
    			{
    				if(!paper.equals(part))
    				{
    					count++;
    				}
    				else if(paper.equals(part))
    				{
    					break;
    				}
    			}
    			String []part2=sprc.split(",");
    			String fin="";
    			for(String part:part2)
    			{
    				if(count!=count2)
    				{
    					fin+=part+",";
    					count2++;
    				}
    				else
    				{
    					fin+=txtPrice.getText()+",";
    					count2++;
    				}
    			}
    			if(fin.endsWith(","))
    			{
    				fin=fin.substring(0, fin.length()-1);
    			}
    			pst=con.prepareStatement("Update customers set sprices=? where mobile=?");
    			pst.setString(1, fin);
    			pst.setString(2, mbl);
    			pst.executeUpdate();
    		}
    	}
    	catch (Exception e) 
    	{
			
			e.printStackTrace();
		}
    //##################################################################################
    	
    	try {
			pst=con.prepareStatement("Update papers set price=? where paper=?");
			pst.setFloat(1, prc);
			pst.setString(2, comboPaper.getSelectionModel().getSelectedItem());
			pst.executeUpdate();
			
		}
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}

    }

    @FXML
    void doSearch(ActionEvent event) 
    {
    	playSound();
    	try {
			pst=con.prepareStatement("Select * from papers where paper=?");
			pst.setString(1, comboPaper.getSelectionModel().getSelectedItem());
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				float prc=table.getFloat("price");
				txtPrice.setText(String.valueOf(prc));
			}
			
			
		}
    	catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}

    }

    @FXML
    void doWithdraw(ActionEvent event) 
    {
    	playSound();
    	try{
        	
        	pst=con.prepareStatement("delete from papers where paper=?");
        	pst.setString(1, comboPaper.getSelectionModel().getSelectedItem());
        		pst.executeUpdate();
        		comboPaper.getItems().clear();
        		doFillPapers();
        		txtPrice.setText(null);
    	}
        	
        	catch(Exception exp)
        	{
        		System.out.println(exp.toString());
        	}
    	
    }
    void doFillPapers()
    {
    	try{
			pst=con.prepareStatement("select distinct paper from papers");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			
			
			
			comboPaper.getItems().add(table.getString("paper"));
		}
		
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

   doFillPapers(); 	
    }

}
