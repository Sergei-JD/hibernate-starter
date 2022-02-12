package com.hibernate.entity;

import lombok.Data;
import lombok.Builder;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SortNatural;

import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.MapKeyColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
//@BatchSize(size = 3)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "username")
    @SortNatural
    private Map<String, User> users = new TreeMap<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locale", joinColumns = @JoinColumn(name = "company_id"))
//    @AttributeOverride(name = "lang", column = @Column(name = "language"))
//    private List<LocaleInfo> locales = new ArrayList<>();
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUsername(), user);
        user.setCompany(this);
    }
}
