package com.example.dvdRental.services;

import com.example.dvdRental.api.model.FilmCategoryDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.FilmCategoryConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.FilmCategory;
import com.example.dvdRental.model.FilmCategory;
import com.example.dvdRental.model.key.FilmCategoryKey;
import com.example.dvdRental.repositories.FilmCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmCategoryServiceImpl implements FilmCategoryService {
    private final FilmCategoryRepository filmCategoryRepository;

    public FilmCategoryServiceImpl(FilmCategoryRepository filmCategoryRepository) {
        this.filmCategoryRepository = filmCategoryRepository;
    }


    @Override
    public List<FilmCategoryDTO> findAllFilmCategories() {
        return filmCategoryRepository
                .findAll()
                .stream()
                .map(FilmCategoryConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FilmCategoryDTO> findFilmCategoryById(FilmCategoryKey filmCategoryId) {
        Optional<FilmCategory> optionalFilmCategory = filmCategoryRepository.findById(filmCategoryId);

        if (optionalFilmCategory.isPresent()) return optionalFilmCategory.map(FilmCategoryConverter::toDTO);

        return Optional.empty();    }

    @Override
    public FilmCategoryDTO createNewFilmCategory(FilmCategoryDTO filmCategoryDTO) {
        FilmCategory filmCategory = FilmCategoryConverter.toEntity(filmCategoryDTO);
        FilmCategory savedFilmCategory = filmCategoryRepository.save(filmCategory);

        return FilmCategoryConverter.toDTO(savedFilmCategory);
    }

    @Override
    public FilmCategoryDTO updateFilmCategory(Integer filmCategoryId, FilmCategoryDTO filmCategoryDTO) {
        return null;
    }

    @Override
    public void deleteFilmCategory(Integer filmCategoryId) {

    }
}
