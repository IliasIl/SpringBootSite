package com.example.sweater.service;

import com.example.sweater.controller.ControllerUtils;
import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.domain.dto.MessageDto;
import com.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ControllerUtils controllerUtils;


    public void editMessage(User currentUser,
                            Message message,
                            String text,
                            String tag,
                            MultipartFile file) throws IOException {
        if (currentUser.getId().equals(message.getAuthor().getId())) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }
            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }
            if (!StringUtils.isEmpty(file)) {
                controllerUtils.saveImage(message, file);
            }
            messageRepo.save(message);
        }
    }

    public Page<MessageDto> findAll(Pageable pageable, String filter, User user) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTag(filter, pageable, user);
        } else {
            return messageRepo.findAll(pageable, user);
        }
    }

    public Page<MessageDto> findAll(Pageable pageable, User user) {
        return messageRepo.findAll(pageable, user);
    }

    public Page<MessageDto> findAllByUser(Pageable pageable, User userCurrent, User user) {
        return messageRepo.findByUser(pageable, userCurrent, user);
    }

    public Message save(Message message) {
        return messageRepo.save(message);
    }
}
