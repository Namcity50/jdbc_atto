package container;

import controller.*;
import dto.Profile;

import java.util.Scanner;

public class ComponentContainer {
    public static Scanner scanInt = new Scanner(System.in);
    public static Scanner scanStr = new Scanner(System.in);
    public static Profile CURRENT_PROFILE;
    public static Profile CURRENT_ADMIN;
    public static String getMenu(){
        return "Not Found : ";
    }
    public Integer getAction(){
        return ComponentContainer.scanInt.nextInt();
    }


}
