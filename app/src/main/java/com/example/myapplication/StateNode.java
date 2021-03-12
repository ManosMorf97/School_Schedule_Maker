package com.example.myapplication;



public class StateNode {
    private final State state;
    private final int value;
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
