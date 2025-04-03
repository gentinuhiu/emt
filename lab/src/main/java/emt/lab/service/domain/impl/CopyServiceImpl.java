package emt.lab.service.domain.impl;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.Copy;
import emt.lab.repository.BookRepository;
import emt.lab.repository.CopyRepository;
import emt.lab.service.domain.BookService;
import emt.lab.service.domain.CopyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopyServiceImpl implements CopyService {
    private final CopyRepository copyRepository;

    public CopyServiceImpl(CopyRepository copyRepository) {
        this.copyRepository = copyRepository;
    }

    @Override
    public List<Copy> findAll() {
        return copyRepository.findAll();
    }

    @Override
    public Optional<Copy> findById(Long id) {
        return copyRepository.findById(id);
    }

    @Override
    public Optional<Copy> update(Long id, Copy copy) {
        return copyRepository.findById(id).map(existingCopy -> {
            if(copy.getQuality() != null){
                existingCopy.setQuality(copy.getQuality());
            }
            existingCopy.setRented(copy.isRented());
            return copyRepository.save(existingCopy);
        });
    }

    @Override
    public Optional<Copy> save(Copy copy) {
        return Optional.of(copyRepository.save(copy));
    }

    @Override
    public void deleteById(Long id) {
//        List<Book> books = bookRepository.findAll();
//        for(Book book : books){
//            if(book.getCopies().removeIf(c -> c.getId().equals(id))){
//                bookRepository.save(book);
//                break;
//            }
//        }

        copyRepository.deleteById(id);
    }
}
