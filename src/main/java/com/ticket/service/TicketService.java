package com.ticket.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ticket.model.AuthenticationResponse;
import com.ticket.model.CompanyDetails;
import com.ticket.model.Customer;
import com.ticket.model.Email;
import com.ticket.model.GroupedBy;
import com.ticket.model.Items;
import com.ticket.model.Log;
import com.ticket.model.Message;
import com.ticket.model.OtpClass;
import com.ticket.model.OtpClass.Role;
import com.ticket.model.PurchaseInvoice;
import com.ticket.model.PurchaseOrder;
import com.ticket.model.Sales;
import com.ticket.model.SalesInvoice;
import com.ticket.model.ServiceRequest;
import com.ticket.model.ServiceRequestUpdated;
import com.ticket.model.URL;
import com.ticket.model.User;
import com.ticket.repository.CompanyDetailsRepository;
import com.ticket.repository.CustomerRepository;
import com.ticket.repository.EmailRepository;
import com.ticket.repository.GroupedByRepository;
import com.ticket.repository.ItemsRepository;
import com.ticket.repository.LogRepository;
import com.ticket.repository.OtpClassRepository;
import com.ticket.repository.PurchaseInvoiceRepository;
import com.ticket.repository.PurchaseOrderRepository;
import com.ticket.repository.SalesInvoiceRepository;
import com.ticket.repository.SalesRepository;
import com.ticket.repository.ServiceRequestRepository;
import com.ticket.repository.ServiceRequestUpdatedRepository;
import com.ticket.repository.URLRepository;
import com.ticket.repository.UserRepository;

@Service
public class TicketService {

	private static final Role ADMIN = null;

	@Autowired
	private UserRepository ownerEmpRepo;

	@Autowired
	private OtpClassRepository otpRepo;

	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private CompanyDetailsRepository companyDetaRepo;

	@Autowired
	private ServiceRequestRepository serviceReqRepo;

	@Autowired
	private ServiceRequestUpdatedRepository serviceRequestUpdatedRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository empRepo;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private ItemsRepository itemsRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepo;

	@Autowired
	private PurchaseInvoiceRepository purchaseInvoiceRepo;

	@Autowired
	private SalesRepository salesRepo;

	@Autowired
	private SalesInvoiceRepository salesInvoiceRepo;

	@Autowired
	private URLRepository urlRepo;

	@Autowired
	private GroupedByRepository groupedByRepo;

	@Autowired
	private EmailRepository emailRepo;

	@Autowired
	private LogRepository logRepo;

	public boolean status = false;

	public String sendingEmailId = "ashish.s@allatone.in";
	// public String mailCCid = "support@allatone.in";

	AuthenticationResponse authResponse;

//	public User saveOwnerInfo(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		return ownerEmpRepo.save(user);
//	}

	public List<User> getAllTickets(String companyId, String role) {
		List<User> ownerInfoList = new ArrayList<User>();
		if (role.equals("SUPERADMIN") || role.equals("SUPERUSER") || role.equals("SUPERMANAGER")) {
			ownerEmpRepo.findAll().forEach(data -> {
				ownerInfoList.add(data);
			});
		} else if (role.equals("ADMIN") || role.equals("USER") || role.equals("MANAGER")) {
			ownerEmpRepo.findAll().forEach(data -> {
				if ((data.getCompanyId().equals(companyId))) {
					ownerInfoList.add(data);
				}
			});
		}
		return ownerInfoList;
	}

	public List<CompanyDetails> getAllDetails() {
		List<CompanyDetails> companyList = new ArrayList<CompanyDetails>();
		companyDetaRepo.findAll().forEach(data -> {
			companyList.add(data);
		});
		return companyList;
	}

	public String updateById(User user) {
		ownerEmpRepo.save(user);
		return "update succesfully";
	}

	public ServiceRequest saveCreateRequest(ServiceRequest createSR, String email) {
		User emp = ownerEmpRepo.findByEmail(email);
		// createSR.setSupportidentifier(emp.getSupportidentifier()); // change it
		createSR.setContact(emp.getContact());
		// List<String> allEmails = getAllBusinessImpact(createSR.getBusinessimpact(),
		// createSR);
		// ////System.out.println(allEmails);
		return serviceReqRepo.save(createSR);
	}

