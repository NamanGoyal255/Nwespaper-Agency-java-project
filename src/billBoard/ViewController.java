package billBoard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ViewController {

	ObservableList<BillBean> ary=FXCollections.observableArrayList();
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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup occBill;

    @FXML
    private RadioButton radAll;

    @FXML
    private RadioButton radPaid;

    @FXML
    private RadioButton radPending;

    @FXML
    private TableView<BillBean> tableData;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMobile;

    @FXML
    void doBillHistory(ActionEvent event) 
    {

    	playSound();
    	TableColumn<BillBean, String> mbl=new TableColumn<BillBean, String>("Mobile");//any thing
    	mbl.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mbl.setMinWidth(80);
    	
    	
    	
    	
    	
    	
    	
    	TableColumn<BillBean, String> dos=new TableColumn<BillBean, String>("Date From");//any thing
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
        dos.setMinWidth(100);
        
        TableColumn<BillBean, String> doe=new TableColumn<BillBean, String>("Date To");//any thing
    	doe.setCellValueFactory(new PropertyValueFactory<>("doe"));
    	doe.setMinWidth(70);
        
    	TableColumn<BillBean, Float> bill=new TableColumn<BillBean, Float>("Bill");//any thing
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
    	bill.setMinWidth(70);
    	
    	TableColumn<BillBean, String> billstatus=new TableColumn<BillBean, String>("Bill Status");//any thing
    	billstatus.setCellValueFactory(new PropertyValueFactory<>("billstatus"));
    	billstatus.setMinWidth(70);
    	
    	
    	tableData.getColumns().clear();
        
    	tableData.getColumns().add(mbl);
    	tableData.getColumns().add(dos);
    	tableData.getColumns().add(doe);
    	tableData.getColumns().add(bill);
    	tableData.getColumns().add(billstatus);
    	tableData.setItems(FetchAllBill());
    }

    @FXML
    void doExport(ActionEvent event) {
    	

    	playSound();
    	try {
    		writeExcel();
//    		txtPname.setText("Exported to excel....");
    		System.out.println("Exported");
    	}
    	catch(Exception exp) {
    		System.out.println(exp.toString());
    	}
    }
    
    //========================================================================================================================
    public void writeExcel() throws IOException {
    	Writer writer = null;
    	try {
    		File file = new File("D:\\Export\\Users.csv");
    		writer = new BufferedWriter(new FileWriter(file));
    		String text="Mobile,Starting Date,Ending Date,Amount,Bill Status\n";
    		writer.write(text);
    		for(BillBean p : ary) {
    			text = p.getMobile() +"," + p.getDos() + "," + p.getDoe()+ "," + p.getBill() +"," + p.getBillstatus() + "\n";
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
    
    
    
    
    //========================================================================================

    @FXML
    void doSearch(ActionEvent event) 
    {

    	playSound();
    	TableColumn<BillBean, String> mbl=new TableColumn<BillBean, String>("Mobile");//any thing
    	mbl.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mbl.setMinWidth(80);
    	
    	
    	
    	
    	
    	
    	
    	TableColumn<BillBean, String> dos=new TableColumn<BillBean, String>("Date From");//any thing
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
        dos.setMinWidth(100);
        
        TableColumn<BillBean, String> doe=new TableColumn<BillBean, String>("Date To");//any thing
    	doe.setCellValueFactory(new PropertyValueFactory<>("doe"));
    	doe.setMinWidth(70);
        
    	TableColumn<BillBean, Float> bill=new TableColumn<BillBean, Float>("Bill");//any thing
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
    	bill.setMinWidth(70);
    	
    	TableColumn<BillBean, String> billstatus=new TableColumn<BillBean, String>("Bill Status");//any thing
    	billstatus.setCellValueFactory(new PropertyValueFactory<>("billstatus"));
    	billstatus.setMinWidth(70);
    	
    	
    	tableData.getColumns().clear();
        
    	tableData.getColumns().add(mbl);
    	tableData.getColumns().add(dos);
    	tableData.getColumns().add(doe);
    	tableData.getColumns().add(bill);
    	tableData.getColumns().add(billstatus);
    	if(radPending.isSelected())
    	tableData.setItems(FetchAllBill2(0));
    	else if(radPaid.isSelected())
    	tableData.setItems(FetchAllBill2(1));
    	else if(radAll.isSelected())
    		tableData.setItems(FetchAllBill3());
    	
    }
//==========================================================================================
    ObservableList<BillBean> FetchAllBill() 
    {
    	float prc=0;
    	//ObservableList<BillBean> ary=FXCollections.observableArrayList();
    	ary.clear();
    	try {
    	
    	
    		pst = con.prepareStatement("select * from bills where mobile=?");
    		pst.setString(1, txtMobile.getText());
    		
    		ResultSet table=pst.executeQuery();
    	
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		
	    		String DOS = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String DOE= String.valueOf(table.getDate("dateto").toLocalDate());
	    		float bill=table.getFloat("bill");
	    		String bs=table.getString("billstatus");
	    		
	    		//System.out.println(name);
	    		BillBean ref=new BillBean( mno, DOS,DOE,bill,bs);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);
	    		prc+=bill;
    		}
    		txtAmount.setText(String.valueOf(prc));

    	}
    	
    	catch(Exception ex)
    	
    	   { 
    		ex.printStackTrace(); 
    		}
    	
    		return ary;
    }
    //=========================================================================================================
    
    ObservableList<BillBean> FetchAllBill2(int status) 
    {
    	float prc=0;
    	//ObservableList<BillBean> ary=FXCollections.observableArrayList();
    	ary.clear();
    	try {
    	
    	
    		pst = con.prepareStatement("select * from bills where billstatus=?");
    		pst.setInt(1, status);
    		
    		ResultSet table=pst.executeQuery();
    	
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		
	    		String DOS = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String DOE= String.valueOf(table.getDate("dateto").toLocalDate());
	    		float bill=table.getFloat("bill");
	    		String bs=table.getString("billstatus");
	    		
	    		//System.out.println(name);
	    		BillBean ref=new BillBean( mno, DOS,DOE,bill,bs);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);
	    		prc+=bill;
    		}
    		txtAmount.setText(String.valueOf(prc));

    	}
    	
    	catch(Exception ex)
    	
    	   { 
    		ex.printStackTrace(); 
    		}
    	
    		return ary;
    }
    //===============================================================================================================
    ObservableList<BillBean> FetchAllBill3() 
    {
    	float prc=0;
    	ary.clear();
    	try {
    	
    	
    		pst = con.prepareStatement("select * from bills  ");
    		//pst.setInt(1, status);
    		
    		ResultSet table=pst.executeQuery();
    	
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		
	    		String DOS = String.valueOf(table.getDate("datefrom").toLocalDate());
	    		String DOE= String.valueOf(table.getDate("dateto").toLocalDate());
	    		float bill=table.getFloat("bill");
	    		String bs=table.getString("billstatus");
	    		
	    		//System.out.println(name);
	    		BillBean ref=new BillBean( mno, DOS,DOE,bill,bs);
	    		//System.out.println(ref.getHname());
	    		ary.add(ref);
	    		prc+=bill;
//	    		System.out.println(prc+"Price");
//	    		System.out.println(bill+"bill");
    		}
    		txtAmount.setText(String.valueOf(prc));

    	}
    	
    	catch(Exception ex)
    	
    	   { 
    		ex.printStackTrace(); 
    		}
    	
    		return ary;
    }
    
    @FXML
    void initialize() 
    {
    	con=BillCollector.MysqlConnector.doconnect();

    }

}


