package com.example.hobbie.service.impl;

import com.example.hobbie.view.EntryViewModel;
import com.example.hobbie.model.entities.Entry;
import com.example.hobbie.model.repostiory.EntryRepository;
import com.example.hobbie.service.EntryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;
    private final ModelMapper modelMapper;

    public EntryServiceImpl(EntryRepository entryRepository, ModelMapper modelMapper) {
        this.entryRepository = entryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EntryViewModel> getAboEntries(Long id) {
        List<Entry> allByAboId = this.entryRepository.findAllByAboId(id);
        return allByAboId.stream().map(entry -> this.modelMapper.map(entry, EntryViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<Entry> saveAboEntries(List<Entry> aboEntries) {

        return this.entryRepository.saveAll(aboEntries);
    }

    @Override
    public void saveUpdatedEntry(Long entryId) {
        Optional<Entry> entry = this.entryRepository.findById(entryId);
        if(entry.isPresent()){
            entry.get().setDate(Calendar.getInstance().getTime());
            this.entryRepository.save(entry.get());
        }
        else {
            throw new NullPointerException();
        }

    }


}
