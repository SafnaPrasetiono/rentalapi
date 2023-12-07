package com.rental.Controller;

import java.math.BigInteger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental.Models.Response.ResultResponse;
import com.rental.Models.Users.UsersModels;
import com.rental.Services.UsersServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class UsersController {
    
    @Autowired
    UsersServices usersServices;

    @GetMapping("/users/data")
    public ResponseEntity<Object> Data() {
        ResultResponse result = new ResultResponse();
        result = usersServices.Data();
        return new ResponseEntity<Object>(result.getResponsed(), result.getStatusResponse());
    }

    @PostMapping("/users/create")
    public ResponseEntity<Object> Create(@RequestBody UsersModels usersModels) {
        ResultResponse result = new ResultResponse();
        result = usersServices.Create(usersModels);
        return new ResponseEntity<Object>(result.getResponsed(), result.getStatusResponse());
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<Object> Update(@PathVariable("id") BigInteger id ,@RequestBody UsersModels usersModels) {
        ResultResponse result = new ResultResponse();
        result = usersServices.Update(id, usersModels);
        return new ResponseEntity<Object>(result.getResponsed(), result.getStatusResponse());
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<Object> Delete(@RequestBody String param) {
        ResultResponse result = new ResultResponse();
        JSONObject objParam = new JSONObject(param);
        result = usersServices.Delete(objParam);
        return new ResponseEntity<Object>(result.getResponsed(), result.getStatusResponse());
    }
    
    
}
