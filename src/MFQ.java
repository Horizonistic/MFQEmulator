import util.ObjectQueue;
import util.SuperOutput;

public class MFQ
{
    SuperOutput so;

    private ObjectQueue level1;
    private ObjectQueue level2;
    private ObjectQueue level3;
    private ObjectQueue levelN;

    public MFQ(SuperOutput so)
    {
        this.so = so;
    }
}