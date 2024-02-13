Brandon Mai

If it says "store.txt" is not found, you might have to open the folder itself in your editor/compiler.

Deleted all hard-coded chapter related things in AudioContentStore since it's now reading from the store.txt file.

The main program to run everything together is MyAudioUI.java.

The list of commands are:

Basic functionality: playsong, playbook, artists, delsong, makepl, printpl, addtopl, delfrompl, playallpl, playpl, sortbylength, sortbyyear, and sortbyname.

Additional features: store, download, downloada, downloadg, search, searcha, and searchg. the 'a' and 'g' suffix at the end of search and download represent artist and genre.

Here are some test cases I did to show some output in the command line:
------------------------------------
                                                                                                                                                                                                                                                                                                     
LOADING SONG
LOADING SONG
LOADING SONG
LOADING SONG
LOADING SONG
LOADING SONG
LOADING SONG
LOADING SONG
LOADING SONG
LOADING AUDIOBOOK
LOADING AUDIOBOOK
LOADING AUDIOBOOK
>store 
1. Title: Yesterday Id: 123 Year: 1965 Type: SONG Length: 2
Artist: The Beatles Composer: Paul McCartney Genre: POP

2. Title: Here Id: 391 Year: 2015 Type: SONG Length: 3
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

3. Title: Man's Not Hot Id: 374 Year: 2017 Type: SONG Length: 2
Artist: Michael Dapaah Composer: Michael Dapaah Genre: RAP

4. Title: Wicked Game Id: 185 Year: 1989 Type: SONG Length: 4
Artist: Chris Isaak Composer: Chris Isaak Genre: ROCK

5. Title: Clocks Id: 875 Year: 2002 Type: SONG Length: 5
Artist: Coldplay Composer: Guy Berryman, Chris Martin Genre: ROCK

6. Title: Scars To Your Beautiful Id: 222 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

7. Title: Radioactive Id: 823 Year: 2012 Type: SONG Length: 3
Artist: Imagine Dragons Composer: Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee Genre: ROCK

8. Title: Feelin' Good" Id: 872 Year: 1965 Type: SONG Length: 3
Artist: Nina Simone Composer: Anthony Newley, Leslie Bricusse Genre: JAZZ

9. Title: Wild Things Id: 443 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

10. Title: Harry Potter and the Goblet of Fire Id: 894 Year: 2015 Type: AUDIOBOOK Length: 1236
Author: J.K. Rowling Narrated by: Jim Dale

11. Title: Moby Dick Id: 376 Year: 2018 Type: AUDIOBOOK Length: 1422
Author: Herman Melville Narrated by: Pete Cross

12. Title: Shogun Id: 284 Year: 2018 Type: AUDIOBOOK Length: 3213
Author: James Clavel Narrated by: Ralph Lister


>search
Title: Wild Things
9. Title: Wild Things Id: 443 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

>search
Title: Hey Jude
No matches for Hey Jude

>searcha
Artist: Alessia Cara
2. Title: Here Id: 391 Year: 2015 Type: SONG Length: 3
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

6. Title: Scars To Your Beautiful Id: 222 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

9. Title: Wild Things Id: 443 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP


>searcha
Artist: Adelle
No matches for Adelle

>searchg
Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ROCK
4. Title: Wicked Game Id: 185 Year: 1989 Type: SONG Length: 4
Artist: Chris Isaak Composer: Chris Isaak Genre: ROCK

5. Title: Clocks Id: 875 Year: 2002 Type: SONG Length: 5
Artist: Coldplay Composer: Guy Berryman, Chris Martin Genre: ROCK

7. Title: Radioactive Id: 823 Year: 2012 Type: SONG Length: 3
Artist: Imagine Dragons Composer: Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee Genre: ROCK


>download
From Store Content #: 2
To Store Content #: 8
SONG Here Added to Library
SONG Man's Not Hot Added to Library
SONG Wicked Game Added to Library
SONG Clocks Added to Library
SONG Scars To Your Beautiful Added to Library
SONG Radioactive Added to Library
SONG Feelin' Good" Added to Library

>download
From Store Content #: 1
To Store Content #: 7
SONG Yesterday Added to Library
Song Here already downloaded
Song Man's Not Hot already downloaded
Song Wicked Game already downloaded
Song Clocks already downloaded
Song Scars To Your Beautiful already downloaded
Song Radioactive already downloaded

