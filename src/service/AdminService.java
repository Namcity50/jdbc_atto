package service;

import container.ComponentRepository;
import container.ComponentService;
import dto.Profile;
import enums.EnumsRole;
import repository.ProfileRepository;

public class AdminService {

    public Profile addAdmin(Profile profile) {
        Profile exist = ComponentRepository.profileRepository.getByProfilePhone(profile.getPhone());
        if (exist.getRole().equals(EnumsRole.ADMIN.name())){
            return exist;
        }
        return null;
    }
}
