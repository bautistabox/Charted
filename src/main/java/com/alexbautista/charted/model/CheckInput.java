package com.alexbautista.charted.model;

public class CheckInput {
    public static boolean checkUsername(String username) {
        return (username != null) && username.matches("[A-Za-z0-9_]+");        
    }
}
