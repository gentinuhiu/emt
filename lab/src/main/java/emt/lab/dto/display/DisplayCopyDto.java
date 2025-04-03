package emt.lab.dto.display;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.Copy;
import emt.lab.model.enumeration.QUALITY;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCopyDto(Long id, Book book, QUALITY quality, boolean rented) {
    public static DisplayCopyDto from(Copy copy){
        return new DisplayCopyDto(copy.getId(), copy.getBook(), copy.getQuality(), copy.isRented());
    }
    public static List<DisplayCopyDto> from(List<Copy> copies){
        return copies.stream().map(DisplayCopyDto::from).collect(Collectors.toList());
    }
}
