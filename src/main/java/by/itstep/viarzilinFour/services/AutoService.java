package by.itstep.viarzilinFour.services;

import by.itstep.viarzilinFour.daos.AutoDao;
import by.itstep.viarzilinFour.domain.Auto;
import by.itstep.viarzilinFour.domain.User;

public class AutoService {

    AutoDao autoDao = new AutoDao();

    public AutoService(){

    }

    public void saveAuto(Auto auto) {
        autoDao.save(auto);
    }

    public Auto findAuto(int id) {
        return autoDao.findById(id);
    }
}
