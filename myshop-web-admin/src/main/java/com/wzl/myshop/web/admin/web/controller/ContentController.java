package com.wzl.myshop.web.admin.web.controller;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.commons.dto.PageInfo;
import com.wzl.myshop.domain.TbContent;
import com.wzl.myshop.web.admin.service.TbContentService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2018/9/14.
 */

@Controller
@RequestMapping(value = "content")
public class ContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;

        //id不为空，则从数据库获取
        if (id != null) {
            tbContent = tbContentService.getById(id);
        } else {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * 跳转到用户列表页
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "content_list";
    }


    /**
     * 跳转用户表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {

        return "content_form";
    }

    /**
     * 保存用户信息
     *
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbContentService.save(tbContent);
        System.out.println("UserController: " + tbContent);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }
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
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除内容成功");
        }else{
            baseResult = BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }


    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<TbContent> page(HttpServletRequest request, TbContent tbContent) {
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);


        //封装DataTables需要的结果
        PageInfo<TbContent> pageInfo = tbContentService.page(start, length, draw, tbContent);
//        //处理上传的名剪名
//        List<TbContent> tbContents = pageInfo.getData();
//        for (TbContent content : tbContents) {
//            content.setPic("/static/upload/" + content.getPic());
//        }

        return pageInfo;
    }


    /**
     * 显示用户详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent) {
        return "user_detail";
    }
}
