package hawkerTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<HawkerBean> tableData;

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

    	TableColumn<HawkerBean, String> name=new TableColumn<HawkerBean, String>("Hawker Name");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("hname"));
    	name.setMinWidth(70);
    	
    	TableColumn<HawkerBean, String> mbl=new TableColumn<HawkerBean, String>("Mobile");//any thing
    	mbl.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mbl.setMinWidth(80);
    	
    	TableColumn<HawkerBean, String> allo_areas=new TableColumn<HawkerBean, String>("Allocated Areas");//any thing
    	allo_areas.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	allo_areas.setMinWidth(200);
    	
    	TableColumn<HawkerBean, String> doj=new TableColumn<HawkerBean, String>("Date Of joining");//any thing
    	doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
        doj.setMinWidth(100);
    	tableData.getColumns().clear();
    	tableData.getColumns().add(name);
    	tableData.getColumns().add(mbl);
    	tableData.getColumns().add(allo_areas);
    	tableData.getColumns().add(doj);
    	tableData.setItems(FetchAllHawkers());
    }

    ObservableList<HawkerBean> FetchAllHawkers() 
    {
    	ObservableList<HawkerBean>	ary=FXCollections.observableArrayList();
    	try {
    	
    	
    		pst = con.prepareStatement("select * from hawkers");
    		
    		ResultSet table=pst.executeQuery();
    	
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("hname");
	    		String DOJ = String.valueOf(table.getDate("doj").toLocalDate());
	    		String alloarea=table.getString("alloareas");
	    		//System.out.println(name);
	    		HawkerBean ref=new HawkerBean(name, mno, alloarea, DOJ);
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
   
    @FXML
    void initialize() 
    {
        con=MysqlConnector.doconnect();

    }

}
