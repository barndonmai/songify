
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library {
	private ArrayList<Song> songs;
	private ArrayList<AudioBook> audiobooks;
	private ArrayList<Playlist> playlists;

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public ArrayList<AudioBook> getAudiobooks() {
		return audiobooks;
	}

	// Public methods in this class set errorMsg string
	// Error Messages can be retrieved from main in class MyAudioUI by calling
	// getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions

	public Library() {
		songs = new ArrayList<Song>();
		audiobooks = new ArrayList<AudioBook>();
		playlists = new ArrayList<Playlist>();
	}

	/*
	 * Download audio content from the store. Since we have decided (design
	 * decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list)
	 * then we need to look at the type of
	 * audio content to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already
	 * there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false.
	 * Otherwise add it to the list and return true
	 */
	public void download(AudioContent content) {

		if (content == null) {

			throw new AudioContentNotFoundException("Content Not Found");
		}
		if (content.getType().equals(Song.TYPENAME)) {
			if (songs.contains(content)) {

				throw new AudioContentAlreadyDownloaded(
						"Song " + content.getTitle() + " already downloaded");
			}

			songs.add((Song) content);
			System.out.print(content.getType() + " " + content.getTitle() + " Added to Library\n");

		} else if (content.getType().equals(AudioBook.TYPENAME)) {
			if (audiobooks.contains(content)) {
				throw new AudioContentAlreadyDownloaded(
						"AudioBook " + content.getTitle() + " already downloaded");
			}

			audiobooks.add((AudioBook) content);
			System.out.print(content.getType() + " " + content.getTitle() + " Added to Library\n");

		}

	}

	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs() {
		for (int i = 0; i < songs.size(); i++) {
			int index = i + 1;
			System.out.print(index + ". ");
			songs.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks() {
		for (int i = 0; i < audiobooks.size(); i++) {
			int index = i + 1;
			System.out.print(index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}

	// Print Information (printInfo()) about all podcasts in the array list

	// Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists() {
		for (int i = 0; i < playlists.size(); i++) {
			int index = i + 1;
			System.out.println(index + ". " + playlists.get(i).getTitle());
		}
	}

	// Print the name of all artists.
	// Go through the songs array list and add to an arraylist only if it is
	// not already there. Once the artist arraylist is complete, print the artists
	// names
	public void listAllArtists() {
		ArrayList<String> artists = new ArrayList<String>();

		for (Song song : songs) {
			if (!artists.contains(song.getArtist()))
				artists.add(song.getArtist());
		}
		for (int i = 0; i < artists.size(); i++) {
			int index = i + 1;
			System.out.println(index + ". " + artists.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) -
	// also go through all playlists and remove it from any playlist as well
	public void deleteSong(int index) {
		if (index < 1 || index > songs.size()) {
			throw new AudioContentNotFoundException("Song Not Found");

		}
		Song song = songs.remove(index - 1);

		// Search all playlists
		for (int i = 0; i < playlists.size(); i++) {
			Playlist pl = playlists.get(i);
			if (pl.getContent().contains(song))
				pl.getContent().remove(song);
		}

	}

	// Sort songs in library by year
	public void sortSongsByYear() {
		Collections.sort(songs, new SongYearComparator());
	}

	private class SongYearComparator implements Comparator<Song> {
		public int compare(Song a, Song b) {
			if (a.getYear() > b.getYear())
				return 1;
			if (a.getYear() < b.getYear())
				return -1;
			return 0;
		}
	}

	// Sort songs by length
	public void sortSongsByLength() {
		Collections.sort(songs, new SongLengthComparator());
	}

	private class SongLengthComparator implements Comparator<Song> {
		public int compare(Song a, Song b) {
			if (a.getLength() > b.getLength())
				return 1;
			if (a.getLength() < b.getLength())
				return -1;
			return 0;
		}
	}

	// Sort songs by title (Comparable)
	public void sortSongsByName() {
		Collections.sort(songs);
	}

	/*
	 * Play Content
	 */

	// Play song from songs list
	public void playSong(int index) {

		if (index < 1 || index > songs.size()) {
			throw new AudioContentNotFoundException("Song Not Found");
		}
		songs.get(index - 1).play();

	}

	// Play audio book from list
	public void playAudioBook(int index, int chapter) {
		if (index < 1 || index > audiobooks.size()) {
			throw new AudioContentNotFoundException("AudioBook Not Found");

		}
		AudioBook book = audiobooks.get(index - 1);
		if (chapter < 1 || chapter > book.getNumberOfChapters()) {
			throw new AudioContentNotFoundException("AudioBook Chapter Not Found");

		}
		book.selectChapter(chapter);
		book.play();

	}

	public void printAudioBookTOC(int index) {
		if (index < 1 || index > audiobooks.size()) {
			throw new AudioContentNotFoundException("AudioBook Not Found");
		}
		AudioBook book = audiobooks.get(index - 1);
		book.printTOC();
	}

	/*
	 * Playlist Related Methods
	 */

	// Make a new playlist
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title) {
		Playlist pl = new Playlist(title);
		if (playlists.contains(pl)) {
			throw new PlaylistAlreadyExists("Playlist " + title + " Already Exists");
		}
		playlists.add(pl);
	}

	// Print list of content (songs, audiobooks etc) in playlist named title from
	// list of playlists
	public void printPlaylist(String title) {
		int index = playlists.indexOf(new Playlist(title));

		if (index == -1) {
			throw new AudioContentNotFoundException("Playlist Not Found");

		}
		playlists.get(index).printContents();

	}

	// Play all content in a playlist
	public void playPlaylist(String playlistTitle) {
		int index = playlists.indexOf(new Playlist(playlistTitle));

		if (index == -1) {
			throw new AudioContentNotFoundException("Playlist Not Found");
		}

		playlists.get(index).playAll();

	}

	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int index) {
		int plIndex = playlists.indexOf(new Playlist(playlistTitle));

		if (plIndex == -1) {
			throw new AudioContentNotFoundException("Playlist Not Found");
		}
		Playlist pl = playlists.get(plIndex);

		if (index < 1 || index > pl.getContent().size()) {
			throw new AudioContentNotFoundException("Invalid Playlist AudioContent #");

		}
		System.out.println(pl.getTitle());

		// Play chapter 1 if this is an audio book. Could also set it to play all
		if (pl.getContent(index).getType().equals(AudioBook.TYPENAME)) {
			AudioBook book = (AudioBook) pl.getContent(index);
			book.selectChapter(1);
		}

		pl.play(index);

	}

	public void addContentToPlaylist(String type, int index, String playlistTitle) {

		if (!type.equalsIgnoreCase(Song.TYPENAME) || !type.equalsIgnoreCase(Song.TYPENAME)) {
			throw new AudioContentNotFoundException("Content Not Found");

		}

		if (!playlists.contains(playlistTitle)) {
			throw new AudioContentNotFoundException("Playlist " + playlistTitle + " Does Not Exist");
		}

		for (int i = 0; i < playlists.size(); i++) {
			if (playlistTitle.equals(playlists.get(i).getTitle())) {

				// check for song type
				if (type.equalsIgnoreCase(Song.TYPENAME)) {
					AudioContent content = (AudioContent) songs.get(index - 1);
					// add content into the playlists content aka the type

					playlists.get(i).addContent(content);

				}

				// check for audiobook type
				else if (type.equalsIgnoreCase(AudioBook.TYPENAME)) {

					// casts the content of the audiobook into already casted content
					AudioContent content = (AudioContent) audiobooks.get(index - 1);

					// add content into the playlists content aka the type
					playlists.get(i).addContent(content);

				}

				else {
					throw new AudioContentNotFoundException("Content Not Found");
				}
			}

		}

	}

	// Delete a song/audiobook/podcast from a playlist
	// Make sure the index is valid
	public void delContentFromPlaylist(int index, String playlistTitle) {

		int plIndex = playlists.indexOf(new Playlist(playlistTitle));

		if (plIndex == -1) {
			throw new AudioContentNotFoundException("Playlist Not Found");

		}
		Playlist pl = playlists.get(plIndex);

		// Delete Content
		if (!pl.contains(index)) {
			throw new AudioContentNotFoundException("Content Not In Playlist");

		}
		pl.deleteContent(index);

	}

}

// Custom already downloaded exception
class AudioContentAlreadyDownloaded extends RuntimeException {

	AudioContentAlreadyDownloaded(String message) {
		super(message);

	}

}

// Custom audio content not found exception
class AudioContentNotFoundException extends RuntimeException {

	AudioContentNotFoundException(String message) {
		System.out.println(message);

	}

}

// Custom playlist already exists exception
class PlaylistAlreadyExists extends RuntimeException {

	PlaylistAlreadyExists(String message) {
		super(message);

	}

}