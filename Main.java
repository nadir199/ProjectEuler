import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Created by Asus on 20/09/2017.
 */
public class Main {
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        System.out.println(Euler.getNumberOfPossible2Pounds_Problem31());
        System.out.println("Execution time:"+(System.currentTimeMillis()-start) +"ms");
    }
}

