package com.example.olibrary.service;

import com.example.olibrary.model.Genre;
import com.example.olibrary.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void testGenre() {
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
        verify(genreRepository, times(1)).findById(1L);
    }
}
