import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
 
public class Driver extends Application {
	
	private Actors actors;
	private Movies movies;
    private Stage window; // Main scene to be shown to the user.
     
    public static void main(String[] args) {
        launch(args);
    }
     
    public void start(Stage primaryStage) {
		/*
		 * Where the program starts.
		 */
    	// Initialising the hashtables with a size of 10 (10 Linked Lists in 1 HashTable).
		actors = new Actors(10);
		movies = new Movies(10);
		
		// Setting the main scene to be the window variable.
        window = primaryStage;
         
        // Loading data and setting the first scene.
        loadData();
        mainMenu();
    }
    
    public void loadScene(Scene scene, String title) {
    	/*
    	 * Alias for window.setScene(scene) method. Simplifies loading a new scene.
    	 */
        window.setScene(scene);
        window.setTitle(title);
        window.show();
    }
     
    public void mainMenu() {
    	/*
    	 * Loading the main scene, prompting user to go-to the Movies or Actors listings.
    	 */
    	// Creating a button that says 'Movies'.
    	Button moviesListing = new Button("Movies");
    	// When clicked, the user is sent to the movie listings.
    	moviesListing.setOnAction(e -> moviesListing());
    	// Enlarging the button's width to be the max value.
    	moviesListing.setMaxWidth(Double.MAX_VALUE);
    	
        Button actorsListing = new Button("Actors");
        actorsListing.setOnAction(e -> actorsListing());
        actorsListing.setMaxWidth(Double.MAX_VALUE);
     
        // Enclosing the buttons into a vertical box.
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
 
        // Adding all the buttons to the box.
        vbButtons.getChildren().addAll(moviesListing, actorsListing);
             
        // Creating a new scene based on the layout (vertical box) created with a canvas size of 600 by 400.
        Scene mainScene = new Scene(vbButtons, 600, 400);
        // Load the scene.
        loadScene(mainScene, "Main Menu");
    }
     
    public void moviesListing() {
    	/*
    	 * Loads the movies listing, prompting user for actions related to movies.
    	 */
        Button addNewMovie = new Button("Add a new movie");
        addNewMovie.setOnAction(e -> promptAddNewMovie());
        
        Button viewMovies = new Button("View all movies");
        viewMovies.setOnAction(e -> showAllMovies(movies));
        
        Button searchMovie = new Button("Search for a movie");
        searchMovie.setOnAction(e -> promptSearchForMovie());
        
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setOnAction(e -> mainMenu());
             
        // Making sure all the buttons auto-sizes their width to the maximum width possible.
        Button[] allButtons = {addNewMovie, viewMovies, searchMovie, buttonGoBack};
        for (int i = 0; i < allButtons.length; i++) {
        	allButtons[i].setMaxWidth(Double.MAX_VALUE);
        }
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(addNewMovie, viewMovies, searchMovie, buttonGoBack);
             
        Scene moviesScene = new Scene(vbButtons, 600, 400);
        loadScene(moviesScene, "Movie Options");
    }
     
    public void actorsListing() {
    	/*
    	 * Loads the actors listing, prompting user for actions related to actors.
    	 */
        Button addNewActor = new Button("Add a new actor");
        addNewActor.setOnAction(e -> promptAddNewActor());
        
        Button viewActors = new Button("View all actors");
        viewActors.setOnAction(e -> showAllActors(actors));
        
        Button searchActor = new Button("Search for an actor");
        searchActor.setOnAction(e -> promptSearchForActor());
        
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setOnAction(e -> mainMenu());
            
        Button[] allButtons = {addNewActor, viewActors, searchActor, buttonGoBack};
        for (int i = 0; i < allButtons.length; i++) {
            allButtons[i].setMaxWidth(Double.MAX_VALUE);
        }
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(addNewActor, viewActors, searchActor, buttonGoBack);
             
        Scene actorsScene = new Scene(vbButtons, 600, 400);
        loadScene(actorsScene, "Actor Options");
    }
     
