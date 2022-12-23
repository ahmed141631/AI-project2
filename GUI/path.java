package GUI;

import java.net.URL;

public class path {

    public static URL load(String path) {
        // InputStream input = ResourceLoader.class.getResourceAsStream(path);
        URL input = path.class.getResource(path);
        if (input == null) {
            input = path.class.getResource("/" + path);
        }
        // String in = input.getPath();
        // System.out.println("in: " + in);
        return input;
    }
}

