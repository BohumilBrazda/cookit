package cz.brazda.cookit.repository.entity;

import cz.brazda.cookit.common.DateElement;
import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.common.NameElement;

import javax.persistence.*;

/**
 * Created by BOBES on 8.2.2015.
 */
@MappedSuperclass
public abstract class Event implements NameElement, IdElement, DateElement {

}