	public Optional<ServiceRequest> findby(Integer id) {
		return serviceReqRepo.findById(id);
	}

	public List<ServiceRequest> getAllCreate(String companyId, String supportId, String role) {
		List<ServiceRequest> requestList = new ArrayList<ServiceRequest>();
		if (role.equals("SUPERADMIN") || role.equals("SUPERUSER") || role.equals("SUPERMANAGER")) {
			serviceReqRepo.findAll().forEach(data -> {
				requestList.add(data);
			});
		} else {
			if ((role.equals("ADMIN") || role.equals("USER") || role.equals("MANAGER"))) {
				serviceReqRepo.findAll().forEach(data -> {
					if ((data.getCompanyId().equals(companyId))) {
						requestList.add(data);
//						&& (data.getSupportidentifier().equals(supportId)
					}
				});
			}
		}
		return requestList;
	}

	public User saveEmployeeInfo(User user) {

		return ownerEmpRepo.save(user);
	}

	public String updateById(CompanyDetails companyDetails) {
		companyDetaRepo.save(companyDetails);
		return "update succesfully";
	}

	public String deleteby(Integer id) {
		companyDetaRepo.deleteById(id);
		return "delete succefull";
	}

	public String Deleteby(Integer id) {
		serviceReqRepo.deleteById(id);
		return " Succefully Delete";
	}

	public Optional<ServiceRequest> updateby(Integer id, ServiceRequest serviceRequest) {
		serviceReqRepo.save(serviceRequest);
		// ////System.out.println(serviceRequest);
		return Optional.empty();
	}

	public User saveOwnerInfo(User empInfo) {
		String id = empInfo.getCompanyId();
		System.out.println(id);
		empInfo.setPassword(passwordEncoder.encode(empInfo.getPassword()));
		empRepo.findAll().forEach(data -> {
//			if (data.getCompanyId().equals(id) || data.getRole().toString().equals("SUPERADMIN")) {
//				if (data.getRole().toString().equals("ADMIN") || data.getRole().toString().equals("SUPERADMIN")) {
					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom(sendingEmailId);
					message.setTo(sendingEmailId);
					message.setSubject("User Account created successfully.");
					String text = "Dear User/ Admin, \n\n" + "Your account is created successfully at "
							+ new Timestamp(Instant.now().toEpochMilli()) + " time." + " Account Details:- \n\n "
							+ "User Name is: " + empInfo.getUsername() + " and Role is: " + empInfo.getRole().toString()
							+ "\n\n"
							+ "For any further query/assistence please contact with Admin or Allatone Solutions Private Limited \n\n"
							+ "Best regards,\n\n" + "Allatone Solutions Private Limited\n"
							+ "Email:- support@allatone.in";
					message.setText(text);
					emailSender.send(message);
//				}
//			}
		});
		return ownerEmpRepo.save(empInfo);
	}

	public ServiceRequestUpdated saveServiceReq(ServiceRequestUpdated serviceRequestUpdated) {
		return serviceRequestUpdatedRepo.save(serviceRequestUpdated);
	}

	public List<ServiceRequestUpdated> getAllRequest(String sr_no) {
		List<ServiceRequestUpdated> updateList = new ArrayList<ServiceRequestUpdated>();
		serviceRequestUpdatedRepo.findAll().forEach(data -> {
			//// System.out.println(data.getSr_no());
			if (data.getServiceRequestNumber().equals(sr_no)) {
				updateList.add(data);
			}
		});
		return updateList;
	}

	public Optional<ServiceRequest> findby1(Integer id) {
		return serviceReqRepo.findById(id);
	}

	public ServiceRequest saveCreateReq(ServiceRequest serviceRequest) {
		//// System.out.println(serviceRequest.getLastupdatedby());
		return serviceReqRepo.save(serviceRequest);
	}

