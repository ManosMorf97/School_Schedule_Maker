package com.example.myapplication;

import com.example.myapplication.State;

public class StateNode {
    private State state;
    private int value;
    public StateNode(State state,int value){
        this.state=state;
        this.value=value;
    }
    public State getState(){
        return state;
    }
    public int getValue(){
        return value;
    }
}
