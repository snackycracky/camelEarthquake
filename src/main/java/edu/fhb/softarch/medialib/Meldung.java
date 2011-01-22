package edu.fhb.softarch.medialib;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class Meldung {

    private Integer id;
    private String  name;

    public Meldung() {
    }

    public Meldung(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("{id=%s,name=%s}", id, name);
    }

}