	public CompanyDetails saveCompanyDet(CompanyDetails companyDetails) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(sendingEmailId);
		msg.setTo("support@allatone.in");
		msg.setSubject("Company registered!");
		String text = "we have successfully registered your company\n\n" + "Company information is : \n\n"
				+ companyDetails + "\n\n";
		msg.setText(text);
		emailSender.send(msg);
		return companyDetaRepo.save(companyDetails);
	}

	public List<String> getAllBusinessImpact(String email, ServiceRequest createReq) {
		List<String> ar = new ArrayList<String>();
		//// System.out.println(email);
		ownerEmpRepo.findAll().forEach(data -> {
			String Ss = data.getServiceType().toString();
			if (Ss.equals(email)) {
				ar.add(data.getEmail());
				SimpleMailMessage msg = new SimpleMailMessage();
				msg.setFrom(sendingEmailId);
				msg.setTo(data.getEmail());
				msg.setSubject("Ticket Raise");

				String text = "Hi Team,\n\n"
						+ "we have got a new ticket, please find the ticket and work on immediately.\n\n"
						+ "Ticket information is : \n\n" + createReq + "\n\n";
				msg.setText(text);
				emailSender.send(msg);
			}
		});
		return ar;
	}

	public void sendOTP(String userName, String email, Timestamp timestamp, String role) {
		////// System.out.println(email);
		Optional<OtpClass> userInfo = otpRepo.findByEmail(email);
		if (!userInfo.toString().equals("Optional.empty")) {
			String userId = userInfo.get().getId().toString();
			removeRecord(userId);
		}
		Random random = new Random();
		int minRange = 100000; // Minimum range (inclusive)
		int maxRange = 999999; // Maximum range (inclusive)
		int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
		System.out.println("Generated random number: " + randomNumber);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(sendingEmailId);
		msg.setTo(email);
		// msg.setCc(mailCCid);
		msg.setSubject("[Allatone Solutions] Please verify your account\r\n");
		String message = "Hey " + userName + "\n\n"
				+ "A sign in attempt requires further verification because we did not recognize your account. To complete the sign in,"
				+ " enter the verification code on the unrecognized device.\n\n" + "Your OTP is " + randomNumber
				+ " and time is " + new Timestamp(Instant.now().toEpochMilli()) + "\n\n "
				+ "Don't share OTP with anyone for security purpose.\n\n"
				+ "If you have any questions or require further assistance regarding the uploaded file, please do not hesitate to contact us."
				+ " Our team is ready to provide any support or clarification you may need. \n\n" + "Best regards,\n\n"
				+ "Allatone Solutions Pvt. Ltd. \n" + "Email:- support@allatone.in";
		msg.setText(message);
		OtpClass otpObj = new OtpClass();
		otpObj.setUserName(userName);
		otpObj.setOtp(Integer.toString(randomNumber));
		otpObj.setUseremail(email);
		otpObj.setRole(ADMIN);
		otpObj.setTimestamp(timestamp);
		otpRepo.save(otpObj);
		emailSender.send(msg);
	}

	public void removeRecord(String userId) {
		otpRepo.deleteById(userId);
	}

	public ResponseEntity<?> validateOtp(OtpClass otp) {
		String userEmail = otp.getEmail();
		String userName = otp.getUserName();
		String mailOtp = otp.getOtp();
		System.out.println(userEmail + "  " + mailOtp + " " + userName);
		Optional<OtpClass> userInfo = otpRepo.findByEmail(userEmail);
		System.out.println(userInfo);
		Optional<User> dbUser = Optional.ofNullable(ownerEmpRepo.findByUserName(userName));
		System.out.println(dbUser);
		User userObject = dbUser.get();
		String jwtToken = jwtService.generateToken(userObject);
		AuthenticationResponse authResponse = new AuthenticationResponse(userObject, jwtToken);
		String dbOtp = userInfo.get().getOtp();
		String userId = userInfo.get().getId().toString();
		if (mailOtp.equals(dbOtp)) {

			removeRecord(userId);
			return ResponseEntity.accepted().body(authResponse);
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

	public ResponseEntity<?> validateCompanyId(String companyId) {
		CompanyDetails companyInfo = companyDetaRepo.findByCompanyId(companyId);
		return ResponseEntity.accepted().body(companyInfo);
	}

	public Optional<User> updateuserby(String userName, User employeeInfo) {
		ownerEmpRepo.save(employeeInfo);
		return Optional.empty();
	}

	public Optional<User> finduserby(String userName) {
		return Optional.ofNullable(ownerEmpRepo.findByUserName(userName));
	}

	public List<CompanyDetails> getAllCompanys() {
		List<CompanyDetails> companyList = new ArrayList<CompanyDetails>();
		companyDetaRepo.findAll().forEach(data -> {
			companyList.add(data);
		});
		return companyList;
	}

	public ArrayList<String> getAllUserName() {
		ArrayList<String> userName = new ArrayList<String>();
		ownerEmpRepo.findAll().forEach(data -> {
			userName.add(data.getUserName() + "(" + data.getName() + ")");
		});
		return userName;
	}

	public ResponseEntity<?> insertItems(Items item) {
		Message msg = new Message();
		try {
			itemsRepo.save(item);
			msg.setMessage("Insert record successfully.");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<Items> getItems() {
		Message msg = new Message();
		try {
			List<Items> items = (List<Items>) itemsRepo.findAll();
			return items;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public Items getItemsByName(String name) {
		Message msg = new Message();
		try {
			return itemsRepo.findByname(name);
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertCustomer(Customer customer) {
		Message msg = new Message();
		try {
			customerRepo.save(customer);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<Customer> getCustomer() {
		Message msg = new Message();
		try {
			List<Customer> customer = (List<Customer>) customerRepo.findAll();
			return customer;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public Customer getItemsByCompanyName(String name) {
		Message msg = new Message();
		try {
			return customerRepo.findBycompanyName(name);
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertPurchaseOrder(PurchaseOrder purchaseOrder) {
		Message msg = new Message();
		try {
			System.out.println(purchaseOrder);
//			PurchaseOrder p = new PurchaseOrder();
//			p.setCompanyName(purchaseOrder.getCompanyName());
//			p.setItemName(purchaseOrder.getItemName());
//			p.setStartDate(purchaseOrder.getStartDate());
//			p.setiAmount(purchaseOrder.getiAmount());
//			p.setQuantity(purchaseOrder.getQuantity());
//			p.setAmount(purchaseOrder.getAmount());
//			p.setEndDate(purchaseOrder.getEndDate());
//			p.setDescription(purchaseOrder.getDescription());
//			p.setItemcategory(purchaseOrder.getItemcategory());
			String companyName = purchaseOrder.getCompanyName();
			System.out.println(companyName);
			Customer companyInfo = customerRepo.findBycompanyName(companyName);
			System.out.println(companyInfo);
//			String state = companyInfo.getState();
//			System.out.println(state);
//			long amount = Long.parseLong(purchaseOrder.getAmount());
//			String bamt = purchaseOrder.getBaseAmount();
//			System.out.println(bamt);
//			if ((bamt.length() < 2) || (bamt.equals(null))) {
//				System.out.println("I am in check base amount");
//				p.setBaseAmount(purchaseOrder.getiAmount());
//			}
//			Long bAmount = Long.parseLong(p.getBaseAmount()) - amount;
//			if (state.equals("Delhi")) {
//				System.out.println("I am in state");
//				long gst = (((bAmount * 18) / 100));
//				p.setIgst(String.valueOf(gst));
//				p.setTotalValue(String.valueOf(bAmount + gst));
//			} else {
//				long gst = (((bAmount * 9) / 100));
//				p.setCgst(String.valueOf(gst));
//				p.setSgst(String.valueOf(gst));
//				p.setTotalValue(String.valueOf(bAmount + gst + gst));
//			}
//			p.setBaseAmount(String.valueOf(bAmount));
//			purchaseOrderRepo.save(p);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<PurchaseOrder> getPurchaseOrder() {
		Message msg = new Message();
		try {
			List<PurchaseOrder> customer = (List<PurchaseOrder>) purchaseOrderRepo.findAll();
			return customer;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public PurchaseOrder getPurchaseOrderByponumber(String ponumber) {
		Message msg = new Message();
		try {
			return purchaseOrderRepo.findByponumber(ponumber);
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertSales(Sales sales) {
		Message msg = new Message();
		try {
			salesRepo.save(sales);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<Sales> getSales() {
		Message msg = new Message();
		try {
			List<Sales> customer = (List<Sales>) salesRepo.findAll();
			return customer;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public Sales getSalesByponumber(String ponumber) {
		Message msg = new Message();
		try {
			return salesRepo.findByponumber(ponumber);
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		Message msg = new Message();
		try {
			purchaseInvoiceRepo.save(purchaseInvoice);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<PurchaseInvoice> getPurchaseInvoice() {
		Message msg = new Message();
		try {
			List<PurchaseInvoice> customer = (List<PurchaseInvoice>) purchaseInvoiceRepo.findAll();
			return customer;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public PurchaseInvoice getPurchaseInvoiceByponumber(String ponumber) {
		Message msg = new Message();
		try {
			return purchaseInvoiceRepo.findByponumber(ponumber);
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertSalesInvoice(SalesInvoice salesInvoice) {
		Message msg = new Message();
		try {
			salesInvoiceRepo.save(salesInvoice);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<SalesInvoice> getSalesInvoice() {
		Message msg = new Message();
		try {
			List<SalesInvoice> saleInfo = (List<SalesInvoice>) salesInvoiceRepo.findAll();
			return saleInfo;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public SalesInvoice getsalesInvoiceByponumber(String ponumber) {
		Message msg = new Message();
		try {
			return salesInvoiceRepo.findByponumber(ponumber);
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertURL(URL url) {
		Message msg = new Message();
		try {
			urlRepo.save(url);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<URL> getURL() {
		Message msg = new Message();
		try {
			List<URL> url = (List<URL>) urlRepo.findAll();
			return url;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertEmail(Email email) {
		Message msg = new Message();
		try {
			emailRepo.save(email);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<Email> getEmail() {
		Message msg = new Message();
		try {
			List<Email> email = (List<Email>) emailRepo.findAll();
			return email;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertLog(Log log) {
		Message msg = new Message();
		try {
			logRepo.save(log);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<Log> getLog() {
		Message msg = new Message();
		try {
			List<Log> tag = (List<Log>) logRepo.findAll();
			return tag;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public ResponseEntity<?> insertGroupedBy(GroupedBy groupedBy) {
		Message msg = new Message();
		try {
			groupedByRepo.save(groupedBy);
			msg.setMessage("Insert record successfully.");
			msg.setReturnCode("200");
		} catch (Exception e) {
			msg.setMessage("Insertion failed.");
			msg.setReturnCode("501");
			return ResponseEntity.badRequest().body(msg);
		}
		return ResponseEntity.ok(msg);
	}

	public List<GroupedBy> getGroupedBy() {
		Message msg = new Message();
		try {
			List<GroupedBy> tag = (List<GroupedBy>) groupedByRepo.findAll();
			return tag;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	public List<Email> getEmailByName(String groupName) {
		Message msg = new Message();
		try {
			List<Email> tag = emailRepo.findBygroupedBy(groupName);
			return tag;
		} catch (Exception e) {
			msg.setMessage("Getting record failed.");
			msg.setReturnCode("501");
			return null;
		}
	}

	// API monitoring application

//	private static final Logger logger = LoggerFactory.getLogger(Service.class);
//	private final RestTemplate restTemplate = new RestTemplate();
//
//	@Scheduled(fixedRate = 1200000) // Check every 10 minutes
//	public void monitorApis() {
//		List<URL> urls = (List<URL>) urlRepo.findAll();
//		if (urls.isEmpty()) {
//			logger.info("No URLs to monitor.");
//		} else {
//			System.out.println(urls.size());
//			for (URL url : urls) {
//				checkApi(url.getUrl(), url.getGroupedBy(), url.getfNameUrl());
//			}
//		}
//	}
//
//	
//	private void checkApi(String url, String groupName, String fNameUrl) {
//	    List<Email> data = getEmailByName(groupName);
//	    HashMap<String, String> userEmailName = new HashMap<>();
//	    data.forEach(record -> {
//	        userEmailName.put(record.getUserName(), record.getUserEmail());
//	    });
//
//	    Log lg = new Log();
//	    Log lgdb = logRepo.findByurl(url);
//
//	    try {
//	        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//	        if (response.getStatusCode().is2xxSuccessful()) {
//	            String str = String.format("API %s is up. Status code: %s", fNameUrl, response.getStatusCode());
//
//	            if (lgdb == null) {
//	                lg.setLog(str);
//	                lg.setUrl(url);
//	                lg.setStatus("UP");
//	                lg.setGroupedBy(groupName);
//	                lg.setEndTime(LocalDateTime.now());
//	                logRepo.save(lg);
//	            } else {
//	                updateLogEntry(lg, lgdb, str, url, "UP", groupName);
//	            }
//
//	            logger.info("API {} is up. Status code: {}", fNameUrl, response.getStatusCode());
//	        } else {
//	            handleNon2xxStatusCode(url, groupName, fNameUrl, lgdb, userEmailName, response.getStatusCode().toString());
//	        }
//	    } catch (Exception e) {
//	        handleException(url, groupName, fNameUrl, lgdb, userEmailName, e);
//	    }
//	}
//
//	private void updateLogEntry(Log lg, Log lgdb, String logMessage, String url, String status, String groupName) {
//	    lg.setId(lgdb.getId());
//	    lg.setUrl(lgdb.getUrl());
//	    lg.setStartTime(lgdb.getStartTime());
//	    lg.setGroupedBy(lgdb.getGroupedBy());
//	    lg.setStatus(status);
//	    lg.setLog(logMessage);
//	    lg.setEndTime(LocalDateTime.now());
//	    
//	    Duration downtimeDuration = Duration.between(lgdb.getStartTime(), LocalDateTime.now());
//	    String durationMessage = formatDuration(downtimeDuration);
//	    lg.setDuration(durationMessage);
//
//	    logRepo.save(lg);
//	}
//
//	private String formatDuration(Duration duration) {
//	    long totalMinutes = duration.toMinutes();
//	    final int MINUTES_IN_AN_HOUR = 60;
//	    final int MINUTES_IN_A_DAY = 24 * MINUTES_IN_AN_HOUR;
//	    
//	    long days = totalMinutes / MINUTES_IN_A_DAY;
//	    long remainingMinutesAfterDays = totalMinutes % MINUTES_IN_A_DAY;
//	    long hours = remainingMinutesAfterDays / MINUTES_IN_AN_HOUR;
//	    long minutes = remainingMinutesAfterDays % MINUTES_IN_AN_HOUR;
//
//	    return days + " days, " + hours + " hours, and " + minutes + " minutes.";
//	}
//
//	private void handleNon2xxStatusCode(String url, String groupName, String fNameUrl, Log lgdb, HashMap<String, String> userEmailName, String statusCode) {
//	    String warning = String.format("API %s returned non-200 status code: %s", fNameUrl, statusCode);
//	    logger.warn(warning);
//
//	    if (lgdb == null) {
//	        Log lg = new Log();
//	        lg.setLog(warning);
//	        lg.setUrl(url);
//	        lg.setGroupedBy(groupName);
//	        lg.setStatus("WARN");
//	        lg.setEndTime(LocalDateTime.now());
//	        logRepo.save(lg);
//	    } else {
//	        updateLogEntry(lgdb, lgdb, warning, url, "WARN", groupName);
//	    }
//
//	    userEmailName.forEach((userName, userEmail) -> {
//	        sendAlert(warning, userName, userEmail, fNameUrl, groupName);
//	    });
//	}
//
//	private void handleException(String url, String groupName, String fNameUrl, Log lgdb, HashMap<String, String> userEmailName, Exception e) {
//	    String error = String.format("API %s request failed", fNameUrl);
//	    logger.error(error, e);
//
//	    if (lgdb == null) {
//	        Log lg = new Log();
//	        lg.setLog(error);
//	        lg.setUrl(url);
//	        lg.setGroupedBy(groupName);
//	        lg.setStatus("DOWN");
//	        lg.setEndTime(LocalDateTime.now());
//	        logRepo.save(lg);
//	    } else {
//	        updateLogEntry(lgdb, lgdb, error, url, "DOWN", groupName);
//	    }
//
//	    String durationMessage = formatDuration(Duration.between(lgdb.getStartTime(), LocalDateTime.now()));
//	    String msg = String.format("%s %s is down for %s minutes\nCause: %s", groupName, fNameUrl, durationMessage, e.getMessage());
//
//	    userEmailName.forEach((userName, userEmail) -> {
//	        sendAlert(msg, userName, userEmail, fNameUrl, groupName);
//	    });
//	}
//
//	
//	private void sendAlert(String message, String userName, String userEmail, String fNameUrl, String groupName) {
//		String subject = "Adani " + groupName + " " + fNameUrl + " Down.";
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setTo(userEmail);
//		mailMessage.setSubject(subject);
//		mailMessage.setText(message);
//	//	emailSender.send(mailMessage);
//	}
}
