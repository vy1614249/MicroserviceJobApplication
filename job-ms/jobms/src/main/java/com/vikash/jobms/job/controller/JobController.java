package com.vikash.jobms.job.controller;

import com.vikash.jobms.job.dto.JobRequestDTO;
import com.vikash.jobms.job.dto.JobWithCompanyDTO;
import com.vikash.jobms.job.model.Job;
import com.vikash.jobms.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/allJobs")
    public List<JobWithCompanyDTO> findAll(){
        return jobService.allJobs();
    }

    @PostMapping("/createJob")
    public String createJob(@RequestBody @Validated JobRequestDTO job){

        return jobService.createJob(job);

    }

    @PutMapping("/updateJob/{id}")
    public String updateJob(@PathVariable Long id,@RequestBody @Validated JobRequestDTO job){
        return jobService.updateJob(id,job);

    }

    @DeleteMapping("/deleteJobById/{id}")
    public String deleteJobById(@PathVariable Long id){
        return jobService.deleteJob(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable Long id){
        JobWithCompanyDTO job=jobService.findById(id);
        if(job!=null){
            return new ResponseEntity<>(job,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobWithCompanyDTO>> jobsByCompany(@PathVariable Long companyId){
        List<JobWithCompanyDTO> jobList=jobService.jobByCompany(companyId);
        if(!jobList.isEmpty()) return new ResponseEntity<>(jobList,HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/company/name/{companyName}")
    public ResponseEntity<List<Job>> jobsByCompanyName(@PathVariable String name){
        List<Job> jobList=jobService.findJobByCompanyName(name);
        if(!jobList.isEmpty()) return new ResponseEntity<>(jobList,HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/isvalid/{id}")
    public boolean isJobWithIdValid(@PathVariable Long id){
        return jobService.isJobWithIdValid(id);
    }

}
