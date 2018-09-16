package com.wzl.myshop.web.admin.web.controller;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.commons.dto.PageInfo;
import com.wzl.myshop.domain.TbUser;
import com.wzl.myshop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2018/9/8.
 */

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;

        //id不为空，则从数据库获取
        if (id != null) {
            tbUser = tbUserService.getById(id);
        } else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     * 跳转到用户列表页
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
//        List<TbUser> tbUsers = tbUserService.selectAll();
//        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }


    /**
     * 跳转用户表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {

        return "user_form";
    }

    /**
     * 保存用户信息
     *
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbUserService.save(tbUser);
        System.out.println("UserController: " + tbUser);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }

    /**
     * 搜索
     * @param tbUser
     * @param model
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(TbUser tbUser, Model model) {
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }


    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult =null;
        if(StringUtils.isNoneBlank(ids)){
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除用户成功");
        }else{
            baseResult = BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }


    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request, TbUser tbUser) {
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);


        //封装DataTables需要的结果
        PageInfo<TbUser> pageInfo = tbUserService.page(start, length, draw, tbUser);

        return pageInfo;
    }


    /**
     * 显示用户详情
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbUser tbUser) {
        System.out.println("tbuser.username: " + tbUser.getUsername());
        return "user_detail";
    }
}








