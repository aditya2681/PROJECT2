package com.cognizant.clientportal.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.clientportal.model.DefectiveComponentDetail;
import com.cognizant.clientportal.model.PaymentCredential;
import com.cognizant.clientportal.model.ProcessRequest;
import com.cognizant.clientportal.model.ProcessResponse;
import com.cognizant.clientportal.model.UserLoginCredential;
import com.cognizant.clientportal.model.UserToken;
import com.cognizant.clientportal.proxyExchange.AuthClient;
import com.cognizant.clientportal.proxyExchange.ComponentProcessingProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author POD 4
 * @apiNote This class is handling all the end points for Component Processing microservice. 
 * This controller has mappings as-
 * 			postmapping-getProcessDetails()
 * 			postmapping-completeProcessing()
 * 
 * @see AuthClient is used to verify the token.
 * @see ComponentProcessingProxy is to call processing methods from other microservice.
 *
 */
@Controller
@Slf4j
public class PagesController {
	@Autowired
	AuthClient authProxy;
	@Autowired
	ComponentProcessingProxy componentProcessingProxy;

	@Autowired
	private Environment env;

	static float total;

	/**
	 * 
	 * @param ModelMap model
	 * @return String
	 * This is method which returns login page
	 */
	@GetMapping("/loginPortal")
	public String getTest(ModelMap model) {
		log.info(env.getProperty("string.start"));
		model.addAttribute("userLoginCredential", new UserLoginCredential()); 
		log.info(env.getProperty("string.end"));
		return "login";
	}

	/**
	 * 
	 * @param userLoginCredential
	 * @param model
	 * @param session
	 * @return ModelAndView
	 * This method returns a form to fill details for request of repair/replacement.
	 */
	@PostMapping("/home")
	public ModelAndView getHome(
			@ModelAttribute("userLoginCredential") UserLoginCredential userLoginCredential,
			ModelMap model, HttpSession session) {

		log.info(env.getProperty("string.start"));
		log.debug(env.getProperty("string.userlogincred"),userLoginCredential);
		model.addAttribute("processRequest", new ProcessRequest()); 
		model.addAttribute("dc", new DefectiveComponentDetail()); 

		ResponseEntity<UserToken> tokenInfo = null;
		try {
			tokenInfo = (ResponseEntity<UserToken>) authProxy.getToken(userLoginCredential);
			UserToken userToken = (UserToken) tokenInfo.getBody();
			session.setAttribute("token", userToken.getToken());
			return new ModelAndView("home");
		} catch (Exception e) {
			ModelAndView mview=new ModelAndView();
			mview.addObject("msg", env.getProperty("string.invalid.cred"));
			log.info(e.getLocalizedMessage());
			mview.setViewName("login");
			log.info(env.getProperty("string.end"));
			return mview;
		}

	}
	/**
	 * 
	 * @param model
	 * @return String
	 */
	@GetMapping("/home")
	public String getHomeAfter(ModelMap model) {
		log.info(env.getProperty("string.start"));
		model.addAttribute("processRequest", new ProcessRequest()); 
		model.addAttribute("dc", new DefectiveComponentDetail()); 
		log.info(env.getProperty("string.end"));
		return "home";
	}
	/**
	 * 
	 * @return String
	 */
	@GetMapping("/tokenExpiredpage")
	public String getTokenExpiredPage() {
		log.info(env.getProperty("string.start"));
		log.info(env.getProperty("string.end"));
		return "tokenExpiredPage";
	}

