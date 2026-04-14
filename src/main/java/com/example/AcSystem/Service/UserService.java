package com.example.AcSystem.Service;

import com.example.AcSystem.Entity.Company;
import com.example.AcSystem.Entity.User;
import com.example.AcSystem.Exception.UserAlreadyExistsException;
import com.example.AcSystem.Exception.UserNotFoundException;
import com.example.AcSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CompanyService companyService;

    @Transactional(readOnly = true)
    public User getUser(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Transactional
    public User createUser(String login, String password, String email, String nameCompany, boolean isCeo){
        try {
            User user = new User(login, password, email);
            User saved = userRepository.save(user);
            if(nameCompany != null) {
                Company company = companyService.getCompany(nameCompany);
                if (company != null) {
                    saved.setCompany(company);
                    companyService.addUser(company, saved);
                    if (isCeo) {
                        companyService.addCeo(company, saved);
                        log.info("ceo added");
                    }
                }
            }
            log.info("User created");
            return saved;
        }
        catch(DataIntegrityViolationException e){
            throw new UserAlreadyExistsException("user already exists");
        }
    }

    @Transactional
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getRandom(){
        List<User> users = userRepository.findAll();
        if(!users.isEmpty()) {
            int bound = users.size();
            int rand = ThreadLocalRandom.current().nextInt(bound);
            return users.get(rand);
        }
        else{
            return null;
        }
    }
}
