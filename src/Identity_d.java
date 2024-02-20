import java.util.ArrayList;

public class Identity_d extends Dice
{

    private int position;

    public Identity_d()
    {
        super("Identity Dice", 8);
        ArrayList<Face> faces = super.getFaces();
        for (int i = 0; i < 8; i++)
            faces.set(i, Face.IDENTITY);
        position = 0;
    }


    @Override
    public int get_value() {
        return 0;
    }

    public void setPosition(int pos)
    {
        position = pos;
    }
    public int getPosition()
    {
        return position;
    }
}
