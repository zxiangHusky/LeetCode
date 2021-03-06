/*
Divide two integers without using multiplication, division and mod operator.
*/
public class Solution {
    /**
     * @param dividend the dividend
     * @param divisor the divisor
     * @return the result
     */
    // public int divide(int dividend, int divisor) {
    //     // Write your code here
    // }
     public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        boolean signNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);
        long tempDividend = Math.abs((long) dividend);
        long tempDivisor = Math.abs((long) divisor);
        long result = divideCore(tempDividend, tempDivisor);
        if (signNegative == true) {
            result = -result;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)result;
    }
    
    private long divideCore(long dividend, long divisor) {
        if (dividend < divisor) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        
        long closestPowNumber = findTheClosestPowNumber(dividend, divisor);
        long subResult = divideCore(dividend - (divisor << closestPowNumber), divisor);
        return ((long)1 << closestPowNumber) + subResult;
    }
    
    private long findTheClosestPowNumber(long dividend, long divisor) {
        long index = 0;
        while (dividend >= (divisor << (index + 1))) {
            index++;
        }
        return index;
    }
}
