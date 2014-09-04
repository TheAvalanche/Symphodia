package org.symphodia.common.band.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by alekkart on 2014.09.02..
 */
@Entity
@Table(name = "BAND")
public class Band {

    @Id
    private long id;

    private String name;

    @OneToMany
    private List<Member> memberList;

    @OneToMany
    private List<News> newsList;


}
