package TestBigInt;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {


        BigInteger bi1 = new BigInteger("11111111111111111111111111111111111111111111111111111111111111");
        BigInteger bi2 = new BigInteger("22222222222222222222222222222222222222222222222222");


        //exercise 1
        System.out.println("Add: " + add(bi1, bi2));

        //exercise 2
        System.out.println("Multiply: " + multiply(bi1,bi2));

        //exercise 3
        System.out.println("To base 2: " + bi1.toString(2));
        System.out.println("To base 2: " +  new BigInteger("11").toString(2));

        //exercise 4
        String ex4 = "";
        for(int i = 0; i <100; i++){
            ex4 += "10";
        }

        BigInteger bi3 = new BigInteger(ex4,2);
        System.out.println(bi3.toString(2));



        //exercise 5
        long start = System.currentTimeMillis();
        System.out.println("Isprime Bigint: " + isPrime(new BigInteger("6220528841")));
//        System.out.println(isPrime(bi1));
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));

    }

    static BigInteger add(BigInteger num1, BigInteger num2){
        return (num1.add(num2));
    }

    static  BigInteger multiply(BigInteger num1, BigInteger num2){
        return (num1.multiply(num2));
    }

    static boolean isPrime(BigInteger numb){
        for (int i = 2; i < numb.sqrt().longValue(); i++){
            if(numb.mod(BigInteger.valueOf(i)).equals(BigInteger.valueOf(0))) return false;
        }
        return true;
    }


    static boolean isPrime(long num){
        for (int i = 2; i < Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
