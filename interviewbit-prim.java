public class Solution {

/*
https://www.interviewbit.com/problems/commutable-islands/?ref=random-problem
*/
    
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
        
        public String toString(){
            return "("+node+","+dist+")";
        }
    }
    
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        
        HashMap<Integer, ArrayList<Node>> hm = new HashMap<>();
        for(ArrayList<Integer> al : B){
            int u = al.get(0);
            int v = al.get(1);
            int cost = al.get(2);
            
            ArrayList<Node> ual = hm.getOrDefault(u, new ArrayList<Node>());
            ual.add(new Node(v,cost));
            hm.put(u,ual);
            
            ArrayList<Node> val = hm.getOrDefault(v, new ArrayList<Node>());
            val.add(new Node(u,cost));
            hm.put(v,val);
        }
        
        // System.out.println(hm);
        
        int dist[] = new int[A+1];
        for(int i=0;i<A+1;i++){
            dist[i]=Integer.MAX_VALUE;
        }
        
        PriorityQueue<Node> minh = new PriorityQueue<>();
        minh.add(new Node(1,0));
        dist[1]=0;
        
        
        HashSet<Integer> hs = new HashSet<>();
        
        
        int sum=0;
        while(minh.size()>0){
            
            Node u = minh.remove();
            
            if(hs.contains(u.node)){
                continue;
            }
            hs.add(u.node);
            
            sum+=u.dist;
            
            for(Node v: hm.get(u.node)){
                if(!hs.contains(v.node) && dist[v.node]>v.dist){ //This is differ from dijkastra
                    dist[v.node]=v.dist;                         //This is differ from dijkastra
                    minh.add(new Node(v.node, v.dist));          //This is differ from dijkastra
                }
            }
            
        }
        
        return sum;
        
    }
}
