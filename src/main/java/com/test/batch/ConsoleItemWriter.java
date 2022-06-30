package com.test.batch;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.istack.NotNull;
import com.test.model.LeadEntity;
import com.test.model.LeadFile;
import com.test.repository.LeadFileRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleItemWriter<T> implements ItemWriter<T> {
    @Autowired
    LeadFileRepository repository;

    @Override
    public void write(List<? extends T> items) throws Exception {
        List<LeadEntity> entities = items.stream().map(data -> this.toEntity((LeadFile)data)).collect(Collectors.toList());
        repository.saveAll(entities);
    }

    public LeadEntity toEntity(LeadFile file){
        LeadEntity leadEntity = new LeadEntity();
        leadEntity.setComment(file.getComment());
        leadEntity.setEmail(file.getEmail());
        leadEntity.setFirstName(file.getFirstName());
        leadEntity.setLastName(file.getLastName());
        leadEntity.setPhone(file.getPhone());
        return leadEntity;
    }


}