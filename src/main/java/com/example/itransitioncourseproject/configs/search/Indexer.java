package com.example.itransitioncourseproject.configs.search;

import com.example.itransitioncourseproject.entities.Item;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
@RequiredArgsConstructor
public class Indexer {

    private final EntityManager entityManager;

    public void indexSchema() {
        try {
            callMassIndexer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void callMassIndexer() throws InterruptedException {
        SearchSession searchSession = Search.session(entityManager);

        searchSession
                .massIndexer(Item.class)
                .threadsToLoadObjects(7)
                .startAndWait();
    }
}
