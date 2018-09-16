package com.wzl.myshop.web.admin.web.controller;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.domain.TbContent;
import com.wzl.myshop.domain.TbContentCategory;
import com.wzl.myshop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2018/9/13.
 */

@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id) {
        TbContentCategory tbContentCategory = null;

        //id不为空，则从数据库获取
        if (id != null) {
            tbContentCategory = tbContentCategoryService.getById(id);
        } else {
            tbContentCategory = new TbContentCategory();
        }

        return tbContentCategory;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();

        //排序
        sortList(sourceList, targetList, 0L);
        System.out.println(targetList+"targetlistttttt");
        model.addAttribute("tbContentCategories", targetList);
        return "content_category_list";
    }


    /**
     * 树型结构
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "tree/data", method=RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id) {
        if (id == null) {
            id = 0L;
        }
        System.out.println(id+"treedataaaaa");
        List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectByPid(id);
        System.out.println(tbContentCategories+"listoftreeeee");
        return tbContentCategories;
    }

    /**
     * 排序
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId 父结点的id
     */
    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory tbContentCategory : sourceList) {
            if (tbContentCategory.getParentId().equals(parentId)) {
                targetList.add(tbContentCategory);

                //判断有没有子结点，如果有继续追加
                if (tbContentCategory.getIsParent()) {
                    for (TbContentCategory contentCategory : sourceList) {
                        if (contentCategory.getParentId().equals(tbContentCategory.getId())) {
//                            System.out.println(tbContentCategory.getId()+"sortListtt");
                            sortList(sourceList, targetList, tbContentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }


    /**
     * 跳转用户表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbContentCategoryService.save(tbContentCategory);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            System.out.println("controller,return 200");
            return "redirect:/content/category/list";
        }
        else{
            model.addAttribute("baseResult", baseResult);
            System.out.println("controller,return notnotnotnot");
            return form(tbContentCategory);
        }
    }

    /**
     * 保存用户信息
     *
     * @param model
     * @param redirectAttributes
     * @return
     */
//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public String save(TbContentCategory tbContentCategory, Model model, RedirectAttributes redirectAttributes) {
//        BaseResult baseResult = TbContentCategoryService.;
//        System.out.println("UserController: " + tbContent);
//        //保存成功
//        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
//            redirectAttributes.addFlashAttribute("baseResult", baseResult);
//            return "redirect:/content/list";
//        }
//        //保存失败
//        else {
//            model.addAttribute("baseResult", baseResult);
//            return "content_form";
//        }
//    }
}
