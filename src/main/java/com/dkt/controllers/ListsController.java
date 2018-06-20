package com.dkt.controllers;

import com.dkt.models.Lists;
import com.dkt.models.stuffList;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ListsController {

    Lists lists = Lists.getInstance();

    @GetMapping("/lists")
    public List<stuffList> viewLists(){
        return lists.getLists();
    }

    @PostMapping("/lists")
    public boolean createStuffList(@RequestBody Map<String, String> list)
    {
        int id = Integer.parseInt(list.get("id"));
        String name = list.get("name");
        String date = list.get("date");
        return lists.createList(id, name, date);
    }

//    @DeleteMapping("lists/{id}")
//    public boolean deleteStuffList(@PathVariable String Id){
//        int id = Integer.parseInt(Id);
//
//    }

    @GetMapping("lists/{id}")
    public stuffList viewStuffList(@PathVariable String id){
        int Id = Integer.parseInt(id);
        return lists.getStuffListById(Id);
    }




}