    public void promptAddNewMovie() {
    	/*
    	 * Prompts the user to input movie details for adding a new movie to the system.
    	 */
    	// Creates a label (box of text) indicating to the user what s/he's doing.
        Label label = new Label("Add a new movie to the system.");
             
        // TextField that allows text input. Will be used to add new movies/actors.
        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");
        
        TextField yearInput = new TextField();
        yearInput.setPromptText("Year of Release");
             
        TextField runningtimeInput = new TextField();
        runningtimeInput.setPromptText("Running Time (in minutes) e.g. 125.5");
             
        TextField genreInput = new TextField();
        genreInput.setPromptText("Genre");
             
        TextField plotInput = new TextField();
        plotInput.setPromptText("Plot Description");
             
        TextField posterUrlInput = new TextField();
        posterUrlInput.setPromptText("Movie Poster URL");
         
        Button submitMovie = new Button("Add Movie to System");
        submitMovie.setMaxWidth(Double.MAX_VALUE);
        // When submit button is pressed, give all the input field's values as arguments into the promptNewMovie method to check out input data.
        submitMovie.setOnAction(e -> addNewMovie(titleInput.getText(), yearInput.getText(), runningtimeInput.getText(), genreInput.getText(), plotInput.getText(), posterUrlInput.getText()));
             
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setMaxWidth(Double.MAX_VALUE);
        buttonGoBack.setOnAction(e -> mainMenu());
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(label, titleInput, yearInput, runningtimeInput, genreInput, plotInput, posterUrlInput, submitMovie, buttonGoBack);
             
        Scene addNewMovie = new Scene(vbButtons, 600, 400);
        loadScene(addNewMovie, "Add a new Movie");
    }
    
