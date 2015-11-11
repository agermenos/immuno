package com.udacity.immuno;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by demouser on 11/10/15.
 */
public class Utility {
    public static final ArrayList<String> MICROBE_IMAGES = new ArrayList<String>(Arrays.asList(
            "http://www.the-scientist.com/images/News/September2013/Gutbacteria310.jpg",
            "http://blogs.independent.co.uk/wp-content/uploads/2012/06/090105101501-large.jpg",
            "http://i.kinja-img.com/gawker-media/image/upload/s--b0yZa7lA--/17pr7zlwzt4awjpg.jpg",
            "https://wattsupwiththat.files.wordpress.com/2011/02/bacteria1.jpg",
            "http://www.iflscience.com/sites/www.iflscience.com/files/styles/ifls_large/public/blog/%5Bnid%5D/microbes.jpg?itok=3dWnQYJ8"));

    public static String randMicrobe() {
        Random r = new Random();
        return MICROBE_IMAGES.get(r.nextInt(MICROBE_IMAGES.size()));
    }

    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
