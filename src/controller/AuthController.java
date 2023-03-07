package controller;

import container.ComponentContainer;
import container.ComponentController;
import container.ComponentService;
import dto.Profile;
import enums.EnumsRole;
import util.MD5Util;

public class AuthController {
    public void start() {

        boolean b = true;
        int act;
        while (b) {
            menuRegistration();
            act = ComponentContainer.scanInt.nextInt();
            switch (act) {
                case 1 -> registrationSignIn();
                case 2 -> registrationSignUp();
                default -> {return;}
            }
        }
    }

    public void registrationSignUp() {
        System.out.print(" Enter name : ");
        String name = ComponentContainer.scanStr.nextLine();
        System.out.print(" Enter surname : ");
        String surname = ComponentContainer.scanStr.nextLine();
        System.out.print(" Enter phone : ");
        String phone = ComponentContainer.scanStr.nextLine();
        System.out.print(" Enter password : ");
        String password = ComponentContainer.scanStr.nextLine();
        Profile profile = new Profile();
        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setPassword(MD5Util.encode(password));
        profile.setVisible(true);
        Boolean b = ComponentService.profileService.addProfile(profile);
        if (b == true) {
            ComponentContainer.CURRENT_PROFILE = profile;
            ComponentController.profileCardController.menuProfileCard();
        }
    }

    public void registrationSignIn() {
        Profile profile = new Profile();
        System.out.println(" Pnone Number: ");
        String phone = ComponentContainer.scanStr.nextLine();
        System.out.println(" Password: ");
        String password = ComponentContainer.scanStr.nextLine();
        profile.setPhone(phone);
        profile.setPassword(password);
        Profile exists = ComponentService.adminService.addAdmin(profile);
        boolean exist = ComponentService.profileService.profileSingIn(profile);
        if (exists != null && exists.getRole().equals(EnumsRole.ADMIN.name())) {
            ComponentContainer.CURRENT_PROFILE = exists;
            ComponentController.adminController.start();
        } else if (exist) {
            ComponentContainer.CURRENT_PROFILE = profile;
            ComponentController.profileController.start();
            System.out.print(" Select : ");

        } else {
            System.out.println(" Not fount profile: ");
        }
    }

    public void menuRegistration() {
        System.out.println("Welcome atto service: ");
        String menu = """
                1 -> Sign In:
                2 -> Sign Up:
                """;
        System.out.println(menu);
        System.out.print(" Select : ");
    }
}
