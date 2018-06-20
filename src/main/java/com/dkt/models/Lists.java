package com.dkt.models;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    private List<stuffList> list;
    private static Lists instance = null;

    public static Lists getInstance(){
        if(instance == null){
            instance = new Lists();
        }
        return instance;
    }

    // init lists for demo
    public Lists(){

        list = new ArrayList<stuffList>();
        list.add(new stuffList(1, "Spring learning", "21-02-2018"));
    }

    public List<stuffList> getLists(){
        return list;
    }

    // get list by id
    public stuffList getStuffListById(int id)
    {
        for(stuffList s : list){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    // create list
    public boolean createList(int id, String name, String createdDate){
        for(stuffList s : list){
            if(s.getId() == id || s.getName().equals(name)){
                return false;
            }
        }
        stuffList newList = new stuffList(id, name, createdDate);
        list.add(newList);
        return true;
    }

    // update list
    public boolean updateList(int id, String name, String createdDate){
        try{

            stuffList newList = new stuffList(id, name, createdDate);
            list.set(id, newList);
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    // delete list
    public boolean deleteListById(int id){
        try{
            list.remove(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