    public void addNewMovie(String title, String yearOfRelease, String runningTime, String genre, String description, String posterImage) {
    	/*
    	 * Scans input data received to be used to add a new movie into the system.
    	 */
    	// Initialising the new movie's variable as null.
    	Movie newMovie = null;
    	
    	// Invalid data such as the running time being a string (instead of being a float) may be used in the input, so the new movie initialisation is enclosed in a try/catch block.
    	try {
    		// Initialising the new movie.
			newMovie = new Movie(title, yearOfRelease, Float.parseFloat(runningTime), genre, description, new URL(posterImage));
			
			// Put the movie into the system.
			movies.put(newMovie.getTitle(), newMovie);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	
    	// Checking if the new movie was successfully added without any errors. If null, the try clause failed.
    	if (newMovie == null) {
    		System.out.println("An error occured within input.");
    	} else {
    		System.out.println("Movie successfully added to the system.");
    		saveData();
    	}
    	
    	moviesListing();
    }
    
    public void promptAddNewActor() {
    	/*
    	 * Prompts the user to input actor details for adding a new actor to the system.
    	 */             
        Label label = new Label("Add a new actor to the system.");
             
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
             
        TextField dobInput = new TextField();
        dobInput.setPromptText("Date of Birth (DD/MM/YYYY format) e.g. 14/05/1999");
             
        TextField genderInput = new TextField();
        genderInput.setPromptText("Gender");
             
        TextField nationalityInput = new TextField();
        nationalityInput.setPromptText("Nationality");
             
        Button submitActor = new Button("Add Actor to System");
        submitActor.setMaxWidth(Double.MAX_VALUE);
        submitActor.setOnAction(e -> addNewActor(nameInput.getText(), dobInput.getText(), genderInput.getText(), nationalityInput.getText()));
 
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setMaxWidth(Double.MAX_VALUE);
        buttonGoBack.setOnAction(e -> mainMenu());
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(label, nameInput, dobInput, genderInput, nationalityInput, submitActor, buttonGoBack);
             
        Scene addNewActor = new Scene(vbButtons, 600, 400);
        loadScene(addNewActor, "Add a new Actor");
    }
    
    public void addNewActor(String name, String dateofbirth, String gender, String nationality) {
    	/*
    	 * Receives input data from TextFields which is used to add a new actor to the system.
    	 */
    	Actor newActor = null;
    	
    	try {
    		newActor = new Actor(name, dateofbirth, gender.charAt(0), nationality);
			
			actors.put(newActor.getName(), newActor);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
    	
    	if (newActor == null) {
    		System.out.println("An error occured within input.");
    	} else {
    		System.out.println("Actor successfully added to the system.");
    		saveData();
    	}
    	
    	actorsListing();
    }
     
    public void showAllMovies(Movies movies) {
    	/*
    	 * Shows all the movies from a list of movies given.
    	 */
    	// Creating an array of buttons to store all the buttons for the movies.
    	// An additional extra index is required to store the go-back-to-menu button.
    	Button[] buttons = new Button[movies.numberOfContents() + 1];
    	// currentIndex is used to keep track of the current index of the array.
    	int currentIndex = 0;
    	
    	// Get the array of linked lists from the movies hashtable.
    	myLinkedList<String, Movie>[] moviesTable = movies.getHashTable();
    	
    	// For every linked list in the array, loop through the linked list and add the movie located in that linked list to the buttons array.
    	for (myLinkedList<String, Movie> moviesFromTable : moviesTable) {
    		for (String movie : moviesFromTable) {
    			buttons[currentIndex] = new Button(movie);
    			// When the button is clicked, show all the movie CRUD options.
    			buttons[currentIndex].setOnAction(e -> showMovieOptions(movies.get(movie)));
    			++currentIndex;
    		}
    	}
    	
    	Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setOnAction(e -> mainMenu());
        buttons[currentIndex] = buttonGoBack;
    	
    	for (int i = 0; i < buttons.length; i++) {
    		buttons[i].setMaxWidth(Double.MAX_VALUE);
    	}
    	
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
        vbButtons.getChildren().addAll(buttons);
        
        Scene viewAllMovies = new Scene(vbButtons, 600, 400);
        loadScene(viewAllMovies, "Movies in the System");   
    }
    
    public void showMovieOptions(Movie movie) {
    	/*
    	 * Shows all the movie CRUD options.
    	 * CRUD: Create, Retrieve, Update, Delete.
    	 */
    	// Show movie details in a label box.
        Label labelMovieDetails = new Label(movie.toString());

    	Button buttonViewActors = new Button("View actors in movie");
    	buttonViewActors.setOnAction(e -> viewActorsFromMovie(movie));
    	
    	Button buttonEditMovie = new Button("Edit Movie");
    	buttonEditMovie.setOnAction(e -> promptEditMovieDetails(movie));
    	
    	Button buttonRemoveMovie = new Button("Delete Movie");
    	buttonRemoveMovie.setOnAction(e -> deleteMovie(movie));
    	
    	Button buttonAddActor = new Button("Add actor to movie");
    	buttonAddActor.setOnAction(e -> promptAddActorToMovie(movie));
    	
    	Button buttonRemoveActor = new Button("Remove actor from movie");
    	buttonRemoveActor.setOnAction(e -> promptRemoveActorFromMovie(movie));
    	
    	Button buttonGoBack = new Button("Go back to the main menu");
    	buttonGoBack.setOnAction(e -> mainMenu());
    	
    	Button[] buttons = {buttonViewActors, buttonEditMovie, buttonRemoveMovie, buttonAddActor, buttonRemoveActor, buttonGoBack};
    	for (int i = 0; i < buttons.length; i++) {
    		buttons[i].setMaxWidth(Double.MAX_VALUE);
    	}
    	
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
        
        vbButtons.getChildren().addAll(labelMovieDetails);
        vbButtons.getChildren().addAll(buttons);
        
        Scene showMovieOptions = new Scene(vbButtons, 600, 400);
        loadScene(showMovieOptions, movie.getTitle() + "'s Options"); 
    }
    
    public void viewActorsFromMovie(Movie movie) {
    	/*
    	 * Shows all the actors from a movie given.
    	 */
    	Button[] buttons = new Button[movie.getActors().numberOfContents() + 1];
    	int currentIndex = 0;
    	
    	myLinkedList<String, Actor>[] actorsTable = movie.getActors().getHashTable();
    	
    	for (myLinkedList<String, Actor> actorsFromTable : actorsTable) {
    		for (String actor : actorsFromTable) {
    			buttons[currentIndex] = new Button(actor);
    			// When button is clicked, direct the user to the actor's CRUD options. Implements the interactive drill down system.
    			buttons[currentIndex].setOnAction(e -> showActorOptions(actors.get(actor)));
    			++currentIndex;
    		}
    	}
    	
    	Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setOnAction(e -> mainMenu());
        buttons[currentIndex] = buttonGoBack;
    	
    	for (int i = 0; i < buttons.length; i++) {
    		buttons[i].setMaxWidth(Double.MAX_VALUE);
    	}
    	
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
        vbButtons.getChildren().addAll(buttons);
        
        Scene allActorsFromMovie = new Scene(vbButtons, 600, 400);
        loadScene(allActorsFromMovie, movie.getTitle() + "'s Actors"); 
    }
    
    public void promptEditMovieDetails(Movie movie) {
    	/*
    	 * Prompts the user to edit details about the movie.
    	 */
        Label label = new Label("Edit " + movie.getTitle() + " details.\nLeave details blank if you do not want to change their properties.");
        
        TextField yearInput = new TextField();
        // Each input box will show the movie's initial values for that property.
        yearInput.setPromptText("Year of Release: " + movie.getYearOfRelease());
             
        TextField runningtimeInput = new TextField();
        runningtimeInput.setPromptText("Running Time (in minutes): " + movie.getRunningTime());
             
        TextField genreInput = new TextField();
        genreInput.setPromptText("Genre: " + movie.getGenre());
             
        TextField plotInput = new TextField();
        plotInput.setPromptText("Plot Description: " + movie.getDescription());
             
        TextField posterUrlInput = new TextField();
        posterUrlInput.setPromptText("Movie Poster URL: " + movie.getPosterImage());
         
        Button buttonEditMovie = new Button("Finish Editing");
        buttonEditMovie.setMaxWidth(Double.MAX_VALUE);
        buttonEditMovie.setOnAction(e -> editMovie(movie, yearInput.getText(), runningtimeInput.getText(), genreInput.getText(), plotInput.getText(), posterUrlInput.getText()));
             
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setMaxWidth(Double.MAX_VALUE);
        buttonGoBack.setOnAction(e -> mainMenu());
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(label, yearInput, runningtimeInput, genreInput, plotInput, posterUrlInput, buttonEditMovie, buttonGoBack);
             
        Scene editMovie = new Scene(vbButtons, 600, 400);
        loadScene(editMovie, "Edit " + movie.getTitle() + "'s details");
    }
    
    public void editMovie(Movie movie, String yearOfRelease, String runningTime, String genre, String description, String posterImage) {
    	/*
    	 * Takes in input data for a movie which will be used to edit the movie's properties.
    	 */
    	Movie editedMovie = null;
    	
    	try {
    		// For every property that can be edited, check if the input data is "", if empty then use old value or otherwise use new value.
    		editedMovie = new Movie(movie.getTitle(), yearOfRelease.equals("") ? movie.getYearOfRelease() : yearOfRelease, runningTime.equals("") ? movie.getRunningTime() : Float.parseFloat(runningTime), genre.equals("") ? movie.getGenre() : genre, description.equals("") ? movie.getDescription() : description, posterImage.equals("") ? movie.getPosterImage() : new URL(posterImage));
    	} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	
    	// Checking if the newly edited movie was successfully added without any errors.
    	if (editedMovie == null) {
    		System.out.println("An error occured within input.");
    	} else {
    		System.out.println("Movie successfully edited.");
    		// Note that any movie with the same title will be automatically replaced by the newer one (editedMovie overwrites original movie data).
    		movies.put(movie.getTitle(), editedMovie);
    		saveData();
    	}
    	
    	showMovieOptions(movie);
    }
    
    public void deleteMovie(Movie movie) {
    	/*
    	 * Removes a movie from the system and goes back to the main menu.
    	 */
    	// Remove the movie from the movies list.
    	movies.remove(movie.getTitle());
    	
    	// Get the linked list array of all the actors from the movie.
    	myLinkedList<String, Actor>[] actorsTable = movie.getActors().getHashTable();
    	
    	// For each linked list, remove each actor in that list from the movie by updating their own movies list.
    	for (myLinkedList<String, Actor> actorsFromTable : actorsTable) {
    		for (String actor : actorsFromTable) {
    			actors.get(actor).getMovies().remove(movie.getTitle());
    		}
    	}
    	
    	saveData();
    	moviesListing();
    }
    
    public void promptAddActorToMovie(Movie movie) {
    	/*
    	 * Shows all actors from the system and prompts the user to add a new actor to the given movie.
    	 */
    	Button[] buttons = new Button[actors.numberOfContents() + 1];
    	int currentIndex = 0;
    	
    	myLinkedList<String, Actor>[] actorsTable = actors.getHashTable();
    	
    	for (myLinkedList<String, Actor> actorsFromTable : actorsTable) {
    		for (String actor : actorsFromTable) {
    			buttons[currentIndex] = new Button(actor);
    			// When clicked, return the movie and the actor's data, which will be used to add the actor to the movie.
    			buttons[currentIndex].setOnAction(e -> addToMovie(movie, actors.get(actor)));
    			++currentIndex;
    		}
    	}
    	
    	Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setOnAction(e -> mainMenu());
        buttons[currentIndex] = buttonGoBack;
    	
    	for (int i = 0; i < buttons.length; i++) {
    		buttons[i].setMaxWidth(Double.MAX_VALUE);
    	}
    	
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
        vbButtons.getChildren().addAll(buttons);
        
        Scene addingActorsToMovie = new Scene(vbButtons, 600, 400);
        loadScene(addingActorsToMovie, "Add a new actor to " + movie.getTitle()); 
    }
    
    public void addToMovie(Movie movie, Actor actor) {
    	/*
    	 * Adds an actor to a movie.
    	 */
    	movie.getActors().put(actor.getName(), actor);
    	actor.getMovies().put(movie.getTitle(), movie);
    	
    	saveData();
    	showMovieOptions(movie);
    }
    
    public void promptRemoveActorFromMovie(Movie movie) {
    	/*
    	 * Shows all actors from the movie and prompts the user to delete one from the given movie.
    	 */
    	Button[] buttons = new Button[movie.getActors().numberOfContents() + 1];
    	int currentIndex = 0;
    	
    	myLinkedList<String, Actor>[] actorsTable = movie.getActors().getHashTable();
    	
    	for (myLinkedList<String, Actor> actorsFromTable : actorsTable) {
    		for (String actor : actorsFromTable) {
    			buttons[currentIndex] = new Button(actor);
    			// When clicked, return the movie and the actor's data to indicate which actor to be removed from the movie.
    			buttons[currentIndex].setOnAction(e -> removeFromMovie(movie, actors.get(actor)));
    			++currentIndex;
    		}
    	}
    	
    	Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setOnAction(e -> mainMenu());
        buttons[currentIndex] = buttonGoBack;
    	
    	for (int i = 0; i < buttons.length; i++) {
    		buttons[i].setMaxWidth(Double.MAX_VALUE);
    	}
    	
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
        vbButtons.getChildren().addAll(buttons);
        
        Scene removingActorsFromMovie = new Scene(vbButtons, 600, 400);
        loadScene(removingActorsFromMovie, "Remove a actor from " + movie.getTitle()); 
    }
    
    public void removeFromMovie(Movie movie, Actor actor) {
    	/*
    	 * Removes an actor from a movie.
    	 */
    	movie.getActors().remove(actor.getName());
    	actor.getMovies().remove(movie.getTitle());
    	
    	saveData();
    	showMovieOptions(movie);
    }
    
    public void showAllActors(Actors actors) {
    	/*
    	 * Shows all the actors from a list of actors given.
    	 */
    	Button[] buttons = new Button[actors.numberOfContents() + 1];
    	int currentIndex = 0;
    	
    	myLinkedList<String, Actor>[] actorsTable = actors.getHashTable();
    	
    	for (myLinkedList<String, Actor> actorsFromTable : actorsTable) {
    		for (String actor : actorsFromTable) {
    			buttons[currentIndex] = new Button(actor);
    			// When clicked, show all the actor's CRUD options.
    			buttons[currentIndex].setOnAction(e -> showActorOptions(actors.get(actor)));
    			++currentIndex;
    		}
    	}
    	
    	Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setOnAction(e -> mainMenu());
        buttons[currentIndex] = buttonGoBack;
    	
    	for (int i = 0; i < buttons.length; i++) {
    		buttons[i].setMaxWidth(Double.MAX_VALUE);
    	}
    	
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
        vbButtons.getChildren().addAll(buttons);
        
        Scene showAllActors = new Scene(vbButtons, 600, 400);
        loadScene(showAllActors, "Actors List"); 
    }
    
    public void showActorOptions(Actor actor) {
    	/*
    	 * Shows all actor CRUD options.
    	 */
    	// Show the actor's profile details.
    	Label labelActorDetails = new Label(actor.toString());
    	
    	Button buttonViewActors = new Button("View movies of actor");
    	buttonViewActors.setOnAction(e -> showAllMovies(actor.getMovies()));
    	
    	Button buttonEditMovie = new Button("Edit Actor");
    	buttonEditMovie.setOnAction(e -> promptEditActorDetails(actor));
    	
    	Button buttonRemoveMovie = new Button("Delete Actor");
    	buttonRemoveMovie.setOnAction(e -> deleteActor(actor));
    	
    	Button buttonGoBack = new Button("Go back to the main menu");
    	buttonGoBack.setOnAction(e -> mainMenu());
    	
    	Button[] buttons = {buttonViewActors, buttonEditMovie, buttonRemoveMovie, buttonGoBack};
    	for (int i = 0; i < buttons.length; i++) {
    		buttons[i].setMaxWidth(Double.MAX_VALUE);
    	}
    	
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
        
        vbButtons.getChildren().addAll(labelActorDetails);
        vbButtons.getChildren().addAll(buttons);
        
        Scene showActorOptions = new Scene(vbButtons, 600, 400);
        loadScene(showActorOptions, actor.getName() + "'s Options"); 
    }
    
    public void promptEditActorDetails(Actor actor) {
    	/*
    	 * Prompts the user to edit details about an actor.
    	 */
        Label label = new Label("Edit " + actor.getName() + " details.\nLeave details blank if you do not want to change their properties.");
        
        TextField dobInput = new TextField();
        dobInput.setPromptText("Date of Birth: " + actor.getDateofbirth());
             
        TextField genderInput = new TextField();
        genderInput.setPromptText("Gender: " + actor.getGender());
             
        TextField nationalityInput = new TextField();
        nationalityInput.setPromptText("Nationality: " + actor.getNationality());
            
        GridPane grid = new GridPane();
        grid.getChildren().addAll(dobInput, genderInput, nationalityInput);
             
        Button buttonEditActor = new Button("Finish Editing");
        buttonEditActor.setMaxWidth(Double.MAX_VALUE);
        buttonEditActor.setOnAction(e -> editActor(actor, dobInput.getText(), genderInput.getText(), nationalityInput.getText()));
 
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setMaxWidth(Double.MAX_VALUE);
        buttonGoBack.setOnAction(e -> mainMenu());
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(label, dobInput, genderInput, nationalityInput, buttonEditActor, buttonGoBack);
             
        Scene editActor = new Scene(vbButtons, 600, 400);
        loadScene(editActor, "Edit " + actor.getName() + "'s details");
    }
    
    public void editActor(Actor actor, String dateofbirth, String gender, String nationality) {
    	/*
    	 * Takes in an actor and input data which will be used to edit the actor's details.
    	 */
    	Actor editedActor = null;
    	
    	try {
    		// Every property that can be edited will be checked if the input data given is empty. If field is empty then the old actor's data will be used.
    		editedActor = new Actor(actor.getName(), dateofbirth.equals("") ? actor.getDateofbirth() : dateofbirth, gender.equals("") ? actor.getGender() : gender.charAt(0), nationality.equals("") ? actor.getNationality() : nationality);
    	} catch (NumberFormatException e) {
			e.printStackTrace();
    	}
    	
    	// Checking if the newly edited actor was successfully added without any errors.
    	if (editedActor == null) {
    		System.out.println("An error occured within input.");
    	} else {
    		System.out.println("Actor successfully edited.");
    		// Note that any actor with the same name will be automatically replaced by the newer one (editedActor overwrites original actor data).
    		actors.put(actor.getName(), editedActor);
    		saveData();
    	}
    	
    	showActorOptions(actor);
    }
    
    public void deleteActor(Actor actor) {
    	/*
    	 * Removes an actor from the system.
    	 */
    	actors.remove(actor.getName());
    	
    	myLinkedList<String, Movie>[] moviesTable = actor.getMovies().getHashTable();
    	
    	for (myLinkedList<String, Movie> moviesFromTable : moviesTable) {
    		for (String movie : moviesFromTable) {
    			// Removing all references of the actor from the movies that the actor starred in.
    			movies.get(movie).getActors().remove(actor.getName());
    		}
    	}
    	
    	saveData();
    	actorsListing();
    }
    
    public void promptSearchForMovie() {
    	/*
    	 * Prompts the user to search for a movie.
    	 */
    	Label label = new Label("Search for a movie by entering the appropriate details.\ni.e. Put 'Artist' in the title input if you want to search for movies with the title 'Artist'.\n\nLeave fields blank if you do not want to search according to that field.");
    	
        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");
        
        TextField yearInput = new TextField();
        yearInput.setPromptText("Year of Release");
             
        TextField runningtimeInput = new TextField();
        runningtimeInput.setPromptText("Running Time (in minutes) e.g. 125.5");
             
        TextField genreInput = new TextField();
        genreInput.setPromptText("Genre");
         
        Button buttonSearchMovie = new Button("Search for Movie");
        buttonSearchMovie.setMaxWidth(Double.MAX_VALUE);
        // When clicked, all the input data will be sent to searchForMovie which'll then sort out all the movies that match the data given.
        buttonSearchMovie.setOnAction(e -> searchForMovie(titleInput.getText(), yearInput.getText(), runningtimeInput.getText(), genreInput.getText()));
             
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setMaxWidth(Double.MAX_VALUE);
        buttonGoBack.setOnAction(e -> mainMenu());
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(label, titleInput, yearInput, runningtimeInput, genreInput, buttonSearchMovie, buttonGoBack);
             
        Scene addNewMovie = new Scene(vbButtons, 600, 400);
        loadScene(addNewMovie, "Search for a Movie");
    }
    
    public void searchForMovie(String title, String yearOfRelease, String runningTime, String genre) {
    	/*
    	 * Receives input data fields for a movie and checks them out appropriately to find the movie desired.
    	 */
    	// Create an initial movies list that contains the list of all movies.
    	Movies moviesFound = movies;
    	
    	// For each searchable property, check if they're empty or not. If they're not empty, search for the property given.
    	// Search methods are built into the movies object itself.
    	if (!title.equals("")) {
    		moviesFound = moviesFound.searchMoviesByTitle(title);
    	}
    	
    	if (!yearOfRelease.equals("")) {
    		// Checking if the year given is an actual year.
    		if (yearOfRelease.length() == 4 && yearOfRelease.toUpperCase().equals(yearOfRelease.toLowerCase())) {
    			moviesFound = moviesFound.searchByYear(yearOfRelease);
    		}
    	}
    	
    	if (!runningTime.equals("")) {
    		// Making sure the running time is a float value. If a string is given, the try clause will fail.
    		try {
    			float runningtime = Float.parseFloat(runningTime);
    			
    			moviesFound = moviesFound.searchByRunningTimes(runningtime);
    		} catch (Exception e) {
    			System.out.println("Invalid running time input.");
    		}
    	}
    	
    	if (!genre.equals("")) {
    		moviesFound = moviesFound.searchByGenre(genre);
    	}
    	
    	showAllMovies(moviesFound);
    }
    
    public void promptSearchForActor() {
    	/*
    	 * Prompts the user to search for an actor.
    	 */
    	Label label = new Label("Search for an actor by entering the appropriate details.\ni.e. Put 'Andy' in the name input if you want to search for actors with name 'Andy'.\n\nLeave fields blank if you do not want to search according to that field.");
    	
    	TextField nameInput = new TextField();
    	nameInput.setPromptText("Name");
    	
        TextField dobInput = new TextField();
        dobInput.setPromptText("Date of Birth (in DD/MM/YYYY format) e.g. 01/01/1998");
             
        TextField genderInput = new TextField();
        genderInput.setPromptText("Gender");
             
        TextField nationalityInput = new TextField();
        nationalityInput.setPromptText("Nationality");
             
        Button buttonSearchActor = new Button("Search for Actor");
        buttonSearchActor.setOnAction(e -> searchForActor(nameInput.getText(), dobInput.getText(), genderInput.getText(), nationalityInput.getText()));
        buttonSearchActor.setMaxWidth(Double.MAX_VALUE);
        
        Button buttonGoBack = new Button("Go back to the main menu");
        buttonGoBack.setMaxWidth(Double.MAX_VALUE);
        buttonGoBack.setOnAction(e -> mainMenu());
             
        VBox vbButtons = new VBox();
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(20, 20, 20, 20));
             
        vbButtons.getChildren().addAll(label, nameInput, dobInput, genderInput, nationalityInput, buttonSearchActor, buttonGoBack);
             
        Scene searchActor = new Scene(vbButtons, 600, 400);
        loadScene(searchActor, "Search for an Actor");
    }
    
    public void searchForActor(String name, String dateofbirth, String gender, String nationality) {
    	/*
    	 * Receives input data for an actor and sorts the data given appropriately, which will be used to search for a desired actor.
    	 */
    	Actors actorsFound = actors;
    	
    	if (!name.equals("")) {
    		actorsFound = actorsFound.searchActorsByName(name);
    	}
    	
    	if (!dateofbirth.equals("")) {
    		// Checking if the dateofbirth given matches the correct criteria for birthday info; DD/MM/YYYY.
    		if (dateofbirth.charAt(2) == '/' && dateofbirth.charAt(5) == '/' && dateofbirth.length() == 10) {
    			actorsFound = actorsFound.searchByDateOfBirth(dateofbirth);
    		}
    	}
    	
    	if (!gender.equals("")) {
    		actorsFound = actorsFound.searchByGender(gender.charAt(0));
    	}
    	
    	if (!nationality.equals("")) {
    		actorsFound = actorsFound.searchByNationality(nationality);
    	}
    	
    	showAllActors(actorsFound);
    }
    
	public void loadData() {
		/*
		 * Loads existing movies and actors back into the hashtables via XML file.
		 */
		XStream xstream = new XStream(new DomDriver());

		try {
			ObjectInputStream in = xstream.createObjectInputStream(new FileReader("data.xml"));
		
			movies = (Movies) in.readObject();
			actors = (Actors) in.readObject();
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveData() {
		/*
		 * Saves existing movies and actors back into the XML file.
		 */
		XStream xstream = new XStream(new DomDriver());
		
		try {
			ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("data.xml"));
			
			out.writeObject(movies);
			out.writeObject(actors);
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}