import java.math.BigInteger;

/**
 * Created by Asus on 20/09/2017.
 */
public class Euler {
    // Problem1 Sum of all multiples of 3 or 5 less than MAX_NUMBER
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
    //Problem 2 sum of all fibonacci numbers less than MAX_NUMBER
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

    //Problem 3 Find the largest prime factor of a number
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

    //Problem 4 Largest palindrome product of two 2-digit numbers
    public static BigInteger invertNumber(BigInteger number){
        BigInteger intVerted=BigInteger.ZERO;
        while(!number.equals(BigInteger.ZERO)){
            intVerted= intVerted.multiply(BigInteger.TEN).add(number.mod(BigInteger.TEN));
            number= number.divide(BigInteger.TEN);
        }
        return intVerted;
    }
    public static boolean isPalindromic(BigInteger number){
        return number.equals(invertNumber(number));
    }
    public static int getSmallestNDigitsNumber(int n){
        int smallest=1;
        for(int i=1;i<n;i++){
            smallest*=10;
        }
        return smallest;
    }
    public static int getLargestNDigitsNumber(int n){
        return getSmallestNDigitsNumber(n)*10 -1;
    }
    public static BigInteger getLargestPalindromeProdOf2nDigNumbers_Problem4(int digits){
        BigInteger smallest=new BigInteger(getSmallestNDigitsNumber(digits)+"");
        BigInteger biggest = new BigInteger(getLargestNDigitsNumber(digits)+"");
        BigInteger maxPalindrome=BigInteger.ZERO;
        for(BigInteger i=new BigInteger(smallest.toString());i.compareTo(biggest)<=0;i=i.add(BigInteger.ONE)){
            for(BigInteger j=new BigInteger(i.toString());j.compareTo(biggest)<=0;j=j.add(BigInteger.ONE)){
                BigInteger mult = i.multiply(j);
                if(mult.compareTo(maxPalindrome)>0 && isPalindromic(mult)) {
                    maxPalindrome = mult;
                    System.out.println(maxPalindrome+" = "+ i + " * " + j);
                }
            }
        }
        return maxPalindrome;
    }

}
