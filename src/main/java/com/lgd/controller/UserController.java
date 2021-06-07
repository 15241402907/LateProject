package com.lgd.controller;

import com.lgd.entity.User;
import com.lgd.service.UserService;
import com.lgd.util.MyFileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String, Object> queryByPage(Integer page, Integer rows){
        Map<String, Object> map = userService.queryByPage(page, rows);
        return  map;
    }

    @RequestMapping("updateStatus")
    public String updateStatus(String id,String status){
        if(status.equals("1")){
            userService.update(new User(id,null,null,null,null,null,"0",null));
        }else{
            userService.update(new User(id,null,null,null,null,null,"1",null));
        }
        return "redirect:/main/main.jsp";
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(String oper,User user,String id){
        String message = null;
        if(oper.equals("del")){
            userService.delete(id);
        }
        if(oper.equals("edit")){
            userService.update(user);
        }
        if(oper.equals("add")){
            userService.insert(user);
            message=user.getId();
        }
        return message;

    }

    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile headImg, HttpServletRequest request,String id){
        String img = MyFileUtils.uploadFile(headImg, request, "/upload");
        User user = userService.selectOne(id);
        user.setHeadImg(img);
        userService.update(user);
    }


}
