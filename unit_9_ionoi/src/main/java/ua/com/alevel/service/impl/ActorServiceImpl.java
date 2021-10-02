package ua.com.alevel.service.impl;

import ua.com.alevel.dao.ActorDao;
import ua.com.alevel.dao.impl.ActorDaoImpl;
import ua.com.alevel.entity.Actor;
import ua.com.alevel.service.ActorService;

import java.util.List;

import static ua.com.alevel.util.Constant.FIRST_INDEX;
import static ua.com.alevel.util.CustomUtil.checksActorFullName;

public class ActorServiceImpl implements ActorService {

    private final ActorDao actorDao = new ActorDaoImpl();

    public void create(Actor filmActor) {
        if (!checksActorFullName(filmActor.getFullName())) {
            return;
        }
        List<Actor> allFilmActors = findAllActors();
        if (allFilmActors != null) {
            for (Actor actorInBase : allFilmActors) {
                if (actorInBase.getFullName().equals(filmActor.getFullName())) {
                    actorDao.updateListOfIdFilms(filmActor.getIdFilms()[FIRST_INDEX], actorInBase);
                    return;
                }
            }
        }
        actorDao.create(filmActor);
    }

    public void delete(int id) {
        actorDao.delete(id);
    }

    public Actor findActorById(int id) {
        return actorDao.findActorById(id);
    }

    public List<Actor> findAllActors() {
        return actorDao.findAllActor();
    }

    public int findIdByFullName(String fullName) {
        return actorDao.findIdByFullName(fullName);
    }

    public void delete(int id, Actor actor) {
        actorDao.delete(id, actor);
    }

    public void updateArrayOfIdFilms(int idFilms, Actor actor) {
        actorDao.updateListOfIdFilms(idFilms, actor);
    }
}
