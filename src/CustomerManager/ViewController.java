package CustomerManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import papermaster.MysqlConnector;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
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
    private ComboBox<String> comboAreas;

    @FXML
    private DatePicker dos;

    @FXML
    private ListView<String> listPaperavail;

    @FXML
    private ListView<String> listPricesavail;

    @FXML
    private ListView<String> listSelectedpapers;

    @FXML
    private ListView<String> listSelectedprices;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtHawker;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    void doFetch(ActionEvent event) 
    {
    	playSound();
    	try 
    	{
    		pst=con.prepareStatement("select * from customers where mobile=?");
    		pst.setString(1, txtMobile.getText());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			txtName.setText(table.getString("name"));
    			txtHawker.setText(table.getString("hawker"));
    			txtEmail.setText(table.getString("email"));
    			txtAddress.setText(table.getString("address"));
    			java.sql.Date dobb= table.getDate("dos");
    			dos.setValue(dobb.toLocalDate());
    			
    			
    			String selpapers=table.getString("spapers");
    			String []parts=selpapers.split(",");
    			for(String part :parts)
    			{
    				listSelectedpapers.getItems().add(part);
    			}
    			
    			String selprices=table.getString("sprices");
    			String []parts2=selprices.split(",");
    			for(String part2 :parts2)
    			{
    				listSelectedprices.getItems().add(part2);
    			}
    		}
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp);
    	}

    }

    @FXML
    void doKaro(MouseEvent event) 
    {
    	playSound();
    	if(event.getClickCount()==2)
    	{
    		
    		ObservableList<String> selItems=listPaperavail.getSelectionModel().getSelectedItems();
    		listSelectedpapers.getItems().addAll(selItems);
        	int index=listPaperavail.getSelectionModel().getSelectedIndex();
        	listSelectedprices.getItems().add(listPricesavail.getItems().get(index));
    	}

    }
    @FXML
    void dokaro2(MouseEvent event)
    {
    	playSound();

    	if(event.getClickCount()==2)
    	{
    		int index=listSelectedpapers.getSelectionModel().getSelectedIndex();
//    		ObservableList<String> selItems=listSelectedpapers.getSelectionModel().getSelectedItems();
    		listSelectedpapers.getItems().remove(index);
    		
    		listSelectedprices.getItems().remove(index);
    	}
    }

    @FXML
    void dohawkerfill(ActionEvent event)
    {
    	playSound();

    	txtHawker.setText("");
    	String area=comboAreas.getSelectionModel().getSelectedItem();
    	try {
    		pst=con.prepareStatement("select hname from hawkers where alloareas like ? OR alloareas like ? ");
    		pst.setString(1, area+"%");
    		pst.setString(2, "%"+(","+area)+"%");
    		
    		
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			txtHawker.setText(table.getString("hname"));
    		}
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp);
    	}
    }
    @FXML
    void doSubscribe(ActionEvent event) 
    {
    	playSound();
    	
    	LocalDate ld=dos.getValue();
    	java.sql.Date db=java.sql.Date.valueOf(ld);

    	 ObservableList<String>arylist=listSelectedpapers.getItems();
    	 String aryselPapers="";
    	 for (String string :arylist)
    	 {
    		 aryselPapers+=string+",";
    	 }
    	 if(aryselPapers.endsWith(","))
    	 {
    		 aryselPapers=aryselPapers.substring(0, aryselPapers.length()-1);
    	 }
    	
    	 ObservableList<String>arypricelist=listSelectedprices.getItems();
    	 String aryselPrices="";
    	 for (String string :arypricelist)
    	 {
    		 aryselPrices+=string+",";
    	 }
    	 if(aryselPrices.endsWith(","))
    	 {
    		 aryselPrices=aryselPrices.substring(0, aryselPrices.length()-1);
    	 }
    	System.out.println(aryselPapers);
    	System.out.println(aryselPrices);
    	 
    	try{
			pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?)");
						
			pst.setString(1, txtMobile.getText());
			pst.setString(2, txtName.getText());
			pst.setString(3, aryselPapers);
			pst.setString(4, aryselPrices);
			pst.setString(5, comboAreas.getSelectionModel().getSelectedItem());
			pst.setString(6, txtHawker.getText());
			pst.setString(7, txtEmail.getText());
			pst.setString(8, txtAddress.getText());
			pst.setDate(9,db);
			pst.executeUpdate();

		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}

    }

    @FXML
    void doUnsubscribe(ActionEvent event) 
    {
    	playSound();

    	try
    	{
    		pst=con.prepareStatement("delete from customers where mobile=?");
    		pst.executeUpdate();
    		txtName.setText("");
    		
			txtHawker.setText("");
			txtEmail.setText("");
			txtAddress.setText("");
			dos.setValue(null);
			comboAreas.getSelectionModel().selectFirst();
			listSelectedprices.getItems().clear();
			listSelectedpapers.getItems().clear();
    	}
      catch (Exception e)
    	{
			
			e.printStackTrace();
		}	
    }
    void clear()
    {
    	//System.out.println("Hello");
    	txtName.setText("");
		txtMobile.setText("");
		txtHawker.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		dos.setValue(null);
		comboAreas.getItems().clear();
		listSelectedprices.getItems().clear();
		listSelectedpapers.getItems().clear();
    }
    @FXML
    void doclear(MouseEvent event)
    {
    	playSound();

    	clear();
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	playSound();


    	LocalDate ld=dos.getValue();
    	java.sql.Date db=java.sql.Date.valueOf(ld);

    	 ObservableList<String>arylist=listSelectedpapers.getItems();
    	 String aryselPapers="";
    	 for (String string :arylist)
    	 {
    		 aryselPapers+=string+",";
    	 }
    	 if(aryselPapers.endsWith(","))
    	 {
    		 aryselPapers=aryselPapers.substring(0, aryselPapers.length()-1);
    	 }
    	
    	 ObservableList<String>arypricelist=listSelectedprices.getItems();
    	 String aryselPrices="";
    	 for (String string :arypricelist)
    	 {
    		 aryselPrices+=string+",";
    	 }
    	 if(aryselPrices.endsWith(","))
    	 {
    		 aryselPrices=aryselPrices.substring(0, aryselPrices.length()-1);
    	 }
    	System.out.println(aryselPapers);
    	System.out.println(aryselPrices);
    	 
    	try{
			pst=con.prepareStatement("update customers set name=?,spapers=?,sprices=?,area=?,hawker=?,email=?,address=?,dos=? where mobile=?");
						
			pst.setString(9, txtMobile.getText());
			pst.setString(1, txtName.getText());
			pst.setString(2, aryselPapers);
			pst.setString(3, aryselPrices);
			pst.setString(4, comboAreas.getSelectionModel().getSelectedItem());
			pst.setString(5, txtHawker.getText());
			pst.setString(6, txtEmail.getText());
			pst.setString(7, txtAddress.getText());
			pst.setDate(8,db);
			pst.executeUpdate();

		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    void dofill() 
    {
    	try
    	{
			pst=con.prepareStatement("select * from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				listPaperavail.getItems().add(table.getString("paper"));
				float prc=table.getFloat("price");
				listPricesavail.getItems().add(String.valueOf(prc));
			}
			
			

		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    void doFillArea()
    {
    	try{
			pst=con.prepareStatement("select distinct area from areas");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			
			
			
			comboAreas.getItems().add(table.getString("area"));
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }
    @FXML
    void initialize() 
    {
    	con=MysqlConnector.doConnect();
    	if(con==null)
			System.out.println("Connection Problem");
	else
		System.out.println("Connected###");  
    //ArrayList<String> items=new ArrayList<String>(Arrays.asList("Select Area","Namdev Road","Ajit Road","Dav Road","Power House Road","Model Town","Green City","Near BusStand"));
      	
      //	comboAreas.getItems().addAll(items);
      	dofill();
      	doFillArea();

    }

}
