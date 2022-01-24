import org.pcj.PCJ;
import org.pcj.RegisterStorage;
import org.pcj.StartPoint;
import org.pcj.Storage;
import org.pcj.internal.PcjThread;

@RegisterStorage(aa.Shared.class)
public class aa implements StartPoint {
    @Storage(aa.class)
    enum Shared{c,v1,v2}
    static long[] c = new long[1000];
    static int[] v1 = new int[1000];
    static int[] v2 = new int[1000];



    @Override
    public void main() throws Throwable {
        if(PCJ.myId()==0) {
            for (int x = 0; x < v1.length; x++) {
                v1[x] = x;
            }
        }else{
            for (int x = 0; x < v1.length; x++) {
                v2[x] = x;
            }
        }
        //PCJ.barrier();



        int z = PCJ.myId();

        for(int x = z*500;x<z*500+500;x++){
           c[x]=v1[x]+v2[x];
        }

    }

    public static void main(String[] args) {

        PCJ.executionBuilder(aa.class).addNode("").addNode("").start();
        System.out.println(aa.c[0]);
        System.out.println(c[1]);
        System.out.println(aa.c[999]);
    }
}
