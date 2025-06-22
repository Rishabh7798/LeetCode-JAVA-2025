2138. Divide a String Into Groups of Size k

//Approach 1  TC = O(n)
// class Solution {
//     public String[] divideString(String s, int k, char fill) {
//         List<String> res = new ArrayList<>();
//         int n = s.length();
//         int curr = 0; // starting index of each group


//         // split string
//         while(curr < n){
//             int end = Math.min(curr + k,n);
//             res.add(s.substring(curr,end));
//             curr += k;
//         }
//         // try to fill in the last group
//         String last = res.get(res.size() - 1);
//         if(last.length() < k){
//             last += String.valueOf(fill).repeat(k - last.length());
//             res.set(res.size() - 1,last);
//         }
//         return res.toArray(new String[0]);
//     }
// }


//Approach 2 TC  =  O(n + k)
class Solution {
    public String[] divideString(String s, int k, char fill) {
        StringBuilder str = new StringBuilder(s);
        while(str.length()%k!=0){
            str.append(fill);
        }
        String arr[]=new String[str.length()/k];
        for(int i=0;i<str.length()/k;i++){
            arr[i]=str.substring(i*k,(i+1)*k);
        }
        return arr;   
 }
}
