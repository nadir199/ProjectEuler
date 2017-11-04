import java.math.BigInteger;

/**
 * Created by Asus on 20/09/2017.
 */
public class Euler {
    // Problem1
    public static int sumMult35_Problem1(int MAX_NUMBER){
        int sum=0;
        for(int i=1;i<MAX_NUMBER;i++)
        {
            if(i%3==0 || i%5 == 0 )
                sum+=i;
        }
        System.out.println(sum);
        return sum;
    }

    public static int sumFibo_Problem2(int MAX_NUMBER){
        int sum=2;
        int prec1=1;
        int prec2=2;
        int news=0;
        while(news<MAX_NUMBER){
            news=prec1+prec2;
            prec1=prec2;
            prec2=news;
            if(news%2==0)
                sum+=news;
        }
        System.out.println(sum);
        return sum;
    }
    public static boolean isPrime(BigInteger number){
        for( BigInteger i= new BigInteger("2");i.compareTo(number.divide(new BigInteger("2")))<0;i=i.add(new BigInteger("1"))){
            if(number.mod(i).equals(BigInteger.ZERO))
                return false;
        }
        return true;
    }
    public static BigInteger smallestPrimeFactor(BigInteger number){
        for(BigInteger i=new BigInteger("2") ;i.compareTo(number.divide(new BigInteger("2")))<0;i=i.add(new BigInteger("1"))){
            if(number.mod(i)==BigInteger.ZERO && isPrime(i))
                return i;
        }
        return number;
    }
    public static BigInteger largestPrimeFactor_Problem3(BigInteger number){
        //get diviseurs
        if(isPrime(number))
            return number;
        return largestPrimeFactor_Problem3(number.divide(smallestPrimeFactor(number)));
    }
}
