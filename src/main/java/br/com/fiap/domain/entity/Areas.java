package br.com.fiap.domain.entity;

public class Areas {

    private Long id;

    private String local;

    public Areas() {
    }

    public Areas(Long id, String local) {
        this.id = id;
        this.local = local;
    }

    public Long getId() {
        return id;
    }

    public Areas setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLocal() {
        return local;
    }

    public Areas setLocal(String local) {
        this.local = local;
        return this;
    }

    @Override
    public String toString() {
        return "Areas{" +
                "id=" + id +
                ", local='" + local + '\'' +
                '}';
    }
}