>downloada
Artist Name: Alessia Cara
Song Here already downloaded
Song Scars To Your Beautiful already downloaded

>downloadg
Genre: POP
Song Yesterday already downloaded
Song Here already downloaded
Song Scars To Your Beautiful already downloaded
Song Wild Things already downloaded

>songs
1. Title: Here Id: 391 Year: 2015 Type: SONG Length: 3
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

2. Title: Man's Not Hot Id: 374 Year: 2017 Type: SONG Length: 2
Artist: Michael Dapaah Composer: Michael Dapaah Genre: RAP

3. Title: Wicked Game Id: 185 Year: 1989 Type: SONG Length: 4
Artist: Chris Isaak Composer: Chris Isaak Genre: ROCK

4. Title: Clocks Id: 875 Year: 2002 Type: SONG Length: 5
Artist: Coldplay Composer: Guy Berryman, Chris Martin Genre: ROCK

5. Title: Scars To Your Beautiful Id: 222 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

6. Title: Radioactive Id: 823 Year: 2012 Type: SONG Length: 3
Artist: Imagine Dragons Composer: Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee Genre: ROCK

7. Title: Feelin' Good" Id: 872 Year: 1965 Type: SONG Length: 3
Artist: Nina Simone Composer: Anthony Newley, Leslie Bricusse Genre: JAZZ

8. Title: Yesterday Id: 123 Year: 1965 Type: SONG Length: 2
Artist: The Beatles Composer: Paul McCartney Genre: POP

9. Title: Wild Things Id: 443 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP


>store
1. Title: Yesterday Id: 123 Year: 1965 Type: SONG Length: 2
Artist: The Beatles Composer: Paul McCartney Genre: POP

2. Title: Here Id: 391 Year: 2015 Type: SONG Length: 3
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

3. Title: Man's Not Hot Id: 374 Year: 2017 Type: SONG Length: 2
Artist: Michael Dapaah Composer: Michael Dapaah Genre: RAP

4. Title: Wicked Game Id: 185 Year: 1989 Type: SONG Length: 4
Artist: Chris Isaak Composer: Chris Isaak Genre: ROCK

5. Title: Clocks Id: 875 Year: 2002 Type: SONG Length: 5
Artist: Coldplay Composer: Guy Berryman, Chris Martin Genre: ROCK

6. Title: Scars To Your Beautiful Id: 222 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

7. Title: Radioactive Id: 823 Year: 2012 Type: SONG Length: 3
Artist: Imagine Dragons Composer: Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee Genre: ROCK

8. Title: Feelin' Good" Id: 872 Year: 1965 Type: SONG Length: 3
Artist: Nina Simone Composer: Anthony Newley, Leslie Bricusse Genre: JAZZ

9. Title: Wild Things Id: 443 Year: 2015 Type: SONG Length: 4
Artist: Alessia Cara Composer: Alessia Cara Genre: POP

10. Title: Harry Potter and the Goblet of Fire Id: 894 Year: 2015 Type: AUDIOBOOK Length: 1236
Author: J.K. Rowling Narrated by: Jim Dale

11. Title: Moby Dick Id: 376 Year: 2018 Type: AUDIOBOOK Length: 1422
Author: Herman Melville Narrated by: Pete Cross

12. Title: Shogun Id: 284 Year: 2018 Type: AUDIOBOOK Length: 3213
Author: James Clavel Narrated by: Ralph Lister


>download
From Store Content #: 10
To Store Content #: 12
AUDIOBOOK Harry Potter and the Goblet of Fire Added to Library
AUDIOBOOK Moby Dick Added to Library
AUDIOBOOK Shogun Added to Library

>books
1. Title: Harry Potter and the Goblet of Fire Id: 894 Year: 2015 Type: AUDIOBOOK Length: 1236
Author: J.K. Rowling Narrated by: Jim Dale

2. Title: Moby Dick Id: 376 Year: 2018 Type: AUDIOBOOK Length: 1422
Author: Herman Melville Narrated by: Pete Cross

3. Title: Shogun Id: 284 Year: 2018 Type: AUDIOBOOK Length: 3213
Author: James Clavel Narrated by: Ralph Lister


>download
From Store Content #: 11
To Store Content #: 11
AudioBook Moby Dick already downloaded

>quit


