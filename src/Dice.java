import java.util.Random;
import java.util.ArrayList;

public abstract class Dice
{
    private String          name;
    private ArrayList<Face> faces;
    private int             numFaces;
    private int             id;
    private int             currentFace;

    public Dice(String name, int numFaces)
    {
        this.name = name;
        this.numFaces = numFaces;
        this.faces = new ArrayList<Face>();
        for (int i = 0; i < numFaces; i++)
            this.faces.add(Face.BURNING_DICE);
        this.currentFace = 0;
        this.id = 0;
    }

    public static void roll_area(ArrayList<Dice> dice)
    {
        for (Dice d : dice)
        {
            d.roll();
        }
    }
    public abstract int get_value();

    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }
    public Face getCurrentFace()
    {
        return this.faces.get(this.currentFace);
    }
    public int getCurrentFaceNum()
    {
        return this.currentFace;
    }

    public void setCurrentFace(int face)
    {
        this.currentFace = face;
    }
    public ArrayList<Face> getFaces()
    {
        return this.faces;
    }

    public Face roll()
    {
        Random rand = new Random();
        this.currentFace = rand.nextInt(this.numFaces);
        return this.faces.get(this.currentFace);
    }
}
