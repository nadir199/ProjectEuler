import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Created by Asus on 20/09/2017.
 */
public class Main {
    public static void main(String[] args){
        //System.out.println(new BigInteger("-4").nextProbablePrime());
        System.out.println(Euler.getLongestSeriesLength(new BigInteger("-79"),new BigInteger("1601")));
        System.out.println(Euler.getLongestSeriesForAsAndBs_Problem27(1000,1000));
    }
}

