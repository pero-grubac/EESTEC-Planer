package com.eestec.planer.service;

import com.eestec.planer.dto.ObjavaDTO;
import org.springframework.stereotype.Service;


public interface ObjavaService {


     boolean deleteNotificationById(int notificationId);


    ObjavaDTO createNotification(String sadrzaj, Integer idKorisnika);

    ObjavaDTO updateObjava(ObjavaDTO objavaDTO);

    //  boolean deleteNotification(Integer id);
}
