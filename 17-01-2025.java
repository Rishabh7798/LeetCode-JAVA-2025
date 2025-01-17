// 2683. Neighboring Bitwise XOR

class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int n=derived.length;
        int sum=0;

        
        // Hints-02: The xor-sum of the derived array should be 0 since there is always a duplicate occurrence of each element.

        // Example: Length of derived=4
        //     derived[0]=org[0]^org[1]
        //     derived[1]=org[1]^org[2]
        //     derived[2]=org[2]^org[3]
        //     derived[3]=org[3]^org[0]

        //     Xor-Sum = derived[0] ^ derived[1] ^ derived[2] ^ derived[3]
        //     Xor-Sum = (org[0]^org[1]) ^ (org[1]^org[2]) ^ (org[2]^org[3]) ^ (org[3]^org[0])
        //     //re-arrange it.....
        //     Xor-Sum = (org[0]^org[0]) ^ (org[1]^org[1]) ^ (org[2]^org[2]) ^ (org[3]^org[3])
        //     Xor-Sum = 0
        
        
        for(int i=0; i<n; i++){
            sum^=derived[i];
        }

        return sum==0;
    }
}
