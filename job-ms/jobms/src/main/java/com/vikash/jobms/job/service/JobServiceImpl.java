package com.vikash.jobms.job.service;

import com.vikash.jobms.job.client.CompanyClient;
import com.vikash.jobms.job.dto.JobRequestDTO;
import com.vikash.jobms.job.dto.JobWithCompanyDTO;
import com.vikash.jobms.job.exception.CompanyNotFoundException;
import com.vikash.jobms.job.external.Company;
import com.vikash.jobms.job.model.Job;
import com.vikash.jobms.job.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyClient companyClient;

    @Override
    public List<JobWithCompanyDTO> allJobs() {
        List<Job> jobList= jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOList;

        if(!jobList.isEmpty()){
            jobWithCompanyDTOList=new ArrayList<>();
                for(Job existingJob:jobList){
//                Company company=restTemplate.getForObject(
//                        "http://companyms/company/getCompany/"+existingJob.getCompanyId(),
//                        Company.class);
                Optional<Company> companyOpt=companyClient.getCompanyById(existingJob.getCompanyId());
                if(companyOpt.isPresent()){
                    Company company=companyOpt.get();
                    JobWithCompanyDTO jobWithCompanyDTO=
                            new JobWithCompanyDTO();
                    jobWithCompanyDTO.setJob(existingJob);
                    jobWithCompanyDTO.setCompany(company);
                    jobWithCompanyDTOList.add(jobWithCompanyDTO);
                }
            }
            return jobWithCompanyDTOList;
        }else{
            return null;
        }
    }

    @Override
    public List<JobWithCompanyDTO> jobByCompany(Long companyId) {
        List<Job> jobList= jobRepository.findByCompanyId(companyId);
        List<JobWithCompanyDTO> jobWithCompanyDTOList;

        if(!jobList.isEmpty()){
            jobWithCompanyDTOList=new ArrayList<>();
            for(Job existingJob:jobList){
                Company company=restTemplate.getForObject(
                        "http://companyms/company/getCompany/"+existingJob.getCompanyId(),
                        Company.class);
                JobWithCompanyDTO jobWithCompanyDTO=
                        new JobWithCompanyDTO();
                jobWithCompanyDTO.setJob(existingJob);
                jobWithCompanyDTO.setCompany(company);
                jobWithCompanyDTOList.add(jobWithCompanyDTO);
            }
            return jobWithCompanyDTOList;
        }else{
            return null;
        }

    }

    @Override
    public JobWithCompanyDTO findById(Long id) {
        Optional<Job> job =jobRepository.findById(id);
        if(job.isPresent()){
            Job existingJob=job.get();
//            Company company=restTemplate.getForObject(
//                    "http://companyms:8082/company/getCompany/"+existingJob.getCompanyId(),
//                    Company.class);

            Optional<Company> companyOpt=companyClient.getCompanyById(existingJob.getCompanyId());
            if (companyOpt.isPresent()){
                Company company=companyOpt.get();
                JobWithCompanyDTO jobWithCompanyDTO=
                        new JobWithCompanyDTO();
                jobWithCompanyDTO.setJob(existingJob);
                jobWithCompanyDTO.setCompany(company);
                return jobWithCompanyDTO;
            }
        }
        return null;
    }

    @Override
    public String createJob(JobRequestDTO job) {
        Job job1=modelMapper.map(job,Job.class);
        Company company=restTemplate.getForObject(
                "http://companyms/company/getCompany/"+job.getCompanyId(),
                Company.class);
        logger.info(" company id: {}",company.getId());
        if (company==null){
            throw new CompanyNotFoundException("Company with id "+job.getCompanyId()+" does not exist!");
        }
        try{
            job1.setId(null);
            jobRepository.save(job1);
            return "Job created successfully!";
        } catch (Exception e) {
            logger.error("Failed to save job {}", e.getMessage(), e);
            return "Something went wrong!";
        }

    }

    @Override
    public String deleteJob(Long id) {
        Optional<Job> job=jobRepository.findById(id);
        if(job.isPresent()){
            jobRepository.delete(job.get());
            return "Job deleted!";
        }else {
            throw new CompanyNotFoundException("Company with id "+id+" does not exist!");
        }
    }

    @Override
    @Transactional
    public String updateJob(Long id, JobRequestDTO job1) {
        Job job=modelMapper.map(job1,Job.class);
        Optional<Job> jobExist = jobRepository.findById(id);
        if (jobExist.isPresent()) {
            Job existingJob = jobExist.get();
            existingJob.setCompanyId(job.getCompanyId());
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setMaxSalary(job.getMaxSalary());
            existingJob.setMinSalary(job.getMinSalary());

            try {
                jobRepository.save(existingJob);
                return "Job updated successfully!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Something went wrong while updating!";
            }
        } else {
            throw new CompanyNotFoundException("Company with id "+id+" does not exist!");
        }
    }

    @Override
    public List<Job> findJobByCompanyName(String name) {
        Company company=restTemplate
                .getForObject("http://companyms/company/getCompanyByName/"+name,
                        Company.class);
        if(company!=null){
            Long companyId= company.getId();
            List<Job> jobList=jobRepository.findByCompanyId(companyId);
            return jobList;

        }
        return List.of();
    }

    @Override
    public boolean isJobWithIdValid(Long id) {
        Optional<Job> job=jobRepository.findById(id);
        if(job.isPresent()) return true;
        return false;
    }
}
