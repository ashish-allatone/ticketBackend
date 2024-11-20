package com.ticket.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.model.AuthenticationResponse;
import com.ticket.model.CompanyDetails;
import com.ticket.model.Customer;
import com.ticket.model.Email;
import com.ticket.model.GroupedBy;
import com.ticket.model.Items;
import com.ticket.model.Log;
import com.ticket.model.Message;
import com.ticket.model.OtpClass;
import com.ticket.model.PurchaseInvoice;
import com.ticket.model.PurchaseOrder;
import com.ticket.model.Sales;
import com.ticket.model.SalesInvoice;
import com.ticket.model.ServiceRequest;
import com.ticket.model.ServiceRequestUpdated;
import com.ticket.model.URL;
import com.ticket.model.User;
import com.ticket.repository.UserRepository;
import com.ticket.service.TicketService;

// @CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "http://141.148.193.21")
@RestController
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserRepository empRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authManager;

	private final String secretKey = "ZXN";

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User emp) {
		// System.out.println(emp.getUserName());
		// System.out.println(emp.getPassword());
		User employee = empRepo.findByUserName(emp.getUserName());
		// System.out.println(employee);
		if (passwordEncoder.matches(emp.getPassword(), employee.getPassword())) {
			User empinfo = new User();
			empinfo.setEmail(employee.getEmail());
			empinfo.setRole(employee.getRole());
			empinfo.setName(employee.getName());
			empinfo.setUserName(employee.getUserName());
			empinfo.setEmployeeId(employee.getEmployeeId());
			empinfo.setContact(employee.getContact());
			empinfo.setLastUpdatedBy(employee.getLastUpdatedBy());
			empinfo.setCompanyId(employee.getCompanyId());
			ticketService.sendOTP(employee.getUserName(), employee.getEmail(),
					new Timestamp(Instant.now().toEpochMilli()), employee.getRole().toString());
			authManager.authenticate(new UsernamePasswordAuthenticationToken(emp.getUserName(), emp.getPassword()));
			// String jwtToken = jwtService.generateToken(employee);
			AuthenticationResponse authResponse = new AuthenticationResponse(empinfo, "");
			return ResponseEntity.ok(authResponse);
		}
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}

	@PostMapping("/register")
	public User saveOwnerInfo(@RequestBody User empInfo) {
		return ticketService.saveOwnerInfo(empInfo);
	}

	@GetMapping("/EmployeeInfo/{companyId}/{role}")
	public List<User> getAllTickets(@PathVariable String companyId, @PathVariable String role) {
		// System.out.println("I am here");
		return (List<User>) ticketService.getAllTickets(companyId, role);
	}

	@PutMapping("/EmployeeInfo")
	public String updateById(@RequestBody() User ownerCompanyEmployeeInfo) {
		return ticketService.updateById(ownerCompanyEmployeeInfo);
	}

	@PostMapping("/CreateReq/{email}")
	public ServiceRequest saveCreateRequest(@RequestBody ServiceRequest serviceRequest, @PathVariable String email) {
		return ticketService.saveCreateRequest(serviceRequest, email);
	}

	@GetMapping("/CreateReq/{companyId}/{supportId}/{role}")
	public List<ServiceRequest> getAllCreate(@PathVariable String companyId, @PathVariable String supportId,
			@PathVariable String role) {
		return ticketService.getAllCreate(companyId, supportId, role);
	}

	@DeleteMapping("/CreateReq/{id}")
	public String Deleteby(@PathVariable("id") Integer id) {
		return ticketService.Deleteby(id);
	}

	@PutMapping("/CreateReq/{id}")
	public Optional<ServiceRequest> updateby(@RequestBody ServiceRequest serviceRequest,
			@PathVariable("id") Integer id) {
		// System.out.println(id);
		return ticketService.updateby(id, serviceRequest);
	}

	@GetMapping("/CreateReq/{id}")
	public Optional<ServiceRequest> findby(@PathVariable("id") Integer id) {
		return ticketService.findby(id);
	}

	@PostMapping("/ServiceRequestUpdated")
	public ServiceRequestUpdated saveServiceReq(@RequestBody ServiceRequestUpdated serviceRequestUpdated) {
		return ticketService.saveServiceReq(serviceRequestUpdated);
	}

	@GetMapping("/ServiceRequestUpdated/{sr_no}")
	public List<ServiceRequestUpdated> getAllRequest(@PathVariable("sr_no") String sr_no) {
		// // System.out.println(sr_no);
		return ticketService.getAllRequest(sr_no);

	}

	@GetMapping("/ServiceRequestUpdatedID/{id}")
	public Optional<ServiceRequest> findbyId(@PathVariable("id") Integer id) {
		return ticketService.findby1(id);
	}

	@PostMapping("/validateOtp")
	public ResponseEntity<?> generateOtp(@RequestBody OtpClass otp) {
		// // System.out.println("Hello this is otp validation!");
		return ticketService.validateOtp(otp);

	}

	@PostMapping("/CompanyDetails")
	public CompanyDetails saveCompanyDet(@RequestBody CompanyDetails companyDetails) {
		return ticketService.saveCompanyDet(companyDetails);

	}

	@GetMapping("/CompanyDetails")
	public List<CompanyDetails> getAllDetails() {
		return ticketService.getAllDetails();
	}

	@GetMapping("/validateCompanyId/{companyId}")
	public ResponseEntity<?> validateCompany(@PathVariable String companyId) {
		// // System.out.println("I am here");
		return ticketService.validateCompanyId(companyId);
	}

	@PutMapping("/User/{userName}")
	public Optional<User> updateduserby(@RequestBody User employeeInfo, @PathVariable("userName") String userName) {
		return ticketService.updateuserby(userName, employeeInfo);
	}

	@GetMapping("/User/{userName}")
	public Optional<User> finduserby(@PathVariable("userName") String userName) {
		return ticketService.finduserby(userName);
	}

	@GetMapping("/matchKey/{localSecretKey}")
	public ResponseEntity<?> matchKey(@PathVariable String localSecretKey) {
		// // System.out.println(localSecretKey);
		if (localSecretKey.equals(secretKey)) {
			Message msg = new Message();
			msg.setMessage("You are elegible for Super Role");
			return ResponseEntity.accepted().body(msg);
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@GetMapping("/Company")
	public List<CompanyDetails> getAllCompanys() {
		return (List<CompanyDetails>) ticketService.getAllCompanys();
	}

	@GetMapping("/getAllUserName")
	public ArrayList<String> getAllUserName() {
		return ticketService.getAllUserName();
	}

	@GetMapping("/resendOtp/{userName}/{email}")
	public ResponseEntity<?> resendOtp(@PathVariable String userName, @PathVariable String email) {
		// // System.out.println(userName + " " + email);
		ticketService.sendOTP(userName, email, new Timestamp(Instant.now().toEpochMilli()), "");
		Message msg = new Message();
		msg.setMessage("OTP send Successfully");
		return ResponseEntity.accepted().body(msg);
	}

	@PostMapping("/items")
	public ResponseEntity<?> insertItems(@RequestBody Items item) {
		return ticketService.insertItems(item);
	}

	@GetMapping("/items")
	public List<Items> getItems() {
		return ticketService.getItems();
	}

	@GetMapping("/items/{name}")
	public Items getItemsByName(@PathVariable String name) {
		return ticketService.getItemsByName(name);
	}

	@PostMapping("/customer")
	public ResponseEntity<?> insertCustomer(@RequestBody Customer customer) {
		return ticketService.insertCustomer(customer);
	}

	@GetMapping("/customer")
	public List<Customer> getCustomer() {
		return ticketService.getCustomer();
	}

	@GetMapping("/customer/{companyName}")
	public Customer getItemsByCompanyName(@PathVariable String companyName) {
		return ticketService.getItemsByCompanyName(companyName);
	}

	@PostMapping("/purchaseOrder")
	public ResponseEntity<?> insertPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
		return ticketService.insertPurchaseOrder(purchaseOrder);
	}

	@GetMapping("/purchaseOrder")
	public List<PurchaseOrder> getPurchaseOrder() {
		return ticketService.getPurchaseOrder();
	}

	@GetMapping("/purchaseOrder/{ponumber}")
	public PurchaseOrder getPurchaseOrderByponumber(@PathVariable String ponumber) {
		return ticketService.getPurchaseOrderByponumber(ponumber);
	}

	@PostMapping("/purchaseInvoice")
	public ResponseEntity<?> insertPurchaseInvoice(@RequestBody PurchaseInvoice purchaseInvoice) {
		return ticketService.insertPurchaseInvoice(purchaseInvoice);
	}

	@GetMapping("/purchaseInvoice")
	public List<PurchaseInvoice> getPurchaseInvoice() {
		return ticketService.getPurchaseInvoice();
	}

	@GetMapping("/purchaseInvoice/{ponumber}")
	public PurchaseInvoice getPurchaseInvoiceByponumber(@PathVariable String ponumber) {
		return ticketService.getPurchaseInvoiceByponumber(ponumber);
	}

	@PostMapping("/sales")
	public ResponseEntity<?> insertSales(@RequestBody Sales sales) {
		return ticketService.insertSales(sales);
	}

	@GetMapping("/sales")
	public List<Sales> getSales() {
		return ticketService.getSales();
	}

	@GetMapping("/sales/{ponumber}")
	public Sales getSalesponumber(@PathVariable String ponumber) {
		return ticketService.getSalesByponumber(ponumber);
	}

	@PostMapping("/salesInvoice")
	public ResponseEntity<?> insertSalesInvoice(@RequestBody SalesInvoice salesInvoice) {
		return ticketService.insertSalesInvoice(salesInvoice);
	}

	@GetMapping("/salesInvoice")
	public List<SalesInvoice> getSalesInvoice() {
		return ticketService.getSalesInvoice();
	}

	@GetMapping("/salesInvoice/{ponumber}")
	public SalesInvoice getsalesInvoiceByponumber(@PathVariable String ponumber) {
		return ticketService.getsalesInvoiceByponumber(ponumber);
	}

	@PostMapping("/url")
	public ResponseEntity<?> insertURL(@RequestBody URL url) {
		return ticketService.insertURL(url);
	}

	@GetMapping("/url")
	public List<URL> getURL() {
		return ticketService.getURL();
	}

	@PostMapping("/email")
	public ResponseEntity<?> insertEmail(@RequestBody Email email) {
		return ticketService.insertEmail(email);
	}

	@GetMapping("/email")
	public List<Email> getEmail() {
		return ticketService.getEmail();
	}

	@PostMapping("/log")
	public ResponseEntity<?> insertLog(@RequestBody Log log) {
		return ticketService.insertLog(log);
	}

	@GetMapping("/log")
	public List<Log> getLog() {
		return ticketService.getLog();
	}

	@PostMapping("/groupedBy")
	public ResponseEntity<?> insertGroupedBy(@RequestBody GroupedBy groupedBy) {
		return ticketService.insertGroupedBy(groupedBy);
	}

	@GetMapping("/groupedBy")
	public List<GroupedBy> getGroupedBy() {
		return ticketService.getGroupedBy();
	}

}