	@PostMapping("/response")
	@HystrixCommand(fallbackMethod = "fallbackgetRes")
	public String getRes(
			@ModelAttribute("processRequest") ProcessRequest processRequest,
			@ModelAttribute("dc") DefectiveComponentDetail detail, ModelMap model,
			HttpSession session) throws ParseException {
		log.info(env.getProperty("string.start"));
		log.debug(env.getProperty("string.def.comp"),detail);
		log.debug(env.getProperty("string.request"),processRequest);
		processRequest.setDc(detail);
		if (session.getAttribute("token").equals("logout"))
			return "redirect:loginPortal";
		ResponseEntity<ProcessResponse> response = null;
		try {
			response = componentProcessingProxy.getProcessDetails("Bearer "+ session.getAttribute("token").toString(), processRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
			if (e.getMessage().contains(env.getProperty("string.conn.refused")))
				return "redirect:componentServiceDown";
			if (e.getMessage().contains(env.getProperty("string.packdel.down")))
				return "redirect:packAndDeliveryServiceDown";
			return "redirect:tokenExpiredpage";
		}
		int requestId = response.getBody().getRequestId();
		float processCharge = response.getBody().getProcessingCharge();
		float deliveryCharge = response.getBody().getPackagingAndDeliveryCharge();
		String dateOfDelivery = response.getBody().getDateOfDelivery();
		model.addAttribute("requestId", requestId);
		model.addAttribute("processCharge", processCharge);
		model.addAttribute("deliveryCharge", deliveryCharge);
		model.addAttribute("dateOfDelivery", dateOfDelivery);
		total = processCharge + deliveryCharge;
		log.info(env.getProperty("string.end"));
		return "response";
	}
	/**
	 * 
	 * @return String
	 * this method returns paymentAndDeliveryServiceDown page
	 */
	@GetMapping("/packAndDeliveryServiceDown")
	public String getPaymentDown() {
		log.info(env.getProperty("string.start"));
		log.info(env.getProperty("string.end"));
		return "paymentAndDeliveryServiceDown";
	}
	/**
	 * 
	 * @return String
	 * this method returns componentServiceDown page
	 */
	@GetMapping("/componentServiceDown")
	public String getDownService() {
		log.info(env.getProperty("string.start"));
		log.info(env.getProperty("string.end"));
		return "componentServiceDown";
	}
	/**
	 * 
	 * @param processRequest
	 * @param dc
	 * @param model
	 * @param session
	 * @return String
	 * @throws ParseException
	 * This is fallback Method for getRes function
	 */
	public String fallbackgetRes(
			@ModelAttribute("processRequest") ProcessRequest processRequest,
			@ModelAttribute("dc") DefectiveComponentDetail detail, ModelMap model,
			HttpSession session) throws ParseException {
		log.info(env.getProperty("string.start"));
		log.debug(env.getProperty("string.def.comp"),detail);
		log.debug(env.getProperty("string.request"),processRequest);
		model.addAttribute("message", env.getProperty("string.servdown"));
		log.info(env.getProperty("string.end"));
		return "paymentInfo";
	}
	/**
	 * 
	 * @param model
	 * @param session
	 * @return String
	 * This method returns Payment page.
	 */
	@GetMapping("/payment")
	public String getPayment(ModelMap model, HttpSession session) {
		log.info(env.getProperty("string.start"));
		if (session.getAttribute("token").equals("logout"))
			return "redirect:loginPortal";
		model.addAttribute("total", total);
		log.info(env.getProperty("string.end"));
		return "payment";
	}
	/**
	 * 
	 * @param paymentCredential
	 * @param model
	 * @param session
	 * @return String
	 * @throws Exception
	 *  This method returns paymentInfo page.
	 */
	@PostMapping("/paymentInfo")
	@HystrixCommand(fallbackMethod = "fallbackgetPayementInfo")
	public String getPayementInfo(
			@ModelAttribute("paymentCred") PaymentCredential paymentCredential,
			ModelMap model, HttpSession session) throws Exception {
		log.info(env.getProperty("string.start"));
		log.debug(env.getProperty("string.pay.cred"),paymentCredential);

		if (session.getAttribute("token").equals("logout"))
			return "redirect:loginPortal";
		paymentCredential.setProcessingCharge(total);
		try {
			String message = componentProcessingProxy.completeProcessing(
					(String) "Bearer " + session.getAttribute("token"),
					paymentCredential).getBody();
			if("Success".equals(message)) 
				model.addAttribute("message", env.getProperty("string.succ"));
			else
				model.addAttribute("message", env.getProperty("string.fail"));


		} catch (Exception e) {
			log.info(e.getMessage());
			return "redirect:tokenExpiredpage";
		}
		log.info(env.getProperty("string.end"));
		return "paymentInfo";
	}
	/**
	 * 
	 * @param paymentCredential
	 * @param model
	 * @param session
	 * @return String
	 * @throws Exception
	 * This is fallback method for PayementInfo page.
	 */
	public String fallbackgetPayementInfo(
			@ModelAttribute("paymentCred") PaymentCredential paymentCredential,
			ModelMap model, HttpSession session) throws Exception {
		log.info(env.getProperty("string.start"));
		log.debug(env.getProperty("string.pay.cred"),paymentCredential);
		model.addAttribute("message", env.getProperty("string.servdown"));
		log.info(env.getProperty("string.end"));
		return "paymentInfo";
	}
	/**
	 * 
	 * @param session
	 * @return String
	 * This method returns login portal page.
	 */
	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		log.info(env.getProperty("string.start"));
		session.setAttribute("token", "logout");
		log.info(env.getProperty("string.end"));
		return "redirect:loginPortal";
	}
	/**
	 * 
	 * @return List<String>
	 * This method adds List to page component.
	 */
	@ModelAttribute("componentTypes")
	public List<String> componentTypesGenerator() {
		log.info(env.getProperty("string.start"));
		List<String> list = new ArrayList<>();
		list.add("Integral");
		list.add("Accessory");
		log.debug("List:",list);
		log.info(env.getProperty("string.end"));
		return list;
	}

}
