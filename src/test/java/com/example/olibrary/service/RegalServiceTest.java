package com.example.olibrary.service;

import com.example.olibrary.model.Book;
import com.example.olibrary.model.Regal;
import com.example.olibrary.repository.RegalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegalServiceTest {
    @Mock
    private RegalRepository regalRepository;

    @InjectMocks
    private  RegalService regalService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegal() {
        Regal regal = new Regal();
        regal.setId(1L);
        regal.setName("Name");
        regal.setDescription("Description");
        regal.setBooksId(new ArrayList<>(Arrays.asList(new Book(), new Book())));

        when(regalRepository.findById(1L)).thenReturn(Optional.of(regal));

        regalService.saveRegal(regal);
        Regal test = regalService.getRegalById(1L);
        assertEquals(1L, test.getId());
        assertEquals("Name", test.getName());
        assertEquals("Description", test.getDescription());
        assertEquals(2, test.getBooksId().size());
        verify(regalRepository, times(1)).findById(1L);
    }
}
