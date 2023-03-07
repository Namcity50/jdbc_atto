package service;

import container.ComponentContainer;
import container.ComponentRepository;
import dto.Profile;
import enums.EnumStatus;
import enums.EnumsRole;
import util.MD5Util;

import java.time.LocalDate;

public class ProfileService {
    public boolean addProfile(Profile profile) {
        Profile exist = ComponentRepository.profileRepository.getByProfilePhone(profile.getPhone());
        if (exist != null) {
            System.out.println(" Mazgi this phone exist : ");
            return false;
        }
        profile.setCreated_date(LocalDate.now());
        profile.setRole(String.valueOf(EnumsRole.User));
        profile.setStatus(String.valueOf(EnumStatus.ACTIVE));
        ComponentRepository.profileRepository.saveProfile(profile);
        return true;
    }
    public boolean profileSingIn(Profile profile) {

        Profile exist = ComponentRepository.profileRepository.getByPhoneAndPassword(profile.getPhone(), MD5Util.encode(profile.getPassword()));
        if (exist!=null ){
            return true;
        }
        System.out.println(" Mazgi phone number or password not found: ");
        return false;
    }

    public void updateStatus(String num) {
        Profile exist = ComponentRepository.profileRepository.getByProfilePhone(num);
        if (exist == null){
            System.out.println(ComponentContainer.getMenu());
        }
       else if (exist.getRole().equals(EnumsRole.User.name()) && exist.getStatus().equals(EnumStatus.ACTIVE.name())){
            ComponentRepository.profileRepository.updateStatusProfileQuery(EnumStatus.BLOCE.name(),num);
        }else if (exist.getRole().equals(EnumsRole.User.name()) && exist.getStatus().equals(EnumStatus.BLOCE.name())){
            ComponentRepository.profileRepository.updateStatusProfileQuery(EnumStatus.ACTIVE.name(),num);
        }
    }
}
