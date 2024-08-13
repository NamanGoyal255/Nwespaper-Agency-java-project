package BillGenerator;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import papermaster.MysqlConnector;

public class ViewController {

	Connection con;
	PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker bdu;

    @FXML
    private ComboBox<String> comboMobile;

    @FXML
    private DatePicker lbd;

    @FXML
    private TextField txtBill;

    @FXML
    private TextField txtMissing;

    @FXML
    private TextField txtPapers;

    @FXML
    private TextField txtPrices;

    @FXML
    private TextField txtTotal;

    
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
    void doFetch(ActionEvent event) 
    {

    	playSound();
    	float tot=0;
    	String mob=comboMobile.getSelectionModel().getSelectedItem();
    	try 
    	{
    		pst=con.prepareStatement("Select * from customers where mobile=?");
    		pst.setString(1, mob);
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			String papers=table.getString("spapers");
    			String prices=table.getString("sprices");
    			txtPapers.setText(papers);
    			txtPrices.setText(prices);
    		}
    		
			String []parts=txtPrices.getText().split(",");
			for(String part :parts)
			{
				tot+=Float.parseFloat(part);
			}
			txtTotal.setText(String.valueOf(tot));
			
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp);
    	}
    }
    int getDays()
    {

    	LocalDate ld=lbd.getValue();
    	LocalDate ld2=bdu.getValue();
    	
    	int x=Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(ld, ld2)));
    	return x;

    }

    @FXML
    void doGenerate(ActionEvent event) 
    {
    	playSound();

    	
    	LocalDate ld=lbd.getValue();
    	java.sql.Date ds=java.sql.Date.valueOf(ld);
    	LocalDate ld2=bdu.getValue();
    	java.sql.Date ds2=java.sql.Date.valueOf(ld2);
    	int days=getDays();
//    	System.out.println(days);
    	
    	int missdays=Integer.parseInt(txtMissing.getText());
    	float tot=Float.parseFloat(txtTotal.getText());
    	float pricetotal=(days-missdays)*tot;
  try 
  {
  	   
  	    pst=con.prepareStatement("insert into bills(mobile,datefrom,dateto,bill) values(?,?,?,?)");
  	    pst.setString(1, comboMobile.getSelectionModel().getSelectedItem());
   	    pst.setDate(2, ds);
   	    pst.setDate(3, ds2);
   	    pst.setFloat(4,pricetotal);
   	    txtBill.setText(String.valueOf(pricetotal));
   	    pst.executeUpdate();
   	    
    }
   	 catch(Exception exp)
 	 {
 		System.out.println(exp);
	 }
    	
  }
    

    @FXML
    void doLastDate(ActionEvent event) 
    {
    	playSound();

    	try
    	{
    		pst=con.prepareStatement("Select count(*) as no from bills where mobile=?");
    		pst.setString(1, comboMobile.getSelectionModel().getSelectedItem());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			int i=table.getInt("no");
    			if(i==0)
    			{
    				pst=con.prepareStatement("Select dos from customers where mobile=?");
    				pst.setString(1, comboMobile.getSelectionModel().getSelectedItem());
    				ResultSet rs=pst.executeQuery();
    				while(rs.next())
    				{
    					java.sql.Date dobb=rs.getDate("dos");
    					lbd.setValue(dobb.toLocalDate());
    				}
    			}
    			else
    			{
    				pst=con.prepareStatement("Select dateto from bills where mobile=? ORDER BY dateto DESC LIMIT 0,1");
    				pst.setString(1, comboMobile.getSelectionModel().getSelectedItem());
    				ResultSet rs=pst.executeQuery();
    				while(rs.next())
    				{
    					java.sql.Date dobb=rs.getDate("dateto");
    					lbd.setValue(dobb.toLocalDate());
    				}
    			}
    				
    		}
    	}
    	 catch(Exception exp)
    	 {
    		System.out.println(exp);
    	 }
       	
    	 
    }
    void FillMobile()
    {
    	
    	try
    	{
    		pst=con.prepareStatement("Select distinct mobile from customers");
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			comboMobile.getItems().add(table.getString("mobile"));
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
    FillMobile();

    }

}
