package CustomerPanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import BillCollector.*;




public class ViewController {

	ObservableList<CustomerBean>	ary=FXCollections.observableArrayList();
	Connection con;
	PreparedStatement pst;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private ComboBox<String> comboPaper;

    @FXML
    private TableView<CustomerBean> tableCustomer;

    
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
    void doExport(ActionEvent event) 
    {
playSound();
try {
	writeExcel();
//	txtPname.setText("Exported to excel....");
	System.out.println("Exported");
}
catch(Exception exp) {
	System.out.println(exp.toString());
}
    	
    }
    
  //==================================================================================================
    public void writeExcel() throws IOException {
    	Writer writer = null;
    	try {
    		File file = new File("D:\\Export\\Users4.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		String text="Mobile,Name,Selected Papers,Email,Address,Start Date\n";
    		writer.write(text);
    		for(CustomerBean p : ary) {
    			text = p.getMobile() +"," + p.getName() + "," + p.getSpapers()+ "," + p.getEmail() +"," + p.getAddress()+","+p.getDos() + "\n";
    			writer.write(text);
    		}
    	}
    	catch(Exception exp) {
    		exp.printStackTrace();
    	}
    	finally {
    		writer.flush();
    		writer.close();
    	}
    }

    
  //=====================================================================================================  

    @FXML
    void doFetch(ActionEvent event) 
    {
    	playSound();

    	TableColumn<CustomerBean, String> mbl=new TableColumn<CustomerBean, String>("Mobile");//any thing
    	mbl.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mbl.setMinWidth(80);
    	
    	TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("Customer Name");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(70);
    	
    	TableColumn<CustomerBean, String> spapers=new TableColumn<CustomerBean, String>("Selected Papers");//any thing
    	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	spapers.setMinWidth(200);
    	
    	TableColumn<CustomerBean, String> email=new TableColumn<CustomerBean, String>("Email Id");//any thing
    	email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	email.setMinWidth(200);
    	
    	TableColumn<CustomerBean, String> address=new TableColumn<CustomerBean, String>("Address");//any thing
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(200);
    	
    	TableColumn<CustomerBean, String> dos=new TableColumn<CustomerBean, String>("Date Of Start");//any thing
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
        dos.setMinWidth(100);
        
        tableCustomer.getColumns().clear();
        
        tableCustomer.getColumns().add(mbl);
        tableCustomer.getColumns().add(name);
        tableCustomer.getColumns().add(spapers);
        tableCustomer.getColumns().add(email);
        tableCustomer.getColumns().add(address);
        tableCustomer.getColumns().add(dos);
        tableCustomer.setItems(FetchAllCustomers2());
    	
    
    }

    @FXML
    void doFilter(ActionEvent event) 
    {
    	playSound();

    	TableColumn<CustomerBean, String> mbl=new TableColumn<CustomerBean, String>("Mobile");//any thing
    	mbl.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mbl.setMinWidth(80);
    	
    	TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("Customer Name");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	name.setMinWidth(70);
    	
    	TableColumn<CustomerBean, String> spapers=new TableColumn<CustomerBean, String>("Selected Papers");//any thing
    	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	spapers.setMinWidth(200);
    	
    	TableColumn<CustomerBean, String> email=new TableColumn<CustomerBean, String>("Email Id");//any thing
    	email.setCellValueFactory(new PropertyValueFactory<>("email"));
    	email.setMinWidth(200);
    	
    	TableColumn<CustomerBean, String> address=new TableColumn<CustomerBean, String>("Address");//any thing
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	address.setMinWidth(200);
    	
    	TableColumn<CustomerBean, String> dos=new TableColumn<CustomerBean, String>("Date Of Start");//any thing
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
        dos.setMinWidth(100);
        
        tableCustomer.getColumns().clear();
        
        tableCustomer.getColumns().add(mbl);
        tableCustomer.getColumns().add(name);
        tableCustomer.getColumns().add(spapers);
        tableCustomer.getColumns().add(email);
        tableCustomer.getColumns().add(address);
        tableCustomer.getColumns().add(dos);
        tableCustomer.setItems(FetchAllCustomers());
    }

    ObservableList<CustomerBean> FetchAllCustomers() 
    {
//    	ObservableList<CustomerBean>	ary=FXCollections.observableArrayList();
    	ary.clear();
    	try {
    	
    	
    		pst = con.prepareStatement("select * from customers where area=?");
    		pst.setString(1, comboArea.getSelectionModel().getSelectedItem());
    		
    		ResultSet table=pst.executeQuery();
    	
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("name");
	    		String DOS = String.valueOf(table.getDate("dos").toLocalDate());
	    		String spapers=table.getString("spapers");
	    		String email=table.getString("email");
	    		String address=table.getString("address");
	    		//System.out.println(name);
	    		CustomerBean ref=new CustomerBean( mno,name, spapers,email,address, DOS);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);
	    		
    		}
    	

    	}
    	catch(Exception ex)
    	
    	   { 
    		ex.printStackTrace(); 
    		}
    	
    		return ary;
    }
    ObservableList<CustomerBean> FetchAllCustomers2() 
    {
    	//ObservableList<CustomerBean>	ary=FXCollections.observableArrayList();
    	ary.clear();
    	String spaper=comboPaper.getSelectionModel().getSelectedItem();
    	try {
    	
    	
    		pst = con.prepareStatement("select * from customers where spapers like ?");
    		pst.setString(1, "%"+spaper+"%");
    		
    		ResultSet table=pst.executeQuery();
    	
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("name");
	    		String DOS = String.valueOf(table.getDate("dos").toLocalDate());
	    		String spapers=table.getString("spapers");
	    		String email=table.getString("email");
	    		String address=table.getString("address");
	    		//System.out.println(name);
	    		CustomerBean ref=new CustomerBean( mno,name, spapers,email,address, DOS);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);
	    		
    		}
    	

    	}
    	catch(Exception ex)
    	
    	   { 
    		ex.printStackTrace(); 
    		}
    	
    		return ary;
    }
    
    void doFillAreas()
    {
    	try{
			pst=con.prepareStatement("select distinct area from areas");
		ResultSet table=pst.executeQuery(); //array of objects
		while(table.next())
		{
			
			
			
			comboArea.getItems().add(table.getString("area"));
		}
		
		}	
		catch(Exception exp)
		{
			System.out.println(exp);
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
        

    	con=MysqlConnector.doconnect();
    	doFillAreas();
    	doFillPapers();
    }

}
