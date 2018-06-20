package com.dkt.models;

import java.util.ArrayList;
import java.util.List;

public class stuffList {

    private int id;
    private String name;
    private String createdDate;
    private List<stuff> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    // init list
    public stuffList(){
        list = new ArrayList<stuff>();
        list.add(new stuff(1, "Spring core", "22-02-2018", "23-02-2018", "step 1: basic"));
        list.add(new stuff(2, "MVC", "22-02-2018", "23-02-2018", "step 2"));
    }

    public stuffList(int id, String name, String createdDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        list = new ArrayList<stuff>();
    }




    public List<stuff> getStuffList(){
        return list;
    }

    public stuff getStuffById(int id){
        for(stuff s: list){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    // search stuff by text
    public List<stuff> searchStuff(String searchString){
        List<stuff> searchList = new ArrayList<stuff>();
        for(stuff s : list){
            if(s.getTitle().toLowerCase().contains(searchString.toLowerCase()) || s.getComment().toLowerCase().contains(searchString.toLowerCase())) {
                searchList.add(s);
            }
        }
        return searchList;
    }

    // add stuff to list
    public boolean addStuff(int id, String title, String startingDate, String endingDate, String comment){
        for(stuff s : list){
            if(s.getId() == id){
                return false;
            }
        }
        stuff newStuff = new stuff(id, title, startingDate, endingDate, comment);
        list.add(newStuff);
        return true;
    }

    // update stuff
    public boolean updateStuff(int id, String title, String startingDate, String endingDate, String comment){
        try{
            stuff newStuff = new stuff(id, title, startingDate, endingDate, comment);
            list.set(id, newStuff);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // delete stuff by id
    public boolean deleteStuffById(int id){
        try{
            list.remove(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
