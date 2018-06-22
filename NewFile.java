@SpringBootApplication(scanBasePackages={"com.hiaServer"})
@ComponentScan({"com.hiaServer"})
@EntityScan("com.hiaServer")
@EnableJpaRepositories("com.hiaServer")
public class HiaServApplication extends SpringBootServletInitializer {

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HiaServApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HiaServApplication.class, args);
	}
}
//====

package com.hiaServer.jobs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hiaServer.user.AppUser;

@Entity
public class JobApplication {

	/*
	 * Id
jobId = HIA1234
company {TCS, INFO}
refEmail
refType: {Strong, medium,weak}
srcShot
JobDetail
user_id
shareLink
likeCount
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String jobId;
	String company;
	String refEmail;
	String refType;
	String srcShot;
	String jobDetail;
	String shareLink;
	int count;
	
	@ManyToOne
	AppUser appUser;
	
	

	public JobApplication(Long id, String jobId, String company, String refEmail, String refType, String srcShot,
			String jobDetail, String shareLink, int count, AppUser appUser) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.company = company;
		this.refEmail = refEmail;
		this.refType = refType;
		this.srcShot = srcShot;
		this.jobDetail = jobDetail;
		this.shareLink = shareLink;
		this.count = count;
		this.appUser = appUser;
	}
	
	
	public JobApplication() {}
	

	/**
	 * @return the user
	 */
	public AppUser getUser() {
		return appUser;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(AppUser user) {
		this.appUser = user;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}
	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the refEmail
	 */
	public String getRefEmail() {
		return refEmail;
	}
	/**
	 * @param refEmail the refEmail to set
	 */
	public void setRefEmail(String refEmail) {
		this.refEmail = refEmail;
	}
	/**
	 * @return the refType
	 */
	public String getRefType() {
		return refType;
	}
	/**
	 * @param refType the refType to set
	 */
	public void setRefType(String refType) {
		this.refType = refType;
	}
	/**
	 * @return the srcShot
	 */
	public String getSrcShot() {
		return srcShot;
	}
	/**
	 * @param srcShot the srcShot to set
	 */
	public void setSrcShot(String srcShot) {
		this.srcShot = srcShot;
	}
	/**
	 * @return the jobDetail
	 */
	public String getJobDetail() {
		return jobDetail;
	}
	/**
	 * @param jobDetail the jobDetail to set
	 */
	public void setJobDetail(String jobDetail) {
		this.jobDetail = jobDetail;
	}
	/**
	 * @return the shareLink
	 */
	public String getShareLink() {
		return shareLink;
	}
	/**
	 * @param shareLink the shareLink to set
	 */
	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
//======


package com.hiaServer.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobApi")
public class JobsController {

	/*
	 * Create job
	 * Update/edit job
	 * delete job with id
	 * 
	 * getAllJobByUserId
	 * getJobsWithOffset
	 * @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
	 * 
	 */
	@Autowired
	JobsService jobService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/jobs")
	public void getAllJobs(@RequestBody JobApplication job) {
				jobService.createJobs(job);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/jobs")
	public ResponseEntity<List<JobApplication>> addUser1() {
		List<JobApplication> list = jobService.getAllJobs();
		 if (list.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<JobApplication>>(list, HttpStatus.OK);
	        
		
	} 
	
	@RequestMapping(method = RequestMethod.PUT, value = "/jobs")
	public void updateJob(@RequestBody JobApplication job) {
		jobService.updateJob(job);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/jobs/{id}")
	public void deleteJob(@PathVariable Long id) {
		jobService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}/jobs")
	public List<JobApplication> getAllJobWithUser(@PathVariable Long id) {
		return jobService.getJobByUserId(id);	
	}
	
	public void getAllJobWithOffset() {
		
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "/comments")
//	public void addComment(@RequestBody JobComments comment) {
//		jobService.createJobs(job);
//	}
	
	
	
	
}
//===

package com.hiaServer.locality;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Area {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String areaName;
	String areaDesc;
	
	// 1 = ITPark, 2= Area
	Integer areaType; 
	
	//city
	@ManyToOne
	City city;

	
	
	public Area() {};
	
	public Area(Long id, String areaName, String desc, Integer areaType, City city) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.areaDesc = desc;
		this.areaType = areaType;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDesc() {
		return areaDesc;
	}

	public void setDesc(String desc) {
		this.areaDesc = desc;
	}

	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	
	
}
//==

package com.hiaServer.locality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hiaServer.user.CityRepo;

@Controller
public class AreaController {

	@Autowired
	AreaRepo areaRepo;
	

	@Autowired
	CityRepo cityRepo;
	
    @GetMapping("/getForm")
    public String getForm() {
    	
        return "areaForm";
    }
    
//    @PostMapping("") 
    @RequestMapping(value = "/savearea", method = RequestMethod.POST)
    public ModelAndView saveDetails(@ModelAttribute Area area) {

    	areaRepo.save(area);

    	ModelAndView model = new ModelAndView();
    	model.addObject("areas", areaRepo.findAll());
    	model.setViewName("indexArea");
    	
    	
        return model;         
    }
    
	
    @GetMapping("/area-list")
    public ModelAndView showAreaDetails() {
    	ModelAndView model = new ModelAndView();
    	model.addObject("areas", areaRepo.findAll());
    	model.setViewName("indexArea");
    	return model;
    }
    

    @GetMapping("/create-city")
    public String getCityForm() {
        return "cityForm";
    }
    

    @PostMapping("/save-city")
    public ModelAndView saveCity(@ModelAttribute City city) {
    	cityRepo.save(city);
    	
    	
    	ModelAndView model = new ModelAndView();
    	model.addObject("cities", cityRepo.findAll());
    	model.setViewName("indexCity");
    	
        return model;
    }
}
//=======



package com.hiaServer.locality;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String cityName;
	String cityDesc;
	
	
	public City() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getCityDesc() {
		return cityDesc;
	}


	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}


	public City(Long id, String cityName, String cityDesc) {
		super();
		this.id = id;
		this.cityName = cityName;
		this.cityDesc = cityDesc;
	}
	

	
	
}
//==


package com.hiaServer.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class AppUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String emailIdc;
	
	String emailIdP;
	
	String token;
	
	String userName;
	
	String user_img;
	
	String user_lat;
	
	String user_lng;
	
	String resume_src;
	
	String appId;
	
	String password;
	
	
	/*
	 * emailidc
	 * emaildp
	 * token
	 * it_park_it*
	 * area_id*
	 * user_img
	 * user_lat
	 * user_lng
	 * resume_src
	 * 
	 */
	String emailId;
	

	
	public AppUser() {}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getEmailIdc() {
		return emailIdc;
	}



	public void setEmailIdc(String emailIdc) {
		this.emailIdc = emailIdc;
	}



	public String getEmailIdP() {
		return emailIdP;
	}



	public void setEmailIdP(String emailIdP) {
		this.emailIdP = emailIdP;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUser_img() {
		return user_img;
	}



	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}



	public String getUser_lat() {
		return user_lat;
	}



	public void setUser_lat(String user_lat) {
		this.user_lat = user_lat;
	}



	public String getUser_lng() {
		return user_lng;
	}



	public void setUser_lng(String user_lng) {
		this.user_lng = user_lng;
	}



	public String getResume_src() {
		return resume_src;
	}



	public void setResume_src(String resume_src) {
		this.resume_src = resume_src;
	}



	public String getAppId() {
		return appId;
	}



	public void setAppId(String appId) {
		this.appId = appId;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public AppUser(Long id, String emailIdc, String emailIdP, String token, String userName, String user_img,
			String user_lat, String user_lng, String resume_src, String appId, String password, String emailId) {
		super();
		this.id = id;
		this.emailIdc = emailIdc;
		this.emailIdP = emailIdP;
		this.token = token;
		this.userName = userName;
		this.user_img = user_img;
		this.user_lat = user_lat;
		this.user_lng = user_lng;
		this.resume_src = resume_src;
		this.appId = appId;
		this.password = password;
		this.emailId = emailId;
	}


	
	
}
//==
package com.hiaServer.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hiaServer.hiaServ.Response;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, value="/users"  )
	public Response addUser(@RequestBody @Valid  AppUser user) {
	    	
		AppUser user1 = userService.createUser(user);
		return new Response(user1.token);
		 
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/users"  )
	public List<AppUser> addUser1() {
		return userService.getAllUser();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/users/{paramId}")
	public AppUser getUser(@PathVariable Long paramId) {	
		return  userService.getUser(paramId);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/users"  )
	public void updateUser(@RequestBody @Valid  AppUser user) {
		userService.createUser(user);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/app-login/{email}/{password}"  )
	public List<AppUser> appLogin(@PathVariable Long email, @PathVariable Long password) {
		
		
		return userService.getAllUser();
	}
	
}
//==

package com.hiaServer.user;

import org.springframework.data.repository.CrudRepository;



public interface UserRepo extends CrudRepository<AppUser, Long> {
	
	public AppUser findByEmailId(String email);
	
//	   List<Article> findByTitle(String title);
//	    List<Article> findDistinctByCategory(String category);
//	    List<Article> findByTitleAndCategory(String title, String category);
//
//	    @Query("SELECT a FROM Article a WHERE a.title=:title and a.category=:category")
//	    List<Article> fetchArticles(@Param("title") String title, @Param("category") String category);
	
}
//===

package com.hiaServer.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	public AppUser  createUser(AppUser user) {
		
		if(!isUserExists(user.emailId)) {
			user.token = genrateUID();
		}
	    
		return  userRepo.save(user);
	}
	
	public boolean isUserExists(String emailId) {
		
		AppUser user = userRepo.findByEmailId(emailId);
		if(user!=null) {
			return true;
		}
		
		return false;
	}
	
	public List<AppUser> getAllUser() {
		List<AppUser> list = new ArrayList<>();
		userRepo.findAll().forEach(list::add);
		return list;
	}
	
	public AppUser getUser(Long id) {
		return  userRepo.findOne(id);
	}
	
	public String genrateUID() {
		UUID idOne = UUID.randomUUID();
		return idOne.toString();
	}
}
//==
