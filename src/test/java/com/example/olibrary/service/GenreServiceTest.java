package com.example.olibrary.service;

import com.example.olibrary.model.Genre;
import com.example.olibrary.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GenreServiceTest {
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private  GenreService genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenreCRUD() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Name");
        genre.setDescription("Description");

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        genreService.saveGenre(genre);

        Genre saved = genreService.getGenreById(1L);
        assertEquals(1L, saved.getId());
        assertEquals("Name", saved.getName());
        assertEquals("Description", saved.getDescription());
    }

    @Test
    public void testGenreFindByName() {
        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Name");
        genre1.setDescription("Description");

        when(genreRepository.findGenreByName("am")).thenReturn(new ArrayList<>(List.of(genre1)));
        when(genreRepository.findGenreByName("0")).thenReturn(new ArrayList<>(List.of()));

        genreService.saveGenre(genre1);

        List<Genre> saved1 = genreService.findGenreByName("am");
        assertEquals(1, saved1.size());
        assertEquals("Description", saved1.get(0).getDescription());
        List<Genre> saved2 = genreService.findGenreByName("0");
        assertEquals(0, saved2.size());
    }
}
