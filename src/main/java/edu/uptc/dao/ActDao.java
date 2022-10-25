package edu.uptc.dao;

import edu.uptc.model.Act;

public class ActDao extends AbstracDao<Act>{

    public ActDao() {
        setEntityClass(Act.class );
    }
}
