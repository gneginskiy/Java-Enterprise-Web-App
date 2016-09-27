package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (int i = 0; i < 10; i++) {
            save(new User(null,"user#"+i,"email#"+i,"password"+i, Role.ROLE_USER));
        }
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()){
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(),user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        repository.remove(id);
        return true;
    }


    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return new ArrayList<>(repository.values());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return getAll().stream().filter(user -> email.equals(user.getEmail())).findFirst().orElse(null);
    }
}