package org.tyaa.demo.java.spring.gae.repositories;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import org.springframework.stereotype.Repository;
import org.tyaa.demo.java.spring.gae.models.Author;
import org.tyaa.demo.java.spring.gae.utils.CopyHelper;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class AuthorDataStoreDao extends AbstractDao<Author>{

    public Author read(String _name) throws Exception {
        Author authorEntity = null;
        Author finalAuthorEntity = new Author();
        ObjectifyService.run(new VoidWork() {
            @Override
            public void vrun() {
                Author authorEntityResult =
                        ofy().load().type(Author.class)
                                .filter("name", _name)
                                .first()
                                .now();
                if (authorEntityResult != null) {
                    CopyHelper.copy(authorEntityResult, finalAuthorEntity);
                }
            }
        });
        authorEntity = finalAuthorEntity;
        return authorEntity;
    }
}
