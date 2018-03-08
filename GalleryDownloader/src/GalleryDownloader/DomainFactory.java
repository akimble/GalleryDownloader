package GalleryDownloader;

// Implementing Factory Pattern bc the Domain objects create fields differently and download
// media differently. This is decoupling the code.
public class DomainFactory {
    public static Domain getDomain(MyViewClass myView, String criteria) {
        switch(criteria) {
            case "Imgur":
                return new Imgur(myView);
        }

        return null;
    }
}
