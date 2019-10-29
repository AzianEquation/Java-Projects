//coded by John Esco
import java.awt.font.GraphicAttribute;
import java.util.*;
public class DijkstraAlgo {

    public static final int GRAPH_SIZE =10;
    public static final int MAX_OB = (GRAPH_SIZE*GRAPH_SIZE)/5;
    public static void main(String[] args) {
        //create the graph
        Vertex[][] graph = new Vertex[GRAPH_SIZE][GRAPH_SIZE];
        for (int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                graph[i][j] = new Vertex(j,i);
            }
        }
        //Place some obstacles
        Random r = new Random();
        int obCount = 0;
        while(obCount<MAX_OB){
            int i = r.nextInt(graph.length);
            int j = r.nextInt(graph.length);
            //set to null will remove edges like there is not a vertex
            if(graph[i][j] != null){
                //cannot be start position or end [0][0] || [9][9]
                if((i==0 && j==0)||(i==graph.length-1 && j==graph.length-1))
                    continue;//starts while over again
                graph[i][j]= null;//removes vertex
                obCount++;
            }
        }//print the graph. could use a for loop but will separate the functionally
        printGraph(graph);
        Vertex finalVert = dijkstras(graph);
        if(finalVert ==null)
            System.out.println("No path found");
        else{
            ArrayList<Vertex> path = new ArrayList<Vertex>();
            while(finalVert != null){
                path.add(finalVert);
                System.out.println(finalVert);
                finalVert = finalVert.prev;
            }
            printGraph(graph,path);
        }
    }

    //one to print normally and another to print showing the shortest path
    public static void printGraph(Vertex[][] graph,ArrayList<Vertex> path){
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                if(graph[i][j] == null)
                    System.out.print("X|");
                else if(path != null && path.contains(graph[i][j])){
                    System.out.print("O|");
                }
                else
                    System.out.print("_|");
            }
            System.out.println();
        }
    }
    public static void printGraph(Vertex[][] graph){
        printGraph(graph,null);
    }
    public static Vertex dijkstras(Vertex[][] graph){
        //behaves a lot like BFS
        //need a priority queue -heap
        PriorityQueue<Vertex> pQ = new PriorityQueue<Vertex>(); //in java this is a straight up min-heap so it puts smaller value up front
        graph[0][0].dist = 0;//assume its the top lefft but can start at any arbitrary vertex
        pQ.add(graph[0][0]);
        while(!pQ.isEmpty()){
            Vertex currV = pQ.remove();
            for(int i = currV.y-1;i<=currV.y+1;i++){
                for(int j = currV.x-1;j<=currV.x+1;j++){
                    //need to ignore one vertex you are on or will result in a infinite loop
                    if( i == currV.y && j == currV.x)
                        continue;
                    if(isValid(i)&&isValid(j)&&graph[i][j] != null){
                        //need two caees since cant add to ddouble.max
                        if(graph[i][j].dist >= Double.MAX_VALUE){
                            graph[i][j].dist = currV.dist+Vertex.distance(currV,graph[i][j]);
                            graph[i][j].prev=currV;
                            //java does not have reheapify
                            if(pQ.contains(graph[i][j]))
                                pQ.remove(graph[i][j]);
                            pQ.add(graph[i][j]);
                        }
                        else{
                            double newDist = currV.dist+Vertex.distance(currV,graph[i][j]);
                            if(newDist <graph[i][j].dist){
                                graph[i][j].dist=newDist;
                                graph[i][j].prev=currV;
                                if(pQ.contains(graph[i][j]))
                                    pQ.remove(graph[i][j]);
                                pQ.add(graph[i][j]);
                            }


                        }
                    }
                }
            }
        }
        if(graph[graph.length-1][graph.length-1].prev == null)
            return null;
        else
            return graph[graph.length-1][graph.length-1];
    }
    public static boolean isValid(int i){
        return i>=0 && i<=GRAPH_SIZE-1;
    }


}
