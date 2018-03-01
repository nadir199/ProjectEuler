import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.math.BigInteger;

/**
 * Created by Asus on 20/09/2017.
 */
public class Main {
    public static void main(String[] args){
        //System.out.println((new String("123456789")).substring(3)+"\n");
        //System.out.println(Euler.getCollatzChainLength(113382));
        Euler.getLongestCollatzChainUnder_Problem14(new BigInteger("1000000"));
    }


}

