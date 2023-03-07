package controller;

import container.ComponentContainer;
import container.ComponentRepository;
import container.ComponentService;
import dto.Profile;
import enums.EnumsRole;
import repository.ProfileRepository;
import service.ProfileService;

import java.util.Arrays;
import java.util.List;

public class AdminProfileController {

    public void menuProfile() {
        String menu = """
                1 -> Profile List:
                2 -> Update Profile Status:
                0 -> Back:
                """;
        System.out.println(menu);
    }
    public void start(){
        boolean flag = true;
        int act;
        while (flag){
            menuProfile();
            act = ComponentContainer.scanInt.nextInt();
            switch (act){
                case 1 -> profileList();
                case 2 -> updatedProfileStatus();
                case 0 -> flag = false;
                default -> System.out.println(ComponentContainer.getMenu());
            }
        }
    }

    public void updatedProfileStatus() {
        System.out.println("Phone number: ");
        String num = ComponentContainer.scanStr.nextLine();
        ComponentService.profileService.updateStatus(num);
    }

    public void profileList() {
        List<Profile> profileList = ComponentRepository.profileRepository.getByProfileList();
        for (Profile profile: profileList){
            if (profile.getRole().equals(EnumsRole.User.name())){
                System.out.println(profile.toString());
            }
        }
    }
}
