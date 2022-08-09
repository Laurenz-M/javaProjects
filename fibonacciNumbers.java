import java.util.Random;

public class FibonacciRandomTest{
    public static void main(String[] args) {
        int lastFibonacciOne = 1;
        int lastFibonacciTwo = 1;
        int currentFibonacci = 0;
        int[] fibonacciArray = {lastFibonacciOne, lastFibonacciTwo};

        for (int i = 0; currentFibonacci < 1000000; i++) {
            currentFibonacci = lastFibonacciOne + lastFibonacciTwo;
            lastFibonacciOne = lastFibonacciTwo;
            lastFibonacciTwo = currentFibonacci;
            fibonacciArray = pushArray(fibonacciArray, currentFibonacci);
        }

        int[] sectionArray = new int[10];
        int startOfSection = randomInt(0,fibonacciArray.length-10-1);
        for(int i = 0; i < 10; i++) {
            sectionArray[i] = fibonacciArray[startOfSection];
            startOfSection++;
        }
        int sectionCombinedValue = 0;
        for(int i = 0; i < sectionArray.length; i++) {
            sectionCombinedValue += sectionArray[i];
        }
        
        System.out.println("The combined value of the section: " + sectionCombinedValue);
        System.out.println("The product from the 7th element and 11: " + sectionArray[6]*11);
        if(sectionCombinedValue == sectionArray[6]*11){
            System.out.println("Value of 7th element multiplied by 11 is equal to the combined value of section");
        }
        else{
            System.out.println("Error");
        }

    }

    public static int[] pushArray(int[] array,int pushValue){

        int[] result = new int[array.length+1];
        for(int i = 0; i < array.length; i++){
            result[i] = array[i];
        }
        result[result.length-1] = pushValue;
        return result;
    }
    public static int randomInt(int min,int max){
        double r = Math.random();
        return (int)(r * (max-min)) + min;
    }
}