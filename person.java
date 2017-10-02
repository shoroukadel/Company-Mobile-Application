package commyc.example.blu_ray91111.timslinesoluation;

/**
 * Created by Blu-Ray on 20/02/2017.
 */

public class person {

    String description;
    String title;
    String image;

    public person() {
    }

    public String gettitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setSurname(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String image) {
        this.image = image;
    }
}