package com.stackroute.newz.test.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class NewsSourceRepositoryTest {

    @Autowired
    private NewsSourceRepository newssourceRepository;
    private NewsSource newssource;

    @BeforeEach
    public void setUp() throws Exception {
    	newssource = new NewsSource();
    	newssource.setSourceId(1);
    	newssource.setSourceName("Cricket-Category");
    	newssource.setSourceDesc("All about Cricket");
    	newssource.setSourceCreatedBy("Jhon123");
    	newssource.getSourceCreationDate();
    }

    @AfterEach
    public void tearDown() throws Exception {

    	newssourceRepository.deleteAll();
    }

    @Test
    public void createNewssourceTest() {

    	newssourceRepository.insert(newssource);
    	NewsSource fetchedNewssource = newssourceRepository.findById(newssource.getSourceId()).get();
        assertThat(1, is(fetchedNewssource.getSourceId()));

    }

    @Test
    public void deleteNewssourcetest() {

    	newssourceRepository.insert(newssource);
    	NewsSource fetchedNewssource = newssourceRepository.findById(newssource.getSourceId()).get();
        assertThat(1, is(fetchedNewssource.getSourceId()));
        newssourceRepository.delete(fetchedNewssource);

    }

    @Test
    public void updateNewssourceTest() {

    	newssourceRepository.insert(newssource);
    	NewsSource fetchedNewssource = newssourceRepository.findById(newssource.getSourceId()).get();
        assertThat(1, is(fetchedNewssource.getSourceId()));
        fetchedNewssource.setSourceDesc("All about T-20");
        newssourceRepository.save(fetchedNewssource);
        fetchedNewssource = newssourceRepository.findById(newssource.getSourceId()).get();
        assertThat("All about T-20", is(fetchedNewssource.getSourceDesc()));


    }

    @Test
    public void getNewssourceByIdTest() {

    	newssourceRepository.insert(newssource);
    	NewsSource fetchedNewssource = newssourceRepository.findById(newssource.getSourceId()).get();
        assertThat(1, is(fetchedNewssource.getSourceId()));
    }


}
