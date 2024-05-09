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
import com.ticket.model.OtpClass;
import com.ticket.model.OtpClass.Role;
import com.ticket.model.ServiceRequest;
import com.ticket.model.ServiceRequestUpdated;
import com.ticket.model.User;
import com.ticket.repository.CompanyDetailsRepository;
import com.ticket.repository.OtpClassRepository;
import com.ticket.repository.ServiceRequestRepository;
import com.ticket.repository.ServiceRequestUpdatedRepository;
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

	public boolean status = false;

	public String sendingEmailId = "ashish.s@allatone.in";
	// public String mailCCid = "support@allatone.in";

	AuthenticationResponse authResponse;

	public User saveOwnerInfo(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return ownerEmpRepo.save(user);
	}

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
		createSR.setSupportidentifier(emp.getSupportidentifier());
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

	public User saveOwnerInfo(User empInfo, String employeeId) {
		String id = empInfo.getCompanyId();
		empInfo.setPassword(passwordEncoder.encode(empInfo.getPassword()));
		empRepo.findAll().forEach(data -> {
			if (data.getCompanyId().equals(id) || data.getRole().toString().equals("SUPERADMIN")) {
				if (data.getRole().toString().equals("ADMIN") || data.getRole().toString().equals("SUPERADMIN")) {
					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom(sendingEmailId);
					message.setTo(sendingEmailId, "support@allatone.in");
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
				}
			}
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
			if (data.getSr_no().equals(sr_no)) {
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
		String text = companyDetails.getCompanyname()+ " ,\n\n"
				+ "we have successfully registered your company\n\n"
				+ "Company information is : \n\n" + companyDetails + "\n\n";
		msg.setText(text);
		emailSender.send(msg);
		return companyDetaRepo.save(companyDetails);
	}

	public List<String> getAllBusinessImpact(String email, ServiceRequest createReq) {
		List<String> ar = new ArrayList<String>();
		//// System.out.println(email);
		ownerEmpRepo.findAll().forEach(data -> {
			String Ss = data.getServicetype().toString();
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

}
