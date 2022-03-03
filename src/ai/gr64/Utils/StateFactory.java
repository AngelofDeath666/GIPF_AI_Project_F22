package ai.gr64.Utils;

import ai.gr64.Data.Interfaces.INode;
import ai.gr64.Engine.DTOs.GameState;
import ai.gr64.Engine.DTOs.GameGraph.InnerNode;
import ai.gr64.Engine.DTOs.GameGraph.OutterNode;

public class StateFactory {
   
    public static GameState create(int layers){
        GameState state = new GameState();
        int numNodes = (((6 + (layers + 1) * 6)*(layers + 1))/2) + 1;
        INode[] nodes = new INode[numNodes];
        for(int i = 0; i < numNodes - (layers + 1) * 6; i++){
            nodes[i] = new InnerNode();
        }

        for(int i = 0; i < (layers + 1) * 6; i++){
            nodes[numNodes - ((layers + 1) * 6) + i] = new OutterNode();
        }

        return state;
        
    }
}
