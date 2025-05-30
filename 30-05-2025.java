2359. Find Closest Node to Given Two Nodes

class Solution {
    static final int VISITED1 = 1;
    static final int VISITED2 = 2;
    static final int VISITED_BOTH = VISITED1 | VISITED2;
    
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        byte[] visited = new byte[n];
        int result = Integer.MAX_VALUE;
        
        // Both paths are still valid.  i.e. node1 and node2 are both valid node numbers.
        while (result == Integer.MAX_VALUE && node1 >= 0 && node2 >= 0) {

            // Find next in path from node1.
            if ((visited[node1] & VISITED1) != 0) 
                node1 = -1;
            else if ((visited[node1] |= VISITED1) == VISITED_BOTH) 
                result = node1;
            else
                node1 = edges[node1];

            // Find next in path from node2.
            if ((visited[node2] & VISITED2) != 0) {
                node2 = -1;
                break;
            }
            if ((visited[node2] |= VISITED2) == VISITED_BOTH) {
                result = Math.min(result, node2);
                break;
            }
            node2 = edges[node2];
        }
        if (result != Integer.MAX_VALUE)  return result;
        
        // If node1 still has a valid path, then node2 has reached the end of its path 
        // without intersecting the node1 path, so we only need to keep searching along 
        // the node1 path.
        if (node1 >= 0) {
            while (node1 >= 0) {
                if ((visited[node1] & VISITED1) != 0)  return -1;
                if ((visited[node1] |= VISITED1) == VISITED_BOTH)  return node1;
                node1 = edges[node1];
            }
            return -1;
        }
        
        // Node2 still has a valid path, then node1 has reached the end of its path 
        // without intersecting the node2 path, so we only need to keep searching along 
        // the node2 path.
        while (node2 >= 0) {
            if ((visited[node2] & VISITED2) != 0)  return -1;
            if ((visited[node2] |= VISITED2) == VISITED_BOTH)  return node2;
            node2 = edges[node2];
        }
        return -1;
    }
}
