2566. Maximum Difference by Remapping a Digit

class Solution {
    private int difference(int num) {
        int firstNonNine = -1, firstDigit = -1;
        int remaining = num;
        while (remaining > 0) {
            int digit = remaining % 10;
            if (digit != 9) {
                firstNonNine = digit;
            }
            firstDigit = digit;
            remaining /= 10;
        }
        
        remaining = num;
        int min = 0, max = 0;
        int multiplier = 1;
        while (remaining > 0) {
            int digit = remaining % 10;
            
            int minDigit = digit;
            int maxDigit = digit;
            
            if (digit == firstDigit) {
                minDigit = 0;
            }

            if (digit == firstNonNine) {
                maxDigit = 9;
            }

            min += multiplier * minDigit;
            max += multiplier * maxDigit;

            multiplier *= 10;    
            remaining /= 10;
        }

        return max - min;
    }

    public int minMaxDifference(int num) {
        return difference(num);
        /*
        String digits = String.valueOf(num);
    
        int i = 0;
        while (i < digits.length() && digits.charAt(i) == '9') i++;

        String max = i < digits.length() ? digits.replace(digits.charAt(i), '9') : digits;
        String min = digits.replace(digits.charAt(0), '0');

        return Integer.parseInt(max) - Integer.parseInt(min);
        */
    }
}
