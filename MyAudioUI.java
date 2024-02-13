
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI {
	public static void main(String[] args) {
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your
		// library
		AudioContentStore store = new AudioContentStore();

		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()) {
			String action = scanner.nextLine();

			if (action == null || action.equals("")) {
				System.out.print("\n>");
				continue;
			} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("STORE")) // List all songs
			{
				store.listAll();
			} else if (action.equalsIgnoreCase("SONGS")) // List all songs
			{
				library.listAllSongs();
			} else if (action.equalsIgnoreCase("BOOKS")) // List all songs
			{
				library.listAllAudioBooks();
			} else if (action.equalsIgnoreCase("ARTISTS")) // List all songs
			{
				library.listAllArtists();
			} else if (action.equalsIgnoreCase("PLAYLISTS")) // List all play lists
			{
				library.listAllPlaylists();
			} else if (action.equalsIgnoreCase("DOWNLOAD")) {
				// Initalize from and to store content variables
				int fromStoreContent = 0;
				int toStoreContent = 0;

				try {
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt()) {
						fromStoreContent = scanner.nextInt();
						scanner.nextLine(); // consume nl

					}

					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt()) {
						toStoreContent = scanner.nextInt();
						scanner.nextLine(); // consume nl

					}

					else {
						throw new AudioContentNotFoundException("Content Not Found");
					}

				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

				// Downloads each piece of content in the range of from and to store content #
				for (int i = fromStoreContent; i < toStoreContent + 1; i++) {
					AudioContent content = store.getContent(i);
					try {

						library.download(content);

					}

					catch (AudioContentNotFoundException e) {
						System.out.println(e.getMessage());

					}

					catch (AudioContentAlreadyDownloaded e) {
						System.out.println(e.getMessage());

					}

				}

			} else if (action.equalsIgnoreCase("PLAYSONG")) {

				try {
					int index = 0;

					System.out.print("Song Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						// consume the nl character since nextInt() does not
						scanner.nextLine();
					}
					library.playSong(index);

				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			} else if (action.equalsIgnoreCase("BOOKTOC")) {
				try {
					int index = 0;

					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					library.printAudioBookTOC(index);

				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			} else if (action.equalsIgnoreCase("PLAYBOOK")) {

				try {
					int index = 0;

					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
					}
					int chapter = 0;
					System.out.print("Chapter: ");
					if (scanner.hasNextInt()) {
						chapter = scanner.nextInt();
						scanner.nextLine();
					}
					library.playAudioBook(index, chapter);
				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}

			else if (action.equalsIgnoreCase("PLAYALLPL")) {

				try {

					String title = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine()) {
						title = scanner.nextLine();
					}
					library.playPlaylist(title);
				}

				catch (AudioContentNotFoundException e) {

					System.out.println(e.getMessage());

				}

			}

			else if (action.equalsIgnoreCase("PLAYPL")) {

				try {
					String title = "";
					int index = 0;

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine()) {
						title = scanner.nextLine();
					}
					System.out.print("Content Number: ");
					if (scanner.hasNextInt()) {
						index = scanner.nextInt();
						scanner.nextLine();
					}
					library.playPlaylist(title, index);

				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}
			// Delete a song from the library and any play lists it belongs to
			else if (action.equalsIgnoreCase("DELSONG")) {

				try {
					int songNum = 0;

					System.out.print("Library Song #: ");
					if (scanner.hasNextInt()) {
						songNum = scanner.nextInt();
						scanner.nextLine();
					}

					library.deleteSong(songNum);

				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			} else if (action.equalsIgnoreCase("MAKEPL")) {

				try {
					String title = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine()) {
						title = scanner.nextLine();
					}
					library.makePlaylist(title);
				}

				catch (PlaylistAlreadyExists e) {
					System.out.println(e.getMessage());
				}

			} else if (action.equalsIgnoreCase("PRINTPL")) // print playlist content
			{
				try {
					String title = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						title = scanner.nextLine();

					library.printPlaylist(title);

				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}
			// Add content from library (via index) to a playlist
			else if (action.equalsIgnoreCase("ADDTOPL")) {

				try {
					int contentIndex = 0;
					String contentType = "";
					String playlist = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();

					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					if (scanner.hasNextLine())
						contentType = scanner.nextLine();

					System.out.print("Library Content #: ");
					if (scanner.hasNextInt()) {
						contentIndex = scanner.nextInt();
						scanner.nextLine(); // consume nl
					}

					library.addContentToPlaylist(contentType, contentIndex, playlist);
				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}
			// Delete content from play list
			else if (action.equalsIgnoreCase("DELFROMPL")) {

				try {
					int contentIndex = 0;
					String playlist = "";

					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();

					System.out.print("Playlist Content #: ");
					if (scanner.hasNextInt()) {
						contentIndex = scanner.nextInt();
						scanner.nextLine(); // consume nl
					}
					library.delContentFromPlaylist(contentIndex, playlist);

				}

				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			} else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				library.sortSongsByYear();
			} else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				library.sortSongsByName();
			} else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				library.sortSongsByLength();
			} else if (action.equalsIgnoreCase("SEARCH")) {

				String title = ""; // I initalize title variable outside of the try block so that I can refer back
									// to it in the catch block
				try {
					System.out.print("Title: ");
					if (scanner.hasNextLine()) {
						title = scanner.nextLine();

						// Sets the index to whatever the corresponding value is based on the title
						// (key)
						int index = store.getTitleMap().get(title);
						System.out.print(index + ". ");

						// Uses the contents ArrayList getter initialized in AudioContentStore and uses
						// printInfo to display the information
						store.getContents().get(index - 1).printInfo();

					}
				} catch (Exception e) {
					System.out.println("No matches for " + title);
				}
			} else if (action.equalsIgnoreCase("SEARCHA")) {

				String artist = "";
				try {
					System.out.print("Artist: ");
					if (scanner.hasNextLine()) {
						artist = scanner.nextLine();

						ArrayList<Integer> index = store.getArtistMap().get(artist);
						// Sets the index to whatever the corresponding value is based on the artist
						// (key)

						for (int i = 0; i < index.size(); i++) {
							System.out.print(index.get(i) + ". ");

							// Uses the contents ArrayList getter initialized in AudioContentStore and uses
							// printInfo to display the information
							store.getContents().get(index.get(i) - 1).printInfo();

							// new line
							System.out.println();
						}

					}
				} catch (Exception e) {
					System.out.println("No matches for " + artist);
				}

			} else if (action.equalsIgnoreCase("SEARCHG")) {

				String genre = "";
				try {
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					if (scanner.hasNextLine()) {
						genre = scanner.nextLine();

						// Sets the index to whatever the corresponding value is based on the genre
						// (key)
						ArrayList<Integer> index = store.getGenreMap().get(genre);

						for (int i = 0; i < index.size(); i++) {
							System.out.print(index.get(i) + ". ");

							// Uses the contents ArrayList getter initialized in AudioContentStore and uses
							// printInfo to display the information
							store.getContents().get(index.get(i) - 1).printInfo();

							// new line
							System.out.println();
						}

					}
				} catch (Exception e) {
					System.out.println("No matches for " + genre);
				}

			} else if (action.equalsIgnoreCase("DOWNLOADA")) {

				String artist = "";

				System.out.print("Artist Name: ");
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine();

					// I have a nested try block here so that it catches the content not found
					// exception if artistIndex is null and keeps looping without terminating the
					// program
					try {
						// Sets this artistIndex ArrayList to the integer ArrayList belonging to the
						// artist in the hashmap
						ArrayList<Integer> artistIndex = store.getArtistMap().get(artist);

						// Make sure the artist key has a value (if they type in Adelle, it returns null
						// since that key doesn't exist in the hashmap)
						if (artistIndex == null) {
							throw new AudioContentNotFoundException("Content Not Found");
						}

						// Iterates through how many songs the artist has
						for (int i = 0; i < artistIndex.size(); i++) {

							// Uses the content getter to grab the corresponding Song belonging to the
							// artist key out of the contents ArrayList
							AudioContent content = store.getContent(artistIndex.get(i));

							try {

								// Check if Song
								if (content.getType().equals(Song.TYPENAME)) {

									// Check if the Songs arraylist already contains the content by using the
									// getSongs() getter
									if (library.getSongs().contains(content)) {

										// Throws custom already downloaded exception
										throw new AudioContentAlreadyDownloaded(
												"Song " + content.getTitle() + " already downloaded");
									}

									// Adds new Song into Songs arrayList
									library.getSongs().add((Song) content);

								}

								// Check if AudioBook
								else if (content.getType().equals(AudioBook.TYPENAME)) {

									// Check if AudioBooks arraylist already contains the content by using the
									// getAudioBooks() ArrayList getter
									if (library.getAudiobooks().contains(content)) {

										// Throws custom already downloaded exception
										throw new AudioContentAlreadyDownloaded(
												"AudioBook " + content.getTitle() + " already downloaded");
									}

									// Adds new AudioBook into AudioBooks ArrayList
									library.getAudiobooks().add((AudioBook) content);

								}

							} catch (AudioContentAlreadyDownloaded e) {
								System.out.println(e.getMessage());
							} catch (AudioContentNotFoundException e) {
								System.out.println(e.getMessage());
							}

						}
					}

					catch (AudioContentNotFoundException e) {

						System.out.println();

					}

				}

			} else if (action.equalsIgnoreCase("DOWNLOADG")) {

				String genre = "";
				// I have a nested try block here so that it catches the content not found
				// exception if artistIndex is null and keeps looping without terminating the
				// program

				try {
					System.out.print("Genre: ");
					if (scanner.hasNextLine()) {
						genre = scanner.nextLine();
						ArrayList<Integer> genreIndex = store.getGenreMap().get(genre);
						// Make sure the genre key has a value (if they type in R&B, it returns null
						// since that key doesn't exist in the hashmap)
						if (genreIndex == null) {

							throw new AudioContentNotFoundException("Content Not Found");
						}
						for (int i = 0; i < genreIndex.size(); i++) {
							AudioContent content = store.getContent(genreIndex.get(i));

							try {

								// Check if Song
								if (content.getType().equals(Song.TYPENAME)) {

									// Check if the Songs arraylist already contains the content by using the
									// getSongs() getter

									if (library.getSongs().contains(content)) {

										// Throws custom already downloaded exception
										throw new AudioContentAlreadyDownloaded(
												"Song " + content.getTitle() + " already downloaded");

									}

									// Adds Song into Songs ArrayList
									library.getSongs().add((Song) content);

								}

								// Check if AudioBook
								else if (content.getType().equals(AudioBook.TYPENAME)) {

									// Check if the AudioBooks arraylist already contains the content by using the
									// getAudioBooks() getter
									if (library.getAudiobooks().contains(content)) {

										// Throws custom already downloaded exception
										throw new AudioContentAlreadyDownloaded(
												"AudioBook " + content.getTitle() + " already downloaded");
									}

									// Adds AudioBook into AudioBooks ArrayList
									library.getAudiobooks().add((AudioBook) content);

								}
							}

							catch (AudioContentNotFoundException e) {
								System.out.println("No matches for " + genre);
							}

							catch (AudioContentAlreadyDownloaded e) {
								System.out.println(e.getMessage());
							}
						}
					}
				}

				catch (AudioContentNotFoundException e) {
					System.out.println();
				}

			}

			System.out.print("\n>");
		}
	}
}
