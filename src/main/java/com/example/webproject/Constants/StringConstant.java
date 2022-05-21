package com.example.webproject.Constants;

import java.util.ArrayList;
import java.util.List;

public class StringConstant {
    public static final String MAIN = "main";
    public static final String SLMAIN = "/main";
    public static final String REDMAIN = "redirect:/main";
    public static final String ADD = "Add";
    public static final String SLADD = "/Add";
    public static final String REDADD = "redirect:/Add";
    public static final String HISTORY = "history";
    public static final String SLHISTORY = "/history/{id}";
    public static final String REDHISTORY = "redirect:/history/{id}";
    public static final String SLREGISTRATION =  "/registration";
    public static final String REGISTRATION = "registration";
    public static final String REDREGISTRATION ="redirect:/registration";
    public static final String SLREPLENISHMENT_ID = "/replenishment/{id}";
    public static final String SLREPLENISHMENT = "/replenishment";
    public static final String REPLENISHMENT = "replenishment";
    public static final String REDREPLENISHMENT ="redirect:/replenishment" ;
    public static final String SLTRANSLATION_ID = "/translation/{id}";
    public static final String SLTRANSLATION = "/translation";
    public static final String TRANSLATION = "translation" ;
    public static final String REDTRANSLATION = "redirect:/translation";
    public static final String REDLOGIN ="redirect:/login" ;
    public static final String PROFILE = "Profile";
    public static final String SLPROFILE = "/Profile";
    public static final String REDPROFILE = "redirect:/Profile";
    public static final String  SLCARDVIEW_ID = "/CardView/{id}";
    public static final String  CARDVIEW = "CardView";
    public static final String  SLADDCARD_ID = "/AddCard/{id}";
    public static final String  ADDCARD = "AddCard";
    public static final String SLREDCARDVIEW = "redirect:/CardView/{id}";
    public static final String INVALIDSESSION = "InvalidSession";
    public static final String SLINVALIDSESSION = "/InvalidSession";
    public static final String SLINVALIDHISTORY = "/InvalidHistory";
    public static final String INVALIDHISTORY = "InvalidHistory";
    public static final String SLREQUEST = "/Request";
    public static final String REQUEST = "Request";
    public static final String REDREQUEST = "redirect:/Request";
    public static final String SLREQUESTADD_ID = "/RequestAdd/{id}";
    public static final String SLREQUESTADD = "/RequestAdd";
    public static final String REQUESTADD = "RequestAdd";
    public static final String MESSAGE_ID = "Message/{id}";
    public static final String SLMESSAGE_ID = "/Message/{id}";
    public static final String MESSAGE = "Message";
    public static final String SLMESSAGE = "/Message";
    public static final String SLNOTIFICATION = "/Notification";
    public static final String NOTIFICATION = "Notification";
    public static final String REDNOTIFICATION = "redirect:/Notification";
    public static final String SLREQUESTCHANGE_ID= "/RequestChange/{id}";
    public static final String REQUESTCHANGED= "RequestChange";
    public static final String SLREQUESTCHANGE= "/RequestChange";

    public static final String SLNOTIFICATIONANSWER_ID = "/NotificationAnswer/{id}";
    public static final String SLNOTIFICATIONANSWER = "/NotificationAnswer";
    public static final String NOTIFICATIONANSWER = "NotificationAnswer";




    public static List<String> statuslist()
    {
        List<String> list = new ArrayList<>();
        list.add("Сreated");
        list.add("Agreed");
        list.add("In progress");
        list.add("Denied");
        list.add("TimeOut");
        return list;
    }







}
