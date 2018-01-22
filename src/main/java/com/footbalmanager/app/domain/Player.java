package com.footbalmanager.app.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static org.springframework.util.Assert.notNull;

@Entity
public class Player implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstname;
    @Column(name = "last_name", nullable = false)
    private String lastname;
	@Column(nullable = false)
	private String username;
    @Column(nullable = false)
    private String password;
	@Column(nullable = false)
	private String email;
	@ManyToOne
	@JoinColumn(name = "team_id")
    private Team team;

    Player() {
    }

	public Player(String firstname, String lastname, String username, String password, String email) {
		notNull(firstname, "Please provide firstname");
		notNull(lastname, "Please provide lastname");
		notNull(username, "Please provide username");
		notNull(password, "Please provide password");
		notNull(email, "Please provide email");
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
