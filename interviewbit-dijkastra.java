public class Solution {
    //: https://www.interviewbit.com/problems/dijsktra/
    class Node implements Comparable<Node>{
        int node;
        int dist;
        public Node(int node, int dist){
            this.node=node;
            this.dist=dist;
        }
        public int compareTo(Node o){
            return this.dist-o.dist;
        }
    }
    
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B, int C) {
        
        
        int m = B.size();
        int dist[] = new int[A];
        for(int i=0;i<A;i++ ){
            dist[i]=Integer.MAX_VALUE;
        }
        
        dist[C]=0;
        
        
        HashMap<Integer, ArrayList<Node>> hm = new HashMap<>();
        for(ArrayList<Integer> al : B){
            
            int u = al.get(0);
            int v = al.get(1);
            int d = al.get(2);
            
            if(!hm.containsKey(u)){
                hm.put(u, new ArrayList<Node>());
            }
            hm.get(u).add(new Node(v,d));
            
            if(!hm.containsKey(v)){
                hm.put(v, new ArrayList<Node>());
            }
            hm.get(v).add(new Node(u,d));
            
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(C,0));
        
        HashSet<Integer> hs = new HashSet<>();
        
        while(pq.size()>0){
            
            Node u = pq.poll();
            if(hs.contains(u.node)){
                continue;
            }
            
            hs.add(u.node);
            dist[u.node]=u.dist;
            if(!hm.containsKey(u.node)){
                continue;
            }
            
            for(Node v: hm.get(u.node)){
                if(!hs.contains(v.node) && dist[v.node]>dist[u.node]+v.dist ){
                    pq.add(new Node(v.node,dist[u.node]+v.dist));
                }
            }
            
            
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0;i<A;i++){
            if(dist[i]==Integer.MAX_VALUE){
                dist[i]=-1;
            }
            ans.add(dist[i]);
        }
        
        
        return ans;
        
    }
}
