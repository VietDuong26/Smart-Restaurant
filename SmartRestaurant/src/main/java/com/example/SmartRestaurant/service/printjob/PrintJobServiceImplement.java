package com.example.SmartRestaurant.service.printjob;

import com.example.SmartRestaurant.dto.request.PrintJobRequest;
import com.example.SmartRestaurant.entity.PrintJobEntity;
import com.example.SmartRestaurant.mapper.PrintJobMapper;
import com.example.SmartRestaurant.repository.PrintJobRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class PrintJobServiceImplement implements PrintJobService {
    PrintJobRepository repository;

    PrintJobMapper mapper;

    SimpMessagingTemplate messagingTemplate;

    @Override
    public void create(PrintJobRequest request) {
        PrintJobEntity printJob = mapper.toEntity(request);
        PrintJobEntity savedPrintJob = repository.save(printJob);
        messagingTemplate.convertAndSend("/host/" + savedPrintJob.getHostDevice().getId(), savedPrintJob);
    }
}
