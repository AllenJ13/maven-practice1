package com.xuexi.controller;

import com.xuexi.domain.Product;
import com.xuexi.service.IProductService;
import com.xuexi.utils.DateStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService iProductService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new DateStringEditor());
	}

	@RequestMapping("/save.do")
	public String save(Product product) throws Exception {
		iProductService.save(product);
		return "redirect:findAll.do";
	}

	/**
	 * 查询所有产品
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findAll.do")
	@RolesAllowed("ADMIN")
	public ModelAndView findAll() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<Product> products = iProductService.findAll();

		modelAndView.addObject("productList", products);
		modelAndView.setViewName("product-list1");
		return modelAndView;
	}
}
