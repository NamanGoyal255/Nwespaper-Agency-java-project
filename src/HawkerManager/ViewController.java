package HawkerManager;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import papermaster.MysqlConnector;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboAreas;

    @FXML
    private ComboBox<String> comboHname;

    @FXML
    private Label lblpicpath;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAllocated;

    @FXML
    private TextField txtMobile;
    @FXML
    private ImageView picPrev;

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
    void doEnroll(ActionEvent event) //#####################################
    {
    	String aa=txtAllocated.getText();
    	playSound();
    	
    	// ##########################################################
    	
    	try {
    		String []parts=aa.split(",");
    		for(String part :parts)
    		{
    			pst=con.prepareStatement("Update areas set areastatus=? where area=?");
    			pst.setInt(1, 1);
    			pst.setString(2, part);
    			pst.executeUpdate();
    			
    		}
    		comboAreas.getItems().clear();
			doFillArea();
    
    	} 
    	catch (SQLException e) 
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    	//##############################################################
    	
    	//##############################################################
    	
    	try
    	{
    		String ar=txtAllocated.getText();
    		String []parts=ar.split(",");
    		for(String part :parts)
    		{
    			pst=con.prepareStatement("Update customers set hawker=? where area=?");
    			pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
    			pst.setString(2, part);
    			pst.executeUpdate();
    			
    		}
    		
    	}
    	catch (Exception e) 
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    	
    	//##############################################################
    	
    	
    	
    	LocalDate ld=LocalDate. now();
    	java.sql.Date db=java.sql.Date.valueOf(ld);
    	try
    	{
    		txtAllocated.setText(aa);
			pst=con.prepareStatement("insert into hawkers values(?,?,?,?,?,?)");
			pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
			pst.setString(2, txtMobile.getText());
			pst.setString(3, txtAddress.getText());
			pst.setString(4, txtAllocated.getText());
			pst.setString(5, lblpicpath.getText());
			pst.setDate(6,db);
			pst.executeUpdate();
			comboHname.getItems().clear();
    		doFillNames();
    		txtAllocated.setText(aa);
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}

    }	

    @FXML
    void doFire(ActionEvent event)//#####################
    {
    	String aa="";
    	playSound();
    	//############################################################################################################################
    	try
    	{
    		pst=con.prepareStatement("select alloareas from hawkers where hname=?");
    		pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			 aa=table.getString("alloareas");
    		}
    		String []parts=aa.split(",");
			for(String part :parts)
			{
				pst=con.prepareStatement("Update areas set areastatus=? where area=?");
				pst.setInt(1, 0);
				pst.setString(2, part);
				pst.executeUpdate();
				comboAreas.getItems().clear();
				doFillArea();
			}

    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}
//############################################################################################################################
    	
    	//############################################################################################################################
    	try 
    	{
    		pst=con.prepareStatement("select alloareas from hawkers where hname=?");
    		pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			 aa=table.getString("alloareas");
    		}
    		String []parts=aa.split(",");
			for(String part :parts)
			{
				pst=con.prepareStatement("Update customers set hawker=? where area=?");
				pst.setString(1, null);
				pst.setString(2, part);
				pst.executeUpdate();
//				comboAreas.getItems().clear();
//				doFillArea();
			}

    	}
    	
    	
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}
    	//############################################################################################################################
try{
        	
        	pst=con.prepareStatement("delete from hawkers where hname=?");
        	pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
        		pst.executeUpdate();
        		comboHname.getItems().clear();
        		doFillNames();
        		txtMobile.setText(null);
        		txtAddress.setText(null);
        		txtAllocated.setText("");
        		
        		 Image img=new Image(new FileInputStream("D:\\pics\\aadhaarcardupdate-1674708827.jpeg"));

        		 
           	    picPrev.setImage(img);
           	    
    	}
        	
        	catch(Exception exp)
        	{
        		System.out.println(exp.toString());
        	}

    }

    @FXML
    void doNew(ActionEvent event) 
    {

    	playSound();
    	try {
    		comboAreas.getItems().clear();
    		comboHname.getItems().clear();
    	txtMobile.setText(null);
		txtAddress.setText(null);
		txtAllocated.setText("");
		Image img=new Image(new FileInputStream("D:\\pics\\aadhaarcardupdate-1674708827.jpeg"));

		
		 
   	    picPrev.setImage(img);
   	    doFillNames();
   	    doFillArea();
    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}
    }

    @FXML
    void doSearch(ActionEvent event)
    {

    	playSound();
      try{
        	
        	pst=con.prepareStatement("select * from hawkers where hname=?");
        	pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
        	ResultSet table=pst.executeQuery();
        		while(table.next())
        		{
        			txtMobile.setText(table.getString("mobile"));
        			txtAddress.setText(table.getString("address"));
        			txtAllocated.setText(table.getString("alloareas"));
        			String picPath= table.getString("picpath");
        			lblpicpath.setText(picPath);
        			picPrev.setImage(new Image(new FileInputStream(picPath)));
        		}
        		
        		
        		
        		 
           	    
    	}
        	
        	catch(Exception exp)
        	{
        		System.out.println(exp.toString());
        	}
    }

    @FXML
    void doUpdate(ActionEvent event) //####################
    {
    	
    	playSound();
    	
    	
    	//##############################################################
    	String tt="";
    	try
    	{
    		pst=con.prepareStatement("select alloareas from hawkers where hname=?");
    		pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			 tt=table.getString("alloareas");
    		}
    		String []parts=tt.split(",");
			for(String part :parts)
			{
				pst=con.prepareStatement("Update areas set areastatus=? where area=?");
				pst.setInt(1, 0);
				pst.setString(2, part);
				pst.executeUpdate();
//				comboAreas.getItems().clear();
//				doFillArea();
			}
			comboAreas.getItems().clear();
			doFillArea();

    	}
    	catch(Exception exp)
    	{
    		System.out.println(exp.toString());
    	}
    	//##############################################################
    	
