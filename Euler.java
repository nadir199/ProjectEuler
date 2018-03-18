import javax.xml.stream.events.Characters;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
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
        if(number.compareTo(BigInteger.ZERO)<0)
            number=number.negate();
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

    //Problem 10 : Get sum of all primes less than MAX_NUMBER
    public static HashSet<Integer> getAllPrimesBelowN(int MAX_NUMBER){
        int numb=3;
        HashSet<Integer> primes = new HashSet<Integer>();
        primes.add(2);
        while(numb<=MAX_NUMBER){
            boolean prime=true;
            for (int pr:primes)
                if(numb%pr==0)
                {
                    prime=false;
                    break;
                }
            if(prime) {
                primes.add(numb);
            }
            else
                numb+=2;
        }
        return primes;
    }
    public static BigInteger getSumAllPrimesBelowN_Problem10(int number){
        HashSet<Integer> primesBelowN= getAllPrimesBelowN(number);
        BigInteger sum = BigInteger.ZERO;
        for(int prime:primesBelowN){
            sum=sum.add(new BigInteger(prime+""));
        }
        return sum;
    }

    //Problem 11: Largest product in a grid
    public static int[][] strGridToIntArray(String grid){
        String[] lines= grid.split("\n");

        int[][] gridArray=new int[lines.length][lines[0].split(" ").length];
        int i=0;
        int j=0;
        for(String line:lines){
            j=0;
            String[] lineArray= line.split(" ");
            for(String number:lineArray){
                gridArray[i][j]=Integer.parseInt(number);
                j++;
            }
            i++;
        }
        return gridArray;

    }

    public static BigInteger getLargestProductInGrid_Problem11(int[][] grid,int numbAdjNumbers){
        //left right etc
        BigInteger maxProduct=BigInteger.ZERO;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                //right;
                BigInteger product=BigInteger.ONE;

                //Right case
                if(j+numbAdjNumbers-1<grid[i].length){
                    for(int k =0;k<numbAdjNumbers;k++){
                        product=product.multiply(new BigInteger(grid[i][j+k]+""));
                    }
                    if(product.compareTo(maxProduct)>0)
                        maxProduct=product;
                }
                //Down case
                product=BigInteger.ONE;
                if(i+numbAdjNumbers-1<grid.length){
                    for(int k =0;k<numbAdjNumbers;k++){
                        product=product.multiply(new BigInteger(grid[i+k][j]+""));
                    }
                    if(product.compareTo(maxProduct)>0)
                        maxProduct=product;
                }
                //Diagonal Right
                product=BigInteger.ONE;
                if(j+numbAdjNumbers-1<grid.length && i+numbAdjNumbers-1<grid[i].length){
                    for(int k=0;k<numbAdjNumbers;k++){
                        product=product.multiply(new BigInteger(grid[i+k][j+k]+""));
                    }
                    if(product.compareTo(maxProduct)>0)
                        maxProduct=product;
                }
                //Diagonal Left
                product=BigInteger.ONE;
                if(j-numbAdjNumbers+1>=0 && i+numbAdjNumbers-1<grid[i].length){
                    for(int k=0;k<numbAdjNumbers;k++){
                        product=product.multiply(new BigInteger(grid[i+k][j-k]+""));
                    }
                    if(product.compareTo(maxProduct)>0)
                        maxProduct=product;
                }
            }
        }
        return maxProduct;
    }
    //Problem 12: Highly divisible triangular number
    //Simplest stuff
    /*public static int getNumberOfDivisors(BigInteger number){
        BigInteger pas=BigInteger.ONE.add(number.mod(new BigInteger("2")));
        int numberDivisors=0;
        for(BigInteger i=BigInteger.ONE;i.compareTo(number.divide(new BigInteger("2")))<=0;i=i.add(pas)){
            if(number.mod(i).compareTo(BigInteger.ZERO)==0)
                numberDivisors++;
        }
        return numberDivisors+1;
    }*/
    private static BigInteger newtonIteration(BigInteger n, BigInteger x0)
    {
        final BigInteger x1 = n.divide(x0).add(x0).shiftRight(1);
        return x0.equals(x1)||x0.equals(x1.subtract(BigInteger.ONE)) ? x0 : newtonIteration(n, x1);
    }

    public static BigInteger sqrt(final BigInteger number)
    {
        if(number.signum() == -1)
            throw new ArithmeticException("We can only calculate the square root of positive numbers.");
        return newtonIteration(number, BigInteger.ONE);
    }
    public static int getNumbDivFaster(BigInteger number){
        ArrayList<Integer> primePowers = new ArrayList<Integer>();
        int numberDivisors=1;
        BigInteger LAST_TEST=sqrt(number);
        for(BigInteger i=new BigInteger("2");i.compareTo(LAST_TEST)<=0;i=i.nextProbablePrime()){
            int power=0;
            while(number.mod(i).compareTo(BigInteger.ZERO)==0)
            {
                power++;
                number=number.divide(i);
            }
            if(power>0)
                numberDivisors*=power+1;

        }
        if(numberDivisors>1)
            return numberDivisors;
        return 2;
    }
    //called triangle number
    /*public static BigInteger sumOfNumbers(BigInteger number){
        BigInteger sum=BigInteger.ZERO;
        for(BigInteger i=BigInteger.ONE;i.compareTo(number)<=0;i=i.add(BigInteger.ONE)){
            sum=sum.add(i);
        }
        return sum;
    }
    //Crap algorithm over 40 minutes for nbDivisors = 500
    public static BigInteger getFirstTriangNumberNbDivOver_Crap(int nbDivisors){
        BigInteger i=BigInteger.ONE;
        BigInteger trigNumber=BigInteger.ZERO;
        while(true){
            trigNumber =sumOfNumbers(i);
            int nbD=getNumberOfDivisors(trigNumber);
            System.out.println(i+ " : "+ nbD);
            if(nbD>nbDivisors)
                break;
            i=i.add(BigInteger.ONE);
        }
        return trigNumber;
    }*/

    public static BigInteger getFirstTriangNumberNbDivOver_Problem12(int nbDivisors){
        BigInteger i=BigInteger.ONE;
        BigInteger trigNumber=BigInteger.ZERO;
        while(true){
            trigNumber =trigNumber.add(i);
            int nbDiv=getNumbDivFaster(trigNumber);
            //System.out.println(trigNumber+"  /   Nbdiv: "+nbDiv);
            if(nbDiv>=nbDivisors)
                break;
            i=i.add(BigInteger.ONE);
        }
        return trigNumber;
    }

    /// PROBLEM 13
    /*public static BigInteger truncateNumber(String number,int numbDigits){
        return new BigInteger(number.substring(number.length()-numbDigits));
    }
    public static BigInteger[] stringsToArray(String numbers,int numbDigitsTruncation){
        String stringsArray[]=numbers.split("\n");
        BigInteger[] numbersTruncated=new BigInteger[stringsArray.length];
        for(int i=0;i<stringsArray.length;i++){
            numbersTruncated[i]=truncateNumber(stringsArray[i],numbDigitsTruncation);
        }
        return numbersTruncated;
    }
    public static BigInteger sumNumbers(BigInteger[] numbers){
        BigInteger sum= BigInteger.ZERO;
        for(int i=0;i<numbers.length;i++) {
            System.out.println(i+"  :: " +numbers[i]+"\n");
            sum=sum.add(numbers[i]);
        }
        return sum;
    }*/
    public static BigInteger getSumTruncatedOfList_Problem13(String numbers,int truncation){
        String stringsArray[]=numbers.split("\n");
        BigInteger[] numbersTruncated=new BigInteger[stringsArray.length];
        BigInteger sum=BigInteger.ZERO;
        for(int i=0;i<stringsArray.length;i++){
            numbersTruncated[i]=new BigInteger(stringsArray[i]);
            sum=sum.add(numbersTruncated[i]);

        }
        return sum;
        /*
        String resultSum=sumNumbers(stringsToArray(numbers,truncation)).toString();
        return truncateNumber(resultSum,truncation);*/
    }

    //Problem 13 Longest chain Collatz Problem
    public static BigInteger collatz(BigInteger n){
        if(n.mod(new BigInteger("2")).equals(BigInteger.ONE))
            return n.multiply(new BigInteger("3")).add(BigInteger.ONE);
        return n.divide(new BigInteger("2"));
    }
    public static int getCollatzChainLength(BigInteger n){
        int count=1;
        while(!n.equals(BigInteger.ONE)){
            n=collatz(n);
            count++;
        }
        return count;
    }

        public static BigInteger getLongestCollatzChainUnder_Problem14(BigInteger THRESHOLD){
        int max=0;
        BigInteger I_MAX=BigInteger.ONE;
        for(BigInteger i=BigInteger.ONE;i.compareTo(THRESHOLD)<=0;i=i.add(BigInteger.ONE)){
            int size=getCollatzChainLength(i);
            System.out.println(i+":\n");
            if(size>max) {
                max = size;
                I_MAX=i;
                System.out.printf("Number : "+I_MAX + " / Length : "+max+"\n");
            }
        }
        System.out.printf("Number : "+I_MAX + " / Length : "+max);
        return I_MAX;
    }

    //Problem 15
    //Go down and right only all possible values
    ///// DIDN'T Know how to do it apart from combinations 000000011111111 same # of 0s and 1s and combination of 1 placements.
    public static long printSetOfMoves(int x,int y,long[][] map){
        if(x==0 || y==0)
            return 1;
        if(map[x][y]!=0)
            return map[x][y];

        map[x][y]=printSetOfMoves(x-1,y,map)+printSetOfMoves(x,y-1,map);
        return map[x][y];
    }
    public static long printSetOfMoves_Problem15(int x,int y){
        long map[][]=new long[x+1][y+1];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                map[i][j]=0;
            }
        }
        return printSetOfMoves(x,y,map);

    }

    //PROBLEM 16
    public static int sumDigitsPow2_Problem16(int pow){
        String[] numbers=(new BigInteger("2")).pow(pow).toString().split("");
        int sum=0;
        for(int i=0;i<numbers.length;i++){
            sum+=Integer.parseInt(numbers[i]);
        }
        return sum;
    }

    //PROBLEM 17

    public static String numberToString(int number){
        String stringNumber="";
        int saveNumber=number;
        String part="";
        if(number==0)
            return "Zero";
        if((number/10)%10!=1)
            switch(number%10){
                case 1:
                    part="One";
                    break;
                case 2:
                    part="Two";
                    break;
                case 3:
                    part="Three";
                    break;
                case 4:
                    part="Four";
                    break;
                case 5:
                    part="Five";
                    break;
                case 6:
                    part="Six";
                    break;
                case 7:
                    part="Seven";
                    break;
                case 8:
                    part="Eight";
                    break;
                case 9:
                    part="Nine";
                    break;

            }
        else
            switch(number%10){
                case 0:
                    part="ten";
                    break;
                case 1:
                    part="eleven";
                    break;
                case 2:
                    part="twelve";
                    break;
                case 3:
                    part="thirteen";
                    break;
                case 4:
                    part="fourteen";
                    break;
                case 5:
                    part="fifteen";
                    break;
                case 6:
                    part="sixteen";
                    break;
                case 7:
                    part="seventeen";
                    break;
                case 8:
                    part="eighteen";
                    break;
                case 9:
                    part="nineteen";
                    break;

            }
        stringNumber+=part;
        part="";
        number=number/10;
        switch(number%10){
            case 2:
                part="twenty";
                break;
            case 3:
                part="thirty";
                break;
            case 4:
                part="forty";
                break;
            case 5:
                part="fifty";
                break;
            case 6:
                part="sixty";
                break;
            case 7:
                part="seventy";
                break;
            case 8:
                part="eighty";
                break;
            case 9:
                part="ninety";
                break;
        }
        stringNumber=part+" "+stringNumber;

        number=number/10;
        part="";
        //Hundreds
        if (saveNumber%100!=0 && saveNumber/100!=0)
            stringNumber="and "+stringNumber;

        switch(number%10){
            case 1:
                part="One hundred";
                break;
            case 2:
                part="Two hundred";
                break;
            case 3:
                part="Three hundred";
                break;
            case 4:
                part="Four hundred";
                break;
            case 5:
                part="Five hundred";
                break;
            case 6:
                part="Six hundred";
                break;
            case 7:
                part="Seven hundred";
                break;
            case 8:
                part="Eight hundred";
                break;
            case 9:
                part="Nine hundred";
                break;
        }
        stringNumber=part +" "+ stringNumber;
        number=number/10;
        part ="";
        switch(number%10){
            case 1:
                part="One thousand";
                break;
            case 2:
                part="Two thousand";
                break;
            case 3:
                part="Three thousand";
                break;
            case 4:
                part="Four thousand";
                break;
            case 5:
                part="Five thousand";
                break;
            case 6:
                part="Six thousand";
                break;
            case 7:
                part="Seven thousand";
                break;
            case 8:
                part="Eight thousand";
                break;
            case 9:
                part="Nine thousand";
                break;
        }
        stringNumber=part +" "+stringNumber;
        return stringNumber;
    }
    public static int countNbLettersInNumber(int number){
        return numberToString(number).replaceAll(" ","").length();
    }
    public static int totalNbLettersFromTo_Problem17(int from,int to) {
        int sum=0;
        for(int i=from;i<=to;i++){
            sum+=countNbLettersInNumber(i);
        }
        return sum;
    }

    //Problem18 Tree
    public static int[][] triangleToArray(String trig){
        String[] lines= trig.split("\n");
        int[][] numberTree = new int[lines.length][];
        for(int i=0;i<numberTree.length;i++){
            String[] line=lines[i].split(" ");
            numberTree[i]=new int[line.length];
            for(int j=0;j<line.length;j++){
                numberTree[i][j]=Integer.parseInt(line[j]);
            }
        }
        return numberTree;
    }
    public static int[][] reduceTriangle(int[][] triangle,boolean[][] path){
        int[][] newTriangle=new int[triangle.length-1][];
        for(int i=0;i<newTriangle.length;i++){
            newTriangle[i]=new int[triangle[i].length];
            for(int j=0;j<newTriangle[i].length;j++){
                newTriangle[i][j]=triangle[i][j];
            }
        }
        int LAST_LINE_INDEX=newTriangle.length-1;
        for(int i=0;i<newTriangle[LAST_LINE_INDEX].length;i++){
            if(triangle[LAST_LINE_INDEX+1][i]>triangle[LAST_LINE_INDEX+1][i+1])
            {
                path[LAST_LINE_INDEX+1][i]=true;
            }
            else if (triangle[LAST_LINE_INDEX+1][i]<triangle[LAST_LINE_INDEX+1][i+1]){
                path[LAST_LINE_INDEX+1][i+1]=true;
            }
            else
            {
                path[LAST_LINE_INDEX+1][i]=true;
                //path[LAST_LINE_INDEX+1][i+1]=true;
            }
            newTriangle[LAST_LINE_INDEX][i]+=Math.max(triangle[LAST_LINE_INDEX+1][i],triangle[LAST_LINE_INDEX+1][i+1]);
        }

        return newTriangle;
    }
    public static boolean[][] initializeArray(int[][] arrayModel){
        boolean[][] boolArray=new boolean[arrayModel.length][];
        for(int i=0;i<arrayModel.length;i++)
        {
            boolArray[i]=new boolean[arrayModel[i].length];
            for(int j=0;j<arrayModel[i].length;j++){
                boolArray[i][j]=false;
            }
        }
        return boolArray;
    }
    public static void showPathTree(boolean[][] path){
        for(int i=0;i<path.length;i++){
            for(int j=0;j<path[i].length;j++){
                System.out.print(path[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int getLongestPathLength_Problem18(String triangle){
        int[][] toNumb=triangleToArray(triangle);
        boolean[][] path=initializeArray(toNumb);

        do{
            toNumb=Euler.reduceTriangle(toNumb,path);
        }while((toNumb.length!=1));
        path[0][0]=true;
        showPathTree(path);
        return toNumb[0][0];
    }

    //Problem 19 Counting sundays

    //dateDebut et dateFin Inclus
    // jour
    // Saturday  0 || Sunday 1
    // Monday    2 || Tuesday 3
    // Wednesday 4 || Thursday 5
    // Friday 6
    //Date Début >= 1/1/1900
    public static int compareDates(String d1,String d2){
        String[] dateStr1=d1.split("/");
        String[] dateStr2=d2.split("/");
        int day1=Integer.parseInt(dateStr1[0]);
        int month1=Integer.parseInt(dateStr1[1]);
        int year1=Integer.parseInt(dateStr1[2]);
        int day2=Integer.parseInt(dateStr2[0]);
        int month2=Integer.parseInt(dateStr2[1]);
        int year2=Integer.parseInt(dateStr2[2]);
        if(year1>year2)
            return 1;
        else if (year2>year1)
            return -1;
        else
        {
            if(month1>month2)
                return 1;
            else if (month2>month1)
                return -1;
            else
            {
                if(day1>day2)
                    return 1;
                else if (day2>day1)
                    return -1;
                else
                    return 0;
            }

        }
    }
    //Count number of weekday between two dates
    public static int getNumberOfDaysBetween_Problem19_WhatIThought_DIdnt_Read_Well(String dateDebut,String dateFin,int jour){
        int nbDays=1;
        int weekDay=2;
        int day= 1;
        int month=1;
        int year=1900;
        String date=day+"/"+month+"/"+year;
        while(compareDates(date,dateDebut)<0){
            date=getNextWeekDay(date,weekDay,jour);
            weekDay=jour;
        }

        while(compareDates(date,dateFin)<0){
            date=getNextWeekDay(date,jour,jour);
            nbDays++;
        }

        return nbDays;
    }
    public static String getNextWeekDay(String currentDate,int curWeekDay, int weekDay){
        String date="";
        String dateString[]=currentDate.split("/");
        int day=Integer.parseInt(dateString[0]);
        int month=Integer.parseInt(dateString[1]);
        int year=Integer.parseInt(dateString[2]);
        int daysRemaining=0;
        if(weekDay>curWeekDay)
            daysRemaining=weekDay-curWeekDay;
        else
            daysRemaining=7-(curWeekDay-weekDay);

        if(month==2){
            int nbDaysFeb;
            if((year%4==0 && year%100!=0) || year%400==0){ // February 29
                nbDaysFeb=29;
            }
            else
            {
                nbDaysFeb=28;
            }
            if(day+daysRemaining<(nbDaysFeb+1)){
                day+=daysRemaining;
            }
            else
            {
                day=(day+daysRemaining)%(nbDaysFeb+1)+1;
                month++;
            }

        }
        else
        {
            if( (month<8 && month%2==0) || (month>=8 && month%2 ==1)){ //Mois de 30
                if(day+daysRemaining<31){
                    day+=daysRemaining;
                }
                else
                {
                    day=(day+daysRemaining)%31+1;
                    month++;
                }
            }
            else // mois de 31
            {
                if(day+daysRemaining<32){
                    day+=daysRemaining;
                }
                else if (month!=12)
                {
                    day=(day+daysRemaining)%32+1;
                    month++;
                }
                else
                {
                    year++;
                    month=1;
                    day=(day+daysRemaining)%32+1;
                }
            }
        }
        date=day+"/"+month+"/"+year;
        return date;
    }

    static int getNumberDaysMonth(int day,int month,int year){
        int nbDaysMonth;
        if(month==2){

            if((year%4==0 && year%100!=0) || year%400==0){ // February 29
                nbDaysMonth=29;
            }
            else
            {
                nbDaysMonth=28;
            }

        }
        else
        {
            if( (month<8 && month%2==0) || (month>=8 && month%2 ==1)){ //Mois de 30
                nbDaysMonth=30;
            }
            else // mois de 31
            {
                nbDaysMonth=31;
            }
        }
        return nbDaysMonth;
    }
    //Date Début >= 1/1/1900
    static int getNumberOfFirstDaysOnDay_Problem19(String date,String dateFin,int jour){


        int weekdayStart=2; //Monday 1/1/1900
        String dateReference="1/1/1900";
        String dateStringReference[]=dateReference.split("/");
        int day=Integer.parseInt(dateStringReference[0]);
        int month=Integer.parseInt(dateStringReference[1]);
        int year=Integer.parseInt(dateStringReference[2]);
        int weekday=weekdayStart;




        int nbDaysMonth;
        while(compareDates(day+"/"+month+"/"+year,date)<0){
            nbDaysMonth=getNumberDaysMonth(day,month,year);
            weekday=(weekday+nbDaysMonth)%7;
            month++;
            if(month==13)
            {
                month=1;
                year++;
            }
        }

        int nbDaysWeekDay=(jour == weekday)?1:0;
        String dateString[]=date.split("/");
        day=Integer.parseInt(dateString[0]);
        month=Integer.parseInt(dateString[1]);
        year=Integer.parseInt(dateString[2]);
        do{
            nbDaysMonth=getNumberDaysMonth(day,month,year);

            weekday=(weekday+nbDaysMonth)%7;
            if(weekday==jour) {
                nbDaysWeekDay++;
            }

            month++;
            if(month==13)
            {
                month=1;
                year++;
            }


        }while(compareDates(day+"/"+month+"/"+year,dateFin)<0);

        return nbDaysWeekDay;
    }

    //Problem 20 Factorial
    static BigInteger bigFactorial(int number){
        if(number==0)
            return BigInteger.ONE;
        BigInteger numberBigint=new BigInteger(""+number);
        BigInteger factorial= BigInteger.ONE;
        for(BigInteger i=new BigInteger("2");i.compareTo(numberBigint)<=0;i=i.add(BigInteger.ONE)){
            factorial=factorial.multiply(i);
        }
        return factorial;
    }
    static int sumOfDigitsOfFactorial_Problem20(int number){
        String[] fact=bigFactorial(number).toString().split("");
        int sumDigits=0;
        for(int i=0;i<fact.length;i++){
            sumDigits+=Integer.parseInt(fact[i]);
        }
        return sumDigits;
    }

    //Problem21 Sum of amicable numbers under 10000
    static int getSumDivisors(int number){
        int sum=0;
        for(int i=1;i<number/2+1;i++){
            if(number%i==0)
                sum+=i;
        }
        return sum;
    }
    static int isAmicable(int number){
        int sumDivisors=getSumDivisors(number);
        if(number==getSumDivisors(sumDivisors) && number!=sumDivisors)
            return sumDivisors;
        return 0;
    }

    static int getSumOfAmicableUnder_Problem21(int THRESHOLD){
        int i=2;
        int sumAmicables=0;
        int ami=0;
        HashSet<Integer> amicable = new HashSet<>();

        while(i<THRESHOLD){
            if(!amicable.contains(i)){
                ami= isAmicable(i);
                if(ami>0)
                {
                    System.out.println("Amis : "+i + " and "+ami+"\n");
                    if(i!=ami) {
                        amicable.add(i);
                        sumAmicables+=i;
                    }
                    amicable.add(ami);
                    sumAmicables+=ami;
                }
            }
            i++;
        }
        return sumAmicables;
    }

    //Problem22 List of names
    static long getAlphabeticalValue(String word){
        long sum=0;
        for(int i=0;i<word.length();i++){
            sum+= (int)word.charAt(i)-(int)'A'+1;
        }
        return sum;
    }
    static String[] fileToStringArraySorted(String filePath){
        String file="";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line = null;
            while ((line = reader.readLine()) != null) {
                file=file+line;
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        String[] names=file.split("\",\"");
        names[0]=names[0].replace("\"","");
        names[names.length-1]=names[names.length-1].replace("\"","");
        Arrays.sort(names);
        return names;
    }
    static BigInteger sumOfNameScores_Problem22(String filePath){
        String[] sortedNames=fileToStringArraySorted(filePath);
        BigInteger totalScore=BigInteger.ZERO;
        for(int i=0;i<sortedNames.length;i++){
            System.out.println((i+1)+" : " +sortedNames[i] + " alph : "+ getAlphabeticalValue(sortedNames[i]));
            totalScore=totalScore.add(new BigInteger((long)(i+1)*getAlphabeticalValue(sortedNames[i])+""));
        }
        return totalScore;
    }

    //Problem 23 Abundant numbers

    static boolean isAbundant(int number)
    {
        int sumDivisors=0;
        for(int i=1;i<number/2+1;i++){

            if(number%i==0)
                sumDivisors+=i;
            if(sumDivisors>number)
                return true;
        }
        return false;
    }

    static int sumAllUnwritableAsSumOfTwoAbundantTill_Problem23(int THRESHOLD){
        int sumUnwritable=0;
        ArrayList<Integer> abundant = new ArrayList<>();
        int abundantNow=1;
        for(int i=1;i<=THRESHOLD;i++){
            while(abundantNow<i){
                if(isAbundant(abundantNow))
                    abundant.add(abundantNow);
                abundantNow++;
            }

            int inf=0;
            int sup= abundant.size()-1;
            boolean found = false;
            while(inf<=sup && !found){
                int sumOfTwoAbs=abundant.get(inf)+abundant.get(sup);
                if(sumOfTwoAbs>i)
                    sup--;
                else if (sumOfTwoAbs<i)
                    inf++;
                else
                    found=true;
            }
            if(!found)
                sumUnwritable+=i;

        }
        return sumUnwritable;
    }

    //Problem 24 Lexicographic permutations
    static int nowTh=0;
    static void printPerms_Problem24(String fixedPart,String toPermute,int NTH){
        if(toPermute.length()==1) {
            nowTh++;
            if(NTH==nowTh)
                System.out.println(toPermute);
            return;
        }
        String saveFixedPart=fixedPart;
        for(int i=0;i<toPermute.length();i++){
            fixedPart=saveFixedPart+toPermute.charAt(i);
            if(toPermute.length()==2 && nowTh+1==NTH)
                System.out.print(fixedPart);
            printPerms_Problem24(fixedPart,toPermute.replace(toPermute.charAt(i)+"",""),NTH);
            //System.out.print(toPermute.charAt(i));
        }
    }

    //Problem 25 Fibonacci
    static long getIndexFirstTermContainsNDigits_Problem25(int nbDigits){
        BigInteger fn_1=BigInteger.ONE;
        BigInteger fn=BigInteger.ONE;
        BigInteger newTerm=BigInteger.ZERO;
        long newTermIndex=2;
        if (nbDigits==1)
            return 1;
        BigInteger power=BigInteger.TEN.pow(nbDigits-1);
        while(newTerm.divide(power).equals(BigInteger.ZERO)){
            newTerm=fn.add(fn_1);
            fn_1=fn;
            fn=newTerm;
            newTermIndex++;
        }
        return newTermIndex;
    }

    //Problem 26
    static int getReduced2And5PowersNumber(int number){
        int pow2=0;
        while(number%2==0){
            number=number/2;
            pow2++;

        }

        int pow5=0;
        while(number%5==0){
            number=number/5;
            pow5++;
        }
        return number;
    }
    //( 1/ 2^alpha*5^beta * A ) has the same cycle as 1/A
    static int getCycleof1OverNumber(int number){

        int reduced=getReduced2And5PowersNumber(number);
        int power=1;
        if(reduced!=1 && reduced==number)
            while(!BigInteger.TEN.pow(power).subtract(BigInteger.ONE).mod(new BigInteger(reduced+"")).equals(BigInteger.ZERO)){
                power++;
            }
        else
            return 0;
        return power;
    }
    static int numberLongestRecurringCycle_Problem26(int THRESHOLD){
        int maxCycle=0;
        int maxCycleNumber=2;
        for(int i=2;i<THRESHOLD;i++){
            int cycle=getCycleof1OverNumber(i);
            System.out.println(i+" : "+cycle);
            if(cycle>maxCycle) {
                maxCycle = cycle;
                maxCycleNumber=i;
            }
        }
        System.out.println("MAX CYCLE : "+maxCycle);
        return maxCycleNumber;
    }
    //Problem 27
    static int getLongestSeriesLength(BigInteger a,BigInteger b){
        int count=0;
        for(BigInteger n=BigInteger.ZERO;n.compareTo(b)<0;n=n.add(BigInteger.ONE)){
            BigInteger calc=n.pow(2).add(a.multiply(n)).add(b);
            if(isPrime(calc)){
                count++;
            }
            else
                break;
        }
        return count;
    }
    static int getLongestSeriesForAsAndBs_Problem27(int THRESHOLDA,int THRESHOLDB){
        int max=0;
        BigInteger maxA=BigInteger.ZERO;
        BigInteger maxB=BigInteger.ZERO;
        if(THRESHOLDA%2==0)
            THRESHOLDA=THRESHOLDA-1;
        for(BigInteger a=new BigInteger("-"+THRESHOLDA);a.compareTo(new BigInteger(""+THRESHOLDA))<=0;a=a.add(new BigInteger("2"))){
            for(BigInteger b=new BigInteger("-"+THRESHOLDB);b.compareTo(new BigInteger(""+THRESHOLDB))<=0;b=b.add(new BigInteger("1"))){
                if(!isPrime(b))
                    continue;
                int length=getLongestSeriesLength(a,b);
                if(length>max){
                    max=length;
                    maxA=a;
                    maxB=b;
                }
                    
            }
        }
        System.out.println("Maximum A : "+maxA+"  maximumB "+maxB+" Product "+maxA.multiply(maxB));
        System.out.println("Equation n²+"+maxA+"n+"+maxB);
        return max;
    }

    //Problem 28 Spiral
    static int getSumSpiral_Problem28(int size){
        int sum=1;
        int number=1;
        for(int i=2;i<size;i=i+2){
            for(int j=0;j<4;j++){
                number=number+i;
                sum+=number;
            }
        }
        return sum;
    }

    //Problem 29 Distinct powers
    static int getNumberOfUniquePower_Problem29(int a,int b){
        HashSet<BigInteger> powersSet=new HashSet<>();
        for(int i=2;i<=a;i++){
            BigInteger bigI=new BigInteger(i+"");
            for(int j=2;j<=b;j++){
                powersSet.add(bigI.pow(j));
            }
        }
        return powersSet.size();
    }

    //Problem 30
    static int sumOfNthPowDigits(int number,int power){
        String numString=Integer.toString(number);
        int sum=0;
        for(int i=0;i<numString.length();i++){
            sum+=(int) Math.pow(Integer.parseInt(""+numString.charAt(i)),power);
        }
        return sum;
    }
    static int sumOfNumbersWrittenAsSumOfPowersOfDigits_Problem30(int power){
        int resultSum=0;
        int i=0;
        int nineToPow=(int) Math.pow(9,power);
        while((int)Math.pow(10,Integer.toString(i).length()-1)<=Integer.toString(i).length()*nineToPow){
            int sum=Euler.sumOfNthPowDigits(i,power);
            if(i==sum) {
                System.out.println(i + " sum : " + sum);
                resultSum+=sum;
            }
            i++;
        }
        return resultSum;
    }
}
