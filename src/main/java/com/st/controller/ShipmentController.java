package com.st.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.st.model.Shipment;
import com.st.model.ShipmentView;
import com.st.service.ShipmentService;

@Controller
@RequestMapping("/stype")
public class ShipmentController {
	private Logger log = LoggerFactory.getLogger(ShipmentController.class);
	//1. show register page
	@Autowired
	private ShipmentService service;
	
	@GetMapping("/sreg")
	public String showShipmentPage(Model model) {
		//form backing object
		model.addAttribute("shipment", new Shipment());
		return "shipmentReg";
	}
	
	//save data
	@PostMapping("/save")
	public String saveShipmentData(@ModelAttribute	Shipment shipment,Model model) {
		log.info("Entered  into save method");
		try {
		//perform save operation
		Integer id=service.saveShipment(shipment);
		//Construct one Message
		String message="ShipmentData '"+id+"' saved";
		log.debug(message);
		//send message to UI
		model.addAttribute("shipment", new Shipment());
		model.addAttribute("message", message);
		}catch(Exception e) {
			log.error("unable to save:"+e.getMessage());
			e.printStackTrace();
		}
		log.info("Back to UI Page");
		return "shipmentReg";
	}
	
	//3.show all data
	@GetMapping("/all")
	public String getAllShipmentData(Model model) {
		
		try {
			log.info("Entered into All Method()");
		List<Shipment> list=service.getAllShipment();
		model.addAttribute("list", list);
		log.info("Got Data from DB using service method size of list is:"+list.size());
		}catch(Exception e) {
			log.error("Unbable to fetch data from DB:"+e.getMessage());
			e.printStackTrace();
		}
		log.info("BAck to UI page");
		return "shipmentData";
	}
	//4.Delete data
	@GetMapping("/delete/{id}")
	public String removeData(@PathVariable Integer id, Model model) {
		
		log.info("Entered for delete with id:"+id);
		try {
		String msg=null;
		if(service.isShipmentExist(id)) {
		service.deleteShipment(id);
		 msg="ShipmentData '"+id+"' deleted";
		 log.debug(msg);
	
		}else {
			 msg="ShipmentData '"+id+"' Not Exists";
			 log.warn(msg);
		}
		//display message
		model.addAttribute("message", msg);
		log.info("Fetching new Data After delete");
		//fetch other rows and display
				List<Shipment> list=service.getAllShipment();
				model.addAttribute("list", list);
		}catch(Exception e) {
			log.error(e.getMessage()+"Unable to Fetch DELETE Operation");
			e.printStackTrace();
		}
		log.info("BAck to UI page");
		return "shipmentData";
		//return "redirect:../all";
	}

	//Edit Data
	@GetMapping("/edit/{id}")
	public String editData(@PathVariable Integer id, Model model) {
		
		log.info("Entered into EditMethod with ID:"+id);
		String page=null;
		try {
		
		log.info("");
		Optional<Shipment> opt=service.getOneShipment(id);
		log.info("Service called is made");
		if(opt.isPresent()) {
			Shipment st=opt.get();
			model.addAttribute("shipment", st);
			page="shipmentedit";
		}else {
			log.warn("Data not exist with Id:"+id);
			page="redirect:../all";
		}
		
		}catch(Exception e) {
			log.error(e.getMessage()+"Unable to Edit Data");
			e.printStackTrace();
		}
		log.info("Back to UIO Page");
		return page;
	}
	
	@PostMapping("/update") 
	public String updateData(@ModelAttribute Shipment shipment, Model model) {
		
		log.info("Entered Into Update operation");
		try {
		service.updateShipment(shipment);
		String msg="Data '"+shipment.getSid()+"'updated";
		log.debug(msg);
		model.addAttribute("message", msg);
		log.info("Fetching Latest Data");
		//fetch other rows and display
		List<Shipment> list=service.getAllShipment();
		model.addAttribute("list", list);
		}catch(Exception e) {
			log.error(e.getMessage()+"Unable to Update Data:");
			e.printStackTrace();
		}
		log.info("BAck to UI page");	
		return "shipmentData";

	}
	
	//Export  Data to Excel file
	@GetMapping("/excel")
	public ModelAndView exportExcel() {
		ModelAndView m= new ModelAndView();
		m.setView(new ShipmentView());
		List<Shipment> list=service.getAllShipment();
		m.addObject("obs", list);
		return m;
	}
	
	//Exort one row from table
	
	@GetMapping("/excel/{id}")
	public ModelAndView exportExcel1(@PathVariable Integer id) {
		ModelAndView m=new ModelAndView();
		m.setView(new ShipmentView());
		Optional<Shipment> opt=service.getOneShipment(id);
		if(opt.isPresent()) {
			m.addObject("obs", Arrays.asList(opt.get()));
		}
		return m;
	}
	
	//AjaxValidation
	@GetMapping("/validate")
	public @ResponseBody String shipmentCodeValidate(@RequestParam String code) {
		String message="";
		if(service.isShipmentCodeExist(code)){
			message="ShipmentCode '"+code+"' already Exists";
		}
		return message;	
		}
	

}
