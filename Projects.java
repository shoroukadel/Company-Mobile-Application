package commyc.example.blu_ray91111.timslinesoluation;

/**
 * Created by Blu-Ray on 01/03/2017.
 */

public class Projects {
    private String yourText = "";
    private String yourstatu= "";
    private String yourdes= "";
    private String yourversion= "";
    private String []img;
    private  String id="";
    private  int imageno;

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
    public String getyourstatu () {
        return yourstatu ;
    }

    public void setyourstatu( String yourstatu) {
        this.yourstatu  = yourstatu;
    }
    public String getYourversion() {
        return yourversion ;
    }

    public void setYourversion( String yourversion) {
        this.yourversion  = yourversion;
    }
    public String[] getYourimages() {
        return  img ;
    }

    public void setYourimages( String [] img) {
        this.img = img;
    }
    public int getImageno() {
        return  imageno ;
    }

    public void setImageno( int imageno) {
        this.imageno = imageno;
    }
    public String getid() {
        return id ;
    }

    public void setid( String id) {
        this.id  = id;
    }
}
