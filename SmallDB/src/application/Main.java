package application;
	
import java.sql.SQLException;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import models.Courses;
import models.Student;
import models.Student_Courses;
import models.Trainer;
import services.CourseService;
import services.StudentService;
import services.Student_CourseService;
import services.TrainerService;


public class Main extends Application {
	
	
	////////////////////////////////// Login FX ////////////////////////************
	ImageView UserNameImage = new ImageView(new Image("username.png"));
	ImageView LoginImage = new ImageView(new Image("Login.jpg"));
	private Label UserNameLabel = new Label("User Name: ", UserNameImage);
	private Label InCorrect = new Label();
	private Label PasswordLabel = new Label("Password: ");
	
	private TextField UserNameField = new TextField("SanadAlawi");
	private PasswordField PasswordField = new PasswordField();
	
	private Button LoginButton = new Button("Login");
	
	////////////////////////////////// Middle FX ////////////////////////************
	private Button CourseButton = new Button("Add Course");
	private Button Student_CourseButton = new Button("Register Course"); // register
	private Button StudentButton = new Button("Register Student");
	private Button TrainerButton = new Button("Add Trainer");
	
	////////////////////////////////// Courses FX //////////////////////////////////************
	// Course Label object
//	private Label CourseIDLabel = new Label("Course ID: ");
//	private Label cTrainerIDLabel = new Label("Trainer ID: ");
	private Label CourseNameLabel = new Label("Course Name: ");
	private Label CourseLabel = new Label("Course Table ");
	
	// Course TextField object
//	private TextField CourseIDField = new TextField();
//	private TextField cTrainerIDField = new TextField();
	private TextField CourseNameField = new TextField();
	private TextField CourseFreeField = new TextField();
	
	// Course Button object
	private Button CourseInsertButton = new Button("insert");
	private Button CourseDeleteButton = new Button("Delete");
	private Button CourseSearchButton = new Button("Search");
	private Button CourseShowButton = new Button("Show Data");
	private Button CourseReturn = new Button("Return");
	
	// TextArea object
	private TextArea Courseta = new TextArea();
	
	// Course Table object
	TableView CourseTable = new TableView();
	ObservableList<Courses> CourseData = FXCollections.observableArrayList();
	
	
	////////////////////////////////// Student_Courses FX //////////////////////////////////************
	// Student_Courses Label object
	private Label s_cStudentIDLabel = new Label("Student ID: ");
	private Label s_cCourseIDLabel = new Label("Course ID: ");
	private Label Student_CoursesLabel = new Label("Student_Courses Table ");
		
	// Student_Courses TextField object
	private TextField s_cStudentIDField = new TextField();
	private TextField s_cCourseIDField = new TextField();
	private TextField Student_CoursesFreeField = new TextField();
		
	// Student_Courses Button object
	private Button Student_CoursesInsertButton = new Button("insert");
	private Button Student_CoursesDeleteButton = new Button("Delete");
	private Button Student_CoursesSearchButton = new Button("Search");
	private Button Student_CoursesShowButton = new Button("Show Data");
	private Button Student_CoursesReturn = new Button("Return");
	
	// TextArea object
	private TextArea Student_Coursesta = new TextArea();
		
	// Student_Courses Table object
	TableView Student_CoursesTable = new TableView();
	ObservableList<Student_Courses> Student_CoursesData = FXCollections.observableArrayList();
	
	
	////////////////////////////////// Student FX //////////////////////////////////************
	// Student Label object
//	private Label StudentIDLabel = new Label("Student ID: ");
//	private Label sTrainerIDLabel = new Label("Trainer ID: ");
	private Label StudentNameLabel = new Label("Student Name: ");
	private Label StudentAgeLabel = new Label("Student Age: ");
//	private Label StudentCourseNameLabel = new Label("Course Name ");
	private Label StudentLabel = new Label("Student Table ");
	
	// Student TextField object
//	private TextField StudentIDField = new TextField();
//	private TextField sTrainerIDField = new TextField();
	private TextField StudentNameField = new TextField();
	private TextField StudentAgeField = new TextField();
//	private TextField StudentCourseNameField = new TextField();
	private TextField StudentFreeField = new TextField();
		
	// Student Button object
	private Button StudentInsertButton = new Button("insert");
	private Button StudentDeleteButton = new Button("Delete");
	private Button StudentSearchButton = new Button("Search");
	private Button StudentShowButton = new Button("Show Data");
	private Button StudentReturn = new Button("Return");
	
	// TextArea object
	private TextArea Studentta = new TextArea();
		
