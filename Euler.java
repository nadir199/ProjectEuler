import java.math.BigInteger;
import java.util.HashSet;

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

    //Problem 5 Smallest multiple //number >=2
    public static BigInteger evenlyDivisibleByAllLessThanOrEquelNumber_Problem5(int number){
        BigInteger smallestMultiple;
        if(number%2==0)
            smallestMultiple=new BigInteger(number+"");
        else
            smallestMultiple=new BigInteger((number+1)+"");
        boolean dividedByAll;
        while (true){
            dividedByAll=true;
            for(int i=1;i<=number;i++){
                if(!smallestMultiple.mod(new BigInteger(i+"")).equals(BigInteger.ZERO)) {
                    dividedByAll = false;
                    break;
                }
            }
            if(!dividedByAll)
                smallestMultiple=smallestMultiple.add(new BigInteger("2"));
            else
                break;
        }
        return smallestMultiple;
    }

    //Problem 6 Sum square difference
    public static BigInteger sumSq(int n){
        BigInteger sum=BigInteger.ZERO;
        for(BigInteger i=BigInteger.ONE;i.compareTo(new BigInteger(n+""))<=0;i=i.add(BigInteger.ONE)){
            sum=sum.add(i.pow(2));
        }
        return sum;
    }
    public static BigInteger sqSum(int n){
        BigInteger sum=BigInteger.ZERO;
        for(BigInteger i=BigInteger.ONE;i.compareTo(new BigInteger(n+""))<=0;i=i.add(BigInteger.ONE)){
            sum=sum.add(i);
        }
        return sum.pow(2);
    }
    public static BigInteger SumSqANDSqSumDiff_Problem6(int n){
        return sqSum(n).subtract(sumSq(n));
    }

    //Problem 7 : get 10001st prime
    public static BigInteger nThPrime_Problem7(int n){
        int i=1;
        BigInteger number=new BigInteger("3");
        HashSet<BigInteger> primes = new HashSet<BigInteger>();
        primes.add(new BigInteger("2"));

        while(i<n){
            boolean prime=true;
            for (BigInteger pr:primes)
                if(number.mod(pr).equals(BigInteger.ZERO))
                {
                    prime=false;
                    break;
                }
            if(prime) {
                primes.add(number);
                i++;
            }
            else
                number=number.add(new BigInteger("2"));
        }
        return number;
    }
    //Additional
    public static HashSet<BigInteger> getAllFirstNPrimes(int n){
        int i=1;
        BigInteger number=new BigInteger("3");
        HashSet<BigInteger> primes = new HashSet<BigInteger>();
        primes.add(new BigInteger("2"));

        while(i<n){
            boolean prime=true;
            for (BigInteger pr:primes)
                if(number.mod(pr).equals(BigInteger.ZERO))
                {
                    prime=false;
                    break;
                }
            if(prime) {
                primes.add(number);
                i++;
            }
            else
                number=number.add(new BigInteger("2"));
        }
        return primes;
    }

    //Problem8 Largest Product in a series
    public static BigInteger getSeriesProduct(String serie){
        String stringNumbs[]= serie.split("");
        BigInteger product = BigInteger.ONE;
        int i=0;
        for(String numb :stringNumbs){
            product=product.multiply(new BigInteger(numb));
        }
        return product;
    }

    public static BigInteger getLargestProductValue_Problem8(String serie,int length){
        int start=0;
        int end =length;
        BigInteger maxValue= BigInteger.ZERO;
        BigInteger product;
        while(end < serie.length()){
            String segment= serie.substring(start,end);
            product= getSeriesProduct(segment);
            if(product.compareTo(maxValue)>0)
                maxValue = product;
            start++;
            end++;

        }
        return maxValue;
    }

    //Problem9 : Special Pythagorean triplet
    public static void EASYSOLUTIONCRAP_Problem9(){
        for(int a=1;a<1000;a++){
            for(int b=a;b<1000;b++){
                for(int c=1;c<1000;c++){
                    if(Math.pow(a,2)+Math.pow(b,2)==Math.pow(c,2) && a+b+c==1000)
                    {
                        System.out.println(a+"   "+b+"   "+c);
                    }
                }
            }
        }
    }
    public static BigInteger pythagoreanTripletAddUpTo_Problem9(int number){
        BigInteger product=BigInteger.ZERO;
        for(int a=1;a<number;a++){
            for(int b=a;b<number;b++){
                int c=number -a-b;
                if(Math.pow(a,2)+Math.pow(b,2)==Math.pow(c,2))
                {
                    System.out.println("(a,b,c)=("+a+" , "+b+" , "+c+")");
                    product=new BigInteger(a+"")
                            .multiply(new BigInteger(b+""))
                            .multiply(new BigInteger(c+""));
                    break;
                }

            }
        }
        return product;
    }
}
