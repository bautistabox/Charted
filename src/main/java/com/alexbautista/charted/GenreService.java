package com.alexbautista.charted;

import com.alexbautista.charted.model.Genre;

public class GenreService {
    public void printGenre(Genre genre){
        if(genre.equals(Genre.JAZZ)){
            System.out.println("You chose JAZZ");
        }else if(genre.equals(Genre.BLUES)){
            System.out.println("You chose BLUES");
        }else if(genre.equals(Genre.ROCK)){
            System.out.println("You chose ROCK");
        }else if(genre.equals(Genre.HIPHOP)){
            System.out.println("You chose HIPHOP");
        }else{
            System.out.println("nothing chosen");
        }
    }
}
