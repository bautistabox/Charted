package com.alexbautista.charted;

import com.alexbautista.charted.model.Genre;

public class GenreService {
    public void printGenre(Genre genre) {
        switch (genre) {
            case JAZZ:
                System.out.println("You chose JAZZ");
                break;
            case BLUES:
                System.out.println("You chose BLUES");
                break;
            case ROCK:
                System.out.println("You chose ROCK");
                break;
            case HIPHOP:
                System.out.println("You chose HIPHOP");
                break;
            default:
                System.out.println("nothing chosen");
                break;
        }
    }
}
