package com.example.myapplication;

import com.example.myapplication.Lesson;

import java.util.ArrayList;

public class Teacher{
		private String name;
		private ArrayList<Lesson>codelessons=new ArrayList<Lesson>();
		private int []D=new int[5];
		private int W;
		public int getD(int i){
			return D[i];
		}
	
		public int getW(){
			return W;
		}
		public void setName(String namee){
			name=namee;
		}
		public void setMaxDay(int md){
			for(int i=0; i<5; i++)
				D[i]=md;
		}
		public void setMaxWeek(int mw){
			W=mw;
		}
		public void setW(int W){
			this.W=W;
		}
		public void setD(int i,int value){
			D[i]=value;
		}
		public String getName(){
			return name;
		}
		public void reduce(int i){
			W--;
			D[i]--;
		}


		public void addLesson(Lesson cod){
			codelessons.add(cod);
		}
		public ArrayList<Lesson> getCodesoflessons(){
			return codelessons;
		}

	}