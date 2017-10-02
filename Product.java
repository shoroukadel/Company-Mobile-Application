package commyc.example.blu_ray91111.timslinesoluation;

/**
 * Created by Blu-Ray on 28/02/2017.
 */

public class Product{

    private String yourText = "";
    private String imgUrl= "";
    private String yourdes= "";


    public String getyourText () {
        return yourText ;
    }

    public void setyourText(String yourText) {
        this.yourText  = yourText;
    }
    public String getyourdescription () {
        return yourdes ;
    }

    public void setyourdescription( String yourdes) {
        this.yourdes  = yourdes;
    }



    public String getimgUrl() {
        return imgUrl;
    }

    public void setimgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}