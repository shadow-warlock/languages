import java.util.ArrayList;
import java.util.List;

public class Application {

    String user = null;
    UserType userType = null;
    String lang = null;
    String action = null;
    ProgramFrame frame;
    String category;
    List<String[]> words = new ArrayList<String[]>();
    int wordNum = 0;
    int errors = 0;
    int trues = 0;

    private static Application self;

    private Application(){}

    public static Application getInstance(){
        if(self == null){
            self = new Application();
        }
        return self;
    }
}
