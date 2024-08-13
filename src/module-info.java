module JavaProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.media;
	
	opens application to javafx.graphics, javafx.fxml;
	opens papermaster to javafx.graphics, javafx.fxml;
	opens HawkerManager to javafx.graphics, javafx.fxml;
	opens CustomerManager to javafx.graphics, javafx.fxml;
	opens BillGenerator to javafx.graphics, javafx.fxml;
	opens BillCollector to javafx.graphics, javafx.fxml;
	opens hawkerTable to javafx.graphics, javafx.fxml,javafx.base;
	opens areaManager to javafx.graphics, javafx.fxml;
	opens AboutUs to javafx.graphics, javafx.fxml;
	opens CustomerPanel to javafx.graphics, javafx.fxml,javafx.base;
	opens billBoard to javafx.graphics, javafx.fxml,javafx.base;
	opens AdminDashBoard to javafx.graphics, javafx.fxml;
	opens AdminLogin to javafx.graphics, javafx.fxml;
}
