package com.rental.Services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rental.Models.Response.CustomResponse;
import com.rental.Models.Response.ResultResponse;
import com.rental.Models.Users.UsersModels;
import com.rental.Repository.UsersRepository;

@Service
public class UsersServices {

    @Autowired
    UsersRepository usersRepository;

    public ResultResponse Data() {
        CustomResponse result = new CustomResponse();
        try {
            List<UsersModels> data = usersRepository.findAll();
            result.setStatus(HttpStatus.OK);
            result.setMessage("Data Mahasiswa");
            result.setData(data);
        } catch (Exception ex) {
            result.setStatus(HttpStatus.BAD_REQUEST);
            result.setMessage(ex.toString());
            result.getData();
        }

        // SET RESULT RESPONSE
        ResultResponse res = new ResultResponse();
        res.setResultGeneral(result);
        return res;
    }

    
    public ResultResponse Create(UsersModels usersModels) {
        CustomResponse result = new CustomResponse();
        try {
            usersRepository.save(usersModels);
            result.setStatus(HttpStatus.OK);
            result.setMessage("Data User Saved!");
            result.setData(usersModels);
        } catch (Exception ex) {
            result.setStatus(HttpStatus.BAD_REQUEST);
            result.setMessage(ex.toString());
            result.getData();
        }
        // SET RESULT RESPONSE
        ResultResponse res = new ResultResponse();
        res.setResultGeneral(result);
        return res;
    }


    public ResultResponse Update(BigInteger id, UsersModels usersModels) {
        CustomResponse result = new CustomResponse();
        try {
            UsersModels data = usersRepository.findById(id).get();
            if (data == null) {
                result.setStatus(HttpStatus.EXPECTATION_FAILED);
                result.setMessage("Data Users Not Found!");
                result.setData(null);
            } else {
                data.setUsername(usersModels.getUsername());
                data.setEmail(usersModels.getEmail());
                data.setAddress(usersModels.getAddress());
                data.setAvatar(usersModels.getAvatar());
                data.setBorn(usersModels.getBorn());
                data.setPassword(usersModels.getPassword());
                data.setPhone(usersModels.getPhone());
                usersRepository.save(data);
                result.setStatus(HttpStatus.OK);
                result.setMessage("Data User Updated!");
                result.setData(data);
            }
        } catch (Exception ex) {
            result.setStatus(HttpStatus.BAD_REQUEST);
            result.setMessage(ex.toString());
            result.getData();
        }
        // SET RESULT RESPONSE
        ResultResponse res = new ResultResponse();
        res.setResultGeneral(result);
        return res;
    }


    public ResultResponse Delete(JSONObject objParam) {
        // SET DATA RESPONSE
        CustomResponse result = new CustomResponse();
        try {
            BigInteger id = new BigInteger(objParam.getString("id"));
            Optional<UsersModels> data = usersRepository.findById(id);
            if (data.isEmpty()) {
                result.setStatus(HttpStatus.EXPECTATION_FAILED);
                result.setMessage("Data Users Not Found!");
                result.setData(null);
            } else {
                usersRepository.delete(data.get());
                result.setStatus(HttpStatus.OK);
                result.setMessage("Data User Deleted!");
                result.setData(data);
            }
        } catch (Exception ex) {
            result.setStatus(HttpStatus.BAD_REQUEST);
            result.setMessage(ex.toString());
            result.getData();
        }

        // SET RESULT RESPONSE
        ResultResponse res = new ResultResponse();
        res.setResultGeneral(result);

        return res;
    }

}