	// Student Table object
	TableView StudentTable = new TableView();
	ObservableList<Student> StudentData = FXCollections.observableArrayList();
	
	
	////////////////////////////////// Trainer FX //////////////////////////////////************
	// Trainer Label object
//	private Label TrainerIDLabel = new Label("Trainer ID: ");
	private Label TrainerNameLabel = new Label("Trainer Name: ");
	private Label TrainerLabel = new Label("Trainer Table ");
			
	// Trainer TextField object
//	private TextField TrainerIDField = new TextField();
	private TextField TrainerNameField = new TextField();
	private TextField TrainerFreeField = new TextField();
			
	// Trainer Button object
	private Button TrainerInsertButton = new Button("insert");
	private Button TrainerDeleteButton = new Button("Delete");
	private Button TrainerSearchButton = new Button("Search");
	private Button TrainerShowButton = new Button("Show Data");
	private Button TrainerReturn = new Button("Return");
	
	// TextArea object
	private TextArea Trainerta = new TextArea();
			
	// Trainer Table object
	TableView TrainerTable = new TableView();
	ObservableList<Trainer> TrainerData = FXCollections.observableArrayList();
	
	// Scene objects
	Scene CourseScene, Student_CourseScene, StudentScene, TrainerScene, LoginScene, MiddleScene;
	
	
	/////////////////////////////// Start Method ///////////////////////////////////
	/**
	 *
	 */
	@Override
	public void start(Stage stage) {
		
		// Course Column Table objects //////////////////////////////////////////////
		TableColumn<Courses, Integer> CourseIDColumn = new TableColumn<>("Course ID");
		CourseIDColumn.setCellValueFactory( new PropertyValueFactory<>("CourseID"));
//		CourseIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
//		CourseIDColumn.setOnEditCommit((CellEditEvent<Courses, Integer> t) -> {
//	         ((Courses) t.getTableView()
//	             .getItems()
//	             .get(t.getTablePosition().getRow()))
//	             .setCourseID(t.getNewValue());
//	     });
		TableColumn<Courses, Integer> cTrainerIDColumn = new TableColumn<>("Trainer ID");
		cTrainerIDColumn.setCellValueFactory( new PropertyValueFactory<>("TrainerID"));
		cTrainerIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
		cTrainerIDColumn.setOnEditCommit((CellEditEvent<Courses, Integer> t) -> {
	         ((Courses) t.getTableView()
	             .getItems()
	             .get(t.getTablePosition().getRow()))
	             .setTrainerID(t.getNewValue());
	     });
		TableColumn<Courses, String> CourseNameIDColumn = new TableColumn<>("Course Name");
		CourseNameIDColumn.setCellValueFactory( new PropertyValueFactory<>("CourseName"));
		CourseNameIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		CourseNameIDColumn.setOnEditCommit((CellEditEvent<Courses, String> t) -> {
	         ((Courses) t.getTableView()
	             .getItems()
	             .get(t.getTablePosition().getRow()))
	             .setCourseName(t.getNewValue());
	     });
		 
		 CourseTable.setEditable(true);
		 CourseTable.setItems(CourseData);
		 CourseTable.getColumns().addAll(CourseIDColumn, cTrainerIDColumn, CourseNameIDColumn);
		 
		 CourseTable.setPrefSize(800, 300);
		 CourseTable.setTranslateY(120);
		 CourseIDColumn.setPrefWidth(266);
		 cTrainerIDColumn.setPrefWidth(266);
		 CourseNameIDColumn.setPrefWidth(266);
		 
//		 CourseIDField.setPrefSize(110, 20);
//		 cTrainerIDField.setPrefSize(110, 20);
		 CourseNameField.setPrefSize(110, 20);
		 CourseFreeField.setPrefSize(110, 20);
		 Courseta.setPrefSize(200, 50);
		 
//		 cTrainerIDField.setFocusTraversable(false);
		 CourseNameField.setFocusTraversable(false);
//		 CourseIDField.setFocusTraversable(false);
		 CourseInsertButton.setFocusTraversable(false);
		 CourseDeleteButton.setFocusTraversable(false);
		 CourseSearchButton.setFocusTraversable(false);
		 CourseShowButton.setFocusTraversable(false);
		 CourseFreeField.setFocusTraversable(false);
		 CourseReturn.setFocusTraversable(false);
		 Courseta.setFocusTraversable(false);
		 CourseTable.setFocusTraversable(false);
		 
		 CourseFreeField.setTooltip(new Tooltip("BY ID"));
	     CourseFreeField.setPromptText("By ID");
	     
	     Courseta.setTooltip(new Tooltip("is Found Or Deleted??"));
	     Courseta.setPromptText("is Found Or Deleted??");
	     
	     HBox CourseButtonhb = new HBox(20);
	     CourseButtonhb.getChildren().addAll(CourseInsertButton, CourseSearchButton, CourseDeleteButton, CourseFreeField, CourseShowButton, Courseta);
		 
	     HBox Coursehb = new HBox(20);
	     Coursehb.getChildren().addAll(CourseNameLabel, CourseNameField, CourseLabel, CourseReturn);
	     CourseLabel.setTranslateX(200);
	     CourseReturn.setTranslateX(250);
	     
	     VBox Coursevb = new VBox(20);
	     Coursevb.getChildren().addAll(Coursehb, CourseButtonhb);
	     Coursevb.setTranslateX(70);
	     
	     Group CourseRoot = new Group();
	     CourseRoot.getChildren().addAll(Coursevb, CourseTable);
	     CourseRoot.setTranslateX(100);
	     
	     CourseScene = new Scene(CourseRoot, 1000, 600);
	     
	     CourseShowButton.setOnAction(e -> { // Course Show Button
			try {
				ObservableList<Courses> CourseList = CourseService.getService().getAllCourses();
				CourseTable.setItems(CourseList);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
	     
	     CourseInsertButton.setOnAction(e -> { // Course Insert Button
	    	 if(!CourseNameField.getText().isEmpty()) {
	    		 try {
					CourseService.getService().Insert(CourseNameField.getText());
					ObservableList<Courses> CourseList = CourseService.getService().getAllCourses();
					CourseTable.setItems(CourseList);
					CourseNameField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Name Field is Empty!!!").show();
	     });
	     
	     CourseSearchButton.setOnAction(e -> { // Course Search Button
	    	 if(!CourseFreeField.getText().isEmpty()) {
	    		 try {
					Courseta.setText(CourseService.getService().Search(CourseFreeField.getText()));
					CourseFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Free Field is Empty!!!").show();
	     });
	     
	     CourseDeleteButton.setOnAction(e -> { // Course Delete Button
	    	 if(!CourseFreeField.getText().isEmpty()) {
	    		 try {
					Courseta.setText(CourseService.getService().Delete(CourseFreeField.getText()));
					ObservableList<Courses> CourseList = CourseService.getService().getAllCourses();
					CourseTable.setItems(CourseList);
					CourseFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Free Field is Empty!!!").show();
	     });
	     
	     CourseReturn.setOnAction(e -> {
				stage.setScene(MiddleScene);
			});
	     
		// Student_Course Column Table objects //////////////////////////////////////////////
		TableColumn<Student_Courses, Integer> s_cStudentIDColumn = new TableColumn<>("Student ID");
		s_cStudentIDColumn.setCellValueFactory( new PropertyValueFactory<>("StudentID"));
//		s_cStudentIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
//		s_cStudentIDColumn.setOnEditCommit((CellEditEvent<Student_Courses, Integer> t) -> {
//		        ((Student_Courses) t.getTableView()
//		             .getItems()
//		             .get(t.getTablePosition().getRow()))
//		             .setStudentID(t.getNewValue());
//		 });
		TableColumn<Student_Courses, Integer> s_cCourseIDColumn = new TableColumn<>("Course ID");
		s_cCourseIDColumn.setCellValueFactory( new PropertyValueFactory<>("CourseID"));
		s_cCourseIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
		s_cCourseIDColumn.setOnEditCommit((CellEditEvent<Student_Courses, Integer> t) -> {
		         ((Student_Courses) t.getTableView()
		             .getItems()
		             .get(t.getTablePosition().getRow()))
		             .setCourseID(t.getNewValue());
		});
			
		Student_CoursesTable.setEditable(true);
		Student_CoursesTable.setItems(Student_CoursesData);
		Student_CoursesTable.getColumns().addAll(s_cStudentIDColumn, s_cCourseIDColumn);
			 
		Student_CoursesTable.setPrefSize(800, 300);
		Student_CoursesTable.setTranslateY(120);
		s_cStudentIDColumn.setPrefWidth(400);
		s_cCourseIDColumn.setPrefWidth(400);
		
		s_cStudentIDField.setPrefSize(110, 20);
		s_cCourseIDField.setPrefSize(110, 20);
		Student_Coursesta.setPrefSize(200, 50);
		
		s_cStudentIDField.setFocusTraversable(false);
		s_cCourseIDField.setFocusTraversable(false);
		Student_CoursesFreeField.setFocusTraversable(false);
		Student_CoursesInsertButton.setFocusTraversable(false);
		Student_CoursesDeleteButton.setFocusTraversable(false);
		Student_CoursesSearchButton.setFocusTraversable(false);
		Student_CoursesShowButton.setFocusTraversable(false);
		Student_CoursesReturn.setFocusTraversable(false);
		Student_CoursesTable.setFocusTraversable(false);
		Student_Coursesta.setFocusTraversable(false);
		
		Student_CoursesFreeField.setTooltip(new Tooltip("BY ID"));
		Student_CoursesFreeField.setPromptText("By ID");
		
		Student_Coursesta.setTooltip(new Tooltip("is Found Or Deleted??"));
		Student_Coursesta.setPromptText("is Found Or Deleted??");
		
		HBox Student_CoursesButtonhb = new HBox(20);
		Student_CoursesButtonhb.getChildren().addAll(Student_CoursesInsertButton, Student_CoursesSearchButton, Student_CoursesDeleteButton, Student_CoursesFreeField, Student_CoursesShowButton, Student_Coursesta);
		 
	    HBox Student_Courseshb = new HBox(20);
	    Student_Courseshb.getChildren().addAll(s_cStudentIDLabel, s_cStudentIDField, s_cCourseIDLabel, s_cCourseIDField, Student_CoursesLabel, Student_CoursesReturn);
	    Student_CoursesLabel.setTranslateX(70);
	    Student_CoursesReturn.setTranslateX(70);
	     
	    VBox Student_Coursesevb = new VBox(20);
	    Student_Coursesevb.getChildren().addAll(Student_Courseshb, Student_CoursesButtonhb);
	    Student_Coursesevb.setTranslateX(50);
	     
	    Group Student_CoursesRoot = new Group();
	    Student_CoursesRoot.getChildren().addAll(Student_Coursesevb, Student_CoursesTable);
	    Student_CoursesRoot.setTranslateX(100);
	     
	    Student_CourseScene = new Scene(Student_CoursesRoot, 1000, 600);
	    
	    Student_CoursesShowButton.setOnAction(e -> { // Course Show Button
			try {
				ObservableList<Student_Courses> Student_CourseList = Student_CourseService.getService().getAllStudent_Courses();
				Student_CoursesTable.setItems(Student_CourseList);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     });
		
	    Student_CoursesInsertButton.setOnAction(e -> { // Course Insert Button
	    	 if(!s_cCourseIDField.getText().isEmpty() && !s_cStudentIDField.getText().isEmpty()) {
	    		 try {
					Student_CourseService.getService().Insert(s_cStudentIDField.getText(), s_cCourseIDField.getText());
					ObservableList<Student_Courses> Student_CourseList = Student_CourseService.getService().getAllStudent_Courses();
					Student_CoursesTable.setItems(Student_CourseList);
					s_cCourseIDField.setText("");
					s_cStudentIDField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Student or Course Field is Empty!!!").show();
	     });
	    
	    Student_CoursesSearchButton.setOnAction(e -> { // Course Search Button
	    	 if(!Student_CoursesFreeField.getText().isEmpty()) {
	    		 try {
	    			 Student_Coursesta.setText(Student_CourseService.getService().Search(Student_CoursesFreeField.getText()));
	    			 Student_CoursesFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Free Field is Empty!!!").show();
	     });
	     
	    Student_CoursesDeleteButton.setOnAction(e -> { // Course Delete Button
	    	 if(!Student_CoursesFreeField.getText().isEmpty()) {
	    		 try {
	    			Student_Coursesta.setText(Student_CourseService.getService().Delete(Student_CoursesFreeField.getText()));
	    			ObservableList<Student_Courses> Student_CourseList = Student_CourseService.getService().getAllStudent_Courses();
					Student_CoursesTable.setItems(Student_CourseList);
					Student_CoursesFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Free Field is Empty!!!").show();
	     });
	    
	    Student_CoursesReturn.setOnAction(e -> {
			stage.setScene(MiddleScene);
		});
	    
		// Student Column Table objects //////////////////////////////////////////////
		TableColumn<Student, Integer> StudentIDColumn = new TableColumn<>("Student ID");
		StudentIDColumn.setCellValueFactory( new PropertyValueFactory<>("StudentID"));
//		StudentIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
//		StudentIDColumn.setOnEditCommit((CellEditEvent<Student, Integer> t) -> {
//		         ((Student) t.getTableView()
//		             .getItems()
//		             .get(t.getTablePosition().getRow()))
//		             .setStudentID(t.getNewValue());
//		});
		
		TableColumn<Student, Integer> sTrainerIDColumn = new TableColumn<>("Trainer ID");
		sTrainerIDColumn.setCellValueFactory( new PropertyValueFactory<>("TrainerID"));
				
		TableColumn<Student, String> StudentNameColumn = new TableColumn<>("Student Name");
		StudentNameColumn.setCellValueFactory( new PropertyValueFactory<>("StudentName"));
		StudentNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		StudentNameColumn.setOnEditCommit((CellEditEvent<Student, String> t) -> {
		         ((Student) t.getTableView()
		             .getItems()
		             .get(t.getTablePosition().getRow()))
		             .setStudentName(t.getNewValue());
		});
		
		TableColumn<Student, Integer> StudentAgeColumn = new TableColumn<>("Student Age");
		StudentAgeColumn.setCellValueFactory( new PropertyValueFactory<>("Age"));
		StudentAgeColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
		StudentAgeColumn.setOnEditCommit((CellEditEvent<Student, Integer> t) -> {
		         ((Student) t.getTableView()
		             .getItems()
		             .get(t.getTablePosition().getRow()))
		             .setAge(t.getNewValue());
		});
		
		TableColumn<Student, String> StudentCourseNumberColumn = new TableColumn<>("Course Number");
		StudentCourseNumberColumn.setCellValueFactory( new PropertyValueFactory<>("NumberOfCourses"));
//		StudentCourseNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		StudentCourseNumberColumn.setOnEditCommit((CellEditEvent<Student, String> t) -> {
//		         ((Student) t.getTableView()
//		             .getItems()
//		             .get(t.getTablePosition().getRow()))
//		             .setNumberOfCourses(t.getNewValue());
//		});
		 
		StudentTable.setEditable(true);
		StudentTable.setItems(StudentData);
		StudentTable.getColumns().addAll(StudentIDColumn, sTrainerIDColumn, StudentNameColumn, StudentAgeColumn, StudentCourseNumberColumn);
		
		StudentTable.setPrefSize(800, 300);
		StudentTable.setTranslateY(120);
		StudentIDColumn.setPrefWidth(160);
		sTrainerIDColumn.setPrefWidth(160);
		StudentNameColumn.setPrefWidth(160);
		StudentAgeColumn.setPrefWidth(160);
		StudentCourseNumberColumn.setPrefWidth(160);
		
//		StudentIDField.setPrefSize(110, 20);
//		sTrainerIDField.setPrefSize(110, 20);
		StudentNameField.setPrefSize(110, 20);
		StudentAgeField.setPrefSize(110, 20);
//		StudentCourseNameField.setPrefSize(110, 20);
		StudentFreeField.setPrefSize(110, 20);
		Studentta.setPrefSize(200, 50);
		
//		StudentIDField.setFocusTraversable(false);
//		sTrainerIDField.setFocusTraversable(false);
		StudentNameField.setFocusTraversable(false);
		StudentAgeField.setFocusTraversable(false);
		StudentFreeField.setFocusTraversable(false);
//		StudentCourseNameField.setFocusTraversable(false);
		StudentReturn.setFocusTraversable(false);
		Studentta.setFocusTraversable(false);
		StudentTable.setFocusTraversable(false);
		StudentInsertButton.setFocusTraversable(false);
		StudentDeleteButton.setFocusTraversable(false);
		StudentSearchButton.setFocusTraversable(false);
		StudentShowButton.setFocusTraversable(false);
		
		StudentFreeField.setTooltip(new Tooltip("BY ID"));
		StudentFreeField.setPromptText("By ID");
		
		Studentta.setTooltip(new Tooltip("is Found Or Deleted??"));
		Studentta.setPromptText("is Found Or Deleted??");
		
		HBox StudentButtonhb = new HBox(20);
		StudentButtonhb.getChildren().addAll(StudentInsertButton, StudentSearchButton, StudentDeleteButton, StudentFreeField, StudentShowButton, Studentta);
		Studentta.setTranslateX(50);
		
		HBox Studenthb = new HBox(20);
		Studenthb.getChildren().addAll(StudentNameLabel, StudentNameField, StudentAgeLabel, StudentAgeField, StudentLabel, StudentReturn);
		StudentLabel.setTranslateX(50);
		StudentReturn.setTranslateX(50);
		
		VBox Studentvb = new VBox(20);
		Studentvb.getChildren().addAll(Studenthb, StudentButtonhb);
		Studentvb.setTranslateX(40);
		
		Group StudentRoot = new Group();
		StudentRoot.getChildren().addAll(Studentvb, StudentTable);
		StudentRoot.setTranslateX(100);
		
		StudentScene = new Scene(StudentRoot, 1000, 600); 
		
		StudentShowButton.setOnAction(e -> {
			ObservableList<Student> StudentList;
			try {
				StudentList = StudentService.getService().getAllStudent();
				StudentTable.setItems(StudentList);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		StudentInsertButton.setOnAction(e -> {
			if(!StudentNameField.getText().isEmpty() && !StudentAgeField.getText().isEmpty()) {
				try {
					StudentService.getService().Insert(StudentNameField.getText(), StudentAgeField.getText());
					ObservableList<Student> StudentList = StudentService.getService().getAllStudent();
					StudentTable.setItems(StudentList);
					StudentNameField.setText("");
					StudentAgeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
				new Alert(AlertType.WARNING, "Name or Age Field is Empty!!!").show();
		});
		
		StudentSearchButton.setOnAction(e -> {
			if(!StudentFreeField.getText().isEmpty()) {
				try {
					Studentta.setText(StudentService.getService().Search(StudentFreeField.getText()));
					StudentFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
				new Alert(AlertType.WARNING, "Free Field is Empty!!!").show();
		});
		
		 StudentDeleteButton.setOnAction(e -> { // Student Delete Button
	    	 if(!StudentFreeField.getText().isEmpty()) {
	    		 try {
	    			 Studentta.setText(StudentService.getService().Delete(StudentFreeField.getText()));
					ObservableList<Student> StudentList = StudentService.getService().getAllStudent();
					StudentTable.setItems(StudentList);
					StudentFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Free Field is Empty!!!").show();
	     });
		 
		 StudentReturn.setOnAction(e -> {
				stage.setScene(MiddleScene);
		});
		
		
		// Trainer Column Table objects //////////////////////////////////////////////
		TableColumn<Trainer, Integer> TrainerIDColumn = new TableColumn<>("Trainer ID");
		TrainerIDColumn.setCellValueFactory( new PropertyValueFactory<>("TrainerID"));
		TrainerIDColumn.setCellFactory(TextFieldTableCell.forTableColumn((new IntegerStringConverter())));
		TrainerIDColumn.setOnEditCommit((CellEditEvent<Trainer, Integer> t) -> {
		         ((Trainer) t.getTableView()
		             .getItems()
		             .get(t.getTablePosition().getRow()))
		             .setID(t.getNewValue());
		});
		
		TableColumn<Trainer, String> TrainerNameColumn = new TableColumn<>("Trainer Name");
		TrainerNameColumn.setCellValueFactory( new PropertyValueFactory<>("TrainerName"));
		TrainerNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		TrainerNameColumn.setOnEditCommit((CellEditEvent<Trainer, String> t) -> {
		         ((Trainer) t.getTableView()
		             .getItems()
		             .get(t.getTablePosition().getRow()))
		             .setName(t.getNewValue());
		});
		
		TrainerTable.setEditable(true);
//		TrainerTable.setItems(TrainerData);
		TrainerTable.getColumns().addAll(TrainerIDColumn, TrainerNameColumn);
		
		TrainerTable.setPrefSize(800, 300);
		TrainerTable.setTranslateY(120);
		TrainerIDColumn.setPrefWidth(400);
		TrainerNameColumn.setPrefWidth(400);
		
//		TrainerIDField.setPrefSize(110, 20);
		TrainerNameField.setPrefSize(110, 20);
		TrainerFreeField.setPrefSize(110, 20);
		Trainerta.setPrefSize(200, 50);
		
//		TrainerIDField.setFocusTraversable(false);
		TrainerNameField.setFocusTraversable(false);
		TrainerFreeField.setFocusTraversable(false);
		TrainerInsertButton.setFocusTraversable(false);
		TrainerDeleteButton.setFocusTraversable(false);
		TrainerSearchButton.setFocusTraversable(false);
		TrainerShowButton.setFocusTraversable(false);
		TrainerReturn.setFocusTraversable(false);
		TrainerTable.setFocusTraversable(false);
		Trainerta.setFocusTraversable(false);
		
		TrainerFreeField.setTooltip(new Tooltip("BY ID"));
		TrainerFreeField.setPromptText("By ID");
		
		Trainerta.setTooltip(new Tooltip("is Found Or Deleted??"));
		Trainerta.setPromptText("is Found Or Deleted??");
		
		
		HBox TrainerButtonhb = new HBox(20);
		TrainerButtonhb.getChildren().addAll(TrainerInsertButton, TrainerSearchButton, TrainerDeleteButton, TrainerFreeField, TrainerShowButton, Trainerta);
		Trainerta.setTranslateX(50);
		
		HBox Trainerhb = new HBox(20);
		Trainerhb.getChildren().addAll(TrainerNameLabel, TrainerNameField, TrainerLabel, TrainerReturn);
		TrainerLabel.setTranslateX(270);
		TrainerReturn.setTranslateX(270);
		
		VBox Trainervb = new VBox(20);
		Trainervb.getChildren().addAll(Trainerhb, TrainerButtonhb);
		Trainervb.setTranslateX(40);
		
		Group TrainerRoot = new Group();
		TrainerRoot.getChildren().addAll(Trainervb, TrainerTable);
		TrainerRoot.setTranslateX(100);
		
		TrainerScene = new Scene(TrainerRoot, 1000, 600); 
		
		TrainerShowButton.setOnAction(e -> {
			ObservableList<Trainer> TrainerList;
			try {
				TrainerList = TrainerService.getService().getAllTrainer();
				TrainerTable.setItems(TrainerList);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		TrainerInsertButton.setOnAction(e -> {
			if(!TrainerNameField.getText().isEmpty()) {
				try {
					TrainerService.getService().Insert(TrainerNameField.getText());
					ObservableList<Trainer> TrainerList = TrainerService.getService().getAllTrainer();
					TrainerTable.setItems(TrainerList);
					TrainerNameField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
				new Alert(AlertType.WARNING, "Name Field is Empty!!!").show();
		});
		
		TrainerSearchButton.setOnAction(e -> {
			if(!TrainerFreeField.getText().isEmpty()) {
				try {
					Trainerta.setText(TrainerService.getService().Search(TrainerFreeField.getText()));
					TrainerFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
				new Alert(AlertType.WARNING, "Free Field is Empty!!!").show();
		});
		
		TrainerDeleteButton.setOnAction(e -> { // Student Delete Button
	    	 if(!TrainerFreeField.getText().isEmpty()) {
	    		 try {
	    			Trainerta.setText(TrainerService.getService().Delete(TrainerFreeField.getText()));
	    			ObservableList<Trainer> TrainerList = TrainerService.getService().getAllTrainer();
					TrainerTable.setItems(TrainerList);
					TrainerFreeField.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	 }
	    	 else
	    		 new Alert(AlertType.WARNING, " Free Field is Empty!!!").show();
	     });
		
		TrainerReturn.setOnAction(e -> {
			stage.setScene(MiddleScene);
		});
		
		///////////////// Middle Form ////////////////////////////////////////////
		CourseButton.setPrefSize(500, 300);
		Student_CourseButton.setPrefSize(500, 300);
		StudentButton.setPrefSize(500, 300);
		TrainerButton.setPrefSize(500, 300);
		
		CourseButton.setFont(new Font("Arial", 2));
		Student_CourseButton.setFont(new Font("Arial", 2));
		StudentButton.setFont(new Font("Arial", 2));
		TrainerButton.setFont(new Font("Arial", 2));
		
		FadeTransition Courseft = new FadeTransition(Duration.millis(3000), CourseButton);
		Courseft.setFromValue(1.0); Courseft.setToValue(0.4); Courseft.setCycleCount(Timeline.INDEFINITE);
		Courseft.setAutoReverse(true);
		Courseft.play();
		CourseButton.setOnMouseEntered(e -> { Courseft.stop(); CourseButton.setStyle("-fx-opacity: 0.9;"); });
		CourseButton.setOnMouseExited(e -> { Courseft.playFromStart(); });
		
		FadeTransition Student_Courseft = new FadeTransition(Duration.millis(3000), Student_CourseButton);
		Student_Courseft.setFromValue(1.0); Student_Courseft.setToValue(0.4); Student_Courseft.setCycleCount(Timeline.INDEFINITE);
		Student_Courseft.setAutoReverse(true);
		Student_Courseft.play();
		Student_CourseButton.setOnMouseEntered(e -> { Student_Courseft.stop(); Student_CourseButton.setStyle("-fx-opacity: 0.9;"); });
		Student_CourseButton.setOnMouseExited(e -> { Student_Courseft.playFromStart(); });
		
		FadeTransition Studentft = new FadeTransition(Duration.millis(3000), StudentButton);
		Studentft.setFromValue(1.0); Studentft.setToValue(0.4); Studentft.setCycleCount(Timeline.INDEFINITE);
		Studentft.setAutoReverse(true);
		Studentft.play();
		StudentButton.setOnMouseEntered(e -> { Studentft.stop(); StudentButton.setStyle("-fx-opacity: 0.9;"); });
		StudentButton.setOnMouseExited(e -> { Studentft.playFromStart(); });
		
		FadeTransition Trainerft = new FadeTransition(Duration.millis(3000), TrainerButton);
		Trainerft.setFromValue(1.0); Trainerft.setToValue(0.4); Trainerft.setCycleCount(Timeline.INDEFINITE);
		Trainerft.setAutoReverse(true);
		Trainerft.play();
		TrainerButton.setOnMouseEntered(e -> { Trainerft.stop(); TrainerButton.setStyle("-fx-opacity: 0.9;"); });
		TrainerButton.setOnMouseExited(e -> { Trainerft.playFromStart(); });
		
		
		CourseButton.getStyleClass().add("CourseButton");
		Student_CourseButton.getStyleClass().add("Student_CourseButton");
		StudentButton.getStyleClass().add("StudentButton");
		TrainerButton.getStyleClass().add("TrainerButton");
		VBox vb1 = new VBox(40);
		vb1.getChildren().addAll(CourseButton, Student_CourseButton);
		
		VBox vb2 = new VBox();
		vb2.getChildren().addAll(StudentButton, TrainerButton);
		
		HBox hb = new HBox();
		hb.getChildren().addAll(vb1, vb2);
		
		Group MiddelRoot = new Group();
		MiddelRoot.getChildren().add(hb);
		
		MiddleScene = new Scene(MiddelRoot, 1000, 600);
		
		CourseButton.setOnAction(e -> {
			stage.setScene(CourseScene);
		});
		
		Student_CourseButton.setOnAction(e -> {
			stage.setScene(Student_CourseScene);
		});
		
		StudentButton.setOnAction(e -> {
			stage.setScene(StudentScene);
		});
		
		TrainerButton.setOnAction(e -> {
			stage.setScene(TrainerScene);
		});
		
		///////////////// Login Form ////////////////////////////////////////////
		UserNameImage.setFitHeight(30);
		UserNameImage.setFitWidth(30);
		
		UserNameLabel.setFont(new Font("Arial", 16));
		PasswordLabel.setFont(new Font("Arial", 16));
		LoginButton.setPrefSize(70, 20);
		InCorrect.setTranslateX(300);
		InCorrect.setTranslateY(330);
		UserNameField.setFocusTraversable(false);
		PasswordField.setFocusTraversable(false);
		LoginButton.setFocusTraversable(false);
		
		UserNameField.setStyle("-fx-text-box-border: #000077; -fx-focus-color: #000022;");
		PasswordField.setStyle("-fx-text-box-border: #000077; -fx-focus-color: #000022;");
		
		VBox loginvb1 = new VBox(20);
		loginvb1.getChildren().addAll(UserNameLabel, PasswordLabel);
		
		UserNameLabel.setContentDisplay(ContentDisplay.RIGHT);
		
		VBox loginvb2 = new VBox(20);
		loginvb2.getChildren().addAll(UserNameField, PasswordField, LoginButton);
		
		HBox loginhb = new HBox(10);
		loginhb.getChildren().addAll(loginvb1, loginvb2);
		loginhb.setTranslateX(300);
		loginhb.setTranslateY(200);
		
		Pane pane = new Pane();
		pane.getChildren().addAll(LoginImage, loginhb, InCorrect);
		LoginImage.setFitWidth(1000);
		LoginImage.setOpacity(0.5);
		
		LoginScene = new Scene(pane, 1000, 600);
		PasswordField.setText("SanadAlawi");
		LoginButton.setOnAction(e -> {
			if(!UserNameField.getText().isEmpty() && !PasswordField.getText().isEmpty()) {
				String username = UserNameField.getText().trim();
				String password = PasswordField.getText().trim();
				if(username.equals("SanadAlawi") && password.equals("SanadAlawi")) {
					stage.setScene(MiddleScene);
					UserNameField.setText("");
					PasswordField.setText("");
					InCorrect.setText("");
					UserNameField.setStyle("-fx-text-box-border: #000077; -fx-focus-color: #000022;");
					PasswordField.setStyle("-fx-text-box-border: #000077; -fx-focus-color: #000022;");
				}
				else {
					UserNameField.setStyle("-fx-text-box-border: #FF2222; -fx-focus-color: #FF0000;");
					PasswordField.setStyle("-fx-text-box-border: #FF2222; -fx-focus-color: #FF0000;");
					InCorrect.setFont(new Font("Arial", 15));
					InCorrect.setTextFill(Color.RED);
					InCorrect.setText("InCorrect Password or username please try again!!!");
				}
			}
			else
				new Alert(AlertType.WARNING, " UserName Or Password Field is Empty!!!").show();
		});
		
		MiddleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(LoginScene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
