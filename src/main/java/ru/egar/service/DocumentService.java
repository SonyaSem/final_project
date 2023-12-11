package ru.egar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egar.dto.document.*;
import ru.egar.exception.NotFoundException;
import ru.egar.mapper.*;
import ru.egar.model.Dismissal;
import ru.egar.model.Document;
import ru.egar.model.Hiring;
import ru.egar.model.Transfer;
import ru.egar.reporitory.DismissalRepository;
import ru.egar.reporitory.DocumentRepository;
import ru.egar.reporitory.HiringRepository;
import ru.egar.reporitory.TransferRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final HiringRepository hiringRepository;
    private final TransferRepository transferRepository;
    private final DismissalRepository dismissalRepository;

    private final DocumentMapper documentMapper;
    private final HiringMapper hiringMapper;
    private final TransferMapper transferMapper;
    private final DismissalMapper dismissalMapper;


    private final RegisterMapper mapper;

    public List<RegisterDTO> listDocument() {
        List<RegisterDTO> list = documentRepository.findAll().stream().map(mapper::toDocumentDTO).collect(Collectors.toList());
        return list;
    }

    public List<RegisterDTO> getListEmployeeDocument(Integer number){
        List<RegisterDTO> list  = documentRepository.findByPersonnelNumber(number).stream().map(mapper::toDocumentDTO).collect(Collectors.toList());
        return list;
    }

    @Transactional
    public void saveHiring(DocumentDTO documentDTO, HiringDTO hiringDTO) {

        Document document = documentMapper.toDocument(documentDTO);
        Hiring hiring = hiringMapper.toHiring(hiringDTO);

        hiring.setIdDocument(documentRepository.save(document));
        hiringRepository.save(hiring);
        log.info("Документ: {}", document.toString());
        log.info("Найм: {}", hiring.toString());
    }

    @Transactional
    public void saveTransfer(DocumentDTO documentDTO, TransferDTO transferDTO) {
        Document document = documentMapper.toDocument(documentDTO);
        Transfer transfer = transferMapper.toTransfer(transferDTO);
        transfer.setIdDocument(documentRepository.save(document));
        transferRepository.save(transfer);
        log.info("Документ: {}", document.toString());
        log.info("Перевод: {}", transfer.toString());

    }

    @Transactional
    public void saveDismissal(DocumentDTO documentDTO, DismissalDTO dismissalDTO) {
        Document document = documentMapper.toDocument(documentDTO);
        Dismissal dismissal = dismissalMapper.toDismissal(dismissalDTO);
        dismissal.setIdDocument(documentRepository.save(document));
        dismissalRepository.save(dismissal);
        log.info("Документ: {}", document.toString());
        log.info("Увольнение: {}",dismissal.toString());
    }

}
