import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist {
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even a mixture

	public Playlist(String title) {
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;

	}

	public void addContent(AudioContent content) {
		contents.add(content);
	}

	public ArrayList<AudioContent> getContent() {
		return contents;
	}

	public AudioContent getContent(int index) {
		if (index >= 1 && index <= contents.size()) {
			return contents.get(index - 1);
		}
		return null;
	}

	public void setContent(ArrayList<AudioContent> contents) {
		this.contents = contents;
	}

	public void printContents() {
		for (int i = 0; i < contents.size(); i++) {
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll() {
		for (AudioContent content : contents) {
			content.play();
			System.out.println();
		}
	}

	// Play the specific AudioContent from the contents array list.
	// Make sure the index is in the correct range.
	public void play(int index) {
		if (index >= 1 && index <= contents.size()) {
			contents.get(index - 1).play();
		}
	}

	public boolean contains(int index) {
		return index >= 1 && index <= contents.size();
	}

	public boolean equals(Object other) {
		Playlist otherPL = (Playlist) other;
		return this.title.equals(otherPL.title);
	}

	public void deleteContent(int index) {
		if (!contains(index))
			return;
		contents.remove(index - 1);
	}

}
