package com.group.rh.service;

import com.group.rh.dto.ReqRes;
import com.group.rh.entity.Admin;
import com.group.rh.entity.Employe;
import com.group.rh.entity.EmployeRH;
import com.group.rh.entity.OurUsers;
import com.group.rh.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class UserManagementService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private EmployeService employeService;
    @Autowired
    private EmployeRHService employeRHService;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();

        try {
            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setRole(OurUsers.Role.valueOf(registrationRequest.getRole()));
            ourUser.setName(registrationRequest.getName());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            OurUsers ourUsersResult = usersRepo.save(ourUser);
            if (ourUsersResult.getId()>0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    // Ajout de la méthode pour créer un utilisateur avec le rôle EMPLOYEE
    public ReqRes registerEmployee(ReqRes registrationRequest) {
        registrationRequest.setRole("EMPLOYEE");
        return register(registrationRequest);
    }

    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            Optional<OurUsers> user = usersRepo.findByEmail(loginRequest.getEmail());
            var jwt = jwtUtils.generateToken(user.get());
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user.get());

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.get().getRole().name());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");

            if (user.get().getAdmin() != null) {
                Admin admin = user.get().getAdmin();
                response.setAdmin(admin);
                response.setNom(admin.getNom());
                response.setPrenom(admin.getPrenom());
                response.setNumeroTelephone(admin.getNumeroTelephone());
            }

            if (user.get().getEmploye() != null) {
                Employe employe = user.get().getEmploye();
                response.setEmploye(employe);
                response.setNom(employe.getNom());
                response.setPrenom(employe.getPrenom());
                response.setNumeroTelephone(employe.getNumDeTelephone());
                response.setEmail(employe.getEmail());
            } else if (user.get().getCandidat() != null) {
                response.setCandidat(user.get().getCandidat());
            } else if (user.get().getEmployeRH() != null) {
                EmployeRH employeRH = user.get().getEmployeRH();
                response.setEmployeRH(employeRH);
                response.setNom(employeRH.getNom());
                response.setPrenom(employeRH.getPrenom());
                response.setNumeroTelephone(employeRH.getNumDeTelephone());
                response.setEmail(employeRH.getEmail());
            }

            response.setMessage("Successfully Logged In!");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            OurUsers users = usersRepo.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)){
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hrs");
                response.setMessage("Successfully Refreshed Token!");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public ReqRes getAllUsers(){
        ReqRes reqRes = new ReqRes();

        try{
            List<OurUsers> result = usersRepo.findAll();
            if (!result.isEmpty()){
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful!");
            }else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No Users Found!");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage(e.getMessage());
            return reqRes;
        }
        return reqRes;
    }

    public ReqRes getUserById(Integer id){
        ReqRes reqRes = new ReqRes();
        try{
            OurUsers userById = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found!"));
            reqRes.setOurUsers(userById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("User with id " + id + " found successfully!");

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error Occurred " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes deleteUser(Integer userId){
        ReqRes reqRes = new ReqRes();
        try{
            Optional<OurUsers> usersOptional = usersRepo.findById(userId);
            if (usersOptional.isPresent()){
                usersRepo.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User Deleted Successfully!");
            }else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion!");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user " + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes updateUser(Integer userId, OurUsers updatedUser){
        ReqRes reqRes = new ReqRes();
        try{
            Optional<OurUsers> usersOptional = usersRepo.findById(userId);
            if (usersOptional.isPresent()){
                OurUsers existingUser = usersOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setRole(updatedUser.getRole());

                //check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()){
                    //encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                OurUsers savedUser = usersRepo.save(existingUser);
                reqRes.setOurUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully!");
            }else{
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update!");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user!" + e.getMessage());
        }
        return reqRes;
    }

    public ReqRes getMyInfo(String email) {
        ReqRes reqRes = new ReqRes();
        try {
            Optional<OurUsers> userOptional = usersRepo.findByEmail(email);
            if (userOptional.isPresent()) {
                OurUsers user = userOptional.get();
                reqRes.setOurUsers(user);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful!");



                if (user.getAdmin() != null) {
                    Admin admin = user.getAdmin();
                    reqRes.setAdmin(admin);
                    reqRes.setNom(admin.getNom());
                    reqRes.setPrenom(admin.getPrenom());
                    reqRes.setNumeroTelephone(admin.getNumeroTelephone());
                } else if (user.getEmploye() != null) {
                    Employe employe = user.getEmploye();
                    reqRes.setEmploye(employe);
                    reqRes.setNom(employe.getNom());
                    reqRes.setPrenom(employe.getPrenom());
                    reqRes.setNumeroTelephone(employe.getNumDeTelephone());
                    reqRes.setAdresse(employe.getAdresse());
                    reqRes.setEmail(employe.getEmail());
                    reqRes.setSalaire(employe.getSalaire());
                    reqRes.setDepartement(employe.getDepartement());
                    reqRes.setPoste(employe.getPoste());
                    reqRes.setDateEmbauche(employe.getDateEmbauche());
                } else if (user.getEmployeRH() != null) {
                    EmployeRH employeRH = user.getEmployeRH();
                    reqRes.setEmployeRH(employeRH);
                    reqRes.setNom(employeRH.getNom());
                    reqRes.setPrenom(employeRH.getPrenom());
                    reqRes.setNumeroTelephone(employeRH.getNumDeTelephone());
                    reqRes.setAdresse(employeRH.getAdresse());
                    reqRes.setEmail(employeRH.getEmail());
                    reqRes.setSalaire(employeRH.getSalaire());
                    reqRes.setDepartement(employeRH.getDepartement());
                    reqRes.setPoste(employeRH.getPoste());
                    reqRes.setDateEmbauche(employeRH.getDateEmbauche());

            } else if (user.getCandidat() != null) {
                reqRes.setCandidat(user.getCandidat());}



            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found!");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info" + e.getMessage());
        }
        return reqRes;
    }


}
