package com.aladdinworksfourfourfour.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworksfourfourfour.dao.GenericDAO;
import com.aladdinworksfourfourfour.service.GenericService;
import com.aladdinworksfourfourfour.service.impl.GenericServiceImpl;
import com.aladdinworksfourfourfour.dao.NotificationDAO;
import com.aladdinworksfourfourfour.domain.Notification;
import com.aladdinworksfourfourfour.dto.NotificationDTO;
import com.aladdinworksfourfourfour.dto.NotificationSearchDTO;
import com.aladdinworksfourfourfour.dto.NotificationPageDTO;
import com.aladdinworksfourfourfour.dto.NotificationConvertCriteriaDTO;
import com.aladdinworksfourfourfour.dto.common.RequestDTO;
import com.aladdinworksfourfourfour.dto.common.ResultDTO;
import com.aladdinworksfourfourfour.service.NotificationService;
import com.aladdinworksfourfourfour.util.ControllerUtils;





@Service
public class NotificationServiceImpl extends GenericServiceImpl<Notification, Integer> implements NotificationService {

    private final static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	@Autowired
	NotificationDAO notificationDao;

	


	@Override
	public GenericDAO<Notification, Integer> getDAO() {
		return (GenericDAO<Notification, Integer>) notificationDao;
	}
	
	public List<Notification> findAll () {
		List<Notification> notifications = notificationDao.findAll();
		
		return notifications;	
		
	}

	public ResultDTO addNotification(NotificationDTO notificationDTO, RequestDTO requestDTO) {

		Notification notification = new Notification();

		notification.setNotificationId(notificationDTO.getNotificationId());


		notification.setDate(notificationDTO.getDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		notification = notificationDao.save(notification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Notification> getAllNotifications(Pageable pageable) {
		return notificationDao.findAll(pageable);
	}

	public Page<Notification> getAllNotifications(Specification<Notification> spec, Pageable pageable) {
		return notificationDao.findAll(spec, pageable);
	}

	public ResponseEntity<NotificationPageDTO> getNotifications(NotificationSearchDTO notificationSearchDTO) {
	
			Integer notificationId = notificationSearchDTO.getNotificationId(); 
   			String sortBy = notificationSearchDTO.getSortBy();
			String sortOrder = notificationSearchDTO.getSortOrder();
			String searchQuery = notificationSearchDTO.getSearchQuery();
			Integer page = notificationSearchDTO.getPage();
			Integer size = notificationSearchDTO.getSize();

	        Specification<Notification> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, notificationId, "notificationId"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Notification> notifications = this.getAllNotifications(spec, pageable);
		
		//System.out.println(String.valueOf(notifications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(notifications.getTotalPages()));
		
		List<Notification> notificationsList = notifications.getContent();
		
		NotificationConvertCriteriaDTO convertCriteria = new NotificationConvertCriteriaDTO();
		List<NotificationDTO> notificationDTOs = this.convertNotificationsToNotificationDTOs(notificationsList,convertCriteria);
		
		NotificationPageDTO notificationPageDTO = new NotificationPageDTO();
		notificationPageDTO.setNotifications(notificationDTOs);
		notificationPageDTO.setTotalElements(notifications.getTotalElements());
		return ResponseEntity.ok(notificationPageDTO);
	}

	public List<NotificationDTO> convertNotificationsToNotificationDTOs(List<Notification> notifications, NotificationConvertCriteriaDTO convertCriteria) {
		
		List<NotificationDTO> notificationDTOs = new ArrayList<NotificationDTO>();
		
		for (Notification notification : notifications) {
			notificationDTOs.add(convertNotificationToNotificationDTO(notification,convertCriteria));
		}
		
		return notificationDTOs;

	}
	
	public NotificationDTO convertNotificationToNotificationDTO(Notification notification, NotificationConvertCriteriaDTO convertCriteria) {
		
		NotificationDTO notificationDTO = new NotificationDTO();
		
		notificationDTO.setNotificationId(notification.getNotificationId());

	
		notificationDTO.setDate(notification.getDate());

	

		
		return notificationDTO;
	}

	public ResultDTO updateNotification(NotificationDTO notificationDTO, RequestDTO requestDTO) {
		
		Notification notification = notificationDao.getById(notificationDTO.getNotificationId());

		notification.setNotificationId(ControllerUtils.setValue(notification.getNotificationId(), notificationDTO.getNotificationId()));

		notification.setDate(ControllerUtils.setValue(notification.getDate(), notificationDTO.getDate()));



        notification = notificationDao.save(notification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public NotificationDTO getNotificationDTOById(Integer notificationId) {
	
		Notification notification = notificationDao.getById(notificationId);
			
		
		NotificationConvertCriteriaDTO convertCriteria = new NotificationConvertCriteriaDTO();
		return(this.convertNotificationToNotificationDTO(notification,convertCriteria));
	}







}
