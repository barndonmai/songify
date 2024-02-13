
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
	private ArrayList<AudioContent> contents;

	// Initalize hashmaps for title, artist, and genre
	private HashMap<String, Integer> titleMap = new HashMap<String, Integer>();
	private HashMap<String, ArrayList<Integer>> artistMap = new HashMap<String, ArrayList<Integer>>();
	private HashMap<String, ArrayList<Integer>> genreMap = new HashMap<String, ArrayList<Integer>>();

	// Initalize getters for contents, title, artist, and genre (because I did it in
	// MyAudioUI)
	public ArrayList<AudioContent> getContents() {
		return contents;
	}

	public HashMap<String, Integer> getTitleMap() {
		return titleMap;
	}

	public HashMap<String, ArrayList<Integer>> getArtistMap() {
		return artistMap;
	}

	public HashMap<String, ArrayList<Integer>> getGenreMap() {
		return genreMap;
	}

	public AudioContentStore() {

		try {
			storeTxtArrayList("store.txt");

		}

		catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		finally {

			// Iterate through all contents
			for (int i = 0; i < contents.size(); i++) {

				// Add title of content along with indice into title map
				titleMap.put(contents.get(i).getTitle(), i + 1);

				// Check if AudioBook
				if (contents.get(i).getType().equalsIgnoreCase("AUDIOBOOK")) {

					// Create new ArrayList to keep track of AudioBook Indices
					ArrayList<Integer> audiobookIndices = new ArrayList<Integer>();

					// Cast AudioBook into contents.get(i)
					// Load only AudioBook objects (songs are loaded individually as well )
					AudioBook audiobooks = (AudioBook) contents.get(i);

					System.out.println("LOADING " + audiobooks.getType());

					// Add in the indices for each AudioBook loaded
					audiobookIndices.add(i + 1);

					// Check if author key is already in hashmap
					// If it's not in the hashmap already, add the author and index into indices
					// ArrayList

					if (!artistMap.containsKey(audiobooks.getAuthor())) {
						artistMap.put(audiobooks.getAuthor(), audiobookIndices);
					}

					else {

						// If it is already in the hashmap, add the index into the indices ArrayList
						artistMap.get(audiobooks.getAuthor()).add(i + 1);
					}
				}

				// Check if Song
				else if (contents.get(i).getType().equalsIgnoreCase("SONG")) {

					// Create an empty songIndices empty ArrayList
					ArrayList<Integer> songIndices = new ArrayList<Integer>();

					// Cast Song into contents.get(i) and load only the Song objects
					Song songs = (Song) contents.get(i);
					System.out.println("LOADING " + songs.getType());

					// Add in the corresponding song indices for the songs
					songIndices.add(i + 1);

					// Check if artist key is already in hashmap
					// If it's not in the hashmap already, add the artist and index into indices
					// ArrayList
					if (!artistMap.containsKey(songs.getArtist())) {
						artistMap.put(songs.getArtist(), songIndices);

					}
					// If it is already in the hashmap, add the index into the indices ArrayList
					else {
						artistMap.get(songs.getArtist()).add(i + 1);
					}

					// Check if genre key is already in hashmap
					// If it's not in the hashmap already, add the genre and index into indices
					// ArrayList
					if (!genreMap.containsKey(songs.getGenre().toString())) {
						genreMap.put(songs.getGenre().toString(), songIndices);
					}

					else {
						// If it is already in the hashmap, add the index into the indices ArrayList

						genreMap.get(songs.getGenre().toString()).add(i + 1);

					}

				}

			}

		}

	}

	// Load contents into the AudioContent ArrayList that takes in the file and
	// throws IOException if not found
	private ArrayList<AudioContent> storeTxtArrayList(String file) throws IOException {

		// Create the new contents list since before it was hard-coded
		contents = new ArrayList<AudioContent>();

		// Initalize the file
		File storeFile = new File("store.txt");

		// scanner takes in file
		Scanner scanner = new Scanner(storeFile);

		while (scanner.hasNextLine()) {

			// Since lines 2-9 are the corresponding values, each line just sets the
			// variable to the line
			String line = scanner.nextLine();
			if (line.equalsIgnoreCase("SONG")) {
				String type = "SONG";
				String id = scanner.nextLine();
				String title = scanner.nextLine();
				int year = scanner.nextInt();
				int length = scanner.nextInt();
				scanner.nextLine(); // consume the newline character left by nextInt()
				String artist = scanner.nextLine();
				String composer = scanner.nextLine();
				String genre = scanner.nextLine();
				int numLyrics = scanner.nextInt();
				scanner.nextLine(); // consume the newline character left by nextInt()
				String lyrics = "";

				// For however many lines of lyrics, add each line to one block of lyrics
				for (int i = 0; i < numLyrics; i++) {
					String lyric = scanner.nextLine();
					lyrics += lyric + "\n";
				}

				// Add in the new Song object containing all of this information into the
				// contents ArrayList
				contents.add(new Song(title, year, id, type, file, length, artist, composer, Song.Genre.valueOf(genre),
						lyrics));

			}

			if (line.equalsIgnoreCase("AUDIOBOOK")) {
				ArrayList<String> chapters = new ArrayList();

				// Since lines 2-8 are the corresponding values, each line just sets the
				// variable to the line

				String type = "AUDIOBOOK";
				String id = scanner.nextLine();
				String title = scanner.nextLine();
				int year = scanner.nextInt();
				scanner.nextLine(); // consume int
				int length = scanner.nextInt();
				scanner.nextLine(); // consume int
				String author = scanner.nextLine();
				String narrator = scanner.nextLine();
				int numChapters = scanner.nextInt();
				scanner.nextLine(); // consume int

				ArrayList<String> chapterTitles = new ArrayList();

				// Add in the titles based on however many chapters the book has
				for (int i = 0; i < numChapters; i++) {
					String titles = scanner.nextLine();
					chapterTitles.add(titles);

				}

				// For however many chapters, it adds in the lines into a block of lines and
				// then gets added to the chapters ArrayList
				for (int p = 0; p < numChapters; p++) {
					int contentAmount = scanner.nextInt();
					String lines = "";
					for (int k = 0; k < contentAmount + 1; k++) {

						lines += scanner.nextLine() + "\n";
					}

					chapters.add(lines);
				}

				// Adds a new AudioBook object containing all of this information into the
				// contents ArrayList

				contents.add(
						new AudioBook(title, year, id, type, file, length, author, narrator, chapterTitles, chapters));

			}

		}

		// Returns the content ArrayList
		return contents;

	}

	public AudioContent getContent(int index) {
		if (index < 1 || index > contents.size()) {
			return null;
		}
		return contents.get(index - 1);
	}

	public void listAll() {
		for (int i = 0; i < contents.size(); i++) {
			int index = i + 1;
			System.out.print(index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

}