// ##########################################################
    	
    	
    	String aa=txtAllocated.getText();
    	try {
    		String []parts=aa.split(",");
    		for(String part :parts)
    	   {
    			pst=con.prepareStatement("Update areas set areastatus=? where area=?");
    			pst.setInt(1, 1);
    			pst.setString(2, part);
    			pst.executeUpdate();
    			
    		}
    		comboAreas.getItems().clear();
			doFillArea();
    		
    		
    		
    
    	} 
    	catch (SQLException e) 
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    	
    	//##############################################################
    	
    	
    	
    	//###############################################################################
    	
    	try
    	{
    		pst=con.prepareStatement("select alloareas from hawkers where hname=?");
    		pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
    		ResultSet table=pst.executeQuery();
    		while(table.next())
    		{
    			 tt=table.getString("alloareas");
    		}
    		String []parts=tt.split(",");
			for(String part :parts)
			{
				pst=con.prepareStatement("Update customers set hawker=? where area=?");
				pst.setString(1, null);
				pst.setString(2, part);
				pst.executeUpdate();
//				comboAreas.getItems().clear();
//				doFillArea();
			}
//			comboAreas.getItems().clear();
//			doFillArea();
    	}
    	
        catch (Exception e) 
    	{
			
			e.printStackTrace();
		}
    	//###############################################################################
    	
// ##########################################################
    	
    	
    	String ab=txtAllocated.getText();
    	try {
    		String []parts=ab.split(",");
    		for(String part :parts)
    	   {
    			pst=con.prepareStatement("Update customers set hawker=? where area=?");
    			pst.setString(1, comboHname.getSelectionModel().getSelectedItem());
    			pst.setString(2, part);
    			pst.executeUpdate();
    			
    		}
//    		comboAreas.getItems().clear();
//			doFillArea();
    		
    		
    		
    
    	} 
    	catch (SQLException e) 
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    	
    	//##############################################################
    	
    	

    	try
    	{
			pst=con.prepareStatement("update hawkers set mobile=?,address=?,alloareas=?,picpath=? where hname=?");
			pst.setString(5, comboHname.getSelectionModel().getSelectedItem());
			pst.setString(1, txtMobile.getText());
			pst.setString(2, txtAddress.getText());
			pst.setString(3, txtAllocated.getText());
			pst.setString(4, lblpicpath.getText());

			 pst.executeUpdate();
//			comboHname.getItems().clear();
//    		doFillNames();
		} 
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	
    	
    }
//    String item="";
    @FXML
    void comboonSelect2(ActionEvent event) 
    {
    	playSound();
    	if(txtAllocated.getText() =="")
    	{
    		txtAllocated.appendText(comboAreas.getSelectionModel().getSelectedItem());
    		
    	}
    	else {
    	txtAllocated.appendText(","+comboAreas.getSelectionModel().getSelectedItem());
    	
    	}

    	
    	
    }
    
    @FXML
    void comboonSelect1(ActionEvent event) {

    }
    @FXML
    void doUpload(ActionEvent event) 
    {
    	playSound();

    	FileChooser fileChooser = new FileChooser();
      	 fileChooser.setTitle("Open Resource File");
      	 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif") );
      	 File selectedFile = fileChooser.showOpenDialog(null);
      	 
      	 if (selectedFile != null) {
      	    lblpicpath.setText(selectedFile.getPath());
      	    Image img=new Image(selectedFile.toURI().toString());
      	    System.out.println(selectedFile.toURI().toString());
      	   // picPrev.setImage(new Image(new FileInputStream(selectedFile)));
      	    picPrev.setImage(img);
      	 }
      	 else
      	 {
      		lblpicpath.setText("\"D:\\pics\\aadhaarcardupdate-1674708827.jpeg\"");
      	 }
    }

    void doFillNames()
    {
    	
    	try{
			pst=con.prepareStatement("select distinct hname from hawkers");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			
			
			
			comboHname.getItems().add(table.getString("hname"));
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
		}
    }
    void doFillArea()
    {
    	try{
			pst=con.prepareStatement("select distinct area from areas where areastatus=0");//#####################
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
    void initialize() {
    	con=MysqlConnector.doConnect();
    	if(con==null)
			System.out.println("Connection Problem");
	else
		System.out.println("Connected###");
    	 lblpicpath.setText("\"D:\\pics\\aadhaarcardupdate-1674708827.jpeg\"");
    	 
    	 
    	
    	 //ArrayList<String> items=new ArrayList<String>(Arrays.asList("Ajit Road","Dav Road","Power House Road","Model Town","Green City","Near BusStand"));
     	
     	//comboAreas.getItems().addAll(items);
     	
     	doFillNames();
     	doFillArea();

    }

}
