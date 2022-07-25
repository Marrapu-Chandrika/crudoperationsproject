package com.example.demo.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Itemdetails;
import com.example.demo.entity.Order;
import com.example.demo.entity.Rdetails;
import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RdetailsRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	
	ArrayList l = new ArrayList();
	@Autowired
	UserRepository userRepository;//step 1
	RdetailsRepository rdetailsRepository;//step3
	ItemRepository itemRepository;//step 4
	OrderRepository orderRepository;
	
	
	
	public UserController(UserRepository userRepository, RdetailsRepository rdetailsRepository,
			ItemRepository itemRepository, OrderRepository orderRepository) {
		super();
		this.userRepository = userRepository;
		this.rdetailsRepository = rdetailsRepository;
		this.itemRepository = itemRepository;
		this.orderRepository = orderRepository;
	}




	
	
	@GetMapping("/users/new")
	public String createUserForm(Model model,HttpServletRequest request)
	{
    	User user = new User();
		model.addAttribute("user", user);
		request.getSession().setAttribute("msg", "");
		return "create_user";//htmlstep1
	}
	
	
	@PostMapping("/users/register")//step1
    public String registerUser(@ModelAttribute("user") @Valid User newUser,BindingResult result, HttpServletRequest request,RedirectAttributes redirAttrs) {
		
		if (result.hasErrors()) {
		    return "create_user";
		  }
		
		redirAttrs.addFlashAttribute("message", "This is message from flash");
        List<User> users = userRepository.findAll();

        System.out.println("New user: " + newUser.toString());

        for (User user : users) {
            System.out.println("Registered user: " + newUser.toString());

            if (user.getuName().equals(newUser.getuName())) {
                System.out.println("User Already exists!");
                request.getSession().setAttribute("msg", "User Existed");
                return "create_user";
                //return "create_user?str=existed";
            }
        }

        userRepository.save(newUser);
        request.getSession().setAttribute("cid", newUser.getuId());
        return "create_login";//HTMLstep1
        //return "create_user.html";
    }


	
	
	
	@GetMapping("/users/signin")
   	public String createLoginForm(Model model)
   	{
       	User user = new User();
   		model.addAttribute("user", user);
   		return "create_login";//htmlstep2
   		
   	}   
	
	@PostMapping("/users/login")
    public String loginUser(@ModelAttribute("user") User user,HttpServletRequest request) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
        	
            if (other.getuName().equals(user.getuName()) && other.getuPassword().equals(user.getuPassword())) {
            	{
            		System.out.println(user.getuId());
            		request.getSession().setAttribute("cid", user.getuName());
            		return "redirect:/res/all";//step2,3
            	}
            }
        }
        
        return "redirect:/users/new";//step2
    }
	
	
	
	
	
	
	@GetMapping("/res/all")
	public String listRestaurants(Model model)
	{
		model.addAttribute("Rdetails",rdetailsRepository.findAll());
		
		return "res_details";//step 3 html
		
	}


	
	
		  
	  @GetMapping("/users/itemlist/{rId}")
		public String findAllByResId(@PathVariable int rId,Model model,@ModelAttribute("ress") Rdetails res,HttpServletRequest request)
		{
			res=rdetailsRepository.getById(rId);
			model.addAttribute("Itemdetails",itemRepository.findAllByResId(rId));
			request.getSession().setAttribute("rid", res.getrName());
			return "item_list";//step 4 html
		}

	  
	  
	  
	  @GetMapping("/users/orderdet")
		public String displayOrder(@RequestParam(value="itemcode") List<Integer> invoiceNoList,HttpServletRequest request,Model model)
		{
					  		
			int n = invoiceNoList.size();
			int totalPrice=0;	
			for(int i=0;i<n;i++)
			{
					Order order = new Order();
					order.setU_Name(""+request.getSession().getAttribute("cid"));
					order.setR_Name(""+request.getSession().getAttribute("rid"));
					//System.out.println(invoiceNoList.get(i));
					//l.add(invoiceNoList.get(i));
					model.addAttribute("Itemdetails"+(i+1),itemRepository.getById(invoiceNoList.get(i)));
					totalPrice += itemRepository.getById(invoiceNoList.get(i)).getiPrice();
					order.setI_Id(itemRepository.getById(invoiceNoList.get(i)).getiId());
					order.setI_Name(itemRepository.getById(invoiceNoList.get(i)).getiName());
					order.setI_Offer((int)itemRepository.getById(invoiceNoList.get(i)).getiOffer());
					order.setI_Price((int)itemRepository.getById(invoiceNoList.get(i)).getiPrice());
					orderRepository.save(order);
					model.addAttribute("order"+(i+1),order);
					l.add(order.getO_Id());
					//request.getSession().setAttribute("orders"+(i+1),order.getO_Id());
			}
			System.out.println(totalPrice);
			request.getSession().setAttribute("totalPrice", totalPrice);
			request.getSession().setAttribute("oids", l);
			return "order_display";//htmlstep5
		}
	 
	  
	  
	  
	  
	  @GetMapping("/orderdel/{id}")
	  public String deleteorder(@PathVariable int id,HttpServletRequest request)
		{
		  
		  ArrayList ll=(ArrayList)request.getSession().getAttribute("oids");
		  Iterator ii = ll.iterator();
		  
		  int temp=0;
		  while(ii.hasNext())
		  {
			  	Integer i=(Integer)ii.next();
			  	if(i.intValue() != id)
			  	{
			  		temp=i.intValue();
			  	}
				  
		  }
		  
		  System.out.println(temp);
		  orderRepository.deleteById(id);
		  	
		  	return "redirect:/users/orderdets?itemcode="+temp;
		}
	  
	  @GetMapping("/users/orderdets")
		public String displayOrders(@RequestParam(value="itemcode") List<Integer> invoiceNoList,HttpServletRequest request,Model model)
		{
					  		
			int n = invoiceNoList.size();
			int totalPrice=0;	
			for(int i=0;i<n;i++)
			{
					Order order = new Order();
					order.setU_Name(""+request.getSession().getAttribute("cid"));
					order.setR_Name(""+request.getSession().getAttribute("rid"));
					//System.out.println(invoiceNoList.get(i));
					//l.add(invoiceNoList.get(i));
					model.addAttribute("Itemdetails"+(i+1),itemRepository.getById(invoiceNoList.get(i)));
					totalPrice += itemRepository.getById(invoiceNoList.get(i)).getiPrice();
					order.setI_Id(itemRepository.getById(invoiceNoList.get(i)).getiId());
					order.setI_Name(itemRepository.getById(invoiceNoList.get(i)).getiName());
					order.setI_Offer((int)itemRepository.getById(invoiceNoList.get(i)).getiOffer());
					order.setI_Price((int)itemRepository.getById(invoiceNoList.get(i)).getiPrice());
					//orderRepository.save(order);
					model.addAttribute("order"+(i+1),order);
					l.add(order.getO_Id());
					//request.getSession().setAttribute("orders"+(i+1),order.getO_Id());
			}
			System.out.println(totalPrice);
			request.getSession().setAttribute("totalPrice", totalPrice);
			request.getSession().setAttribute("oids", l);
			return "order_display";//htmlstep5
		}
	 	
	 
}
