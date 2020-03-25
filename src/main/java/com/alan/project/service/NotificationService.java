package com.alan.project.service;

import com.alan.project.dao.Notification;
import com.alan.project.dao.Pagination;
import com.alan.project.dto.NotificationDTO;
import com.alan.project.entity.Page;
import com.alan.project.mapper.NotificationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NotificationService {

    @Resource
    private NotificationMapper notificationMapper;

    public NotificationDTO getNotifications( String uid,Integer currentPage, Integer pageSize) {
        Integer totalPage;
        boolean more;
        Integer totalCount = notificationMapper.getTotalByUid(uid);
        if (totalCount % pageSize == 0)
            totalPage = totalCount / pageSize;
        else
            totalPage = totalCount / pageSize + 1;
        if (currentPage == totalPage)
            more = false;
        else
            more = true;
        Integer offset = pageSize * (currentPage - 1);
        Page page = new Page();
        page.setTotal(totalCount);
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setMore(more);
        Pagination<String> pagination = new Pagination<>();
        pagination.setId(uid);
        pagination.setOffset(offset);
        pagination.setSize(pageSize);
        List<Notification> notificationList = notificationMapper.getNotifications(pagination);
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationList(notificationList);
        notificationDTO.setPage(page);
        return notificationDTO;
    }

    public Integer getUnreadCounts(String uid, int status) {
        return notificationMapper.getUnreadCounts(uid,status);
    }

    public void updateNotificationStatus(String uid, int status) {
        notificationMapper.updateNotificationStatus(uid,status);
    }
}
