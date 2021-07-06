package com.lgd.controller;

import com.lgd.entity.Admin;
import com.lgd.service.AdminService;
import com.lgd.util.ImageCodeUtil;
import com.lgd.util.Md5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
//@RestController
public class AdminController {
    @Resource
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(String username, String password,String code,HttpSession session){
        Admin admin = adminService.login(username);
        HashMap<String, Object> map = new HashMap<>();
        String str = (String) session.getAttribute("code");
        if(code.equals(str)){
            if(admin==null){
                map.put("message","该用户不存在");
                map.put("status",404);
            }else {
                String md5Code = Md5Utils.getMd5Code(admin.getSalt() + password + admin.getSalt());
                if (admin.getPassword().equals(md5Code)) {
                    map.put("message", "密码正确");
                    map.put("status", 200);
                    session.setAttribute("admin", admin);
                } else {
                    map.put("message", "密码错误");
                    map.put("status", 500);
                }
            }
        }else{
            map.put("message","验证码错误！");
            map.put("status",101);
        }

        return map;
    }
    @RequestMapping("code")
    public void code(HttpServletResponse response, HttpSession session){
        String securityCode = ImageCodeUtil.getSecurityCode();
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        try {
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write(image,"png",os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("code",securityCode);
    }

    @RequestMapping("logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/login/login.jsp";
    }

    @RequestMapping("queryByPage")
    @ResponseBody
    public Map<String, Object> queryByPage(Integer page,Integer rows){
        Map<String, Object> map = adminService.queryByLevel(page,rows);
        return  map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public void  edit(String oper,Admin admin,String id){
        if(oper.equals("add")){
            adminService.insert(admin);
        }
        if(oper.equals("edit")){
            adminService.update(admin);
        }
        if(oper.equals("del")){
            adminService.delete(id);
        }
    }



}
