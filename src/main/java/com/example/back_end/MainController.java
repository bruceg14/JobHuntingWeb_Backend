package com.example.back_end;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

@Controller // This means that this class is a Controller
@CrossOrigin(origins = {"http://localhost:3000", "https://jobhuntingrecord.vercel.app/",
        "http://44.216.166.182/", "http://bruceguo.com", "http://www.bruceguo.com"})
@RequestMapping(path="/jobHunting")
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private JobApplicationRepository jobApplicationRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewApplication (@RequestParam String jobTitle
            , @RequestParam String company, @RequestParam String link, @RequestParam String date) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        JobApplication x = new JobApplication();
        x.setDate(date);
        x.setCompany(company);
        x.setJobTitle(jobTitle);
        x.setLink(link);
        jobApplicationRepository.save(x);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<JobApplication> getAllUsers() {
        // This returns a JSON or XML with the users
        return jobApplicationRepository.findAll();
    }

    @GetMapping(path="/getByDate")
    public @ResponseBody Iterable<JobApplication> getByDate(String date) {
        return jobApplicationRepository.findByDate(date);
    }

    @GetMapping(path="getByCompany")
    public @ResponseBody Iterable<JobApplication> getByCompany(String company) {
        Specification<JobApplication> spec = JobApplicationSpecification.companyNameContains(company);
        return jobApplicationRepository.findAll(spec);
    }

    @GetMapping(path="getHighestDate")
    public @ResponseBody List getHighestDate() {
       List<List> listOfDates = jobApplicationRepository.findDateWithMostApplications();
        if (!listOfDates.isEmpty()) {
            return listOfDates.get(0);
        } else {
            return null;
        }
    }


}
