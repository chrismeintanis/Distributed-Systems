package com.distributed.systems.service.impl;

import com.distributed.systems.dto.ApplicationDto;
import com.distributed.systems.dto.UserDto;
import com.distributed.systems.entity.Application;
import com.distributed.systems.entity.User;
import com.distributed.systems.repository.ApplicationRepository;
import com.distributed.systems.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    public ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void saveApplication(ApplicationDto applicationDto) {
        Application application = new Application();
//        applicationDto.setApplicationDate(new Date(System.currentTimeMillis()));
        applicationDto.setState("Pending");
        application.setApplicationId(applicationDto.getApplicationId());
        application.setApplicantsAFM(applicationDto.getApplicantsAFM());
//        application.setApplicationDate(applicationDto.getApplicationDate());
        application.setState(applicationDto.getState());
        application.setLocation(applicationDto.getLocation());
        application.setDescription(applicationDto.getDescription());
        application.setStaffId(applicationDto.getStaffId());

        applicationRepository.save(application);
    }

    @Override
    public Application findApplicationByApplicantsAFMAndState(String applicantsAFM, String state) {
        return applicationRepository.findByApplicantsAFMAndState(applicantsAFM,state);
    }

    @Override
    public ApplicationDto getApplicationById(Long applicationId) {
        Application application = applicationRepository.findById(applicationId).get();
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setApplicantsAFM(application.getApplicantsAFM());
//        applicationDto.setApplicationDate(application.getApplicationDate());
        applicationDto.setApplicationId(application.getApplicationId());
        applicationDto.setLocation(application.getLocation());
        applicationDto.setState(application.getState());
        applicationDto.setStaffId(application.getStaffId());
        applicationDto.setDescription(application.getDescription());
        return applicationDto;
    }

    @Override
    public void updateApplication(ApplicationDto applicationDto) {
        System.out.println(applicationDto.toString());
        Application application = new Application();
        application.setApplicationId(applicationDto.getApplicationId());
        application.setApplicantsAFM(applicationDto.getApplicantsAFM());
//        application.setApplicationDate(applicationDto.getApplicationDate());
        application.setState(applicationDto.getState());
        application.setLocation(applicationDto.getLocation());
        application.setDescription(applicationDto.getDescription());
        application.setStaffId(applicationDto.getStaffId());

        applicationRepository.save(application);
    }

    @Override
    public List<ApplicationDto> findAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream().map((application) -> mapToApplicationDto(application)).collect(Collectors.toList());
    }

    private ApplicationDto mapToApplicationDto(Application application){
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setApplicantsAFM(application.getApplicantsAFM());
        applicationDto.setLocation(application.getLocation());
        applicationDto.setDescription(application.getDescription());
        applicationDto.setApplicationId(application.getApplicationId());
        applicationDto.setState(application.getState());
        applicationDto.setStaffId(application.getStaffId());
        return applicationDto;
    }


//    @Override
//    public Application findApplicationById(Long id) {
//        return applicationRepository.findAllByApplicationId(id);
//    }


}
