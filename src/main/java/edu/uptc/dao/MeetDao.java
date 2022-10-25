package edu.uptc.dao;

import edu.uptc.model.Meet;

public class MeetDao extends AbstracDao<Meet>{

    public MeetDao() {
        setEntityClass( Meet.class );
    }
}
