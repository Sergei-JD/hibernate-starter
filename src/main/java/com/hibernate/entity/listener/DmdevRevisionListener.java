package com.hibernate.entity.listener;

import com.hibernate.entity.Revision;
import org.hibernate.envers.RevisionListener;

public class DmdevRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        // SecurityContext.getUser().getId()
        ((Revision) revisionEntity).setUsername("dmdev");
    }
}
